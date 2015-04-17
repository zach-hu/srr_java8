<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<% pageContext.setAttribute("oid", oid);
   pageContext.setAttribute("language", language);%>
<%
	String	helpType = HiltonUtility.ckNull((String) request.getAttribute("helpType"));
	String	currentApproverId = HiltonUtility.ckNull((String) request.getAttribute("currentApproverId"));
	String	currentApprover = UserManager.getInstance().getUser(oid, currentApproverId).getDisplayName();
%>

<script language="JavaScript1.2" src="<%=contextPath%>/scripts/calendar.js"></script>

<tsa:hidden name="allowBrowse" value="true"/>

<br>

<%	if (helpType.equals("CALLFORWARDMSG")) {%>
<table border=0 cellpadding=0 cellspacing=0 width=100%>
<tsa:tr>
	<tsa:td align="center">
		<table border=0 cellpadding=0 cellspacing=0 width=450px>
		<tsa:tr>
			<tsa:td cssClass="label" align="center">
				<tsa:label labelName="" defaultString=""/> <tsa:label labelName="approverOnLeaveCallForwardAnother" defaultString="If your approver is on leave, you can temporarily set the requisitions to be call forwarded to another approver by clicking on the call forward icon"/> <img src="<%=contextPath%>/images/call_forward.gif" border=0> <tsa:label labelName="locateNextApprover" defaultString="located next to the appropriate approver"/>.
			</tsa:td>
		</tsa:tr>
		<tsa:tr><tsa:td><br><br></tsa:td></tsa:tr>
		<tsa:tr><tsa:td width="100%" align="center"><a href="javascript: cancel(); void(0);"><img class="button" src="<%=contextPath%>/images/button_ok.gif" border=0 alt="ok"></a></tsa:td></tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>
<%	} else if (helpType.equals("CALLFORWARD")) {%>
<table border=0 cellpadding=0 cellspacing=0 width=100%>
<tsa:tr>
	<tsa:td align="center">
		<table border=0 cellpadding=2 cellspacing=0>
		<tsa:tr>
			<tsa:td><tsa:label labelName="clickCallForward" defaultString="Click on the 'Call Forward To' link to select a temporary approver for"/> <%=currentApprover%>.</tsa:td>
		</tsa:tr>
		</table>

		<br>

		<table border=0 cellpadding=2 cellspacing=0>
		<tsa:tr>
			<tsa:td cssClass="label" align="right"><a href="javascript: browseLookup('UserProfile_callForward', 'approver'); void(0);" title="Click here to choose the approver call forward User ID or enter the User ID in the box on the right."><tsa:label labelName="call-forward-to" defaultString="Call Forward To" checkRequired="true"/>:</a></tsa:td>
			<tsa:td><input type=text name="UserProfile_callForward" value=""></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td>&nbsp;</tsa:td>
			<tsa:td><input type=text name="as_approverName" value="" disabled></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td cssClass="label" align="right"><tsa:label labelName="callForwardExpires" defaultString="Call Forward Expires"/>:</tsa:td>
			<tsa:td>
				<input type=text name="callForwardExpirationDate" value="">
				<a href="javascript: show_calendar('callForwardExpirationDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
			</tsa:td>
		</tsa:tr>
		</table>

		<br>

		<table border=0 cellpadding=0 cellspacing=0 width=300px>
		<tsa:tr>
			<tsa:td width="50%" align="center"><a href="javascript: setCallForward(); void(0);"><img class="button" src="<%=contextPath%>/images/button_ok.gif" border=0 alt="ok"></a></tsa:td>
			<tsa:td width="50%" align="center"><a href="javascript: cancel(); void(0);"><img class="button" src="<%=contextPath%>/images/button_cancel.gif" border=0 alt="cancel"></a></tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>
<%	} else if (helpType.equals("ADDAPPROVERMSG")) {%>
<table border=0 cellpadding=0 cellspacing=0 width=100%>
<tsa:tr>
	<tsa:td align="center">
		<table border=0 cellpadding=0 cellspacing=0 width=450px>
		<tsa:tr>
			<tsa:td cssClass="label" align="center">
				<tsa:label labelName="addApproverIcon" defaultString="To add an approver, click on the add icon"/> <img src="<%=contextPath%>/images/cmd_add_approver.gif" border=0>&nbsp;&nbsp;<tsa:label labelName="locatedAboveApprovalRule" defaultString="located above the group of approvers for the appropriate approval rule"/>.
			</tsa:td>
		</tsa:tr>
		<tsa:tr><tsa:td><br><br></tsa:td></tsa:tr>
		<tsa:tr><tsa:td width="100%" align="center"><a href="javascript: cancel(); void(0);"><img class="button" src="<%=contextPath%>/images/button_ok.gif" border=0 alt="ok"></a></tsa:td></tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>
<%	} else if (helpType.equals("REMOVEAPPROVERMSG")) {%>
<table border=0 cellpadding=0 cellspacing=0 width=100%>
<tsa:tr>
	<tsa:td align="center">
		<table border=0 cellpadding=0 cellspacing=0 width=450px>
		<tsa:tr>
			<tsa:td cssClass="label" align="center">
				<!--You can permantly remove an approver from the list as long as the approver is not required to approve this requisition.  --><tsa:label labelName="" defaultString="To remove an approver, click on the delete icon"/> <img src="<%=contextPath%>/images/delete.gif" border=0>&nbsp;&nbsp;<tsa:label labelName="locatedNextAppropriateApprover" defaultString="located next to the appropriate approver"/>.
			</tsa:td>
		</tsa:tr>
		<tsa:tr><tsa:td><br><br></tsa:td></tsa:tr>
		<tsa:tr><tsa:td width="100%" align="center"><a href="javascript: cancel(); void(0);"><img class="button" src="<%=contextPath%>/images/button_ok.gif" border=0 alt="ok"></a></tsa:td></tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>
<%	} else {%>
<tsa:label labelName="helpType" defaultString="HELP TYPE"/> = <%=helpType%>
<%	}%>

<%@ include file="/system/footer.jsp" %>

<!---- JavaScripts for Entry Validation ----->
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	thisfrm = document.purchaseform;
	self.focus();

	function cancel() {
		window.top.hidePopWin();
	}

	function openWindow (url, w, h) {
		if (browser == "Netscape") {
			if (w == undefined) { w = 'WIDTH=500'; }
			if (h == undefined) { h = 'HEIGHT=300'; }
			if (theFocus == undefined) { theFocus = 'detail'; }
		}
		else {
			if (w == null) { w = 'WIDTH=500'; }
			if (h == null) { h = 'HEIGHT=300'; }
			if (theFocus == null) { theFocus = 'detail'; }
		}
		var winspecs = w +","+ h +",resizable=1,scrollbars=1,menubar=0,location=0";

		detail_window = window.open("<%=contextPath%>/system/popup_html.jsp", "detail_window", winspecs);

		if (theFocus == 'main') {
			self.focus();
		}
		else {
			detail_window.focus();
		}

		if (detail_window.opener == null) detail_window.opener = window;
		self.name = "main";
	}

// end hiding contents -->
</SCRIPT>