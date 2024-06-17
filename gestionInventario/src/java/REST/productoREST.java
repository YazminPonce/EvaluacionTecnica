/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package REST;

import Controlador.controladorProducto;
import Modelo.Productos;
import com.google.gson.Gson;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author yazmi
 */
@Path("producto")
public class productoREST {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("guardar")
    public Response guaradar (Productos producto){
     Gson gson = new Gson();
       
       String out = null;
        controladorProducto cl= new controladorProducto();
       int p=0;
        
           try {
            p = cl.insertarProducto(producto, producto.getEstatus());
           }catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception\":\"Error interno del servidor.\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
            if(p != 0){
                out= new Gson().toJson(p);
                
            }else{
             out = "{\"error\":\"error\"}" ;
            }
        
        return Response.status(Response.Status.OK).entity(out).build();
    
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("CambiarCantidad")
    public Response CambiarCantidad (Productos producto){
     Gson gson = new Gson();
       
       String out = null;
        controladorProducto cl= new controladorProducto();
       int p=0;
        
           try {
            p = cl.cambiarCantidad(producto.getCantidad(),producto.getIdProductos());
           }catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception\":\"Error interno del servidor.\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
            if(p != 0){
                out= new Gson().toJson(p);
                
            }else{
             out = "{\"error\":\"error\"}" ;
            }
        
        return Response.status(Response.Status.OK).entity(out).build();
    
    }
    
     @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("CambiarEstatus")
    public Response CambiarEstatus (Productos producto){
     Gson gson = new Gson();
       
       String out = null;
        controladorProducto cl= new controladorProducto();
       int p=0;
        
           try {
            p = cl.cambiarEstatus(producto.getIdProductos(),producto.getEstatus());
           }catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception\":\"Error interno del servidor.\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
           
               
                out= new Gson().toJson(producto.getEstatus());
                
            
        
        return Response.status(Response.Status.OK).entity(out).build();
    
    }
    
    @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {

        String out = null;
        controladorProducto c= new controladorProducto();
        List<Productos> producto = null;

          try {

            producto = c.getAllP();
            out = new Gson().toJson(producto);

             }catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception\":\"Error interno del servidor.\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }

        
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("CambiarCantidadSalidad")
    public Response CambiarCantidadSalidad (Productos producto){
     Gson gson = new Gson();
       
       String out = null;
        controladorProducto cl= new controladorProducto();
       int p=0;
        
           try {
            p = cl.cambiarCantidadSalida(producto.getCantidad(),producto.getIdProductos(),producto.getEstatus());
           }catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception\":\"Error interno del servidor.\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
            if(p != 0){
                out= new Gson().toJson(p);
                
            }else{
             out = "{\"error\":\"error\"}" ;
            }
        
        return Response.status(Response.Status.OK).entity(out).build();
    
    }
}
