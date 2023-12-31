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
    
    public int getHourIndex(Booking b) {
        if (b.GetStringHour().equals("19:00") ) {
            return 1;
        } else if (b.GetStringHour().equals("19:30")) {
            return 2;
        } else if (b.GetStringHour().equals("20:00")) {
            return 3;
        } else if (b.GetStringHour().equals("20:30")) {
            return 4;
        } else if (b.GetStringHour().equals("21:00")) {
            return 5;
        } else if (b.GetStringHour().equals("21:30")) {
            return 6;
        } else if (b.GetStringHour().equals("22:00")) {
            return 7;
        } else if (b.GetStringHour().equals("22:30")) {
            return 8;
        } else if (b.GetStringHour().equals("23:00")) {
            return 9;
        } else if (b.GetStringHour().equals("23:30")) {
            return 10;
        } else if (b.GetStringHour().equals("00:00")) {
            return 11;
        } else {
            return 0;
        }
    }
}
