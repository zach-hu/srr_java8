<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<%	String report = (String)request.getAttribute("report"); %>

<table border="0" cellspacing="0" cellpadding="0" width="100%">
<tsa:tr>
	<tsa:td width="100%" align="center" valign="top"><br><b><tsa:label labelName="printingAssignedRequisitions" defaultString="Printing Assigned Requisitions... Please wait" />.</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border=1 width=200px height=15px></tsa:td>
</tsa:tr>
</table>


<br>
<br>

<br/>
<%@ include file="/system/footer.jsp" %>
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	self.focus();
	openReport('<%=report%>');
	setTimeout("doSubmit('/menu/main_menu.jsp', 'DoNothing')", 5000);

	function openReport(report)
	{
	  popupUrl = "";
	  popupHandler = "PrintAssignedPdfRequisitions";
	  popupUserId = frm.userId.value;
	  popupOrganizationId = frm.organizationId.value;
	  popupParameters = "report=" + report;
	  if (theFocus == null) { theFocus = 'document_window'; }

	  var winspecs = "WIDTH=680,HEIGHT=680,resizable=1,scrollbars=1,menubar=1,location=0,top=0,left=0";
	  document_window = window.open("<%=contextPath%>/system/popup_html.jsp", "document_window", winspecs);

	  if (theFocus == 'main') {
	    self.focus();
	  }
	  else {
	    document_window.focus();
	  }

	  if (document_window.opener == null) document_window.opener = window;
	  self.name = "main";
	}

// end hiding contents -->
</SCRIPT>
