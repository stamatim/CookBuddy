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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
import com.example.primaryfolder.cookbuddy.other.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class ShoppingListFragment  extends Fragment {

    private String TAG = ShoppingListFragment.class.getSimpleName();
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Recipe> data;
    public static View.OnClickListener myOnClickListener;
    private String url = "http://proj309-sb-02.misc.iastate.edu:8080/recipes/all";
    public SessionManager uSession;

    private Button btnGetSL, btnSaveSL, btnResetSL;
    private EditText slTextView;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(Const.TAG_SHOPPING_LIST);


    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {

        // Session class instance
        uSession = new SessionManager(getContext());
        super.onCreate(savedInstanceState);

        slTextView = (EditText) view.findViewById(R.id.editTextSL);
        btnGetSL = (Button) view.findViewById(R.id.buttonGetSL);
        btnSaveSL = (Button) view.findViewById(R.id.buttonSaveSL);
        btnResetSL = (Button) view.findViewById(R.id.buttonResetSL);

        uSession.checkLogin(); // check to make sure user is logged in
        final HashMap<String, String> user = uSession.getUserDetails(); // get user data from session

        btnGetSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shoppingList = user.get(SessionManager.KEY_SHOPPING_LIST);
                slTextView.setText(shoppingList);
            }
        });

        btnSaveSL.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String shoppingList = slTextView.getText().toString();
                uSession.saveUserShoppingList(shoppingList);
            }
        });

        btnResetSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uSession.saveUserShoppingList("");
                slTextView.setText("");
            }
        });



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_list, container, false);

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