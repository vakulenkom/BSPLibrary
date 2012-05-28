package ru.infos.dcn.BSPLibrary;

import static java.lang.System.*;

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
        final BinaryTree2D bspTree = new BinaryTree2D(stucture.getPointArraySource());

        out.println("BSP print preorder:");
        bspTree.print();

        bspTree.drawBSPTreeJavaGUI();
    }

}
