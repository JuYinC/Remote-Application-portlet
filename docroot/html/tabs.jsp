<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/html/init.jsp"%>

<%
	String tabs = "approved,pending,denied";
// temp
// 	if (permissionChecker.isOmniadmin()) {
		tabs += ",scheduler-view";
		//tabs += ",scheduler-view,project-organization,administrator";
//	} 

	String tabs1 = ParamUtil.getString(request, "tabs1", "approved");
	Employee curEmployee = EmployeeLocalServiceUtil.fetchByLRUserId(user.getUserId());
	User auditor = UserLocalServiceUtil.fetchUserByScreenName(company.getCompanyId(), defaultAuditor);
%>

<liferay-portlet:renderURL varImpl="tabsURL">
	<liferay-portlet:param name="tabs1" value="<%=tabs1 %>"/>
</liferay-portlet:renderURL>

<aui:layout>
	<liferay-ui:header title="my-remote-application-forms" />

	<liferay-ui:tabs names="<%=tabs %>" portletURL="<%=tabsURL %>" />
	
	<c:choose>
		<c:when test='<%=Validator.isNull(auditor) %>'>
			<div class="alert alert-block">
				<h3><liferay-ui:message key="tip"/>!</h3>
				<p><aui:icon image="warning-sign" label="please-setup-the-correct-auditor" /></p>
			</div>
		</c:when>
		<c:when test='<%=Validator.isNull(curEmployee) %>'>
			<div class="alert alert-block">
				<h3><liferay-ui:message key="tip"/>!</h3>
				<p><aui:icon image="warning-sign" label="please-contact-the-administrator-because-you-do-not-have-employee-profile" /></p>
			</div>
		</c:when>
		<c:when test='<%=tabs1.equals("approved")%>'>
			<liferay-util:include page="/html/approved.jsp" servletContext="<%=application%>" />
		</c:when>
		<c:when test='<%=tabs1.equals("pending")%>'>
			<liferay-util:include page="/html/pending.jsp" servletContext="<%=application%>" />
		</c:when>
		<c:when test='<%=tabs1.equals("denied")%>'>
			<liferay-util:include page="/html/denied.jsp" servletContext="<%=application%>" />
		</c:when>
		<c:when test='<%=tabs1.equals("scheduler-view")%>'>
			<liferay-util:include page="/html/scheduler_view.jsp" servletContext="<%=application%>" />
		</c:when>
	</c:choose>

</aui:layout>

<aui:script use="aui-base">
var portlet = A.one('#p_p_id<portlet:namespace/>');
var refreshPortlet = function() { Liferay.Portlet.refresh("#p_p_id<portlet:namespace/>");};
portlet.delegate('click', refreshPortlet, '#<portlet:namespace/>refreshButton');
</aui:script>