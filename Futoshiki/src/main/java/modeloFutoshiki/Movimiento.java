package modeloFutoshiki;

/**
 *
 * @author ximena molina - juan pablo cambronero
 */
public class Movimiento{
    private int fila;
    private int columna;
    private String valor;

    public Movimiento(int fila, int columna, String valor) {
        this.fila = fila;
        this.columna = columna;
        this.valor = valor;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public String getValor() {
        return valor;
    }
}
