/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Connection.Conexion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Booking;
import model.Tools.DateHandler;

/**
 *
 * @author cejit
 */
public class daoBooking {
    
    private Conexion con;
    
    
    public daoBooking() {
        con = new Conexion();
    }
    
    public boolean insertBooking(Booking reserva) {
        
        PreparedStatement ps = null;
        DateHandler gestor = new DateHandler();
        try {
            ps = con.Connect().prepareStatement("INSERT INTO Reservas VALUES(null,?,?,?,?)");
            ps.setString(1, reserva.GetNombre());
            ps.setInt(2, 1);
            ps.setString(3, gestor.formatFromLDT(reserva.GetHora()));
            ps.setInt(4, reserva.GetPersonas());
            ps.executeUpdate();
            con.Disconnect();
            return true;          
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteBooking(int id) {
        
        PreparedStatement ps = null;
        try {
            ps = con.Connect().prepareStatement("DELETE FROM Reservas WHERE ID=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            con.Disconnect();
            return true;          
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateBooking(Booking reserva) {
        
        PreparedStatement ps = null;
        DateHandler gestor = new DateHandler();
        try {
            ps = con.Connect().prepareStatement("UPDATE Reservas SET Nombre=?, Hora =?, Personas=? WHERE id=?");
            ps.setString(1, reserva.GetNombre());
            ps.setString(2, gestor.formatFromLDT(reserva.GetHora()));
            ps.setInt(3, reserva.GetPersonas());
            ps.setInt(4, reserva.GetID());
            ps.executeUpdate();
            con.Disconnect();
            return true;          
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<Booking> consultBookings() {
        ArrayList<Booking> lista = new ArrayList<Booking>();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        DateHandler gestor = new DateHandler();
        
        try {
            ps = con.Connect().prepareStatement("SELECT * FROM Reservas");
            rs = ps.executeQuery();
            
            while(rs.next()) {
                Booking reserva = new Booking(
                rs.getInt("ID"),
                rs.getString("Nombre"),
                rs.getInt("Mesa"),
                gestor.formatFromString(rs.getString("Hora")),
                rs.getInt("Personas")
                );
                lista.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        con.Disconnect();
        return lista;
    }
}
