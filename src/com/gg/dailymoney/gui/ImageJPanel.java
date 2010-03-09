package com.gg.dailymoney.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImageJPanel extends JPanel {

    public ImageJPanel() {
        image = null;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        java.awt.geom.Rectangle2D.Double back = new java.awt.geom.Rectangle2D.Double(0.0D, 0.0D, getWidth(), getHeight());
        g2.setPaint(new Color(255, 255, 255));
        g2.fill(back);
        java.awt.geom.Rectangle2D.Double rect = new java.awt.geom.Rectangle2D.Double(0.0D, 0.0D, getWidth(), getHeight());
        g2.setPaint(new Color(0, 0, 0));
        g2.setStroke(new BasicStroke(2.0F));
        g2.draw(rect);
        if (image == null) {
            java.awt.geom.Line2D.Double line1 = new java.awt.geom.Line2D.Double(10D, 10D, getWidth() - 10, getHeight() - 10);
            java.awt.geom.Line2D.Double line2 = new java.awt.geom.Line2D.Double(getWidth() - 10, 10D, 10D, getHeight() - 10);
            g2.setPaint(new Color(255, 0, 0));
            g2.setStroke(new BasicStroke(4F));
            g2.draw(line1);
            g2.draw(line2);
        } else {
            g.drawImage(image, 1, 1, getWidth() - 2, getHeight() - 2, null);
        }
    }
    private BufferedImage image;
}
