<%@ page import="com.tsa.puridiom.steporder.*" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>
<table border=0 cellpadding=0 cellspacing=0>
<tsa:tr><tsa:td colspan="3" height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" height="1px" width="100%"></tsa:td></tsa:tr>
<tsa:tr cssClass="sidebar"><tsa:td colspan="3" height="5px">&nbsp;</tsa:td></tsa:tr>

<%
	HashMap processMap = new HashMap();
	String	s_process_code = "";
	String	s_process_link = "";
	String	s_process_label = "";
	String	s_process_method = "";

	String	s_previous_link = "";
	String	s_previous_label = "";
	String	s_previous_method = "";
	String	s_next_link = "";
	String	s_next_label = "";
	String	s_next_method = "";

	boolean	b_current_process = false;

	int	i_step = 1;

	ProcessSteps steps = null;

	steps = new ProcessSteps("assetsteps_asset", oid);

	//ADITION
	String s_start_condition = "";
	String s_end_condition = "";
	if (s_current_process.equalsIgnoreCase("ASSET"))
	{
		s_start_condition = " if (frm.Asset_statusOriginal.value != frm.Asset_assetStatus.value && frm.AssetNote_stdText.value == '') { alert('You must enter a reason for change de status of the asset.'); } else { ";
		s_end_condition = " } ";
	}
	if (!(action.equalsIgnoreCase("new")))
	{
		s_current_method = "DoNothing";
	}

	//END ADITION

	for (int ip = 0; ip < steps.getSize(); ip++)
	{
		s_process_code = steps.getProcess(ip);
		s_process_link = steps.getLink(ip);
		s_process_label = steps.getLabel(ip);
		s_process_method = steps.getMethod(ip);

		processMap.put(s_process_code, "TRUE");

		if (s_current_process.equals(s_process_code))
		{
			b_current_process = true;
			s_current_process_method = s_process_method;

			if (ip > 0)
			{
				s_previous_link = steps.getLink(ip - 1);
				s_previous_label = steps.getLabel(ip - 1);
				s_previous_method = steps.getMethod(ip - 1);
			}
			if ( (ip + 1) < steps.getSize())
			{
				s_next_link = steps.getLink(ip + 1);
				s_next_label = steps.getLabel(ip + 1);
				s_next_method = steps.getMethod(ip + 1);
			}
		}
		else if (s_current_process.indexOf("LINE") == 0 && (s_process_code.equals("ITEMS") || s_process_code.equals("ITEM")))
		{
			b_current_process = false;

			if (ip > 0)
			{
				s_previous_link = steps.getLink(ip - 1);
				s_previous_label = steps.getLabel(ip - 1);
				s_previous_method = steps.getMethod (ip -1);
			}
			if ( (ip + 1) < steps.getSize())
			{
				s_next_link = steps.getLink(ip + 1);
				s_next_label = steps.getLabel(ip + 1);
				s_next_method = steps.getMethod(ip + 1);
			}
		}
		else
		{
			b_current_process = false;
		}

		if (b_current_process)
		{
%>
<tsa:tr height="25px">
	<tsa:td width="5px" cssClass="processOn">&nbsp;</tsa:td>
	<tsa:td cssClass="processOn" valign="middle">
    <div id="pxsmallbutton"><%=i_step%></div>
	</tsa:td>
	<tsa:td cssClass="processOn" noWrap="NOWRAP">&nbsp;<tsa:label labelName="<%=s_process_label%>" defaultString="<%=s_process_label %>" />&nbsp;</tsa:td>
</tsa:tr>
<%
		}
		else
		{
%>
<tsa:tr height="25px">
	<tsa:td cssClass="processOff" width="5px">&nbsp;</tsa:td>
	<tsa:td cssClass="processOff" valign="middle">
    <div id="pxsmallbutton"><a href="javascript: <% if(action.equalsIgnoreCase("itemnew")) { %>addassetonnext('<%= action %>');<% } else { %>doSubmit('<%=s_process_link%>', '<%=s_current_method%>;<%=s_process_method%>');<% } %>"><%=i_step%></a></div>
	</tsa:td>
	<tsa:td cssClass="processOff" noWrap="nowrap" valign="middle">
		<%if((s_process_link.indexOf("asset_general") > -1) || (s_process_link.indexOf("asset_review") > -1) || (s_process_link.indexOf("location/assetlocation_review") > -1))
		{%>
			&nbsp;<A HREF="javascript: <%=s_start_condition%><% if(action.equalsIgnoreCase("itemnew")) { %>addassetonnext('<%= action %>');<% } else { %> doSubmit('<%=s_process_link%>', '<%if (role.getAccessRights("ASSETS")>=1 && !s_current_process.equalsIgnoreCase("ASSETREVIEW")) {%><%=s_current_method%>;<% } %><%=s_process_method%>'); <% } %><%=s_end_condition%>" class="processOff"><tsa:label labelName="<%=s_process_label%>" defaultString="<%=s_process_label %>" checkRequired="false" /></A>&nbsp;
		<%}
		else
		{%>
			&nbsp;<A HREF="javascript: <%=s_start_condition%> frm.browseName.value = '<%=s_process_method%>'; setForBrowseAsset('<%=s_process_method%>'); <% if(action.equalsIgnoreCase("itemnew")) { %>addassetonnext('<%= action %>');<% } else { %>doSubmit('/browse/browse.jsp', '<%if (role.getAccessRights("ASSETS")>=1 && !s_current_process.equalsIgnoreCase("ASSETREVIEW")) {%><%=s_current_method%>;<% } %>BrowseRetrieve'); <% } %><%=s_end_condition%>" class="processOff"><tsa:label labelName="<%=s_process_label%>" defaultString="<%=s_process_label %>" checkRequired="false" /></A>&nbsp;
		<%}%>
	</tsa:td>
</tsa:tr>
<%			if (s_process_code.equals("ITEMS") || s_process_code.equals("ITEM"))
			{
%>
				<%//@ include file="/xchange/requests/req_item_sidebar.jsp" %>
<%			}

		}

		i_step++;
	}
