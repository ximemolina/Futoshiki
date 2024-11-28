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

    /**
     *
     * @param dimension
     * @param valores
     */
    public MatrizJuego(int dimension, List valores){
        setDimension(dimension);
        setValoresArchivoPartida(valores);
        botonesCasillas = new ArrayList <> ();
        botonesNumeros = new ArrayList <>();
        desigualdades = new ArrayList <> ();
    }
    

    /**
     *
     * @param fila
     * @param columna
     * @return botón dada la fila y columna
     */
    public JButton obtenerBoton(int fila, int columna) {
        return botonesCasillas.get(fila * dimension + columna);
    }    
    

    /**
     *
     * @param boton
     * @return fila del boton
     */
    public int obtenerFila(JButton boton) {
        int indice = getBotonesCasillas().indexOf(boton);
        return indice / dimension; // División entera
    }

    /**
     *
     * @param boton
     * @return columan del boton
     */
    public int obtenerColumna(JButton boton) {
        int indice = getBotonesCasillas().indexOf(boton);
        return indice % dimension; // Resto
    }    
    
    /**
     *
     * @return partida mostrar al azar
     */
    public int partidaAzar(){
        Random random = new Random();
        return random.nextInt((valoresArchivoPartida.size()-1 - 0) + 1) + 0;
    }
    
    /**
     *
     * @param botonSeleccionado
     * @param pantalla
     * @throws Exception
     */
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
                    }else if (obtenerFila(boton) == fila+1 && obtenerColumna(boton) == columna && !boton.getText().equals("") && fila ==contFilas-1){ //boton está justo abajo del seleccionado
                        if(desigualdad.getText().trim().equals("^")){
                            if( (Integer.parseInt(boton.getText().replaceAll("<[^>]*>", "").trim())) < Integer.parseInt(valor)){
                                boton.setBackground(Color.red);
                                throw new Exception("JUGADA NO ES VÁLIDA PORQUE NO CUMPLE CON LA RESTRICCIÓN DE MENOR");
                            }   
                          }
                        else if(desigualdad.getText().trim().equals("V")){
                              if( (Integer.parseInt(boton.getText().replaceAll("<[^>]*>", "").trim())) > Integer.parseInt(valor)){
                                  boton.setBackground(Color.red);
                                  throw new Exception("JUGADA NO ES VÁLIDA PORQUE NO CUMPLE CON LA RESTRICCIÓN DE MAYOR");
                              } 
                        }
                    }
                }
            
            }
            cont++;
        }
    }     
    
    

    /**
     *
     * @param columna
     * @param fila
     * @param constante
     */
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

    /**
     *
     * @param columna
     * @param fila
     * @param identificador
     */
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

    /**
     *
     * @param columna
     * @param fila
     * @param identificador
     */
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

    /**
     *
     * @return
     */
    public int getDimension() {
        return dimension;
    }

    /**
     *
     * @param dimension
     */
    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    /**
     *
     * @return
     */
    public List getValoresArchivoPartida() {
        return valoresArchivoPartida;
    }

    /**
     *
     * @param valoresArchivoPartida
     */
    public void setValoresArchivoPartida(List valoresArchivoPartida) {
        this.valoresArchivoPartida = valoresArchivoPartida;
    }

    /**
     *
     * @return
     */
    public ArrayList<JButton> getBotonesCasillas() {
        return botonesCasillas;
    }

    /**
     *
     * @param botonesCasillas
     */
    public void setBotonesCasillas(ArrayList<JButton> botonesCasillas) {
        this.botonesCasillas = botonesCasillas;
    }

    /**
     *
     * @return
     */
    public ArrayList<JButton> getBotonesNumeros() {
        return botonesNumeros;
    }

    /**
     *
     * @param botonesNumeros
     */
    public void setBotonesNumeros(ArrayList<JButton> botonesNumeros) {
        this.botonesNumeros = botonesNumeros;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<JLabel> getDesigualdades() {
        return desigualdades;
    }

    /**
     *
     * @param desigualdades
     */
    public void setDesigualdades(ArrayList<JLabel> desigualdades) {
        this.desigualdades = desigualdades;
    }
       
    /**
     *
     * @return
     */
    public Integer getIndicePartida() {
        return indicePartida;
    }

    /**
     *
     * @param indicePartida
     */
    public void setIndicePartida(int indicePartida) {
        this.indicePartida = indicePartida;
    }
    // </editor-fold >

}