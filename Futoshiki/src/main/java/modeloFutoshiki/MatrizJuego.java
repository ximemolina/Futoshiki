package modeloFutoshiki;
import java.util.*;
import javax.swing.JButton;
/**
 *
 * @author ximena molina - juan pablo cambronero
 */
public class MatrizJuego {
    private int dimension;
    private List valoresArchivoPartida;
    private ArrayList<JButton> botonesCasillas; //botones de las casillas de la plantilla del juego
    private ArrayList<JButton> botonesNumeros; // botones que están al lado que contienen los posibles números a poner

    //constructor
    public MatrizJuego(int dimension, List valores){
        setDimension(dimension);
        setValoresArchivoPartida(valores);
        botonesCasillas = new ArrayList <> ();
        botonesNumeros = new ArrayList <>();
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
    
    
    // </editor-fold >
    
}
