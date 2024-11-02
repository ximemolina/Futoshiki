package controladorFutoshiki;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistaFutoshiki.*;
import modeloFutoshiki.*;

/**
 *
 * @author ximena molina - juan pablo cambronero
 */
public class MenuConfiguracionControlador {
    private Juego juego;
    private MenuConfiguracion menu;

    //constructor
    public MenuConfiguracionControlador(Juego juego, MenuConfiguracion menu) {
        this.juego = juego;
        this.menu = menu;
        
        
        this.menu.btnVolver.addActionListener(new ActionListener() { //espera a que usuario presione el boton de config
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuPrincipal pantalla = new MenuPrincipal(); //inicializa pantalla configuracion
                menu.setVisible(false);
                pantalla.setVisible(true);
                MenuPrincipalControlador controlador = new MenuPrincipalControlador(juego,pantalla);// envia las clases
                                                                                            //necesarias al controlador de la config
            }
        });
    }
    
}
