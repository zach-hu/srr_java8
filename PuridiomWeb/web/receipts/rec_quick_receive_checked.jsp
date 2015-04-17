<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<%
	List poHeaderList = (List) request.getAttribute("poHeaderList");
	if (poHeaderList == null)
	{
		poHeaderList = new ArrayList(10);
	}

	List poNumberList = (List) request.getAttribute("poNumberList");
	if (poNumberList == null)
	{
		poNumberList = new ArrayList(10);
	}

	List requirement = (List) request.getAttribute("requirement");
	if (requirement == null)
	{
		requirement = new ArrayList(10);
	}

	String notFound = "Not Found";
	String notEligible = "Not Eligible";
	String found = "Found: ";
	PoHeader poHeader = null;
	int cont = 0;
%>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Quick Receiving</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table width=<%=formEntryWidth%> border=0 cellspacing=0 cellpadding=2>
	<tr>
		<td>
			<table width="70%" border=0 cellspacing=0 cellpadding=2>

			<!--Headings-->
			<tr>
				<td style="font:13" height=30px align="center">
					<b>Enter PO Numbers:</b>
				</td>
			</tr>
		<% for(int i = 0; i < poHeaderList.size(); i++){%>
			<tr>
				<td align="center">
					<%=(i+1)%>:&nbsp;&nbsp;
					<input name="PoNumber<%=(i+1)%>" type="text" size="20" maxlength="20" value="<%=poNumberList.get(i)%>"></input>
				</td>
				<td align="right">
						<%	if (poHeaderList.get(i) != "" && poHeaderList.get(i) != "NE")
						  	{
								poHeader = (PoHeader) poHeaderList.get(i);
								if(requirement.get(i).equals("I")){
									out.print("<span style=\"font-weight:bold\"><FONT COLOR=#0fdb0f> OK </FONT>&nbsp;&nbsp; " + found + DocumentStatus.toString(poHeader.getStatus(), oid) + "&nbsp;&nbsp;<FONT COLOR=DB1010> RECEIPT INSPECTION REQUIRED </FONT></span>");
								} else if(requirement.get(i).equals("M")){
									out.print("<span style=\"font-weight:bold\"><FONT COLOR=#0fdb0f> OK </FONT>&nbsp;&nbsp; " + found + DocumentStatus.toString(poHeader.getStatus(), oid) + "&nbsp;&nbsp;<FONT COLOR=DB1010> RECEIPT MARK/TAG REQUIRED </FONT></span>");
								} else {
									out.print("<span style=\"font-weight:bold\"><FONT COLOR=#0fdb0f> OK </FONT>&nbsp;&nbsp; " + found + DocumentStatus.toString(poHeader.getStatus(), oid) + "</span>");
								}
								out.print("<input type=\"hidden\" name=\"icPoHeader\" value=\""+poHeader.getIcPoHeader().toString()+"\"/>");
								cont++;
						  	}
							else if (poHeaderList.get(i) == "NE")
							{
								out.print("<FONT COLOR=DB1010><span style=\"font-weight:bold\">" + notEligible + "</FONT>");
							}
							else if (poNumberList.get(i) != "")
							{
								out.print("<FONT COLOR=DB1010><span style=\"font-weight:bold\">" + notFound + "</FONT>");
							}
						%>
				</td>
			</tr>
		<% }%>
			</table>
		</td>
		<td>
			<table width="30%" border=0 cellspacing=0 cellpadding=2>
			<tr><td>&nbsp;&nbsp;</td></tr>
			<tr><td>&nbsp;&nbsp;</td></tr>
		<% if(cont > 0) {%>
			<tr>
				<td><div id="pxbutton"><a href="javascript: submitThis();">Submit</a></div></td>
			</tr>
		<% } else { %>
			<tr><td>&nbsp;&nbsp;</td></tr>
		<% } %>
			<tr><td>&nbsp;&nbsp;</td></tr>
			<tr><td>&nbsp;&nbsp;</td></tr>
			<tr>
				<td><div id="pxbutton"><a href="javascript: quickRcv();">Reset</a></div></td>
			</tr>
			</table>
		</td>
	</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	setNavCookie("/receipts/rec_quick_receive_checked.jsp", "DoNothing", "Quick Receiving");

	function returnMe() {
		doSubmit('menu/main_menu.jsp', 'DoNothing');
	}

	function moreQuickReceipts() {
		doSubmit('receipts/rec_quick_receive.jsp', 'DoNothing');
	}

	function submitThis() {
		doSubmit('receipts/rec_quick_receive.jsp', 'QuickReceiving');
	}

// End Hide script -->
</SCRIPT>