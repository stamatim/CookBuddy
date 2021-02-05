package com.example.primaryfolder.cookbuddy.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.primaryfolder.cookbuddy.R;
import com.example.primaryfolder.cookbuddy.activities.ViewRecipes;
import com.example.primaryfolder.cookbuddy.app.AppController;
import com.example.primaryfolder.cookbuddy.net_utils.Const;
import com.example.primaryfolder.cookbuddy.other.CustomAdapter;
import com.example.primaryfolder.cookbuddy.other.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class ViewRecipesFragment  extends Fragment {

    private String TAG = ViewRecipes.class.getSimpleName();
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Recipe> data;
    public static View.OnClickListener myOnClickListener;
    private String url = "http://proj309-sb-02.misc.iastate.edu:8080/recipes/all";
    //public SessionManager uSession;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(Const.TAG_VIEW_RECIPES);


    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {

        // Session class instance
        //uSession = new SessionManager(getContext());
        super.onCreate(savedInstanceState);

        data = new ArrayList<Recipe>();
        JsonArrayRequest jsonReq = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response){
                        VolleyLog.d(TAG, response.toString());

                        try{

                            for(int i = 0; i < response.length(); i++){
                                //get current Json object
                                JSONObject recipe = response.getJSONObject(i);
                                data.add(new Recipe(recipe));
                            }
                            populate(view);

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(jsonReq);

        myOnClickListener = new MyOnClickListener(this.getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_recipes, container, false);

    }

    public void populate(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);

    }


    public static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        public MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
        }

    }


}