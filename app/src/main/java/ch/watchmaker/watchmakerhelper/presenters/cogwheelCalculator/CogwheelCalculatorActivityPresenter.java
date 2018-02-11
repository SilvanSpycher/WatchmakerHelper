package ch.watchmaker.watchmakerhelper.presenters.cogwheelCalculator;

import java.util.ArrayList;

import ch.watchmaker.watchmakerhelper.model.Result;
import ch.watchmaker.watchmakerhelper.presenters.base.BasePresenter;
import ch.watchmaker.watchmakerhelper.presenters.base.ToolbarPresenter;

/**
 * Created by silva on 06.02.2018.
 */

public interface CogwheelCalculatorActivityPresenter extends BasePresenter, ToolbarPresenter {

    /**
     * initiate the presenter
     */
    void init();

    interface View {

        /**
         * changes the frame to start the activities starting fragment
         */
        void changeToCogwheelCalculatorResultFragment();

        /**
         * changes the frame to start the activities starting fragment
         */
        void changeToCogwheelCalculatorInputFragment();

        void setResults(ArrayList<Result> results);

        ArrayList<Result> getResults();
    }
}
