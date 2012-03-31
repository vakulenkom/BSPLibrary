package ru.infos.dcn.BSPLibrary;

import static java.lang.Math.floor;
import static java.lang.System.arraycopy;

/**
 * Created by IntelliJ IDEA.
 * User: mike
 * Date: 14.03.12
 * Time: 20:01
 * To change this template use File | Settings | File Templates.
 */
public class BinaryTree {
    private QuickSort quickSort;
    int sortType=1;



    static class Node {
        Node left;
        Node right;
        Point[] value;

        public Node(Point[] value) {
            this.value = value;
        }
    }
    public BinaryTree (Node rootNode, Point[] pointsArray){
        quickSort = new QuickSort();
        recursiveTreeBuilding(rootNode, pointsArray);
    }

    private void recursiveTreeBuilding(Node rootNode, Point[] pointsArray) {
        sortType = (sortType + 1) % 2;
        pointsArray = quickSort.sort(pointsArray, sortType);
        int leftNodeSize = (int)floor(pointsArray.length / 2);
        int rightNodeSize = pointsArray.length-leftNodeSize;
        Point[] nodePoints1;
        Point[] nodePoints2;

        nodePoints1 = new Point[leftNodeSize];
        arraycopy(pointsArray, 0, nodePoints1, 0, leftNodeSize);
        insert(rootNode, null, true);
        if (nodePoints1.length > Stucture.leafSize) {
            recursiveTreeBuilding(rootNode.left, nodePoints1);
        }
        else{
            insert(rootNode, nodePoints1, true);
        }

        nodePoints2 = new Point[rightNodeSize];
        arraycopy(pointsArray, leftNodeSize, nodePoints2, 0, rightNodeSize);
        insert(rootNode, null, false);
        if (nodePoints2.length > Stucture.leafSize) {
            recursiveTreeBuilding(rootNode.right, nodePoints2);
        }
        else{
            insert(rootNode, nodePoints2, true);
        }
    }

    public static void insert(Node node, Point[] value, boolean isLeft) {
        if (isLeft) {
            if (node.left != null) {
                insert(node.left, value, isLeft);
            } else {
//                out.println("\nInserted to left of node ");
                node.left = new Node(value);
            }
        } else {
            if (node.right != null) {
                insert(node.right, value, isLeft);
            } else {
//                out.println("\nInserted to right of node ");
                node.right = new Node(value);
            }
        }
    }
    public static void printPreOrder(Node node) {
        if (node != null) {
            Stucture.pointArrayPrint(node.value);
//            System.out.println();
            printPreOrder(node.left);
            printPreOrder(node.right);
        }
    }
}

