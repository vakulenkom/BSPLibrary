package ru.infos.dcn.BSPLibrary;

import javax.swing.*;
import java.util.ArrayList;

/**
 * User: mike
 * e-mail: vakulenkom@gmail.com
 * Date: 20.04.12
 * Time: 0:39
 */
    class PaintFrame extends JFrame {
        public PaintFrame(Point[] points, BinaryTree2D.Node rootNode) {
            setTitle("BSP data plot");
            setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

            PaintPanel panel = new PaintPanel(points, rootNode);
            add(panel);
        }

        public static final int DEFAULT_WIDTH=800;
        public static final int DEFAULT_HEIGHT=800;
    }
