package com.motion.takku;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.motion.takku.Model.User;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class Signup2Activity extends AppCompatActivity {
    public static final String EXTRA_USERNAME = "extra_username";
    public static final String EXTRA_EMAIL = "extra_email";
    public static final String EXTRA_PASSWORD = "extra_password";
    public static final int PICK_IMAGE_REQUEST = 1;

    private String id;
    private String UsernameInput;
    private String EmailInput;
    private String PasswordInput;
    private String NameInput;
    private int targetTAK1 = 0;

    Button btnSubmit, btnPlusTak, btnMinusTak;
    FrameLayout btnSelectPhoto;
    RelativeLayout progressBar;
    TextInputLayout TargetTak, Name;
    TextView errName, errProfilePicture, selectPhoto;
    ImageView profilePhotos;
    Uri mImageUri;

    private FirebaseAuth mAuth;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        btnSelectPhoto = findViewById(R.id.fl_profile_photo);
        selectPhoto = findViewById(R.id.tv_select_photo);
        profilePhotos = findViewById(R.id.iv_profile_photo);
        errProfilePicture = findViewById(R.id.err_profile_photos);

        Name = findViewById(R.id.et_signup_name);
        errName = findViewById(R.id.err_name);

        TargetTak = findViewById(R.id.et_signup_targettak);
        btnPlusTak = findViewById(R.id.btn_plus_tak);
        btnMinusTak = findViewById(R.id.btn_minus_tak);

        progressBar = findViewById(R.id.progress_bar);
        btnSubmit = findViewById(R.id.btn_signup_submit);

        mAuth = FirebaseAuth.getInstance();

        mStorageRef = FirebaseStorage.getInstance().getReference("Profile_photos");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users");

        btnSelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyImage.openChooserWithGallery(Signup2Activity.this, "Choose Picture", PICK_IMAGE_REQUEST);
            }
        });

        btnPlusTak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String targetTak = TargetTak.getEditText().getText().toString();
                if (!targetTak.isEmpty()) {
                    targetTAK1 = Integer.parseInt(targetTak);
                }
                targetTAK1++;
                TargetTak.getEditText().setText(String.valueOf(targetTAK1));
            }
        });

        btnMinusTak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String targetTak = TargetTak.getEditText().getText().toString();
                if (!targetTak.isEmpty()) {
                    targetTAK1 = Integer.parseInt(targetTak);
                }
                targetTAK1--;
                if (targetTAK1 >= 0) {
                    TargetTak.getEditText().setText(String.valueOf(targetTAK1));
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsernameInput = getIntent().getStringExtra(EXTRA_USERNAME);
                EmailInput = getIntent().getStringExtra(EXTRA_EMAIL);
                PasswordInput = getIntent().getStringExtra(EXTRA_PASSWORD);
                NameInput = Name.getEditText().getText().toString().trim();
                String targetTak = TargetTak.getEditText().getText().toString();
                if (!targetTak.isEmpty()) {
                    targetTAK1 = Integer.parseInt(targetTak);
                }

                if (!validateName(NameInput) | !validateProfilePicture()) {
                    return;
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(EmailInput, PasswordInput).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                id = firebaseUser.getUid();
                                User user = new User(id, NameInput, 0, "Pemula", UsernameInput, targetTAK1);
                                mDatabaseRef.child(id).setValue(user);

                                if (mUploadTask != null && mUploadTask.isInProgress()){
                                    Toast.makeText(Signup2Activity.this, "User register in process", Toast.LENGTH_SHORT).show();
                                } else {
                                    UploadProfilePicture(ExtractFileName());
                                }

                                progressBar.setVisibility(View.GONE);

                                Toast.makeText(getApplicationContext(), "User Registered Successfull", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Signup2Activity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            } else {
                                if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                    Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
            }
        });
    }

    /*
    private void openfileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                CropImage.activity(Uri.fromFile(imageFile))
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        .setFixAspectRatio(true)
                        .start(Signup2Activity.this);
            }

            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                super.onImagePickerError(e, source, type);
                Toast.makeText(Signup2Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                super.onCanceled(source, type);
            }
        });

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                mImageUri = result.getUri();

                Glide.with(this)
                        .load(mImageUri)
                        .into(profilePhotos);

                selectPhoto.setVisibility(View.GONE);
                profilePhotos.setVisibility(View.VISIBLE);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();

                Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        /*if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Glide.with(this).load(mImageUri).into(profilePhotos);
            selectPhoto.setVisibility(View.GONE);
            profilePhotos.setVisibility(View.VISIBLE);
        }*/
    }


    private String getFileExtension(Uri mImageUri){
        Uri file = Uri.fromFile(new File(mImageUri.getPath()));
        return MimeTypeMap.getFileExtensionFromUrl(file.toString());
    }

    private String ExtractFileName() {
        return System.currentTimeMillis() + "." + getFileExtension(mImageUri);
    }

    private void UploadProfilePicture(String nameFile) {
        if (mImageUri != null) {
            final StorageReference fileReference = mStorageRef.child(nameFile);
            mUploadTask = fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();

                            DatabaseReference databaseImage = mDatabaseRef.child(firebaseUser.getUid());

                            databaseImage.child("profile_image").setValue(String.valueOf(uri));

                            /*HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("ImageUrl", String.valueOf(uri));
                            databaseImage.setValue(hashMap);*/
                        }
                    });
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Signup2Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(Signup2Activity.this, "No profil picture selected", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateName(String UsernameInput) {
        if (UsernameInput.isEmpty()) {
            Name.getEditText().setBackground(getResources().getDrawable(R.drawable.bg_input_error));
            errName.setVisibility(View.VISIBLE);
            errName.setText(R.string.error_text_name_empty);
            return false;
        } else {
            errName.setVisibility(View.INVISIBLE);
            return true;
        }
    }

    private boolean validateProfilePicture () {
        if (profilePhotos.getVisibility() == View.GONE) {
            btnSelectPhoto.setBackground(getResources().getDrawable(R.drawable.bg_input_error));
            errProfilePicture.setVisibility(View.VISIBLE);
            errProfilePicture.setText(R.string.error_text_picture_empty);
            return false;
        } else {
            errProfilePicture.setVisibility(View.INVISIBLE);
            return true;
        }
    }
}
