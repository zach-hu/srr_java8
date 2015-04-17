<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsagate.foundation.rule.*" %>

<%
	ValidationRules rules = (ValidationRules)request.getAttribute("rules");
	DisbHeader disbHeader = (DisbHeader) request.getAttribute("disbHeader");
	String s_disb_number = disbHeader.getDisbNumber();
	boolean bForward = false;
	String disbaction = (String)request.getAttribute("disbaction");
	if (disbaction == null){ disbaction = "VALIDATE";	}
	if (HiltonUtility.isEmpty(s_disb_number))
	{
		s_disb_number = "N/A";
	}
%>

<tsa:hidden name="DisbHeader_icDsbHeader" value="<%=disbHeader.getIcDsbHeader()%>"/>
<tsa:hidden name="disbaction" value=""/>

<table border="0" cellpadding="0" cellspacing="0" width=655px>
<tr><td><br></td></tr>
<tr>
	<td width="100%">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td valign="top" width="50px" height="30px">
				<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
				<tr>
					<td height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
				</tr>
				<tr>
					<td nowrap class="hdr12" valign="middle">
						<div style="margin-left:10px; margin-right:10px" class="hdr12">Validation Results</div>
					</td>
				</tr>
				</table>
			</td>
			<td valign="bottom" align="left"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
			<td valign="bottom" align="right" height="30px" width="100%">
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr>
					<td width="100%" height="1px" class="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
				</tr>
				<tr>
					<td height="2px" class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr><td><br></td></tr>
<tr>
	<td align="center">
		<font class="formType">Disbursement </font><font class="hdr12">#<%=s_disb_number%></font>
	</td>
</tr>
<tr><td><br></td></tr>
<tr>
	<td width="100%" align="center" valign="top">
		<%@ include file="/base/validation-rules.jsp" %>
	</td>
</tr>
</table>

<br>
<br>

<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" ALIGN="CENTER" VALIGN="BOTTOM" width="400px">
<TR>
<%	if (rules.getResult() > -1 && disbaction.equalsIgnoreCase("FORWARD"))
		{ %>
	<TD align=center><a href="javascript: disbForward(); void(0);"><img class="button" src="<%=contextPath%>/images/button_forward.gif" border=0 alt="Forward"></a></TD>
<%	} %>
	<td align=center><a href="javascript: window.top.hidePopWin(); void(0);"><img class="button" src="<%=contextPath%>/images/button_close.gif" border=0 alt="Close"></a></td>
</tr>
<%	if (rules.getResult() == 1 && disbaction.equalsIgnoreCase("FORWARD"))
		{ %>
			 <tr>
				<td>
			  		<TABLE>
			  			<TD VALIGN="MIDDLE"><IMG SRC='<%=contextPath%>/images/alert.gif' VALIGN="MIDDLE" BORDER="0"></TD>
			  			<TD VALIGN="MIDDLE" CLASS="basic"><B>This window will close automatically when the forward process is complete.</B></TD>
			  		</TABLE>
				</td>
			</tr>
<%	} %>
</TR>	
</TABLE>
</FORM>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	self.focus();
<%	if (rules.getResult() == 1 && disbaction.equalsIgnoreCase("FORWARD")) { %>
	 reqForward();
<%	} %>

	function disbForward()
	{
		var disbaction = '<%=disbaction%>';
		frm.disbaction.value = disbaction;
		window.parent.frm.disbForward();
	}

// end hiding contents -->
</SCRIPT>

</BODY>
</HTML>