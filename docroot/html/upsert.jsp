<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/html/init.jsp"%>

<%
String displayName = user.getFullName();
String cmd = ParamUtil.getString(request, Constants.CMD);
long remoteFormId = ParamUtil.getLong(request, "remoteFormId");

String creator = ParamUtil.getString(request, "creator", user.getScreenName());
String applicant = ParamUtil.getString(request, "applicant", user.getScreenName());
String remoteDate = ParamUtil.getString(request, "remoteDate");
String startTime = ParamUtil.getString(request, "startTime");
String endTime = ParamUtil.getString(request, "endTime");
String remark = ParamUtil.getString(request, "remark");

if (remoteFormId > 0) {
	RemoteForm remoteForm = RemoteFormLocalServiceUtil.fetchRemoteForm(remoteFormId);
	creator = remoteForm.getCreator();
	applicant = remoteForm.getApplicant();
	remoteDate = _dateFormat.format(remoteForm.getRemoteDate());
	startTime = _H_MFormat.format(remoteForm.getRemoteDate());
	endTime = _H_MFormat.format(remoteForm.getEndDateTime());
	remark = remoteForm.getRemark();
}

String keywords = StringPool.BLANK;
int workStatus = Constants._WORKING_STATUS.WORKING;
OrderByComparator obc = OrderByComparatorFactoryUtil.create("ee", "employeeKey", false);
List<Employee> employees = EmployeeLocalServiceUtil.search(workStatus, keywords, QueryUtil.ALL_POS,
		QueryUtil.ALL_POS, obc);

String creatorLabel = LanguageUtil.get(locale, "creator") + ": " + displayName;

Employee curEmployee = EmployeeLocalServiceUtil.fetchByLRUserId(user.getUserId());
%>

<liferay-portlet:actionURL name="upsertRemoteForm" var="upsertRemoteFormURL" varImpl="upsertRemoteFormURL" />

<aui:row>
	<aui:col span="1"/>
	<aui:col span="10" cssClass="fill-form-wrapper">
	
		<liferay-ui:error key="definition-not-enabled-cannot-start-workflow" message="definition-not-enabled-cannot-start-workflow" />
		<liferay-ui:error key="cannot-get-form-entry" message="cannot-get-form-entry"/>
	
		<aui:form action="${upsertRemoteFormURL }" name="fm" method="post" enctype="multipart/form-data"
			onSubmit='<%="event.preventDefault(); " + renderResponse.getNamespace() + "submitForm();"%>'>
		
			<aui:input type="hidden" name="creator" value="<%=creator %>" />
			<aui:input type="hidden" name="remoteFormId" value="<%=remoteFormId %>" />
			<aui:input type="hidden" name="<%=Constants.CMD %>" value="<%=cmd %>" />
			<aui:input type="hidden" name="dayTabs" value="<%=RemoteFormConstants.DAY_TABS.ALL[0] %>" />
			<aui:input type="hidden" name="auditors" value="" />
			
			<h2 class="fill-form-header"><aui:icon image="edit" label="fill-the-remote-application-form"/></h2>
			
			<aui:row>
				<aui:col span="4">								
					<aui:input name="creator" label='<%=creatorLabel %>' title="<%= creatorLabel%>" value="<%=creator %>" cssClass="span12" disabled="true">
						<aui:validator name="required"/>
						<aui:validator name="maxLength">75</aui:validator>
					</aui:input>
				</aui:col>
				
				<aui:col span="4">								
					 <aui:select name="applicant" label="applicant" title="applicant" cssClass="span12" required="true">
						<aui:option value="" label="please-select-an-option" selected=""/>
						<%
						for (Employee employee : employees) {
							String lrUserScreenName = Validator.isNotNull(employee.getLRUser()) ? employee.getLRUser().getScreenName() : null;
							String applicantLabel = employee.getName_() + StringPool.SLASH + employee.getEnglishName();
						%>
							<aui:option value="<%=lrUserScreenName %>" label="<%=applicantLabel %>" selected="<%=applicant.equals(lrUserScreenName) %>"/>
						<%
						}
						%>
					</aui:select>
				</aui:col>
				

			</aui:row>		

			<aui:row>
				<aui:col span="4">								
					<aui:input name="remoteDate" label="remote-date" title="remote-date" value="<%=remoteDate %>" cssClass="datepicker span12" placeholder="yyyy-MM-dd">
						<aui:validator name="required"/>
						<aui:validator name="date"/>
					</aui:input>
				</aui:col>
				<aui:col span="7">
					<div class="tabbable">
						<liferay-ui:tabs names="<%=StringUtil.merge(RemoteFormConstants.DAY_TABS.ALL) %>" refresh="false">
							<%--當天整日 --%>
							<liferay-ui:section>
								<aui:icon image="check" label="same-remote-date" cssClass="label label-warning" />
							</liferay-ui:section>
							
							<%--當天時數 --%>
							<liferay-ui:section>
								<aui:select id="startTime" name="startTime" label="start-time" cssClass="text-center input-medium" inlineField="true">
									<%
									for (int i = 7; i < 20; i++) {
									%>
									<aui:option label='<%=String.format("%02d", i) + ":00" %>' value='<%=String.format("%02d", i) + ":00" %>' selected='<%=startTime.equals(String.format("%02d", i) + ":00") %>' />
<%-- 									<aui:option label='<%=String.format("%02d", i) + ":30" %>' value='<%=String.format("%02d", i) + ":30" %>' selected='<%=startTime.equals(String.format("%02d", i) + ":30") %>' /> --%>
									<%
									} 
									%>
								</aui:select>
								<aui:select id="endTime" name="endTime" label="end-time" cssClass="text-center input-medium" inlineField="true">
									<%
									for (int i = 8; i <= 20; i++) {
									%>
									<aui:option label='<%=String.format("%02d", i) + ":00" %>' value='<%=String.format("%02d", i) + ":00" %>' selected='<%=endTime.equals(String.format("%02d", i) + ":00") %>' />
<%-- 									<aui:option label='<%=String.format("%02d", i) + ":30" %>' value='<%=String.format("%02d", i) + ":30" %>' selected='<%=endTime.equals(String.format("%02d", i) + ":30") %>' /> --%>
									<%
									} 
									%>
								</aui:select>
							</liferay-ui:section>
						</liferay-ui:tabs>
					</div>
				</aui:col>
			
			</aui:row>

			<aui:row>
				<aui:input type="textarea" rows="4" name="remark" cssClass="span12" required="true">					
					<aui:validator name="maxLength">500</aui:validator>
				</aui:input>
			</aui:row>
			
			<liferay-ui:panel title="simple-tips" extended="true" cssClass="simple-statistics-panel" iconCssClass="icon-comment">
				
				<aui:row>
					<p>
						<aui:icon image="comment" label="approval-checkpoint-description" />:
						<span class="simple-statistics-label">
							<liferay-ui:message key="supervisor-approval" />
						</span>
					</p>
				</aui:row>
				
				<aui:row>
					<blockquote id="<portlet:namespace/>auditCheckpoint">
						<p><liferay-ui:message key="audit-checkpoint"/>:</p>
						<ol class="inline">
							<%
							User defaultAuditorName = UserLocalServiceUtil.fetchUserByScreenName(company.getCompanyId(), defaultAuditor);
							%>
							<li>→<aui:icon id="auditorLabel" image="user" label="<%=defaultAuditorName.getFullName() %>" cssClass="badge" /></li>
						</ol>
					</blockquote>
				</aui:row>
				
			</liferay-ui:panel>
		
			<aui:button-row cssClass="text-center">
				<liferay-portlet:renderURL varImpl="backURL" var="backURL">
					<liferay-portlet:param name="jspPath" value="/html/view.jsp"/>
				</liferay-portlet:renderURL>
				<aui:button type="cancel" value="back" icon="icon-chevron-left" cssClass="btn-large" href="${backURL }"/>
				<aui:button type="submit" value="submit" icon="icon-ok" cssClass="btn-large"/>
			</aui:button-row>
		
		</aui:form>
	
	</aui:col>
	<aui:col span="1"/>
