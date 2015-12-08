package com.gmail.ivanytskyy.vitaliy.controller;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
 * Task #1/2015/11/29 (pet web project #1)
 * InputDataValidator
 * @version 1.01 2015.11.29
 * @author Vitaliy Ivanytskyy
 */
public class InputDataValidator {	
	public static boolean isDay(String day){
		if(day == null || day.equals("")){
			return false;
		}
		String regexOfDay = "[0-9]+";
		Pattern patternOfDay = Pattern.compile(regexOfDay);			
		Matcher matcherOfDay = patternOfDay.matcher(day);
		boolean isNumber = matcherOfDay.matches();
		boolean isDay = false;
		if(isNumber){
			isDay = (Integer.valueOf(day) > 0 && Integer.valueOf(day) <= 31) ? true : false;
		}
		return isDay;
	}
	public static boolean isMonth(String month){
		if(month == null || month.equals("")){
			return false;
		}
		String regexOfMonth = "[0-9]+";
		Pattern patternOfMonth = Pattern.compile(regexOfMonth);			
		Matcher matcherOfMonth = patternOfMonth.matcher(month);
		boolean isNumber = matcherOfMonth.matches();
		boolean isMonth = false;
		if(isNumber){
			isMonth = (Integer.valueOf(month) > 0 && Integer.valueOf(month) <= 12) ? true : false;
		}
		return isMonth;
	}
	public static boolean isYear(String year){
		if(year == null || year.equals("")){
			return false;
		}
		String regexOfYear = "[0-9]+";
		Pattern patternOfYear = Pattern.compile(regexOfYear);			
		Matcher matcherOfYear = patternOfYear.matcher(year);
		boolean isNumber = matcherOfYear.matches();
		boolean isYear = false;
		if(isNumber){
			isYear = (Integer.valueOf(year) > 1970 && Integer.valueOf(year) <= 2050) ? true : false;
		}
		return isYear;
	}
	public static boolean isPositiveIntNumber(String numberAsStr){
		if(numberAsStr == null || numberAsStr.equals("")){
			return false;
		}
		String regexOfNumber = "[0-9]{1,10}";
		Pattern patternOfNumber = Pattern.compile(regexOfNumber);			
		Matcher matcherOfNumber = patternOfNumber.matcher(numberAsStr);
		boolean isNumber = matcherOfNumber.matches();
		boolean isPositiveIntNumber = false;
		if(isNumber){
			isPositiveIntNumber = (Integer.valueOf(numberAsStr) > 0 && Integer.valueOf(numberAsStr) <= Integer.MAX_VALUE) ? true : false;
		}
		return isPositiveIntNumber;
	}
}