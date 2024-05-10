package com.moutamid.tiptop.utilis;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Constants {
	public static final String DATEFORMATE = "dd/MM/yyyy";  // 25/11/2024
	public static final String DATEFORMATE1 = "MM/dd/yyyy"; // 11/25/2024
	public static final String DATEFORMATE2 = "yyyy/dd/MM"; // 2024/25/11
	public static final String DATEFORMATE3 = "dd/MM/yyyy HH:mm"; // 25/11/2024 17:35
	public static final String DATEFORMATE4 = "dd/MM/yyyy hh:mm"; // 25/11/2024 05:35
	public static final String DATEFORMATE5 = "dd/MM/yyyy hh:mm:ss"; // 25/11/2024 05:35:10
	public static final String DATEFORMATE6 = "dd/MM/yyyy hh:mm:ss aa"; // 25/11/2024 05:35:10 AM (or PM)

// Just change the variable of your need or you can make your own pref
    public static String getFormatedDate(long date) {
        return new SimpleDateFormat(DATEFORMATE, Locale.getDefault()).format(date);
    }

/** You can call this function using this Constants.getFormatedDate(new Date().getTimestamps()) */

}
