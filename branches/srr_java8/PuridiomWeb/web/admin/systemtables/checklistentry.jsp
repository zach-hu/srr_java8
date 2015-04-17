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

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Checklist Entry</div>
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
					<td nowrap align=right class=processOn>Document Type:&nbsp;</td>
					<td>
						<select name="ChecklistEntry_referenceType" tabindex=17>
							<option value="REQ" <% if (s_referenceType.equals("REQ")){ %>SELECTED<%}%>>Requisition</option>
							<option value="RFQ" <% if (s_referenceType.equals("RFQ")){ %>SELECTED<%}%>>Solicitation</option>
							<option value="POH" <% if (s_referenceType.equals("POH")){ %>SELECTED<%}%>>Order</option>
						</select>
					</td>
				</tr>
				<tr>
					<td nowrap align=right class=processOn>Response Type:&nbsp;</td>
					<td>
						<select name="ChecklistEntry_responseType" tabindex=17 onchange="setupResponseValues(false);">
							<option value="YN" <% if (s_responseType.equals("YN")){ %>SELECTED<%}%>><%=ChecklistEntryResponseType.toString("YN", oid)%></option>
							<option value="YNN" <% if (s_responseType.equals("YNN")){ %>SELECTED<%}%>><%=ChecklistEntryResponseType.toString("YNN", oid)%></option>
							<option value="TEXT" <% if (s_responseType.equals("TEXT")){ %>SELECTED<%}%>><%=ChecklistEntryResponseType.toString("TEXT", oid)%></option>
							<option value="BOTH" <% if (s_responseType.equals("BOTH")){ %>SELECTED<%}%>><%=ChecklistEntryResponseType.toString("BOTH", oid)%></option>
							<option value="MC" <% if (s_responseType.indexOf("MC")>= 0){ %>SELECTED<%}%>><%=ChecklistEntryResponseType.toString("MC", oid)%></option>
						</select>
					</td>
				</tr>
				<tr>
					<td nowrap align=right valign=top class=processOn>Entry Text:&nbsp;</td>
					<td><textarea wrap=nonvirtual name="ChecklistEntry_questionText" cols=50 rows=5 ONKEYDOWN="limit(frm.ChecklistEntry_questionText, 250);" ONKEYUP="limit(frm.ChecklistEntry_questionText, 250);">${esapi:encodeForHTML(checklistEntry.questionText)}</textarea></td>
				</tr>
				</table>
			</td>
			<td valign=top>
				<table border=0 cellpadding=0 cellspacing=0 width=250px align=center id="responseValues">
				<tr>
					<td class=label align=center>Response Value</td>
					<td class=label align=center>Response Option</td>
				</tr>
<%
			List	responseValueList = checklistEntry.getResponseValueList();
			for (int ir = 0; ir < 5; ir++) {
				ResponseValue responseValue;
				if (responseValueList != null && ir < responseValueList.size()) {
					responseValue = (ResponseValue) responseValueList.get(ir);
				} else {
					ResponseValuePK responseValuePK = new ResponseValuePK();
					responseValue = new ResponseValue(responseValuePK);
				}
		%>
				<tr>
					<td align=center><input type=text name="ResponseValue_responseValue" value="<%=responseValue.getComp_id().getResponseValue()%>" size="6"><tsa:hidden name="ResponseValue_icQuestion" value="<%=bd_icQuestion%>"/></td>
					<td align=center><input type=text name="ResponseValue_responseText" value="<%=responseValue.getResponseText()%>" size="20"></td>
				</tr>
<%		}%>
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
	frm.browseName.value="checklistentry-admin";

	var responseRows = frm.elements.item("ResponseValue_responseValue").length;

	function limit(field, maxlimit)
	{
		if (field.value.length > maxlimit) {
			// field is too long...trim it!
			field.value = field.value.substring(0, maxlimit);
		}
	}

	setupResponseValues(true);

	function setupResponseValues(initialSetup) {
		var responseType = frm.ChecklistEntry_responseType[frm.ChecklistEntry_responseType.selectedIndex].value;

		if (responseType == "YN" || responseType == "BOTH") {
			clearResponseValues();
			frm.ResponseValue_responseValue[0].value = "Y";
			frm.ResponseValue_responseValue[0].disabled = true;
			frm.ResponseValue_responseText[0].value = "Yes";
			frm.ResponseValue_responseText[0].disabled = true;
			frm.ResponseValue_responseValue[1].value = "N";
			frm.ResponseValue_responseValue[1].disabled = true;
			frm.ResponseValue_responseText[1].value = "No";
			frm.ResponseValue_responseText[1].disabled = true;

			for (var i=2; i < responseRows; i++) {
				frm.ResponseValue_responseValue[i].disabled = true;
				frm.ResponseValue_responseText[i].disabled = true;
			}
		} else if (responseType == "YNN") {
			clearResponseValues();
			frm.ResponseValue_responseValue[0].value = "Y";
			frm.ResponseValue_responseValue[0].disabled = true;
			frm.ResponseValue_responseText[0].value = "Yes";
			frm.ResponseValue_responseText[0].disabled = true;
			frm.ResponseValue_responseValue[1].value = "N";
			frm.ResponseValue_responseValue[1].disabled = true;
			frm.ResponseValue_responseText[1].value = "No";
			frm.ResponseValue_responseText[1].disabled = true;
			frm.ResponseValue_responseValue[2].value = "NA";
			frm.ResponseValue_responseValue[2].disabled = true;
			frm.ResponseValue_responseText[2].value = "N/A";
			frm.ResponseValue_responseText[2].disabled = true;

			for (var i=3; i < responseRows; i++) {
				frm.ResponseValue_responseValue[i].disabled = true;
				frm.ResponseValue_responseText[i].disabled = true;
			}
		} else if (responseType == "MC") {
			if (!initialSetup) {
				clearResponseValues();
			}
		} else if (responseType == "TEXT") {
			clearResponseValues();

			for (var i=0; i < responseRows; i++) {
				frm.ResponseValue_responseValue[i].disabled = true;
				frm.ResponseValue_responseText[i].disabled = true;
			}
		}
	}

	function clearResponseValues() {
		for (var i=0; i < responseRows; i++) {
			frm.ResponseValue_responseValue[i].disabled = false;
			frm.ResponseValue_responseValue[i].value = "";
			frm.ResponseValue_responseText[i].disabled = false;
			frm.ResponseValue_responseText[i].value = "";
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

			var responseType = frm.ChecklistEntry_responseType[frm.ChecklistEntry_responseType.selectedIndex].value;

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

	function deleteEmptyResponseRows() {
		var myTable = document.getElementById("responseValues");

		for (var i=responseRows-1; i >= 0; i--) {
			var responseValue = frm.ResponseValue_responseValue[i].value;
			var responseText = frm.ResponseValue_responseText[i].value;

			if (isEmpty(responseValue) && isEmpty(responseText)) {
				deleteRow(myTable, i+1);
			}
		}
	}


// End Hide script -->
</SCRIPT>