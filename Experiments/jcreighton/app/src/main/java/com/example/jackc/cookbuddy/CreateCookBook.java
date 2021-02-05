package com.example.jackc.cookbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateCookBook extends AppCompatActivity {
    Button b2, bDone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_cook_book);

        b2 = (Button) findViewById(R.id.buttonAddRecipe);
        bDone = (Button) findViewById(R.id.buttonDoneCB);

        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateCookBook.this, AddRecipe.class);
                startActivity(i);
            }
        });

        bDone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateCookBook.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}
