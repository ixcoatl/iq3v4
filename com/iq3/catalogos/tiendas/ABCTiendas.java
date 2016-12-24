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
        this.setColumnas("id,operador,compania,determinante,nombre,formato,formato_codigo,fecha_apertura,cp,asentamiento,municipio,estado,ciudad,coordenadas_geograficas,zona_nielsen,direccion,telefono,scantrack,colonia,impuesto,operador_determinante,region,territorio,clave_cedis,cedis,nud,nombre_cliente,status,clave_ruta_entrega,team_leader,formato_cliente,fecha_alta,fecha_baja,operador_cliente,cadena_cliente,zona_venta,promotor,agencia,determinante_cliente,supervisor,vendedor,poblacion_base,agencia_1,demostradora");
        this.setColumnasVisibles("operador,compania,determinante,nombre,formato_codigo,formato");
        this.setTitulo("Tiendas");

        
      /*  this.agregarColumna("operador", "Operador").setRequerido(true).TextField();
        this.agregarColumna("compania", "Compañia").TextField();
        this.agregarColumna("determinante", "Determinante").TextField();
        this.agregarColumna("nombre", "Nombre").TextField();
        this.agregarColumna("formato", "Formato").TextField();
        this.agregarColumna("formato_codigo", "Código de formato").TextField();*/
        
this.agregarColumna("id", "ID").TextField().setSoloLecturaGuardar(true);        
this.agregarColumna("operador","Operador").setRequerido(true).TextField();
this.agregarColumna("compania","Compañia").TextField();
this.agregarColumna("determinante","Determinante").TextField();
this.agregarColumna("nombre","Nombre").TextField();
this.agregarColumna("formato","Código de formato").TextField();
this.agregarColumna("formato_codigo","Formato código").TextField();
this.agregarColumna("fecha_apertura","Fecha apertura").TextField();
this.agregarColumna("cp","C.P.").TextField();
this.agregarColumna("asentamiento","Asentamiento").TextField();
this.agregarColumna("municipio","Municipio").TextField();
this.agregarColumna("estado","Estado").TextField();
this.agregarColumna("ciudad","Ciudad").TextField();
this.agregarColumna("coordenadas_geograficas","Coordenadas geográficas").TextField();
this.agregarColumna("zona_nielsen","Zona Nielsen").TextField();
this.agregarColumna("direccion","Dirección").TextField();
this.agregarColumna("telefono","Teléfono").TextField();
this.agregarColumna("scantrack","Scantrack").TextField();
this.agregarColumna("colonia","Colonia").TextField();
this.agregarColumna("impuesto","Impuesto").TextField();
this.agregarColumna("operador_determinante","Operador del determinante").TextField();
this.agregarColumna("region","Región").TextField();
this.agregarColumna("territorio","Territorio").TextField();
this.agregarColumna("clave_cedis","Clave del Cedis").TextField();
this.agregarColumna("cedis","Cedis").TextField();
this.agregarColumna("nud","Nud").TextField();
this.agregarColumna("nombre_cliente","Nombre del cliente").TextField();
this.agregarColumna("status","status").TextField();
this.agregarColumna("clave_ruta_entrega","Clave de ruta de entrega").TextField();
this.agregarColumna("team_leader","team_leader").TextField();
this.agregarColumna("formato_cliente","Formato del Cliente").TextField();
this.agregarColumna("fecha_alta","Fecha de alta").TextField();
this.agregarColumna("fecha_baja","Fecha de baja").TextField();
this.agregarColumna("operador_cliente","Operador del cliente").TextField();
this.agregarColumna("cadena_cliente","Cadena del cliente").TextField();
this.agregarColumna("zona_venta","Zona de venta").TextField();
this.agregarColumna("promotor","Promotor").TextField();
this.agregarColumna("agencia","Agencia").TextField();
this.agregarColumna("determinante_cliente","Determinante del cliente").TextField();
this.agregarColumna("supervisor","Supervisor").TextField();
this.agregarColumna("vendedor","Vendedor").TextField();
this.agregarColumna("poblacion_base","Población base").TextField();
this.agregarColumna("agencia_1","Agencia 1").TextField();
this.agregarColumna("demostradora","Demostradora").TextField();

        
        
        
        
//this.setFormaCreacion("Vm 'Información~de~Tienda H F  operador determinante formato_codigo formato bCrear .");

this.setFormaCreacion("Vm 'Información~de~Tienda H F  operador compania determinante nombre formato formato_codigo fecha_apertura cp asentamiento municipio estado ciudad coordenadas_geograficas zona_nielsen direccion telefono scantrack colonia impuesto operador_determinante region territorio clave_cedis cedis nud nombre_cliente status clave_ruta_entrega team_leader formato_cliente fecha_alta fecha_baja operador_cliente cadena_cliente zona_venta promotor agencia determinante_cliente supervisor vendedor poblacion_base agencia_1 demostradora bCrear .");



this.setFormaEdicion("Vm 'Información~de~Tienda H "
                             + "F id operador compania determinante nombre formato formato_codigo fecha_apertura cp asentamiento municipio estado ciudad coordenadas_geograficas zona_nielsen direccion telefono scantrack colonia impuesto operador_determinante region territorio clave_cedis . "
                             + "Fm cedis nud nombre_cliente status clave_ruta_entrega team_leader formato_cliente fecha_alta fecha_baja operador_cliente cadena_cliente zona_venta promotor agencia determinante_cliente supervisor vendedor poblacion_base agencia_1 demostradora . "
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
