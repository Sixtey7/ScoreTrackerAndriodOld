package com.patrickwshaw.scoretracker.view.fragment.carcassone.types.impl;

import com.patrickwshaw.scoretracker.utility.game.CarcassonneUtil;
import com.patrickwshaw.scoretracker.utility.logging.LoggingUtil;
import com.patrickwshaw.scoretracker.view.fragment.carcassone.types.parent.CarcassonneScoreable;

/**
 * Created by Patrick on 1/23/2015.
 */
public class MonkScoreable extends CarcassonneScoreable
{
    private final int POINTS_PER_SPACE = 1;

    private LoggingUtil logger = new LoggingUtil("MonkScoreable", "MonkScoreable");

    private int numSpaces;

    public int getNumSpaces()
    {
        return numSpaces;
    }

    public void setNumSpaces(int numSpaces)
    {
        this.numSpaces = numSpaces;
    }

    public MonkScoreable() {
        super();
        logger.logEnter("Constructor");
        this.setType(CarcassonneUtil.carcassonActionTypes.MONK);
        logger.logExit("Constructor");
    }
    public int setNumberAndCalculateScore(int inNumber)
    {
        logger.logEnter("setNumberAndCalculateScore");
        numSpaces = inNumber;

        calculateAndUpdateScore();

        logger.logExit("setNumberAndCalculateScore");
        return this.getScore();
    }

    public String getDisplayText()
    {
        logger.logEnter("getDisplayText");
        logger.logExit("getDisplayText");

        return "Monk Impacting " + numSpaces + " tiles";
    }

    @Override
    protected void calculateAndUpdateScore() {
        logger.logEnter("calculateAndUpdateScore");
        this.setScore(numSpaces * POINTS_PER_SPACE);
        logger.logExit("calculateAndUpdateScore");
    }
}
