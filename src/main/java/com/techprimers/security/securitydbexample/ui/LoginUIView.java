package com.techprimers.security.securitydbexample.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import javax.servlet.annotation.WebServlet;

@SpringUI(path = "/login")
@Title("HomeCare+ Login")
@Theme("HybridMenu")
public class LoginUIView extends UI
{
    @Autowired
    DaoAuthenticationProvider daoAuthenticationProvider;

    @Override
    protected void init(VaadinRequest request)
    {
        setSizeFull();
        setContent(new LoginView(daoAuthenticationProvider));
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = LoginUIView.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet
    {

    }
}