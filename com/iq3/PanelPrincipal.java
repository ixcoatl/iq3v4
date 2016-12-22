/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iq3;

import com.vaadin.server.FileResource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Image;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Link;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

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
    private final TabSheet tabsheet;
    Map tabs = new HashMap();

    public PanelPrincipal()
    {
        this.principal = new VerticalSplitPanel();
        this.principal.setSizeFull();
        this.principal.setSplitPosition(45, Sizeable.UNITS_PIXELS, false);
        this.addComponent(principal);

        this.panelMenu = new HorizontalLayout();
        this.ponerLogo();
        this.principal.setFirstComponent(panelMenu);

        this.tabsheet = new TabSheet();
        this.tabsheet.setSizeFull();
        this.principal.setSecondComponent(tabsheet);

        this.menu = new MenuBar();
        this.panelMenu.addComponent(menu);

        menuAdministracion();
        menuReportes();

    }

    public void agregarTab(String nombre, AbstractComponent componente)
    {
        if (tabs.get(nombre) != null)
        {
            return;
        }
        tabs.put(nombre, componente);
        tabsheet.addTab(componente, nombre);

    }

    public final void menuAdministracion()
    {
        // MENU DE ADMINISTRACIÓN
        {
            this.menuAdmin = this.menu.addItem("Adminstración", null, null);

            this.menuAdmin.addItem("Administradores del sistema", null, new MenuBar.Command()
                           {
                               @Override
                               public void menuSelected(MenuItem selectedItem)
                               {
                                   System.out.println("Administradores del sistema");
                                   agregarTab("Administradores", (AbstractComponent) new Panel("Administradores"));
                               }
                           });

            this.menuAdmin.addItem("Usuarios del sistema", null, new MenuBar.Command()
                           {
                               @Override
                               public void menuSelected(MenuItem selectedItem)
                               {
                                   System.out.println("Usuarios del sistema");
                                   agregarTab("Usuarios", (AbstractComponent) new Panel("Usuarios"));
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
                                            agregarTab("Totales por fecha", (AbstractComponent) new Panel("Totales por fecha"));
                                        }
                                    });
        }
    }

    public final void ponerLogo()
    {
        String basepath = VaadinService.getCurrent()
                .getBaseDirectory().getAbsolutePath();

        FileResource resource = new FileResource(new File(basepath
                                                          + "/img/logo-iq3-40px.png"));

        Image image = new Image(null, resource);
        this.panelMenu.addComponent(image);
    }
}
