package com.patrickwshaw.scoretracker.utility.game;

import android.widget.Button;

import com.patrickwshaw.scoretracker.R;
import com.patrickwshaw.scoretracker.model.type.score.impl.MultiScore;
import com.patrickwshaw.scoretracker.model.type.score.impl.RangeScore;
import com.patrickwshaw.scoretracker.model.type.score.impl.SingleScore;
import com.patrickwshaw.scoretracker.model.type.score.parent.Score;
import com.patrickwshaw.scoretracker.model.type.score.type.ScoreRange;
import com.patrickwshaw.scoretracker.view.fragment.agricola.types.ScoreItem;
import com.patrickwshaw.scoretracker.view.fragment.agricola.listeners.SubAddOnClick;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrick on 1/4/2015.
 */
public class AgricolaUtil
{
    public static enum BUTTON_ACTIONS
    {
        ADD,
        SUBTRACT
    }

    private static SubAddOnClick onClickListener;

    private static AgricolaFragmentUtil agricolaFragmentUtil;

    public static Score createFieldScore()
    {
        List<ScoreRange> fieldScoreRanges = new ArrayList<ScoreRange>(5);
        fieldScoreRanges.add(new ScoreRange(0,-1));
        fieldScoreRanges.add(new ScoreRange(2,1));
        fieldScoreRanges.add(new ScoreRange(3,2));
        fieldScoreRanges.add(new ScoreRange(4,3));
        fieldScoreRanges.add(new ScoreRange(5,4));

        return new RangeScore(fieldScoreRanges);
    }

    public static Score createPastureScore()
    {
        List<ScoreRange> pastureScoreRanges = new ArrayList<ScoreRange>(5);
        pastureScoreRanges.add(new ScoreRange(0, -1));
        pastureScoreRanges.add(new ScoreRange(1, 1));
        pastureScoreRanges.add(new ScoreRange(2, 2));
        pastureScoreRanges.add(new ScoreRange(3, 3));
        pastureScoreRanges.add(new ScoreRange(4, 4));

        return new RangeScore(pastureScoreRanges);
    }

    public static Score createGrainScore()
    {
        List<ScoreRange> grainScoreRanges = new ArrayList<ScoreRange>(5);
        grainScoreRanges.add(new ScoreRange(0, -1));
        grainScoreRanges.add(new ScoreRange(1, 1));
        grainScoreRanges.add(new ScoreRange(4, 2));
        grainScoreRanges.add(new ScoreRange(6, 3));
        grainScoreRanges.add(new ScoreRange(8, 4));

        return new RangeScore((grainScoreRanges));
    }

    public static Score createVegetableScore()
    {
        List<ScoreRange> vegetableScoreRanges = new ArrayList<ScoreRange>(5);
        vegetableScoreRanges.add(new ScoreRange(0, -1));
        vegetableScoreRanges.add(new ScoreRange(1, 1));
        vegetableScoreRanges.add(new ScoreRange(2, 2));
        vegetableScoreRanges.add(new ScoreRange(3, 3));
        vegetableScoreRanges.add(new ScoreRange(4, 4));

        return new RangeScore(vegetableScoreRanges);
    }

    public static Score createSheepScore()
    {
        List<ScoreRange> sheepScoreRange = new ArrayList<ScoreRange>(5);
        sheepScoreRange.add(new ScoreRange(0, -1));
        sheepScoreRange.add(new ScoreRange(1, 1));
        sheepScoreRange.add(new ScoreRange(4, 2));
        sheepScoreRange.add(new ScoreRange(6, 3));
        sheepScoreRange.add(new ScoreRange(8, 4));

        return new RangeScore(sheepScoreRange);
    }

    public static Score createBoarScore()
    {
        List<ScoreRange> boarScoreRange = new ArrayList<ScoreRange>(5);
        boarScoreRange.add(new ScoreRange(0, -1));
        boarScoreRange.add(new ScoreRange(1, 1));
        boarScoreRange.add(new ScoreRange(3, 2));
        boarScoreRange.add(new ScoreRange(5, 3));
        boarScoreRange.add(new ScoreRange(7, 4));

        return new RangeScore(boarScoreRange);
    }

    public static Score createCowScore()
    {
        List<ScoreRange> cowScoreRange = new ArrayList<ScoreRange>(5);
        cowScoreRange.add(new ScoreRange(0, -1));
        cowScoreRange.add(new ScoreRange(1, 1));
        cowScoreRange.add(new ScoreRange(2, 2));
        cowScoreRange.add(new ScoreRange(4, 3));
        cowScoreRange.add(new ScoreRange(6, 4));

        return new RangeScore(cowScoreRange);
    }

    public static Score createUnusedFarmyardSpaceScore()
    {
        return new MultiScore(-1);
    }

    public static Score createFencedStableScore()
    {
        return new MultiScore(1);
    }

    public static Score createClayHutScore()
    {
        return new MultiScore(1);
    }

    public static Score createStoneHouseScore()
    {
        return new MultiScore(2);
    }

    public static Score createFamilyMemberScore()
    {
        return new MultiScore(3);
    }

    public static Score createCardPointsScore()
    {
        return new SingleScore();
    }

    public static Score createBonusPointsScore()
    {
        return new SingleScore();
    }

    public static void assignButtonsForScoreItem(Button subButton, Button addButton, ScoreItem scoreItem)
    {
        //lazy init the onclicklistener
        if (AgricolaUtil.onClickListener == null)
        {
            AgricolaUtil.onClickListener = new SubAddOnClick();
        }

        subButton.setTag(R.id.AGRICOLA_SCORE_ITEM_TAG, scoreItem);
        subButton.setTag(R.id.AGRICOLA_SCORE_ITEM_ACTION, BUTTON_ACTIONS.SUBTRACT);
        subButton.setOnClickListener(AgricolaUtil.onClickListener);

        addButton.setTag(R.id.AGRICOLA_SCORE_ITEM_TAG, scoreItem);
        addButton.setTag(R.id.AGRICOLA_SCORE_ITEM_ACTION, BUTTON_ACTIONS.ADD);
        addButton.setOnClickListener(AgricolaUtil.onClickListener);
    }

    public static AgricolaFragmentUtil getAgricolaFragmentUtil() { return AgricolaUtil.agricolaFragmentUtil; }

    public static void setAgricolaFragmentUtil(AgricolaFragmentUtil agricolaFragmentUtil)
    {
        AgricolaUtil.agricolaFragmentUtil = agricolaFragmentUtil;
    }
}
