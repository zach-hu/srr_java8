<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<%@ page import="com.tsa.puridiom.entity.PoHeader"%>
<%@ page import="java.math.BigDecimal" %>
<%
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");

	BigDecimal	bd_ic_po_header = poHeader.getIcPoHeader();
	String poNumber = poHeader.getPoNumber();
	BigDecimal releaseNumber = HiltonUtility.ckNull(poHeader.getReleaseNumber());
	String revisionDelete = (String) request.getAttribute("revisionDelete");
%>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=bd_ic_po_header%>"/>
<br/>
<br/>
<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" ALIGN="CENTER" height="66%">
	<TR height="20%"><TD align='center'><img src="<%=contextPath%>/images/alert.gif" border=0></TD></TR>
<%if(releaseNumber.compareTo(new BigDecimal(0)) == 0) {%>
 	<TR height="20%"><TD class="error">Order <%=poNumber%> Revision <%=revisionDelete%> was Deleted</TD></TR>
 <%} else { %>
 	<TR height="20%"><TD class="error">Order <%=poNumber%> Release <%=releaseNumber%> Revision <%=revisionDelete%> was Deleted</TD></TR>
 <%} %>
	<TR height="60%"><TD ALIGN="CENTER"><div id="pxbutton"><A HREF="javascript: window.top.hidePopWin(); void(0)">Close</A></div></TD></TR>
</TABLE>

<%//@include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	self.focus();

// end hiding contents -->
</SCRIPT>