%>
<tsa:tr cssClass="processOff"><tsa:td colspan="3" height="5px">&nbsp;</tsa:td></tsa:tr>
<tsa:tr><tsa:td colspan="3" height="1px" cssClass="darkShadow"><IMG SRC="<%=contextPath%>/images/none.gif" HEIGHT="1px" WIDTH="100%"></tsa:td></tsa:tr>
<tsa:tr cssClass="processOff"><tsa:td colspan="3" height="5px">&nbsp;</tsa:td></tsa:tr>

<tsa:tr>
	<tsa:td colspan="3">
		<table border="0" width="100%">
		<tsa:tr>
			<tsa:td align="left">
<%	if (s_previous_link.length() > 0) { %>
				<table border="0">
				<tsa:tr>
					<%if((s_previous_link.indexOf("asset_general") > -1) || (s_previous_link.indexOf("asset_review") > -1) || (s_previous_link.indexOf("location/assetlocation_review") > -1))
					{%>
						<tsa:td><div id="pxsmallbutton"><a href="javascript: <%=s_start_condition%> doSubmit('<%=s_previous_link%>', '<%if (role.getAccessRights("ASSETS")>=1) {%><%=s_current_method%>;<% } %><%=s_previous_method%>'); <%=s_end_condition%>"><<</a></div></tsa:td>
						<tsa:td cssClass="processOn"><a href="javascript: <%=s_start_condition%> doSubmit('<%=s_previous_link%>', '<%if (role.getAccessRights("ASSETS")>=1) {%><%=s_current_method%>;<% } %><%=s_previous_method%>'); <%=s_end_condition%>" class="processOn" title="<%=s_previous_label%>"><tsa:label labelName="ass-back" defaultString="Back"></tsa:label></a></tsa:td>
					<%}
					else
					{%>
						<tsa:td><div id="pxsmallbutton"><% if (action.equalsIgnoreCase("new") || !(s_current_process.equalsIgnoreCase("ASSET"))) { %><a href="javascript: <%=s_start_condition%> frm.browseName.value = '<%=s_previous_method%>'; setForBrowseAsset('<%=s_previous_method%>'); doSubmit('/browse/browse.jsp', '<%if (role.getAccessRights("ASSETS")>=1) {%><%=s_current_method%>;<% } %>BrowseRetrieve'); <%=s_end_condition%>"><% } %><<</a></div></tsa:td>
						<tsa:td cssClass="processOn"><% if (action.equalsIgnoreCase("new") || !(s_current_process.equalsIgnoreCase("ASSET"))) { %><a href="javascript: <%=s_start_condition%> frm.browseName.value = '<%=s_previous_method%>'; setForBrowseAsset('<%=s_previous_method%>'); doSubmit('/browse/browse.jsp', '<%if (role.getAccessRights("ASSETS")>=1) {%><%=s_current_method%>;<% } %>BrowseRetrieve'); <%=s_end_condition%>" class="processOn" title="<%=s_previous_label%>"><% } %><tsa:label labelName="ass-back" defaultString="Back"></tsa:label></a></tsa:td>
					<%}%>
				</tsa:tr>
				</table>
<%	} else { %>&nbsp;<% } %>
			</tsa:td>
			<tsa:td align="right">
