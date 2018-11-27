package com.rawks.jeremy.electriccarfinancing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{
    int intYears;      // Loan duration in years entered by user
    int intLoan;       // Loan amount entered by user
    float fltInterest; // Interest rate entered by user

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get object representations of the activity widgets
        final EditText txtYears = findViewById(R.id.txtYears);
        final EditText txtLoan = findViewById(R.id.txtLoan);
        final EditText txtInterest = findViewById(R.id.txtInterest);
        final Button btnPayment = findViewById(R.id.btnPayment);

        final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        // Create an OnClick even handler for the button
        btnPayment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean blnInputError = false;  // Whether user input has errors

                // Get the interest rate input by the user
                String strInterest = txtInterest.getText().toString();
                if (strInterest.isEmpty())
                {
                    blnInputError = true;
                    txtInterest.setError(getString(R.string.txtInputError));
                }
                else
                {
                    fltInterest = Float.parseFloat(strInterest);
                }

                // Get the loan amount input by the user
                String strLoan = txtLoan.getText().toString();
                if (strLoan.isEmpty())
                {
                    blnInputError = true;
                    txtLoan.setError(getString(R.string.txtInputError));
                }
                else
                {
                    intLoan = Integer.parseInt(strLoan);
                }

                // Get the number of years input by the user
                String strYears = txtYears.getText().toString();
                if (strYears.isEmpty())
                {
                    blnInputError = true;
                    txtYears.setError(getString(R.string.txtInputError));
                }
                else
                {
                    intYears = Integer.parseInt(strYears);
                }

                if (!blnInputError)
                {
                    // Save all user input values to shared preferences
                    SharedPreferences.Editor spEditor = sp.edit();
                    spEditor.putInt("years", intYears);
                    spEditor.putInt("loan", intLoan);
                    spEditor.putFloat("interest", fltInterest);
                    spEditor.commit();

                    // Launch the payment activity
                    startActivity(new Intent(MainActivity.this, Payment.class));
                }
            }
        });
    }
}