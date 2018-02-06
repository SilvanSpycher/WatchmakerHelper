package ch.watchmaker.watchmakerhelper.adapters;

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
import ch.watchmaker.watchmakerhelper.model.adapters.Cogwheel;

/**
 * Created by silva on 05.11.2017.
 */

public class CogwheelResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<Cogwheel> data = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cogwheel_result_item, parent, false);

        return new CogwheelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Cogwheel cogwheel = data.get(position);
        CogwheelViewHolder cogwheelViewHolder = (CogwheelViewHolder) holder;

        if (position % 2 == 0) {
            cogwheelViewHolder.cwri_txt_number_left.setText(String.valueOf(cogwheel.getNumberOfTeeth()));
        } else {
            cogwheelViewHolder.cwri_txt_number_left.setVisibility(View.INVISIBLE);
            cogwheelViewHolder.cwri_img_cogwheel_left.setVisibility(View.INVISIBLE);
            cogwheelViewHolder.cwri_txt_number_right.setVisibility(View.VISIBLE);
            cogwheelViewHolder.cwri_img_cogwheel_right.setVisibility(View.VISIBLE);
            cogwheelViewHolder.cwri_txt_number_right.setText(String.valueOf(cogwheel.getNumberOfTeeth()));
        }
    }

    public void setData(ArrayList<Cogwheel> cogwheels){
        data = cogwheels;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CogwheelViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cwri_txt_number_left)TextView cwri_txt_number_left;
        @BindView(R.id.cwri_img_cogwheel_left)AppCompatImageView cwri_img_cogwheel_left;
        @BindView(R.id.cwri_txt_number_right)TextView cwri_txt_number_right;
        @BindView(R.id.cwri_img_cogwheel_right)AppCompatImageView cwri_img_cogwheel_right;

        public CogwheelViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
