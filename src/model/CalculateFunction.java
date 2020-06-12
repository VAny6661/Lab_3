package model;

import java.util.LinkedList;
import java.util.List;

public abstract class CalculateFunction {
    private final double step = 0.01;
    protected int k;
    double[] X;
    double[] Y;
    int maxValue;
    int minValue;

    public CalculateFunction(int maxValue, int minValue, int k) {
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.k = k;
        List<Double> x = new LinkedList<>();
        List<Double> y = new LinkedList<>();
         for (double i = minValue; i < maxValue; i += step) {
             x.add(i);
            y.add(calculateY(i, k));
        }
        X = new double[x.size()];
        Y = new double[y.size()];
        for (int i = 0; i < x.size(); i++) {
            X[i] = x.get(i);
            Y[i] = y.get(i);
        }
    }

    public abstract double calculateY(double x, int k);

    public double[] getXes() {
        return X; 
    }

    public double[] getYes() {
        return Y;
    }
}
