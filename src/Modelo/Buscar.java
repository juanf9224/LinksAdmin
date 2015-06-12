/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Servicio.Servicio;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Brey
 */
public class Buscar {

    Servicio srv = new Servicio();
    Conexion con = new Conexion();

    public void buscar(String fromDate, String toDate, String findPublisher) throws SQLException {

        ArrayList<Object[]> datos = new ArrayList<>();

        if (findPublisher.equals("N/A")) {
            String sql = "Select video, post_script, analytics, publisher, fecha_creacion from tabla_contenido where fecha_creacion >='" + fromDate + "' and fecha_creacion <='" + toDate + "'";
            PreparedStatement pstmt;
            int rows = 0;

            pstmt = con.Conectar().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery(sql);
            ResultSetMetaData rsm;

            try {
                con.testConnection();

                if (rs.next() == true) {
                    try {
                        pstmt = con.Conectar().prepareStatement(sql);
                        rs = pstmt.executeQuery();
                        rsm = rs.getMetaData();

                        while (rs.next()) {
                            Object[] filas = new Object[rsm.getColumnCount()];
                            for (int i = 0; i < filas.length; i++) {
                                filas[i] = rs.getObject(i + 1);

                            }
                            datos.add(filas);
                        }

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error > " + ex.toString());
                        ex.printStackTrace();
                    }
                }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, "Error de SQL > " + e.toString());
            }
        } else {
            String sql = "Select video, post_script, analytics, publisher, fecha_creacion from tabla_contenido where fecha_creacion >='" + fromDate + "' and fecha_creacion <='" + toDate + "' and publisher ='" + findPublisher + "'";
            PreparedStatement pstmt;
            int rows = 0;

            try {
                con.testConnection();
                pstmt = con.Conectar().prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery(sql);
                ResultSetMetaData rsm;

                if (rs.next() == true) {
                    try {
                        pstmt = con.Conectar().prepareStatement(sql);
                        rs = pstmt.executeQuery();
                        rsm = rs.getMetaData();
                        while (rs.next()) {
                            Object[] filas = new Object[rsm.getColumnCount()];
                            for (int i = 0; i < filas.length; i++) {
                                filas[i] = rs.getObject(i + 1);

                            }
                            datos.add(filas);
                        }

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error > " + ex.toString());
                    }
                }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, "Error de SQL > " + e.toString());
            }
        }
        srv.llenarTabla(datos);
    }
}
