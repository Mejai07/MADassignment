package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;


public class Level5 extends AppCompatActivity {




    public int score;
    private TextView mTextViewCountDown; //countdown text
    public TextView mTextViewScore; //Score text
    private CountDownTimer mCountDownTimer,levelChange;
    private boolean mTimerRunning;// tell if the time is running
    private long mTimeLeftInMillis = 5000;//start with 5 seconds




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level5);


        score = getIntent().getIntExtra("score", 0);



        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mTextViewScore = findViewById(R.id.text_view_score);
        updateScoreText();
        startTimer();//start timer
        Change();
        activity();

    }

    //startTimer() function

    private void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {//start with time left and update the timer every one second
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();//Update time left on top of the level

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                mTextViewCountDown.setText("GAME OVER");

            }
        }.start();


    }

    private void updateCountDownText(){
        int minutes = (int) mTimeLeftInMillis / 1000 / 60 ; //return minutes
        int seconds = (int) mTimeLeftInMillis / 1000 % 60 ; //return seconds

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
    }

    //Level Change every 5 seconds
    private void Change() {
        levelChange = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Do nothing
            }

            @Override
            public void onFinish() {

                Intent leaderboard = new Intent(Level5.this, InputName.class);//change to level2
                leaderboard.putExtra("score", score);
                startActivity(leaderboard);
                finish();
            }
        }.start();
    }

    //Start the activity
    private void activity(){

        //Initialize the buttons
        Button[] buttons;

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button button10 = findViewById(R.id.button10);
        Button button11 = findViewById(R.id.button11);
        Button button12 = findViewById(R.id.button12);
        Button button13 = findViewById(R.id.button13);
        Button button14 = findViewById(R.id.button14);
        Button button15 = findViewById(R.id.button15);
        Button button16 = findViewById(R.id.button16);
        Button button17 = findViewById(R.id.button17);
        Button button18 = findViewById(R.id.button18);
        Button button19 = findViewById(R.id.button19);
        Button button20 = findViewById(R.id.button20);
        Button button21 = findViewById(R.id.button21);
        Button button22 = findViewById(R.id.button22);
        Button button23 = findViewById(R.id.button23);
        Button button24 = findViewById(R.id.button24);
        Button button25 = findViewById(R.id.button25);
        Button button26 = findViewById(R.id.button26);
        Button button27 = findViewById(R.id.button27);
        Button button28 = findViewById(R.id.button28);
        Button button29 = findViewById(R.id.button29);
        Button button30 = findViewById(R.id.button30);
        Button button31 = findViewById(R.id.button31);
        Button button32 = findViewById(R.id.button32);
        Button button33 = findViewById(R.id.button33);
        Button button34 = findViewById(R.id.button34);
        Button button35 = findViewById(R.id.button35);
        Button button36 = findViewById(R.id.button36);


        //put the buttons into the array for easier access
        buttons = new Button[]{button1, button2, button3, button4, button5, button6, button7, button8, button9};
        int randomIndex = new Random().nextInt(buttons.length);
        Button randomButton = buttons[randomIndex];

        // start the game by randomly select one of the view to be highlighted
        int green = Color.rgb(0, 255 ,0);
        randomButton.setBackgroundColor(green);
        randomButton.setEnabled(true);

        for (Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    view.setEnabled(false);//disable the activated button
                    Button currentButton = (Button) view;
                    int red = Color.rgb(255, 0 ,0);
                    view.setBackgroundColor(red);
                    // Randomly select a button to change its color
                    int randomIndex = new Random().nextInt(buttons.length);
                    Button randomButton = buttons[randomIndex];
                    // Prevent selecting the same button twice in a row
                    while (randomButton == currentButton) {
                        randomIndex = new Random().nextInt(buttons.length);
                        randomButton = buttons[randomIndex];
                    }

                    // Change the color of the randomly selected button and make the button clickable
                    int green = Color.rgb(0, 255 ,0);
                    randomButton.setBackgroundColor(green);
                    randomButton.setEnabled(true);
                    ++score;
                    updateScoreText();

                }
            });
        }




    }




    //update the scoreText with the score of the player
    private void updateScoreText(){

        mTextViewScore = findViewById(R.id.text_view_score);
        mTextViewScore.setText(String.valueOf(score));
    }
}