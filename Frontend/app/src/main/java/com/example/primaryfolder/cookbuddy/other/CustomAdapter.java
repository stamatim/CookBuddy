package com.example.primaryfolder.cookbuddy.other;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.primaryfolder.cookbuddy.R;
import com.example.primaryfolder.cookbuddy.fragments.RecipeFragment;
import com.example.primaryfolder.cookbuddy.fragments.ViewRecipesFragment;
import com.example.primaryfolder.cookbuddy.other.Recipe;

import java.util.ArrayList;



public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<Recipe> recipeList;
    private AdapterView.OnItemClickListener listener;
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        private Recipe r;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.recipeName);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){

                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Fragment rFragment = RecipeFragment.newInstance(r);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, rFragment).addToBackStack(null).commit();


                }
            });
        }

    }

    public CustomAdapter(ArrayList<Recipe> data) {
        this.recipeList = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);

        view.setOnClickListener(ViewRecipesFragment.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;

        textViewName.setText(recipeList.get(listPosition).getRecipeName());

        holder.r=recipeList.get(listPosition);

    }


    @Override
    public int getItemCount() {
        return recipeList.size();
    }
}

