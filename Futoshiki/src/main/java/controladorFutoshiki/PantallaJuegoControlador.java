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
                
        this.pantalla.btnVolver.addActionListener(new ActionListener() { //espera a que usuario presione el boton de volver
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuPrincipal pantalla2 = new MenuPrincipal(); //inicializa pantalla configuracion
                pantalla.setVisible(false);
                pantalla2.setVisible(true);
                MenuPrincipalControlador controlador = new MenuPrincipalControlador(juego,pantalla2);// envia las clases
                                                                                            //necesarias al controlador del menu principal
            }
        });
        
        try{
            this.pantalla.lblNombre.setText(juego.getJugador().getNombre()); //mostrar nombre de jugador
        }catch( NullPointerException e){
            this.pantalla.lblNombre.setText("Incógnito"); //si no hay nombre, se toma como incognito
        }
        
        this.pantalla.lblNivel.setText("Nivel "+ juego.getNivel());
    }
    
    
}
