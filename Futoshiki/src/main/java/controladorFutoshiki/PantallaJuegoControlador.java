package controladorFutoshiki;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import vistaFutoshiki.*;
import modeloFutoshiki.*;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

/**
 *
 * @author ximena molina - juan pablo cambronero
 */
public class PantallaJuegoControlador {
    private Juego juego;
    private PantallaJuego2 pantalla;
    private Timer temporizador;
    private MatrizJuego matriz;
    
    //constructor
    public PantallaJuegoControlador(Juego juego, PantallaJuego2 pantalla) {
        this.juego = juego;
        this.pantalla = pantalla;
        this.matriz = juego.getMatriz();
        
        inicializarVista();

        
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
        
        this.pantalla.btnJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarTemporizadorSiEsNecesario();
            }
        });
        
    }

    
    // Inicializa la tabla del temporizador con los valores iniciales
    private void inicializarTablaTemporizador() {
        DefaultTableModel model = (DefaultTableModel) pantalla.getTblTemporizador().getModel();
        Reloj reloj = juego.getReloj();
        model.setRowCount(0); // Limpia la tabla
        model.addRow(new Object[]{reloj.getHoras(), reloj.getMinutos(), reloj.getSegundos()});
    }
    
    // Inicia el temporizador si el juego está configurado como temporizador
    private void iniciarTemporizadorSiEsNecesario() {
        if (temporizador != null && temporizador.isRunning()) {
            temporizador.stop(); // Detener cualquier temporizador previo
        }

        temporizador = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (juego.getReloj().getTipo() == 2) { // Tipo 2: Temporizador
                    actualizarTemporizador();
                } else if (juego.getReloj().getTipo() == 1) { // Tipo 1: Cronómetro
                    actualizarCronometro();
                }
            }
        });
        temporizador.start();
    }
    
    private void actualizarCronometro() {
        Reloj reloj = juego.getReloj();

        // Incrementa el tiempo en el objeto Reloj
        if (reloj.getSegundos() < 59) {
            reloj.setSegundos(reloj.getSegundos() + 1);
        } else {
            reloj.setSegundos(0);
            if (reloj.getMinutos() < 59) {
                reloj.setMinutos(reloj.getMinutos() + 1);
            } else {
                reloj.setMinutos(0);
                reloj.setHoras(reloj.getHoras() + 1);
            }
        }

        // Actualiza la fila del JTable con el tiempo actual del cronómetro
        if (pantalla.getTblTemporizador() != null) {
            DefaultTableModel model = (DefaultTableModel) pantalla.getTblTemporizador().getModel();
            model.setValueAt(reloj.getHoras(), 0, 0);
            model.setValueAt(reloj.getMinutos(), 0, 1);
            model.setValueAt(reloj.getSegundos(), 0, 2);
        }
    }


    
    // Actualiza el temporizador en el JTable y detiene el temporizador cuando llega a cero
    private void actualizarTemporizador() {
        Reloj reloj = juego.getReloj();
        
        if (reloj.getSegundos() > 0) {
            reloj.setSegundos(reloj.getSegundos() - 1);
        } else if (reloj.getMinutos() > 0) {
            reloj.setSegundos(59);
            reloj.setMinutos(reloj.getMinutos() - 1);
        } else if (reloj.getHoras() > 0) {
            reloj.setSegundos(59);
            reloj.setMinutos(59);
            reloj.setHoras(reloj.getHoras() - 1);
        } else {
            temporizador.stop();
            JOptionPane.showMessageDialog(pantalla, "¡El tiempo ha terminado!");
            
            // Ir al menú principal después de que se cierre el mensaje
            pantalla.setVisible(false); // Oculta la pantalla actual
            MenuPrincipal pantallaMenu = new MenuPrincipal();
            pantallaMenu.setVisible(true);

            new MenuPrincipalControlador(juego, pantallaMenu);
        }
        
        // Actualiza la fila del JTable con el tiempo restante
        DefaultTableModel model = (DefaultTableModel) pantalla.getTblTemporizador().getModel();
        model.setValueAt(reloj.getHoras(), 0, 0);
        model.setValueAt(reloj.getMinutos(), 0, 1);
        model.setValueAt(reloj.getSegundos(), 0, 2);
    }
    
    
    // Inicializa la vista con los datos del juego
    private void inicializarVista() {
        mostrarNombreJugador();
        mostrarNivel();
        inicializarTablaTemporizador();
    }
    
    // Muestra el nombre del jugador en la etiqueta lblNombre
    private void mostrarNombreJugador() {
        String nombre = juego.getJugador() != null ? juego.getJugador().getNombre() : null;

        if (nombre == null || nombre.trim().isEmpty()) {
            this.pantalla.lblNombre.setText("Incógnito");
        } else {
            this.pantalla.lblNombre.setText(nombre);
        }
    }

    // Muestra el nivel del juego en la etiqueta lblNivel
    private void mostrarNivel() {
        if (juego.getNivel() == 0)this.pantalla.lblNivel.setText("Nivel fácil");
        if (juego.getNivel() == 1)this.pantalla.lblNivel.setText("Nivel intermedio");
        if (juego.getNivel() == 2)this.pantalla.lblNivel.setText("Nivel difícil");
    }
    

      
}

    
    

