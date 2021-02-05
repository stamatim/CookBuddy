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

public class RegisterUser extends AppCompatActivity {

    // The tag
    private String TAG = RegisterUser.class.getSimpleName();

    // Variables for buttons on the login screen
    Button btnSubmitInfo, btnToSignInPage;

    // Values for the user fields
    EditText uName, uEmail, uPassword, uPasswordConfirm;

    // The user session manager
    public SessionManager uSession;

    // The url for the server
    static final String SERVER_URL = "http://proj309-sb-02.misc.iastate.edu:8080/users/";
    private int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        // Initialize variables
        btnSubmitInfo = (Button) findViewById(R.id.submitInfo); // Submits the user info to the server
        btnToSignInPage = (Button) findViewById(R.id.signInPage); // Takes user to another page to sign in with existing credentials

        uName = (EditText) findViewById(R.id.userName); // Variable for the name entered in field
        uEmail = (EditText) findViewById(R.id.userEmail); // Variable for the email entered in field
        uPassword = (EditText) findViewById(R.id.userPassword); // Variable for the password entered in field
        uPasswordConfirm = (EditText) findViewById(R.id.userPasswordConfirm); // Variable for the password in field

        uSession = new SessionManager(getApplicationContext());
        Toast.makeText(getApplicationContext(), "User Login Status: " + uSession.isLoggedIn(), Toast.LENGTH_LONG).show();

        // The "Sign In" button on this activity that will redirect to the existing user sign in activity
        btnToSignInPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterUser.this, SignInActivity.class);
                startActivity(i);
            }
        });

        // The "Register" button on this activity that will submit the info entered to the server and redirect to MainActivity
        btnSubmitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog pDialog = new ProgressDialog(RegisterUser.this); // The progress dialog object for this activity
                pDialog.setMessage("Verifying your information, Please Wait...");
                pDialog.show();

                /*
                 *
                 * String format of entered information
                 *
                 */
                final String userName, userEmail, userPassword, userShoppingList;

                // Check to see if there is a name entered
                if (uName.getText().toString().equals("")) {
                    Toast.makeText(RegisterUser.this, "Error, Please enter your name in the required field", Toast.LENGTH_LONG).show();
                    pDialog.hide();
                }

                // Check to see if there is an email entered
                else if (uEmail.getText().toString().equals("")) {
                    Toast.makeText(RegisterUser.this, "Error, Please enter a valid email in the required field", Toast.LENGTH_LONG).show();
                    pDialog.hide();
                }

                // Check to see if there is a password entered in the field
                else if (uPassword.getText().toString().equals("")) {
                    Toast.makeText(RegisterUser.this, "Error, Please enter a password in the required field", Toast.LENGTH_LONG).show();
                    pDialog.hide();
                }

                // Check to make sure passwords match
                else if (!uPassword.getText().toString().equals(uPasswordConfirm.getText().toString())) {
                    Toast.makeText(RegisterUser.this, "Error, Please re-enter your passwords and make sure they match", Toast.LENGTH_LONG).show();
                    pDialog.hide();
                }

                else {


                    /*
                     *
                     * Initialize String fields
                     *
                     */
                    userName = uName.getText().toString();
                    userEmail = uEmail.getText().toString();
                    userPassword = uPassword.getText().toString();
                    userShoppingList = "";

                    /*
                     *
                     * Parameters used by back end methods
                     *
                     */
                    Map<String, String> postParam = new HashMap<>();
                    postParam.put("name", userName);
                    postParam.put("email", userEmail);
                    postParam.put("password", userPassword);

                    /*
                     *
                     * Json Request using Post method
                     *
                     */
                    JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, SERVER_URL + "add", new JSONObject(postParam),

                            // Response Listener
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.d(TAG, response.toString());
                                    pDialog.hide();
                                    int err;
                                    try {
                                        err = response.getInt("Error");
                                        userID = response.getInt("userId");
                                    } catch (JSONException e) {
                                        err = 1;
                                    }
                                    if (err == 0) {
                                        // Create user login session
                                        uSession.createLoginSession(userName, userEmail, userID);
                                        uSession.saveUserShoppingList(userShoppingList);

                                        Intent j = new Intent(RegisterUser.this, MainActivity.class);
                                        startActivity(j);

                                    } else {
                                        Toast.makeText(RegisterUser.this, "Err" +
                                                "or: User name or email taken", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            },

                            // Error Listener
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                                    pDialog.hide();
                                }

                            }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {

                            Map<String, String> headers = new HashMap<String, String>();
                            headers.put("Content-Type", "application/json; charset=utf-8");

                            return headers;
                        }
                    };

                    // Add request to queue
                    AppController.getInstance().addToRequestQueue(jsonObjReq);
                }
            }
        });
    }
}
