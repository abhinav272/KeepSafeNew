package com.abhinav.keepsafe.utils;

import android.util.Patterns;

import com.abhinav.keepsafe.Constants;

import java.util.regex.Pattern;

/**
 * Created by abhinav.sharma on 13/10/17.
 */

public class KeepSafeRegexUtil {

    public static boolean isValidEmail(CharSequence emailId) {
        return Patterns.EMAIL_ADDRESS.matcher(emailId).matches();
    }

    public static boolean isValidPhoneNumber(CharSequence phone){
        String phoneGarbage = "[()\\s-\\s+]+";
        String phoneRegex = "\\d{10}";
        phone = phone.toString().replaceAll(phoneGarbage, "");
        Pattern pattern = Pattern.compile(phoneRegex);
        return phone != null && pattern.matcher(phone).matches() && phone.length() == 10;
    }
}
