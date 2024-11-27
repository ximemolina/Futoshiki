package controladorFutoshiki;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import vistaFutoshiki.*;
import modeloFutoshiki.*;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.util.Stack;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.List;



/**
 *
 * @author ximena molina - juan pablo cambronero
 */
public class PantallaJuegoControlador {
    private Juego juego;
    private PantallaJuego2 pantalla;
    private Timer temporizador;
    private MatrizJuego matriz;
    private Stack<Movimiento> pilaJugadas = new Stack<>();
    private Stack<Movimiento> pilaJugadasBorradas = new Stack<>();
    private boolean juegoEnProgreso;
    
    //constructor
    public PantallaJuegoControlador(Juego juego, PantallaJuego2 pantalla) {
        this.juego = juego;
        this.pantalla = pantalla;
        this.matriz = juego.getMatriz();
        this.juegoEnProgreso = false;
        
        
        inicializarVista();
        
        this.pantalla.btnVolver.addActionListener(new ActionListener() { //espera a que usuario presione el boton de volver
            @Override
            public void actionPerformed(ActionEvent e) {
                if(temporizador != null) temporizador.stop();
                MenuPrincipal pantalla2 = new MenuPrincipal(); //inicializa pantalla configuracion
                pantalla.setVisible(false);
                pantalla2.setVisible(true);
                MenuPrincipalControlador controlador = new MenuPrincipalControlador(juego,pantalla2);// envia las clases necesarias al controlador del menu principal
            }
        });
        
        this.pantalla.btnJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!juegoEnProgreso) {
                    if (!matriz.getValoresArchivoPartida().isEmpty()) {
                        iniciarTemporizadorSiEsNecesario();
                        elementosJuego(matriz.partidaAzar()); // Generar una nueva partida
                        juego.getReloj().setHorasIniciales(juego.getReloj().getHoras());
                        juego.getReloj().setMinutosIniciales(juego.getReloj().getMinutos());
                        juego.getReloj().setSegundosIniciales(juego.getReloj().getSegundos());
                        juegoEnProgreso = true;
                    } else {
                        JOptionPane.showMessageDialog(pantalla, "No hay partidas para este nivel");
                        MenuPrincipal pantalla2 = new MenuPrincipal(); 
                        pantalla.setVisible(false);
                        pantalla2.setVisible(true);
                        MenuPrincipalControlador controlador = new MenuPrincipalControlador(juego,pantalla2);
                    }
                } else {
                    // Si ya hay un juego cargado, simplemente inicia el temporizador
                    iniciarTemporizadorSiEsNecesario();
                }
                    }
                });

        this.pantalla.btnBorrarJugada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarJugada();
            }
        });

        this.pantalla.btnRehacerJugada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rehacerJugada();
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
                    try{
                        matriz.validarJugada(boton, pantalla);
                        seleccionarBoton(boton);
                        revisarGane();
                    }catch(Exception w){
                        JOptionPane.showMessageDialog(pantalla, w.getMessage());
                    }
                }
            });
        }
        
        this.pantalla.btnBorrador.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                resetearBotonesNumeros(pantalla.btnBorrador);
                pantalla.btnBorrador.setBackground(Color.GREEN);
                
            }
            
        });
        
        this.pantalla.btnBorrarJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmacion = JOptionPane.showConfirmDialog(pantalla, 
                    "¿Estás seguro de que deseas reiniciar el tablero (excepto constantes)?", 
                    "Confirmación", 
                    JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    borrarJuego();
                }
            }
        });
        
        this.pantalla.btnTerminarJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmacion = JOptionPane.showConfirmDialog(pantalla, 
                    "¿Estás seguro de que deseas terminar el juego y generar una nueva partida?", 
                    "Confirmación", 
                    JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    terminarJuego();
                }
            }
        }); 

        this.pantalla.btnGuardarJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (juegoEnProgreso) { // Solo se puede guardar si el juego ya comenzó
                    String archivo = "partida_guardada.txt";
                    guardarJuego(archivo);
                } else {
                    JOptionPane.showMessageDialog(pantalla, "No puedes guardar un juego antes de iniciarlo.");
                }
            }
        }); 
        
        this.pantalla.btnCargarJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!juegoEnProgreso) { // Solo se puede cargar si el juego aún no ha comenzado
                    String archivo = "partida_guardada.txt";
                    cargarJuego(archivo); // Carga el juego pero no inicia el temporizador
                    pantalla.btnGuardarJuego.setEnabled(true); // Habilitar guardar después de cargar
                } else {
                    JOptionPane.showMessageDialog(pantalla, "No puedes cargar un juego mientras hay uno en progreso.");
                }
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
        pantalla.btnBorrarJuego.setEnabled(false);
        pantalla.btnGuardarJuego.setEnabled(false);
        pantalla.btnBorrarJugada.setEnabled(false);
        pantalla.btnTerminarJuego.setEnabled(false);
        pantalla.btnBorrador.setEnabled(false);
        for (JButton btn : pantalla.botonesNumeros){
            btn.setEnabled(false);
        }
        for(JButton btn: pantalla.botones){
            btn.setEnabled(false);
        }
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
        
    // muestra constantes y desigualdades del tablero
    private void elementosJuego(int indice){
        //activa botones
        pantalla.btnBorrarJuego.setEnabled(true);
        pantalla.btnGuardarJuego.setEnabled(true);
        pantalla.btnBorrarJugada.setEnabled(true);
        pantalla.btnTerminarJuego.setEnabled(true);
        pantalla.btnBorrador.setEnabled(true);
        for (JButton btn : pantalla.botonesNumeros){
            btn.setEnabled(true);
        }
        for(JButton btn: pantalla.botones){
            btn.setEnabled(true);
        }
        //desactiva botones ya que solo se pueden utilizar cuando no se ha iniciado juego
        pantalla.btnCargarJuego.setEnabled(false);
        pantalla.btnJugar.setEnabled(false);
        
        String lista = String.valueOf(matriz.getValoresArchivoPartida().get(indice));
        String[] valores = lista.split(",");
        int columna = 0;
        int fila = 0;
        int constante = 0;
        boolean identificador; //false: menor || true: mayor
        for (int i=0; i<valores.length;i++){
            if ((String.valueOf(valores[i])).replaceAll("[\\[\\]]", "").trim().equals("const")){

                constante = Integer.parseInt((String.valueOf(valores[i+1]).replaceAll("[\\[\\]]", "").trim()));
                columna= Integer.parseInt((String.valueOf(valores[i+3]).replaceAll("[\\[\\]]", "").trim()));
                fila = Integer.parseInt((String.valueOf(valores[i+2]).replaceAll("[\\[\\]]", "").trim()));
                matriz.mostrarConstante(columna,fila, constante); //actualiza la pantalla con las constantes
            }
            if ((String.valueOf(valores[i]).replaceAll("[\\[\\]]", "")).trim().equals("maf")){ //mayor en fila 
                fila = Integer.parseInt((String.valueOf(valores[i+1]).replaceAll("[\\[\\]]", "").trim()));
                columna= Integer.parseInt((String.valueOf(valores[i+2]).replaceAll("[\\[\\]]", "").trim()));
                identificador = true;
                matriz.desigualdadesFila(columna, fila, identificador);
            } else if((String.valueOf(valores[i]).replaceAll("[\\[\\]]", "").trim().equals("mef"))){ // menor en fila
                fila = Integer.parseInt((String.valueOf(valores[i+1]).replaceAll("[\\[\\]]", "").trim()));
                columna= Integer.parseInt((String.valueOf(valores[i+2]).replaceAll("[\\[\\]]", "").trim()));
                identificador = false;
                matriz.desigualdadesFila(columna, fila, identificador);
            } else if ((String.valueOf(valores[i])).replaceAll("[\\[\\]]", "").trim().equals("mac")){ // mayor en columna
                fila = Integer.parseInt((String.valueOf(valores[i+1]).replaceAll("[\\[\\]]", "").trim()));
                columna= Integer.parseInt((String.valueOf(valores[i+2]).replaceAll("[\\[\\]]", "").trim()));
                identificador = true;
                matriz.desigualdadesColumna(columna, fila, identificador);
            } else if ((String.valueOf(valores[i])).replaceAll("[\\[\\]]", "").trim().equals("mec")){ // menor en columna
                fila = Integer.parseInt((String.valueOf(valores[i+1]).replaceAll("[\\[\\]]", "").trim()));
                columna= Integer.parseInt((String.valueOf(valores[i+2]).replaceAll("[\\[\\]]", "").trim()));
                identificador = false;
                matriz.desigualdadesColumna(columna, fila, identificador);
            }
            
        
        }
        juego.getMatriz().getValoresArchivoPartida().remove(indice); //eliminar de lista para que no vuelva a aparecer
        
    }
    
    // pone el boton seleccionado en verde y hace que los demás sean blancos
    void resetearBotonesNumeros(JButton boton){
        for (Component comp: matriz.getBotonesNumeros()){
            if (!comp.equals(boton)){ //verificar que no sea el boton que está siendo seleccionado
                comp.setBackground(new Color(240, 240, 240));
            } else comp.setBackground(Color.GREEN);
        }
        if(!boton.equals(pantalla.btnBorrador)) pantalla.btnBorrador.setBackground(Color.WHITE);
    }
      
    // pone en el boton el número seleccionado
    void seleccionarBoton(JButton boton) {
        // Obtén la fila y columna del botón
        int fila = matriz.obtenerFila(boton);
        int columna = matriz.obtenerColumna(boton);

        // Si un número está seleccionado
        for (Component comp : matriz.getBotonesCasillas()) {
            if (comp.equals(boton)) {
                for (JButton comp2 : matriz.getBotonesNumeros()) {
                    if (comp2.getBackground() == Color.GREEN) { // Número seleccionado en verde
                        String valor = comp2.getText();
                        boton.setText("<html>" + valor + "</html>");
                        registrarJugada(fila, columna, valor); // Registrar la jugada en la pila principal
                    }
                }
            }
        }
        // Si el botón borrador está seleccionado (en verde)
        if (pantalla.btnBorrador.getBackground() == Color.GREEN) {
            // Registra la jugada actual en la pila principal antes de moverla a jugadas borradas
            boton.setBackground(new Color(240, 240, 240));
            String valorActual = boton.getText();
            if (!valorActual.isEmpty()) { // Solo registrar si hay algo que borrar
                Movimiento jugadaActual = new Movimiento(fila, columna, valorActual);
                pilaJugadas.push(jugadaActual); // Registra la jugada en la pila principal
                pilaJugadasBorradas.push(jugadaActual); // Mueve la jugada a la pila de borradas
            }
            boton.setText(""); // Borra el texto del botón
        }
    }

    
    private void registrarJugada(int fila, int columna, String valor) {
        pilaJugadas.push(new Movimiento(fila, columna, valor));
        pilaJugadasBorradas.clear(); // Limpiar las jugadas borradas porque se pierde la posibilidad de rehacer
    }
        
    private void borrarJugada() {
        if (!pilaJugadas.isEmpty()) {
            // Sacar la última jugada de la pila principal
            Movimiento ultimaJugada = pilaJugadas.pop();
            pilaJugadasBorradas.push(ultimaJugada); // Guardar en la pila de jugadas borradas

            // Actualizar el tablero: borrar el valor del botón
            JButton boton = matriz.obtenerBoton(ultimaJugada.getFila(), ultimaJugada.getColumna());
            boton.setText(""); // Dejar el botón vacío
        } else {
            JOptionPane.showMessageDialog(pantalla, "No hay jugadas para borrar.");
        }
    }
    
    private void rehacerJugada() {
        if (!pilaJugadasBorradas.isEmpty()) {
            // Sacar la última jugada borrada
            Movimiento jugadaBorrada = pilaJugadasBorradas.pop();
            pilaJugadas.push(jugadaBorrada); // Volver a agregarla a la pila principal

            // Actualizar el tablero: restaurar el valor del botón
            JButton boton = matriz.obtenerBoton(jugadaBorrada.getFila(), jugadaBorrada.getColumna());
            boton.setText("<html><b>" + jugadaBorrada.getValor() + "</b></html>");
        } else {
            JOptionPane.showMessageDialog(pantalla, "No hay jugadas para rehacer.");
        }
    }
        
    private void borrarJuego() {
        // Recorrer todos los botones del tablero
        for (int i = 0; i < matriz.getBotonesCasillas().size(); i++) {
            JButton boton = matriz.getBotonesCasillas().get(i);

            // Verifica si el botón está deshabilitado (indicador de constante)
            if (!boton.isEnabled()) {
                continue; // Si es una constante, no lo borres
            }

            // Borra el contenido del botón
            boton.setText("");
            boton.setEnabled(true); // Reactiva el botón si estaba deshabilitado
        }

        // No borrar las desigualdades, así que no iteramos sobre pantalla.desigualdades

        // Limpiar las pilas de jugadas
        pilaJugadas.clear();
        pilaJugadasBorradas.clear();
        juegoEnProgreso = false;

        // Mostrar mensaje de confirmación
        JOptionPane.showMessageDialog(pantalla, "El tablero ha sido reiniciado, excepto las constantes y desigualdades.");
    }

   
    
    //limpia todos los botones y desigualdades de la plantilla
    private void limpiarTodo(){

        for (int i = 0; i < matriz.getBotonesCasillas().size(); i++) {
            JButton boton = matriz.getBotonesCasillas().get(i);
            
            boton.setText("");
            boton.setEnabled(true); // Reactiva el botón si estaba deshabilitado
        }
        
        for(JLabel desigualdad : matriz.getDesigualdades()){
            desigualdad.setText("");
        }
        // Limpiar las pilas de jugadas
        pilaJugadas.clear();
        pilaJugadasBorradas.clear();
    }
    
    //se encarga de revisar si todas las casillas están completadas
    private void revisarGane(){
        for (JButton boton : matriz.getBotonesCasillas()){
            if(boton.getText().isEmpty())return;
        }
        
        String tiempo = tiempoGane();
        
        if(!juego.isMultinivel()){ //si es multinivel, continuaría con el siguiente nivel, no finalizaría la partida
            JOptionPane.showMessageDialog(pantalla, "¡Excelente, juego terminado con éxito!");
            
                    
                    
                        try{
                            Archivo.agregarInformacionTop10(juego, tiempo); 
                        }catch(Exception e){}
            

            //luego de ganar, vuelve a menu principal
            MenuPrincipal pantalla2 = new MenuPrincipal(); //inicializa pantalla configuracion
            pantalla.setVisible(false);
            pantalla2.setVisible(true);
            MenuPrincipalControlador controlador = new MenuPrincipalControlador(juego,pantalla2);// envia las clases necesarias al controlador del menu principal
        } else{
            limpiarTodo();
            juego.setTiempoAcumulado(tiempo); // Guarda el tiempo acumulado del nivel actual
            int numNivel = juego.getNivel() +1;
            if (numNivel > 2){ //si ya llegó al nivel máximo (dificil) se queda jugando ahí
                 
                    
                        try{
                            Archivo.agregarInformacionTop10(juego, tiempo); 
                        }catch(Exception e){}
                
                if (!matriz.getValoresArchivoPartida().isEmpty()) {
                    int indice = matriz.partidaAzar();
                    elementosJuego(indice); 
                } else {
                    JOptionPane.showMessageDialog(pantalla, "Ya no hay más partidas disponibles para este nivel.");
                    MenuPrincipal pantalla2 = new MenuPrincipal(); 
                    pantalla.setVisible(false);
                    pantalla2.setVisible(true);
                    new MenuPrincipalControlador(juego, pantalla2);
                }
            }else { //en caso de que esté en nivel facil o intermedio, solo muestra partida de ese nivel
                
                
                    try{
                        Archivo.agregarInformacionTop10(juego, tiempo); 
                    }catch(Exception e){}
                
                juego.setNivel(numNivel);
                Archivo.cargarArchivoPartidas(juego.getNivel(), juego.getTamano()); //carga todas las partidas del siguiente nivel
                List datosJuego = Archivo.cargarArchivoPartidas(juego.getNivel(),juego.getTamano()); // carga info de partidas
                juego.getMatriz().setValoresArchivoPartida(datosJuego);
                this.matriz = juego.getMatriz();
                
                // Configura el tiempo inicial del nuevo nivel con el tiempo acumulado
                tiempoInicial(matriz.partidaAzar(), juego.getTiempoAcumulado());
                limpiarTodo();
                elementosJuego(matriz.partidaAzar());//muestra en pantalla partida
                mostrarNivel(); //actualiza el label de nivel
                resetearBotonesNumeros(pantalla.btnVolver); // manda un boton random para poder llamar a la funcion y limpiar cualquier num que haya quedado seleccionado
                juegoEnProgreso = true;
            }
        }
    }
    
    // Calcula el tiempo de la partida desde que se presionó el botón Jugar hasta ganar
    private String tiempoGane() {
        Reloj reloj = juego.getReloj();
        int horas = 0;
        int minutos = 0;
        int segundos = 0;

        // Si el reloj está configurado como cronómetro
        if (reloj.getTipo() == 1) { // Cronómetro
            horas = reloj.getHoras();
            minutos = reloj.getMinutos();
            segundos = reloj.getSegundos();

        } else if (reloj.getTipo() == 2) { // Temporizador
            // En un temporizador, el tiempo restante debe ser restado del tiempo inicial
            int horasIniciales = juego.getReloj().getHorasIniciales();
            int minutosIniciales = juego.getReloj().getMinutosIniciales();
            int segundosIniciales = juego.getReloj().getSegundosIniciales();

            // Calculamos el tiempo transcurrido
            int totalInicial = (horasIniciales * 3600) + (minutosIniciales * 60) + segundosIniciales;
            int totalRestante = (reloj.getHoras() * 3600) + (reloj.getMinutos() * 60) + reloj.getSegundos();
            int totalTranscurrido = totalInicial - totalRestante;

            horas = totalTranscurrido / 3600;
            minutos = (totalTranscurrido % 3600) / 60;
            segundos = totalTranscurrido % 60;

        } else { // Sin temporizador ni cronómetro
            // Calculamos un tiempo aproximado si no hay medición real (podría ser manual o una variable fija)
            // Aquí podrías incluir una forma de calcular el tiempo si hay una implementación base para esto.
            return "00:00:00"; // Por defecto, retorna 0 si no hay registro
        }

        // Formatear las horas, minutos y segundos para que tengan siempre dos dígitos
        String tiempoFormateado = String.format("%02d:%02d:%02d", horas, minutos, segundos);
        return tiempoFormateado;
    }
    
    private void tiempoInicial(int indicePartida, String tiempoAcumulado) {
        if (tiempoAcumulado == null || tiempoAcumulado.isEmpty()) {
            // Si no hay tiempo acumulado, inicia desde 00:00:00
            juego.setTiempoAcumulado("00:00:00");
        } else {
            // Establece el tiempo acumulado como el tiempo inicial
            juego.setTiempoAcumulado(tiempoAcumulado);
        }


    }


    
    
    private void terminarJuego() {
        // Limpia el tablero actual
        limpiarTodo(); // Reutilizamos el método existente para limpiar el tablero excepto constantes y desigualdades.

        // Selecciona una nueva partida al azar y actualiza los elementos del tablero
        if (!matriz.getValoresArchivoPartida().isEmpty()) {
            int indice = matriz.partidaAzar();
            elementosJuego(indice); // Reutiliza el método para desplegar las constantes y desigualdades de la nueva partida.
            JOptionPane.showMessageDialog(pantalla, "Se ha generado una nueva partida con la misma dificultad.");
        } else {
            JOptionPane.showMessageDialog(pantalla, "No hay más partidas disponibles para este nivel.");
            MenuPrincipal pantalla2 = new MenuPrincipal(); // Regresa al menú principal
            pantalla.setVisible(false);
            pantalla2.setVisible(true);
            new MenuPrincipalControlador(juego, pantalla2);
        }

        // Limpia las pilas de jugadas para el nuevo tablero
        pilaJugadas.clear();
        pilaJugadasBorradas.clear();
        juegoEnProgreso = false;
    }
    
    private void guardarJuego(String archivo) {
        String jugador = pantalla.lblNombre.getText().trim(); // Obtiene el nombre del jugador desde la etiqueta

        if (jugador.equalsIgnoreCase("Incógnito")) {
            JOptionPane.showMessageDialog(pantalla, "No se puede guardar la partida con el nombre 'Incógnito'. Cambie el nombre del jugador.");
            return; // Salir si el jugador es "Incógnito"
        }

        try {
            // Leer las partidas existentes
            ArrayList<String> partidasExistentes = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                StringBuilder partidaActual = new StringBuilder();
                boolean esPartidaJugador = false;

                while ((linea = reader.readLine()) != null) {
                    if (linea.startsWith("JUGADOR:")) {
                        // Si ya hay una partida acumulada, guárdala en la lista
                        if (partidaActual.length() > 0 && !esPartidaJugador) {
                            partidasExistentes.add(partidaActual.toString());
                        }
                        // Inicia una nueva partida
                        partidaActual = new StringBuilder();
                        esPartidaJugador = linea.equalsIgnoreCase("JUGADOR:" + jugador);
                    }
                    // Acumula las líneas de la partida actual
                    partidaActual.append(linea).append("\n");
                }

                // Agregar la última partida si no es del jugador actual
                if (partidaActual.length() > 0 && !esPartidaJugador) {
                    partidasExistentes.add(partidaActual.toString());
                }
            } catch (Exception e) {
                // Si no existe el archivo, no hay partidas existentes
            }

            // Sobrescribir la partida del jugador actual
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, false))) {
                // Guardar las partidas existentes (excepto la del jugador actual)
                for (String partida : partidasExistentes) {
                    writer.write(partida);
                }

                // Guardar la nueva partida del jugador actual
                writer.write("JUGADOR:" + jugador + "\n");

                // Guardar el estado de los botones
                for (JButton boton : pantalla.botones) {
                    String texto = boton.getText().replaceAll("<[^>]*>", ""); // Elimina etiquetas HTML
                    boolean habilitado = boton.isEnabled();
                    if (!habilitado) { // Si el botón está deshabilitado, es una constante
                        writer.write("CONSTANTE;" + texto + ";" + habilitado + "\n");
                    } else {
                        writer.write(texto + ";" + habilitado + "\n");
                    }
                }

                // Guardar las desigualdades
                for (JLabel desigualdad : pantalla.desigualdades) {
                    writer.write("DESIGUALDAD;" + desigualdad.getText() + "\n");
                }

                // Guardar el tiempo del temporizador
                Reloj reloj = juego.getReloj();
                writer.write("TIEMPO;" + reloj.getHoras() + ";" + reloj.getMinutos() + ";" + reloj.getSegundos() + "\n");

                // Guardar las pilas de jugadas
                for (Movimiento jugada : pilaJugadas) {
                    writer.write("JUGADA;" + jugada.getFila() + ";" + jugada.getColumna() + ";" + jugada.getValor() + "\n");
                }

                for (Movimiento jugadaBorrada : pilaJugadasBorradas) {
                    writer.write("BORRADA;" + jugadaBorrada.getFila() + ";" + jugadaBorrada.getColumna() + ";" + jugadaBorrada.getValor() + "\n");
                }
            }

            JOptionPane.showMessageDialog(pantalla, "Partida guardada con éxito para el jugador: " + jugador);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(pantalla, "Error al guardar el juego: " + e.getMessage());
        }
    }


    private void cargarJuego(String archivo) {
        String jugador = pantalla.lblNombre.getText().trim(); // Obtiene el nombre del jugador desde la etiqueta

        if (jugador.equalsIgnoreCase("Incógnito")) {
            JOptionPane.showMessageDialog(pantalla, "No se puede cargar una partida para el jugador 'Incógnito'. Cambie el nombre del jugador.");
            return; // Salir si el jugador es "Incógnito"
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            boolean esPartidaJugador = false;
            int botonIndex = 0; // Índice para rastrear los botones
            int desigualdadIndex = 0; // Índice para rastrear las desigualdades

            borrarJuego(); // Limpia el tablero actual antes de cargar

            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("JUGADOR:")) {
                    // Verifica si el jugador coincide
                    esPartidaJugador = linea.equalsIgnoreCase("JUGADOR:" + jugador);
                    continue; // Salta a la siguiente línea
                }

                if (esPartidaJugador) {
                    String[] partes = linea.split(";");

                    if (partes.length > 0) {
                        switch (partes[0]) {
                            case "CONSTANTE":
                                // Manejo de constantes
                                if (botonIndex < pantalla.botones.size()) {
                                    JButton boton = pantalla.botones.get(botonIndex);
                                    String texto = partes[1]; // Recupera el valor limpio de la constante
                                    boton.setText("<html><b style='color: black; font-size: 14px;'>" + texto + "</b></html>");
                                    boton.setEnabled(false); // Deshabilita el botón para marcarlo como constante
                                    botonIndex++;
                                }
                                break;

                            case "DESIGUALDAD":
                                // Manejo de desigualdades
                                if (desigualdadIndex < pantalla.desigualdades.size()) {
                                    String texto = partes.length > 1 ? partes[1] : ""; // Evitar problemas con desigualdades vacías
                                    pantalla.desigualdades.get(desigualdadIndex).setText(texto);
                                    desigualdadIndex++;
                                }
                                break;

                            case "TIEMPO":
                                // Manejo del tiempo del temporizador
                                if (partes.length >= 4) {
                                    Reloj reloj = juego.getReloj();
                                    reloj.setHoras(Integer.parseInt(partes[1]));
                                    reloj.setMinutos(Integer.parseInt(partes[2]));
                                    reloj.setSegundos(Integer.parseInt(partes[3]));
                                    inicializarTablaTemporizador();
                                }
                                break;

                            case "JUGADA":
                                // Manejo de las jugadas
                                if (partes.length >= 4) {
                                    int fila = Integer.parseInt(partes[1]);
                                    int columna = Integer.parseInt(partes[2]);
                                    String valor = partes[3];
                                    JButton boton = matriz.obtenerBoton(fila, columna);
                                    boton.setText(valor);
                                    boton.setEnabled(true); // Asegura que sea interactivo si no es constante
                                    pilaJugadas.push(new Movimiento(fila, columna, valor));
                                }
                                break;

                            case "BORRADA":
                                // Manejo de jugadas borradas
                                if (partes.length >= 4) {
                                    int fila = Integer.parseInt(partes[1]);
                                    int columna = Integer.parseInt(partes[2]);
                                    String valor = partes[3];
                                    pilaJugadasBorradas.push(new Movimiento(fila, columna, valor));
                                }
                                break;

                            default:
                                // Manejo de botones regulares
                                if (botonIndex < pantalla.botones.size()) {
                                    JButton boton = pantalla.botones.get(botonIndex);
                                    if (partes.length >= 2) {
                                        String texto = partes[0];
                                        boolean habilitado = Boolean.parseBoolean(partes[1]);

                                        if (!habilitado) {
                                            // Si es constante, aplica el formato
                                            boton.setText("<html><b style='color: black; font-size: 14px;'>" + texto + "</b></html>");
                                        } else {
                                            boton.setText(texto.isEmpty() ? "" : texto); // Asegúrate de que no quede vacío
                                        }
                                        boton.setEnabled(habilitado);
                                    } else {
                                        boton.setText(""); // Por defecto, vacío si no hay texto
                                        boton.setEnabled(true); // Por defecto, habilitado
                                    }
                                    botonIndex++;
                                }
                                break;
                        }
                    }
                }
            }

            if (!esPartidaJugador) {
                JOptionPane.showMessageDialog(pantalla, "No se encontró una partida guardada para el jugador: " + jugador);
                return; // Salir si no se encontró partida para el jugador
            }

            JOptionPane.showMessageDialog(pantalla, "Juego cargado con éxito para el jugador: " + jugador);
            juegoEnProgreso = true; // Indica que el juego está en progreso
        } catch (Exception e) {
            e.printStackTrace(); // Muestra detalles del error en la consola
            JOptionPane.showMessageDialog(pantalla, "Error al cargar el juego: " + e.getMessage());
        }
    }


}
