package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText expression;
    Button getResultButton;
    TextView resultText;
Button cleanButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultText = (TextView) findViewById(R.id.resultText);
        getResultButton = (Button) findViewById(R.id.getResultButton);
        expression = (EditText) findViewById(R.id.expression);
        cleanButton = (Button) findViewById(R.id.cleanButton);
cleanButton.setOnClickListener(this);
        getResultButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getResultButton:
                if (expression.getText().toString().isEmpty()) {
                    resultText.setText("Empty string");
                } else {
                    if (Validation.IsValid(expression.getText().toString())) {

                        double result = Calculation.CalculateExpression(expression.getText().toString());

                        if(result!=Double.NaN)resultText.setText(String.valueOf(result));
                        else resultText.setText("Wrong input");
                    } else resultText.setText("Wrong input");
                }
                break;
            case R.id.cleanButton:
                expression.setText("");
                break;
        }
    }
}
