<%@ page language="java" errorPage="/xchange/error_pg.jsp" %>
<%@ page import="com.tsa.general.InputObj" %>
<%@ page import="com.tsa.general.OutputObj" %>
<%@ page import="com.tsa.inisettings.*" %>
<%@ page import="java.math.BigDecimal" %>
<%@ include file="/xchange/session/ck_session.jsp" %>
<%@ include file="/xchange/session/session_variables.jsp" %>

<HTML>
<HEAD>
	<META HTTP-EQUIV="Expires" CONTENT="Sat, 11 Sep 1971 12:00:00 GMT">
	<META NAME="Description" Content="/puridiom/xchange/budget/budgetCtr_check.jsp">
	<TITLE>Budget Check</TITLE>
	<SCRIPT LANGUAGE="JavaScript1.2" SRC="/puridiom/xchange/scripts/xchange.js"></SCRIPT>
	<LINK REL=STYLESHEET TYPE="text/css" HREF="/puridiom/xchange/xchange_style.css">
	<%@ include file="/xchange/session/onlinecheck.jsp" %>
</HEAD>

<BODY ONLOAD="if(check) (ss_preload(server));" marginwidth="0" marginheight="0" topmargin="0" leftmargin="0"  CLASS="htable">
<TABLE CLASS="htable" BORDER="0" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" HEIGHT="100%" MARGIN="0" VALIGN="BOTTOM">
<%
	String	s_accs = request.getParameter("accs");
	String	s_year = request.getParameter("year");
	String	ss_acct_sep = (String) sessionId.getValue("ss_acct_sep");
	String	s_hic = "";
	String  s_status = "";
	String	errorMsg = "";
	ss_gcsProcess = "REQBUDGET";
	String 	component = "budgetx";

	InputObj iO = new InputObj();
	iO.setComponentName(component);
	iO.setMethodName("retrieve_budget");

	iO.setInputValue("accs", s_accs);
	iO.setInputValue("fyear", s_year);
	iO.setInputValue("sep",ss_acct_sep);

	iO.setInputValue("update", "false");
	OutputObj oObj = BeanJag.retrieve(iO);

	retval = oObj.getRetval();
	errorMsg = (String) oObj.getOutValue("errorstr") ;

	if (retval == 1)
	{
		int i_dbrows = BeanJag.getRowCount("budget");
		if (i_dbrows > 1) {
			String bdBalance = oObj.getOutValue("budget_balance");
%>
<TR><TD CLASS="htable" ALIGN="CENTER"><BR><B>Budget Information</B></TD></TR>
<TR>
	<TD WIDTH="100%">
		<TABLE ALIGN="CENTER" VALIGN="TOP" BORDER="0" WIDTH="100%" CLASS="htable">
		<TR>
			<TD CLASS="htable">&nbsp;</TD>
			<TD CLASS="htable">
				<TABLE ALIGN="CENTER" CLASS="htable" BORDER="1" CELLPADDING="1" CELLSPACING="2" WIDTH="70%" BGCOLOR="#999999">
				<TR>
					<TD CLASS="htable"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "fiscalYear", "Fiscal Year")%></TD>
					<TD CLASS="htable"><%=s_year%></TD>
				</TR>
				<TR>
					<TD CLASS="htable">Project</TD>
					<TD CLASS="htable"><%=BeanJag.getValue("budget", "budget_1", 1)%></TD>
				</TR>
				</TABLE>
				<TABLE ALIGN="CENTER" CLASS="htable" BORDER="0" CELLPADDING="1" CELLSPACING="2" WIDTH="70%" BGCOLOR="#999999">
				<TR>
					<TD CLASS="htable">Budgeted</TD>
					<TD CLASS="htable" ALIGN="RIGHT"><%=BeanJag.getBigDecimal("budget", "budgeted", 1, 2)%></TD>
				<TR>
					<TD CLASS="htable">Pre-Encumbered</TD>
					<TD CLASS="htable" ALIGN="RIGHT"><%=BeanJag.getBigDecimal("budget", "pre_encumbered", 1,2)%></TD>
				</TR>
				<TR>
					<TD CLASS="htable">Encumbered</TD>
					<TD CLASS="htable" ALIGN="RIGHT"><%=BeanJag.getBigDecimal("budget", "encumbered", 1, 2)%></TD>
				</TR>
				<TR>
					<TD CLASS="htable">Expensed</TD>
					<TD CLASS="htable" ALIGN="RIGHT"><%=BeanJag.getBigDecimal("budget", "expensed", 1, 2)%></TD>
				</TR>
				<TR>
					<TD CLASS="htable"></TD>
					<TD CLASS="htable" ALIGN="RIGHT">------------</TD>
				</TR>
				<TR>
					<TD CLASS="htable">Balance</TD>
					<TD CLASS="htable" ALIGN="RIGHT"><%=bdBalance%></TD>
				</TR>

				</TABLE>
			</TD>
			<TD CLASS="htable">&nbsp;</TD>
		</TR>
		</TABLE>
	</TD>
</TR>
<%
		}
		else
		{ %>
		<TR>
			<TD ALIGN="CENTER" CLASS="htable">Budget information could not be found! <%=i_dbrows%></TD>
		</TR>
<%		}
	}
	else
	{
		sessionId.putValue("s_errorstr",errorMsg);
		response.sendRedirect("/puridiom/xchange/error_pg.jsp?err=retrieve");
	}
%>
<TR>
 	<TD ALIGN="RIGHT" CLASS="htable">
 		<A HREF="javascript: parent.close(); void(0);"><IMG SRC="/puridiom/xchange/images/button_close.gif" BORDER="0"></A>
 	</TD>
</TR>
</TABLE>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	self.focus();

</SCRIPT>
</BODY>
</HTML>
