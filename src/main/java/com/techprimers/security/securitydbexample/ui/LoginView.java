package com.techprimers.security.securitydbexample.ui;

import com.vaadin.ui.Button;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoginView extends LoginUI
{

    DaoAuthenticationProvider daoAuthenticationProvider;

    public LoginView(DaoAuthenticationProvider daoAuthenticationProvider)
    {
        this.daoAuthenticationProvider = daoAuthenticationProvider;
        buttonSignIn.addClickListener(click -> loginButtonClick(click) );
    }

    public void loginButtonClick(Button.ClickEvent e)
    {
        Authentication auth = new UsernamePasswordAuthenticationToken(username.getValue(),password.getValue());
        Authentication authenticated = daoAuthenticationProvider.authenticate(auth);
        SecurityContextHolder.getContext().setAuthentication(authenticated);

        getUI().getPage().setLocation("/admin/panel");
    }
}
