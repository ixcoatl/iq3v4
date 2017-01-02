/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iq3.ui.catalogos;

import com.coatl.appengine.IU7;
import com.coatl.vaadin.abc.ixABCDialogosGAE;
import com.coatl.vaadin.ixUI;
import com.google.appengine.api.datastore.Entity;
import com.vaadin.ui.Button;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author matus
 */
public class ABCColumnas extends ixABCDialogosGAE
{

    public ABCColumnas(ixUI ui)
    {
        super(ui);

        this.setNombreTabla("iq3_columnas");
        this.setColumnas("id,tabla,columna,descripcion,seleccionable,filtrable");
        this.setColumnasVisibles("id,tabla,columna,descripcion,seleccionable,filtrable");
        this.setTitulo("Columnas de Datos");

        this.agregarColumna("id", "ID").TextField().setSoloLecturaGuardar(true);
        this.agregarColumna("tabla", "Tabla").TextField().setSoloLecturaGuardar(true);
        this.agregarColumna("columna", "Columna").TextField().setSoloLecturaGuardar(true);
        this.agregarColumna("descripcion", "Descripción").TextField();
        this.agregarColumna("seleccionable", "Seleccionable").CheckBox();
        this.agregarColumna("filtrable", "Filtrable").CheckBox();

        this.setFormaCreacion("Vm H F tabla columna descripcion seleccionable filtrable bCrear .");
        this.setFormaEdicion("Vm H F id tabla columna descripcion seleccionable filtrable bGuardar bBorrar . ");
        this.setFormaBorrado("Vm H F id tabla columna descripcion seleccionable filtrable bConfBorrar .");

        Button cols = new Button("Crear columnas comunes");
        this.getEncabezado().addComponent(cols);
        cols.addClickListener(new Button.ClickListener()
        {
            @Override
            public void buttonClick(Button.ClickEvent event)
            {
                crearColumnasComunes();
            }
        });

        this.armarTabla();
    }

    @Override
    public boolean antesDeCrear(Map m)
    {
        String tabla = defFormaCrear.getTextField("tabla").getValue().trim();
        String columna = defFormaCrear.getTextField("columna").getValue().trim();
        if (tabla.equals("") || columna.equals(""))
        {
            return false;
        }
        m.put("id", tabla + "-" + columna);
        return true;
    }

