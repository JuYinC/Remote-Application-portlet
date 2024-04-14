package com.remote.application.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import com.liferay.portal.kernel.util.DateRange;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;

public class MonthDateRange {
	public static SimpleDateFormat getDateTimeFormat(String pattern) {
		return getDateTimeFormat(pattern, Locale.ENGLISH);
	}
	
	public static SimpleDateFormat getDateTimeFormat(String pattern, Locale locale) {
		return getDateTimeFormat(pattern, locale, TimeZone.getTimeZone("Asia/Taipei"));
	}

	public static SimpleDateFormat getDateTimeFormat(String pattern, TimeZone timeZone) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		dateFormat.setTimeZone(timeZone);
		return dateFormat;
	}

	public static SimpleDateFormat getDateTimeFormat(String pattern, Locale locale, TimeZone timeZone) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, locale);
		dateFormat.setTimeZone(timeZone);
		return dateFormat;
	}
	
	Date startDatetime = null;
	Date endDatetime = null;

	public MonthDateRange(Date startDatetime, Date endDatetime) {

		super();
		this.startDatetime = startDatetime;
		this.endDatetime = endDatetime;
	}

	public Date getStartDatetime() {

		return startDatetime;
	}

	public Date getEndDatetime() {

		return endDatetime;
	}
	
	// 該周第一天 跟 第七天;
	public static DateRange getDateRangeOfWeek(int year_, int week_) {

		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.SUNDAY);
		cal.set(Calendar.YEAR, year_);
		cal.set(Calendar.WEEK_OF_YEAR, week_);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		Date startDatetime = cal.getTime();

		cal.add(Calendar.DAY_OF_WEEK, 6);
		Date endDatetime = cal.getTime();

		return new DateRange(startDatetime, endDatetime);
	}

	// 月初月底
	public static DateRange getDateRangeOfMonth(Calendar cal) {
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date startDatetime = pureDate(cal.getTime());
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date endDatetime = fullTime(cal.getTime());
		return new DateRange(startDatetime, endDatetime);
	}

	// 年初年底
	public static DateRange getDateRangeOfYear(Calendar cal) {
		cal.set(Calendar.DAY_OF_YEAR, cal.getActualMinimum(Calendar.DAY_OF_YEAR));
		Date startDatetime = pureDate(cal.getTime());
		cal.set(Calendar.DAY_OF_YEAR, cal.getActualMaximum(Calendar.DAY_OF_YEAR));
		Date endDatetime = fullTime(cal.getTime());
		return new DateRange(startDatetime, endDatetime);
	}

	// 取得本年度之到職日範圍;
	public static DateRange getDateRangeOfOnboardDateInThisYear(Calendar onboardDateCal) {
		Calendar nowCal = Calendar.getInstance();
		onboardDateCal.set(Calendar.YEAR, nowCal.get(Calendar.YEAR));
		if (nowCal.get(Calendar.MONTH) < onboardDateCal.get(Calendar.MONTH)) {
			onboardDateCal.add(Calendar.YEAR, -1);
		}
		Date startDatetime = onboardDateCal.getTime();
		onboardDateCal.add(Calendar.YEAR, 1);
		Date endDatetime = onboardDateCal.getTime();
		return new DateRange(startDatetime, endDatetime);
	}

	// 取得本年度之到職日範圍;
	public static DateRange getDateRangeOfOnboardDateInThisYear(Date onboardDate) {
		Calendar onboardDateCal = Calendar.getInstance();
		onboardDateCal.setTime(onboardDate);
		return getDateRangeOfOnboardDateInThisYear(onboardDateCal);
	}
	
	public static Date pureDate(Date date) {
		if (Validator.isNull(date)) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date fullTime(Date date) {
		if (Validator.isNull(date)) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
//			cal.set(Calendar.MILLISECOND, 999);// 多存了這個進去 db 會自動進位到下一天;
		return cal.getTime();
	}

	public static int diffYears(Date date1, Date date2) {
		return diffYears(date1, date2, 0);
	}

	public static int diffYears(Date date1, Date date2, int deductedDays) {
		// final long ONE_DAY_MILLIS = 1000L * 60 * 60 * 24;
		int gapDays = (int) diffDays(date1, date2);
		return Math.abs((gapDays - deductedDays) / 365);
	}

	public static long diffDays(Date date1, Date date2) {
		long diff = (date1.getTime() - date2.getTime()) / Time.DAY;
		return TimeUnit.DAYS.toDays(diff);
	}

	public static long diffHours(Date date1, Date date2) {
		return diffHours(date1, date2, true);
	}

	// 中間橫跨 12:00~13:00 是否要扣除? 預設扣除;
	public static long diffHours(Date date1, Date date2, boolean deductLunchBreak) {
		long diff = (date1.getTime() - date2.getTime()) / Time.HOUR;
		long hours = TimeUnit.HOURS.toHours(diff);

		if (deductLunchBreak) {
			// Calendar calendar = Calendar.getInstance();
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Taipei"));
			calendar.setTime(date1);
			int hour1 = calendar.get(Calendar.HOUR_OF_DAY);

			calendar.setTime(date2);
			int hour2 = calendar.get(Calendar.HOUR_OF_DAY);
			if (hour1 >= 13 && hour2 <= 12) {
				return hours - 1;
			}
		}
		return hours;
	}

	// 此 method 用在變數名稱 換成 語系 key;
	public static String splitCamal(String text) {
		// "dannyChen" -> "danny-chen";
		text = text.replaceAll("([a-z])([A-Z])", "$1-$2").toLowerCase();

		// 如有前後底線，也去除;
		text = text.replaceAll("^_*|_*$", "").toLowerCase();
		return text;
	}

	// 取得當年總週數;
	public static int getWeeks(int year) {
		Calendar gcal = new GregorianCalendar(year, Calendar.JANUARY, 1);
		gcal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		int weeks = gcal.getActualMaximum(Calendar.WEEK_OF_YEAR);
		if (weeks == 52 || weeks == 53) {
			// 如果最後一周跨年，則不算作本年度的週數
			gcal.add(Calendar.DATE, 7);
			if (gcal.get(Calendar.YEAR) > year) {
				weeks--;
			}
		}
		return weeks;
	}
	
}
