package com.example.controller.dao.services;

import com.example.controller.dao.ProyectoEnergiaDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.ProyectoEnergia;
import com.example.models.enumerator.Provincia;

public class ProyectoEnergiaServices {
    private ProyectoEnergiaDao obj;

    public ProyectoEnergiaServices() {
        obj = new ProyectoEnergiaDao();
    }

    public ProyectoEnergia getProyectoEnergia() {
        return obj.getProyectoEnergia();
    }

    public void setProyectoEnergia(ProyectoEnergia proyectoEnergia) {
        obj.setProyectoEnergia(proyectoEnergia);
    }

    public LinkedList<ProyectoEnergia> listAll() {
        return obj.getListAll();
    }

    public ProyectoEnergia get(Integer id) throws Exception {
        System.out.println("ProyectoEnergiaServices.get");
        return obj.get(id);
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public Boolean deleteProyectoEnergia(Integer id) throws Exception {
        return obj.deleteProyectoEnergia(id);
    }
   
    public Provincia getProvincia(String provincia) {
        return obj.getProvincia(provincia);
    }

    public Provincia[] getProvincias() {
        return obj.getProvincias();
    }

    public ProyectoEnergia[] ordenar(String atributo, Integer tipoOrden, Integer metodoOrden) throws Exception {
        return obj.ordenar(atributo, tipoOrden, metodoOrden);
    }

    public ProyectoEnergia[] buscar(String atributo, Object x) throws Exception {
        return obj.buscar(atributo, x);
    }
}
