package ch.watchmaker.watchmakerhelper.fragments.cogwheelCalculator;

import ch.watchmaker.watchmakerhelper.R;
import ch.watchmaker.watchmakerhelper.fragments.base.BaseFragment;
import ch.watchmaker.watchmakerhelper.presenters.cogwheelCalculator.CogwheelCalculatorResultDetailFragmentPresenter;

/**
 * Created by silva on 09.02.2018.
 */

public class CogwheelCalculatorResultDetailFragment extends BaseFragment implements CogwheelCalculatorResultDetailFragmentPresenter.View, {

    public static final String TAG = CogwheelCalculatorResultDetailFragment.class.getName();
    private CogwheelCalculatorResultDetailFragmentPresenter presenter;
    private CogwheelResultDetailAdapter adapter;




    @Override
    public int getLayout() {
        return R.layout.fragment_cogwheel_result;
    }
}
