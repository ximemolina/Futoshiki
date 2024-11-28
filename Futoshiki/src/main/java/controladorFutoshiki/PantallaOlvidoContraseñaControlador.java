package controladorFutoshiki;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import vistaFutoshiki.*;
import modeloFutoshiki.*;

/**
 *
 * @author ximena molina - juan pablo cambronero
 */
public class PantallaOlvidoContraseñaControlador {
    private PantallaOlvidoContraseña menu;
    private Juego juego;
    private String pin;
    //constructor

    /**
     *
     * @param menu
     * @param juego
     * @param pin
     */
    public PantallaOlvidoContraseñaControlador(PantallaOlvidoContraseña menu, Juego juego, String pin) {
        this.menu = menu;
        this.juego = juego;
        this.pin = pin;
        
        this.menu.btnVolver.addActionListener(new ActionListener() { //espera a que usuario presione el boton de volver
            @Override
            public void actionPerformed(ActionEvent e) {
                juego.setJugador(null); //se borra jugador previamente asignado
                MenuConfiguracion pantalla = new MenuConfiguracion(); //inicializa pantalla configuracion
                menu.setVisible(false);
                pantalla.setVisible(true);
                MenuConfiguracionControlador controlador = new MenuConfiguracionControlador(juego,pantalla);// envia las clases
                                                                                            //necesarias al controlador del menu de config
            }
        });
        
        this.menu.inpPin.getDocument().addDocumentListener(new DocumentListener() { //espera a que usuario presione el boton de volver

            @Override
            public void insertUpdate(DocumentEvent e) {
                validarPin();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validarPin();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarPin();
            }
        });
        
        this.menu.btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!menu.inpNuevaContraseña.getText().isEmpty()){
                    Archivo.modificarContrasena(juego.getJugador().getNombre(), menu.inpNuevaContraseña.getText()); //modifica la contrasena en el archivo
                    juego.setJugador(null); // borra jugador que se habia guardado ya que ya fue utilizada su informacion
                    MenuConfiguracion pantalla = new MenuConfiguracion(); //inicializa pantalla configuracion
                    menu.setVisible(false);
                    pantalla.setVisible(true);
                    MenuConfiguracionControlador controlador = new MenuConfiguracionControlador(juego,pantalla);// envia las clases
                                                                                            //necesarias al controlador del menu de config
                    
                } else JOptionPane.showMessageDialog(null, "Debe ingresar una nueva contraseña", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        
        });
        
    }
    
    void validarPin(){
        if(menu.inpPin.getText().trim().equals(pin.trim())){
           menu.inpNuevaContraseña.setEnabled(true);
        }else{
           menu.inpNuevaContraseña.setEnabled(false);
           menu.inpNuevaContraseña.setText("");
        }
            
    }
    
}
