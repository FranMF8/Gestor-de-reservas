/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.daoBooking;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import model.Booking;
import model.Tables.Table;
import model.Tools.BookingHandler;
import model.Tools.ColorHandler;

public class vTimeline extends javax.swing.JFrame {
    
    JPanel[][] gridRows; 
    ColorHandler colorGenerator;
    private ArrayList<Table> listaMesas;
    private ArrayList<Table> dummyTableList;
    private ArrayList<Booking> listaReservas;
    private daoBooking dao;
    BookingHandler gestorReservas;
    
       
    public vTimeline() {
        initComponents();    
        
        gridRows = new JPanel[7][];//Each row contains every column
        colorGenerator = new ColorHandler();
        listaMesas = new ArrayList<Table>();
        dummyTableList = new ArrayList<Table>();
        listaReservas = new ArrayList<Booking>();
        dao = new daoBooking();
        gestorReservas = new BookingHandler();
        
        
        updateBookings();
        Grid.setLayout(new GridLayout(7, 12));
        PaintGrid();       
    }
    
    //Methods
    
    private void PaintGrid() {
        int rowIndex = 0;
        boolean painted = false;
        for (JPanel[] row : gridRows) {
            int columnIndex = 0;
            boolean isfirstRow = false;
            if (rowIndex == 0) {
                
                isfirstRow = true;
            }
            row = new JPanel[12];
             Color rndmClr = colorGenerator.generateRandomColor();
             int max = 0;
            for (JPanel column : row) {  
                
                column = new JPanel();
                Border blackline = BorderFactory.createLineBorder(Color.black);                         
                
                if (columnIndex == 0 || isfirstRow) {
                    column.setBackground(colorGenerator.createColor(255, 255, 255));
                    column.setBorder(blackline);
                    
                    if (isfirstRow) {
                        addHourLabels(column, columnIndex);
                    } else {
                        JLabel table = new JLabel();
                        table.setText("Mesa " + rowIndex);                   
                        column.setLayout(new GridBagLayout());
                        column.add(table, new GridBagConstraints());
                    }
                } else {
                    painted = paintBookings(rndmClr, column, columnIndex, rowIndex);

                    if (max != 0) {
                        painted = true;
                    } 
                    
                    if (painted) {
                        if (max < 4) {                         
                            column.setBackground(rndmClr);
                            max++;
                        } else {
                            painted = false;
                            max = 0;
                        }                 
                    }
                }
                Grid.add(column);
                columnIndex++;
            }
            rowIndex++;
        } 
    }
    
    private void handleBookings() {
        dummyTableList = new ArrayList<Table>();
        
        this.insertTablesIntoDummyList();    
        
        
        listaMesas = gestorReservas.insertBookingsIntoTables(dummyTableList, listaReservas);      
    }
    
    private void insertTablesIntoDummyList() {
        this.dummyTableList.add(new Table(2, 1));
        this.dummyTableList.add(new Table(2, 2));
        this.dummyTableList.add(new Table(4, 3));
        this.dummyTableList.add(new Table(4, 4));
        this.dummyTableList.add(new Table(6, 5));
        this.dummyTableList.add(new Table(6, 6));
    }
    
    private void updateBookings() {
        listaReservas = dao.consultBookings();
        this.handleBookings();
    }
    
    private boolean paintBookings(Color clr, JPanel panel, int columnIndex, int rowIndex) {
        for(Table t : listaMesas) {
            for(Booking b : t.bookings) {
                if (!b.equals(null)) {             
                    
                if (b.GetMesa() == rowIndex && gestorReservas.getHourIndex(b) == columnIndex) {

                    panel.setBackground(clr);                 
                    return true;
                }
                }            
            }
        }
        panel.setBackground(colorGenerator.createColor(255, 255, 255));  
        return false;
    }
    
    private void addHourLabels(JPanel row, int i) {
        JLabel hour = new JLabel();
        if (i == 1) {
            hour.setText("19:00");
        } else if (i == 2) {
            hour.setText("19:30");
        } else if (i == 3) {
            hour.setText("20:00");
        } else if (i == 4) {
            hour.setText("20:30");
        } else if (i == 5) {
            hour.setText("21:00");
        } else if (i == 6) {
            hour.setText("21:30");
        } else if (i == 7) {
            hour.setText("22:00");
        } else if (i == 8) {
            hour.setText("22:30");
        } else if (i == 9) {
            hour.setText("23:00");
        } else if (i == 10) {
            hour.setText("23:30");
        } else if (i == 11) {
            hour.setText("00:00");           
        }  
        row.setLayout(new GridBagLayout());
        row.add(hour, new GridBagConstraints());
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Grid = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout GridLayout = new javax.swing.GroupLayout(Grid);
        Grid.setLayout(GridLayout);
        GridLayout.setHorizontalGroup(
            GridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        GridLayout.setVerticalGroup(
            GridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Grid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Grid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vTimeline().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Grid;
    // End of variables declaration//GEN-END:variables
}
