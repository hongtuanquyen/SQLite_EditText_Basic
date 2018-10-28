package com.firstapp.quyen.testapplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtViewA;
    EditText edtTextA;
    Button btnConfirm;
    EditTextHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtViewA = findViewById(R.id.textView1);
        edtTextA = findViewById(R.id.editText1);
        btnConfirm = findViewById(R.id.button_confirm);


        helper = new EditTextHelper(MainActivity.this);
        assignDataBaseToTextVIew();

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtViewA.setText(edtTextA.getText().toString());
                helper.saveEditText(edtTextA.getText().toString());
            }
        });
    }

    public void assignDataBaseToTextVIew(){
        Cursor cursor = helper.getEditTextList();
        if (cursor.moveToFirst()) {
            do {
                String txtViewContent = cursor.getString(1);
                txtViewA.setText(txtViewContent);
            } while (cursor.moveToNext());
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
    }
}

