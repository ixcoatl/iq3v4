/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iq3.ui.reportes.totalesporfecha;

import com.coatl.vaadin.abc.ixABCDialogos;
import com.coatl.vaadin.grids.ixGridTabla;
import com.iq3.ui.util.DiaFiltros;
import com.vaadin.ui.Button;

/**
 *
 * @author matus
 */
public class TotalesPorFechaSimple extends ixABCDialogos
{

    DiaFiltros df = null;

    public TotalesPorFechaSimple(com.coatl.vaadin.ixUI ui)
    {
        super(ui);
        this.getEncabezado().removeAllComponents();

        Button cols = new Button("\u2699 Configuración");
        this.getEncabezado().addComponent(cols);
        cols.addClickListener(new Button.ClickListener()
        {
            @Override
            public void buttonClick(Button.ClickEvent event)
            {
                if (df == null)
                {
                    df = new DiaFiltros();
                }
                getIxUI().addWindow(df);
                df.center();
            }
        });
    }

}
