package com.patrickwshaw.scoretracker.model.type.score.parent;

/**
 * Created by Patrick on 1/3/2015.
 */
public abstract class Score
{
    private String name;

    public abstract int calculateScoreForNumber(int number);

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }


}
