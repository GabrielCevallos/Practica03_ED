package com.example.controller.dao.services;

import com.example.controller.dao.HistorialDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Historial;

public class HistorialServices {
    private HistorialDao obj;

    public HistorialServices() {
        this.obj = new HistorialDao();
    }
    
    public Historial getHistorial() {
        return this.obj.getHistorial();
    }

    public void setHistorial(Object objeto, String operacion) throws Exception {
        this.obj.setHistorial(objeto, operacion);
    }

    public LinkedList<Historial> listAll() {
        return obj.getListAll();
    }

    public Historial get(Integer id) throws Exception {
        System.out.println("HistorialServices.get");
        return obj.get(id);
    }

    public Historial save() throws Exception {
        return obj.save();
    }
}