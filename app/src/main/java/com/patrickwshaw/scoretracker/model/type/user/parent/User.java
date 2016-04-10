package com.patrickwshaw.scoretracker.model.type.user.parent;

import java.io.Serializable;

/**
 * Created by Patrick on 1/11/2015.
 */
public class User implements Serializable
{
    private String playerName;
    private int totalScore;

    public User()
    {
        this.playerName = "";
        this.totalScore = 0;
    }

    public User(String playerName)
    {
        this.playerName = playerName;
        this.totalScore = 0;
    }

    public User(String playerName, int totalScore)
    {
        this.playerName = playerName;
        this.totalScore = totalScore;
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    public int getTotalScore()
    {
        return totalScore;
    }

    public void setTotalScore(int totalScore)
    {
        this.totalScore = totalScore;
    }
}
