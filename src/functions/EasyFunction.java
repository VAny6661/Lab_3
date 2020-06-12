package functions;

import model.CalculateFunction;

public class EasyFunction extends CalculateFunction {
    public EasyFunction(int topValue, int bottomValue, int k) {
        super(topValue, bottomValue, k);
    }

    @Override
    public double calculateY(double x, int k) {
        return x-3;
    }
}
