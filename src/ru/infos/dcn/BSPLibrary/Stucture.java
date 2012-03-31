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
public class Stucture {
    private Point point;
    private Point[] pointArraySource;
    public static final int N = 12*4;     //количество точек
    public static final int minLeafSize = 4; //размер листа
    //todo сделать чтение N из файла
    public Stucture() {
        pointArraySource = new Point[N];
        for (int i=0;i<N;i++){
            point = new Point((int) round(random() * 50),(int) round(random() * 50));
            pointArraySource[i]=point;
        }
    }

    public Point[] getPointArraySource() {
        return pointArraySource;
    }

    public static void pointArrayPrint(Point[] points){
        if (points != null){
            for (int i=0;i<points.length;i++){
                    out.print("("+ points[i].getX()+","+ points[i].getY()+")");
            }
            out.println();
        }
    }

}

