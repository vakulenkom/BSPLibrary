package ru.infos.dcn.BSPLibrary;

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
        Analyser analyser = new Analyser();
        QuickSort quickSort = new QuickSort();
        analyser.treePrint(analyser.getPointArraySource());
        Point[] sortPoints = quickSort.sort(analyser.getPointArraySource(), QuickSort.PointType.y);
        System.out.println("\nAfter sort");
        analyser.treePrint(sortPoints);

    }
}
