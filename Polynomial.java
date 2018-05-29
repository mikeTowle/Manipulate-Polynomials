package Clm;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author mike 
 * holds data and data structure for polynomial 
 */
public class Polynomial {

    private LinkedList<Double> coeffs;

    
    //creates polynomial
    public Polynomial() {
        Double[] c = {0.0, 0.0, 0.0};
        this.coeffs = new LinkedList(Arrays.asList(c));
    }

    //creates polynomial from file name of type string
    public Polynomial(String s) {
        this.coeffs = parseString(s);
    }

    //gets coeffs from file and adds to coeffs linked list
    private LinkedList parseString(String s) {
        LinkedList polys = new LinkedList();
        int index = 0;
        if (!s.isEmpty()) {
            try {
                for (String co : s.split("\\s*,\\s*")) {
                    polys.add((Double.parseDouble(co)));
                }

            } catch (NumberFormatException e) {
                System.out.println("Number Formating Error");
            }
        }
        return polys;
    }

    //creates polynomial from array of coeffs
    public Polynomial(Double[] coeffs) {
        this.coeffs = new LinkedList(Arrays.asList(coeffs));
    }

    //creates new polynomial with Linked List of Coeffs
    public Polynomial(LinkedList<Double> coeffs) {
        this.coeffs = new LinkedList<>();
        boolean leadingZero = false;
        for (double ci : coeffs) {
            if (ci == 0.0 && leadingZero) {
                continue;
            }
            if (ci != 0.0) {
                leadingZero = false;
            }
            this.coeffs.add(ci);
        }
        if (this.coeffs.isEmpty()) {
            this.coeffs.add(0.0);
        }
    }

    //adds Double[] of coeffs to linkedList
    public void addArrayToPoly(Double[] d) {
        this.coeffs = new LinkedList(Arrays.asList(d));
    }

    //returns string
    @Override
    public String toString() {
        return this.coeffs.toString() + " ";
    }

    //prints poly in increasing order
    public String printInc() {
        String result = "";
        int i = 0;
        double d;
        if ((coeffs.size() - 1) == 0) {
            return coeffs.get(0).toString();
        }
        Iterator<Double> iterator = coeffs.descendingIterator();
        while (iterator.hasNext()) {
            d = iterator.next();
            if (d == 0.0) {
                i++;
                continue;
            } else if (d < 0) {
                result += " - " + -d;
            } else {
                result += " + " + d;
            }
            if (i != 0) {
                result += "x^" + i;
            }
            i++;
        }
        return result;
    }

    //prints polynomial in descending order
    public String printDescending() {
        String result = "";
        for (int i = 0; i < coeffs.size(); i++) {
            double d = coeffs.get(i);
            if (d == 0) {
                continue;
            }
            if (getPower(i) != 0) {
                result += d + "x^" + getPower(i) + " + ";
            } else {
                result += d;
            }
        }
        return result;
    }

    //multiplies this polynomial with @param polynomial
    public Polynomial multiply(Polynomial x) {
        int thisSize = this.coeffs.size();
        int xSize = x.coeffs.size();
        Double[] result = new Double[((xSize + thisSize) - 1)];

        for (int i = 0; i < (thisSize + xSize) - 1; i++) {
            result[i] = 0.0;
        }
        if (thisSize >= xSize) {
            for (int i = 0; i < thisSize; i++) {
                for (int j = 0; j < xSize; j++) {
                    result[i + j] += (this.coeffs.get(i) * x.coeffs.get(j));
                }
            }
        } else if (xSize > thisSize) {
            for (int i = 0; i < xSize; i++) {
                for (int j = 0; j < thisSize; j++) {
                    result[i + j] += (this.coeffs.get(j) * x.coeffs.get(i));
                }
            }
        }
        Polynomial multPoly = new Polynomial(result);
        return multPoly;

    }

    //gets power of polynomial based on linkedList index
    public int getPower(int i) {
        return coeffs.size() - i - 1;

    }

    //multiplies this polynomial by a double for subtracting 
    public Polynomial multiply(double d) {
        LinkedList<Double> newCo = new LinkedList<>();
        for (double c : coeffs) {
            newCo.add(d * c);
        }
        return new Polynomial(newCo);
    }

    //adds this polynomial to @param polynomial
    public Polynomial add(Polynomial p) {
        LinkedList<Double> newCo = new LinkedList<>();
        Iterator<Double> pIterator = p.coeffs.descendingIterator();
        Iterator<Double> thisIterator = this.coeffs.descendingIterator();
        double ci, di;

        while (pIterator.hasNext() && thisIterator.hasNext()) {
            newCo.addFirst(pIterator.next() + thisIterator.next());
        }
        while (pIterator.hasNext()) {
            newCo.addFirst(pIterator.next());
        }
        while (thisIterator.hasNext()) {
            newCo.addFirst(thisIterator.next());
        }
        return new Polynomial(newCo);
    }

    //subtracts 
    public Polynomial subtract(Polynomial p) {
        return add(p.multiply(-1.0));
    }
    
    public String printAll() {
        String polyStr = "";
        int i = coeffs.size()-1;
        
        for(double ci : coeffs) {
            polyStr += ci;
            if (i != 0 )
                polyStr += "x^" + i + " + ";
            i--;
        }
        return polyStr;
    }


    //prints coeeffs in linkedList
    public void printCoeffs() {
        System.out.println(coeffs.toString());
    }

    public double evaluate(double a) {
        int i = coeffs.size() - 1;
        double result = 0.0;
        for (double ci : coeffs) {
            result += ci * Math.pow(a, (double) i);
            i--;
        }
        return result;
    }

}
