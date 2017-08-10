package com.segvek.terminal.gui.tab;

import com.segvek.terminal.gui.DialogAddContractContent;
import com.segvek.terminal.gui.MainFrame;
import com.segvek.terminal.gui.image.ImageHelper;
import com.segvek.terminal.model.Client;
import com.segvek.terminal.model.ContentContract;
import com.segvek.terminal.model.Contract;
import com.segvek.terminal.service.ClientService;
import com.segvek.terminal.service.ContractService;
import com.segvek.terminal.service.ServiceException;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PanelContract extends Tab {
    ContractService contractService = new ContractService();
    ClientService clientService = new ClientService();
    
    private Contract contract;
    private boolean save = true;
    private boolean init = true;

    public PanelContract() {
        initComponents();
        contract = Contract.newInstance();
        initComboClients();
        init = false;
    }
    public PanelContract(Contract c) {
        initComponents();
        contract = c;
        initTableContentContract();
        fieldNumber.setText(contract.getNumber());
        GregorianCalendar beginCalendar = new GregorianCalendar();
        beginCalendar.setTime(contract.getBeginDate());
        beginDateField.setSelectedDate(beginCalendar);
        GregorianCalendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(contract.getEndDate());
        endDateField.setSelectedDate(endCalendar);
        initComboClients();
        clients.setSelectedItem(contract.getClient());
        init = false;
    }
    
    private void initTableContentContract() {
        DefaultTableModel dtm = (DefaultTableModel) tableContentContract.getModel();
        dtm.setRowCount(0);
        for (ContentContract cc : contract.getContent()) {
            dtm.addRow(new Object[]{cc, cc.getVolume()});
        }
    }

    private void initComboClients() {
        try {
            clients.removeAllItems();
            for (Client c : clientService.getAllClients()) {
                clients.addItem(c);
            }
        } catch (ServiceException ex) {
            Logger.getLogger(PanelContract.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        beginDateField = new datechooser.beans.DateChooserCombo();
        jLabel3 = new javax.swing.JLabel();
        endDateField = new datechooser.beans.DateChooserCombo();
        clients = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        fieldNumber = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableContentContract = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablePlanAdmission = new javax.swing.JTable();
        btnAdd1 = new javax.swing.JButton();
        btnDel1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setText("Клиент:");

        jLabel2.setText("Дата начала:");

        beginDateField.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);
        beginDateField.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
            public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
                beginDateFieldOnSelectionChange(evt);
            }
        });

        jLabel3.setText("Дата окончания:");

        endDateField.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);
        endDateField.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
            public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
                endDateFieldOnSelectionChange(evt);
            }
        });

        clients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientsActionPerformed(evt);
            }
        });

        jLabel4.setText("Номер:");

        fieldNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldNumberKeyReleased(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Содержание договора", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel1.setOpaque(false);

        tableContentContract.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Груз", "Колличество"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableContentContract);

        btnAdd.setIcon(ImageHelper.loadImage("plus.png")
        );
        btnAdd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnAdd.setFocusable(false);
        btnAdd.setMaximumSize(new java.awt.Dimension(25, 25));
        btnAdd.setMinimumSize(new java.awt.Dimension(25, 25));
        btnAdd.setPreferredSize(new java.awt.Dimension(25, 25));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDel.setIcon(ImageHelper.loadImage("delete.png"));
        btnDel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnDel.setFocusable(false);
        btnDel.setMaximumSize(new java.awt.Dimension(25, 25));
        btnDel.setMinimumSize(new java.awt.Dimension(25, 25));
        btnDel.setPreferredSize(new java.awt.Dimension(25, 25));
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        tablePlanAdmission.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tablePlanAdmission);

        btnAdd1.setIcon(ImageHelper.loadImage("plus.png"));
        btnAdd1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnAdd1.setFocusable(false);
        btnAdd1.setMaximumSize(new java.awt.Dimension(25, 25));
        btnAdd1.setMinimumSize(new java.awt.Dimension(25, 25));
        btnAdd1.setPreferredSize(new java.awt.Dimension(25, 25));
        btnAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd1ActionPerformed(evt);
            }
        });

        btnDel1.setIcon(ImageHelper.loadImage("delete.png"));
        btnDel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnDel1.setFocusable(false);
        btnDel1.setMaximumSize(new java.awt.Dimension(25, 25));
        btnDel1.setMinimumSize(new java.awt.Dimension(25, 25));
        btnDel1.setPreferredSize(new java.awt.Dimension(25, 25));
        btnDel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDel1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAdd1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("План завоза", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 549, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 449, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("План отгрузки", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(clients, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(beginDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(endDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fieldNumber))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(fieldNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(clients, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(beginDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(endDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        DialogAddContractContent d = new DialogAddContractContent(MainFrame.getInstance(), true);
        if (d.showDialog()) {
            ContentContract cc = d.getResult();
            cc.setContract(contract);
            contract.addContentElement(cc);
            initTableContentContract();
        }
        editPanel();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        int rows[] = tableContentContract.getSelectedRows();
        if (rows.length <= 0) {
            return;
        }
        for (int i = 0; i < rows.length; i++) {
            ContentContract cc = (ContentContract) tableContentContract.getValueAt(rows[i], 0);
            contract.deleteElementContract(cc);
        }
        initTableContentContract();
        editPanel();
    }//GEN-LAST:event_btnDelActionPerformed

    private void fieldNumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldNumberKeyReleased
        contract.setNumber(fieldNumber.getText());
        editPanel();
    }//GEN-LAST:event_fieldNumberKeyReleased

    private void beginDateFieldOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_beginDateFieldOnSelectionChange
        contract.setBeginDate(beginDateField.getCurrent().getTime());
        editPanel();
    }//GEN-LAST:event_beginDateFieldOnSelectionChange

    private void endDateFieldOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_endDateFieldOnSelectionChange
        contract.setEndDate(endDateField.getCurrent().getTime());
        editPanel();
    }//GEN-LAST:event_endDateFieldOnSelectionChange

    private void clientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientsActionPerformed
        if(clients.getSelectedItem()!=null && !init){
            contract.setClient((Client) clients.getSelectedItem());
            System.out.println(contract.getClient());
            editPanel();
        }
    }//GEN-LAST:event_clientsActionPerformed

    private void btnAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdd1ActionPerformed

    private void btnDel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDel1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDel1ActionPerformed

    private void editPanel() {
        if (!init) {
            save = false;
            MainFrame.getInstance().initInstrumentPanel();
        }
    }
    
    
     @Override
    public boolean isNeedSave() {
        return !save;
    }

    @Override
    public void save() {
        try {
            contractService.saveContract(contract);
            save = true;
            MainFrame.getInstance().initInstrumentPanel();
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(clients, e.getMessage());
        }
    }

    @Override
    public String getName() {
        if (contract.getId() == -1L) {
            return "Договор (Новый)";
        } else {
            return "Договор №" + contract.getNumber();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo beginDateField;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAdd1;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnDel1;
    private javax.swing.JComboBox<Client> clients;
    private datechooser.beans.DateChooserCombo endDateField;
    private javax.swing.JTextField fieldNumber;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tableContentContract;
    private javax.swing.JTable tablePlanAdmission;
    // End of variables declaration//GEN-END:variables

}