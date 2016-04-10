package com.patrickwshaw.scoretracker.view.fragment.carcassone.types.impl;

import android.widget.CheckBox;

import com.patrickwshaw.scoretracker.utility.game.CarcassonneUtil;
import com.patrickwshaw.scoretracker.utility.logging.LoggingUtil;
import com.patrickwshaw.scoretracker.view.fragment.carcassone.types.parent.CarcassonneScoreable;

/**
 * Created by Patrick on 1/23/2015.
 */
public class KnightScoreable extends CarcassonneScoreable
{
    private LoggingUtil logger = new LoggingUtil("KnightScoreable", "KnightScoreable");

    private int POINTS_PER_TILE = 2;
    private int POINTS_PER_FLAG = 2;

    private int POINTS_PER_TILE_END = 1;
    private int POINTS_PER_FLAG_END = 1;

    private int POINTS_PER_TILE_CATH = 3;
    private int POINTS_PER_FLAG_CATH = 3;

    private boolean hasCath;
    private int numSpaces;
    private int numFlags;

    public KnightScoreable()
    {
        super();
        logger.logEnter("Constructor");
        this.setType(CarcassonneUtil.carcassonActionTypes.KNIGHT);
        logger.logExit("Constructor");
    }

    public KnightScoreable(boolean inEndGame, boolean hasCath)
    {
        super(inEndGame);
        logger.logEnter("ConstructorWithParams");

        this.hasCath = hasCath;
        logger.logExit("ConstructorWithParams");

    }

    public KnightScoreable(boolean inEndGame, boolean hasCath, CheckBox hasCathWidget) {
        super(inEndGame);

    }

    public boolean isHasCath()
    {
        return hasCath;
    }

    public void setHasCath(boolean hasCath)
    {
        this.hasCath = hasCath;
    }

    public int setHasCathAndCalculateScore(boolean hasCath) {
        logger.logEnter("setHasCathAndCalculateScore");
        this.hasCath = hasCath;

        calculateAndUpdateScore();

        logger.logExit("setHasCathAndCalculateScore");
        return this.getScore();
    }

    public int getNumSpaces()
    {
        return numSpaces;
    }

    public void setNumSpaces(int numSpaces)
    {
        this.numSpaces = numSpaces;
    }

    public int getNumFlags()
    {
        return numFlags;
    }

    public void setNumFlags(int numFlags)
    {
        this.numFlags = numFlags;
    }

    public int setNumberAndCalculateScore(int numSpaces, int numFlags)
    {
        logger.logEnter("setNumAndGetScore");

        this.numSpaces = numSpaces;
        this.numFlags = numFlags;

        calculateAndUpdateScore();

        logger.logExit("setNumAndGetScore");
        return this.getScore();
    }

    public String getDisplayText()
    {
        logger.logEnter("getDisplayText");
        if (hasCath)
        {
            logger.logExit("getDisplayText");
            return "Knight In " + numSpaces + " Space City with " + numFlags + " Banners and a Cathedral";
        }
        else
        {
            logger.logExit("getDisplayText");
            return "Knight In " + numSpaces + " Space City with " + numFlags + " Banners";
        }
    }

    @Override
    protected void calculateAndUpdateScore() {
        if (hasCath)
        {
            logger.d("The knight has a cath");
            if (this.getEndGame()) {
                logger.d("It is the end game and the player has a cath - no points for them!");
                this.setScore(0);
            }
            else {
                this.setScore((this.numSpaces * POINTS_PER_TILE_CATH) + (this.numFlags * POINTS_PER_FLAG_CATH));
            }

        }
        else {
            logger.d("No cath");
            if (this.getEndGame()) {
                this.setScore((this.numSpaces * POINTS_PER_TILE_END) + (this.numFlags * POINTS_PER_FLAG_END));
            } else {
                this.setScore((this.numSpaces * POINTS_PER_TILE) + (this.numFlags * POINTS_PER_FLAG));
            }
        }
    }
}
