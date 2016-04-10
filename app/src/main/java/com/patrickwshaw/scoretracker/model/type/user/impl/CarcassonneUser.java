package com.patrickwshaw.scoretracker.model.type.user.impl;

import com.patrickwshaw.scoretracker.model.type.user.parent.User;
import com.patrickwshaw.scoretracker.utility.logging.LoggingUtil;
import com.patrickwshaw.scoretracker.view.fragment.carcassone.types.parent.CarcassonneScoreable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrick on 1/23/2015.
 */
public class CarcassonneUser extends User implements Serializable
{
    private List<CarcassonneScoreable> playerScoreables;

    private LoggingUtil logger = new LoggingUtil("CarcassoneUser", "CarcassoneUser");

    public CarcassonneUser()
    {
        this.playerScoreables = new ArrayList<CarcassonneScoreable>();
        logger.logEnter("constructor");
        logger.logExit("constructor");
    }

    public CarcassonneUser(String userName)
    {
        super(userName);
        logger.logEnter("contructorWithParams");
        this.playerScoreables = new ArrayList<CarcassonneScoreable>();
        logger.logExit("contructorWithParams");
    }

    public List<CarcassonneScoreable> getPlayerScoreables()
    {
        return playerScoreables;
    }

    public void setPlayerScoreables(List<CarcassonneScoreable> playerScoreables)
    {
        this.playerScoreables = playerScoreables;
    }

    public void addScorableToList(CarcassonneScoreable scoreable)
    {
        logger.logEnter("addScoreableToList");
        this.playerScoreables.add(scoreable);
        logger.logExit("addScoreableToList");
    }

    public boolean updateScoreableInList(CarcassonneScoreable scoreable) {
        logger.logEnter("updateScoreableInList");
        if (this.playerScoreables.contains(scoreable)) {
            logger.d("The list of player scoreables contains the passed in one - updating it!");
            int location = this.playerScoreables.indexOf(scoreable);
            this.playerScoreables.set(location, scoreable);
            logger.logExit("updateScoreableInList");
            return true;
        }
        else {
            logger.e("The list of player scoreables did NOT contain the passed in one - returning false");
            logger.logExit("updateScoreableInList");
            return false;
        }
    }
}
