package com.patrickwshaw.scoretracker.view.fragment.carcassone.listeners;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.patrickwshaw.scoretracker.R;
import com.patrickwshaw.scoretracker.model.type.user.impl.CarcassonneUser;
import com.patrickwshaw.scoretracker.utility.general.UserUtil;
import com.patrickwshaw.scoretracker.utility.logging.LoggingUtil;
import com.patrickwshaw.scoretracker.view.fragment.agricola.AgricolaFragment;
import com.patrickwshaw.scoretracker.view.fragment.carcassone.FarmerFragment;
import com.patrickwshaw.scoretracker.view.fragment.carcassone.KnightFragment;
import com.patrickwshaw.scoretracker.view.fragment.carcassone.MonkFragment;
import com.patrickwshaw.scoretracker.view.fragment.carcassone.ThiefFragment;
import com.patrickwshaw.scoretracker.view.util.DialogFactory;

/**
 * Created by Patrick on 1/24/2015.
 */
public class AddUserActionOnClickListener implements View.OnClickListener
{

    private LoggingUtil logger = new LoggingUtil("AddUserActionOnClickListener", "AddUserActionOnClickListener");

    private Activity activity;
    private CarcassonneUser user;

    private AddUserActionOnClickListener()
    {
        //no one should call the default constructor
    }

    public AddUserActionOnClickListener(Activity activity, CarcassonneUser user)
    {
        logger.logEnter("ConstructorWithTwoParams");
        this.activity = activity;
        this.user = user;
        logger.logExit("ConstructorWithTwoParams");
    }
    @Override
    public void onClick(View v)
    {
        logger.logEnter("onClick");

        //present the user with a dialog box asking them which thingy they would like to add
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

                Fragment actionFragment = null;
                switch(which)
                {
                    case 0:
                        //Knight
                        logger.d("The user selected to add a new knight");
                        actionFragment = new KnightFragment();
                        break;
                    case 1:
                        //Monk
                        logger.d("The user selected to add a new monk");
                        actionFragment = new MonkFragment();
                        break;
                    case 2:
                        //Thief
                        logger.d("The user selected to add a new thief");
                        actionFragment = new ThiefFragment();
                        break;
                    case 3:
                        //Farmer
                        logger.d("The user selected to add a new farmer");
                        actionFragment = new FarmerFragment();
                        break;
                    default:
                        //Uh-Oh - The use found some position unique to them
                        logger.e("The user's action didn't match any of the options - in AddUserActionOnClickListener.OnClickListener.onClick");
                        DialogFactory.createDefaultErrorDialog(activity).show();
                        activity.getFragmentManager().popBackStack();
                }

                if (actionFragment != null) {
                    //we have a target fragment, start the transaction
                    FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
                    //pack up a bundle to pass to the fragment
                    Bundle args = new Bundle();
                    args.putString(UserUtil.SELECTED_USER_TAG, user.getPlayerName());

                    actionFragment.setArguments(args);

                    //replace the current fragment with the agricola fragment
                    transaction.replace(R.id.fragmentContainer, actionFragment);

                    //add the current fragment to the back stack
                    //(this will tell android that on "back" go back here)
                    transaction.addToBackStack(null);

                    //Commit the transaction
                    transaction.commit();
                }

            }
        };

        DialogFactory.createListBoxDialog(activity, R.string.carcassonneAddUserActionDialogTitle, R.array.carcassonneMeepleOptions, listener).show();


        logger.logExit("onClick");
    }
}
