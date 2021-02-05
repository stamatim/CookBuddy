package com.example.primaryfolder.cookbuddy.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.primaryfolder.cookbuddy.R;
import com.example.primaryfolder.cookbuddy.app.AppController;
import com.example.primaryfolder.cookbuddy.other.Recipe;
import com.example.primaryfolder.cookbuddy.other.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecipeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecipeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters
    public SessionManager uSession;
    private Recipe r;
    private TextView title;
    private TextView ingred;
    private TextView desc;
    private Button btnAddToSL;
    private String ingList;
    private String url = "http://proj309-sb-02.misc.iastate.edu:8080/recipesIng/";
    private String TAG = RecipeFragment.class.getSimpleName();




    public RecipeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param r A recipe.
     * @return A new instance of fragment RecipeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecipeFragment newInstance(Recipe res) {
        RecipeFragment fragment = new RecipeFragment();
        fragment.setR(res);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_recipe, container, false);
    }

    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        title = view.findViewById(R.id.recipeTitle);
        title.setText(r.getRecipeName());
        btnAddToSL = view.findViewById(R.id.buttonAddToSL);

        ingList = "";
        JsonArrayRequest jsonReq = new JsonArrayRequest(Request.Method.GET, url+r.getRecipeId()+"/all", null,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response){
                        VolleyLog.d(TAG, response.toString());

                        try{

                            for(int i = 0; i < response.length(); i++){
                                //get current Json object
                                JSONObject ing = response.getJSONObject(i);
                                ingList = ingList + ing.getString("ingredientName")+"\n";

                            }
                            buildIng(view, ingList);

                        }catch (JSONException e){
                            e.printStackTrace();
                            buildIng(view, response.toString());
                        }
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(jsonReq);


        desc = view.findViewById(R.id.recipeDesc);
        desc.append("\n" + r.getInstructions());



        btnAddToSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create a user session and checked that they're logged in
                uSession = new SessionManager(getContext());
                uSession.checkLogin();

                //get the current shopping list, add to it, then resave it
                HashMap<String, String> user = uSession.getUserDetails(); // get user data from session
                String shoppingList = user.get(SessionManager.KEY_SHOPPING_LIST);
                shoppingList += "\n" + ingList;
                uSession.saveUserShoppingList(shoppingList);
            }
        });
    }

    private void buildIng (View view, String s) {
        ingred = view.findViewById(R.id.recipeIngredients);
        if (s.length() != 0) {
            ingred.append("\n" + s);
        }
    }


    public void setR(Recipe res) { r = res; }




}
