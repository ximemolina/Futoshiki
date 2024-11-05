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
