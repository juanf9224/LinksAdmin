/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.LinksAdmin;
import com.toedter.calendar.JDateChooser;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Brey
 */
public class Subir {
    public void subir(String video, String script, String analisis, String publisher, String fecha) throws SQLException{
        Conexion con = new Conexion();
        
        String sql = "insert into tabla_contenido(video, post_script, analytics, publisher, fecha_creacion) values(?,?,?,?,?)";
        PreparedStatement pstmt = null;
        
               
        try{
            con.testConnection();
            
            pstmt = con.Conectar().prepareStatement(sql);            
            pstmt.setString(1, video);
            pstmt.setString(2, script);
            pstmt.setString(3, analisis);
            pstmt.setString(4, publisher);
            pstmt.setString(5, fecha);
            pstmt.executeUpdate();
            
            pstmt.close();
            con.Conectar().close();
            JOptionPane.showMessageDialog(null, "Los datos se guardaron correctamente!");
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null,"Hubo un error al guardar los datos" + e.toString());
        }
    }
    
}
