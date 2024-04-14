<%@page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util"%>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security"%>




<%@page import="com.employment.model.Employee"%>
<%@page import="com.employment.model.Employment"%>
<%@page import="com.employment.model.Position"%>
<%@page import="com.employment.service.EmployeeLocalServiceUtil"%>
<%@page import="com.employment.service.EmploymentLocalServiceUtil"%>
<%@page import="com.employment.service.PositionLocalServiceUtil"%>
<%@page import="com.employment.service.common.EmpUtil"%>
<%@page import="com.employment.service.constants.Constants"%>
<%@page import="com.employment.service.constants.WorkflowConstants"%>
<%@page import="com.remote.application.bean.MonthDateRange"%>
<%@page import="com.remote.application.constants.PortletKey"%>
<%@page import="com.remote.application.constants.RemoteFormConstants"%>
<%@page import="com.remote.application.model.RemoteForm"%>
<%@page import="com.remote.application.service.RemoteFormLocalServiceUtil"%>
<%@page import="com.remote.application.util.RemoteUtil"%>
<%@page import="com.remote.application.constants.WebKeys"%>
<%@page import="com.liferay.calendar.model.CalendarResource"%>
<%@page import="com.liferay.calendar.service.CalendarLocalServiceUtil"%>
<%@page import="com.liferay.calendar.service.CalendarResourceLocalServiceUtil"%>
<%@page import="com.liferay.calendar.util.comparator.CalendarNameComparator"%>
<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%@page import="com.liferay.portal.kernel.dao.search.DisplayTerms"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@page import="com.liferay.portal.kernel.dao.search.RowChecker"%>
<%@page import="com.liferay.portal.kernel.dao.search.SearchContainer"%>
<%@page import="com.liferay.portal.kernel.json.JSONArray"%>
<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil"%>
<%@page import="com.liferay.portal.kernel.log.Log"%>
<%@page import="com.liferay.portal.kernel.log.LogFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.DynamicRenderRequest"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletMode"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.kernel.search.Document"%>
<%@page import="com.liferay.portal.kernel.search.Field"%>
<%@page import="com.liferay.portal.kernel.search.Hits"%>
<%@page import="com.liferay.portal.kernel.search.Indexer"%>
<%@page import="com.liferay.portal.kernel.search.IndexerRegistryUtil"%>
<%@page import="com.liferay.portal.kernel.search.SearchContext"%>
<%@page import="com.liferay.portal.kernel.search.SearchContextFactory"%>
<%@page import="com.liferay.portal.kernel.search.Sort"%>
<%@page import="com.liferay.portal.kernel.search.SortFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.servlet.SessionErrors"%>
<%@page import="com.liferay.portal.kernel.util.ArrayUtil"%>
<%@page import="com.liferay.portal.kernel.util.CalendarFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.util.DateRange"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@page import="com.liferay.portal.kernel.util.HttpUtil"%>
<%@page import="com.liferay.portal.kernel.util.KeyValuePair"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="com.liferay.portal.kernel.util.MimeTypesUtil"%>
<%@page import="com.liferay.portal.kernel.util.OrderByComparator"%>
<%@page import="com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.PrefsParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.PrefsPropsUtil"%>
<%@page import="com.liferay.portal.kernel.util.PropertiesParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.PropertiesUtil"%>
<%@page import="com.liferay.portal.kernel.util.PropsKeys"%>
<%@page import="com.liferay.portal.kernel.util.PropsUtil"%>
<%@page import="com.liferay.portal.kernel.util.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@page import="com.liferay.portal.kernel.util.TextFormatter"%>
<%@page import="com.liferay.portal.kernel.util.Time"%>
<%@page import="com.liferay.portal.kernel.util.TimeZoneUtil"%>
<%@page import="com.liferay.portal.kernel.util.UnicodeFormatter"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowHandler"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowInstance"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowInstanceManagerUtil"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowLog"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowLogManagerUtil"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowTask"%>
<%@page import="com.liferay.portal.kernel.workflow.comparator.WorkflowComparatorFactoryUtil"%>
<%@page import="com.liferay.portal.model.Group"%>
<%@page import="com.liferay.portal.model.Organization"%>
<%@page import="com.liferay.portal.model.OrganizationConstants"%>
<%@page import="com.liferay.portal.model.Role"%>
<%@page import="com.liferay.portal.model.Ticket"%>
<%@page import="com.liferay.portal.model.TicketConstants"%>
<%@page import="com.liferay.portal.model.User"%>
<%@page import="com.liferay.portal.model.UserGroupRole"%>
<%@page import="com.liferay.portal.model.WorkflowInstanceLink"%>
<%@page import="com.liferay.portal.security.permission.PermissionChecker"%>
<%@page import="com.liferay.portal.security.permission.PermissionCheckerFactoryUtil"%>
<%@page import="com.liferay.portal.service.ClassNameLocalServiceUtil"%>
<%@page import="com.liferay.portal.service.LayoutLocalServiceUtil"%>
<%@page import="com.liferay.portal.service.LockLocalServiceUtil"%>
<%@page import="com.liferay.portal.service.OrganizationLocalServiceUtil"%>
<%@page import="com.liferay.portal.service.RoleLocalServiceUtil"%>
<%@page import="com.liferay.portal.service.ServiceContext"%>
<%@page import="com.liferay.portal.service.TicketLocalServiceUtil"%>
<%@page import="com.liferay.portal.service.UserGroupRoleLocalServiceUtil"%>
<%@page import="com.liferay.portal.service.UserLocalServiceUtil"%>
<%@page import="com.liferay.portal.service.WorkflowInstanceLinkLocalServiceUtil"%>
<%@page import="com.liferay.portal.util.Portal"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="com.liferay.portlet.PortalPreferences"%>
<%@page import="com.liferay.portlet.PortletPreferencesFactoryUtil"%>
<%@page import="com.liferay.portlet.PortletURLFactory"%>
<%@page import="com.liferay.portlet.PortletURLFactoryUtil"%>
<%@page import="com.liferay.portlet.PortletURLUtil"%>
<%@page import="com.liferay.portlet.asset.model.AssetCategory"%>
<%@page import="com.liferay.portlet.asset.model.AssetEntry"%>
<%@page import="com.liferay.portlet.asset.model.AssetVocabulary"%>
<%@page import="com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil"%>
<%@page import="com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil"%>
<%@page import="com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil"%>
<%@page import="com.liferay.portlet.documentlibrary.model.DLFileEntryConstants"%>
<%@page import="com.liferay.portlet.documentlibrary.store.DLStoreUtil"%>
<%@page import="com.liferay.portlet.social.model.SocialActivity"%>
<%@page import="com.liferay.portlet.usersadmin.util.UsersAdminUtil"%>
<%@page import="com.liferay.taglib.aui.AUIUtil"%>
<%@page import="com.liferay.util.Encryptor"%>
<%@page import="java.io.File"%>
<%@page import="java.io.Serializable"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.security.Key"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.LinkedHashSet"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.util.TreeSet"%>
<%@page import="javax.portlet.MimeResponse"%>
<%@page import="javax.portlet.PortletConfig"%>
<%@page import="javax.portlet.PortletContext"%>
<%@page import="javax.portlet.PortletException"%>
<%@page import="javax.portlet.PortletMode"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@page import="javax.portlet.PortletRequest"%>
<%@page import="javax.portlet.PortletResponse"%>
<%@page import="javax.portlet.PortletSession"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="javax.portlet.ResourceURL"%>
<%@page import="javax.portlet.UnavailableException"%>
<%@page import="javax.portlet.ValidatorException"%>
<%@page import="javax.portlet.WindowState"%>



