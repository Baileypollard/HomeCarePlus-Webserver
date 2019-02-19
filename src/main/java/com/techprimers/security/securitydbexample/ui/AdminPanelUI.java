package com.techprimers.security.securitydbexample.ui;

import com.techprimers.security.securitydbexample.service.AppointmentServiceImpl;
import com.techprimers.security.securitydbexample.service.ClientServiceImpl;
import com.techprimers.security.securitydbexample.service.EmployeeServiceImpl;
import com.techprimers.security.securitydbexample.ui.pages.AppointmentPage;
import com.techprimers.security.securitydbexample.ui.pages.ClientPage;
import com.techprimers.security.securitydbexample.ui.pages.EmployeePage;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.*;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import elemental.json.JsonArray;
import kaesdingeling.hybridmenu.HybridMenu;
import kaesdingeling.hybridmenu.components.*;
import kaesdingeling.hybridmenu.data.MenuConfig;
import kaesdingeling.hybridmenu.data.enums.ToggleMode;
import kaesdingeling.hybridmenu.design.DesignItem;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI(path = "/admin/panel")
@Title("Admin Panel")
@Theme("HybridMenu")
public class AdminPanelUI extends UI implements ClientConnector.DetachListener
{
    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private AppointmentServiceImpl appointmentService;

    @Autowired
    private ClientServiceImpl clientService;

    private Navigator navigator;
    private HybridMenu hybridMenu;

    @Override
    protected void init(VaadinRequest vaadinRequest)
    {
        VerticalLayout layout =  new VerticalLayout();
        layout.setHeight("100%");

        hybridMenu = HybridMenu.get()
                .withNaviContent(layout)
                .withConfig(MenuConfig.get().withDesignItem(DesignItem.getWhiteDesign()))
                .build();

        AppointmentPage appointmentPage =  new AppointmentPage(employeeService, clientService, appointmentService);
        ClientPage clientPage =  new ClientPage(clientService);
        EmployeePage employeePage = new EmployeePage(employeeService);

        navigator = new Navigator(this, hybridMenu.getNaviContent());

        navigator.addView("Appointments", appointmentPage);
        navigator.addView("Clients", clientPage);
        navigator.addView("Employees", employeePage);
        navigator.navigateTo("Appointments");

        buildTopMenu();
        buildLeftMenu();

        setContent(hybridMenu);

        JavaScript.getCurrent().addFunction("aboutToClose", new JavaScriptFunction()
        {
            private static final long serialVersionUID = 1L;
            @Override
            public void call(JsonArray arguments)
            {
                detach();
            }
        });

        Page.getCurrent().getJavaScript().execute("window.onbeforeunload = function (e) { var e = e || window.event; aboutToClose(); return; };");

    }

    @Override
    public void detach(DetachEvent detachEvent)
    {
        System.out.println("Detached");
        super.detach();
        getUI().detach();
    }


    private void buildTopMenu()
    {
        TopMenu topMenu = hybridMenu.getTopMenu();

        topMenu.add(HMButton.get().withClickListener(click -> navigator.navigateTo("Appointments"))
                .withIcon(VaadinIcons.HOME)
                .withDescription("Appointments"));

        topMenu.add(HMButton.get().withClickListener(clickEvent -> logOut()))
                .withIcon(VaadinIcons.ARROW_BACKWARD)
                .withDescription("Log out");
    }

    private void buildLeftMenu()
    {
        LeftMenu leftMenu = hybridMenu.getLeftMenu();

        leftMenu.setToggleMode(ToggleMode.NORMAL);

        leftMenu.add(HMLabel.get()
                .withCaption("<b>Homecare+</b> Menu")
                .withIcon(new ThemeResource("images/app_logo.png")));

        leftMenu.add(HMButton.get()
                .withCaption("Appointments")
                .withIcon(VaadinIcons.LIST)
                .withClickListener(clickEvent -> navigator.navigateTo("Appointments")));

        leftMenu.add(HMButton.get()
                .withCaption("Clients")
                .withIcon(VaadinIcons.USERS)
                .withClickListener(clickEvent -> navigator.navigateTo("Clients")));

        leftMenu.add(HMButton.get()
                .withCaption("Employees")
                .withIcon(VaadinIcons.USER)
                .withClickListener(clickEvent -> navigator.navigateTo("Employees")));
    }

    public HybridMenu getHybridMenu()
    {
        return hybridMenu;
    }

    private void logOut()
    {
        VaadinService.getCurrentRequest().getWrappedSession().invalidate();
        getUI().getPage().setLocation("/login");
    }
}
