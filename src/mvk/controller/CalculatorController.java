package mvk.controller;

import mvk.View.CalculatorView;
import mvk.models.Polynomials;
import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class CalculatorController {
//clasa care se ocupa de actiunile pe care le face utilizatorul
private CalculatorView calculatorView;
private Polynomials polynomials;
private Polynomials polynomials1;
private Polynomials polynomials2;
private Polynomials resultPolynomial1;
private Polynomials resultPolynomials2;


public  CalculatorController(CalculatorView calculatorView, Polynomials polynomials1,Polynomials polynomials2,Polynomials resultPolynomials1,Polynomials resultPolynomials2,Polynomials polynomials)
{
    this.calculatorView = calculatorView;
    this.polynomials1 = polynomials1;
    this.polynomials2 = polynomials2;
    this.resultPolynomial1 = resultPolynomials1;
    this.resultPolynomials2 = resultPolynomials2;
    this.polynomials = polynomials;

    this.calculatorView.sumListener(new SumListener());
    this.calculatorView.substractionListener(new SubstractionListener());
    this.calculatorView.multiplicationListener(new MultiplicationListener());
    this.calculatorView.divisionListener(new DivisionListener());
    this.calculatorView.derivativeListener(new DerivationListener());
    this.calculatorView.integrationListener(new IntegrationListener());
}

    public void read()
    {
        String polynomString1 = calculatorView.getTextFieldPolynom1();
        String polynomString2 = calculatorView.getTextFieldPolynom2();

        List<String> polynom1List = new ArrayList<>();
        polynom1List.addAll(List.of(polynomString1.replace("-","+-").split("[+]")));

        List<String> polynom2List = new ArrayList<>();
        polynom2List.addAll(List.of(polynomString2.replace("-","+-").split("[+]")));
        //MERGE MOMENTAN CU MINUS DAR FARA SA PUI SPATIU INTRE MINUS SI NUMARUL CU CARE TREBUIE SA FIE LEGAT

        monom(polynom1List,polynomials1);  //mii se salveaza monoamele in functi de power respectiv coefficient in doua hashmap uri diferite
        monom(polynom2List,polynomials2);
    }

    public void monom(@NotNull List<String> polynom1List,Polynomials polynomials) {
        for (String monom : polynom1List)
        {
            double coefficient = 0;
            int power = 0;
            if (monom.length() > 0)
            {

                if (monom.equals("x") )
                {
                    coefficient = 1;
                    power = 1;
                } else if (monom.equals("-x") )
                {
                    coefficient = -1;
                    power = 1;
                } else if (!monom.contains("x") )    //if x has power equal to zero
                {
                    coefficient = Double.parseDouble(monom);
                    power = 0;
                }
                else {
                    String coefficientString;
                    coefficientString = !monom.isEmpty() ? monom.substring(0, monom.indexOf("x")).trim() : "none";
                    // coefficient = Double.parseDouble(coefficientString);
                    coefficient = Double.parseDouble(coefficientString);
                    if(monom.contains("^")) {
                        String powerString = !monom.isEmpty() ? monom.substring(monom.indexOf("^") + 1, monom.length()).trim() : "none";
                        power = Integer.parseInt(powerString);
                    }
                    else {
                        power = 1;
                    }
                }
            }
            polynomials.addInHashMap(coefficient,power);
            //PUNE IN HASHMAPU PE CARE O SA L CREEZI IN CLASA POLYNOM
        }
    }

    public void refreshHashMaps()
    {
        resultPolynomial1.getPolynomial().clear();
        resultPolynomials2.getPolynomial().clear();
        polynomials1.getPolynomial().clear();
        polynomials2.getPolynomial().clear();
    }

    class SumListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshHashMaps();
               read();

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
            polynomials1.displayResultedPolynomInOrder("sum", resultPolynomial1,calculatorView);
        }
    }

    //This is used only for displaying the polynomial in descending order by power


    class SubstractionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshHashMaps();

              read();
            for (Map.Entry<Integer, Double> entry : polynomials1.getPolynomial().entrySet()) {
                if (polynomials2.getPolynomial().containsKey(entry.getKey())) {
                    double newResult = polynomials1.getPolynomial().get(entry.getKey()) - polynomials2.getPolynomial().get(entry.getKey());
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
                    resultPolynomial1.getPolynomial().put(entry.getKey(), -entry.getValue());
                }
            }
            polynomials1.displayResultedPolynomInOrder("sub", resultPolynomial1,calculatorView);
        }
    }

    class MultiplicationListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            refreshHashMaps();
            read();

            for (Map.Entry<Integer, Double> entry1 : polynomials1.getPolynomial().entrySet())
            {
                for (Map.Entry<Integer, Double> entry2 : polynomials2.getPolynomial().entrySet())
                {
                    resultPolynomial1.getPolynomial().put(entry1.getKey()+entry2.getKey(),entry1.getValue()*entry2.getValue());
                }
            }
            polynomials1.displayResultedPolynomInOrder("mul",resultPolynomial1,calculatorView);

        }
    }

    class DivisionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
                refreshHashMaps();
               read();

                HashMap<Integer, Double> quotient = new HashMap<>();
                HashMap<Integer, Double> remainder = new HashMap<>(polynomials1.getPolynomial());

                while (remainder.size() >= polynomials2.getPolynomial().size()) {
                    // Get the highest power terms of the dividend and divisor
                    int dividendPower = Collections.max(remainder.keySet());
                    int divisorPower = Collections.max(polynomials2.getPolynomial().keySet());

                    // Calculate the coefficient and power of the next term in the quotient
                    double quotientCoefficient = remainder.get(dividendPower) / polynomials2.getPolynomial().get(divisorPower);
                    int quotientPower = dividendPower - divisorPower;

                    // Add the next term to the quotient
                    resultPolynomial1.getPolynomial().put(quotientPower, quotientCoefficient);

                    // Subtract the divisor times the next term in the quotient from the remainder
                    for (int power : polynomials2.getPolynomial().keySet()) {
                        double coefficient = polynomials2.getPolynomial().get(power) * quotientCoefficient;
                        int remainderPower = power + quotientPower;
                        double remainderCoefficient = remainder.getOrDefault(remainderPower, 0.0);
                        remainder.put(remainderPower, remainderCoefficient - coefficient);
                    }

                    // Remove any terms with zero coefficients from the remainder
                    remainder.entrySet().removeIf(entry -> entry.getValue() == 0);
                }
                polynomials1.displayResultedPolynomInOrder("div",resultPolynomial1,calculatorView);

        }
    }

    class DerivationListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            refreshHashMaps();
            read();

            double newCoefficient;
            int newPower;
            for (Map.Entry<Integer, Double> entry : polynomials1.getPolynomial().entrySet())
            {
                if(entry.getKey()>0)
                {
                    newCoefficient = entry.getKey() * entry.getValue();
                    newPower = entry.getKey() - 1;
                    resultPolynomial1.getPolynomial().put(newPower,newCoefficient);
                }
            }
            for (Map.Entry<Integer, Double> entry : polynomials2.getPolynomial().entrySet())
            {
                if(entry.getKey()>0)
                {
                    newCoefficient = entry.getKey() * entry.getValue();
                    newPower = entry.getKey() - 1;
                    resultPolynomials2.getPolynomial().put(newPower,newCoefficient);
                }
            }
            polynomials1.displayResultedPolynomInOrder("der1",resultPolynomial1,calculatorView);
            polynomials2.displayResultedPolynomInOrder("der2",resultPolynomials2,calculatorView);

        }
    }

    class IntegrationListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshHashMaps();
            read();
            double newCoefficient;
            int newPower;


            for (Map.Entry<Integer, Double> entry : polynomials1.getPolynomial().entrySet())
            {
                if(entry.getKey() >=0)
                {
                    newPower = entry.getKey()+1;
                    newCoefficient = entry.getValue() / newPower;

                    resultPolynomial1.getPolynomial().put(newPower,newCoefficient);
                }
            }
           polynomials1.displayResultedPolynomInOrder("int1",resultPolynomial1,calculatorView);
            for (Map.Entry<Integer, Double> entry : polynomials2.getPolynomial().entrySet())
            {
                if(entry.getKey() >=0)
                {
                    newPower = entry.getKey()+1;
                    newCoefficient = entry.getValue() / newPower;

                    resultPolynomials2.getPolynomial().put(newPower,newCoefficient);
                }
            }
            polynomials2.displayResultedPolynomInOrder("int2",resultPolynomials2,calculatorView);
        }
    }
}
