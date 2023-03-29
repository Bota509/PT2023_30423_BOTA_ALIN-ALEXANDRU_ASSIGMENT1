package mvk.models;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Operations {

    //clasa care tine doar operatiile
            Polynomials resultPolynomial1;
            Polynomials resultPolynomial2;
            public Operations(Polynomials resultPolynomial1,Polynomials resultPolynomial2)
            {
                this.resultPolynomial1 = resultPolynomial1;
                this.resultPolynomial2 = resultPolynomial2;
            }
            public Polynomials substraction(Polynomials polynomials1,Polynomials polynomials2) {

                 for (Map.Entry<Integer, Double> entry : polynomials1.getPolynomial().entrySet()) {
                     if (polynomials2.getPolynomial().containsKey(entry.getKey())) {
                         double newResult = polynomials1.getPolynomial().get(entry.getKey()) - polynomials2.getPolynomial().get(entry.getKey());
                         resultPolynomial1.getPolynomial().put(entry.getKey(), newResult);
                     } else {
                         resultPolynomial1.getPolynomial().put(entry.getKey(), entry.getValue());
                     }
                 }
                 for (Map.Entry<Integer, Double> entry : polynomials2.getPolynomial().entrySet()) {
                     if (!resultPolynomial1.getPolynomial().containsKey(entry.getKey())) {
                         resultPolynomial1.getPolynomial().put(entry.getKey(), -entry.getValue());
                     }
                 }
                 return resultPolynomial1;
             }

             public Polynomials multiplication(Polynomials polynomials1,Polynomials polynomials2)
             {
                 for (Map.Entry<Integer, Double> entry1 : polynomials1.getPolynomial().entrySet())
                 {
                     for (Map.Entry<Integer, Double> entry2 : polynomials2.getPolynomial().entrySet())
                     {
                         resultPolynomial1.getPolynomial().put(entry1.getKey()+entry2.getKey(),entry1.getValue()*entry2.getValue());
                     }
                 }
                 return resultPolynomial1;
             }

             public Polynomials integration(Polynomials polynomial)
             {
                 resultPolynomial1.getPolynomial().clear();
                 double newCoefficient;
                 int newPower;
                 for (Map.Entry<Integer, Double> entry : polynomial.getPolynomial().entrySet())
                 {
                     if(entry.getKey() >=0)
                     {
                         newPower = entry.getKey()+1;
                         newCoefficient = entry.getValue() / newPower;
                         resultPolynomial1.getPolynomial().put(newPower,newCoefficient);
                     }
                 }
                 return resultPolynomial1;
             }
             public Polynomials derivation(Polynomials polynomials)
             {
                 resultPolynomial1.getPolynomial().clear();
                 double newCoefficient;
                 int newPower;
                 for (Map.Entry<Integer, Double> entry : polynomials.getPolynomial().entrySet())
                 {
                     if(entry.getKey()>0)
                     {
                         newCoefficient = entry.getKey() * entry.getValue();
                         newPower = entry.getKey() - 1;
                         resultPolynomial1.getPolynomial().put(newPower,newCoefficient);
                     }
                 }
                 return resultPolynomial1;
             }
    public Polynomials sum(Polynomials polynomials1, Polynomials polynomials2) {
        for (Map.Entry<Integer, Double> entry : polynomials1.getPolynomial().entrySet()) {
            if (polynomials2.getPolynomial().containsKey(entry.getKey())) {
                double newResult = polynomials1.getPolynomial().get(entry.getKey()) + polynomials2.getPolynomial().get(entry.getKey());
                resultPolynomial1.getPolynomial().put(entry.getKey(), newResult);
            }
            else
            {
                resultPolynomial1.getPolynomial().put(entry.getKey(), entry.getValue());
            }
        }
        for (Map.Entry<Integer, Double> entry : polynomials2.getPolynomial().entrySet())
        {
            if(!resultPolynomial1.getPolynomial().containsKey(entry.getKey()))
            {
                resultPolynomial1.getPolynomial().put(entry.getKey(), entry.getValue());
            }
        }
        return resultPolynomial1;
    }

    public  String trTostring(Polynomials resultPolynomial) {
        TreeMap<Integer, Double> sortedDescendingdMap = new TreeMap<>(Collections.reverseOrder());//implement a treeMap to put the elements
        //from the hashmap in order by key -> in this case the power

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.DOWN); //to format de double result in a maximum of 2 decimals

        sortedDescendingdMap.putAll(resultPolynomial.getPolynomial());
        StringBuilder stringBuilder = new StringBuilder();
        String resultString;
        for (Map.Entry<Integer, Double> entry : sortedDescendingdMap.entrySet())
        {
            String coefficientInString = df.format(entry.getValue());
            String powerInString = entry.getKey().toString();
            if(entry.getValue()!=0.0 ) {

                if(entry.getKey()!=0) {
                    if (entry.getValue() > 0.0)
                        stringBuilder.append(" +").append(coefficientInString).append("x^").append(entry.getKey());
                    else
                        stringBuilder.append(" ").append(coefficientInString).append("x^").append(entry.getKey());
                }
                else if(entry.getKey() == 0)
                {
                    if (entry.getValue() > 0.0)
                        stringBuilder.append(" +").append(coefficientInString);
                    else
                        stringBuilder.append(" ").append(coefficientInString);
                }
            }
        }
        resultString = stringBuilder.toString();
        return resultString;
    }
    public Polynomials division(Polynomials polynomials1,Polynomials polynomials2)
    {
        while (polynomials1.getPolynomial().size() >= polynomials2.getPolynomial().size()) {
            // Get the highest power terms of the dividend and divisor
            int dividendPower = Collections.max(polynomials1.getPolynomial().keySet());
            int divisorPower = Collections.max(polynomials2.getPolynomial().keySet());

            // Calculate the coefficient and power of the next term in the quotient
            double quotientCoefficient = polynomials1.getPolynomial().get(dividendPower) / polynomials2.getPolynomial().get(divisorPower);
            int quotientPower = dividendPower - divisorPower;

            // Add the next term to the quotient
            resultPolynomial1.getPolynomial().put(quotientPower, quotientCoefficient);

            // Subtract the divisor times the next term in the quotient from the remainder
            for (int power : polynomials2.getPolynomial().keySet()) {
                double coefficient = polynomials2.getPolynomial().get(power) * quotientCoefficient;
                int remainderPower = power + quotientPower;
                double remainderCoefficient = polynomials1.getPolynomial().getOrDefault(remainderPower, 0.0);
                polynomials1.getPolynomial().put(remainderPower, remainderCoefficient - coefficient);
            }

            // Remove any terms with zero coefficients from the remainder
            polynomials1.getPolynomial().entrySet().removeIf(entry -> entry.getValue() == 0);
        }
        resultPolynomial2 = sum(polynomials1,resultPolynomial1);

        return resultPolynomial2;
    }

}
