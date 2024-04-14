package com.remote.application.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.util.PortalUtil;
import com.remote.application.constants.PortletKey;

public class URLUtil {
	public static String getReviewURL(long groupId) throws PortalException, SystemException {
		String portletId = PortletKey.APPROVAL;				
		String reviewURL = PortalUtil.getLayoutFullURL(groupId, portletId);
		
		return reviewURL;
	}
	
	public static String getApprovedURL(long groupId) throws PortalException, SystemException {
		String portletId = PortletKey.REMOTE_APPLICATION;
		String approvedURL = PortalUtil.getLayoutFullURL(groupId, portletId);
		
		return approvedURL;
	}
}
