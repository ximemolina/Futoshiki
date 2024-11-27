package modeloFutoshiki;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ximena molina - juan pablo cambronero
 */
public class Archivo {
    //guardar la informacion de la configuracion del juego
    public static void guardarArchivoConfiguracion(String mensaje){
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
    public static Juego cargarConfiguracion(){
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
    public static void guardarArchivoTop10(String mensaje){
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
    public static void guardarArchivoJugadores(String mensaje){
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
    public static List cargarArchivoPartidas(int numNivel, int cuadricula){
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
    public static boolean validarNombreUnico(String nombre){
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
    public static String validarContrasena(String nombre, String contrasena){
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
    public static String recuperarCorreo(String nombre){
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
    public static void modificarContrasena(String nombre, String contrasenaNueva){
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
    
    //carga la tabla con las posiciones del tamano de cuadricula pedido
    public static void cargarTablaPosiciones( int tamanoCuadricula, JTable tabla) {

        DefaultTableModel modelo = new DefaultTableModel(new String[]{"Dificultad", "Jugador", "Tiempo"}, 0); //crea tabla

        List<Object[]> filas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("futoshiki2024top10.txt"))) {
            String linea;
            boolean procesar = false;

            while ((linea = br.readLine()) != null) {
                linea = linea.trim();

                // Identificar el tamaño de cuadrícula
                if (linea.startsWith("Tamaño:")) {
                    int tamanoActual = Integer.parseInt(linea.split(":")[1].trim());
                    procesar = (tamanoActual == tamanoCuadricula); //si ambos son iguales retornan true
                } else if (procesar && !linea.isEmpty()) {
                    // Procesar solo si estamos en el tamaño de cuadrícula deseado
                    String[] partes = linea.split(",");
                    if (partes.length == 3) {
                        String dificultad = partes[0].trim();
                        String jugador = partes[1].trim();
                        String tiempo = partes[2].trim();

                        // Añadir los datos a la lista de filas
                        filas.add(new Object[]{dificultad, jugador, tiempo});
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage());
        }

        // Ordenar las filas directamente en la lista
        filas.sort((fila1, fila2) -> {String dificultad1 = fila1[0].toString();String dificultad2 = fila2[0].toString();
            return Integer.compare(obtenerPrioridad(dificultad1), obtenerPrioridad(dificultad2));});

        for (Object[] fila : filas) {
            modelo.addRow(fila);
        }
        
        tabla.setModel(modelo);
    } 
    
    //retorna prioridad en que se mostrarán las cosas en la tabla
    public static int obtenerPrioridad(String dificultad) {
        switch (dificultad.toLowerCase()) {
            case "dificil":
                return 1;
            case "intermedio":
                return 2;
            case "facil":
                return 3;
            default:
                return Integer.MAX_VALUE; // Cualquier otro valor tendrá menor prioridad
        }
    }
    
    public static void agregarInformacionTop10( Juego juego, String tiempo) throws IOException {
        int tamaño = juego.getTamano();
        String dificultad = juego.obtenerStringDificultad();
        String nombre = juego.getJugador().getNombre();
        
        List<String> lineas = new ArrayList<>();
        boolean encontrado = false;
        List<String> registrosTamaño = new ArrayList<>();
        String encabezadoTamaño = "Tamaño: " + tamaño;

        // Leer el archivo línea por línea
        try (BufferedReader reader = new BufferedReader(new FileReader("futoshiki2024top10.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lineas.add(linea);
            }
        }

        // Procesar las líneas para encontrar el bloque correspondiente
        for (int i = 0; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            if (linea.equals(encabezadoTamaño)) {
                encontrado = true;
                registrosTamaño.clear();
                int j = i + 1;

                // Extraer las líneas del tamaño actual
                while (j < lineas.size() && !lineas.get(j).startsWith("Tamaño:")) {
                    registrosTamaño.add(lineas.get(j));
                    j++;
                }

                // Salir del bucle, ya que el bloque fue encontrado
                break;
            }
        }

        // Clasificar las líneas por dificultad y agregar el nuevo registro
        List<String> facil = new ArrayList<>();
        List<String> intermedio = new ArrayList<>();
        List<String> dificil = new ArrayList<>();

        for (String registro : registrosTamaño) {
            if (registro.startsWith("facil,")) facil.add(registro);
            else if (registro.startsWith("intermedio,")) intermedio.add(registro);
            else if (registro.startsWith("dificil,")) dificil.add(registro);
        }

        String nuevoRegistro = String.format("%s,%s,%s", dificultad, nombre, tiempo);
        List<String> listaObjetivo = switch (dificultad) {
            case "facil" -> facil;
            case "intermedio" -> intermedio;
            case "dificil" -> dificil;
            default -> throw new IllegalArgumentException("Dificultad no válida");
        };

        agregarOrdenado(listaObjetivo, nuevoRegistro);

        // Limitar cada lista a un máximo de 10 registros
        if (facil.size() > 10) facil = facil.subList(0, 10);
        if (intermedio.size() > 10) intermedio = intermedio.subList(0, 10);
        if (dificil.size() > 10) dificil = dificil.subList(0, 10);

        // Construir los nuevos registros para el tamaño actual
        List<String> nuevosRegistrosTamaño = new ArrayList<>();
        nuevosRegistrosTamaño.addAll(facil);
        nuevosRegistrosTamaño.addAll(intermedio);
        nuevosRegistrosTamaño.addAll(dificil);

        // Reemplazar o agregar el bloque del tamaño en las líneas generales
        List<String> lineasFinales = new ArrayList<>();
        boolean bloqueActualizado = false;

        for (int i = 0; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            if (linea.equals(encabezadoTamaño)) {
                // Insertar el bloque actualizado
                lineasFinales.add(linea);
                lineasFinales.addAll(nuevosRegistrosTamaño);
                bloqueActualizado = true;

                // Saltar las líneas del bloque actual en el archivo original
                while (i + 1 < lineas.size() && !lineas.get(i + 1).startsWith("Tamaño:")) {
                    i++;
                }
            } else {
                lineasFinales.add(linea);
            }
        }

        if (!bloqueActualizado) {
            // Si no existe el bloque, agregarlo al final
            lineasFinales.add(encabezadoTamaño);
            lineasFinales.addAll(nuevosRegistrosTamaño);
        }

        // Escribir las líneas actualizadas en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("futoshiki2024top10.txt"))) {
            for (String linea : lineasFinales) {
                writer.write(linea + "\n");
            }
        }
    }

    // Método auxiliar para insertar una línea en orden por tiempo
    public static void agregarOrdenado(List<String> lista, String nuevoRegistro) {
        String nuevoTiempo = nuevoRegistro.split(",")[2]; // Extraer tiempo del nuevo registro
        int indice = 0;

        for (; indice < lista.size(); indice++) {
            String tiempoExistente = lista.get(indice).split(",")[2]; // Extraer tiempo del registro existente
            if (compararTiempos(nuevoTiempo, tiempoExistente) < 0) {
                break;
            }
        }

        lista.add(indice, nuevoRegistro); // Insertar en la posición adecuada
    }

    // Método para comparar dos tiempos en formato HH:MM:SS
    public static int compararTiempos(String tiempo1, String tiempo2) {
        String[] partes1 = tiempo1.split(":");
        String[] partes2 = tiempo2.split(":");
        int horas1 = Integer.parseInt(partes1[0].trim());
        int minutos1 = Integer.parseInt(partes1[1].trim());
        int segundos1 = Integer.parseInt(partes1[2].trim());
        int horas2 = Integer.parseInt(partes2[0].trim());
        int minutos2 = Integer.parseInt(partes2[1].trim());
        int segundos2 = Integer.parseInt(partes2[2].trim());

        if (horas1 != horas2) return Integer.compare(horas1, horas2);
        if (minutos1 != minutos2) return Integer.compare(minutos1, minutos2);
        return Integer.compare(segundos1, segundos2);
    }
}
