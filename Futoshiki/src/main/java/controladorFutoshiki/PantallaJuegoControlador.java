package controladorFutoshiki;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import vistaFutoshiki.*;
import modeloFutoshiki.*;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.border.Border;

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
        
        for (JButton boton : this.pantalla.botonesNumeros){
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    resetearBotonesNumeros(boton);
                }
            });
        }
        
        for(JButton boton : this.pantalla.botones){
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    seleccionarBoton(boton);
                }
            });
        }
        
        this.pantalla.btnBorrador.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                resetearBotonesNumeros(pantalla.btnBorrador);
                Border borde = BorderFactory.createLineBorder(Color.GREEN, 10);
                System.out.println("hola");
                pantalla.btnBorrador.setBackground(Color.GREEN);
                
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
    
    //despliega todas las constantes en la plantilla
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
               boton.setText("<html><b style='color: black; font-size: 14px;'>" + constante + "</b></html>");
               

               // Condición para cambiar el color y la fuente dependiendo del nivel del juego
               if(juego.getNivel() == 9 || juego.getNivel() == 8 || juego.getNivel() == 10){
                   boton.setForeground(Color.BLACK); // Establecer el color del texto
                   boton.setFont(new Font(boton.getFont().getName(), Font.BOLD, 14)); 
               }
               boton.setEnabled(false); // creo que ya funciona lo de negrita
               
               


            }
            contadorColum ++;
        }
 
    }
    
    //se encargar de mostrar las desigualdades que están en las filas (maf - mef)
    private void desigualdadesFila(int columna, int fila, boolean identificador){
        ArrayList<JLabel> lista = pantalla.desigualdades;
        int contadorColum = 0;
        int contadorFila = 0;
        boolean revisar = true;
        for (JLabel label : lista){
            if (contadorColum >= juego.getTamano()-1) {
                
                if (!revisar && contadorColum >= juego.getTamano() ) {
                    contadorFila++;
                    revisar = true;
                    contadorColum = 0;
                }else if (revisar ){
                    contadorColum = 0;
                    revisar = false;
                }
            }
            if (contadorFila == fila && contadorColum == columna && revisar){
                if(identificador) label.setText(">");
                else label.setText("<");
                break;
            }
            contadorColum ++;
        }
    }
    
    //se encargar de mostrar las desigualdades que están en las columnas(mac - mec)
    private void desigualdadesColumna(int columna, int fila, boolean identificador){
        ArrayList<JLabel> lista = pantalla.desigualdades;
        int contadorColum = 0;
        int contadorFila = 0;
        boolean revisar = true;
        for (JLabel label : lista){
            if (contadorColum >= juego.getTamano()-1) {
                
                if (!revisar && contadorColum >= juego.getTamano() ) {
                    contadorFila++;
                    revisar = true;
                    contadorColum = 0;
                }else if (revisar ){
                    contadorColum = 0;
                    revisar = false;
                }
            }
            if (contadorFila == fila && contadorColum == columna && !revisar){
                if(identificador) label.setText("V");
                else label.setText("^");
                break;
            }
            contadorColum ++;
        }
    
    }
    
    // muestra constantes y desigualdades del tablero
    private void elementosJuego(){
        int indice = partidaAzar();
        String lista = String.valueOf(matriz.getValoresArchivoPartida().get(indice));
        String[] valores = lista.split(",");
        int columna = 0;
        int fila = 0;
        int constante = 0;
        boolean identificador; //false: menor || true: mayor
        for (int i=0; i<valores.length;i++){
            if ((String.valueOf(valores[i])).trim().equals("const")){

                constante = Integer.parseInt((String.valueOf(valores[i+1]).replaceAll("[\\[\\]]", "").trim()));
                columna= Integer.parseInt((String.valueOf(valores[i+3]).replaceAll("[\\[\\]]", "").trim()));
                fila = Integer.parseInt((String.valueOf(valores[i+2]).replaceAll("[\\[\\]]", "").trim()));
                mostrarConstante(columna,fila, constante); //actualiza la pantalla con las constantes
            }
            if ((String.valueOf(valores[i]).replaceAll("[\\[\\]]", "")).trim().equals("maf")){ //mayor en fila 
                fila = Integer.parseInt((String.valueOf(valores[i+1]).replaceAll("[\\[\\]]", "").trim()));
                columna= Integer.parseInt((String.valueOf(valores[i+2]).replaceAll("[\\[\\]]", "").trim()));
                identificador = true;
                desigualdadesFila(columna, fila, identificador);
            } else if((String.valueOf(valores[i]).replaceAll("[\\[\\]]", "").trim().equals("mef"))){ // menor en fila
                fila = Integer.parseInt((String.valueOf(valores[i+1]).replaceAll("[\\[\\]]", "").trim()));
                columna= Integer.parseInt((String.valueOf(valores[i+2]).replaceAll("[\\[\\]]", "").trim()));
                identificador = false;
                desigualdadesFila(columna, fila, identificador);
            } else if ((String.valueOf(valores[i])).trim().equals("mac")){ // mayor en columna
                fila = Integer.parseInt((String.valueOf(valores[i+1]).replaceAll("[\\[\\]]", "").trim()));
                columna= Integer.parseInt((String.valueOf(valores[i+2]).replaceAll("[\\[\\]]", "").trim()));
                identificador = true;
                desigualdadesColumna(columna, fila, identificador);
            } else if ((String.valueOf(valores[i])).trim().equals("mec")){ // menor en columna
                fila = Integer.parseInt((String.valueOf(valores[i+1]).replaceAll("[\\[\\]]", "").trim()));
                columna= Integer.parseInt((String.valueOf(valores[i+2]).replaceAll("[\\[\\]]", "").trim()));
                identificador = false;
                desigualdadesColumna(columna, fila, identificador);
            }
            
        
        }
        juego.getMatriz().getValoresArchivoPartida().remove(indice); //eliminar de lista para que no vuelva a aparecer
        
    }
    
    // pone el boton seleccionado en verde y hace que los demás sean blancos
    void resetearBotonesNumeros(JButton boton){
        for (Component comp: matriz.getBotonesNumeros()){
            if (!comp.equals(boton)){ //verificar que no sea el boton que está siendo seleccionado
                comp.setBackground(Color.WHITE);
            } else comp.setBackground(Color.GREEN);
        }
        if(!boton.equals(pantalla.btnBorrador)) pantalla.btnBorrador.setBackground(Color.WHITE);
    }
      
    // pone en el boton el número seleccionado
    void seleccionarBoton(JButton boton){
        for(Component comp : matriz.getBotonesCasillas()){
            if (comp.equals(boton)){}
                for (JButton comp2 : matriz.getBotonesNumeros()){
                    if (comp2.getBackground() == Color.GREEN){
                        boton.setText("<html>" + comp2.getText() + "</html>");
                    }
                }
        }
        if (pantalla.btnBorrador.getBackground()== Color.GREEN) boton.setText("");
    }
}

    
    

