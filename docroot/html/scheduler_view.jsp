<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/html/init.jsp"%>



<%
	String tabs1 = ParamUtil.getString(request, "tabs1");
	String applicant = ParamUtil.getString(request, "applicant", user.getScreenName());

	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.DAY_OF_YEAR, -180);
	Date searchDateStart = cal.getTime();
	long searchDateStartValue = searchDateStart.getTime();
	
	cal.add(Calendar.DAY_OF_YEAR, 240);
	Date searchDateEnd = cal.getTime();
	long searchDateEndValue = searchDateEnd.getTime();
	
	String[] colorSet = {"#FF99CC", "#0099FF", "#00FF99", "#9933FF", 
		"#99CC99", "#CC9900", "#CCFF99", "#88DD88", 
		"#FF6699", "#FFCC33", "#66CCFF", "#CCCC00",
		"#99CCCC", "#996666", "#99FFFF", "#CC9966",
		"#33CCCC", "#9999FF", "#FF3366", "#FFCC99",
		"#00CCFF", "#339966", "#CC0033", "#FFFF33",
		"#FFCC66", "#FFCCFF", "#FF6666", "#66CC66",
		"#66FFFF", "#9999CC", "#555555", "#AAAAAA"};


	List<RemoteForm> remoteForms = RemoteFormLocalServiceUtil.searchForStatistics(applicant, new int[] { WorkflowConstants.STATUS_APPROVED, WorkflowConstants.STATUS_PENDING }, searchDateStart, searchDateEnd);
	
	List<Object[]> list = new ArrayList<Object[]>();
	
	for (RemoteForm remoteForm : remoteForms) {
		
	//	int index = leaveTypes.indexOf(leaveForm.getLeaveType());
		String content = "(" + RemoteFormConstants.REMOTEFORM_TYPE + ")" + remoteForm.getRemark();
		
		Date startTime = remoteForm.getRemoteDate();
		Date endTime = Validator.isNotNull(remoteForm.getEndDateTime()) ? remoteForm.getEndDateTime() : MonthDateRange.fullTime(startTime);
		boolean allDay = Validator.isNotNull(remoteForm.getDayTab()) ? !remoteForm.getDayTab().equals(RemoteFormConstants.DAY_TABS.THAT_DAY) : true;
		
		list.add(new Object[]{remoteForm.getRemoteFormId(), content , startTime, endTime, colorSet[0], allDay });		
	//	list.add(new Object[]{remoteForm.getRemoteFormId(), content , startTime, endTime, allDay });
	}	
	
	String schedulerScript = "[";
	for (Object[] objs : list) {
		String remoteFormId = objs[0].toString();
		
		String viewURL = StringPool.BLANK;
		if (!remoteFormId.equals("0")) {
			PortletURL portletURL = renderResponse.createRenderURL();
			portletURL.setParameter("jspPage", "/html/view.jsp");
			portletURL.setParameter("remoteFormId", remoteFormId);
			portletURL.setParameter("redirect", currentURL);
			viewURL = portletURL.toString();
		}
		
		String content = objs[1].toString();
		String startDate = "new Date(" + auiSchedulerDateFormant.format((Date)objs[2]) + ")";
		String endDate = "new Date(" + auiSchedulerDateFormant.format((Date)objs[3]) + ")";
		String color = objs[4].toString();
		String allDay = objs[5].toString();

		StringBuilder sb = new StringBuilder();
		sb.append("content: '" + HtmlUtil.escapeJS(content) + "',");
		sb.append("startDate: " + startDate + ",");
		sb.append("endDate: " + endDate + ",");
		sb.append("color: '" + HtmlUtil.escapeJS(color) + "',");
		sb.append("borderColor: '" + HtmlUtil.escapeJS(color) + "',");
		sb.append("colorBrightnessFactor: " + 2.2 + ",");
		sb.append("colorSaturationFactor: " + 0.88 + ",");
		sb.append("borderWidth: '6px',");
		sb.append("borderStyle: 'ridge',");
		sb.append("meeting: true,");
		sb.append("allDay: " + allDay + ",");
		if (!Validator.isBlank(viewURL)) {
			sb.append("viewURL: '" + viewURL + "',");
		}
		
		schedulerScript += String.format("{%s},", sb.toString());
	}

	//去除最後一個逗點;
	if (list.size() > 0) {
		schedulerScript = schedulerScript.substring(0, schedulerScript.length() - 1);
	}
	schedulerScript += "]";
%>

<liferay-portlet:renderURL var="filterURL" varImpl="filterURL">
	<portlet:param name="jspPage" value="/html/tabs.jsp" />
	<portlet:param name="tabs1" value="<%=tabs1 %>" />
</liferay-portlet:renderURL>

