
package com.segvek.terminal.gui.tab;

import com.segvek.terminal.gui.MainFrame;
import com.segvek.terminal.model.Contract;
import com.segvek.terminal.service.ContractService;
import com.segvek.terminal.service.ServiceException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class PanelCcontractList extends Tab{
    private PanelCcontractListControl control;
    
 
    public PanelCcontractList() {
        initComponents();
        control = new PanelCcontractListControl();
        control.setTableListContract(jTable1);
        
        init();
    }

    @Override
    public void init() {
        control.init();
    }

    @Override
    public boolean isCanBeEdit() {
        return control.isCanBeEdit();
    }

    @Override
    public void edit() {
        control.edit();
    }
    
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(51, 51, 51));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Номер", "Клиент", "Дата начала", "Дата окончания"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 822, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        MainFrame.getInstance().initInstrumentPanel();
    }//GEN-LAST:event_jTable1MouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    @Override
    public boolean isCanBeAdd() {
        return true;
    }
}

class PanelCcontractListControl{
    private JTable tableListContract;
    
    void init() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tableListContract.getModel();
            dtm.setRowCount(0);
            ContractService service = new ContractService();
            for(Contract c:service.getAllContract()){
                dtm.addRow(new Object[]{c,c.getClient(),c.getBeginDate(),c.getEndDate()});
            }
        } catch (ServiceException ex) {
            Logger.getLogger(PanelCcontractListControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void setTableListContract(JTable table) {
        tableListContract=table;
    }

    boolean isCanBeEdit() {
        int row[] = tableListContract.getSelectedRows();
        return row.length>0;
    }

    void edit() {
        int row[] = tableListContract.getSelectedRows();
        if(row.length<1)
            return;
        for(int i=0; i<row.length; i++){
            Contract c = (Contract) tableListContract.getValueAt(row[i], 0);
            PanelContract pc = new PanelContract(c);
            MainFrame.getInstance().addPanelTab("Договор № "+c.getNumber()+" ", pc);
        }
    }

    
    
}
