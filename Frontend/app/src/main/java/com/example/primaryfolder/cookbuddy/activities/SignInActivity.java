package com.example.primaryfolder.cookbuddy.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.primaryfolder.cookbuddy.R;
import com.example.primaryfolder.cookbuddy.app.AppController;
import com.example.primaryfolder.cookbuddy.other.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignInActivity extends AppCompatActivity {

    private String TAG = SignInActivity.class.getSimpleName();


    // Variables for the entered email and password
    EditText enteredUserEmail, enteredUserPassword;

    // Variable for the sign in button
    Button btnSignIn, btnSignUp;

    // The session manager for user login
    public SessionManager uSession;

    // Server url
    static final String SERVER_URL = "http://proj309-sb-02.misc.iastate.edu:8080//users/sign_in";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btnSignIn = (Button) findViewById(R.id.SignIn);
        btnSignUp = (Button) findViewById(R.id.btnGoToUserRegister);
        enteredUserEmail = (EditText) findViewById(R.id.enteredEmailAddress);
        enteredUserPassword = (EditText) findViewById(R.id.enteredPassword);

        uSession = new SessionManager(getApplicationContext());
        Toast.makeText(getApplicationContext(), "User Login Status: " + uSession.isLoggedIn(), Toast.LENGTH_LONG).show();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignInActivity.this, RegisterUser.class);
                startActivity(i);
            }
        });




        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // The progress dialog
                final ProgressDialog pDialog = new ProgressDialog(SignInActivity.this); // The progress dialog object for this activity
                pDialog.setMessage("Verifying your information, Please Wait...");
                pDialog.show();

                // String for the entered info
                final String enteredEmail, enteredPassword;

                // Check to make sure there is an email entered
                if (enteredUserEmail.getText().toString().equals("")) {
                    Toast.makeText(SignInActivity.this, "Error, Please enter a valid email in the required field", Toast.LENGTH_LONG).show();
                    pDialog.hide();
                }

                if (enteredUserPassword.getText().toString().equals("")) {
                    Toast.makeText(SignInActivity.this, "Error, Please enter a valid password in the required field", Toast.LENGTH_LONG).show();
                    pDialog.hide();
                }

                else {

                    // Initialize string fields
                    enteredEmail = enteredUserEmail.getText().toString();
                    enteredPassword = enteredUserPassword.getText().toString();

                    //these are the parameters used by the back end methods
                    Map<String, String> postParam = new HashMap<String, String>();
                    postParam.put("email", enteredEmail);
                    postParam.put("password", enteredPassword);

                    //Json request using the post method
                    JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.POST, SERVER_URL, new JSONObject(postParam),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.d(TAG, response.toString());
                                    pDialog.hide();
                                    int id = 0;
                                    try {
                                        if (response.getInt("Error") == 0) {
                                            try {
                                                // Create user login session
                                                id = Integer.parseInt((String) response.get("userId"));

                                                uSession.createLoginSession(response.get("name").toString(), enteredEmail, id);

                                                Intent i = new Intent(SignInActivity.this, MainActivity.class);
                                                startActivity(i);
                                            }

                                            catch (JSONException e) {
                                                Toast.makeText(SignInActivity.this, "Error: Something went wrong!"+ id, Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(SignInActivity.this, "Error: User name or password incorrect", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        Toast.makeText(SignInActivity.this, "Error: Something is wrong!", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            VolleyLog.d(TAG, "Error: " + error.getMessage());
                            pDialog.hide();
                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<String, String>();
                            headers.put("Content-Type", "application/json; charset=utf-8");
                            return headers;
                        }
                    };
                    AppController.getInstance().addToRequestQueue(jsonReq);
                }
            }
        });
    }
}
