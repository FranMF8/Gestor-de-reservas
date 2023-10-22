/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.daoBooking;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import model.Booking;
import model.Tools.DateHandler;
import model.Tools.HintHandler;

/**
 *
 * @author cejit
 */
public class vSearch extends javax.swing.JFrame {
    private daoBooking dao;
    private DateHandler gestorHora;
    private HintHandler gestorAtajos;
    /**
     * Creates new form vSearch
     */
    public vSearch() {
        dao = new daoBooking();
        gestorHora = new DateHandler();
        gestorAtajos = new HintHandler();
        initComponents();
        clearOutputs();
        this.setTitle("Buscar reserva");
        outputPanel.setVisible(false);
        
        idInputText.addKeyListener(new KeyAdapter() {
        @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    getByID();
                }
            }
        });
        
        Action enterAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getByID();
            }
        };
        
        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enterAction");
        this.getRootPane().getActionMap().put("enterAction", enterAction);
    }

    private void clearOutputs() {
            idOutputLabel.setText("");
            tableOutputLabel.setText("");
            nameOutputLabel.setText("");
            dateTimeOutputLabel.setText("");
            peopleOutputLabel.setText("");
            gestorAtajos.makeHintOnFocus(idInputText, "Ingrese un id...");
    }
    private void getByID() {
        Booking actualBooking = new Booking();
        actualBooking = dao.getByID(Integer.parseInt(idInputText.getText()));
        if (actualBooking == null) {
            outputPanel.setVisible(false);
            JOptionPane.showMessageDialog(null, "Reserva no encontrada");
            clearOutputs();               
        } else {
            outputPanel.setVisible(true);
            idOutputLabel.setText("" + actualBooking.GetID());
            tableOutputLabel.setText("" + actualBooking.GetMesa());
            nameOutputLabel.setText(actualBooking.GetNombre());
            dateTimeOutputLabel.setText(gestorHora.formatFromLDT(actualBooking.GetHora()));
            peopleOutputLabel.setText("" + actualBooking.GetPersonas());
        }   
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inputPanel = new javax.swing.JPanel();
        idInputLabel = new javax.swing.JLabel();
        idInputText = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        outputPanel = new javax.swing.JPanel();
        quitButton = new javax.swing.JButton();
        idLabel = new javax.swing.JLabel();
        idOutputLabel = new javax.swing.JLabel();
        tableLabel = new javax.swing.JLabel();
        tableOutputLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        nameOutputLabel = new javax.swing.JLabel();
        dateTimeLabel = new javax.swing.JLabel();
        dateTimeOutputLabel = new javax.swing.JLabel();
        peopleLabel = new javax.swing.JLabel();
        peopleOutputLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(700, 300));

        inputPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        inputPanel.setMaximumSize(new java.awt.Dimension(189, 167));
        inputPanel.setMinimumSize(new java.awt.Dimension(189, 167));
        inputPanel.setName(""); // NOI18N

        idInputLabel.setText("Ingrese ID de reserva");

        idInputText.setToolTipText("Ingrese un ID...");
        idInputText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                idInputTextFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                idInputTextFocusLost(evt);
            }
        });
        idInputText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idInputTextActionPerformed(evt);
            }
        });

        searchButton.setText("Buscar");
        searchButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inputPanelLayout = new javax.swing.GroupLayout(inputPanel);
        inputPanel.setLayout(inputPanelLayout);
        inputPanelLayout.setHorizontalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inputPanelLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(idInputLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(idInputText, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(37, 37, 37))
        );
        inputPanelLayout.setVerticalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputPanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(idInputLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(idInputText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        outputPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        quitButton.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        quitButton.setForeground(new java.awt.Color(0, 0, 0));
        quitButton.setText("X");
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });

        idLabel.setText("ID Reserva: ");

        idOutputLabel.setText("texto");

        tableLabel.setText("Mesa:");

        tableOutputLabel.setText("texto");

        nameLabel.setText("Nombre:");

        nameOutputLabel.setText("texto");

        dateTimeLabel.setText("Hora:");

        dateTimeOutputLabel.setText("texto");

        peopleLabel.setText("Personas:");

        peopleOutputLabel.setText("texto");

        javax.swing.GroupLayout outputPanelLayout = new javax.swing.GroupLayout(outputPanel);
        outputPanel.setLayout(outputPanelLayout);
        outputPanelLayout.setHorizontalGroup(
            outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(outputPanelLayout.createSequentialGroup()
                        .addGroup(outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameLabel)
                            .addComponent(dateTimeLabel)
                            .addComponent(peopleLabel))
                        .addGap(22, 22, 22)
                        .addGroup(outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(peopleOutputLabel)
                            .addComponent(nameOutputLabel)
                            .addComponent(dateTimeOutputLabel)))
                    .addGroup(outputPanelLayout.createSequentialGroup()
                        .addGroup(outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idLabel)
                            .addComponent(tableLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(outputPanelLayout.createSequentialGroup()
                                .addComponent(tableOutputLabel)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(outputPanelLayout.createSequentialGroup()
                                .addComponent(idOutputLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                                .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        outputPanelLayout.setVerticalGroup(
            outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, outputPanelLayout.createSequentialGroup()
                .addGroup(outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(outputPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idLabel)
                            .addComponent(idOutputLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(outputPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)))
                .addGroup(outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tableLabel)
                    .addComponent(tableOutputLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameOutputLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateTimeLabel)
                    .addComponent(dateTimeOutputLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(peopleLabel)
                    .addComponent(peopleOutputLabel))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(outputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void idInputTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idInputTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idInputTextActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        getByID();
    }//GEN-LAST:event_searchButtonActionPerformed

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        outputPanel.setVisible(false);
    }//GEN-LAST:event_quitButtonActionPerformed

    private void idInputTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_idInputTextFocusGained
        gestorAtajos.quitHintOnFocus(idInputText, "Ingrese un id...");
    }//GEN-LAST:event_idInputTextFocusGained

    private void idInputTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_idInputTextFocusLost
        gestorAtajos.makeHintOnFocus(idInputText, "Ingrese un id...");
    }//GEN-LAST:event_idInputTextFocusLost
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(vSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vSearch().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dateTimeLabel;
    private javax.swing.JLabel dateTimeOutputLabel;
    private javax.swing.JLabel idInputLabel;
    private javax.swing.JTextField idInputText;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel idOutputLabel;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel nameOutputLabel;
    private javax.swing.JPanel outputPanel;
    private javax.swing.JLabel peopleLabel;
    private javax.swing.JLabel peopleOutputLabel;
    private javax.swing.JButton quitButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel tableLabel;
    private javax.swing.JLabel tableOutputLabel;
    // End of variables declaration//GEN-END:variables
}
