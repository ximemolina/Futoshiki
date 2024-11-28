package modeloFutoshiki;

/**
 *
 * @author ximena molina - juan pablo cambronero
 */
public class Movimiento{
    private int fila;
    private int columna;
    private String valor;

    /**
     *
     * @param fila
     * @param columna
     * @param valor
     */
    public Movimiento(int fila, int columna, String valor) {
        this.fila = fila;
        this.columna = columna;
        this.valor = valor;
    }

    /**
     *
     * @return
     */
    public int getFila() {
        return fila;
    }

    /**
     *
     * @return
     */
    public int getColumna() {
        return columna;
    }

    /**
     *
     * @return
     */
    public String getValor() {
        return valor;
    }
}
