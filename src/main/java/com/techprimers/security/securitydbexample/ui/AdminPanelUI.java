package com.techprimers.security.securitydbexample.ui;

import com.techprimers.security.securitydbexample.model.Appointment;
import com.techprimers.security.securitydbexample.service.AppointmentServiceImpl;
import com.techprimers.security.securitydbexample.service.ClientServiceImpl;
import com.techprimers.security.securitydbexample.service.EmployeeServiceImpl;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.*;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import elemental.json.JsonArray;
import kaesdingeling.hybridmenu.HybridMenu;
import kaesdingeling.hybridmenu.components.*;
import kaesdingeling.hybridmenu.data.MenuConfig;
import kaesdingeling.hybridmenu.design.DesignItem;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI(path = "/admin/panel")
@Title("Admin Panel")
@Theme("valo")
public class AdminPanelUI extends UI implements ClientConnector.DetachListener
{
    private HybridMenu hybridMenu;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private AppointmentServiceImpl appointmentService;

    @Autowired
    private ClientServiceImpl clientService;


    @Override
    protected void init(VaadinRequest vaadinRequest)
    {
        hybridMenu = HybridMenu.get()
                .withNaviContent(new VerticalLayout())
                .withConfig(MenuConfig.get().withDesignItem(DesignItem.getDarkDesign()))
                .build();

        buildTopOnlyMenu();
        buildLeftMenu();

//        getNavigator().addViewChangeListener(new ViewChangeListener() {
//            private static final long serialVersionUID = -1840309356612297980L;
//            @Override
//            public boolean beforeViewChange(ViewChangeEvent event) {
//                if (event.getOldView() != null && event.getOldView().getClass().getSimpleName().equals(ThemeBuilderPage.class.getSimpleName())) {
//                    hybridMenu.switchTheme(DesignItem.getDarkDesign());
//                }
//                return true;
//            }
//        });

        setContent(hybridMenu);

        JavaScript.getCurrent().addFunction("aboutToClose", new JavaScriptFunction() {
            private static final long serialVersionUID = 1L;
            @Override
            public void call(JsonArray arguments) {
                detach();
            }
        });

        Page.getCurrent().getJavaScript().execute("window.onbeforeunload = function (e) { var e = e || window.event; aboutToClose(); return; };");

    }

    @Override
    public void detach(DetachEvent detachEvent)
    {
        getUI().detach();
    }


    private void buildTopOnlyMenu()
    {
        TopMenu topMenu = hybridMenu.getTopMenu();

        topMenu.add(HMTextField.get(VaadinIcons.SEARCH, "Search ..."));

        topMenu.add(HMButton.get()
                .withIcon(VaadinIcons.HOME)
                .withDescription("Appointments")
                .withNavigateTo(AppointmentPage.class));

        hybridMenu.getNotificationCenter()
                .setNotiButton(topMenu.add(HMButton.get()
                        .withDescription("Notifications")));
    }

    private void buildLeftMenu()
    {
        LeftMenu leftMenu = hybridMenu.getLeftMenu();

        leftMenu.add(HMLabel.get()
                .withCaption("<b>Hybrid</b> Menu")
                .withIcon(new ThemeResource("images/hybridmenu-Logo.png")));

        hybridMenu.getBreadCrumbs().setRoot(leftMenu.add(HMButton.get()
                .withCaption("Appointments")
                .withIcon(VaadinIcons.HOME)
                .withNavigateTo(AppointmentPage.class)));

        leftMenu.add(HMButton.get()
                .withCaption("Notification Builder")
                .withIcon(VaadinIcons.BELL)
                .withNavigateTo(AppointmentPage.class));

        leftMenu.add(HMButton.get()
                .withCaption("Theme Builder")
                .withIcon(FontAwesome.WRENCH)
                .withNavigateTo(AppointmentPage.class));

        HMSubMenu memberList = leftMenu.add(HMSubMenu.get()
                .withCaption("Member")
                .addSubView(AppointmentPage.class)
                .withIcon(VaadinIcons.USERS));

        memberList.add(HMButton.get()
                .withCaption("Settings")
                .withIcon(VaadinIcons.COGS)
                .withNavigateTo(AppointmentPage.class));

        memberList.add(HMButton.get()
                .withCaption("Member")
                .withIcon(VaadinIcons.USERS)
                .withNavigateTo(AppointmentPage.class));

        memberList.add(HMButton.get()
                .withCaption("Group")
                .withIcon(VaadinIcons.USERS)
                .withNavigateTo(AppointmentPage.class));

        HMSubMenu memberListTwo = memberList.add(HMSubMenu.get()
                .withCaption("Member")
                .withIcon(VaadinIcons.USERS));

        HMSubMenu demoSettings = leftMenu.add(HMSubMenu.get()
                .withCaption("Settings")
                .withIcon(VaadinIcons.COGS));

        demoSettings.add(HMButton.get()
                .withCaption("White Theme")
                .withIcon(VaadinIcons.PALETE)
                .withClickListener(e -> hybridMenu.switchTheme(DesignItem.getWhiteDesign())));

        demoSettings.add(HMButton.get()
                .withCaption("Dark Theme")
                .withIcon(VaadinIcons.PALETE)
                .withClickListener(e -> hybridMenu.switchTheme(DesignItem.getDarkDesign())));

        demoSettings.add(HMButton.get()
                .withCaption("Minimal")
                .withIcon(VaadinIcons.COG)
                .withClickListener(e -> hybridMenu.getLeftMenu().toggleSize()));
    }

    public HybridMenu getHybridMenu() {
        return hybridMenu;
    }


}
