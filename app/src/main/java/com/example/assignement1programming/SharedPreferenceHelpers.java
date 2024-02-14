package com.example.assignement1programming;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceHelpers {
/// this class helps us build an MVC structure shared preference so that we have a much cleaner code
    // this was inspired from the assignment document.
    private SharedPreferences sharedPreferences1,sharedPreferences2,sharedPreferences3,sharedPreferences4,sharedPreferences5,shared1,shared2,shared3,shared4;

    public SharedPreferenceHelpers(Context context) {
        sharedPreferences1 = context.getSharedPreferences("Preference1", Context.MODE_PRIVATE);
        sharedPreferences2 = context.getSharedPreferences("Preference2", Context.MODE_PRIVATE);
        sharedPreferences3 = context.getSharedPreferences("Preference3", Context.MODE_PRIVATE);
        sharedPreferences4 = context.getSharedPreferences("Preference4", Context.MODE_PRIVATE);
        sharedPreferences5 = context.getSharedPreferences("Preference5", Context.MODE_PRIVATE);
        shared1 = context.getSharedPreferences("shared1", Context.MODE_PRIVATE);
        shared2 = context.getSharedPreferences("shared2", Context.MODE_PRIVATE);
        shared3 = context.getSharedPreferences("shared3", Context.MODE_PRIVATE);
        shared4 = context.getSharedPreferences("shared4", Context.MODE_PRIVATE);
    }

    // bunch of function to save name and numbers
    public void saveName1(String name)
    {
        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
        editor1.putString("Name1",name );
        editor1.commit();
    }

    public void saveName2(String name)
    {
        SharedPreferences.Editor editor2= sharedPreferences2.edit();
        editor2.putString("Name2",name );
        editor2.commit();
    }
    public void saveName3(String name)
    {
        SharedPreferences.Editor editor3 = sharedPreferences3.edit();
        editor3.putString("Name3",name );
        editor3.commit();

    }
    public void saveName4(String name)
    {
        SharedPreferences.Editor editor4 = sharedPreferences4.edit();
        editor4.putString("Name4",name );
        editor4.commit();

    }
    public void saveName5(String name)
    {
        SharedPreferences.Editor editor5 = sharedPreferences5.edit();
        editor5.putString("Name5",name );
        editor5.commit();

    }
    public void saveNumber1(int number)
    {
        SharedPreferences.Editor editor = shared1.edit();
        editor.putInt("Number1",number );
        editor.commit();
    }
    public void saveNumber2(int number)
    {
        SharedPreferences.Editor editor = shared2.edit();
        editor.putInt("Number2",number );
        editor.commit();
    }
    public void saveNumber3(int number)
    {
        SharedPreferences.Editor editor = shared3.edit();
        editor.putInt("Number3",number );
        editor.commit();
    }
    public void saveNumber4(int number)
    {
        SharedPreferences.Editor editor = shared4.edit();
        editor.putInt("Number4",number );
        editor.commit();
    }

    // getter for the name and the numbers
    public String getName1()
    {
        return sharedPreferences1.getString("Name1", null);
    }
    public String getName2()
    {
        return sharedPreferences2.getString("Name2", null);
    }
    public String getName3()
    {
        return sharedPreferences3.getString("Name3", null);
    }
    public String getName4()
    {
        return sharedPreferences4.getString("Name4", null);
    }
    public String getName5()
    {
        return sharedPreferences5.getString("Name5", null);
    }

    public int getNumber1()
    {
        return shared1.getInt("Number1", 0);
    }
    public int getNumber2()
    {
        return shared2.getInt("Number2", 0);
    }
    public int getNumber3()
    {
        return shared3.getInt("Number3", 0);
    }


}



