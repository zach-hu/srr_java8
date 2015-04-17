<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.RequestParameters" %>

<%	Object requestId = request.getAttribute("requestId");
RequestParameters requestToProcess = RequestIdManager.getInstance().getRequest(requestId);
%>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
	<tr><td align="center" height="50px" class="error"><%="To make sure that you requests are processed correctly <p>please use the navigation options provided within Puridiom."%></td></tr>
	<tr><td align="center"><br><%=requestToProcess.getProcessMsg()%>&nbsp;
	<a href="javascript: <%=requestToProcess.getLink()%> void(0);" class="formType"><%=requestToProcess.getProcess()%></a>
		</td>
	</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<!---- JavaScripts for Entry Validation ----->
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	
	function openReq(ic) {
		var newInputField = "<input type='hidden' name='RequisitionHeader_icReqHeader' value='" + ic + "'>";
		setHiddenFields(newInputField);
		frm.viewType.value = "WIZARD";
		doSubmit('/requests/req_review.jsp', 'RequisitionRetrieve');
	}
	
	function openOrder(ic) {
		var newInputField = "<input type='hidden' name='PoHeader_icPoHeader' value='" + ic + "'>";
		setHiddenFields(newInputField);
		frm.viewType.value = "WIZARD";		
		doSubmit('/orders/po_review.jsp','PoRetrieve');
	}
	
	function openDsb(ic) {
		frm.viewType.value = "WIZARD";
		var newInputField = "<input type='hidden' name='DisbHeader_icDsbHeader' value='" + ic + "'>";
		setHiddenFields(newInputField);
		doSubmit('/inventory/dsb_review.jsp', 'DisbursementRetrieve');
	}
	
	function openInvItem(itemNum) {		
		var newInputField =  "<input type=\"hidden\" name=\"InvItem_itemNumber\" value=\"" + itemNum + "\" ><input type=\"hidden\" name=\"itemAction\" value=\"UPDATE\">";
		setHiddenFields(newInputField);
		doSubmit('/inventory/inv_item.jsp', 'InvItemRetrieveById');
	}
	
	function openCatalog(catalog){
		var newInputField =  "<INPUT TYPE=\"HIDDEN\" NAME=\"Catalog_catalogId\" value=\"" + catalog + "\" ><INPUT TYPE=\"HIDDEN\" NAME=\"CatalogItem_catalogId\" value=\"" + catalog + "\" >";
				setHiddenFields(newInputField);
		doSubmit('/admin/catalog/catalog.jsp', 'CatalogRetrieveById;CatalogItemRetrieveCountByCatalog');
	}

// end hiding contents -->
</SCRIPT>
<%@ include file="/system/prevent_caching.jsp" %>