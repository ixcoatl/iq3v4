/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iq3.ui.util.paneles;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Panel;

/**
 *
 * @author matus
 */
public class PanelFechas extends Panel
{

    FormLayout fl = new FormLayout();
    private final DateField f1;
    private final DateField f2;
    private final DateField f3;
    private final DateField f4;
    private final CheckBox comparar;

    public PanelFechas()
    {

        this.setCaptionAsHtml(true);
        this.setCaption("<b>Fechas</b>");

        this.f1 = new DateField("Fecha incial");
        f1.setDateFormat("yyyy-MM-dd");
        this.f2 = new DateField("Fecha incial");
        f2.setDateFormat("yyyy-MM-dd");
        this.comparar = new CheckBox("Comparar");
        this.f3 = new DateField("Fecha incial");
        f3.setDateFormat("yyyy-MM-dd");
        this.f4 = new DateField("Fecha incial");
        f4.setDateFormat("yyyy-MM-dd");

        fl.addComponent(f1);
        fl.addComponent(f2);
        fl.addComponent(comparar);
        fl.addComponent(f3);
        fl.addComponent(f4);
        fl.setSizeFull();
        fl.setMargin(true);

        this.setSizeFull();

        this.setContent(fl);
    }

}
