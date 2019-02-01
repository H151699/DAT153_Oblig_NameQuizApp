package com.dat153.andrew.mnamequizeapp.utils;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dat153.andrew.mnamequizeapp.R;
import com.dat153.andrew.mnamequizeapp.activities.MainActivity;
import com.dat153.andrew.mnamequizeapp.activities.WhoIsWhoActivity;

public class OwnerDialog extends AppCompatDialogFragment {

    private EditText editText_ownerName;
    private OwnerDialogListener ownerDialogListener;




    // create a dialog , tip ctr + O

    /**
     *
     * @param savedInstanceState
     * @return
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
       // return super.onCreateDialog(savedInstanceState);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); //  pass context with : getActivity() : context of the activity

        // layout
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // build viwer
        View view = inflater.inflate(R.layout.add_owner_dialog, null); // rootView pass null

        builder.setView(view)
                .setTitle("Please add owner name")
                //.setMessage("This is a dialog")
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {

                    private SharedPreferences mPreferences;
                    private SharedPreferences.Editor sharePrefEditor;

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String ownerName = editText_ownerName.getText().toString().trim();
                        //Add sharedpreF
                        mPreferences = getActivity().getApplicationContext().getSharedPreferences("mySharedPref", Context.MODE_PRIVATE);
                        sharePrefEditor = mPreferences.edit();
                        sharePrefEditor.putString("ownerNameKey", ownerName );
                        sharePrefEditor.commit();



                        ownerDialogListener.applyTexts(ownerName); // sendto Activity

                    }
                });


        //
        editText_ownerName = view.findViewById(R.id.editText_ownerName);


        // return the builded Dialog
        return builder.create();

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            ownerDialogListener = (OwnerDialogListener)context; // shotcut for try/catch :  1.select 2,ctr+alt+t
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement OwnerDialogListener");
        }
    }

    //
    public interface OwnerDialogListener{

        void applyTexts(String oname);


    }






}
