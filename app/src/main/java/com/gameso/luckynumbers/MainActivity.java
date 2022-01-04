package com.gameso.luckynumbers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonStart;
    private Button buttonPolicy;
    private Button buttonExit;
    private Bundle savedInstanceState;


    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStart = findViewById(R.id.buttonStart);
        buttonPolicy = findViewById(R.id.buttonPolicy);
        buttonExit = findViewById(R.id.buttonExit);
        Button[] buttons = new Button[]{buttonStart, buttonPolicy, buttonExit};
        for(Button b: buttons){
            b.setOnClickListener(onClickListener);
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int selectedItem = Integer.parseInt(view.getTag().toString());
            switch(selectedItem){
                case 0:
                    Intent intentStartGame = new Intent(MainActivity.this, GameActivity.class);
                    startActivity(intentStartGame);
                    finish();
                    break;
                case 1:
                    Toast.makeText(getApplicationContext(), "Policy is not yet implemented", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    finishAffinity();
                    break;
            }
        }
    };
}