<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.math.*" %>

<%
	TaxCode taxCode = (TaxCode) request.getAttribute("taxCode");
	String s_prefix = request.getParameter("formField");
	String s_row = request.getParameter("row");

	if (s_prefix == null)
	{
		s_prefix = "RequisitionHeader_";
	}

	if (s_row == null)
	{
		s_row = "0";
	}

	if (s_prefix.indexOf("RequisitionHeader_") >= 0)
	{
		s_prefix = "RequisitionHeader_";
	}
	else if (s_prefix.indexOf("RequisitionLine_") >= 0)
	{
		s_prefix = "RequisitionLine_";
	}
	else if (s_prefix.indexOf("PoHeader_") >= 0)
	{
		s_prefix = "PoHeader_";
	}
	else if (s_prefix.indexOf("PoLine_") >= 0)
	{
		s_prefix = "PoLine_";
	}
	else
	{
		s_prefix = "RequisitionLine_";
	}

%>
<HTML>
<HEAD>
</HEAD>

<BODY ONUNLOAD="setWindowNull();">

<FORM NAME="taxform" method="POST">
<%	if (taxCode != null) { %>
		<tsa:hidden name="tax_rate" value="<%=Utility.getBigDecimalFormatted(taxCode.getTaxRate(), 2)%>"/>
<%	} else { %>
		<tsa:hidden name="tax_rate" value="0.00"/>
<%	} %>
</FORM>

</BODY>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	setTaxCode("<%=s_prefix%>","<%=s_row%>");

	parent.hideArea("getInfoFrame");

	function setTaxCode(prefix, row)
	{
		if (prefix == "RequisitionHeader_")
		{
			var code = trim(parent.frm.RequisitionHeader_taxCode.value).toUpperCase();
			var test = "";
			var perc = "0.00";

			perc = document.taxform.tax_rate.value;

			parent.frm.RequisitionHeader_taxPercent.value = perc;

			if (parent.rowcount > 1)
			{
				for (var i=0; i < parent.rowcount; i++)
				{
					parent.frm.RequisitionLine_taxCode[i].value = code;
					parent.frm.RequisitionLine_taxPercent[i].value = perc;
				}
			}
			else if (parent.rowcount == 1)
			{
				parent.frm.RequisitionLine_taxCode.value = code;
				parent.frm.RequisitionLine_taxPercent.value = perc;
			}

			parent.changeHeaderAmount(parent.frm.RequisitionHeader_taxPercent);
		}
		else if (prefix == "RequisitionLine_")
		{
			var code = "";
			var test = "";
			var perc = "0.00";

			perc = document.taxform.tax_rate.value;
			parent.frm.RequisitionLine_taxPercent[row].value = perc;

			parent.changeLineAmount(parent.frm.RequisitionLine_taxPercent,row);
		}
		else if (prefix == "PoHeader_")
		{
			var code = trim(parent.frm.PoHeader_taxCode.value).toUpperCase();
			var test = "";
			var perc = "0.00";

			perc = document.taxform.tax_rate.value;

			parent.frm.PoHeader_taxPercent.value = perc;

			if (parent.rowcount > 1)
			{
				for (var i=0; i < parent.rowcount; i++)
				{
					parent.frm.PoLine_taxCode[i].value = code;
					parent.frm.PoLine_taxPercent[i].value = perc;
				}
			}
			else if (parent.rowcount == 1)
			{
				parent.frm.PoLine_taxCode.value = code;
				parent.frm.PoLine_taxPercent.value = perc;
			}

			parent.changeHeaderAmount(parent.frm.PoHeader_taxPercent);
		}
		else if (prefix == "PoLine_")
		{
			var code = "";
			var test = "";
			var perc = "0.00";

			perc = document.taxform.tax_rate.value;
			parent.frm.PoLine_taxPercent[row].value = perc;

			parent.changeLineAmount(parent.frm.PoLine_taxPercent,row);
		}
		//set cursor to hourglass while the system is processing
		parent.document.body.style.cursor = "ready";

		parent.hideArea("getInfoFrame");
	}

	function trim (x) {
		while(''+x.charAt(x.length-1)==' ') {
			x=x.substring(0,x.length-1);
		}
		while(''+x.charAt(0)==' ') {
			x=x.substring(1,x.length);
		}

		return x;
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