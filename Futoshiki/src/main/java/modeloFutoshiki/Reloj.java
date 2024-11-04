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
        this.horas = horas;
    }
    
    public int getMinutos(){
        return minutos;
    }
    
    public void setMinutos(int minutos){
        this.minutos = minutos;
    }
    
    public int getSegundos(){
        return segundos;
    }
    
    public void setSegundos(int segundos){
        this.segundos = segundos;
    }
    // </editor-fold>
}
