package ch.watchmaker.watchmakerhelper.presenters.cogwheelCalculator;

import java.util.Queue;

import ch.watchmaker.watchmakerhelper.model.Result;

/**
 * Created by silva on 07.12.2017.
 */

public interface CogwheelCalculatorInputFragmentPresenter {


    /**
     * calculate the values
     */
    void calculate(double start, double end, double error, int maxwheels, int minteeth, int maxteeth);

    interface View {


        /**
         * when the calculate button is pressed
         */
        void calculatePressed();

        /**
         * after calculating possible results the view has to change
         * @param resluts
         */
        void changeToResultView(Queue<Result> resluts);

    }
}
