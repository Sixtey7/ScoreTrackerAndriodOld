package com.patrickwshaw.scoretracker.model.type.score.type;

/**
 * Created by Patrick on 1/3/2015.
 */
public class ScoreRange
{
    private int scoreValue;
    private int rangeValue;

    public ScoreRange(int rangeValue, int scoreValue)
    {
        this.rangeValue = rangeValue;
        this.scoreValue = scoreValue;
    }

    public int getScoreValue()
    {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue)
    {
        this.scoreValue = scoreValue;
    }

    public int getRangeValue()
    {
        return rangeValue;
    }

    public void setRangeValue(int rangeValue)
    {
        this.rangeValue = rangeValue;
    }
}
