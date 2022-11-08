package com.example.week3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**@author Yujin Xiong
 * @version 1.0
 * @since October 31, 2022
 */


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.Nullable;


import com.example.week3.databinding.ActivityMainBinding;

public class MainActivity extends Activity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate( getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginButton.setOnClickListener( click -> {
            Intent nextPage = new Intent( MainActivity.this, SecondActivity.class );

            nextPage.putExtra("EmailAddress", binding.editTextEmailAddress.getText().toString());

            startActivity(nextPage);
        });

    }
}