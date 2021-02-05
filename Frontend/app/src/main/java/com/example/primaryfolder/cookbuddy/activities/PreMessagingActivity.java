package com.example.primaryfolder.cookbuddy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.primaryfolder.cookbuddy.R;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

//This class will force a user to enter a username before joining a global chat and opening the messaging activity
public class PreMessagingActivity extends AppCompatActivity {
    Button b1;
    EditText e1;
    TextView t1;
    private WebSocketClient cc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premessaging);
        b1=(Button)findViewById(R.id.JoinChatButton);
        e1=(EditText)findViewById(R.id.userNameEnter);
        t1=(TextView)findViewById(R.id.ChatView);

        b1.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View view){
                Draft[] drafts = {new Draft_6455()};
                //Connects to server
                String w = "proj309-sb-02.misc.iastate.edu:8080/websocket/"+e1.getText().toString();
                try {
                    Log.d("Socket:", "Trying socket");
                    cc = new WebSocketClient(new URI(w),(Draft) drafts[0]) {
                        @Override
                        public void onMessage(String message) {
                            Log.d("", "run() returned: " + message);
                            String s=t1.getText().toString();
                            t1.setText(s+" Server:"+message);
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
                        public void onError(Exception e)
                        {
                            Log.d("Exception:", e.toString());
                        }
                    };
                }
                catch (URISyntaxException e) {
                    Log.d("Exception:", e.getMessage().toString());
                    e.printStackTrace();
                }
                cc.connect();
                Intent i = new Intent(PreMessagingActivity.this, MessagingActivity.class);
                startActivity(i);
            }
        });

    }
}

