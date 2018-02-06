package ch.watchmaker.watchmakerhelper.presenters.cogwheelCalculator;

/**
 * Created by silva on 07.12.2017.
 */

public interface CogwheelCalculatorInputFragmentPresenter {


    /**
     * calculate the
     * @param values
     */
    void calculate(double start, double end, double error, int maxwheels, int minteeth, int maxteeth);

    interface View {


        /**
         * when the calculate button is pressed
         */
        void calculatePressed();



    }
}
