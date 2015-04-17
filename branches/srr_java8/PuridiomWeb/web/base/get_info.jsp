<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ page import="com.tsa.puridiom.usermanager.*" %>
<%@ page import="com.tsa.puridiom.common.utility.HiltonUtility" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.commodity.CommodityManager" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.departmentbuyer.tasks.DepartmentBuyerManager " %>
<%@ page import="com.tsa.puridiom.xrefcombo.tasks.XrefComboMxpwManager " %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>

<%
	String	oid = (String) request.getAttribute("organizationId");
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String	s_type	= request.getParameter("as_type");
	String	s_code	= request.getParameter("as_code");
	String	s_code2	= HiltonUtility.ckNull(request.getParameter("as_code2"));
	String	s_next  = request.getParameter("as_next");
	String	s_row	= request.getParameter("as_row");
	String	s_fld	= request.getParameter("as_fld");
	String	s_fld2	= HiltonUtility.ckNull(request.getParameter("as_fld2"));
	String	s_value = "";
	String	s_dept	= "";
	String	s_title = "";
	String	s_amount = "";
	String	s_excludeLess = "";
	String	s_approveByLine = "";
	String  s_commodityName = CommodityManager.getInstance().getCommodityDescription(oid, s_code);
	String  s_commodityType = CommodityManager.getInstance().getCommodityType(oid, s_code);
	String  s_commodityTaxable = "";
	Commodity commodity = CommodityManager.getInstance().getCommodityEntity(oid, s_code);
	if (commodity != null) { s_commodityTaxable = commodity.getTaxable(); }
	String  s_departmentBuyer = DepartmentBuyerManager.getInstance().getBuyerId(oid, s_code);
	String	s_departmentBuyerName = UserManager.getInstance().getUser(oid, s_departmentBuyer).getDisplayName();
	String  s_accountFLD2 = XrefComboMxpwManager.getInstance().getCode4(oid, s_code, s_code2);

	if (s_type == null) {	s_type = "";	}
	if (s_code == null) {	s_code = "";	}
	if (s_next == null) {	s_next = "";	}
	if (s_row == null) {	s_row = "";	}
	if (s_fld == null) {	s_fld = "as_sup_contact_name";	}

	if (s_row.equals("undefined"))
	{
		s_row = "0";
	}

	if ( s_type.equals("contact") )
	{
		String	s_supplier = request.getParameter("as_supcode");

		//component = "supplieraddress";
		//method = "getContactName";

		//iO.setInputValue("supplier_code", s_supplier);
		//iO.setInputValue("contact_code", s_code);

		//s_value = oO.getOutValue("contact_name");
	}
	if ( s_type.equals("poolUser") ){
		AppPool userPool = UserManager.getInstance().getUserPool(oid, s_code);
		s_value = userPool.getPooldesc();
	}
	else
	{
		UserProfile user = UserManager.getInstance().getUser(oid, s_code);

		s_value = user.getDisplayName();

		if (s_fld.equals("as_requisitionerName"))
		{
			s_dept = user.getDepartment();
		}

		if (s_fld.equals("as_approverName") || s_fld.indexOf("as_deptAppr") == 0)
		{
			s_title = user.getTitle();
			s_amount = HiltonUtility.getFormattedDollar(user.getApprovalAmount(),oid).toString();
			s_excludeLess = HiltonUtility.getFormattedDollar(user.getExcludeLess(),oid).toString();
			s_approveByLine = user.getApproveByLine();
		}
	}
%>
<html>
<head></head>

<body>

<script language="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	var multipleRows = false;

