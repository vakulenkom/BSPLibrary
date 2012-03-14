package ru.infos.dcn.BSPLibrary;

import static java.lang.Math.*;
import static java.lang.System.*;

/**
 * Created by IntelliJ IDEA.
 * User: mike
 * Date: 02.03.12
 * Time: 13:53
 * To change this template use File | Settings | File Templates.
 */
public class Analyser {
    private Point point;
    private Point[] pointArraySource;
    private Point[] pointArrayNode;
    public static final int N = 10;     //количество точек

    public Analyser() {
        pointArraySource = new Point[N];
        for (int i=0;i<10;i++){
            point = new Point((int) round(random() * 15),(int) round(random() * 80));
            pointArraySource[i]=point;
        }
    }

    public Point[] getPointArraySource() {
        return pointArraySource;
    }

    public void divide(){
        //while
//        QuickSortX //returns Point[]
//        divide
//        QuickSortY
    }

    public void treePrint(Point[] points){
        for (int i=0;i<10;i++){
            out.print("("+ points[i].getX()+","+ points[i].getY()+")");
        }
    }
}

