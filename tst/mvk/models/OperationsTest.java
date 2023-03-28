package mvk.models;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class OperationsTest {

    @Test
    void sum() {
        Polynomials polynomials1 = new Polynomials();
        Polynomials polynomials2 = new Polynomials();

        Polynomials auxPolynomial = new Polynomials();
        HashMap<Integer,Double> aux = new HashMap<>();
        aux.put(1,2.0);
        aux.put(3,3.0);
        auxPolynomial.setPolynomial(aux);

        HashMap<Integer,Double> pol1 = new HashMap<>();
        pol1.put(1,1.0);
        pol1.put(3,1.0);
        polynomials1.setPolynomial(pol1);

        HashMap<Integer,Double> pol2 = new HashMap<>();
        pol2.put(1,1.0);
        pol2.put(3,2.0);
        polynomials2.setPolynomial(pol2);


        Operations operations = new Operations(polynomials1,polynomials2);

        assertEquals(auxPolynomial.getPolynomial(),operations.sum(polynomials1,polynomials2).getPolynomial());
    }

    @Test
    void substractionTest() {


        Polynomials polynomials1 = new Polynomials();
        Polynomials polynomials2 = new Polynomials();

        Polynomials auxPolynomial = new Polynomials();
        HashMap<Integer,Double> aux = new HashMap<>();
        aux.put(1,0.0);
        aux.put(3,-1.0);
        auxPolynomial.setPolynomial(aux);

        HashMap<Integer,Double> pol1 = new HashMap<>();
        pol1.put(1,1.0);
        pol1.put(3,1.0);
        polynomials1.setPolynomial(pol1);

        HashMap<Integer,Double> pol2 = new HashMap<>();
        pol2.put(1,1.0);
        pol2.put(3,2.0);
        polynomials2.setPolynomial(pol2);


        Operations operations = new Operations(polynomials1,polynomials2);

        assertEquals(auxPolynomial.getPolynomial(),operations.substraction(polynomials1,polynomials2).getPolynomial());
    }


    @Test
    void derivation1() {



    }

    @Test
    void derivation2() {


        Polynomials polynomials1 = new Polynomials();
        Polynomials polynomials2 = new Polynomials();

        Polynomials auxPolynomial = new Polynomials();
        HashMap<Integer,Double> aux = new HashMap<>();
        aux.put(1,0.0);
        aux.put(3,-1.0);
        auxPolynomial.setPolynomial(aux);

        HashMap<Integer,Double> pol1 = new HashMap<>();
        pol1.put(1,1.0);
        pol1.put(3,1.0);
        polynomials1.setPolynomial(pol1);

        HashMap<Integer,Double> pol2 = new HashMap<>();
        pol2.put(1,1.0);
        pol2.put(3,2.0);
        polynomials2.setPolynomial(pol2);


        Operations operations = new Operations(polynomials1,polynomials2);

        assertEquals(auxPolynomial.getPolynomial(),operations.substraction(polynomials1,polynomials2).getPolynomial());
    }




    @Test
    void multiplicationTest() {

        Polynomials polynomials1 = new Polynomials();
        Polynomials polynomials2 = new Polynomials();

        Polynomials auxPolynomial = new Polynomials();
        HashMap<Integer,Double> aux = new HashMap<>();
        aux.put(4,9.0);

        auxPolynomial.setPolynomial(aux);

        HashMap<Integer,Double> pol1 = new HashMap<>();
        pol1.put(2,3.0);

        polynomials1.setPolynomial(pol1);

        HashMap<Integer,Double> pol2 = new HashMap<>();
        pol2.put(2,3.0);

        polynomials2.setPolynomial(pol2);


        Operations operations = new Operations(polynomials1,polynomials2);

        assertEquals(auxPolynomial.getPolynomial(),operations.multiplication(polynomials1,polynomials2).getPolynomial());
    }


}