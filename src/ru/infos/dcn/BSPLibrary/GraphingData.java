package ru.infos.dcn.BSPLibrary;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;

public class GraphingData extends JPanel {
    Stucture stucture =new Stucture();
    final int PAD = 50;

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
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
        double xInc = (double)(w - 2*PAD)/getMaxX();
        double yInc = (double)(h - 2*PAD)/getMaxY();

        // Draw lines.
//        g2.setPaint(Color.green.darker());
//        for(int i = 0; i < data.length-1; i++) {
//            double x1 = PAD + i*xInc;
//            double y1 = h - PAD - scale*data[i];
//            double x2 = PAD + (i+1)*xInc;
//            double y2 = h - PAD - scale*data[i+1];
//            g2.draw(new Line2D.Double(x1, y1, x2, y2));
//        }

        //initialize data
//        dataX = new int[stucture.N];
//        dataY = new int[stucture.N];
//        for(int i = 0; i < stucture.N; i++) {
//            dataX[i]=(int)stucture.getPointArraySource()[i].getX();
//            System.out.println("x"+i+"= "+dataX[i]);
//            dataY[i]=(int)stucture.getPointArraySource()[i].getY();
//            System.out.println("y"+i+"= "+dataY[i]);
//        }

        // Mark data points.

        for(int i = 0; i < Stucture.N; i++) {
            g2.setPaint(Color.red);
            double x = PAD + (int)stucture.getPointArraySource()[i].getX()*xInc;             //х-координата точки
            double y = h - PAD - (int)stucture.getPointArraySource()[i].getY()*yInc;        //у-координата точки
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
            // point label.
            g2.setPaint(Color.black);
            s ="("+stucture.getPointArraySource()[i].getX()+","+stucture.getPointArraySource()[i].getY()+")";
            sy = (float) (y-2);
            sw = (float)font.getStringBounds(s, frc).getWidth();
            sx = (float) (x+1);
            g2.drawString(s, sx, sy);
        }
    }
    private int getMaxY() {
        int max = -Integer.MAX_VALUE;
        for(int i = 0; i < Stucture.N; i++) {
            if((int)stucture.getPointArraySource()[i].getY() > max)
                max = (int)stucture.getPointArraySource()[i].getY();
        }
        return max;
    }

    private int getMaxX() {
        int max = -Integer.MAX_VALUE;
        for(int i = 0; i < Stucture.N; i++) {
            if((int)stucture.getPointArraySource()[i].getX() > max)
                max = (int)stucture.getPointArraySource()[i].getX();
        }
        return max;
    }
}