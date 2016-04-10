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
import com.patrickwshaw.scoretracker.view.fragment.carcassone.listeners.AddUserActionOnClickListener;
import com.patrickwshaw.scoretracker.view.fragment.carcassone.types.impl.FarmerScoreable;
import com.patrickwshaw.scoretracker.view.util.DialogFactory;

/**
 * Created by Patrick on 1/24/2015.
 */
public class FarmerFragment extends Fragment
{
    private LoggingUtil logger = new LoggingUtil("FarmerFragment", "FarmerFragment");

    private CarcassonneUser currentUser;

    private FarmerScoreable farmerScoreable;
    private boolean updateFarmer;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        logger.logEnter("onCreate");


        //for now, turn off the options menu
        //TODO: Determine if we want an options menu here or not
        setHasOptionsMenu(false);

        //set the title
        getActivity().setTitle(R.string.carcassonneAddFarmerFragmentName);

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
            farmerScoreable = (FarmerScoreable) args.getSerializable(CarcassonneUtil.CURRENT_ACTION_TAG);
            logger.d("Got the Farmer Scoreable: " + farmerScoreable.getDisplayText());
            updateFarmer = true;
        }
        else {
            logger.d("No farmer was passed in the bundle - creating a new one");
            farmerScoreable = new FarmerScoreable();
            updateFarmer = false;
        }

        logger.logExit("onCreate");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);
        logger.logEnter("onCreateView");

        View v = inflater.inflate(R.layout.fragment_carcassonne_farmer, parent, false);

        final EditText farmerValue = (EditText) v.findViewById(R.id.carcassonneFarmerValue);
        final TextView farmerScore = (TextView) v.findViewById(R.id.carcassonneFarmerScore);

        farmerValue.setText(Integer.toString(farmerScoreable.getNumCities()));
        farmerScore.setText(Integer.toString(farmerScoreable.getScore()));

        Button subCityButton = (Button) v.findViewById(R.id.carcassoneSubFarmerCityButton);

        subCityButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                farmerScore.setText(Integer.toString(farmerScoreable.setNumberAndCalculateScore(farmerScoreable.getNumCities() - 1)));
                farmerValue.setText(Integer.toString(farmerScoreable.getNumCities()));
            }
        });

        Button addCityButton = (Button) v.findViewById(R.id.carcassonneAddFarmerCityButton);

        addCityButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                farmerScore.setText(Integer.toString(farmerScoreable.setNumberAndCalculateScore(farmerScoreable.getNumCities() + 1)));
                farmerValue.setText(Integer.toString(farmerScoreable.getNumCities()));
            }
        });

        Button submitFarmerButton = (Button) v.findViewById(R.id.carcassonneSubmitFarmerButton);
        submitFarmerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (updateFarmer) {
                    logger.d("Update Farmer was set to true - attempting to update the farmer in the list");
                    //the farmer better already exist in the current user
                    if (currentUser.updateScoreableInList(farmerScoreable)) {
                        logger.d("Success back from the current user - all is well");
                    }
                    else {
                        logger.e("Fail back from the current user - throwing an error");
                        DialogFactory.createDefaultErrorDialog(getActivity()).show();
                    }
                }
                else {
                    logger.d("Adding the farmer to the current user");
                    currentUser.addScorableToList(farmerScoreable);
                }

                getActivity().getFragmentManager().popBackStack();
            }
        });

        logger.logExit("onCreateView");

        return v;
    }
}
