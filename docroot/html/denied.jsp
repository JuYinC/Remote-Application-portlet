<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/html/init.jsp"%>
<%
String tabs1 = ParamUtil.getString(request, "tabs1");
String keywords = ParamUtil.getString(request, "keywords");
String cycle = ParamUtil.getString(request, "cycle", _yymmFormat.format(RemoteUtil.getMonthDateRange(Calendar.getInstance()).getEndDatetime()));

String orderByCol = ParamUtil.getString(request, "orderByCol", "remoteDate");
String orderByType = ParamUtil.getString(request, "orderByType", "desc");

List<Integer> logTypes = new ArrayList<Integer>();
logTypes.add(WorkflowLog.TASK_COMPLETION);
String applicant = user.getScreenName();
%>

<liferay-portlet:renderURL varImpl="iteratorURL">
	<liferay-portlet:param name="jspPage" value="/html/tabs.jsp" />
	<liferay-portlet:param name="tabs1" value="<%=tabs1 %>" />
	<liferay-portlet:param name="orderByCol" value="<%=orderByCol %>" />
	<liferay-portlet:param name="orderByType" value="<%=orderByType %>" />
	<liferay-portlet:param name="keywords" value="<%=keywords %>" />
	<liferay-portlet:param name="cycle" value="<%=cycle %>" />
</liferay-portlet:renderURL>

<portlet:renderURL var="searchURL">
	<liferay-portlet:param name="jspPage" value="/html/tabs.jsp" />
	<liferay-portlet:param name="tabs1" value="<%=tabs1 %>" />
	<liferay-portlet:param name="cycle" value="<%=cycle %>" />
</portlet:renderURL>

<aui:form action="<%=searchURL %>" method="post" name="fm">
	
	<aui:input type="hidden" name="cycle" value="<%=cycle %>"/>
	
	<aui:nav-bar>
		<aui:nav>		
			<aui:nav-item dropdown="true" label='<%=LanguageUtil.get(locale, "cycle") + ": " + cycle %>' iconCssClass="icon-calendar">
				<%
				List<String> cycles = RemoteFormLocalServiceUtil.fetchCycles(applicant, WorkflowConstants.STATUS_DENIED);
				for (String c : cycles) {
				%>
				<aui:nav-item label="<%=c %>"  title="<%=c %>" iconCssClass="icon-calendar" selected="<%=c.equals(cycle) %>"
					href='<%="javascript:" + renderResponse.getNamespace() +  "changeCycle(\'" + c + "\');" %>'/>
				<%
				}
				%>
			</aui:nav-item>
			
		</aui:nav>
		
		<aui:nav-bar-search cssClass="pull-right">
			<div class="form-search"> 
				<liferay-ui:input-search autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>" name="keywords" placeholder='<%=LanguageUtil.get(locale, "keywords")%>' />
			</div>
		</aui:nav-bar-search>
		
		<aui:nav cssClass="pull-right"> 
			<aui:nav-item label="refresh" title="refresh" iconCssClass="icon-refresh" name="refreshButton" id="refreshButton" />
		</aui:nav> 
	</aui:nav-bar>
</aui:form>

