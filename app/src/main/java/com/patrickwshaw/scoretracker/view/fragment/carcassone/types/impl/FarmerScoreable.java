package com.patrickwshaw.scoretracker.view.fragment.carcassone.types.impl;

import com.patrickwshaw.scoretracker.utility.game.CarcassonneUtil;
import com.patrickwshaw.scoretracker.utility.logging.LoggingUtil;
import com.patrickwshaw.scoretracker.view.fragment.carcassone.types.parent.CarcassonneScoreable;

/**
 * Created by Patrick on 1/23/2015.
 */
public class FarmerScoreable extends CarcassonneScoreable
{
    private final int POINTS_PER_SPACE = 3;

    private int numCities;

    private LoggingUtil logger = new LoggingUtil("FarmerScoreable", "FarmerScoreable");

    public FarmerScoreable() {
        super();
        numCities = 0;
        this.setType(CarcassonneUtil.carcassonActionTypes.FARMER);
    }

    public int getNumCities() { return numCities; }

    public void setNumCities(int numCities) { this.numCities = numCities; }

    public int setNumberAndCalculateScore(int inNumber)
    {
        logger.logEnter("setNumbAndGetScore");

        numCities = inNumber;

        calculateAndUpdateScore();

        logger.logExit("setNumbAndGetScore");
        return this.getScore();
    }

    public String getDisplayText()
    {
        logger.logEnter("getDisplayText");
        logger.logExit("getDisplayText");
        return "Farmer Impacting " + numCities + " Cities";
    }

    @Override
    protected void calculateAndUpdateScore() {
        logger.logEnter("calculateAndUpdateScore");
        this.setScore(numCities * POINTS_PER_SPACE);
        logger.logExit("calculateAndUpdateScore");
    }

}
