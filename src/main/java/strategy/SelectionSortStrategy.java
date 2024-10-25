package strategy;

//example usage:
//SelectionSortStrategy<T> sorter = new SelectionSortStrategy<>();
//sorter.sort(array, Comparator<T>)

import java.util.Comparator;

public class SelectionSortStrategy<T> implements SortStrategy<T> {
    public void sort(T[] array, Comparator<T> comparator) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(array[j], array[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            //swap elements if minIndex changed
            if (minIndex != i) {
                T temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
            }
        }
    }
}

