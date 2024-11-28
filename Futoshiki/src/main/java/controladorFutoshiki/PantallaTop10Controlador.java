package controladorFutoshiki;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistaFutoshiki.*;
import modeloFutoshiki.*;
/**
 *
 * @author ximena molina - juan pablo cambronero
 */
public class PantallaTop10Controlador {
    private Juego juego;
    private PantallaTop10 pantalla;
    
    //constructor

    /**
     *
     * @param juego
     * @param pantalla
     */
    public PantallaTop10Controlador(Juego juego, PantallaTop10 pantalla) {
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
        
        this.pantalla.comboBoxCuadricula.addActionListener(new ActionListener() { //espera a que usuario presione el boton de volver
            @Override
            public void actionPerformed(ActionEvent e) {
                int cuadricula = Integer.parseInt(String.valueOf(pantalla.comboBoxCuadricula.getSelectedItem()).split("x")[0]);
                Archivo.cargarTablaPosiciones(cuadricula, pantalla.tablaPosiciones); //actualiza tabla con valores
            }
        });
        
        
    }
        
}
