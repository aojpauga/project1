package com.example.project1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button mNextButton;
    private EditText mAnswerInput;
    private TextView mQuestionTextview;
    private int mScore = 0;
    private int mHighScore = 0;
    private boolean mScoreSaved;
    private TextView mHighScoreTextView;
    private static final int REQUEST_CODE_SCORE = 0;
    private static final Random random = new Random();

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.add_question, 4),
            new Question(R.string.subtract_question, 2),
            new Question(R.string.multiply_question, 10),
            new Question(R.string.divide_question, 50),
            new Question(R.string.add_question_2, 12),
            new Question(R.string.subtract_question_2, 6),
            new Question(R.string.multiply_question_2, 36),
            new Question(R.string.divide_question_2, 10),
            new Question(R.string.divide_question_3, 10),
            new Question(R.string.divide_question_4, 20)
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHighScoreTextView = (TextView) findViewById(R.id.high_score_text_view);

        mQuestionTextview = (TextView) findViewById(R.id.question_text_view);
        int question = mQuestionBank[mCurrentIndex].getTextResID();
        mQuestionTextview.setText(question);

        mAnswerInput = (EditText)findViewById(R.id.input_answer);


        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //checkAnswer()

                int messageResId = 0;

                if (mAnswerInput.getText().toString().isEmpty()){
                    messageResId = R.string.null_entry;
                } else { // user typed an answer
                    int userAnswer = Integer.valueOf(mAnswerInput.getText().toString());
                    int correctAnswer = mQuestionBank[mCurrentIndex].getAnswer();

                    if (userAnswer == correctAnswer) {
                        messageResId = R.string.dont_give_up;
                        mScore += 1;
                        mCurrentIndex += 1;
                        mAnswerInput.getText().clear();
                    } else {
                        messageResId = R.string.you_got_this_toast;
                        mCurrentIndex += 1;
                        mAnswerInput.getText().clear();
                    }

                }

                Toast.makeText(getApplicationContext(), messageResId, Toast.LENGTH_SHORT).show();
                Log.d("p1", "the score is now " + mScore);

                if (mCurrentIndex >= mQuestionBank.length) {
                    // all done, go to the next screen
                    Intent intent = showScore.newIntent(MainActivity.this, mScore);
                    startActivityForResult(intent, REQUEST_CODE_SCORE);
                    Log.d("p1", "DONE! Advance. Score is: " + mScore);
                } else {
                    updateQuestion();
                    if (mCurrentIndex == mQuestionBank.length - 1) {
                        // last question!
                        // update button text to SUBMIT
                        mNextButton.setText(R.string.submit_button);
                        Log.d("p1", "Almost done. Last question.");
                    }
                }
            }
        });
        updateQuestion();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mScoreSaved) {
            Log.d("p1", "was score saved? " + mScoreSaved);
            mCurrentIndex = 0;
            int question = mQuestionBank[mCurrentIndex].getTextResID();
            mQuestionTextview.setText(question);
            mNextButton.setText(R.string.next_button);
            mHighScoreTextView.setText("Your High Score is " + String.valueOf(mHighScore) + "!");
        }
    }

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getTextResID();
        mQuestionTextview.setText(question);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQUEST_CODE_SCORE){
            if(data == null){
                return;
            }
            mHighScore = showScore.scoreNumber(data);
            mScoreSaved = showScore.scoreWasSaved(data);
            Log.d("p1", "High score is " + mHighScore);
        }
    }
}
