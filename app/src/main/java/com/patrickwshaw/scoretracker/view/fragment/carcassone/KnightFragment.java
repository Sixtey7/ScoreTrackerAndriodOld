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
import com.patrickwshaw.scoretracker.view.fragment.carcassone.types.impl.KnightScoreable;
import com.patrickwshaw.scoretracker.view.util.DialogFactory;

/**
 * Created by Patrick on 1/24/2015.
 */
public class KnightFragment extends Fragment
{
    private LoggingUtil logger = new LoggingUtil("KnightFragment", "KnightFragment");

    private CarcassonneUser currentUser;

    private KnightScoreable knightScoreable;
    private boolean updateKnight;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        logger.logEnter("onCreate");


        //for now, turn off the options menu
        //TODO: Determine if we want an options menu here or not
        setHasOptionsMenu(false);

        //set the title
        getActivity().setTitle(R.string.carcassonneAddKnightFragmentName);

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
            knightScoreable = (KnightScoreable) args.getSerializable(CarcassonneUtil.CURRENT_ACTION_TAG);
            logger.d("Got the Knight Scoreable: " + knightScoreable.getDisplayText());
            updateKnight = true;
        }
        else {
            logger.d("No knight was passed in the bundle - creating a new one");
            knightScoreable = new KnightScoreable();
            updateKnight = false;
        }

        logger.logExit("onCreate");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);
        logger.logEnter("onCreateView");

        View v = inflater.inflate(R.layout.fragment_carcassonne_knight, parent, false);

        final EditText knightSpacesValue = (EditText) v.findViewById(R.id.carcassonneKnightSpacesValue);
        final EditText knightBannersValue = (EditText) v.findViewById(R.id.carcassonneKnightBannersValue);
        final TextView knightScore = (TextView) v.findViewById(R.id.carcassonneKnightScore);
        final CheckBox knightHasCath = (CheckBox) v.findViewById(R.id.carcassonneKnightHasCath);
        final CheckBox knightEndGame = (CheckBox) v.findViewById(R.id.carcassonneKnightEndGame);

        knightSpacesValue.setText(Integer.toString(knightScoreable.getNumSpaces()));
        knightBannersValue.setText(Integer.toString(knightScoreable.getNumFlags()));
        knightScore.setText(Integer.toString(knightScoreable.getScore()));
        if (knightScoreable.isHasCath()) {
            knightHasCath.setChecked(true);
        }

        if (knightScoreable.getEndGame()) {
            knightEndGame.setChecked(true);
        }

        knightHasCath.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                logger.d("Setting HasCath: " + isChecked);
                knightScore.setText(Integer.toString(knightScoreable.setHasCathAndCalculateScore(isChecked)));
            }
        });

        knightEndGame.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                logger.d("Setting EndGame: " + isChecked);
                knightScore.setText(Integer.toString(knightScoreable.setEndGameAndCalculateScore(isChecked)));
            }
        });

        Button subSpaceButton = (Button) v.findViewById(R.id.carcassonneSubKnightSpacesButton);

        subSpaceButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                knightScore.setText(Integer.toString(knightScoreable.setNumberAndCalculateScore((knightScoreable.getNumSpaces() - 1), knightScoreable.getNumFlags())));
                knightSpacesValue.setText(Integer.toString(knightScoreable.getNumSpaces()));
            }
        });

        Button addSpaceButton = (Button) v.findViewById(R.id.carcassonneAddKnightSpaceButton);

        addSpaceButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                knightScore.setText(Integer.toString(knightScoreable.setNumberAndCalculateScore((knightScoreable.getNumSpaces() + 1), knightScoreable.getNumFlags())));
                knightSpacesValue.setText(Integer.toString(knightScoreable.getNumSpaces()));
            }
        });

        Button subBannerButton = (Button) v.findViewById(R.id.carcassonneSubKnightBannersButton);

        subBannerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                knightScore.setText(Integer.toString(knightScoreable.setNumberAndCalculateScore(knightScoreable.getNumSpaces(), (knightScoreable.getNumFlags() - 1))));
                knightBannersValue.setText(Integer.toString(knightScoreable.getNumFlags()));
            }
        });

        Button addBannerButton = (Button) v.findViewById(R.id.carcassonneAddKnightBannersButton);

        addBannerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                knightScore.setText(Integer.toString(knightScoreable.setNumberAndCalculateScore(knightScoreable.getNumSpaces(), (knightScoreable.getNumFlags() + 1))));
                knightBannersValue.setText(Integer.toString(knightScoreable.getNumFlags()));
            }
        });

        Button submitKnightButton = (Button) v.findViewById(R.id.carcassonneSubmitKnightButton);
        submitKnightButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (updateKnight) {
                    logger.d("Update knight was set to true - attempting to update the knight in the list");
                    //the knight better already exist in the current user
                    if (currentUser.updateScoreableInList(knightScoreable)) {
                        logger.d("Success back from the current user - all is well");
                    }
                    else {
                        logger.e("Fail back from the current user - throwing an error");
                        DialogFactory.createDefaultErrorDialog(getActivity()).show();
                    }
                }
                else {
                    logger.d("Adding the knight to the current user");
                    currentUser.addScorableToList(knightScoreable);
                }

                getActivity().getFragmentManager().popBackStack();
            }
        });

        logger.logExit("onCreateView");

        return v;
    }
}
