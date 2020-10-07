package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Playable extends AppCompatActivity {

    String player,player1,player2,pdisplay;
    TextView appName;
    TextView playerName;
    int [][] winner={{0,1,2},{0,3,6},{1,4,7},{2,5,8},{3,4,5},{6,7,8},{0,4,8},{2,4,6}};
    int[] playerChoice=new int[9];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playable);
        appName=findViewById(R.id.name);
        appName.setText(R.string.app_name);
        playerName=findViewById(R.id.playerName);
        player=getString(R.string.player1);
        player1=getIntent().getStringExtra(getString(R.string.player1intent));
        player2=getIntent().getStringExtra(getString(R.string.player2intent));
        pdisplay=player1;
        playerName.setText("PLAYER'S CHANCE:"+pdisplay);
        for(int i:playerChoice)
        {
            playerChoice[i]=0;
        }
    }

    public void ImageIsClicked(View view)
    {
        ImageView image=(ImageView)view;
        int tag = Integer.parseInt(image.getTag().toString());
        if(isClicked(tag))
        {

        }
        else {
            image.setTranslationX(-2000);
            if (player.equals(getString(R.string.player1))) {
                image.setImageResource(R.drawable.circle);
                image.animate().translationXBy(2000).rotationY(3600).alpha(1).setDuration(1000);
                player = getString(R.string.player2);
                playerName.setText("PLAYER'S CHANCE:"+player2);
                pdisplay=player1;

                playerChoice[tag] = R.string.player1;
            } else if (player.equals(getString(R.string.player2))) {
                image.setImageResource(R.drawable.cross);
                image.animate().translationXBy(2000).rotationY(3600).alpha(1).setDuration(1000);
                player = getString(R.string.player1);
                playerName.setText("PLAYER'S CHANCE:"+player1);
                pdisplay=player2;
                playerChoice[tag] = R.string.player2;
            }
        }
        boolean win=checkWinner();
        boolean isVacant=isVacant();
        if(win)
        {
            showWinAlert(pdisplay);
        }

        else if(isVacant)
        {
            showWinAlert("NO ONE");
        }

    }
    private boolean checkWinner()
    {
        int flag;
        for(int [] winnerNum:winner)
        {
            if(playerChoice[winnerNum[0]]!=0
                    && playerChoice[winnerNum[0]]==playerChoice[winnerNum[1]]
                    && playerChoice[winnerNum[1]]==playerChoice[winnerNum[2]])
            {
                return true;
            }
        }
        return false;
    }
    private void showWinAlert(String playerName)
    {
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle(getString(R.string.winnerAlert)+" "+playerName);
        alert.setPositiveButton(getString(R.string.newgameStart), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(Playable.this,Playable.class);
                intent.putExtra(getString(R.string.player1intent),player1);
                intent.putExtra(getString(R.string.player2intent),player2);
                startActivity(intent);
            }
        });
        alert.setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alert.setNeutralButton(getString(R.string.newPlayerText), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Playable.this,MainActivity.class));
            }
        });
        alert.setCancelable(false);
        alert.create().show();
    }

    private boolean isVacant()
    {

        for(int i=0;i<9;i++)
        {
            if(playerChoice[i]==0)
                return false;
        }
        return true;
    }
    private boolean isClicked(int tag)
    {
        if(playerChoice[tag]==0)
            return false;
        else
            return true;
    }

}