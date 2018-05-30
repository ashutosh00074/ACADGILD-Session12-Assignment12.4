package com.tech.gigabyte.intermediatealertdiaglog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by GIGABYTE on 5/30/2017.
 */

public class MyDialog extends DialogFragment {

    TextInputLayout tilname, tilphone, tildob;
    EditText name, phone, dob;
    Button save, cancel;
    DBhelper dBhelper;
    Dialog dialog;
    ArrayList<Eachrow> arrayList;

    @Override

    //Creating a Custom Dialog

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Enter the Details");
        inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_dialog, null);
        init(view);
        builder.setView(view);
        dialog = builder.create();
        dialog.setCancelable(false);
        return dialog;
    }

    public void init(View view) {

        tilname = (TextInputLayout) view.findViewById(R.id.tilname);
        tilphone = (TextInputLayout) view.findViewById(R.id.tilphone);
        tildob = (TextInputLayout) view.findViewById(R.id.tildob);
        name = (EditText) view.findViewById(R.id.edit_name);
        phone = (EditText) view.findViewById(R.id.edit_phone);
        dob = (EditText) view.findViewById(R.id.edit_dob);
        save = (Button) view.findViewById(R.id.button_save);
        cancel = (Button) view.findViewById(R.id.button_cancel);
        dBhelper = new DBhelper(getActivity());
        arrayList = new ArrayList<>();

        //On Click Save Button --> Saving Entered Data

        save.setOnClickListener(new View.OnClickListener() {
            @Override

            //Normal Input Field Validation

            public void onClick(View view) {
                if (name.getText().toString().trim().isEmpty()) {
                    tilname.setError(getString(R.string.err_name));
                    return;
                } else if (phone.getText().toString().trim().isEmpty()) {
                    tilphone.setError(getString(R.string.err_phone));
                    return;
                } else if (dob.getText().toString().trim().isEmpty()) {
                    tildob.setError(getString(R.string.err_dob));
                    return;
                }
                addvalues();
                Toast.makeText(getActivity(), "Data Saved Successfully ", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        //On Click Cancel Button --> Abort by User

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "You Didn't Save Data", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });
    }

    public void addvalues() {

        //Showing Data from saved in database

        String n = name.getText().toString();
        String p = phone.getText().toString();
        String d = dob.getText().toString();

        long check = dBhelper.insertintodb(n, p, d);
        if (check == -1) {
            Toast.makeText(getActivity(), "Data not inserted ! Something went wrong", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), "Inserting into Database", Toast.LENGTH_LONG).show();
        }

    }
}