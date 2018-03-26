package com.example.varun.mortgage_calculator;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.varun.mortgage_calculator.database.DBHelper;
import com.example.varun.mortgage_calculator.database.DBschema;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.util.ArrayList;
import java.util.List;

public class SaveCalculation extends AppCompatActivity {

    private Spinner propertyType;
    private EditText Street;
    private EditText City;
    private Spinner State;
    private EditText Zip;
    private Button Save;
    private static final String TAG = "SaveCalculation";
    private SQLiteDatabase database;
    private DBHelper dbhelper;

    public String valuePtype;
    public String valueStreet;
    public String valueCity;
    public String valueState;
    public String valueZip;
    public double result = 0.0;
    public String valuePrice;
    public String valueDown;
    public String valueAPR;
    public String valueTerm;




    @Override
    protected void onCreate(final Bundle savedInstanceState) {


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

        Save = (Button) findViewById(R.id.button2);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valuePtype = propertyType.getSelectedItem().toString();
                valueStreet = Street.getText().toString();
                valueCity = City.getText().toString();
                valueState = State.getSelectedItem().toString();
                valueZip = Zip.getText().toString();

                Bundle bundle = getIntent().getExtras();
                valuePrice = bundle.getString("valuePrice");
                valueDown = bundle.getString("valueDown");
                valueAPR = bundle.getString("valueAPR");
                valueTerm = bundle.getString("valueTerm");
                result = bundle.getDouble("result");

                if(valuePtype.equals("") || valueStreet.equals("") || valueCity.equals("") || valueState.equals("") || valueZip.equals("")) {
                    Toast.makeText(SaveCalculation.this, R.string.wrong_input, Toast.LENGTH_SHORT).show();
                }
                else{
                    dbhelper = new DBHelper(getApplicationContext());
                    database = dbhelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(DBschema.PROP_TYPE, valuePtype);
                    values.put(DBschema.ST_ADDRESS, valueStreet);
                    values.put(DBschema.CITY, valueCity);
                    values.put(DBschema.STATE, valueState);
                    values.put(DBschema.ZIPCODE, valueZip);
                    values.put(DBschema.PROP_PRICE, valuePrice);
                    values.put(DBschema.DOWN_PAY, valueDown);
                    values.put(DBschema.RATE, valueAPR);
                    values.put(DBschema.TERMS, valueTerm);
                    values.put(DBschema.RES, result);
                    long status = database.insert(DBschema.TABLE_NAME, null, values);
                    if(status == -1) {
                        Toast.makeText(SaveCalculation.this, "Data not Entered", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(SaveCalculation.this, "Data Entered", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });



    }
}
