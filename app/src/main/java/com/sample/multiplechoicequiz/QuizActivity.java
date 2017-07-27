package com.sample.multiplechoicequiz;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private QuestionBank mQuestionLibrary = new QuestionBank();
    private TextView mScoreView;   // view for current total score
    private TextView mQuestionView;  //current question to answer
    private Button mButtonChoice1; // multiple choice 1 for mQuestionView
    private Button mButtonChoice2; // multiple choice 2 for mQuestionView
    private Button mButtonChoice3; // multiple choice 3 for mQuestionView
    private Button mButtonChoice4; // multiple choice 4 for mQuestionView

    private String mAnswer;  // correct answer for question in mQuestionView
    private int mScore = 0;  // current total score
    private int mQuestionNumber = 0; // current question number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        // prevent the screen rotation
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // setup screen for the first question with four alternative to answer
        mScoreView = (TextView)findViewById(R.id.score);
        mQuestionView = (TextView)findViewById(R.id.question);
        mButtonChoice1 = (Button)findViewById(R.id.choice1);
        mButtonChoice2 = (Button)findViewById(R.id.choice2);
        mButtonChoice3 = (Button)findViewById(R.id.choice3);
        mButtonChoice4 = (Button)findViewById(R.id.choice4);
        updateQuestion();
        // show current total score for the user
        updateScore(mScore);
    }

    private void updateQuestion(){
        // check if we are not outside array bounds for questions
        if(mQuestionNumber<mQuestionLibrary.getLength() ){
            // set the text for new question, and new 4 alternative to answer on four buttons
            mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
            this.setTitle("Problem "+(mQuestionNumber+1));
            mButtonChoice1.setText(mQuestionLibrary.getChoice(mQuestionNumber, 0));
            mButtonChoice2.setText(mQuestionLibrary.getChoice(mQuestionNumber, 1));
            mButtonChoice3.setText(mQuestionLibrary.getChoice(mQuestionNumber, 2));
            mButtonChoice4.setText(mQuestionLibrary.getChoice(mQuestionNumber, 3));
            mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
            mQuestionNumber++;
       }
        else {
            Intent intent = new Intent(QuizActivity.this, HighestScoreActivity.class);
            intent.putExtra("score", mScore);
            // pass the current score to the second screen
            startActivity(intent);
            this.finish();
        }
    }

    // show current total score for the user
    private void updateScore(int point) {
        mScoreView.setText(String.valueOf(mScore));
    }

    public void onClick(View view) {
        // to create right or wrong response sounds
        MediaPlayer mp = new MediaPlayer();
        //all logic for all answer buttons in one method
        final Button answer = (Button) view;
        // disable answer responses until next question arrives
        mButtonChoice1.setClickable(false);
        mButtonChoice2.setClickable(false);
        mButtonChoice3.setClickable(false);
        mButtonChoice4.setClickable(false);
        // if the answer is correct, increase the score
        if (answer.getText() == mAnswer){
            answer.setBackgroundColor(Color.GREEN);
            mp = MediaPlayer.create(this, R.raw.yes);
            mScore = mScore + 5;
        }
        else {
            mScore = mScore - 2;
            answer.setBackgroundColor(Color.RED);
            mp = MediaPlayer.create(this, R.raw.fail);
        }
        mp.start();
        if(mScore>0)
            mScoreView.setTextColor(Color.GREEN);
        else
            mScoreView.setTextColor(Color.LTGRAY);
        // show current total score for the user
        updateScore(mScore);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // revert back the colour of the choice button to grey
                answer.setBackgroundColor(Color.WHITE);
                // once user answer the question, we move on to the next one, if any
                updateQuestion();
                // enable answer responses until next question arrives
                mButtonChoice1.setClickable(true);
                mButtonChoice2.setClickable(true);
                mButtonChoice3.setClickable(true);
                mButtonChoice4.setClickable(true);
            }
        },1500);
    }
 }