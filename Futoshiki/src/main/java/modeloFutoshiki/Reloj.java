package modeloFutoshiki;

/**
 *
 * @author ximena molina - juan pablo cambronero
 */
public class Reloj {
    private int tipo; // 0: sin reloj | 1: cronómetro | 2: temporizador
    private int horas;
    private int minutos;
    private int segundos;
    
    //constructor
    public Reloj (int tipo){
        setTipo(tipo);
    }
    
    public String toString(){
        if (getTipo() == 2) return String.valueOf(tipo) + String.valueOf(horas) + String.valueOf(minutos) + String.valueOf(segundos);
        else return String.valueOf(tipo);
    }
    
    // Método en la clase Reloj para obtener datos del temporizador
    public int[] obtenerDatosTemporizador() {
        if (tipo == 2) { // Tipo 2 es temporizador
            return new int[]{horas, minutos, segundos};
        } else {
            return new int[]{0, 0, 0}; // O valores que indiquen que no hay temporizador activo
        }
}

    
    // <editor-fold defaultstate="collapsed" desc="Setters-Getters">
    public int getTipo() {
        return tipo;
    }
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    public int getHoras(){
        return horas;
    }
    
    public void setHoras(int horas){
        if (horas >= 0 && horas <= 5){
            this.horas = horas;
        } else {
            throw new IllegalArgumentException("Las horas deben estar entre 0 y 5.");
        }
    }
    
    public int getMinutos(){
        return minutos;
    }
    
    public void setMinutos(int minutos){
        if (minutos >= 0 && minutos <= 59 ){
            this.minutos = minutos;
        } else {
            throw new IllegalArgumentException("Los minutos deben estar entre 0 y 59.");
        }
    }
    
    public int getSegundos(){
        return segundos;
    }
    
    public void setSegundos(int segundos){
        if (segundos >= 0 && segundos <= 59 ){
            this.segundos = segundos;
        } else {
            throw new IllegalArgumentException("Los segundos deben estar entre 0 y 59.");
        }

    }
    // </editor-fold>
}
