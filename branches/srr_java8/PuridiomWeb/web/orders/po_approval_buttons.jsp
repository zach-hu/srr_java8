<div name="approve_link" style="visibility: visible;">
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
  <td id="approve_link" align="center" width="680px">
  	<br>
  	<br>
    <table border="0" cellspacing="0" cellpadding="0" width="665px" id="approverButtons">
    <tr>
		<td align="center"><a href="javascript: doSubmitApprove('N'); void(0);"><img src="<%=contextPath%>/images/approve_black.gif" border="0" alt="Approve"></a></td>
		<td align="center"><a href="javascript: rejectMe(); void(0);"><img src="<%=contextPath%>/images/reject_black.gif" border="0" alt="Reject"></a></td>
		<td align="center"><a href="javascript: rerouteMe(); void(0);"><img src="<%=contextPath%>/images/reroute_black.gif" border="0" alt="Reroute"></a></td>
		<td align="center"><a href="javascript: deferMe(); void(0);"><img src="<%=contextPath%>/images/defer_black.gif" border="0" alt="Defer"></a></td>
	</tr>
	<tr>
		<td align="center"><a href="javascript: doSubmitApprove('N'); void(0);">Click here to approve this Order.</a></td>
		<td align="center"><a href="javascript: rejectMe(); void(0);">Click here to reject this Order</a></td>
		<td align="center">&nbsp;<a href="javascript: rerouteMe(); void(0);">Click here to reroute this Order.</a></td>
		<td align="center">&nbsp;<a href="javascript: deferMe(); void(0);">Click here to defer this Order.</a></td>
	</tr>
    </table>
     <table border="0" cellspacing="0" cellpadding="0" width="680px" id="adminApproverButtons" style="visibility: hidden; display: none;">
	    <tsa:tr>
			<tsa:td align="center" width="20%"><a href="javascript: doSubmitApprove('N'); void(0);"><img src="<%=contextPath%>/images/approve_black.gif" border="0" alt="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approve", "Approve")%>"></a></tsa:td>
			<tsa:td align="center" width="20%"><a href="javascript: doSubmitApprove('Y'); void(0);"><img src="<%=contextPath%>/images/approve_for_all.gif" border="0" alt="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approve", "Approve")%>"></a></tsa:td>
			<tsa:td align="center" width="20%"><a href="javascript: rejectMe(); void(0);"><img src="<%=contextPath%>/images/reject_black.gif" border="0" alt="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reject", "Reject")%>"></a></tsa:td>
			<tsa:td align="center" width="20%"><a href="javascript: rerouteMe(); void(0);"><img src="<%=contextPath%>/images/reroute_black.gif" border="0" alt="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reroute", "Reroute")%>"></a></tsa:td>
			<tsa:td align="center" width="20%"><a href="javascript: deferMe(); void(0);"><img src="<%=contextPath%>/images/defer_black.gif" border="0" alt="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "defer", "Defer")%>"></a></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="center" width="20%"><a href="javascript: doSubmitApprove('N'); void(0);">
			<tsa:label labelName="clickhere-approveon" defaultString="Click here to approve on" /><br>
			<tsa:label labelName="behalf-currentapprover" defaultString="behalf of the current approver" />.</a></tsa:td>
			<tsa:td align="center" width="20%"><a href="javascript: doSubmitApprove('Y'); void(0);">
			<tsa:label labelName="clickhere-approveon" defaultString="Click here to approve on" /><br>
			<tsa:label labelName="behalf-allapprovers" defaultString="behalf of all approvers" />.</a></tsa:td>
			<tsa:td align="center" width="20%"><a href="javascript: rejectMe(); void(0);">
			<tsa:label labelName="clickhere-reject" defaultString="Click here to reject" />.</a></tsa:td>
			<tsa:td align="center" width="20%">&nbsp;<a href="javascript: rerouteMe(); void(0);">
			<tsa:label labelName="clickhere-reroute" defaultString="Click here to reroute" />.</a></tsa:td>
			<tsa:td align="center" width="20%">&nbsp;<a href="javascript: deferMe(); void(0);">
			<tsa:label labelName="clickhere-defer" defaultString="Click here to defer" />.</a></tsa:td>
		</tsa:tr>
    </table>
  </td>
</tr>
</table>
</div>