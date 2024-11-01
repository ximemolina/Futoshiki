package mainFutoshiki;//paquete de Main

import modeloFutoshiki.*; //paquete de modelo, aqui se encontrarán todas las clases propias del proyecto
import vistaFutoshiki.*; //paquete de vista, aqui se encontrarán todos las clases de Swing
import controladorFutoshiki.*;//paquete de controlador, aqui se encontrarán todas las clases que conectan interfaz
                              //con las acciones de las clases del modelo
/**
 *
 * @author ximena molina - juan pablo cambronero
 */
public class Futoshiki {

    public static void main(String[] args) {
        //inicializa y/o carga config juego
        Juego juego = new Juego();
        //inicializa vista
        MenuPrincipal menu  = new MenuPrincipal();
        menu.setVisible(true);
        //inicializa controlador
        MenuPrincipalControlador controlador = new MenuPrincipalControlador(juego, menu);
        
    }
}
