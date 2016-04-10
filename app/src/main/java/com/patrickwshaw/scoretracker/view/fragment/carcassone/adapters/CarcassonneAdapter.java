package com.patrickwshaw.scoretracker.view.fragment.carcassone.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.patrickwshaw.scoretracker.R;
import com.patrickwshaw.scoretracker.model.type.user.parent.User;
import com.patrickwshaw.scoretracker.utility.logging.LoggingUtil;
import com.patrickwshaw.scoretracker.view.fragment.carcassone.types.parent.CarcassonneScoreable;

import java.util.List;

/**
 * Created by Patrick on 1/23/2015.
 */
public class CarcassonneAdapter extends ArrayAdapter<CarcassonneScoreable>
{
    public LoggingUtil logger = new LoggingUtil("CarcassonneAdapter", "CarcassonneAdapter");

    private Activity activity;

    public CarcassonneAdapter(Activity activity, List<CarcassonneScoreable> inScoreableList)
    {
        super(activity, 0, inScoreableList);
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
            convertView = activity.getLayoutInflater().inflate(R.layout.fragment_carcassonne, null);
        }

        //get the user
        CarcassonneScoreable scoreable = getItem(position);

        //set the player name
        TextView carcassonneActionName = (TextView) convertView.findViewById(R.id.fragmentCarcassonneActionName);
        carcassonneActionName.setText(scoreable.getDisplayText());

        //set the player score
        TextView carcassonneActionScore = (TextView) convertView.findViewById(R.id.fragmentCarcassonneActionScore);
        carcassonneActionScore.setText(Integer.toString(scoreable.getScore()));

        logger.logExit("getView");
        return convertView;
    }

}
