package com.example.controller.dao.services;

import com.example.controller.dao.InversionistaDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Inversionista;
import com.example.models.enumerator.TipoIdentificacion;

public class InversionistaServices {
    private InversionistaDao obj;

    public InversionistaServices() {
        this.obj = new InversionistaDao();
    }
    
    public Inversionista getInversionista() {
        return this.obj.getInversionista();
    }

    public void setInversionista(Inversionista inversionista) {
        this.obj.setInversionista(inversionista);
    } 

    public LinkedList<Inversionista> listAll() {
        return obj.getListAll();
    }

    public Inversionista get(Integer id) throws Exception {
        System.out.println("InversionistaServices.get");
        return obj.get(id);
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public Boolean deleteInversionista(Integer id) throws Exception {
        return obj.deleteInversionista(id);
    }

    public TipoIdentificacion getTipoIdentificacion(String tipo) {
        return obj.getTipoIdentificacion(tipo);
    }

    public TipoIdentificacion[] getTipos() {
        return obj.getTipos();
    }

    public Inversionista[] ordenar(String atributo, Integer tipoOrden, Integer metodoOrden) throws Exception {
        return this.obj.ordenar(atributo, tipoOrden, metodoOrden);
    }

    public Inversionista[] buscar(String atributo, Object x) throws Exception {
        return this.obj.buscar(atributo, x);
    }
}