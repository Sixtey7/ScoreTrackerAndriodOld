package com.patrickwshaw.scoretracker.model.type.score.impl;

import com.patrickwshaw.scoretracker.model.type.score.parent.Score;
import com.patrickwshaw.scoretracker.model.type.score.type.ScoreRange;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrick on 1/8/2015.
 */
public class RangeScore extends Score
{
    private List<ScoreRange> scoreRangeList;

    public List<ScoreRange> getScoreRangeList()
    {
        return scoreRangeList;
    }

    public void setScoreRangeList(List<ScoreRange> scoreRangeList)
    {
        this.scoreRangeList = scoreRangeList;
    }

    public RangeScore()
    {
        //default constructor
        this.scoreRangeList = new ArrayList<ScoreRange>();
    }

    public RangeScore(List<ScoreRange> inScoreRangeList)
    {
        this.scoreRangeList = inScoreRangeList;
    }

    public void addScoreRangeToList(ScoreRange scoreRangeToAdd)
    {
        if (!scoreRangeList.contains(scoreRangeToAdd))
        {
            //the list does not current contain the passed in object, add it
            scoreRangeList.add(scoreRangeToAdd);
        }
    }

    @Override
    public int calculateScoreForNumber(int number)
    {
        int smallestDifference = 9999;
        int currentScore = 0;

        for(ScoreRange thisScoreRange : scoreRangeList)
        {
            if (number == thisScoreRange.getRangeValue())
            {
                //we have an exact match, not going to get any closer than that
                return thisScoreRange.getScoreValue();
            }
            else if (number > thisScoreRange.getRangeValue())
            {
                if ((number - thisScoreRange.getRangeValue()) < smallestDifference)
                {
                    smallestDifference = number - thisScoreRange.getRangeValue();
                    currentScore = thisScoreRange.getScoreValue();
                }
            }
        }

        return currentScore;
    }
}
