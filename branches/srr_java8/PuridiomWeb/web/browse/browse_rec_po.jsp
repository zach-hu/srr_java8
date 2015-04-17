<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ page import="java.math.BigDecimal" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/filter.js"></script>

<%@ include file="/browse/browse_form.jsp" %>

<input type=hidden name=browsePage value="/browse/browse_rec_po.jsp">
<input type=hidden name=browseName value="">

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function thisLoad()
	{
		f_StartIt();
	}

	function getOrder(icPoHeader)
	{
		if (confirm("Get this Order ?"))
		{
			if (window.parent.frm.PoHeader_icPoHeader) {
				window.parent.frm.PoHeader_icPoHeader.value = icPoHeader;
			}
			if (window.parent.frm.PoLine_icPoHeader) {
				window.parent.frm.PoLine_icPoHeader.value = icPoHeader;
			}
			window.parent.doSubmit('/receipts/rec_general_info.jsp','ReceiptPackageUpdateFromOrder;ReceiptLineCreateFromPoLineList');
			doSubmit('/system/close_window.jsp', 'BrowseCleanupByBrowseId');
		}
	}

	function browse(x)
	{
		frm.browseName.value = x;

		doSubmit('/browse/browse_rec_po.jsp', 'BrowseRetrieve');
	}

// End Hide script -->
</SCRIPT>