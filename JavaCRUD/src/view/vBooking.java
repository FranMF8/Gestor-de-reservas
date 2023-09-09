/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.daoBooking;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Booking;
import model.Tables.Table;
import model.Tools.DateHandler;
import model.Tools.DecisionTree.BinaryTree;

/**
 *
 * @author cejit
 */
public class vBooking extends javax.swing.JFrame {

    private daoBooking dao;
    private ArrayList<Booking> lista;
    private int fila = -1;
    private Booking selectedReserva;
    private DateHandler gestor;
    private Comparator<Table> comparador;
    private BinaryTree<Table> mesas;
    private DefaultTableModel model;
    
    
    private void insertTables() {
        mesas.insertNode(new Table(2, 1));
        mesas.insertNode(new Table(2, 2));
        mesas.insertNode(new Table(4, 3));
        mesas.insertNode(new Table(4, 4));     
        mesas.insertNode(new Table(6, 5));
        mesas.insertNode(new Table(6, 6));
    }
    
    public void cleanInputs() {
        NameTextField.setText("");
        PeopleInput.setValue(1);
        IDOutputLabel.setText("");
        HourInput.setSelectedIndex(0);
        AddButton.setEnabled(true);
        DeleteButton.setEnabled(false);
        UpdateButton.setEnabled(false);
        CancelButton.setEnabled(false);
        this.updateTable();
    }
    
    public void updateTable() {    
        while(model.getRowCount()>0) {
            model.removeRow(0);
        }
        lista = dao.consultBookings();
        
        for(Booking r: lista) {
            Object reserva[] = new Object[5];
            reserva[0] = r.GetID();
            reserva[1] = r.GetNombre();
            reserva[2] = r.GetMesa();
            reserva[3] = r.GetHora();
            reserva[4] = r.GetPersonas();
            
            model.addRow(reserva);
        }
        BookingsTable.setModel(model);
    }
    
    public void createTableModel() {
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Mesa");
        model.addColumn("Hora");
        model.addColumn("Personas");
    }
    
    public int returnTableID(List<Table> mesasFiltradas, Booking r) {
        
        for(Table m: mesasFiltradas) {
            if (m.checkTableState(r)) {
                Table newMesa = m;
                newMesa.insertBooking(r);
                mesas.replaceNodeValue(m, newMesa);
                return m.getID();
            }
        }
        
        return 0;
    }
    
    
    /**
     * Creates new form vBooking
     */
    
