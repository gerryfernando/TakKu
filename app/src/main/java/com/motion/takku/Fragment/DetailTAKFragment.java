package com.motion.takku.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.motion.takku.Model.DetailTAK;
import com.motion.takku.R;

public class DetailTAKFragment extends Fragment {
    Toolbar mtoolbar;
    DetailTAK mDataTak;

    TextView tvEventName, tvEventDate, tvEventOrganizer, tvJumlahTAK;


    public DetailTAKFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detailtak, container, false);

        //init toolbar
        mtoolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mtoolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Detail Tak");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        mtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        tvEventName = view.findViewById(R.id.tv_event_name);
        tvEventDate = view.findViewById(R.id.tv_event_date);
        tvEventOrganizer = view.findViewById(R.id.tv_event_organizer);
        tvJumlahTAK = view.findViewById(R.id.tv_jumlah_tak_event);

        Bundle bundle = getArguments();
        mDataTak = bundle.getParcelable("EXTRA_DETAIL_TAK");

        tvEventName.setText(mDataTak.getNamaAcara());
        tvEventDate.setText(mDataTak.getTanggal());
        tvEventOrganizer.setText(mDataTak.getPenyelenggara());

        String jumTAk = String.valueOf(mDataTak.getJumlahTak()) + " TAK";
        tvJumlahTAK.setText(jumTAk);

        return view;
    }
}
