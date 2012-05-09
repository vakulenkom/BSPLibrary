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
    final public int[] coord = new int[2];


    public Point(int x, int y){
        this.coord[0] = x;
        this.coord[1] = y;
        }
    @Override
    public boolean equals(Object p) {
        if (p == this)
            return true;
        if (p == null)
            return false;
        if (p.getClass() != this.getClass())
            return false;

        final Point t = (Point) p;
        return t.coord[0] == coord[0] && t.coord[1] == coord[1];
    }

    @Override
    public int hashCode() {
        return coord[0]*1024 + coord[1];
    }
}
