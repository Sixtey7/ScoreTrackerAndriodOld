package com.patrickwshaw.scoretracker.view.fragment.carcassone;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.patrickwshaw.scoretracker.R;
import com.patrickwshaw.scoretracker.model.type.user.impl.CarcassonneUser;
import com.patrickwshaw.scoretracker.utility.game.CarcassonneUtil;
import com.patrickwshaw.scoretracker.utility.general.UserUtil;
import com.patrickwshaw.scoretracker.utility.logging.LoggingUtil;
import com.patrickwshaw.scoretracker.view.fragment.carcassone.adapters.CarcassonneAdapter;
import com.patrickwshaw.scoretracker.view.fragment.carcassone.listeners.AddUserActionOnClickListener;
import com.patrickwshaw.scoretracker.view.fragment.carcassone.types.parent.CarcassonneScoreable;
import com.patrickwshaw.scoretracker.view.fragment.general.listeners.AddUserOnClick;

/**
 * Created by Patrick on 1/23/2015.
 */
public class CarcassonneFragment extends ListFragment
{
    private LoggingUtil logger = new LoggingUtil("CarcassoneFragment", "CarcassoneFragment");

    private CarcassonneUser currentUser;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        logger.logEnter("onCreate");

        //for now, turn off the options menu
        //TODO: Determine if we want an options menu here or not
        setHasOptionsMenu(false);

        //unpack the bundle
        Bundle args = this.getArguments();
        if (args.containsKey(UserUtil.SELECTED_USER_TAG))
        {
            logger.d("Bundle contains selected user tag - will load settings for user");
            String userName = args.getString(UserUtil.SELECTED_USER_TAG);
            logger.d("Got the username: " + userName);
            currentUser = (CarcassonneUser) UserUtil.getUserFromList(userName);
        }
        else
        {
            logger.w("Bundle did not contain an entry for Selected User Tag");
        }

        if (currentUser == null)
        {
            logger.w("Current user was still null (after unpacking bundle) - creating an empty user");
            currentUser = new CarcassonneUser("");
        }


        logger.logExit("onCreate");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        logger.logEnter("onActivityCreated");

        logger.d("Setting up the list adapter");
        CarcassonneAdapter listAdapter = new CarcassonneAdapter(getActivity(), currentUser.getPlayerScoreables());
        setListAdapter(listAdapter);

        CarcassonneUtil.setCarcassonneAdapter(listAdapter);

        //TODO: Eventually determine if we want to be long clickable
        getListView().setLongClickable(false);


        logger.d("Adding the button to the bottom");
        Button addItemButton = new Button(getActivity());
        addItemButton.setText(this.getString(R.string.carcassonneAddUserActionButtonLabel));
        addItemButton.setOnClickListener(new AddUserActionOnClickListener(getActivity(), currentUser));

        getListView().addFooterView(addItemButton);
        logger.logExit("onActivityCreated");
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        logger.logEnter("onListItemClick");

        CarcassonneScoreable selected = CarcassonneUtil.getCarcassonneAdapter().getItem(position);

        Fragment actionFragment = null;

        switch(selected.getType()) {
            case FARMER:
                logger.d("The user selected to edit a farmer");
                actionFragment = new FarmerFragment();
                break;
            case KNIGHT:
                logger.d("The user selected to edit a knight");
                actionFragment = new KnightFragment();
                break;
            case MONK:
                logger.d("The user selected to edit a monk");
                actionFragment = new MonkFragment();
                break;
            case THIEF:
                logger.d("The user selected to edit a thief");
                actionFragment = new ThiefFragment();
                break;
            //no need for a default here - it's an enum
        }


        if (actionFragment != null) {
            //we have a target fragment, start the transaction
            FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
            //pack up a bundle to pass to the fragment
            Bundle args = new Bundle();
            args.putString(UserUtil.SELECTED_USER_TAG, currentUser.getPlayerName());
            args.putSerializable(CarcassonneUtil.CURRENT_ACTION_TAG, selected);

            actionFragment.setArguments(args);

            //replace the current fragment with the agricola fragment
            transaction.replace(R.id.fragmentContainer, actionFragment);

            //add the current fragment to the back stack
            //(this will tell android that on "back" go back here)
            transaction.addToBackStack(null);

            //Commit the transaction
            transaction.commit();
        }


        logger.logExit("onListItemClick");
    }
    @Override
    public void onResume() {
        super.onResume();
        logger.logEnter("onResume");

        //(re)set the title
        getActivity().setTitle(R.string.carcassonneFragmentName);

        CarcassonneUtil.calculateAndSetTotalScore(currentUser);

        logger.logExit("onResume");
    }

}
