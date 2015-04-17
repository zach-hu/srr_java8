<table border=0 cellspacing=0 cellpadding=2>
  <tr>
  	<!--In this part we show the ItemNumber attribute of asset-->
    <td nowrap align="right" valign="top">
    	<table border="0" cellpadding="0" cellspacing="0">
    	<tr><td valign="top">
    		<% for(int x=0; x<15; x++) {
    			System.out.println(x + " - " + labeludf[x]) ;
    		%>

    		<div id="udffull<%=x%>" <% if(Utility.isEmpty(labeludf[x])) { %>style="visibility: hidden; display:none;"<% } %>>
    		<table border="0" cellpadding="0" cellspacing="0">
    		<tr><td>
    			<table border="0" cellpadding="0" cellspacing="0" width="100%">
    				<tr><td align="right">
    					<table border="0" cellpadding="0" cellspacing="0">
    						<tr>
    						<td>
    						<div id="labelEditUDF<%=x%>">
    						<a href="javascript:editLabel(<%=x%>);"><img src="<%=contextPath%>/images/cmd_edit.gif" width="16" height="16" border="0"/></a>
    						</div>
    						</td>
    						<td>
    						<div id="labelSaveUDF<%=x%>" style="visibility: hidden; display:none;">
    						<a href="javascript:saveLabel(<%=x%>);"><img src="<%=contextPath%>/images/save.gif" width="16" height="16" border="0"/></a>
    						</div>
    						</td>
    						</tr>
    					</table>
    				</td><td align="left" width="120px">
    					<input type="text" name="label" value="<% if(!Utility.isEmpty(labeludf[x])) { %><%=labeludf[x]%><% } %>" size="20" maxlength="50" style="border: none" readonly/>
    				</td></tr>
    			</table>
    		</td><td>
    			<select name="UDF" size="1" <% if(Utility.isEmpty(labeludf[x])) { %>disabled<% } %>>
    			<%
    				for(int y=0; y<16; y++) {
    					String fld = "Fld" + (y + 1) ;
    					if (y == 15) fld = "Commodity" ;
    					int sel = 0;
    					if(budgetudf[x].equals(fld)) {
    						sel = 1 ;
    					}
    					%>
    				<option value="<%=fld%>" <%if (sel == 1) {  %> SELECTED<% }  %>><%=fld%></option>
    			<% 	}  %>
    			</select>
    		</td></tr>
    		</table>
    		</div>

    		<% } %>


    	</td></tr>
    	<tr><td>&nbsp;</td></tr>
    	<tr><td width="100%" align="right" colspan="3">
    		<table border="0" cellpadding="0" cellspacing="0">
    			<tr>
    				<td><div id="dropUp" style="visibility: hidden; display:none;"><a href="javascript:displayHiddens();"><img src="<%=contextPath%>/images/up.gif" width="16" height="16" border="0"/></a></div></td>
    				<td><div id="dropDown"><a href="javascript:showOneHiddenMore();"><img src="<%=contextPath%>/images/down.gif" width="16" height="16" border="0"/></a></div></td>
    			</tr>
    		</table>


    	</td></tr>
    	</table>
	</td>
	<td>&nbsp;</td>
	<td nowrap="nowrap" align="center" valign="top">
		<table border=0 cellspacing=0 cellpadding=2>
		  <tr>
			<td nowrap="nowrap" align="left">
				<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "authority", "Authority")%>
			</td>
			<td>&nbsp;</td>
			<td nowrap="nowrap" align="left">
			<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "budgetFiscalYear", "Budget Fiscal Year")%></td>
		  </tr>
		  <tr>
		  	<td nowrap="nowrap" align="left">
		  		<select name="authority" size="1" onchange="activeAccount(this);">
					<%if(oid.equals("MSG07P")) {%>
			  			<option value="Division" <% if(authority.equalsIgnoreCase("Division")) { %>selected<% } %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "budgetAuthorityDivision", "Division")%></option>
					<%}else{%>
		  				<option value="Department" <% if(authority.equalsIgnoreCase("Department")) { %>selected<% } %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "budgetAuthorityDepartment", "Department")%></option> -->
					<%}%>
		  			<option value="Requisitioner" <% if(authority.equalsIgnoreCase("Requisitioner")) { %>selected<% } %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitioner", "Requisitioner")%></option>
		  			<option value="Account UDF" <% if(authority.equalsIgnoreCase("Account UDF")) { %>selected<% } %>>Account UDF</option>
		  		</select>
		  	</td>
		  	<td>&nbsp;</td>
		  	<td nowrap="nowrap" align="right">
		  		<input type="text" name="budgetYear" value="<%=budgetyear%>" size="10" maxlength="4" style="text-align:right" onC
		  		hange="yearFilter(this);"/>
		  	</td>
		  </tr>
		  <tr>
		  	<td nowrap="nowrap" align="left">
		  		<select name="authorityudf" size="1" <% if(!authority.equalsIgnoreCase("Account UDF")) { %> style="visibility:hidden;<% } %>>
		  			<option value="1" <% if(authorityudf.equalsIgnoreCase("1")) { %>selected<% } %>>Account 1</option>
		  			<option value="2" <% if(authorityudf.equalsIgnoreCase("2")) { %>selected<% } %>>Account 2</option>
		  			<option value="3" <% if(authorityudf.equalsIgnoreCase("3")) { %>selected<% } %>>Account 3</option>
		  			<option value="4" <% if(authorityudf.equalsIgnoreCase("4")) { %>selected<% } %>>Account 4</option>
		  			<option value="5" <% if(authorityudf.equalsIgnoreCase("5")) { %>selected<% } %>>Account 5</option>
		  			<option value="6" <% if(authorityudf.equalsIgnoreCase("6")) { %>selected<% } %>>Account 6</option>
		  			<option value="7" <% if(authorityudf.equalsIgnoreCase("7")) { %>selected<% } %>>Account 7</option>
		  			<option value="8" <% if(authorityudf.equalsIgnoreCase("8")) { %>selected<% } %>>Account 8</option>
		  			<option value="9" <% if(authorityudf.equalsIgnoreCase("9")) { %>selected<% } %>>Account 9</option>
		  			<option value="10" <% if(authorityudf.equalsIgnoreCase("10")) { %>selected<% } %>>Account 10</option>
		  			<option value="11" <% if(authorityudf.equalsIgnoreCase("11")) { %>selected<% } %>>Account 11</option>
		  			<option value="12" <% if(authorityudf.equalsIgnoreCase("12")) { %>selected<% } %>>Account 12</option>
		  			<option value="13" <% if(authorityudf.equalsIgnoreCase("13")) { %>selected<% } %>>Account 13</option>
		  			<option value="14" <% if(authorityudf.equalsIgnoreCase("14")) { %>selected<% } %>>Account 14</option>
		  			<option value="15" <% if(authorityudf.equalsIgnoreCase("15")) { %>selected<% } %>>Account 15</option>
		  		</select>
		  	</td>
		  </tr>
		  <tr><td>&nbsp;</td></tr>
		  <tr>
		  	<td nowrap="nowrap" colspan="3" align="center">
		  		<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "", "")%>Budget Check Options<br>
		  		<hr width="100%"/>
		  	</td>
		  </tr>
		  <tr>
		  	<td nowrap="nowrap" valign="top">
		  		<table border="0" cellpadding="0" cellspacing="0">
		  			<tr><td nowrap="nowrap" align="left"><input type="radio" name="checkbudget" value="N" <% if(checkbudget.equalsIgnoreCase("N")) { %>checked<% } %>/><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "noCheck", "No Check")%></td></tr>
		  			<tr><td nowrap="nowrap" align="left"><input type="radio" name="checkbudget" value="M" <% if(checkbudget.equalsIgnoreCase("M")) { %>checked<% } %>/><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "manual", "Manual")%></td></tr>
		  			<tr><td nowrap="nowrap" align="left"><input type="radio" name="checkbudget" value="A" <% if(checkbudget.equalsIgnoreCase("A")) { %>checked<% } %>/><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "automatic", "Automatic")%></td></tr>
		  			<tr><td>&nbsp;</td></tr>
		  			<tr><td nowrap="nowrap" align="left"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "tolerance", "Tolerance")%>: </td></tr>
		  			<tr><td nowrap="nowrap" align="left"><input type="text" name="tolerance" value="<%=tolerance%>" size="10" maxlength="5" onChange="intFilter(this);"/></td></tr>
		  		</table>
		  	</td>
		  	<td>&nbsp;</td>
		  	<td nowrap="nowrap" valign="top">
		  		<table border="0" cellpadding="0" cellspacing="0">
		  			<tr><td nowrap="nowrap" align="left"><input type="checkbox" name="preencumberence" value="Y" <% if(preencumberence.equalsIgnoreCase("Y")) { %>checked<% } %>/><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "preEncumbrance", "Pre-Encumbrance")%></td></tr>
		  			<tr><td nowrap="nowrap" align="left"><input type="checkbox" name="encumberence" value="Y" <% if(encumberence.equalsIgnoreCase("Y")) { %>checked<% } %>/><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "encumbrance", "Encumbrance")%></td>
		  			</tr>
		  			<tr><td nowrap="nowrap" align="left"><input type="checkbox" name="expense" value="Y" <% if(expense.equalsIgnoreCase("Y")) { %>checked<% } %>/><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "expense", "Expense")%></td></tr>
		  			<tr><td nowrap="nowrap" align="left"><input type="checkbox" name="autoexpense" value="Y" <% if(autoexpense.equalsIgnoreCase("Y")) { %>checked<% } %>/><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autoExpense", "AutoExpense")%></td></tr>
		  		</table>
		  	</td>
		  </tr>

		  <tr>
		  	<td nowrap="nowrap" colspan="3" align="center">
		  		<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "budgetCheckError", "Budget Check Error")%><br>
		  		<hr width="100%"/>
		  	</td>
		  </tr>
		  <tr>
		  	<td  colspan=3 nowrap="nowrap" valign="top">
		  		<table border="0" cellpadding="0" cellspacing="0">
		  			<tr>
			  			<td nowrap="nowrap" align="left">
				  			<select name="existserr" size="1" onchange="activeAccount(this);">
   	  							<% if(oid.equalsIgnoreCase("DTN07P")){ %>
   	  								<option value="N" <% if(existserr.equalsIgnoreCase("N")) { %>selected<% } %>>No Check</option>
								<% } %>
								<option value="F" <% if(existserr.equalsIgnoreCase("F")) { %>selected<% } %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "fail", "Fail")%></option>
 								<option value="W" <% if(existserr.equalsIgnoreCase("W")) { %>selected<% } %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "warn", "Warn")%></option>
	  							<option value="I" <% if(existserr.equalsIgnoreCase("I")) { %>selected<% } %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ignore", "Ignore")%></option>
	  							</select></td>
	  							<td>&nbsp;</td>
	  					<td nowrap="nowrap" align="left"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "budgetNotExistInactive", "Budget Does Not Exist, Budget Is Inactive")%></td>
	  					</tr>
		  			<tr>
		  				<td nowrap="nowrap" align="center">
			  				<select name="overerr" size="1" onchange="activeAccount(this);">
  								<% if(oid.equalsIgnoreCase("DTN07P")){ %>
  									<option value="N" <% if(overerr.equalsIgnoreCase("N")) { %>selected<% } %>>No Check</option>
			  					<% } %>
			  					<option value="F" <% if(overerr.equalsIgnoreCase("F")) { %>selected<% } %>>Fail</option>
 								<option value="W" <% if(overerr.equalsIgnoreCase("W")) { %>selected<% } %>>Warn</option>
	  							<option value="I" <% if(overerr.equalsIgnoreCase("I")) { %>selected<% } %>>Ignore</option>
	  							</select></td>
	  							<td>&nbsp;</td>
	  					<td nowrap="nowrap" align="left"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "overBudget", "Over Budget")%></td>
	  					</tr>
		  			<tr>
		  				<td nowrap="nowrap" align="left">
			  				<select name="autherr" size="1" onchange="activeAccount(this);">
	  							<% if(oid.equalsIgnoreCase("DTN07P")){ %>
	  								<option value="N" <% if(autherr.equalsIgnoreCase("N")) { %>selected<% } %>>No Check</option>
	  							<% } %>
	  							<option value="F" <% if(autherr.equalsIgnoreCase("F")) { %>selected<% } %>>Fail</option>
 								<option value="W" <% if(autherr.equalsIgnoreCase("W")) { %>selected<% } %>>Warn</option>
	  							<option value="I" <% if(autherr.equalsIgnoreCase("I")) { %>selected<% } %>>Ignore</option>
	  							</select></td>
	  							<td>&nbsp;</td>
	  					<td nowrap="nowrap" align="left"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "authorityNotExist", "Authority Does not Exist")%></td>
	  					</tr>
		  			<tr>
		  				<td nowrap="nowrap" align="left">
			  				<select name="tolerr" size="1" onchange="activeAccount(this);">
	  							<% if(oid.equalsIgnoreCase("DTN07P")){ %>
	  								<option value="N" <% if(tolerr.equalsIgnoreCase("N")) { %>selected<% } %>>No Check</option>
	  							<% } %>
	  							<option value="F" <% if(tolerr.equalsIgnoreCase("F")) { %>selected<% } %>>Fail</option>
 								<option value="W" <% if(tolerr.equalsIgnoreCase("W")) { %>selected<% } %>>Warn</option>
	  							<option value="I" <% if(tolerr.equalsIgnoreCase("I")) { %>selected<% } %>>Ignore</option>
	  							</select></td>
	  							<td>&nbsp;</td>
		  						<td nowrap="nowrap" align="left"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "budgetNotwithinTolerance", "Budget Not within Tolerance")%></td>
		  				</tr>
		  		</table>
		  	</td>
		  </tr>
		</table>
	</td>
  </tr>
</table>