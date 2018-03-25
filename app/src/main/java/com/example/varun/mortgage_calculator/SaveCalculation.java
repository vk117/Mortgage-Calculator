package com.example.varun.mortgage_calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SaveCalculation extends AppCompatActivity {

    private Spinner propertyType;
    private EditText Street;
    private EditText City;
    private Spinner State;
    private EditText Zip;
    private Button Save;

    public String valuePtype;
    public String valueStreet;
    public String valueCity;
    public String valueState;
    public String valueZip;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ouput);
        propertyType = (Spinner) findViewById(R.id.spinner);
        Street = (EditText) findViewById(R.id.editText);
        City = (EditText) findViewById(R.id.editText2);
        State = (Spinner) findViewById(R.id.spinner2);
        Zip = (EditText) findViewById(R.id.editText3);

        ArrayAdapter<String> adapter;
        List<String> list;
        list = new ArrayList<String>();
        list.add("House");
        list.add("Townhouse");
        list.add("Condo");
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        propertyType.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.array_state, android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        State.setAdapter(adapter2);



    }
}
