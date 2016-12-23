/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iq3.catalogos.tiendas;

import com.coatl.appengine.IU7;
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
import java.util.HashMap;
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
        String linea;

        try
        {
            BufferedReader br = new BufferedReader(new StringReader(fos.toString()));
            int n = 0;
            while ((linea = br.readLine()) != null)
            {
                //System.out.println(linea);
                String[] l = linea.split("\t");
                // Aqui hay que armar el mapa de las tiendas e inyectarlo
                Map m = new HashMap();
                m.put("operador", l[0]);
                m.put("compania", l[1]);
                m.put("determinante", l[2]);
                n++;
                //IU7.ds.guardar("iq3_tiendas", m);
            }
            System.out.println("Leidos bien " + n + " lineas.");
        } catch (Exception e)
        {
            System.err.println("Error: " + e);
        }

    }
}
