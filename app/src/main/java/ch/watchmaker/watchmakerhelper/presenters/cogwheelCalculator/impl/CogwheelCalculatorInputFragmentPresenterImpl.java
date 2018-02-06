package ch.watchmaker.watchmakerhelper.presenters.cogwheelCalculator.impl;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ch.watchmaker.watchmakerhelper.presenters.cogwheelCalculator.CogwheelCalculatorInputFragmentPresenter;

/**
 * Created by silva on 07.12.2017.
 */

public class CogwheelCalculatorInputFragmentPresenterImpl implements CogwheelCalculatorInputFragmentPresenter {

    private final int NROFITERATIONS = 100000;


    private View view;


    public CogwheelCalculatorInputFragmentPresenterImpl(View view) {
        this.view = view;
    }


    @Override
    public void calculate(double start, double end, double error, int maxwheels, int minteeth, int maxteeth) {
        double target = start/end;
        algorithmStart(target, error, maxwheels, minteeth, maxteeth);
    }

    private void algorithmStart(double target, double maxerror, int maxwheels, int minteeth, int maxteeth) {
        int i = 1;
        double d1 = target;
        double d2 = 0;
        double error = 9999999;
        int iterations = 100000;
        if (maxerror > 0) {
            error = maxerror;
        }

        algorithm(i, d1, d2, error, maxteeth, iterations);

    }


    private boolean algorithm(int i, double d1, double d2, double error, int maxteeth, int iterations) {
        boolean searching = true;
        List<Integer> iPrime;
        List<Integer> jPrime;
        int j;

        while (searching) {
            j = (int) Math.ceil(i / d1);
            d2 = i / j;
            if (Math.abs(d1 - d2) < error) {
                iPrime = primeFactors(i);
                jPrime = primeFactors(j);
                boolean possibleSolution = true;
                for (Integer a : iPrime) {
                    if (a > maxteeth) {
                        possibleSolution = false;
                        break;
                    }
                }
                if (possibleSolution == true) {
                    for (Integer a : jPrime) {
                        if (a > maxteeth) {
                            possibleSolution = false;
                            break;
                        }
                    }
                }

                if (possibleSolution == true) {
                    error = Math.abs(d1 - d2);
                    save(iPrime, jPrime);
                    Log.d("POSSIBLE SOLUTION", iPrime.toString() + jPrime.toString());
                }
            }
            i += 1;
            if (i > iterations) {
                searching = false;
                break;
            }
        }

        return true;
    }

    private List<Integer> primeFactors(int number) {
        int n = number;
        List<Integer> factors = new ArrayList<Integer>();
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        return factors;
    }


    private void save(List<Integer> iPrime, List<Integer> jPrime) {
        //TODO impl


    }


}
