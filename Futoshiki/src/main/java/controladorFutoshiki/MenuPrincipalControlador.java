package controladorFutoshiki;

import vistaFutoshiki.*;
import modeloFutoshiki.*;

import java.awt.event.*;
/**
 *
 * @author ximena molina - juan pablo cambronero
 */
public class MenuPrincipalControlador {
    private Juego juego;
    private MenuPrincipal menu;
    
    //constructor
    public MenuPrincipalControlador(Juego juego, MenuPrincipal menu) {
        this.juego = juego;
        this.menu = menu;
        
        this.menu.btnConfiguracion.addActionListener(new ActionListener() { //espera a que usuario presione el boton de config
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuConfiguracion pantalla = new MenuConfiguracion(); //inicializa pantalla configuracion
                menu.setVisible(false);
                pantalla.setVisible(true);
                MenuConfiguracionControlador controlador = new MenuConfiguracionControlador(juego,pantalla);// envia las clases
                                                                                            //necesarias al controlador de la config
            }
        });
    
        this.menu.btnJugar.addActionListener(new ActionListener() { //espera a que usuario presione el boton de jugar
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaJuego2 pantalla = new PantallaJuego2(juego); //inicializa pantalla jugar
                menu.setVisible(false);
                pantalla.setVisible(true);
                PantallaJuegoControlador controlador = new PantallaJuegoControlador(juego,pantalla);// envia las clases
                                                                                           //necesarias al controlador de la pantalla jugar
            }
        });
    
        this.menu.btnTop10.addActionListener(new ActionListener() { //espera a que usuario presione el boton de Top 10
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaTop10 pantalla = new PantallaTop10(); //inicializa pantalla Top10
                menu.setVisible(false);
                pantalla.setVisible(true);
                PantallaTop10Controlador controlador = new PantallaTop10Controlador(juego,pantalla);// envia las clases
                                                                                           //necesarias al controlador de la pantalla Top10
            }
        });
    }
    
    
}
