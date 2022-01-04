package com.gameso.luckynumbers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private ImageView[] picks;
    private String selectedItemName;
    private boolean gameRunning = false;
    private ImageView imageViewRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button buttonBackToMenu = findViewById(R.id.buttonBackToMenu);
        Button buttonExitGame = findViewById(R.id.buttonExitGame);
        Button[] butt = new Button[]{buttonBackToMenu, buttonExitGame};
        for (Button bb : butt) {
            bb.setOnClickListener(onClickButtonListener);
        }

        ImageView imageViewOne = findViewById(R.id.imageViewOne);
        ImageView imageViewTwo = findViewById(R.id.imageViewTwo);
        ImageView imageViewThree = findViewById(R.id.imageViewThree);
        ImageView imageViewFour = findViewById(R.id.imageViewFour);
        ImageView imageViewFive = findViewById(R.id.imageViewFive);
        imageViewRandom = findViewById(R.id.imageViewRandom);
        picks = new ImageView[]{imageViewOne, imageViewTwo, imageViewThree, imageViewFour, imageViewFive};
        for (ImageView item : picks) {
            item.setOnClickListener(onClickGameListener);
            String resourceName = getResources().getResourceEntryName(item.getId());
            item.setTag(resourceName);
        }
    }

    View.OnClickListener onClickButtonListener = view -> {
        int selectedItem = Integer.parseInt(view.getTag().toString());
        switch (selectedItem) {
            case 3:
                Intent intentBackToMenu = new Intent(GameActivity.this, MainActivity.class);
                GameActivity.this.startActivity(intentBackToMenu);
                GameActivity.this.finish();
                break;
            case 4:
                GameActivity.this.finishAffinity();
                break;
        }
    };

    View.OnClickListener onClickGameListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!gameRunning){
                selectedItemName = view.getTag().toString();
                startAnimation();
            } else {
                Toast.makeText(getApplicationContext(), "You already did your spin!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void startAnimation(){
        if(!gameRunning){
            gameRunning = true;
            CountDownTimer countDownTimer = new CountDownTimer(300,100) {
                @Override
                public void onTick(long l) {
                    int random = (int) (Math.random()*picks.length);
                    imageViewRandom.setImageDrawable(picks[random].getDrawable());
                    imageViewRandom.setTag(picks[random].getTag().toString());
                }

                @Override
                public void onFinish() {
                    gameRunning = false;
                    if(imageViewRandom.getTag().equals(selectedItemName)){
                        Toast.makeText(GameActivity.this, "Great! Try again!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(GameActivity.this, "Idiot! Wrong Number! Try again", Toast.LENGTH_SHORT).show();
                    }
                }
            }.start();
        }
    }
}