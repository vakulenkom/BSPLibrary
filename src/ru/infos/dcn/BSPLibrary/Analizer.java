package ru.infos.dcn.BSPLibrary;
import java.lang.Math.*;

import static java.lang.System.*;

/**
 * Created by IntelliJ IDEA.
 * User: mike
 * Date: 02.03.12
 * Time: 13:53
 * To change this template use File | Settings | File Templates.
 */
public class Analizer {
    private Point point;
    private Point[] pointArray1;
    private Point[] pointArray2;

    public Analizer() {
        this.point = point;
        pointArray1 = new Point[10];
        for (int i=0;i<10;i++){
           point = new Point(i,i);
        }
        for (int i=0;i<10;i++){
            pointArray1[i]=point;
        }
    }

    public boolean analize(Point point1, Point point2){
        double scalarComposition;
        scalarComposition = point.getX()*point.getY()*Math.sin(point.getX());
        return scalarComposition>0;
    }

    public void treeBuilding(){

    }

    public void treePrint(){
        for (int i=0;i<10;i++){
            out.println("x="+pointArray1[i].getX()+"y="+pointArray1[i].getY());
        }
    }
}

