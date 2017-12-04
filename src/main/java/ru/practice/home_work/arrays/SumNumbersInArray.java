package ru.practice.home_work.arrays;

/**
 * Class to FirstTask.
 *
 * @author SergeyIndyukov
 * @version 0.1
 * @since 04.12.2017
 */
public class SumNumbersInArray {
    /**
     * method is searching couple.
     *
     * @param a
     * @param number
     * @return quantity couple
     */
    static int searchCouples(int[] a, int number) {
        int quantityCouple = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] <= number) {
                for (int j = 1; j < a.length; j++) {
                    if (a[i] + a[j] == number) {
                        quantityCouple++;
                    }
                }
            }
        }
        return quantityCouple;
    }

    /**
     * method is searching unique elements in first array which is not in second array.
     *
     * @param a
     * @param b
     * @return
     */
    static public int[] searchNonElementsInSecondArray(int[] a, int[] b) {
        int[] temp = new int[a.length >= b.length ? a.length : b.length];
        int indexFirstArray = 0, indexSecondArray = 0, indexResultArray = 0;
        while (indexFirstArray < a.length && indexSecondArray < b.length) {
            if (a[indexFirstArray] < b[indexSecondArray]) {
                temp[indexResultArray++] = a[indexFirstArray++];
            } else if (a[indexFirstArray] > b[indexSecondArray]) {
                indexSecondArray++;
            } else {
                indexFirstArray++;
                indexSecondArray++;
            }
        }
        if (a.length > indexFirstArray) {
            while (a.length > indexFirstArray) {
                temp[indexResultArray++] = a[indexFirstArray++];
            }
        }
        int[] result = new int[indexResultArray];
        System.arraycopy(temp, 0, result, 0, indexResultArray);
        return result;
    }
}
