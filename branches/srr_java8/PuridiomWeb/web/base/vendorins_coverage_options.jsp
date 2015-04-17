<%	if (insCoverageList.size() == 0) {%>
							<option value="A" <% if (coverage.equals("A")) {%>selected<%}%>>Approved</option>
							<option value="C" <% if (coverage.equals("C")) {%>selected<%}%>>Continuous</option>
							<option value="I" <% if (coverage.equals("I")) {%>selected<%}%>>Inactive</option>
							<option value="P" <% if (coverage.equals("P")) {%>selected<%}%>>Pending</option>
							<option value="U" <% if (coverage.equals("U")) {%>selected<%}%>>Unapproved</option>
							<option value="W" <% if (coverage.equals("W")) {%>selected<%}%>>Waived</option>
<%	} else {
			for (int il = 0; il < insCoverageList.size(); il++) {
				StdTable stdTable = (StdTable) insCoverageList.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
									<option value="<%=stdTablePK.getTableKey()%>" <%if (coverage.equals(stdTablePK.getTableKey())) {%>selected<%}%>><%=stdTable.getDescription()%></option>
<%		}
		}%>