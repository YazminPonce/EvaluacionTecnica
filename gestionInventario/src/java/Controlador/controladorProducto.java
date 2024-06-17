/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import BD.ConexionMySQL;
import Modelo.Productos;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.sql.*;


/**
 *
 * @author yazmi
 */
public class controladorProducto {
      public int insertarProducto(Productos producto, int idUsuario) throws SQLException {
        int idProducto = 0;
        String sql = "INSERT INTO productos (nombre, cantidad, estatus) VALUES (?, ?, ?)";
         String sqlMovimiento = "INSERT INTO movimientos (idUsuario, tipoMovimiento, fechaHora) VALUES (?, ?, ?)";

        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.open();
        conn.setAutoCommit(false);


        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
         PreparedStatement pstmtMovimiento = null;
        try {
            pstmt.setString(1, producto.getNombre());
            pstmt.setInt(2, 0); 
            pstmt.setInt(3, 1);  
            pstmt.executeUpdate();

            // Obtener el ID generado
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                idProducto = rs.getInt(1);
            }
            rs.close();
            
            
            pstmtMovimiento = conn.prepareStatement(sqlMovimiento);
            pstmtMovimiento.setInt(1, idUsuario);
            pstmtMovimiento.setString(2, "Entrada");
            pstmtMovimiento.setTimestamp(3,  Timestamp.valueOf(LocalDateTime.now()));

            pstmtMovimiento.executeUpdate();
            
             conn.commit();
            return idProducto;
         } catch (SQLException e) {
            // En caso de error, revertir transacción
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (pstmtMovimiento != null) {
                pstmtMovimiento.close();
            }
            if (conn != null) {
                conn.setAutoCommit(true);
                connMySQL.close();
            }
        }
    }

    
      public List<Productos> getAllP() throws Exception {
        String sql = "SELECT * FROM productos";
        ConexionMySQL connMySQL = new ConexionMySQL();

        try (
                 Connection conn = connMySQL.open();  PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            List<Productos> pro = new ArrayList<>();

            while (rs.next()) {
                pro.add(fill(rs));
            }

            return pro;
        }
    }

     
     
    private Productos fill(ResultSet rs) throws SQLException {

        Productos p = new Productos();
        
        p.setIdProductos(rs.getInt("idProducto"));
        p.setNombre(rs.getString("nombre"));
        p.setCantidad(rs.getInt("cantidad"));
        p.setEstatus(rs.getInt("estatus"));
        
      


        return p;
    }
    
       public int cambiarEstatus(int idProducto,int estatus) throws Exception {
        String sql = "UPDATE productos set estatus = ? WHERE idProducto=?;";
        ConexionMySQL connMySQL = new ConexionMySQL();

        Connection conn = connMySQL.open();

        CallableStatement cs = conn.prepareCall(sql);

        cs.setInt(1,estatus);
        cs.setInt(2, idProducto);

        cs.execute(); 

        cs.close();
        return idProducto;

    }
       
        public int cambiarCantidad(int cantidad, int idproducto) throws Exception {
        String sql = "UPDATE productos set cantidad = ? WHERE idProducto=?;";
        ConexionMySQL connMySQL = new ConexionMySQL();

        Connection conn = connMySQL.open();

        CallableStatement cs = conn.prepareCall(sql);

        cs.setInt(1,cantidad);
        cs.setInt(2, idproducto);

        cs.execute(); // Cambié cs.executeUpdate() a cs.execute()

        cs.close();
        return idproducto;

    }
        
        
public int cambiarCantidadSalida(int cantidad, int idproducto,int idUsuario) throws Exception {
        String sqlUpdateProducto = "UPDATE productos SET cantidad = ? WHERE idProducto = ?";
        String sqlInsertMovimiento = "INSERT INTO movimientos (idUsuario, tipoMovimiento, fechaHora) VALUES (?, ?, ?)";

        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.open();

        PreparedStatement pstmtUpdateProducto = null;
        PreparedStatement pstmtInsertMovimiento = null;

        try {
            // Iniciar transacción
            conn.setAutoCommit(false);

   
            pstmtUpdateProducto = conn.prepareStatement(sqlUpdateProducto);
            pstmtUpdateProducto.setInt(1, cantidad);
            pstmtUpdateProducto.setInt(2, idproducto);
            pstmtUpdateProducto.executeUpdate();

            
            
      
            pstmtInsertMovimiento = conn.prepareStatement(sqlInsertMovimiento);
            pstmtInsertMovimiento.setInt(1, idUsuario);
            pstmtInsertMovimiento.setString(2, "SALIDA");
            pstmtInsertMovimiento.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now())); 
            pstmtInsertMovimiento.executeUpdate();

           
            conn.commit();

            return idproducto;
        } catch (SQLException e) {
            // En caso de error, revertir transacción
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (pstmtUpdateProducto != null) {
                pstmtUpdateProducto.close();
            }
            if (pstmtInsertMovimiento != null) {
                pstmtInsertMovimiento.close();
            }
            if (conn != null) {
                conn.setAutoCommit(true);
                connMySQL.close();
            }
        }
    }


    
}
