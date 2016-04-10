package com.patrickwshaw.scoretracker.view.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.patrickwshaw.scoretracker.R;
import com.patrickwshaw.scoretracker.utility.general.UserUtil;
import com.patrickwshaw.scoretracker.utility.logging.LoggingUtil;
import com.patrickwshaw.scoretracker.view.fragment.general.SelectGameFragment;
import com.patrickwshaw.scoretracker.view.fragment.general.SelectUserFragment;


public class ScoreTracker extends Activity
{

    private LoggingUtil logger = new LoggingUtil("ScoreTracker", "ScoreTracker");
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        logger.logEnter("onCreate");

        setContentView(R.layout.activity_score_tracker);

        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if (fragment == null)
        {
            fragment = new SelectGameFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }

        logger.logExit("onCreate");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        logger.logEnter("onCreateOptionsMenu");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_score_tracker, menu);
        logger.logExit("onCreateOptionsMenu");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        logger.logEnter("onOptionsItemSelected");
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            logger.logExit("onOptionsItemSelected");
            return true;
        }

        logger.logExit("onOptionsItemSelected");
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState (Bundle outState)
    {
        super.onSaveInstanceState(outState);
        logger.logEnter("onSaveInstanceState");
        outState.putString(UserUtil.AGRICOLA_USER_TAG, "hello gov'nor");
        logger.logExit("onSaveInstanceState");
    }
}
