package com.example.jackc.demo2_frontend;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.jackc.demo2_frontend.app.AppController;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // Variables for the two buttons 'Submit' and 'View Recipes'
    Button btnSubmit, btnViewRecipes;

    // Values for the name, ingredients, and tags that are entered by the user
    EditText rName, rIngredients, rTags;

    // The url for the server
    static final String SERVER_URL = "http://proj309-sb-02.misc.iastate.edu:3306/recipes/";

    // Alert Dialog
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize variables
        btnSubmit = (Button) findViewById(R.id.buttonSubmitRecipe);
        btnViewRecipes = (Button) findViewById(R.id.buttonViewRecipes);
        rName = (EditText) findViewById(R.id.rName);
        rIngredients = (EditText) findViewById(R.id.rIngredients);
        rTags = (EditText) findViewById(R.id.rTags);
        builder = new AlertDialog.Builder(MainActivity.this);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String recipeName, recipeIngredients, recipeTags;
                    recipeName = rName.getText().toString();
                    recipeIngredients = rIngredients.getText().toString();
                    recipeTags = rTags.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.GET, SERVER_URL+"add?recipeName="+recipeName,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                builder.setTitle("Server Response");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        rName.setText("");
                                        rIngredients.setText("");
                                        rTags.setText("");
                                    }
                                });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }
                        },

                        new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MainActivity.this, "Error:", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                        rTags.setText(error.toString());
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("recipeName", recipeName);
                        params.put("recipeIngredients", recipeIngredients);
                        //params.put("recipeTags", recipeTags);
                        return params;
                    }
                };
                AppController.getInstance().addToRequestQueue(stringRequest);
            }
        });

        btnViewRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ReceiveRecipes.class);
                startActivity(i);
            }
        });
    }



}
