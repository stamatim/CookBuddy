package com.example.jackc.demo2_frontend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.jackc.demo2_frontend.app.AppController;
import com.example.jackc.demo2_frontend.net_utils.Const;

public class ReceiveRecipes extends AppCompatActivity {

    private String TAG = ReceiveRecipes.class.getSimpleName();
    private Button btnGetRecipe, btnAddRecipe;
    private TextView receivedIngredients, receivedTags;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_recipes);

        btnGetRecipe = (Button) findViewById(R.id.buttonGetRecipe);
        btnAddRecipe = (Button) findViewById(R.id.buttonAddNewRecipe);
        receivedIngredients = (TextView) findViewById(R.id.receivedIngredients);
        receivedTags = (TextView) findViewById(R.id.receivedTags);

        btnAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ReceiveRecipes.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnGetRecipe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                makeStringRequest();
            }
        });


    }

    private void makeStringRequest(){
        StringRequest strReq = new StringRequest(Request.Method.GET, "http://proj309-sb-02.misc.iastate.edu:8080/recipes//all", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                receivedIngredients.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });

        AppController.getInstance().addToRequestQueue(strReq);
    }
}

