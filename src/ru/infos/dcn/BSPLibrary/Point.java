package ru.infos.dcn.BSPLibrary;

import static java.lang.System.out;

/**
 * Created by IntelliJ IDEA.
 * User: mike
 * Date: 02.03.12
 * Time: 12:57
 * To change this template use File | Settings | File Templates.
 */
public class Point{
    final private int[] coord = new int[2];

    public Point(int x, int y){
        this.coord[0] = x;
        this.coord[1] = y;
        }

    public int[] getCoord(){
        return coord;

    }



    public static void main(String [] args){
        Point point1 = new Point(1, 0);
        Point point2 = new Point(0,0);
        Point[] points = new Point [2];
        points[0] = point1;
        points[1] = point2;

        PointsComparatorX pointsComparatorX = new PointsComparatorX();

        out.println();
        java.util.Arrays.sort(points, pointsComparatorX);
        Stucture.printPointArray(points);
    }
}
