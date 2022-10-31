package com.example.week3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**@author Yujin Xiong
 * @version 1.0
 * @since October 31, 2022
 */

public class MainActivity extends AppCompatActivity {

    /** This holds the login button reference */

    protected Button lButton;
    /** This holds the editText reference for login */
    protected EditText loginText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lButton = findViewById(R.id.button1);
        loginText = findViewById(R.id.editText);

        TextView tv = findViewById(R.id.theText);

        lButton.setOnClickListener(click ->
                tv.setText("You click the button"));

        stringHas123(loginText.getText().toString());

    }

    /**This function checkes if s has the string "123" somewhere int it
     *
     * @param s Is the string that we are checking for "123"
     * @return Returns true if s has "123", false otherwise
     */
    public boolean stringHas123(String s){
        return s.contains("123");
    }
}