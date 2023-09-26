package com.cosmah.basiccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText txtEntry = (EditText) findViewById(R.id.txtEntry);
        txtEntry.setText("Hello from Java");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            txtEntry.setShowSoftInputOnFocus(false);
        } else {
            txtEntry.setTextIsSelectable(true);
        }


        TextView txtView = (TextView) findViewById(R.id.txtView);
        txtView.setText("I love coding");


        Button myButton = (Button) findViewById(R.id.btnseven);
        myButton.setOnClickListener(v -> {
            String currentText = txtEntry.getText().toString();
            currentText += "7";
            txtEntry.setText(currentText);
        });


    }
}