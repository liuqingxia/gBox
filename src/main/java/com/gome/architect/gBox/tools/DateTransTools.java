package com.gome.architect.gBox.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTransTools {
	
	/**
	 * String to Date
	 * @param str
	 * @param pattern
	 * @param locale
	 * @return
	 */
	public static Date parse(String str, String pattern, Locale locale) {
        if(str == null || pattern == null) {
            return null;
        }
        try {
            return new SimpleDateFormat(pattern, locale).parse(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 
	/**
	 * Date to  String 
	 * @param date
	 * @param pattern
	 * @param locale
	 * @return
	 */
    public static String format(Date date, String pattern, Locale locale) {
        if(date == null || pattern == null) {
            return null;
        }
        return new SimpleDateFormat(pattern, locale).format(date);
    }

}
