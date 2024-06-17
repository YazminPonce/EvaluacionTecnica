/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author yazmi
 */
public class Movimientos {
  private int   idMovimiento;
  private Usuario usuario;
  private Date fechaHora;
  private String tipoMovimiento;

    public Movimientos(int idMovimiento, Usuario usuario, Date fechaHora, String tipoMovimiento) {
        this.idMovimiento = idMovimiento;
        this.usuario = usuario;
        this.fechaHora = fechaHora;
        this.tipoMovimiento = tipoMovimiento;
    }

    public Movimientos() {
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

  
}
