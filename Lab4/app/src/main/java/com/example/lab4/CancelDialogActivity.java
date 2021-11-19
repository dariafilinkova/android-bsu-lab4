package com.example.lab4;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;

public class CancelDialogActivity extends DialogFragment {
    protected ClickListener clickListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage(R.string.areYouSure)
                .setPositiveButton(R.string.yes, ((dialog, id) -> clickListener.click()))
                .setNegativeButton(R.string.no, (dialog, id) -> dismiss());

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        clickListener = (ClickListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        clickListener = null;
    }
}
