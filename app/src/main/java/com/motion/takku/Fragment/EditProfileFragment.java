package com.motion.takku.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.motion.takku.Model.User;
import com.motion.takku.R;
import com.motion.takku.Signup2Activity;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class EditProfileFragment extends Fragment {

    public static final int PICK_IMAGE_REQUEST = 1;

    Toolbar mtoolbar;
    TextInputLayout editName, editTargetTak;
    FrameLayout btnSelectPhoto;
    TextView erreditName;
    ImageView editprofilePicture, profilePhotos;
    Button btnUpdate, btnPlusTak, btnMinusTak;

    private int targetTAK1;
    Uri mImageUri;

    User CurrentUser;

    public EditProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        CurrentUser = getArguments().getParcelable("EXTRA_CURRENT_USER");

        btnSelectPhoto = view.findViewById(R.id.fl_profile_photo);
        profilePhotos = view.findViewById(R.id.iv_profile_photo);

        editName = view.findViewById(R.id.et_editprofile_name);
        erreditName = view.findViewById(R.id.err_name);

        editTargetTak = view.findViewById(R.id.et_editprofile_targettak);
        btnPlusTak = view.findViewById(R.id.btn_plus_tak);
        btnMinusTak = view.findViewById(R.id.btn_minus_tak);
        targetTAK1 = CurrentUser.getTak_target();

        editprofilePicture = view.findViewById(R.id.iv_profile_picture);

        editName.getEditText().setHint(CurrentUser.getName());
        editTargetTak.getEditText().setHint(String.valueOf(CurrentUser.getTak_target()));
        Glide.with(view)
                .load(CurrentUser.getProfile_image())
                .apply(new RequestOptions().override(125,125))
                .into(editprofilePicture);

        mtoolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mtoolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Edit Profile");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        mtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        /*btnSelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyImage.openChooserWithGallery(getActivity(), "Choose Picture", PICK_IMAGE_REQUEST);
            }
        });*/

        btnPlusTak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String targetTak = editTargetTak.getEditText().getText().toString();
                if (!targetTak.isEmpty()) {
                    targetTAK1 = Integer.parseInt(targetTak);
                }
                targetTAK1++;
                editTargetTak.getEditText().setText(String.valueOf(targetTAK1));
            }
        });

        btnMinusTak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String targetTak = editTargetTak.getEditText().getText().toString();
                if (!targetTak.isEmpty()) {
                    targetTAK1 = Integer.parseInt(targetTak);
                }
                targetTAK1--;
                if (targetTAK1 >= 0) {
                    editTargetTak.getEditText().setText(String.valueOf(targetTAK1));
                }
            }
        });

        btnUpdate = view.findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
            }
        });


        return view;
    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new DefaultCallback() {
            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                CropImage.activity(Uri.fromFile(imageFile))
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        .setFixAspectRatio(true)
                        .start(getActivity());
            }

            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                super.onImagePickerError(e, source, type);
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                super.onCanceled(source, type);
            }
        });

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == -1) {
                mImageUri = result.getUri();

                Glide.with(this)
                        .load(mImageUri)
                        .into(profilePhotos);


                profilePhotos.setVisibility(View.VISIBLE);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();

                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }*/

    private boolean validateName(String UsernameInput) {
        if (UsernameInput.isEmpty()) {
            editName.getEditText().setBackground(getResources().getDrawable(R.drawable.bg_input_error));
            erreditName.setVisibility(View.VISIBLE);
            erreditName.setText(R.string.error_text_name_empty);
            return false;
        } else {
            erreditName.setVisibility(View.INVISIBLE);
            return true;
        }
    }
}