    public void crearColumnasComunes()
    {
        System.out.println("Creando columnas comunes.");
        List l = new ArrayList();

        agregarColumna(l, "datos", "iddato", false, false);
        agregarColumna(l, "datos", "idinyeccion", false, false);
        agregarColumna(l, "datos", "fecha");
        agregarColumna(l, "datos", "operador");
        agregarColumna(l, "datos", "determinante");
        agregarColumna(l, "datos", "upc");
        agregarColumna(l, "datos", "venta_pesos", false, false);
        agregarColumna(l, "datos", "venta_unidades", false, false);
        agregarColumna(l, "datos", "costo_pesos", false, false);
        agregarColumna(l, "datos", "inventario_unidades", false, false);
        agregarColumna(l, "datos", "inventario_venta", false, false);
        agregarColumna(l, "datos", "inventario_costo", false, false);
        agregarColumna(l, "datos", "costo_especifico_tienda", false, false);
        agregarColumna(l, "datos", "venta_especifica_tienda", false, false);
        agregarColumna(l, "datos", "suboperador", false, false);
        agregarColumna(l, "datos", "impuesto", false, false);
        agregarColumna(l, "datos", "inventario_dia_anterior", false, false);
        agregarColumna(l, "datos", "unidades_transito", false, false);
        agregarColumna(l, "datos", "operador_determinante", false, false);
        agregarColumna(l, "datos", "numprov", false, false);

        agregarColumna(l, "tiendas", "operador");
        agregarColumna(l, "tiendas", "compania");
        agregarColumna(l, "tiendas", "determinante");
        agregarColumna(l, "tiendas", "nombre");
        agregarColumna(l, "tiendas", "formato");
        agregarColumna(l, "tiendas", "formato_codigo");
        agregarColumna(l, "tiendas", "fecha_apertura", false, false);
        agregarColumna(l, "tiendas", "cp", false, false);
        agregarColumna(l, "tiendas", "asentamiento", false, false);
        agregarColumna(l, "tiendas", "municipio", false, false);
        agregarColumna(l, "tiendas", "estado", false, false);
        agregarColumna(l, "tiendas", "ciudad", false, false);
        agregarColumna(l, "tiendas", "coordenadas_geograficas", false, false);
        agregarColumna(l, "tiendas", "zona_nielsen");
        agregarColumna(l, "tiendas", "direccion", false, false);
        agregarColumna(l, "tiendas", "telefono", false, false);
        agregarColumna(l, "tiendas", "scantrack", false, false);
        agregarColumna(l, "tiendas", "colonia", false, false);
        agregarColumna(l, "tiendas", "impuesto");
        agregarColumna(l, "tiendas", "operador_determinante");
        agregarColumna(l, "tiendas", "region");
        agregarColumna(l, "tiendas", "territorio");
        agregarColumna(l, "tiendas", "clave_cedis");
        agregarColumna(l, "tiendas", "cedis");
        agregarColumna(l, "tiendas", "nud");
        agregarColumna(l, "tiendas", "nombre_cliente");
        agregarColumna(l, "tiendas", "status", false, false);
        agregarColumna(l, "tiendas", "clave_ruta_entrega");
        agregarColumna(l, "tiendas", "team_leader");
        agregarColumna(l, "tiendas", "formato_cliente");
        agregarColumna(l, "tiendas", "fecha_alta", false, false);
        agregarColumna(l, "tiendas", "fecha_baja", false, false);
        agregarColumna(l, "tiendas", "operador_cliente");
        agregarColumna(l, "tiendas", "cadena_cliente");
        agregarColumna(l, "tiendas", "zona_venta");
        agregarColumna(l, "tiendas", "promotor");
        agregarColumna(l, "tiendas", "agencia");
        agregarColumna(l, "tiendas", "determinante_cliente");
        agregarColumna(l, "tiendas", "supervisor");
        agregarColumna(l, "tiendas", "vendedor");
        agregarColumna(l, "tiendas", "poblacion_base");
        agregarColumna(l, "tiendas", "agencia_1");
        agregarColumna(l, "tiendas", "demostradora");

        agregarColumna(l, "productos", "dun14", false, false);
        agregarColumna(l, "productos", "cantidad_empaque", false, false);
        agregarColumna(l, "productos", "sku_interno", false, false);
        agregarColumna(l, "productos", "sku_ched", false, false);
        agregarColumna(l, "productos", "descripcion");
        agregarColumna(l, "productos", "departamento");
        agregarColumna(l, "productos", "familia");
        agregarColumna(l, "productos", "subfamilia");
        agregarColumna(l, "productos", "licencia");
        agregarColumna(l, "productos", "marca");
        agregarColumna(l, "productos", "submarca");
        agregarColumna(l, "productos", "personaje", false, false);
        agregarColumna(l, "productos", "color", false, false);
        agregarColumna(l, "productos", "sabor", false, false);
        agregarColumna(l, "productos", "aroma", false, false);
        agregarColumna(l, "productos", "activo_suspendido", false, false);
        agregarColumna(l, "productos", "in_out_linea");
        agregarColumna(l, "productos", "alto", false, false);
        agregarColumna(l, "productos", "ancho", false, false);
        agregarColumna(l, "productos", "profundidad", false, false);
        agregarColumna(l, "productos", "peso", false, false);
        agregarColumna(l, "productos", "presentacion");
        agregarColumna(l, "productos", "presentacion_ml");
        agregarColumna(l, "productos", "contenido_neto", false, false);
        agregarColumna(l, "productos", "unidades", false, false);
        agregarColumna(l, "productos", "franquiciatario", false, false);
        agregarColumna(l, "productos", "cavidades", false, false);
        agregarColumna(l, "productos", "factor8oz", false, false);
        agregarColumna(l, "productos", "factorcl", false, false);
        agregarColumna(l, "productos", "factorcf", false, false);
        agregarColumna(l, "productos", "retornable", false, false);
        agregarColumna(l, "productos", "tipo_envase", false, false);
        agregarColumna(l, "productos", "tipo_serv", false, false);
        agregarColumna(l, "productos", "botellas_caja_fisica", false, false);
        agregarColumna(l, "productos", "tier1");
        agregarColumna(l, "productos", "etapa");
        agregarColumna(l, "productos", "genero");
        agregarColumna(l, "productos", "clave_sist", false, false);

        agregarColumna(l, "fechas", "fecha");
        agregarColumna(l, "fechas", "semana_domingo");
        agregarColumna(l, "fechas", "num_semana_domingo");
        agregarColumna(l, "fechas", "semana_lunes");
        agregarColumna(l, "fechas", "num_semana_lunes");
        agregarColumna(l, "fechas", "semana_wm_pre");
        agregarColumna(l, "fechas", "semana_wm");
        agregarColumna(l, "fechas", "num_semana_wm");
        agregarColumna(l, "fechas", "num_dia");
        agregarColumna(l, "fechas", "nombre_dia");
        agregarColumna(l, "fechas", "num_dia_anio");
        agregarColumna(l, "fechas", "num_dia_mes");
        agregarColumna(l, "fechas", "num_mes");
        agregarColumna(l, "fechas", "nombre_mes");
        agregarColumna(l, "fechas", "anio");
        agregarColumna(l, "fechas", "anio_siglo");
        agregarColumna(l, "fechas", "trimestre");
        agregarColumna(l, "fechas", "cuatrimeste");

        IU7.ds.datastore.put(l);
        this.armarTabla();
    }

    private void agregarColumna(List l, String tabla, String columna)
    {
        agregarColumna(l, tabla, columna, true, true);
    }

    private void agregarColumna(List l, String tabla, String columna, boolean sel, boolean fil)
    {
        Entity m = new Entity("iq3_columnas", tabla + "-" + columna);
        //m.put("id", tabla + "-" + columna);
        m.setProperty("tabla", tabla);
        m.setProperty("columna", columna);
        m.setProperty("descripcion", columna);
        m.setProperty("seleccionable", sel);
        m.setProperty("filtrable", fil);
        l.add(m);
    }
}
