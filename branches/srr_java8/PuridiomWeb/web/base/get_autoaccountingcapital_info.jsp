<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%

	List xrefComboList = (List) request.getAttribute("xrefComboList");
	XrefCombo xrefCombo = null;

	if (xrefComboList != null && xrefComboList.size() > 0)
	{
		xrefCombo = (XrefCombo)xrefComboList.get(0);
	}

	String	account_fld5 = "";
	String	account_fld6 = "";

	if (xrefCombo != null) {
		account_fld5 = xrefCombo.getCode2();
		account_fld6 = xrefCombo.getCode4();
	}

%>
<HTML>
<HEAD>
</HEAD>

<BODY>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	if( isArray(parent.frm.Account_fld5) && isArray(parent.frm.Account_fld6))
	{
		parent.frm.Account_fld5[parent.currentRow].value = '<%=account_fld5%>';
		parent.frm.Account_fld6[parent.currentRow].value = '<%=account_fld6%>';
	}
	else
	{
		parent.frm.Account_fld5.value = '<%=account_fld5%>';
		parent.frm.Account_fld6.value = '<%=account_fld6%>';
	}

	parent.hideArea("getInfoFrame");

// end hiding contents -->
</SCRIPT>
</BODY>
</HTML>

<%@ include file="/system/prevent_caching.jsp" %>