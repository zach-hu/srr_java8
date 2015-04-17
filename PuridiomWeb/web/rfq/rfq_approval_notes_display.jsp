<%
	List routingList = (List) request.getAttribute("routingList");
	String lastApproverName="";
	if (!(routingList == null))
	{%>
		<br>
	    <table border="1" cellpadding="2" cellspacing="0" align="center" width="99%">
		<tsa:tr><tsa:td cssClass="browseHdrDk" height="16px"><b><tsa:label labelName="previousApproverNotes" defaultString="Previous Approver Notes"/></b></tsa:td></tsa:tr>
		<tsa:tr>
			<tsa:td align="center" cssClass="summary">
			    <table border="0" cellpadding="2" cellspacing="0" align="center" width="100%" class="summary">
<%
		for (Iterator iterator = routingList.iterator(); iterator.hasNext();)
		{
				ApprovalLog approvalLog = (ApprovalLog)iterator.next();
				String notes = approvalLog.getApproverNotes();
				String approverName = UserManager.getInstance().getUser(oid, approvalLog.getUserId()).getDisplayName();
				if (approverName == null) approverName = "" ;
				if (notes == null) notes = "" ;
				if(!approverName.equals(lastApproverName))
				{	%>
				<tsa:tr><tsa:td cssClass="summary"><b><%=approverName%> <tsa:label labelName="req-wrote" defaultString="wrote"/>:</b></tsa:td></tsa:tr>
				<%}
				if(!HiltonUtility.isEmpty(notes)){ %>
				<tsa:tr><tsa:td cssClass="summary"><%=notes%><br></tsa:td></tsa:tr>
			 <% }
				lastApproverName=approverName;
		}  %>
				</table>
			</tsa:td>
		</tsa:tr>
		</table>
		<br>
<%  } %>