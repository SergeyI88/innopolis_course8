package lections.collections.maps;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

/**
 * Created by admin on 09.07.2017.
 */
public class MyHashMapTest {

    @Test
    public void whenUseWithCollisions() {
        MyLinkedHashMap<User, String> myLinkedHashMap = new MyLinkedHashMap<>();
        myLinkedHashMap.insert(new User("ser"), "11");
        myLinkedHashMap.insert(new User("ser"), "12");
        myLinkedHashMap.insert(new User("ser"), "13");
        myLinkedHashMap.insert(new User("ser"), "14");
        myLinkedHashMap.insert(new User("ser1"), "11");
        myLinkedHashMap.insert(new User("ser1"), "12");
        myLinkedHashMap.insert(new User("ser1"), "13");
        myLinkedHashMap.insert(new User("ser2"), "2");
        myLinkedHashMap.insert(new User("ser3"), "3");
        myLinkedHashMap.insert(new User("ser44"), "44");
        myLinkedHashMap.insert(new User("ser555"), "555");
        myLinkedHashMap.insert(new User("ser66"), "66");
        Assert.assertThat("14", Is.is(myLinkedHashMap.get(new User("ser"))));
        Assert.assertThat("2", Is.is(myLinkedHashMap.get(new User("ser2"))));
        Assert.assertThat("3", Is.is(myLinkedHashMap.get(new User("ser3"))));
        Assert.assertThat("66", Is.is(myLinkedHashMap.get(new User("ser66"))));
        Assert.assertThat("44", Is.is(myLinkedHashMap.get(new User("ser44"))));
        myLinkedHashMap.remove(new User("ser555"));
        Assert.assertNull(myLinkedHashMap.get(new User("ser555")));
    }
    @Test
    public void whenAddAndUseGetInDirectory() {
        MyLinkedHashMap myLinkedHashMap = new MyLinkedHashMap();
        myLinkedHashMap.insert(1, "1");
        myLinkedHashMap.insert(2, "2");
        myLinkedHashMap.insert(3, "3");
        myLinkedHashMap.insert(4, "4");
        myLinkedHashMap.insert(5, "5");
        myLinkedHashMap.insert(6, "5");
        myLinkedHashMap.insert(7, "5");
        myLinkedHashMap.insert(8, "5");
        myLinkedHashMap.insert(9, "5");
        myLinkedHashMap.insert(10, "5");
        Assert.assertThat("5", Is.is(myLinkedHashMap.get(10)));
    }
    @Test
    public void whenDelIndirectory() {
        MyLinkedHashMap myLinkedHashMap = new MyLinkedHashMap();
        myLinkedHashMap.insert(1, "1");
        myLinkedHashMap.insert(2, "2");
        myLinkedHashMap.insert(3, "3");
        myLinkedHashMap.insert(4, "4");
        myLinkedHashMap.insert(5, "5");
        myLinkedHashMap.insert(6, "5");
        myLinkedHashMap.insert(7, "5");
        myLinkedHashMap.insert(8, "5");
        myLinkedHashMap.insert(9, "5");
        myLinkedHashMap.insert(10, "5");
        myLinkedHashMap.remove(10);
        Assert.assertThat(null, Is.is(myLinkedHashMap.get(10)));
    }

    @Test
    public void whenUseIteratorInDirectory() {
        MyLinkedHashMap<Integer, String> myLinkedHashMap = new MyLinkedHashMap<>();
        myLinkedHashMap.insert(1, "1");
        myLinkedHashMap.insert(2, "2");
        myLinkedHashMap.insert(3, "3");
        Iterator<MyLinkedHashMap.Node> iterator = myLinkedHashMap.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat("1", Is.is(iterator.next().getValue()));
        iterator.next();
        iterator.next();
        Assert.assertFalse(iterator.hasNext());
    }
}
