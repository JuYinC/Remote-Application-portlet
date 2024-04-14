package com.remote.application.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.remote.application.bean.MonthDateRange;

public class RemoteUtil {

	public static DateFormat getDateTimeFormat() {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setTimeZone(TimeZoneUtil.GMT);
		return dateFormat;
	}

	public static DateFormat getDateFormat() {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setTimeZone(TimeZoneUtil.GMT);
		return dateFormat;
	}

	public static DateFormat getH_MFormat() {

		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		dateFormat.setTimeZone(TimeZoneUtil.GMT);
		return dateFormat;
	}
	
	public static MonthDateRange getMonthDateRange(Calendar cal) {
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date startDatetime = cal.getTime();
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		Date endDatetime = cal.getTime();
		return new MonthDateRange(startDatetime, endDatetime);
	}
}
