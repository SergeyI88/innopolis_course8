package lections.collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class ContainerLinkedList<E> implements Iterable<E> {
    private int index = 0;
    Entry first = null;
    Entry last = null;

    protected class IteratorList<E> implements Iterator<E> {
        Entry first;
        Entry last;

        public IteratorList(Entry first, Entry last) {
            this.first = first;
            this.last = last;
        }

        @Override
        public boolean hasNext() {
            if (this.first != null) {
                while (last != null) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public E next() {
            Entry next = first;
            first = next;
            first = first.next;
            return (E) next.element;
        }

        @Override
        public void remove() {

        }
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorList(this.first, this.last);
    }

    /*Необходимо создать контейнер с методами
    add(E value);
    E get(int index);
    То есть метод add(E value) - может принимать бесконечное количество элементов.*/
    protected class Entry {
        Object element;

        Entry prev = null;
        Entry next = null;

        public Entry(E element) {
            this.element = element;
        }
    }

    public void add(E element) {
        if (first == null) {
            first = new Entry(element);
        } else if (last == null) {
            last = new Entry(element);
            first.next = last;
            last.prev = first;
        } else {
            Entry entry = last;
            last = new Entry(element);
            entry.next = last;
            last.prev = entry;
        }
        index++;
    }

    public E get(int index)   {
        if (index > this.index) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int tempIndex = 0;
        Entry entry = first.next;
        do {
            if (tempIndex == 0) {
                if (index == tempIndex) {
                    return (E) first.element;
                }
            } else if (tempIndex == 1) {
                if (index == tempIndex) {
                    return (E) first.next.element;
                }
            } else {
                entry = entry.next;
                if (index == tempIndex) {
                    return (E) entry.element;
                }
            }
        } while (tempIndex++ != index);
        return null;
    }

    public E get(E value) {
        if (value == null) {
            throw new NullPointerException();
        }
        E result = null;
        Entry e = first;
        while (e.next != null) {
            if (e.element.equals(value)) {
                result = (E) e.element;
                break;
            }
        }
        return result;
    }

    public boolean remove(E value) {
        if (value == null) {
            throw new NullPointerException();
        }
        boolean result = false;
        Entry e = first;
        if (first.element.equals(value)) {
            first = first.next;
            return true;
        } else if (last.element.equals(value)) {
            last = last.prev;
            return true;
        } else {
            e = first.next;
            while (e != null) {
                if (e.element.equals(value)) {
                    result = true;
                    Entry temp = e;
                    temp.prev.next = e.next.prev;
                    temp.next.prev = e.prev.next;
                    break;
                } else {
                    e = e.next;
                }
            }
        }
        return result;
    }

    public void remove(int index) {
        int count = 0;
        Entry e = first;
        if (index == 0) {
            first = first.next;
        } else if (index == this.index) {
            last = last.prev;
        } else {
            e = e.next;
            while (e != null) {
                if (++count == index) {
                    Entry temp = e;
                    temp.prev.next = e.next.prev;
                    temp.next.prev = e.prev.next;
                    break;
                } else {
                    e = e.next;
                }
            }
        }
    }

    public boolean isEmpty() {
        return index == 0 ? true : false;
    }

    public int size() {
        return index;
    }

    public Object[] toArray() {
        Object[] objects = new Object[index];
        objects[0] = first.element;
        Entry temp = first;
        for (int i = 1; i < index; i++) {
            objects[i] = temp.next;
            temp = temp.next;
        }
        return objects;
    }

    public List<E> subList(int fromIndex, int toIndex) {
        List<E> list = new LinkedList<>();
        int count = 0;
        if (fromIndex == count++) {
            list.add((E) first.element);
        } else {
            Entry temp = first;
            while (temp != null || count++ > toIndex) {
                list.add((E) temp.next.element);
                temp = temp.next;
            }
        }
        return list;
    }

    public int indexOf(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        int count = 0;
        Entry e = first;
        if (first.element.equals(o)) {
            return 0;
        } else if (last.element.equals(o)) {
            return this.index;
        } else {
            e = e.next;
            while (e != null || e != last) {
                if (e.element.equals(o)) {
                    return count++;
                } else {
                    e = e.next;
                    count++;
                }
            }
        }
        return - 1;
    }

    public void clear() {
        first = null;
        last = null;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }
}