    public vBooking() {
        initComponents();
        
        this.dao = new daoBooking();
        this.selectedReserva = new Booking();
        this.gestor = new DateHandler();
        this.comparador = Comparator.naturalOrder();
        this.mesas = new BinaryTree<Table>(this.comparador);
        this.model = new DefaultTableModel() {
        
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        setLocationRelativeTo(null);
        setTitle("Gestor de Reservas");
        
        
        this.createTableModel();
        this.updateTable();
        this.cleanInputs();
        this.insertTables();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        IDLabel = new javax.swing.JLabel();
        IDOutputLabel = new javax.swing.JLabel();
        NameLabel = new javax.swing.JLabel();
        NameTextField = new javax.swing.JTextField();
        HourLabel = new javax.swing.JLabel();
        HourInput = new javax.swing.JComboBox<>();
        PeopleLabel = new javax.swing.JLabel();
        PeopleInput = new javax.swing.JSpinner();
        CancelButton = new javax.swing.JButton();
        UpdateButton = new javax.swing.JButton();
        DeleteButton = new javax.swing.JButton();
        AddButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        BookingsTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        IDLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        IDLabel.setText("ID:");

        IDOutputLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        NameLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NameLabel.setText("Nombre:");

        HourLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        HourLabel.setText("Hora:");

        HourInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30", "00:00" }));
        HourInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HourInputActionPerformed(evt);
            }
        });

        PeopleLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        PeopleLabel.setText("Personas:");

        PeopleInput.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        CancelButton.setText("Cancelar");
        CancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        UpdateButton.setText("Actualizar");
        UpdateButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        UpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateButtonActionPerformed(evt);
            }
        });

        DeleteButton.setText("Eliminar");
        DeleteButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonActionPerformed(evt);
            }
        });

        AddButton.setText("Agregar");
        AddButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddButtonActionPerformed(evt);
            }
        });

        BookingsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        BookingsTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BookingsTable.getTableHeader().setReorderingAllowed(false);
        BookingsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BookingsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(BookingsTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(UpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(AddButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(HourLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PeopleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(PeopleInput, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                            .addComponent(NameTextField)
                            .addComponent(IDOutputLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(HourInput, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IDOutputLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(HourLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(HourInput))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PeopleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PeopleInput, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UpdateButton)
                    .addComponent(DeleteButton)
                    .addComponent(CancelButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateButtonActionPerformed
              
        try {
            if (NameTextField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Campos vacios.");
                return;
            }
            
            String nombre = NameTextField.getText();
            LocalTime hora = gestor.formatFromString( (String) HourInput.getSelectedItem());
            int personas = (Integer) PeopleInput.getValue();
            
            List<Table> mesasFiltradas = mesas.returnBiggerThan(personas);
            
            int mesa = this.returnTableID(mesasFiltradas, selectedReserva);
            
            if (mesa == 0) {
                JOptionPane.showMessageDialog(null, "No fue posible actualizar la reserva");
                return;
            }
            
            selectedReserva.SetNombre(nombre);
            selectedReserva.SetHora(hora);
            selectedReserva.SetPersonas(personas);
            selectedReserva.SetMesa(mesa);
            
            if (dao.updateBooking(this.selectedReserva)) {
                this.updateTable();
                JOptionPane.showMessageDialog(null, "Reserva actualizada correctamente.");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR");
        }
        
        this.cleanInputs();
    }//GEN-LAST:event_UpdateButtonActionPerformed

    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonActionPerformed
        try {
            int option = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar esta reserva?", "Eliminar reserva", JOptionPane.YES_NO_OPTION);
            
            if (option == 0) {
                
                if (!IDOutputLabel.getText().equals("")) {
                    int toSendDelete = Integer.parseInt(IDOutputLabel.getText());
            
                    this.dao.deleteBooking(toSendDelete);
    
                    JOptionPane.showMessageDialog(null, "Eliminado correctamente.");
            
                    this.updateTable();
                    this.cleanInputs();
            
            } else {
                JOptionPane.showMessageDialog(null, "No puedes eliminar un usuario vacio.");
                }
            }       
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR");
        }
    }//GEN-LAST:event_DeleteButtonActionPerformed

    private void AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonActionPerformed
        try {
            if (NameTextField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Campos vacios.");
                return;
            }
            
            Booking reserva = new Booking();
            String nombre = NameTextField.getText();
            String horaString = (String) HourInput.getSelectedItem();
            LocalTime hora = gestor.formatFromString(horaString);
            int personas = (Integer) PeopleInput.getValue();
            
            reserva.SetNombre(nombre);
            reserva.SetHora(hora);
            reserva.SetPersonas(personas);
            
            List<Table> mesasFiltradas = mesas.returnBiggerThan(personas);
            Collections.sort(mesasFiltradas);
            
            int mesa = this.returnTableID(mesasFiltradas, reserva);
            
            if (mesa == 0) {
                JOptionPane.showMessageDialog(null, "La reserva no es posible.");          
                return;
            }         
            
            reserva.SetMesa(mesa);
            
            if (dao.insertBooking(reserva)) {
                this.updateTable();
                JOptionPane.showMessageDialog(null, "Agregado correctamente.");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR");
            e.printStackTrace();
        }
        
        
    }//GEN-LAST:event_AddButtonActionPerformed

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        this.cleanInputs();
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void BookingsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BookingsTableMouseClicked
        fila = BookingsTable.getSelectedRow();
        
        this.selectedReserva = lista.get(fila);
        
        IDOutputLabel.setText("" + selectedReserva.GetID());
        NameTextField.setText(selectedReserva.GetNombre());
        PeopleInput.setValue(selectedReserva.GetPersonas());
        HourInput.setSelectedItem( (String) gestor.formatFromLDT(selectedReserva.GetHora()));
        
        AddButton.setEnabled(false);
        DeleteButton.setEnabled(true);
        UpdateButton.setEnabled(true);
        CancelButton.setEnabled(true);
    }//GEN-LAST:event_BookingsTableMouseClicked

    private void HourInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HourInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HourInputActionPerformed
    
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
            java.util.logging.Logger.getLogger(vBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vBooking().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JTable BookingsTable;
    private javax.swing.JButton CancelButton;
    private javax.swing.JButton DeleteButton;
    private javax.swing.JComboBox<String> HourInput;
    private javax.swing.JLabel HourLabel;
    private javax.swing.JLabel IDLabel;
    private javax.swing.JLabel IDOutputLabel;
    private javax.swing.JLabel NameLabel;
    private javax.swing.JTextField NameTextField;
    private javax.swing.JSpinner PeopleInput;
    private javax.swing.JLabel PeopleLabel;
    private javax.swing.JButton UpdateButton;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
