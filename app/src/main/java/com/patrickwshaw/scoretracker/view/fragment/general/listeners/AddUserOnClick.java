package com.patrickwshaw.scoretracker.view.fragment.general.listeners;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;

import com.patrickwshaw.scoretracker.R;
import com.patrickwshaw.scoretracker.model.type.user.impl.AgricolaUser;
import com.patrickwshaw.scoretracker.model.type.user.impl.CarcassonneUser;
import com.patrickwshaw.scoretracker.model.type.user.parent.User;
import com.patrickwshaw.scoretracker.utility.general.GameUtil;
import com.patrickwshaw.scoretracker.utility.general.UserUtil;
import com.patrickwshaw.scoretracker.utility.logging.LoggingUtil;
import com.patrickwshaw.scoretracker.view.util.DialogFactory;

/**
 * Created by Patrick on 1/11/2015.
 */
public class AddUserOnClick implements View.OnClickListener
{
    private LoggingUtil logger = new LoggingUtil("AddUserOnClick", "AddUserOnClick");

    private Activity activity;
    private GameUtil.GAME_TYPES gameType;

    public AddUserOnClick()
    {
        //default constructor
    }

    public AddUserOnClick(Activity activity)
    {
        logger.logEnter("ConstructorWithSingleParam");
        this.activity = activity;
        logger.d("No game type was passed in, defaulting to agricola");
        this.gameType = GameUtil.GAME_TYPES.AGRICOLA;
        logger.logExit("ConstructorWithSingleParam");
    }

    public AddUserOnClick(Activity activity, GameUtil.GAME_TYPES gameType)
    {
        logger.logEnter("ConstructorWithTwoParams");
        this.activity = activity;
        this.gameType = gameType;
        logger.logExit("ConstructorWithTwoParams");
    }
    @Override
    public void onClick(View v)
    {
        logger.logEnter("onClick");

        //present the user with a dialog box to enter a user
        final EditText inputTextString = new EditText(activity);

        //create an onclicklistener for the dialog
        DialogInterface.OnClickListener createNewUserListener = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                switch(which)
                {
                    case DialogInterface.BUTTON_POSITIVE:
                        logger.d("The user selected to create the new user, create it");
                        String userInputText = inputTextString.getText().toString();
                        if (userInputText != "")
                        {
                            logger.d("The user entered: " + userInputText);
                            User newUser;

                            switch (gameType)
                            {
                                case AGRICOLA:
                                    logger.d("Creating new Agricola User");
                                    newUser = new AgricolaUser(userInputText);
                                    break;
                                case CARCASSONNE:
                                    logger.d("Creating new Carcassone User");
                                    newUser = new CarcassonneUser(userInputText);
                                    break;
                                default:
                                    logger.w("No Game Type Matched in CreateNewUserListener - defaulting to Agricola");
                                    newUser = new AgricolaUser((userInputText));
                            }

                            UserUtil.addUserToList(newUser);

                            logger.d("Notifying the UserUtil.getUserAdapter() that it's dataset has changed");
                            UserUtil.getUserAdapter().notifyDataSetChanged();

                        }
                        else
                        {
                            logger.d("The user did not enter anything - not going to do anything");
                        }
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        logger.d("The user choose to not create a new user");
                        break;
                    default:
                        logger.e("Invalid action detected: " + which);
                        DialogFactory.createDefaultErrorDialog(activity).show();
                        break;
                }
            }
        };
        //create the alertdialog
        logger.d("creating the dialog");
        DialogFactory.createInputStringDialog(activity, R.string.dialogFactoryUsernamePromptTitle, R.string.dialogFactoryUsernamePromptMessage, R.string.dialogFactoryUsernamePromptAddLabel, R.string.dialogFactoryUsernamePromptCancelLabel, createNewUserListener, inputTextString).show();

        logger.logExit("onClick");
    }
}
