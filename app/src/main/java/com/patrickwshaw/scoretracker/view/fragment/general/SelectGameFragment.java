package com.patrickwshaw.scoretracker.view.fragment.general;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.patrickwshaw.scoretracker.R;
import com.patrickwshaw.scoretracker.model.type.game.parent.Game;
import com.patrickwshaw.scoretracker.utility.general.GameUtil;
import com.patrickwshaw.scoretracker.utility.general.UserUtil;
import com.patrickwshaw.scoretracker.utility.logging.LoggingUtil;
import com.patrickwshaw.scoretracker.view.fragment.general.adapters.GameListAdapter;
import com.patrickwshaw.scoretracker.view.util.DialogFactory;

/**
 * Created by Patrick on 1/22/2015.
 */
public class SelectGameFragment extends ListFragment
{
    private LoggingUtil logger = new LoggingUtil("SelectGameFragment", "SelectGameFragment");

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        logger.logEnter("onCreate");

        //TODO: Eventually determine if we want an options menu here or not
        setHasOptionsMenu(false);

        logger.logExit("onCreate");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        logger.logEnter("onActivityCreated");

        logger.d("setting up the list adapter");

        GameListAdapter listAdapter = new GameListAdapter(getActivity(), GameUtil.getGameList(getActivity()));

        setListAdapter(listAdapter);

        //TODO: Eventually determine if we want to be long clickable
        getListView().setLongClickable(false);

        logger.logExit("onActivityCreated");
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        logger.logEnter("onListItemClick");

        final Game selected = (Game) getListAdapter().getItem(position);

        logger.d("The user selected: " + selected.getGameName());

        if (selected.getGameType() != null)
        {
            //first check if there is already a selected game type
            GameUtil.GAME_TYPES currentlySelectedGameType = GameUtil.getSelectedGameType();
            if (currentlySelectedGameType != null)
            {
                if (currentlySelectedGameType.equals(selected.getGameType()))
                {
                    logger.d("The new selected game type matches the previously selected game type");
                }
                else
                {
                    logger.w("The previously selected game type was: " + currentlySelectedGameType +
                    "\nThe user's new selection was: " + selected.getGameType() +
                    "\nAlerting the user.");
                    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            logger.logInnerClassEnter("DialogInterface.OnClickListener", "onClick");

                            switch(which)
                            {
                                case DialogInterface.BUTTON_POSITIVE:
                                    logger.d("The user selected to void their current session and start a new one - do it");
                                    //first, convert any existing users to the new type (only saves the user name)
                                    UserUtil.convertUsersToType(selected.getGameType());

                                    //now, set the selected game type
                                    GameUtil.setSelectedGameType(selected.getGameType());

                                    //finally, open the fragment
                                    logger.d("Opening the select user fragment from the onClickListener");
                                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                                    Fragment selectUserFragment = new SelectUserFragment();

                                    //replace the current fragment with the agricola fragment
                                    transaction.replace(R.id.fragmentContainer, selectUserFragment);

                                    //add the current fragment to the back stack
                                    //(this will tell android that on "back" go back here)
                                    transaction.addToBackStack(null);

                                    //Commit the transaction
                                    transaction.commit();

                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                    //do nothing - that's what the user wants
                                    logger.d("The user selected to keep their current game session - doing nothing");
                                    break;
                            }


                            logger.logInnerClassExit("DialogInterface.OnClickListener", "onClick");
                        }
                    };

                    DialogFactory.createYesNoDialog(getActivity(), R.string.selectGameFragmentSecondGameSelectedWarningTitle, R.string.selectGameFragmentSecondGameSelectedWarningText, R.string.defaultYesLabel, R.string.defaultNoLabel, listener).show();

                    //any transitions will be handled by the onclicklisener - returning from here
                    logger.logExit("onListItemClick");
                    return;
                }
            }
            else
            {
                logger.d("There was no currently selected game type - setting it to: " + selected.getGameType());
                GameUtil.setSelectedGameType(selected.getGameType());
            }


            //open the select user fragment
            logger.d("Opening the select user fragment");
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            Fragment selectUserFragment = new SelectUserFragment();

            //replace the current fragment with the agricola fragment
            transaction.replace(R.id.fragmentContainer, selectUserFragment);

            //add the current fragment to the back stack
            //(this will tell android that on "back" go back here)
            transaction.addToBackStack(null);

            //Commit the transaction
            transaction.commit();
        }
        else
        {
            logger.e("The selected game type was somehow null!");
            DialogFactory.createDefaultErrorDialog(getActivity()).show();
        }

        logger.logExit("onListItemClick");
    }

    @Override
    public void onResume() {
        super.onResume();
        logger.logEnter("onResume");

        //(re)set the title
        getActivity().setTitle(R.string.selectGameFragmentName);


        logger.logExit("onResume");
    }
}
