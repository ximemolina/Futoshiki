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
    
    /**
     *
     * @param nombre
     * @param contrasena
     * @param correo
     * @throws Exception
     */
    public Jugador(String nombre, String contrasena, String correo) throws Exception{
        setNombre(nombre);
        setContrasena(contrasena);
        setCorreo(correo);
        this.tiempoActual = null;
    }
    
    /**
     *
     * @return
     */
    public String toString(){
        return nombre + "," + contrasena + "," + correo + "\n";
    }

    // <editor-fold defaultstate="collapsed" desc="Setters-Getters">

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     * @throws Exception
     */
    public void setNombre(String nombre) throws Exception{
        if (nombre.length() >= 0 && nombre.length() <= 30){
            this.nombre = nombre;
        }else throw new Exception("Nombre de jugador debe de ser de 0 a 30 caracteres");
    }

    /**
     *
     * @return
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     *
     * @param contrasena
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     *
     * @return
     */
    public LocalTime getTiempoActual() {
        return tiempoActual;
    }

    /**
     *
     * @param tiempoActual
     */
    public void setTiempoActual(LocalTime tiempoActual) {
        this.tiempoActual = tiempoActual;
    }
    
    /**
     *
     * @return
     */
    public String getCorreo() {
        return correo;
    }

    /**
     *
     * @param correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }  
    
    // </editor-fold>

}
