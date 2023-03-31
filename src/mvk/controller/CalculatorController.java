package mvk.controller;

import mvk.View.CalculatorView;
import mvk.models.Monom;
import mvk.models.Operations;
import mvk.models.Polynomials;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;


public class CalculatorController {
//clasa care se ocupa de actiunile pe care le face utilizatorul
private CalculatorView calculatorView;
private Polynomials polynomials1;
private Polynomials polynomials2;
private Polynomials polynomials;
private Polynomials resultPolynomial1;
private Polynomials resultPolynomials2;
private Polynomials[] divisionPolynomials;
private Operations operations;
private Monom monom;


public  CalculatorController(CalculatorView calculatorView,Polynomials polynomials, Polynomials polynomials1,Polynomials polynomials2,
                             Polynomials resultPolynomials1,Polynomials resultPolynomials2,Operations operations,Monom monom,
                             Polynomials[] divisionPolynomials)
{
    this.calculatorView = calculatorView;
    this.polynomials1 = polynomials1;
    this.polynomials2 = polynomials2;
    this.resultPolynomial1 = resultPolynomials1;
    this.resultPolynomials2 = resultPolynomials2;
    this.operations = operations;
    this.polynomials = polynomials;
    this.monom = monom;
    this.divisionPolynomials = divisionPolynomials;

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

        monom.transform(polynom1List,polynomials1);  //mii se salveaza monoamele in functi de power respectiv coefficient in doua hashmap uri diferite
        monom.transform(polynom2List,polynomials2);
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
            polynomials1.displayResultedPolynomialInOrder("sum", resultPolynomial1,calculatorView);
        }
    }

    class SubstractionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshHashMaps();
              read();
              resultPolynomial1 = operations.substraction(polynomials1,polynomials2);
            polynomials1.displayResultedPolynomialInOrder("sub", resultPolynomial1,calculatorView);
        }
    }

    class MultiplicationListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            refreshHashMaps();
            read();
            resultPolynomial1 = operations.multiplication(polynomials1,polynomials2);
            polynomials1.displayResultedPolynomialInOrder("mul",resultPolynomial1,calculatorView);

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
               calculatorView.setTextAreaDivisionText("");

                divisionPolynomials = operations.division(polynomials1,polynomials2);

                polynomials1.displayResultedPolynomialInOrder("div",divisionPolynomials[0],calculatorView);
            calculatorView.setTextAreaDivisionText(calculatorView.getTextAreaDivisionText() + " r :");
            polynomials1.displayResultedPolynomialInOrder("div",divisionPolynomials[1],calculatorView);

        }
    }

    class DerivationListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            refreshHashMaps();
            read();

            resultPolynomial1 = operations.derivation(polynomials1);
            polynomials1.displayResultedPolynomialInOrder("der1",resultPolynomial1,calculatorView);
            resultPolynomials2 = operations.derivation(polynomials2);
            polynomials2.displayResultedPolynomialInOrder("der2",resultPolynomials2,calculatorView);

        }
    }
    class IntegrationListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshHashMaps();
            read();
            resultPolynomial1 = operations.integration(polynomials1);
            polynomials1.displayResultedPolynomialInOrder("int1",resultPolynomial1,calculatorView);
            resultPolynomials2 = operations.integration(polynomials2);
            polynomials2.displayResultedPolynomialInOrder("int2",resultPolynomials2,calculatorView);
        }
    }
}
