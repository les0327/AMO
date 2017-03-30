package com.example.vova.amo.lab2;

/**
 * Created on 09.03.2017.
 *
 * @author Les.
 * @version 1.0.
 */
public class Timer {

    private static long start;

    public static void start(){
        start = System.nanoTime();
    }

    static long finish(){
        return System.nanoTime() - start;
    }
}
