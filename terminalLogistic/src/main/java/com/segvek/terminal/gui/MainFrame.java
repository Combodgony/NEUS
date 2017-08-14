package com.segvek.terminal.gui;

import com.segvek.terminal.gui.tab.PanelClientList;
import com.segvek.terminal.gui.tab.PanelInteractivEditor;
import com.segvek.terminal.gui.tab.PanelHello;
import com.segvek.terminal.gui.tab.PanelCcontractList;
import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import com.segvek.terminal.Loader;
import com.segvek.terminal.gui.image.ImageHelper;
import com.segvek.terminal.gui.tab.CloseableTabComponent;
import com.segvek.terminal.gui.tab.PanelContract;
import com.segvek.terminal.gui.tab.Tab;
import java.awt.event.KeyEvent;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class MainFrame extends javax.swing.JFrame{
    private static MainFrame instance;
    public static MainFrame getInstance(){
        return instance;
    }
    
    public void addPanelTab(String title,JPanel p){
        int tabIndex = jTabbedPane1.getTabCount();
        jTabbedPane1.insertTab(title, null, p, null, tabIndex);
        jTabbedPane1.setTabComponentAt(tabIndex, new CloseableTabComponent(jTabbedPane1, title));
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (jTabbedPane1.getTabCount() > 1) {
                    jTabbedPane1.setSelectedIndex(jTabbedPane1.getTabCount() - 1);
                }
            }
        });
    }
    public void initInstrumentPanel(){
        Tab tab = (Tab) jTabbedPane1.getSelectedComponent();
        btnSave.setEnabled(tab.isNeedSave());
        btnAdd.setEnabled(tab.isCanBeAdd());
        btnEdit.setEnabled(tab.isCanBeEdit());
    }
    
    
    private PanelHello helloPanel;
    private PanelInteractivEditor interactivEditorPanel;
    private PanelCcontractList contractListPanel;
    private PanelClientList clientListPanel;
       
    
    
    private MainFrame() {
        instance=this;
        initComponents();
        helloPanel=new PanelHello();
        interactivEditorPanel = new PanelInteractivEditor();
        contractListPanel=new PanelCcontractList();
        clientListPanel= new PanelClientList();
        
        jTabbedPane1.addTab("Приветствие", helloPanel);
        jTabbedPane1.setTabComponentAt(0, new CloseableTabComponent(jTabbedPane1, "Приветствие"));
      
        
        jTabbedPane1.addTab("Интерактивный редактор", interactivEditorPanel);
        jTabbedPane1.addTab("Договора", contractListPanel);
        jTabbedPane1.addTab("Клиенты", clientListPanel);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        popupMenu1 = new java.awt.PopupMenu();
        insptumentPanel = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        contentPanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        statusPanel = new javax.swing.JPanel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        interactivRedactor = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();

        jLabel1.setText("jLabel1");

        popupMenu1.setLabel("popupMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        insptumentPanel.setBackground(new java.awt.Color(51, 51, 51));
        insptumentPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, new javax.swing.border.MatteBorder(null)));
        insptumentPanel.setMaximumSize(new java.awt.Dimension(32767, 50));
        insptumentPanel.setMinimumSize(new java.awt.Dimension(0, 50));
        insptumentPanel.setName(""); // NOI18N
        insptumentPanel.setPreferredSize(new java.awt.Dimension(800, 50));
        insptumentPanel.setRequestFocusEnabled(false);

        btnSave.setIcon(ImageHelper.loadImage("saveActive.png"));
        btnSave.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSave.setEnabled(false);
        btnSave.setFocusable(false);
        btnSave.setMaximumSize(new java.awt.Dimension(25, 25));
        btnSave.setMinimumSize(new java.awt.Dimension(25, 25));
        btnSave.setPreferredSize(new java.awt.Dimension(25, 25));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnAdd.setIcon(ImageHelper.loadImage("plus.png"));
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

        btnEdit.setIcon(ImageHelper.loadImage("edit.png"));
        btnEdit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnEdit.setFocusable(false);
        btnEdit.setMaximumSize(new java.awt.Dimension(25, 25));
        btnEdit.setMinimumSize(new java.awt.Dimension(25, 25));
        btnEdit.setPreferredSize(new java.awt.Dimension(25, 25));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout insptumentPanelLayout = new javax.swing.GroupLayout(insptumentPanel);
        insptumentPanel.setLayout(insptumentPanelLayout);
        insptumentPanelLayout.setHorizontalGroup(
            insptumentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(insptumentPanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        insptumentPanelLayout.setVerticalGroup(
            insptumentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, insptumentPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(insptumentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        contentPanel.setBackground(new java.awt.Color(51, 51, 51));

        jTabbedPane1.setFocusable(false);
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
        );

        statusPanel.setBackground(new java.awt.Color(51, 51, 51));
        statusPanel.setMaximumSize(new java.awt.Dimension(32767, 20));
        statusPanel.setMinimumSize(new java.awt.Dimension(100, 20));
        statusPanel.setPreferredSize(new java.awt.Dimension(0, 20));

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jMenuBar2.setBackground(new java.awt.Color(102, 102, 102));
        jMenuBar2.setOpaque(false);

        jMenu4.setText("Файл");

        jMenuItem1.setText("Закрыть");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuBar2.add(jMenu4);

        jMenu5.setText("Вид");

        interactivRedactor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
        interactivRedactor.setSelected(true);
        interactivRedactor.setText("Интерактивный редактор");
        interactivRedactor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                interactivRedactorMouseReleased(evt);
            }
        });
        jMenu5.add(interactivRedactor);

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("Договора");
        jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem1ActionPerformed(evt);
            }
        });
        jMenu5.add(jCheckBoxMenuItem1);

        jMenuBar2.add(jMenu5);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(insptumentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(statusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(insptumentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setSize(new java.awt.Dimension(813, 463));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_F11)
            if(getExtendedState()!=MAXIMIZED_BOTH)
                setExtendedState(MAXIMIZED_BOTH);
            else
                setExtendedState(NORMAL);
    }//GEN-LAST:event_formKeyReleased

    private void jCheckBoxMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ActionPerformed
        if(!jCheckBoxMenuItem1.getState()){
            jTabbedPane1.remove(contractListPanel);
        }else{
            jTabbedPane1.addTab("Договора", contractListPanel);
        }
    }//GEN-LAST:event_jCheckBoxMenuItem1ActionPerformed

    private void interactivRedactorMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_interactivRedactorMouseReleased
        if(!interactivRedactor.getState()){
            jTabbedPane1.remove(interactivEditorPanel);
        }else{
            jTabbedPane1.addTab("Интерактивный редактор", interactivEditorPanel);
        }
    }//GEN-LAST:event_interactivRedactorMouseReleased

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if(jTabbedPane1.getSelectedComponent()==clientListPanel)
            addPanelTab("Клиент (Новый) ", new Tab());
        if(jTabbedPane1.getSelectedComponent()==contractListPanel)
            addPanelTab("Договор (Новый) ", new PanelContract());
    }//GEN-LAST:event_btnAddActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        initInstrumentPanel();
        Tab tab = (Tab) jTabbedPane1.getSelectedComponent();
        tab.init();
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        Tab tab = (Tab) jTabbedPane1.getSelectedComponent();
        tab.save();
        CloseableTabComponent c = (CloseableTabComponent) jTabbedPane1.getTabComponentAt(jTabbedPane1.getSelectedIndex());
        if(c!=null){
            StringBuilder sb= new StringBuilder();
            sb.append(tab.getName()).append("  ");
            c.setTile(sb.toString());
        }
        initInstrumentPanel();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        Tab tab = (Tab) jTabbedPane1.getSelectedComponent();
        tab.edit();
        CloseableTabComponent c = (CloseableTabComponent) jTabbedPane1.getTabComponentAt(jTabbedPane1.getSelectedIndex());
        if(c!=null){
            StringBuilder sb= new StringBuilder();
            sb.append(tab.getName()).append("  ");
            c.setTile(sb.toString());
        }
        initInstrumentPanel();
    }//GEN-LAST:event_btnEditActionPerformed
  
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) { 
        Loader.getContext();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                        Properties props = new Properties();
                        props.put("logoString", ""); 
                        props.put("windowDecoration", "off");
                        UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");//com.jtattoo.plaf.noire.NoireLookAndFeel");
                        HiFiLookAndFeel.setCurrentTheme(props);
                    } catch (Exception ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                JFrame.setDefaultLookAndFeelDecorated(false);
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JPanel insptumentPanel;
    private javax.swing.JCheckBoxMenuItem interactivRedactor;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private java.awt.PopupMenu popupMenu1;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

}
