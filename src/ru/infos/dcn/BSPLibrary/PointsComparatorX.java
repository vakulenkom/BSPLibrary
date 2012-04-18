package ru.infos.dcn.BSPLibrary;

import java.util.Comparator;

/**
 * User: mike
 * e-mail: vakulenkom@gmail.com
 * Date: 13.04.12
 * Time: 15:08
 */
public class PointsComparatorX implements Comparator<Point>{
    public int compare(Point p1, Point p2){
        return p1.getCoord()[0] - p2.getCoord()[0];
    }
}
