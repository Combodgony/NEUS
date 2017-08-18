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
import com.segvek.terminal.service.AdmissionService;
import com.segvek.terminal.service.CargoService;
import com.segvek.terminal.service.ContractService;
import com.segvek.terminal.service.DependencyService;
import com.segvek.terminal.service.DrainLocationService;
import com.segvek.terminal.service.ServiceException;
import com.segvek.terminal.service.TankService;
import java.util.Calendar;
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
    private AdmissionService admissionService = new AdmissionService();

    private Admission admission;

    private boolean save = true;
    private boolean init = true;

    public PanelAdmission() {
	admission = Admission.newInstance();
	initComponents();
	initContractComboBox();
	initCargoComboBox();
	initTankComboBox();
	initDrainLocationComboBox();
	AutoCompleteDecorator.decorate(contractComboBox);
	init = false;
	save = false;
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
	if (!a.isPlan()) {
	    jPanel2.setVisible(true);
	    jCheckBox1.setSelected(true);
	    timeSpinner1.setValue(a.getFactBegin());
	    timeSpinner2.setValue(a.getFactEnd());
	    Calendar begin = new GregorianCalendar();
	    begin.setTime(a.getFactBegin());
	    factBeginDateField.setSelectedDate(begin);
	    Calendar end = new GregorianCalendar();
	    end.setTime(a.getFactEnd());
	    factEndDateField.setSelectedDate(end);
	}
	init = false;
    }

    PanelAdmission(Contract contract, Cargo cargo) {
	admission = Admission.newInstance();
	admission.setContract(contract);
	admission.setCargo(cargo);
	initComponents();
	initContractComboBox();
	initCargoComboBox();
	initTankComboBox();
	initDrainLocationComboBox();
	AutoCompleteDecorator.decorate(contractComboBox);
	contractComboBox.setSelectedItem(contract);
	cargoComboBox.setSelectedItem(cargo);
	init = false;
	save = false;
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
	DefaultTableModel dtm = (DefaultTableModel) dependencyAdmissionTable.getModel();
	dtm.setRowCount(0);
	try {
	    List<DependencyAdmission> list = dependencyService.getDependencyAdmissionsByAdmission(admission);
	    for (DependencyAdmission d : list) {
		dtm.addRow(new Object[]{d, d.getDepend(), d.getDepend().getBegin(), d.getIndependnet(), d.getIndependnet().getBegin()});
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
        dependencyAdmissionTable = new javax.swing.JTable();
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
        factBeginDateField = new datechooser.beans.DateChooserCombo();
        timeSpinner1 = new JSpinner( new SpinnerDateModel() );
        jLabel12 = new javax.swing.JLabel();
        factEndDateField = new datechooser.beans.DateChooserCombo();
        timeSpinner2 = new JSpinner( new SpinnerDateModel() );
        btnDel = new javax.swing.JButton();

        setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setText("Груз");

        cargoComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargoComboBoxActionPerformed(evt);
            }
        });

        jLabel2.setText("Договор");

        contractComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contractComboBoxActionPerformed(evt);
            }
        });

        planDateField.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
            public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
                planDateFieldOnSelectionChange(evt);
            }
        });

        jLabel3.setText("Плановая дата");

        jLabel4.setText("Цистерна");

        tankComboBox.setEditable(true);
        tankComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tankComboBoxActionPerformed(evt);
            }
        });

        jLabel5.setText("Объём");

        jLabel6.setText("Место слива");

        jLabel7.setText("Накопитель");

        volumeField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                volumeFieldKeyReleased(evt);
            }
        });

        drainLocationComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drainLocationComboBoxActionPerformed(evt);
            }
        });

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Зависимости завозов");

        dependencyAdmissionTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(dependencyAdmissionTable);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 553, Short.MAX_VALUE)
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
        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseReleased(evt);
            }
        });

        jPanel2.setVisible(false);
        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel11.setText("Начало");

        factBeginDateField.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
            public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
                factBeginDateFieldOnSelectionChange(evt);
            }
        });

        timeEditor1 = new JSpinner.DateEditor(timeSpinner1, "HH:mm");
        timeSpinner1.setEditor(timeEditor1);
        timeSpinner1.setValue(new Date());
        timeSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                timeSpinner1StateChanged(evt);
            }
        });

        jLabel12.setText("Конец");

        factEndDateField.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
            public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
                factEndDateFieldOnSelectionChange(evt);
            }
        });

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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(factEndDateField, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                    .addComponent(factBeginDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timeSpinner2)
                    .addComponent(timeSpinner1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(timeSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(factBeginDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(factEndDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timeSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnDel.setIcon(ImageHelper.loadImage("plus.png"));
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
                            .addComponent(cargoComboBox, 0, 265, Short.MAX_VALUE)
                            .addComponent(contractComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                    .addComponent(btnAdd2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
	if (cargoComboBox.getSelectedItem() != null && !init) {
	    admission.setCargo((Cargo) cargoComboBox.getSelectedItem());
	    editPanel();
	}
    }//GEN-LAST:event_cargoComboBoxActionPerformed

    private void timeSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_timeSpinnerStateChanged
	if (!init) {
	    Date date = (Date) timeSpinner.getValue();
	    Calendar c = new GregorianCalendar();
	    c.setTime(planDateField.getSelectedDate().getTime());
	    c.set(Calendar.HOUR_OF_DAY, date.getHours());
	    c.set(Calendar.MINUTE, date.getMinutes());
	    admission.setBegin(c.getTime());
	    editPanel();
	}
    }//GEN-LAST:event_timeSpinnerStateChanged

    private void btnAdd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd2ActionPerformed
	//todo добавить обработку результатов диалога
	DialogAddAdmissionDependency d = new DialogAddAdmissionDependency(MainFrame.getInstance(), true);
	if (d.showDialog()) {
	    try {
		dependencyService.save(d.getResult());
		initTableDependencyAdmission();
	    } catch (ServiceException ex) {
		Logger.getLogger(PanelAdmission.class.getName()).log(Level.SEVERE, null, ex);
		JOptionPane.showMessageDialog(cargoComboBox, "Не удалось сохранить зависимость");
	    }
	}
    }//GEN-LAST:event_btnAdd2ActionPerformed

    private void timeSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_timeSpinner1StateChanged
	if (!init) {
	    Date date = (Date) timeSpinner1.getValue();
	    Calendar c = new GregorianCalendar();
	    c.setTime(factBeginDateField.getSelectedDate().getTime());
	    c.set(Calendar.HOUR_OF_DAY, date.getHours());
	    c.set(Calendar.MINUTE, date.getMinutes());
	    admission.setFactBegin(c.getTime());
	    editPanel();
	}
    }//GEN-LAST:event_timeSpinner1StateChanged

    private void timeSpinner2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_timeSpinner2StateChanged
	if (!init) {
	    Date date = (Date) timeSpinner2.getValue();
	    Calendar c = new GregorianCalendar();
	    c.setTime(factEndDateField.getSelectedDate().getTime());
	    c.set(Calendar.HOUR_OF_DAY, date.getHours());
	    c.set(Calendar.MINUTE, date.getMinutes());
	    admission.setFactEnd(c.getTime());
	    editPanel();
	}
    }//GEN-LAST:event_timeSpinner2StateChanged

    private void jCheckBox1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseReleased
	jPanel2.setVisible(jCheckBox1.isSelected());
	if (!init) {
	    admission.setPlan(!jCheckBox1.isSelected());
	}
    }//GEN-LAST:event_jCheckBox1MouseReleased

    private void contractComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contractComboBoxActionPerformed
	if (contractComboBox.getSelectedItem() != null && !init) {
	    admission.setContract((Contract) contractComboBox.getSelectedItem());
	    editPanel();
	}
    }//GEN-LAST:event_contractComboBoxActionPerformed

    private void tankComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tankComboBoxActionPerformed
	if (tankComboBox.getSelectedItem() != null && !init) {
	    admission.setTank((Tank) tankComboBox.getSelectedItem());
	    editPanel();
	}
    }//GEN-LAST:event_tankComboBoxActionPerformed

    private void volumeFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_volumeFieldKeyReleased
	int volume=0;
	try{
	    volume = Integer.parseInt(volumeField.getText());
	}catch(NumberFormatException e){
	    JOptionPane.showMessageDialog(cargoComboBox, "Не верно введён объём.\nВведите целое число.");
	    return;
	}
	admission.setVolume(volume);
    }//GEN-LAST:event_volumeFieldKeyReleased

    private void drainLocationComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drainLocationComboBoxActionPerformed
	if (drainLocationComboBox.getSelectedItem() != null && !init) {
	    admission.setDrainLocation((DrainLocation) drainLocationComboBox.getSelectedItem());
	    editPanel();
	}
    }//GEN-LAST:event_drainLocationComboBoxActionPerformed

    private void planDateFieldOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_planDateFieldOnSelectionChange
        if (!init) {
	    Date date = (Date) timeSpinner.getValue();
	    Calendar c = new GregorianCalendar();
	    c.setTime(planDateField.getSelectedDate().getTime());
	    c.set(Calendar.HOUR_OF_DAY, date.getHours());
	    c.set(Calendar.MINUTE, date.getMinutes());
	    admission.setBegin(c.getTime());
	    editPanel();
	}
    }//GEN-LAST:event_planDateFieldOnSelectionChange

    private void factBeginDateFieldOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_factBeginDateFieldOnSelectionChange
        if (!init) {
	    Date date = (Date) timeSpinner1.getValue();
	    Calendar c = new GregorianCalendar();
	    c.setTime(factBeginDateField.getSelectedDate().getTime());
	    c.set(Calendar.HOUR_OF_DAY, date.getHours());
	    c.set(Calendar.MINUTE, date.getMinutes());
	    admission.setFactBegin(c.getTime());
	    editPanel();
	}
    }//GEN-LAST:event_factBeginDateFieldOnSelectionChange

    private void factEndDateFieldOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_factEndDateFieldOnSelectionChange
        if (!init) {
	    Date date = (Date) timeSpinner2.getValue();
	    Calendar c = new GregorianCalendar();
	    c.setTime(factEndDateField.getSelectedDate().getTime());
	    c.set(Calendar.HOUR_OF_DAY, date.getHours());
	    c.set(Calendar.MINUTE, date.getMinutes());
	    admission.setFactEnd(c.getTime());
	    editPanel();
	}
    }//GEN-LAST:event_factEndDateFieldOnSelectionChange

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        int row = dependencyAdmissionTable.getSelectedRow();
	if(row<0)
	    return;
	DependencyAdmission da = (DependencyAdmission) dependencyAdmissionTable.getValueAt(row, 0);
	try {
	    dependencyService.deleteDependencyAdmission(da);
	} catch (ServiceException ex) {
	    JOptionPane.showMessageDialog(cargoComboBox, ex.getMessage());
	    Logger.getLogger(PanelAdmission.class.getName()).log(Level.SEVERE, null, ex);
	}
	initTableDependencyAdmission();
    }//GEN-LAST:event_btnDelActionPerformed

    @Override
    public boolean isNeedSave() {
	return !save;
    }

    private void editPanel() {
	if (!init) {
	    save = false;
	    MainFrame.getInstance().initInstrumentPanel();
	}
    }

    @Override
    public void save() {
	try {
	    admissionService.saveAdmission(admission);
	    save = true;
	    MainFrame.getInstance().initInstrumentPanel();
	} catch (ServiceException e) {
	    JOptionPane.showMessageDialog(this, e.getMessage());
	}
    }

    @Override
    public String getName() {
	if (admission.isNewInstance()) {
	    return "Завоз (Новый)";
	} else {
	    return "Завоз №" + admission.getId();
	}
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd2;
    private javax.swing.JButton btnDel;
    private javax.swing.JComboBox<Cargo> cargoComboBox;
    private javax.swing.JComboBox<Contract> contractComboBox;
    private javax.swing.JTable dependencyAdmissionTable;
    private javax.swing.JComboBox<DrainLocation> drainLocationComboBox;
    private datechooser.beans.DateChooserCombo factBeginDateField;
    private datechooser.beans.DateChooserCombo factEndDateField;
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
    private datechooser.beans.DateChooserCombo planDateField;
    private javax.swing.JComboBox<Tank> tankComboBox;
    private javax.swing.JSpinner timeSpinner;
    private javax.swing.JSpinner timeSpinner1;
    private javax.swing.JSpinner timeSpinner2;
    private javax.swing.JTextField umberField;
    private javax.swing.JTextField volumeField;
    // End of variables declaration//GEN-END:variables
}
