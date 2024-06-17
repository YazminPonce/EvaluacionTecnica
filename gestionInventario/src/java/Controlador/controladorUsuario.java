/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import BD.ConexionMySQL;
import Modelo.Rol;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author yazmi
 */
public class controladorUsuario {
     public Usuario busquedaUsuario(String nombre) throws SQLException {

        String sql = "SELECT * FROM usuarios WHERE nombre= ?";

         ConexionMySQL connMySQL = new ConexionMySQL();

        Connection conn = connMySQL.open();

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, nombre);

        Usuario u = null;

        ResultSet rs = pstmt.executeQuery();
        if (rs == null) {
            u = null;
        } else {
            if (rs.next()) {
                u = (fill(rs));
            }
        }
        rs.close();
        pstmt.close();
        connMySQL.close();
        return u;
    }
     
     
    private Usuario fill(ResultSet rs) throws SQLException {

        Usuario u = new Usuario();
        Rol  r= new Rol();
        u.setIdUsuario(rs.getInt("idUsuario"));
        u.setCorreo(rs.getString("correo"));
        u.setNombre(rs.getString("nombre"));
        u.setContrasenia(rs.getString("contrasena"));
        u.setEstatus(rs.getInt("estatus"));
        
        r.setIdRol(rs.getInt("idRol"));
        u.setRol(r);


        return u;
    }
}
