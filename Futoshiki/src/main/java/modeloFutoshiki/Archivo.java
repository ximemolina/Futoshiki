package modeloFutoshiki;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
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
    //guardar la informacion del jugador
    void guardarArchivoJugadores(String mensaje){
        try{
            File nombreArchivo = new File("futoshiki2024jugadores.txt");
            FileWriter escribir = new FileWriter(nombreArchivo, true); //permite escribir en diferentes ocasiones en archivos
            escribir.write(mensaje); //escribe informarion de usuario
            escribir.close(); //cierra escritor
       } catch(Exception e){
           System.out.print(e.getMessage());
       }
    }
    //cargar archivo tipo xml que contiene todas las posibles combinaciones de partidas
    void cargarArchivoPartidas(){
        try {
            // Configurar para leer el archivo XML
            DocumentBuilderFactory configuracion = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = configuracion.newDocumentBuilder();
            Document documento = constructor.parse("futoshiki2024partidas.xml");

            documento.getDocumentElement().normalize();

            NodeList listaPartidas = documento.getElementsByTagName("partida");

            // Recorrer cada partida en el archivo
            for (int i = 0; i < listaPartidas.getLength(); i++) {
                Element elementoPartida = (Element) listaPartidas.item(i);

                //***********************************************************Revisar como vamos a utilizar la info de este archivo**************************
                String nivel = elementoPartida.getElementsByTagName("nivel").item(0).getTextContent();
                String tamañoCuadricula = elementoPartida.getElementsByTagName("cuadricula").item(0).getTextContent();
                String desigualdades = elementoPartida.getElementsByTagName("des").item(0).getTextContent();
                String constantes = elementoPartida.getElementsByTagName("cons").item(0).getTextContent();
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
