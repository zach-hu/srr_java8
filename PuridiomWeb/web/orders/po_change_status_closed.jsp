<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<%@ page import="com.tsa.puridiom.common.documents.ReferenceInfo" %>
<%@ page import="com.tsa.puridiom.po.tasks.PoBlanketInfo" %>
<%@ page import="com.tsa.puridiom.jasperreports.ReportUtils" %>
<%@ page import="com.tsa.puridiom.common.documents.ScheduleType"%>
<%@ page import="com.tsagate.foundation.utility.Utility"%>
<%@ page import="com.tsa.puridiom.stdtable.tasks.StdTableManager" %>
<%
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	BigDecimal	bd_ic_po_header = poHeader.getIcPoHeader();
	String	s_requisitioner_code = poHeader.getRequisitionerCode();
	String  oldStatus = (String) request.getAttribute("OldStatus");
	String	s_po_type = poHeader.getPoType();
	BigDecimal	bd_zero = new BigDecimal(0);
	BigDecimal	bd_release_number = poHeader.getReleaseNumber();
	BigDecimal	bd_revision_number = poHeader.getRevisionNumber();
	String	s_po_number = poHeader.getPoNumber();
	String	s_po_status = poHeader.getStatus();
	String	s_fiscal_year = poHeader.getFiscalYear();
	changeRequisitioner = "Y";
%>

<tsa:hidden name="PoHeader_icPoHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="PoHeader_status" value="<%=oldStatus%>"/>
<tsa:hidden name="Reset_status" value="Y"/>
<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Reset PO Closed Status</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign="bottom"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign="bottom" align="right" height="30px">
		<%@ include file="/orders/po_display_number.jsp" %>
	</td>
</tr>
</table>
<br>
<br>
<table width=680px cellpadding=0 cellspacing=0 border=0>
	<tr>
		<td align="right" nowrap>Reset to Status:&nbsp;</td>
		<td nowrap><input type="text" name="poStatusChanged" tabindex=29 size=40 maxlength=40 value="<%=DocumentStatus.toString(oldStatus, oid)%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td align="right" nowrap>Reason for Change:&nbsp;</td>
		<td nowrap><input type="text" name="historyreason" size=100 maxlength=100 value=""></td>
	</tr>
</table>
<br>
<table width=680px cellpadding=0 cellspacing=0 border=0>
	<tr>
		<td align=center><a href="javascript: save(); void(0);"><img src="<%=contextPath%>/images/button_save.gif" border=0 alt="save" class=button></a></td>
	    <td align=center><a href="javascript: returnMe(); void(0);"><img src="<%=contextPath%>/images/button_close.gif" border=0 alt="close" class=button></a></td>
	</tr>
</table>


<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	setNavCookie("/orders/po_review.jsp", "PoRetrieve", "Reset PO Closed", false);
	function save()
	{
		doSubmit('orders/po_review.jsp', 'PoHeaderUpdateStatus;PoRetrieve');
	}
	function returnMe()
	{
		doSubmit('orders/po_review.jsp', 'PoRetrieve');
	}


// End Hide script -->
</SCRIPT>
