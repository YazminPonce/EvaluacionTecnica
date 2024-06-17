/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author yazmi
 */
public class Productos {
  private int   idProductos;
  private String nombre;
  private int cantidad;
  private int estatus;

    public Productos() {
    }

    public Productos(int idProductos, String nombre, int cantidad, int estatus) {
        this.idProductos = idProductos;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.estatus = estatus;
    }

    public int getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(int idProductos) {
        this.idProductos = idProductos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }
  
  
}
