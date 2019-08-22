package com.motion.takku.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.motion.takku.LoginActivity;
import com.motion.takku.Model.User;
import com.motion.takku.R;

public class ProfileFragment extends Fragment {
    private final String EXTRA_CURRENT_USER = "extra_current_user";
    private User CurrentUser;

    Toolbar mtoolbar;
    TextView userFullname, userJumlahtakStatus;
    ImageView userProfilePicture;
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

        userFullname = view.findViewById(R.id.tv_user_fullname);
        userJumlahtakStatus = view.findViewById(R.id.tv_user_jumlahtak_status);
        userProfilePicture = view.findViewById(R.id.iv_profile_picture);
        btnEditProfile = view.findViewById(R.id.btn_editprofile);
        btnLogout = view.findViewById(R.id.btn_logout);

        CurrentUser = getArguments().getParcelable("EXTRA_CURRENT_USER");

        userFullname.setText(CurrentUser.getName());
        String takStatus = CurrentUser.getJumlah_tak() + " TAK (" + CurrentUser.getStatus() + ")";
        userJumlahtakStatus.setText(takStatus);
        Glide.with(view)
                .load(CurrentUser.getProfile_image())
                .apply(new RequestOptions().override(125,125))
                .into(userProfilePicture);

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("EXTRA_CURRENT_USER", CurrentUser);

                EditProfileFragment editProfileFragment = new EditProfileFragment();
                editProfileFragment.setArguments(bundle);

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, editProfileFragment)
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
