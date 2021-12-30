package com.example.chat_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView userRecyclerView ;
    private ArrayList<User> userList ;
    private UserAdapter adapter ;
    private FirebaseAuth mAuth ;
    private DatabaseReference mDbRef ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mDbRef = FirebaseDatabase.getInstance().getReference();

        userList = new ArrayList();
        adapter = new UserAdapter(this,userList);

     /*   userList adapter = new userList(this, R.layout.user_layout, List<User>);
        userList .setAdapter(adapter); */

        userRecyclerView = findViewById(R.id.userRecyclerView);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        userRecyclerView.setAdapter(adapter);

        mDbRef.child("user").addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                userList.clear();
                for(DataSnapshot postSnapshot: snapshot.getChildren()){

                  //  private currentUser postSnapshot.getValue(User.class) ;
                    final User currentUser = postSnapshot.getValue(User.class);

                    if(mAuth.getCurrentUser().getUid() != currentUser.getUid())
                    userList.add(currentUser);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        })

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.logout){
            mAuth.signOut();
            Intent i = new Intent(getApplicationContext(),Login.class);
            finish();
            startActivity(i);
            return true;
        }
        return true;
    }

}