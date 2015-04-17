<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ include file="/browse/browse_form.jsp" %>
<%
	String s_icRfqHeader = (String) request.getAttribute("RfqHeader_icRfqHeader");
	String s_rfqNumber = (String) request.getAttribute("RfqHeader_rfqNumber");
	String s_rfqAmendment = (String) request.getAttribute("RfqHeader_rfqAmendment");
	String s_rfqStatus = (String) request.getAttribute("RfqHeader_status");
%>

<tsa:hidden name="browsePage" value="/browse/browse_std_question.jsp"/>
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_rfqNumber%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="<%=s_rfqAmendment%>"/>
<tsa:hidden name="RfqQuestion_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqHeader_status" value="<%=s_rfqStatus%>"/>
<tsa:hidden name="RfqHeader_rfqType" value="${RfqHeader_rfqType}"/>

<div id="StdQuestion"></div>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: addStdQuestions(); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_questions.jsp', 'RfqQuestionRetrieveByHeader'); void(0);">Return</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	hideArea("filterTextDisplay");
	hideArea("resetOriginalOption");

	function addStdQuestions()
	{
		var checkboxes = document.getElementsByName("c_checkbox");
		var qstIcs = document.getElementsByName("StdQuestion_icQuestion");
		var selectIcs = "";

		if (checkboxes.length > 1) {
			for(i = 0;i < checkboxes.length; i++) {
				var cbox = checkboxes[i];
				var ic = qstIcs[i];
				if (cbox.checked == true) {
					selectIcs = selectIcs + "<INPUT TYPE=\"HIDDEN\" NAME=\"StdQuestion_icQuestion\" value=\"" + ic.value + "\">";
				}else{
					ic.disabled="disabled";
					ic.name = "StdQuestion_icQuestionN";
				}
			}
		} else {
			var cbox = checkboxes;

			if (cbox.checked == true) {
				var ic = qstIcs;
				selectIcs = "<INPUT TYPE=\"HIDDEN\" NAME=\"StdQuestion_icQuestion\" value=\"" + ic.value + "\">";
			}
		}
		var myCell = document.getElementById("hiddenFields");
		doSubmit('/rfq/rfq_questions.jsp', 'BrowseSetInputRequestValues;RfqQuestionAddStandard;RfqQuestionRetrieveByHeader');
	}

	function browse(x) {
		frm.browseName.value = x;

		doSubmit('browse/browse_std_question.jsp', 'BrowseRetrieve');
	}

// End Hide script -->
</SCRIPT>