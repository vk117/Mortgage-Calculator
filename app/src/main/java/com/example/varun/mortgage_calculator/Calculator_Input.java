package com.example.varun.mortgage_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;


public class Calculator_Input extends AppCompatActivity {

    private EditText propertyPrice;
    private EditText downPayment;
    private EditText APR;
    private Spinner terms;
    private Button calculate;
    public String valuePrice;
    public String valueDown;
    public String valueAPR;
    public String valueTerm;
    public double result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        propertyPrice = (EditText) findViewById(R.id.editText18);
        downPayment = (EditText) findViewById(R.id.editText19);
        APR = (EditText) findViewById(R.id.editText20);
        terms = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter;
        List<String> list;
        list = new ArrayList<String>();
        list.add("15");
        list.add("30");
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        terms.setAdapter(adapter);

        calculate = (Button) findViewById(R.id.button);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valuePrice = propertyPrice.getText().toString();
                valueDown = downPayment.getText().toString();
                valueAPR = APR.getText().toString();
                valueTerm = terms.getSelectedItem().toString();
                result = calculate();
                Intent intent = new Intent(Calculator_Input.this, OuputActivity.class);
                intent.putExtra("result", result);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_calculation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.new_calculation) {
            propertyPrice.setText("");
            downPayment.setText("");
            APR.setText("");
            terms.setSelection(0);
        }
        return super.onOptionsItemSelected(item);
    }

    public double calculate() {
        double Principle = Double.parseDouble(valuePrice) - Double.parseDouble(valueDown);
        double rate = Double.parseDouble(valueAPR)/1200;
        double n = Double.parseDouble(valueTerm) * 12;
        double Power = Math.pow(1+rate, n);
        double num = Principle * rate * Power;
        double den = Power - 1;
        double result = num/den;
        return result;
    }
}
