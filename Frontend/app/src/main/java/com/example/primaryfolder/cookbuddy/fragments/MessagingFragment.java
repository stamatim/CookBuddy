package com.example.primaryfolder.cookbuddy.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.primaryfolder.cookbuddy.R;
import com.example.primaryfolder.cookbuddy.activities.MainActivity;
import com.example.primaryfolder.cookbuddy.net_utils.Const;
import com.example.primaryfolder.cookbuddy.other.SessionManager;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

import static com.example.primaryfolder.cookbuddy.activities.MainActivity.*;


public class MessagingFragment extends Fragment {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    private Button  b1,b2;
    private EditText e2;
    private TextView t1;
    private WebSocketClient cc;

    String userName = MainActivity.userName;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(Const.TAG_MESSAGING);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messaging, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        b1=(Button)view.findViewById(R.id.bt1);
        b2=(Button)view.findViewById(R.id.bt2);
        e2=(EditText)view.findViewById(R.id.et2);
        t1=(TextView)view.findViewById(R.id.tx1);
        t1.setMovementMethod(new ScrollingMovementMethod());


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Draft[] drafts = {new Draft_6455()};

                /**
                 * If running this on an android device, make sure it is on the same network as your
                 * computer, and change the ip address to that of your computer.
                 * If running on the emulator, you can use localhost.
                 */
                //proj309-sb-02.misc.iastate.edu:8080
                String w = "ws://proj309-sb-02.misc.iastate.edu:8080/websocket/" + MainActivity.userName;
                try {
                    cc.close();
                }
                catch(Exception e){

                }
                try {
                    Log.d("Socket:", "Trying socket");
                    cc = new WebSocketClient(new URI(w), (Draft) drafts[0]) {
                        @Override
                        public void onMessage(String message) {
                            Log.d("", "run() returned: " + message);
                            String s = t1.getText().toString();
                            //t1.setText("hello world");
                            //Log.d("first", "run() returned: " + s);
                            //s=t1.getText().toString();
                            //Log.d("second", "run() returned: " + s);
                            t1.setText(s  +  message + "\n");
                        }

                        @Override
                        public void onOpen(ServerHandshake handshake) {
                            Log.d("OPEN", "run() returned: " + "is connecting");
                        }

                        @Override
                        public void onClose(int code, String reason, boolean remote) {
                            Log.d("CLOSE", "onClose() returned: " + reason);
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.d("Exception:", e.toString());
                        }
                    };
                } catch (URISyntaxException e) {
                    Log.d("Exception:", e.getMessage().toString());
                    e.printStackTrace();
                }
                cc.connect();

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cc.send(userName + ":" + e2.getText().toString());
                }
                catch (Exception e)
                {
                    Log.d("ExceptionSendMessage:", e.getMessage().toString());
                }
            }
        });
    }
}
