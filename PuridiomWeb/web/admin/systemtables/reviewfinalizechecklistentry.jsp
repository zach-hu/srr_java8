<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.ChecklistEntryResponseType" %>
<%@ page import="java.util.List.*" %>
<%@ page import="java.math.*" %>
<%
		ChecklistEntry checklistEntry = (ChecklistEntry) request.getAttribute("checklistEntry");
		if (checklistEntry == null) {
			checklistEntry = new ChecklistEntry();
		}
		BigDecimal bd_icQuestion = checklistEntry.getIcQuestion();
		String	s_referenceType = checklistEntry.getReferenceType();
		String	s_responseType = checklistEntry.getResponseType();
		String	s_questionText = checklistEntry.getQuestionText();
%>

<tsa:hidden name="ChecklistEntry_icQuestion" value="<%=bd_icQuestion%>"/>
<tsa:hidden name="ResponseValue_icQuestion" value="<%=bd_icQuestion%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Review Finalize Entry</div>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top width=100%>
		<table border=0 cellspacing=0 cellpadding=0 width=100%>
		<tr>
			<td valign=top>
				<table border=0 cellpadding=0 cellspacing=2 width=250px align=center>
				<tr>
					<td colspan="2">
						<tsa:hidden name="ChecklistEntry_referenceType" value="POH"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<tsa:hidden name="ChecklistEntry_responseType" value="TEXT"/>
					</td>
				</tr>
				<tr>
					<td nowrap align=right valign=top class=processOn>Entry Text:&nbsp;</td>
					<td><textarea wrap=nonvirtual name="ChecklistEntry_questionText" cols=50 rows=5 ONKEYDOWN="limit(frm.ChecklistEntry_questionText, 250);" ONKEYUP="limit(frm.ChecklistEntry_questionText, 250);">${esapi:encodeForHTML(checklistEntry.questionText)}</textarea></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'ResponseValueUpdateList;ChecklistEntryUpdateById;BrowseRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'BrowseRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	frm.browseName.value="checklistentry-reviewfinalize";

	function limit(field, maxlimit)
	{
		if (field.value.length > maxlimit) {
			// field is too long...trim it!
			field.value = field.value.substring(0, maxlimit);
		}
	}

	function validateForm() {
		var handlers = frm.handler.value;
		if (handlers.indexOf("ResponseValueUpdateList") >= 0) {
			var questionText = frm.ChecklistEntry_questionText.value;

			if (isEmpty(questionText)) {
				alert("You must enter an entry to save.");
				return false;
			}

			var responseType = frm.ChecklistEntry_responseType.value;

			if (responseType == "MC") {
				var cnt = 0;
				var emptyCnt = 0;

				for (var i=0; i < responseRows; i++) {
					var responseValue = frm.ResponseValue_responseValue[i].value;
					var responseText = frm.ResponseValue_responseText[i].value;

					if (!isEmpty(responseValue) && !isEmpty(responseText)) {
						cnt++;
					} else if (isEmpty(responseValue) && !isEmpty(responseText)) {
						emptyCnt++;
					} else if (!isEmpty(responseValue) && isEmpty(responseText)) {
						emptyCnt++;
					}
				}
				if (cnt < 2) {
					alert("You must specify at least 2 possible responses for Multiple Choice Questions.");
					return false;
				}
				if (emptyCnt > 0) {
					alert("You must specify the value and text for each possible response.");
					return false;
				}
				deleteEmptyResponseRows();
			}
		}
		return true;
	}


// End Hide script -->
</SCRIPT>