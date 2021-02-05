package com.example.jackc.cookbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddRecipe extends AppCompatActivity {
    Button bDone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        bDone = (Button) findViewById(R.id.buttonDoneRecipe);

        bDone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddRecipe.this, CreateCookBook.class);
                startActivity(i);
            }
        });
    }


}
