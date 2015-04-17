<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DisbursementType" %>
<%
	DisbHeader disbHeader = (DisbHeader) request.getAttribute("disbHeader");
	//BigDecimal bd_ic_dsb_header = disbHeader.getIcDsbHeader();
	String	s_dsb_number = disbHeader.getDisbNumber();
	String	s_dsb_type = disbHeader.getDisbType();
	String s_dsb_title = "Disbursement ";
%>

<tsa:hidden name="DisbHeader_icDsbHeader" value="<%= disbHeader.getIcDsbHeader() %>"/>
<tsa:hidden name="DisbHeader_status" value="<%= disbHeader.getStatus() %>"/>
<tsa:hidden name="DisbHeader_disbType" value="<%= s_dsb_type %>"/>
<tsa:hidden name="DisbHeader_disbNumber" value="<%= s_dsb_number %>"/>
<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td align=center>
		Disbursement <font class=hdr12><%=s_dsb_number%></font> has been Disbursed!
	</td>
</tr>
</table>

<br>
<br>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td align=center>
		<table border=0 cellpadding=2 cellspacing=4>
			<tr>
				<td valign=top>
					<table border=0 cellpadding=2 cellspacing=0>
						<tr><td align=right width=50px><img src="<%=contextPath%>/images/add.gif"></td><td nowrap><a href="javascript: doSubmit('/inventory/dsb_select_type.jsp', 'DoNothing'); void(0);">Create another Disbursement</a></td></tr>
						<tr><td colspan=2 align=center><br></td></tr>
						<tr><td align=right width=50px><img src="<%=contextPath%>/images/print.gif"></td><td nowrap><a href="javascript: printPdf(); void(0);">Print <%= s_dsb_title %> #<%= s_dsb_number %></a></td></tr>
					</table>
				</td>
				<td align=center valign=top>
					<table border=0 cellpadding=2 cellspacing=0>
						<tr><td align=right width=50px><img src="<%=contextPath%>/images/returnto.gif"></td><td nowrap><a href="javascript: doSubmit('/inventory/dsb_review.jsp', 'DisbSetProperty;DisbursementRetrieve'); void(0);">Return to <%= s_dsb_title %> #<%= s_dsb_number %></a></td></tr>
						<tr><td colspan=2 align=center><br></td></tr>
						<tr><td align=right width=50px><img src="<%=contextPath%>/images/pwac_sm.gif"></td><td nowrap><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);">Return to the Procurement Workload Activity Center</a></td></tr>
					</table>
				</td>
			</tr>
		</table>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function printPdf() {
		doSubmit('/inventory/dsb_print_pdf.jsp', 'DoNothing');
	}

// End Hide script -->
</SCRIPT>