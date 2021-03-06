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
        public PaintFrame(BinaryTree2D bspTree) {
            setTitle("BSP data plot");
            setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

            PaintPanel panel = new PaintPanel(bspTree);
            add(panel);
        }

        public static final int DEFAULT_WIDTH=800;
        public static final int DEFAULT_HEIGHT=800;
    }
