<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.graphs.DashboardFactory"%>
<%@ page import="com.tsa.puridiom.graphs.DashBoard"%>
<%@ page import="com.tsa.puridiom.graphs.*" %>
<%
String graphName = (String)request.getAttribute("graphName");
if(HiltonUtility.isEmpty(graphName)){		graphName = (String) request.getParameter("graphName");		}
if(HiltonUtility.isEmpty(uid)){					uid = (String) request.getParameter("uid");		}
if(HiltonUtility.isEmpty(oid)){					oid = (String) request.getParameter("oid");		}

DashBoard dashboard = DashboardFactory.getInstance(oid, "buyer", uid);
dashboard.setGraph(graphName);
Graph graph = dashboard.getGraph(graphName);
%>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>
<div id="pageLoading" style="width:50%;height:50%;top:25%;left:25%;position :absolute; display:block;visibility:hidden; z-index: 10; ">
<table align="center" border="0" cellspacing="0" cellpadding="0" >
<tr><td width="100%" align="center" valign="top"><br><b>Loading Graph... Please wait.</b><br><br><br><img src="<%=contextPath%>/images/yui/loading.gif" border=1 width=200px height=15px></td></tr>
</table>
</div>

<div id="<%=graphName%>" style="position: absolute; border:  1px solid #d1d3d4;  left: 10px; height: <%=graph.getHeigth()%>px; width: <%=graph.getWidth()%>px; z-index: 1;">
		<div id="<%=graphName%>Header" style="height: 15px; width: 100%; border-bottom: 1px solid #d1d3d4;">
			<table width="100%">
				<tbody>
					<tr>
						<td width="90%" align="center"><%=graph.getTitle()%></td>
						<td width="10%"><a href="javascript: hideMe('<%=graphName%>')"><img src="<%=contextPath%>/images/yui/tree/action_stop.gif" border="0"></a></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div><%=graph.getGraphInfo() %></div>
		<div id="<%=graphName%>Footer" style="border-top: 1px solid #d1d3d4; width: 100%;">
			<%java.util.List userWhere = graph.getUserWhere();
			  	if(userWhere.size() > 0)
			  	{%>
			  	<table align="center">
			  		<tr>
			  			<td>Period: </td>
			  			<td><select name="<%=graph.getName()%>_userFilter" size=1>
			  				<%String filter = (String)userWhere.get(1);%>
			  					<option value=":none" <%if(filter.equalsIgnoreCase(":none")) {%>selected="selected" <%} %>>None</option>
								<option value=":thisyear" <%if(filter.equalsIgnoreCase(":thisyear")) {%>selected="selected"<%} %>>This Year</option>
								<option value=":thisweek" <%if(filter.equalsIgnoreCase(":thisquarter")) {%>selected="selected"<%} %>>This Quarter</option>
								<option value=":thismonth" <%if(filter.equalsIgnoreCase(":thismonth")) {%>selected="selected"<%} %>>This Month</option>
								<option value=":thisweek" <%if(filter.equalsIgnoreCase(":thisweek")) {%>selected="selected"<%} %>>This Week</option>
								</select> </td>
						<td><a href="javascript: saveMe();">Filter</a> </td>
			  		</tr>
			  	</table>
			  	<%} %>
		</div>
	</div>
<tsa:hidden name="graphName" value="<%=graphName %>" />
<tsa:hidden name="prefName" value="" />
<tsa:hidden name="prefValue" value="" />
<%@ include file="/system/footer_popup.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script
	frm = document.purchaseform;
	<%if(graph.isUserHidden())
	{%>
		parent.hideArea('<%=graphName%>');
	<%}%>


	function showGraph()
	{
		parent.displayArea('<%=graphName%>');
		displayArea('pageLoading');
		frm.organizationId.value = "<%=oid%>";
		frm.userId.value = "${esapi:encodeForJavaScript(userId)}";
		frm.prefName.value = "<%=graphName%>hidden";
		frm.prefValue.value = "N";
		doSubmit('/graph/graph.jsp', 'GraphSaveViewUserPreference');
	}

	function hideMe(graphName)
	{
		parent.uncheckOption(graphName);
	}
	function hideGraph(graphName)
	{
		//displayArea('pageLoading');
		frm.organizationId.value = "<%=oid%>";
		frm.userId.value = "${esapi:encodeForJavaScript(userId)}";
		frm.prefName.value = "<%=graphName%>hidden";
		frm.prefValue.value = "Y";
		doSubmit('/graph/graph.jsp', 'GraphSaveViewUserPreference');
	}

	function closeiframe()
	{
		parent.document.getElementById('recframe').style.display="none";
	}
	function thisLoadPopup()
	{
  		addHandle(document.getElementsByTagName('body').item(0), window);
	}

	function closeMe()
	{
		parent.document.getElementById('recframe').style.display="none";
	}

	function saveMe()
	{
		displayArea('pageLoading');
		frm.organizationId.value = "<%=oid%>";
		frm.userId.value = "${esapi:encodeForJavaScript(userId)}";
		doSubmit('/graph/graph.jsp', 'GraphUserPreferenceUpdate');
	}

	// End Hide script -->
</SCRIPT>