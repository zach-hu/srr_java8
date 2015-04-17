<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsagate.foundation.rule.*" %>
<%@ page import="com.tsa.puridiom.common.documents.*" %>
<%
	ValidationRules rules = (ValidationRules)request.getAttribute("rules");
	PoHeader poHeader = (PoHeader)request.getAttribute("header");
	String s_form_number = poHeader.getPoNumber();
	boolean bForward = false;
	String poAction = (String)request.getAttribute("poaction");
	if(poAction == null){ poAction = "VALIDATE";	}
%>
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
		<font class="formType"><%=OrderType.toString(poHeader.getPoType(), oid)%> </font><font class="hdr12">#<%=s_form_number%></font>
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

<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" ALIGN="CENTER" VALIGN="BOTTOM" WIDTH="400px">
			<TR>
    		<%if(rules.getResult() > -1 && poAction.equalsIgnoreCase("FORWARD"))
    		{%>
      			<td align="center"><div id="pxbutton"><a href="javascript: poForward(); void(0);">Forward</a></div></TD>
    		<%}%>
    			<td align="center"><div id="pxbutton"><a href="javascript: window.top.hidePopWin(); void(0);">Close</a></div></td>
			</tr>
		<%if(rules.getResult() == 1 && poAction.equalsIgnoreCase("FORWARD"))
		{%>
			 <tr>
    		<td>
      		<TABLE>
      			<TD VALIGN="MIDDLE"><IMG SRC='<%=contextPath%>/images/alert.gif' VALIGN="MIDDLE" BORDER="0"></TD>
      			<TD VALIGN="MIDDLE" CLASS="basic"><B>This window will close automatically when the forward process is complete.</B></TD>
      		</TABLE>
    		</td>
			</tr>
		<%}%>
	</TR>	
</TABLE>
</FORM>
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	thisfrm = document.purchaseform;
	self.focus();
	<%if(rules.getResult() == 1 && poAction.equalsIgnoreCase("FORWARD")){%>
	 poForward();
	<%}%>

	function poForward()
	{
	  var poaction = '<%=poAction%>';
		
		//set cursor to hourglass while the system is processing
		window.parent.document.body.style.cursor = "wait";
		window.parent.frm.poaction.value = "FORWARD";
		window.parent.orderForward();
	
		setTimeout('window.top.hidePopWin();', 500);	
//		window.top.hidePopWin();
	}

// end hiding contents -->
</SCRIPT>

</BODY>
</HTML>