/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import model.Tools.DateHandler;

/**
 *
 * @author cejit
 */
public class Booking {
    private int ID;
    private String nombre;
    private int mesa;
    private LocalTime hora;
    private int personas;
    
    public DateHandler gestorDeFechas;
    
    public Booking(int id, String name, int table, LocalTime hour, int people) {
        this.ID = id;
        this.nombre = name;
        this.mesa = table;
        this.hora = hour;
        this.personas = people;
    }
    
    public Booking() {
        
    }
    ///////////Getters & Setters///////////
    
    ///////////ID///////////
    public int GetID() {
        return this.ID;
    }
    public void SetID(int id) {
        this.ID = id;
    }
    ////////nombre////////
    public String GetNombre() {
        return this.nombre;
    }
    public void SetNombre(String name) {
        this.nombre = name;
    }
    /////////mesa/////////
    public int GetMesa() {
        return this.mesa;
    }
    public void SetMesa(int table) {
        this.mesa = table;
    }
    /////////hora/////////
    public LocalTime GetHora() {
        return this.hora;
    }
    public void SetHora(LocalTime hour) {
        this.hora = hour;
    }
    public String GetStringHour() {
        return this.gestorDeFechas.formatFromLDT(this.hora);
    }
    ///////personas///////
    public int GetPersonas() {
        return this.personas;
    }
    public void SetPersonas(int people) {
        this.personas = people;
    }
    /////////////////////
    
    ///////////Methods///////////
    
    
}
