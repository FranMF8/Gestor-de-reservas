/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.daoBooking;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import model.Booking;
import model.Tables.Table;
import model.Tools.BookingHandler;
import model.Tools.DateHandler;
import model.Tools.HintHandler;
import model.Tools.PDFHandler;

/**
 *
 * @author cejit
 */
public class vBooking extends javax.swing.JFrame {

    private daoBooking dao;
    private ArrayList<Booking> listaReservas;
    private int fila = -1;
    private Booking selectedReserva;
    private DateHandler gestor;
    private DefaultTableModel model;
    private ArrayList<Table> listaMesas;
    private ArrayList<Table> dummyTableList;
    private HintHandler gestorAtajos;
    private int counter;
    private PDFHandler PDFTool;
    
    
    //Inicia la vista
    public vBooking() {
        initComponents();
        this.setSize(800, 480); 
        //Inicializa los objetos
        this.dao = new daoBooking();
        this.selectedReserva = new Booking();
        this.gestor = new DateHandler();
        this.listaMesas = new ArrayList<Table>();
        this.gestorAtajos = new HintHandler();
        this.PDFTool = new PDFHandler();
        
        //Hace que las celdas de la tabla no sean editables por el usuario
        this.model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        counter = 0;
        
        setTitle("Gestor de Reservas");

        BookingsTable.addKeyListener(new KeyAdapter() {
        @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    DeleteButtonActionPerformed(null);
                }
            }
        });
        
        Action enterAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddButtonActionPerformed(null);
            }
        };
        
        NameTextField.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enterAction");
        NameTextField.getActionMap().put("enterAction", enterAction);
       
        this.createTableModel();
        this.updateTable();
        this.cleanInputs();
    }  
    
    //Gestiona las reservas insertandolas en sus respectivas mesas
    private void handleBookings() {
        dummyTableList = new ArrayList<Table>();
                
        this.insertTablesIntoDummyList();
        BookingHandler gestorReservas = new BookingHandler();
        
        listaMesas = gestorReservas.insertBookingsIntoTables(dummyTableList, listaReservas);
    }
    
    private ArrayList<Table> filterTables( ArrayList<Table> toFilterList, int value) 
    {      
        ArrayList<Table> filteredList = new ArrayList<Table>();
        
        for( Table t: toFilterList) {
            if (t.getQuantity() >= value) {
                filteredList.add(t);
            }
        }       
        return filteredList;
    }
    
    private int update(Booking reservaGuardada) {
        try {
            if (NameTextField.getText().equals("") || NameTextField.getText().equals("Ingrese nombre...")) {
                JOptionPane.showMessageDialog(null, "Campos vacios.");
                return 0;
            }
            
            Booking reserva = new Booking();
            int id = Integer.parseInt(IDOutputLabel.getText());
            String nombre = NameTextField.getText();
            String horaString = (String) HourInput.getSelectedItem();
            LocalTime hora = gestor.formatFromString(horaString);
            int personas = (Integer) PeopleInput.getValue();
            
            reserva.SetID(id);
            reserva.SetNombre(nombre);
            reserva.SetHora(hora);
            reserva.SetPersonas(personas);
            
            ArrayList<Table> mesasFiltradas = this.filterTables(listaMesas, personas);
            
            int mesa = this.replaceTableID(mesasFiltradas, reserva, id);
     
            
            if (mesa == 0) {
                if (dao.deleteBooking(id) && counter < 67) {
                    counter++;
                    this.updateTable();
                    mesa = this.returnTableID(mesasFiltradas, reserva);   
                    System.out.println("Mesa: " + mesa);
                    System.out.println();
                    this.update(reservaGuardada);
                    counter = 0;
                    return 1;
                }
                else {
                dao.deleteAndReAdd(reservaGuardada);
                this.updateTable();
                JOptionPane.showMessageDialog(null, "No fue posible actualizar la reserva.");
                counter = 0;
                return 0;
                }
            }         
            
            reserva.SetMesa(mesa);
            
            if (dao.deleteAndReAdd(reserva)) {
                this.updateTable();
                JOptionPane.showMessageDialog(null, "Reserva actualizada correctamente.");
                return 1;
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la reserva");
            e.printStackTrace();
            return 0;
        }
        return 0;
    }
    
    //Genera las mesas
    private void insertTablesIntoDummyList() {
        this.dummyTableList.add(new Table(2, 1));
        this.dummyTableList.add(new Table(2, 2));
        this.dummyTableList.add(new Table(4, 3));
        this.dummyTableList.add(new Table(4, 4));
        this.dummyTableList.add(new Table(6, 5));
        this.dummyTableList.add(new Table(6, 6));
    }

    public void cleanInputs() {
        NameTextField.setText("");
        PeopleInput.setValue(1);
        IDOutputLabel.setText("");
        TableOutputLabel.setText("");
        HourInput.setSelectedIndex(0);
        AddButton.setEnabled(true);
        DeleteButton.setEnabled(false);
        UpdateButton.setEnabled(false);
        CancelButton.setEnabled(false);

        gestorAtajos.makeHintOnFocus(NameTextField, "Ingrese nombre...");
        this.selectedReserva = new Booking();    
    }
    
    
    //Consulta las reservas en la bdd y las mete dentro de listaReservas
    public void updateTable() {    
        while(model.getRowCount()>0) {
            model.removeRow(0);
        }
        listaReservas = dao.consultBookings();
        this.handleBookings();
        for(Booking r: listaReservas) {
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
    
    //Crea el modelo para la tabla
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
                return m.getID();
            }
        }     
        return 0;
    }
    
    public int replaceTableID(List<Table> mesasFiltradas, Booking r, int id) {       
        for(Table m: mesasFiltradas) {
            if (m.checkTableState(r)) {                         
                m.changeBookingValue(id, r);
                return m.getID();
            }
        }    
        return 0;
    }    
  
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        openTimelineButton = new javax.swing.JButton();
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
        TableOutputLabel = new javax.swing.JLabel();
        TableLabel = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(600, 200));
        setSize(new java.awt.Dimension(500, 300));

        openTimelineButton.setText("Ver reservas");
        openTimelineButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        openTimelineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openTimelineButtonActionPerformed(evt);
            }
        });

        IDLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        IDLabel.setText("ID:");

        IDOutputLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        NameLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NameLabel.setText("Nombre:");

        NameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                NameTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                NameTextFieldFocusLost(evt);
            }
        });

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

        PeopleInput.setModel(new javax.swing.SpinnerNumberModel(1, 1, 6, 1));

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
        BookingsTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                BookingsTableFocusGained(evt);
            }
        });
        BookingsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BookingsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(BookingsTable);

        TableOutputLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        TableLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TableLabel.setText("Mesa:");

        searchButton.setText("Buscar reserva");
        searchButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(IDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(IDOutputLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(NameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(NameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(HourLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(HourInput, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(PeopleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PeopleInput, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(CancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(UpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(DeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TableLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TableOutputLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(openTimelineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(49, 49, 49)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(openTimelineButton, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(IDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IDOutputLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TableOutputLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TableLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(HourInput, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HourLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PeopleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PeopleInput, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(UpdateButton)
                            .addComponent(DeleteButton)
                            .addComponent(CancelButton))
                        .addGap(18, 18, 18)
                        .addComponent(AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateButtonActionPerformed
        Booking reservaGuardada = selectedReserva;
        update(reservaGuardada);

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
            if (NameTextField.getText().equals("") || NameTextField.getText().equals("Ingrese nombre...")) {
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
            
            ArrayList<Table> mesasFiltradas = this.filterTables(listaMesas, personas);
            
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
            this.cleanInputs();
            
            //this.PDFTool.CreatePDF(12);
            
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR");
            e.printStackTrace();
        }
        
        
    }//GEN-LAST:event_AddButtonActionPerformed

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        this.updateTable();   
        this.cleanInputs();
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void BookingsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BookingsTableMouseClicked
        fila = BookingsTable.getSelectedRow();
        
        this.selectedReserva = listaReservas.get(fila);       
        
        IDOutputLabel.setText("" + selectedReserva.GetID());
        TableOutputLabel.setText("" + selectedReserva.GetMesa());
        NameTextField.setText(selectedReserva.GetNombre());
        PeopleInput.setValue(selectedReserva.GetPersonas());
        HourInput.setSelectedItem( (String) gestor.formatFromLDT(selectedReserva.GetHora()));
        NameTextField.setForeground(new Color(0, 0, 0));
        
        AddButton.setEnabled(false);
        DeleteButton.setEnabled(true);
        UpdateButton.setEnabled(true);
        CancelButton.setEnabled(true);
        CancelButton.setFocusable(true);
    }//GEN-LAST:event_BookingsTableMouseClicked
   
    private void HourInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HourInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HourInputActionPerformed

    private void NameTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NameTextFieldFocusGained
        gestorAtajos.quitHintOnFocus(NameTextField, "Ingrese nombre...");
        this.CancelButton.setEnabled(true);
        this.CancelButton.setFocusPainted(true);
    }//GEN-LAST:event_NameTextFieldFocusGained

    private void NameTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NameTextFieldFocusLost
        gestorAtajos.makeHintOnFocus(NameTextField, "Ingrese nombre...");
        this.CancelButton.setFocusPainted(false);
    }//GEN-LAST:event_NameTextFieldFocusLost

    private void BookingsTableFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BookingsTableFocusGained
        
    }//GEN-LAST:event_BookingsTableFocusGained
    
    //Abre la vista de vTimeline
    private void openTimelineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openTimelineButtonActionPerformed
        vTimeline timeline = new vTimeline();
        timeline.setVisible(true);
    }//GEN-LAST:event_openTimelineButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        vSearch search = new vSearch();
        search.setVisible(true);
    }//GEN-LAST:event_searchButtonActionPerformed
    
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
                vBooking bookingFrame = new vBooking();
                bookingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                bookingFrame.setVisible(true);
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
    private javax.swing.JLabel TableLabel;
    private javax.swing.JLabel TableOutputLabel;
    private javax.swing.JButton UpdateButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton openTimelineButton;
    private javax.swing.JButton searchButton;
    // End of variables declaration//GEN-END:variables
}
