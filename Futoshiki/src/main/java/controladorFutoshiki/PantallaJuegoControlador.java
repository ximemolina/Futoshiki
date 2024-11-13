package controladorFutoshiki;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
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
        elementosJuego();
        
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
    
        // selecciona al azar cual partida mostrar
    private int partidaAzar(){
        Random random = new Random();
        return random.nextInt((matriz.getValoresArchivoPartida().size()-1 - 0) + 1) + 0;
        
    }
    
    private void mostrarConstante(int columna, int fila, int constante){
        ArrayList<JButton> lista = pantalla.botones;
        int contadorColum = 0;
        int contadorFila = 0;
        for (JButton boton : lista){
            if (contadorColum == juego.getTamano()) {
                contadorColum = 0;
                contadorFila ++;
            }
            if (contadorColum == columna && contadorFila == fila){
                
               boton.setText("<html>" + String.valueOf(constante) + "</html>");

               // Condición para cambiar el color y la fuente dependiendo del nivel del juego
               if(juego.getNivel() == 9 || juego.getNivel() == 8 || juego.getNivel() == 10){
                   boton.setForeground(Color.BLACK); // Establecer el color del texto
                   boton.setFont(new Font(boton.getFont().getName(), Font.BOLD, 14)); 
               }
               boton.setEnabled(false); // *****************************************aqui faltaría revisar pq no logré q se pusiera en negrita las constantes************

            }
            contadorColum ++;
        }
 
    }
    
    private void mostrarDesigualdades(int columna, int fila){
    
    }
    
    
    // muestra constantes y desigualdades del tablero
    private void elementosJuego(){
        int indice = partidaAzar();
        String lista = String.valueOf(matriz.getValoresArchivoPartida().get(indice));
        String[] valores = lista.split(",");
        int columna = 0;
        int fila = 0;
        int constante = 0;
        for (int i=0; i<valores.length;i++){
            if ((String.valueOf(valores[i])).trim().equals("const")){

                constante = Integer.parseInt((String.valueOf(valores[i+1]).replaceAll("[\\[\\]]", "").trim()));
                columna= Integer.parseInt((String.valueOf(valores[i+3]).replaceAll("[\\[\\]]", "").trim()));
                fila = Integer.parseInt((String.valueOf(valores[i+2]).replaceAll("[\\[\\]]", "").trim()));

                mostrarConstante(columna,fila, constante); //actualiza la pantalla con las constantes
            }
            
            if ((String.valueOf(valores[i])).trim().equals("des")){ //revisar cual desigualdad es 
                
                if ((String.valueOf(valores[i+1])).trim().equals("maf")){ //mayor en fila
                
                } else if ((String.valueOf(valores[i+1])).trim().equals("mef")){ // menor en fila
                
                } else if ((String.valueOf(valores[i+1])).trim().equals("mac")){ // mayor en columna
                    
                }else if ((String.valueOf(valores[i+1])).trim().equals("mec")){ // menor en columna
                
                }
            }
        
        }
        juego.getMatriz().getValoresArchivoPartida().remove(indice); //eliminar de lista para que no vuelva a aparecer
        
    }
    
      
}

    
    

