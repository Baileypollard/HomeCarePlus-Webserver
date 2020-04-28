package com.bailey.projects.homecareplus.ui.views;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class CreateWindowWithLayout extends Window
{
    private HorizontalLayout layout;

    public CreateWindowWithLayout(HorizontalLayout layout)
    {
        super(layout.getCaption());
        this.layout = layout;
        this.layout.setSizeFull();
        this.layout.setMargin(true);
        setContent(layout);
        getContent().setSizeUndefined();
        center();
    }
}
