package com.shail.robotiumtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;


public class RoboCheckboxes extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robo_check_boxes);

        final CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        final CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        final CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        Button submit = (Button) findViewById(R.id.button1);

        //spinner handler
        final Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.mnth_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        submit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if ((checkBox1.isChecked()) && (checkBox2.isChecked()) && (checkBox3.isChecked())) {
                    Toast.makeText(getApplicationContext(), R.string.android_windows_iphone_selected, Toast.LENGTH_SHORT).show();
                } else if ((checkBox1.isChecked()) && (checkBox2.isChecked())) {
                    Toast.makeText(getApplicationContext(), R.string.android_windows_selected, Toast.LENGTH_SHORT).show();
                } else if ((checkBox1.isChecked()) && (checkBox3.isChecked())) {
                    Toast.makeText(getApplicationContext(), R.string.android_iphone_selected, Toast.LENGTH_SHORT).show();
                } else if ((checkBox2.isChecked()) && (checkBox3.isChecked())) {
                    Toast.makeText(getApplicationContext(), R.string.windows_iphone_selected, Toast.LENGTH_SHORT).show();
                } else if ((checkBox1.isChecked()) && (!checkBox2.isChecked()) && (!checkBox3.isChecked())) {
                    Toast.makeText(getApplicationContext(), R.string.android_os_selected, Toast.LENGTH_SHORT).show();
                } else if ((checkBox2.isChecked()) && (!checkBox1.isChecked() ) && (!checkBox3.isChecked() )) {
                    Toast.makeText(getApplicationContext(), R.string.windows_os_selected, Toast.LENGTH_SHORT).show();
                } else if ((checkBox3.isChecked()) && (!checkBox1.isChecked() ) && (!checkBox2.isChecked() )) {
                    Toast.makeText(getApplicationContext(), R.string.iphone_os_selected, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.none_os_selected, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
