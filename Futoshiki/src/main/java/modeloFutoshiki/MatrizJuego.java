package modeloFutoshiki;
import vistaFutoshiki.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.*;
import javax.swing.*;
/**
 *
 * @author ximena molina - juan pablo cambronero
 */
public class MatrizJuego {
    private int dimension;
    private List valoresArchivoPartida;
    private ArrayList<JButton> botonesCasillas; //botones de las casillas de la plantilla del juego
    private ArrayList<JButton> botonesNumeros; // botones que están al lado que contienen los posibles números a poner
    private ArrayList<JLabel> desigualdades; //labels que contienen todas las desigualdades de la plantilla
    private Integer indicePartida;
    

    //constructor
    public MatrizJuego(int dimension, List valores){
        setDimension(dimension);
        setValoresArchivoPartida(valores);
        botonesCasillas = new ArrayList <> ();
        botonesNumeros = new ArrayList <>();
        desigualdades = new ArrayList <> ();
    }
    
    //retorna botón dada la fila y columna
    public JButton obtenerBoton(int fila, int columna) {
        return botonesCasillas.get(fila * dimension + columna);
    }    
    
    //retorna la fila del boton
    public int obtenerFila(JButton boton) {
        int indice = getBotonesCasillas().indexOf(boton);
        return indice / dimension; // División entera
    }

    //retorna la columan del boton
    public int obtenerColumna(JButton boton) {
        int indice = getBotonesCasillas().indexOf(boton);
        return indice % dimension; // Resto
    }    
    
    // selecciona al azar cual partida mostrar
    public int partidaAzar(){
        Random random = new Random();
        return random.nextInt((valoresArchivoPartida.size()-1 - 0) + 1) + 0;
    }
    
