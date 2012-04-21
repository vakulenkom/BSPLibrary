package ru.infos.dcn.BSPLibrary;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.*;
import static java.lang.System.arraycopy;
import static java.lang.System.out;

/**
 * Created by IntelliJ IDEA.
 * User: mike
 * Date: 14.03.12
 * Time: 20:01
 * To change this template use File | Settings | File Templates.
 */
public final class BinaryTree2D {
    //инициализация компораторов
    PointsComparatorX pointsComparatorX = new PointsComparatorX();
    PointsComparatorY pointsComparatorY = new PointsComparatorY();
    final Node rootNode = new Node();
    Point[] sourcePoints;
    static class Node {
        Node left;
        Node right;
        Point[] value;

        public Node(Point[] value) {
            this.value = value;
        }

        public Node() {
        }
    }

    public BinaryTree2D (Point[] points){
        sourcePoints = points;  //потом сделать не копию по ссылке а копированием значений
        this.makeBinaryTree2D(rootNode, points);
    }

    private void makeBinaryTree2D(Node rootNode, Point[] pointsArray){
        recursiveTreeBuilding(rootNode, pointsArray, 1);
    }

    private void recursiveTreeBuilding(Node rootNode, Point[] pointsArray, int sortType) {
        sortType = sortType % 2;
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
        if (nodePoints1.length > Stucture.minLeafSize * 2) {
            recursiveTreeBuilding(rootNode.left, nodePoints1, sortType + 1);
        }
        else{
            insert(rootNode, nodePoints1, true);
        }
        nodePoints2 = new Point[rightNodeSize];
        arraycopy(pointsArray, leftNodeSize, nodePoints2, 0, rightNodeSize);
        insert(rootNode, null, false);
        if (nodePoints2.length > Stucture.minLeafSize * 2) {
            recursiveTreeBuilding(rootNode.right, nodePoints2, sortType + 1);
        }
        else{
            insert(rootNode, nodePoints2, false);
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

//    private void treeTraverse(Node node){
//        if (node != null) {
//            for (Point p : node.value){
//                pointsInLeafs.add(p);
//                treeTraverse(node.left);
//                treeTraverse(node.right);
//            }
//        }
//    }
    //вывод дерева в консоль в порядке "PreOrder"
    private void printPreOrder(Node node) {
        if (node != null) {
            Stucture.printPointArray(node.value);
            printPreOrder(node.left);
            printPreOrder(node.right);
        }
    }

    public void print(){
        this.printPreOrder(rootNode);
    }

    public Node getBSPTree2DRootNode(){
        return this.rootNode;
    }

    public Point[] getBSPTree2DPointsArray(){
//        treeTraverse(this.rootNode);
        return sourcePoints;
    }

    public void drawBSPTreeJavaGUI(){
//        EventQueue.invokeLater(new Runnable() {  //Что это?
//            public void run() {
                PaintFrame frame = new PaintFrame(this );
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
//            }
//        });
    }

//    public Point[] searchPointsForRectangle{
//        return ;
//    }
}

