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

public class MessagingActivity extends AppCompatActivity {
    Button b1, b2;
    EditText e1;
    TextView t1;

    private WebSocketClient cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);
        b1 = (Button) findViewById(R.id.ExitButton);
        b2 = (Button) findViewById(R.id.SendButton);
        e1 = (EditText) findViewById(R.id.UserMessage);
        t1 = (TextView) findViewById(R.id.ChatView);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MessagingActivity.this, MainActivity.class);
                startActivity(i);
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cc.send(e1.getText().toString());
                } catch (Exception e) {
                    Log.d("ExceptionSendMessage:", e.getMessage().toString());
                }
            }
        });

    }
}
