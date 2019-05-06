package com.example.android.connect;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class game extends AppCompatActivity {
    //0 denotes oreo
    //1 denotes lollipop
    int activeplayer=0;
    int []gamestate={8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8};//8 means vacant cell
    int[][] winningPositions = {{0,1,2,3}, {4,5,6,7}, {8,9,10,11}, {12,13,14,15}, {0,4,8,12}, {1,5,9,13}, {2,6,10,14}, {3,7,11,15},{0,5,10,15},{3,6,9,12}};

    boolean  gameIsActive=true;
    public void dropIn(View view)
    {
        ImageView cell=(ImageView) view;
        int tappedcell=Integer.parseInt(cell.getTag().toString());
        if(gamestate[tappedcell]==8 && gameIsActive)
        {
            gamestate[tappedcell]=activeplayer;
            cell.setTranslationY(-1000f);
            if(activeplayer==0)
            {
                cell.setImageResource(R.drawable.oreo);
                activeplayer=1;
            }
            else
            {
                cell.setImageResource(R.drawable.lollipop);
                activeplayer=0;
            }
            cell.animate().translationYBy(1000f).rotation(360).setDuration(300);
            for(int []p:winningPositions) {
                if (gamestate[p[0]] == gamestate[p[1]]
                        && gamestate[p[1]] == gamestate[p[2]]
                        && gamestate[p[2]] == gamestate[p[3]]

                        && gamestate[p[0]] != 8) {
                    gameIsActive = false;
                    String winner = "LOLLIPOP";
                    if (gamestate[p[0]] == 0) {
                        winner = "OREO";
                    }
                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(" " + winner + " has won! ");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                    break;
                }
                else {
                    boolean gameIsOver = true;

                    for (int i : gamestate) {
                        if (i == 8)
                            gameIsOver = false;
                    }

                    if (gameIsOver) {
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText("  MATCH DRAW");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }
    public void playAgain(View view) {
        gameIsActive = true;
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activeplayer = 0;
        for (int i = 0; i < gamestate.length; i++) {
            gamestate[i] = 8;
        }
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridlayout);
        for (int i = 0; i< gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        Log.i("button tapped","twice");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
