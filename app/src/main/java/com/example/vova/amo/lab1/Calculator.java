package com.example.vova.amo.lab1;

public class Calculator {

    private Calculator(){}

    public static double calculateFirstExpression(double a, double b, double c){
        return (a/b + b/a) * (a/c + c/a) / (a + b + c);
    }

    public static double calculateSecondExpression(double i, double m, double d){
        if (i % 2 == 1)
            return (d * Math.pow(m, 5) - Math.pow(d, 5) * m) / i / d;
        else
            return i * d / (d * Math.pow(m, 3) - Math.pow(d, 3) * m);
    }

    public static double calculateThirdExpression(int n, double[] c, double g){
        double f = 0;

        for (int a = 0; a < n; a++)
            f +=  a * a + 56 * c[a] * f * g;

        return f;
    }
}
