package com.shail.robotiumtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        progressBar.setMax(100);
        progressBar.setProgress(30);

        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton1);
        final ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton1);
        final RadioButton radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        final RadioButton radioButton2 = (RadioButton) findViewById(R.id.radioButton2);

        toggleButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton.isChecked()) {
                    Toast.makeText(getApplicationContext(), R.string.toggle_on, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.toggle_off, Toast.LENGTH_SHORT).show();
                }
            }
        });

        imageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.image_btn_selected, Toast.LENGTH_SHORT).show();
                Intent int_checkview = new Intent(MainActivity.this, RoboCheckboxes.class);
                startActivity(int_checkview);
            }
        });

        radioButton1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getApplicationContext(), R.string.android_selected, Toast.LENGTH_SHORT).show();
            }
        });

        radioButton2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getApplicationContext(), R.string.iphone_selected, Toast.LENGTH_SHORT).show();
            }
        });

        // storing string resources into Array
        String[] stringArray = getResources().getStringArray(R.array.products);

        ListView lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, stringArray));

        // listening to single list item on click
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // selected item
                String product = ((TextView) view).getText().toString();
                Intent i = new Intent(getApplicationContext(), SingleListItem.class);
                // sending data to new activity
                i.putExtra("product", product);
                Toast.makeText(getApplicationContext(), product + " selected",
                        Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
    }


}
