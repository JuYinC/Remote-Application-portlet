package com.remote.notification;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.notifications.BaseUserNotificationHandler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.UserNotificationEvent;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.util.ContentUtil;
import com.remote.application.constants.PortletKey;

public class RemoteFormNotificationHandler extends BaseUserNotificationHandler {
	public static final String PORTLET_ID = PortletKey.REMOTE_APPLICATION;

	public RemoteFormNotificationHandler() {
		setPortletId(PORTLET_ID);
	}

	// 右上角 通知的畫面顯示的 "內容";
	@Override
	protected String getBody(UserNotificationEvent userNotificationEvent, ServiceContext serviceContext)
			throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(userNotificationEvent.getPayload());
		String title = jsonObject.getString("entryTitle");
		String body = StringUtil.replace(getBodyTemplate(), new String[] { "[$TITLE$]" }, new String[] { title });
		return body;
	}

	// 右上角 通知的畫面顯示 點擊 要去的連結;
	@Override
	protected String getLink(UserNotificationEvent userNotificationEvent, ServiceContext serviceContext)
			throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(userNotificationEvent.getPayload());
		long remoteFormId = jsonObject.getLong("remoteFormId");

		long plid = PortalUtil.getPlidFromPortletId(serviceContext.getScopeGroupId(), PORTLET_ID);

		PortletURL renderURL = PortletURLFactoryUtil.create(serviceContext.getLiferayPortletRequest(), PORTLET_ID, plid,
				PortletRequest.RENDER_PHASE);

		renderURL.setParameter("redirect", serviceContext.getLayoutFullURL());
		renderURL.setParameter("mvcPath", "/html/view.jsp");
		renderURL.setParameter("remoteFormId", String.valueOf(remoteFormId));
		renderURL.setParameter("userNotificationEventId",
				String.valueOf(userNotificationEvent.getUserNotificationEventId()));
		renderURL.setWindowState(WindowState.NORMAL);

		return renderURL.toString();
	}

	// 右上角 通知的畫面顯示 "HTML版型";
	protected String getBodyTemplate() throws Exception {
		String body = ContentUtil.get("/com/fansysoft/remote/notification/tmpl/remote_form.tmpl", true);
		return body;
	}

}
