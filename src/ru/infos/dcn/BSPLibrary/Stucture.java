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
    public static final int N = 20;     //количество точек
    //todo сделать чтение N из файла
    public Stucture() {
        pointArraySource = new Point[N];
        for (int i=0;i<N;i++){
            point = new Point((int) round(random() * 15),(int) round(random() * 80));
            pointArraySource[i]=point;
        }
    }

    public Point[] getPointArraySource() {
        return pointArraySource;
    }

    public static void treePrint(Point[] points){
        if (points != null){
            for (int i=0;i<points.length;i++){
                    out.print("("+ points[i].getX()+","+ points[i].getY()+")");
            }
            out.println();
        }
    }
    //todo проверить дана точка и радиус есть ли в этом радиусе точки (или в квадрате) публичный метод в публичном классе

}

