package com.example.models;

public class Historial {
    private Integer id;
    private String clase;
    private String fecha;
    private String hora;
    private String descripcion;
    private String idObject;

    //CONSTRUCTOR VACIO PARA PODER INSTANCIAR
    public Historial() {}

    //CONSTRUCTOR
    public Historial ConstructorHistorial(Integer id, String clase, String fecha, String hora, String descripcion, String idObject) {
        this.id = id;
        this.clase = clase;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
        this.idObject = idObject;
        return this;
    }

    //GETTERS Y SETTERS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }
    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdObject() {
        return idObject;
    }

    public void setIdObject(String idObject) {
        this.idObject = idObject;
    }
}