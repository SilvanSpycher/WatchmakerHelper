package ch.watchmaker.watchmakerhelper.adapters;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ch.watchmaker.watchmakerhelper.R;
import ch.watchmaker.watchmakerhelper.model.Result;
import ch.watchmaker.watchmakerhelper.model.adapters.Cogwheel;



public class CogwheelResultDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Result data;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cogwheel_result_detail_item, parent, false);

        return new ResultDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Cogwheel cogwheel = data.getCogs().get(position);
        ResultDetailViewHolder resultDetailViewHolder = (ResultDetailViewHolder) holder;

        if (position % 2 == 0) {
            resultDetailViewHolder.cwrdi_txt_cogwheel_left.setText(String.valueOf(cogwheel.getNumberOfTeeth()));
        } else {
            resultDetailViewHolder.cwrdi_img_cogwheel_left.setVisibility(View.GONE);
            resultDetailViewHolder.cwrdi_txt_cogwheel_left.setVisibility(View.GONE);
            resultDetailViewHolder.cwrdi_img_cogwheel_right.setVisibility(View.VISIBLE);
            resultDetailViewHolder.cwrdi_txt_cogwheel_right.setVisibility(View.VISIBLE);
            resultDetailViewHolder.cwrdi_txt_cogwheel_right.setText(String.valueOf(cogwheel.getNumberOfTeeth()));
        }
    }

    public void setData(Result result){
        data = result;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.getCogs().size();
    }

    class ResultDetailViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cwrdi_txt_cogwheel_left)TextView cwrdi_txt_cogwheel_left;
        @BindView(R.id.cwrdi_img_cogwheel_left)AppCompatImageView cwrdi_img_cogwheel_left;
        @BindView(R.id.cwrdi_txt_cogwheel_right)TextView cwrdi_txt_cogwheel_right;
        @BindView(R.id.cwrdi_img_cogwheel_right)AppCompatImageView cwrdi_img_cogwheel_right;
        @BindView(R.id.cwrdi_layout)ConstraintLayout cwrdi_layout;

        private ResultDetailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
