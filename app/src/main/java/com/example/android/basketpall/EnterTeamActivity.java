package com.example.android.basketpall;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EnterTeamActivity extends AppCompatActivity {
    EditText team1Input;
    EditText team2Input;
    TextView enterTeam1Text;
    TextView enterTeam2Text;
    Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_team);

        team1Input = findViewById(R.id.edit_text_team1);
        team2Input = findViewById(R.id.edit_text_team2);

        enterTeam1Text = findViewById(R.id.enter_team1);
        Typeface typefaceEnterTeam1 = ResourcesCompat.getFont(this, R.font.arcade);
        enterTeam1Text.setTypeface(typefaceEnterTeam1);

        enterTeam2Text = findViewById(R.id.enter_team2);
        Typeface typefaceEnterTeam2 = ResourcesCompat.getFont(this, R.font.arcade);
        enterTeam2Text.setTypeface(typefaceEnterTeam2);

        playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent playIntent = new Intent(EnterTeamActivity.this, PlayActivity.class);
                playIntent.putExtra("team1", team1Input.getText().toString());
                playIntent.putExtra("team2", team2Input.getText().toString());
                startActivity(playIntent);
            }
        });
    }
}
