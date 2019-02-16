package com.techprimers.security.securitydbexample.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

@SpringUI(path = "/")
public class MainUI extends UI
{
    @Override
    protected void init(VaadinRequest vaadinRequest)
    {
        getUI().getPage().setLocation("/admin/panel");
    }
}
