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
            BufferedReader br = new BufferedReader(new StringReader(fos.toString()));
            int n = 0;
            List lista = new ArrayList();
            while ((linea = br.readLine()) != null)
            {
                //System.out.println(linea);
                String[] l = linea.split("\t");
                // Aqui hay que armar el mapa de las tiendas e inyectarlo
//                Map m = new HashMap();
                String id = l[0] + "-" + l[2];

                Entity e = new Entity("iq3_tiendas", id);
                e.setProperty("id", id);
                e.setProperty("operador", l[0]);
                e.setProperty("compania", l[1]);
                e.setProperty("determinante", l[2]);

                lista.add(e);
                if (lista.size() > 100)
                {
                    datastore.put(lista);
                    lista = new ArrayList();
                }

                n++;
            }
            System.out.println("Leidos bien " + n + " lineas.");
        } catch (Exception e)
        {
            System.err.println("Error: " + e);
        }

    }
}
