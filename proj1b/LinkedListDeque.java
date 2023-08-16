

public class LinkedListDeque<item> implements Deque<item>{
    public class node {
        item i;
        node pre;
        node next;

        public node(item a, node ppre, node nnext) {
            i = a;
            pre = ppre;
            next = nnext;
        }

        public node(node ppre, node nnext) {
            pre = ppre;
            next = nnext;
        }

    }

    public node sen = new node(null, null);
    public int size = 0;

    public void addFirst(item d) {
        if (size != 0) {
            node a = new node(d, sen, sen.next);
            sen.next.pre = a;
            sen.next = a;
        } else {
            node a = new node(d, sen, sen);
            sen.next = a;
            sen.pre = a;
        }
        size += 1;
    }

    public void addLast(item d) {
        if (size != 0) {
            node a = new node(d, sen.pre, sen);
            sen.pre.next = a;
            sen.pre = a;
        } else {
            node a = new node(d, sen, sen);
            sen.next = a;
            sen.pre = a;
        }
        size += 1;


    }

    public boolean isEmpty() {
        return sen.next == null;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        node a = sen.next;
        while (a != null) {
            System.out.print(a.i);
            System.out.print(' ');
            a = a.next;
        }
    }

    public item removeFirst() {
        if (size == 0)
            return null;
        else {
            node a = sen.next;
            if (size == 1) {
                sen.next = null;
                sen.pre = null;
            } else {
                sen.next = a.next;
                a.next.pre = sen;
            }
            size -= 1;
            return a.i;
        }
    }

    public item removeLast() {
        if (size == 0)
            return null;
        else if (size == 1) {
            node a = sen.pre;
            sen.pre = null;
            sen.next = null;
            size -= 1;
            return a.i;
        } else {
            node a = sen.pre;
            sen.pre = a.pre;
            a.pre.next = sen;
            size -= 1;
            return a.i;
        }
    }

    public item get(int index) {
        if (size <= index)
            return null;
        node a = sen.next;
        for (int i = 0; i != index; i += 1) {
            a = a.next;
        }
        return a.i;
    }

    public item getRecursive(int index) {
        if (size <= index)
            return null;
        node a = sen.next;
        for (int i = 0; i != index; i += 1) {
            a = a.next;
        }
        return a.i;
    }
}
