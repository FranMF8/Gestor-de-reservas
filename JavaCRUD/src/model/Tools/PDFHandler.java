/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Tools;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cejit
 */
public class PDFHandler {
    
    public void CreatePDF(int id) {
        
        try {
            Document document = new Document();
            
            String route = "Receipt" + id + ".pdf";
            
            PdfWriter.getInstance(document, new FileOutputStream(route));
            
            document.open();
            
            Phrase p = new Phrase("Reserva test nro: " + id);
            document.add(p);
            
            document.close();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(PDFHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
