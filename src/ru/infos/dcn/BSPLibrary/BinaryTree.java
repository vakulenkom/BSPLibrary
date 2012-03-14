package ru.infos.dcn.BSPLibrary;

import static java.lang.System.out;

/**
 * Created by IntelliJ IDEA.
 * User: mike
 * Date: 14.03.12
 * Time: 20:01
 * To change this template use File | Settings | File Templates.
 */
public class BinaryTree {
    static class Node {
        Node left;
        Node right;
        Point[] value;

        public Node(Point[] value) {
            this.value = value;
        }
    }

    public static void insert(Node node, Point[] value, boolean isLeft) {
        if (isLeft) {
            if (node.left != null) {
                insert(node.left, value, isLeft);
            } else {
                out.println("\nInserted to left of node ");
                node.left = new Node(value);
            }
        } else {
            if (node.right != null) {
                insert(node.right, value, isLeft);
            } else {
                out.println("\nInserted to right of node ");
                node.right = new Node(value);
            }
        }
    }
    public static void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            Analyser.treePrint(node.value);
            System.out.println("\nNode printed");
            printInOrder(node.right);
        }
    }
}

