package android.satoripop.com.topquiz.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.satoripop.com.topquiz.Model.Question;
import android.satoripop.com.topquiz.Model.QuestionBank;
import android.satoripop.com.topquiz.R;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mQuestionText;
    private Button mAnswer1,mAnswer2,mAnswer3,mAnswer4;
    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;
    private int mNumberOfQuestions;
    private int mScore=0;
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    private boolean mEnableTouchEvents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mQuestionText= findViewById(R.id.activity_game_question_text);
        mAnswer1= findViewById(R.id.activity_game_answer1_btn);
        mAnswer2= findViewById(R.id.activity_game_answer2_btn);
        mAnswer3= findViewById(R.id.activity_game_answer3_btn);
        mAnswer4= findViewById(R.id.activity_game_answer4_btn);
        mEnableTouchEvents = true;

//Remlpissage Des Questions
        mQuestionBank = this.generateQuestions();
        mNumberOfQuestions = 4;
        mCurrentQuestion=mQuestionBank.getQuestion();
        displayQuestion(mCurrentQuestion);

        // Use the tag property to 'name' the buttons
        mAnswer1.setTag(0);
        mAnswer2.setTag(1);
        mAnswer3.setTag(2);
        mAnswer4.setTag(3);
        mAnswer1.setOnClickListener(this);
        mAnswer2.setOnClickListener(this);
        mAnswer3.setOnClickListener(this);
        mAnswer4.setOnClickListener(this);

    }

    private void displayQuestion(final Question question) {
        // Set the text for the question text view and the four buttons
        mQuestionText.setText(question.getQuestion());
        mAnswer1.setText(question.getChoiceList().get(0));
        mAnswer2.setText(question.getChoiceList().get(1));
        mAnswer3.setText(question.getChoiceList().get(2));
        mAnswer4.setText(question.getChoiceList().get(3));

    }

    private QuestionBank generateQuestions() {

        Question question1 = new Question("Who is the creator of Android?",
                Arrays.asList("Andy Rubin",
                        "Steve Wozniak",
                        "Jake Wharton",
                        "Paul Smith"),
                0);

        Question question2 = new Question("When did the first man land on the moon?",
                Arrays.asList("1958",
                        "1962",
                        "1967",
                        "1969"),
                3);

        Question question3 = new Question("What is the house number of The Simpsons?",
                Arrays.asList("42",
                        "101",
                        "666",
                        "742"),
                3);
        Question question4 = new Question("Who is the creator of this Application?",
                Arrays.asList("El PAPPI",
                        "Nogo Jari",
                        "Bolbol Moulinex",
                        "Sabri Las Vegas"),
                3);

        return new QuestionBank(Arrays.asList(question1,
                question2,
                question3,question4));

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

            return mEnableTouchEvents && super.dispatchTouchEvent(ev);

    }

    @Override
    public void onClick(View v) {
        //Get Button Clicked !
        int responseIndex = (int) v.getTag();

        if (responseIndex == mCurrentQuestion.getAnswerIndex()) {
            // Good answer
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            mScore++;
        } else {
            // Wrong answer
            Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show();
        }

        mEnableTouchEvents = false;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mEnableTouchEvents = true;

                // If this is the last question, ends the game.
                // Else, display the next question.
                if (--mNumberOfQuestions == 0) {
                    // End the game
                    endGame();
                } else {
                    mCurrentQuestion = mQuestionBank.getQuestion();
                    displayQuestion(mCurrentQuestion);
                }
            }
        }, 2000); // LENGTH_SHORT is usually 2 second long



        if (--mNumberOfQuestions == 0) {
            // No question left, end the game
           endGame();
        } else {
            mCurrentQuestion = mQuestionBank.getQuestion();
            displayQuestion(mCurrentQuestion);
        }

    }

    private void endGame() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);

        builder.setTitle("Well done!")
                .setMessage("Your score is " + mScore)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // End the activity
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }


}


