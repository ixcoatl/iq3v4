/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iq3.catalogos.tiendas;

import com.coatl.vaadin.abc.ixABCDialogosGAE;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.Window;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 *
 * @author matus
 */
public class ABCTiendas extends ixABCDialogosGAE

{

    private final Button bSubir;
    private Window diaSubir;
    private Upload upload;

    public ABCTiendas(UI ui)
    {
        super(ui);

        this.setNombreTabla("iq3_tiendas");
        this.setColumnas("id,operador,compania,determinante,nombre,formato_codigo,formato");
        this.setColumnasVisibles("operador,compania,determinante,nombre,formato_codigo,formato");
        this.setTitulo("Tiendas");

        this.agregarColumna("id", "ID").TextField().setSoloLecturaGuardar(true);
        this.agregarColumna("operador", "Operador").setRequerido(true).TextField();
        this.agregarColumna("compania", "Compañia").TextField();
        this.agregarColumna("determinante", "Determinante").TextField();
        this.agregarColumna("nombre", "Nombre").TextField();
        this.agregarColumna("formato", "Formato").TextField();
        this.agregarColumna("formato_codigo", "Código de formato").TextField();

        this.setFormaCreacion("Vm 'Información~de~Tienda H F  operador determinante formato_codigo formato bCrear .");
        this.setFormaEdicion("Vm 'Información~de~Tienda H "
                             + "F id operador determinante formato_codigo formato . "
                             + "Fm nombre compania . "
                             + "F bGuardar bBorrar . ");
        this.setFormaBorrado(" Vm H F id operador determinante formato_codigo formato nombre bConfBorrar .");
        this.armarTabla();

        hacerDialogoSubir();

        HorizontalLayout enc = this.getEncabezado();

        this.bSubir = new Button("Subir archivo");
        enc.addComponent(bSubir);
        bSubir.addClickListener(new Button.ClickListener()
        {
            @Override
            public void buttonClick(Button.ClickEvent event)
            {
                abrirDialogoSubir();
            }

        });
    }

    private final void hacerDialogoSubir()
    {
        this.setDiaSubir(new Window("Subir"));
        HorizontalLayout hl = new HorizontalLayout();
        hl.setMargin(true);
        getDiaSubir().setContent(hl);

        ReceptorDeTiendas receptor = new ReceptorDeTiendas(this);

        this.upload = new Upload("Archivo de tiendas:", receptor);
        upload.addSucceededListener(receptor);

        hl.addComponent(upload);

    }

    private void abrirDialogoSubir()
    {
        getDiaSubir().center();
        this.getUI().addWindow(getDiaSubir());
    }

    @Override
    public boolean antesDeCrear(Map m)
    {
        String operador = defFormaCrear.getTextField("operador").getValue().trim();
        String determinante = defFormaCrear.getTextField("determinante").getValue().trim();
        if (operador.equals("") || determinante.equals(""))
        {
            return false;
        }
        m.put("id", operador + "-" + determinante);
        return true;
    }

    /**
     * @return the diaSubir
     */
    public Window getDiaSubir()
    {
        return diaSubir;
    }

    /**
     * @param diaSubir the diaSubir to set
     */
    public void setDiaSubir(Window diaSubir)
    {
        this.diaSubir = diaSubir;
    }

}
