package com.example.project1;

public class Question {
    private int mTextResID;
    private int mAnswer;

    public int getTextResID() {
        return mTextResID;
    }

    public void setTextResID(int textResID) {
        mTextResID = textResID;
    }

    public int getAnswer() {
        return mAnswer;
    }

    public void setAnswer(int answer) {
        mAnswer = answer;
    }


    public Question(int textResID, int answer){
        mTextResID = textResID;
        mAnswer = answer;
    }
}
