package com.snigelentertainments.snigelgroup.archase.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;

import com.snigelentertainments.snigelgroup.archase.R;

/**
 * Created by Robin on 15.05.2016.
 */
public class RulesDialogFragmentPlane extends DialogFragment{

    public Dialog onCreateDialog(Bundle savedInstance){
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Archenemy Rules");


        StringBuffer completeMessage = new StringBuffer("");
        completeMessage.append("Description:\n");
        completeMessage.append(Html.fromHtml(getResources().getString(R.string.planechase_description)));
        completeMessage.append("\n\nLeaving the game:\n");
        completeMessage.append(Html.fromHtml(getResources().getString(R.string.planechase_rules_leaving_the_game)));
        completeMessage.append("\n\nRules:\n");
        completeMessage.append(Html.fromHtml(getResources().getString(R.string.planechase_rules)));
        //completeMessage.append(s);

        builder.setMessage(completeMessage.toString());
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        // Create the AlertDialog object and return it
        return builder.create();

    }
}
