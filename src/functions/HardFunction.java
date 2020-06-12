package functions;



import model.CalculateFunction;

public class HardFunction extends CalculateFunction{

	int maxValue, minValue , k;
	double rez, x;
	
    public HardFunction(int maxValue, int minValue, int k) {
        super(maxValue, minValue, k);

    }
    @Override
    public double calculateY(double x, int k) {
       float rez=0;
       for(int i =1;i<k;i++)
    	rez+=(Math.pow(-1, i)+Math.pow(x, i))/factorial(factorial(i)+1);
       rez=rez/factorial(k);
       return rez;
    }
    private int factorial(int num) {
    	int rez=1;
    	for(int i =1;i<=num;i++) {
    		rez=rez*i;
    	}
    	return rez;
    }

}
