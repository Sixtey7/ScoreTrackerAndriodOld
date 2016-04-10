package com.patrickwshaw.scoretracker.utility.game;

import android.widget.TextView;

import com.patrickwshaw.scoretracker.model.type.user.impl.AgricolaUser;
import com.patrickwshaw.scoretracker.view.fragment.agricola.types.ScoreItem;
import com.patrickwshaw.scoretracker.utility.logging.LoggingUtil;

/**
 * Created by Patrick on 1/9/2015.
 */
public class AgricolaFragmentUtil
{
    private ScoreItem fieldScoreItem;
    private ScoreItem pastureScoreItem;
    private ScoreItem grainScoreItem;
    private ScoreItem vegetableScoreItem;
    private ScoreItem sheepScoreItem;
    private ScoreItem boarScoreItem;
    private ScoreItem cowScoreItem;
    private ScoreItem emptySpaceScoreItem;
    private ScoreItem fencedStableScoreItem;
    private ScoreItem clayRoomScoreItem;
    private ScoreItem stoneRoomScoreItem;
    private ScoreItem familyMemberScoreItem;
    private ScoreItem cardPointScoreItem;
    private ScoreItem bonusPointsScoreItem;

    private static LoggingUtil logger = new LoggingUtil("AgricolaFragmentUtil", "AgricolaFragmentUtil");

    private TextView totalScoreWidget;

    public AgricolaFragmentUtil()
    {
        //default constructor
    }

    public AgricolaFragmentUtil(TextView totalScoreWidget)
    {
        this.totalScoreWidget = totalScoreWidget;
    }

    /**
     * Getters and Setters
     */
    public  ScoreItem getFieldScoreItem()
    {
        return fieldScoreItem;
    }

    public void setFieldScoreItem(ScoreItem fieldScoreItem)
    {
        this.fieldScoreItem = fieldScoreItem;
    }

    public ScoreItem getPastureScoreItem()
    {
        return pastureScoreItem;
    }

    public void setPastureScoreItem(ScoreItem pastureScoreItem)
    {
        this.pastureScoreItem = pastureScoreItem;
    }

    public ScoreItem getGrainScoreItem()
    {
        return grainScoreItem;
    }

    public void setGrainScoreItem(ScoreItem grainScoreItem)
    {
        this.grainScoreItem = grainScoreItem;
    }

    public ScoreItem getVegetableScoreItem()
    {
        return vegetableScoreItem;
    }

    public void setVegetableScoreItem(ScoreItem vegtableScoreItem)
    {
        this.vegetableScoreItem = vegtableScoreItem;
    }

    public ScoreItem getSheepScoreItem()
    {
        return sheepScoreItem;
    }

    public void setSheepScoreItem(ScoreItem sheepScoreItem)
    {
        this.sheepScoreItem = sheepScoreItem;
    }

    public ScoreItem getBoarScoreItem()
    {
        return boarScoreItem;
    }

    public void setBoarScoreItem(ScoreItem boarScoreItem)
    {
        this.boarScoreItem = boarScoreItem;
    }

    public ScoreItem getCowScoreItem()
    {
        return cowScoreItem;
    }

    public void setCowScoreItem(ScoreItem cowScoreItem)
    {
        this.cowScoreItem = cowScoreItem;
    }

    public ScoreItem getEmptySpaceScoreItem()
    {
        return emptySpaceScoreItem;
    }

    public void setEmptySpaceScoreItem(ScoreItem emptySpaceScoreItem)
    {
        this.emptySpaceScoreItem = emptySpaceScoreItem;
    }

    public ScoreItem getFencedStableScoreItem()
    {
        return fencedStableScoreItem;
    }

    public void setFencedStableScoreItem(ScoreItem fencedStableScoreItem)
    {
        this.fencedStableScoreItem = fencedStableScoreItem;
    }

    public ScoreItem getClayRoomScoreItem()
    {
        return clayRoomScoreItem;
    }

    public void setClayRoomScoreItem(ScoreItem clayRoomScoreItem)
    {
        this.clayRoomScoreItem = clayRoomScoreItem;
    }

    public ScoreItem getStoneRoomScoreItem()
    {
        return stoneRoomScoreItem;
    }

    public void setStoneRoomScoreItem(ScoreItem stoneRoomScoreItem)
    {
        this.stoneRoomScoreItem = stoneRoomScoreItem;
    }

    public ScoreItem getFamilyMemberScoreItem()
    {
        return familyMemberScoreItem;
    }

    public void setFamilyMemberScoreItem(ScoreItem familyMemberScoreItem)
    {
        this.familyMemberScoreItem = familyMemberScoreItem;
    }

