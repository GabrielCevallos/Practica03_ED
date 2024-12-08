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
import com.example.controller.dao.services.InversionistaServices;


@Path("investor")
public class InversionistaApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        HashMap<String, Object> map = new HashMap<>();
        InversionistaServices is = new InversionistaServices();
        try {
            map.put("status", "OK");
            map.put("data", is.listAll().toArray());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "ERROR");
            map.put("data", e.getMessage());
        }
        if (is.listAll().isEmpty()) {
            map.put("status", "OK");
            map.put("data", new Object[] {});
        }
        return Response.ok(map).build();
    }


    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInvestor(@PathParam("id") Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        InversionistaServices is = new InversionistaServices();
        try {
            is.setInversionista(is.get(id));
            map.put("status", "OK");
            map.put("data", is.getInversionista());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "ERROR");
            map.put("data", e.getMessage()); 
        }
        if (is.getInversionista().getId() == null) {
            map.put("data", "No existe el Inversionista con ese identificador");
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }
        return Response.ok(map).build();
    }


    @Path("/listType")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getType() {
        HashMap<String, Object> map = new HashMap<>();
        InversionistaServices is = new InversionistaServices();
        try {
            map.put("status", "OK");
            map.put("data", is.getTipos());
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
        System.out.println("*********************\n" + a);
        try {
            InversionistaServices is = new InversionistaServices();
            is.getInversionista().setNombre(map.get(("nombre")).toString());
            is.getInversionista().setApellido(map.get(("apellido")).toString());
            is.getInversionista().setDni(map.get("dni").toString());
            is.getInversionista().setDireccion(map.get(("direccion")).toString());
            is.getInversionista().setCapitalInvertido((Double.parseDouble(map.get("capitalInvertido").toString())));
            is.getInversionista().setTipo(is.getTipoIdentificacion(map.get("tipo").toString()));
            is.save();
            res.put("status", "OK");
            res.put("data", "Inversionista registrado correctamente");
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
            InversionistaServices is = new InversionistaServices();
            is.setInversionista(is.get(Integer.parseInt(map.get("id").toString())));
            is.getInversionista().setNombre(map.get(("nombre")).toString());
            is.getInversionista().setApellido(map.get(("apellido")).toString());
            is.getInversionista().setDni(map.get("dni").toString());
            is.getInversionista().setDireccion(map.get(("direccion")).toString());
            is.getInversionista().setCapitalInvertido((Double.parseDouble(map.get("capitalInvertido").toString())));
            is.getInversionista().setTipo(is.getTipoIdentificacion(map.get("tipo").toString()));
            is.update();
            res.put("status", "OK");
            res.put("data", "Inversionista actualizado correctamente");
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
        InversionistaServices is = new InversionistaServices();
        try {
            is.deleteInversionista(id);
            map.put("status", "OK");
            map.put("data", "Inversionista eliminado correctamente");
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
        InversionistaServices is = new InversionistaServices();
        try {
            map.put("status", "OK");
            map.put("data", is.ordenar(atributo, tipoOrden, metodoOrden));
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "ERROR");
            map.put("data", e.getMessage());
        }
        if (is.listAll().isEmpty()) {
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
        InversionistaServices is = new InversionistaServices();
        try {
            map.put("status", "OK");
            map.put("data", is.buscar(atributo, x));
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "ERROR");
            map.put("data", e.getMessage());
        }
        if (is.listAll().isEmpty()) {
            map.put("status", "OK");
            map.put("data", new Object[] {});
        }
        return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
    }
    
}