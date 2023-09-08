/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Tools;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author cejit
 */
public class DateHandler {
    
    private DateTimeFormatter stringFormatter;
    private DateTimeFormatter hourFormatter;
    private DateTimeFormatter minutesFormatter;
    
    public DateHandler() {
        this.stringFormatter = DateTimeFormatter.ofPattern("HH:mm");
        this.hourFormatter = DateTimeFormatter.ofPattern("HH");
        this.minutesFormatter = DateTimeFormatter.ofPattern("mm");
    }
    
    public String formatFromLDT(LocalTime hour) {
        String formatedHour = hour.format(stringFormatter);
        
        return formatedHour;
    }
    
    public LocalTime formatFromString(String hour) {
        LocalTime formatedHour = LocalTime.parse(hour, stringFormatter);
        
        return formatedHour;
    }
    
    public int getHourFrom(LocalTime hour) {
        String formatedHour = hour.format(hourFormatter);
        int toReturnHour = Integer.parseInt(formatedHour);
        
        return toReturnHour;
    }
    
    public int getMinutesFrom(LocalTime hour) {
        String formatedHour = hour.format(minutesFormatter);
        int toReturnMinutes = Integer.parseInt(formatedHour);
        
        return toReturnMinutes;
    }
}
