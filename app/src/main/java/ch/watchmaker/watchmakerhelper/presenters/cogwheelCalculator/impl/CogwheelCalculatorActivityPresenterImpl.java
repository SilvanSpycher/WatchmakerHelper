package ch.watchmaker.watchmakerhelper.presenters.cogwheelCalculator.impl;


import ch.watchmaker.watchmakerhelper.presenters.cogwheelCalculator.CogwheelCalculatorActivityPresenter;

/**
 * Created by silva on 05.11.2017.
 */

public class CogwheelCalculatorActivityPresenterImpl implements CogwheelCalculatorActivityPresenter {

    private View view;

    public CogwheelCalculatorActivityPresenterImpl(View view) {
        this.view = view;
    }


    @Override
    public void stopSubscriptionsOnPause() {

    }

    @Override
    public void onMenuItemSelected(int menuItemInternalId) {

    }

    @Override
    public void init() {
        view.changeToCogwheelCalculatorInputFragment();
    }
}
