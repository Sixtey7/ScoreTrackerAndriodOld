package com.patrickwshaw.scoretracker.view.fragment.agricola.types;

import android.widget.EditText;
import android.widget.TextView;

import com.patrickwshaw.scoretracker.model.type.score.parent.Score;

/**
 * Created by Patrick on 1/3/2015.
 */
public class ScoreItem
{
    private Score score;
    private EditText numberWidget;
    private int number;
    private TextView scoreWidget;

    public ScoreItem(Score score, int number, EditText numberWidget, TextView scoreWidget)
    {
        this.score = score;
        this.number = number;
        this.numberWidget = numberWidget;
        this.scoreWidget = scoreWidget;

        this.numberWidget.setText(Integer.toString(this.number));
        this.scoreWidget.setText(Integer.toString(this.score.calculateScoreForNumber(this.number)));
    }

    public Score getScore()
    {
        return score;
    }

    public void setScore(Score score)
    {
        this.score = score;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
        this.numberWidget.setText(Integer.toString(this.number));
    }

    public void setNumberAndCalculateScore(int number)
    {
        this.number = number;
        int scoreValue = score.calculateScoreForNumber(this.number);

        numberWidget.setText(Integer.toString(number));
        scoreWidget.setText(Integer.toString(scoreValue));
    }

    public TextView getScoreWidget() { return scoreWidget; }

    public void setScoreWidget(TextView scoreWidget) { this.scoreWidget = scoreWidget; }

    public int calculateScore()
    {
        return score.calculateScoreForNumber(number);
    }

    public int calculateAndSetScore()
    {
        int scoreValue = score.calculateScoreForNumber(number);
        numberWidget.setText(Integer.toString(number));
        scoreWidget.setText(Integer.toString(scoreValue));

        return scoreValue;
    }

    public int calculateAndSetScore(int number)
    {
        this.number = number;
        int scoreValue = score.calculateScoreForNumber(this.number);

        numberWidget.setText(Integer.toString(number));
        scoreWidget.setText(Integer.toString(scoreValue));

        return scoreValue;
    }
    public void setNumberWidget(EditText numberWidget) { this.numberWidget = numberWidget; }

    public EditText getNumberWidget() { return  this.numberWidget; }
}
