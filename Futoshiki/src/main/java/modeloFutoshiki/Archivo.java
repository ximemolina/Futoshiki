package modeloFutoshiki;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.util.*;
/**
 *
 * @author ximena molina - juan pablo cambronero
 */
public class Archivo {
    //guardar la informacion de la configuracion del juego
    public void guardarArchivoConfiguracion(String mensaje){
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
    public void guardarArchivoTop10(String mensaje){
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
    public void guardarArchivoJugadores(String mensaje){
        try{
            File nombreArchivo = new File("futoshiki2024jugadores.txt");
            FileWriter escribir = new FileWriter(nombreArchivo, true); //permite escribir en diferentes ocasiones en archivos
            escribir.write(mensaje); //escribe informarion de usuario
            escribir.close(); //cierra escritor
       } catch(Exception e){
           System.out.print(e.getMessage());
       }
    }
    
    //guardar el juego actual
    public void guardarArchivoJuegoActual(String mensaje){
        try{
            File nombreArchivo = new File("futoshiki2024juegoactual.txt");
            FileWriter escribir = new FileWriter(nombreArchivo, true); //permite escribir en diferentes ocasiones en archivos
            escribir.write(mensaje); //escribe informarion de usuario
            escribir.close(); //cierra escritor
       } catch(Exception e){
           System.out.print(e.getMessage());
       }
    }
    
    //cargar archivo tipo xml que contiene todas las posibles combinaciones de partidas
    public List cargarArchivoPartidas(int numNivel, int cuadricula){
        String nivelDeseado = "";
        if(numNivel == 0) nivelDeseado = "Facil";
        if(numNivel == 1) nivelDeseado = "Intermedio";
        if(numNivel == 2) nivelDeseado = "Dificil";
        List<List<String>> partidasValidas = new ArrayList<>();
        
        try {
            // Configurar para leer el archivo XML
            DocumentBuilderFactory configuracion = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = configuracion.newDocumentBuilder();
            Document documento = constructor.parse("futoshiki2024partidas.xml");

            documento.getDocumentElement().normalize();

            NodeList listaPartidas = documento.getElementsByTagName("partida");
            
            
            ArrayList<String> partidaIndividual;
            String nivel="" ;
            String tamañoCuadricula="" ;
            String desigualdades="";
            String constantes="";
            int numItem = 0;
            int numItem2 = 0;
            // Recorrer cada partida en el archivo
            for (int i = 0; i < listaPartidas.getLength(); i++) {
                Element elementoPartida = (Element) listaPartidas.item(i);

                nivel = elementoPartida.getElementsByTagName("nivel").item(0).getTextContent();
                tamañoCuadricula = elementoPartida.getElementsByTagName("cuadricula").item(0).getTextContent();
                numItem = 0;
                numItem2 = 0;
                
                if (nivel.trim().equals(nivelDeseado) && Integer.parseInt(tamañoCuadricula) == cuadricula) //revisar que sea la dificultad y numero de cuadricula que se desea
                {
                    partidaIndividual = new ArrayList<>();
                    try{
                        while( elementoPartida.getElementsByTagName("des").item(numItem).getTextContent() != null){
                            desigualdades = elementoPartida.getElementsByTagName("des").item(numItem).getTextContent(); 
                            partidaIndividual.add(desigualdades);
                            numItem ++;
                        }
                    } catch(Exception e){};
                    try{
                        while (elementoPartida.getElementsByTagName("cons").item(numItem2).getTextContent() != null){
                            constantes = elementoPartida.getElementsByTagName("cons").item(numItem2).getTextContent();
                            partidaIndividual.add("const");
                            partidaIndividual.add(constantes);
                            numItem2 ++;
                        }
                    } catch(Exception e){}
                    
                    partidasValidas.add(partidaIndividual);
                    }
            }
            //********************************para revisar que se esté guardando bien**********************************************************************
            for (List lista : partidasValidas){
                System.out.println(lista);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return partidasValidas;
    }
}
