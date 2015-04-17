<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.asset.checks.AssetChecks" %>
<%@ page import="com.tsa.puridiom.common.documents.ReceiptType" %>
<%
	ReceiptHeader receiptHeader = (ReceiptHeader) request.getAttribute("receiptHeader");
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	if (receiptHeader == null) {
		receiptHeader = new ReceiptHeader();
	}
	if (poHeader == null) {
		poHeader = new PoHeader();
	}
	List	poLineList = poHeader.getPoLineList();
	if (poLineList == null) {
		poLineList = new ArrayList();
	}
	String	s_rec_type = receiptHeader.getReceiptType();
	String	s_rec_status = receiptHeader.getReceiptStatus();
	String	forwardTo = receiptHeader.getForwardTo();
	String	users = "";
	String s_po_number=poHeader.getPoNumber();

	if (forwardTo.equals("END-USERS")) {
		Map endUsersSet = new HashMap();
		forwardTo = "";
		for (int il = 0; il < poLineList.size(); il++) {
			PoLine poLine = (PoLine) poLineList.get(il);
			String	endUser = poLine.getRequisitionerCode();

			if (HiltonUtility.isEmpty(endUser)) {
				endUser = poHeader.getRequisitionerCode();
			}
			if (HiltonUtility.isEmpty(endUser)) {
				endUser = poHeader.getOwner();
			}
			if (!HiltonUtility.isEmpty(endUser) && !endUsersSet.containsKey(endUser)) {
				forwardTo = forwardTo + UserManager.getInstance().getUser(oid, endUser).getDisplayName() + ";";
				endUsersSet.put(endUser, "Y");
			}
		}
	} else {
		forwardTo = UserManager.getInstance().getUser(oid, forwardTo).getDisplayName();
	}

%>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td align=center>
	<%	if (s_rec_type.equals("A")) { %>
		<%=ReceiptType.toString(s_rec_type, oid)%> #<font class=hdr12><%=receiptHeader.getReceiptNumber()%></font> has been forwarded to <%=forwardTo%>.
	<%	} else { %>
		Receipt #<font class=hdr12><%=receiptHeader.getReceiptNumber()%></font> has been created and forwarded to <%=forwardTo%>.
	<%	} %>
	</td>
</tr>
<tr><td><br><br></td></tr>
<tr><td align=center>Would you like to receive more packages?</td></tr>
<tr><td><br><br></td></tr>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
		<%	if (s_rec_type.equals("A")) { %>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: browse('receipt-package-order'); void(0);">Yes</a></div></td>
		<%	} else { %>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: browseFilter('receipt-order-pkg'); void(0);">Yes</a></div></td>
		<%	} %>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('menu/main_menu.jsp', 'DoNothing'); void(0);">No</a></div></td>
		</tr>
		<tr>
			<td colspan=2 width=100% align=center>
			<% if (assetsActive && !HiltonUtility.isEmpty(poHeader.getStatus()) && Integer.valueOf(poHeader.getStatus()).intValue() >= 3030 && AssetChecks.getInstance(oid).isPoItemsAsset(oid,s_po_number)) { %><a href="javascript: viewAssetRelated();">Update Asset Information</a><% } %>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	setNavCookie("/receipts/rec_select_type.jsp", "DoNothing", "Create New Receipt", true);

	function viewAssetRelated()
	{
		var newInputField = "<input type='hidden' name='allowBrowse' value='true'>";
		setHiddenFields(newInputField);
		doSubmit("/asset/asset_po_browse.jsp", "AssetRetrieveByPoLineList");
	}

// End Hide script -->
</SCRIPT>