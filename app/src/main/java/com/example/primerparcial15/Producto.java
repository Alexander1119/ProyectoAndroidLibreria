package com.example.primerparcial15;

public class Producto {

    int item_producto;
    String nombre_producto_;
    String descripcion_producto;
    float precio_venta ;
    int stock;
    byte[] imagen;

    public Producto(int item_producto, String nombre_producto_, String descripcion_producto, float precio_venta, int stock, byte[] imagen) {
        this.item_producto = item_producto;
        this.nombre_producto_ = nombre_producto_;
        this.descripcion_producto = descripcion_producto;
        this.precio_venta = precio_venta;
        this.stock = stock;
        this.imagen = imagen;
    }

    public Producto(String nombre_producto_, String descripcion_producto, float precio_venta, int stock) {
        this.nombre_producto_ = nombre_producto_;
        this.descripcion_producto = descripcion_producto;
        this.precio_venta = precio_venta;
        this.stock = stock;
    }

    public Producto(String nombre_producto_, String descripcion_producto, float precio_venta, int stock, byte[] imagen) {
        this.nombre_producto_ = nombre_producto_;
        this.descripcion_producto = descripcion_producto;
        this.precio_venta = precio_venta;
        this.stock = stock;
        this.imagen = imagen;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public int getItem_producto() {
        return item_producto;
    }

    public String getNombre_producto_() {
        return nombre_producto_;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public float getPrecio_venta() {
        return precio_venta;
    }

    public int getStock() {
        return stock;
    }

    public void setItem_producto(int item_producto) {
        this.item_producto = item_producto;
    }

    public void setNombre_producto_(String nombre_producto_) {
        this.nombre_producto_ = nombre_producto_;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }

    public void setPrecio_venta(float precio_venta) {
        this.precio_venta = precio_venta;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
