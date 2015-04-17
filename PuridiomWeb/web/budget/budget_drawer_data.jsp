<table border="1" cellpadding="0" cellspacing="0" bordercolor="gainsboro">
	<tr>
		<td>
			<table border="4" cellpadding="0" cellspacing="0" bordercolor="ghostwhite">
				<tr>
					<td colspan="2" align="center">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr bgcolor="gainsboro">
								<td align="center">New Authority</td>
								<td width="16px" align="center"><a href="javascript:saveAuthority();"><img src="<%=contextPath%>/images/save.gif" width="16" height="16" border="0"/></a></td>
								<td width="16px" align="center"><a href="javascript:cancelAuthority();"><img src="<%=contextPath%>/images/bar_close.gif" width="16" height="16" border="0"/></a></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr bgcolor="ghostwhite">
					<td align="right">Type: </td>
					<td align="left">
						<select name="BudgetDrawerOP_type" size="1" disabled="disabled">
							<option value="Department" <% if(authority.equalsIgnoreCase("Department")) { %>selected<% } %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "budgetAuthorityDepartment", "Department")%></option>
							<option value="Requisitioner" <% if(authority.equalsIgnoreCase("Requisitioner")) { %>selected<% } %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitioner", "Requisitioner")%></option>
							<option value="<%=authorityudf%>" <% if(authority.equalsIgnoreCase("Account UDF")) { %>selected<% } %>>Account UDF</option>
						</select>
					</td>
				</tr>
				<tr bgcolor="ghostwhite">
					<td align="right">Authority: </td>
					<td align="left"><input type="text" name="BudgetDrawerOP_authority" size="17" onChange="upperCase(this); trim(this);" disabled="disabled"/></td>
				</tr>
				<tr bgcolor="ghostwhite">
					<td align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%>: </td>
					<td align="left"><input type="text" name="BD_ownerName" size="17" disabled="disabled" value="<%=owner.getDisplayName()%>"/></td>
				</tr>
				<tr bgcolor="ghostwhite">
					<td align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>: </td>
					<td align="left">
						<select name="BudgetDrawerOP_status" size="1" disabled="disabled">
							<option value="01" selected>Active</option>
							<option value="02">Inactive</option>
						</select>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>