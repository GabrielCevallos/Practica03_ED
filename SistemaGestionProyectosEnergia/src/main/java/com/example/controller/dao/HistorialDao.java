package com.example.controller.dao;

import com.example.models.Historial;
import com.example.controller.dao.implement.AdapterDao;
import com.example.controller.tda.list.LinkedList;

import java.lang.reflect.Method;
import java.time.LocalDateTime;


public class HistorialDao extends AdapterDao<Historial> {
    private Historial historial;
    private LinkedList<Historial> listAll;

    //CONSTRUCTOR
    public HistorialDao() {
        super(Historial.class);
    }
    //GETTERS Y SETTERS
    public Historial getHistorial() {
        if (historial == null) {
            historial = new Historial();
        }
        return this.historial;
    }

    public void setHistorial(Object objeto, String operacion) throws Exception {
        final Integer id = listAll().getSize() + 1;
        final String clase = objeto.getClass().getSimpleName();
        final String fecha = currentDate();
        final String tiempo = currentTime();
        Method metodo = objeto.getClass().getMethod("getId");
        final Integer idObjeto = (Integer)metodo.invoke(objeto);
        this.historial = new Historial().ConstructorHistorial(id, clase, fecha, tiempo, operacion, idObjeto.toString());
    }

    //OBTENER FECHA ACTUAL
    public String currentDate() {
        final LocalDateTime dateTime = LocalDateTime.now();
        final int dia = dateTime.getDayOfMonth(); //Obtener dia
        final int mes = dateTime.getMonthValue(); //Obtener mes
        final int anio = dateTime.getYear();    //Obtener año

        StringBuilder fecha = new StringBuilder();
        fecha.append(dia).append("-").append(mes).append("-").append(anio); //Concatenar dia, mes y año
        return fecha.toString();
    }

    //OBTENER HORA ACTUAL
    public String currentTime() {
        final LocalDateTime dateTime = LocalDateTime.now();
        final int hora = dateTime.getHour(); //Obtener hora
        final int minuto = dateTime.getMinute(); //Obtener minuto
        final int segundo = dateTime.getSecond(); //Obtener segundo

        StringBuilder tiempo = new StringBuilder();
        tiempo.append(hora).append(":").append(minuto).append(":").append(segundo); //Concatenar hora, minuto y segundo
        return tiempo.toString();
    }
    
    public void registrarOperacion(Object objeto, String operacion) throws Exception {
        setHistorial(objeto, operacion); //Registrar
        save(); //Guardar
    }

    public Historial save() throws Exception {
        Integer id = this.getHistorial().getId();
        this.getHistorial().setId(id);
        return persist(historial);
    }

    public LinkedList<Historial> getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }
}