<liferay-ui:search-container emptyResultsMessage="no-entries-were-found" iteratorURL="<%=iteratorURL %>" orderByCol="<%=orderByCol %>" orderByType="<%=orderByType %>">

	<liferay-ui:search-container-results>
		<%
		Date cycleDate = GetterUtil.getDate(cycle + "01", _yymmddFormat, null);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(cycleDate);
		MonthDateRange monthDateRange = RemoteUtil.getMonthDateRange(cal);
		
		Date startDateTime = monthDateRange.getStartDatetime();
		Date endDateTime = monthDateRange.getEndDatetime();
		
		OrderByComparator obc = OrderByComparatorFactoryUtil.create("rrf", orderByCol, orderByType.equals("asc"));
		results = RemoteFormLocalServiceUtil.search(applicant, WorkflowConstants.STATUS_DENIED, startDateTime, endDateTime, keywords, searchContainer.getStart(), searchContainer.getEnd(), obc);
		total = RemoteFormLocalServiceUtil.countBySearch(applicant, WorkflowConstants.STATUS_DENIED, startDateTime, endDateTime, keywords);
		
		pageContext.setAttribute("results", results);
		pageContext.setAttribute("total", total);
		
		%>
	</liferay-ui:search-container-results>
	
	
	<liferay-ui:search-container-row className="com.remote.application.model.RemoteForm" keyProperty="remoteFormId" modelVar="remoteForm" indexVar="indexNo">
		<liferay-portlet:renderURL	var="viewURL" varImpl="viewURL" windowState="<%=LiferayWindowState.POP_UP.toString() %>">
			<liferay-portlet:param name="jspPage" value="/html/view.jsp"/>
			<liferay-portlet:param name="remoteFormId" value="<%=String.valueOf(remoteForm.getRemoteFormId()) %>"/>
		</liferay-portlet:renderURL>
	
		<liferay-ui:search-container-column-text name="action">
			<liferay-ui:icon-menu message="action" showWhenSingleIcon="true">
				<liferay-ui:icon image="view" label="true" message="view" url="${viewURL }" useDialog="true"/>				
				
				<li class="nav-header"><aui:icon image="ban-circle" label="delete" /></li>
				<liferay-portlet:actionURL name="deleteRemoteForm" var="deleteRemoteFormURL" varImpl="deleteRemoteFormURL">
					<liferay-portlet:param name="remoteFormId" value="<%=String.valueOf(remoteForm.getRemoteFormId()) %>"/>
					<liferay-portlet:param name="redirect" value="<%=currentURL %>"/>
				</liferay-portlet:actionURL>
				<liferay-ui:icon-delete image="trash" label="true" message="delete" url="${deleteRemoteFormURL }" />
			</liferay-ui:icon-menu>
		</liferay-ui:search-container-column-text>
		
		<liferay-ui:search-container-column-text name="No." value="<%=String.valueOf(indexNo + searchContainer.getStart() + 1).concat(StringPool.PERIOD) %>" />
		
		<liferay-ui:search-container-column-text name="form-no">
			<p><%=remoteForm.getRemoteFormId() %></p>
			<p><small class="muted"><%=LanguageUtil.get(locale, "create-date") %>: <%=_dateTimeFormat.format(remoteForm.getCreateDate()) %></small></p>
		</liferay-ui:search-container-column-text>
		
		<liferay-ui:search-container-column-status name="status" status="<%=remoteForm.getStatus() %>" cssClass="text-center" />
		
		
		
		<liferay-ui:search-container-column-text name="applicant" cssClass="text-center">
			<%
				User applicantUser = UserLocalServiceUtil.fetchUserByScreenName(company.getCompanyId(), remoteForm.getApplicant());
				String applicantName = Validator.isNotNull(applicantUser) ? applicantUser.getFullName() : remoteForm.getApplicant();
				
				User creator = UserLocalServiceUtil.fetchUserByScreenName(company.getCompanyId(), remoteForm.getCreator());
				String creatorName = Validator.isNotNull(creator) ? creator.getFullName() : remoteForm.getCreator();
			%>
			<p><liferay-ui:icon image="user_icon" message="<%=applicantName %>" label="true" url="${viewURL }" useDialog="true" /></p>
			<p><small class="muted"><%=LanguageUtil.get(locale, "creator") %>: <%=remoteForm.getCreator() %></small></p>
		</liferay-ui:search-container-column-text>	
		
		<liferay-ui:search-container-column-text name="remote-date" orderable="true" orderableProperty="remoteDate">
			<c:choose>
				<c:when test="<%=remoteForm.getEndDateTime() == null %>">
					<p><%=RemoteFormLocalServiceUtil.getRemotePeriodDisplay(remoteForm.getRemoteDate(), remoteForm.getRemoteDate(), timeZone) %></p>
				</c:when>
				<c:otherwise>
					<p><%=RemoteFormLocalServiceUtil.getRemotePeriodDisplay(remoteForm.getRemoteDate(), remoteForm.getEndDateTime(), timeZone) %></p>
				</c:otherwise>
			</c:choose>
		</liferay-ui:search-container-column-text>
	
		<liferay-ui:search-container-column-text name="remark" >
			<p><%=remoteForm.getRemark().replaceAll("\n", "<br>") %></p>
		</liferay-ui:search-container-column-text>
		
		<liferay-ui:search-container-column-text name="audit-opinions">
			<%
			WorkflowInstance workflowInstance = null;
			WorkflowInstanceLink workflowInstanceLink = WorkflowInstanceLinkLocalServiceUtil.fetchWorkflowInstanceLink(remoteForm.getCompanyId(), remoteForm.getGroupId(), RemoteForm.class.getName(), remoteForm.getPrimaryKey());
			if (Validator.isNotNull(workflowInstanceLink)) {
				try {
					workflowInstance = WorkflowInstanceManagerUtil.getWorkflowInstance(company.getCompanyId(), workflowInstanceLink.getWorkflowInstanceId());
				} catch (Exception e) {}
			}
			
			List<WorkflowLog> workflowLogs = new LinkedList<WorkflowLog>();
			if (Validator.isNotNull(workflowInstance)) {
				workflowLogs = WorkflowLogManagerUtil.getWorkflowLogsByWorkflowInstance(company.getCompanyId(), workflowInstance.getWorkflowInstanceId(), logTypes, 
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, WorkflowComparatorFactoryUtil.getLogCreateDateComparator(true));
			}
			%>
			<%@ include file="/html/asset/workflow_logs.jspf" %>
		</liferay-ui:search-container-column-text>
		
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<liferay-portlet:renderURL varImpl="changeCycleURL">
	<liferay-portlet:param name="mvcPath" value="/html/tabs.jsp" />
	<liferay-portlet:param name="tabs1" value="denied" />
	<liferay-portlet:param name="orderByCol" value="<%=orderByCol %>" />
	<liferay-portlet:param name="orderByType" value="<%=orderByType %>" />
	<liferay-portlet:param name="keywords" value="<%=keywords %>" />
	<liferay-portlet:param name="cycle" value="changeCycle" />
</liferay-portlet:renderURL>

<aui:script  use="aui-base">
Liferay.provide(window, '<portlet:namespace/>changeCycle', function(cycle) {
	var url = '<%=changeCycleURL.toString() %>';
	url = url.replace("changeCycle", cycle);
	location.href = url;
});
</aui:script>