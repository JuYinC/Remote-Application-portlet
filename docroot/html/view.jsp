<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/html/init.jsp"%>

<%
	PortletURL backURL = renderResponse.createRenderURL();
	backURL.setParameter("jspPage", "/html/tabs.jsp");
	String redirect = ParamUtil.getString(request, "redirect", backURL.toString());
	
	long remoteFormId = ParamUtil.getLong(request, "remoteFormId");
%>

<aui:layout>
	<aui:row>
		<aui:col span="2"/>
		<aui:col span="8" cssClass="fill-form-wrapper">
			<liferay-util:include page="/html/form_body.jsp" servletContext="<%=application %>">
				<liferay-util:param name="remoteFormId" value="<%=String.valueOf(remoteFormId) %>"/>
			</liferay-util:include>
		</aui:col>
		<aui:col span="2"/>
	</aui:row>
	
	<aui:button-row cssClass="text-center">
		<aui:button type="cancel" name="clostBtn" icon="icon-off" value="close" cssClass="btn-large" href="<%=redirect %>" />
	</aui:button-row>

</aui:layout>

<aui:script use="aui-base">
A.one('#<portlet:namespace/>clostBtn').on('click', function() {
	Liferay.Util.getWindow().hide();
});
</aui:script>