    //revisa que las jugadas cumplan con las reglas del juego
    public void validarJugada(JButton botonSeleccionado, PantallaJuego2 pantalla) throws Exception{
        if (pantalla.btnBorrador.getBackground() == Color.GREEN) return; //no se realizan validaciones con el borrador
        
        int columna = obtenerColumna(botonSeleccionado);
        int fila = obtenerFila(botonSeleccionado);
        String valor = "";
       
        // Si un número está seleccionado
        for (Component comp : getBotonesCasillas()) {
            if (comp.equals(botonSeleccionado)) {
                for (JButton comp2 : getBotonesNumeros()) {
                    if (comp2.getBackground() == Color.GREEN) { // Número seleccionado en verde
                        valor = (comp2.getText()).replaceAll("<[^>]*>", "");
                    }
                }
            }
            
            if (comp.getBackground() == Color.red) comp.setBackground(new Color(240, 240, 240)); // quita las casilla en rojo de las validaciones
        }
        
        if (valor.equals(""))throw new Exception("FALTA QUE SELECCIONE UN DÍGITO."); //revisar que si esté seleccionado un dígito
        
        for (JButton boton : getBotonesCasillas()){ //validar columna
            if (obtenerColumna(boton) == columna) {
                if ((boton.getText().replaceAll("<[^>]*>", "")).equals(valor)){
                    boton.setBackground(Color.red);
                    throw new Exception("JUGADA NO ES VÁLIDA PORQUE EL ELEMENTO YA ESTÁ EN LA COLUMNA");
                }
            }
        }
        
        for (JButton boton : getBotonesCasillas()){ //validar fila
            if (obtenerFila(boton) == fila) {
                if ((boton.getText().replaceAll("<[^>]*>", "")).equals(valor)){
                    boton.setBackground(Color.red);
                    throw new Exception("JUGADA NO ES VÁLIDA PORQUE EL ELEMENTO YA ESTÁ EN LA FILA");
                }
            }
        }
        
        boolean filaOColumna = true;
        int cont= 0;
        int contFilas = 0;
        for (JLabel desigualdad : getDesigualdades()){
            
            if (cont+1 >= dimension && filaOColumna){ // una vez que termina fila, baja una columna y continua con la siguiente fila
                    cont = 0;
                    contFilas ++;
                    filaOColumna = false;
            } else if (cont >= dimension && !filaOColumna){
                    cont = 0;
                    filaOColumna = true;
            }
            
            if (columna == cont && filaOColumna){ //aplicar desigualdad 
                for (JButton boton : getBotonesCasillas()){
                    if(obtenerFila(boton) == fila && !boton.getText().equals("") && fila == contFilas){ //verificar q estén en misma fila ya q desigualdad es mef o maf
                        if (obtenerColumna(boton)-1 ==columna){
                            if(desigualdad.getText().trim().equals(">")){
                                if( (Integer.parseInt(boton.getText().replaceAll("<[^>]*>", ""))) > Integer.parseInt(valor)){
                                    boton.setBackground(Color.red);
                                    throw new Exception("JUGADA NO ES VÁLIDA PORQUE NO CUMPLE CON LA RESTRICCIÓN DE MAYOR");
                                    
                                }
                            } else  if(desigualdad.getText().trim().equals("<")){
                                if( (Integer.parseInt(boton.getText().replaceAll("<[^>]*>", ""))) < Integer.parseInt(valor)){
                                    boton.setBackground(Color.red);
                                    throw new Exception("JUGADA NO ES VÁLIDA PORQUE NO CUMPLE CON LA RESTRICCIÓN DE MENOR");
                                }
                            }
                        }
                    }
                
                }
            } else if (columna == cont+1 && filaOColumna){
                for (JButton boton : getBotonesCasillas()){
                    if(obtenerFila(boton) == fila && !boton.getText().equals("") && fila == contFilas){ //verificar q estén en misma fila ya q desigualdad es mef o maf
                        if (obtenerColumna(boton) ==columna-1){
                            if(desigualdad.getText().trim().equals(">")){
                                if( (Integer.parseInt(boton.getText().replaceAll("<[^>]*>", ""))) < Integer.parseInt(valor)){
                                    boton.setBackground(Color.red);
                                    throw new Exception("JUGADA NO ES VÁLIDA PORQUE NO CUMPLE CON LA RESTRICCIÓN DE MENOR");
                                }
                            } else  if(desigualdad.getText().trim().equals("<")){
                                if( (Integer.parseInt(boton.getText().replaceAll("<[^>]*>", ""))) > Integer.parseInt(valor)){
                                    boton.setBackground(Color.red);
                                    throw new Exception("JUGADA NO ES VÁLIDA PORQUE NO CUMPLE CON LA RESTRICCIÓN DE MAYOR");
                                }
                            }
                        }
                    }
                
                }
            } else if(columna == cont && !filaOColumna){
                for (JButton boton : getBotonesCasillas()){
                    if (obtenerFila(boton) == fila-1 && obtenerColumna(boton) == columna && !boton.getText().equals("") && fila ==contFilas){ //boton está justo arriba que el seleccionado
                        if(desigualdad.getText().trim().equals("^")){
                            if( (Integer.parseInt(boton.getText().replaceAll("<[^>]*>", ""))) > Integer.parseInt(valor)){
                                boton.setBackground(Color.red);
                                throw new Exception("JUGADA NO ES VÁLIDA PORQUE NO CUMPLE CON LA RESTRICCIÓN DE MAYOR");
                            }   
                          }
                        else if(desigualdad.getText().trim().equals("V")){
                              if( (Integer.parseInt(boton.getText().replaceAll("<[^>]*>", ""))) < Integer.parseInt(valor)){
                                      boton.setBackground(Color.red);
                                      throw new Exception("JUGADA NO ES VÁLIDA PORQUE NO CUMPLE CON LA RESTRICCIÓN DE MENOR");
                                  } 
                        }
                    }else if (obtenerFila(boton) == fila+1 && obtenerColumna(boton) == columna && !boton.getText().equals("") && fila ==contFilas){ //boton está justo abajo del seleccionado
                        if(desigualdad.getText().trim().equals("^")){
                            if( (Integer.parseInt(boton.getText().replaceAll("<[^>]*>", ""))) < Integer.parseInt(valor)){
                                boton.setBackground(Color.red);
                                throw new Exception("JUGADA NO ES VÁLIDA PORQUE NO CUMPLE CON LA RESTRICCIÓN DE MENOR 11");
                            }   
                          }
                        else if(desigualdad.getText().trim().equals("V")){
                              if( (Integer.parseInt(boton.getText().replaceAll("<[^>]*>", "").trim())) > Integer.parseInt(valor)){
                                  boton.setBackground(Color.red);
                                  throw new Exception("JUGADA NO ES VÁLIDA PORQUE NO CUMPLE CON LA RESTRICCIÓN DE MAYOR 11");
                              } 
                        }
                    }
                }
            
            }
            cont++;
        }
    }     
    
    
    //despliega todas las constantes en la plantilla
    public void mostrarConstante(int columna, int fila, int constante){
        ArrayList<JButton> lista = botonesCasillas;
        int contadorColum = 0;
        int contadorFila = 0;
        for (JButton boton : lista){
            if (contadorColum == dimension) {
                contadorColum = 0;
                contadorFila ++;
            }
            if (contadorColum == columna && contadorFila == fila){
               boton.setText("<html><b style='color: black; font-size: 14px;'>" + constante + "</b></html>");
               

               // Condición para cambiar el color y la fuente dependiendo del nivel del juego
               if(dimension == 9 || dimension == 8 || dimension == 10){
                   boton.setForeground(Color.BLACK); // Establecer el color del texto
                   boton.setFont(new Font(boton.getFont().getName(), Font.BOLD, 14)); 
               }
               boton.setEnabled(false);

            }
            contadorColum ++;
        }
 
    }
    
