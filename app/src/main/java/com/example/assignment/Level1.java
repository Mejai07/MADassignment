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


public class Level1 extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 25000;//start time one level one assuming 5 level and 5 seconds each
    public int score = 0;
    public int final_score = 0;
    private TextView mTextViewCountDown; //countdown text
    public TextView mTextViewScore; //Score text
    private CountDownTimer mCountDownTimer,levelChange;
    private boolean mTimerRunning;// tell if the time is running
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;//start with 25 seconds


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);




        mTextViewCountDown = findViewById(R.id.text_view_countdown);
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


            }

            @Override
            public void onFinish() {

                Intent level2 = new Intent(Level1.this, Level2.class);//change to level2
                level2.putExtra("score", score);
                startActivity(level2);
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
        //put the buttons into the array for easier access
        buttons = new Button[]{button1, button2, button3, button4};
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

                    updateScoreText();
                }
            });
        }




    }




    //update the scoreText with the score of the player
    private void updateScoreText(){
        ++score;
        mTextViewScore = findViewById(R.id.text_view_score);
        mTextViewScore.setText(String.valueOf(score));
    }
    }