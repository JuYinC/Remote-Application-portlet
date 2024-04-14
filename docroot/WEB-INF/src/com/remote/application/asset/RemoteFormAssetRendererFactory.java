package com.remote.application.asset;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;

import com.remote.application.model.RemoteForm;
import com.remote.application.service.RemoteFormLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.BaseAssetRendererFactory;
import com.remote.application.constants.PortletKey;

public class RemoteFormAssetRendererFactory extends BaseAssetRendererFactory {

	private final Log _log = LogFactoryUtil.getLog(this.getClass());
	public static final String TYPE = "remoteForm";

	@Override
	public AssetRenderer getAssetRenderer(long classPK, int type) throws PortalException, SystemException {

		RemoteForm remoteForm = RemoteFormLocalServiceUtil.fetchRemoteForm(classPK);

		RemoteFormAssetRenderer remoteFormAssetRenderer = new RemoteFormAssetRenderer(remoteForm);

		remoteFormAssetRenderer.setAssetRendererType(type);

		return remoteFormAssetRenderer;
	}

	@Override
	public String getClassName() {

		return RemoteForm.class.getName();
	}

	@Override
	public String getType() {

		return TYPE;
	}

	@Override
	public PortletURL getURLView(LiferayPortletResponse liferayPortletResponse, WindowState windowState) {
//		_log.info("getURLView");

		LiferayPortletURL liferayPortletURL = liferayPortletResponse
				.createLiferayPortletURL(PortletKey.REMOTE_APPLICATION, PortletRequest.RENDER_PHASE);

//		liferayPortletURL.setParameter("mvcPath", "/html/view.jsp");
//		liferayPortletURL.setParameter("remoteFormId", String.valueOf(_remoteForm.getRemoteFormId()));

		try {
			liferayPortletURL.setWindowState(windowState);
		} catch (WindowStateException wse) {
		}

		return liferayPortletURL;
	}

	@Override
	public boolean hasPermission(PermissionChecker permissionChecker, long classPK, String actionId) throws Exception {
//		_log.info("hasPermission");
		return Boolean.TRUE;
	}

	@Override
	public boolean isLinkable() {
//		_log.info("isLinkable");
		return _LINKABLE;
	}

	@Override
	protected String getIconPath(ThemeDisplay themeDisplay) {

		// return themeDisplay.getPathThemeImages() + "/common/pages.png";
		return themeDisplay.getURLPortal().concat("/Remote-Application-portlet/images/remote.png");
	}

	private static final boolean _LINKABLE = Boolean.TRUE;

}
