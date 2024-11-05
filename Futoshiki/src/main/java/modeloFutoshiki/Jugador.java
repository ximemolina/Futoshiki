package modeloFutoshiki;

import java.time.*;
/**
 *
 * @author ximena molina - juan pablo cambronero
 */
public class Jugador {
    //atributos
    private String nombre;
    private String contrasena;
    private LocalTime tiempoActual;
    private LocalTime tiempoTerminaPartida; //este tiempo solo sería en caso de que se juegue con temporizador
    
    public Jugador(String nombre, String contrasena){
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.tiempoActual = null;
        this.tiempoTerminaPartida = null;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Setters-Getters">
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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
