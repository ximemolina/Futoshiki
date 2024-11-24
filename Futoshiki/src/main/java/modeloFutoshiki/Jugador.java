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
    
    public Jugador(String nombre, String contrasena, String correo) throws Exception{
        setNombre(nombre);
        setContrasena(contrasena);
        setCorreo(correo);
        this.tiempoActual = null;
    }
    
    public String toString(){
        return nombre + "," + contrasena + "," + correo + "\n";
    }

    // <editor-fold defaultstate="collapsed" desc="Setters-Getters">
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws Exception{
        if (nombre.length() >= 0 && nombre.length() <= 30){
            this.nombre = nombre;
        }else throw new Exception("Nombre de jugador debe de ser de 0 a 30 caracteres");
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
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }  
    
    // </editor-fold>

}