<%	if (s_next_link.length() > 0) { %>
				<table border="0">
				<tsa:tr>
					<%if((s_next_link.indexOf("asset_general") > -1) || (s_next_link.indexOf("asset_review") > -1) || (s_next_link.indexOf("location/assetlocation_review") > -1))
					{%>
						<tsa:td cssClass="processOn"><a href="javascript: <%=s_start_condition%> <% if(action.equalsIgnoreCase("itemnew")) { %>addassetonnext('<%= action %>');<% } else { %>doSubmit('<%=s_next_link%>', '<%if (role.getAccessRights("ASSETS")>=1) {%><%=s_current_method%>;<% } %><%=s_next_method%>'); <% } %><%=s_end_condition%>" class="processOn" title="<%=s_previous_label%>"><tsa:label labelName="ass-next" defaultString="Next"></tsa:label></a></tsa:td>
						<tsa:td><div id="pxsmallbutton"><a href="javascript: <%=s_start_condition%> <% if(action.equalsIgnoreCase("itemnew")) { %>addassetonnext('<%= action %>');<% } else { %>doSubmit('<%=s_next_link%>', '<%if (role.getAccessRights("ASSETS")>=1) {%><%=s_current_method%>;<% } %><%=s_next_method%>'); <% } %><%=s_end_condition%>">>></a></div></tsa:td>
					<%}
					else
					{%>
						<tsa:td cssClass="processOn"><% if (action.equalsIgnoreCase("new") || !(s_current_process.equalsIgnoreCase("ASSET"))) { %><a href="javascript: <%=s_start_condition%> frm.browseName.value = '<%=s_next_method%>'; setForBrowseAsset('<%=s_next_method%>'); doSubmit('/browse/browse.jsp', '<%if (role.getAccessRights("ASSETS")>=1) {%><%=s_current_method%>;<% } %>BrowseRetrieve'); <%=s_end_condition%>" class="processOn" title="<%=s_previous_label%>"><% } %><tsa:label labelName="ass-next" defaultString="Next"></tsa:label></a></tsa:td>
						<tsa:td><div id="pxsmallbutton"><% if (action.equalsIgnoreCase("new") || !(s_current_process.equalsIgnoreCase("ASSET"))) { %><a href="javascript: <%=s_start_condition%> frm.browseName.value = '<%=s_next_method%>'; setForBrowseAsset('<%=s_next_method%>'); doSubmit('/browse/browse.jsp', '<%if (role.getAccessRights("ASSETS")>=1) {%><%=s_current_method%>;<% } %>BrowseRetrieve'); <%=s_end_condition%>"><% } %>>></a></div></tsa:td>
					<%}%>
				</tsa:tr>
				</table>
<%	} else { %>&nbsp;<% } %>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>

<tsa:tr><tsa:td colspan="3" cssClass="processOff"><br></tsa:td></tsa:tr>

</table>

<SCRIPT value=JavaScript>
<!-- Hide script
	<!--In this part we can a function to show the InvItems-->
	function viewInvItem(itemNum) {
		var myCell = document.getElementById("hiddenFields");
		myCell.innerHTML = "";
		var newInputField =  "<input type=\"hidden\" name=\"InvItem_itemNumber\" value=\"" + itemNum + "\" ><input type=\"hidden\" name=\"itemAction\" value=\"UPDATE\">";
		myCell.innerHTML = newInputField;
		doSubmit('/inventory/inv_item.jsp','InvItemRetrieveById');
	}

	function addassetonnext(action) {
		if ( action == "itemnew") {
			if (frm.Asset_itemNumber.value == "")
			{
				alert('You must enter a Item Number.');
			}
			else
			{
				doSubmit("/asset/location/assetlocation_review.jsp", "AssetAdd;InvItemAssetAdd;AssetRetrieveById");
			}
		}
	}
// End Hide script -->
</SCRIPT>