package com.patrickwshaw.scoretracker.view.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;

import com.patrickwshaw.scoretracker.R;
import com.patrickwshaw.scoretracker.utility.logging.LoggingUtil;

import java.util.logging.Logger;

/**
 * Created by Patrick on 1/11/2015.
 */
public class DialogFactory
{
    private static LoggingUtil logger = new LoggingUtil("DialogFactory", "DialogFactory");

    public static final AlertDialog createErrorDialog(Activity activity, int messasgeId)
    {
        logger.logEnter("createErrorDialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.dialogFactoryErrorDialogTitle);
        builder.setMessage(messasgeId);
        builder.setCancelable(false);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.cancel();
            }

        });

        logger.logExit("createErrorDialog");
        return builder.create();
    }

    public static final AlertDialog createDefaultErrorDialog(Activity activity)
    {
        logger.logEnter("createDefaultErrorDialog");
        logger.logExit("createDefaultErrorDialog");
        return DialogFactory.createErrorDialog(activity, R.string.dialogFactoryErrorDialogDefaultText);
    }

    public static final AlertDialog createInputStringDialog(Activity activity, int title, int message, int yesLabel, int noLabel, DialogInterface.OnClickListener onClickListener, EditText textEdit)
    {
        logger.logEnter("createInputStringDialog");
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);

        alert.setTitle(title);
        alert.setTitle(message);

        alert.setView(textEdit);

        alert.setPositiveButton(yesLabel, onClickListener);
        alert.setNegativeButton(noLabel, onClickListener);

        logger.logExit("createInputStringDialog");
        return alert.create();
    }

    public static final AlertDialog createYesNoDialog(Activity activity, int title, int message, int yesLabel, int noLabel, DialogInterface.OnClickListener listener)
    {
        logger.logEnter("createYesNoDialog");

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);

        alert.setTitle(title);
        alert.setMessage(message);

        alert.setPositiveButton(yesLabel, listener);
        alert.setNegativeButton(noLabel, listener);

        logger.logExit("createYesNoDialog");
        return alert.create();
    }

    public static final AlertDialog createListBoxDialog(Activity activity, int title, int itemsArray, DialogInterface.OnClickListener listener)
    {
        logger.logEnter("createListBoxDialog");

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);

        alert.setTitle(title);

        alert.setItems(itemsArray, listener);

        logger.logExit("createListBoxDialog");

        return alert.create();
    }

}