<liferay-ui:panel title="filter" state="open">

	<aui:form action="${filterURL }" method="post" name="fm">
		<aui:row>
			<aui:col cssClass="text-center" span="2">
				<aui:select name="applicant" label="applicant" cssClass="span12">
					<%
					String keywords = StringPool.BLANK;
					int workStatus = Constants._WORKING_STATUS.WORKING;
					OrderByComparator obc = OrderByComparatorFactoryUtil.create("ee", "employeeKey", false);
					Set<Employee> employees = new LinkedHashSet<Employee>();
					
					if (permissionChecker.isOmniadmin()) {
						employees.addAll(EmployeeLocalServiceUtil.search(workStatus, keywords, QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc));
						
					} else {
						Employee curEmployee = EmployeeLocalServiceUtil.fetchByLRUserId(user.getUserId());
						
						if (Validator.isNotNull(curEmployee)) {
							employees.add(curEmployee);
							
							int level = 3;
							employees.addAll(EmployeeLocalServiceUtil.getEmployeeHierarchy(curEmployee, level, new LinkedList<Employee>()));
						}
					}
					
					for (Employee employee : employees) {
						String lrUserScreenName = Validator.isNotNull(employee.getLRUser()) ? employee.getLRUser().getScreenName() : StringPool.BLANK;
						String applicantLabel = employee.getName_() + StringPool.SLASH + employee.getEnglishName();
					%>
						<aui:option value="<%=lrUserScreenName %>" label="<%=applicantLabel %>" selected="<%=applicant.equals(lrUserScreenName) %>"/>
					<%
					}
					%>
				</aui:select>
			</aui:col>
			<aui:col width="20">
				<aui:button-row cssClass="text-center">
					<aui:button type="submit" value="filter" icon="icon-eye-open" />
				</aui:button-row>
			</aui:col>
		</aui:row>
	</aui:form>
</liferay-ui:panel>



<div style="float: left; margin: 10px 0px; ">
	<%

		String content = RemoteFormConstants.REMOTEFORM_TYPE;
		%>
		<span class="color-legend" style="background-color: <%=colorSet[0] %>;">
			<%=content %>
		</span>
</div>

<div class="clearfix"></div>


<div id="<portlet:namespace/>remoteFormScheduler"></div>



<aui:script use="aui-base, dd-drag">
	//https://alloyui.com/api/modules/aui-scheduler.html
	
	var calendar;
	A.use('aui-scheduler', function(A) {
		var events = <%=schedulerScript %>;
	    var eventRecorder = new A.CRSchedulerEventRecorder();
	    //var eventRecorder = new A.SchedulerEventRecorder();
	    var agendaView = new A.SchedulerAgendaView();
	    var dayView = new A.SchedulerDayView();
	    var monthView = new A.SchedulerMonthView();
	    var weekView = new A.SchedulerWeekView();

		calendar = new A.Scheduler({
			activeView: monthView,
			boundingBox: '#<portlet:namespace/>remoteFormScheduler',
			date: new Date(),
			eventRecorder: eventRecorder,
			items: events,
			render: true,
			views: [dayView, weekView, monthView, agendaView],
			on: {
		        "drag:mouseDown": function (event) {
		             // 防止拖曳
		        	event.preventDefault();
		         }
		     }
		});
	});
	
	Liferay.Service('/FansySoft-ClockInOut-portlet.clockinout/search',
	{
	    inOut: '',
	    clockTimeBegin: '<%=searchDateStartValue %>',
	    clockTimeEnd: '<%=searchDateEndValue %>',
	    workLocation: '',
	    keywords: '<%=applicant %>'
	}, function(objs) {
		//console.log(objs);
		if (calendar) {
			var events = [];
			for (var i = 0; i < objs.length; i++) {
				var clockInOut = objs[i];
				
				var color = "#339900";
				var content = '<%=LanguageUtil.get(portletConfig, locale, "clock-out") %>';
				
				if (clockInOut.inOut_ == 'in') {
					color = "#FF9933";
					content = '<%=LanguageUtil.get(portletConfig, locale, "clock-in") %>';
				}
				if (clockInOut.insistent) {
					color = "#B94A48";
					content = '<%=LanguageUtil.get(portletConfig, locale, "leave-work-early") %>';
				}
				var event = {};
				
				event['content'] = content;
				event['startDate'] = new Date(clockInOut.clockTime);
				event['endDate'] = new Date(clockInOut.clockTime);
				event['color'] = color;
				event['borderColor'] = color;
				event['colorBrightnessFactor'] = 1.5;
				event['colorSaturationFactor'] = 1.3;
				event['meeting'] = true;
				events.push(event);
			}
			calendar.addEvents(events);
			calendar.syncEventsUI();
		}
	});
	
	
	
	var portlet = A.one('#p_p_id<portlet:namespace/>');
	var refreshPortlet = function() { Liferay.Portlet.refresh("#p_p_id<portlet:namespace/>");};
	portlet.delegate('click', refreshPortlet, '#<portlet:namespace/>refreshButton');
	
	var applicantSelector = A.one('#<portlet:namespace/>applicant');
	
	if (applicantSelector) {
		applicantSelector.on('change', function(e) {
			//console.log('this.val():' + this.val() + "--");
			var data = {
				<portlet:namespace/>tabs1: '<%=tabs1 %>',
				<portlet:namespace/>applicant: this.val()
			}
			Liferay.Portlet.refresh("#p_p_id<portlet:namespace/>", data);
		});
	}
</aui:script>
