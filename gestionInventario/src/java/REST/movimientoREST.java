/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package REST;

import Controlador.controladorsMovimiento;
import Modelo.Movimientos;
import com.google.gson.Gson;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author yazmi
 */
@Path("historial")

public class movimientoREST {
    
    @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {

        String out = null;
        controladorsMovimiento c= new controladorsMovimiento();
        List<Movimientos> movimientos = null;

          try {

            movimientos = c.getAll();
            out = new Gson().toJson(movimientos);

             }catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception\":\"Error interno del servidor.\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }

        
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
