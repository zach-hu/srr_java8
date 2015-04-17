<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>

<%
	String	s_row = request.getParameter("as_row");

	if (s_row == null) {	s_row = "";	}

	AccountQxref accountQxref = (AccountQxref) request.getAttribute("accountQxref");

	String	s_wellFacility = "";
	String	s_costCenter = "";

	if (accountQxref == null)
	{
		accountQxref = new AccountQxref();
		AccountQxrefPK accountQxrefPk = new AccountQxrefPK();
		accountQxrefPk.setLocation("");
		accountQxrefPk.setAfe("");
		accountQxref.setComp_id(accountQxrefPk);
	}

	if (accountQxref != null)
	{
		s_wellFacility = accountQxref.getWellFacility();
		s_costCenter = accountQxref.getCostCenter();
	}

	boolean isFound = false;
	if (!HiltonUtility.isEmpty(s_wellFacility) || !HiltonUtility.isEmpty(s_costCenter))
	{
		isFound = true;
	}

%>
<HTML>
<HEAD>
</HEAD>

<BODY>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	var rowCount = parent.maxRows;

	if (rowCount > 1)
	{
		parent.frm.Account_fld4[<%=s_row%>].value = "<%=s_wellFacility%>";
		parent.frm.Account_fld5[<%=s_row%>].value = "<%=s_costCenter%>";
<%	if (isFound) {	%>
			parent.frm.Account_fld4[<%=s_row%>].disabled = "true";
			parent.frm.Account_fld5[<%=s_row%>].disabled = "true";
<%	}	%>
		if (browser != "NS6")
		{
			parent.frm.Account_fld3[<%=s_row%>].fireEvent("onfocus");
		}
	}
	else
	{
		parent.frm.Account_fld4.value = "<%=s_wellFacility%>";
		parent.frm.Account_fld5.value = "<%=s_costCenter%>";
<%	if (isFound) {	%>
			parent.frm.Account_fld4.disabled = "true";
			parent.frm.Account_fld5.disabled = "true";
<%	}	%>
		if (browser != "NS6")
		{
			parent.frm.Account_fld3.fireEvent("onfocus");
		}
	}

	parent.hideArea("getInfoFrame");

// end hiding contents -->
</SCRIPT>
</BODY>
</HTML>
