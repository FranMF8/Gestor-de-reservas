/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import model.Booking;

/**
 *
 * @author cejit
 */
public interface IReservable {
    public void insertBooking(Booking r);
    public void updateBooking(Booking newBooking);
    public void removeBooking(Booking toDeleteBooking);
    public void listBookings();
}
