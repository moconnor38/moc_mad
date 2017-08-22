package com.example.mark.geoquiz;

/**
 * Created by marko on 21/08/2017.
 */

public class Question {
    private int mTextResID;
    private boolean mAnswerTrue;

    public Question (int textResID, boolean answerTrue) {
        mTextResID = textResID;
        mAnswerTrue = answerTrue;
    }

    public int getTextResID() {
        return mTextResID;
    }

    public void setTextResID(int mTextResID) {
        this.mTextResID = mTextResID;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean mAnswerTrue) {
        this.mAnswerTrue = mAnswerTrue;
    }
}
