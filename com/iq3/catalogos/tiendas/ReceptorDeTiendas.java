/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iq3.catalogos.tiendas;

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
public class ReceptorDeTiendas implements Receiver, SucceededListener
{

    ByteArrayOutputStream fos = null;
    private final ABCTiendas abctiendas;

    public ReceptorDeTiendas(ABCTiendas abctiendas)
    {
        this.abctiendas = abctiendas;
    }

    @Override
    public OutputStream receiveUpload(String filename,
                                      String mimeType)
    {
        System.out.println("Recibiendo archivo de tiendas: " + filename + "/" + mimeType);
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
                //System.out.println(linea);
                String[] l = linea.split("\t",-1);
                // Aqui hay que armar el mapa de las tiendas e inyectarlo
//                Map m = new HashMap();
                String id = l[0] + "-" + l[2];

                Entity e = new Entity("iq3_tiendas", id);
                e.setProperty("id", id);
                e.setProperty("operador", l[0]);
                e.setProperty("compania", l[1]);
                e.setProperty("determinante", l[2]);
                e.setProperty("nombre", l[3]);
                e.setProperty("formato", l[4]);
                e.setProperty("formato_codigo", l[5]);
                e.setProperty("fecha_apertura", l[6]);
                e.setProperty("cp", l[7]);
                e.setProperty("asentamiento", l[8]);
                e.setProperty("municipio", l[9]);
                e.setProperty("estado", l[10]);
                e.setProperty("ciudad", l[11]);
                e.setProperty("coordenadas_geograficas", l[12]);
                e.setProperty("zona_nielsen", l[13]);
                e.setProperty("direccion", l[14]);
                e.setProperty("telefono", l[15]);
                e.setProperty("scantrack", l[16]);
                e.setProperty("colonia", l[17]);
                e.setProperty("impuesto", l[18]);
                e.setProperty("operador_determinante", l[19]);
                e.setProperty("region", l[20]);
                e.setProperty("territorio", l[21]);
                e.setProperty("clave_cedis", l[22]);
                e.setProperty("cedis", l[23]);
                e.setProperty("nud", l[24]);
                e.setProperty("nombre_cliente", l[25]);
                e.setProperty("status", l[26]);
                e.setProperty("clave_ruta_entrega", l[27]);
                e.setProperty("team_leader", l[28]);
                e.setProperty("formato_cliente", l[29]);
                e.setProperty("fecha_alta", l[30]);
                e.setProperty("fecha_baja", l[31]);
                e.setProperty("operador_cliente", l[32]);
                e.setProperty("cadena_cliente", l[33]);
                e.setProperty("zona_venta", l[34]);
                e.setProperty("promotor", l[35]);
                e.setProperty("agencia", l[36]);
                e.setProperty("determinante_cliente", l[37]);
                e.setProperty("supervisor", l[38]);
                e.setProperty("vendedor", l[39]);
                e.setProperty("poblacion_base", l[40]);
                e.setProperty("agencia_1", l[41]);
                e.setProperty("demostradora", l[42]);
                
                

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
        }

    }
}
