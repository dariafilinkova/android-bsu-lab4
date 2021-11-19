package com.example.lab4;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity implements ClickListener{

    private TextInputLayout nameInputLayout;
    private TextInputLayout addressInputLayout;
    private TextInputLayout commentInputLayout;
    private EditText nameEditText;
    private EditText addressEditText;
    private EditText commentEditText;
    private Button editName;
    private Button editAddress;
    private Button editComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.nameInputLayout = (TextInputLayout) this.findViewById(R.id.nameInputLayout);
        this.addressInputLayout = (TextInputLayout) this.findViewById(R.id.addressInputLayout);
        this.commentInputLayout = (TextInputLayout) this.findViewById(R.id.commentInputLayout);
        this.nameEditText = (EditText) this.findViewById(R.id.nameEditText);
        this.addressEditText = (EditText) this.findViewById(R.id.addressEditText);
        this.commentEditText = (EditText) this.findViewById(R.id.commentEditText);

        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Types type = (Types) data.getSerializableExtra("FIELD_TYPE_KEY");
                            String fieldContent = data.getStringExtra("RESULT_KEY");
                            switch (type) {
                                case NAME:
                                    MainActivity.this.nameEditText.setText(fieldContent);
                                    break;
                                case ADDRESS:
                                    MainActivity.this.addressEditText.setText(fieldContent);
                                    break;
                                case COMMENT:
                                    MainActivity.this.commentEditText.setText(fieldContent);
                                    break;
                            }
                        }
                    }
                });

        this.editName = (Button) this.findViewById(R.id.edit_name);
        this.editName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NameActivity.class);
                someActivityResultLauncher.launch(intent);
            }
        });

        this.editAddress = (Button) this.findViewById(R.id.edit_address);
        this.editAddress.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.lab4.AddressActivity.class);
                someActivityResultLauncher.launch(intent);
            }
        });

        this.editComment = (Button) this.findViewById(R.id.edit_comment);
        this.editComment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.lab4.CommentActivity.class);
                someActivityResultLauncher.launch(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_exit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuExit) {
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