<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%//@ page import="java.util.*" %>
<%
	String	s_type = request.getParameter("as_type");
	String	s_next  = request.getParameter("as_next");
	String	s_row = request.getParameter("as_row");
	String	s_pcard = "";

	if (s_type == null) {	s_type = "";	}
	if (s_next == null) {	s_next = "";	}
	if (s_row == null) {	s_row = "";	}

	Address address = (Address) request.getAttribute("address");

	String	s_address_line_1 = "";
	String	s_address_line_2 = "";
	String	s_address_line_3 = "";
	String	s_address_line_4 = "";
	String	s_city = "";
	String	s_state = "";
	String	s_postal_code = "";
	String	s_country = "";
	String	s_inventory = "";

	if (address != null)
	{
		s_address_line_1 = address.getAddressLine1();
		s_address_line_2 = address.getAddressLine2();
		s_address_line_3 = address.getAddressLine3();
		s_address_line_4 = address.getAddressLine4();
		s_city = address.getCity();
		s_state = address.getState();
		s_postal_code = address.getPostalCode();
		s_country = address.getCountry();
		s_inventory = address.getInventory();
	}

	if (s_address_line_1 == null)		{	s_address_line_1 = "";		}
	if (s_address_line_2 == null)		{	s_address_line_2 = "";		}
	if (s_address_line_3 == null)		{	s_address_line_3 = "";		}
	if (s_address_line_4 == null)		{	s_address_line_4 = "";		}
	if (s_city == null)					{	s_city = "";						}
	if (s_state == null)					{	s_state = "";					}
	if (s_postal_code == null)			{	s_postal_code = "";			}
	if (s_country == null)				{	s_country = "";				}

/*
	if (retval == 1)
	{
		s_pcard = oO.getOutValue("pcard_code");
	}
*/
%>
<HTML>
<HEAD>
</HEAD>

<BODY>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	var rowCount;

<%	if (s_type.equalsIgnoreCase("ShipTo_shipToCode")) {%>
		parent.setDate();
		parent.setQty(<%=s_row%>);
		rowCount = parent.maxRows;

		if (rowCount > 1)
		{
			parent.frm.Address_addressLine1[<%=s_row%>].value = "<%=s_address_line_1%>";
			parent.frm.ShipTo_attention[<%=s_row%>].value = "";
			parent.frm.ShipTo_quantity[<%=s_row%>].select();
			parent.frm.ShipTo_quantity[<%=s_row%>].focus();
			if (browser != "NS6") {
				parent.frm.ShipTo_quantity[<%=s_row%>].fireEvent("onfocus");
			}
		}
		else
		{
			parent.frm.Address_addressLine1.value = "<%=s_address_line_1%>";
			parent.frm.ShipTo_attention.value = "";
			parent.frm.ShipTo_quantity.select();
			parent.frm.ShipTo_quantity.focus();
			if (browser != "NS6") {
				parent.frm.ShipTo_quantity.fireEvent("onfocus");
			}
		}
<%	}
	else if (s_type.equalsIgnoreCase("receiptShipTo")) {%>
		parent.frm.asShipToAddress_addressLine1.value = "<%=s_address_line_1%>";
		parent.frm.asShipToAddress_addressLine2.value = "<%=s_address_line_2%>";
		parent.frm.asShipToAddress_addressLine3.value = "<%=s_address_line_3%>";
		parent.frm.asShipToAddress_addressLine4.value = "<%=s_address_line_4%>";
		parent.frm.asShipToAddress_city.value = "<%=s_city%>";
		parent.frm.asShipToAddress_state.value = "<%=s_state%>";
		parent.frm.asShipToAddress_postalCode.value = "<%=s_postal_code%>";
		parent.frm.asShipToAddress_country.value = "<%=s_country%>";
		parent.frm.ReceiptHeader_shipToInv.value = "<%=s_inventory%>";
		parent.frm.PoHeader_shipToInv.value = "<%=s_inventory%>";
<%	}
	else if (s_type.equalsIgnoreCase("shipTo") || s_type.equalsIgnoreCase("billTo")) {%>
		parent.frm.Address_addressLine1.value = "<%=s_address_line_1%>";
		parent.frm.Address_addressLine2.value = "<%=s_address_line_2%>";
		parent.frm.Address_addressLine3.value = "<%=s_address_line_3%>";
		parent.frm.Address_addressLine4.value = "<%=s_address_line_4%>";
		parent.frm.Address_city.value = "<%=s_city%>";
		parent.frm.Address_state.value = "<%=s_state%>";
		parent.frm.Address_postalCode.value = "<%=s_postal_code%>";
		parent.frm.Address_country.value = "<%=s_country%>";
		if (parent.frm.PoHeader_shipToInv != undefined) {
			parent.frm.PoHeader_shipToInv.value = "<%=s_inventory%>";
		}
<%	}
	else if (s_type.equalsIgnoreCase("BillTo_billToCode")) {%>
		parent.setQty(<%=s_row%>);

		var rowCount = parent.frm.elements.item("Address_addressLine1");
		if (rowCount != undefined && rowCount.length > 1)
		{
			parent.frm.Address_addressLine1[<%=s_row%>].value = "<%=s_address_line_1%>";
			parent.frm.BillTo_attention[<%=s_row%>].value = "";
			parent.frm.BillTo_quantity[<%=s_row%>].select();
			parent.frm.BillTo_quantity[<%=s_row%>].focus();
			if (browser != "NS6") {
				parent.frm.BillTo_quantity[<%=s_row%>].fireEvent("onfocus");
			}
		}
		else
		{
			parent.frm.Address_addressLine1.value = "<%=s_address_line_1%>";
			parent.frm.BillTo_attention.value = "";
			parent.frm.BillTo_quantity.select();
			parent.frm.BillTo_quantity.focus();
			if (browser != "NS6") {
				parent.frm.BillTo_quantity.fireEvent("onfocus");
			}
		}
<%	}
	else if (s_type.equalsIgnoreCase("vendor")) {%>
		parent.frm.Address_addressLine1.value = "<%=s_address_line_1%>";
		parent.frm.Address_addressLine2.value = "<%=s_address_line_2%>";
		parent.frm.Address_addressLine3.value = "<%=s_address_line_3%>";
		parent.frm.Address_addressLine4.value = "<%=s_address_line_4%>";
		parent.frm.Address_city.value = "<%=s_city%>";
		parent.frm.Address_state.value = "<%=s_state%>";
		parent.frm.Address_postalCode.value = "<%=s_postal_code%>";
		parent.frm.Address_country.value = "<%=s_country%>";
<% } %>

<%	if (s_next.length() > 0) { %>
		if (parent.frm.<%=s_next%>) {
			if ( parent.frm.<%=s_next%>.type != 'hidden') {
				parent.frm.<%=s_next%>.focus();
				if (browser != "NS6") {
					parent.frm.<%=s_next%>.fireEvent("onfocus");
				}
			}
		}
<%	} %>

		parent.hideArea("getInfoFrame");


// end hiding contents -->
</SCRIPT>
</BODY>
</HTML>
