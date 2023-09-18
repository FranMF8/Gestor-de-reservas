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
public class Table implements Comparable<Table> {
    
    private int ID;
    private String state;
    private int quantity;
    private ArrayList<Booking> bookings;
    
    public Table(int cantidad, int id) {
        
        this.ID = id;
        this.quantity = cantidad;
        this.bookings = new ArrayList<Booking>();
        this.state = "Libre";
    }
    
    public Table(int cantidad) {
       
        this.ID = 0;
        this.quantity = cantidad;
        this.bookings = new ArrayList<Booking>();
        this.state = "Libre";
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
    
    public boolean setState(int estado) {
        if (estado == 0) {
            this.state = "Libre";
            return true;
        } else if (estado == 1) {
            this.state = "Ocupada";
            return true;
        }
        
        return false;
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
    
    public void insertBooking(Booking r) {
        this.listBookings();
        this.bookings.add(r);
        System.out.println("Se a;adio la reserva con ID: " + r.GetID());
        this.listBookings();
    }
    
    public void updateBooking(Booking newBooking) {
    
        for(Booking booking: this.bookings) {
            if (newBooking.GetID() == booking.GetID()) {
                this.removeBooking(booking);
                this.bookings.add(newBooking);
                System.out.println("Se actualizo la reserva con ID: " + newBooking.GetID());
                break;
            }
        }
        
    }
    
    public void removeBooking(Booking toDeleteBooking) {
        this.bookings.remove(toDeleteBooking);
        System.out.println("Se elimino la reserva con ID: " + toDeleteBooking.GetID());
        this.listBookings();
    }
    
    public void listBookings() {
        for( Booking b: this.bookings) {
            System.out.println("ID: " + b.GetID());
            System.out.println("Nombre: " + b.GetNombre());
            System.out.println("Hora: " + b.GetHora());
            System.out.println("Personas: " + b.GetPersonas());
            System.out.println("Mesa: " + b.GetMesa());
        }
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
                if (hour - 1 == rHour || hour + 1 == rHour || hour == rHour) {
                    return false;
                }             
            }
            
            if (reservaEnPunto && !reservaGuardadaEnPunto) {
                if (hour - 2 == rHour || hour - 1 == rHour || hour + 1 == rHour || hour == rHour) {
                    return false;
                }      
            }
            
            if (!reservaEnPunto && reservaGuardadaEnPunto) {
                if (hour - 1 == rHour || hour + 1 == rHour || hour + 2 == rHour || hour == rHour) {
                    return false;
                }      
            }
        }
        
        return true;
    }
    
    @Override
    public int compareTo(Table otherTable) {
        return Integer.compare(this.quantity, otherTable.getQuantity());
    }
    
}
