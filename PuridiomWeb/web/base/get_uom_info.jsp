<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.math.*" %>
<%@ page import="java.util.*" %>

<%
	List	uomList = (List) request.getAttribute("retrieve-allUnitOfMeasure");
	String	s_prefix = request.getParameter("as_prefix");
	String	s_row = request.getParameter("as_row");
	int i_row = -1;
	String	s_uom_code = "";
	BigDecimal	bd_uom_factor = new BigDecimal(0);
	int	i_rowCnt = 0;

	try {
		i_row = Integer.valueOf(s_row).intValue();
	} catch (Exception e) {
		i_row = -1;
	}

	if (uomList != null)
	{
		i_rowCnt = uomList.size();
	}
%>
<HTML>
<HEAD>
</HEAD>

<BODY ONUNLOAD="setWindowNull();" BGCOLOR="#CFCFB8int	">
<FORM NAME="uomform" method="POST">
<TABLE BORDER="0">
<TR>
	<TD ALIGN="CENTER">UOM FACTORS</TD>
</TR>
<TR>
	<TD>
<%	for (int ir = 0; ir < i_rowCnt; ir++)
		{
			UnitOfMeasure uom = (UnitOfMeasure) uomList.get(ir);
			s_uom_code = uom.getUmCode();
			bd_uom_factor = uom.getFactor();
%>
		<tsa:hidden name="uom_code" value="<%=s_uom_code.trim()%>"/>
		<tsa:hidden name="uom_factor" value="<%=bd_uom_factor%>"/>
<%	} %>
		<tsa:hidden name="uom_code" value=""/>
		<tsa:hidden name="uom_factor" value=""/>
	</TD>
</TR>
</TABLE>
</FORM>

</BODY>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	thisfrm = document.uomform;

	setUomCode("<%=s_prefix%>");

	populateArray();

	function populateArray() {
		var i = 0;
		var a_uom = new Array();

<%	for (int ir = 0; ir < i_rowCnt; ir++)
		{
			UnitOfMeasure uom = (UnitOfMeasure) uomList.get(ir);
			s_uom_code = uom.getUmCode();
			bd_uom_factor = uom.getFactor();
%>
			a_uom[i] = new Array("<%=s_uom_code.trim()%>", "<%=bd_uom_factor%>");
			i++;
<%	} %>
		parent.populateUOM(a_uom);

		setWindowNull();
		parent.hideArea("getInfoFrame");
	}

	function setUomCode(prefix)
	{
		var row = <%=i_row%>;

		if (prefix == "RequisitionLine_")
		{
			var code = (parent.frm.RequisitionLine_umCode.value).toUpperCase();
			var test = "";
			var factor = "1";

			for (var i = 0; i < <%=i_rowCnt%>; i++)
			{
				test = (thisfrm.uom_code[i].value).toUpperCase();
				if (code == test)
				{
					factor = thisfrm.uom_factor[i].value;
					break;
				}
			}
			parent.frm.RequisitionLine_umFactor.value = factor;
			parent.addUp();
		}
		else if (prefix == "PoLine_" && row > -1)
		{
			var code = "";
			var test = "";
			var factor = "1";

			if (parent.frm.PoLine_umCode && parent.frm.PoLine_umCode.length > 1)
				code = (parent.frm.PoLine_umCode[row].value).toUpperCase();
			else if (parent.frm.PoLine_umCode)
				code = (parent.frm.PoLine_umCode.value).toUpperCase();
			var test = "";
			var factor = "1";

			for (var i = 0; i < <%=i_rowCnt%>; i++)
			{
				test = (thisfrm.uom_code[i].value).toUpperCase();
				if (code == test) {
					factor = thisfrm.uom_factor[i].value;
					break;
				}
			}
			if (parent.frm.PoLine_umFactor && parent.frm.PoLine_umFactor.length > 1)
				parent.frm.PoLine_umFactor[row].value = factor;
			else if (parent.frm.PoLine_umFactor)
				parent.frm.PoLine_umFactor.value = factor;
		}
		else if (prefix == "PoLine_")
		{
			var code = (parent.frm.PoLine_umCode.value).toUpperCase();
			var test = "";
			var factor = "1";

			for (var i = 0; i < <%=i_rowCnt%>; i++)
			{
				test = (thisfrm.uom_code[i].value).toUpperCase();
				if (code == test) {
					factor = thisfrm.uom_factor[i].value;
					break;
				}
			}
			parent.frm.PoLine_umFactor.value = factor;
			parent.addUp();
		}
		else if (prefix == "RfqLine_")
		{
			var code = (parent.frm.RfqLine_umCode.value).toUpperCase();
			var test = "";
			var factor = "1";

			for (var i = 0; i < <%=i_rowCnt%>; i++)
			{
				test = (thisfrm.uom_code[i].value).toUpperCase();
				if (code == test) {
					factor = thisfrm.uom_factor[i].value;
					break;
				}
			}
			parent.frm.RfqLine_umFactor.value = factor;
		}
		else if (prefix == "InvoiceLine_")
		{
			var code = "";
			var test = "";
			var factor = "1";

			if (parent.frm.InvoiceLine_umCode[row])
			{
				code = (parent.frm.InvoiceLine_umCode[row].value).toUpperCase();
			}
			else
			{
				code = (parent.frm.InvoiceLine_umCode.value).toUpperCase();
			}

			for (var i = 0; i < <%=i_rowCnt%>; i++)
			{
				test = (thisfrm.uom_code[i].value).toUpperCase();
				if (code == test)
				{
					factor = thisfrm.uom_factor[i].value;
					break;
				}
			}
			if (parent.frm.InvoiceLine_umFactor[row])
			{
				parent.frm.InvoiceLine_umFactor[row].value = factor;
			}
			else
			{
				parent.frm.InvoiceLine_umFactor.value = factor;
			}
			parent.addUp(row);
		}
		else
		{
			alert("There is no code specified for this process.");
		}
	}


	function setWindowNull()
	{
		if (parent.closed == false) {
			parent.info_window = null;
		}
	}

// end hiding contents -->
</SCRIPT>

</HTML>