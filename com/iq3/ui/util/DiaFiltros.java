/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iq3.ui.util;

import com.coatl.vaadin.ixUI;
import com.vaadin.ui.Window;

/**
 *
 * @author matus
 */
public class DiaFiltros extends Window
{

    public DiaFiltros(ixUI ixUI)
    {
        super("Filtros de reporte");
        this.setWidth("1200px");
        this.setHeight("520px");

        PanelFiltros p = new PanelFiltros(ixUI);
        p.setSizeFull();
        this.setContent(p);
    }

}
