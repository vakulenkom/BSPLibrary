package ru.infos.dcn.BSPLibrary;
import java.lang.Math.*;
/**
 * Created by IntelliJ IDEA.
 * User: mike
 * Date: 02.03.12
 * Time: 13:53
 * To change this template use File | Settings | File Templates.
 */
public class Analizer {
    private Point point;
    private Point[] pointArray;

    public Analizer(Point point) {
        this.point = point;
    }

    public boolean analize(){
        double scalarComposition;
        scalarComposition = point.getX()*point.getY()*Math.sin(point.getX());
        return scalarComposition>0;
    }
}

