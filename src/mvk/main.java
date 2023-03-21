package mvk;

import mvk.View.CalculatorView;
import mvk.controller.CalculatorController;
import mvk.models.Polynomials;

public class main {

    public static void main(String[] args) {
        Polynomials polynomials1 = new Polynomials();
        Polynomials polynomials2 = new Polynomials();
        Polynomials resultPolynomials = new Polynomials();
        CalculatorView calculatorView = new CalculatorView();
        CalculatorController calculatorController = new CalculatorController(calculatorView,polynomials1,polynomials2,resultPolynomials);

    }
}