    public ScoreItem getCardPointScoreItem()
    {
        return cardPointScoreItem;
    }

    public void setCardPointScoreItem(ScoreItem cardPointScoreItem)
    {
        this.cardPointScoreItem = cardPointScoreItem;
    }

    public ScoreItem getBonusPointsScoreItem()
    {
        return bonusPointsScoreItem;
    }

    public void setBonusPointsScoreItem(ScoreItem bonusPointsScoreItem)
    {
        this.bonusPointsScoreItem = bonusPointsScoreItem;
    }

    public int calculateTotalScore()
    {
        logger.logEnter("calculateTotalScore");
        int totalScore = 0;
        totalScore += fieldScoreItem.calculateScore();
        totalScore += pastureScoreItem.calculateScore();
        totalScore += grainScoreItem.calculateScore();
        totalScore += vegetableScoreItem.calculateScore();
        totalScore += sheepScoreItem.calculateScore();
        totalScore += boarScoreItem.calculateScore();
        totalScore += cowScoreItem.calculateScore();
        totalScore += emptySpaceScoreItem.calculateScore();
        totalScore += fencedStableScoreItem.calculateScore();
        totalScore += clayRoomScoreItem.calculateScore();
        totalScore += stoneRoomScoreItem.calculateScore();
        totalScore += familyMemberScoreItem.calculateScore();
        totalScore += cardPointScoreItem.calculateScore();
        totalScore += bonusPointsScoreItem.calculateScore();

        logger.logExit("calculateTotalScore");
        return totalScore;
    }

    public int calculateAndSetTotalScore()
    {
        logger.logEnter("calculateAndSetTotalScore");
        int totalScore = this.calculateTotalScore();

        if (totalScoreWidget != null)
        {
            totalScoreWidget.setText(Integer.toString(totalScore));
        }

        logger.logExit("calculateAndSetTotalScore");
        return totalScore;
    }

    public void setScoresForPlayer(AgricolaUser thisUser)
    {
        this.fieldScoreItem.setNumber(thisUser.getFieldNumber());
        this.pastureScoreItem.setNumber(thisUser.getPastureNumber());
        this.grainScoreItem.setNumber(thisUser.getGrainNumber());
        this.vegetableScoreItem.setNumber(thisUser.getVegetableNumber());
        this.sheepScoreItem.setNumber(thisUser.getSheepNumber());
        this.boarScoreItem.setNumber(thisUser.getBoarNumber());
        this.cowScoreItem.setNumber(thisUser.getCowNumber());
        this.emptySpaceScoreItem.setNumber(thisUser.getEmptySpaceNumber());
        this.fencedStableScoreItem.setNumber(thisUser.getFencedStableNumber());
        this.clayRoomScoreItem.setNumber(thisUser.getClayRoomNumber());
        this.stoneRoomScoreItem.setNumber(thisUser.getStoneRoomNumber());
        this.familyMemberScoreItem.setNumber(thisUser.getFamilyMemberNumber());
        this.cardPointScoreItem.setNumber(thisUser.getCardPointNumber());
        this.bonusPointsScoreItem.setNumber(thisUser.getBonusPointNumber());
    }

    public void setPlayerScoresFromUI(AgricolaUser thisUser)
    {
        //store off all of the numbers for the user
        thisUser.setFieldNumber(this.fieldScoreItem.getNumber());
        thisUser.setPastureNumber(this.pastureScoreItem.getNumber());
        thisUser.setGrainNumber(this.grainScoreItem.getNumber());
        thisUser.setVegetableNumber(this.vegetableScoreItem.getNumber());
        thisUser.setSheepNumber(this.sheepScoreItem.getNumber());
        thisUser.setBoarNumber(this.boarScoreItem.getNumber());
        thisUser.setCowNumber(this.cowScoreItem.getNumber());
        thisUser.setEmptySpaceNumber(this.emptySpaceScoreItem.getNumber());
        thisUser.setFencedStableNumber(this.fencedStableScoreItem.getNumber());
        thisUser.setClayRoomNumber(this.clayRoomScoreItem.getNumber());
        thisUser.setStoneRoomNumber(this.stoneRoomScoreItem.getNumber());
        thisUser.setFamilyMemberNumber(this.familyMemberScoreItem.getNumber());
        thisUser.setCardPointNumber(this.cardPointScoreItem.getNumber());
        thisUser.setBonusPointNumber(this.bonusPointsScoreItem.getNumber());

        //calculate their total score
        thisUser.setTotalScore(calculateTotalScore());
    }
}
