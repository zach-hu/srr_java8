<%
	if (HiltonUtility.isEmpty(s_saleNumber)) {
		s_saleNumber = "N/A";
	}%>
		<table border=0 cellspacing=0 cellpadding=1>
		<tr>
<%	int icolspan=1;%>
			<td nowrap align=right><b>Transaction #:</b></td>
			<td><%=s_saleNumber%></td>
<%	if (s_amendment.compareTo("0000") > 0) {
			icolspan=4;%>
			<td>&nbsp;</td>
			<td nowrap align=right><b>Amendment #:</b></td>
			<td nowrap><%=s_amendment%></td>
<%	} %>
		</tr>
		<tr>
			<td align=right colspan=<%=icolspan%>><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td nowrap><%=DocumentStatus.toString(s_status, oid)%></td>
		</tr>
		</table>