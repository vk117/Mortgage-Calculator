package com.example.varun.mortgage_calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OuputActivity extends AppCompatActivity {

    private TextView output;
    private Button map;
    public double s;
    public String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ouput);
        s = getIntent().getDoubleExtra("result", 0.00);
        result = Double.toString(s);
        output = (TextView) findViewById(R.id.textView5);
        output.setText(result);
        map = (Button) findViewById(R.id.button2);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OuputActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}
