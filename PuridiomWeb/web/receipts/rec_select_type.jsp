<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>

<tsa:hidden name="ReceiptHeader_receiptType" value=""/>
<tsa:hidden name="formtype" value="RCV"/>
<tsa:hidden name="invbrowse" value=""/>

<%
	int fromNothingAcess = role.getAccessRights("RCVNOTHING");
	int byItemAccess = role.getAccessRights("RCVBYITEM");
	int transferReqAccess = role.getAccessRights("REQT");

	int rcvByPkgAccess = role.getAccessRights("RCVBYPKG");
	int rcvByEndUserAccess = role.getAccessRights("RCVBYENDUSER");
	int rcvFromNothingAccess = role.getAccessRights("RCVNOTHING");
	int rcvByItemAccess = role.getAccessRights("RCVBYITEM");
	int rcvFinalizeAccess = role.getAccessRights("RCVFINALIZE");
	int rcvAdjAccess = role.getAccessRights("RCVADJUST");
	int rcvReturnAccess = role.getAccessRights("RCVRETURN");
	int rcvHistoryAccess = role.getAccessRights("RCVHISTORY");
%>

<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td valign="top" width="150px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tr>
			<td height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class="hdr12" valign="middle">
				<div style="margin-left:10px; margin-right:10px" class="hdr12"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rcvtypeselection", "Receipt Type Selection")%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign="bottom"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign="bottom" align="right">
<%
		PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
		String displayViewType = propertiesManager.getProperty("RCV OPTIONS", "DISPLAYVIEWTYPE", "N");
		boolean disableEndUserAdjust = propertiesManager.getProperty("REC DEFAULTS", "DISABLE END USER ADJUST", "N").equalsIgnoreCase("Y");
		if (displayViewType.equalsIgnoreCase("Y")) {
%>
		<table border="0" cellspacing="0" cellpadding="0" border="0">
		<tr>
			<td width="100px">&nbsp;</td>
			<td align="right">&nbsp;<input type=radio name="as_view_type" value="CLASSIC"></td>
			<td width="100px" nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "classicview", "Classic View")%></b></td>
			<td align="right"><input type=radio name="as_view_type" value="WIZARD" CHECKED></td>
			<td nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "wizardview", "Wizard View")%></b></td>
			<td width="10px">&nbsp;</td>
		</tr>
		</table>
<%	}	%>

		<table cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td width="1000px" height="1px" align="center"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid,"rcv-type-selection","")%></td>
			</tr>
			<tr>
				<td width="1000px" height="1px" class="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
			</tr>
			<tr>
				<td height="2px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
			</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td width="680px" align="center">
	<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" WIDTH="650" border="0" ALIGN="CENTER">
