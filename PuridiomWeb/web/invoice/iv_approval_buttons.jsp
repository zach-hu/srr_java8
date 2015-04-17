<div name="approve_link" style="visibility: visible;">
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
  <td id="approve_link" align="center" width="680px">
  	<br>
  	<br>
    <table border="0" cellspacing="0" cellpadding="0" width="680px">
	    <tr>
	    	<td align="center" width="6%">&nbsp;</td>
			<td align="center" width="22%"><a href="javascript: approveMe(); void(0);"><img src="<%=contextPath%>/images/approve_black.gif" border="0" alt="Approve"></a></td>
			<td align="center" width="22%"><a href="javascript: rejectMe(); void(0);"><img src="<%=contextPath%>/images/reject_black.gif" border="0" alt="Reject"></a></td>
			<td align="center" width="22%"><a href="javascript: rerouteMe(); void(0);"><img src="<%=contextPath%>/images/reroute_black.gif" border="0" alt="Reroute"></a></td>
<%	if (HiltonUtility.isEmpty(override) || !user.isAnOverrider()) {%>
			<td align="center" width="22%"><a href="javascript: deferMe(); void(0);"><img src="<%=contextPath%>/images/defer_black.gif" border="0" alt="Defer"></a></td>
<%	} %>
			<td align="center" width="6%">&nbsp;</td>
		</tr>
<%	if (HiltonUtility.ckNull(override).equals("Y") && user.isAnOverrider()) {%>
		<tr>
			<td align="center" width="6%">&nbsp;</td>
			<td align="center" width="22%"><a href="javascript: approveMe(); void(0);">Click here to approve on<br>behalf of the current approver.</a></td>
			<td align="center" width="22%"><a href="javascript: rejectMe(); void(0);">Click here to reject.</a></td>
			<td align="center" width="22%">&nbsp;<a href="javascript: rerouteMe(); void(0);">Click here to reroute.</a></td>
<!--			<td align="center" width="22%">&nbsp;<a href="javascript: deferMe(); void(0);">Click here to defer.</a></td>-->
			<td align="center" width="6%">&nbsp;</td>
		</tr>
<%	} %>
    </table>
  </td>
</tr>
</table>
</div>