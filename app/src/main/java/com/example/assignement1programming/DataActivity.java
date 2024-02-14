package com.example.assignement1programming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataActivity extends AppCompatActivity {

    protected ListView ListView1;//
    protected TextView EventA,EventB,EventC,CountA,CountB,CountC,CountD;//

    protected boolean toggle = false;
    protected Toolbar toolbar2;
    protected SharedPreferenceHelpers sharedPreferenceHelpers;// only one SharePreference


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        setup();
        ToolbarSetup();
        sharedPreferenceHelpers= new SharedPreferenceHelpers(DataActivity.this);// MVC structure

    }
    // I'm accessing all the XML id file for this activity for all the editText and Button and toolbar
    private void setup() {
        EventA=(TextView)findViewById(R.id.EventA);
        EventB=(TextView)findViewById(R.id.EventB);
        EventC=(TextView)findViewById(R.id.EventC);
        CountD=(TextView)findViewById(R.id.Count);
        toolbar2 =(Toolbar)findViewById(R.id.toolbar2);
        ListView1 = findViewById(R.id.Listview1);
    }
    // helping in display the return mode for the toolbar when pressed to go back to MainActivity with certain imported Library
    private void ToolbarSetup(){
        setSupportActionBar(toolbar2);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    @Override
    public void onStart(){
        super.onStart();
        String data= sharedPreferenceHelpers.getName1();// getting the name of the counter 1 and displaying it
        String data1=sharedPreferenceHelpers.getName2();
        String data2=sharedPreferenceHelpers.getName3();
        int counter1 = sharedPreferenceHelpers.getNumber1();// getting the number of the counter 1 total pressing
        int counter2 =sharedPreferenceHelpers.getNumber2();
        int counter3 =sharedPreferenceHelpers.getNumber3();
        int total= counter1+counter2+counter3;// adding the number of counter each time to total number
        EventA.setText(data+": "+String.valueOf(counter1));// display the counter1 name and number
        EventB.setText(data1+": "+String.valueOf(counter2));
        EventC.setText(data2+": "+String.valueOf(counter3));
        CountD.setText("Total Counter : "+String.valueOf(total));// adding that total and displaying it
        displayListView();// initalizing the listview to be seen in false toggle
    }

// this is the function for the togglebar for the return pointer
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.togglebar, menu);
        return true;
    }
    // switch the boolean of toggle from flase to true when pressed
    public boolean StateOfToggle(){
        return toggle = !toggle;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int counter1 = sharedPreferenceHelpers.getNumber1();//getting the number of each counter being pressed to display it in a toggled mode
        int counter2 =sharedPreferenceHelpers.getNumber2(); //
        int counter3 =sharedPreferenceHelpers.getNumber3();
    if (item.getItemId() == android.R.id.home) {
            finish();// if the return button pressed it bring back to MainActivity
            return true;
        }
        if (item.getItemId() == R.id.toggle) {

            StateOfToggle();
            if (toggle) {
                EventA.setText("Counter 1"+":"+String.valueOf(counter1) );// if toggle element on top display that
                EventB.setText("Counter 2"+":"+String.valueOf(counter2) );
                EventC.setText("Counter 3"+":"+String.valueOf(counter3) );
                displayListView();// display by Id counter 1= 1, counter 2 =2 counter3=3
            } else {
                onStart();
                displayListView();// display by name when toggle is false
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

// method used to display the listview
    protected void displayListView() {
        String listCounter = sharedPreferenceHelpers.getName5();// getting the name from the sharepreference
        if (listCounter != null ) {
            List<String> loadList = Arrays.asList(listCounter.split(","));// here this part separate each element that listCounter has by the , inserted in the MainActivity
// two part if toggle button is pressed , we need to sort them by ID
            if (toggle) {
                List<String> idOnly = new ArrayList<>();// declaration of a new list for the ID only
                for (int i = 0; i < loadList.size(); i++) {
                    String WholePart = loadList.get(i);
                    String[] IdPartsOnly = WholePart.split("-"); // In mainactivity we wrote the DisplayView with elementName+"-"+Id so here we only add the ID part in the list
                    if (IdPartsOnly.length > 1) { // here we only add the second part of this string of IdPartsString to the idList and this will be displayed for us
                        idOnly.add(IdPartsOnly[1]);// here we display the second part of the array
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, idOnly);
                ListView1.setAdapter(adapter);// use this Adapter method as seen class
            } else {// if not toggled
                List<String> nameOnly = new ArrayList<>();// same thing for this part as the ID part we declare a new list for names list
                for (int i = 0; i < loadList.size(); i++) {
                    String WholePart = loadList.get(i);
                    String[] parts = WholePart.split("-"); // for each part we split the EventName and Id by a "-"
                    if (parts.length > 0) { // we only take the part that is from the beginning of the string array which is the first element of the string at[0]
                        nameOnly.add(parts[0]); // and we add it to the list to be displayed
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nameOnly);
                ListView1.setAdapter(adapter);// display the Adapter to the listview with namesList only
            }
        }
    }


    }

