package com.example.vova.amo.lab4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.vova.amo.R;

import java.util.Locale;

public class ChordsActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTexta;
    private EditText editTextb;
    private EditText editTextE;

    private TextView textViewIteration;
    private TextView textViewChordsResult;
    private TextView textViewExpected;
    private TextView textViewError;

    private Button ButtonChords;

    private Searcher searcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chords);

        editTexta = (EditText) findViewById(R.id.editText_a_chords);
        editTextb = (EditText) findViewById(R.id.editText_b_chords);
        editTextE = (EditText) findViewById(R.id.editText_E_chords);

        textViewIteration    = (TextView) findViewById(R.id.textView_iteration);
        textViewChordsResult = (TextView) findViewById(R.id.textView_chords_result);
        textViewExpected     = (TextView) findViewById(R.id.textView_root);
        textViewError        = (TextView) findViewById(R.id.textView_root_error);

        ButtonChords = (Button) findViewById(R.id.button_chords);
        searcher     = new Searcher();

        ButtonChords.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_chords:
                double a = Double.parseDouble(editTexta.getText().toString().trim());
                double b = Double.parseDouble(editTextb.getText().toString().trim());
                double E = Double.parseDouble(editTextE.getText().toString().trim());

                double root = searcher.methodChords(a, b , E);
                double expectedRoot;

                double[] roots = Searcher.roots;
                if (b < roots[1])
                    expectedRoot = roots[0];
                else if (roots[1] < b && b < roots[2])
                    expectedRoot = roots[1];
                else
                    expectedRoot = roots[2];


                textViewIteration.setText(String.format(Locale.UK, "Iterations: %d", searcher.getIterations()));
                textViewChordsResult.setText(String.format(Locale.UK, "Chords method result: %f", root));
                textViewExpected.setText(String.format(Locale.UK, "Root: %f", expectedRoot));
                textViewError.setText(String.format(Locale.UK, "Error: %f", Math.abs(expectedRoot - root)));
                break;
        }
    }
}
