package com.dat153.andrew.mnamequizeapp.utils;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.dat153.andrew.mnamequizeapp.R;
import com.dat153.andrew.mnamequizeapp.activities.MainActivity;
import com.dat153.andrew.mnamequizeapp.activities.WhoIsWhoActivity;

public class OwnerDialog extends AppCompatDialogFragment {

    private EditText editText_ownerName;



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
                .setTitle("Add Owner")
                .setMessage("This is a dialog")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        // close dialog

                    }
                });


        //
        editText_ownerName = view.findViewById(R.id.editText_ownerName);


        // return the builded Dialog
        return builder.create();

    }




}
