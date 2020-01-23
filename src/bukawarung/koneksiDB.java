/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bukawarung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class koneksiDB {
    public Connection getConnection() throws SQLException{
        Connection cnn;
        try{
            String server  = "jdbc:mysql://localhost/bukawarung";
            String drever  = "com.mysql.jdbc.Driver";
            Class.forName(drever);
        cnn = DriverManager.getConnection(server,"root","");
        return cnn;
        }catch(SQLException | ClassNotFoundException se ){
            System.out.println(se);
            return null;
        }
    }
}
