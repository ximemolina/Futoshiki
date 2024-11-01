package modeloFutoshiki;

import java.time.*;
/**
 *
 * @author ximena molina - juan pablo cambronero
 */
public class Jugador {
    //atributos
    private String nombre;
    private String pin;
    private LocalTime tiempoActual;
    private LocalTime tiempoTerminaPartida; //este tiempo solo sería en caso de que se juegue con temporizador
    
    // <editor-fold defaultstate="collapsed" desc="Setters-Getters">
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public LocalTime getTiempoActual() {
        return tiempoActual;
    }

    public void setTiempoActual(LocalTime tiempoActual) {
        this.tiempoActual = tiempoActual;
    }

    public LocalTime getTiempoTerminaPartida() {
        return tiempoTerminaPartida;
    }

    public void setTiempoTerminaPartida(LocalTime tiempoTerminaPartida) {
        this.tiempoTerminaPartida = tiempoTerminaPartida;
    }
    // </editor-fold>
}
