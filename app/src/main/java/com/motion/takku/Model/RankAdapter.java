package com.motion.takku.Model;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.motion.takku.Fragment.EditProfileFragment;
import com.motion.takku.R;

import java.util.List;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ListViewHolder> {

    private Context mContext;
    private List<User> mData;

    public RankAdapter(Context mContext, List<User> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_row_rank, viewGroup, false);
        ListViewHolder viewHolder = new ListViewHolder(view);

        return  viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {

        holder.ivProfilePicture.setImageResource(mData.get(position).getProfile_image());
        holder.ivRankLogo.setImageResource(mData.get(position).getRank_logo_image());
        holder.tvUsername.setText(mData.get(position).getName());
        holder.tvStatus.setText(mData.get(position).getStatus());

        String jumlahTak = String.valueOf(mData.get(position).getJumlah_tak());
        holder.tvJumlahTAK.setText(jumlahTak);

        String ArrayPosition = String.valueOf(position+1);
        String rankPosition = "#" + ArrayPosition;
        holder.tvRankPosition.setText(rankPosition);

        if ((position+1) == getItemCount()) {
            Resources r = mContext.getResources();
            int px = (int) r.getDisplayMetrics().density;
            px = 50;

            holder.rlRank.setPadding(0,0,0, px);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfilePicture, ivRankLogo;
        RelativeLayout rlRank;
        TextView tvUsername, tvStatus, tvJumlahTAK, tvRankPosition;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            rlRank = itemView.findViewById(R.id.item_rank);
            ivProfilePicture = itemView.findViewById(R.id.iv_profile_picture);
            ivRankLogo = itemView.findViewById(R.id.iv_rank_logo);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvJumlahTAK = itemView.findViewById(R.id.tv_jumlah_tak);
            tvRankPosition = itemView.findViewById(R.id.tv_rank_position);
        }
    }
}
