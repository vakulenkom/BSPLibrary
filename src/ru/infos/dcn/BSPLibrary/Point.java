package ru.infos.dcn.BSPLibrary;

/**
 * Created by IntelliJ IDEA.
 * User: mike
 * Date: 02.03.12
 * Time: 12:57
 * To change this template use File | Settings | File Templates.
 */
public class Point {
    private int x;
    private int y;

    public Point(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