<%	if (s_fld.equals("as_commodityName")) {%>
	var setReqTaxable = false;
	var setPOTaxable = false;
	var commodityTaxable = <%=s_commodityTaxable.equals("Y")%>;
	<%	if (propertiesManager.getProperty("REQ OPTIONS", "TAXABLEFROMCOMMODITY", "N").equals("Y") && (s_commodityTaxable.equals("Y") || s_commodityTaxable.equals("N"))) {%>
		setReqTaxable = true;
	<%	}
		if (propertiesManager.getProperty("PO OPTIONS", "TAXABLEFROMCOMMODITY", "N").equals("Y") && (s_commodityTaxable.equals("Y") || s_commodityTaxable.equals("N"))) {%>
		setPOTaxable = true;
	<%	}%>			parent.frm.as_commodityName.value = "<%=s_commodityName%>";
			if (setReqTaxable && parent.frm.RequisitionLine_taxable != undefined) {
				parent.frm.c_checkbox[2].checked = commodityTaxable;
				parent.frm.c_checkbox[2].fireEvent("onclick");
			} else if (setPOTaxable && parent.frm.PoLine_taxable != undefined) {
				parent.frm.c_checkbox[1].checked = commodityTaxable;
				parent.frm.c_checkbox[1].fireEvent("onclick");
			}

		<% if (s_fld2.equals("RequisitionLine_udf10Code")) {%>

			    parent.frm.RequisitionLine_udf10Code.value = "<%=s_commodityType%>";
    	<%}
	} else if (s_fld.equals("RequisitionHeader_buyer")) {
		if (!HiltonUtility.isEmpty(s_departmentBuyer)) {%>
		parent.frm.RequisitionHeader_buyer.value = "<%=s_departmentBuyer%>";
<%			if (!HiltonUtility.isEmpty(s_fld2)) {%>
		parent.frm.<%=s_fld2%>.value = "<%=s_departmentBuyerName%>";
<%
			}
		}
	} else {%>
		parent.frm.<%=s_fld%>.value = "<%=s_value%>";
<%	}

	if (s_fld.equals("as_requisitionerName")) {
		if (Integer.valueOf(s_row).intValue() > 0) { %>
			multipleRows = true;
<%	} else {%>
			if (parent.frm.as_requisitionerName[0]) {
				multipleRows = true;
			} else {
				multipleRows = false;
			}
<%	}%>
			if (multipleRows == true) {
				if (parent.frm.elements.item("as_requisitionerName")) {
					parent.frm.as_requisitionerName[<%=s_row%>].value = "<%=s_value%>";
				}
				if (parent.frm.elements.item("RequisitionHeader_departmentCode")) {
					parent.frm.RequisitionHeader_departmentCode[<%=s_row%>].value = "<%=s_dept%>";
				}
				if (parent.frm.elements.item("RequisitionLine_departmentCode")) {
					parent.frm.RequisitionLine_departmentCode[<%=s_row%>].value = "<%=s_dept%>";
				}
				if (parent.frm.elements.item("RfqHeader_departmentCode")) {
					parent.frm.RfqHeader_departmentCode[<%=s_row%>].value = "<%=s_dept%>";
				}
				if (parent.frm.elements.item("PoHeader_departmentCode")) {
					parent.frm.PoHeader_departmentCode[<%=s_row%>].value = "<%=s_dept%>";
				}
				if (parent.frm.elements.item("PoLine_departmentCode")) {
					parent.frm.PoLine_departmentCode[<%=s_row%>].value = "<%=s_dept%>";
				}
			} else {
				if (parent.frm.RequisitionHeader_departmentCode) {
					parent.frm.RequisitionHeader_departmentCode.value = "<%=s_dept%>";
				}
				if (parent.frm.RequisitionLine_departmentCode) {
					parent.frm.RequisitionLine_departmentCode.value = "<%=s_dept%>";
				}
				if (parent.frm.RfqHeader_departmentCode) {
					parent.frm.RfqHeader_departmentCode.value = "<%=s_dept%>";
				}
				if (parent.frm.PoHeader_departmentCode) {
					parent.frm.PoHeader_departmentCode.value = "<%=s_dept%>";
				}
				if (parent.frm.PoLine_departmentCode) {
					parent.frm.PoLine_departmentCode.value = "<%=s_dept%>";
				}
			}
<%	}
		if ( s_next.length() > 0 ) {%>
			if ( parent.frm.<%=s_next%> ) {
				if ( parent.frm.<%=s_next%>.type != 'hidden') {
					parent.frm.<%=s_next%>.focus();
				}
			}
<%	}%>


