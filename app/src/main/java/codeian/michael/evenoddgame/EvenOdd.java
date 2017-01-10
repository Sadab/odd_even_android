package codeian.michael.evenoddgame;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class EvenOdd extends Activity {
    TextView randText;
    Button btnOdd;
    Button btnEven;
    TextView scoreText;
    TextView timeRemainText;
    TextView highText;
    TextView scoreMainText;
    TextView highMainText;
    TextView timer;
    CountDownTimer tmr;
    int randomNumber;
    Random generator;
    DatabaseConnect myDB;
    int finalScore = 0;
    int highScore;
    final int RANDOM_LOWER_BOUND = 1;
    final int RANDOM_UPPER_BOUND = 1000;
    int time = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_even_odd);
        myDB = new DatabaseConnect(this);
        generator = new Random();
        timer = (TextView) findViewById(R.id.timer);
        scoreMainText = (TextView) findViewById(R.id.scoreMainText);
        highMainText = (TextView) findViewById(R.id.highMainText);
        scoreText = (TextView) findViewById(R.id.scoreText);
        timeRemainText = (TextView) findViewById(R.id.timeRemainText);
        highText = (TextView) findViewById(R.id.highText);
        randText = (TextView) findViewById(R.id.randText);
        btnOdd = (Button) findViewById(R.id.btnOdd);
        btnEven = (Button) findViewById(R.id.btnEven);
        displayNewNumber();
        listeners();
        gameTimer();
    }

    public void displayNewNumber(){
        int tempOldRand = randomNumber;
        randomNumber = generator.nextInt(RANDOM_UPPER_BOUND) + RANDOM_LOWER_BOUND;
        while(randomNumber == tempOldRand){
            randomNumber = generator.nextInt(RANDOM_UPPER_BOUND);
        }
        randText.setText(Integer.toString(randomNumber));
    }

    public void listeners(){
        btnOdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(randomNumber%2 != 0){
                    displayNewNumber();
                    updateScore();
                }
                else {
                    gameOver();
                }
            }
        });

        btnEven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(randomNumber%2 == 0){
                    displayNewNumber();
                    updateScore();
                }
                else {
                    gameOver();
                }
            }
        });

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void updateScore(){
        finalScore++;
        scoreMainText.setText(Integer.toString(finalScore));
        myDB.insertData(scoreMainText.getText().toString());

    }

    public void gameTimer(){
       tmr = new CountDownTimer(32000,1000) {
           @Override
           public void onTick(long l) {
               timer.setText(Integer.toString(time));
               time--;
           }
           @Override
           public void onFinish() {
                    alert();
           }
       }.start();
    }

    public void alert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(EvenOdd.this);
        builder.setMessage("Play Again?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                time = 30;
                scoreMainText.setText(Integer.toString(0));
                finalScore = 0;
                displayNewNumber();
                tmr.start();

            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent duckIt = new Intent(EvenOdd.this,Home.class);
                startActivity(duckIt);
            }
        });
        AlertDialog alert = builder.create();
        alert.setTitle("Time's up!");
        alert.show();
    }

    public void gameOver(){
        AlertDialog.Builder builder = new AlertDialog.Builder(EvenOdd.this);
        builder.setMessage("Your answer is wrong").setCancelable(false).setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent duckIt = new Intent(EvenOdd.this,Home.class);
                startActivity(duckIt);
            }
        });
        AlertDialog alert = builder.create();
        alert.setTitle("Wrong Answer!");
        alert.show();
    }

}


