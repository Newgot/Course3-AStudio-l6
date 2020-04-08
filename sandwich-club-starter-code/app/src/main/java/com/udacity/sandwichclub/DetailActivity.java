package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    TextView origin_tv;
    TextView ingredients_tv;
    TextView description_tv;
    TextView also_known_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        origin_tv = findViewById(R.id.origin_tv);
        ingredients_tv  = findViewById(R.id.ingredients_tv);
        description_tv = findViewById(R.id.description_tv);
        also_known_tv = findViewById(R.id.also_known_tv);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Gson gson = new Gson();
        Sandwich sandwich = gson.fromJson(json, Sandwich.class);

        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
        origin_tv.setText(sandwich.getPlaceOfOrigin());

        //Создание списка ингридиентов
        String temp = "";
        for (String elem: sandwich.getIngredients()) {
            temp = temp + elem + "\n";
        }
        ingredients_tv.setText(temp);
        description_tv.setText(sandwich.getDescription());

        //Что-то там с алко кнов ас
        temp = "";
        if (sandwich.getAlsoKnownAs() != null){
            for (String elem: sandwich.getAlsoKnownAs()) {
                temp = temp + elem + "\n";
            }
        }

        also_known_tv.setText(temp);


    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

}
