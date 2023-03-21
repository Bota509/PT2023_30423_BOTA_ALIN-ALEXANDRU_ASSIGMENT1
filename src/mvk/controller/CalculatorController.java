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
private Polynomials polynomials1;
private Polynomials polynomials2;
private Polynomials resultPolynomials;
private boolean isRead = false;

public  CalculatorController(CalculatorView calculatorView, Polynomials polynomials1,Polynomials polynomials2,Polynomials resultPolynomials)
{
    this.calculatorView = calculatorView;
    this.polynomials1 = polynomials1;
    this.polynomials2 = polynomials2;
    this.resultPolynomials = resultPolynomials;

    this.calculatorView.sumListener(new SumListener());
    this.calculatorView.substractionListener(new SubstractionListener());
    this.calculatorView.multiplicationListener(new MultiplicationListener());
    this.calculatorView.divisionListener(new DivisionListener());
    this.calculatorView.derivativeListener(new DerivationListener());
    this.calculatorView.integrationListener(new IntegrationListener());
}

    public void read()
    {
        isRead=true;
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
        resultPolynomials.getPolynomial().clear();
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
                    resultPolynomials.getPolynomial().put(entry.getKey(), newResult);
                }
                else
                {
                    resultPolynomials.getPolynomial().put(entry.getKey(), entry.getValue());
                }
            }
            for (Map.Entry<Integer, Double> entry : polynomials2.getPolynomial().entrySet())
            {
                if(!resultPolynomials.getPolynomial().containsKey(entry.getKey()))
                {
                    resultPolynomials.getPolynomial().put(entry.getKey(), entry.getValue());
                }
            }
            displayResultedPolynomInOrder("sum");
        }
    }

    //This is used only for displaying the polynomial in descending order by power
    private void displayResultedPolynomInOrder(String type) {
        TreeMap<Integer, Double> sortedDescendingdMap = new TreeMap<>(Collections.reverseOrder());//implement a treeMap to put the elements
        //from the hashmap in order by key -> in this case the power
        sortedDescendingdMap.putAll(resultPolynomials.getPolynomial());
        StringBuilder stringBuilder = new StringBuilder();
        String resultString;
        for (Map.Entry<Integer, Double> entry : sortedDescendingdMap.entrySet())
        {
            String coefficientInString = entry.getValue().toString();
            String powerInString = entry.getKey().toString();
            if(entry.getValue()!=0.0 && entry.getKey()!=0) {

                if (entry.getValue() > 0.0)
                    stringBuilder.append(" +").append(entry.getValue()).append("x^").append(entry.getKey());
                else
                    stringBuilder.append(" ").append(entry.getValue()).append("x^").append(entry.getKey());
            }
        }
        resultString = stringBuilder.toString();
        if(type.equals("sum"))       //depends on what type is equal to on displaying on the UI
         calculatorView.setTextAreaSumText(resultString);
        else if(type.equals("sub"))
            calculatorView.setTextAreaSubstractionText(resultString);
    }

    class SubstractionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshHashMaps();

              read();
            for (Map.Entry<Integer, Double> entry : polynomials1.getPolynomial().entrySet()) {
                if (polynomials2.getPolynomial().containsKey(entry.getKey())) {
                    double newResult = polynomials1.getPolynomial().get(entry.getKey()) - polynomials2.getPolynomial().get(entry.getKey());
                    resultPolynomials.getPolynomial().put(entry.getKey(), newResult);
                }
                else
                {
                    resultPolynomials.getPolynomial().put(entry.getKey(), entry.getValue());
                }
            }
            for (Map.Entry<Integer, Double> entry : polynomials2.getPolynomial().entrySet())
            {
                if(!resultPolynomials.getPolynomial().containsKey(entry.getKey()))
                {
                    resultPolynomials.getPolynomial().put(entry.getKey(), -entry.getValue());
                }
            }
            displayResultedPolynomInOrder("sub");
        }
    }

    class MultiplicationListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

                read();
        }
    }

    class DivisionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

               read();
        }
    }

    class DerivationListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

              read();
        }
    }

    class IntegrationListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            read();
        }
    }


}
