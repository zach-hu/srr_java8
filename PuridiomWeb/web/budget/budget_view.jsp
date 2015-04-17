<%@ page language="java" errorPage="/system/error.jsp"%>
<%@ include file="/system/prevent_caching.jsp"%>
<%@ include file="/system/header_popup.jsp"%>
<%@ page import="com.tsa.puridiom.property.PropertiesManager"%>
<%@ page import="com.tsagate.foundation.utility.*"%>
<%@ page import="com.tsa.puridiom.entity.*"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.math.BigDecimal"%>

<SCRIPT LANGUAGE="JavaScript1.2"
	SRC="<%=contextPath%>/scripts/budget.js" type="text/javascript"></SCRIPT>

<table width="680px" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%"
			width="100%">
			<tr>
				<td height="1px" class="darkShadow"><img
					src="<%=contextPath%>/images/none.gif" width="1" height="1px"
					alt="" /></td>
			</tr>
			<tr>
				<td nowrap class="hdr12" valign="middle">
				<div style="margin-left:10px; margin-right:10px" class="hdr12">Budget
				Information</div>
				</td>
				<td></td>
			</tr>
		</table>
		</td>
		<td valign="bottom"><img class="hdr12"
			src="<%=contextPath%>/images/angle.gif" height="31" alt="" /></td>
		<td valign="bottom" align="right" height="30px"></td>
		<td valign="bottom" align="right" height="30px">
		<table cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td width="1000px" height="1px" class="lightShadow"><img
					src="<%=contextPath%>/images/none.gif" width="1" height="1" alt="" /></td>
			</tr>
			<tr>
				<td height="2px" class="darkShadow"><img
					src="<%=contextPath%>/images/none.gif" width="1" height="2" alt="" /></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<BR>
<BR>
<TABLE BORDER="0" CELLPADDING="0" CELLSPACING="0" WIDTH="100%"  MARGIN="0">
	<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_account_separator = propertiesManager.getProperty("MISC", "AccountSeparator", "-");

	String	s_accs = request.getParameter("accountString");
	String	sYear = request.getParameter("fiscalYear");
	String	s_currency = request.getParameter("currencyCode");
	String	sDesc = "" ;
	BigDecimal bdPreEncumbered = new BigDecimal(0) ;
	BigDecimal bdExpensed = new BigDecimal(0) ;
	BigDecimal bdEncumbered = new BigDecimal(0) ;
	BigDecimal bdBudgeted = new BigDecimal(0) ;
	BigDecimal bdBalance = new BigDecimal(0) ;


	int	budgetColumns = Integer.parseInt((String) request.getAttribute("budgetColumns")) ;

	String	labelPrefix = "req" ;
	List budgetList = (List)request.getAttribute("budgetList") ;
	if (budgetList == null) budgetList = new ArrayList() ;

	if (budgetList.size() > 0)
	{
		BudgetCenter budget = (BudgetCenter) budgetList.get(0) ;
		bdPreEncumbered = budget.getPreEncumbered() ;
		bdExpensed = budget.getExpensed() ;
		bdEncumbered = budget.getEncumbered() ;
		bdBudgeted = budget.getBudgeted() ;
		bdBalance = budget.getBudgeted() ;
		bdBalance = bdBalance.subtract(bdPreEncumbered) ;
		bdBalance = bdBalance.subtract(bdEncumbered) ;
		bdBalance = bdBalance.subtract(bdExpensed) ;
		sDesc = budget.getComments() ;
	} else {
		sDesc = "Budget is not on file!" ;
	}
