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
}
