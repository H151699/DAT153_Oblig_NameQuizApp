package com.dat153.andrew.mnamequizeapp.utils;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;

// NOT USED CLASS



public class Validator {



    public boolean checkImgNameExists(String ImgName, DataSnapshot dataSnapshot){
        Log.d("log",  "checkImgNameExists : checking if: " + ImgName + "exists already." );

            Upload uploadImgName = new Upload();

            // loop through firebase nodes
            for(DataSnapshot ds: dataSnapshot.getChildren()){
                Log.d("datasnapshot", "checkImgNameExists : datasnapshot: " + ds);

                uploadImgName.setImgName(ds.getValue(Upload.class).getImgName());

                if(uploadImgName.getImgName().equals(ImgName)){

                    Log.d("checkImgNameExists", "Found A MATCH " + uploadImgName.getImgName());
                    return true;



                }

            }
            return false;




    }


}
