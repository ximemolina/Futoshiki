package controladorFutoshiki;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
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
                    String pin = generarPin();
                    //******************************************aqui incluir llamada a funcion/clase de correo con el pin*****************************
                    PantallaOlvidoContraseña pantalla = new PantallaOlvidoContraseña();
                    menu.setVisible(false);
                    pantalla.setVisible(true);
                    PantallaOlvidoContraseñaControlador controlador = new PantallaOlvidoContraseñaControlador(pantalla,juego, pin);
                    System.out.println(pin); //solo para pruebas. se quita cuando ya se envie correo
                }else
                    JOptionPane.showMessageDialog(null, "Debe ingresar un usuario válido", "ERROR", JOptionPane.ERROR_MESSAGE);
               
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
}
