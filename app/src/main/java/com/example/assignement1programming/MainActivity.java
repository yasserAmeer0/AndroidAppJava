package com.example.assignement1programming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    protected  TextView totalNbr1;
    protected Button buttonEventA, buttonEventB,buttonEventC,showNbr,SettingActivity;
    protected int counter1,counter2,counter3,total = 0;
    protected SharedPreferenceHelpers sharedPreferenceHelpers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
        sharedPreferenceHelpers= new SharedPreferenceHelpers(MainActivity.this);
    }

    // here im setting up the button that will be pressed in the main activity when setting activity is pressed goes to the other activty and ....
    private void setup() {
        SettingActivity= (Button)findViewById(R.id.SettingActivity);
        buttonEventA=(Button)findViewById(R.id.buttonEventA);
        buttonEventB=(Button)findViewById(R.id.buttonEventB);
        buttonEventC=(Button)findViewById(R.id.buttonEventC);
        showNbr=(Button)findViewById(R.id.showNbr);
        totalNbr1=(TextView)findViewById(R.id.totalNbr);

// goes to first button which is the SettingActivity
        SettingActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSettingActivity();
            }
        });
// goes to DataActivity when the last button is pressed
        showNbr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              toDataActivity();
            }
        });

   /// the three down button have several function: they all represent counter 1-2-
   /// first we verify if there is any name for the counter if not then when you click it it goes to the SettingActivity
   /// else it will increment the first counter and the total , then save the number of each counter and update it in the
   // DataActivity by name and in the list also with it proper Id

        buttonEventA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = buttonEventA.getText().toString();
                if(text.isEmpty()){
                    toSettingActivity();
                }else{
                counter1++;// increment the first counter
                    IncrementTotal();// Increment the total Nbr and display it
                sharedPreferenceHelpers.saveName1(text);
                sharedPreferenceHelpers.saveNumber1(counter1);// save the number incremented
                displayListView(buttonEventA.getText().toString(), 1);// to be displayed in ListView in DataActivity
                    }// the rest of the two button does the same thing.
            }
        });
        buttonEventB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = buttonEventB.getText().toString();
                if(text.isEmpty()){
                    toSettingActivity();
                }else{
                counter2++;
                IncrementTotal();
                sharedPreferenceHelpers.saveName2(text);
                sharedPreferenceHelpers.saveNumber2(counter2);
                displayListView(buttonEventB.getText().toString(), 2);
                    }
            }
        });

        buttonEventC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = buttonEventC.getText().toString();
                if(text.isEmpty()){
                    toSettingActivity();
                }else{
                counter3++;
                IncrementTotal();
                sharedPreferenceHelpers.saveName3(text);
                sharedPreferenceHelpers.saveNumber3(counter3);
                displayListView(buttonEventC.getText().toString(), 3);

            }}
        });

    }


    // going to the settingActivity when needed
    private void toSettingActivity(){
        Intent intent= new Intent(MainActivity.this,SettingActivity.class);
        startActivity(intent);

    }
    // increment the total number of the 3 counter together
    private void IncrementTotal(){
        total++;
        totalNbr1.setText("TotalNbr: "+String.valueOf(total));
    }


// going to DataActivity when needed
    private void toDataActivity(){
        Intent intent= new Intent(MainActivity.this,DataActivity.class);
        startActivity(intent);

    }
    // here it helps us get the name from the settingActivity and change to the right button name
    @Override
    protected void onStart(){
        super.onStart();

        String data= sharedPreferenceHelpers.getName1();// getting the name of the button to display them
        String data1=sharedPreferenceHelpers.getName2();
        String data2=sharedPreferenceHelpers.getName3();
        // if the button dont have name on them it direct them directly to setup to make the user edit and add name
        if (data==null||data1==null||data2==null){
            setup();
        }
        else{
            // else it just display it text from Setting Activity
            buttonEventA.setText(data);
            buttonEventB.setText(data1);
            buttonEventC.setText(data2);
        }
}
// here we are taking the name of each counter that is being pressed and record them into a historyList for us to display in DataActivity ,
// we are sorting them by EventName and Id using the sharepreference method.
  //
    private void displayListView(String eventName,int Id) {
        String ListCounterHistory = sharedPreferenceHelpers.getName5();// we are getting the name of the button that is touched
        //to avoid getting a null in the first element in the listview we add a if condition so that the first element gets inserted properly
        if(ListCounterHistory==null){
            ListCounterHistory=eventName+"-"+Id;
        }else{
            ListCounterHistory = ListCounterHistory+ "," + eventName+"-"+Id;// adding each element in this list by separating each element with a comma and adding a"-" between name and id
        }
        sharedPreferenceHelpers.saveName5(ListCounterHistory);// saving the name with MVC sharepreferences
    }




}