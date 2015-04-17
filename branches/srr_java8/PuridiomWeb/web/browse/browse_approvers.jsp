<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ page import="java.math.BigDecimal" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/filter.js"></script>

<%
	String	formField = (String) request.getAttribute("formField");
	String	fromPage = (String) request.getAttribute("fromPage");
	String	row = (String) request.getAttribute("index");
	String	addUserRule = (String) request.getAttribute("addUserRule");
	String	fyiApprover = (String) request.getAttribute("fyiApprover");
	String addUserAmount = (String) request.getAttribute("addUserAmount");
	String formtype = (String) request.getAttribute("formtype");
	if (formField == null)	{	formField = "";	}
	if (fromPage == null)	{	fromPage = "";	}
	if (row == null)			{	row = "0";			}
	if (addUserRule == null)	{	addUserRule = "";	}
	if (fyiApprover == null)	{	fyiApprover = "N";	}
	if (addUserAmount == null)	{	addUserAmount = "0.00";	}
	if (formtype == null)	{	formtype = "";	}
%>

<div id=pageForm style="display:none;visibility:hidden;">
<%@ include file="/browse/browse_form.jsp" %>

<tsa:hidden name="browsePage" value="/browse/browse_approvers.jsp"/>
<tsa:hidden name="browseName" value=""/>
<tsa:hidden name="formField" value="<%=formField%>"/>
<tsa:hidden name="ApprovalLog_icHeader" value=""/>
<tsa:hidden name="addUser" value=""/>
<tsa:hidden name="addUserRule" value="<%=addUserRule%>"/>
<tsa:hidden name="fyiApprover" value="<%=fyiApprover%>"/>
<tsa:hidden name="addUserAmount" value="<%=addUserAmount%>"/>
<tsa:hidden name="index" value="<%=row%>"/>
<tsa:hidden name="fromPage" value="<%=fromPage%>"/>
<tsa:hidden name="formtype" value="<%=formtype%>"/>

<table width=680px border=0>
<tr>
	<td align=center>
<%	if (fromPage.equalsIgnoreCase("routinglist")) { %>
		<div id="pxbutton"><a href="javascript: cancelAddApprover(); void(0);">Cancel</a></div>
<%	} else { %>
		<div id="pxbutton"><a href="javascript: 	doSubmit('/system/close_window.jsp', 'BrowseCleanupByBrowseId');">Close</a></div>
<%	} %>
	</td>
</tr>
</table>

</div>
<div id=pageLoading style="display:block;visibility:visible;">
<table border=0 cellspacing=0 cellpadding=0 width=100%>
<tr><td width=100% align=center valign=top><br><b>Loading Page... Please wait.</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border=1 width=200px height=15px></td></tr>
</table>
</div>

<div id="<%=browseObject.getBrowseName()%>-browse-instructions" style="visibility:hidden; display:none;">
<br>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
	<td align=right width=30px><img src="<%=contextPath%>/images/alert.gif" border=0></td>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr><td>Select an approver below to add to the routing list for your requisition.</td></tr>
		<!--tr><td>If you cannot find the person you are looking for by filtering through the list of approvers below, <a href="javascript: browseNonApprovers(); void(0);">CLICK HERE</a>.</td></tr-->
		</table>
	</td>
</tr>
</table>
</div>
<div id="user-defined-approver-browse-instructions" style="visibility:hidden; display:none;">
<br>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
	<td align=right width=30px><img src="<%=contextPath%>/images/alert.gif" border=0></td>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr><td>Select an approver below to add to the routing list for your requisition.</td></tr>
		<!--tr><td>If you cannot find the person you are looking for by filtering through the list of approvers below, <a href="javascript: browseNonApprovers(); void(0);">CLICK HERE</a>.</td></tr-->
		</table>
	</td>
