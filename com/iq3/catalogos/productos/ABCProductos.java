/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iq3.catalogos.productos;

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
public class ABCProductos extends ixABCDialogosGAE
{
    
   
    private final Button bSubir;
    private Window diaSubir;
    private Upload upload;

    public ABCProductos(UI ui)
    {
        super(ui);

        this.setNombreTabla("iq3_productos");
        this.setColumnas("id,upc,dun14,cantidad_empaque,sku_interno,sku_ched,descripcion,departamento,familia,subfamilia,licencia,marca,submarca,personaje,color,sabor,aroma,activo_suspendido,in_out_linea,alto,ancho,peso,presentacion,presentacion_ml,contenido_neto,unidades,franquiciatario,cavidades,factor8oz,factorcl,factorcf,retornable,tipo_envase,tipo_serv,botellas_caja_fisica,tier1,etapa,genero,clave_sist");
        this.setColumnasVisibles("id,upc,descripcion,departamento,familia,subfamilia,marca,submarca");
        this.setTitulo("Productos");

       /* this.agregarColumna("id", "ID").TextField().setSoloLecturaGuardar(true);
        this.agregarColumna("upc", "UPC").setSoloLecturaGuardar(true).setRequerido(true).TextField();
        this.agregarColumna("descripcion", "Descripción").TextField();
        this.agregarColumna("departamento", "Departamento").TextField();
        this.agregarColumna("familia", "Familia").TextField();
        this.agregarColumna("subfamilia", "Subfamilia").TextField();
        this.agregarColumna("marca", "Marca").TextField();
        this.agregarColumna("submarca", "Submarca").TextField();*/
        
        
        this.agregarColumna("id", "ID").TextField().setSoloLecturaGuardar(true);
        this.agregarColumna("upc","upc").setSoloLecturaGuardar(true).setRequerido(true).TextField();
        this.agregarColumna("dun14","dun14").TextField();
this.agregarColumna("cantidad_empaque","cantidad_empaque").TextField();
this.agregarColumna("sku_interno","sku_interno").TextField();
this.agregarColumna("sku_ched","sku_ched").TextField();
this.agregarColumna("descripcion","descripcion").TextField();
this.agregarColumna("departamento","departamento").TextField();
this.agregarColumna("familia","familia").TextField();
this.agregarColumna("subfamilia","subfamilia").TextField();
this.agregarColumna("licencia","licencia").TextField();
this.agregarColumna("marca","marca").TextField();
this.agregarColumna("submarca","submarca").TextField();
this.agregarColumna("personaje","personaje").TextField();
this.agregarColumna("color","color").TextField();
this.agregarColumna("sabor","sabor").TextField();
this.agregarColumna("aroma","aroma").TextField();
this.agregarColumna("activo_suspendido","activo_suspendido").TextField();
this.agregarColumna("in_out_linea","in_out_linea").TextField();
this.agregarColumna("alto","alto").TextField();
this.agregarColumna("ancho","ancho").TextField();
this.agregarColumna("profundidad","profundidad").TextField();
this.agregarColumna("peso","peso").TextField();
this.agregarColumna("presentacion","presentacion").TextField();
this.agregarColumna("presentacion_ml","presentacion_ml").TextField();
this.agregarColumna("contenido_neto","contenido_neto").TextField();
this.agregarColumna("unidades","unidades").TextField();
this.agregarColumna("franquiciatario","franquiciatario").TextField();
this.agregarColumna("cavidades","cavidades").TextField();
this.agregarColumna("factor8oz","factor8oz").TextField();
this.agregarColumna("factorcl","factorcl").TextField();
this.agregarColumna("factorcf","factorcf").TextField();
this.agregarColumna("retornable","retornable").TextField();
this.agregarColumna("tipo_envase","tipo_envase").TextField();
this.agregarColumna("tipo_serv","tipo_serv").TextField();
this.agregarColumna("botellas_caja_fisica","botellas_caja_fisica").TextField();
this.agregarColumna("tier1","tier1").TextField();
this.agregarColumna("etapa","etapa").TextField();
this.agregarColumna("genero","genero").TextField();
this.agregarColumna("clave_sist","clave_sist").TextField();

      

        this.setFormaCreacion("Vm 'Información~de~Productos H F  upc dun14 cantidad_empaque sku_interno sku_ched descripcion departamento familia subfamilia licencia marca submarca personaje color sabor aroma activo_suspendido  in_out_linea alto ancho profundidad peso presentacion contenido_neto unidades franquiciatario cavidades factor8oz factorcl factorcf retornable tipo_envase tipo_serv botellas_caja_fisica tier1 etapa genero clave_sist bCrear .");
        this.setFormaEdicion("Vm 'Información~de~Tienda H "
                             + "F id upc dun14 cantidad_empaque sku_interno sku_ched descripcion departamento familia subfamilia licencia marca submarca personaje color sabor aroma activo_suspendido  in_out_linea alto ancho . "
                             + "Fm profundidad peso presentacion contenido_neto unidades franquiciatario cavidades factor8oz factorcl factorcf retornable tipo_envase tipo_serv botellas_caja_fisica tier1 etapa genero clave_sist . "
                             + "F bGuardar bBorrar . ");
        this.setFormaBorrado("Vm H F id upc descripcion departamento familia subfamilia marca submarca bConfBorrar .");
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

        ReceptorDeProductos receptor = new ReceptorDeProductos(this);

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
        String upc = defFormaCrear.getTextField("upc").getValue().trim();
       
        if (upc.equals("") )
        {
            return false;
        }
        m.put("id", upc);
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