</aui:row>

<liferay-portlet:resourceURL id="serveValidateRemoteForm" var="serveValidateRemoteFormURL" varImpl="serveValidateRemoteFormURL" />

<aui:script use="aui-base,aui-io-request-deprecated,aui-node,node-event-simulate,liferay-util-list-fields">
Liferay.on('showTab', function(tab) {
	A.one('#<portlet:namespace/>dayTabs').val(tab.id);
});

Liferay.provide(window, '<portlet:namespace />submitForm', function() {

	var applicantSelector = A.one('#<portlet:namespace/>applicant');
	
	var startDate = A.one('#<portlet:namespace />remoteDate');
	var startTime = A.one('#<portlet:namespace />startTime');
	var endTime = A.one('#<portlet:namespace />endTime');
	
	var startDatetime;
	var endDatetime;
	
	var dayTabs = A.one('#<portlet:namespace />dayTabs').val();
	if (dayTabs == '<%=RemoteFormConstants.DAY_TABS.ALL_DAY %>') {
		startDatetime = new Date(startDate.val() + "T" + "00:00:00");
		endDatetime = new Date(startDate.val() + "T" + "23:59:59");
		
	} else if (dayTabs == '<%=RemoteFormConstants.DAY_TABS.THAT_DAY %>') {
		startDatetime = new Date(startDate.val() + "T" + startTime.val() + ":00");
		endDatetime = new Date(startDate.val() + "T" + endTime.val() + ":00");
		
	} 
	
	if (isNaN(startDatetime)) {
		alert('<liferay-ui:message key="unreasonable-start-time"/>');
		startDate.focus();
		return;
	}
	
	if (isNaN(endDatetime)) {
		alert('<liferay-ui:message key="unreasonable-end-time"/>');
		endTime.focus();
		return;
	}
	
	if (startDatetime.getTime() >= endDatetime.getTime()) {
		alert('<liferay-ui:message key="search-custom-range-invalid-date-range"/>');
		endTime.focus();
		return;
	}
	
	var startDateVal = startDatetime.getFullYear() + "-" + ('0' + (startDatetime.getMonth()+1)).slice(-2) + "-" + ('0' + startDatetime.getDate()).slice(-2);
	var startTimeVal = ('0' + startDatetime.getHours()).slice(-2) + ":" + ('0' + startDatetime.getMinutes()).slice(-2);	
	
	var data = { 
		<portlet:namespace />applicant: applicantSelector.val(),
		<portlet:namespace />startDate: startDateVal,
		<portlet:namespace />startTime: startTimeVal,
	}
	
	A.io.request('${serveValidateRemoteFormURL }' , {
		dataType: 'json',
		data: data,
		on: {
			failure: function (event, id, xhr) {
				alert('<liferay-ui:message key="an-error-occurred-while-processing-the-requested-resource" />');
			}
		},
		after: {
			success: function(event, id, xhr) {
				var data = this.get('responseData');
				if (data.errorMsg) {
					alert(data.errorMsg);
					return;
				}
								
				submitForm(document.<portlet:namespace />fm);
			}
		}
	});
});
</aui:script>