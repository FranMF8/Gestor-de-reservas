/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import model.Tools.ColorHandler;

public class vTimeline extends javax.swing.JFrame {
    
    JPanel[][] gridRows = new JPanel[7][]; //Each row contains every column
    ColorHandler colorGenerator = new ColorHandler();
    
    public vTimeline() {
        initComponents();       
        
        Grid.setLayout(new GridLayout(7, 12));
        PaintGrid();       
    }
    
    //Methods
    
    private void PaintGrid() {
        int j = 0;
        for (JPanel[] column : gridRows) {
            int i = 0;
            boolean paintWhite = false;
            if (j == 0) {
                paintWhite = true;
            }
            column = new JPanel[11];
             Color rndmClr = colorGenerator.generateRandomColor();
            
            for (JPanel row : column) {  
                
                row = new JPanel();
                Border blackline = BorderFactory.createLineBorder(Color.black);                         
                
                if (i == 0 || paintWhite) {
                    row.setBackground(colorGenerator.createColor(255, 255, 255));
                    row.setBorder(blackline);
                } else {
                    row.setBackground(rndmClr);   
                }
                Grid.add(row);
                i++;
            }
            j++;
        } 
    }
    
    @SuppressWarnings("unchecked")
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
