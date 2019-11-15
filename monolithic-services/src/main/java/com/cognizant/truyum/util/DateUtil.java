package com.cognizant.truyum.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 810491
 *
 */
public class DateUtil {
	public static Date convertToDate(String date) throws ParseException {
		return new SimpleDateFormat("dd/MM/yyyy").parse(date);
	}
}
