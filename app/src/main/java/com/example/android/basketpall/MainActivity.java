package com.example.android.basketpall;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button enterButton;
    TextView fontText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fontText = findViewById(R.id.basketPall);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.arcade);
        fontText.setTypeface(typeface);

        enterButton = findViewById(R.id.enter_button);
        enterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent enterIntent = new Intent(MainActivity.this, EnterTeamActivity.class);
                startActivity(enterIntent);
            }
        });
    }
}
