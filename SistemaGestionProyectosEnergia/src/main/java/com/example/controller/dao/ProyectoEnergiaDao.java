package com.example.controller.dao;

import com.example.models.ProyectoEnergia;
import com.example.models.enumerator.Provincia;
import com.example.controller.dao.implement.AdapterDao;
import com.example.controller.tda.list.LinkedList;

public class ProyectoEnergiaDao extends AdapterDao<ProyectoEnergia> {
    private ProyectoEnergia proyectoEnergia;
    private LinkedList<ProyectoEnergia> listAll;

    //CONSTRUCTOR
    public ProyectoEnergiaDao() {
        super(ProyectoEnergia.class);
    }

    //GETTERS Y SETTERS
    public ProyectoEnergia getProyectoEnergia() {
        if (proyectoEnergia == null) {
            proyectoEnergia = new ProyectoEnergia();
        }
        return this.proyectoEnergia;
    }

    public void setProyectoEnergia(ProyectoEnergia proyectoEnergia) {
        this.proyectoEnergia = proyectoEnergia;
    }

    //CRUD
    public LinkedList<ProyectoEnergia> getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        proyectoEnergia.setId(id);
        this.persist(this.proyectoEnergia);
        registrarOperacionProyecto("REGISTRAR");
        this.listAll = listAll(); 
        return true;
    }

    public Boolean update() throws Exception {
        final Integer id = this.getProyectoEnergia().getId();
        if(!camposLLenos()) {
            throw new Exception("Los campos estan vacios, por favor completarlos");
        }
        this.merge(getProyectoEnergia(), id);
        this.listAll = listAll();
        new HistorialDao().registrarOperacion(this.getProyectoEnergia(), "ACTUALIZAR");
        return true;
    }

    public Boolean deleteProyectoEnergia(Integer id) throws Exception {
        this.remove(id);    
        this.listAll = listAll();
        new HistorialDao().registrarOperacion(this.get(id), "ELIMINAR");
        return true;
    }

    //GET PROVINCIAS
    public Provincia getProvincia(String provincia) {
        return Provincia.valueOf(provincia);
    }

    public Provincia[] getProvincias() {
        return Provincia.values();
    }

    //VALIDADORES
    public Boolean camposLLenos() {
        if(this.getProyectoEnergia().getNombre() == null) return false;
        if(this.getProyectoEnergia().getFechaInicio() == null) return false;
        if(this.getProyectoEnergia().getFechaFin() == null) return false;
        if(this.getProyectoEnergia().getCosto() == 0.0) return false;
        if(this.getProyectoEnergia().getCantElectricidad() == 0.0) return false;
        if(this.getProyectoEnergia().getMontoTotalInversion() == 0.0) return false;
        if(this.getProyectoEnergia().getProvincia() == null) return false;
        return true;
    }

    public void registrarOperacionProyecto(String operacion) throws Exception {
        new HistorialDao().registrarOperacion(this.getProyectoEnergia(), operacion);
    }

    //ORDENAR
    public ProyectoEnergia[] ordenar(String atributo, Integer tipoOrden, Integer metodoOrden) throws Exception {
        LinkedList<ProyectoEnergia> list = listAll();
        switch (metodoOrden) {
            case 0:
                list.quickSort(atributo, tipoOrden);
                break;
            case 1:
                list.mergeSort(atributo, tipoOrden);
                break;
            case 2:
                list.shellSort(atributo, tipoOrden);
                break;       
            default:
                System.out.println("Metodo de ordenamiento no valido");
                break;
        }
        return list.toArray();
    }

    //BUSCAR
    public ProyectoEnergia[] buscar(String atributo, Object x) throws Exception {
        try {
            LinkedList<ProyectoEnergia> list = listAll();
            if(atributo.equals("nombre")) {
                return list.buscarPorAtributo(atributo, x).toArray();
            } else {
                return list.buscarPorAtributo(atributo, x).toArray();
            }
        } catch (Exception e) {
            return new ProyectoEnergia[] {};
        }
    }
}
