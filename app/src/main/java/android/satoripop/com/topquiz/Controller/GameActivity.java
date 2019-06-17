package android.satoripop.com.topquiz.Controller;

import android.os.Bundle;
import android.satoripop.com.topquiz.Model.Question;
import android.satoripop.com.topquiz.Model.QuestionBank;
import android.satoripop.com.topquiz.R;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity {


    private TextView QuestionText;
    private Button Answer1,Answer2,Answer3,Answer4;
    private QuestionBank mQuestionBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        QuestionText= findViewById(R.id.activity_game_question_text);
        Answer1= findViewById(R.id.activity_game_answer1_btn);
        Answer2= findViewById(R.id.activity_game_answer2_btn);
        Answer3= findViewById(R.id.activity_game_answer3_btn);
        Answer4= findViewById(R.id.activity_game_answer4_btn);
        //Remlpissage Des Questions
        mQuestionBank = this.generateQuestions();





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

        return new QuestionBank(Arrays.asList(question1,
                question2,
                question3));

    }
}
