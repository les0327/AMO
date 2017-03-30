package com.example.vova.amo.lab4;

import com.example.vova.amo.lab3.Function;

/**
 * Created on 23.03.2017.
 *
 * @author Les.
 * @version 1.0.
 */
public class Searcher {

    public static final double[] roots = {-3.067, -0.695, 3.757};

    private Function equation         = new Function() {
        @Override
        public double valueAt(double x) {
            return x * x * x - 12 * x - 8;
        }
    };
    private Function firstDerivative  = new Function() {
        @Override
        public double valueAt(double x) {
            return 3 * x * x - 12;
        }
    };
    private Function secondDerivative = new Function() {
        @Override
        public double valueAt(double x) {
            return 6 * x;
        }
    };

    private int iterations;

    /**
     * @param a - left limit
     * @param b - right limit
     * @param E - error
     * @return equation root at [a, b] with E error.
     */
    public double methodChords(double a, double b, double E){
        double root = 0;
        if (equation.valueAt(a) * equation.valueAt(b) < 0) { // check for root existence

            iterations = 0;

            // if 1 derivative * 2 derivative < 0 it means that b is movable end
            if (firstDerivative.valueAt((a + b)/2) * secondDerivative.valueAt((a + b) /2) < 0) {
                double buf = a;
                a = b;
                b = buf;
            }

            boolean flag;
            do {

                root = a - (equation.valueAt(a) * (b - a) / (equation.valueAt(b) - equation.valueAt(a)));
                iterations++;

                flag = !(Math.abs(root - a) < E); // checking for error. while flag is true, error is too big.
                if (flag)
                    a = root;

            } while (flag);

        }
        return root;
    }

    public int getIterations() {
        return iterations;
    }
}
