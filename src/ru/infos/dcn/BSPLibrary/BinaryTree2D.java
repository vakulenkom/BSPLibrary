package ru.infos.dcn.BSPLibrary;

import java.util.Arrays;

import static java.lang.Math.floor;
import static java.lang.System.arraycopy;

/**
 * Created by IntelliJ IDEA.
 * User: mike
 * Date: 14.03.12
 * Time: 20:01
 * To change this template use File | Settings | File Templates.
 */
public class BinaryTree2D {
    int sortType=1;   //тип сортировки 1=у 0=х
    //инициализация компораторов
    PointsComparatorX pointsComparatorX = new PointsComparatorX();
    PointsComparatorY pointsComparatorY = new PointsComparatorY();

    static class Node {
        Node left;
        Node right;
        Point[] value;

        public Node(Point[] value) {
            this.value = value;
        }
    }

    public BinaryTree2D(Node rootNode, Point[] pointsArray){
        recursiveTreeBuilding(rootNode, pointsArray);
    }

    private void recursiveTreeBuilding(Node rootNode, Point[] pointsArray) {
        sortType = (sortType + 1) % 2;  //сортировка по у или х
        if (sortType == 0){
            Arrays.sort(pointsArray, pointsComparatorX);
        }
        else if (sortType == 1){
            Arrays.sort(pointsArray, pointsComparatorY);
        }
        int leftNodeSize = (int)floor(pointsArray.length / 2);
        int rightNodeSize = pointsArray.length-leftNodeSize;
        Point[] nodePoints1;
        Point[] nodePoints2;

        nodePoints1 = new Point[leftNodeSize];
        arraycopy(pointsArray, 0, nodePoints1, 0, leftNodeSize);
        insert(rootNode, null, true);
        if (nodePoints1.length > Stucture.minLeafSize *2) {
            recursiveTreeBuilding(rootNode.left, nodePoints1);
        }
        else{
            insert(rootNode, nodePoints1, true);
        }

        nodePoints2 = new Point[rightNodeSize];
        arraycopy(pointsArray, leftNodeSize, nodePoints2, 0, rightNodeSize);
        insert(rootNode, null, false);
        if (nodePoints2.length > Stucture.minLeafSize *2) {
            recursiveTreeBuilding(rootNode.right, nodePoints2);
        }
        else{
            insert(rootNode, nodePoints2, true);
        }
        rootNode.value = null;
    }

    private void insert(Node node, Point[] value, boolean isLeft) {
        if (isLeft) {
            if (node.left != null) {
                insert(node.left, value, isLeft);
            } else {
                node.left = new Node(value);
            }
        } else {
            if (node.right != null) {
                insert(node.right, value, isLeft);
            } else {
                node.right = new Node(value);
            }
        }
    }
    //вывод дерева в консоль в порядке "PreOrder"
    public static void printPreOrder(Node node) {
        if (node != null) {
            Stucture.printPointArray(node.value);
            printPreOrder(node.left);
            printPreOrder(node.right);
        }
    }
}

