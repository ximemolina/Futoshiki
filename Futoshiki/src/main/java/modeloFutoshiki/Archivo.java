package modeloFutoshiki;

import java.io.*;

/**
 *
 * @author ximena molina - juan pablo cambronero
 */
public class Archivo {
    //guardar la informacion de la configuracion del juego
    void guardarArchivoConfiguracion(String mensaje){
        try{
            File nombreArchivo = new File("futoshiki2024configuracion.txt");
            FileWriter escribir = new FileWriter(nombreArchivo, true); //permite escribir en diferentes ocasiones en archivos
            escribir.write(mensaje); //escribe informarion de usuario
            escribir.close(); //cierra escritor
       } catch(Exception e){
           System.out.print(e.getMessage());
       }
    }
    //guardar la informacion del top10
    void guardarArchivoTop10(String mensaje){
        try{
            File nombreArchivo = new File("futoshiki2024top10.txt");
            FileWriter escribir = new FileWriter(nombreArchivo, true); //permite escribir en diferentes ocasiones en archivos
            escribir.write(mensaje); //escribe informarion de usuario
            escribir.close(); //cierra escritor
       } catch(Exception e){
           System.out.print(e.getMessage());
       }
    }
}
