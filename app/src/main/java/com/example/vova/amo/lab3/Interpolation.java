package com.example.vova.amo.lab3;

import java.io.Serializable;

/**
 * Created on 16.03.2017.
 *
 * @author Les.
 * @version 1.0.
 */
public class Interpolation implements Serializable {

    private static Function function = new Function() {
        @Override
        public double valueAt(double value) {
            return Math.sin(value) * Math.sin(value);
        }
    };

    private int numOfPoints;                 // num of points
    private float a;                         // left limit
    private float b;        // right limit
    private float h;      // step
    private double[] x; // array of arguments
    private double[] y; // array of function values
    private int n = 2;                            // interpolation degree

    public Interpolation(float a, float b, int numOfPoints, int n) {
        this.a = a;
        this.b = b;
        this.numOfPoints = numOfPoints;
        this.n = n;
        h = (b - a) / numOfPoints;
        x = new double[numOfPoints];
        y = new double[numOfPoints];

        x[0] = a;
        y[0] = function.valueAt(x[0]);

        for (int i = 1; i < x.length; i++){
            x[i] = x[i - 1] + h;
            y[i] = function.valueAt(x[i]);
        }
    }

    public double interpolate(double value) {
        double result = 0;
        double mul;
        for (int k = 0; k < numOfPoints - 1; k++) {

            if (!(value >= x[k] && value < x[k + 1])) {
                continue;
            }

            if (k <= numOfPoints / 2) {
                for (int j = k; j <= k + n; j++) {
                    mul = y[j];
                    for (int i = k; i <= k + n; i++) {
                        if (i != j) {
                            mul *= (value - x[i]) / (x[j] - x[i]);
                        }
                    }
                    result += mul;
                }
            } else {
                for (int j = k + 1; j >= k - n + 1; j--) {
                    mul = y[j];
                    for (int i = k + 1; i >= k - n + 1; i--) {
                        if (i != j) {
                            mul *= (value - x[i]) / (x[j] - x[i]);
                        }
                    }
                    result += mul;
                }
            }
        }
        return result;
    }

    public double valueAt(double value){
        return function.valueAt(value);
    }

    public float getA() {
        return a;
    }

    public float getB() {
        return b;
    }

    public float getH() {
        return h;
    }

    public int getNumOfPoints() {
        return numOfPoints;
    }
}
