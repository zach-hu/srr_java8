<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="java.math.*" %>

<%
List	errorList = (List) request.getAttribute("errorList");
String	sProcess = (String) request.getAttribute("process") ;
String	s_status = (String) request.getAttribute("budgetStatus");
String	s_form_type = request.getParameter("budgetType");
String	s_action_code = request.getParameter("budgetAction");
String	s_action = request.getParameter("budgetActionText");
String 	s_prc_action = request.getParameter("prc_action");
String	errorMsg = "";
%>

<TABLE BORDER="0" CELLPADDING="0" CELLSPACING="0"  WIDTH="100%"  MARGIN="0" >
<%
	//String	redirect_pg = request.getParameter("pg");
	if (sProcess.indexOf("REQ") >= 0)
	{
		if (s_form_type == null)
		{
			s_form_type = "REQ";
			s_action_code = "1";
		}
	}
	if (sProcess.indexOf("PO") >= 0)
	{
		if (s_form_type == null)
		{
			s_form_type = "PO";
			s_action_code = "1";
		}
	}

	if (s_action == null) s_action = "check" ;


		if (s_status.equalsIgnoreCase("FAILED"))
		{
			int i_dbrows = errorList.size() ;
%>
<TR>
	<TD CLASS="htable" WIDTH="100%">
		<TABLE ALIGN="CENTER" VALIGN="TOP" BORDER="0" WIDTH="100%" >
		<TR>
			<TD>&nbsp;</TD>
			<TD>
				<TABLE BORDER="1" CELLPADDING="1" CELLSPACING="2" WIDTH="100%" BGCOLOR="#999999">
				<TR>
					<TD CLASS="browseHdrDk" NOWRAP ALIGN="CENTER">Error Message</TD>
					<TD CLASS="browseHdrDk" NOWRAP ALIGN="CENTER">Budget Id</TD>
					<TD CLASS="browseHdrDk" NOWRAP ALIGN="CENTER">Budget Authority</TD>
					<TD CLASS="browseHdrDk" NOWRAP ALIGN="CENTER">Initial Budget</TD>
					<TD CLASS="browseHdrDk" NOWRAP ALIGN="CENTER">Budget Balance</TD>
				</TR>
			<%for (int i = 0; i < i_dbrows; i++)
				{
					Hashtable ht = (Hashtable) errorList.get(i) ;
					String		bError = (String) ht.get("budgetError");
					String		bId = (String) ht.get("budgetId");
					String		bAuth = (String) ht.get("budgetAuth");
					BigDecimal		bBalance = (BigDecimal) ht.get("budgetBalance");
					BigDecimal		bInitial = (BigDecimal) ht.get("budgetInitial");
					String		bSeverity = (String) ht.get("errorSeverity");
				%>
				<TR>

					<TD CLASS="browseRow" NOWRAP ALIGN="LEFT"><%=bError%>&nbsp;</TD>
					<TD CLASS="browseRow" NOWRAP ALIGN="LEFT"><%=bId%>&nbsp;</TD>
					<TD CLASS="browseRow" NOWRAP ALIGN="LEFT"><%=bAuth%>&nbsp;</TD>
					<TD CLASS="browseRow" NOWRAP ALIGN="RIGHT"><%=HiltonUtility.getFormattedDollar(bInitial,oid)%>&nbsp;</TD>
					<TD CLASS="browseRow" NOWRAP ALIGN="RIGHT"><%=HiltonUtility.getFormattedDollar(bBalance,oid)%>&nbsp;</TD>
				</TR>
<%			} %>
				</TABLE>
			</TD>
			<TD>&nbsp;</TD>
		</TR>
		</TABLE>
	</TD>
</TR>
<%
		}
