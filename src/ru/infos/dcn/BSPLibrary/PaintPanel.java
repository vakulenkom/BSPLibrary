package ru.infos.dcn.BSPLibrary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * User: mike
 * e-mail: vakulenkom@gmail.com
 * Date: 20.04.12
 * Time: 0:44
 */

class PaintPanel extends JPanel {
    private ArrayList<Point> points;

    public PaintPanel(ArrayList<Point> points) {
        this.points = points;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addPoint(new Point(e.getX(), e.getY()));
                repaint();
            }
        });
    }

    public void addPoint(Point p) {
        if (!points.contains(p))
            points.add(p);
    }

    @Override
    public void paintComponent(Graphics g) {
        for (Point p : points) {
            g.fillRect(p.getCoord()[0]-3, p.getCoord()[1]-3, 7, 7);
        }
    }
}
