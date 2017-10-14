package com.abhinav.keepsafe;

/**
 * Created by Abhinav on 17/05/17.
 */
public final class Constants {

    public static final class ExtrasKey{
        public static final String INTENT_EXTRA_BUNDLE = "intent_extra_bundle";
        public static final String SELECTED_CATEGORY_POSITION = "selected_category";
    }

    public static final class NavigateTo{
        public static final int HOME = 1;
    }

    public static final class LaunchedBy {
        public static final String LOGIN = "login";
        public static final String HOME = "home";
    }

    public static final class Database {
        public static final String DATABASE_NAME = "keepsafe_db";
        public static final int DATABASE_VERSION = 1;
        public static final String T_BANK = "t_bank";
        public static final String T_EMAIL = "t_email";
        public static final String T_SOCIAL_NETWORK = "t_social_network";
        public static final String T_ECOMMERCE = "t_ecommerce";
    }
}
