package com.example.primaryfolder.cookbuddy.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.primaryfolder.cookbuddy.R;
import com.example.primaryfolder.cookbuddy.net_utils.Const;
import com.example.primaryfolder.cookbuddy.other.SessionManager;

import java.util.HashMap;


public class ProfileFragment extends Fragment {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters

    Button btnLogout;
    public SessionManager uSession;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(Const.TAG_PROFILE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // Session class instance
        uSession = new SessionManager(getContext());

        // for displaying user data
        TextView lblName = (TextView) view.findViewById(R.id.lblName);
        TextView lblEmail = (TextView) view.findViewById(R.id.lblEmail);
        btnLogout = (Button) view.findViewById(R.id.logout);

        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
        uSession.checkLogin();

        // get user data from session
        HashMap<String, String> user = uSession.getUserDetails();

        // name
        String name = user.get(SessionManager.KEY_NAME);

        // email
        String email = user.get(SessionManager.KEY_EMAIL);

        // displaying user data
        lblName.setText(Html.fromHtml("Name: <b>" + name + "</b>"));
        lblEmail.setText(Html.fromHtml("Email: <b>" + email + "</b>"));


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uSession.logoutUser();
            }
        });

    }
}
