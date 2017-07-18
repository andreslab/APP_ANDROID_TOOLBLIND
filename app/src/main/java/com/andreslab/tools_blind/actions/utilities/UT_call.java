package com.andreslab.tools_blind.actions.utilities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by macbookpro on 7/18/17.
 */

public class UT_call {

    Activity activity;
    public UT_call(Activity act){
        activity = act;
    }

    public void call_phone(String phonenumber){
        Boolean permission = true;
        if(permission) {
            Intent intentcall = new Intent();
            intentcall.setAction(Intent.ACTION_CALL);
            intentcall.setData(Uri.parse("tel:" + phonenumber)); // set the Uri
            activity.startActivity(intentcall);
        }else{

        }
    }
}
