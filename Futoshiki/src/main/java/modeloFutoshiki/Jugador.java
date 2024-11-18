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
    private String correo;
    private LocalTime tiempoActual;
    private LocalTime tiempoTerminaPartida; //este tiempo solo sería en caso de que se juegue con temporizador
    
    public Jugador(String nombre, String contrasena, String correo){
        setNombre(nombre);
        setContrasena(contrasena);
        setCorreo(correo);
        this.tiempoActual = null;
        this.tiempoTerminaPartida = null;
    }
    
    public String toString(){
        return nombre + "," + contrasena + "," + correo + "\n";
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
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }  
    
    // </editor-fold>

}
