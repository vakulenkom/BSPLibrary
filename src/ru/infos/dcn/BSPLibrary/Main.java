package ru.infos.dcn.BSPLibrary;

import static java.lang.Math.floor;
import static java.lang.System.*;
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
        Stucture stucture = new Stucture();
        QuickSort quickSort = new QuickSort();


        out.println("Random array:");
        Stucture.treePrint(stucture.getPointArraySource());
        Point[] sortPoints = quickSort.sort(stucture.getPointArraySource(), QuickSort.PointType.x);
        out.println("Array after sort:");
        Stucture.treePrint(sortPoints);

        Node rootNode = new Node(stucture.getPointArraySource());

        addToTree(rootNode, sortPoints);

        out.println("BSP print preorder:");
        printPreOrder(rootNode);


    }

    private static void addToTree(Node rootNode, Point[] sortPoints){
        int leftNodeSize = (int)floor(sortPoints.length / 2);
        int rightNodeSize = sortPoints.length-leftNodeSize;
        Point[] nodePoints1;
        Point[] nodePoints2;

        nodePoints1 = new Point[leftNodeSize];
        arraycopy(sortPoints, 0, nodePoints1, 0, leftNodeSize);
        insert(rootNode, null, true);
        if (nodePoints1.length>3){
            addToTree(rootNode.left,nodePoints1);
        }
        else{
            insert(rootNode, nodePoints1, true);
        }

        nodePoints2 = new Point[rightNodeSize];
        arraycopy(sortPoints, leftNodeSize, nodePoints2, 0, rightNodeSize);
        insert(rootNode, null, false);
        if (nodePoints2.length>3){
            addToTree(rootNode.right,nodePoints2);
        }
        else{
            insert(rootNode, nodePoints2, true);
        }
    }
}
