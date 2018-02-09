package ch.watchmaker.watchmakerhelper.activities;

import android.os.Bundle;

import ch.watchmaker.watchmakerhelper.R;
import ch.watchmaker.watchmakerhelper.activities.base.BaseToolbarActivity;
import ch.watchmaker.watchmakerhelper.fragments.cogwheelCalculator.CogwheelCalculatorInputFragment;
import ch.watchmaker.watchmakerhelper.fragments.cogwheelCalculator.CogwheelCalculatorResultFragment;
import ch.watchmaker.watchmakerhelper.presenters.cogwheelCalculator.CogwheelCalculatorActivityPresenter;
import ch.watchmaker.watchmakerhelper.presenters.cogwheelCalculator.impl.CogwheelCalculatorActivityPresenterImpl;

public class Cogwheelcalculator extends BaseToolbarActivity implements CogwheelCalculatorActivityPresenter.View {

    private CogwheelCalculatorActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (presenter == null) {
            presenter = new CogwheelCalculatorActivityPresenterImpl(this);
        }

        if(savedInstanceState == null) {
            presenter.init();
        }
    }

    @Override
    public int getLayout() {
        return R.layout.activity_cogwheelcalculator;
    }


    @Override
    public void changeToCogwheelCalculatorInputFragment() {
        changeFragment(CogwheelCalculatorInputFragment.class, true, CogwheelCalculatorInputFragment.TAG);
    }

    @Override
    public void changeToCogwheelCalculatorResultFragment() {
        changeFragment(CogwheelCalculatorResultFragment.class, true, CogwheelCalculatorResultFragment.TAG);
    }

    @Override
    public void changeToolbarToSignatureStyle() {

    }
}