<liferay-theme:defineObjects />
<portlet:defineObjects />

<%
	//eclipse 查詢中文字元 [^\x00-\xff]
		
	// 	DateFormat calDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm+08:00");
// 	DateFormat dateWeekTimeFormat = new SimpleDateFormat("yyyy/MM/dd E HH:mm a", locale);
	//aui 行事曆 月份扣1 hr;
	DateFormat auiSchedulerDateFormant = new SimpleDateFormat("yyyy, MM-1, dd, HH, mm");
	DateFormat _dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	DateFormat _yymmddFormat = new SimpleDateFormat("yyyyMMdd");
	DateFormat _yymmFormat = new SimpleDateFormat("yyyyMM");
	DateFormat _dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat _H_MFormat = new SimpleDateFormat("HH:mm");
	DateFormat _H_M_SFormat = new SimpleDateFormat("HH:mm:ss");
	DateFormat hourFormat = new SimpleDateFormat("HH");
	
// 	_dateTimeFormat.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
// 	_dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
// 	_H_MFormat.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
// 	_H_M_SFormat.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
	
	_dateTimeFormat.setTimeZone(user.getTimeZone());
	//_dateFormat.setTimeZone(user.getTimeZone());
	//_H_MFormat.setTimeZone(user.getTimeZone());
	//_H_M_SFormat.setTimeZone(user.getTimeZone());
	
	WindowState windowState = renderRequest.getWindowState();
	PortletMode portletMode = renderRequest.getPortletMode();

	PortletURL currentURLObj = PortletURLUtil.getCurrent(liferayPortletRequest, liferayPortletResponse);

	String currentURL = currentURLObj.toString();

	boolean hasAdmRole = permissionChecker.isOmniadmin();
	
	String defaultAuditor = portletPreferences.getValue(RemoteFormConstants.DEFAULT_AUDITOR, "boss");
	long employeeAttendanceCalendarId = GetterUtil.getLong(portletPreferences.getValue("employeeAttendanceCalendarId", "0"));
%>