%>
<%
	if (s_status.equalsIgnoreCase("FAILED"))
	{
%>
<TR><TD CLASS="htable" ALIGN="CENTER"><BR><B>Budget Check Failed for one of the following reason(s):</B></TD></TR>
<%	} else if (s_status.equalsIgnoreCase("IGNORE")) { %>
<TR><TD CLASS="htable" ALIGN="CENTER"><BR><BR><BR><B>A Budget Check is not required for this Requisition!</B><BR><BR></TD></TR>
<%	} else { %>
<TR><TD CLASS="htable" ALIGN="CENTER"><BR><BR><B>Budget Check Passed!</B><BR><BR></TD></TR>
<%
	}
%>
<!--
<TR><TD CLASS="htable" ALIGN="CENTER"><BR><B>Budget Check <%=s_status.toUpperCase()%> !</B></TD></TR>
-->
<%
		if (s_status.equalsIgnoreCase("FAILED"))
		{

%>
<TR><TD  ALIGN="CENTER"><BR><B>FAILURE REASONS</B></TD></TR>
<TR><TD CLASS="basic11" ALIGN="CENTER"><BR>1. The amount of the request exceeds the budget.<BR></TD></TR>
<TR><TD CLASS="basic11" ALIGN="CENTER">2. The Center Code is invalid<BR></TD></TR>
<TR><TD CLASS="basic11" ALIGN="CENTER">3. The Budget Id entered is invalid<br><br></TD></TR>
<TR><TD CLASS="basic11" ALIGN="CENTER"> Please re-submit a revised and approved project budget to Procurement<br><br></TD></TR>
<%			} 	%>

 <%	if ( s_status.equalsIgnoreCase("passed") ){%>
		<tsa:hidden name="action" value="forward"/>
 		<tsa:hidden name="preview" value="N"/>
 		<tsa:hidden name="pg" value="/puridiom/xchange/requests/req_review.jsp"/>
 		<tsa:hidden name="prc_action" value="<%=s_prc_action%>"/>
 <%	}%>

<TR>
<%	if ( s_action.equals("forward") && s_status.equalsIgnoreCase("PASSED") )
 {%>
	 <TR>
		<TD ALIGN="CENTER" CLASS="basic"><br><br><B>This window will close automatically when the forward process is complete.</B><BR><BR></TD>
	 </TR>
 	<TD ALIGN="RIGHT">
 		<A HREF="javascript:  openValidate(); void(0);"><img class=button src="<%=contextPath%>/images/button_continue.gif" border=0 alt="Continue"></A>
 	</TD>
 <%	}
 	else
  	{ %>
 	<TD ALIGN="RIGHT">
 		<A HREF="javascript: parent.close(); void(0);"><img class=button src="<%=contextPath%>/images/button_close.gif" border=0 alt="Close"></A>
 	</TD>
 <%	} %>
 </TR>
<!-- <TR><TD CLASS="darkshadow" ALIGN="RIGHT" VALIGN="BOTTOM"><A HREF="javascript: parent.close();"><IMG SRC="/puridiom/xchange/images/button_close.gif" BORDER="0" VALIGN="BOTTOM"></A>&nbsp;</TD></TR>
-->
<TR><TD HEIGHT="5" CLASS="htable">&nbsp;</TD></TR>
</TABLE>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	window.resizeTo(550,275);
	self.focus();
<%
	if (s_status.equalsIgnoreCase("PASSED") && s_action.equalsIgnoreCase("forward"))
	{ %>
		document.body.style.cursor = "wait";
		setTimeout('openValidate();', 8000);
<%	} %>

<%
	if (s_status.equalsIgnoreCase("IGNORE") && s_action.equalsIgnoreCase("forward"))
	{ %>
		document.body.style.cursor = "wait";
		setTimeout('openValidate();', 1000);
<%	} %>

function openValidate() {
<%	if (s_process.equals("REQ") { %>
 	window.location='/puridiom/xchange/requests/req_validate.jsp?action=forward';
<%	} else if (s_process.equals("PO")) { %>
<% } %>
}

</SCRIPT>
</BODY>
</HTML>
