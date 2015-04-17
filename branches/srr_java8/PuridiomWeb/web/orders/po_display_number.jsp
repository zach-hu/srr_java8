<%
	Encoder encoderTitle = DefaultEncoder.getInstance();
%>
	<table cellpadding=0 cellspacing=0 border=0 width=95%>
	<tr>
		<td align=right>
			<table cellpadding=0 cellspacing=0 border=0>
	<%	int i_colspan = 1;%>
			<tr>
				<td nowrap align="right"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "order", "Order", false)%> #:</b></td>
				<td width=100px>&nbsp;<%=encoderTitle.encodeForHTML(s_po_number)%></td>
	<%	if (bd_release_number.compareTo(bd_zero) > 0)
			{
				i_colspan = i_colspan + 2; %>
				<td nowrap align="right"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "release", "Release", false)%> #:</b></td>
				<td width=100px>&nbsp;<%=bd_release_number%></td>
	<%	}
		    if (bd_revision_number.compareTo(bd_zero) > 0)
		    {
				i_colspan = i_colspan + 2; %>
				<td nowrap align="right"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "revision", "Revision", false)%> #:</b></td>
				<td width=100px>&nbsp;<%=bd_revision_number%></td>
	<%	} %>
			</tr>
			<tr>
				<td colspan=<%=i_colspan%> nowrap align="right"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status", false)%>:</b></td>
				<td width=150px nowrap>&nbsp;
					<% try {
					int rfqStatusNumber = Integer.parseInt(s_po_status);
					if (s_po_status.length() < 5) {%>
						<%=DocumentStatus.toString(s_po_status, oid)%>
					<% } %>
					<% } catch (Exception e) {} %>
				</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	<table cellpadding="0" cellspacing="0" border="0" width=100%>
	<tr>
		<td width=100% height="1px" class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
	</tr>
	<tr>
		<td height=2px class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
	</tr>
	</table>