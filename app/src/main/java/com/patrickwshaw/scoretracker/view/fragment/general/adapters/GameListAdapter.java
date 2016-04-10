package com.patrickwshaw.scoretracker.view.fragment.general.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.patrickwshaw.scoretracker.R;
import com.patrickwshaw.scoretracker.model.type.game.parent.Game;
import com.patrickwshaw.scoretracker.utility.logging.LoggingUtil;

import java.util.List;

/**
 * Created by Patrick on 1/22/2015.
 */
public class GameListAdapter extends ArrayAdapter<Game>
{
    private LoggingUtil logger = new LoggingUtil("GameListAdapter", "GameListAdapter");

    private Activity activity;
    public GameListAdapter(Activity activity, List<Game> games)
    {
        super(activity, 0, games);
        logger.logEnter("constructor");

        this.activity = activity;

        logger.logExit("constructor");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        logger.logEnter("getView");
        if (convertView == null)
        {
            logger.d("Convert View was null, inflating a new one");
            //TODO: Note, I read an article linked from reddit that said passing null here was the devil
            //TODO: Should look that up again and figure out why
            convertView = activity.getLayoutInflater().inflate(R.layout.fragment_select_game, null);
        }

        //get the user
        Game thisGame = getItem(position);

        //set the game name
        TextView userTitleText = (TextView) convertView.findViewById(R.id.fragment_select_game_name);
        userTitleText.setText(thisGame.getGameName());

        logger.logExit("getView");
        return convertView;
    }
}
