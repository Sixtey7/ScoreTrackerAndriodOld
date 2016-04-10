package com.patrickwshaw.scoretracker.model.type.score.impl;

import com.patrickwshaw.scoretracker.model.type.score.parent.Score;

/**
 * Created by Patrick on 1/8/2015.
 */
public class SingleScore extends Score
{
    @Override
    public int calculateScoreForNumber(int number)
    {
        return number;
    }
}
