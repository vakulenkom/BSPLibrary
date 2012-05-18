package ru.infos.dcn.BSPLibrary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import static java.lang.Math.random;
import static java.lang.Math.round;

/**
 * User: mike
 * e-mail: vakulenkom@gmail.com
 * Date: 20.04.12
 * Time: 0:44
 */

class PaintPanel extends JPanel {
    private ArrayList<Point> pointsArrayForMouseAction = new ArrayList<Point>();
    private Point[] pointsArrayForTreeData;
    private final int PAD = 50;
    private static final int colorsNumber = Stucture.N / Stucture.minLeafSize; //количество цветов прямоугольников
    private Color[] pColor = new Color[colorsNumber]; //массив цветов прямоугольника
    private BinaryTree2D.Node rootNode;
    private Graphics2D graphicContext2D;
    private double xCoordinateCoeff, yCoordinateCoeff;    //растягивание точек по осям
    private int h;
    private int w;
    private int colorNum;  //номер цвета из массива цветов прямоугольника
    private BinaryTree2D bspTree;

    public PaintPanel(BinaryTree2D bspTree) {
        this.pointsArrayForTreeData = bspTree.getBSPTree2DPointsArray();
        this.rootNode = bspTree.rootNode;
        this.bspTree = bspTree;
        //задание рандомных цветов для прямоугольника
        int red = 255;
        int green = 0;
        int blue = 0;
        for (int i=0; i<colorsNumber;i++) {
            pColor[i] = new Color(red,green,blue);
            red = (int) round (random() * 1000) % 256 ;
            green = (int) round (random() * 1000) % 256;
            blue = (int) round (random() * 1000) % 256;
        }
        colorNum = 0;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addPoint(new Point(e.getX(), e.getY()));
                repaint();
            }
        });
    }

    public void addPoint(Point p) {
        if (!pointsArrayForMouseAction.contains(p))
            pointsArrayForMouseAction.add(p);
    }

    private int getMaxCoord(Point[] points, int coord) {
        //coord value means x or y
        int max = -Integer.MAX_VALUE;
        for(int i = 0; i < points.length; i++) {
            if((int)points[i].coord[coord] > max)
                max = (int)points[i].coord[coord];
        }
        return max;
    }

    private int getMinCoord(Point[] points, int coord) {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < points.length; i++) {
            if((int)points[i].coord[coord] < min)
                min = (int)points[i].coord[coord];
        }
        return min;
    }

    @Override
    public void paintComponent(Graphics g) {
        graphicContext2D = (Graphics2D) g;
        graphicContext2D.clearRect(0, 0, this.getWidth(), this.getHeight());
        graphicContext2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        w = getWidth();
        h = getHeight();
        // Draw ordinate.
        graphicContext2D.draw(new Line2D.Double(PAD, PAD, PAD, h - PAD));
        // Draw abcissa.
        graphicContext2D.draw(new Line2D.Double(PAD, h - PAD, w - PAD, h - PAD));
        // Draw labels.
        Font font = graphicContext2D.getFont();
        FontRenderContext frc = graphicContext2D.getFontRenderContext();
        LineMetrics lm = font.getLineMetrics("0", frc);
        float sh = lm.getAscent() + lm.getDescent();
        // Ordinate label.
        String s = "y axis";
        float sy = PAD + ((h - 2*PAD) - s.length()*sh)/2 + lm.getAscent();
        for(int i = 0; i < s.length(); i++) {
            String letter = String.valueOf(s.charAt(i));
            float sw = (float)font.getStringBounds(letter, frc).getWidth();
            float sx = (PAD - sw)/2;
            graphicContext2D.drawString(letter, sx, sy);
            sy += sh;
        }
        // Abcissa label.
        s = "x axis";
        sy = h - PAD + (PAD - sh)/2 + lm.getAscent();
        float sw = (float)font.getStringBounds(s, frc).getWidth();
        float sx = (w - sw)/2;
        graphicContext2D.drawString(s, sx, sy);
        //Scale increments
        xCoordinateCoeff = (double)(w - 2*PAD)/ getMaxCoord(pointsArrayForTreeData, 0);
        yCoordinateCoeff = (double)(h - 2*PAD)/ getMaxCoord(pointsArrayForTreeData, 1);
        //рисуем точки из дерева
        drawPointsWithColor(Color.blue, pointsArrayForTreeData);
        //рисуем точки для мыши
        for (Point p : pointsArrayForMouseAction) {
            graphicContext2D.fillRect(p.coord[0] - 3, p.coord[1] - 3, 7, 7);
        }
        if (pointsArrayForMouseAction.size()==2)  {
            graphicContext2D.draw(new Rectangle(
                    pointsArrayForMouseAction.get(0).coord[0],
                    pointsArrayForMouseAction.get(0).coord[1],
                    pointsArrayForMouseAction.get(1).coord[0] - pointsArrayForMouseAction.get(0).coord[0],
                    pointsArrayForMouseAction.get(1).coord[1] - pointsArrayForMouseAction.get(0).coord[1]));
            Point[] rectPoints = new Point[2];
            rectPoints[0] = new Point();
            rectPoints[1] = new Point();
            rectPoints[0].coord[0] = Math.round((int)((pointsArrayForMouseAction.get(0).coord[0] - PAD) / xCoordinateCoeff));
            rectPoints[0].coord[1] = Math.round((int) ((-pointsArrayForMouseAction.get(0).coord[1] - PAD + h) / yCoordinateCoeff));
            rectPoints[1].coord[0] = Math.round((int) ((pointsArrayForMouseAction.get(1).coord[0] - PAD) / xCoordinateCoeff));
            rectPoints[1].coord[1] = Math.round((int) ((-pointsArrayForMouseAction.get(1).coord[1] - PAD + h) / yCoordinateCoeff));
            ArrayList<Point> foundedPoints = bspTree.findPointsInRectangle(rectPoints);
            drawPointsWithColor(Color.red, arrayList2Array(foundedPoints));
            pointsArrayForMouseAction.clear();
            System.out.println();
        }
        //рисуем прямоугольники

        colorNum = 0;
        drawRectangles(rootNode);
    }

    private Point[] arrayList2Array(ArrayList<Point> points){
        Point[] newPoints = new Point[points.size()];
        for (int i=0; i < newPoints.length; i++){
            newPoints[i] = points.get(i);
        }
        return newPoints;
    }
    private void drawPointsWithColor(Color color, Point[] points) {
        String s;
        float sy;
        float sx;
        for (Point p : points) {
            graphicContext2D.setPaint(color);
            double x = PAD + (int)p.coord[0]* xCoordinateCoeff;             //х-координата точки
            double y = h - PAD - (int)p.coord[1]* yCoordinateCoeff;         //у-координата точки
            graphicContext2D.fill(new Ellipse2D.Double(x - 2, y - 2, 4, 4));
            // point label.
            graphicContext2D.setPaint(Color.black);
            s ="("+p.coord[0]+","+p.coord[1]+")";
            sy = (float) (y-2);
            sx = (float) (x+1);
            graphicContext2D.drawString(s, sx, sy);
        }
    }

    public void drawRectangles(BinaryTree2D.Node rootNode) {
        if (rootNode != null) {
            if (rootNode.value!=null)     {
                graphicContext2D.setPaint(pColor[colorNum]);
                colorNum = (colorNum + 1) % colorsNumber;
                graphicContext2D.draw(new Rectangle(
                        (int) (PAD + (getMinCoord(rootNode.value, 0) - 0.2) * xCoordinateCoeff),
                        (int) (h - PAD - (getMaxCoord(rootNode.value, 1) + 0.2) * yCoordinateCoeff),
                        (int) ((getMaxCoord(rootNode.value, 0) - getMinCoord(rootNode.value, 0) + 0.5) * xCoordinateCoeff),
                        (int) ((getMaxCoord(rootNode.value, 1) - getMinCoord(rootNode.value, 1) + 0.5) * yCoordinateCoeff) + 1));
            }
            else{
                graphicContext2D.setPaint(pColor[colorNum]);
                colorNum = (colorNum + 1) % colorsNumber;
                if (rootNode.sortType == 0){
                    graphicContext2D.drawLine((int)(PAD + rootNode.edgeCoordMin * xCoordinateCoeff), PAD,
                            (int)(PAD + rootNode.edgeCoordMin * xCoordinateCoeff), h - PAD);
                    graphicContext2D.drawLine((int)(PAD + rootNode.edgeCoordMax * xCoordinateCoeff), PAD,
                            (int)(PAD + rootNode.edgeCoordMax * xCoordinateCoeff), h - PAD);
                }
                else {
                    graphicContext2D.drawLine(PAD,h - PAD - (int)(rootNode.edgeCoordMin * yCoordinateCoeff) ,
                            w - PAD, h - PAD - (int)(rootNode.edgeCoordMin * yCoordinateCoeff));
                    graphicContext2D.drawLine(PAD,h - PAD - (int)(rootNode.edgeCoordMax * yCoordinateCoeff) ,
                            w - PAD, h - PAD - (int)(rootNode.edgeCoordMax * yCoordinateCoeff));
                }
            }
            drawRectangles(rootNode.left);
            drawRectangles(rootNode.right);
        }
    }
}
