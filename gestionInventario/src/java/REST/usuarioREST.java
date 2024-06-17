/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package REST;

import Controlador.controladorUsuario;
import com.google.gson.Gson;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import Modelo.Usuario;

/**
 *
 * @author yazmi
 */
@Path("usuario")

public class usuarioREST {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("in")
    public Response login (Usuario user){
     Gson gson = new Gson();
       
       String out = null;
        controladorUsuario cl= new controladorUsuario();
        Usuario u = null;
        
           try {
            u = cl.busquedaUsuario(user.getNombre());
           }catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception\":\"Error interno del servidor.\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
            if(u != null){
                String c=u.getContrasenia();
                String cc=user.getContrasenia();

                if( c.equals(cc)){
                    out= new Gson().toJson(u);
                
                }else{
                    out = "{\"error\":\"datos incorrectos\"}" ;
                }
                
            }else{
             out = "{\"error\":\"datos incorrectos\"}" ;
            }
        
        return Response.status(Response.Status.OK).entity(out).build();
    
    }
}
