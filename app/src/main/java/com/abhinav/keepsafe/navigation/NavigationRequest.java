package com.abhinav.keepsafe.navigation;

import android.os.Bundle;

/**
 * Created by Abhinav on 17/05/17.
 */
public class NavigationRequest {
    private final int navigateTo;
    private final String launchedBy;
    private final Bundle bundle;

    private NavigationRequest(Builder builder) {
        navigateTo = builder.navigateTo;
        launchedBy = builder.launchedBy;
        bundle = builder.bundle;
    }

    public int getNavigateTo() {
        return navigateTo;
    }

    public String getLaunchedBy() {
        return launchedBy;
    }

    public Bundle getBundle() {
        return bundle;
    }


    public static class Builder {
        private int navigateTo;
        private String launchedBy;
        private Bundle bundle;

        public Builder setNavigateTo(int navigateTo) {
            this.navigateTo = navigateTo;
            return this;
        }

        public Builder setLaunchedBy(String launchedBy) {
            this.launchedBy = launchedBy;
            return this;
        }

        public Builder setBundle(Bundle bundle) {
            this.bundle = bundle;
            return this;
        }

        public NavigationRequest build() {
            return new NavigationRequest(this);
        }

    }

}
