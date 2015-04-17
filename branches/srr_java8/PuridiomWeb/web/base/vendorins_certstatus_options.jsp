<%	if (certStatusList.size() == 0) {%>
									<option value="A" <% if (certStatus.equals("A")) {%>selected<%}%>>Approved</option>
									<option value="P" <% if (certStatus.equals("P")) {%>selected<%}%>>Pending</option>
									<option value="U" <% if (certStatus.equals("U")) {%>selected<%}%>>Unapproved</option>
									<option value="W" <% if (certStatus.equals("W")) {%>selected<%}%>>Waived</option>
									<option value="N" <% if (certStatus.equals("N")) {%>selected<%}%>>NA</option>
<%	} else {
			for (int il = 0; il < certStatusList.size(); il++) {
				StdTable stdTable = (StdTable) certStatusList.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
									<option value="<%=stdTablePK.getTableKey()%>" <%if (certStatus.equals(stdTablePK.getTableKey())) {%>selected<%}%>><%=stdTable.getDescription()%></option>
<%		}
		}%>