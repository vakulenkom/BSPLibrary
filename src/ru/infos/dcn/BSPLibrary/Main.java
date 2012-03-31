package ru.infos.dcn.BSPLibrary;

import javax.swing.*;
import java.util.TreeSet;

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


        out.println("Random array:");
        Stucture.treePrint(stucture.getPointArraySource());
//        Point[] sortPoints = QuickSort.sort(stucture.getPointArraySource(), QuickSort.PointType.x);
//        out.println("Array after sort:");
//        Stucture.treePrint(sortPoints);

        Node rootNode = new Node(stucture.getPointArraySource());

        BinaryTree bspTree = new BinaryTree(rootNode, stucture.getPointArraySource());

        out.println("BSP print preorder:");
        printPreOrder(rootNode);


        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new GraphingData(stucture.getPointArraySource()));
        f.setSize(800,800);
        f.setLocation(400,400);
        f.setVisible(true);
        f.setEnabled(true);

    }
}
