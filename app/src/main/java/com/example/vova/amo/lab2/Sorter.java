package com.example.vova.amo.lab2;

/**
 * Created on 09.03.2017.
 *
 * @author Les.
 * @version 1.0.
 */
public class Sorter {

    public static <T extends Comparable> void binSort (T[] array) {
        T x;
        int left;
        int right;
        int mid;
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1].compareTo(array[i] ) > 0) {
                x = array[i];
                left = 0;
                right = i - 1;
                do {
                    mid = (left+right)/2;
                    if (array[mid].compareTo(x) < 0 )
                        left = mid + 1;
                    else
                        right = mid - 1;
                } while (left <= right);
                for (int j = i - 1; j >= left;j--)
                    array[j + 1]= array[j];
                array[left]= x;
            }
        }
    }
}
