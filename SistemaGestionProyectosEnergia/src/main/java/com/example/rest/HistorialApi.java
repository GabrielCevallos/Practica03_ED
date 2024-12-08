package com.example.rest;

import java.util.HashMap;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.example.controller.dao.services.HistorialServices;


@Path("history")
public class HistorialApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        HashMap<String, Object> map = new HashMap<>();
        HistorialServices hs = new HistorialServices();
        try {
            map.put("msg", "OK");
            map.put("data", hs.listAll().toArray());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "ERROR");
            map.put("data", e.getMessage());
        }
        return Response.ok(map).build();
    }
}