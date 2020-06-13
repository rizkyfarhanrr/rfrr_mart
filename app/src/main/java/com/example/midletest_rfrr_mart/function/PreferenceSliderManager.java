package com.example.midletest_rfrr_mart.function;

import android.content.Context;
import android.content.SharedPreferences;

    public class PreferenceSliderManager {

        SharedPreferences preferences;
        SharedPreferences.Editor editorPreferences;
        Context context;

        int PRIVATE_MODE = 0;

        private static final String PREF_NAME = "intro_TRAVEL_APPS";
        private static final String IS_FIRST_TIME_LAUNCH = "isFirstTimeLaunch";

        public PreferenceSliderManager(Context context) {
            this.context = context;
            preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
            editorPreferences = preferences.edit();
        }

        public void setFirstTimeLaunch(boolean isFirstTime) {
            editorPreferences.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
            editorPreferences.commit();
        }

        public boolean isFirstTimeLaunch(){
            return preferences.getBoolean(IS_FIRST_TIME_LAUNCH, true);
        }
}
