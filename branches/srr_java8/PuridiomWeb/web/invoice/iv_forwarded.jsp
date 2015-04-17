<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	InvoiceHeader invoiceHeader = (InvoiceHeader) request.getAttribute("invoiceHeader");
	String	s_ivc_number = invoiceHeader.getInvoiceNumber();
	String	currencyCode = invoiceHeader.getCurrencyCode() ;
	String	s_fiscal_year = invoiceHeader.getFiscalYear();
	String	s_status_code = invoiceHeader.getStatus();
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

<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceHeader_status" value="<%=invoiceHeader.getStatus()%>"/>
<tsa:hidden name="InvoiceHeader_poNumber" value="<%=s_ivc_number%>"/>
<br>

<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td align="center">
		<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invoice", "Invoice")%>:<span class="hdr12"><a href="javascript: openInvoice(); void(0);">#<%=s_ivc_number%></a></span>
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

	function createAnotherInvoice() {
		doSubmit('/invoice/iv_review.jsp', 'InvoiceCreate');
	}

	function setRoutingDisplay()
	{
		var myTable = document.all("approvers0");
		if (myTable != undefined && myTable.rows != undefined && myTable.rows.length > 0)
		{
			displayArea("routingListDisplay");
		}
	}

	function openInvoice() {
		doSubmit('/invoice/iv_review.jsp', 'InvoiceRetrieve');
	}

	function printPdf() {
		doSubmit('/invoice/iv_print_pdf.jsp', 'DoNothing');
	}

// End Hide script -->
</SCRIPT>