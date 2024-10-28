package strategy;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class BinarySearcher {

    public static <T> int find(List<T> originalList, T target, Comparator<T> comparator) {
        //creating new list in order to avoid sorting the original collection
        List<T> list = new ArrayList<> (originalList);

        //sort the list to make binarySearch work
        SelectionSortStrategy<T> sortStrategy = new SelectionSortStrategy<>();
        sortStrategy.sort(list, comparator);

        return Collections.binarySearch(list, target, comparator);
    }
}

