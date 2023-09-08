/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Tables;

import java.util.ArrayList;
import model.Booking;
import model.Tools.DateHandler;

/**
 *
 * @author cejit
 */
public class Table {
    
    private int ID;
    private String state;
    private int quantity;
    private ArrayList<Booking> bookings;
    
    public Table(int cantidad) {
        
        this.quantity = cantidad;
        this.bookings = new ArrayList<Booking>();
        
    }
    
    ///////////Getters & Setters///////////
    
    ////////////////ID////////////////
    
    public int getID() {
        return this.ID;
    }
    
    public void setID(int id) {
        this.ID = id;
    }
    
    //////////////State//////////////
    
    public String getState() {
        return this.state;
    }
    
    public void setState(String estado) {
        this.state = estado;
    }
    
    ////////////Quantity////////////
    
    public int getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(int cantidad) {
        this.quantity = cantidad;
    }
    
    private boolean equalsZero(int n) {
        
        if (n == 00) {
            return true;
        }
        
        return false;
    }
    
    public boolean checkTableState(Booking reserva) {
        
        DateHandler gestor = new DateHandler();
        
        int hour = gestor.getHourFrom(reserva.GetHora());
        int minutes = gestor.getMinutesFrom(reserva.GetHora());
        
        boolean reservaEnPunto = this.equalsZero(minutes);
        
        for(Booking r : this.bookings) {
            
            int rHour = gestor.getHourFrom(r.GetHora());
            int rMinutes = gestor.getMinutesFrom(r.GetHora());
            
            boolean reservaGuardadaEnPunto = this.equalsZero(rMinutes);
            
            if ( (reservaEnPunto && reservaGuardadaEnPunto) || (!reservaEnPunto && !reservaGuardadaEnPunto)) {               
                if (hour - 1 != rHour && hour + 1 != rHour && hour != rHour) {
                    return true;
                }             
            }
            
            if (reservaEnPunto && !reservaGuardadaEnPunto) {
                if (hour - 2 != rHour && hour - 1 != rHour && hour + 1 != rHour && hour != rHour) {
                    return true;
                }      
            }
            
            if (!reservaEnPunto && reservaGuardadaEnPunto) {
                if (hour - 1 != rHour && hour + 1 != rHour && hour + 2 != rHour && hour != rHour) {
                    return true;
                }      
            }
        }
        
        return false;
    }
    
}
