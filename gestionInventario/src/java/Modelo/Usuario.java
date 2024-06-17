/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author yazmi
 */
public class Usuario {
       
  private int   idUsuario;
  private String correo;
  private String nombre;
  private String contrasenia;
  private Rol rol;
  private int estatus;

    public Usuario() {
    }

    public Usuario(int idUsuario, String correo, String nombre, String contrasenia, Rol rol, int estatus) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.estatus = estatus;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }
  
  

}
