package com.kai.readev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class CreateMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);

        Intent intent = getIntent();
        Log.d("abc", intent.toString());
    }

    public void onSendMessage(View view) {
        EditText input = (EditText) findViewById(R.id.message_input);
        String message = input.getText().toString();
        if (message.trim().length() == 0) return;

//        Intent intent = new Intent(this, ReceiveMessageActivity.class);
//        intent.putExtra(ReceiveMessageActivity.EXTRA_MESSAGE, message);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, message);

        Intent chosenIntent
                = Intent.createChooser(intent, "Send message via...");
        startActivity(chosenIntent);
    }
}