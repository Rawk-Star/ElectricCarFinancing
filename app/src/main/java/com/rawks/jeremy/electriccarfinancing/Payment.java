package com.rawks.jeremy.electriccarfinancing;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Payment extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Get object representations of the activity widgets
        final TextView txtMonthlyPayment = findViewById(R.id.txtMonthlyPayment);
        final ImageView imgYears = findViewById(R.id.imgYears);

        // Retrieve the shared preference values
        final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        int intYears = sp.getInt("years", 0);
        int intLoan = sp.getInt("loan", 0);
        float fltInterest = sp.getFloat("interest", 0);

        // Set the displayed image based on loan duration
        boolean blnYearsValid = true;
        switch (intYears)
        {
            case 3:
                imgYears.setImageResource(R.drawable.three);
                break;
            case 4:
                imgYears.setImageResource(R.drawable.four);
                break;
            case 5:
                imgYears.setImageResource(R.drawable.five);
                break;
            default:
                blnYearsValid = false;
        }

        if (blnYearsValid)
        {
            // Calculate and display the monthly payment amount
            float fltMonthlyPayment = (intLoan * (1 + (fltInterest * intYears))) / (12 * intYears);
            DecimalFormat currency = new DecimalFormat("$###,###.##");
            String strMonthlyPayment = getString(R.string.txtPayment) + " " + currency.format(fltMonthlyPayment);
            txtMonthlyPayment.setText(strMonthlyPayment);
        }
        else
        {
            // Display message indicating that loan duration is invalid.
            txtMonthlyPayment.setText(getString(R.string.txtYearsError));
        }
    }
}