/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Timestamp;

/**
 *
 * @author Jimena
 */
public class Historial {
    private int id;
    private int idProducto;
    private String tipo; // "ENTRADA" o "SALIDA"
    private int cantidad;
    private String usuario;
    private Timestamp fechaHora;

    public Historial() {
    }

    
    // Constructor
    public Historial(int id, int idProducto, String tipo, int cantidad, String usuario, Timestamp fechaHora) {
        this.id = id;
        this.idProducto = idProducto;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.usuario = usuario;
        this.fechaHora = fechaHora;
    }

    // Getters y Setters
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

    public Timestamp getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Timestamp fechaHora) {
        this.fechaHora = fechaHora;
    }

//    @Override
//    public String toString() {
//        return "HistorialMovimiento{" +
//                "id=" + id +
//                ", idProducto=" + idProducto +
//                ", tipo='" + tipo + '\'' +
//                ", cantidad=" + cantidad +
//                ", usuario='" + usuario + '\'' +
//                ", fechaHora=" + fechaHora +
//                '}';
//    }
    
}