%>
	<TR>
		<TD WIDTH="100%">

		<TABLE ALIGN="CENTER" VALIGN="TOP" BORDER="0" WIDTH="100%">
			<TR>
				<TD>&nbsp;</TD>
				<TD>
				<TABLE ALIGN="LEFT" BORDER="0" CELLPADDING="1" CELLSPACING="2" WIDTH="70%" >
					<TR>
						<TD CLASS="label"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "fiscalYear", "Fiscal Year")%>:</TD>
						<TD><%=sYear%></TD>
					</TR>
					<TR>
						<TD CLASS="label">Description:</TD>
						<TD><%=sDesc%></TD>
					</TR>
				</TABLE>
				</TD>
			</TR>
			<% String sWidth = (100 / budgetColumns) + "%" ; %>
			<TR>
				<TABLE ID="acclabels" CLASS="hdr12" BORDER="1" CELLPADDING="1" 	WIDTH="100%">
					<TR>
						<%			for (int ix = 1; ix <= budgetColumns; ix++) {
					String		sLabel = (String) request.getAttribute("budget_label" + ix) ;
					%>
						<TD NOWRAP ALIGN="CENTER" WIDTH=<%=sWidth%>><%=sLabel%></TD>
						<%			} %>
					</TR>
				</TABLE>
			</TR>
			<TR>
			<TABLE ID="accdata" BORDER="1" CELLPADDING="1" WIDTH="100%">
				<TR>
					<%
				for (int ix = 1; ix <= budgetColumns; ix++) {
					String		sData = (String) request.getAttribute("BudgetCenter_budget" + ix) ;
%>
					<TD NOWRAP ALIGN="LEFT" WIDTH=<%=sWidth%>><%=sData%></TD>
					<%			} %>
				</TR>
			</TABLE>
			</TR>

			<%			if (budgetList.size() > 0) { %>
			<TR>
				<TABLE ALIGN="CENTER" BORDER="0" CELLPADDING="1" CELLSPACING="2"
					WIDTH="70%" BGCOLOR="#999999">
					<TR>
						<TD CLASS="label">Budgeted</TD>
						<TD ALIGN="RIGHT"><%=HiltonUtility.getFormattedCurrency(bdBudgeted, s_currency, oid)%></TD>
					</TR>
					<TR>
						<TD CLASS="label">Pre-Encumbered</TD>
						<TD ALIGN="RIGHT"><%=HiltonUtility.getFormattedCurrency(bdPreEncumbered, s_currency, oid)%></TD>
					</TR>
					<TR>
						<TD CLASS="label">Encumbered</TD>
						<TD ALIGN="RIGHT"><%=HiltonUtility.getFormattedCurrency(bdEncumbered, s_currency, oid)%></TD>
					</TR>
					<TR>
						<TD CLASS="label">Expensed</TD>
						<TD ALIGN="RIGHT"><%=HiltonUtility.getFormattedCurrency(bdExpensed, s_currency, oid)%></TD>
					</TR>
					<TR>
						<TD></TD>
						<TD CLASS="label" ALIGN="RIGHT">------------</TD>
					</TR>
					<TR>
						<TD CLASS="label">Balance</TD>
						<TD CLASS="label" ALIGN="RIGHT"><%=HiltonUtility.getFormattedCurrency(bdBalance, s_currency, oid)%></TD>
					</TR>

				</TABLE>
				<TD></TD>
				<TD>&nbsp;</TD>
			</TR>
			<% } %>
		</TABLE>
		</TD>
	</TR>
</TABLE>
<%	if (budgetList.size() == 0) { %>
<BR>
<TABLE BORDER="0" CELLPADDING="0" CELLSPACING="0" WIDTH="100%"  MARGIN="0" >
	<TR WIDTH="100%" height="20px">
		<TD ALIGN="CENTER" CLASS="error">Budget information could not be found for the above GL string!</TD>
	</TR>
</TABLE>
<% } %>
	<BR>
<TABLE BORDER="0" CELLPADDING="0" CELLSPACING="0" WIDTH="100%"  MARGIN="0" >
	<TR>
		<td align=center><a href="javascript: window.top.hidePopWin(); void(0);"><img class=button src="<%=contextPath%>/images/button_close.gif" border=0></a></td>
	</TR>
</TABLE>

<SCRIPT LANGUAGE="JavaScript1.2" type="text/javascript">
<!--  hide script from old browsers

	self.focus();

</SCRIPT>
<%@ include file="/system/footer.jsp" %>
