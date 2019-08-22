package com.motion.takku.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.motion.takku.Model.DetailTAK;
import com.motion.takku.Model.ListTAKAdapter;
import com.motion.takku.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class HomeFragment extends Fragment {
    FloatingActionButton fabInputTAK;
    Toolbar mtoolbar;
    private RecyclerView rvDetailTak;
    private List<DetailTAK> mListDetailTAK = new ArrayList<>();

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //init Toolbar
        mtoolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mtoolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("TAK-Ku");
        setHasOptionsMenu(true);

        fabInputTAK = view.findViewById(R.id.fab_home);
        fabInputTAK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new InputTAKFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        //init RecyclerView
        rvDetailTak = view.findViewById(R.id.rv_list_tak);
        rvDetailTak.setHasFixedSize(true);
        ListTAKAdapter takAdapter = new ListTAKAdapter(getContext(), mListDetailTAK);
        rvDetailTak.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvDetailTak.setAdapter(takAdapter);

        takAdapter.setOnItemClickCallback(new ListTAKAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(DetailTAK data) {
                showDetailTAK(data);
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListDetailTAK.add(new DetailTAK("Upacara 17 Agustus", "Kemahasiswaan Tel-U", "17/08/2018", 2));
        mListDetailTAK.add(new DetailTAK("Seminar Nasional Interfest", "HIMA IF", "01/09/2018", 5));
        mListDetailTAK.add(new DetailTAK("Seminar Internasional SEARCH", "SEARCH Tel-U", "15/11/2018", 7));
        mListDetailTAK.add(new DetailTAK("Asisten Praktikum DAP", "FIF Tel-U", "10/01/2018", 15));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.home_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void showDetailTAK (DetailTAK data) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("EXTRA_DETAIL_TAK", data);

            DetailTAKFragment detailTAKFragment = new DetailTAKFragment();
            detailTAKFragment.setArguments(bundle);

            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, detailTAKFragment)
                    .addToBackStack(null)
                    .commit();
    }

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home_notification){

        }
        return super.onOptionsItemSelected(item);
    }
    */
}
