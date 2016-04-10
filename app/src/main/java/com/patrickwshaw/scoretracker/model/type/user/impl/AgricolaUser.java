package com.patrickwshaw.scoretracker.model.type.user.impl;

import com.patrickwshaw.scoretracker.model.type.user.parent.User;

import java.io.Serializable;

/**
 * Created by Patrick on 1/11/2015.
 */
public class AgricolaUser extends User implements Serializable
{
    private int fieldNumber;
    private int pastureNumber;
    private int grainNumber;
    private int vegetableNumber;
    private int sheepNumber;
    private int boarNumber;
    private int cowNumber;
    private int emptySpaceNumber;
    private int fencedStableNumber;
    private int clayRoomNumber;
    private int stoneRoomNumber;
    private int familyMemberNumber;
    private int bonusPointNumber;
    private int cardPointNumber;

    public AgricolaUser()
    {
        initProperties();
    }

    public AgricolaUser(String userName)
    {
        super(userName);
        initProperties();
    }

    private void initProperties()
    {
        this.fieldNumber = 0;
        this.pastureNumber = 0;
        this.grainNumber = 0;
        this.vegetableNumber = 0;
        this.sheepNumber = 0;
        this.boarNumber = 0;
        this.cowNumber = 0;
        this.emptySpaceNumber = 0;
        this.fencedStableNumber = 0;
        this.clayRoomNumber = 0;
        this.stoneRoomNumber = 0;
        this.familyMemberNumber = 0;
        this.bonusPointNumber = 0;
        this.cardPointNumber = 0;
    }

    public int getFieldNumber()
    {
        return fieldNumber;
    }

    public void setFieldNumber(int fieldNumber)
    {
        this.fieldNumber = fieldNumber;
    }

    public int getPastureNumber()
    {
        return pastureNumber;
    }

    public void setPastureNumber(int pastureNumber)
    {
        this.pastureNumber = pastureNumber;
    }

    public int getGrainNumber()
    {
        return grainNumber;
    }

    public void setGrainNumber(int grainNumber)
    {
        this.grainNumber = grainNumber;
    }

    public int getVegetableNumber()
    {
        return vegetableNumber;
    }

    public void setVegetableNumber(int vegetableNumber)
    {
        this.vegetableNumber = vegetableNumber;
    }

    public int getSheepNumber()
    {
        return sheepNumber;
    }

    public void setSheepNumber(int sheepNumber)
    {
        this.sheepNumber = sheepNumber;
    }

    public int getBoarNumber()
    {
        return boarNumber;
    }

    public void setBoarNumber(int boarNumber)
    {
        this.boarNumber = boarNumber;
    }

    public int getCowNumber()
    {
        return cowNumber;
    }

    public void setCowNumber(int cowNumber)
    {
        this.cowNumber = cowNumber;
    }

    public int getEmptySpaceNumber()
    {
        return emptySpaceNumber;
    }

    public void setEmptySpaceNumber(int emptySpaceNumber)
    {
        this.emptySpaceNumber = emptySpaceNumber;
    }

    public int getFencedStableNumber()
    {
        return fencedStableNumber;
    }

    public void setFencedStableNumber(int fencedStableNumber)
    {
        this.fencedStableNumber = fencedStableNumber;
    }

    public int getClayRoomNumber()
    {
        return clayRoomNumber;
    }

    public void setClayRoomNumber(int clayRoomNumber)
    {
        this.clayRoomNumber = clayRoomNumber;
    }

    public int getStoneRoomNumber()
    {
        return stoneRoomNumber;
    }

    public void setStoneRoomNumber(int stoneRoomNumber)
    {
        this.stoneRoomNumber = stoneRoomNumber;
    }

    public int getFamilyMemberNumber()
    {
        return familyMemberNumber;
    }

    public void setFamilyMemberNumber(int familyMemberNumber)
    {
        this.familyMemberNumber = familyMemberNumber;
    }

    public int getBonusPointNumber()
    {
        return bonusPointNumber;
    }

    public void setBonusPointNumber(int bonusPointNumber)
    {
        this.bonusPointNumber = bonusPointNumber;
    }

    public int getCardPointNumber()
    {
        return cardPointNumber;
    }

    public void setCardPointNumber(int cardPointNumber)
    {
        this.cardPointNumber = cardPointNumber;
    }


}
