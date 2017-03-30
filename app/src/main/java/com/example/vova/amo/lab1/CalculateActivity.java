package com.example.vova.amo.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.example.vova.amo.R;

import java.util.Locale;

public class CalculateActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private TextView textViewResult;

    private EditText editText1;
    private EditText editText2;
    private EditText editText3;

    private TextView label1;
    private TextView label2;
    private TextView label3;

    private int SELECTED_EXPRESSION = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        Button calculateButton = (Button) findViewById(R.id.calculate_button);
        Button clearButton     = (Button) findViewById(R.id.clear_button);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);

        label1 = (TextView) findViewById(R.id.label_1);
        label2 = (TextView) findViewById(R.id.label_2);
        label3 = (TextView) findViewById(R.id.label_3);

        imageView      = (ImageView) findViewById(R.id.imageView);
        textViewResult = (TextView) findViewById(R.id.textViewResult);

        calculateButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        double result = 0;
        switch (v.getId()) {
            case R.id.calculate_button:
                try {
                    double var1 = Double.parseDouble(editText1.getText().toString());
                    double var2 = 0;
                    double var3 = Double.parseDouble(editText3.getText().toString());
                    switch (SELECTED_EXPRESSION) {

                        case 0:
                            var2 = Double.parseDouble(editText2.getText().toString());
                            result = Calculator.calculateFirstExpression(var1, var2, var3);
                            break;
                        case 1:
                            var2 = Double.parseDouble(editText2.getText().toString());
                            result = Calculator.calculateSecondExpression((int) var1, var2, var3);
                            break;
                        case 2:
                            double[] arr = parseC(editText2.getText().toString().trim());
                            if (arr.length != var1){
                                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            result = Calculator.calculateThirdExpression((int) var1, arr, var3);
                    }
                    textViewResult.setText(String.format(Locale.UK, "Result = %g", result));
                } catch (NumberFormatException e){
                    Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
                }
                break;


            case R.id.clear_button:
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                textViewResult.setText("");
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, 0, 0, "1");
        menu.add(0, 1, 1, "2");
        menu.add(0, 2, 2, "3");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (SELECTED_EXPRESSION = item.getItemId()){

            case 0:
                imageView.setImageResource(R.drawable.first);
                label1.setText(R.string.enter_a);
                label2.setText(R.string.enter_b);
                label3.setText(R.string.enter_c);
                break;
            case 1:
                imageView.setImageResource(R.drawable.second);
                label1.setText(R.string.enter_i);
                label2.setText(R.string.enter_m);
                label3.setText(R.string.enter_d);
                break;
            case 2:
                imageView.setImageResource(R.drawable.third);
                label1.setText(R.string.enter_n);
                label2.setText(R.string.enter_c);
                label3.setText(R.string.enter_g);
                break;
        }
        textViewResult.setText("");
        return super.onOptionsItemSelected(item);
    }

    private double[] parseC(String str){
        String[] arr       = str.split(" ");
        double[] doubleArr = new double[arr.length];
        for (int i = 0; i < arr.length; i++)
            doubleArr[i] = Double.parseDouble(arr[i]);
        return doubleArr;
    }
}
