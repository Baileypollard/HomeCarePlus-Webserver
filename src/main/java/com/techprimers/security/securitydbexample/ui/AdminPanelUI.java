package com.techprimers.security.securitydbexample.ui;

import com.github.appreciated.app.layout.AppLayout;
import com.github.appreciated.app.layout.behaviour.AppLayoutComponent;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.Section;
import com.github.appreciated.app.layout.builder.design.AppLayoutDesign;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.factories.DefaultSpringNavigationElementInfoProducer;
import com.github.appreciated.app.layout.component.MenuHeader;
import com.github.appreciated.app.layout.component.button.IconButton;
import com.github.appreciated.app.layout.component.layout.VerticalFlexBoxLayout;
import com.techprimers.security.securitydbexample.model.Employee;
import com.techprimers.security.securitydbexample.repository.AppointmentTypeRepository;
import com.techprimers.security.securitydbexample.service.AppointmentServiceImpl;
import com.techprimers.security.securitydbexample.service.ClientServiceImpl;
import com.techprimers.security.securitydbexample.service.CustomUserDetailsService;
import com.techprimers.security.securitydbexample.service.EmployeeServiceImpl;
import com.techprimers.security.securitydbexample.ui.pages.AppointmentPage;
import com.techprimers.security.securitydbexample.ui.pages.ClientPage;
import com.techprimers.security.securitydbexample.ui.pages.EmployeePage;
import com.vaadin.annotations.*;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.server.*;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebServlet;

@PushStateNavigation
@SpringUI(path = "/admin/panel")
@Viewport("width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no")
@Title("Admin Panel")
@Theme("demo")
public class AdminPanelUI extends UI implements ClientConnector.DetachListener
{
    DefaultBadgeHolder badge = new DefaultBadgeHolder();

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private AppointmentServiceImpl appointmentService;

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private AppointmentTypeRepository repository;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private SpringNavigator navigator;

    @Override
    protected void init(VaadinRequest vaadinRequest)
    {
        AppLayoutComponent component = AppLayout.getDefaultBuilder(Behaviour.LEFT_RESPONSIVE_HYBRID)
                .withNavigator(components ->
        {
            navigator.init(this, components);
            return navigator;
        }).withNavigationElementInfoProducer(new DefaultSpringNavigationElementInfoProducer())
                .withTitle("HomeCare+").withDesign(AppLayoutDesign.MATERIAL)
                .add("Appointments", VaadinIcons.LIST, badge, AppointmentPage.class)
                .add("Clients", VaadinIcons.USERS, badge, ClientPage.class)
                .add("Employees", VaadinIcons.USER, badge, EmployeePage.class)
                .addClickable("Log out", VaadinIcons.ARROW_BACKWARD, clickEvent -> logOut())
                .build();

        setContent(component);
    }

    @Override
    public void detach(DetachEvent detachEvent)
    {
        super.detach();
        getUI().detach();
    }

    private void logOut()
    {
        VaadinService.getCurrentRequest().getWrappedSession().invalidate();
        getUI().getPage().setLocation("login/");
    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = AdminPanelUI.class, productionMode = false)
    public static class MyUIServlet extends SpringVaadinServlet
    {

    }
}
