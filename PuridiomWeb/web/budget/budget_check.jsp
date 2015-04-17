<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>

<%

List	errorList = (List) request.getAttribute("errorList");
String	sProcess = (String) request.getAttribute("process") ;
String	s_status = (String) request.getAttribute("budgetStatus");

%>

<TABLE BORDER="0" CELLPADDING="0" CELLSPACING="0" CLASS="htable" WIDTH="100%" HEIGHT="100%" MARGIN="0" VALIGN="BOTTOM">
<%
	if (s_status.equalsIgnoreCase("FAILED")) {
			int i_dbrows = errorList.size() ;
%>
<TR>
	<TD CLASS="htable" WIDTH="100%">
		<TABLE ALIGN="CENTER" VALIGN="TOP" BORDER="0" WIDTH="100%" CLASS="htable">
		<TR>
			<TD CLASS="htable">&nbsp;</TD>
			<TD CLASS="htable">
				<TABLE BORDER="0" CELLPADDING="1" CELLSPACING="2" WIDTH="100%" BGCOLOR="#999999">
				<TR>
					<TD CLASS="lihdr" NOWRAP ALIGN="CENTER">Budget Error</TD>
					<TD CLASS="lihdr" NOWRAP ALIGN="CENTER">Budget Id</TD>
					<TD CLASS="lihdr" NOWRAP ALIGN="CENTER">Budget Authority</TD>
					<TD CLASS="lihdr" NOWRAP ALIGN="CENTER">Budget Balance</TD>
				</TR>
<%			for (int i = 0; i < i_dbrows; i++) {
					Hashtable ht = (Hashtable) errorList.get(i) ;
					String		bError = (String) ht.get("budgetError");
					String		bId = (String) ht.get("budgetId");
					String		bAuth = (String) ht.get("budgetAuth");
					String		bBalance = (String) ht.get("budgetBalance");
					String		bInitial = (String) ht.get("budgetInitial");
					String		bSeverity = (String) ht.get("errorSeverity");
%>
				<TR>

					<TD CLASS="wlrowA" NOWRAP ALIGN="LEFT"><%=bError%>&nbsp;</TD>
					<TD CLASS="wlrowA" NOWRAP ALIGN="LEFT"><%=bId%>&nbsp;</TD>
					<TD CLASS="wlrowA" NOWRAP ALIGN="LEFT"><%=bAuth%>&nbsp;</TD>
					<TD CLASS="wlrowA" NOWRAP ALIGN="LEFT"><%=bInitial%>&nbsp;</TD>

				</TR>
<%			} %>
				</TABLE>
			</TD>
			<TD CLASS="htable">&nbsp;</TD>
		</TR>
		</TABLE>
	</TD>
</TR>
<%
	}
%>

<TR><TD CLASS="htable" ALIGN="CENTER"><BR><B>Budget Check <%=s_status.toUpperCase()%> !</B></TD></TR>
<TR><TD CLASS="htable" ALIGN="RIGHT" VALIGN="BOTTOM"><A HREF="javascript: parent.close();"><IMG SRC="/images/button_close.gif" BORDER="0" VALIGN="BOTTOM"></A>&nbsp;</TD></TR>
<TR><TD HEIGHT="5" CLASS="htable">&nbsp;</TD></TR>
</TABLE>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	self.focus();

// end hiding contents -->
</SCRIPT>
