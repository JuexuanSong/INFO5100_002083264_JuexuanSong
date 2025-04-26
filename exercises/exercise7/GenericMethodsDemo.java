import java.util.*;
import java.util.function.Predicate;

public class GenericMethodsDemo {
    public static void main(String[] args) {
        // ======== (a) Count elements with a specific property ========
        System.out.println("=== (a) Counting Elements with a Property ===");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        int oddCount = GenericMethods.countIf(numbers, x -> x % 2 != 0);
        System.out.println("Number of odd numbers: " + oddCount); // 5

        int greaterThanFiveCount = GenericMethods.countIf(numbers, x -> x > 5);
        System.out.println("Numbers greater than 5: " + greaterThanFiveCount); // 4

        // ======== (b) Swap two elements in an array ========
        System.out.println("\n=== (b) Swapping Elements in an Array ===");
        String[] words = {"apple", "banana", "cherry"};
        System.out.println("Before swap: " + Arrays.toString(words));
        GenericMethods.swap(words, 0, 2);
        System.out.println("After swap: " + Arrays.toString(words));

        // ======== (c) Find maximal element in a list range ========
        System.out.println("\n=== (c) Finding Maximal Element in a Range ===");
        List<Integer> nums = Arrays.asList(10, 20, 5, 30, 25);
        int maxNum = GenericMethods.findMaxInRange(nums, 1, 4); // from index 1 to index 3
        System.out.println("Max number between index 1 and 4: " + maxNum);

        List<String> wordList = Arrays.asList("apple", "banana", "cherry", "date");
        String maxWord = GenericMethods.findMaxInRange(wordList, 0, 3); // "cherry" is max
        System.out.println("Max word between index 0 and 3: " + maxWord);
    }
}

// ========== Helper Class with Generic Methods ==========
class GenericMethods {
    // (a) Generic method to count elements matching a specific property
    public static <T> int countIf(Collection<T> collection, Predicate<T> predicate) {
        int count = 0;
        for (T element : collection) {
            if (predicate.test(element)) {
                count++;
            }
        }
        return count;
    }

    // (b) Generic method to swap two elements in an array
    public static <T> void swap(T[] array, int i, int j) {
        if (i < 0 || j < 0 || i >= array.length || j >= array.length) {
            throw new IndexOutOfBoundsException("Invalid index for swapping.");
        }
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // (c) Generic method to find the maximal element in a list range [begin, end)
    public static <T extends Comparable<T>> T findMaxInRange(List<T> list, int begin, int end) {
        if (list == null || begin < 0 || end > list.size() || begin >= end) {
            throw new IllegalArgumentException("Invalid range for finding maximum.");
        }
        T max = list.get(begin);
        for (int i = begin + 1; i < end; i++) {
            if (list.get(i).compareTo(max) > 0) {
                max = list.get(i);
            }
        }
        return max;
    }
}
