/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Tools;

import java.util.ArrayList;
import model.Booking;
import model.Tables.Table;

/**
 *
 * @author cejit
 */
public class BookingHandler {
    public ArrayList<Table> insertBookingsIntoTables(ArrayList<Table> tableList, ArrayList<Booking> bookingList) {
        ArrayList<Table> toReturnList = new ArrayList<Table>();
        
                
        for(Booking r : bookingList) {
            switch (r.GetMesa()) {
                case 1 -> {
                    System.out.println("Trying at 1");
                    tableList.get(0).updateBooking(r);
                }
                case 2 -> {
                    System.out.println("Trying at 2");
                    tableList.get(1).updateBooking(r);
                }
                case 3 -> {
                    System.out.println("Trying at 3");
                    tableList.get(2).updateBooking(r);
                }
                case 4 -> {
                    System.out.println("Trying at 4");
                    tableList.get(3).updateBooking(r);
                }
                case 5 -> {             
                    System.out.println("Trying at 5");
                    tableList.get(4).updateBooking(r);
                }
                case 6 -> {
                    System.out.println("Trying at 6");
                    tableList.get(5).updateBooking(r);
                }
                default -> {
                }
            }
        }
        
        toReturnList = tableList;
        
        System.out.println("Bookings Handled");
        
        return toReturnList;
    }
}
