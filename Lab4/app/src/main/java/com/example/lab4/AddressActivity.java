package com.example.lab4;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class AddressActivity extends AppCompatActivity implements ClickListener {

    private TextInputLayout countryInputLayout;
    private TextInputLayout cityInputLayout;
    private TextInputLayout addressInputLayout;
    private EditText countryEditText;
    private EditText cityEditText;
    private EditText addressEditText;
    private Button save;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_form);

        this.countryInputLayout = (TextInputLayout) this.findViewById(R.id.countryInputLayout);
        this.cityInputLayout = (TextInputLayout) this.findViewById(R.id.cityInputLayout);
        this.addressInputLayout = (TextInputLayout) this.findViewById(R.id.addressInputLayout);
        this.countryEditText = (EditText) this.findViewById(R.id.countryEditText);
        this.cityEditText = (EditText) this.findViewById(R.id.cityEditText);
        this.addressEditText = (EditText) this.findViewById(R.id.addressEditText);
        this.save = (Button) this.findViewById(R.id.save_button);
        this.cancel = (Button) this.findViewById(R.id.cancel_button);

        this.save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("FIELD_TYPE_KEY", Types.ADDRESS);
                returnIntent.putExtra("RESULT_KEY",
                        AddressActivity.this.countryEditText.getText().toString() +
                                ", " + AddressActivity.this.cityEditText.getText().toString()
                                + ", " + AddressActivity.this.addressEditText.getText().toString());
                AddressActivity.this.setResult(Activity.RESULT_OK, returnIntent);
                AddressActivity.this.finish();
            }
        });

        this.cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AddressActivity.this.setResult(Activity.RESULT_CANCELED);
                AddressActivity.this.finish();
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
