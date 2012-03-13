package ru.infos.dcn.BSPLibrary;

public class QuickSort  {
    private int[] numbers;
    private int number;
    private Point[] points;
    int i;

    public Point[] sort(Point[] points) {
        //initialization from Points[] to int[]
//        numbers = new int[Analyser.N];
//        i=0;
//        for (Point point: points){
//            numbers[i]=point.getX();
//            i++;
//        }
        // Check for empty or null array
//        if (numbers ==null || numbers.length==0){
//            return;
//        }
        this.points=points;
        number =Analyser.N;
        quicksort(0, number - 1);
        return this.points;
    }

    private void quicksort(int low, int high) {
        int i = low, j = high;
        // Get the pivot element from the middle of the list
        int pivot = points[low + (high-low)/2].getX();

        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller then the pivot
            // element then get the next element from the left list
            while (points[i].getX() < pivot) {
                i++;
            }
            // If the current value from the right list is larger then the pivot
            // element then get the next element from the right list
            while (points[j].getX() > pivot) {
                j--;
            }

            // If we have found a values in the left list which is larger then
            // the pivot element and if we have found a value in the right list
            // which is smaller then the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (i <= j) {
                exchange(i, j);
                i++;
                j--;
            }
        }
        // Recursion
        if (low < j)
            quicksort(low, j);
        if (i < high)
            quicksort(i, high);
    }

    private void exchange(int i, int j) {
        Point temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }
}