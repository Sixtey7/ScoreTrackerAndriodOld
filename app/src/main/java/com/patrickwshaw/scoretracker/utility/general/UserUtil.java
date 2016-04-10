package com.patrickwshaw.scoretracker.utility.general;

import android.widget.ArrayAdapter;

import com.patrickwshaw.scoretracker.model.type.user.impl.AgricolaUser;
import com.patrickwshaw.scoretracker.model.type.user.impl.CarcassonneUser;
import com.patrickwshaw.scoretracker.model.type.user.parent.User;
import com.patrickwshaw.scoretracker.utility.logging.LoggingUtil;
import com.patrickwshaw.scoretracker.view.util.DialogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrick on 1/14/2015.
 */
public class UserUtil
{
    /**
     * TAGs related to users
     */
    public static final String SELECTED_USER_TAG = "com.patrickwshaw.scoretracker.utility.general.userutil.selectedusertag";
    public static final String AGRICOLA_USER_TAG = "com.patrickwshaw.scoretracker.utility.general.userutil.agricolausertag";

    /**
     * General Global Variables
     */
    private static List<User> allUsers = new ArrayList<User>();

    private static ArrayAdapter<User> userAdapter;

    private static LoggingUtil logger = new LoggingUtil("UserUtil", "UserUtil");

    public static List<User> getAllUsers()
    {
        logger.logEnter("getAllUsers");
        logger.logExit("getAllUsers");
        return allUsers;
    }

    public static void addUserToList(User userToAdd)
    {
        logger.logEnter("addUserToList");
        logger.logExit("addUserToList");
        allUsers.add(userToAdd);
    }

    public static User getUserFromList(String nameToGet)
    {
        logger.logEnter("getUserFromList");

        for (User thisUser : allUsers)
        {
            if (thisUser.getPlayerName().equals(nameToGet))
            {
                logger.logExit("getUserFromList");
                return thisUser;
            }
        }
        logger.e("No user was found for: " + nameToGet);
        logger.logExit("getUserFromList");
        return null;
    }

    public static void removeAllUsersFromList()
    {
        logger.logEnter("removeAllUsersFromList");

        UserUtil.allUsers.clear();

        logger.logExit("removeAllUsersFromList");
    }

    public static void convertUsersToType(GameUtil.GAME_TYPES gameType)
    {
        List<User> newUserList = new ArrayList<User>(UserUtil.allUsers.size());

        switch(gameType)
        {
            case AGRICOLA:
                for (User thisUser : UserUtil.allUsers)
                {
                    newUserList.add(new AgricolaUser(thisUser.getPlayerName()));
                }
                break;
            case CARCASSONNE:
                for (User thisUser : UserUtil.allUsers)
                {
                    newUserList.add(new CarcassonneUser(thisUser.getPlayerName()));
                }
                break;
        }

        UserUtil.allUsers = newUserList;
    }

    public static ArrayAdapter<User> getUserAdapter()
    {
        logger.logEnter("getUserAdapter");
        logger.logExit("getUseradapter");
        return userAdapter;
    }

    public static void setUserAdapter(ArrayAdapter<User> inUserAdapter)
    {
        logger.logEnter("setUserAdapter");
        userAdapter = inUserAdapter;
        logger.logExit("setUserAdapter");
    }
}
