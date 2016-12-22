/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iq3;

import com.vaadin.server.FileResource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Image;
import com.vaadin.ui.Link;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import java.io.File;

/**
 *
 * @author matus
 */
public class PanelPrincipal extends VerticalLayout
{

    private VerticalSplitPanel principal;
    private MenuBar menu = null;
    private HorizontalLayout panelMenu = null;
    private MenuBar.MenuItem menuAdmin = null;
    private MenuItem menuReportes = null;
    private MenuItem menuTotalesSimples = null;

    public PanelPrincipal()
    {
        this.principal = new VerticalSplitPanel();
        this.principal.setSizeFull();
        this.principal.setSplitPosition(45, Sizeable.UNITS_PIXELS, false);
        this.addComponent(principal);

        this.panelMenu = new HorizontalLayout();
        this.ponerLogo();
        this.principal.setFirstComponent(panelMenu);

        this.menu = new MenuBar();
        this.panelMenu.addComponent(menu);

        menuAdministracion();
        menuReportes();

    }

    public final void menuAdministracion()
    {
        // MENU DE ADMINISTRACIÓN
        {
            this.menuAdmin = this.menu.addItem("Adminstración", null, null);

            this.menuAdmin.addItem("Usuarios administradores", null, new MenuBar.Command()
                           {
                               @Override
                               public void menuSelected(MenuItem selectedItem)
                               {
                                   System.out.println("Usuarios administradores");
                               }
                           });

            this.menuAdmin.addItem("Usuarios del sistema", null, new MenuBar.Command()
                           {
                               @Override
                               public void menuSelected(MenuItem selectedItem)
                               {
                                   System.out.println("Usuarios del sistema");
                               }
                           });
        }
    }

    public final void menuReportes()
    {
        // MENU DE REPORTES
        {
            this.menuReportes = menu.addItem("Reportes", null, null);
            this.menuTotalesSimples = this.menuReportes.addItem("Totales simples", null, null);

            this.menuTotalesSimples.addItem("Totales por fecha", null, new MenuBar.Command()
                                    {
                                        @Override
                                        public void menuSelected(MenuItem selectedItem)
                                        {
                                            System.out.println("Totales por fecha");
                                        }
                                    });
        }
    }

    public final void ponerLogo()
    {
        String basepath = VaadinService.getCurrent()
                .getBaseDirectory().getAbsolutePath();

// Image as a file resource
        FileResource resource = new FileResource(new File(basepath
                                                          + "/img/logo-iq3-40px.png"));

// Show the image in the application
        Image image = new Image(null, resource);
        this.panelMenu.addComponent(image);
// Let the user view the file in browser or download it
        //Link link = new Link("Link to the image file", resource);
    }
}
