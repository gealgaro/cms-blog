/*
 * StartJDialog.java
 *
 * Created on 05-sep-2009, 2:39:10
 */
package com.gg.dailymoney.gui;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author German Garcia
 */
public class StartJDialog extends javax.swing.JDialog {

    public StartJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            imageJPanel1.setImage(ImageIO.read(getClass().getResourceAsStream("/com/gg/dailymoney/res/img/finance.jpg")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void setMessage(String string) {
        jLabelStatus.setText(string);
    }

    public void setProgress(int time) {
        jProgressBar1.setValue(time);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        imageJPanel1 = new com.gg.dailymoney.gui.ImageJPanel();
        jLabelStatus = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("");
        setUndecorated(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        imageJPanel1.setLayout(new java.awt.GridBagLayout());

        jLabelStatus.setFont(new java.awt.Font("Trebuchet MS", 1, 12));
        jLabelStatus.setForeground(new java.awt.Color(255, 255, 255));
        jLabelStatus.setText("Este software no ha sido activado");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        imageJPanel1.add(jLabelStatus, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(imageJPanel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jProgressBar1, gridBagConstraints);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-450)/2, (screenSize.height-320)/2, 450, 320);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.gg.dailymoney.gui.ImageJPanel imageJPanel1;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
