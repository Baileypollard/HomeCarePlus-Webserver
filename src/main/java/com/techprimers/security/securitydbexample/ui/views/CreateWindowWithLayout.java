package com.techprimers.security.securitydbexample.ui.views;

import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class CreateWindowWithLayout extends Window
{
    private VerticalLayout layout;

    public CreateWindowWithLayout(VerticalLayout layout)
    {
        super(layout.getCaption());
        this.layout = layout;
        this.layout.setSizeFull();
        setContent(layout);
        getContent().setSizeUndefined();
        center();
    }
}
