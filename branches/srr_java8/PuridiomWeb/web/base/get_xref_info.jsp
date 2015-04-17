<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>

<%
	String s_row = request.getParameter("as_row");

	if (s_row == null)
	{
		s_row = "";
	}

	List xrefComboList = (List) request.getAttribute("xrefComboList");

	String code4 = "";

	if (xrefComboList != null && xrefComboList.size() > 0)
	{
		XrefCombo xrefCombo = (XrefCombo)xrefComboList.get(0);
		code4 = xrefCombo.getCode4();
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
		if (parent.frm.Account_fld4[<%=s_row%>])
		{
			parent.frm.Account_fld4[<%=s_row%>].value = "<%=code4%>";
		}
		if (parent.frm.Account_fld2[<%=s_row%>] && browser != "NS6")
		{
			parent.frm.Account_fld2[<%=s_row%>].fireEvent("onfocus");
		}
	}
	else
	{
		if (parent.frm.Account_fld4)
		{
			parent.frm.Account_fld4.value = "<%=code4%>";
		}
		if (parent.frm.Account_fld2 && browser != "NS6")
		{
			parent.frm.Account_fld2.fireEvent("onfocus");
		}
	}

	parent.hideArea("getInfoFrame");

// end hiding contents -->
</SCRIPT>
</BODY>
</HTML>
