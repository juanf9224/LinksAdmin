/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Brey
 */

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {

    public String db = "linksadmin";
    public String url = "jdbc:mysql://localhost/"+ db;
    public String user = "root";
    public String pass = "root";
    public final String success = "Conexion exitosa a la BD! :)";
    public final String failure = "No se ha podido conectar a la BD! Error: ";


    
    
    public Connection testConnection(){
           Connection link = null;
        
       try{

           Class.forName("com.mysql.jdbc.Driver");

           link = DriverManager.getConnection(this.url, this.user, this.pass);
           
           JOptionPane.showMessageDialog(null, success);
                   
       }catch(ClassNotFoundException | SQLException ex){

           JOptionPane.showMessageDialog(null, failure + ex);

       }


       return link;
       }
    
    
   public Connection Conectar(){
       
        Connection link = null;
        
       try{

           Class.forName("com.mysql.jdbc.Driver");

           link = DriverManager.getConnection(this.url, this.user, this.pass);
           
       }catch(ClassNotFoundException | SQLException ex){

           JOptionPane.showMessageDialog(null, failure + ex);

       }


       return link;

   }

}
