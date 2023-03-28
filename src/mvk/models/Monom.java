package mvk.models;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Monom {


    public void transform(@NotNull List<String> polynom1List, Polynomials polynomials) {
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

}
