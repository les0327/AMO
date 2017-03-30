package com.example.vova.amo.lab5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.vova.amo.R;

import java.util.Arrays;
import java.util.Locale;

public class SystemActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextMatrix;
    private EditText editTextFree;

    private TextView textViewResult;
    private TextView textViewIteration;

    private Button solveButton;

    private Solver solver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);

        editTextMatrix = (EditText) findViewById(R.id.editText_matrix);
        editTextFree   = (EditText) findViewById(R.id.editText_free);

        textViewResult    = (TextView) findViewById(R.id.textView_system_result);
        textViewIteration = (TextView) findViewById(R.id.textView_system_iteration);

        solveButton = (Button) findViewById(R.id.button_solve);

        solver = new Solver();

        solveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.button_solve:
                String[] buf = editTextMatrix.getText().toString().trim().split("\n");
                String[][] matrix = new String[buf.length][0];

                for (int i = 0; i < buf.length; i++)
                    matrix[i] = buf[i].split(" ");

                double[][] A = new double[matrix.length][matrix[0].length];

                for (int i = 0; i < A.length; i++)
                    for (int j = 0; j < A[i].length; j++)
                        A[i][j] = Double.parseDouble(matrix[i][j]);

                buf = editTextFree.getText().toString().trim().split(" ");

                double[][] B = new double[buf.length][1];

                for (int i = 0 ; i < B.length; i++)
                    B[i][0] = Double.parseDouble(buf[i]);

                double[][] At = solver.transpose(A);
                A = solver.multiply(At, A);
                B = solver.multiply(At, B);


                double[] x = solver.solve(
                        solver.getAlfa(A),
                        solver.getBeta(B, A),
                        0.0001
                        );

                textViewIteration.setText(String.format(Locale.UK, "Iterations: %d", solver.getIterations()));
                textViewResult.setText(String.format(Locale.UK, "Result: %s", Arrays.toString(x)));

                matrix = null;
                buf    = null;
                At = null;
                A = null;
                B = null;
                x = null;
                System.gc();

                break;

        }
    }
}
