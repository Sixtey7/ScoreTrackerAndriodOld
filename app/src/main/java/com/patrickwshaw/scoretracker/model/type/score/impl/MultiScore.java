package com.patrickwshaw.scoretracker.model.type.score.impl;

import com.patrickwshaw.scoretracker.model.type.score.parent.Score;

/**
 * Created by Patrick on 1/3/2015.
 */
public class MultiScore extends Score
{
    private int value;

    public MultiScore(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    @Override
    public int calculateScoreForNumber(int number)
    {
        return value * number;
    }
}