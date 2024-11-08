package mainFutoshiki;//paquete de Main

import modeloFutoshiki.*; //paquete de modelo, aqui se encontrarán todas las clases propias del proyecto
import vistaFutoshiki.*; //paquete de vista, aqui se encontrarán todos las clases de Swing
import controladorFutoshiki.*;//paquete de controlador, aqui se encontrarán todas las clases que conectan interfaz
                              //con las acciones de las clases del modelo

import java.io.File; //uso de archivos
/**
 *
 * @author ximena molina - juan pablo cambronero
 */
public class Futoshiki {

    public static void main(String[] args) {
        //Verifica existencia de los archivos
        File archivoConfiguracion = new File("futoshiki2024configuracion.txt");
        try{
            if(!archivoConfiguracion.exists())
                archivoConfiguracion.createNewFile();
        }
        catch(Exception e){
            e.getMessage();
        }
        File archivoTop10 = new File("futoshiki2024top10.txt");
        try{
            if(!archivoTop10.exists())
                archivoTop10.createNewFile();
        }
        catch(Exception e){
            e.getMessage();
        }
        File archivoJugadores = new File("futoshiki2024jugadores.txt");
        try{
            if(!archivoJugadores.exists())
                archivoJugadores.createNewFile();
        }
        catch(Exception e){
            e.getMessage();
        }
        File archivoJuegoActual = new File("futoshiki2024juegoactual.txt");
        try{
            if(!archivoJuegoActual.exists())
                archivoJuegoActual.createNewFile();
        }
        catch(Exception e){
            e.getMessage();
        }
        //inicializa y/o carga config juego
        Juego juego = new Juego();
        //inicializa vista
        MenuPrincipal menu  = new MenuPrincipal();
        menu.setVisible(true);
        /*          Solo para pruebas de cuadricula pq aún no agrego XML    */
        juego.setTamano(10);
        juego.setPosicion(true);
        juego.setNivel(2);
        //inicializa controlador
        MenuPrincipalControlador controlador = new MenuPrincipalControlador(juego, menu);
        
    }
}
