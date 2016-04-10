package com.patrickwshaw.scoretracker.view.fragment.carcassone;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.patrickwshaw.scoretracker.R;
import com.patrickwshaw.scoretracker.model.type.user.impl.CarcassonneUser;
import com.patrickwshaw.scoretracker.utility.game.CarcassonneUtil;
import com.patrickwshaw.scoretracker.utility.general.UserUtil;
import com.patrickwshaw.scoretracker.utility.logging.LoggingUtil;
import com.patrickwshaw.scoretracker.view.fragment.carcassone.types.impl.ThiefScoreable;
import com.patrickwshaw.scoretracker.view.util.DialogFactory;

/**
 * Created by Patrick on 1/24/2015.
 */
public class ThiefFragment extends Fragment
{
    private LoggingUtil logger = new LoggingUtil("ThiefFragment", "ThiefFragment");

    private CarcassonneUser currentUser;

    private ThiefScoreable thiefScoreable;
    private boolean updateThief;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        logger.logEnter("onCreate");


        //for now, turn off the options menu
        //TODO: Determine if we want an options menu here or not
        setHasOptionsMenu(false);

        //set the title
        getActivity().setTitle(R.string.carcassonneAddThiefFragmentName);

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
            thiefScoreable = (ThiefScoreable) args.getSerializable(CarcassonneUtil.CURRENT_ACTION_TAG);
            logger.d("Got the Thief Scoreable: " + thiefScoreable.getDisplayText());
            updateThief = true;
        }
        else {
            logger.d("No thief was passed in the bundle - creating a new one");
            thiefScoreable = new ThiefScoreable();
            updateThief = false;
        }

        logger.logExit("onCreate");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);
        logger.logEnter("onCreateView");

        View v = inflater.inflate(R.layout.fragment_carcassonne_thief, parent, false);

        final EditText thiefValue = (EditText) v.findViewById(R.id.carcassonneThiefValue);
        final TextView thiefScore = (TextView) v.findViewById(R.id.carcassonneThiefScore);
        final CheckBox thiefHasLake = (CheckBox) v.findViewById(R.id.carcassonneThiefHasLake);
        final CheckBox thiefEndGame = (CheckBox) v.findViewById(R.id.carcassonneThiefEndGame);

        if (thiefScoreable.getHasLake()) {
            thiefHasLake.setChecked(true);
        }

        if (thiefScoreable.getEndGame()) {
            thiefEndGame.setChecked(true);
        }

        thiefValue.setText(Integer.toString(thiefScoreable.getNumSpaces()));
        thiefScore.setText(Integer.toString(thiefScoreable.getScore()));

        thiefHasLake.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                logger.d("Setting HasLake: " + isChecked);
                thiefScore.setText(Integer.toString(thiefScoreable.setHasLakeAndCalculateScore(isChecked)));
            }
        });

        thiefEndGame.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                logger.d("Setting EndGame: " + isChecked);
                thiefScore.setText(Integer.toString(thiefScoreable.setEndGameAndCalculateScore(isChecked)));
            }
        });

        Button subSpaceButton = (Button) v.findViewById(R.id.carcassonneSubThiefSpacesButton);

        subSpaceButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                thiefScore.setText(Integer.toString(thiefScoreable.setNumberAndCalculateScore(thiefScoreable.getNumSpaces() - 1)));
                thiefValue.setText(Integer.toString(thiefScoreable.getNumSpaces()));
            }
        });

        Button addSpaceButton = (Button) v.findViewById(R.id.carcassonneAddThiefSpaceButton);

        addSpaceButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                thiefScore.setText(Integer.toString(thiefScoreable.setNumberAndCalculateScore(thiefScoreable.getNumSpaces() + 1)));
                thiefValue.setText(Integer.toString(thiefScoreable.getNumSpaces()));
            }
        });

        Button submitThiefButton = (Button) v.findViewById(R.id.carcassonneSubmitThiefButton);
        submitThiefButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (updateThief) {
                    logger.d("Update Thief was set to true - attempting to update the thief in the list");
                    //the thief better already exist in the current user
                    if (currentUser.updateScoreableInList(thiefScoreable)) {
                        logger.d("Success back from the current user - all is well");
                    }
                    else {
                        logger.e("Fail back from the current user - throwing an error");
                        DialogFactory.createDefaultErrorDialog(getActivity()).show();
                    }
                }
                else {
                    logger.d("Adding the thief to the current user");
                    currentUser.addScorableToList(thiefScoreable);
                }

                getActivity().getFragmentManager().popBackStack();
            }
        });

        logger.logExit("onCreateView");

        return v;
    }
}
