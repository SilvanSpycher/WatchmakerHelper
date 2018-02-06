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
import ch.watchmaker.watchmakerhelper.model.adapters.Cogwheel;
import ch.watchmaker.watchmakerhelper.presenters.cogwheelCalculator.CogwheelCalculatorResultFragmentPresenter;
import ch.watchmaker.watchmakerhelper.presenters.cogwheelCalculator.impl.CogwheelCalculatorResultFragmentPresenterImpl;


public class CogwheelCalculatorResultFragment extends BaseFragment implements CogwheelCalculatorResultFragmentPresenter.View{

    public static final String TAG = CogwheelCalculatorResultFragment.class.getName();
    private CogwheelCalculatorResultFragmentPresenter presenter;
    private CogwheelResultAdapter adapter;

    private ArrayList<Cogwheel> cogwheels = new ArrayList<>();



    @BindView(R.id.fcr_rv_cogwheels)RecyclerView fcr_rv_cogwheels;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        Cogwheel cogwheel1 = new Cogwheel(3);
        Cogwheel cogwheel2 = new Cogwheel(4);
        Cogwheel cogwheel3 = new Cogwheel(5);


        cogwheels.add(cogwheel1);
        cogwheels.add(cogwheel2);
        cogwheels.add(cogwheel3);

        if (presenter == null) {
            presenter = new CogwheelCalculatorResultFragmentPresenterImpl(this);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

            adapter = new CogwheelResultAdapter();

            fcr_rv_cogwheels.setLayoutManager(layoutManager);
            fcr_rv_cogwheels.setAdapter(adapter);
            adapter.setData(cogwheels);

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
}
