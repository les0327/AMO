package com.example.vova.amo.lab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.vova.amo.R;

import java.util.Locale;

public class InterpolationActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTexta;
    private EditText editTextb;
    private EditText editTextN;
    private EditText editTextn;
    private EditText editTextx;

    private Button interpolationButton;

    private TextView textViewInterpolation;
    private TextView textViewFunction;
    private TextView textViewError;

    private Interpolation interpolation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolation);

        editTexta = (EditText) findViewById(R.id.editText_a);
        editTextb = (EditText) findViewById(R.id.editText_b);
        editTextN = (EditText) findViewById(R.id.editText_N);
        editTextn = (EditText) findViewById(R.id.editText_n);
        editTextx = (EditText) findViewById(R.id.editText_x);

        interpolationButton = (Button) findViewById(R.id.button_interpolation);

        textViewInterpolation = (TextView) findViewById(R.id.textView_interpolation_result);
        textViewFunction      = (TextView) findViewById(R.id.textView_function_result);
        textViewError         = (TextView) findViewById(R.id.textView_error);

        interpolationButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.button_interpolation:
                interpolation = new Interpolation(
                        Float.parseFloat(editTexta.getText().toString().trim()),
                        Float.parseFloat(editTextb.getText().toString().trim()),
                        Integer.parseInt(editTextN.getText().toString().trim()),
                        Integer.parseInt(editTextn.getText().toString().trim())
                );
                double x    = Double.parseDouble(editTextx.getText().toString().trim());
                double val1 = interpolation.interpolate(x);
                double val2 = interpolation.valueAt(x);
                textViewInterpolation.setText(String.format(Locale.UK, "Interpolation: (sin(%.3f))^2 = %.4f.", x, val1));
                textViewFunction.setText(String.format(Locale.UK, "Function: (sin(%.3f))^2 = %.4f.", x, val2));
                textViewError.setText(String.format(Locale.UK, "Error = %f.", Math.abs(val2 - val1)));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, 0, 0, "Clean");
        menu.add(0, 1, 1, "Graph");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case 0:
                editTexta.setText(null);
                editTextb.setText(null);
                editTextN.setText(null);
                editTextn.setText(null);
                editTextx.setText(null);
                textViewInterpolation.setText("Interpolation");
                textViewFunction.setText("Function");
                textViewError.setText("Error");
                break;

            case 1:
                Intent intent = new Intent("Graph");
                intent.putExtra("object", interpolation);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
