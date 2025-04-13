public class MyMinHeap {
    private MyArrayList<Integer> heap = new MyArrayList<>();

    public void add(int element) {
        heap.add(element);
        heapifyUp(heap.size() - 1);
    }

    public int poll() {
        if (isEmpty()) throw new IllegalStateException("Heap is empty");
        int result = heap.get(0);
        int lastElement = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.add(0, lastElement);
            heapifyDown(0);
        }
        return result;
    }

    public int peek() {
        if (isEmpty()) throw new IllegalStateException("Heap is empty");
        return heap.get(0);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    public void clear() {
        heap.clear();
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index) >= heap.get(parentIndex)) break;
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void heapifyDown(int index) {
        int size = heap.size();
        while (index < size) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < size && heap.get(left) < heap.get(smallest)) smallest = left;
            if (right < size && heap.get(right) < heap.get(smallest)) smallest = right;

            if (smallest == index) break;

            swap(index, smallest);
            index = smallest;
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.add(i, heap.get(j));
        heap.remove(i + 1);
        heap.add(j, temp);
        heap.remove(j + 1);
    }
}