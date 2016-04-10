package com.patrickwshaw.scoretracker.view.fragment.carcassone.types.parent;

import com.patrickwshaw.scoretracker.utility.game.CarcassonneUtil;

import java.io.Serializable;

/**
 * Created by Patrick on 1/23/2015.
 */
public abstract class CarcassonneScoreable implements Serializable
{
    private int score;
    private boolean endGame;
    private CarcassonneUtil.carcassonActionTypes type;

    public CarcassonneScoreable()
    {
        endGame = false;
        score = 0;
    }

    public CarcassonneScoreable(boolean endGame)
    {
        this.endGame = endGame;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public boolean getEndGame()
    {
        return endGame;
    }

    public void setEndGame(boolean endGame)
    {
        this.endGame = endGame;
    }

    public CarcassonneUtil.carcassonActionTypes getType() {
        return type;
    }

    public void setType(CarcassonneUtil.carcassonActionTypes type) {
        this.type = type;
    }

    public int setEndGameAndCalculateScore(boolean endGame) {
        this.endGame = endGame;
        calculateAndUpdateScore();

        return this.getScore();
    }

    public abstract String getDisplayText();

    protected abstract void calculateAndUpdateScore();
}
