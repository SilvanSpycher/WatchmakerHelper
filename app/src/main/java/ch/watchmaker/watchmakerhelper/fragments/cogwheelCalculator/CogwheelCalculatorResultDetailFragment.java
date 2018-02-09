package ch.watchmaker.watchmakerhelper.fragments.cogwheelCalculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import ch.watchmaker.watchmakerhelper.R;
import ch.watchmaker.watchmakerhelper.adapters.CogwheelResultDetailAdapter;
import ch.watchmaker.watchmakerhelper.fragments.base.BaseFragment;
import ch.watchmaker.watchmakerhelper.model.Result;
import ch.watchmaker.watchmakerhelper.presenters.cogwheelCalculator.CogwheelCalculatorActivityPresenter;
import ch.watchmaker.watchmakerhelper.presenters.cogwheelCalculator.CogwheelCalculatorResultDetailFragmentPresenter;
import ch.watchmaker.watchmakerhelper.presenters.cogwheelCalculator.impl.CogwheelCalculatorResultDetailFragmentPresenterImpl;

public class CogwheelCalculatorResultDetailFragment extends BaseFragment implements CogwheelCalculatorResultDetailFragmentPresenter.View {

    public static final String TAG = CogwheelCalculatorResultDetailFragment.class.getName();
    private CogwheelCalculatorResultDetailFragmentPresenter presenter;
    private CogwheelResultDetailAdapter adapter;
    private int resultPosition;

    @BindView(R.id.fcr_rv_results)RecyclerView fcr_rv_results;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        if (savedInstanceState != null) {
            resultPosition = savedInstanceState.getInt("ResultPosition");
        } else {
            resultPosition = getArguments().getInt("ResultPosition");
        }

        ArrayList<Result> results = ((CogwheelCalculatorActivityPresenter.View) getActivity()).getResults();

        if (presenter == null) {
            presenter = new CogwheelCalculatorResultDetailFragmentPresenterImpl(this);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        adapter = new CogwheelResultDetailAdapter();

        fcr_rv_results.setLayoutManager(layoutManager);
        fcr_rv_results.setAdapter(adapter);
        adapter.setData(results.get(resultPosition));



        return view;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_cogwheel_result;
    }
}
