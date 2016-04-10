package com.patrickwshaw.scoretracker.view.fragment.agricola;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.patrickwshaw.scoretracker.R;
import com.patrickwshaw.scoretracker.model.type.user.impl.AgricolaUser;
import com.patrickwshaw.scoretracker.utility.game.AgricolaFragmentUtil;
import com.patrickwshaw.scoretracker.utility.general.UserUtil;
import com.patrickwshaw.scoretracker.view.fragment.agricola.types.ScoreItem;
import com.patrickwshaw.scoretracker.utility.game.AgricolaUtil;
import com.patrickwshaw.scoretracker.utility.logging.LoggingUtil;

/**
 * Created by Patrick on 1/9/2015.
 */
public class AgricolaFragment extends Fragment
{
    public static LoggingUtil logger = new LoggingUtil("AgricolaFragment", "AgricolaFragment");

    private AgricolaUser currentUser;

    private AgricolaFragmentUtil agricolaFragmentUtil;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        logger.logEnter("onCreate");

        //unpack the bundle
        Bundle args = this.getArguments();
        if (args.containsKey(UserUtil.SELECTED_USER_TAG))
        {
            logger.d("Bundle contains selected user tag - will load settings for user");
            String userName = args.getString(UserUtil.SELECTED_USER_TAG);
            logger.d("Got the username: " + userName);
            currentUser = (AgricolaUser) UserUtil.getUserFromList(userName);
        }
        else
        {
            logger.w("Bundle did not contain an entry for Selected User Tag");
        }

        if (currentUser == null)
        {
            logger.w("Current user was still null (after unpacking bundle) - creating an empty user");
            currentUser = new AgricolaUser("");
        }

        getActivity().setTitle("Agricola Scores");

