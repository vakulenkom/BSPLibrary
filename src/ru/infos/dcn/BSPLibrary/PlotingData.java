package ru.infos.dcn.BSPLibrary;

import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;

public class PlotingData extends JPanel {
//    Stucture stucture =new Stucture();
    final int PAD = 50;
    Point[] points;
    BinaryTree.Node rootNode;
    Graphics2D g2;
    double xInc, yInc;
    int h;
    int paintColor = 0;
    MouseEvent mouseEvent;
    
    public PlotingData(Point[] points, BinaryTree.Node rootNode) {
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
        xInc = (double)(w - 2*PAD)/ getMaxCoord(points, 0);
        yInc = (double)(h - 2*PAD)/ getMaxCoord(points, 1);

        // Mark data points.
        for(int i = 0; i < Stucture.N; i++) {
            g2.setPaint(Color.red);
            double x = PAD + (int)points[i].getCoord()[0]*xInc;             //х-координата точки
            double y = h - PAD - (int)points[i].getCoord()[1]*yInc;        //у-координата точки
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
            // point label.
            g2.setPaint(Color.black);
            s ="("+points[i].getCoord()[0]+","+points[i].getCoord()[1]+")";
            sy = (float) (y-2);
            sw = (float)font.getStringBounds(s, frc).getWidth();
            sx = (float) (x+1);
            g2.drawString(s, sx, sy);
        }
        
        //draw rectangles
        drawRectangles(rootNode);

        //mouse interaction
        g2.setPaint(Color.pink);
        MouseInteraction();
    }

    private void MouseInteraction() {
//        long eventMask = AWTEvent.MOUSE_MOTION_EVENT_MASK + AWTEvent.MOUSE_EVENT_MASK;
        long eventMask = AWTEvent.MOUSE_EVENT_MASK;
        Toolkit.getDefaultToolkit().addAWTEventListener( new AWTEventListener()
        {
            public void eventDispatched(AWTEvent e)
            {
                mouseEvent = (MouseEvent)e;
//                if (mouseEvent.getID() )
                System.out.println(mouseEvent.getID());
                if (mouseEvent.getID()==501){
                    g2.fill(new Ellipse2D.Double(mouseEvent.getX(), mouseEvent.getY(), 6, 6));
//                    g2.fill(new Ellipse2D.Double(PAD + 10*(int)xInc,h - PAD - 10*(int)yInc, 40, 10));
                    System.out.println("make ellipse!!!");
                }

                System.out.println(mouseEvent);
            }
        }, eventMask);
        
    }

    public void drawRectangles(BinaryTree.Node rootNode) {
        if (rootNode != null) {
            if (rootNode.value!=null)     {
                paintColor = (paintColor + 1) % 5;
                switch (paintColor){
                    case 0:
                        g2.setPaint(Color.orange.darker());
                        break;
                    case 1:
                        g2.setPaint(Color.yellow.darker());
                        break;
                    case 2:
                        g2.setPaint(Color.green.darker());
                        break;
                    case 3:
                        g2.setPaint(Color.blue.darker());
                        break;
                    case 4:
                        g2.setPaint(Color.magenta.darker());
                        break;
                }
                g2.draw(new Rectangle(
                        (int)(PAD + (getMinCoord(rootNode.value, 0) - 0.5)*xInc),
                        (int)(h - PAD - (getMaxCoord(rootNode.value, 1) + 0.5)*yInc),
                        (int)((getMaxCoord(rootNode.value, 0) - getMinCoord(rootNode.value, 0) + 1)*xInc),
                        (int)((getMaxCoord(rootNode.value, 1) - getMinCoord(rootNode.value, 1) + 1)*yInc) + 6));
            }
            drawRectangles(rootNode.left);
            drawRectangles(rootNode.right);
        }
    }
    private int getMaxCoord(Point[] points, int coord) {
        //coord value means x or y
        int max = -Integer.MAX_VALUE;
        for(int i = 0; i < points.length; i++) {
            if((int)points[i].getCoord()[coord] > max)
                max = (int)points[i].getCoord()[coord];
        }
        return max;
    }

    private int getMinCoord(Point[] points, int coord) {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < points.length; i++) {
            if((int)points[i].getCoord()[coord] < min)
                min = (int)points[i].getCoord()[coord];
        }
        return min;
    }
}