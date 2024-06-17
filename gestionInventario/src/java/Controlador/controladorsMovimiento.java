/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import BD.ConexionMySQL;
import Modelo.Movimientos;
import Modelo.Rol;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yazmi
 */
public class controladorsMovimiento {
     public List<Movimientos> getAll() throws Exception {
        String sql = "SELECT tipoMovimiento, fechaHora, nombre, idRol FROM movimientos  inner join usuarios  on movimientos.idUsuario =usuarios.idUsuario;";
        ConexionMySQL connMySQL = new ConexionMySQL();

        try (
                 Connection conn = connMySQL.open();  PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            List<Movimientos> mo = new ArrayList<>();

            while (rs.next()) {
                mo.add(fill(rs));
            }

            return mo;
        }
    }

     
     
    private Movimientos fill(ResultSet rs) throws SQLException {

        Movimientos m = new Movimientos();
        Usuario u = new Usuario();
        Rol r= new Rol();
        m.setFechaHora(rs.getTimestamp("fechaHora"));
        m.setTipoMovimiento(rs.getString("tipoMovimiento"));
        u.setNombre(rs.getString("nombre"));
        r.setIdRol(rs.getInt("idRol"));
        u.setRol(r);
        
        m.setUsuario(u);

      


        return m;
    }
}
