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


        Analyser.treePrint(analyser.getPointArraySource());
        Point[] sortPoints = quickSort.sort(analyser.getPointArraySource(), QuickSort.PointType.x);
        System.out.println("\nAfter sort");
        Analyser.treePrint(sortPoints);

        Node rootNode = new Node(analyser.getPointArraySource());

        addToTree(rootNode, sortPoints);

        printPreOrder(rootNode);


    }

    private static void addToTree(Node rootNode, Point[] sortPoints){
        int leftNodeSize = (int)floor(sortPoints.length / 2);
        int rightNodeSize = sortPoints.length-leftNodeSize;
        Point[] nodePoints1;
        Point[] nodePoints2;

        nodePoints1 = new Point[leftNodeSize];
        System.out.println("size= "+leftNodeSize+" length="+nodePoints1.length);
        System.arraycopy(sortPoints, 0, nodePoints1,0, leftNodeSize);
        insert(rootNode, nodePoints1, true);
        if (nodePoints1.length>1){
            addToTree(rootNode.left,nodePoints1);
        }

        nodePoints2 = new Point[rightNodeSize];   //Почему это нужно делать каждый раз???!!!
        System.arraycopy(sortPoints, leftNodeSize, nodePoints2,0, rightNodeSize);
        insert(rootNode, nodePoints2, false);
        if (nodePoints2.length>1){
            addToTree(rootNode.right,nodePoints2);
        }
    }
}
