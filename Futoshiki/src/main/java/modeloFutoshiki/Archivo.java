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
            FileWriter escribir = new FileWriter(nombreArchivo, false); //permite escribir en diferentes ocasiones en archivos
            escribir.write(mensaje); //escribe informarion de usuario
            escribir.close(); //cierra escritor
       } catch(Exception e){
           System.out.print(e.getMessage());
       }
    }
    //cargar archivo de configuracion del juego
    public Juego cargarConfiguracion(){
        Juego juego = null;
        File archivo = new File("futoshiki2024configuracion.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            String[] datos= null;
            while ((linea = br.readLine()) != null) {
                datos = linea.split(",");
            }
            juego = new Juego(Integer.parseInt(datos[0]),Integer.parseInt(datos[1]), Boolean.parseBoolean(datos[2]),Boolean.parseBoolean(datos[3]),Integer.parseInt(datos[4]));

            if (Integer.parseInt(datos[4]) == 2){
                juego.getReloj().setHoras(Integer.parseInt(datos[5]));
                juego.getReloj().setMinutos(Integer.parseInt(datos[6]));
                juego.getReloj().setSegundos(Integer.parseInt(datos[7]));
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return juego;
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
    public void guardarArchivoJuegoActual(Juego juego){
        ArrayList<List> lista = new ArrayList<>();
        lista.add(juego.getJugador().listaInfo());
        lista.add(juego.listaInfo());
        lista.add(juego.getMatriz().listaInfo());
        /*    
        List<String> lineas = new ArrayList<>();

        // Leer todas las líneas del archivo
        try (BufferedReader reader = new BufferedReader(new FileReader("futoshiki2024juegoactual.txt"))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] elementos = linea.split(",");
                if (elementos.length >= 2 && elementos[0].trim().equals(juego.getJugador().getNombre())) {
                    elementos[1] = contrasenaNueva; // Modificar el segundo elemento
                    linea = String.join(",", elementos); // Reconstruir la línea
                }
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        // Escribir las líneas modificadas de nuevo en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("futoshiki2024juegoactual.txt"))) {
            for (String linea : lineas) {
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }*/

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
                if (nivelDeseado.trim().equals(nivel) && Integer.parseInt(tamañoCuadricula) == cuadricula) //revisar que sea la dificultad y numero de cuadricula que se desea
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
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return partidasValidas;
    }
    
    // válida que no exista otro nombre en el archivo igual al que se desea agregar
    public boolean validarNombreUnico(String nombre){
        try (BufferedReader reader = new BufferedReader(new FileReader("futoshiki2024jugadores.txt"))) {
            String linea;
            int numeroLinea = 1;

            while ((linea = reader.readLine()) != null) {
                String[] elementos = linea.split(",");
                if (elementos.length > 0) {
                    String primerElemento = elementos[0].trim();
                    if (primerElemento.equals(nombre)) {
                        return false;
                    }
                }
                numeroLinea++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return true;
    }
    
    //valida que la contrasena y el nombre de usuario coincidan. En caso de que coincidan retorna el String del correo, sino null
    public String validarContrasena(String nombre, String contrasena){
        try (BufferedReader reader = new BufferedReader(new FileReader("futoshiki2024jugadores.txt"))) {
            String linea;
            int numeroLinea = 1;

            while ((linea = reader.readLine()) != null) {
                String[] elementos = linea.split(",");
                try{
                    if (elementos.length > 0) {
                        String primerElemento = elementos[0].trim();
                        String segundoElemento = elementos[1].trim();
                        if (primerElemento.equals(nombre) && segundoElemento.equals(contrasena)) {
                            return elementos[2].trim();
                        }
                    }
                }catch(ArrayIndexOutOfBoundsException e){}
                numeroLinea++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return null;
    }
    
    // retorna el correo del usuario, si no encuentra usuario, retorna null
    public String recuperarCorreo(String nombre){
        try (BufferedReader reader = new BufferedReader(new FileReader("futoshiki2024jugadores.txt"))) {
            String linea;
            int numeroLinea = 1;

            while ((linea = reader.readLine()) != null) {
                String[] elementos = linea.split(",");
                try{
                    if (elementos.length > 0) {
                        String primerElemento = elementos[0].trim();
                        if (primerElemento.equals(nombre)) {
                            return elementos[2].trim();
                        }
                    }
                }catch(ArrayIndexOutOfBoundsException e){}
                numeroLinea++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return null;
    }
    
    // cambia el segundo elemento de la línea (contrasena) que contenga el nombre del jugador
    public void modificarContrasena(String nombre, String contrasenaNueva){
        List<String> lineas = new ArrayList<>();

        // Leer todas las líneas del archivo
        try (BufferedReader reader = new BufferedReader(new FileReader("futoshiki2024jugadores.txt"))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] elementos = linea.split(",");
                if (elementos.length >= 2 && elementos[0].trim().equals(nombre)) {
                    elementos[1] = contrasenaNueva; // Modificar el segundo elemento
                    linea = String.join(",", elementos); // Reconstruir la línea
                }
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        // Escribir las líneas modificadas de nuevo en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("futoshiki2024jugadores.txt"))) {
            for (String linea : lineas) {
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
    

}
