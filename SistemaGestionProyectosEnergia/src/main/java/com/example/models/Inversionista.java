package com.example.models;

import com.example.models.enumerator.TipoIdentificacion;

public class Inversionista {
    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;
    private String direccion;
    private double capitalInvertido;
    private TipoIdentificacion tipo;

    //CONSTRUCTOR VACIO PARA PODER INSTANCIAR
    public Inversionista() {}

    //CONSTRUCTOR
    public Inversionista(Integer id, String nombre, String apellido, String dni, String direccion, double capitalInvertido, TipoIdentificacion tipo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccion = direccion;
        this.capitalInvertido = capitalInvertido;
        this.tipo = tipo;
    }

    //GETTERS Y SETTERS
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getCapitalInvertido() {
        return this.capitalInvertido;
    }

    public void setCapitalInvertido(double capitalInvertido) {
        this.capitalInvertido = capitalInvertido;
    }

    public TipoIdentificacion getTipo() {
        return this.tipo;
    }

    public void setTipo(TipoIdentificacion tipo) {
        this.tipo = tipo;
    }
    //TO STRING PARA IMPRIMIR EL OBJETO INVERSIONISTA
    public String toString() {
        return "id: " + this.id + "\n" +
                "Nombre: " + this.nombre + "\n" +
                "Apellido: " + this.apellido + "\n" +
                "Dni: " + this.dni + "\n" +
                "Direccion: " + this.direccion + "\n" +
                "Capital Invertido: " + this.capitalInvertido + "\n" +
                "Tipo de Identificacion: " + this.tipo + "\n";
    }
}