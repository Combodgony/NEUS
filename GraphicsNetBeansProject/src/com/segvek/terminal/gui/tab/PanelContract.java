package com.segvek.terminal.gui.tab;

import com.segvek.terminal.gui.DialogAddContractContent;
import com.segvek.terminal.gui.MainFrame;
import com.segvek.terminal.model.Client;
import com.segvek.terminal.model.ContentContract;
import com.segvek.terminal.model.Contract;
import com.segvek.terminal.service.ClientService;
import com.segvek.terminal.service.ContractService;
import com.segvek.terminal.service.ServiceException;
import datechooser.beans.DateChooserCombo;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PanelContract extends Tab{
    
    private PanelContractControl control;
  
    public PanelContract() {
        initComponents();
        control = new PanelContractControl();
        control.setTableContentContract(jTable1);
        control.setClients(jComboBox1);
        control.setNumberField(jTextField1);
        control.setBeginDateField(dateChooserCombo1);
        control.setEndDateField(dateChooserCombo2);
        
        control.init();
    }

    PanelContract(Contract c) {
        initComponents();
        control = new PanelContractControl(c);
        control.setTableContentContract(jTable1);
        control.setClients(jComboBox1);
        control.setNumberField(jTextField1);
        control.setBeginDateField(dateChooserCombo1);
        control.setEndDateField(dateChooserCombo2);
        control.init();
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jLabel3 = new javax.swing.JLabel();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setText("Клиент:");

        jLabel2.setText("Дата начала:");

        dateChooserCombo1.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);
        dateChooserCombo1.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
            public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
                dateChooserCombo1OnSelectionChange(evt);
            }
        });

        jLabel3.setText("Дата окончания:");

        dateChooserCombo2.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);
        dateChooserCombo2.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
            public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
                dateChooserCombo2OnSelectionChange(evt);
            }
        });

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel4.setText("Номер:");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Содержание договора", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel1.setOpaque(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/segvek/terminal/gui/image/plus.png"))); // NOI18N
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton3.setFocusable(false);
        jButton3.setMaximumSize(new java.awt.Dimension(25, 25));
        jButton3.setMinimumSize(new java.awt.Dimension(25, 25));
        jButton3.setPreferredSize(new java.awt.Dimension(25, 25));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/segvek/terminal/gui/image/delete.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton1.setFocusable(false);
        jButton1.setMaximumSize(new java.awt.Dimension(25, 25));
        jButton1.setMinimumSize(new java.awt.Dimension(25, 25));
        jButton1.setPreferredSize(new java.awt.Dimension(25, 25));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))
        );

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
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField1))))
                .addGap(0, 296, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        control.addElementContent();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        control.delElementConten();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        control.setNumber(jTextField1.getText());
    }//GEN-LAST:event_jTextField1KeyReleased

    private void dateChooserCombo1OnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dateChooserCombo1OnSelectionChange
        control.setBeginDate(dateChooserCombo1.getCurrent().getTime());
    }//GEN-LAST:event_dateChooserCombo1OnSelectionChange

    private void dateChooserCombo2OnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dateChooserCombo2OnSelectionChange
        control.setEndDate(dateChooserCombo2.getCurrent().getTime());
    }//GEN-LAST:event_dateChooserCombo2OnSelectionChange

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if(jComboBox1.getSelectedIndex()<0)
            return;
        control.setClient((Client)jComboBox1.getSelectedItem());
    }//GEN-LAST:event_jComboBox1ItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserCombo dateChooserCombo2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<Client> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    @Override
    public boolean isNeedSave() {
        return control.isNeedSave();
    }

    @Override
    public void save() {
        control.save();
    }

    @Override
    public String getName() {
        return control.getName();
    }
    
    
    
}

class PanelContractControl{
    //form elements
    private JTextField numberField;
    private DateChooserCombo beginDateField;
    private DateChooserCombo endDateField;
    private JTable tableContentContract; 
    private JComboBox<Client> clients;
    
    //logic elements
    private Contract contract;
    boolean save=true;
    boolean init=true;

    public PanelContractControl() {
        contract=new Contract(-1L, null, new Date(), new Date(), null);
    }

    PanelContractControl(Contract c) {
        contract=c;
    }
    void init() {
        initComboClients();
        if(contract.getId()!=-1L){
            initTableContentContract();
            clients.setSelectedItem(contract.getClient());
            numberField.setText(contract.getNumber());
            GregorianCalendar beginCalendar = new GregorianCalendar();
            beginCalendar.setTime(contract.getBeginDate());
            beginDateField.setSelectedDate(beginCalendar);
            GregorianCalendar endCalendar = new GregorianCalendar();
            endCalendar.setTime(contract.getEndDate());
            endDateField.setSelectedDate(endCalendar);
        }
        init=false;
    }

    private void initTableContentContract() {
        DefaultTableModel dtm = (DefaultTableModel) tableContentContract.getModel();
        dtm.setRowCount(0);
        for(ContentContract cc:contract.getContent()){
            dtm.addRow(new Object[]{cc,cc.getVolume()});
        }
    }
    private void initComboClients(){
        try {
            ClientService service = new ClientService();
            
            clients.removeAllItems();
            for(Client c:service.getAllClients()){
                clients.addItem(c);
            }
        } catch (ServiceException ex) {
            Logger.getLogger(PanelContractControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void setTableContentContract(JTable tableContentContract) {
        this.tableContentContract = tableContentContract;
    }

    public void setClients(JComboBox<Client> clients) {
        this.clients = clients;
    }
    
    
    void addElementContent(){
        DialogAddContractContent d = new DialogAddContractContent(MainFrame.getInstance(), true);
        if(d.showDialog()){
            ContentContract cc = d.getResult();
            cc.setContract(contract);
            contract.addContentElement(cc);
            initTableContentContract();
        }
        editPanel();
    }
    void delElementConten() {
        int rows[] = tableContentContract.getSelectedRows();
        if(rows.length<=0)
            return;
        for(int i=0; i<rows.length; i++){
            ContentContract cc = (ContentContract) tableContentContract.getValueAt(rows[i],0);
            contract.deleteElementContract(cc);
        }
        initTableContentContract();
        editPanel();
    }
    void setNumber(String text) {
        contract.setNumber(text);
        editPanel();
    }
    void setClient(Client client) {
        contract.setClient(client);
        editPanel();
    }
    void setBeginDate(Date time) {
        contract.setBeginDate(time);
        editPanel();
    }
    void setEndDate(Date time) {
        contract.setEndDate(time);
        editPanel();
    }

    public void setNumberField(JTextField numberField) {
        this.numberField = numberField;
    }

    public void setBeginDateField(DateChooserCombo beginDateField) {
        this.beginDateField = beginDateField;
    }

    public void setEndDateField(DateChooserCombo endDateField) {
        this.endDateField = endDateField;
    }
    
    
   
    boolean isNeedSave() {
        return !save;
    }

    void save() {
        ContractService service = new ContractService();
        try{
            service.saveContract(contract);
            save=true;
            MainFrame.getInstance().initInstrumentPanel();
        }catch(ServiceException e){
            JOptionPane.showMessageDialog(clients, e.getMessage());
        }
    }

    String getName() {
        if(contract.getId()==-1L)
            return "Договор (Новый)";
        else
            return "Договор №"+contract.getNumber();
    }

    private void editPanel(){
        if(!init){
            save=false;
            MainFrame.getInstance().initInstrumentPanel();
        }
    }

}
