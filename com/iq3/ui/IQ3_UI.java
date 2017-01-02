/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iq3.ui;

import com.coatl.vaadin.ixUI;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 *
 * @author matus
 */
@Theme("tema_iu7")
public class IQ3_UI extends ixUI
{

    @Override
    protected void init(VaadinRequest vaadinRequest)
    {
        PanelPrincipal prin = new PanelPrincipal(this);
        prin.setSizeFull();
        setContent(prin);
    }

}
