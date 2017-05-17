package com.abhinav.keepsafe.navigation;

import android.content.Context;
import android.content.Intent;

import com.abhinav.keepsafe.Constants;
import com.abhinav.keepsafe.home.HomeActivity;

/**
 * Created by Abhinav on 17/05/17.
 */
public class Navigator {

    public static void navigateTo(Context context, NavigationRequest navigationRequest){
        switch (navigationRequest.getNavigateTo()){
            case Constants.NavigateTo.HOME:
                Intent intent = new Intent(context, HomeActivity.class);
                if (navigationRequest.getBundle() != null) {
                    intent.putExtra(Constants.ExtrasKey.INTENT_EXTRA_BUNDLE, navigationRequest.getBundle());
                }
                context.startActivity(intent);
                break;
        }
    }
}
