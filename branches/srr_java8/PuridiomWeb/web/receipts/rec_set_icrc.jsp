<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="java.math.*" %>
<%
	List invBinLocationList = (List) request.getAttribute("invBinLocationList");
	String receiptRow = (String) request.getAttribute("receiptRow");
	String	icRc = "";
	BigDecimal	qtyTotal = new BigDecimal(0) ;
	BigDecimal	duomQtyTotal = new BigDecimal(0) ;

	if (invBinLocationList != null && invBinLocationList.size() > 0) {
		InvBinLocation invBinLocation = (InvBinLocation) invBinLocationList.get(0);
		if (invBinLocation != null) {
			icRc = String.valueOf(invBinLocation.getIcRc());
			BigDecimal qty = invBinLocation.getQtyOnHand() ;
			if (qty == null) qty = new BigDecimal(0) ;
			qtyTotal = qtyTotal.add(qty) ;
			BigDecimal duomQty = invBinLocation.getDuomQtyOnHand() ;
			if (duomQty == null) duomQty = new BigDecimal(0) ;
			duomQtyTotal = duomQtyTotal.add(duomQty) ;
		}
	}
	if (receiptRow == null) {	receiptRow = "0";	}
%>
<HTML>
<HEAD></HEAD>
<BODY>
<table border=0 cellspacing=0 cellpadding=0 width=100%>
<tr>
	<td width=100% align=center valign=top><br><b>Processing... Please wait.</b><br><br><br><img src="/hilton/images/processing.gif" border=1 width=200px height=15px></td>
</tr>
<tr><td id=requestParameters></td></tr>
</table>
</BODY>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers
	var frm = document.purchaseform;
	var qtyTotal = "<%=qtyTotal%>" ;
	var duomQtyTotal = "<%=duomQtyTotal%>" ;
	var serialNumberRequired = "<%=(String) request.getAttribute("serialNumberRequired")%>" ;

	window.parent.focus();
//	window.parent.setInvPropertyIcRc('<%=icRc%>', <%=receiptRow%>);
	window.parent.frm.ReceiptLine_duomQtyReceived.value = duomQtyTotal ;
	if (serialNumberRequired == "false") {
		if (window.parent.openedFromPopup) {
			window.parent.hideArea('getInfoFrame');
		} else {
			window.top.hidePopWin();
		}
	} else {
		checkProperty() ;
	}

	function checkProperty() {
		var shipToInv = window.parent.frm.ReceiptHeader_shipToInv.value;

		if (window.parent.frm.receiptMethod.value == 'ReceiveByTransfer') {
		   	var itemLocation = window.parent.frm.RequisitionLine_itemLocation.value;
			var itemNumber = window.parent.frm.RequisitionLine_itemNumber.value;
			var itemCost = window.parent.frm.RequisitionLine_unitPrice.value;
			var umCode = window.parent.frm.RequisitionLine_umCode.value;
			var commodity = window.parent.frm.RequisitionLine_commodityCode.value;
			var receiptOption = window.parent.frm.RequisitionLine_receiptRequired.value;
		} else {
			var itemLocation = window.parent.frm.PoLine_itemLocation.value;
			var itemNumber = window.parent.frm.PoLine_itemNumber.value;
			var itemCost = window.parent.frm.PoLine_unitPrice.value;
			var umCode = window.parent.frm.PoLine_umCode.value;
			var commodity = window.parent.frm.PoLine_commodity.value;
			var receiptOption = window.parent.frm.PoLine_receiptRequired.value;
		}
		var icPoLine = window.parent.frm.ReceiptLine_icPoLine.value;
		var qtyReceived = window.parent.frm.ReceiptLine_qtyAccepted.value;
		var duomQtyReceived = window.parent.frm.ReceiptLine_duomQtyReceived.value;
		var duomUmCode = window.parent.frm.ReceiptLine_duomUmCode.value;
		var receiptLineFactor = window.parent.frm.receiptLineFactor.value;
		var icRecLine = window.parent.frm.ReceiptLine_icRecLine.value ;
		var icRc = "<%=icRc%>" ;
		parameters = "InvItem_itemNumber=" + itemNumber + ";InvBinLocation_itemNumber=" + itemNumber +
				";InvBinLocation_itemLocation=" + itemLocation + ";InvLocation_itemNumber=" + itemNumber +
				";InvLocation_itemLocation=" + itemLocation + ";qtyReceived=" + qtyReceived + ";duomQtyReceived=" + duomQtyReceived +
				";index=0;InvBinLocation_tempIc=" + window.parent.frm.InvBinLocation_tempIc.value + ";InvBinLocation_cost=" + itemCost +
				";PoLine_icPoLine=" + icPoLine + ";PoLine_umCode=" + umCode + ";commodity=" + commodity +
				";receiptRow=0;receiptLineFactor=" +  receiptLineFactor + ";InvProperty_icRecLine=" + icRecLine + ";InvProperty_icRc=" + icRc;

		myHtml = "<input type=hidden name=\"InvItem_itemNumber\" value=\"" + itemNumber + "\">";
		myHtml = myHtml + "<input type=hidden name=\"InvBinLocation_itemNumber\" value=\"" + itemNumber + "\">";
		myHtml = myHtml + "<input type=hidden name=\"InvBinLocation_itemLocation\" value=\"" + itemLocation + "\">";
		myHtml = myHtml + "<input type=hidden name=\"InvLocation_itemNumber\" value=\"" + itemNumber + "\">";
		myHtml = myHtml + "<input type=hidden name=\"qtyReceived\" value=\"" + qtyReceived + "\">";
		myHtml = myHtml + "<input type=hidden name=\"duomQtyReceived\" value=\"" + duomQtyReceived + "\">";
		myHtml = myHtml + "<input type=hidden name=\"InvBinLocation_tempIc\" value=\"" + window.parent.frm.InvBinLocation_tempIc.value + "\">";
		myHtml = myHtml + "<input type=hidden name=\"InvBinLocation_cost\" value=\"" + itemCost + "\">";
		myHtml = myHtml + "<input type=hidden name=\"PoLine_icPoLine\" value=\"" + icPoLine + "\">";
		myHtml = myHtml + "<input type=hidden name=\"PoLine_umCode\" value=\"" + umCode + "\">";
		myHtml = myHtml + "<input type=hidden name=\"commodity\" value=\"" + commodity + "\">";
		myHtml = myHtml + "<input type=hidden name=\"receiptLineFactor\" value=\"" + receiptLineFactor + "\">";
		myHtml = myHtml + "<input type=hidden name=\"InvProperty_icRecLine\" value=\"" + icRecLine + "\">";
		myHtml = myHtml + "<input type=hidden name=\"ReceiptLine_icRecLine\" value=\"" + icRecLine + "\">";
		myHtml = myHtml + "<input type=hidden name=\"InvProperty_icRc\" value=\"" + icRc + "\">";
		myHtml = myHtml + "<input type=hidden name=\"InvBinLocation_icRc\" value=\"" + icRc + "\">";

		document.getElementById("requestParameters").innerHTML = myHtml;
		doSubmit('receipts/rec_property.jsp', 'InvPropertyRetrieveByIcRecLine');
	}


//-->
</SCRIPT>
</HTML>