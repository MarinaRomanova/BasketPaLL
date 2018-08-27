package com.example.android.basketpall;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity {

    final String TEAM1_SCORE = "saved score team 1";
    final String TEAM2_SCORE = "saved score team 2";

    // Var that tracks the score for Team 1
    int scoreTeam1;
    // Var that tracks the score for Team 2
    int scoreTeam2;

    // TextView that displays the name of Team 1
    TextView team1Text;
    // TextView that displays the name of Team 2
    TextView team2Text;

    //TextView that displays the score for team 1
    TextView scoreViewTeam1;
    //TextView that displays the score for team 2
    TextView scoreViewTeam2;

    // Button "SHARE" that sends email with the score results of the game
    Button shareScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        /**
         * Passes the names of teams entered in EditText field in EnterTeamActivity
         */

        Intent intent = getIntent();
        final String strTeam1Input = intent.getStringExtra("team1");
        final String strTeam2Input = intent.getStringExtra("team2");

        /**
         * Sets the name of Team 1 in TextView, using information entered by the user, if not
         * Team 1 is displayed by default.
         */

        team1Text = findViewById(R.id.team1);
        Typeface typefaceTeam1 = ResourcesCompat.getFont(this, R.font.arcade);
        team1Text.setTypeface(typefaceTeam1);
        if (strTeam1Input != null && !strTeam1Input.isEmpty()) {
            team1Text.setText(strTeam1Input);
        }

        /**
         * Sets the name of Team 2 in TextView, using information entered by the user, if not
         * Team 2 is displayed by default.
         */

        team2Text = findViewById(R.id.team2);
        Typeface typefaceTeam2 = ResourcesCompat.getFont(this, R.font.arcade);
        team2Text.setTypeface(typefaceTeam2);
        if (strTeam2Input != null && !strTeam2Input.isEmpty()) {
            team2Text.setText(strTeam2Input);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //Saves the score for two teams
        savedInstanceState.putInt(TEAM1_SCORE, scoreTeam1);
        savedInstanceState.putInt(TEAM2_SCORE, scoreTeam2);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //Passes the score for two teams
        scoreTeam1 = savedInstanceState.getInt(TEAM1_SCORE);
        scoreTeam2 = savedInstanceState.getInt(TEAM2_SCORE);

        //Dosplays saved score for Team 1 in TextView
        TextView textViewTeam1 = findViewById(R.id.team1_score);
        textViewTeam1.setText(String.valueOf(scoreTeam1));

        //Displays saved score for Team 2 in TextView
        TextView textViewTeam2 = findViewById(R.id.team2_score);
        textViewTeam2.setText(String.valueOf(scoreTeam2));
    }

    /**
     * When Share button is clicked, an email app is open with pre-entered text with the score of the game.
     */

    public void shareScore(View view) {

        shareScore = findViewById(R.id.share_button);
        shareScore.setOnClickListener(new View.OnClickListener() {

            TextView team1Input = (TextView) findViewById(R.id.team1);
            String strTeam1Input = team1Input.getText().toString();

            TextView team2Input = (TextView) findViewById(R.id.team2);
            String strTeam2Input = team2Input.getText().toString();

            String scoreMessage = createScoreSummary(strTeam1Input, strTeam2Input, scoreTeam1, scoreTeam2);

            @Override
            public void onClick(View view) {
                Intent shareScoreIntent = new Intent(Intent.ACTION_SENDTO);
                shareScoreIntent.setData(Uri.parse("mailto:")); // only email apps should handle this
                shareScoreIntent.putExtra(Intent.EXTRA_TEXT, scoreMessage);
                shareScoreIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.subject_email));
                startActivity(shareScoreIntent);
            }
        });

    }

    /**
     * This method creates email text message with score results.
     * The method is used inside the shareScore method.
     */

    public String createScoreSummary(String team1Name, String team2Name, int score1, int score2) {
        String scoreMessage = team1Name + " " + getResources().getString(R.string.scored_message) + " " + score1;
        scoreMessage += "\n" + team2Name + " " + getResources().getString(R.string.scored_message) + " " + score2;
        return scoreMessage;
    }

    /**
     * This method adds 1 point to Team 1 when button Free Throw is clicked.
     */

    public void freeThrowTeam1(View v) {
        scoreTeam1 += 1;
        displayForTeam1(scoreTeam1);
    }

    /**
     * This method adds 2 points to a Team 1 when button 2 is clicked.
     */

    public void add2PointsTeam1(View v) {
        scoreTeam1 += 2;
        displayForTeam1(scoreTeam1);
    }

    /**
     * This method adds 3 points to a Team 1 when button 1 is clicked.
     */

    public void add3PointsTeam1(View v) {
        scoreTeam1 += 3;
        displayForTeam1(scoreTeam1);
    }

    /**
     * This method adds 1 point to Team 2 when button Free Throw is clicked.
     */

    public void freeThrowTeam2(View v) {
        scoreTeam2 += 1;
        displayForTeam2(scoreTeam2);
    }

    /**
     * This method adds 2 points to a Team 2 when button 2 is clicked.
     */

    public void add2PointsTeam2(View v) {
        scoreTeam2 += 2;
        displayForTeam2(scoreTeam2);
    }


    /**
     * This method adds 3 points to a Team 2 when button 1 is clicked.
     */

    public void add3PointsTeam2(View v) {
        scoreTeam2 += 3;
        displayForTeam2(scoreTeam2);
    }


    /**
     * This method resets scores for both teams.
     */

    public void resetScore(View view) {
        scoreTeam1 = 0;
        scoreTeam2 = 0;
        displayForTeam1(scoreTeam1);
        displayForTeam2(scoreTeam2);
    }

    private void displayForTeam1(int score) {
        scoreViewTeam1 = findViewById(R.id.team1_score);
        scoreViewTeam1.setText(String.valueOf(score));
    }

    private void displayForTeam2(int score) {
        scoreViewTeam2 = findViewById(R.id.team2_score);
        scoreViewTeam2.setText(String.valueOf(score));
    }

}
