/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iq3.ui.catalogos;

import com.coatl.vaadin.abc.ixABCDialogosGAE;
import com.coatl.vaadin.ixUI;

/**
 *
 * @author matus
 */
public class ABCColumnas extends ixABCDialogosGAE
{

    public ABCColumnas(ixUI ui)
    {
        super(ui);

        this.setNombreTabla("iq3_productos");
        this.setColumnas("id,tabla,columna,descripcion,seleccionable,filtrable");
        this.setColumnas("id,tabla,columna,descripcion,seleccionable,filtrable");
        this.setTitulo("Columnas de datos");
        this.agregarColumna("id", "ID").TextField().setSoloLecturaGuardar(true);
        this.agregarColumna("tabla", "Tabla").TextField().setSoloLecturaGuardar(true);
        this.agregarColumna("columna", "Columna").TextField().setSoloLecturaGuardar(true);
        this.agregarColumna("descripcion", "Descripción").TextField();
        this.agregarColumna("seleccionable", "Seleccionable").CheckBox();
        this.agregarColumna("filtrable", "Filtrable").CheckBox();

        this.setFormaCreacion("Vm H F tabla columna descripcion seleccionable filtrable bCrear .");
        this.setFormaEdicion("Vm H F id tabla columna descripcion seleccionable filtrable bGuardar bBorrar . ");
        this.setFormaBorrado("Vm H F id tabla columna descripcion seleccionable filtrable bConfBorrar .");
        this.armarTabla();

    }
}
