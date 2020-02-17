package com.example.hp_wiki.helper;

import android.app.Activity;
import android.content.DialogInterface;

public class ErrorHandler {
    private Activity activity;

    public ErrorHandler(Activity activity) {
        this.activity = activity;
    }

    public void alertApiError() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);
        builder.setTitle("API connection failed");
        builder.setMessage("There seems to be a problem with the API. Please try again later.");
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void alertInternetError() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);
        builder.setTitle("No Internet Connection");
        builder.setMessage("It appears that you are offline. Please check your internet connection and try again");
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(1);
                dialog.dismiss();
            }
        });
        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
