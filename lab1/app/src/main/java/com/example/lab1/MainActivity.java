package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public TextView text;
    public TextView youScores;
    public TextView otherScores;
    public Button button;
    public Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.textView);
        youScores = findViewById(R.id.youcube);
        otherScores = findViewById(R.id.othercube);
        button = findViewById(R.id.button);
    }

    public void dropCubes(View view){
        button.setText("Бросить кубики!");
        int cubic1 =random.nextInt(6)+1;
        int cubic2=random.nextInt(6)+1;
        int cubic3 =random.nextInt(6)+1;
        int cubic4 =random.nextInt(6)+1;
        while (cubic3==cubic4) {
            cubic4 =random.nextInt(6)+1;
        }
        if (cubic1==cubic2) {
            text.setText(String.valueOf(cubic1)+" и "+String.valueOf(cubic2)+"\n Выпал дубль!");
            button.setText("Перебросить кубики!");
        } else {
            text.setText(String.valueOf(cubic1)+" и "+String.valueOf(cubic2));
            int numOfYou = Integer.parseInt(youScores.getText().toString());
            youScores.setText(String.valueOf(cubic1+cubic2+numOfYou));
            int numOfOther = Integer.parseInt(otherScores.getText().toString());
            otherScores.setText(String.valueOf(cubic3+cubic4+numOfOther));
            if ((Integer.parseInt(youScores.getText().toString())>=100) & (Integer.parseInt(youScores.getText().toString())>Integer.parseInt(otherScores.getText().toString()))) {
                text.setText("Брось кубики и набери 100 очков раньше соперника!");
                youScores.setText("0");
                otherScores.setText("0");
                button.setText("Бросить кубики!");
                Intent intent= new Intent(this, Winner.class);
                startActivity(intent);
            } else
            if ((Integer.parseInt(otherScores.getText().toString())>=100) & (Integer.parseInt(otherScores.getText().toString())>Integer.parseInt(youScores.getText().toString()))) {
                text.setText("Брось кубики и набери 100 очков раньше соперника!");
                youScores.setText("0");
                otherScores.setText("0");
                button.setText("Бросить кубики!");
                Intent intent= new Intent(this, Looser.class);
                startActivity(intent);

            } else
            if ((Integer.parseInt(youScores.getText().toString())>=100) & (Integer.parseInt(youScores.getText().toString())==Integer.parseInt(otherScores.getText().toString()))) {
                text.setText("Брось кубики и набери 100 очков раньше соперника!");
                youScores.setText("0");
                otherScores.setText("0");
                button.setText("Бросить кубики!");
                Intent intent= new Intent(this, Normal.class);
                startActivity(intent);
            }
        }
    }

}
