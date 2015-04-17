<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>

<%
	String approverId = HiltonUtility.ckNull((String) request.getAttribute("approverId"));
	String approvalRule = HiltonUtility.ckNull((String) request.getAttribute("approvalRule"));
	String ruleNotes = HiltonUtility.ckNull((String) request.getAttribute("ruleNotes"));
	String ruleType = HiltonUtility.ckNull((String) request.getAttribute("ruleType"));
	String currentRow = (String) request.getAttribute("currentRow");
	String rowCount = (String) request.getAttribute("rowCount");
	boolean allowEdit = HiltonUtility.ckNull((String) request.getAttribute("allowEdit")).equals("true");

	UserProfile approver = UserManager.getInstance().getUser(oid, approverId);

	if (HiltonUtility.isEmpty(rowCount)) {
		rowCount = "0";
	}
	if (HiltonUtility.isEmpty(currentRow)) {
		currentRow = "0";
	}
	if (HiltonUtility.isEmpty(ruleType)) {
		ruleType = "STD";
	}
%>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "apprulenotes", "Rule Notes")%> for <%=approver.getDisplayName()%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=480px>
<tr>
	<td valign=top align=center>
		<table border=0 cellspacing=0 cellpadding=2 height=100%>
		<tr>
			<td valign=top align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApprovalRule", "Approval Rule")%>:</td>
			<td valign=top><%=approvalRule%></td>
		</tr>
		<tr><td colspan=2><br></td></tr>
		<tr>
			<td valign=top align=right class=label nowrap>Notes:</td>
<%	if (allowEdit) {%>
			<td valign=top><input type=text name="ruleNotes"size=65 maxLength=60 value="<%=ruleNotes%>" tabIndex=1></td>
<%	} else {%>
			<td valign=top><%=ruleNotes%></td>
<%	}%>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=480px>
<tr>
<%	if (allowEdit) {%>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: saveText(); void(0);">Save</a></div></td>
<%	}%>
	<td width=* align=center><div id="pxbutton"><a href="javascript: window.top.hidePopWin(); void(0);">Close</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var rowCount = <%=rowCount%>;

	frm.ruleNotes.focus();

	function saveText()
	{
		if (rowCount > 1)
		{
<%	if (ruleType.equals("STD")) {%>
			window.parent.frm.AppRule_notes[<%=currentRow%>].value = frm.ruleNotes.value;
<%	} else if (ruleType.equals("EXT")) {%>
			window.parent.frm.AppRulesExt_notes[<%=currentRow%>].value = frm.ruleNotes.value;
<%	}%>
			window.top.hidePopWin();
		}
		else
		{
<%	if (ruleType.equals("STD")) {%>
			window.parent.frm.AppRule_notes.value = frm.ruleNotes.value;
<%	} else if (ruleType.equals("EXT")) {%>
			window.parent.frm.AppRulesExt_notes.value = frm.ruleNotes.value;
<%	}%>
			window.top.hidePopWin();
		}
	}

// End Hide script -->
</SCRIPT>