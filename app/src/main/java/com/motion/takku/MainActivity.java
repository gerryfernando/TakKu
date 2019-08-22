 package com.motion.takku;

 import android.content.Intent;
 import android.os.Bundle;
 import android.support.annotation.NonNull;
 import android.support.annotation.Nullable;
 import android.support.design.widget.BottomNavigationView;
 import android.support.v4.app.Fragment;
 import android.support.v7.app.AppCompatActivity;
 import android.view.MenuItem;
 import android.widget.Toast;

 import com.google.firebase.auth.FirebaseAuth;
 import com.google.firebase.auth.FirebaseUser;
 import com.google.firebase.database.DataSnapshot;
 import com.google.firebase.database.DatabaseError;
 import com.google.firebase.database.DatabaseReference;
 import com.google.firebase.database.FirebaseDatabase;
 import com.google.firebase.database.ValueEventListener;
 import com.motion.takku.Fragment.EventsFragment;
 import com.motion.takku.Fragment.HomeFragment;
 import com.motion.takku.Fragment.ProfileFragment;
 import com.motion.takku.Fragment.RankFragment;
 import com.motion.takku.Model.User;

 import java.util.ArrayList;
 import java.util.List;

 public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;

    private String id;
    private FirebaseAuth mAuth;
    DatabaseReference mDatabaseUserRef;

    User CurrentUser;
    List<User> mListUser = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        id = firebaseUser.getUid();

        mDatabaseUserRef = FirebaseDatabase.getInstance().getReference().child("Users");


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            gotoHome();
                            break;
                        case R.id.nav_event:
                            selectedFragment = new EventsFragment();
                            break;
                        case R.id.nav_rank:
                            selectedFragment = new RankFragment();
                            break;
                        case R.id.nav_profile:
                            gotoProfile();
                            break;
                    }

                    if (selectedFragment !=null){
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment)
                                .commit();
                    }

                    return true;
                }
            };

    public void gotoHome(){
        mDatabaseUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mListUser.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    if (dataSnapshot.exists()){
                        User user = postSnapshot.getValue(User.class);
                        if (user.getId().equals(id)) {
                            CurrentUser = user;
                        }

                        Bundle bundle = new Bundle();
                        bundle.putParcelable("EXTRA_CURRENT_USER", user);

                        HomeFragment homeFragment = new HomeFragment();
                        homeFragment.setArguments(bundle);

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment)
                                .commit();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void gotoProfile() {

         mDatabaseUserRef.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 mListUser.clear();
                 for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                     if (dataSnapshot.exists()){
                         User user = postSnapshot.getValue(User.class);
                         if (user.getId().equals(id)) {
                             CurrentUser = user;
                         }
                         Bundle bundle = new Bundle();
                         bundle.putParcelable("EXTRA_CURRENT_USER", user);

                         ProfileFragment profileFragment = new ProfileFragment();
                         profileFragment.setArguments(bundle);

                         getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, profileFragment)
                                 .commit();
                     }
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {
                 Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
             }
         });
    }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
         //super.onActivityResult(requestCode, resultCode, data);
         Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
         fragment.onActivityResult(requestCode, resultCode, data);
     }
 }
