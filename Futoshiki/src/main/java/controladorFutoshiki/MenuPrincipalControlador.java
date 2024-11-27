package controladorFutoshiki;

import java.util.*;
import vistaFutoshiki.*;
import modeloFutoshiki.*;
import java.awt.event.*;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
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
                List datosJuego = Archivo.cargarArchivoPartidas(juego.getNivel(),juego.getTamano()); // carga info de partidas
                MatrizJuego matriz = new MatrizJuego(juego.getTamano(), datosJuego);
                juego.setMatriz(matriz);
                PantallaJuego2 pantalla = new PantallaJuego2(juego); //inicializa pantalla jugar
                menu.setVisible(false);
                pantalla.setVisible(true);
                PantallaJuegoControlador controlador = new PantallaJuegoControlador(juego,pantalla);// envia las clases necesarias al controlador de la pantalla jugar
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
        
        this.menu.btnAyuda.addActionListener(new ActionListener() { // Espera a que usuario presione el botón de ayuda
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirManualUsuario(); // Llama a la función para abrir el manual de usuario
            }
        });
    }
    
    // Método para abrir el manual de usuario en formato PDF
    private void abrirManualUsuario() {
        String rutaManual = "programa2_futoshiki_manual_de_usuario.pdf"; // Ruta del archivo PDF
        try {
            File archivo = new File(rutaManual); // Crear un objeto File con la ruta del archivo

            if (!archivo.exists()) {
                // Mostrar error si el archivo no existe
                JOptionPane.showMessageDialog(menu, "El archivo del manual no se encontró.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (Desktop.isDesktopSupported()) { // Verificar si el sistema soporta Desktop
                Desktop desktop = Desktop.getDesktop();
                desktop.open(archivo); // Abrir el archivo con la aplicación predeterminada
            } else {
                // Mostrar error si Desktop no está soportado
                JOptionPane.showMessageDialog(menu, "Esta funcionalidad no está soportada en este sistema.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            // Manejo de errores durante la apertura del archivo
            JOptionPane.showMessageDialog(menu, "Ocurrió un error al intentar abrir el manual: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
}
