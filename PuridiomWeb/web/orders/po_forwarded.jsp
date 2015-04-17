<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	String	s_po_number = poHeader.getPoNumber();
	String	currencyCode = poHeader.getCurrencyCode() ;
	String	s_fiscal_year = poHeader.getFiscalYear();
	String	s_status_code = poHeader.getStatus();
	String	s_forwardedto = HiltonUtility.ckNull((String) request.getAttribute("forwardedTo"));
	StringBuffer forwardedToNames = new StringBuffer("");

	if (!HiltonUtility.isEmpty(s_forwardedto)) {
		String forwardedToArray[] = s_forwardedto.split(";");
		for (int ift=0; ift < forwardedToArray.length; ift++) {
			forwardedToNames.append(UserManager.getInstance().getUser(oid, forwardedToArray[ift]).getDisplayName() + ", ");
		}
		if (forwardedToNames.length() > 2) {
			forwardedToNames.setLength(forwardedToNames.length() - 2);
		}
	}
%>

<tsa:hidden name="PoHeader_icPoHeader" value="<%=poHeader.getIcPoHeader()%>"/>
<tsa:hidden name="PoHeader_status" value="<%=poHeader.getStatus()%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<br>

<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td align="center">
		<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "order", "Order")%>:<span class="hdr12"><a href="javascript: openOrder(); void(0);">#<%=s_po_number%></a></span>
		<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "hasBeenForwarded", "has been forwarded to")%> <%=forwardedToNames.toString()%>!
				<div id="routingListDisplay" style="visibility:hidden; display:none;">
					<br><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-belowFinalRoutingList", "Below is the final routing list for this order")%>.<br>
<%	Map approvalNotes = new HashMap();
	String backgroundClass = "basic";

	if (propertiesManager.getProperty("APPROVALS", "RESORTBY", "").equals("RULE")) {%>
		<%@include file="/base/approval_routing_list.jsp" %>
<%	} else { %>
		<%@include file="/base/approval_routing_list_amt.jsp" %>
<%	}%>
				</div>
	</td>
</tr>
</table>
<br>
<br>
<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	hideArea("navTable");
	displayArea("menubarSpacer");
	setRoutingDisplay();

	function createAnotherOrder() {
		doSubmit('/orders/po_review.jsp', 'PoCreate');
	}

	function setRoutingDisplay()
	{
		var myTable = document.all("approvers0");
		if (myTable != undefined && myTable.rows != undefined && myTable.rows.length > 0)
		{
			displayArea("routingListDisplay");
		}
	}

	function openOrder() {
		doSubmit('orders/po_review.jsp', 'PoRetrieve');
	}

	function printPdf() {
		doSubmit('/orders/po_print_pdf.jsp', 'DoNothing');
	}

// End Hide script -->
</SCRIPT>