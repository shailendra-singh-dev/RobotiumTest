package com.shail.robotiumtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputNunberOne;
    private EditText inputNunberSecond;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        inputNunberOne = (EditText) findViewById(R.id.input_number_1);
        inputNunberSecond = (EditText) findViewById(R.id.input_number_2);

        final Button addNumbers = (Button) findViewById(R.id.add_numbers);
        addNumbers.setOnClickListener(this);

        final Button subtractNumbers = (Button) findViewById(R.id.subtract_numbers);
        subtractNumbers.setOnClickListener(this);

        final Button multiplyNumbers = (Button) findViewById(R.id.multiply_numbers);
        multiplyNumbers.setOnClickListener(this);

        final Button devideNumbers = (Button) findViewById(R.id.devide_numbers);
        devideNumbers.setOnClickListener(this);

        mResultTextView = (TextView) findViewById(R.id.result);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        String number1Str = inputNunberOne.getText().toString();
        String number2Str = inputNunberSecond.getText().toString();

        int number1 = Integer.parseInt(number1Str);
        int number2 = Integer.parseInt(number2Str);

        int result = 0;

        switch (v.getId()) {
            case R.id.add_numbers:
                result = number1 + number2;
                break;

            case R.id.subtract_numbers:
                result = number1 - number2;
                break;

            case R.id.multiply_numbers:
                result = number1 * number2;
                break;

            case R.id.devide_numbers:
                result = number1 / number2;
                break;
        }

        mResultTextView.setText(String.valueOf(result));
    }
}
