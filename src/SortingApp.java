// Interface for SortingAlgorithm
interface SortingAlgorithm {
    void sort(int[] array);
}

//Bubble Sort
class BubbleSort implements SortingAlgorithm {
    @Override
    public void sort(int[] array) {
        // Implementation of Bubble Sort
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap array[j] and array[j+1]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}

//Quick Sort
class QuickSort implements SortingAlgorithm {
    @Override
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            // Partition the array, and get the index of the pivot
            int pivotIndex = partition(array, low, high);

            // Recursively sort the sub-arrays
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;

                // Swap array[i] and array[j]
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // Swap array[i+1] and array[high] (pivot)
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }
}


// Factory interface for creating SortingAlgorithm instances
interface SortingAlgorithmFactory {
    SortingAlgorithm createSortingAlgorithm();
}

// Factory class for Bubble Sort
class BubbleSortFactory implements SortingAlgorithmFactory {
    @Override
    public SortingAlgorithm createSortingAlgorithm() {
        return new BubbleSort();
    }
}

// Factory class for Quick Sort
class QuickSortFactory implements SortingAlgorithmFactory {
    @Override
    public SortingAlgorithm createSortingAlgorithm() {
        return new QuickSort();
    }
}

// Client code that uses the SortingAlgorithmFactory
public class SortingApp {
    public static void main(String[] args) {
        // Use the factory to create instances of different sorting algorithms
        SortingAlgorithmFactory bubbleSortFactory = new BubbleSortFactory();
        SortingAlgorithm bubbleSort = bubbleSortFactory.createSortingAlgorithm();
        bubbleSort.sort(new int[]{3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5});

        SortingAlgorithmFactory quickSortFactory = new QuickSortFactory();
        SortingAlgorithm quickSort = quickSortFactory.createSortingAlgorithm();
        quickSort.sort(new int[]{3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5});
    }
}
