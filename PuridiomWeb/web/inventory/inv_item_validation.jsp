<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.rule.*" %>
<%@ page import="com.tsa.puridiom.common.documents.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	ValidationRules rules = (ValidationRules)request.getAttribute("rules");
	InvItem invItem = (InvItem) request.getAttribute("header");
	String s_number = invItem.getItemNumber();
	boolean bForward = false;
	String itemAction = (String)request.getAttribute("itemAction");
	if (itemAction == null){ itemAction = "VALIDATE";	}
%>

<tsa:hidden name="InvItem_itemNumber" value="<%=s_number%>"/>
<tsa:hidden name="itemAction" value="<%=itemAction%>"/>

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
			<td valign="bottom" align="left"><img class="hdr12" src="<%=contextPath%>/images/angle.gif"height="31" /></td>
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
		<font class="formType">Inventory Item </font><font class="hdr12">#<%=s_number%></font>
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
	<td align="center" ><a href="javascript: returnToItem(); void(0);"><img class="button" src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return to Item"></a></td>
</tr>
<%	if (rules.getResult() == 1 && itemAction.equalsIgnoreCase("FORWARD"))
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
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers
	setNavCookie("/inventory/inv_item_validate.jsp", "DoNothing", "Validation Results");

	frm = document.purchaseform;
	self.focus();
	displayArea('validationrules');

<%	if (rules.getResult() == 1 && itemAction.equalsIgnoreCase("UPDATE"))
		{ %>
	  		returnToItem();
<%	} %>

	function itemForward()
	{
		returnToItem();
	}

	function returnToItem()
	{
		doSubmit('/inventory/inv_item.jsp', 'InvItemRetrieveById');
	}
// end hiding contents -->
</SCRIPT>

<%@ include file="/system/footer.jsp" %>