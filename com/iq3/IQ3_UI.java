/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iq3;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author matus
 */
@Theme("tema_iu7")
public class IQ3_UI extends UI
{

    @Override
    protected void init(VaadinRequest vaadinRequest)
    {
        PanelPrincipal prin = new PanelPrincipal(this);
        prin.setSizeFull();
        setContent(prin);
    }

}
