package com.example.primerparcial15;

public class Cliente {

    int id_cliente;
    String ci_cliente;
    String apellido;
    String nombre;
    String correo;
    int telefono;
    String password;


    public Cliente(int id_cliente, String ci_cliente, String apellido, String nombre, String correo, int telefono, String password) {
        this.id_cliente = id_cliente;
        this.ci_cliente = ci_cliente;
        this.apellido = apellido;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.password = password;
    }

    public Cliente(String ci_cliente, String apellido, String nombre, String correo, int telefono, String password) {
        this.ci_cliente = ci_cliente;
        this.apellido = apellido;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.password = password;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public String getCi_cliente() {
        return ci_cliente;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setCi_cliente(String ci_cliente) {
        this.ci_cliente = ci_cliente;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
