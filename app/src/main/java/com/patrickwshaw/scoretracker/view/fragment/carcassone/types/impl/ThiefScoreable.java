package com.patrickwshaw.scoretracker.view.fragment.carcassone.types.impl;

import com.patrickwshaw.scoretracker.utility.game.CarcassonneUtil;
import com.patrickwshaw.scoretracker.utility.logging.LoggingUtil;
import com.patrickwshaw.scoretracker.view.fragment.carcassone.types.parent.CarcassonneScoreable;

/**
 * Created by Patrick on 1/23/2015.
 */
public class ThiefScoreable extends CarcassonneScoreable
{
    private boolean hasLake;

    private final int POINTS_FOR_SPACE = 1;
    private final int POINTS_FOR_LAKE_SPACE = 2;

    private LoggingUtil logger = new LoggingUtil("TheifScoreable", "TheifScoreable");

    private int numSpaces;

    public int getNumSpaces()
    {
        return numSpaces;
    }

    public void setNumSpaces(int numSpaces)
    {
        this.numSpaces = numSpaces;
    }

    public ThiefScoreable()
    {
        logger.logEnter("Constructor");
        hasLake = false;
        this.setType(CarcassonneUtil.carcassonActionTypes.THIEF);
        logger.logExit("Constructor");
    }

    public ThiefScoreable(boolean inEndGame, boolean hasLake)
    {
        super(inEndGame);
        logger.logEnter("ConstructorWithParams");
        this.hasLake = hasLake;
        logger.logExit("ConstructorWithParams");

    }

    public boolean getHasLake()
    {
        return hasLake;
    }

    public void setHasLake(boolean hasLake)
    {
        this.hasLake = hasLake;
    }

    public int setHasLakeAndCalculateScore(boolean hasLake) {
        logger.logEnter("setHasLakeAndCalculateScore");

        this.hasLake = hasLake;

        calculateAndUpdateScore();

        logger.logExit("setHasLakeAndCalculateScore");
        return this.getScore();
    }

    public int setNumberAndCalculateScore(int inNumber)
    {
        logger.logEnter("setNumbAndGetScore");

        numSpaces = inNumber;

        calculateAndUpdateScore();

        logger.logExit("setNumbAndGetScore");
        return this.getScore();
    }

    protected void calculateAndUpdateScore() {
        logger.logEnter("calculateAndUpdateScore");
        if (this.hasLake)
        {
            if (this.getEndGame())
            {
                logger.d("Has a lake and is the end of the game - NO POINTS FOR YOU");
                this.setScore(0);
            }
            else
            {
                this.setScore(numSpaces * POINTS_FOR_LAKE_SPACE);
            }
        }
        else
        {
            this.setScore(numSpaces * POINTS_FOR_SPACE);
        }
        logger.logExit("calculateAndUpdateScore");
    }

    public String getDisplayText()
    {
        logger.logEnter("getDisplayText");
        if (hasLake)
        {
            logger.logExit("getDisplayText");
            return "Thief thiefing " + numSpaces + " tile road with a lake";
        }
        else
        {
            logger.logExit("getDisplayText");
            return "Thief thiefing " + numSpaces + " tile road";
        }
    }
}
