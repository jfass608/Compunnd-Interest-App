package com.calc.app.compoundcalc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText initialBalance;
    private EditText interestRate;
    private EditText timeAmt;
    private EditText cmpAmt;
    private TextView finalAmt;
    private EditText monthAmt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialBalance = (EditText) findViewById(R.id.initialBalance);
        interestRate = (EditText) findViewById(R.id.interestRate);
        timeAmt = (EditText) findViewById(R.id.timeAmt);
        cmpAmt = (EditText) findViewById(R.id.cmpAmt);
        finalAmt = (TextView) findViewById(R.id.finalAmt);
        monthAmt = (EditText) findViewById(R.id.monthAmt);

        initialBalance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcFinalAmount();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        interestRate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcFinalAmount();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        timeAmt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcFinalAmount();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

         cmpAmt.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcFinalAmount();
             }

             @Override
             public void afterTextChanged(Editable s) {

             }
         });

        monthAmt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                return;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcFinalAmount();
            }

            @Override
            public void afterTextChanged(Editable s) {
                return;
            }
        });
    }

    private void calcFinalAmount() {
        double start = 0;
        if (!initialBalance.getText().toString().equals("")) {
            start = Double.parseDouble(initialBalance.getText().toString());
        }

        double rate = 0;
        if (!interestRate.getText().toString().equals("")) {
            rate = Double.parseDouble(interestRate.getText().toString());
            rate = rate / 100;
        }

        if (rate == 0) {
            interestRate.setError("You must enter an interest rate.");
            return;
        }


        int time = 0;
        if (!timeAmt.getText().toString().equals("")) {
            time = Integer.parseInt(timeAmt.getText().toString());
        }

        int cmp = 0;
        if (!cmpAmt.getText().toString().equals("")) {
            cmp = Integer.parseInt(cmpAmt.getText().toString());
        }

        if (cmp==0) {
            cmpAmt.setError("You must enter a compounding value.");
            finalAmt.setText("In " + time + " years you will have $0.00.");
            return;
        }

        double finalCalc = 1 + (rate / cmp);
        finalCalc = Math.pow(finalCalc,cmp*time);
        finalCalc *= start;
        double monthContr = calcFutureAmt(rate, time, cmp);
        finalCalc += monthContr;
        DecimalFormat df = new DecimalFormat("#.##");
        String finalC = "";
        finalC = df.format(finalCalc);
        finalAmt.setText("In " + time +  " years you will have $" + finalC + ".");
    }

    private double calcFutureAmt(double rate, int time, int cmp) {
        double monthContr = 0;
        if (!monthAmt.getText().toString().equals("")) {
            monthContr = Double.parseDouble(monthAmt.getText().toString());
        }

        if (monthContr == 0) {
            return 0;
        }

        double finalCalc = 1 + (rate / cmp);
        finalCalc = Math.pow(finalCalc,cmp*time);
        finalCalc = finalCalc-1;
        finalCalc = finalCalc / (rate/cmp);
        finalCalc = finalCalc * (1 + rate/cmp);
        finalCalc*=monthContr;
        return finalCalc;
    }
}
