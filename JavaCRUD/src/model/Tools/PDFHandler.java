/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Tools;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Booking;

/**
 *
 * @author cejit
 */
public class PDFHandler {
    
    public Boolean PrintBooking(Booking b) {
        
        try {
            Document document = new Document();
            
            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.ITALIC | Font.UNDERLINE | Font.BOLD);   
            Font stdFont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.ITALIC);
            String route = "Receipt Number " + b.GetID() + ".pdf";
            
            PdfWriter.getInstance(document, new FileOutputStream(route));
            
            document.open();
            
            
            Phrase idPhrase = new Phrase();
            idPhrase.setFont(boldFont);
            idPhrase.add("Reserva nro:");
            document.add(idPhrase);
            
            Phrase id = new Phrase();
            id.setFont(stdFont);
            id.add(" " + b.GetID() + " \n" + "\n");
            document.add(id);
            
            Phrase namePhrase = new Phrase();
            namePhrase.setFont(boldFont);
            namePhrase.add("Nombre:");
            document.add(namePhrase);
            
            Phrase name = new Phrase();
            name.setFont(stdFont);
            name.add(" " + b.GetNombre() + " \n" + "\n");
            document.add(name);
            
            Phrase tablePhrase = new Phrase();
            tablePhrase.setFont(boldFont);
            tablePhrase.add("Mesa nro:");
            document.add(tablePhrase);
            
            Phrase table = new Phrase();
            table.setFont(stdFont);
            table.add(" " + b.GetMesa() + " \n" + "\n");
            document.add(table);
            
            Phrase hourPhrase = new Phrase();
            hourPhrase.setFont(boldFont);
            hourPhrase.add("Horario:");
            document.add(hourPhrase);
            
            Phrase hour = new Phrase();
            hour.setFont(stdFont);
            hour.add(" " + b.GetStringHour()+ " \n" + "\n");
            document.add(hour);
            
            Phrase peoplePhrase = new Phrase();
            peoplePhrase.setFont(boldFont);
            peoplePhrase.add("Cant. personas:");
            document.add(peoplePhrase);
            
            Phrase people = new Phrase();
            people.setFont(stdFont);
            people.add(" " + b.GetPersonas() + " \n" + "\n");
            document.add(people);
            
            document.close();
            
            return true;
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(PDFHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
