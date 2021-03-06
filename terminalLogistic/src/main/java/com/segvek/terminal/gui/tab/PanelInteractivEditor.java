package com.segvek.terminal.gui.tab;

import com.segvek.terminal.gui.DialogAddAdmissionDependency;
import com.segvek.terminal.gui.MainFrame;
import com.segvek.terminal.gui.image.ImageHelper;
import com.segvek.terminal.gui.tab.interactiv.InteractivGrafic;
import com.segvek.terminal.gui.tab.interactiv.InteractivGraficListener;
import com.segvek.terminal.gui.tab.interactiv.InteractiveGraficException;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.DependencyAdmission;
import com.segvek.terminal.model.Estakada;
import com.segvek.terminal.service.AdmissionService;
import com.segvek.terminal.service.DependencyService;
import com.segvek.terminal.service.EstakadService;
import com.segvek.terminal.service.ServiceException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

/**
 *
 * @author Panas
 */
public class PanelInteractivEditor extends Tab {

    JSpinner.DateEditor timeEditor, timeEditor1;

    private AdmissionService admissionService;
    private EstakadService estakadService;
    private DependencyService dependencyService;

    private boolean editableData = false;
    private double weidthMinute = 2;

    private List<Admission> admissions;
    private List<Estakada> estakads;
    private List<DependencyAdmission> dependencyAdmissions;

    InteractivGrafic gi;

