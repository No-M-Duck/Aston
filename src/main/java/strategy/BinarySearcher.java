package strategy;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class BinarySearcher {

    //returns the element itself; doesn't sort original collection
    public static <T> T findElement(List<T> originalList, T target, Comparator<T> comparator) {
        List<T> list = new ArrayList<>(originalList);
        sortForBinary(list, comparator);
        int index = binarySearch(list, target, comparator);
        try {
            return list.get(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Element not found.");
            return null;
        }
    }

    //return index position of the element; sorts the original collection
    public static <T> int findIndex(List<T> list, T target, Comparator<T> comparator) {
        sortForBinary(list, comparator);
        return binarySearch(list, target, comparator);
    }

    private static <T> void sortForBinary(List<T> list, Comparator<T> comparator) {
        SelectionSortStrategy<T> sortStrategy = new SelectionSortStrategy<>();
        sortStrategy.sort(list, comparator);

    }

    private static <T> int binarySearch(List<T> list, T target, Comparator<T> comparator) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = low  + ((high - low) / 2);

            int c = comparator.compare(list.get(mid), target);

            if (c < 0) {
                low = mid + 1;
            }
            else if (c > 0) {
                high = mid - 1;
            }
            else {
                return mid;
            }

        }
        return -1;
    }
}

