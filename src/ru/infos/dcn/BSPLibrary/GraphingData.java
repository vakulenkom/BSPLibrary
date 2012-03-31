package ru.infos.dcn.BSPLibrary;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;

public class GraphingData extends JPanel {
//    Stucture stucture =new Stucture();
    final int PAD = 50;
    Point[] points;
    BinaryTree.Node rootNode;
    Graphics2D g2;
    double xInc, yInc;
    int h;

    public GraphingData (Point[] points, BinaryTree.Node rootNode) {
        this.points = points;
        this.rootNode = rootNode;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        h = getHeight();
        // Draw ordinate.
        g2.draw(new Line2D.Double(PAD, PAD, PAD, h-PAD));
        // Draw abcissa.
        g2.draw(new Line2D.Double(PAD, h-PAD, w-PAD, h-PAD));
        // Draw labels.
        Font font = g2.getFont();
        FontRenderContext frc = g2.getFontRenderContext();
        LineMetrics lm = font.getLineMetrics("0", frc);
        float sh = lm.getAscent() + lm.getDescent();
        // Ordinate label.
        String s = "y axis";
        float sy = PAD + ((h - 2*PAD) - s.length()*sh)/2 + lm.getAscent();
        for(int i = 0; i < s.length(); i++) {
            String letter = String.valueOf(s.charAt(i));
            float sw = (float)font.getStringBounds(letter, frc).getWidth();
            float sx = (PAD - sw)/2;
            g2.drawString(letter, sx, sy);
            sy += sh;
        }
        // Abcissa label.
        s = "x axis";
        sy = h - PAD + (PAD - sh)/2 + lm.getAscent();
        float sw = (float)font.getStringBounds(s, frc).getWidth();
        float sx = (w - sw)/2;
        g2.drawString(s, sx, sy);
        //Scale increments
        xInc = (double)(w - 2*PAD)/getMaxX(points);
        yInc = (double)(h - 2*PAD)/getMaxY(points);

        // Mark data points.

        for(int i = 0; i < Stucture.N; i++) {
            g2.setPaint(Color.red);
            double x = PAD + (int)points[i].getX()*xInc;             //х-координата точки
            double y = h - PAD - (int)points[i].getY()*yInc;        //у-координата точки
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
            // point label.
            g2.setPaint(Color.black);
            s ="("+points[i].getX()+","+points[i].getY()+")";
            sy = (float) (y-2);
            sw = (float)font.getStringBounds(s, frc).getWidth();
            sx = (float) (x+1);
            g2.drawString(s, sx, sy);
        }
        
        //draw lines

        g2.setPaint(Color.green.darker());
//        g2.draw(new Rectangle(PAD+(int)xInc*15,h - PAD - (int)yInc*10,(int)xInc*5,(int)yInc*40));
        drawRectangles(rootNode);
    }

    public void drawRectangles(BinaryTree.Node rootNode) {
        if (rootNode != null) {
            if (rootNode.value!=null)     {
                g2.draw(new Rectangle(
                        (int)(PAD + (getMinX(rootNode.value) - 0.5)*xInc),
                        (int)(h - PAD - (getMaxY(rootNode.value) + 0.5)*yInc),
                        (int)((getMaxX(rootNode.value) - getMinX(rootNode.value) + 1)*xInc),
                        (int)((getMaxY(rootNode.value) - getMinY(rootNode.value) + 1)*yInc) + 6));
            }
            drawRectangles(rootNode.left);
            drawRectangles(rootNode.right);
        }
    }
    private int getMaxY(Point[] points) {
        int max = -Integer.MAX_VALUE;
        for(int i = 0; i < points.length; i++) {
            if((int)points[i].getY() > max)
                max = (int)points[i].getY();
        }
        return max;
    }

    private int getMinY(Point[] points) {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < points.length; i++) {
            if((int)points[i].getY() < min)
                min = (int)points[i].getY();
        }
        return min;
    }

    private int getMaxX(Point[] points) {
        int max = -Integer.MAX_VALUE;
        for(int i = 0; i < points.length; i++) {
            if((int)points[i].getX() > max)
                max = (int)points[i].getX();
        }
        return max;
    }

    private int getMinX(Point[] points) {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < points.length; i++) {
            if((int)points[i].getX() < min)
                min = (int)points[i].getX();
        }
        return min;
    }
}