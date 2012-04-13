package ru.infos.dcn.BSPLibrary;

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
//    private class PointsComparator implements Comparable <Point>{
//
//        public int compareTo(Point point) {
//            return 0;  //To change body of implemented methods use File | Settings | File Templates.
//        }
//    }
}
