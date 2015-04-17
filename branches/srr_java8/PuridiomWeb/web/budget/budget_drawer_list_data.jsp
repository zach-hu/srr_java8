<table border="0" cellpadding="0" cellspacing="0" width=100%>
	<% int y=0;
	   for(int x=0; x<budgetDrawerList.size();x++) {%>
	<tr><td>
	<div id="aut<%=x%>">
	<table border="0" cellpadding="0" cellspacing="0" width=100%>
		<tr>
			<%
				BudgetDrawer budgetDrawer = (BudgetDrawer) budgetDrawerList.get(x);
				BudgetDrawerPK budgetDrawerPK = budgetDrawer.getComp_id();
				String tp = budgetDrawerPK.getAuthType();
				if (tp == null) tp = "" ;
				tp = tp.trim() ;
				if (tp.equalsIgnoreCase("D")) {
				    tp = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "budgetAuthorityDepartment", "Department");
				 } else if (tp.equalsIgnoreCase("R")) {
				 	tp = "Requisitioner" ;
				 } else {
				    tp = "Other";   // authorityudf ;
				 }
			%>
			<td>
			<table border="0" cellpadding="2" cellspacing="0" width=100%>
			<tr>
			<td align="left" height="12">
				<input type="text" name="tp" value="<%=tp%>" size="20" style="border: none" readonly/>
				<tsa:hidden name="BudgetDrawer_type" value="<%=budgetDrawerPK.getAuthType()%>"/>
				<a href="javascript:updateAuthority(<%=x%>);"><img src="<%=contextPath%>/images/cmd_edit.gif" width="14" height="14" border="0"/></a>
				<a href="javascript:deleteAuthority(<%=x%>);"><img src="<%=contextPath%>/images/delete.gif" width="10" height="10" border="0"/></a>
			</td></tr>
			<tr><td align="left" height="12">
				&nbsp;&nbsp;<input type="text" name="BudgetDrawer_authority" value="<%=budgetDrawerPK.getAuthority()%>" size="25" style="border: none" readonly/>
				<tsa:hidden name="BudgetDrawer_status" value="<%=budgetDrawer.getStatus()%>"/>
			</td>
			</tr>
			</table>
			</td>
		</tr>
	</table>
	</div>
	</td></tr>
	<% y=x+1;
	}
	int Auth=10;
	if(oid.equalsIgnoreCase("DTN07P")){
		Auth=100;
	}
	for(int x=y; x<=Auth;x++) {%>
	<tr><td>
	<div id="aut<%=x%>" style="visibility: hidden; display:none;">
	<table border="0" cellpadding="0" cellspacing="0" width=100%>
		<tr>
			<td>
			<table border="0" cellpadding="2" cellspacing="0" width=100%>
			<tr>
			<td align="left" height="12">
				<input type="text" name="BudgetDrawer_type" value="" size="20" style="border: none" readonly/>
				<a href="javascript:updateAuthority(<%=x%>);"><img src="<%=contextPath%>/images/none.gif" width="14" height="14" border="0"/></a>
				<a href="javascript:deleteAuthority(<%=x%>);"><img src="<%=contextPath%>/images/none.gif" width="10" height="10" border="0"/></a>
			</td></tr>
			<tr><td align="left" height="12">
				&nbsp;&nbsp;<input type="text" name="BudgetDrawer_authority" value="" size="25" style="border: none" readonly/>
				<tsa:hidden name="BudgetDrawer_status" value=""/>
			</td>
			</tr>
			</table>
			</td>
		</tr>
	</table>
	</div>
	</td></tr>
	<% } %>
</table>
<tsa:hidden name="qtyAuthorities" value="<%=y%>"/>
<tsa:hidden name="qtyAuthInitial" value="<%=y%>"/>
