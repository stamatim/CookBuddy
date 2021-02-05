package com.example.chris.androidtableexperiment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 =(Button) findViewById(R.id.PizzaPageLink);
        b2 =(Button) this.<View>findViewById(R.id.BBQpageLink);
        b3 =(Button) this.<View>findViewById(R.id.SaladLink);

        b1.setOnClickListener(new OnClickListener(){
            @Override
                    public void onClick(View view){
                Intent j = new Intent(MainActivity.this, PizzaActivity.class);
                startActivity(j);
            }
                });
        b2.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(MainActivity.this, BBQActivity.class);
                    startActivity(i);
                }
                });
        b3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(MainActivity.this, SaladActivity.class);
                startActivity(k);
            }
        });

                        }
            }
