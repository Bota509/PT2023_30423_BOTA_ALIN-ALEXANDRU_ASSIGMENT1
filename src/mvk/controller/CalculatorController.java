package mvk.controller;

import mvk.View.CalculatorView;
import mvk.models.Operations;
import mvk.models.Polynomials;
import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class CalculatorController {
//clasa care se ocupa de actiunile pe care le face utilizatorul
private CalculatorView calculatorView;
private Polynomials polynomials1;
private Polynomials polynomials2;
private Polynomials resultPolynomial1;
private Polynomials resultPolynomials2;
private Operations operations;


public  CalculatorController(CalculatorView calculatorView, Polynomials polynomials1,Polynomials polynomials2,Polynomials resultPolynomials1,Polynomials resultPolynomials2,Operations operations)
{
    this.calculatorView = calculatorView;
    this.polynomials1 = polynomials1;
    this.polynomials2 = polynomials2;
    this.resultPolynomial1 = resultPolynomials1;
    this.resultPolynomials2 = resultPolynomials2;
    this.operations = operations;

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

            resultPolynomial1 = operations.sum(polynomials1, polynomials2);
            polynomials1.displayResultedPolynomInOrder("sum", resultPolynomial1,calculatorView);
        }
    }

    class SubstractionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshHashMaps();
              read();
              resultPolynomial1 = operations.substraction(polynomials1,polynomials2);
            polynomials1.displayResultedPolynomInOrder("sub", resultPolynomial1,calculatorView);
        }
    }

    class MultiplicationListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            refreshHashMaps();
            read();
            resultPolynomial1 = operations.multiplication(polynomials1,polynomials2);
            polynomials1.displayResultedPolynomInOrder("mul",resultPolynomial1,calculatorView);

        }
    }

    class DivisionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
                refreshHashMaps();
                read();
               // HashMap<Integer, Double> remainder = new HashMap<>(polynomials1.getPolynomial());

                //make the sum between the two hashmaps

                resultPolynomials2 = operations.division(polynomials1,polynomials2);
                polynomials1.displayResultedPolynomInOrder("div",resultPolynomials2,calculatorView);
        }
    }

    class DerivationListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            refreshHashMaps();
            read();

            resultPolynomial1 = operations.derivation(polynomials1);
            polynomials1.displayResultedPolynomInOrder("der1",resultPolynomial1,calculatorView);
            resultPolynomials2 = operations.derivation(polynomials2);
            polynomials2.displayResultedPolynomInOrder("der2",resultPolynomials2,calculatorView);

        }
    }
    class IntegrationListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshHashMaps();
            read();
            resultPolynomial1 = operations.integration(polynomials1);
            polynomials1.displayResultedPolynomInOrder("int1",resultPolynomial1,calculatorView);
            resultPolynomials2 = operations.integration(polynomials2);
            polynomials2.displayResultedPolynomInOrder("int2",resultPolynomials2,calculatorView);
        }
    }
}
