package com.remote.application.portlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.remote.application.bean.MonthDateRange;
import com.remote.application.model.RemoteForm;
import com.remote.application.service.RemoteFormLocalServiceUtil;
import com.remote.application.model.RemoteForm;
import com.remote.application.service.RemoteFormLocalServiceUtil;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarBookingConstants;
import com.liferay.calendar.recurrence.Frequency;
import com.liferay.calendar.recurrence.PositionalWeekday;
import com.liferay.calendar.recurrence.Recurrence;
import com.liferay.calendar.recurrence.RecurrenceSerializer;
import com.liferay.calendar.recurrence.Weekday;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.portal.NoSuchWorkflowDefinitionLinkException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.model.WorkflowInstanceLink;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.service.WorkflowInstanceLinkLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.remote.application.constants.PortletKey;
import com.remote.application.constants.RemoteFormConstants;
import com.remote.application.util.JCalendarUtil;
import com.remote.application.util.Mailer;
import com.remote.application.util.RemoteUtil;

public class RemoteFormPortlet extends MVCPortlet {

	private final Log _log = LogFactoryUtil.getLog(RemoteFormPortlet.class);
	private final DateFormat _dateFormat = RemoteUtil.getDateFormat();
	private final SimpleDateFormat _dateTimeFormat = MonthDateRange.getDateTimeFormat("yyyy-MM-dd HH:mm");
	
