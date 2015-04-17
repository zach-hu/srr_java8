<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.RfqType" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.math.BigDecimal" %>
<%
	List	questionList = (List) request.getAttribute("vendorQuestionList");;
	int i_qstrows = 0;

	if (questionList != null) {
		i_qstrows = questionList.size();
	}
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Question & Answers</div>
			</td>
		</tr>
		</table>
	</td>
	<td width=30px valign=bottom><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" width="30" height="31" /></td>
	<td valign=bottom align=right height=30px>
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

<table border=0 width=680px valign=top>
<tr>
	<td valign=top align=center>
		<%@ include file="/supplierportal/rfq/rfq_qa_list.jsp" %>
		<br>
		<table border=0 width=100%>
		<tr>
			<td align=center>
				<a href="javascript: window.close(); void(0);"><img tabindex=31 src="<%=contextPath%>/supplierportal/images/button_close.gif" class=button border=0></a>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<!---- JavaScripts for Entry Validation ----->
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

// end hiding contents -->
</SCRIPT>

</body>
</html>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>