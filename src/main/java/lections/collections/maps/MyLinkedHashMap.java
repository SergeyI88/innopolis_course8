package lections.collections.maps;

import lections.collections.ContainerLinkedList;

import java.util.*;

/**
 * Class MyLinkedHashMap
 *
 * @param <T>
 * @param <V>
 * @author IndyukovSergey
 * @since 07.12.2017
 * @version 0.1
 */
public class MyLinkedHashMap<T, V> implements Iterable {

    /**
     * @param nodes
     * @param loadfactor
     * @param treshHold
     */
    private Node[] nodes;
    private double loadfactor = 0.75;
    private int treshHold = 0;

    /**
     * Constructor
     */
    public MyLinkedHashMap() {
        nodes = new Node[8];
    }

    /**
     * Paste in inside
     *
     * @param key   should be uniquie.
     * @param value
     * @return
     */
    public boolean insert(T key, V value) {
        if (checkingArray()) {
            System.out.println(nodes.length);
        }
        int result = indexFor(hash(key.hashCode()));
        Node node = new Node(key, value);
        if (nodes[result] == null) {
            nodes[result] = node;
        } else {
            Node temp = nodes[result];
            while (temp != null) {
                if (nodes[result].key.equals(key)) {
                    node.next = nodes[result].next;
                    nodes[result] = node;
                    break;
                } else if (temp.key.equals(node.key)) {
                    temp.next = node.next;
                    temp.prev = node.prev;
                    temp.next.prev = node;
                    temp.prev.next = node;
                    break;
                } else if (temp.next == null) {
                    temp.next = node;
                    node.prev = temp;
                    treshHold++;
                    break;
                } else {
                    temp = temp.next;
                }
            }
        }
        return false;
    }

    private int hash(int i) {
        return i ^ (i >>> 16);
    }

    private int indexFor(int i) {
        return i & (nodes.length) - 1;
    }

    /**
     * Method is checking whether it is necessary to increase the array.
     * @return true if need.
     */
    private boolean checkingArray() {
        boolean result = false;
        if (treshHold * loadfactor >= nodes.length) {
            Node[] temp = new Node[nodes.length << 1];
            System.arraycopy(nodes, 0, temp, 0, nodes.length);
            nodes = temp;
        }
        return result;
    }

    /**
     * Method get find value by the key.
     *
     * @param key
     * @return value.
     */
    public V get(T key) {
        Object value = null;
        int result = indexFor(hash(key.hashCode()));
        Node temp = nodes[result];
        while (temp != null) {
            if (temp == null) {
                break;
            } else if (temp.key.equals(key)) {
                value = temp.value;
                break;
            } else {
                temp = temp.next;
            }
        }
        return (V) value;
    }

    /**
     * He is removing Node from maps by key.
     * @param key
     */
    public void remove(T key) {
        int result = indexFor(hash(key.hashCode()));
        Node temp = nodes[result];
        System.out.println(temp);
        while (temp != null) {
            if (temp.key.equals(key)) {
                Node next = temp.next;
                Node prev = temp.prev;
                if (prev == null) {
                    nodes[result] = null;
                    treshHold--;
                    break;
                } else {
                    if (next != null) {
                        next.prev = prev;
                    }
                    prev.next = next;
                    System.out.println("log");
                    treshHold--;
                    break;
                }
            } else {
                temp = temp.next;
            }
        }
    }


    /**
     * Iterator
     *
     * @return
     */
    @Override
    public Iterator<Node> iterator() {
        return new IteratorMap<Node>(nodes);
    }

    /**
     * Class IteratorMap need for directory
     *
     * @param <V>
     */
    private class IteratorMap<V> implements Iterator<V> {
        int length = 0;
        private ContainerLinkedList<Node> lists;

        public IteratorMap(Node[] nodes) {
            ContainerLinkedList<Node> list = new ContainerLinkedList<>();
            for (Node n : nodes) {
                while (n != null) {
                    list.add(n);
                    n = n.next;
                }
            }
            lists = list;
        }

        @Override
        public boolean hasNext() {
            return length < this.lists.size();
        }

        @Override
        public V next() {
            V res = (V) lists.get(length++);
            return res;
        }

        @Override
        public void remove() {

        }
    }

    /**
     * Class entry contains uniquie key and value.
     *
     * @param <T>
     * @param <V>
     */
    public class Node<T, V> {
        protected Object key;
        protected Object value;
        private Node next;
        private Node prev;

        public Node(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Node() {
        }

        public T getKey() {
            return (T) key;
        }

        private void setKey(T key) {
            this.key = key;
        }

        public V getValue() {
            return (V) value;
        }

        private void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            if (key != null && value != null) {
                return key + "=" + value;
            }
            return null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node<?, ?> node = (Node<?, ?>) o;

            if (key != null ? !key.equals(node.key) : node.key != null) return false;
            return value != null ? value.equals(node.value) : node.value == null;
        }

        @Override
        public int hashCode() {
            return key != null ? key.hashCode() : 0;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(nodes);
    }
}
