<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.InvoiceType" %>

<%
    PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	InvItem invItem = (InvItem) request.getAttribute("invItem");
	String inventoryAction = HiltonUtility.ckNull((String) request.getAttribute("inventoryaction"));
	String s_status = invItem.getApproveStatus();

	boolean forwardOptionAvailable = true;

	String classType = "summary";

	String fromEmail = (String) request.getAttribute("fromEmail");
	if (fromEmail == null)
	{
		 fromEmail = "N";
	}
	String s_item_number = invItem.getItemNumber();
%>

<tsa:hidden name="InvItem_itemNumber" value="<%=s_item_number%>"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td align=center>
		<br><br>
		<font class="formType">Inventory #</font><font class=hdr12><%=s_item_number%></font>&nbsp;
<%		if (s_status.compareTo(DocumentStatus.INV_APPROVING) >= 0 && s_status.compareTo(DocumentStatus.INV_APPROVED) < 0)
		{
			String s_forwardedto = HiltonUtility.ckNull ((String) request.getAttribute("forwardedTo"));%>
			has been forwarded to <%=UserManager.getInstance().getUser(oid, s_forwardedto).getDisplayName()%>!
<%	}
		else if (inventoryAction.equalsIgnoreCase("REJECT"))
		{ %>
			has been Rejected!
<%	}
		else
		{ %>
			has been Approved!
<%	} %>
	</td>
</tr>
</table>

<br>
<br>

<br>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function checkHiddenMenu()
	{
		hideArea("navTable");
		displayArea("menubarSpacer");
	}
	  
	function openInvoice() {
		doSubmit('/invoice/iv_summary.jsp', 'InvoiceRetrieve');
	}

// End Hide script -->
</SCRIPT>