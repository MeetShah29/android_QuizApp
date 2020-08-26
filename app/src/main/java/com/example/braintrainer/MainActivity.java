package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton, button1, button2, button3, button4,playAgainBtn;
    Random randomNumber = new Random();
    TextView sumTextView, answerTxtView, pointsTxtView, timerTxt;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswers;
    int score = 0;
    int numberOfQuestions = 0;
    ConstraintLayout gameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = findViewById(R.id.goBtn);
        answerTxtView = findViewById(R.id.answerTextView);
        sumTextView = findViewById(R.id.sumTextView);
        pointsTxtView = findViewById(R.id.pointsTextView);
        timerTxt = findViewById(R.id.timerTextView);
        playAgainBtn=findViewById(R.id.playAgainBtn);
        gameLayout=findViewById(R.id.gameLayout);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

    }

    public void goClick(View view) {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        onPlayAgain(findViewById(R.id.playAgainBtn));
    }

    public void chooseAnswer(View view) {
        Log.i("Tag", String.valueOf(view.getTag()));
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswers))) {
            score++;
            answerTxtView.setText("Correct");
        } else {
            answerTxtView.setText("Wrong");
        }
        // Below lines will be executed every time user chooses an answer doesn't matter wether it is right or wrong
        numberOfQuestions++;
        pointsTxtView.setText(score + "/" + numberOfQuestions);
        generateQuestion();


    }

    public void generateQuestion() {
        int a = randomNumber.nextInt(21);
        int b = randomNumber.nextInt(21);
        sumTextView.setText(a + " + " + b);

        int inCorrectAnswer;

        locationOfCorrectAnswers = randomNumber.nextInt(4);
        answers.clear();

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswers) {
                answers.add(a + b);
            } else {
                inCorrectAnswer = randomNumber.nextInt(41);

                /* while loop will keep on going till the time inCorrectAnswer is equal to the correct answer i.e(a+b) which is generated,
                 and if it is true then we will generate the inCorrectAnswer
                 */
                while (inCorrectAnswer == a + b) {

                    inCorrectAnswer = randomNumber.nextInt(41);

                }
                answers.add(inCorrectAnswer);
            }
        }

        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));
    }

    public void onPlayAgain(View view){
        score=0;
        numberOfQuestions=0;

        timerTxt.setText("0s");
        pointsTxtView.setText("0/0");
        answerTxtView.setText("");
        playAgainBtn.setVisibility(View.INVISIBLE);
        generateQuestion();

        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                timerTxt.setText(String.valueOf(l / 1000)+"s");
            }

            @Override
            public void onFinish() {
                timerTxt.setText("0s");
                answerTxtView.setText("Your score is: "+score + "/" + numberOfQuestions);
                playAgainBtn.setVisibility(View.VISIBLE);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);

            }
        }.start();
    }

}