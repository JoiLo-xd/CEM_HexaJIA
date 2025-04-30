/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cem.persistence;

import cem.model.Marxa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ivang
 */
public class cemDAO {
    
    private Connection conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/cem";
        String user = "root";
        String pass = "root";
        Connection c = DriverManager.getConnection(url, user, pass);
        return c;
    }
    
    private void desconectar(Connection c) throws SQLException {
        c.close();
    }
    
    public void inserMarxa(Marxa marxa) throws SQLException {
        Connection c = conectar();
        PreparedStatement ps = c.prepareStatement("insert into cliente values (?);");
        ps.setInt(1, marxa.getEdicio());
        ps.executeUpdate();
        ps.close();
        desconectar(c);
    }
    
}
