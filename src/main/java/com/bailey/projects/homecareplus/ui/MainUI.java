package com.bailey.projects.homecareplus.ui;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;

@SpringUI(path = "/")
public class MainUI extends UI
{
    @Override
    protected void init(VaadinRequest vaadinRequest)
    {
        getUI().getPage().setLocation("/HomeCarePlus/admin/panel");
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MyUIServlet extends SpringVaadinServlet
    {

    }
}
