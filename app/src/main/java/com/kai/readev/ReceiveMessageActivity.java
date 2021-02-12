package com.kai.readev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiveMessageActivity extends AppCompatActivity {

    static final String EXTRA_MESSAGE = "sms";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);

        TextView view = (TextView) findViewById(R.id.message_view);
        view.setText(message);
    }
}