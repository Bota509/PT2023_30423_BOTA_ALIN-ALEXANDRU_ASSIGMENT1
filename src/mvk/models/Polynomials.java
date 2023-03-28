package mvk.models;

import mvk.View.CalculatorView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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

    public void displayResultedPolynomialInOrder(String type, Polynomials resultPolynomial, CalculatorView calculatorView) {
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
        if(resultString.equals(""))
        {
            resultString = "0";
        }
        if(type.equals("sum"))       //depends on what type is equal to on displaying on the UI
            calculatorView.setTextAreaSumText(resultString);
        else if(type.equals("sub"))
            calculatorView.setTextAreaSubstractionText(resultString);
        else if(type.equals("der1")) {
            calculatorView.setTextAreaDerivationPolynom1Text(resultString);
        }
        else if(type.equals("der2")) {
            calculatorView.setTextAreaDerivationPolynom2Text(resultString);
        }

        else if(type.equals("int1")) {
            calculatorView.setTextAreaIntegrationPolynom1Text(resultString);
        }
        else if(type.equals("int2")) {
            calculatorView.setTextAreaIntegrationPolynom2Text(resultString);
        }
        else if(type.equals("mul"))
        {
            calculatorView.setTextAreaMultiplicationText(resultString);
        }
        else  if (type.equals("div"))
        {
            calculatorView.setTextAreaDivisionText(resultString);
        }
        else
        {
            System.out.println("Error");
        }

    }
}
