package com.snigelentertainments.snigelgroup.archase;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Robin on 15.05.2016.
 */
public class RulesDialogFragment extends DialogFragment{

    public Dialog onCreateDialog(Bundle savedInstance){
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("blablablablablablablbalbabalbalbalbalbalbalbalbalbalblablablablablabalb ablbalbalblabfkndjkfahfj dfhajdfhadhfjakhfdoiaf a fdhfahdfjanfjkdjfhadjfha fhdfihadfjhajfhadfj af")
                .setPositiveButton("", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();

    }
}
