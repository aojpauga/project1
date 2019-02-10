package com.example.project1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class showScore extends AppCompatActivity {
    private static final String EXTRA_SCORE =
            "com.example.project1.score";
    private static final String EXTRA_SCORE_SAVED =
           "com.example.project1.score_saved";

    private int mScore;
    private TextView mScoreTextView;
    private TextView mYouScoredTextView;
    private TextView mNumQuestions;
    private Button mSaveButton;

    public static Intent newIntent(Context packageContext, int score)
    {
        Intent intent = new Intent(packageContext, showScore.class);
        intent.putExtra(EXTRA_SCORE, score);
        return intent;
    }
    public static boolean scoreWasSaved(Intent result){
        return result.getBooleanExtra(EXTRA_SCORE_SAVED, false);
    }
    public static int scoreNumber(Intent result){
        return result.getIntExtra(EXTRA_SCORE, 0);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_score);

        mScore = getIntent().getIntExtra(EXTRA_SCORE, 0);

        mScoreTextView = (TextView) findViewById(R.id.score_text_view);
        mScoreTextView.setText(String.valueOf(mScore));

        mYouScoredTextView = (TextView) findViewById(R.id.you_scored_text_view);
        mYouScoredTextView.setText(R.string.you_scored_TextView);

        mNumQuestions = (TextView) findViewById(R.id.num_questions_TextView);
        mNumQuestions.setText(R.string.num_questions);


        mSaveButton = (Button) findViewById(R.id.save_button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setScore(mScore);
            }
        });
    }

    private void setScore(int score){
        Intent data = new Intent();
        data.putExtra(EXTRA_SCORE, score);
        data.putExtra(EXTRA_SCORE_SAVED, true);
        setResult(RESULT_OK, data);
    }
}

