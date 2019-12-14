package com.example.primerparcial15;

public class Compras {

    int id_compra;
    String nombre_producto;
    int cantidad;
    double precio_compra;
    double costo_total;

    public Compras(int id_compra, String nombre_producto, int cantidad, double precio_compra, double costo_total) {
        this.id_compra = id_compra;
        this.nombre_producto = nombre_producto;
        this.cantidad = cantidad;
        this.precio_compra = precio_compra;
        this.costo_total = costo_total;
    }

    public Compras(String nombre_producto, int cantidad, double precio_compra, double costo_total) {
        this.nombre_producto = nombre_producto;
        this.cantidad = cantidad;
        this.precio_compra = precio_compra;
        this.costo_total = costo_total;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public double getCosto_total() {
        return costo_total;
    }

    public void setCosto_total(double costo_total) {
        this.costo_total = costo_total;
    }
}
