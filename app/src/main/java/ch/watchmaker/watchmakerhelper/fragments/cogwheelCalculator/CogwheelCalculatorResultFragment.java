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
import ch.watchmaker.watchmakerhelper.adapters.CogwheelResultAdapter;
import ch.watchmaker.watchmakerhelper.fragments.base.BaseFragment;
import ch.watchmaker.watchmakerhelper.model.Result;
import ch.watchmaker.watchmakerhelper.model.adapters.Cogwheel;
import ch.watchmaker.watchmakerhelper.presenters.cogwheelCalculator.CogwheelCalculatorActivityPresenter;
import ch.watchmaker.watchmakerhelper.presenters.cogwheelCalculator.CogwheelCalculatorResultFragmentPresenter;
import ch.watchmaker.watchmakerhelper.presenters.cogwheelCalculator.impl.CogwheelCalculatorResultFragmentPresenterImpl;


public class CogwheelCalculatorResultFragment extends BaseFragment implements CogwheelCalculatorResultFragmentPresenter.View, CogwheelResultAdapter.ResultAdapterCallback{

    public static final String TAG = CogwheelCalculatorResultFragment.class.getName();
    private CogwheelCalculatorResultFragmentPresenter presenter;
    private CogwheelResultAdapter adapter;

    private ArrayList<Cogwheel> cogwheels = new ArrayList<>();



    @BindView(R.id.fcr_rv_results)RecyclerView fcr_rv_results;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        ArrayList<Result> results = ((CogwheelCalculatorActivityPresenter.View) getActivity()).getResults();

        if (presenter == null) {
            presenter = new CogwheelCalculatorResultFragmentPresenterImpl(this);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

            adapter = new CogwheelResultAdapter(this);

            fcr_rv_results.setLayoutManager(layoutManager);
            fcr_rv_results.setAdapter(adapter);
            adapter.setData(results);

        }


        return view;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_cogwheel_result;
    }


    @Override
    public void displayList(ArrayList<Cogwheel> cogwheels) {

    }

    @Override
    public void onResultChosen(int selectedResult) {
        Bundle bundle = new Bundle();
        bundle.putInt("ResultPosition", selectedResult);
        changeFragment(CogwheelCalculatorResultDetailFragment.class, true, CogwheelCalculatorResultDetailFragment.TAG, bundle);
    }
}
