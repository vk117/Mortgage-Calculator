package com.example.varun.mortgage_calculator;

import android.support.design.widget.FloatingActionButton;
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
    private TextView heading;
    private TextView show;
    private FloatingActionButton myFAB;

    public String valuePrice;
    public String valueDown;
    public String valueAPR;
    public String valueTerm;
    public double result = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        propertyPrice = (EditText) findViewById(R.id.editText18);
        downPayment = (EditText) findViewById(R.id.editText19);
        APR = (EditText) findViewById(R.id.editText20);
        terms = (Spinner) findViewById(R.id.spinner3);
        heading = (TextView) findViewById(R.id.textView3);
        show = (TextView) findViewById(R.id.textView6);
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
                if(valuePrice.equals("") || valueDown.equals("")|| valueAPR.equals("")|| valueTerm.equals("")) {

                    Toast.makeText(Calculator_Input.this, R.string.wrong_input, Toast.LENGTH_SHORT).show();
                }
                else {
                    result = calculate();
                    heading.setText("Monthly Payment:");
                    show.setText(Double.toString(result));
                    heading.setVisibility(View.VISIBLE);
                    show.setVisibility(View.VISIBLE);
                }
            }
        });

        myFAB = (FloatingActionButton) findViewById(R.id.fab);
        myFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(valuePrice.equals("") || valueDown.equals("")|| valueAPR.equals("")|| valueTerm.equals("")|| result == 0.0){
                    Toast.makeText(Calculator_Input.this, R.string.cannot_save, Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(Calculator_Input.this, SaveCalculation.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("valuePrice", valuePrice);
                    bundle.putString("valueDown", valueDown);
                    bundle.putString("valueAPR", valueAPR);
                    bundle.putString("valueTerm", valueTerm);
                    bundle.putDouble("result", result);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
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
            heading.setText("");
            show.setText("");
            heading.setVisibility(View.INVISIBLE);
            show.setVisibility(View.INVISIBLE);
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