    public PanelInteractivEditor() {
        admissionService = new AdmissionService();
        estakadService = new EstakadService();
        dependencyService = new DependencyService();
        initComponents();
        gi = (InteractivGrafic) contentPanel;
        gi.setWeidthMinut(weidthMinute);
        Calendar c = new GregorianCalendar();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.add(Calendar.DATE, 1);
        dateChooserCombo2.setSelectedDate(c);

        
        
        try {
            gi.addInteractiveGraficListener(new InteractivGraficListener() {
                @Override
                public void selected(Admission admission) {
                    interactiveGraficAelectedAdmission(admission);
                }
            });
        } catch (InteractiveGraficException ex) {
            Logger.getLogger(PanelInteractivEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        contentPanel.setSize(500, 500);
        initData();
    }

    private void initData() {
	Date date = (Date) timeSpinner.getValue();
        Calendar c = new GregorianCalendar();
        c.setTime(gi.getStart());
        c.set(Calendar.HOUR_OF_DAY, date.getHours());
        c.set(Calendar.MINUTE, date.getMinutes());
        gi.setStart(c.getTime());
	
	Date date2 = (Date) timeSpinner1.getValue();
        Calendar c2 = new GregorianCalendar();
        c2.setTime(gi.getEnd());
        c2.set(Calendar.HOUR_OF_DAY, date2.getHours());
        c2.set(Calendar.MINUTE, date2.getMinutes());
        gi.setEnd(c2.getTime());
	
        try {
            estakads = estakadService.getAllEstacad();
            admissions = admissionService.getAllAdmission();
            dependencyAdmissions = dependencyService.getAllDependency();
        } catch (ServiceException ex) {
            Logger.getLogger(PanelInteractivEditor.class.getName())
                    .log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(contentPanel, "Не удалось загрузить данные для интерактивного режима.");
            return;
        }
        gi.setDependencyAdmissions(dependencyAdmissions);
        gi.setEstakads(estakads);
        gi.setAdmissions(admissions);
        gi.init();

    }

    @Override
    public void init() {
        initData();
        gi.repaint();
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        timeSpinner = new JSpinner( new SpinnerDateModel() );
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();
        timeSpinner1 = new JSpinner( new SpinnerDateModel() );
        dateChooserCombo3 = new datechooser.beans.DateChooserCombo();
        jCheckBox1 = new javax.swing.JCheckBox();
        jSlider1 = new javax.swing.JSlider();
        btnAdd2 = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        contentPanel = new InteractivGrafic(editableData);
        jPanel2 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(51, 204, 0));

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setMaximumSize(new java.awt.Dimension(32767, 30));
        jPanel1.setMinimumSize(new java.awt.Dimension(100, 30));
        jPanel1.setPreferredSize(new java.awt.Dimension(450, 30));

        timeEditor = new JSpinner.DateEditor(timeSpinner, "HH");
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setValue(new Date());
        timeSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                timeSpinnerStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Начало:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Конец:");

        dateChooserCombo2.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dateChooserCombo2OnCommit(evt);
            }
        });

        timeEditor1 = new JSpinner.DateEditor(timeSpinner1, "HH");
        timeSpinner1.setEditor(timeEditor1);
        timeSpinner1.setValue(new Date());
        timeSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                timeSpinner1StateChanged(evt);
            }
        });

        dateChooserCombo3.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dateChooserCombo3OnCommit(evt);
            }
        });

        jCheckBox1.setForeground(new java.awt.Color(204, 204, 204));
        jCheckBox1.setText("Редактирование");
        jCheckBox1.setOpaque(false);
        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseReleased(evt);
            }
        });

        jSlider1.setMaximum(50);
        jSlider1.setMinimum(2);
        jSlider1.setValue(20);
        jSlider1.setFocusable(false);
        jSlider1.setOpaque(false);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
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

        btnUpdate.setIcon(ImageHelper.loadImage("update.png"));
        btnUpdate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnUpdate.setFocusable(false);
        btnUpdate.setMaximumSize(new java.awt.Dimension(25, 25));
        btnUpdate.setMinimumSize(new java.awt.Dimension(25, 25));
        btnUpdate.setPreferredSize(new java.awt.Dimension(25, 25));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateChooserCombo3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addContainerGap(8, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(dateChooserCombo3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(timeSpinner1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                                    .addComponent(jSlider1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(dateChooserCombo2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(timeSpinner, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(3, 3, 3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jSplitPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSplitPane1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jSplitPane1ComponentResized(evt);
            }
        });

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 301, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(contentPanel);

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 777, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 301, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSplitPane1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jSplitPane1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jSplitPane1ComponentResized
        jSplitPane1.setDividerLocation(jSplitPane1.getWidth() - 200);
    }//GEN-LAST:event_jSplitPane1ComponentResized

    private void dateChooserCombo3OnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dateChooserCombo3OnCommit
        Calendar beginDate = (Calendar) dateChooserCombo3.getSelectedDate().clone();
        Date date = (Date) timeSpinner.getValue();
        beginDate.set(Calendar.HOUR_OF_DAY, date.getHours());
        beginDate.set(Calendar.MINUTE, date.getMinutes());
        gi.setStartDate(beginDate.getTime());
        gi.init();
        gi.repaint();
    }//GEN-LAST:event_dateChooserCombo3OnCommit

    private void timeSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_timeSpinnerStateChanged
        Date date = (Date) timeSpinner.getValue();
        Calendar c = new GregorianCalendar();
        c.setTime(gi.getStart());
        c.set(Calendar.HOUR_OF_DAY, date.getHours());
        c.set(Calendar.MINUTE, date.getMinutes());
        gi.setStart(c.getTime());
        gi.init();
        gi.repaint();
    }//GEN-LAST:event_timeSpinnerStateChanged

    private void dateChooserCombo2OnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dateChooserCombo2OnCommit
        Calendar enddate = (Calendar) dateChooserCombo2.getSelectedDate().clone();
        Date date = (Date) timeSpinner1.getValue();
        enddate.set(Calendar.HOUR_OF_DAY, date.getHours());
        enddate.set(Calendar.MINUTE, date.getMinutes());
        gi.setEnd(enddate.getTime());
        gi.init();
        gi.repaint();
    }//GEN-LAST:event_dateChooserCombo2OnCommit

    private void timeSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_timeSpinner1StateChanged
        Date date = (Date) timeSpinner1.getValue();
        Calendar c = new GregorianCalendar();
        c.setTime(gi.getEnd());
        c.set(Calendar.HOUR_OF_DAY, date.getHours());
        c.set(Calendar.MINUTE, date.getMinutes());
        gi.setEnd(c.getTime());
        gi.init();
        gi.repaint();
    }//GEN-LAST:event_timeSpinner1StateChanged

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        weidthMinute = jSlider1.getValue() / 10.0;
        gi.setWeidthMinut(weidthMinute);
        gi.init();
        gi.repaint();
    }//GEN-LAST:event_jSlider1StateChanged

    private void btnAdd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd2ActionPerformed
        DialogAddAdmissionDependency d = new DialogAddAdmissionDependency(MainFrame.getInstance(), true);
        if (d.showDialog()) {
	    try {
		dependencyService.save(d.getResult());
		initData();
		gi.repaint();
	    } catch (ServiceException ex) {
		Logger.getLogger(PanelInteractivEditor.class.getName()).log(Level.SEVERE, null, ex);
		JOptionPane.showMessageDialog(contentPanel, "не удалось добавить зависимость");
	    }
        }
    }//GEN-LAST:event_btnAdd2ActionPerformed

    private void jCheckBox1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseReleased
        editableData = jCheckBox1.isSelected();
        gi.setEditable(editableData);
        gi.init();
	gi.repaint();
    }//GEN-LAST:event_jCheckBox1MouseReleased

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        initData();
	gi.repaint();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void interactiveGraficAelectedAdmission(Admission admission) {
        MainFrame.getInstance().addPanelTab("Завоз №"+admission.getId(), new PanelAdmission(admission));
    }

    @Override
    public boolean isNeedSave() {
        return ((InteractivGrafic) contentPanel).isEdited();
    }

    @Override
    public void save() {
        try {
            admissionService.saveAllAdmissions(admissions);
            ((InteractivGrafic) contentPanel).setEdited(false);
            MainFrame.getInstance().initInstrumentPanel();
        } catch (ServiceException ex) {
            JOptionPane.showMessageDialog(contentPanel, "Не удлось сохранить завоз, для испрвления ошибки обратитесь к администратору!");
            Logger.getLogger(PanelInteractivEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd2;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JPanel contentPanel;
    private datechooser.beans.DateChooserCombo dateChooserCombo2;
    private datechooser.beans.DateChooserCombo dateChooserCombo3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSpinner timeSpinner;
    private javax.swing.JSpinner timeSpinner1;
    // End of variables declaration//GEN-END:variables

}
