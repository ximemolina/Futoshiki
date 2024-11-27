package modeloFutoshiki;


/**
 *
 * @author ximena molina - juan pablo cambronero
 */

public class Juego {
    //atributos
    private int tamano;
    private int nivel; // 0: facil | 1: intermedio | 2: dificil
    private boolean multinivel;
    private boolean posicion; // true: derecho | false: izquierdo
    private Jugador jugador;
    private Reloj reloj;
    private MatrizJuego matriz;
    private String tiempoAcumulado;
    
    //constructor
    public Juego (int tamano, int nivel, boolean multinivel, boolean posicion, int tipoReloj){
        setTamano(tamano);
        setNivel(nivel);
        setMultinivel(multinivel);
        setPosicion(posicion);
        Reloj reloj = new Reloj(tipoReloj);
        setReloj(reloj);
    }
    
    //retorna información del juego en un solo string
    public String toString(){
        return String.valueOf(getTamano())+ "," + String.valueOf(getNivel())+ "," + isMultinivel()+ "," + isPosicion()+ "," + getReloj().toString();
    }
    
    //retorna la dificultad en String en lugar de un entero
    public String obtenerStringDificultad(){
        if(nivel == 0) return "facil";
        if(nivel == 1) return "intermedio";
        if(nivel == 2) return "dificil";
        return "";
    }
    
    // <editor-fold defaultstate="collapsed" desc="Setters-Getters">
    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public boolean isMultinivel() {
        return multinivel;
    }

    public void setMultinivel(boolean multinivel) {
        this.multinivel = multinivel;
    }

    public boolean isPosicion() {
        return posicion;
    }

    public void setPosicion(boolean posicion) {
        this.posicion = posicion;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Reloj getReloj() {
        return reloj;
    }

    public void setReloj(Reloj reloj) {
        this.reloj = reloj;
    }
    
    public MatrizJuego getMatriz() {
        return matriz;
    }

    public void setMatriz(MatrizJuego matriz) {
        this.matriz = matriz;
    }
    
    public String getTiempoAcumulado() {
        return tiempoAcumulado;
    }

    public void setTiempoAcumulado(String tiempoAcumulado) {
        this.tiempoAcumulado = tiempoAcumulado;
    }
     
    //</editor-fold>
    
}
