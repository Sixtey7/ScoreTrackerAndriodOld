package com.patrickwshaw.scoretracker.view.fragment.general;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.patrickwshaw.scoretracker.R;
import com.patrickwshaw.scoretracker.model.type.user.impl.AgricolaUser;
import com.patrickwshaw.scoretracker.model.type.user.parent.User;
import com.patrickwshaw.scoretracker.utility.general.GameUtil;
import com.patrickwshaw.scoretracker.utility.general.UserUtil;
import com.patrickwshaw.scoretracker.utility.logging.LoggingUtil;
import com.patrickwshaw.scoretracker.view.fragment.agricola.AgricolaFragment;
import com.patrickwshaw.scoretracker.view.fragment.carcassone.CarcassonneFragment;
import com.patrickwshaw.scoretracker.view.fragment.general.adapters.UserListAdapter;
import com.patrickwshaw.scoretracker.view.fragment.general.listeners.AddUserOnClick;
import com.patrickwshaw.scoretracker.view.util.DialogFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrick on 1/11/2015.
 */
public class SelectUserFragment extends ListFragment
{
    private LoggingUtil logger = new LoggingUtil("SelectUserFragment", "SelectUserFragment");

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        logger.logEnter("onCreate");

        //for now, turn off the options menu
        //TODO: Determine if we want an options menu here or not
        setHasOptionsMenu(false);

        logger.logExit("onCreate");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        logger.logEnter("onActivityCreated");

        logger.d("Setting up the list adapter");
        UserListAdapter listAdapter = new UserListAdapter(getActivity(), UserUtil.getAllUsers());
        setListAdapter(listAdapter);

        //tell the user util about the list adapter
        UserUtil.setUserAdapter(listAdapter);

        //TODO: Eventually determine if we want to be long clickable
        getListView().setLongClickable(false);


        logger.d("Adding the button to the bottom");
        Button addItemButton = new Button(getActivity());
        addItemButton.setText(this.getString(R.string.selectUserAddUserButtonText));
        addItemButton.setOnClickListener(new AddUserOnClick(getActivity(), GameUtil.getSelectedGameType()));

        getListView().addFooterView(addItemButton);

        logger.logExit("onActivityCreated");
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        logger.logEnter("onListItemClick");

        User selected = UserUtil.getUserAdapter().getItem(position);

        logger.d("User: " + selected.getPlayerName() + " was selected");

        //Figure out which game the user is playing
        GameUtil.GAME_TYPES selectedGameType = GameUtil.getSelectedGameType();
        logger.d("The selected game type was: " + selectedGameType);
        switch (selectedGameType)
        {
            case AGRICOLA:
                logger.d("The selected game type matched AGRICOLA - launching that");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                Fragment agricolaFragment = new AgricolaFragment();

                //pack up a bundle to pass to the fragment
                Bundle args = new Bundle();
                args.putString(UserUtil.SELECTED_USER_TAG, selected.getPlayerName());

                agricolaFragment.setArguments(args);

                //replace the current fragment with the agricola fragment
                transaction.replace(R.id.fragmentContainer, agricolaFragment);

                //add the current fragment to the back stack
                //(this will tell android that on "back" go back here)
                transaction.addToBackStack(null);

                //Commit the transaction
                transaction.commit();

                break;
            case CARCASSONNE:
                logger.d("The selected game time matched CARCASSONE - launching that");
                FragmentTransaction carcassonneTransaction = getFragmentManager().beginTransaction();

                Fragment carcassonneFragment = new CarcassonneFragment();

                //pack up a bundle to pass to the fragment
                Bundle carcassonneArgs = new Bundle();
                carcassonneArgs.putString(UserUtil.SELECTED_USER_TAG, selected.getPlayerName());

                carcassonneFragment.setArguments(carcassonneArgs);

                //replace the current fragment with the carcassonne fragment
                carcassonneTransaction.replace(R.id.fragmentContainer, carcassonneFragment);

                //add the current fragment to the back stack
                //(this will tell android that on "back" go back here)
                carcassonneTransaction.addToBackStack(null);

                //Commit the transaction
                carcassonneTransaction.commit();
                break;
            default:
                logger.e("The default game type didn't match any of the defined types!");
                DialogFactory.createDefaultErrorDialog(getActivity());
        }
        logger.logExit("onListItemClick");
    }

    @Override
    public void onSaveInstanceState (Bundle outState)
    {
        super.onSaveInstanceState(outState);
        logger.logEnter("onSaveInstanceState");
        //get all of the users, and great two arrays, one of usernames
        //and one of user's scores
        List<User> userList = UserUtil.getAllUsers();

        if (userList.size() > 0)
        {
            logger.d("There were : " + userList.size() + " users in the game, packing themn into the bundle");

            ArrayList<AgricolaUser> agricolaUsers = new ArrayList<AgricolaUser>();
            for (User thisUser : userList)
            {
                if (thisUser instanceof AgricolaUser)
                {
                    agricolaUsers.add((AgricolaUser) thisUser);
                }
            }

            logger.d("Of those users, " + agricolaUsers.size() + " were Agricola Users");

            if (agricolaUsers.size() > 0)
            {
                outState.putSerializable(UserUtil.AGRICOLA_USER_TAG, agricolaUsers);
            }
            else
            {
                logger.w("There were no agricola users - but there were general users - not putting anything in the bundle");
            }
        }
        else
        {
            logger.w("There were no users - not saving anything into the bundle");
        }
        logger.logExit("onSaveInstanceState");
    }

    @Override
    public void onResume() {
        super.onResume();
        logger.logEnter("onResume");

        //(re)set the title
        getActivity().setTitle(R.string.selectUserFragmentName);

        logger.logExit("onResume");
    }
}