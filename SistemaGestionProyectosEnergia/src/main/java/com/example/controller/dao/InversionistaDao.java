package com.example.controller.dao;

import com.example.models.Inversionista;
import com.example.models.enumerator.TipoIdentificacion;
import com.example.controller.dao.implement.AdapterDao;
import com.example.controller.tda.list.LinkedList;


public class InversionistaDao extends AdapterDao<Inversionista> {
    private Inversionista inversionista;
    private LinkedList<Inversionista> listAll;

    //CONSTRUCTOR
    public InversionistaDao() {
        super(Inversionista.class);
    }

    //GETTERS Y SETTERS
    public Inversionista getInversionista() {
        if (inversionista == null) {
            inversionista = new Inversionista();
        }
        return this.inversionista;
    }

    public void setInversionista(Inversionista inversionista) {
        this.inversionista = inversionista;
    }

    //CRUD
    public LinkedList<Inversionista> getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        inversionista.setId(id);
        this.persist(this.inversionista);
        this.listAll = listAll();
        registrarOperacionInversionista("REGISTRAR");
        return true;
    }

    public Boolean update() throws Exception {
        final Integer id = this.getInversionista().getId();
        if(!camposLLenos()) {
            throw new Exception("Los campos estan vacios, por favor completarlos");
        }
        new HistorialDao().registrarOperacion(this.getInversionista(), "ACTUALIZAR");
        this.merge(getInversionista(), id);
        this.listAll = listAll();
        return true;
    }

    public Boolean deleteInversionista(Integer id) throws Exception {
        new HistorialDao().registrarOperacion(this.get(id), "ELIMINAR");
        this.remove(id);    //Elimina el inversionista mediante el id
        this.listAll = listAll();
        return true;
    }

    //GET TIPOS DE IDENTIFICACION
    public TipoIdentificacion getTipoIdentificacion(String tipo) {
        return TipoIdentificacion.valueOf(tipo);
    }

    public TipoIdentificacion[] getTipos() {
        return TipoIdentificacion.values();
    }
    
    //VALIDADORES
    public Boolean camposLLenos() {
        if(this.getInversionista().getNombre() == null) return false;
        if(this.getInversionista().getApellido() == null) return false;
        if(this.getInversionista().getDni() == null) return false;
        if(this.getInversionista().getTipo() == null) return false;
        if(this.getInversionista().getDireccion() == null) return false;
        if(this.getInversionista().getCapitalInvertido() == 0) return false;
        return true;
    }

    public Boolean validarIdentificacion(String tipo) {
        final String code = this.getInversionista().getDni();
        if(tipo.equals("CEDULA")) {
            if(code.length() != 10) return false;
        } else if(tipo.equals("RUC")) {
            if(code.length() != 13) return false;
        } else if(tipo.equals("PASAPORTE")) {
            if(code.length() != 10) return false;
        }
        return true;
    }


    public void registrarOperacionInversionista(String operacion) throws Exception {
        new HistorialDao().registrarOperacion(this.getInversionista(), operacion);
    }

    //ORDENAR
    public Inversionista[] ordenar(String atributo, Integer tipoOrden, Integer metodoOrden) throws Exception {
        LinkedList<Inversionista> list = listAll();
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
    public Inversionista[] buscar(String atributo, Object x) throws Exception {
        try {
            LinkedList<Inversionista> list = listAll();
            if(atributo.equals("dni")) {
                return new Inversionista[] {list.busquedaBinaria(atributo, x)};
            } else {
                return list.buscarPorAtributo(atributo, x).toArray();
            }
        } catch (Exception e) {
            return new Inversionista[] {};
        }
    }
}