<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/html/init.jsp"%>

<%
long classNameId = ClassNameLocalServiceUtil.fetchClassNameId(Group.class.getName());
CalendarResource calendarResource = CalendarResourceLocalServiceUtil.fetchCalendarResource(classNameId, scopeGroupId);

Map<Locale, String> nameMap = Collections.EMPTY_MAP;
List<com.liferay.calendar.model.Calendar> actualCalendars = CalendarLocalServiceUtil.search(company.getCompanyId(),
        new long[] { scopeGroupId }, new long[] { calendarResource.getCalendarResourceId() },
        nameMap.get(locale), true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new CalendarNameComparator());
%>


<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%=configurationURL%>" method="post" name="fm">
	<aui:input name="<%=Constants.CMD%>" type="hidden" value="<%=Constants.UPDATE%>" />

	<aui:input name="preferences--defaultAuditor--" label="default-auditor" value="<%=defaultAuditor %>" showRequiredLabel="false">
		<aui:validator name="required"/>
		<aui:validator name="maxLength">75</aui:validator>
	</aui:input>
	
	
	<aui:select name="preferences--employeeAttendanceCalendarId--" label="employee-attendance-calendar">
		<%
		for (com.liferay.calendar.model.Calendar actualCalendar : actualCalendars) {
		%>
			<aui:option label="<%=actualCalendar.getName() %>" value="<%=actualCalendar.getCalendarId() %>" 
				selected="<%=employeeAttendanceCalendarId == actualCalendar.getCalendarId() %>" />
		<%
		}
		%>
	</aui:select>

	<aui:button-row>
		<aui:button type="submit" value="save" icon="icon-ok" />
	</aui:button-row>
</aui:form>




