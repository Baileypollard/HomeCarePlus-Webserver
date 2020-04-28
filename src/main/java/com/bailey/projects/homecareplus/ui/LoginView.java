package com.bailey.projects.homecareplus.ui;

import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoginView extends LoginUI
{
    private DaoAuthenticationProvider daoAuthenticationProvider;

    public LoginView(DaoAuthenticationProvider daoAuthenticationProvider)
    {
        this.username.focus();
        this.daoAuthenticationProvider = daoAuthenticationProvider;
        this.buttonSignIn.addClickListener(click -> loginButtonClick(click) );
    }

    private void loginButtonClick(Button.ClickEvent e)
    {
        Authentication auth = new UsernamePasswordAuthenticationToken(username.getValue(),password.getValue());
        try
        {

//            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            String hashedPassword = passwordEncoder.encode(password.getValue());
//            System.out.println(hashedPassword);


            Authentication authenticated = daoAuthenticationProvider.authenticate(auth);
            SecurityContextHolder.getContext().setAuthentication(authenticated);

            if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated())
            {
                String authorityList = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
                if (authorityList.contains("ROLE_ADMIN"))
                {
                    VaadinService.reinitializeSession(VaadinService.getCurrentRequest());
                    getUI().getPage().setLocation("/HomeCarePlus/admin/panel");
                }
                else if (authorityList.contains("ROLE_USER"))
                {
                    getUI().getPage().setLocation("/HomeCarePlus/user/panel");
                }
            }
        }
        catch (BadCredentialsException exception)
        {
            Notification.show("You have entered the wrong Username or Password. Please try again!", Notification.Type.ERROR_MESSAGE);
        }

    }
}
