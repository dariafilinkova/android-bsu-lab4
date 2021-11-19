package com.example.lab4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;


public class NameActivity extends AppCompatActivity implements ClickListener{

    private TextInputLayout nameInputLayout;
    private TextInputLayout surnameInputLayout;
    private EditText nameEditText;
    private EditText surnameEditText;
    private Button save;
    private Button cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_form);

        this.nameInputLayout = (TextInputLayout) this.findViewById(R.id.nameInputLayout);
        this.surnameInputLayout = (TextInputLayout) this.findViewById(R.id.surnameInputLayout);
        this.nameEditText = (EditText) this.findViewById(R.id.nameEditText);
        this.surnameEditText = (EditText) this.findViewById(R.id.surnameEditText);
        this.save = (Button) this.findViewById(R.id.save_button);
        this.cancel = (Button) this.findViewById(R.id.cancel_button);

        this.save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("FIELD_TYPE_KEY", Types.NAME);
                returnIntent.putExtra("RESULT_KEY", NameActivity.this.nameEditText.getText().toString() + " " + NameActivity.this.surnameEditText.getText().toString());
                NameActivity.this.setResult(Activity.RESULT_OK, returnIntent);
                NameActivity.this.finish();
            }
        });

        this.cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                NameActivity.this.setResult(Activity.RESULT_CANCELED);
                NameActivity.this.finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cancel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuCancel) {
            CancelDialogActivity cancelDialog = new CancelDialogActivity();
            cancelDialog.show(getSupportFragmentManager(), "CANCEL_DIALOG_KEY");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void click() {
        finish();
    }
}