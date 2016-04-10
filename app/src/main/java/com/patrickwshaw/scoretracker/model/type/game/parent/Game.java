package com.patrickwshaw.scoretracker.model.type.game.parent;

import com.patrickwshaw.scoretracker.utility.general.GameUtil;

import java.io.Serializable;

/**
 * Created by Patrick on 1/23/2015.
 */
public class Game implements Serializable
{
    private String gameName;
    private GameUtil.GAME_TYPES gameType;

    public Game(String gameName, GameUtil.GAME_TYPES gameType)
    {
        this.gameName = gameName;
        this.gameType = gameType;
    }

    public String getGameName()
    {
        return gameName;
    }

    public void setGameName(String gameName)
    {
        this.gameName = gameName;
    }

    public GameUtil.GAME_TYPES getGameType()
    {
        return gameType;
    }

    public void setGameType(GameUtil.GAME_TYPES gameType)
    {
        this.gameType = gameType;
    }
}
