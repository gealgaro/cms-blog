package com.gg.dailymoney.gui;

import com.gg.dailymoney.init.DataManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ActivateJDialog extends JDialog {

    public ActivateJDialog(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        DataManager data = new DataManager();
        jTextAreaActCode.setText(data.getSoftwareKey());
    }

    private void initComponents() {
        jBackPanel = new JPanel();
        jLabelTitle = new JLabel();
        jPanelContent = new JPanel();
        jLabelActCode = new JLabel();
        jScrollPaneActCode = new JScrollPane();
        jTextAreaActCode = new JTextArea();
        jLabelLicNum = new JLabel();
        jScrollPaneLicNum = new JScrollPane();
        jTextAreaLicNum = new JTextArea();
        jPanelFooter = new JPanel();
        jButtonSave = new JButton();
        jButtonClose = new JButton();
        setDefaultCloseOperation(2);
        setTitle("Licencia de activaci\363n del software");
        jBackPanel.setLayout(new BorderLayout());
        jLabelTitle.setBackground(new Color(255, 255, 255));
        jLabelTitle.setFont(new Font("Trebuchet MS", 1, 28));
        jLabelTitle.setHorizontalAlignment(0);
        jLabelTitle.setIcon(new ImageIcon(getClass().getResource("/com/gg/dailymoney/res/icons/32x32/lock.png")));
        jLabelTitle.setText("Activar el software");
        jLabelTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jLabelTitle.setOpaque(true);
        jBackPanel.add(jLabelTitle, "North");
        jPanelContent.setBackground(new Color(255, 255, 255));
        jPanelContent.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createEtchedBorder()));
        jPanelContent.setLayout(new GridBagLayout());
        jLabelActCode.setFont(new Font("Trebuchet MS", 0, 14));
        jLabelActCode.setForeground(new Color(0, 0, 255));
        jLabelActCode.setText("Codigo de activaci\363n");
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(5, 10, 5, 10);
        jPanelContent.add(jLabelActCode, gridBagConstraints);
        jTextAreaActCode.setBackground(new Color(255, 255, 153));
        jTextAreaActCode.setColumns(20);
        jTextAreaActCode.setFont(new Font("Trebuchet MS", 0, 14));
        jTextAreaActCode.setForeground(new Color(51, 153, 0));
        jTextAreaActCode.setLineWrap(true);
        jTextAreaActCode.setWrapStyleWord(true);
        jScrollPaneActCode.setViewportView(jTextAreaActCode);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0D;
        gridBagConstraints.weighty = 1.0D;
        gridBagConstraints.insets = new Insets(5, 10, 10, 10);
        jPanelContent.add(jScrollPaneActCode, gridBagConstraints);
        jLabelLicNum.setFont(new Font("Trebuchet MS", 0, 14));
        jLabelLicNum.setForeground(new Color(0, 0, 255));
        jLabelLicNum.setText("N\372mero de licencia");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(10, 10, 5, 10);
        jPanelContent.add(jLabelLicNum, gridBagConstraints);
        jTextAreaLicNum.setBackground(new Color(255, 255, 153));
        jTextAreaLicNum.setColumns(20);
        jTextAreaLicNum.setFont(new Font("Trebuchet MS", 0, 14));
        jTextAreaLicNum.setForeground(new Color(255, 0, 0));
        jTextAreaLicNum.setLineWrap(true);
        jTextAreaLicNum.setWrapStyleWord(true);
        jScrollPaneLicNum.setViewportView(jTextAreaLicNum);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0D;
        gridBagConstraints.weighty = 1.0D;
        gridBagConstraints.insets = new Insets(5, 10, 5, 10);
        jPanelContent.add(jScrollPaneLicNum, gridBagConstraints);
        jBackPanel.add(jPanelContent, "Center");
        jPanelFooter.setBackground(new Color(255, 255, 255));
        jPanelFooter.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanelFooter.setLayout(new GridBagLayout());
        jButtonSave.setFont(new Font("Trebuchet MS", 0, 14));
        jButtonSave.setIcon(new ImageIcon(getClass().getResource("/com/gg/dailymoney/res/icons/22x22/filesave.png")));
        jButtonSave.setText("Guardar");
        jButtonSave.setMargin(new Insets(2, 8, 2, 8));
        jButtonSave.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanelFooter.add(jButtonSave, gridBagConstraints);
        jButtonClose.setFont(new Font("Trebuchet MS", 0, 14));
        jButtonClose.setIcon(new ImageIcon(getClass().getResource("/com/gg/dailymoney/res/icons/22x22/fileclose.png")));
        jButtonClose.setText("Cerrar");
        jButtonClose.setToolTipText("Cerrar esta ventana.");
        jButtonClose.setMargin(new Insets(2, 8, 2, 8));
        jButtonClose.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanelFooter.add(jButtonClose, gridBagConstraints);
        jBackPanel.add(jPanelFooter, "South");
        getContentPane().add(jBackPanel, "Center");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 400) / 2, (screenSize.height - 400) / 2, 400, 400);
    }

    private void jButtonCloseActionPerformed(ActionEvent evt) {
        dispose();
    }

    private void jButtonSaveActionPerformed(ActionEvent evt) {
        DataManager data = new DataManager();
        String licnum = jTextAreaLicNum.getText();
        if (licnum.length() > 0) {
            if (data.isLicenseCorrect(licnum)) {
                JOptionPane.showMessageDialog(this, "Felicitaciones! Su software ha sido activado satisfactoriamente.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "La licencia suministrada no es v\341lida.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe digitar un c\363digo de licencia v\341lido.");
        }
    }
    private JPanel jBackPanel;
    private JButton jButtonClose;
    private JButton jButtonSave;
    private JLabel jLabelActCode;
    private JLabel jLabelLicNum;
    private JLabel jLabelTitle;
    private JPanel jPanelContent;
    private JPanel jPanelFooter;
    private JScrollPane jScrollPaneActCode;
    private JScrollPane jScrollPaneLicNum;
    private JTextArea jTextAreaActCode;
    private JTextArea jTextAreaLicNum;
}
