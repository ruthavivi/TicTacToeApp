package com.example.class3_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView playerstatus;
    private ImageButton[] buttons=new ImageButton[9];
    private int rountCount;
    boolean activePlayer;
    //p1=>0
    //p2=>1
    //empty=>2
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][]winningPosition={{0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        for(int i=0;i<buttons.length;i++) {


            String buttonID = "imageButton" + i;
            int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = (ImageButton) findViewById(resourceID);
            buttons[i].setOnClickListener(this);
        }
        rountCount=0;
        activePlayer=true;



        }














    @Override
    public void onClick(View v) {
        if(((ImageButton)v).isSelected()){
            return;
        }
        String buttonID=v.getResources().getResourceEntryName(v.getId());
        int gameStatePointer=Integer.parseInt(buttonID.substring(buttonID.length()-1,buttonID.length()));
        ImageButton image=findViewById(R.id.imageButton9);
        if(activePlayer){
            Drawable xplay=getResources().getDrawable(R.drawable.xplay);
            image.setImageDrawable(xplay);
            Drawable odraw=getResources().getDrawable(R.drawable.o);
            ((ImageButton)v).setImageDrawable(odraw);
            gameState[gameStatePointer]=0;
            activePlayer=!activePlayer;
        }
        else{
            Drawable oplay=getResources().getDrawable(R.drawable.oplay);
            image.setImageDrawable(oplay);
            Drawable xdraw=getResources().getDrawable(R.drawable.x);
            ((ImageButton)v).setImageDrawable(xdraw);
            gameState[gameStatePointer]=1;
            activePlayer=!activePlayer;
        }
        rountCount++;
        if(checkWinner()){
            if(activePlayer){
                Drawable xwin=getResources().getDrawable(R.drawable.xwin);
                image.setImageDrawable(xwin);

                playAgain();
            }
            else{
                Drawable owin=getResources().getDrawable(R.drawable.owin);
                image.setImageDrawable(owin);

                playAgain();

            }

        }
        else if(rountCount==9){
            Drawable nowin=getResources().getDrawable(R.drawable.nowin);
            image.setImageDrawable(nowin);
            playAgain();
        }
        else{



        }



    }
    public boolean checkWinner(){
        boolean winnerResult =false;
        for(int[]winningPosition:winningPosition){
            if(gameState[winningPosition[0]]==gameState[winningPosition[1]]&&
                    gameState[winningPosition[1]]==gameState[winningPosition[2]]&&
                    gameState[winningPosition[0]]!=2){
                winnerResult=true;

            }

        }
        return winnerResult;
    }

    public void playAgain(){
        rountCount=0;
        activePlayer=true;
        for(int i=0;i<buttons.length;i++){
            gameState[i]=2;
            String buttonID = "imageButton" + i;
            int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = (ImageButton) findViewById(resourceID);
            Drawable empty=getResources().getDrawable(R.drawable.empty);
            buttons[i].setImageDrawable(empty);

        }


    }




}