	public void upsertRemoteForm(ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortalException, SystemException, IOException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		ServiceContext context = ServiceContextFactory.getInstance(RemoteForm.class.getName(), actionRequest);
		_dateFormat.setTimeZone(TimeZoneUtil.GMT);
		//_dateTimeFormat.setTimeZone(TimeZoneUtil.GMT);

		
		long remoteFormId = ParamUtil.getLong(actionRequest, "remoteFormId", 0l);
		String creator = ParamUtil.getString(actionRequest, "creator");
		String applicant = ParamUtil.getString(actionRequest, "applicant");
		
		String startDate = ParamUtil.getString(actionRequest, "remoteDate");
		String startTime = ParamUtil.getString(actionRequest, "startTime");
		String endTime = ParamUtil.getString(actionRequest, "endTime");
		
		Date startDateTime = null;
		Date endDateTime = null;
		String dayTabs = ParamUtil.getString(actionRequest, "dayTabs");
		
		// 當天整日的話，就當作起迄日相同; 不看小時了;
		if (dayTabs.equals(RemoteFormConstants.DAY_TABS.ALL_DAY)) {
			startDateTime = GetterUtil.getDate(startDate, _dateFormat);
			endDateTime = startDateTime;

			// 當天請幾個小時的話，就要區分時間;
		} else if (dayTabs.equals(RemoteFormConstants.DAY_TABS.THAT_DAY)) {
			startDateTime = GetterUtil.getDate(startDate + StringPool.SPACE + startTime, _dateTimeFormat);
			endDateTime = GetterUtil.getDate(startDate + StringPool.SPACE + endTime, _dateTimeFormat);
		}
		
		String remark = ParamUtil.getString(actionRequest, "remark");		
		
		boolean startWorkflow = ParamUtil.getBoolean(actionRequest, "startWorkflow", Boolean.TRUE);
		
		if (!checkWorkflowDefinitionLinkEnabled(actionRequest)) {
			SessionMessages.add(actionRequest,
					PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
			SessionErrors.add(actionRequest.getPortletSession(), "definition-not-enabled-cannot-start-workflow");
			PortalUtil.copyRequestParameters(actionRequest, actionResponse);
			actionResponse.setRenderParameter("mvcPath", "/html/upsert.jsp");
			return;
		}
		
		JSONObject extPayload = JSONFactoryUtil.createJSONObject();
		String defaultAuditor = actionRequest.getPreferences().getValue(RemoteFormConstants.DEFAULT_AUDITOR, "arcane");
		extPayload.put("auditor", defaultAuditor);		
		
		if (_log.isDebugEnabled()) {
			_log.debug("extPayload.length(): " + extPayload.length() + "--");
			_log.debug("extPayload.names(): " + extPayload.names() + "--");
			_log.debug("extPayload: " + extPayload + "--");
		}
		
		RemoteForm remoteForm = RemoteFormLocalServiceUtil.upsertRemoteForm(remoteFormId, creator, applicant, startDateTime, 
				endDateTime, remark, extPayload.toString(), dayTabs, context);
		
		if (Validator.isNull(remoteForm)) {
			SessionMessages.add(actionRequest,
					PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
			SessionErrors.add(actionRequest.getPortletSession(), "cannot-get-form-entry");

			PortalUtil.copyRequestParameters(actionRequest, actionResponse);
			actionResponse.setRenderParameter("mvcPath", "/html/upsert.jsp");
			return;
		}
		
		if (startWorkflow) {
			WorkflowInstanceLink workflowInstanceLink = WorkflowInstanceLinkLocalServiceUtil.fetchWorkflowInstanceLink(
					remoteForm.getCompanyId(), remoteForm.getGroupId(), RemoteForm.class.getName(),
					remoteForm.getPrimaryKey());
			if (Validator.isNotNull(workflowInstanceLink)) {
				WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLink(workflowInstanceLink);
			}
			WorkflowHandlerRegistryUtil.startWorkflowInstance(remoteForm.getCompanyId(), remoteForm.getGroupId(),
					themeDisplay.getUserId(), RemoteForm.class.getName(), remoteForm.getPrimaryKey(), remoteForm, context);
			try {
				Mailer.getInstance().sendReviewMail(remoteForm, defaultAuditor, context);
			} catch (AddressException e) {
				_log.error(e);
			}
		}
		
		PortletURL portletURL = PortletURLFactoryUtil.create(actionRequest,
				(String) actionRequest.getAttribute(WebKeys.PORTLET_ID), context.getPlid(),
				PortletRequest.RENDER_PHASE);
		portletURL.setParameter("mvcPath", "/html/tabs.jsp");
		actionResponse.sendRedirect(portletURL.toString());
	}
	
	public void deleteRemoteForm(ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortalException, SystemException {

		long remoteFormId = ParamUtil.getLong(actionRequest, "remoteFormId");
		RemoteFormLocalServiceUtil.removeRemoteForm(remoteFormId);
	}
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException {

		String resourceID = resourceRequest.getResourceID();
		try {
			if (resourceID.equals("serveValidateRemoteForm")) {
				serveValidateRemoteForm(resourceRequest, resourceResponse);
			} 
		} catch (Exception e) {
			_log.warn(e);
		}
	}
	
	protected void serveValidateRemoteForm(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, SystemException {

		String applicant = ParamUtil.getString(resourceRequest, "applicant");
		String startDate = ParamUtil.getString(resourceRequest, "startDate");
		String startTime = ParamUtil.getString(resourceRequest, "startTime");

		SimpleDateFormat dateTimeFormat = MonthDateRange.getDateTimeFormat("yyyy-MM-dd HH:mm", TimeZoneUtil.GMT);
		Date startDatetime = GetterUtil.getDate(startDate + " " + startTime, dateTimeFormat);	

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		List<RemoteForm> overlappingTimesList = RemoteFormLocalServiceUtil.checkOverlappingTimes(applicant, startDatetime);

		if (overlappingTimesList.size() > 0) {
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
			for (RemoteForm remoteForm : overlappingTimesList) {
				jsonArray.put(remoteForm.getRemoteFormId());
			}

			String errorMsg = LanguageUtil.format(getPortletConfig(), resourceRequest.getLocale(),
					"the-remote-time-overlaps-with-other-application-forms-and-the-form-number-is-x",
					new Object[] { jsonArray.toString() });

			jsonObject.put("errorMsg", errorMsg);
			writeJSON(resourceRequest, resourceResponse, jsonObject.toString());
			return;
		}

		writeJSON(resourceRequest, resourceResponse, jsonObject.toString());
	}
	
	private boolean checkWorkflowDefinitionLinkEnabled(ActionRequest actionRequest)
			throws PortalException, SystemException {

		ServiceContext context = ServiceContextFactory.getInstance(RemoteForm.class.getName(), actionRequest);

		long classPK = 0;
		long typePK = 0;
		try {
			WorkflowDefinitionLinkLocalServiceUtil.getWorkflowDefinitionLink(context.getCompanyId(),
					context.getScopeGroupId(), RemoteForm.class.getName(), classPK, typePK);
		} catch (Exception e) {
			if (e instanceof NoSuchWorkflowDefinitionLinkException) {
				SessionMessages.add(actionRequest.getPortletSession(), "workflow-not-enabled");
			}
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
}
