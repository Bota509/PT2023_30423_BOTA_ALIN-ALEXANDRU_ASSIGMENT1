package mvk.models;

import java.net.Inet4Address;
import java.util.HashMap;

public class Polynomials {

private HashMap<Integer,Double> polynomial = new HashMap<>();

    public Polynomials(HashMap<Integer, Double> polynomial) {
        this.polynomial = polynomial;
    }

    public Polynomials() {
    }

    public HashMap<Integer, Double> getPolynomial() {
        return polynomial;
    }

    public void setPolynomial(HashMap<Integer, Double> polynomial) {
        this.polynomial = polynomial;
    }

    public void addInHashMap(double coefficient, int power)
    {

        if(this.polynomial.containsKey(power))
        {
            double newResult = 0.0;
            newResult = this.polynomial.get(power) + coefficient;
            this.polynomial.replace(power,newResult);
        }
        else
        {
            this.polynomial.put(power,coefficient);
        }
    }
}
