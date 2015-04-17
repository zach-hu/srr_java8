<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.utility.HiltonUtility" %>
<%@ page import="com.tsa.puridiom.entity.PoLine" %>
<%
	PoLine poLine = (PoLine) request.getAttribute("poLine");
	String	lookupStatus = HiltonUtility.ckNull((String) request.getAttribute("lookupStatus"));
	String	shipToInv = HiltonUtility.ckNull((String) request.getAttribute("shipToInv"));
	String currentRow = (String) request.getAttribute("currentRow");
	boolean itemFound = true;

	if (poLine == null) {
		poLine = new PoLine();
		itemFound = false;
	}
	if (!lookupStatus.equals("FOUND")) {
		itemFound = false;
	}
%>
<html>
<head></head>

<body>

<script language="JavaScript1.2">
<!--  hide script from old browsers

	var row = <%=currentRow%>;
	var mainWindow;

	if (parent != undefined && parent.document.all("getInfoFrame") != undefined) {
		mainWindow = parent;
	} else {
		mainWindow = opener;
	}
<%	if (itemFound) {%>
	if (mainWindow.frm.PoLine_umCode.length > 1) {
		mainWindow.frm.PoLine_umCode[row].value = "<%=poLine.getUmCode()%>";
	} else {
		mainWindow.frm.PoLine_umCode.value = "<%=poLine.getUmCode()%>";
	}
	if (mainWindow.frm.PoLine_umFactor.length > 1) {
		mainWindow.frm.PoLine_umFactor[row].value = "<%=poLine.getUmFactor()%>";
	} else {
		mainWindow.frm.PoLine_umFactor.value = "<%=poLine.getUmFactor()%>";
	}
//	mainWindow.frm.PoLine_itemNumber[row].value = "<%=poLine.getItemNumber()%>";
	if (mainWindow.frm.PoLine_altItemNumber) {
		if (mainWindow.frm.PoLine_altItemNumber.length > 1) {
			mainWindow.frm.PoLine_altItemNumber[row].value = "<%=poLine.getItemNumber()%>";
		} else {
			mainWindow.frm.PoLine_altItemNumber.value = "<%=poLine.getItemNumber()%>";
		}
	}
	if (mainWindow.frm.PoLine_description.length > 1) {
		mainWindow.frm.PoLine_description[row].value = "<%=poLine.getDescription()%>";
	} else {
		mainWindow.frm.PoLine_description.value = "<%=poLine.getDescription()%>";
	}
	if (mainWindow.frm.PoLine_unitPrice.length > 1) {
		mainWindow.frm.PoLine_unitPrice[row].value = "<%=poLine.getUnitPrice()%>";
	} else {
		mainWindow.frm.PoLine_unitPrice.value = "<%=poLine.getUnitPrice()%>";
	}
	if (mainWindow.frm.PoLine_itemSource.length > 1) {
		mainWindow.frm.PoLine_itemSource[row].value = "<%=poLine.getItemSource()%>";
	} else {
		mainWindow.frm.PoLine_itemSource.value = "<%=poLine.getItemSource()%>";
	}
	if (mainWindow.frm.PoLine_itemLocation.length > 1) {
		mainWindow.frm.PoLine_itemLocation[row].value = "<%=poLine.getItemLocation()%>";
	} else {
		mainWindow.frm.PoLine_itemLocation.value = "<%=poLine.getItemLocation()%>";
	}
	if (mainWindow.frm.PoLine_asset.length > 1) {
		mainWindow.frm.PoLine_asset[row].value = "<%=poLine.getAsset()%>";
	} else {
		mainWindow.frm.PoLine_asset.value = "<%=poLine.getAsset()%>";
	}
	if (mainWindow.frm.PoLine_receiptRequired.length > 1) {
		mainWindow.frm.PoLine_receiptRequired[row].value = "<%=poLine.getReceiptRequired()%>";
	} else {
		mainWindow.frm.PoLine_receiptRequired.value = "<%=poLine.getReceiptRequired()%>";
	}
	if (mainWindow.frm.PoLine_icPoLine.length > 1) {
		mainWindow.frm.PoLine_icPoLine[row].value = "<%=poLine.getIcPoLine()%>";
	} else {
		mainWindow.frm.PoLine_icPoLine.value = "<%=poLine.getIcPoLine()%>";
	}
	if (mainWindow.frm.PoLine_udf1Code.length > 1) {
		mainWindow.frm.PoLine_udf1Code[row].value = "<%=poLine.getUdf1Code()%>";
	} else {
		mainWindow.frm.PoLine_udf1Code.value = "<%=poLine.getUdf1Code()%>";
	}
	if (mainWindow.frm.PoLine_udf2Code.length > 1) {
		mainWindow.frm.PoLine_udf2Code[row].value = "<%=poLine.getUdf2Code()%>";
	} else {
		mainWindow.frm.PoLine_udf2Code.value = "<%=poLine.getUdf2Code()%>";
	}
	if (mainWindow.frm.PoLine_udf3Code.length > 1) {
		mainWindow.frm.PoLine_udf3Code[row].value = "<%=poLine.getUdf3Code()%>";
	} else {
		mainWindow.frm.PoLine_udf3Code.value = "<%=poLine.getUdf3Code()%>";
	}
	if (mainWindow.frm.PoLine_udf4Code.length > 1) {
		mainWindow.frm.PoLine_udf4Code[row].value = "<%=poLine.getUdf4Code()%>";
	} else {
		mainWindow.frm.PoLine_udf4Code.value = "<%=poLine.getUdf4Code()%>";
	}
//	mainWindow.frm.PoLine_udf5Code[row].value = "<%=poLine.getUdf5Code()%>";
//	mainWindow.frm.PoLine_catalogId[row].value = "<%=poLine.getCatalogId()%>";
	if (mainWindow.frm.ReceiptLine_qtyReceived.length > 1) {
		mainWindow.frm.ReceiptLine_qtyReceived[row].focus();
	} else {
		mainWindow.frm.ReceiptLine_qtyReceived.focus();
	}
	closeMe();
<%	} else if (!itemFound && shipToInv.equals("Y") && oid.equalsIgnoreCase("VSE06P")) {%>
	if (mainWindow.frm.PoLine_altItemNumber) {
		if (mainWindow.frm.PoLine_altItemNumber.length > 1) {
			mainWindow.frm.PoLine_altItemNumber[row].value = "";
		} else {
			mainWindow.frm.PoLine_altItemNumber.value = "";
		}
	}
	// Lookup Alternate Part Numbers
	mainWindow.altItemLookup(row);
	closeMe();
<%	} else {%>
	closeMe();
<%	}%>

	function closeMe() {
		if (parent != undefined && parent.document.all("getInfoFrame") != undefined) {
			parent.hideArea("getInfoFrame");
		} else {
			self.close();
		}
	}
// end hiding contents -->
</script>

</body>

</html>