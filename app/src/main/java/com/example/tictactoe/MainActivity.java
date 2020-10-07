package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText p1;
    EditText p2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p1=findViewById(R.id.p1Name);
        p2=findViewById(R.id.p2Name);
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pn1=p1.getText().toString();
                String pn2=p2.getText().toString();
                Intent intent=new Intent(MainActivity.this,Playable.class);
                intent.putExtra(getString(R.string.player1intent),pn1);
                intent.putExtra(getString(R.string.player2intent),pn2);
                startActivity(intent);
            }
        });

    }

    }