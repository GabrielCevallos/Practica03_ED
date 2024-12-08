package com.example.rest;

import java.util.HashMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.google.gson.Gson;
import com.example.controller.dao.services.ProyectoEnergiaServices;


@Path("proyect")
public class ProyectoEnergiaApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        HashMap<String, Object> map = new HashMap<>();
        ProyectoEnergiaServices pes = new ProyectoEnergiaServices();
        try {
            map.put("status", "OK");
            map.put("data", pes.listAll().toArray());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "ERROR");
            map.put("data", e.getMessage());
        }
        if (pes.listAll().isEmpty()) {
            map.put("status", "OK");
            map.put("data", new Object[] {});
        }
        return Response.ok(map).build();
    }


    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProyecto(@PathParam("id") Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        ProyectoEnergiaServices pes = new ProyectoEnergiaServices();
        try {
            pes.setProyectoEnergia(pes.get(id));
            map.put("status", "OK");
            map.put("data", pes.getProyectoEnergia());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "ERROR");
            map.put("data", e.getMessage());
        }
        if (pes.getProyectoEnergia().getId() == null) {
            map.put("data", "No existe el Proyecto con ese identificador");
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }
        return Response.ok(map).build();
    }


    @Path("/provincias")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getType() {
        HashMap<String, Object> map = new HashMap<>();
        ProyectoEnergiaServices pes = new ProyectoEnergiaServices();
        try {
            map.put("status", "OK");
            map.put("data", pes.getProvincias());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "ERROR");
            map.put("data", e.getMessage());
        }
        return Response.ok(map).build();
    }

    
    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap<String, Object> map) {
        HashMap<String, Object> res = new HashMap<>();
        Gson g = new Gson();
        String a = g.toJson(map);
        System.out.println("****************************\n" + a);
        try {
            ProyectoEnergiaServices pes = new ProyectoEnergiaServices();
            
            pes.getProyectoEnergia().setNombre(map.get(("nombre")).toString());
            pes.getProyectoEnergia().setFechaInicio(map.get(("fechaInicio")).toString());
            pes.getProyectoEnergia().setFechaFin(map.get("fechaFin").toString());
            pes.getProyectoEnergia().setCosto((Double.parseDouble(map.get("costo").toString())));
            pes.getProyectoEnergia().setCantElectricidad((Double.parseDouble(map.get("cantElectricidad").toString())));
            pes.getProyectoEnergia().setMontoTotalInversion((Double.parseDouble(map.get("montoTotalInversion").toString())));
            pes.getProyectoEnergia().setProvincia(pes.getProvincia(map.get("provincia").toString()));
            pes.save();
            res.put("status", "OK");
            res.put("data", "Proyecto registrado correctamente");
            return Response.ok(res).build();
        } catch (Exception e) {
            e.printStackTrace();
            res.put("status", "ERROR");
            res.put("data", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }


    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(HashMap<String, Object> map) {
        HashMap<String, Object> res = new HashMap<>();
        try {
            ProyectoEnergiaServices pes = new ProyectoEnergiaServices();
            pes.setProyectoEnergia(pes.get(Integer.parseInt(map.get("id").toString())));
            pes.getProyectoEnergia().setNombre(map.get(("nombre")).toString());
            pes.getProyectoEnergia().setFechaInicio(map.get(("fechaInicio")).toString());
            pes.getProyectoEnergia().setFechaFin(map.get("fechaFin").toString());
            pes.getProyectoEnergia().setCosto((Double.parseDouble(map.get("costo").toString())));
            pes.getProyectoEnergia().setCantElectricidad((Double.parseDouble(map.get("cantElectricidad").toString())));
            pes.getProyectoEnergia().setMontoTotalInversion((Double.parseDouble(map.get("montoTotalInversion").toString())));            
            pes.getProyectoEnergia().setProvincia(pes.getProvincia(map.get("provincia").toString())); 
            pes.update();
            res.put("status", "OK");
            res.put("data", "Proyecto actualizado correctamente");
            return Response.ok(res).build();
        } catch (Exception e) {
            e.printStackTrace();
            res.put("status", "ERROR");
            res.put("data", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }


    @Path("/delete/{id}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        ProyectoEnergiaServices pes = new ProyectoEnergiaServices();
        try {
            pes.deleteProyectoEnergia(id);
            map.put("status", "OK");
            map.put("data", "Proyecto eliminado correctamente");
            return Response.ok(map).build();
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "ERROR");
            map.put("data", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }


    @Path("/sort/{atributo}/{tipoOrden}/{metodoOrden}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response sort(@PathParam("atributo") String atributo, @PathParam("tipoOrden") Integer tipoOrden, @PathParam("metodoOrden") Integer metodoOrden) {
        HashMap<String, Object> map = new HashMap<>();
        ProyectoEnergiaServices pes = new ProyectoEnergiaServices();
        try {
            map.put("status", "OK");
            map.put("data", pes.ordenar(atributo, tipoOrden, metodoOrden));
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "ERROR");
            map.put("data", e.getMessage());
        }
        if (pes.listAll().isEmpty()) {
            map.put("status", "OK");
            map.put("data", new Object[] {});
        }
        return Response.ok(map).build();
    }


    @Path("/search/{atributo}/{x}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@PathParam("atributo") String atributo, @PathParam("x") String x) {
        HashMap<String, Object> map = new HashMap<>();
        ProyectoEnergiaServices pes = new ProyectoEnergiaServices();
        try {
            map.put("status", "OK");
            map.put("data", pes.buscar(atributo, x));
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "ERROR");
            map.put("data", e.getMessage());
        }
        if (pes.listAll().isEmpty()) {
            map.put("status", "OK");
            map.put("data", new Object[] {});
        }
        return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
    }    
}    