package ru.infos.dcn.BSPLibrary;

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

    public Analyser() {
        pointArraySource = new Point[10];
        for (int i=0;i<10;i++){
            point = new Point(Math.round(Math.random() * 15), Math.round(Math.random() * 80));
            pointArraySource[i]=point;
        }
    }

    public Point[] getPointArraySource() {
        return pointArraySource;
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
            out.println("x= "+ pointArraySource[i].getX()+" y="+ pointArraySource[i].getY());
        }
    }
}

