package com.remote.application.asset;

import java.util.Date;
import java.util.Locale;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import com.remote.application.model.RemoteForm;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.asset.model.BaseAssetRenderer;
import com.remote.application.constants.WebKeys;

public class RemoteFormAssetRenderer extends BaseAssetRenderer {
	
	private RemoteForm _remoteForm;
	public static final String TYPE = "remoteForm";
	
	public static long getClassPK(RemoteForm remoteForm) {
		return remoteForm.getRemoteFormId();
	}
	
	public RemoteFormAssetRenderer(RemoteForm remoteForm) {
		_remoteForm = remoteForm;
	}
	
	@Override
	public String getClassName() {
		return RemoteForm.class.getName();
	}

	@Override
	public long getClassPK() {
		return getClassPK(_remoteForm);
	}
	
	@Override
	public Date getDisplayDate() {
		return _remoteForm.getModifiedDate();
	}

	@Override
	public long getGroupId() {
		return _remoteForm.getGroupId();
	}

	@Override
	public String getSummary(Locale locale) {
		return getTitle(locale);
	}

	@Override
	public String getTitle(Locale locale) {
		return _remoteForm.getFormTitle();
	}
	
	@Override
	public PortletURL getURLEdit(LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse) throws Exception {
//		_log.info("getURLEdit");

		PortletURL portletURL = liferayPortletResponse.createRenderURL();
		portletURL.setParameter("mvcPath", "/html/upsert.jsp");
		portletURL.setParameter("remoteFormId", String.valueOf(_remoteForm.getRemoteFormId()));

		return portletURL;
	}

	@Override
	public PortletURL getURLExport(LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse) {

		PortletURL exportPortletURL = liferayPortletResponse.createActionURL();
		exportPortletURL.setParameter("javax.portlet.action", "exportRemoteForm");
		exportPortletURL.setParameter("remoteFormId", String.valueOf(_remoteForm.getRemoteFormId()));

		return exportPortletURL;
	}

	@Override
	public PortletURL getURLView(LiferayPortletResponse liferayPortletResponse, WindowState windowState)
			throws Exception {
//		_log.info("getURLView");

		AssetRendererFactory assetRendererFactory = getAssetRendererFactory();

		PortletURL portletURL = assetRendererFactory.getURLView(liferayPortletResponse, windowState);

		portletURL.setParameter("mvcPath", "/html/view.jsp");
		portletURL.setParameter("remoteFormId", String.valueOf(_remoteForm.getRemoteFormId()));
		portletURL.setWindowState(windowState);

		return portletURL;
	}

	@Override
	public String getURLViewInContext(LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse, String noSuchEntryRedirect) {
		
		return getURLViewInContext(liferayPortletRequest, noSuchEntryRedirect, "", "remoteFormId",
				_remoteForm.getRemoteFormId());
	}


	@Override
	public long getUserId() {
		return _remoteForm.getCuserId();
	}

	@Override
	public String getUserName() {
		return _remoteForm.getApplicant();
	}

	@Override
	public String getUuid() {
		return String.valueOf(_remoteForm.getRemoteFormId());
	}

	public boolean hasDeletePermission(PermissionChecker permissionChecker) throws SystemException {
//		_log.info("hasDeletePermission");
		
		return Boolean.FALSE;
	}

	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) throws SystemException {
//		_log.info("hasEditPermission");
		//從待辦工作清單 點單筆進來檢視會觸發;
		
		return Boolean.FALSE;
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) throws SystemException {
//		_log.info("hasViewPermission");
		//從待辦工作清單 點單筆進來檢視會觸發;
		
		return Boolean.FALSE;
	}

	@Override
	public boolean isConvertible() {
//		_log.info("isConvertible");
		return Boolean.FALSE;
	}

	@Override
	public boolean isPrintable() {
//		_log.info("isPrintable");
		return Boolean.FALSE;
	}
	
	@Override
	public String render(RenderRequest renderRequest,
			RenderResponse renderResponse, String template) throws Exception {
		if (template.equals(TEMPLATE_ABSTRACT) || template.equals(TEMPLATE_FULL_CONTENT)) {

			renderRequest.setAttribute(WebKeys.REMOTE_FORM, _remoteForm);

			return "/html/asset/" + template + ".jsp";
		} else {
			return null;
		}
	}
	
	@Override
	protected String getIconPath(ThemeDisplay themeDisplay) {
		// return themeDisplay.getPathThemeImages() + "/common/otform.png";
		return themeDisplay.getURLPortal().concat("/Remote-Application-portlet/images/remote.png");
	}

}
