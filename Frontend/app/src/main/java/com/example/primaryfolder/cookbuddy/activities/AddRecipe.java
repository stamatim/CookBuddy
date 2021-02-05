package com.example.primaryfolder.cookbuddy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.primaryfolder.cookbuddy.R;
import com.example.primaryfolder.cookbuddy.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddRecipe extends AppCompatActivity {

    String TAG = AddRecipe.class.getSimpleName();

    // Variables for the two buttons 'Submit' and 'View Recipes'
    Button btnSubmit, btnViewRecipes;

    // Values for the name, ingredients, and tags that are entered by the user
    EditText rName, rIngredients, rInstructions;

    // The url for the server
    static final String SERVER_URL = "http://proj309-sb-02.misc.iastate.edu:8080/";
    int userID = MainActivity.userID;
    int recipeID;

    // Alert Dialog
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        // Initialize variables
        btnSubmit = (Button) findViewById(R.id.buttonSubmitRecipe);
        btnViewRecipes = (Button) findViewById(R.id.buttonViewRecipes);
        rName = (EditText) findViewById(R.id.rName);
        rIngredients = (EditText) findViewById(R.id.rIngredients);
        rInstructions = (EditText) findViewById(R.id.rTags);
        builder = new AlertDialog.Builder(AddRecipe.this);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String recipeName, recipeIngredients, recipeInstructions;
                recipeName = rName.getText().toString();
                recipeIngredients = rIngredients.getText().toString();
                recipeInstructions = rInstructions.getText().toString();

                Map<String, String> postParam = new HashMap<String, String>();
                postParam.put("recipeName", recipeName);
                postParam.put("instructions", recipeInstructions);



                JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.POST, SERVER_URL+"recipes/"+userID+"/add",
                        new JSONObject(postParam),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d(TAG, response.toString());
                                try {
                                    setRID(response.getInt("recipeId"),recipeIngredients);
                                    Intent i = new Intent(AddRecipe.this, MainActivity.class);
                                    startActivity(i);
                                } catch (JSONException e){
                                    Log.d(TAG, "ERROR:" + response.toString());
                                }
                            }
                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Toast.makeText(AddRecipe.this, "Error:", Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                                rInstructions.setText(error.toString());
                            }
                        }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        return headers;
                    }
                };
                AppController.getInstance().addToRequestQueue(jsonReq);

                /*HashMap<String, String> i = new HashMap<String, String>();
                i.put("name", recipeIngredients);
                JSONObject postParam2 = new JSONObject(i);
                JSONArray n;
                try {
                    n= new JSONArray(postParam2);
                } catch (Exception e) {
                    n= new JSONArray();
                }
                //postParam.put("instructions", recipeInstructions);
                try{
                    wait(1000);
                } catch (Exception e ){

                }


                JsonArrayRequest jsonReq2 = new JsonArrayRequest(Request.Method.POST, SERVER_URL+"recipesIng/"+recipeID+"/addall",
                        n,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                Log.d(TAG, response.toString());
                                Intent i = new Intent(AddRecipe.this, MainActivity.class);
                                startActivity(i);
                            }
                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Toast.makeText(AddRecipe.this, "Error:", Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                                rInstructions.setText(error.toString());
                            }
                        }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        return headers;
                    }
                };
<<<<<<< HEAD
                AppController.getInstance().addToRequestQueue(jsonReq2);*/


            }
        });

        btnViewRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddRecipe.this, ViewRecipes.class);
                startActivity(i);
            }
        });
    }

    public void setRID (int id, String recipeIngredients) {
        HashMap<String, String> i = new HashMap<String, String>();
        i.put("name", recipeIngredients);
        JSONObject postParam2 = new JSONObject(i);
        ArrayList<JSONObject> ins = new  ArrayList<JSONObject>();
        ins.add(postParam2);
        JSONArray n;
        try {
            n= new JSONArray(ins);
        } catch (Exception e) {
            n= new JSONArray();
        }
        //postParam.put("instructions", recipeInstructions);


        JsonArrayRequest jsonReq2 = new JsonArrayRequest(Request.Method.POST, SERVER_URL+"recipesIng/"+id+"/addall",
                n,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonReq2);}
}
