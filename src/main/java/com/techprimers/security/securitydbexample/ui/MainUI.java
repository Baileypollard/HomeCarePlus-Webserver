package com.techprimers.security.securitydbexample.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;

@SpringUI(path = "/")
public class MainUI extends UI
{
    @Override
    protected void init(VaadinRequest vaadinRequest)
    {
        getUI().getPage().setLocation("/admin/panel");
    }
}
