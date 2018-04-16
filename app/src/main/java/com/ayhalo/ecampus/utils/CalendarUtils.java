package com.ayhalo.ecampus.utils;

import java.util.Calendar;

public class CalendarUtils {

	private static Calendar calendar = Calendar.getInstance();
	private static int theDay = calendar.get(Calendar.DAY_OF_MONTH);
	private static int theMonth = calendar.get(Calendar.MONTH) + 1;
	private static int theYear = calendar.get(Calendar.YEAR);

	public static void reset() {

		calendar.set(theYear, theMonth - 1, theDay);
		printCalendar();
	}
	
	public static Calendar getCanlendar(){
		return calendar;
	}

	public static int getToday() {
		return theDay;
	}

	public static int getTomonth() {
		return theMonth;
	}

	public static int getToyear() {
		return theYear;
	}

	public static int getCurrentYear() {
		return calendar.get(Calendar.YEAR);
	}

	public static int getCurrentMonth() {
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static int getCurrentDate() {
		return calendar.get(Calendar.DATE);
	}

	public static int getCurrentMaxNumOfMonth() {
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static int getCurrentFirstWeekdayOfMoth() {
		int today = getCurrentDate();
		calendar.set(Calendar.DATE, 1);
		int weekday = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		calendar.set(Calendar.DATE, today);
		return weekday;
	}

	public static void nextMonth() {
		calendar.add(Calendar.MONTH, 1);
	}

	public static void preMonth() {
		calendar.add(Calendar.MONTH, -1);
	}

	public static void printCalendar() {
		LogUtils.d("CalendarUtils", getCurrentYear() + "年" + getCurrentMonth() + "月" + getCurrentDate() + "日");

	}

	public static String getNextDay(int year, int month, int day) {
		calendar.set(year, month - 1, day + 1);
		int currentYear = getCurrentYear();
		int currentMonth = getCurrentMonth();
		int currentDate = getCurrentDate();
		reset();
		return currentYear + "-" + currentMonth + "-" + currentDate;
	}

	public static String getPreDay(int year, int month, int day) {
		calendar.set(year, month - 1, day - 1);
		int currentYear = getCurrentYear();
		int currentMonth = getCurrentMonth();
		int currentDate = getCurrentDate();
		reset();
		return currentYear + "-" + currentMonth + "-" + currentDate;
	}
	

	/**
	 * O 日 距离  T 日 的天数
	 * @param yearo
	 * @param montho
	 * @param dayo
	 * @param yeart
	 * @param montht
	 * @param dayt
	 * @return
	 */
	public static int getGapCount(int yearo, int montho, int dayo, int yeart, int montht, int dayt) {
		int oldYear = getCurrentYear();
		int oldMonth = getCurrentMonth();
		int oldDay = getCurrentDate();
		
		calendar.set(yearo, montho, dayo, 0, 0, 0);
		long time1 = calendar.getTime().getTime();
		
		calendar.set(yeart, montht, dayt, 0, 0, 0);
		long time2 = calendar.getTime().getTime();
		
		calendar.set(oldYear, oldMonth-1 , oldDay);
		
		return (int) ((time1-time2) / (1000 * 60 * 60 * 24));
	}
	
	public static boolean isPreDay( int year, int month, int day){
		return getGapCount(year, month, day, theYear, theMonth, theDay) < 0;
	}

	public static void set(int year, int month, int day) {
		// TODO Auto-generated method stub
		calendar.set(year, month, day);
	}

}
