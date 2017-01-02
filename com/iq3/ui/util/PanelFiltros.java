/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iq3.ui.util;

import com.iq3.ui.util.paneles.PanelFechas;
import com.iq3.ui.util.paneles.columnas.PanelColumnas;
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
public class PanelFiltros extends HorizontalSplitPanel
{

    VerticalLayout filtros = new VerticalLayout();
    Map mBotones = new HashMap();
    Map mPanelesNombre = new HashMap();
    Map mPanelesBoton = new HashMap();

    public PanelFiltros()
    {
        this.setFirstComponent(filtros);
        //filtros.setSizeFull();
        this.setSplitPosition(250, Unit.PIXELS);
        this.agregar();
    }

    public void agregar()
    {
        agregar(filtros, "Fechas", new PanelFechas());
        agregar(filtros, "Columnas", new PanelColumnas());

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
                AbstractComponent pb = (AbstractComponent) mPanelesBoton.get(bb);
                fijarComponente(pb);
            }
        });
    }

    public void fijarComponente(AbstractComponent ac)
    {
        this.setSecondComponent(ac);
    }
}