        logger.logExit("onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, parent, savedInstanceState);
        logger.logEnter("onCreateView");

        View v  = inflater.inflate(R.layout.frament_agricola, parent, false);

        logger.d("Creating the Agricola Game Object");
        this.agricolaFragmentUtil = new AgricolaFragmentUtil((TextView) v.findViewById(R.id.agricolaTotalScoreText));

        AgricolaUtil.setAgricolaFragmentUtil(this.agricolaFragmentUtil);
        logger.d("Setting up the text fields");
        agricolaFragmentUtil.setFieldScoreItem(new ScoreItem(AgricolaUtil.createFieldScore(), currentUser.getFieldNumber(), (EditText) v.findViewById(R.id.agricolaFieldResourceValue), (TextView) v.findViewById(R.id.agricolaFieldResourceScore)));
        agricolaFragmentUtil.setPastureScoreItem(new ScoreItem(AgricolaUtil.createPastureScore(), currentUser.getPastureNumber(), (EditText) v.findViewById(R.id.agricolaPastureResourceValue), (TextView) v.findViewById(R.id.agricolaPastureResourceScore)));
        agricolaFragmentUtil.setGrainScoreItem(new ScoreItem(AgricolaUtil.createGrainScore(), currentUser.getGrainNumber(), (EditText) v.findViewById(R.id.agricolaGrainResourceValue), (TextView) v.findViewById(R.id.agricolaGrainResourceScore)));
        agricolaFragmentUtil.setVegetableScoreItem(new ScoreItem(AgricolaUtil.createVegetableScore(), currentUser.getVegetableNumber(), (EditText) v.findViewById(R.id.agricolaVegetableResourceValue), (TextView) v.findViewById(R.id.agricolaVegetableResourceScore)));
        agricolaFragmentUtil.setSheepScoreItem(new ScoreItem(AgricolaUtil.createSheepScore(), currentUser.getSheepNumber(), (EditText) v.findViewById(R.id.agricolaSheepResourceValue), (TextView) v.findViewById(R.id.agricolaSheepResourceScore)));
        agricolaFragmentUtil.setBoarScoreItem(new ScoreItem(AgricolaUtil.createBoarScore(), currentUser.getBoarNumber(), (EditText) v.findViewById(R.id.agricolaBoarResourceValue), (TextView) v.findViewById(R.id.agricolaBoarResourceScore)));
        agricolaFragmentUtil.setCowScoreItem(new ScoreItem(AgricolaUtil.createCowScore(), currentUser.getCowNumber(), (EditText) v.findViewById(R.id.agricolaCowResourceValue), (TextView) v.findViewById(R.id.agricolaCowResourceScore)));
        agricolaFragmentUtil.setEmptySpaceScoreItem(new ScoreItem(AgricolaUtil.createUnusedFarmyardSpaceScore(), currentUser.getEmptySpaceNumber(), (EditText) v.findViewById(R.id.agricolaUnusedSpaceResourceValue), (TextView) v.findViewById(R.id.agricolaUnusedSpaceResourceScore)));
        agricolaFragmentUtil.setFencedStableScoreItem(new ScoreItem(AgricolaUtil.createFencedStableScore(), currentUser.getFencedStableNumber(), (EditText) v.findViewById(R.id.agricolaFencedStableResourceValue), (TextView) v.findViewById(R.id.agricolaFencedStableResourceScore)));
        agricolaFragmentUtil.setClayRoomScoreItem(new ScoreItem(AgricolaUtil.createClayHutScore(), currentUser.getClayRoomNumber(), (EditText) v.findViewById(R.id.agricolaClayRoomResourceValue), (TextView) v.findViewById(R.id.agricolaClayRoomResourceScore)));
        agricolaFragmentUtil.setStoneRoomScoreItem(new ScoreItem(AgricolaUtil.createStoneHouseScore(), currentUser.getStoneRoomNumber(), (EditText) v.findViewById(R.id.agricolaStoneRoomResourceValue), (TextView) v.findViewById(R.id.agricolaStoneRoomResourceScore)));
        agricolaFragmentUtil.setFamilyMemberScoreItem(new ScoreItem(AgricolaUtil.createFamilyMemberScore(), currentUser.getFamilyMemberNumber(), (EditText) v.findViewById(R.id.agricolaFamilyMemberResourceValue), (TextView) v.findViewById(R.id.agricolaFamilyMemberResourceScore)));
        agricolaFragmentUtil.setCardPointScoreItem(new ScoreItem(AgricolaUtil.createCardPointsScore(), currentUser.getCardPointNumber(), (EditText) v.findViewById(R.id.agricolaCardPointsResourceValue), (TextView) v.findViewById(R.id.agricolaCardPointsResourceScore)));
        agricolaFragmentUtil.setBonusPointsScoreItem(new ScoreItem(AgricolaUtil.createBonusPointsScore(), currentUser.getBonusPointNumber(), (EditText) v.findViewById(R.id.agricolaBonusPointsResourceValue), (TextView) v.findViewById(R.id.agricolaBonusPointsResourceScore)));
        logger.d("All Text Fields Wired Up");

        logger.d("Wiring up the buttons");
        AgricolaUtil.assignButtonsForScoreItem(
                (Button) v.findViewById(R.id.agricolaSubFieldButton),
                (Button) v.findViewById(R.id.agricolaAddFieldButton),
                agricolaFragmentUtil.getFieldScoreItem()
        );

        AgricolaUtil.assignButtonsForScoreItem(
                (Button) v.findViewById(R.id.agricolaSubPasturesButton),
                (Button) v.findViewById(R.id.agricolaAddPasturesButton),
                agricolaFragmentUtil.getPastureScoreItem()
        );

        AgricolaUtil.assignButtonsForScoreItem(
                (Button) v.findViewById(R.id.agricolaSubGrainButton),
                (Button) v.findViewById(R.id.agricolaAddGrainButton),
                agricolaFragmentUtil.getGrainScoreItem()
        );

        AgricolaUtil.assignButtonsForScoreItem(
                (Button) v.findViewById(R.id.agricolaSubVegetableButton),
                (Button) v.findViewById(R.id.agricolaAddVegetableButton),
                agricolaFragmentUtil.getVegetableScoreItem()
        );

        AgricolaUtil.assignButtonsForScoreItem(
                (Button) v.findViewById(R.id.agricolaSubSheepButton),
                (Button) v.findViewById(R.id.agricolaAddSheepButton),
                agricolaFragmentUtil.getSheepScoreItem()
        );

        AgricolaUtil.assignButtonsForScoreItem(
                (Button) v.findViewById(R.id.agricolaSubBoarButton),
                (Button) v.findViewById(R.id.agricolaAddBoarButton),
                agricolaFragmentUtil.getBoarScoreItem()
        );

        AgricolaUtil.assignButtonsForScoreItem(
                (Button) v.findViewById(R.id.agricolaSubCowButton),
                (Button) v.findViewById(R.id.agricolaAddCowButton),
                agricolaFragmentUtil.getCowScoreItem()
        );

        AgricolaUtil.assignButtonsForScoreItem(
                (Button) v.findViewById(R.id.agricolaSubUnusedSpaceButton),
                (Button) v.findViewById(R.id.agricolaAddUnusedSpaceButton),
                agricolaFragmentUtil.getEmptySpaceScoreItem()
        );

        AgricolaUtil.assignButtonsForScoreItem(
                (Button) v.findViewById(R.id.agricolaSubFencedStableButton),
                (Button) v.findViewById(R.id.agricolaAddFencedStableButton),
                agricolaFragmentUtil.getFencedStableScoreItem()
        );

        AgricolaUtil.assignButtonsForScoreItem(
                (Button) v.findViewById(R.id.agricolaSubClayHutButton),
                (Button) v.findViewById(R.id.agricolaAddClayHutButton),
                agricolaFragmentUtil.getClayRoomScoreItem()
        );

        AgricolaUtil.assignButtonsForScoreItem(
                (Button) v.findViewById(R.id.agricolaSubStoneRoomButton),
                (Button) v.findViewById(R.id.agricolaAddStoneRoomButton),
                agricolaFragmentUtil.getStoneRoomScoreItem()
        );

        AgricolaUtil.assignButtonsForScoreItem(
                (Button) v.findViewById(R.id.agricolaSubFamilyMemberButton),
                (Button) v.findViewById(R.id.agricolaAddFamilyMemberButton),
                agricolaFragmentUtil.getFamilyMemberScoreItem()
        );

        AgricolaUtil.assignButtonsForScoreItem(
                (Button) v.findViewById(R.id.agricolaSubCardPointButton),
                (Button) v.findViewById(R.id.agricolaAddCardPointButton),
                agricolaFragmentUtil.getCardPointScoreItem()
        );

        AgricolaUtil.assignButtonsForScoreItem(
                (Button) v.findViewById(R.id.agricolaSubBonusPointButton),
                (Button) v.findViewById(R.id.agricolaAddBonusPointButton),
                agricolaFragmentUtil.getBonusPointsScoreItem()
        );

        logger.d("Setting the initial total score");
        this.agricolaFragmentUtil.calculateAndSetTotalScore();

        logger.logExit("onCreateView");
        return v;
    }

    @Override
    public void onPause()
    {
        super.onPause();
        logger.logEnter("onPause");
        logger.d("Pausing - Storing off the players numbers");
        agricolaFragmentUtil.setPlayerScoresFromUI(currentUser);
        logger.logExit("onPause");
    }

    @Override
    public void onStop()
    {
        super.onStop();
        logger.logEnter("onStop");
        logger.d("Stopping - Storing off the players numbers");
        agricolaFragmentUtil.setPlayerScoresFromUI(currentUser);
        logger.logExit("onStop");
    }
}
