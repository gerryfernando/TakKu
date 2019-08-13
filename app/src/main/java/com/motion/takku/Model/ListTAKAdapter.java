package com.motion.takku.Model;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.motion.takku.R;

import java.util.List;

public class ListTAKAdapter extends RecyclerView.Adapter<ListTAKAdapter.ListViewHolder> {

    private Context mContext;
    private List<DetailTAK> mData;

    public ListTAKAdapter(Context mContext, List<DetailTAK> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_row_tak, viewGroup, false);

         ListViewHolder viewHolder = new ListViewHolder(view);
         return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        String jumlahTAK = String.valueOf(mData.get(position).getJumlahTak());
        String tak = jumlahTAK + " TAK";

        holder.tvJumlahTAK.setText(tak);
        holder.tvNamaEvent.setText(mData.get(position).getNamaAcara());
        holder.tvTanggalEvent.setText(mData.get(position).getTanggal());
        holder.tvPenyelenggara.setText(mData.get(position).getPenyelenggara());


        if ((position+1) == getItemCount()) {
            Resources r = mContext.getResources();
            int px = (int) r.getDisplayMetrics().density;
            px = 100;

            holder.rlListTak.setPadding(0,0,0, px);
            holder.vLineBawah.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvJumlahTAK, tvNamaEvent, tvTanggalEvent, tvPenyelenggara;
        RelativeLayout rlListTak;
        View vLineBawah;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            rlListTak = itemView.findViewById(R.id.item_list_tak);
            tvJumlahTAK = itemView.findViewById(R.id.tv_jumlah_tak_event);
            tvNamaEvent = itemView.findViewById(R.id.tv_event_name);
            tvTanggalEvent = itemView.findViewById(R.id.tv_event_date);
            tvPenyelenggara = itemView.findViewById(R.id.tv_event_organizer);
            vLineBawah = itemView.findViewById(R.id.v_line_bawah);
        }
    }
}
