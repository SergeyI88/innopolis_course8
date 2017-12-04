package ru.practice.home_work.arrays;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import static ru.practice.home_work.arrays.SumNumbersInArray.searchNonElementsInSecondArray;

public class SumNumberInArrayTest {
    @Test
    public void weAreChekingQuantityCoupleInArray() {
        int[] a = {1, 2, 3, 5, 6, 7, 8, 9, 0, 12, 44, 22, 11, 33, 10, 34, 0, 21, 23};
        Assert.assertThat(9, Is.is(SumNumbersInArray.searchCouples(a, 9)));
    }

    @Test
    public void weAreCheckingWhichNumbersReceive() {
        int[] b = {1, 2, 3, 5, 6, 7, 8, 9, 10};
        int[] a = {3, 6, 7, 10, 12};
        int[] result = searchNonElementsInSecondArray(a, b);
        Assert.assertThat(12, Is.is(result[0]));
        result = searchNonElementsInSecondArray(b, a);
        Assert.assertThat(1, Is.is(result[0]));
        Assert.assertThat(2, Is.is(result[1]));
        Assert.assertThat(5, Is.is(result[2]));
    }

    @Test
    public void weAreCheckingHowMuchElementsReceived() {
        int[] b = {1, 2, 3, 5, 6, 7, 8, 9, 10};
        int[] a = {3, 6, 7, 10, 12};
        int[] result = searchNonElementsInSecondArray(b, a);
        Assert.assertThat(5, Is.is(result.length));

    }
}
