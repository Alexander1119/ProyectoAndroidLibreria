package com.example.primerparcial15;

public class Venta {

    int id_venta;
    String fecha;
    float CantidadTotal;
    int id_cliente;
    int tiempo;

    public Venta(int id_venta, String fecha, float cantidadTotal, int id_cliente, int tiempo) {
        this.id_venta = id_venta;
        this.fecha = fecha;
        CantidadTotal = cantidadTotal;
        this.id_cliente = id_cliente;
        this.tiempo = tiempo;
    }

    public Venta(String fecha, float cantidadTotal, int id_cliente, int tiempo) {
        this.fecha = fecha;
        CantidadTotal = cantidadTotal;
        this.id_cliente = id_cliente;
        this.tiempo = tiempo;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getCantidadTotal() {
        return CantidadTotal;
    }

    public void setCantidadTotal(float cantidadTotal) {
        CantidadTotal = cantidadTotal;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
}
