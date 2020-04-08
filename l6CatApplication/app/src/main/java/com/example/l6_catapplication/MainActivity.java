package com.example.l6_catapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity{

    TextView textView;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        view = findViewById(R.id.view);

        Cat momo = new Cat("Momo",8, Color.GRAY);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String jsonText = gson.toJson(momo);

        Cat clone = gson.fromJson(jsonText, Cat.class);

        String tv = "name: " + clone.name + ", age: " + clone.age;

        textView.setText(tv);

        view.setBackgroundColor(clone.color);
    }
}
