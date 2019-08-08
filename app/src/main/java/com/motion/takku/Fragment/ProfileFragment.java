package com.motion.takku.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.motion.takku.LoginActivity;
import com.motion.takku.MainActivity;
import com.motion.takku.R;

public class ProfileFragment extends Fragment {
    Toolbar mtoolbar;

    Button btnEditProfile, btnLogout;

    public ProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mtoolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mtoolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Profile");

        btnEditProfile = view.findViewById(R.id.btn_editprofile);
        btnLogout = view.findViewById(R.id.btn_logout);

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction().
                        replace(R.id.fragment_container, new EditProfileFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }

}
