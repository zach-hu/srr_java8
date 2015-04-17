<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	List labelsList = (List) request.getAttribute("labelsList");
	String handlerProcessed = (String) request.getAttribute("handler"); 
	int labelsUpdated = 0;
	String actionCompleted = "updated to the database";
	if (labelsList != null) {
		labelsUpdated = labelsList.size();
	}
	
	if (HiltonUtility.ckNull(handlerProcessed).startsWith("LabelsRefresh")) {
		actionCompleted = "refreshed in the labels dictionary";
	}
%>
<table border="0" cellspacing="0" cellpadding="0" width="680px" height="350px">
<tr>
  <td align="center" valign="top" width="500px" id="systemSetupFrame">
    <div id="SystemProcessing" style="visibility: visible; position:absolute; top:150px; left:25px; height:400px;">
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
      <tr>
        <td width="100%" align="center" valign="top"><b>Refreshing labels dictionary from database... Please wait.</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border=1 width=200px height=15px></td>
      </tr>
      </table>
    </div>
    <div id="SystemProcessingComplete" style="visibility: hidden; display: none; position:absolute; top:150px; left:25px; height:400px;">
		<table border="0" cellspacing="0" cellpadding="0" width="100%">
		<tr>
			<td width="100%" align="center" valign="top"><b>COMPLETE - <%=labelsUpdated%> Label(s) have been <%=actionCompleted%> for <%=oid%>.</b></td>
		</tr>
		</table>
		<br>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
		<tr><td align=center><a href="javascript: doSubmit('/tsaadmin/xadmin_menu.jsp'); void(0);"><img src="<%=contextPath%>/images/button_return.gif" border=0 class=button alt="Return to Tsa Admin Menu"></a></td></tr>
		</table>
    </div>
  </td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

  frm = document.purchaseform;

  function thisLoad() {
    hideArea("SystemProcessing");
    displayArea("SystemProcessingComplete");
  }

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>