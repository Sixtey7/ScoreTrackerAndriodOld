package com.patrickwshaw.scoretracker.utility.general;

import android.app.Activity;

import com.patrickwshaw.scoretracker.R;
import com.patrickwshaw.scoretracker.model.type.game.parent.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrick on 1/22/2015.
 */
public class GameUtil
{
    public static enum GAME_TYPES
    {
        AGRICOLA,
        CARCASSONNE;
    }

    private static GAME_TYPES selectedGameType;

    public static List<Game> getGameList(Activity activity)
    {
        List<Game> returnList = new ArrayList<Game>(2);
        returnList.add(new Game(activity.getString(R.string.agricolaGameName), GAME_TYPES.AGRICOLA));
        returnList.add(new Game(activity.getString(R.string.carcassonneGameName), GAME_TYPES.CARCASSONNE));

        return returnList;
    }

    public static void setSelectedGameType(GAME_TYPES selectedGameType)
    {
        GameUtil.selectedGameType = selectedGameType;
    }

    public static GAME_TYPES getSelectedGameType()
    {
        return selectedGameType;
    }
}
