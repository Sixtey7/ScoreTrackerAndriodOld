package com.patrickwshaw.scoretracker.view.fragment.agricola.listeners;

import android.view.View;

import com.patrickwshaw.scoretracker.R;
import com.patrickwshaw.scoretracker.view.fragment.agricola.types.ScoreItem;
import com.patrickwshaw.scoretracker.utility.game.AgricolaUtil;
import com.patrickwshaw.scoretracker.utility.logging.LoggingUtil;

/**
 * Created by Patrick on 1/10/2015.
 */
public class SubAddOnClick implements View.OnClickListener
{
    private LoggingUtil logger = new LoggingUtil("SubAddOnClick", "SubAddOnClick");

    @Override
    public void onClick(View v)
    {
        logger.logEnter("onClick");
        Object scoreItemTag = v.getTag(R.id.AGRICOLA_SCORE_ITEM_TAG);
        if (scoreItemTag == null)
        {
            logger.e("No object found for tag: AGRICOLA_SCORE_ITEM_TAG!");
            //TODO: Show the user an error
            logger.logExit("onClick");
            return;
        }

        logger.d("ScoreItemTag was not null!");
        Object actionTag = v.getTag(R.id.AGRICOLA_SCORE_ITEM_ACTION);
        if (actionTag == null)
        {
            logger.e("No object found for tag: AGRICOLA_SCORE_ITEM_ACTION!");
            //TODO: Show the user an error
            logger.logExit("onClick");
            return;
        }
        logger.d("ActionTag was not null!");
        if ((scoreItemTag instanceof ScoreItem) && (actionTag instanceof AgricolaUtil.BUTTON_ACTIONS))
        {
            logger.d("Both elements were the correct instanceof");
            ScoreItem scoreItem = (ScoreItem) scoreItemTag;
            AgricolaUtil.BUTTON_ACTIONS action = (AgricolaUtil.BUTTON_ACTIONS) actionTag;

            if (actionTag == AgricolaUtil.BUTTON_ACTIONS.ADD)
            {
                scoreItem.setNumberAndCalculateScore(scoreItem.getNumber() + 1);
            }
            else
            {
                scoreItem.setNumberAndCalculateScore(scoreItem.getNumber() - 1);
            }
        }
        else
        {
            if (!(scoreItemTag instanceof ScoreItem))
            {
                logger.e("ScoreItemTag was not an instance of ScoreItem");
            }

            if (!(actionTag instanceof  AgricolaUtil.BUTTON_ACTIONS))
            {
                logger.e("ActionTag was not an instance of BUTTON_ACTIONS");
            }

            //TODO: Show the user an error
            logger.logExit("onClick");
            return;
        }

        AgricolaUtil.getAgricolaFragmentUtil().calculateAndSetTotalScore();
        logger.logExit("onClick");
    }
}
