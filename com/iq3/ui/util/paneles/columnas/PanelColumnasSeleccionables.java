/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iq3.ui.util.paneles.columnas;

import com.coatl.vaadin.ixUI;
import com.iq3.ui.util.paneles.PanelFechas;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author matus
 */
public class PanelColumnasSeleccionables extends HorizontalSplitPanel
{

    Map mBotones = new HashMap();
    Map mPanelesNombre = new HashMap();
    Map mPanelesBoton = new HashMap();

    VerticalLayout filtros = new VerticalLayout();

    public PanelColumnasSeleccionables(ixUI ixUI)
    {
        this.setSplitPosition(250, Unit.PIXELS);

        agregar(filtros, "Datos", new PanelColumnas(ixUI, "datos"));
        agregar(filtros, "Tiendas", new PanelColumnas(ixUI, "tiendas"));
        agregar(filtros, "Productos", new PanelColumnas(ixUI, "productos"));
        agregar(filtros, "Fechas", new PanelColumnas(ixUI, "fechas"));

        this.setFirstComponent(filtros);
    }

    public void agregar(Layout l, String nombre, AbstractComponent panel)
    {
        Button b = new Button(nombre);
        b.setWidth("100%");
        filtros.addComponent(b);
        mBotones.put(nombre, b);
        mPanelesNombre.put(nombre, panel);
        mPanelesBoton.put(b, panel);

        b.addClickListener(new Button.ClickListener()
        {
            @Override
            public void buttonClick(Button.ClickEvent event)
            {
                Button bb = event.getButton();
                PanelColumnas pb = (PanelColumnas) mPanelesBoton.get(bb);
                fijarComponente(pb);
            }
        });
    }

    public void fijarComponente(PanelColumnas ac)
    {
        this.setSecondComponent(ac);
        ac.armarTabla();
    }
}
