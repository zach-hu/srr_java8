<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	String	s_code	= request.getParameter("as_code");
	String	s_next  = request.getParameter("as_next");
	String	s_value = "";
	String	s_number = "";
	String	buyer_code = "";
	String	buyer_name = "";
	int	i_number = 0;
	boolean	b_get_info = false;

	if (s_code == null) {	s_code = "";	}
	if (s_next == null) {	s_next = "";	}

	int retval = 1;
	
	UserProfile reqbyUser = UserManager.getInstance().getUser(oid, s_code);
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	
	String udf1 = propertiesManager.getProperty("NAME UDFS","UDF1", "");
	String udf2 = propertiesManager.getProperty("NAME UDFS","UDF2", "");
	String udf3 = propertiesManager.getProperty("NAME UDFS","UDF3", "");
	String udf4 = propertiesManager.getProperty("NAME UDFS","UDF4", "");
	String udf5 = propertiesManager.getProperty("NAME UDFS","UDF5", "");
	String dept = propertiesManager.getProperty("NAME UDFS","DEPT", "");

/*
	InputObj iO = new InputObj();
	iO.setComponentName("REQ");
	iO.setMethodName("jag_get_reqby_dflts");
	iO.setInputValue("requisitioner", s_code);

	OutputObj oO = BeanJag.action(iO);
	retval = oO.getRetval();

	if ( retval == -100 ) {
		response.sendRedirect("/puridiom/xchange/error_pg.jsp?err=invalidJaguarSession");
	}
*/
	String	udfArray[][] = new String[2][8];
	
	udfArray[0][0] = udf1;
	udfArray[1][0] = reqbyUser.getNameUdf1();
	
	udfArray[0][1] = udf2;
	udfArray[1][1] = reqbyUser.getNameUdf2();
	
	udfArray[0][2] = udf3;
	udfArray[1][2] = reqbyUser.getNameUdf3();
	
	udfArray[0][3] = udf4;
	udfArray[1][3] = reqbyUser.getNameUdf4();
	
	udfArray[0][4] = udf5;
	udfArray[1][4] = reqbyUser.getNameUdf5();
	
	udfArray[0][5] = dept;
	udfArray[1][5] = reqbyUser.getDepartment();
	
	udfArray[0][6] = "requisitioner_name";
	udfArray[1][6] = reqbyUser.getDisplayName();
	
	if (udf1.equalsIgnoreCase("BUYER"))
	{
		buyer_code = user.getNameUdf1();
	}
	if (udf2.equalsIgnoreCase("BUYER"))
	{
		buyer_code = user.getNameUdf2();
	}
	if (udf3.equalsIgnoreCase("BUYER"))
	{
		buyer_code = user.getNameUdf3();
	}
	if (udf5.equalsIgnoreCase("BUYER"))
	{
		buyer_code = user.getNameUdf4();
	}
	if (udf5.equalsIgnoreCase("BUYER"))
	{
		buyer_code = user.getNameUdf5();
	}
	
	if (buyer_code.length() > 0)
	{
		UserProfile buyer = UserManager.getInstance().getUser(oid, buyer_code);
		buyer_name = buyer.getDisplayName();
	}
		
	udfArray[0][7] = "buyer_name";
	udfArray[1][7] = buyer_name;

	
/*
	for (int ix = 0; ix < 6; ix++)
	{ 
		String udf = oO.getOutValue( String.valueOf(ix + 1) );
		String name = Form.parseData(udf, "\u0008");

		int iStart = udf.indexOf("\u0008") + 1;
		int iStop = udf.length();

		String value = udf.substring(iStart, iStop);

		udfArray[0][ix] = name;
		udfArray[1][ix] = value;
	}

	udfArray[0][6] = "requisitioner_name";
	udfArray[1][6] = oO.getOutValue("requisitioner_name");

	udfArray[0][7] = "buyer_name";
	udfArray[1][7] = oO.getOutValue("buyer_name");
*/
%>
<HTML>
<HEAD>
</HEAD>

<BODY BGCOLOR="#CFCFB8">
<tsa:label labelName="requisionerDefaultValues" defaultString="REQUISITIONER DEFAULT VALUES" />
</BODY>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	window.parent.focus();
	frm = document.purchaseform;

<%
	for (int i = 0; i < 8; i++) {
		s_value = udfArray[1][i].toUpperCase();

		if (udfArray[0][i].equalsIgnoreCase("ROUTING")) {%>
			window.parent.frm.RequisitionHeader_routing.value = "<%=s_value%>";
<%		}
		else if (udfArray[0][i].equalsIgnoreCase("SHIP_VIA")) {%>
			window.parent.frm.RequisitionHeader_shippingCode.value = "<%=s_value%>";
<%		}
		else if (udfArray[0][i].equalsIgnoreCase("SHIP_TO_ADDR")) {
			b_get_info = true;
%>			window.parent.frm.RequisitionHeader_shipToCode.value = "<%=s_value%>";
<%		}
		else if (udfArray[0][i].equalsIgnoreCase("INV_LOCATION")) {%>
			window.parent.frm.RequisitionHeader_invLocation.value = "<%=s_value%>";
<%		}
		else if (udfArray[0][i].equalsIgnoreCase("PRIORITY")) {%>
			window.parent.frm.RequisitionHeader_priorityCode.value = "<%=s_value%>";
<%		}
		else if (udfArray[0][i].equalsIgnoreCase("TAX_ID")) {%>
			window.parent.frm.RequisitionHeader_taxCode.value = "<%=s_value%>";
<%		}
		else if (udfArray[0][i].equalsIgnoreCase("BUYER")) {%>
			if ( !window.parent.frm.RequisitionHeader_requisitionType.equals("S") ) {
				window.parent.frm.RequisitionHeader_buyer.value = "<%=s_value%>";
			}
<%		}
		else if (udfArray[0][i].indexOf("DATA_UDF") >= 0) {
			s_number = udfArray[0][i].substring(8,udfArray[0][i].length());
			i_number = (Integer.valueOf(s_number)).intValue();
			s_number = String.valueOf(i_number);
%>
			window.parent.frm.RequisitionHeader_udf<%=s_number%>Code.value = "<%=s_value%>";;
<%		}
		else if (udfArray[0][i].equalsIgnoreCase("REQUISITIONER_NAME")) {%>
			window.parent.frm.as_requisitionerName.value = "<%=s_value%>";
<%		}
		else if (udfArray[0][i].equalsIgnoreCase("BUYER_NAME")) {%>
			if ( window.parent.frm.RequisitionHeader_requisitionType != "S") {
			window.parent.frm.as_buyerName.value = "<%=s_value%>";
			}
<%		}
	}

	if ( s_next.length() > 0 ) {%>
		if ( window.parent.frm.<%=s_next%> ) {
			if ( window.parent.frm.<%=s_next%>.type != 'hidden') {
				window.parent.frm.<%=s_next%>.focus();
			}
		}
<%	}

	if (b_get_info) {%>
		window.parent.getNewInfo('ship_to', window.parent.frm.RequisitionHeader_shipToCode);
<%	}
	else {%>
		window.top.hidePopWin();
<%	}%>


// end hiding contents -->
</SCRIPT>

</HTML>