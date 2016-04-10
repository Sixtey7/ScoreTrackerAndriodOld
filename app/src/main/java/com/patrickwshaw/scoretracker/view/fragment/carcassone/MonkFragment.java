package com.patrickwshaw.scoretracker.view.fragment.carcassone;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.patrickwshaw.scoretracker.R;
import com.patrickwshaw.scoretracker.model.type.user.impl.CarcassonneUser;
import com.patrickwshaw.scoretracker.utility.game.CarcassonneUtil;
import com.patrickwshaw.scoretracker.utility.general.UserUtil;
import com.patrickwshaw.scoretracker.utility.logging.LoggingUtil;
import com.patrickwshaw.scoretracker.view.fragment.carcassone.types.impl.MonkScoreable;
import com.patrickwshaw.scoretracker.view.util.DialogFactory;

/**
 * Created by Patrick on 1/24/2015.
 */
public class MonkFragment extends Fragment
{
    private LoggingUtil logger = new LoggingUtil("MonkFragment", "MonkFragment");

    private CarcassonneUser currentUser;

    private MonkScoreable monkScoreable;
    private boolean updateMonk;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        logger.logEnter("onCreate");


        //for now, turn off the options menu
        //TODO: Determine if we want an options menu here or not
        setHasOptionsMenu(false);

        //set the title
        getActivity().setTitle(R.string.carcassonneAddMonkFragmentName);

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
            //we can't do much here without a user (since we'll have nowhere to add the score to)
            //bomb and exit
            logger.e("Bundle did not contain an entry for Selected User Tag");
            DialogFactory.createDefaultErrorDialog(getActivity()).show();
            getActivity().getFragmentManager().popBackStack();
        }

        if (currentUser == null)
        {
            //we can't do much here without a user (since we'll have nowhere to add the score to)
            //bomb and exit
            logger.d("Current user was still null (after unpacking bundle) - bombing out");
            DialogFactory.createDefaultErrorDialog(getActivity()).show();
            getActivity().getFragmentManager().popBackStack();
        }

        if (args.containsKey(CarcassonneUtil.CURRENT_ACTION_TAG)) {
            logger.d("The current action tag was set, we must be updating something");
            monkScoreable = (MonkScoreable) args.getSerializable(CarcassonneUtil.CURRENT_ACTION_TAG);
            logger.d("Got the Monk Scoreable: " + monkScoreable.getDisplayText());
            updateMonk = true;
        }
        else {
            logger.d("No monk was passed in the bundle - creating a new one");
            monkScoreable = new MonkScoreable();
            updateMonk = false;
        }

        logger.logExit("onCreate");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);
        logger.logEnter("onCreateView");

        View v = inflater.inflate(R.layout.fragment_carcassonne_monk, parent, false);

        final EditText monkValue = (EditText) v.findViewById(R.id.carcassonneMonkValue);
        final TextView monkScore = (TextView) v.findViewById(R.id.carcassonneMonkScore);

        monkValue.setText(Integer.toString(monkScoreable.getNumSpaces()));
        monkScore.setText(Integer.toString(monkScoreable.getScore()));

        Button subSpaceButton = (Button) v.findViewById(R.id.carcassonneSubMonkSpaceButton);

        subSpaceButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                monkScore.setText(Integer.toString(monkScoreable.setNumberAndCalculateScore(monkScoreable.getNumSpaces() - 1)));
                monkValue.setText(Integer.toString(monkScoreable.getNumSpaces()));
            }
        });

        Button addSpaceButton = (Button) v.findViewById(R.id.carcassonneAddMonkSpaceButton);

        addSpaceButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                monkScore.setText(Integer.toString(monkScoreable.setNumberAndCalculateScore(monkScoreable.getNumSpaces() + 1)));
                monkValue.setText(Integer.toString(monkScoreable.getNumSpaces()));
            }
        });

        Button submitMonkButton = (Button) v.findViewById(R.id.carcassonneSubmitMonkButton);
        submitMonkButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (updateMonk) {
                    logger.d("Update Monk was set to true - attempting to update the monk in the list");
                    //the monk better already exist in the current user
                    if (currentUser.updateScoreableInList(monkScoreable)) {
                        logger.d("Success back from the current user - all is well");
                    }
                    else {
                        logger.e("Fail back from the current user - throwing an error");
                        DialogFactory.createDefaultErrorDialog(getActivity()).show();
                    }
                }
                else {
                    logger.d("Adding the monk to the current user");
                    currentUser.addScorableToList(monkScoreable);
                }

                getActivity().getFragmentManager().popBackStack();
            }
        });

        logger.logExit("onCreateView");

        return v;
    }
}
