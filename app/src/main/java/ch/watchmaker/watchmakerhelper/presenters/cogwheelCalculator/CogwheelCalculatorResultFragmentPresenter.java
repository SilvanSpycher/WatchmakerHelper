package ch.watchmaker.watchmakerhelper.presenters.cogwheelCalculator;

import java.util.ArrayList;

import ch.watchmaker.watchmakerhelper.model.adapters.Cogwheel;

/**
 * Created by silva on 05.11.2017.
 */

public interface CogwheelCalculatorResultFragmentPresenter {

    /**
     * initiates the presenter
     */
    void init();


    interface View {

        /**
         * displays the solution
         * @param cogwheels arraylist of cogwheels
         */
        void displayList(ArrayList<Cogwheel> cogwheels);


    }

}
