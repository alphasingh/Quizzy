package com.sample.multiplechoicequiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class HighestScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highest_score);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ImageView medal = (ImageView) findViewById(R.id.medal);
        TextView txtScore = (TextView) findViewById(R.id.textScore);
        TextView msg = (TextView) findViewById(R.id.msg);
        MediaPlayer mp = new MediaPlayer();
        TextView txtHighScore = (TextView) findViewById(R.id.textHighScore);
        // receive the score from last activity by Intent
        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        // display current score
        txtScore.setText("You scored "+score);

        // use Shared preferences to save the best score
        SharedPreferences mypref = getPreferences(MODE_PRIVATE);
        int highscore = mypref.getInt("highscore",0);
        // show medals according to performance
        if(score>45) {
            msg.setText("An amazing performance, definitely you. Keep it up!");
            medal.setImageResource(R.drawable.gold);
            mp = MediaPlayer.create(this, R.raw.winner);
        }
        else if(score>30) {
            medal.setImageResource(R.drawable.silver);
            mp = MediaPlayer.create(this, R.raw.winner);
        }
        else {
            msg.setText("Below par performance, definitely not you. Come on! You can do better!");
            medal.setImageResource(R.drawable.bronze);
            mp = MediaPlayer.create(this, R.raw.loser);
        }
        mp.start();
        // set high scores and update for next usage
        if(highscore>= score)
            txtHighScore.setText("High score: "+highscore);
        else
        {
            Toast.makeText(this, "You set a new Record!", Toast.LENGTH_SHORT).show();
            txtHighScore.setText("New highscore: "+score);
            SharedPreferences.Editor editor = mypref.edit();
            editor.putInt("highscore", score);
            editor.apply();
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(HighestScoreActivity.this, QuizActivity.class);
        startActivity(intent);
    }
}

