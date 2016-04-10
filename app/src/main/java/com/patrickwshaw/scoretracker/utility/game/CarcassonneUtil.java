package com.patrickwshaw.scoretracker.utility.game;

import android.widget.ArrayAdapter;

import com.patrickwshaw.scoretracker.model.type.user.impl.CarcassonneUser;
import com.patrickwshaw.scoretracker.utility.logging.LoggingUtil;
import com.patrickwshaw.scoretracker.view.fragment.carcassone.types.parent.CarcassonneScoreable;

/**
 * Created by Patrick on 1/24/2015.
 */
public class CarcassonneUtil
{
    private static ArrayAdapter<CarcassonneScoreable> carcassonneAdapter;

    public static final String CURRENT_ACTION_TAG = "com.patrickwshaw.scoretracker.utility.game.CarcassoneUtil.currentactiontag";

    private static LoggingUtil logger = new LoggingUtil("UserUtil", "UserUtil");

    public enum carcassonActionTypes {
        FARMER,
        KNIGHT,
        MONK,
        THIEF
    }

    public static ArrayAdapter<CarcassonneScoreable> getCarcassonneAdapter()
    {
        return carcassonneAdapter;
    }

    public static void setCarcassonneAdapter(ArrayAdapter<CarcassonneScoreable> carcassonneAdapter)
    {
        CarcassonneUtil.carcassonneAdapter = carcassonneAdapter;
    }

    public static int calculateAndSetTotalScore(CarcassonneUser user) {
        logger.logEnter("calculateAndSetTotalScore");

        int scoreToSet = calculateTotalScore(user);

        user.setTotalScore(scoreToSet);

        logger.logExit("calculateAndSetTotalScore");
        return scoreToSet;
    }

    private static int calculateTotalScore(CarcassonneUser user) {
        logger.logEnter("calculateTotalScore");
        int returnScore = 0;

        for (CarcassonneScoreable thisScoreable : user.getPlayerScoreables()) {
            returnScore += thisScoreable.getScore();
        }


        logger.logExit("calculateTotalScore");
        return returnScore;
    }
}
