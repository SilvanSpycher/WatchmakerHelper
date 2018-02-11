package ch.watchmaker.watchmakerhelper.fragments.cogwheelCalculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

import butterknife.BindView;
import butterknife.OnClick;
import ch.watchmaker.watchmakerhelper.R;
import ch.watchmaker.watchmakerhelper.fragments.base.BaseFragment;
import ch.watchmaker.watchmakerhelper.model.Result;
import ch.watchmaker.watchmakerhelper.presenters.cogwheelCalculator.CogwheelCalculatorActivityPresenter;
import ch.watchmaker.watchmakerhelper.presenters.cogwheelCalculator.CogwheelCalculatorInputFragmentPresenter;
import ch.watchmaker.watchmakerhelper.presenters.cogwheelCalculator.impl.CogwheelCalculatorInputFragmentPresenterImpl;


public class CogwheelCalculatorInputFragment extends BaseFragment implements CogwheelCalculatorInputFragmentPresenter.View {

    public static final String TAG = CogwheelCalculatorInputFragment.class.getName();
    private CogwheelCalculatorInputFragmentPresenter presenter;

    @BindView(R.id.fci_edt_startcog)EditText fci_edt_startcog;
    @BindView(R.id.fci_edt_endcog)EditText fci_edt_endcog;
    @BindView(R.id.fci_edt_error)EditText fci_edt_error;
    @BindView(R.id.fci_edt_minteeth)EditText fci_edt_minteeth;
    @BindView(R.id.fci_edt_maxteeth)EditText fci_edt_maxteeth;
    @BindView(R.id.fci_edt_nrcogs)EditText fci_edt_nrcogs;
    @BindView(R.id.fci_btn_calculate)Button fci_btn_calculate;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        if (presenter == null) {
            presenter = new CogwheelCalculatorInputFragmentPresenterImpl(this);
        }

        fci_edt_maxteeth.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    calculatePressed();
                    return true;
                }
                return false;
            }
        });

        return view;
    }

    /**
     *
     * @return
     */
    @Override
    public int getLayout() {
        return R.layout.fragment_cogwheel_input;
    }

    @OnClick(R.id.fci_btn_calculate)
    @Override
    public void calculatePressed() {
        if (fci_edt_startcog.getText().toString().equals("")) {
            fci_edt_startcog.setText(fci_edt_startcog.getHint());
        }
        if (fci_edt_endcog.getText().toString().equals("")) {
            fci_edt_endcog.setText(fci_edt_endcog.getHint());
        }
        if (fci_edt_error.getText().toString().equals("")) {
            fci_edt_error.setText(fci_edt_error.getHint());
        }
        if (fci_edt_minteeth.getText().toString().equals("")) {
            fci_edt_minteeth.setText(fci_edt_minteeth.getHint());
        }
        if (fci_edt_maxteeth.getText().toString().equals("")) {
            fci_edt_maxteeth.setText(fci_edt_maxteeth.getHint());
        }
        if (fci_edt_nrcogs.getText().toString().equals("")) {
            fci_edt_nrcogs.setText(fci_edt_nrcogs.getHint());
        }

        //TODO check for wrong inputs


        double startcog = Double.parseDouble(fci_edt_startcog.getText().toString());
        double endcog = Double.parseDouble(fci_edt_endcog.getText().toString());
        double error = Double.parseDouble(fci_edt_error.getText().toString());
        int minteeth;
        int maxteeth;
        int nrcogs;
        try {
            minteeth = Integer.parseInt(fci_edt_minteeth.getText().toString());
        } catch (Exception e) {
            minteeth = 6;
        }
        try {
            maxteeth = Integer.parseInt(fci_edt_maxteeth.getText().toString());
        } catch (Exception e) {
            maxteeth = 120;
        }
            try {
            nrcogs = Integer.parseInt(fci_edt_nrcogs.getText().toString());
        } catch (Exception e) {
            nrcogs = 4;
        }

        //set values if 0
        if (minteeth == 0) minteeth = 1;
        if (maxteeth == 0) maxteeth = 1000000;
        if (nrcogs == 0) nrcogs = 1000000;
        presenter.calculate(startcog, endcog, error, nrcogs, minteeth, maxteeth);
    }

    @Override
    public void changeToResultView(Queue<Result> results) {
        ArrayList resultList = new ArrayList(results);
        Collections.reverse(resultList);
        ((CogwheelCalculatorActivityPresenter.View) getActivity()).setResults(resultList);
        changeFragment(CogwheelCalculatorResultFragment.class, true, CogwheelCalculatorResultFragment.TAG);
    }
}
