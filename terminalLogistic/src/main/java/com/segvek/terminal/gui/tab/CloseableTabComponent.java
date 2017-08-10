package com.segvek.terminal.gui.tab;

import com.segvek.terminal.gui.image.ImageHelper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class CloseableTabComponent extends JPanel {
        
    private JTabbedPane tabbedPane = null; // the tabbed pane this component belongs to
    private JLabel titleLabel = null; // the title of the tab
    private JButton closeButton = null; // the closer button on the right side of the tab
    private Font defaultFont = null; // the default font of the title label
    private Font selectedFont = null; // the font of the title label if tab is selected
    private Color selectedColor = null; // the foreground color of the title lable if tab is selected
       
    public CloseableTabComponent(JTabbedPane aTabbedPane, String title) {
        super(new BorderLayout());
        tabbedPane = aTabbedPane;
        setOpaque(false);

        // setup the controls of this tab component
        titleLabel = new JLabel(title);
        titleLabel.setOpaque(false);
        // get the defaults for rendering the title label
        defaultFont = titleLabel.getFont().deriveFont(~Font.BOLD);
        selectedFont = titleLabel.getFont().deriveFont(Font.BOLD);
        selectedColor = UIManager.getColor("TabbedPane.selectedForeground");
        if (selectedColor == null) {
            selectedColor = tabbedPane.getForeground();
        }
        closeButton = new CloseableTabComponent.CloseButton();
        add(titleLabel, BorderLayout.CENTER);
        add(closeButton, BorderLayout.EAST);
        // add a action listener for closing tabs
        closeButton.addActionListener(new ActionListener() {
             
            @Override
            public void actionPerformed(ActionEvent e) {
                // calculate the tab index of this tab component
                int tabIndex = getTabIndex();
                if (tabIndex >= 0) {
                    // remove the tab from the tabbed pane
                    Tab tab = (Tab) tabbedPane.getComponentAt(tabIndex);
                    if(tab.isNeedSave()){
                        if (JOptionPane.showConfirmDialog(null, "Вы хотите выйти не сохранив изменения?","Предупреждение",
                                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            tabbedPane.removeTabAt(tabIndex);
                        }else{
                            return;
                        }
                            
                    }else{
                        tabbedPane.removeTabAt(tabIndex);
                    }
                }
                if ((tabbedPane.getTabCount() > 1) && (tabbedPane.getSelectedIndex() == tabbedPane.getTabCount() - 1)) {
                    tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
                }
            }
        });
          
    }
        
    // calculate the tab index of this tab component
    private int getTabIndex() {
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            if (this.equals(tabbedPane.getTabComponentAt(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void updateUI() {
        super.updateUI();
        // if look and feel changes we have to set the new defaults for rendering the title label
        if (titleLabel != null) {
            defaultFont = titleLabel.getFont().deriveFont(~Font.BOLD);
            selectedFont = titleLabel.getFont().deriveFont(Font.BOLD);
            selectedColor = UIManager.getColor("TabbedPane.selectedForeground");
            if (selectedColor == null) {
                selectedColor = tabbedPane.getForeground();
            }
        }
    }

    // We have to override paint to handle the rendering of the title label, because we want
    // the title to be painted different when tab is selected.
    @Override
    public void paint(Graphics g) {
        int tabIndex = getTabIndex();
        if (tabIndex >= 0) {
            if (tabIndex == tabbedPane.getSelectedIndex()) {
                titleLabel.setFont(selectedFont);
                if (tabbedPane.getForegroundAt(tabIndex) instanceof ColorUIResource) {
                    titleLabel.setForeground(selectedColor);
                } else {
                    titleLabel.setForeground(tabbedPane.getForegroundAt(tabIndex));
                }
            } else {
                titleLabel.setFont(defaultFont);
                titleLabel.setForeground(tabbedPane.getForegroundAt(tabIndex));
            }
        }
            
        super.paint(g);
    }

    private static class CloseButton extends JButton {
        
        private static final ImageIcon CLOSER_ICON = ImageHelper.loadImage("closer.gif");
        private static final ImageIcon CLOSER_ROLLOVER_ICON = ImageHelper.loadImage("closer_rollover.gif");
        private static final ImageIcon CLOSER_PRESSED_ICON = ImageHelper.loadImage("closer_pressed.gif");
        
        private static Dimension prefSize = new Dimension(16, 16);
        
        public CloseButton() {
            super("");
            setIcon(CLOSER_ICON);
            setRolloverIcon(CLOSER_ROLLOVER_ICON);
            setPressedIcon(CLOSER_PRESSED_ICON);
            setContentAreaFilled(false);
            setBorder(BorderFactory.createEmptyBorder());
            setFocusable(false);
            prefSize = new Dimension(CLOSER_ICON.getIconWidth(), CLOSER_ICON.getIconHeight());
        }

        @Override
        public Dimension getPreferredSize() {
            return prefSize;
        }
        
    }
    
    public void setTile(String title){
        titleLabel.setText(title);
        repaint();
    }
}