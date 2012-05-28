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
    private Point[] pointArraySource;
    public static final int N = 16;     //количество точек
//    public static final int N = 1000000;
    public static final int minLeafSize = 2; //минимальный размер листа
    //todo сделать чтение N из файла
    public Stucture() {
        pointArraySource = new Point[N];
        for (int i=0;i<N;i++){
            pointArraySource[i] = new Point((int) round(random() * 50),(int) round(random() * 50));
        }
    }

    public Point[] getPointArraySource() {
        return pointArraySource;
    }

    public static void printPointArray(Point[] points){
        if (points != null){
            for (int i=0;i<points.length;i++){
                    out.print("("+ points[i].coord[0]+","+ points[i].coord[1]+")");
            }
            out.println();
        }
    }

}

