/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.remote.application.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.servlet.ServletContext;

import com.remote.application.bean.MonthDateRange;
import com.remote.application.constants.PortletKey;
import com.remote.application.constants.RemoteFormActivityConstants;
import com.remote.application.constants.RemoteFormConstants;
import com.remote.application.model.RemoteForm;
import com.remote.application.util.JCalendarUtil;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarBookingConstants;
import com.liferay.calendar.recurrence.Frequency;
import com.liferay.calendar.recurrence.PositionalWeekday;
import com.liferay.calendar.recurrence.Recurrence;
import com.liferay.calendar.recurrence.RecurrenceSerializer;
import com.liferay.calendar.recurrence.Weekday;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetLinkConstants;
import com.remote.application.service.base.RemoteFormLocalServiceBaseImpl;

/**
 * The implementation of the remote form local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.remote.application.service.RemoteFormLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author JuYin
 * @see com.remote.application.service.base.RemoteFormLocalServiceBaseImpl
 * @see com.remote.application.service.RemoteFormLocalServiceUtil
 */
public class RemoteFormLocalServiceImpl extends RemoteFormLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.remote.application.service.RemoteFormLocalServiceUtil} to access the remote form local service.
	 */
	
private final SimpleDateFormat _dateTimeFormat = MonthDateRange.getDateTimeFormat("yyyy-MM-dd HH:mm");
	
	public List<RemoteForm> search(String applicant, int status, Date startDateTime, Date endDateTime, String keywords,
			int start, int end, OrderByComparator obc) throws SystemException {
		
		return remoteFormFinder.search(applicant, status, startDateTime, endDateTime, keywords, start, end, obc);
	}
	
	public int countBySearch(String applicant, int status, Date startDateTime, Date endDateTime, String keywords)
			throws SystemException {
		
		return remoteFormFinder.countBySearch(applicant, status, startDateTime, endDateTime, keywords);
	}
	
	public List<String> fetchCycles(String applicant, int status) throws SystemException {

		return remoteFormFinder.fetchCycles(applicant, status);
	}
	
	@Deprecated
	public RemoteForm upsertRemoteForm(long remoteFormId, String creator, String applicant,
			Date remoteDate, String remark,	String extPayload, ServiceContext context)
			throws SystemException, PortalException {
		
		PortletConfig portletConfig = null;
		try {
			ServletContext servletContext = context.getLiferayPortletRequest().getHttpServletRequest()
					.getServletContext();
			portletConfig = PortalUtil.getPortletConfig(context.getCompanyId(), context.getPortletId(), servletContext);
		} catch (PortletException e) {
		}
		

		
		boolean isNewEntry = Boolean.FALSE;
		int activityKey = RemoteFormActivityConstants.TYPE_UPDATE;
		RemoteForm remoteForm = null;
		if (remoteFormId >0) {
			remoteForm = remoteFormPersistence.fetchByPrimaryKey(remoteFormId);
		} else {
			remoteForm = remoteFormPersistence.create(counterLocalService.increment());
			remoteForm.setCompanyId(context.getCompanyId());
			remoteForm.setGroupId(context.getScopeGroupId());
			remoteForm.setCreateDate(context.getCreateDate(new Date()));
			remoteForm.setCuserId(context.getUserId());
			
			activityKey = RemoteFormActivityConstants.TYPE_CREATE;
			isNewEntry = Boolean.TRUE;
		}
		

		
		remoteForm.setCreator(creator);
		remoteForm.setApplicant(applicant);
		remoteForm.setRemoteDate(remoteDate);
		remoteForm.setRemark(remark);
		if(!Validator.isBlank(extPayload)) {
			remoteForm.setExtPayload(extPayload);
		}	
				

		User applicantUser = userLocalService.fetchUserByScreenName(context.getCompanyId(), applicant);
		String applicantUserScreenName = Validator.isNotNull(applicantUser) ? applicantUser.getFullName() : applicant;
		DateFormat dateFormat = MonthDateRange.getDateTimeFormat("yyyy-MM-dd");		
		String remoteDateS = dateFormat.format(remoteDate);			
		String formTitle = getFormTitle(portletConfig, context.getLocale(), applicantUserScreenName, remoteDateS);	
		remoteForm.setFormTitle(formTitle);
		remoteForm.setModifiedDate(context.getModifiedDate(new Date()));
		remoteForm.setMuserId(context.getUserId());
		remoteForm = remoteFormPersistence.update(remoteForm);
		
		updateAsset(remoteForm.getCuserId(), remoteForm, context);
		
		context.setAttribute("isNewEntry", isNewEntry);
		
		updateActivity(remoteForm, context, activityKey);
		
		return remoteForm;
	}
	
	public RemoteForm upsertRemoteForm(long remoteFormId, String creator, String applicant,
			Date remoteDate, Date endDateTime, String remark, String extPayload, String dayTab, ServiceContext context)
			throws SystemException, PortalException {		
		
		PortletConfig portletConfig = null;
		try {
			ServletContext servletContext = context.getLiferayPortletRequest().getHttpServletRequest()
					.getServletContext();
			portletConfig = PortalUtil.getPortletConfig(context.getCompanyId(), context.getPortletId(), servletContext);
		} catch (PortletException e) {
		}
		

		
		boolean isNewEntry = Boolean.FALSE;
		int activityKey = RemoteFormActivityConstants.TYPE_UPDATE;
		RemoteForm remoteForm = null;
		if (remoteFormId >0) {
			remoteForm = remoteFormPersistence.fetchByPrimaryKey(remoteFormId);
		} else {
			remoteForm = remoteFormPersistence.create(counterLocalService.increment());
			remoteForm.setCompanyId(context.getCompanyId());
			remoteForm.setGroupId(context.getScopeGroupId());
			remoteForm.setCreateDate(context.getCreateDate(new Date()));
			remoteForm.setCuserId(context.getUserId());
			
			activityKey = RemoteFormActivityConstants.TYPE_CREATE;
			isNewEntry = Boolean.TRUE;
		}
		

		
		remoteForm.setCreator(creator);
		remoteForm.setApplicant(applicant);
		remoteForm.setRemoteDate(remoteDate);
		if (Validator.isNotNull(endDateTime)) {
			remoteForm.setEndDateTime(endDateTime);
		}
		remoteForm.setRemark(remark);
		if(!Validator.isBlank(extPayload)) {
			remoteForm.setExtPayload(extPayload);
		}
		
		_dateTimeFormat.setTimeZone(TimeZoneUtil.GMT);
		Date calStartDateTime = GetterUtil.getDate(remoteDate, _dateTimeFormat);
		Date calEndDateTime = GetterUtil.getDate(endDateTime, _dateTimeFormat);
		
		CalendarBooking calendarBooking = addEventToCalendar(context, applicant, calStartDateTime, calEndDateTime, dayTab);		
		remoteForm.setCalendarBookingId(calendarBooking.getCalendarBookingId());
		
		long userId = context.getUserId();
		User user = userLocalService.fetchUser(userId);
		TimeZone timeZone = Validator.isNotNull(user) ? user.getTimeZone() : TimeZoneUtil.GMT;

		User applicantUser = userLocalService.fetchUserByScreenName(context.getCompanyId(), applicant);
		String applicantUserScreenName = Validator.isNotNull(applicantUser) ? applicantUser.getFullName() : applicant;
		
		String remotePeriodDisplay = getRemotePeriodDisplay(remoteForm.getRemoteDate(), remoteForm.getEndDateTime(),
				timeZone);	
		String formTitle = getFormTitle(portletConfig, context.getLocale(), applicantUserScreenName, remotePeriodDisplay);	
		remoteForm.setFormTitle(formTitle);
		remoteForm.setDayTab(dayTab);
		remoteForm.setModifiedDate(context.getModifiedDate(new Date()));
		remoteForm.setMuserId(context.getUserId());
		remoteForm = remoteFormPersistence.update(remoteForm);
		
		updateAsset(remoteForm.getCuserId(), remoteForm, context);
		
		context.setAttribute("isNewEntry", isNewEntry);
		
		updateActivity(remoteForm, context, activityKey);
		
		return remoteForm;
	}
	
	public void removeRemoteForm(long remoteFormId) throws SystemException, PortalException {

		RemoteForm remoteForm = remoteFormPersistence.fetchByPrimaryKey(remoteFormId);
		workflowInstanceLinkLocalService.deleteWorkflowInstanceLink(remoteForm.getCompanyId(), remoteForm.getGroupId(),
				RemoteForm.class.getName(), remoteFormId);
		AssetEntry assetEntry = assetEntryLocalService.fetchEntry(RemoteForm.class.getName(), remoteFormId);
		socialActivityLocalService.deleteActivities(assetEntry);
		assetEntryPersistence.remove(assetEntry);

		assetLinkLocalService.deleteLinks(assetEntry.getEntryId());

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(RemoteForm.class);
		indexer.delete(remoteForm);
		
		if(remoteForm.getStatus() != WorkflowConstants.STATUS_DENIED) {
			CalendarBookingLocalServiceUtil.deleteCalendarBooking(remoteForm.getCalendarBookingId());
		}
		
		remoteFormPersistence.remove(remoteForm);
	}
	
	public RemoteForm updateStatus(long userId, long remoteFormId, int status, ServiceContext context)
			throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		RemoteForm remoteForm = remoteFormPersistence.fetchByPrimaryKey(remoteFormId);

		remoteForm.setModifiedDate(context.getModifiedDate(now));
		remoteForm.setStatus(status);
		remoteForm.setStatusByUserId(user.getUserId());
		remoteForm.setStatusByUsername(user.getFullName());
		remoteForm.setStatusDate(context.getModifiedDate(now));

		remoteForm = super.updateRemoteForm(remoteForm);

		if (status != WorkflowConstants.STATUS_APPROVED) {
			return remoteForm;
		}

		updateAsset(userId, remoteForm, context);

		int activityKey = RemoteFormActivityConstants.TYPE_APPROVE;
		updateActivity(remoteForm, context, activityKey);

		return remoteForm;
	}
	
	public List<RemoteForm> searchForStatistics(String applicant, int[] statuses, Date startDateTime,
			Date endDateTime) throws SystemException {
		return remoteFormFinder.searchForStatistics(applicant, statuses, startDateTime, endDateTime);
	}
	
	public List<RemoteForm> checkOverlappingTimes(String applicant, Date startDateTime) throws SystemException {
		return remoteFormFinder.checkOverlappingTimes(applicant, startDateTime);
	}
	
	protected void updateAsset(long userId, RemoteForm remoteForm, ServiceContext context)
			throws PortalException, SystemException {
		AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId, remoteForm.getGroupId(), RemoteForm.class.getName(), 
				remoteForm.getPrimaryKey(), context.getAssetCategoryIds(), context.getAssetTagNames());
		
		String classUuid = null;
		long classTypeId = 0;
		boolean visible = Boolean.TRUE;
		Date startDate = null;
		Date endDate = null;
		Date expirationDate = null;
		String mimeType = null;
		String title = remoteForm.getFormTitle();
		String description = remoteForm.getRemark();
		String summary = title;
		String url = null;
		String layoutUuid = null;
		int height = 0;
		int width = 0;
		int priority = 0;
		boolean sync = Boolean.FALSE;

		assetEntry = assetEntryLocalService.updateEntry(userId, remoteForm.getGroupId(), context.getCreateDate(new Date()), 
				context.getModifiedDate(new Date()), RemoteForm.class.getName(), remoteForm.getPrimaryKey(), classUuid, 
				classTypeId, context.getAssetCategoryIds(), context.getAssetTagNames(), visible, startDate, endDate, 
				expirationDate, mimeType, title, description, summary, url, layoutUuid, height, width, priority, sync);
		
		assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(), context.getAssetLinkEntryIds(),
				AssetLinkConstants.TYPE_RELATED);;
	}	

	protected void updateActivity(RemoteForm remoteForm, ServiceContext context, int activityKey)
			throws PortalException, SystemException {

		String extraData = remoteForm.getExtraDataJSON();
				
		long receiverUserId = 0;
		socialActivityLocalService.addActivity(context.getUserId(), context.getScopeGroupId(), RemoteForm.class.getName(), 
				remoteForm.getPrimaryKey(), activityKey, extraData.toString(), receiverUserId);
	}
	
	private CalendarBooking addEventToCalendar(ServiceContext serviceContext, String applicant, Date remoteDate, Date endDateTime, String dayTab)
			throws PortalException, SystemException {
//		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
//		PortletPreferences portletPreferences = request.getPreferences();

//		HttpServletRequest request = serviceContext.getRequest();// 這個會是 null;

		long ownerId = PortletKeys.PREFS_OWNER_ID_DEFAULT;
		int ownerType = PortletKeys.PREFS_OWNER_TYPE_LAYOUT;

		long plid1 = PortalUtil.getPlidFromPortletId(serviceContext.getScopeGroupId(), PortletKey.REMOTE_APPLICATION);
		com.liferay.portal.model.PortletPreferences portletPreferences1 = PortletPreferencesLocalServiceUtil
				.getPortletPreferences(ownerId, ownerType, plid1, PortletKey.REMOTE_APPLICATION);
		PortletPreferences portletPreferences = PortletPreferencesFactoryUtil
				.fromDefaultXML(portletPreferences1.getPreferences());

		long userId = serviceContext.getUserId();
		long attendanceCalendarId = GetterUtil.getLong(portletPreferences.getValue("employeeAttendanceCalendarId", "444921"));
//		long attendanceCalendarId = 22013;

//		long[] childCalendarIds = ParamUtil.getLongValues(request, "childCalendarIds", new long[0]);
		long[] childCalendarIds = new long[0];
		long parentCalendarBookingId = CalendarBookingConstants.PARENT_CALENDAR_BOOKING_ID_DEFAULT;
//		Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(request, "title");
		Map<Locale, String> titleMap = new HashMap<Locale, String>();

		User applicantUser = UserLocalServiceUtil.fetchUserByScreenName(serviceContext.getCompanyId(),
				applicant);
		String applicantUserName = Validator.isNotNull(applicantUser) ? applicantUser.getFirstName() : StringPool.BLANK;
		String title = applicantUserName + "-" + RemoteFormConstants.REMOTEFORM_TYPE;
		titleMap.put(Locale.TAIWAN, title);

//		Map<Locale, String> descriptionMap = LocalizationUtil.getLocalizationMap(request, "description");
		Map<Locale, String> descriptionMap = new HashMap<Locale, String>();
//		descriptionMap.put(Locale.TAIWAN, leaveForm.getFormTitle() + "<br>" + leaveForm.getReason());
		descriptionMap.put(Locale.TAIWAN, title);

//		String location = ParamUtil.getString(request, "location");
		String location = "Event come from Remoteform portlet.";
//		Calendar startTimeJCalendar = getJCalendar(resourceRequest, "startTime");
//		Calendar endTimeJCalendar = getJCalendar(resourceRequest, "endTime");
//		boolean allDay = ParamUtil.getBoolean(request, "allDay", Boolean.TRUE);

		long startTime = remoteDate.getTime();
		long endTime = endDateTime.getTime();
		boolean allDay = !dayTab.equals(RemoteFormConstants.DAY_TABS.THAT_DAY);
		if (allDay) {
			endTime = MonthDateRange.fullTime(endDateTime).getTime();
		}


		String recurrence = getRecurrence();
		long[] reminders = getReminders();
		String[] remindersType = getRemindersType();

		// ServiceContext serviceContext =
		// ServiceContextFactory.getInstance(CalendarBooking.class.getName(), request);		
		
		CalendarBooking calendarBooking = CalendarBookingLocalServiceUtil.addCalendarBooking(userId, attendanceCalendarId, childCalendarIds, parentCalendarBookingId, titleMap, descriptionMap, location, 
				startTime, endTime, allDay, recurrence, reminders[0], remindersType[0], reminders[1], remindersType[1], serviceContext);
		
		return calendarBooking;		
		
	}
	
	protected long[] getReminders() {
//		long firstReminder = ParamUtil.getInteger(request, "reminderValue0");
//		long firstReminderDuration = ParamUtil.getInteger(request, "reminderDuration0");
//		long secondReminder = ParamUtil.getInteger(request, "reminderValue1");
//		long secondReminderDuration = ParamUtil.getInteger(request, "reminderDuration1");

		long firstReminder = 0L;
		long firstReminderDuration = 0L;
		long secondReminder = 0L;
		long secondReminderDuration = 0L;

		return new long[] { firstReminder * firstReminderDuration * Time.SECOND,
				secondReminder * secondReminderDuration * Time.SECOND };
	}

	protected String[] getRemindersType() {
//		String firstReminderType = ParamUtil.getString(request, "reminderType0");
//		String secondReminderType = ParamUtil.getString(request, "reminderType1");
		String firstReminderType = StringPool.BLANK;
		String secondReminderType = StringPool.BLANK;

		return new String[] { firstReminderType, secondReminderType };
	}

	protected String getRecurrence() {
		// boolean repeat = ParamUtil.getBoolean(request, "repeat");
		boolean repeat = Boolean.FALSE;

		if (!repeat) {
			return null;
		}

		Recurrence recurrence = new Recurrence();
		int count = 0;
//		String ends = ParamUtil.getString(request, "ends");
		String ends = StringPool.BLANK;
		if (ends.equals("after")) {
//			count = ParamUtil.getInteger(request, "count");
			count = 0;
		}
		recurrence.setCount(count);

//		Frequency frequency = Frequency.parse(ParamUtil.getString(request, "frequency"));
		Frequency frequency = Frequency.parse(StringPool.BLANK);
		recurrence.setFrequency(frequency);

//		int interval = ParamUtil.getInteger(request, "interval");
		int interval = 0;
		recurrence.setInterval(interval);

		java.util.Calendar untilJCalendar = null;

		if (ends.equals("on")) {
//			int untilDateDay = ParamUtil.getInteger(request, "untilDateDay");
//			int untilDateMonth = ParamUtil.getInteger(request, "untilDateMonth");
//			int untilDateYear = ParamUtil.getInteger(request, "untilDateYear");

			int untilDateDay = 0;
			int untilDateMonth = 0;
			int untilDateYear = 0;

			untilJCalendar = CalendarFactoryUtil.getCalendar();

			untilJCalendar.set(java.util.Calendar.DATE, untilDateDay);
			untilJCalendar.set(java.util.Calendar.MONTH, untilDateMonth);
			untilJCalendar.set(java.util.Calendar.YEAR, untilDateYear);
		}

		recurrence.setUntilJCalendar(untilJCalendar);

		List<PositionalWeekday> positionalWeekdays = new ArrayList<PositionalWeekday>();

		if (frequency == Frequency.WEEKLY) {
			for (Weekday weekday : Weekday.values()) {
//				boolean checked = ParamUtil.getBoolean(request, weekday.getValue());
				boolean checked = Boolean.FALSE;

				if (checked) {
					positionalWeekdays.add(new PositionalWeekday(weekday, 0));
				}
			}
		} else if ((frequency == Frequency.MONTHLY) || (frequency == Frequency.YEARLY)) {

//			boolean repeatOnWeekday = ParamUtil.getBoolean(request, "repeatOnWeekday");
			boolean repeatOnWeekday = Boolean.FALSE;
			if (repeatOnWeekday) {
//				int position = ParamUtil.getInteger(request, "position");
				int position = 0;

//				Weekday weekday = Weekday.parse(ParamUtil.getString(request, "weekday"));
				Weekday weekday = Weekday.parse(StringPool.BLANK);
				positionalWeekdays.add(new PositionalWeekday(weekday, position));

				if (frequency == Frequency.YEARLY) {
//					List<Integer> months = Arrays.asList(ParamUtil.getInteger(request, "startTimeMonth"));
					List<Integer> months = Arrays.asList(0);
					recurrence.setMonths(months);
				}
			}
		}

		recurrence.setPositionalWeekdays(positionalWeekdays);

//		String[] exceptionDates = StringUtil.split(ParamUtil.getString(request, "exceptionDates"));
		String[] exceptionDates = StringUtil.split(StringPool.BLANK);
		for (String exceptionDate : exceptionDates) {
			recurrence.addExceptionDate(JCalendarUtil.getJCalendar(Long.valueOf(exceptionDate)));
		}

		return RecurrenceSerializer.serialize(recurrence);
	}
	
	private String getFormTitle(PortletConfig portletConfig, Locale locale, String applicant, String remotePeriodDisplay) {
		
		String applicantLabel = LanguageUtil.get(portletConfig, locale, "applicant");		
		
		StringBuilder sb = new StringBuilder();
		sb.append(applicantLabel);
		sb.append(StringPool.COLON);
		sb.append(StringPool.SPACE);
		sb.append(applicant);
		sb.append(StringPool.OPEN_PARENTHESIS);
		sb.append(remotePeriodDisplay);
		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}	
	
	public String getRemotePeriodDisplay(Date start, Date end, TimeZone timeZone) {

		DateFormat dateFormat = MonthDateRange.getDateTimeFormat("yyyy-MM-dd", timeZone);
		String startDatetime = dateFormat.format(start);
		String endDatetime = StringPool.BLANK;
		if(end != null) {
			endDatetime = dateFormat.format(end);
		}

		// 起迄日期+時間 都相同，表示當天整日;
		if (start.getTime() == end.getTime() || end == null) {
			return startDatetime + "(all-day)";
		} else {
			// 起迄當日的話，就顯示到時間
			//if (startDatetime.equals(endDatetime))
			DateFormat dateTimeFormat = MonthDateRange.getDateTimeFormat("yyyy-MM-dd HH:mm");
			DateFormat timeFormat = MonthDateRange.getDateTimeFormat("HH:mm");
			startDatetime = dateTimeFormat.format(start);
			endDatetime = timeFormat.format(end);
		}

		return startDatetime + " ~ " + endDatetime;
	}
}