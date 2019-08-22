package com.motion.takku.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.motion.takku.Model.RankAdapter;
import com.motion.takku.Model.User;
import com.motion.takku.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RankFragment extends Fragment {
    private Toolbar mtoolbar;
    private RecyclerView rvRank;
    private List<User> mListUser = new ArrayList<>();

    public RankFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rank, container, false);

        //initToolbar
        mtoolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mtoolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Rank");

        //init RecyclerView
        rvRank = view.findViewById(R.id.rv_rank);
        rvRank.setHasFixedSize(true);
        rvRank.setNestedScrollingEnabled(false);
        RankAdapter rankAdapter = new RankAdapter(getContext(), mListUser);
        rvRank.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvRank.setAdapter(rankAdapter);


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListUser.add(new User("Hamad Fauzi Jesar", "https://upload.wikimedia.org/wikipedia/commons/b/be/Col_Gatot_Subroto%2C_Kenang-Kenangan_Pada_Panglima_Besar_Letnan_Djenderal_Soedirman%2C_p27.jpg", 500, "Superman"));
        mListUser.add(new User("Firman Ramdhani", "https://upload.wikimedia.org/wikipedia/commons/b/be/Col_Gatot_Subroto%2C_Kenang-Kenangan_Pada_Panglima_Besar_Letnan_Djenderal_Soedirman%2C_p27.jpg", 1000, "The King"));
        mListUser.add(new User("Yudha Alsepky", "https://upload.wikimedia.org/wikipedia/commons/b/be/Col_Gatot_Subroto%2C_Kenang-Kenangan_Pada_Panglima_Besar_Letnan_Djenderal_Soedirman%2C_p27.jpg", 60, "Senior"));
        mListUser.add(new User("Restu Assegaf", "https://upload.wikimedia.org/wikipedia/commons/b/be/Col_Gatot_Subroto%2C_Kenang-Kenangan_Pada_Panglima_Besar_Letnan_Djenderal_Soedirman%2C_p27.jpg", 140, "Aktifis"));
        mListUser.add(new User("Dedih Kurnia", "https://upload.wikimedia.org/wikipedia/commons/b/be/Col_Gatot_Subroto%2C_Kenang-Kenangan_Pada_Panglima_Besar_Letnan_Djenderal_Soedirman%2C_p27.jpg", 480, "Superman"));
        mListUser.add(new User("John Doe", "https://upload.wikimedia.org/wikipedia/commons/b/be/Col_Gatot_Subroto%2C_Kenang-Kenangan_Pada_Panglima_Besar_Letnan_Djenderal_Soedirman%2C_p27.jpg", 29, "Pemula"));

        Collections.sort(mListUser, new SortbyJumlahTak());
    }

    class SortbyJumlahTak implements Comparator<User> {

        @Override
        public int compare(User o1, User o2) {
            return o2.getJumlah_tak() - o1.getJumlah_tak();
        }
    }
}
