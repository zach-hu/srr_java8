<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.RfqType" %>
<%@ page import="com.tsa.puridiom.entity.RfqHeader" %>
<%@ page import="com.tsa.puridiom.entity.VendorQuestion" %>
<%@ page import="java.math.BigDecimal" %>
<%
	List	questionList = (List) request.getAttribute("vendorQuestionList");
	int i_qstrows = 0;

	if (questionList != null) {
		i_qstrows = questionList.size();
	}
	String rfqType = (String) request.getAttribute("rfqType");
	String rfqNumber = (String) request.getAttribute("rfqNumber");
	String rfqAmendment = (String) request.getAttribute("rfqAmendment");
%>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Q & A Log for <%=RfqType.toString(rfqType, oid)%> # <%=rfqNumber%></td></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right height=30px>
		<%// include file="/rfq/rfq_status_title.jsp" %>
		<table cellpadding="0" cellspacing="0" border=0>
		<tr>
			<td width=1000px height=1px class=lightShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 width=680px valign=top>
<tr>
	<td valign=top align=center>
		<%@ include file="/supplierportal/rfq/rfq_qa_list.jsp" %>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr><td width=100% align=center><a href="javascript: window.close(); void(0);"><img class=button src="<%=contextPath%>/supplierportal/images/button_close.gif" border=0 alt="Close"></a></td></tr>
</table>

<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

// End Hide script -->
</SCRIPT>