<tsa:hidden name="as_maxrows" value="1"/>
<script type="text/javascript">
<!--
frm = document.purchaseform;

var accountFld1Browse = "systable";
var accountFld2Browse = "systable";
<% if(oid.equalsIgnoreCase("DTN07P")) {%>
	accountFld2Browse = "account-fld2";
<%}%>
var accountFld3Browse = "systable";
var accountFld4Browse = "systable";
var accountFld5Browse = "systable";
var accountFld6Browse = "systable";
var accountFld7Browse = "systable";
var accountFld8Browse = "systable";
var accountFld9Browse = "systable";
var accountFld10Browse = "systable";
var accountFld11Browse = "systable";
var accountFld12Browse = "systable";
var accountFld13Browse = "systable";
var accountFld14Browse = "systable";
var accountFld15Browse = "systable";

var maxRows = frm.as_maxrows.value;
var tableType = "AC";


//-->
</script>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/accounts.js"></SCRIPT>

<% String s_comments = budgetCenter.getComments() ; %>
<table border="0" cellpadding="2" cellspacing="0">
	<tr>
		<td>
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "fiscalYear", "Fiscal Year")%>: </td>
					<td align="left"><input type="text" name="BudgetCenter_budgetPeriod" value="<% if(action.equalsIgnoreCase("old")) { %><%=budgetCenter.getBudgetPeriod()%><% } else { %><%=(d_today.getYear()+1900)%><% } %>" size="15" /></td>
					<td width="100px">&nbsp;</td>
					<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>: </td>
					<td>
						<select name="BudgetCenter_status" onchange="" tabIndex=80>
							<option value="02" <% if (budgetCenter.getStatus().equals("02")) { %> selected <% } %>>Permanent</option>
							<option value="01" <% if (budgetCenter.getStatus().equals("01")) { %> selected <% } %>>Temporary</option>
							<option value="03" <% if (budgetCenter.getStatus().equals("03")) { %> selected <% } %>>Inactive</option>
						</select>
					</td>
				</tr>
			</table>

		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr <%=HtmlWriter.isVisible(oid, "bgt-comments")%>>
	    <td>
		<table border="0" cellpadding="2" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<td  align=left width=88% nowrap>Description:</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td width=88%><input type=text name="BudgetCenter_comments" size=100 maxlength=200 tabindex=81 value="<%=s_comments%>" ></td>
		</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="center">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<% for(int x=0; x<16; x++) {
						 if( !Utility.isEmpty(labeludf[x])) { %>
					<td nowrap>
						 <% String fld = HiltonUtility.ckNull((String) propertiesManager.getProperty("BUDGET","BUDGET UDF" + (x + 1),""));
						 	if  (oid.equals("HOY08P") &&  !fld.equalsIgnoreCase("")) { %>
							 <a  href="javascript:browseAccountFld('BudgetCenter_budget<%=x+1%>', 'AC0<%=fld.substring(3,fld.length())%>'); void(0); " title="" ><%=labeludf[x]%>:</a>
						 <% }
						   else if  (x==4 && oid.equals("DTN07P")) { %>
	   						<a HREF="javascript: browseCommodity('BudgetCenter_budget<%=x+1%>', 'commodity', ''); void(0);" " title="" >Commodity:</a>
	   					<%} else {%>
							 <a  href="javascript:browseAccountFld('BudgetCenter_budget<%=x+1%>', 'AC0<%=x+1%>'); void(0); " title="" ><%=labeludf[x]%>:</a>
						<%} %>
					</td>
					<td>&nbsp;</td>
					<% 	 }
					 } %>

					<td>Budget Amt.</td>
				</tr>


				<tr>
					<% for(int x=0; x<16; x++) {
						 if(!Utility.isEmpty(labeludf[x])) { %>
						<td>
							<input type="text" name="BudgetCenter_budget<%=x+1%>" value="<%=BudgetCenter_budget[x]%>" size="20" ONCHANGE="upperCase(this);" <% if (budgetInUse) { %>disabled<% } %>/>
						</td>
						<td>&nbsp;</td>
					<% 	 }
					   } %>
					<td>
						<input type="text" name="BudgetCenter_budgeted" value="<%=HiltonUtility.getFormattedDollar(budgetCenter.getBudgeted(), oid)%>" size="20" onchange="setValues(this);" style="text-align:right"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>
		<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td valign="top">
					<table>
						<tr>
							<td align="right">Pre-encumbered :</td>
							<td>&nbsp;</td>
							<td><input type="text" name="BudgetCenter_preEncumbered" value="<%=HiltonUtility.getFormattedDollar(budgetCenter.getPreEncumbered(), oid)%>" size="12" onchange="setValues(this);" style="text-align:right"/></td>
						</tr>
						<tr>
							<td align="right">Encumbered :</td>
							<td>&nbsp;</td>
							<td><input type="text" name="BudgetCenter_encumbered" value="<%=HiltonUtility.getFormattedDollar(budgetCenter.getEncumbered(), oid)%>" size="12" onchange="setValues(this);" style="text-align:right"/></td>
						</tr>
						<tr>
							<td align="right">Expensed: </td>
							<td>&nbsp;</td>
							<td><input type="text" name="BudgetCenter_expensed" value="<%=HiltonUtility.getFormattedDollar(budgetCenter.getExpensed(), oid)%>" size="12" onchange="setValues(this);" style="text-align:right"/></td>
						</tr>
						<tr>
							<td colspan="3"><hr/></td>
						</tr>
						<tr>
							<td align="right">Available Balance: </td>
							<td>&nbsp;</td>
							<td><input type="text" name="BudgetCenter_balance" value="" size="12" onchange="formatPrice(this)" style="text-align:right;border=none" readonly/></td>
						</tr>
						<tr>
							<td align="center" colspan="3"><a href="javascript: browseAuditTtrail(); void(0);" >Audit Trail</a>&nbsp;</td>
						</tr>
					</table>
				</td>
				<td>&nbsp;</td>
				<td valign="top">
					<table border="1" bordercolor="gainsboro" cellpadding="0" cellspacing="0" width="165px" valign="top">
						<tr>
							<td>
								<table border="0" cellpadding="3" cellspacing="0" width="100%">
									<tr bgcolor="gainsboro">
										<td align="center">Authorities:</td>
										<td align="right" width="16px"><a href="javascript:addAuthorities();"><img src="<%=contextPath%>/images/add.gif" width="16" height="16" border="0"/></a></td>
									</tr>
								</table>
							</td>
						<tr>
							<td>
								<%@ include file="/budget/budget_drawer_list_data.jsp" %>
							</td>
						</tr>
					</table>
				</td>
				<td width="20px">&nbsp;</td>
				<td valign="top">
					<table border="0" cellpadding="0" cellspacing="0" valign="top">
						<tr>
							<td>
								<%@ include file="/budget/budget_drawer_data.jsp" %>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>

<tsa:hidden name="BudgetCenter_owner"			value="<%=budgetCenter.getOwner()%>"/>
<tsa:hidden name="BudgetCenter_ownerPassword"	value="<%=budgetCenter.getOwnerPassword()%>"/>
<tsa:hidden name="BudgetCenter_approved"		value="<%=budgetCenter.getApproved()%>"/>
<tsa:hidden name="BudgetCenter_projectId"		value="<%=budgetCenter.getProjectId()%>"/>

