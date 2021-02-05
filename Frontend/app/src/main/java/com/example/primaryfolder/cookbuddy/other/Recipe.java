package com.example.primaryfolder.cookbuddy.other;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Map;

public class Recipe implements Serializable {
    private String userId;
    private String userName;
    private String recipeId;
    private String recipeName;
    private String instructions;

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getInstructions() {
        return instructions;
    }

    public Recipe(JSONObject r) {
        try {
            userId = (String) r.get("userId");
            userName = (String) r.get("name");
            recipeId = (String) r.get("recipeId");
            recipeName = (String) r.get("recipeName");
            instructions = (String) r.get("instructions");
        }
        catch (JSONException e)
        {
            recipeName = "ERROR";
        }

    }
}