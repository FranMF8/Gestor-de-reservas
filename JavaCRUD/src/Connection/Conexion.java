/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author cejit
 */
public class Conexion {
    public Connection con = null;
    
    public Connection Connect() {
        
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:db_bookings.db");
            System.out.print("Conexion exitosa. \n");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }   
        return con;
    }
    
    public void Disconnect() {
        try {
            this.con.close();
            System.out.print("Conexion terminada.\n");
        } catch(SQLException e) {
            e.printStackTrace();
        }  
    }
    
    public static void main(String [] args) {
        Conexion con = new Conexion();
        con.Connect();
    }
}
