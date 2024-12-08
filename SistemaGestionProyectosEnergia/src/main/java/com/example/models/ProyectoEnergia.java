package com.example.models;

import com.example.models.enumerator.Provincia;

public class ProyectoEnergia {
    private Integer id;
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
    private double costo;
    private double cantidadElectricidad;
    private double montoTotalInversion;
    private Provincia provincia;

    //CONSTRUCTOR VACIO PARA PODER INSTANCIAR
    public ProyectoEnergia() {}

    //CONSTRUCTOR
    public ProyectoEnergia ConstructorProyectoEnergia(Integer id, String nombre, String fechaInicio, String fechaFin, double costo, 
                                                    double cantidadElectricidad, double montoTotalInversion, Provincia provincia) {
        this.id = id;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.costo = costo;
        this.cantidadElectricidad = cantidadElectricidad;
        this.montoTotalInversion = montoTotalInversion;
        this.provincia = provincia;
        return this;
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

    public String getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }   

    public double getCosto() {
        return this.costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getCantElectricidad() {
        return this.cantidadElectricidad;
    }

    public void setCantElectricidad(double cantidadElectricidad) {
        this.cantidadElectricidad = cantidadElectricidad;
    }

    public double getMontoTotalInversion() {
        return this.montoTotalInversion;
    }

    public void setMontoTotalInversion(double montoTotalInversion) {
        this.montoTotalInversion = montoTotalInversion;
    }

    public Provincia getProvincia() {
        return this.provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    //TO STRING PARA IMPRIMIR EL OBJETO PROYECTO ENERGIA
    @Override
    public String toString() {
        return " id: " + this.id + "\n" +
                " Nombre: " + this.nombre + "\n" +
                " Fecha de Inicio: " + this.fechaInicio + "\n" +
                " Fecha de Fin: " + this.fechaFin + "\n" +
                " Costo: " + this.costo + "\n" +
                " Cantidad de Electricidad: " + this.cantidadElectricidad + "\n" +
                " Monto Total de Inversion: " + this.montoTotalInversion + "\n" +
                " Provincia: " + this.provincia + "\n";
    }
}