<%
	if (HiltonUtility.isEmpty(s_rfqNumber)) {
		s_rfqNumber = "N/A";
	}%>
		<table border=0 cellspacing=0 cellpadding=0 width=100%>
		<tr>
<%	int icolspan=1;%>
			<td nowrap align=right><b><tsa:label labelName="rfq-Solicitation_number" defaultString="Solicitation #" />:&nbsp;</b></td>
			<td width=125px><%=headerEncoder.encodeForHTML(s_rfqNumber)%></td>
<%	if (HiltonUtility.ckNull(s_rfqAmendment).compareTo("0000") > 0) {
			icolspan=4;%>
			<td>&nbsp;</td>
			<td width=125px nowrap align=right><b><tsa:label labelName="rfq-Amendment_number" defaultString="Amendment #" />:&nbsp;</b></td>
			<td width=125px nowrap><%=headerEncoder.encodeForHTML(s_rfqAmendment)%></td>
<%	} %>
		</tr>
		<tr>
			<td align=right colspan=<%=icolspan%>><b><tsa:label labelName="status" defaultString="Status" />:&nbsp;</b></td>
			<td nowrap>
				<% try {
					int rfqStatusNumber = Integer.parseInt(s_rfqStatus);
					if (s_rfqStatus.length() < 5) {%>
						<%=DocumentStatus.toString(s_rfqStatus, oid)%>
					<% } %>
				<% } catch (Exception e) {} %>
			</td>
		</tr>
		</table>