<%	if (rcvByPkgAccess > 0) { %>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="40%" class="formType">
			<A HREF="javascript: createRcv('A'); void(0);" title="Clicking here will allow you to create a Package Receipt from Purchase Order."; class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rcvtype-a", "Package Receipt From Purchase Order")%></A>
		</TD>
		<TD><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rcvtxt-a", "Create a Package Receipt from a Purchase Order.", false)%></TD>
	</TR>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="40%" class="formType">
			<A HREF="javascript: createRcv('ANP'); void(0);" title="Clicking here will allow you to create a Package Receipt no Purchase Order."; class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rcvtype-a-np", "Package Receipt no Purchase Order")%></A>
		</TD>
		<TD><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rcvtxt-a-np", "Create a Package Receipt without a Purchase Order.", false)%></TD>
	</TR>
<%	}
	if (rcvFinalizeAccess >= 3) { %>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="40%" class="formType">
			<A HREF="javascript: createRcv('AF'); void(0);" title="Clicking here will allow you to create a Finalize Receipt from Purchase Order."; class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rcvtype-b", "Finalize Receipt From Purchase Order")%></A>
		</TD>
		<TD><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rcvtxt-b", "Finalize a Package Receipt from Purchase Order.", false)%></TD>
	</TR>
<%	}
	if (byItemAccess > 0) { %>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="40%" class="formType">
			<A HREF="javascript: createRcv('P'); void(0);" title="Clicking here will allow you to create a Full Receipt from a Purchase Order."; class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rcvtype-p", "Full Receipt From Purchase Order")%></A>
		</TD>
		<TD><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rcvtxt-p", "Create a Full Receipt from a Purchase Order.", false)%></TD>
	</TR>
<%	}
	if (fromNothingAcess > 0) { %>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="40%" class="formType">
			<A HREF="javascript: createRcv('O'); void(0);" title="Clicking here will allow you to create a Receipt from Nothing."; class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rcvtype-o", "Receipt From Other")%></A>
		</TD>
		<TD><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rcvtxt-o", "Create a Full Receipt from Other/Nothing - Non PO Receipt.", false)%></TD>
	</TR>
<%	}
	if (rcvAdjAccess > 0) {%>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="40%" class=formType>
			<A HREF="javascript: <%if(disableEndUserAdjust){%>adjustReceiptsNoEndUser();<%}else{%>adjustReceipts();<%}%> void(0);" title="Clicking here will allow you to create a Receipt Adjustment."; class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rcvtype-d", "Adjust Receipt Quantities")%></A>
		</TD>
		<TD><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rcvtxt-d", "Create a Receipt Adjustment.", false)%></TD>
	</TR>
<%	}
	if (rcvReturnAccess > 0) {%>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="40%" class=formType>
			<A HREF="javascript: returnAgainstReceipts(); void(0);" title="Clicking here will allow you to create a Receipt Return."; class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rcvtype-e", "Receipt Return")%></A>
		</TD>
		<TD><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rcvtxt-e", "Create a Receipt Return.", false)%></TD>
	</TR>
<%	}
	if (transferReqAccess > 0) {%>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="40%" class=formType>
			<A HREF="javascript: createRcv('T'); void(0);" title="Clicking here will allow you to create a Receipt from a Transfer Request."; class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rcvtype-t", "Receipt From Transfer Request")%></A>
		</TD>
		<TD><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rcvtxt-t", "Create a Receipt from a Transfer Request.", false)%></TD>
	</TR>
<%	}  %>

	</TABLE>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var userId = '${esapi:encodeForJavaScript(userId)}';
	var department = '<%=user.getDepartment()%>';

	setNavCookie("/receipts/rec_select_type.jsp", "DoNothing", "Create New Receipt", true);

	function createRcv(type)
	{
		setViewType();
		frm.ReceiptHeader_receiptType.value = type;

		var newInputField;

		if (type == "A")	/*  Purchase Order  */
		{
			<% if (rcvByPkgAccess >= 3) { %>
				browseFilter('receipt-package-order');
			<% } else if (rcvByPkgAccess == 2) { %>
				browseFilter('receipt-order-pkg-by-dept');
			<% } else if (rcvByPkgAccess == 1) { %>
				browseFilter('receipt-order-pkg-enduser');
			<% } %>
		}
		if (type == "ANP")	/*  without Purchase Order  */
		{
			var newInputField = "<input type='hidden' name='receiptMethod' value='ReceiveByPackage'>";
			setHiddenFields(newInputField);
			doSubmit('/receipts/rec_general_info.jsp', 'ReceiptPackageCreate');
		}
		if (type == "AF")	/*  Purchase Order  */
		{
			browse('receipt-finalize-order');
		}
		if (type == "P")	/*  Purchase Order  */
		{
			receiveByItem();
		}
		else if (type == "O")	/*  Nothing  */
		{
			receiveFromNothing();
		}
		else if (type == 'T')	/*  Transfer Request  */
		{
			receiveFromTransfer();
		}
	}

	function setViewType()
	{
		var displayViewType = "<%=displayViewType%>";
		if (displayViewType != "Y")
		{
			frm.viewType.value = "WIZARD";
		}
		else
		{
			var viewType = "";
			var types = frm.elements.item("as_view_type");

			for (var i = 0; i < types.length; i++)
			{
				if (frm.as_view_type[i].checked) {
					viewType = frm.as_view_type[i].value
				}
			}

			if (viewType == "CLASSIC")
			{
				frm.viewType.value = "CLASSIC";
			}
			else if (viewType == "WIZARD")
			{
				frm.viewType.value = "WIZARD";
			}
		}
	}

// End Hide script -->
</SCRIPT>
<%@ include file="/system/prevent_caching.jsp" %>