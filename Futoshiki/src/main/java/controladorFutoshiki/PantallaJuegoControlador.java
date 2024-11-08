package controladorFutoshiki;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistaFutoshiki.*;
import modeloFutoshiki.*;

/**
 *
 * @author ximena molina - juan pablo cambronero
 */
public class PantallaJuegoControlador {
    private Juego juego;
    private PantallaJuego2 pantalla;
    
    //constructor
    public PantallaJuegoControlador(Juego juego, PantallaJuego2 pantalla) {
        this.juego = juego;
        this.pantalla = pantalla;
            //constructor
        this.pantalla.btnVolver.addActionListener(new ActionListener() { //espera a que usuario presione el boton de volver
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuPrincipal pantalla = new MenuPrincipal(); //inicializa pantalla configuracion
                pantalla.setVisible(false);
                pantalla.setVisible(true);
                MenuPrincipalControlador controlador = new MenuPrincipalControlador(juego,pantalla);// envia las clases
                                                                                            //necesarias al controlador del menu principal
            }
        });
    }
    
    
}