</tr>
</table>
</div>
<div id="non-approver-browse-instructions" style="visibility:hidden; display:none;">
<br>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
	<td align=right width=30px><img src="<%=contextPath%>/images/alert.gif" border=0></td>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr><td>Select a user below to add as an approver.  This user will then also be added to the routing list for your requisition.</td></tr>
		</table>
	</td>
</tr>
</table>
</div>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var rowSelect;
	var selectedData;
	var row = "<%=row%>";

	hideArea("resetOriginalOption");
	hideArea("resetFilterOptions");

	 if (filterSet) {
		displayArea("resetOption");
	} else {
		hideArea("resetOption");
	}


<%	if (formField.indexOf("ApprovalLog_") == 0) {%>
		frm.ApprovalLog_icHeader.value = "<%=(String) request.getAttribute("RequisitionHeader_icReqHeader")%>";
		frm.RequisitionHeader_icReqHeader.value = "<%=(String) request.getAttribute("RequisitionHeader_icReqHeader")%>";
<%	}%>

	function poApproverAdd(x, y)
	{
		var fld = "<%=formField%>";
		var user = x;
		var userName = y;

		if (fld.indexOf("poApprovers") >= 0)
		{
			window.parent.addApprover(user, userName);
		}

		doSubmit('/system/close_window.jsp', 'BrowseCleanupByBrowseId');
	}

	function returnMe(x) {
		var fld = "<%=formField%>";
		selectedData = x;

		if (fld.indexOf("ApprovalLog_") == 0)
		{
			window.parent.frm.addUser.value = x;
			window.parent.frm.addUserRule.value = frm.addUserRule.value;
			window.parent.frm.fyiApprover.value = frm.fyiApprover.value;
			window.parent.frm.addUserAmount.value = frm.addUserAmount.value;
			if (frm.fyiApprover.value == "Y") {
				window.parent.frm.insertBefore.value = "-1";
			}
			<%	if (formtype.equalsIgnoreCase("REQ")) { %>
			window.parent.doSubmit('/requests/req_routinglist_no_popup.jsp', 'ApprovalLogAdd', 'width=505px', 'height=450px');
			<%	} else if (formtype.equalsIgnoreCase("PO")) { %>
			window.parent.doSubmit('/orders/po_routinglist_no_popup.jsp', 'ApprovalLogAdd', 'width=505px', 'height=450px');
			<%	} else if (formtype.equalsIgnoreCase("IVC")) { %>
			window.parent.doSubmit('/invoice/iv_routinglist.jsp', 'ApprovalLogAdd', 'width=505px', 'height=450px');
			<%	} %>
			doSubmit('/system/close_window.jsp', 'BrowseCleanupByBrowseId');
		}
		else if (fld.indexOf("poApprovers") >= 0)
		{
			window.parent.addApprover(x, frm.UserProfile_displayName[rowSelect].value);
		}

		doSubmit('/system/close_window.jsp', 'BrowseCleanupByBrowseId');
	}

	function browse(x) {
		frm.browseName.value = x;

		doSubmit('/browse/browse_approvers.jsp', 'BrowseRetrieve');
	}

	function cancelAddApprover()
	{
		frm.ApprovalLog_icHeader.value = "<%=(String) request.getAttribute("RequisitionHeader_icReqHeader")%>";
		frm.RequisitionHeader_icReqHeader.value = "<%=(String) request.getAttribute("RequisitionHeader_icReqHeader")%>";

		doSubmit('/requests/req_routinglist.jsp', 'ApprovalLogRetrieveByHeader');
	}

	function browseNonApprovers() {
		browse("non-approver");
	}

	function addAsApprover(x) {
	}

	document.getElementById("instructions").innerHTML = document.getElementById(browseName + "-browse-instructions").innerHTML;
	document.getElementById(browseName + "-browse-instructions").innerHTML = "";

	displayArea("instructions");

	hideArea("pageLoading");
	displayArea("pageForm");

// End Hide script -->
</SCRIPT>