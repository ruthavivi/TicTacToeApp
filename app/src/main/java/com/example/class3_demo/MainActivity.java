package com.example.class3_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private TextView playerstatus;
    private ImageButton[] buttons=new ImageButton[9];
    private int rountCount;
    boolean activePlayer;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][]winningPosition={{0,1,2},{3,4,5},{6,7,8}, //ROWS
                            {0,3,6},{1,4,7},{2,5,8}, //COLUMNS
                            {0,4,8},{2,4,6}};        //CROSS

    private Button ResetButton;
    //DATA
    //p1=>0
    //p2=>1
    //empty=>2


    //-----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i=0;i<buttons.length;i++)
        {
            String buttonID = "imageButton" + i;
            int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = (ImageButton) findViewById(resourceID);
            buttons[i].setOnClickListener(this);
        }
        ResetButton= (Button) findViewById(R.id.resetGame);
        rountCount=0;
        activePlayer=true;
        ResetButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                playAgain();
            }
        });
    }

    //-----------------------------------------------------------------------------------------------

    @Override
    public void onClick(View v)
    {
        if(((ImageButton)v).isSelected())
        {
            return;
        }

        String buttonID=v.getResources().getResourceEntryName(v.getId());
        int gameStatePointer=Integer.parseInt(buttonID.substring(buttonID.length()-1,buttonID.length()));
        ImageButton image=findViewById(R.id.imageButton9);

        if(activePlayer)
        {
            Drawable xplay=getResources().getDrawable(R.drawable.xplay,null);
            image.setImageDrawable(xplay);
            Drawable odraw=getResources().getDrawable(R.drawable.o,null);
            ((ImageButton)v).setImageDrawable(odraw);
            gameState[gameStatePointer]=0;
            activePlayer=!activePlayer;
        }

        else
        {
            Drawable oplay=getResources().getDrawable(R.drawable.oplay,null);
            image.setImageDrawable(oplay);
            Drawable xdraw=getResources().getDrawable(R.drawable.x,null);
            ((ImageButton)v).setImageDrawable(xdraw);
            gameState[gameStatePointer]=1;
            activePlayer=!activePlayer;
        }

        rountCount++;


        if(checkWinner()){
            ImageView image1=findViewById(R.id.imageView12);
            if(gameState[0]==gameState[1]&&gameState[1]==gameState[2]){
                Drawable row1=getResources().getDrawable(R.drawable.mark6);
                image1.setImageDrawable(row1);
            }else if(gameState[3]==gameState[4]&&gameState[4]==gameState[5]){
                Drawable row2=getResources().getDrawable(R.drawable.mark7);
                image1.setImageDrawable(row2);
            }else if(gameState[6]==gameState[7]&&gameState[7]==gameState[8]) {
                Drawable row3 = getResources().getDrawable(R.drawable.mark8);
                image1.setImageDrawable(row3);
            }else if(gameState[0]==gameState[3]&&gameState[3]==gameState[6]) {
                Drawable colom1 = getResources().getDrawable(R.drawable.mark3);
                image1.setImageDrawable(colom1);
            }else if(gameState[1]==gameState[4]&&gameState[4]==gameState[7]) {
                Drawable colom2 = getResources().getDrawable(R.drawable.mark4);
                image1.setImageDrawable(colom2);
            }else if(gameState[2]==gameState[5]&&gameState[5]==gameState[8]) {
                Drawable colom3 = getResources().getDrawable(R.drawable.mark5);
                image1.setImageDrawable(colom3);
            }else if(gameState[0]==gameState[4]&&gameState[4]==gameState[8]) {
                Drawable slant1 = getResources().getDrawable(R.drawable.mark1);
                image1.setImageDrawable(slant1);
            }else if(gameState[2]==gameState[4]&&gameState[4]==gameState[6]) {
                Drawable slant2 = getResources().getDrawable(R.drawable.mark2);
                image1.setImageDrawable(slant2);
            }
        }


        if(checkWinner())
        {
            if(activePlayer)
            {
                Drawable xwin=getResources().getDrawable(R.drawable.xwin,null);
                image.setImageDrawable(xwin);
            }
            else
             {
                Drawable owin=getResources().getDrawable(R.drawable.owin,null);
                image.setImageDrawable(owin);

                 /*Drawable line73 = getResources().getDrawable(R.drawable.mark1,null);
                 image.setImageDrawable(line73);
                 Drawable line91 = getResources().getDrawable(R.drawable.mark2,null);
                 image.setImageDrawable(line91);
                 Drawable line82 = getResources().getDrawable(R.drawable.mark4,null);
                 image.setImageDrawable(line82);
                 Drawable line71 = getResources().getDrawable(R.drawable.mark3,null);
                 image.setImageDrawable(line71);
                 Drawable line93 = getResources().getDrawable(R.drawable.mark5,null);
                 image.setImageDrawable(line93);*/

                //playAgain();

            }

        }
        else if(rountCount==9)
        {
            Drawable nowin=getResources().getDrawable(R.drawable.nowin,null);
            image.setImageDrawable(nowin);
            playAgain();
        }

    }
    //-----------------------------------------------------------------------------------------------

    public boolean checkWinner()
    {
        boolean winnerResult =false;
        for(int [] winningPosition:winningPosition )
        {
            if(gameState[winningPosition[0]]==gameState[winningPosition[1]]&&
                    gameState[winningPosition[1]]==gameState[winningPosition[2]]&&
                    gameState[winningPosition[0]]!=2)
            {
                winnerResult=true;

            }

        }
        return winnerResult;
    }

    //-----------------------------------------------------------------------------------------------
    public void playAgain()
    {
        rountCount=0;
        activePlayer=true;
        ImageView image1=findViewById(R.id.imageView12);
        Drawable emptyy=getResources().getDrawable(R.drawable.empty);
        image1.setImageDrawable(emptyy);

        for(int i=0;i<buttons.length;i++)
        {
            gameState[i]=2;
            String buttonID = "imageButton" + i;
            int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = (ImageButton) findViewById(resourceID);
            Drawable empty=getResources().getDrawable(R.drawable.empty,null);
            buttons[i].setImageDrawable(empty);

        }

    }
    //-----------------------------------------------------------------------------------------------


}
