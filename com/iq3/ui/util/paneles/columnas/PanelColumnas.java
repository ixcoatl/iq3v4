/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iq3.ui.util.paneles.columnas;

import com.coatl.ed.filtros.ixFiltro;
import com.coatl.vaadin.abc.ixABCDialogosGAE;

/**
 *
 * @author matus
 */
public class PanelColumnas extends ixABCDialogosGAE
{

    private final String tipoCol;

    public PanelColumnas(com.coatl.vaadin.ixUI ui, String tipoCol)
    {
        super(ui);
        this.setNombreTabla("iq3_columnas");
        this.setTipoBusqueda("agrupar");

        this.setColumnas("id,columna");
        this.setColumnasVisibles("columna");
        this.setTitulo("Columnas de " + tipoCol);

        this.agregarColumna("id", "ID").TextField().setSoloLecturaGuardar(true);
        this.agregarColumna("columna", "Columna").TextField().setSoloLecturaGuardar(true);

        this.getbCrear().setVisible(false);
        //this.getEncabezado().removeAllComponents();
        this.armarTabla();
        this.getbCrear().setVisible(false);

        this.tipoCol = tipoCol;
    }

    @Override
    public ixFiltro getFiltros()
    {
        //ixFiltro f = new ixFiltro();
        ixFiltro f = super.getFiltros();

        f.getp f
        .iniciarY();
        f.agregarCoincideCadenaIgnCaso("tabla", tipoCol);
        f.agregarCoincideBooleano("seleccionable", true);
        f.terminarTodo();

        return f;
    }

}
