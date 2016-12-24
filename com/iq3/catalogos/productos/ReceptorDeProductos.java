/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iq3.catalogos.productos;

import com.coatl.appengine.IU7;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author matus
 */
public class ReceptorDeProductos implements Receiver, SucceededListener
{
    
     ByteArrayOutputStream fos = null;
    private final ABCProductos abcproductos;

    public ReceptorDeProductos(ABCProductos abcproductos)
    {
        this.abcproductos = abcproductos;
    }

    @Override
    public OutputStream receiveUpload(String filename,
                                      String mimeType)
    {
        System.out.println("Recibiendo archivo de productos: " + filename + "/" + mimeType);
        try
        {
            fos = new ByteArrayOutputStream();
        } catch (Exception e)
        {
            new Notification("Imposible recibir el archivo<br/>",
                             e.getMessage(),
                             Notification.Type.ERROR_MESSAGE)
                    .show(Page.getCurrent());
            return null;
        }
        return fos;
    }

    @Override
    public void uploadSucceeded(SucceededEvent event)
    {
        System.out.println("Recibidos bien " + fos.size() + " bytes.");

        /*
        * Aqui posiblemente hay que borrar las tiendas anteriores
         */
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        String linea;

        try
        {
            BufferedReader br = new BufferedReader(new StringReader(new String(fos.toByteArray(),"UTF8")));
            int n = 0;
            List lista = new ArrayList();
            while ((linea = br.readLine()) != null)
            {
               System.out.println(linea);
                String[] l = linea.split("\t",-1);
                // Aqui hay que armar el mapa de los productos e inyectarlo
//                Map m = new HashMap();
                String id = l[0] ;

                Entity e = new Entity("iq3_productos", id);
                e.setProperty("id", id);
                e.setProperty("upc", l[0]);
                e.setProperty("dun14", l[1]);
                e.setProperty("cantidad_empaque", l[2]);
                e.setProperty("sku_interno", l[3]);
                e.setProperty("sku_ched", l[4]);
                e.setProperty("descripcion", l[5]);
                e.setProperty("departamento", l[6]);
                e.setProperty("familia", l[7]);
                e.setProperty("subfamilia", l[8]);
                e.setProperty("licencia", l[9]);
                e.setProperty("marca", l[10]);
                e.setProperty("submarca", l[11]);
                e.setProperty("personaje", l[12]);
                e.setProperty("color", l[13]);
                e.setProperty("sabor", l[14]);
                e.setProperty("aroma", l[15]);
                e.setProperty("activo_suspendido", l[16]);
                e.setProperty("in_out_linea", l[17]);
                e.setProperty("alto", l[18]);
                e.setProperty("ancho", l[19]);
                e.setProperty("profundidad", l[20]);
                e.setProperty("peso", l[21]);
                e.setProperty("presentacion", l[22]);
                e.setProperty("presentacion_ml", l[23]);
                e.setProperty("contenido_neto", l[24]);
                e.setProperty("unidades", l[25]);
                e.setProperty("franquiciatario", l[26]);
                e.setProperty("cavidades", l[27]);
                e.setProperty("factor8oz", l[28]);
                e.setProperty("factorcl", l[29]);
                e.setProperty("factorcf", l[30]);
                e.setProperty("retornable", l[31]);
                e.setProperty("tipo_envase", l[32]);
                e.setProperty("tipo_serv", l[33]);
                e.setProperty("botellas_caja_fisica", l[34]);
                e.setProperty("tier1", l[35]);
                e.setProperty("etapa", l[36]);
                e.setProperty("genero", l[37]);
                e.setProperty("clave_sist", l[38]);
                
                
                

                lista.add(e);
                if (lista.size() > 100)
                {
                    datastore.put(lista);
                    lista = new ArrayList();
                }

                n++;
            }
            if (lista.size() > 0)
            {
                datastore.put(lista);
            }
            System.out.println("Leidos bien " + n + " lineas.");
        } catch (Exception e)
        {
            System.err.println("Error: " + e);
            e.printStackTrace();
        }

    }

}
