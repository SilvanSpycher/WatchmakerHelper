package ch.watchmaker.watchmakerhelper.presenters.cogwheelCalculator.impl;

import android.util.Log;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

import ch.watchmaker.watchmakerhelper.model.Result;
import ch.watchmaker.watchmakerhelper.model.adapters.Cogwheel;
import ch.watchmaker.watchmakerhelper.presenters.cogwheelCalculator.CogwheelCalculatorInputFragmentPresenter;


public class CogwheelCalculatorInputFragmentPresenterImpl implements CogwheelCalculatorInputFragmentPresenter {

    private final int NROFITERATIONS = 100000;


    private View view;


    public CogwheelCalculatorInputFragmentPresenterImpl(View view) {
        this.view = view;
    }


    @Override
    public void calculate(double start, double end, double error, int maxwheels, int minteeth, int maxteeth) {
        double targetDecimal = end/start;
        algorithmStart(targetDecimal, error, maxwheels, minteeth, maxteeth);
    }

    private void algorithmStart(double targetDecimal, double maxerror, int maxwheels, int minteeth, int maxteeth) {
        int i = 1;
        double error = 9999999;
        int iterations = 1000000;
        int maxResults = 20;
        if (maxerror > 0) {
            error = maxerror;
        }

        Queue<Result> results = algorithm(i, targetDecimal, error, maxteeth, iterations, maxResults, minteeth, maxwheels);
        view.changeToResultView(results);
    }


    private Queue<Result> algorithm(int i, double targetDecimal, double error, int maxteeth, int iterations, int maxResults, int minteeth, int maxwheels) {
        List<Integer> iPrime;
        List<Integer> jPrime;
        int j;
        double approxDecimal;
        Queue<Result> resultsQueue = new ArrayDeque<>();

        while (i < iterations) {
            if (i == 531) {
                Log.d("TEST", "TEST");
            }
            j = (int) Math.round(i / targetDecimal);
            approxDecimal = (double) i / j;
            if (Math.abs(targetDecimal - approxDecimal) < error) {
                iPrime = primeFactors(i, maxteeth);
                jPrime = primeFactors(j, maxteeth);
                if (!primesTooBig(iPrime, jPrime, maxteeth)) {
                    double newError = Math.abs(targetDecimal - approxDecimal);
                    List<Cogwheel> cogs = calculateCogwheels(iPrime, jPrime, minteeth, maxteeth, maxwheels);
                    if (cogs != null) {
                        if (resultsQueue.size() >= maxResults) {
                            resultsQueue.poll();
                        }
                        resultsQueue.add(new Result(cogs, newError));
                        error = newError;
                    }
                }
            }
            i += 1;
        }

        return resultsQueue;
    }

    private List<Cogwheel> calculateCogwheels(List<Integer> iPrime, List<Integer> jPrime, int minteeth, int maxteeth, int maxwheels) {
        Collections.sort(iPrime);
        Collections.sort(jPrime);
        boolean impossible = false;

        //when either the smallest cog is still smaller than minteeth or the number of cogwheels is too big
        //put together the smallest two cogwheels and sort the list again
        while ((iPrime.get(0) < minteeth || iPrime.size() > maxwheels / 2) && iPrime.size() > 1) {
            int newElement = iPrime.get(0) * iPrime.get(1);
            iPrime.remove(0);
            iPrime.remove(0);
            iPrime.add(newElement);
            Collections.sort(iPrime);
            if (newElement > maxteeth) {
                impossible = true;
            }
        }
        //same for jPrime, but it doesnt test if jPrime uses more than half the wheels possible but if it is longer than iPrime
        while ((jPrime.get(0) < minteeth || jPrime.size()-iPrime.size() > 0) && jPrime.size() > 1) {
            int newElement = jPrime.get(0) * jPrime.get(1);
            jPrime.remove(0);
            jPrime.remove(0);
            jPrime.add(newElement);
            Collections.sort(jPrime);
            if (newElement > maxteeth) {
                impossible = true;
            }
        }

        // last check if iPrime is longer than jPrime and if so, put the smallest 2 together
        while (jPrime.size() - iPrime.size() < 0 && iPrime.size() > 1) {
            int newElement = iPrime.get(0) * iPrime.get(1);
            iPrime.remove(0);
            iPrime.remove(0);
            iPrime.add(newElement);
            if (newElement > maxteeth) {
                impossible = true;
            }
        }

        //edgecase with 2 cogs where one is smaller than minteeth
        if (jPrime.size() == 1 && jPrime.get(0) < minteeth) {
            int multiplicator = minteeth / jPrime.get(0);
            if (multiplicator == 1) {
                multiplicator = 2;
            }
            jPrime.add(jPrime.get(0) * multiplicator);
            jPrime.remove(0);
            iPrime.add(iPrime.get(0) * multiplicator);
            iPrime.remove(0);
            if (jPrime.get(0) > maxteeth || iPrime.get(0) > maxteeth) {
                impossible = true;
            }
        }
        if (iPrime.size() == 1 && iPrime.get(0) < minteeth) {
            int multiplicator = minteeth / iPrime.get(0);
            if (multiplicator == 1) {
                multiplicator = 2;
            }
            jPrime.add(jPrime.get(0) * multiplicator);
            jPrime.remove(0);
            iPrime.add(iPrime.get(0) * multiplicator);
            iPrime.remove(0);
            if (jPrime.get(0) > maxteeth || iPrime.get(0) > maxteeth) {
                impossible = true;
            }
        }

        List<Cogwheel> cogwheelList = new ArrayList<>();

        for (int i = 0; i < iPrime.size(); i++) {
            cogwheelList.add(new Cogwheel(iPrime.get(i)));
            cogwheelList.add(new Cogwheel(jPrime.get(i)));
        }

        if (impossible) {
            return null;
        } else {
            return cogwheelList;
        }
    }


    private boolean primesTooBig(List<Integer> iPrime, List<Integer> jPrime, int maxteeth) {
        return (iPrime.get(iPrime.size()-1) > maxteeth || jPrime.get(jPrime.size()-1) > maxteeth);
    }

    private List<Integer> primeFactors(int number, int maxteeth) {
        int n = number;
        List<Integer> factors = new ArrayList<>();
        int divisor = 2;
        while (divisor * divisor <= n && divisor <= maxteeth) {
            if (n % divisor == 0) {
                factors.add(divisor);
                n /= divisor;
            } else if (divisor == 2){
                divisor += 1;
            } else {
                divisor += 2;
            }
        }
        if (n > 1) {
            factors.add(n);
        }
        if (divisor > maxteeth) {
            factors.add(0, maxteeth+1);
        }

        if (factors.size() == 0) {
            factors.add(1);
        }

        return factors;
    }


}
