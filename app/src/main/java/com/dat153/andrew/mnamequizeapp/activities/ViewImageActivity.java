package com.dat153.andrew.mnamequizeapp.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dat153.andrew.mnamequizeapp.adapters.ImageAdapter;
import com.dat153.andrew.mnamequizeapp.R;
import com.dat153.andrew.mnamequizeapp.utils.Upload;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ViewImageActivity extends AppCompatActivity implements ImageAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    private ProgressBar progressBar;

    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;


    private List<Upload> mUploads;

    private TextView textViewTest;
    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);


        /** Add toolbar to all layouts **/
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        progressBar=findViewById(R.id.progress_circular);
        mRecyclerView=findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUploads=new ArrayList<>();

        mAdapter=new ImageAdapter(ViewImageActivity.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(ViewImageActivity.this);

        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("uploads"); // Firebase image root name;
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {

            /**
             *
             * @param dataSnapshot
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUploads.clear();

                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){

                    Upload upload=postSnapshot.getValue(Upload.class);
                    upload.setImgKey(postSnapshot.getKey());
                    mUploads.add(upload);
                }

                mAdapter.notifyDataSetChanged();
//
//                mAdapter=new ImageAdapter(ViewImageActivity.this, mUploads);
//                mRecyclerView.setAdapter(mAdapter);
//
//                mAdapter.setOnItemClickListener(ViewImageActivity.this);
                progressBar.setVisibility(View.INVISIBLE);
            }


            /**
             *
             * @param databaseError
             */
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ViewImageActivity.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });








    }


    @Override
    public void onItemClick(int position) {

        Toast.makeText(this,  "Normal click at position" + position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onWhatEverClick(int position) {

        Toast.makeText(this,  "Whatever click at position" + position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDeleteClick(int position) {

        Upload selectedItem = mUploads.get(position);
        final String selectedKey =  selectedItem.getImgKey();
        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getImgUrl());
        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                mDatabaseRef.child(selectedKey).removeValue();
                Toast.makeText(ViewImageActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }




}