<%	if (s_fld.equals("as_approverName")) {
			if (Integer.valueOf(s_row).intValue() > 0) { %>
				multipleRows = true;
<%			} else {%>
				if (parent.frm.as_approverName[0]) {
					multipleRows = true;
				} else {
					multipleRows = false;
				}
<%			}%>
			if (multipleRows == true) {
				if (parent.frm.elements.item("as_approverName")) {
					parent.frm.as_approverName[<%=s_row%>].value = "<%=s_value%>";
				}
				if (parent.frm.elements.item("as_title")) {
					parent.frm.as_title[<%=s_row%>].value = "<%=s_title%>";
				}
				if (parent.frm.elements.item("as_approvalAmount")) {
					parent.frm.as_approvalAmount[<%=s_row%>].value = "<%=s_amount%>";
				}
				if (parent.frm.elements("UserProfile_approvalAmount")) {
					parent.frm.UserProfile_approvalAmount[<%=s_row%>].value = "<%=s_amount%>";
					parent.frm.UserProfile_approvalAmount[<%=s_row%>].style.textAlign = "right";
<%		if (!s_approveByLine.equalsIgnoreCase("Y")) {%>
					if (parent.frm.elements("AppRule_amount")) {
						parent.frm.AppRule_amount[<%=s_row%>].value = "<%=s_amount%>";
					}
<%		}%>
				}
				if (parent.frm.elements("UserProfile_excludeLess")) {
					parent.frm.UserProfile_excludeLess[<%=s_row%>].value = "<%=s_excludeLess%>";
					parent.frm.UserProfile_excludeLess[<%=s_row%>].style.textAlign = "right";
<%		if (!s_approveByLine.equalsIgnoreCase("Y")) {%>
					if (parent.frm.elements("AppRule_excludeLess")) {
						parent.frm.AppRule_excludeLess[<%=s_row%>].value = "<%=s_excludeLess%>";
					}
<%		}%>
				}
				if (parent.frm.elements("UserProfile_approveByLine")) {
					parent.frm.UserProfile_approveByLine[<%=s_row%>].value = "<%=s_approveByLine%>";
				}
				if (parent.frm.elements("UserProfile_userId") && parent.frm.elements("AppRule_userId")) {
					parent.frm.UserProfile_userId[<%=s_row%>].value = parent.frm.AppRule_userId[<%=s_row%>].value;
				}
			} else {
				if (parent.frm.as_title) {
					parent.frm.as_title.value = "<%=s_title%>";
				}
				if (parent.frm.as_approvalAmount) {
					parent.frm.as_approvalAmount.value = "<%=s_amount%>";
				}
				if (parent.frm.UserProfile_approvalAmount) {
					parent.frm.UserProfile_approvalAmount.value = "<%=s_amount%>";
					parent.frm.UserProfile_approvalAmount.style.textAlign = "right";
<%		if (!s_approveByLine.equalsIgnoreCase("Y")) {%>
					if (parent.frm.AppRule_amount) {
						parent.frm.AppRule_amount.value = "<%=s_amount%>";
					}
<%		}%>
				}
				if (parent.frm.UserProfile_excludeLess) {
					parent.frm.UserProfile_excludeLess.value = "<%=s_excludeLess%>";
					parent.frm.UserProfile_excludeLess.style.textAlign = "right";
<%		if (!s_approveByLine.equalsIgnoreCase("Y")) {%>
					if (parent.frm.AppRule_excludeLess) {
						parent.frm.AppRule_excludeLess.value = "<%=s_excludeLess%>";
					}
<%		}%>
				}
				if (parent.frm.UserProfile_approveByLine) {
					parent.frm.UserProfile_approveByLine.value = "<%=s_approveByLine%>";
				}
				if (parent.frm.UserProfile_userId && parent.frm.AppRule_userId) {
					parent.frm.UserProfile_userId.value = parent.frm.AppRule_userId.value;
				}
			}
<%	}
		else if (s_fld.equals("as_name")) {
			if (Integer.valueOf(s_row).intValue() > 0) { %>
				multipleRows = true;
<%			} else {%>
				if (parent.frm.as_name[0]) {
					multipleRows = true;
				} else {
					multipleRows = false;
				}
<%			}%>
			if (multipleRows == true) {
				if (parent.frm.elements.item("as_name")) {
					parent.frm.as_name[<%=s_row%>].value = "<%=s_value%>";
				}
				if (parent.frm.elements.item("as_title")) {
					parent.frm.as_title[<%=s_row%>].value = "<%=s_title%>";
				}
			} else {
				if (parent.frm.elements.item("as_name")) {
					parent.frm.as_name.value = "<%=s_value%>";
				}
			}
<%	}%>

<%	if (s_fld.equals("as_buyerName")) {
			if (Integer.valueOf(s_row).intValue() > 0) { %>
				parent.frm.as_buyerName[<%=s_row%>].value = "<%=s_value%>";
<%			}
		}%>

<%	if (s_fld.equals("accountLine")) {
		if (Integer.valueOf(s_row).intValue() > 0) { %>

			var accountString = "";
			var accountFLD2 = "<%= s_accountFLD2%>";

			if( accountFLD2.value != "" )
			{
				if ( parent.document.getElementById("accFld1")!=null &&  parent.document.getElementById("accFld1")!=null &&
					 parent.document.getElementById("accFld1")!=null )
				{
					parent.document.getElementById("accFld2").value = parent.document.getElementById("accountFLD2").value ;
					accountString = parent.document.getElementById("accFld1").value + "/" +
								parent.document.getElementById("accFld2").value + "/" +
								parent.document.getElementById("accFld3").value;

					parent.document.getElementById("accountLine").innerHTML = accountString;
				}
			}

<%		}
	} else if (s_fld.indexOf("as_deptApprv") == 0) {%>
		parent.frm.<%=s_fld2%>.value = "<%=s_amount%>";
<%	}%>

	parent.hideArea("getInfoFrame");

// end hiding contents -->
</script>

</body>

</html>