<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>

<%
	String	s_row = request.getParameter("as_row");

	if (s_row == null) {	s_row = "";	}

//	DeltekProjSetup accountVseXref = (DeltekProjSetup) request.getAttribute("accountVseXref");

	StdTable accountVseXref = (StdTable) request.getAttribute("accountVseXref");

	String	s_udf3 = "";
	String	s_udf4 = "";
	String	s_udf5 = "";

	if (accountVseXref == null)
	{
//		accountVseXref = new DeltekProjSetup();
		accountVseXref = new StdTable();

	}

	if (accountVseXref != null)
	{
//		s_udf3 = accountVseXref.getPrimeContrId() ;
//		s_udf4 = accountVseXref.getSubctrId() ;
//		s_udf5 = accountVseXref.getDmsDpsCd();
		s_udf3 = accountVseXref.getUdf1Code() ;
		s_udf4 = accountVseXref.getUdf2Code() ;
		s_udf5 = accountVseXref.getUdf3Code() ;

	}

	boolean isFound = false;
	if (!HiltonUtility.isEmpty(s_udf3) || !HiltonUtility.isEmpty(s_udf4) || !HiltonUtility.isEmpty(s_udf5))
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
		parent.frm.Account_fld3[<%=s_row%>].value = "<%=s_udf3%>";
		parent.frm.Account_fld4[<%=s_row%>].value = "<%=s_udf4%>";
		parent.frm.Account_fld5[<%=s_row%>].value = "<%=s_udf5%>";
	}
	else
	{
		parent.frm.Account_fld3.value = "<%=s_udf3%>";
		parent.frm.Account_fld4.value = "<%=s_udf4%>";
		parent.frm.Account_fld5.value = "<%=s_udf5%>";
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
