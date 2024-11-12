/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Jimena
 */
public class Movimientos {
    public int id;
    public int idProducto;
    public String tipo; 
    public int cantidad; 
    public String usuario;  
    public String fechaHora;

    public Movimientos() {
    }

    public Movimientos(int id, int idProducto, String tipo, int cantidad, String usuario, String fechaHora) {
        this.id = id;
        this.idProducto = idProducto;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.usuario = usuario;
        this.fechaHora = fechaHora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    
}
