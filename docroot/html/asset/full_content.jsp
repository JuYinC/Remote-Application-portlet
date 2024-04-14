<%@ include file="/html/init.jsp" %>

<%
RemoteForm remoteForm = (RemoteForm)request.getAttribute(WebKeys.REMOTE_FORM);
%>

<aui:row>
	<aui:col span="1"/>
	<aui:col span="10" cssClass="fill-form-wrapper">
		<liferay-util:include page="/html/form_body.jsp" servletContext="<%=application %>">
			<liferay-util:param name="remoteFormId" value="<%=String.valueOf(remoteForm.getRemoteFormId()) %>"/>
		</liferay-util:include>
	</aui:col>
	<aui:col span="1"/>
</aui:row>
