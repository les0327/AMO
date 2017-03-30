package com.example.vova.amo.lab5;

import java.util.Arrays;

/**
 * Created on 23.03.2017.
 *
 * @author Les.
 * @version 1.0.
 */
public class Solver {

    private int iterations;

    public double[][] transpose(double[][] A){
        double[][] At = new double[A[0].length][A.length];

        for (int i = 0; i < At.length; i++)
            for (int j = 0; j < At[i].length; j++)
                At[i][j] = A[j][i];

        return At;
    }

    public double[][] multiply(double[][] A, double[][] B){
        double[][] c = new double[A.length][B[0].length];

        for (int i = 0; i < c.length; i++)
            for (int j = 0; j < c[i].length; j++)
                for (int k = 0; k < c.length; k++)
                    c[i][j] += A[i][k] * B[k][j];

        return c;
    }

    public double[][] getAlfa(double[][] A){
        double[][] alfa = new double[A.length][A[0].length];
        for (int i = 0; i < alfa.length; i++){
            for (int j = 0; j < alfa[i].length; j++)
                alfa[i][j] = -A[i][j] / A[i][i];
            alfa[i][i] = 0;
        }
        return alfa;
    }

    public double[][] getBeta(double[][] B, double[][] A){
        double[][] beta = new double[B.length][1];
        for (int i = 0; i < beta.length; i++){
            beta[i][0] = B[i][0] / A[i][i];
        }
        return beta;
    }

    public double[] solve(double[][] alfa, double[][] beta, double E){
        double[]   x = new double[alfa[0].length];
        double[] buf = new double[alfa[0].length];

        int index;
        boolean flag;

        iterations = 0;
        do {
            iterations++;
            index = 0;
            for (int i = 0; i < buf.length; i++){
                buf[i] = beta[i][0];
                for (int j = 0; j < index; j ++){
                    buf[i] += alfa[i][j] * buf[j];
                }
                for (int j = index; j < alfa[i].length; j++){
                    buf[i] += alfa[i][j] * x[j];
                }
                index++;
            }

            double maxE = Math.abs(buf[0] - x[0]);
            for (int i = 1; i < buf.length; i++){
                double r = Math.abs(buf[i] - x[i]);
                if (r > maxE)
                    maxE = r;
            }

            x = Arrays.copyOf(buf, buf.length);
            flag = (maxE < E);
        } while (!flag);

        return x;
    }

    public int getIterations() {
        return iterations;
    }
}
