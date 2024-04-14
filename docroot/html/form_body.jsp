<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/html/init.jsp"%>

<style>
.hour-display {
	font-weight: bold;
	font-size: 1.6em;
	line-height: 1.6em;
	text-decoration: underline;
}

</style>

<%
	long remoteFormId = ParamUtil.getLong(request, "remoteFormId");
	RemoteForm remoteForm = RemoteFormLocalServiceUtil.fetchRemoteForm(remoteFormId);
	
	String creator = remoteForm.getCreator();
	User creatorUser = UserLocalServiceUtil.fetchUserByScreenName(company.getCompanyId(), creator);
	String creatorLabel = Validator.isNotNull(creatorUser) ? creatorUser.getFullName() : creator;
			
	String applicant = remoteForm.getApplicant();
	User applicantUser = UserLocalServiceUtil.fetchUserByScreenName(company.getCompanyId(), applicant);
	long applicantUserId = Validator.isNotNull(applicantUser) ? applicantUser.getUserId() : 0L;
	String applicantLabel = Validator.isNotNull(applicantUser) ? applicantUser.getFullName() : applicant;
	String remoteDate = _dateFormat.format(remoteForm.getRemoteDate());
	String remark = remoteForm.getRemark();
	int status = remoteForm.getStatus();
	
	Employee curEmployee = EmployeeLocalServiceUtil.fetchByLRUserId(applicantUserId);
	String curEmployeeKey = Validator.isNotNull(curEmployee) ? curEmployee.getEmployeeKey() : StringPool.BLANK; 	

	PortletConfig remoteAppPortletConfig = PortalUtil.getPortletConfig(company.getCompanyId(), PortletKey.REMOTE_APPLICATION, pageContext.getServletContext());
%>


<h2 class="fill-form-header">
	<%=LanguageUtil.get(remoteAppPortletConfig, locale, "remote-application-form") %>
</h2>

<table class="table table-bordered table-hover table-striped data-detail">
	<tbody class="table-data">
		<tr>
			<th><%=LanguageUtil.get(remoteAppPortletConfig, locale, "applicant") %></th>
<%-- 			<td><aui:icon image="user" label="<%=applicant %>" /></td> --%>
			<td><liferay-ui:user-display userId="<%=applicantUserId %>" displayStyle="3" url="javascript:;" /></td>
			<th><%=LanguageUtil.get(remoteAppPortletConfig, locale, "status") %></th>
			<td><aui:workflow-status status="<%=status %>" showLabel="false" /></td>
		</tr>
		<tr>
			<th><%=LanguageUtil.get(remoteAppPortletConfig, locale, "remote-date") %></th>
			<c:choose>
				<c:when test="<%=remoteForm.getEndDateTime() == null %>">
					<td colspan="3"><aui:icon image="calendar" label="<%=RemoteFormLocalServiceUtil.getRemotePeriodDisplay(remoteForm.getRemoteDate(), remoteForm.getRemoteDate(), timeZone) %>" /></td>
				</c:when>
				<c:otherwise>
					<td colspan="3"><aui:icon image="calendar" label="<%=RemoteFormLocalServiceUtil.getRemotePeriodDisplay(remoteForm.getRemoteDate(), remoteForm.getEndDateTime(), timeZone) %>" /></td>
				</c:otherwise>
			</c:choose>
			
		</tr>
		<tr>
			<th><%=LanguageUtil.get(remoteAppPortletConfig, locale, "remark") %></th>
			<td colspan="3"><div style="min-height: 100px;"><%=remark.replaceAll("\n", "<br>") %></div></td>
		</tr>
	</tbody>
</table>





