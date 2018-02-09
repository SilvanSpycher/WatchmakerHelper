package ch.watchmaker.watchmakerhelper.adapters;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ch.watchmaker.watchmakerhelper.R;
import ch.watchmaker.watchmakerhelper.model.Result;

/**
 * Created by silva on 05.11.2017.
 */

public class CogwheelResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<Result> data = new ArrayList<>();
    private int selectedResult;
    private ResultAdapterCallback resultAdapterCallback;

    public CogwheelResultAdapter(ResultAdapterCallback callback) {
        this.resultAdapterCallback = callback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cogwheel_result_item, parent, false);

        return new ResultsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final Result result = data.get(position);
        ResultsViewHolder resultsViewHolder = (ResultsViewHolder) holder;
        resultsViewHolder.cwri_txt_error.setText(String.valueOf(result.getError()));
        resultsViewHolder.cwri_txt_nr_of_cogwheels.setText(String.valueOf(result.getCogs().size()));
        resultsViewHolder.cwri_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedResult = holder.getAdapterPosition();
                if (resultAdapterCallback != null) {
                    resultAdapterCallback.onResultChosen(selectedResult);
                }
            }
        });
    }

    public void setData(ArrayList<Result> results){
        data = results;
        notifyDataSetChanged();
    }

    public interface ResultAdapterCallback {
        void onResultChosen(int selectedResult);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ResultsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cwri_txt_error)TextView cwri_txt_error;
        @BindView(R.id.cwri_img_ruler_error)AppCompatImageView cwri_img_ruler_error;
        @BindView(R.id.cwri_txt_nr_of_cogwheels)TextView cwri_txt_nr_of_cogwheels;
        @BindView(R.id.cwri_img_nr_of_cogwheels)AppCompatImageView cwri_img_nr_of_cogwheels;
        @BindView(R.id.cwri_layout)ConstraintLayout cwri_layout;

        public ResultsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

}
