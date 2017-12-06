package lections.collections;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class ContainerLinkedListTest {
    @Test
    public void whenAddToContainer() {
        ContainerLinkedList<Integer> containerLinkedList = new ContainerLinkedList<>();
        containerLinkedList.add(10);
        containerLinkedList.add(20);
        containerLinkedList.add(30);
        Assert.assertThat(20, Is.is(containerLinkedList.get(1)));
    }
    @Test
    public void whenUsedIterator() {
        ContainerLinkedList<Integer> containerLinkedList = new ContainerLinkedList<>();
        containerLinkedList.add(10);
        containerLinkedList.add(20);
        containerLinkedList.add(30);
        Iterator<Integer> iterator = containerLinkedList.iterator();
        Assert.assertThat(10, Is.is(iterator.next()));
        iterator.next();
        iterator.next();
        Assert.assertFalse(iterator.hasNext());
    }
}
