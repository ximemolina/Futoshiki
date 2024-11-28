package controladorFutoshiki;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
        
        seleccionarBotones();
        
        this.menu.btnVolver.addActionListener(new ActionListener() { //espera a que usuario presione el boton de volver
            @Override
            public void actionPerformed(ActionEvent e) {               
                
                
                MenuPrincipal pantalla = new MenuPrincipal(); //inicializa pantalla configuracion
                menu.setVisible(false);
                pantalla.setVisible(true);
                MenuPrincipalControlador controlador = new MenuPrincipalControlador(juego,pantalla);// envia las clases
                                                                                            //necesarias al controlador del menu principal
            }
        });
        
        this.menu.btnAceptar.addActionListener(new ActionListener() { // Espera a que el usuario presione el botón de Aceptar
            @Override
            public void actionPerformed(ActionEvent e) {
                // Configurar el tamaño de la cuadrícula en el objeto `Juego`
                if (menu.btnTamano3.isSelected()) {
                    juego.setTamano(3);
                } else if (menu.btnTamano4.isSelected()) {
                    juego.setTamano(4);
                } else if (menu.btnTamano5.isSelected()) {
                    juego.setTamano(5);
                } else if (menu.btnTamano6.isSelected()) {
                    juego.setTamano(6);
                } else if (menu.btnTamano7.isSelected()) {
                    juego.setTamano(7);
                } else if (menu.btnTamano8.isSelected()) {
                    juego.setTamano(8);
                } else if (menu.btnTamano9.isSelected()) {
                    juego.setTamano(9);
                } else if (menu.btnTamano10.isSelected()) {
                    juego.setTamano(10);
                }

                // Configurar el nivel de juego
                if (menu.btnFacil.isSelected()) {
                    juego.setNivel(0); // Nivel fácil
                } else if (menu.btnIntermedio.isSelected()) {
                    juego.setNivel(1); // Nivel intermedio
                } else if (menu.btnDificil.isSelected()) {
                    juego.setNivel(2); // Nivel difícil
                }

                // Configurar multivel
                juego.setMultinivel(menu.btnMultinivelSi.isSelected());

                // Configurar posición en la ventana del panel de dígitos
                juego.setPosicion(menu.btnDerecha.isSelected());

                // Configurar el reloj (dependiendo de la selección)
                
                if (menu.btnCronometro.isSelected()) {
                    juego.getReloj().setTipo(1);
                } else if (menu.btnTemporizador.isSelected()) {
                    juego.getReloj().setTipo(2);
                    try {
                        int horas = Integer.parseInt(menu.inpHoras.getText());
                        int minutos = Integer.parseInt(menu.inpMinutos.getText());
                        int segundos = Integer.parseInt(menu.inpSegundos.getText());
                        juego.getReloj().setHoras(horas);
                        juego.getReloj().setMinutos(minutos);
                        juego.getReloj().setSegundos(segundos);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(menu, "Por favor ingrese valores numéricos válidos para el temporizador", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Salir del método si hay un error de formato
                    } catch (IllegalArgumentException x){
                        JOptionPane.showMessageDialog(menu, x.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                } else if (menu.btnSinReloj.isSelected()) juego.getReloj().setTipo(0);
                
                String nombre = menu.inpNombre.getText();
                String contraseña = menu.inpContraseña.getText();
                String correo = menu.inpCorreo.getText();
                
                if (!nombre.isEmpty()){
                    if (contraseña.isEmpty() || correo.isEmpty()) {
                        JOptionPane.showMessageDialog(menu, 
                            "Por favor complete los campos de Contraseña y Correo si ha ingresado un Nombre", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                        return; // Salir del método si la validación falla
                    }else {
                        if(!correo.contains("@")){ //verificar que haya ingresado un correo válido
                            JOptionPane.showMessageDialog(menu, "Ingrese un correo válido", "Error",JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (Archivo.validarNombreUnico(nombre)){ //validar que el nombre sea único
                            try{
                                Jugador jugador = new Jugador(nombre, contraseña, correo);
                                juego.setJugador(jugador);
                                Archivo.guardarArchivoJugadores(jugador.toString());
                            }catch(Exception m){
                                JOptionPane.showMessageDialog(menu, m.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }else{
                            JOptionPane.showMessageDialog(menu, "Debe registrar otro nombre, ese ya está siendo utilizado", "Error",JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }else if (!contraseña.isEmpty() || !correo.isEmpty()){
                    JOptionPane.showMessageDialog(menu, "Debe ingresar todos los datos correspondientes al usuario si desea crear uno", "Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if(!menu.inpNombreIngresar.getText().isEmpty() && !menu.inpContraseñaIngresar.getText().isEmpty()){
                    String correo2 = Archivo.validarContrasena(menu.inpNombreIngresar.getText(), menu.inpContraseñaIngresar.getText());
                    if (correo2 != null){
                        try{
                            Jugador jugador = new Jugador(menu.inpNombreIngresar.getText(),menu.inpContraseñaIngresar.getText(),correo2);
                            juego.setJugador(jugador);
                        }catch(Exception r){
                            JOptionPane.showMessageDialog(menu, r.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                   } else{
                        JOptionPane.showMessageDialog(menu, "Usuario no encontrado", "Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                       
                }
                
                
                Archivo.guardarArchivoConfiguracion(juego.toString());
                
                // Confirmar configuración completada
                JOptionPane.showMessageDialog(menu, "Configuración guardada exitosamente", "Configuración", JOptionPane.INFORMATION_MESSAGE);
                
                //salir de pantalla
                MenuPrincipal pantalla = new MenuPrincipal(); //inicializa pantalla configuracion
                menu.setVisible(false);
                pantalla.setVisible(true);
                MenuPrincipalControlador controlador = new MenuPrincipalControlador(juego,pantalla);
                
            }
        });

        
        
        
        this.menu.btnTamano3.addActionListener(new ActionListener() { //espera a que usuario presione el boton de tamaño de cuadricula
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.btnTamano3.setForeground(Color.red);
               resetearBotonesTamaño(menu.btnTamano3);
            }
        });
        
        this.menu.btnTamano4.addActionListener(new ActionListener() { //espera a que usuario presione el boton de tamaño de cuadricula
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.btnTamano4.setForeground(Color.red);
               resetearBotonesTamaño(menu.btnTamano4);
            }
        });
        
        this.menu.btnTamano5.addActionListener(new ActionListener() { //espera a que usuario presione el boton de tamaño de cuadricula
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.btnTamano5.setForeground(Color.red);
               resetearBotonesTamaño(menu.btnTamano5);
            }
        });
        
        this.menu.btnTamano6.addActionListener(new ActionListener() { //espera a que usuario presione el boton de tamaño de cuadricula
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.btnTamano6.setForeground(Color.red);
               resetearBotonesTamaño(menu.btnTamano6);
            }
        });
        
        this.menu.btnTamano7.addActionListener(new ActionListener() { //espera a que usuario presione el boton de tamaño de cuadricula
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.btnTamano7.setForeground(Color.red);
               resetearBotonesTamaño(menu.btnTamano7);
            }
        });
        
        this.menu.btnTamano8.addActionListener(new ActionListener() { //espera a que usuario presione el boton de tamaño de cuadricula
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.btnTamano8.setForeground(Color.red);
               resetearBotonesTamaño(menu.btnTamano8);
            }
        });
        
        this.menu.btnTamano9.addActionListener(new ActionListener() { //espera a que usuario presione el boton de tamaño de cuadricula
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.btnTamano9.setForeground(Color.red);
               resetearBotonesTamaño(menu.btnTamano9);
            }
        });
        
        this.menu.btnTamano10.addActionListener(new ActionListener() { //espera a que usuario presione el boton de tamaño de cuadricula
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.btnTamano10.setForeground(Color.red);
               resetearBotonesTamaño(menu.btnTamano10);
            }
        });
        
        this.menu.btnFacil.addActionListener(new ActionListener() { //espera a que usuario presione el boton de nivel
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.btnFacil.setForeground(Color.red);
               resetearBotonesNivel(menu.btnFacil);
            }
        });
        
        this.menu.btnIntermedio.addActionListener(new ActionListener() { //espera a que usuario presione el boton de nivel
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.btnIntermedio.setForeground(Color.red);
               resetearBotonesNivel(menu.btnIntermedio);
            }
        });
        
        this.menu.btnDificil.addActionListener(new ActionListener() { //espera a que usuario presione el boton de nivel
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.btnDificil.setForeground(Color.red);
               resetearBotonesNivel(menu.btnDificil);
            }
        });
        
        this.menu.btnMultinivelNo.addActionListener(new ActionListener() { //espera a que usuario presione el boton de multinivel
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.btnMultinivelNo.setForeground(Color.red);
               resetearBotonesMultinivel(menu.btnMultinivelNo);
            }
        });
        
        this.menu.btnMultinivelSi.addActionListener(new ActionListener() { //espera a que usuario presione el boton de multinivel
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.btnMultinivelSi.setForeground(Color.red);
               resetearBotonesMultinivel(menu.btnMultinivelSi);
            }
        });
        
        this.menu.btnDerecha.addActionListener(new ActionListener() { //espera a que usuario presione el boton de posicion
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.btnDerecha.setForeground(Color.red);
               resetearBotonesLados(menu.btnDerecha);
            }
        });
        
        this.menu.btnIzquierda.addActionListener(new ActionListener() { //espera a que usuario presione el boton de posicion
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.btnIzquierda.setForeground(Color.red);
               resetearBotonesLados(menu.btnIzquierda);
            }
        });
        
        this.menu.btnCronometro.addActionListener(new ActionListener() { //espera a que usuario presione el boton de reloj
            @Override
            public void actionPerformed(ActionEvent e) {
               resetearBotonesReloj(menu.btnCronometro);
               menu.btnCronometro.setForeground(Color.red);
               menu.inpHoras.setText("");
               menu.inpMinutos.setText("");
               menu.inpSegundos.setText("");
               menu.inpHoras.setEnabled(false);
               menu.inpMinutos.setEnabled(false);
               menu.inpSegundos.setEnabled(false);
            }
        });
        
        this.menu.btnTemporizador.addActionListener(new ActionListener() { //espera a que usuario presione el boton de reloj
            @Override
            public void actionPerformed(ActionEvent e) { //si es seleccionado, habilitar los inputs del reloj
               resetearBotonesReloj(menu.btnTemporizador);
               menu.btnTemporizador.setForeground(Color.red);
               menu.inpHoras.setEnabled(true);
               menu.inpMinutos.setEnabled(true);
               menu.inpSegundos.setEnabled(true);
            }
        });
        
        this.menu.btnSinReloj.addActionListener(new ActionListener() { //espera a que usuario presione el boton de reloj
            @Override
            public void actionPerformed(ActionEvent e) {
               resetearBotonesReloj(menu.btnSinReloj);
               menu.btnSinReloj.setForeground(Color.red);
               menu.inpHoras.setText("");
               menu.inpMinutos.setText("");
               menu.inpSegundos.setText("");
               menu.inpHoras.setEnabled(false);
               menu.inpMinutos.setEnabled(false);
               menu.inpSegundos.setEnabled(false);
            }
        });
        
        this.menu.btnContraseña.addActionListener(new ActionListener() { //espera a que usuario presione el boton de Olvido de contraseña
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(menu.inpNombreIngresar.getText()).equals("")){ //validar que si se haya ingresado un usuario del cual agarrar el correo
                    String correoUsuario = Archivo.recuperarCorreo(menu.inpNombreIngresar.getText());
                    if (correoUsuario != null){
                        try{
                            Jugador jugador = new Jugador(menu.inpNombreIngresar.getText(), menu.inpContraseñaIngresar.getText(), correoUsuario);
                            juego.setJugador(jugador); //se setea para utilizar datos en pantalla de olvido de contraseña
                        }catch(Exception t){} //ignorar validación
                        
                        String pin = generarPin();
                        // Crear una instancia de la clase Correo
                        Correo correo = new Correo("juanpacamal08@gmail.com", "adqs eueu mrbs vngz", "smtp.gmail.com"); 

                        // Definir el destinatario y el contenido del mensaje
                        String destinatario = correoUsuario;
                        String asunto = "Código de recuperación de contraseña";
                        String cuerpo = "Su código de recuperación es: " + pin;

                        // Enviar el correo con el PIN
                        correo.enviarCorreo(destinatario, asunto, cuerpo);

                        PantallaOlvidoContraseña pantalla = new PantallaOlvidoContraseña();
                        menu.setVisible(false);
                        pantalla.setVisible(true);
                        PantallaOlvidoContraseñaControlador controlador = new PantallaOlvidoContraseñaControlador(pantalla,juego, pin);
                    }else
                        JOptionPane.showMessageDialog(null, "Debe ingresar un usuario válido", "ERROR", JOptionPane.ERROR_MESSAGE);
                }else
                    JOptionPane.showMessageDialog(null, "Debe ingresar un usuario válido", "ERROR", JOptionPane.ERROR_MESSAGE);
               
            }
        });
        
        this.menu.inpNombre.getDocument().addDocumentListener(new DocumentListener() {//espera a que usuario ingrese algún dato en el input
            @Override
            public void changedUpdate(DocumentEvent e) {
                revisarInputs();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                revisarInputs();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                revisarInputs();
            }
        });
        
        this.menu.inpCorreo.getDocument().addDocumentListener(new DocumentListener() { //espera a que usuario ingrese algún dato en el input
            @Override
            public void changedUpdate(DocumentEvent e) {
                revisarInputs();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                revisarInputs();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                revisarInputs();
            }
        });
        
        this.menu.inpContraseña.getDocument().addDocumentListener(new DocumentListener() { //espera a que usuario ingrese algún dato en el input
            @Override
            public void changedUpdate(DocumentEvent e) {
                revisarInputs();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                revisarInputs();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                revisarInputs();
            }
        });
        
        this.menu.inpNombreIngresar.getDocument().addDocumentListener(new DocumentListener() { //espera a que usuario ingrese algún dato en el input
            @Override
            public void changedUpdate(DocumentEvent e) {
                revisarInputs();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                revisarInputs();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                revisarInputs();
            }
        });
        
        this.menu.inpContraseñaIngresar.getDocument().addDocumentListener(new DocumentListener() { //espera a que usuario ingrese algún dato en el input
            @Override
            public void changedUpdate(DocumentEvent e) {
                revisarInputs();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                revisarInputs();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                revisarInputs();
            }
        });
    }
    
    //evita que varios botones de tamaño de cuadricula estén presionados al mismo tiempo
    void resetearBotonesTamaño(JRadioButton boton){
        for (Component comp: menu.PanelConfig.getComponents()){
            if(comp instanceof JRadioButton) //verificar que sea un boton de tipo JRadioButton
                if(comp.equals(menu.btnTamano10) || comp.equals(menu.btnTamano9)|| comp.equals(menu.btnTamano8)|| comp.equals(menu.btnTamano7)|| comp.equals(menu.btnTamano6)|| comp.equals(menu.btnTamano5)|| comp.equals(menu.btnTamano4)|| comp.equals(menu.btnTamano3))
                    if (!comp.equals(boton)){ //verificar que no sea el boton que está siendo seleccionado
                        ((JRadioButton)comp).setSelected(false); 
                        comp.setForeground(Color.black);
                    }
        }
    }
    
    //evita que varios botones de nivel estén presionados al mismo tiempo
    void resetearBotonesNivel(JRadioButton boton){
        for(Component comp: menu.PanelConfig.getComponents()){
            if(comp instanceof JRadioButton && !comp.equals(boton)) //verificar que sea un boton de tipo JRadioButton y diferente al que está presionado
                if(comp.equals(menu.btnFacil) || comp.equals(menu.btnIntermedio) || comp.equals(menu.btnDificil)){
                    ((JRadioButton)comp).setSelected(false); 
                    comp.setForeground(Color.black);
                }
        }
    }
    
    //evita que varios botones del multinivel estén presionados al mismo tiempo
    void resetearBotonesMultinivel(JRadioButton boton){
        for(Component comp: menu.PanelConfig.getComponents()){
            if(comp instanceof JRadioButton && !comp.equals(boton)) //verificar que sea un boton de tipo JRadioButton y diferente al que está presionado
                if(comp.equals(menu.btnMultinivelNo) || comp.equals(menu.btnMultinivelSi)){
                    ((JRadioButton)comp).setSelected(false); 
                    comp.setForeground(Color.black);
                }
        }
    }
    
    //evita que varios botones de posicion de juego estén presionados al mismo tiempo
    void resetearBotonesLados(JRadioButton boton){
        for(Component comp: menu.PanelConfig.getComponents()){
            if(comp instanceof JRadioButton && !comp.equals(boton)) //verificar que sea un boton de tipo JRadioButton y diferente al que está presionado
                if(comp.equals(menu.btnDerecha) || comp.equals(menu.btnIzquierda)){
                    ((JRadioButton)comp).setSelected(false); 
                    comp.setForeground(Color.black);
                }
        }
    }
    
    //evita que varios botones de reloj estén presionados al mismo tiempo
    void resetearBotonesReloj(JRadioButton boton){
        for(Component comp: menu.PanelConfig.getComponents()){
            if(comp instanceof JRadioButton && !comp.equals(boton)) //verificar que sea un boton de tipo JRadioButton y diferente al que está presionado
                if(comp.equals(menu.btnCronometro) || comp.equals(menu.btnSinReloj) || comp.equals(menu.btnTemporizador)){
                    ((JRadioButton)comp).setSelected(false); 
                    comp.setForeground(Color.black);
                }
        }
    }
    
    //genera Pin al azar
    String generarPin(){
        String caracteresPermitidos = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
            Random random = new Random();
            StringBuilder sb = new StringBuilder(4);

            for (int i = 0; i < 4; i++) {
                int indiceAleatorio = random.nextInt(caracteresPermitidos.length());
                char caracterAleatorio = caracteresPermitidos.charAt(indiceAleatorio);
                sb.append(caracterAleatorio);
            }
            String pin = sb.toString();
        return pin;
    }
    
    void revisarInputs(){
        if(!menu.inpContraseña.getText().isEmpty() || !menu.inpNombre.getText().isEmpty() || !menu.inpCorreo.getText().isEmpty()){
            menu.inpNombreIngresar.setEnabled(false);
            menu.inpContraseñaIngresar.setEnabled(false);
            menu.inpNombreIngresar.setText("");
            menu.inpContraseñaIngresar.setText("");
        }else{
            menu.inpContraseñaIngresar.setEnabled(true);
            menu.inpNombreIngresar.setEnabled(true);
        }
        if(!menu.inpContraseñaIngresar.getText().isEmpty() || !menu.inpNombreIngresar.getText().isEmpty()){
            menu.inpNombre.setEnabled(false);
            menu.inpContraseña.setEnabled(false);
            menu.inpCorreo.setEnabled(false);
            menu.inpNombre.setText("");
            menu.inpContraseña.setText("");
            menu.inpCorreo.setText("");
        }else{
            menu.inpContraseña.setEnabled(true);
            menu.inpNombre.setEnabled(true);
            menu.inpCorreo.setEnabled(true);
        }    
        
    
    }
    
    void seleccionarBotones(){
        //revisar botones de tamaño
        if (juego.getTamano() == 3) {
            menu.btnTamano3.setSelected(true); 
            menu.btnTamano3.setForeground(Color.red);
            resetearBotonesTamaño(menu.btnTamano3); 
            
        }
        if (juego.getTamano() == 4) {
            menu.btnTamano4.setSelected(true);
            menu.btnTamano4.setForeground(Color.red);
            resetearBotonesTamaño(menu.btnTamano4); 
        }
        if (juego.getTamano() == 5) {
            menu.btnTamano5.setSelected(true);
            menu.btnTamano5.setForeground(Color.red);
            resetearBotonesTamaño(menu.btnTamano5); 
        }
        if (juego.getTamano() == 6) {
            menu.btnTamano6.setSelected(true);
            menu.btnTamano6.setForeground(Color.red);
            resetearBotonesTamaño(menu.btnTamano6); 
        }
        if (juego.getTamano() == 7) {
            menu.btnTamano7.setSelected(true);
            menu.btnTamano7.setForeground(Color.red);
            resetearBotonesTamaño(menu.btnTamano7); 
        }
        if (juego.getTamano() == 8) {
            menu.btnTamano8.setSelected(true);
            menu.btnTamano8.setForeground(Color.red);
            resetearBotonesTamaño(menu.btnTamano8); 
        }
        if (juego.getTamano() == 9) {
            menu.btnTamano9.setSelected(true);
            menu.btnTamano9.setForeground(Color.red);
            resetearBotonesTamaño(menu.btnTamano9); 
        }
        if (juego.getTamano() == 10) {
            menu.btnTamano10.setSelected(true);
            menu.btnTamano10.setForeground(Color.red);
            resetearBotonesTamaño(menu.btnTamano10);
        }
        //revisar botones dificultad
        if (juego.getNivel() == 0) {
            menu.btnFacil.setSelected(true);
            menu.btnFacil.setForeground(Color.red);
            resetearBotonesNivel(menu.btnFacil); 
        }
        if (juego.getNivel() == 1) {
            menu.btnIntermedio.setSelected(true);
            menu.btnIntermedio.setForeground(Color.red);
            resetearBotonesNivel(menu.btnIntermedio);
        }
        if (juego.getNivel() == 2) {
            menu.btnDificil.setSelected(true);
            menu.btnDificil.setForeground(Color.red);
            resetearBotonesNivel(menu.btnDificil); 
        }  
        //revisar botones de lado de pantalla
        if (juego.isPosicion()) {
            menu.btnDerecha.setSelected(true);
            menu.btnDerecha.setForeground(Color.red);
            resetearBotonesLados(menu.btnDerecha);
        }
        if (!juego.isPosicion()) {
            menu.btnIzquierda.setSelected(true);
            menu.btnIzquierda.setForeground(Color.red);
            resetearBotonesLados(menu.btnIzquierda);
        }
        //revisar boton de multinivel
        if (juego.isMultinivel()) {
            menu.btnMultinivelSi.setSelected(true);
            menu.btnMultinivelSi.setForeground(Color.red);
            resetearBotonesMultinivel(menu.btnMultinivelSi);
        }
        if (!juego.isMultinivel()) {
            menu.btnMultinivelNo.setSelected(true);
            menu.btnMultinivelNo.setForeground(Color.red);
            resetearBotonesMultinivel(menu.btnMultinivelNo);
        }
        //revisar boton de reloj
        try{
            if (juego.getReloj().getTipo() == 0){
                menu.btnSinReloj.setSelected(true);
                menu.btnSinReloj.setForeground(Color.red);
                resetearBotonesReloj(menu.btnSinReloj);
            }
            if (juego.getReloj().getTipo() == 1){
                menu.btnCronometro.setSelected(true);
                menu.btnCronometro.setForeground(Color.red);
                resetearBotonesReloj(menu.btnCronometro);
            }
            if (juego.getReloj().getTipo() == 2){
                menu.btnTemporizador.setSelected(true);
                menu.btnTemporizador.setForeground(Color.red);
                menu.inpHoras.setText(String.valueOf(juego.getReloj().getHoras()));
                menu.inpHoras.setEnabled(true);
                menu.inpMinutos.setText(String.valueOf(juego.getReloj().getMinutos()));
                menu.inpMinutos.setEnabled(true);
                menu.inpSegundos.setText(String.valueOf(juego.getReloj().getSegundos()));
                menu.inpSegundos.setEnabled(true);
                resetearBotonesReloj(menu.btnTemporizador);
            }
        }catch (Exception e){}
    }
}
