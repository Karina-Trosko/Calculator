package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText expression; //TODO need use private access for members
    Button getResultButton;
    TextView resultText;
Button cleanButton; //TODO Ctrl+Alt+l reformat code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultText = (TextView) findViewById(R.id.resultText); //TODO Studio warning that redundant code need remove
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
                    resultText.setText("Empty string"); //TODO All hardcoded strings need extract to string resources
                } else {
                    if (Validation.IsValid(expression.getText().toString())) { //TODO expression.getText().toString() repeated three times, need extract to local variable Ctrl+alt+v

                        double result = Calculation.CalculateExpression(expression.getText().toString());

                        if(result!=Double.NaN)resultText.setText(String.valueOf(result));
                        else resultText.setText("Wrong input"); //TODO not good idea use if else block without { }
                    } else resultText.setText("Wrong input");
                }
                break;
            case R.id.cleanButton:
                expression.setText("");
                break;
        }
    }
}
