package ru.infos.dcn.BSPLibrary;

import static java.lang.Math.floor;
import static ru.infos.dcn.BSPLibrary.BinaryTree.*;

/**
 * Created by IntelliJ IDEA.
 * User: mike
 * Date: 14.03.12
 * Time: 0:59
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String [ ] args)
    {
        Analyser analyser = new Analyser();
        QuickSort quickSort = new QuickSort();
        Point[] nodePoints = new Point[analyser.N/2];

//        Analyser.treePrint(analyser.getPointArraySource());
        Node rootNode = new Node(analyser.getPointArraySource());
        Point[] sortPoints = quickSort.sort(analyser.getPointArraySource(), QuickSort.PointType.x);
        System.out.println("\nAfter sort");
        Analyser.treePrint(sortPoints);
        int leftNodeSize = (int)floor(sortPoints.length / 2);
        System.arraycopy(sortPoints, 0, nodePoints,0, leftNodeSize);
        insert(rootNode, nodePoints, true);

        nodePoints = new Point[analyser.N/2];
        System.arraycopy(sortPoints, leftNodeSize, nodePoints,0, sortPoints.length-leftNodeSize);
        insert(rootNode, nodePoints, false);

        printInOrder(rootNode);

    }
}
