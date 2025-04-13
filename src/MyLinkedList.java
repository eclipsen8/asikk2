public class MyLinkedList<T> implements MyList<T> {
    private class MyNode {
        T data;
        MyNode next;
        MyNode prev;

        MyNode(T data) {
            this.data = data;
        }
    }

    private MyNode head;
    private MyNode tail;
    private int size;

    @Override
    public void add(T element) {
        MyNode newNode = new MyNode(element);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        if (index == size) {
            add(element);
            return;
        }

        MyNode newNode = new MyNode(element);
        if (index == 0) {
            newNode.next = head;
            if (head != null) head.prev = newNode;
            head = newNode;
        } else {
            MyNode current = nodeAt(index);
            newNode.prev = current.prev;
            newNode.next = current;
            if (current.prev != null) current.prev.next = newNode;
            current.prev = newNode;
        }

        if (newNode.next == null) tail = newNode;
        size++;
    }

    private MyNode nodeAt(int index) {
        MyNode current = head;
        for (int i = 0; i < index; i++) current = current.next;
        return current;
    }

    @Override
    public T get(int index) {
        return nodeAt(index).data;
    }

    @Override
    public T remove(int index) {
        MyNode toRemove = nodeAt(index);
        if (toRemove.prev != null) toRemove.prev.next = toRemove.next;
        else head = toRemove.next;

        if (toRemove.next != null) toRemove.next.prev = toRemove.prev;
        else tail = toRemove.prev;

        size--;
        return toRemove.data;
    }

    @Override
    public boolean remove(T element) {
        MyNode current = head;
        while (current != null) {
            if (current.data.equals(element)) {
                if (current.prev != null) current.prev.next = current.next;
                else head = current.next;

                if (current.next != null) current.next.prev = current.prev;
                else tail = current.prev;

                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private MyNode current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}