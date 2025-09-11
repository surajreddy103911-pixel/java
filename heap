import java.util.ArrayList;

public class MaxHeap {
    private ArrayList<Integer> heap;

    public MaxHeap() {
        heap = new ArrayList<>();
    }

    // Insert method (for testing)
    public void insert(int val) {
        heap.add(val);
        int i = heap.size() - 1;

        // Bubble up
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap.get(i) > heap.get(parent)) {
                int temp = heap.get(i);
                heap.set(i, heap.get(parent));
                heap.set(parent, temp);
                i = parent;
            } else {
                break;
            }
        }
    }

    // Delete root (max)
    public int delete() {
        if (heap.size() == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        int rootValue = heap.get(0); // root value to return

        // Move last element to root
        int last = heap.remove(heap.size() - 1);
        if (heap.size() > 0) {
            heap.set(0, last);
            heapify(0); // restore heap property
        }

        return rootValue;
    }

    // Heapify down from index i
    private void heapify(int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;

        if (left < heap.size() && heap.get(left) > heap.get(largest)) {
            largest = left;
        }

        if (right < heap.size() && heap.get(right) > heap.get(largest)) {
            largest = right;
        }

        if (largest != i) {
            int temp = heap.get(i);
            heap.set(i, heap.get(largest));
            heap.set(largest, temp);
            heapify(largest); // recursive call
        }
    }

    // Print heap
    public void printHeap() {
        System.out.println(heap);
    }

    // Testing
    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap();
        heap.insert(50);
        heap.insert(30);
        heap.insert(40);
        heap.insert(10);
        heap.insert(20);
        heap.insert(35);
        heap.insert(60);

        System.out.println("Heap before delete:");
        heap.printHeap(); // [60, 30, 50, 10, 20, 35, 40]

        System.out.println("Deleted root: " + heap.delete());
        System.out.println("Heap after delete:");
        heap.printHeap(); // [50, 30, 40, 10, 20, 35]
    }
}
