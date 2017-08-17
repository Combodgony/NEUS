package com.segvek.terminal.gui.tab;

import com.segvek.terminal.gui.DialogAddAdmissionDependency;
import com.segvek.terminal.gui.MainFrame;
import com.segvek.terminal.gui.image.ImageHelper;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.Cargo;
import com.segvek.terminal.model.Contract;
import com.segvek.terminal.model.DependencyAdmission;
import com.segvek.terminal.model.DrainLocation;
import com.segvek.terminal.model.Tank;
import com.segvek.terminal.service.CargoService;
import com.segvek.terminal.service.ContractService;
import com.segvek.terminal.service.DependencyService;
import com.segvek.terminal.service.DrainLocationService;
import com.segvek.terminal.service.ServiceException;
import com.segvek.terminal.service.TankService;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class PanelAdmission extends Tab {

    private JSpinner.DateEditor timeEditor, timeEditor1, timeEditor2;
    private ContractService contractService = new ContractService();
    private CargoService cargoService = new CargoService();
    private TankService tankService = new TankService();
    private DrainLocationService drainLocationService = new DrainLocationService();
    private DependencyService dependencyService = new DependencyService();

    private Admission admission;

    public PanelAdmission() {
        initComponents();
        initContractComboBox();
        initCargoComboBox();
        initTankComboBox();
        initDrainLocationComboBox();
        AutoCompleteDecorator.decorate(contractComboBox);
    }

    public PanelAdmission(Admission a) {
        this.admission = a;
        initComponents();
        initContractComboBox();
        initCargoComboBox();
        initTankComboBox();
        initDrainLocationComboBox();
        initTableDependencyAdmission();

        umberField.setText(a.getId().toString());
        volumeField.setText("" + a.getVolume());
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(a.getBegin());
        planDateField.setSelectedDate(c);
        timeSpinner.setValue(a.getBegin());
        contractComboBox.setSelectedItem(a.getContract());
        cargoComboBox.setSelectedItem(a.getCargo());
        tankComboBox.setSelectedItem(a.getTank());
        drainLocationComboBox.setSelectedItem(a.getDrainLocation());
    }

    private void initDrainLocationComboBox() {
        drainLocationComboBox.removeAllItems();
        List<DrainLocation> list = null;
        try {
            list = drainLocationService.getAllDrainLocation();
        } catch (ServiceException ex) {
            Logger.getLogger(PanelAdmission.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (DrainLocation dl : list) {
            drainLocationComboBox.addItem(dl);
        }
    }

    private void initTableDependencyAdmission() {
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        try {
            List<DependencyAdmission> list = dependencyService.getDependencyAdmissionsByAdmission(admission);
            for (DependencyAdmission d : list) {
                dtm.addRow(new Object[]{d,d.getAdmission(),d.getAdmission().getBegin(),d.getIndependet(),d.getIndependet().getBegin()});
            }
        } catch (ServiceException ex) {
            Logger.getLogger(PanelAdmission.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(cargoComboBox, "Не удалось загрузить зависимости завоза");
        }
    }

    private void initTankComboBox() {
        tankComboBox.removeAllItems();
        List<Tank> list = null;
        try {
            list = tankService.getAllTank();

        } catch (ServiceException ex) {
            Logger.getLogger(PanelAdmission.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        for (Tank t : list) {
            tankComboBox.addItem(t);
        }
    }

    private void initContractComboBox() {
        contractComboBox.removeAllItems();
        List<Contract> list = null;
        try {
            list = contractService.getAllContract();

        } catch (ServiceException ex) {
            Logger.getLogger(PanelAdmission.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        for (Contract c : list) {
            contractComboBox.addItem(c);
        }
    }

    private void initCargoComboBox() {
        cargoComboBox.removeAllItems();
        List<Cargo> list = null;
        try {
            list = cargoService.getAllCargo();

        } catch (ServiceException ex) {
            Logger.getLogger(PanelAdmission.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        for (Cargo c : list) {
            cargoComboBox.addItem(c);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cargoComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        contractComboBox = new javax.swing.JComboBox<>();
        planDateField = new datechooser.beans.DateChooserCombo();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tankComboBox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        volumeField = new javax.swing.JTextField();
        drainLocationComboBox = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        umberField = new javax.swing.JTextField();
        timeSpinner = new JSpinner( new SpinnerDateModel() );
        btnAdd2 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        planDateField1 = new datechooser.beans.DateChooserCombo();
        timeSpinner1 = new JSpinner( new SpinnerDateModel() );
        jLabel12 = new javax.swing.JLabel();
        planDateField2 = new datechooser.beans.DateChooserCombo();
        timeSpinner2 = new JSpinner( new SpinnerDateModel() );

        setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setText("Груз");

        cargoComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargoComboBoxActionPerformed(evt);
            }
        });

        jLabel2.setText("Договор");

        jLabel3.setText("Плановая дата");

        jLabel4.setText("Цистерна");

        tankComboBox.setEditable(true);

        jLabel5.setText("Объём");

        jLabel6.setText("Место слива");

        jLabel7.setText("Накопитель");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Зависимости завозов");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Номер", "Первый", "Время", "Зависимый", "Время"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 584, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        jLabel9.setText("(тони)");

        jLabel10.setText("Номер");

        umberField.setEditable(false);

        timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setValue(new Date());
        timeSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                timeSpinnerStateChanged(evt);
            }
        });

        btnAdd2.setIcon(ImageHelper.loadImage("plus.png"));
        btnAdd2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnAdd2.setFocusable(false);
        btnAdd2.setMaximumSize(new java.awt.Dimension(25, 25));
        btnAdd2.setMinimumSize(new java.awt.Dimension(25, 25));
        btnAdd2.setPreferredSize(new java.awt.Dimension(25, 25));
        btnAdd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd2ActionPerformed(evt);
            }
        });

        jCheckBox1.setBackground(new java.awt.Color(51, 51, 51));
        jCheckBox1.setText("завершено");
        jCheckBox1.setFocusable(false);
        jCheckBox1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox1StateChanged(evt);
            }
        });
        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseReleased(evt);
            }
        });

        jPanel2.setVisible(false);
        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel11.setText("Начало");

        timeEditor1 = new JSpinner.DateEditor(timeSpinner1, "HH:mm");
        timeSpinner1.setEditor(timeEditor1);
        timeSpinner1.setValue(new Date());
        timeSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                timeSpinner1StateChanged(evt);
            }
        });

        jLabel12.setText("Конец");

        timeEditor2 = new JSpinner.DateEditor(timeSpinner2, "HH:mm");
        timeSpinner2.setEditor(timeEditor2);
        timeSpinner2.setValue(new Date());
        timeSpinner2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                timeSpinner2StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(planDateField1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timeSpinner1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(planDateField2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timeSpinner2)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(timeSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(planDateField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(planDateField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timeSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tankComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(volumeField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(drainLocationComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(planDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timeSpinner))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jCheckBox1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(umberField)
                            .addComponent(contractComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cargoComboBox, 0, 265, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 488, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAdd2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 101, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(umberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(contractComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cargoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(planDateField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(timeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tankComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(volumeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(drainLocationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cargoComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargoComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cargoComboBoxActionPerformed

    private void timeSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_timeSpinnerStateChanged
//        Date date = (Date) timeSpinner.getValue();
//        Calendar c = new GregorianCalendar();
//        c.setTime(gi.getStart());
//        c.set(Calendar.HOUR_OF_DAY,date.getHours());
//        c.set(Calendar.MINUTE,date.getMinutes());
//        gi.setStart(c.getTime());
//        gi.init();
//        gi.repaint();
    }//GEN-LAST:event_timeSpinnerStateChanged

    private void btnAdd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd2ActionPerformed
        DialogAddAdmissionDependency d = new DialogAddAdmissionDependency(MainFrame.getInstance(), true);
        if (d.showDialog()) {
            System.out.println("add admission");
//            ContentContract cc = d.getResult();
//            cc.setContract(contract);
//            contract.addContentElement(cc);
//            initTableContentContract();
        }
//        editPanel();
    }//GEN-LAST:event_btnAdd2ActionPerformed

    private void timeSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_timeSpinner1StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_timeSpinner1StateChanged

    private void timeSpinner2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_timeSpinner2StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_timeSpinner2StateChanged

    private void jCheckBox1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox1StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1StateChanged

    private void jCheckBox1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseReleased
        jPanel2.setVisible(jCheckBox1.isSelected());
    }//GEN-LAST:event_jCheckBox1MouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd2;
    private javax.swing.JComboBox<Cargo> cargoComboBox;
    private javax.swing.JComboBox<Contract> contractComboBox;
    private javax.swing.JComboBox<DrainLocation> drainLocationComboBox;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private datechooser.beans.DateChooserCombo planDateField;
    private datechooser.beans.DateChooserCombo planDateField1;
    private datechooser.beans.DateChooserCombo planDateField2;
    private javax.swing.JComboBox<Tank> tankComboBox;
    private javax.swing.JSpinner timeSpinner;
    private javax.swing.JSpinner timeSpinner1;
    private javax.swing.JSpinner timeSpinner2;
    private javax.swing.JTextField umberField;
    private javax.swing.JTextField volumeField;
    // End of variables declaration//GEN-END:variables
}
