package com.example.primaryfolder.cookbuddy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.primaryfolder.cookbuddy.R;
import com.example.primaryfolder.cookbuddy.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ViewRecipes extends AppCompatActivity {

    private String TAG = ViewRecipes.class.getSimpleName();
    private Button btnGetRecipes, btnNewRecipe;
    private TextView mTextView;
    private String url = "http://proj309-sb-02.misc.iastate.edu:8080/recipes/all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipes);

        btnGetRecipes = (Button) findViewById(R.id.buttonGetRecipes);
        btnNewRecipe = (Button) findViewById(R.id.buttonAddRecipe);
        mTextView = (TextView) findViewById(R.id.textViewRecipes);

        btnNewRecipe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(ViewRecipes.this, AddRecipe.class);
                startActivity(i);
            }
        });

        btnGetRecipes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                JsonArrayRequest jsonReq = new JsonArrayRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>(){
                            @Override
                            public void onResponse(JSONArray response){
                                VolleyLog.d(TAG, response.toString());

                                try{

                                    for(int i = 0; i < response.length(); i++){
                                        //get current Json object
                                        JSONObject recipe = response.getJSONObject(i);

                                        String recipeName = recipe.getString("recipeName");
                                        int recipeID = recipe.getInt("recipeId");
                                        String recipeInstructions = recipe.getString("instructions");

                                        mTextView.append("Recipe Name: " + recipeName + " ID: " + recipeID +"\n");
                                        mTextView.append("Instructions: " + recipeInstructions + "\n\n");
                                    }

                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error){
                                VolleyLog.d(TAG, "Error: " + error.getMessage());
                                mTextView.append(error.getMessage());
                            }
                        });
                AppController.getInstance().addToRequestQueue(jsonReq);

            }
        });

    }
}
