package com.example.vova.amo.lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.*;
import com.example.vova.amo.R;

import java.util.Locale;

public class SorterActivity extends AppCompatActivity implements View.OnClickListener{

    private Button sortButton;
    private EditText sortEditText;

    private RadioButton textRadioButton;
    private RadioButton numRadioButton;

    private TextView timeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorter);

        sortButton   = (Button) findViewById(R.id.sort_button);
        sortEditText = (EditText) findViewById(R.id.sort_editText);

        textRadioButton = (RadioButton) findViewById(R.id.text_radioButton);
        numRadioButton  = (RadioButton) findViewById(R.id.num_radioButton);

        timeTextView    = (TextView) findViewById(R.id.time_textView);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        sortButton.setOnClickListener(this);
        textRadioButton.setOnClickListener(this);
        numRadioButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.sort_button:
                String[] array = sortEditText.getText().toString().trim().split(" ");

                if (textRadioButton.isChecked()){
                    Timer.start();
                    Sorter.binSort(array);
                    timeTextView.setText(String.format(Locale.UK, "%s %d ns", getString(R.string.time), Timer.finish()));
                    sortEditText.setText(arrToString(array));
                    return;
                }

                if (numRadioButton.isChecked()) {
                    Double[] doubleArray = stringArrToDouble(array);
                    Timer.start();
                    Sorter.binSort(doubleArray);
                    timeTextView.setText(String.format(Locale.UK, "Time: %d ns", Timer.finish()));
                    sortEditText.setText(arrToString(doubleArray));
                }
                break;

            case R.id.num_radioButton:
                sortEditText.setInputType(InputType.TYPE_CLASS_PHONE);
                clear();
                break;
            case R.id.text_radioButton:
                sortEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                clear();
                break;
        }
    }

    private static <T> String arrToString(T[] arr){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arr.length; i++)
            stringBuilder.append(arr[i]).append(" ");
        return stringBuilder.toString().trim();
    }

    private Double[] stringArrToDouble(String[] arr){
        Double[] doubleArr = new Double[arr.length];
        for (int i = 0; i < arr.length; i++)
            doubleArr[i] = Double.parseDouble(arr[i]);
        return doubleArr;
    }

    private void clear(){
        sortEditText.setText("");
        timeTextView.setText(getString(R.string.time));
    }

}