    //se encargar de mostrar las desigualdades que están en las filas (maf - mef)
    public void desigualdadesFila(int columna, int fila, boolean identificador){
        ArrayList<JLabel> lista = desigualdades;
        int contadorColum = 0;
        int contadorFila = 0;
        boolean revisar = true;
        for (JLabel label : lista){
            if (contadorColum >= dimension-1) {
                
                if (!revisar && contadorColum >= dimension ) {
                    contadorFila++;
                    revisar = true;
                    contadorColum = 0;
                }else if (revisar ){
                    contadorColum = 0;
                    revisar = false;
                }
            }
            if (contadorFila == fila && contadorColum == columna && revisar){
                if(identificador) label.setText(">");
                else label.setText("<");
                break;
            }
            contadorColum ++;
        }
    }
    
    //se encargar de mostrar las desigualdades que están en las columnas(mac - mec)
    public void desigualdadesColumna(int columna, int fila, boolean identificador){
        ArrayList<JLabel> lista = desigualdades;
        int contadorColum = 0;
        int contadorFila = 0;
        boolean revisar = true;
        for (JLabel label : lista){
            if (contadorColum >= dimension-1) {
                
                if (!revisar && contadorColum >= dimension ) {
                    contadorFila++;
                    revisar = true;
                    contadorColum = 0;
                }else if (revisar ){
                    contadorColum = 0;
                    revisar = false;
                }
            }
            if (contadorFila == fila && contadorColum == columna && !revisar){
                if(identificador) label.setText("V");
                else label.setText("^");
                break;
            }
            contadorColum ++;
        }
    
    }    
    // <editor-fold defaultstate="collapsed" desc="Setters - Getters"> 
    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public List getValoresArchivoPartida() {
        return valoresArchivoPartida;
    }

    public void setValoresArchivoPartida(List valoresArchivoPartida) {
        this.valoresArchivoPartida = valoresArchivoPartida;
    }

    public ArrayList<JButton> getBotonesCasillas() {
        return botonesCasillas;
    }

    public void setBotonesCasillas(ArrayList<JButton> botonesCasillas) {
        this.botonesCasillas = botonesCasillas;
    }

    public ArrayList<JButton> getBotonesNumeros() {
        return botonesNumeros;
    }

    public void setBotonesNumeros(ArrayList<JButton> botonesNumeros) {
        this.botonesNumeros = botonesNumeros;
    }
    
 
    public ArrayList<JLabel> getDesigualdades() {
        return desigualdades;
    }

    public void setDesigualdades(ArrayList<JLabel> desigualdades) {
        this.desigualdades = desigualdades;
    }
       
    public Integer getIndicePartida() {
        return indicePartida;
    }

    public void setIndicePartida(int indicePartida) {
        this.indicePartida = indicePartida;
    }
    // </editor-fold >

}