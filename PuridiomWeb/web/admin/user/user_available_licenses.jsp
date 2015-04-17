<%
	int licensedRequisitioners = OrganizationManager.getInstance().getLicensedRequisitioners(oid);
	int licensedBuyers = OrganizationManager.getInstance().getLicensedBuyers(oid);
	int enabledRequisitioners = UserManager.getInstance().getUserCount(oid, "REQUISITIONER").intValue();
	int enabledBuyers = UserManager.getInstance().getUserCount(oid, "BUYER").intValue();
	int availableRequisitioners = licensedRequisitioners - enabledRequisitioners;
	int availableBuyers = licensedBuyers - enabledBuyers;
%>
<table border=0 cellpadding=0 cellspacing=0>
<tr>
	<td align=right valign=top rowspan=2><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "administrator", "Administrator", false)%>:</b></td>
	<td valign=top rowspan=2 width=200px><%=UserManager.getInstance().getAdministratorName(oid)%></td>
	<td align=right>
		<table border=0 cellpadding=1 cellspacing=0>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitioners", "Requisitioners", false)%>:</b></td>
			<td><%=enabledRequisitioners%> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "outof", "out of", false)%> <%=licensedRequisitioners%> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "enabled", "enabled", false)%></td>
		</tr>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "buyers", "Buyers", false)%>:</b></td>
			<td><%=enabledBuyers%> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "outof", "out of", false)%> <%=licensedBuyers%> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "enabled", "enabled", false)%></td>
		</tr>
		</table>
	</td>
</tr>
</table>