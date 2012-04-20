package ru.infos.dcn.BSPLibrary;

import javax.swing.*;

import java.awt.*;

import static java.lang.System.*;
import static ru.infos.dcn.BSPLibrary.BinaryTree2D.*;

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
        final Stucture stucture = new Stucture();


        out.println("Random array:");
        Stucture.printPointArray(stucture.getPointArraySource());

        final Node rootNode = new Node(stucture.getPointArraySource());

        BinaryTree2D bspTree = new BinaryTree2D(rootNode, stucture.getPointArraySource());

        out.println("BSP print preorder:");
        printPreOrder(rootNode);


        EventQueue.invokeLater(new Runnable() {  //Что это?
            public void run() {
                PaintFrame frame = new PaintFrame(stucture.getPointArraySource(), rootNode);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
//        JFrame f = new JFrame();
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.add(new PlotingData(stucture.getPointArraySource(), rootNode));
//        f.setSize(800,800);
//        f.setLocation(400,400);
//        f.setVisible(true);
//        f.setEnabled(true);

    }
}
