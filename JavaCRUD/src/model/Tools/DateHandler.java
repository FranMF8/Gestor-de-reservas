/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Tools;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author cejit
 */
public class DateHandler {
    private DateTimeFormatter dtf;
    
    public DateHandler() {
        this.dtf = DateTimeFormatter.ofPattern("HH:mm");
    }
    
    public String formatFromLDT(LocalTime hour) {
        String formatedHour = hour.format(dtf);
        
        return formatedHour;
    }
    
    public LocalTime formatFromString(String hour) {
        LocalTime formatedHour = LocalTime.parse(hour, dtf);
        
        return formatedHour;
    }
}
