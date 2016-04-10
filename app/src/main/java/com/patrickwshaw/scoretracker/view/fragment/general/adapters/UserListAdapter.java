package com.patrickwshaw.scoretracker.view.fragment.general.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.patrickwshaw.scoretracker.R;
import com.patrickwshaw.scoretracker.model.type.user.parent.User;
import com.patrickwshaw.scoretracker.utility.logging.LoggingUtil;

import java.util.List;

/**
 * Created by Patrick on 1/16/2015.
 */
public class UserListAdapter extends ArrayAdapter<User>
{
    public LoggingUtil logger = new LoggingUtil("UserListAdapter", "UserListAdapter");

    private Activity activity;

    public UserListAdapter(Activity activity, List<User> inUserList)
    {
        super(activity, 0, inUserList);
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
            convertView = activity.getLayoutInflater().inflate(R.layout.fragment_select_user, null);
        }

        //get the user
        User u = getItem(position);

        //set the player name
        TextView userTitleText = (TextView) convertView.findViewById(R.id.select_user_title_text);
        userTitleText.setText(u.getPlayerName());

        //set the player's score
        TextView userScoreText = (TextView) convertView.findViewById(R.id.select_user_points_text);
        userScoreText.setText(u.getTotalScore() + "");

        //TODO: Eventually we'll have at least one more property - the score - to set here

        logger.logExit("getView");
        return convertView;
    }
}
