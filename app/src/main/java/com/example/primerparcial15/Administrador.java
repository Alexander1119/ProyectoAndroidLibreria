package com.example.primerparcial15;

public class Administrador {

    int id_admi;
    String ci_admi;
    String apellido_paterno;
    String apellido_materno;
    String nombre;
    String direccion;
    String correo;
    String password;

    public Administrador(int id_admi, String ci_admi, String apellido_paterno, String apellido_materno, String nombre, String direccion, String correo, String password) {
        this.id_admi = id_admi;
        this.ci_admi = ci_admi;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
        this.password = password;
    }

    public Administrador(String ci_admi, String apellido_paterno, String apellido_materno, String nombre, String direccion, String correo, String password) {
        this.ci_admi = ci_admi;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
        this.password = password;
    }

    public int getId_admi() {
        return id_admi;
    }

    public String getCi_admi() {
        return ci_admi;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPassword() {
        return password;
    }

    public void setId_admi(int id_admi) {
        this.id_admi = id_admi;
    }

    public void setCi_admi(String ci_admi) {
        this.ci_admi = ci_admi;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
