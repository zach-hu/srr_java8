<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.math.*" %>
<%
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	BigDecimal bd_ic_po_header = poHeader.getIcPoHeader();
	String	s_po_number = poHeader.getPoNumber();
	BigDecimal	bd_release_number = poHeader.getReleaseNumber();
	BigDecimal	bd_revision_number = poHeader.getRevisionNumber();
	BigDecimal	bd_zero = new BigDecimal(0);
	String	s_po_type = poHeader.getPoType();
	String	s_status = poHeader.getStatus();

	if (s_po_type == null)
	{
		s_po_type = "Purchase Order ";
	}
	else
	{
		s_po_type = OrderType.toString(s_po_type, oid);
	}

	//List reqList = (List) request.getAttribute

	/*if (s_status_code.equals(DocumentStatus.HISTORY)) {

	} */

%>

<tsa:hidden name="PoHeader_icPoHeader" value="<%=bd_ic_po_header%>"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td align=center>
		<%=s_po_type%>&nbsp;
		<font class=hdr12><%=s_po_number%>&nbsp;
<%	if (bd_revision_number.compareTo(bd_zero) > 0) {  %>
		<%=bd_revision_number%>&nbsp;
<%	}
		if (bd_release_number.compareTo(bd_zero) > 0) {  %>
		<%=bd_release_number%>&nbsp;
<%	} %>
		</font>has been awarded!
	</td>
</tr>
<tr><td><br><br></td></tr>
<tr>
	<td align=center>
		<div id="pxbutton"><a href="javascript: viewOrder(); void(0);">Return</a></div>
	</td>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function checkHiddenMenu()
	{
		hideArea("navTable");
		displayArea("menuBarSpacer");
	}

	function viewOrder() {
<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
		doSubmit('/orders/po_summary.jsp', 'PoRetrieve');
<%	} else {%>
		doSubmit('/orders/po_review.jsp', 'PoRetrieve');
<%	}%>
	}

// End Hide script -->
</SCRIPT>