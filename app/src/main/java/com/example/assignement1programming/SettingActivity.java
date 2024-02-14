package com.example.assignement1programming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {
    protected EditText EditA,EditB,EditC,Number;// buttons help me edit first, second, third name
    protected Button Save;
    protected SharedPreferenceHelpers sharedPreferenceHelpers;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setup();// setup the display of the activity
        ToolbarSetup();// setup the toolbar with the return pointer
        sharedPreferenceHelpers= new SharedPreferenceHelpers(SettingActivity.this);/// Initialization my MVC shared preference
    }
    private void setup() {
        // I'm accessing all the XML id file for this activity for all the editText and Button and toolbar
        EditA=findViewById(R.id.editA);
        EditB=findViewById(R.id.editB);
        EditC=findViewById(R.id.editC);
        Number=findViewById(R.id.Number);
        Save=findViewById(R.id.save);
        toolbar = findViewById(R.id.toolbar);

        // making my character input for the event counter name equal to 20 , method describe in the document
        int max = 20;
        InputFilter[] filtersA = new InputFilter[1];
        filtersA[0] = new InputFilter.LengthFilter(max);
        EditA.setFilters(filtersA);// setting this filter to 20 character Counter1
        EditB.setFilters(filtersA);// setting this filter to 20 character Counter1
        EditC.setFilters(filtersA);// setting this filter to 20 character Counter1



        // this is here to check if my field already have names or not if it doesn't have name then it will let the user edit otherwise he need to press the Edit button from the option bar
        if((EditA!= null)|| (EditB != null) || (EditC != null)){
            disableText();// function created to enable the textedit mode and the save button when
        }
        else if ((EditA== null)&&(EditB == null)&&(EditC == null)){
            enableText();// make the edit mode possible
        }
        // onClick listner mode for the save button
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // whenever i click on save it take 4 thing and save it counter1-2-3-total number and save it

                String editA= EditA.getText().toString().trim();
                String editB= EditB.getText().toString().trim();
                String editC= EditC.getText().toString().trim();
                String totalNbr = Number.getText().toString().trim();
                // function to make sure that the inputted number of the totalNbr is not more than 200 and not less than 5
                int totalNbr1 = Integer.parseInt(totalNbr);// changing the string to input from totalNbr
                if (totalNbr1 < 5 || totalNbr1 > 200 ) {
                    Toast.makeText(SettingActivity.this, "IT NEED TO BE BETWEEN 5 AND 200", Toast.LENGTH_LONG).show();
                    return;
                }


                // SharedMVC function of saver
                sharedPreferenceHelpers.saveName1(editA);
                sharedPreferenceHelpers.saveName2(editB);
                sharedPreferenceHelpers.saveName3(editC);
                sharedPreferenceHelpers.saveName4(totalNbr);
                disableText();
                Toast.makeText(SettingActivity.this,"Saved",Toast.LENGTH_LONG).show();
            }

        });
    }
    // Setting Up the toolbar action to helps navigate back to the MainActivity
    private void ToolbarSetup(){
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }
    //the getters of the MVC sharedPReference helper, and setText for it
    @Override
    protected void onStart(){
        super.onStart();
        String data= sharedPreferenceHelpers.getName1();
        String data1=sharedPreferenceHelpers.getName2();
        String data2=sharedPreferenceHelpers.getName3();
        String data3=sharedPreferenceHelpers.getName4();
            EditA.setText(data);
            EditB.setText(data1);
            EditC.setText(data2);
            Number.setText(data3);
        }
    //}

    // include in the edit bar method
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editbar, menu);
        return true;
    }
// help me use the edit function button to make the edit of the counter available
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.edit) {
         /// now  you can edit the text like desired and save it
            enableText();
            Toast.makeText(this, "You can now edit the fields", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (item.getItemId() == android.R.id.home) {
            finish(); // finish activity make the child go back to the parent activity when the return pointer is pressed
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    /// enable disable functions
    private void disableText(){
        EditA.setEnabled(false);
        EditB.setEnabled(false);
        EditC.setEnabled(false);
        Number.setEnabled(false);
        Save.setVisibility(View.GONE);
    }
    private void enableText(){
        EditA.setEnabled(true);
        EditB.setEnabled(true);
        EditC.setEnabled(true);
        Number.setEnabled(true);
        Save.setVisibility(View.VISIBLE);
    }

}