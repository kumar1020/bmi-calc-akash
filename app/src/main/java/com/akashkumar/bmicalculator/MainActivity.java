package com.akashkumar.bmicalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText weightEditText;
    EditText heightEditText;
    Spinner heightSpinner;
    Spinner weightSpinner;
    String heightSpinnerText = "";
    String weightSpinnerText = "";
    TextView resultsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculateClickHandler(View view) {

        if (view.getId() == R.id.calculateButton) {

            weightEditText = (EditText) findViewById(R.id.weightInput);
            heightEditText = (EditText) findViewById(R.id.heightInput);
            weightSpinner = (Spinner) findViewById(R.id.weightSpinner);
            heightSpinner = (Spinner) findViewById(R.id.heightSpinner);
            heightSpinnerText = heightSpinner.getSelectedItem().toString();
            weightSpinnerText = weightSpinner.getSelectedItem().toString();
            resultsTextView = (TextView) findViewById(R.id.resultsTextView);
            double weight = 0;
            double height = 0;

            if (!(weightEditText.getText().toString().equals(""))) {
                weight = Double.parseDouble(weightEditText.getText().toString());
            }

            if (!(heightEditText.getText().toString().equals(""))) {
                height = Double.parseDouble(heightEditText.getText().toString());
            }

            double bmi;

            // calculate bmi value - pounds and inch
            if (weightSpinnerText.equals("Pounds") && heightSpinnerText.equals("Inches")) {
                bmi = calculateBMI(weight, height);
            } else if (weightSpinnerText.equals("Kilograms") &&
                    heightSpinnerText.equals("Inches")) {
                weight = weight * 2.205;
                bmi = calculateBMI(weight, height);
            } else if (weightSpinnerText.equals("Pounds") && heightSpinnerText.equals("Centimeters")) {
                height = height / 2.54;
                bmi = calculateBMI(weight, height);
            } else {
                weight = weight * 2.205;
                height = height / 2.54;
                bmi = calculateBMI(weight, height);
            }

            // round to 2 digits
            double newBMI = Math.round(bmi * 100.0) / 100.0;
            DecimalFormat f = new DecimalFormat("##.00");

            // interpret the meaning of the bmi value
            String bmiInterpretation = interpretBMI(bmi);

            // now set the value in the results text
            resultsTextView.setText("BMI Score = " + f.format(newBMI) + "\n" + bmiInterpretation);

        }

    }

    private double calculateBMI (double weight, double height) {
        // convert values to metric
        return (double) (((weight / 2.2046) / (height * 0.0254)) / (height * 0.0254));
    }


    private String interpretBMI(double bmi) {

        if (bmi < 16) {
            return "You are Severely Underweight";
        } else if (bmi < 18.5) {
            return "You are Underweight";
        } else if (bmi < 25) {
            return "You are Normal";
        }else if (bmi < 30) {
            return "You are Overweight";
        }else if (bmi < 40) {
            return "You are Obese";
        }else if (bmi >= 40) {
            return "You are Morbidly Obese";
        }else {
            return "Enter your Details";
        }
    }

}
