package strategy;

//example usage:
//SelectionSortStrategy<T> sorter = new SelectionSortStrategy<>();
//sorter.sort(array, Comparator<T>)

import java.util.Comparator;
import java.util.List;

public class SelectionSortStrategy<T> implements SortStrategy<T> {
    public void sort(List<T> list, Comparator<T> comparator) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(list.get(j), list.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                T temp = list.get(minIndex);
                list.set(minIndex, list.get(i));
                list.set(i, temp);
            }
        }
    }
}

