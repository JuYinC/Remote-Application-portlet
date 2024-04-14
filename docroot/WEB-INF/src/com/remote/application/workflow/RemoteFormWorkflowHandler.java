package com.remote.application.workflow;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

import com.remote.application.model.RemoteForm;
import com.remote.application.service.RemoteFormLocalServiceUtil;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.BaseWorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ResourceActionsUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.remote.application.util.Mailer;

public class RemoteFormWorkflowHandler extends BaseWorkflowHandler {

	private final Log _log = LogFactoryUtil.getLog(RemoteFormWorkflowHandler.class);
	
	@Override
	public String getClassName() {

		return RemoteForm.class.getName();
	}

	@Override
	public String getType(Locale locale) {

		return ResourceActionsUtil.getModelResource(locale, getClassName());
	}

	@Override
	public RemoteForm updateStatus(int status,
			Map<String, Serializable> workflowContext) throws PortalException,
			SystemException {

		long userId = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));
		long classPK = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
		
		ServiceContext serviceContext = (ServiceContext) workflowContext.get("serviceContext");
		
		RemoteForm remoteForm = RemoteFormLocalServiceUtil.fetchRemoteForm(classPK);
		JSONObject extPayload = JSONFactoryUtil.createJSONObject(remoteForm.getExtPayload());

		for (int i = 0; i < extPayload.names().length(); i++) {
			String key = extPayload.names().getString(i);

			if (_log.isDebugEnabled()) {
				_log.debug("key: " + key + ", val: " + extPayload.getString(key) + "--");
			}

			// supervisor-1, supervisor-2,..
			if (key.startsWith("auditor")) {
				User auditor = UserLocalServiceUtil.fetchUserByScreenName(serviceContext.getCompanyId(), extPayload.getString(key));

				if (Validator.isNotNull(auditor)) {
					workflowContext.put(key, auditor);
				}
			} else {
				workflowContext.put(key, extPayload.getString(key));
			}
		}

		workflowContext.put("status", status);

		if (_log.isDebugEnabled()) {
			_log.debug("status: " + WorkflowConstants.getStatusLabel(status) + ", userId: " + userId + ", classPK: "
					+ classPK);

			for (String key : serviceContext.getAttributes().keySet()) {
				_log.debug("serviceContext: " + key + " : " + serviceContext.getAttribute(key));
			}

			for (Object key : workflowContext.keySet()) {
				_log.debug("workflowContext key: " + key + ", val: " + workflowContext.get(key) + "--");
			}
		}

		// 如果變換狀態是 approved, 表示簽核通過了;
		if (remoteForm.getStatus() != WorkflowConstants.STATUS_APPROVED && status == WorkflowConstants.STATUS_APPROVED) {
			try {
				Mailer.getInstance().sendApprovedMail(remoteForm, userId);				
				_log.info("STATUS_APPROVED");
			} catch (Exception e) {
				_log.error(e);
			}
		}

		// 如果變換狀態是 denied, 表示簽核不通過;
		if (remoteForm.getStatus() != WorkflowConstants.STATUS_DENIED && status == WorkflowConstants.STATUS_DENIED) {
			try {
				Mailer.getInstance().sendRejectedMail(remoteForm, userId);
				CalendarBookingLocalServiceUtil.deleteCalendarBooking(remoteForm.getCalendarBookingId());
				_log.info("STATUS_DENIED");
			} catch (Exception e) {
				_log.error(e);
			}
		}

		return RemoteFormLocalServiceUtil.updateStatus(userId, classPK, status, serviceContext);
	}
	
	@Override
	protected String getIconPath(ThemeDisplay themeDisplay) {

		// return themeDisplay.getPathThemeImages() + "/common/pages.png";
		return themeDisplay.getURLPortal().concat("/Remote-Application-portlet/images/remote.png");
	}

}
