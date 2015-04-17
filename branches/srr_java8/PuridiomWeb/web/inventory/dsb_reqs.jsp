<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.util.List.*" %>
<%@ page import="java.math.*" %>

<form name="purchaseform" target="_parent" action="/procure" method="POST">

<br>

<table border=0 cellpadding=0 cellspacing=0 width=800px>
<tr>
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=800px>
		<tr>
			<td width=100% align=center valign=top>
<%
	String s_requisitionType = (String) request.getAttribute("RequisitionHeader_requisitionType");
	String s_reqType = "";
	if (s_requisitionType.equals("S")){
		s_reqType = "Supply";
	}
	else if (s_requisitionType.equals("T")){
		s_reqType = "Transfer";
	}
%>
				<table id=requestTable border=1 cellspacing=0 cellpadding=0 width=800px class=browseHdr>
				<tr>
					<td class=browseHdr height=18px nowrap>&nbsp;Create from <%=s_reqType%> Request:</td>
				</tr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr>
							<td width=15% align=left><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitionNumber", "Requisition #")%></b></td>
							<td width=15% align=center><b>Req Date</b></td>
							<td width=15% align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%></b></td>
							<td width=15% align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitioner", "Requisitioner")%></b></td>
							<td width=15% align=center><b>Priority</b></td>
							<td width=15% align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "fiscalYear", "Fiscal Year")%></b></td>
						</tr>
						</table>
						<tsa:hidden name="RequisitionHeader_icReqHeader" value=""/>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%
	List reqList = (List) request.getAttribute("requisitionHeaderList");
	for(int i=0;i<reqList.size();i++){
		RequisitionHeader requisitionHeader = (RequisitionHeader) reqList.get(i);
		BigDecimal s_req_icheader = requisitionHeader.getIcReqHeader();
		String s_requisition_number = requisitionHeader.getRequisitionNumber();
		String s_req_status = requisitionHeader.getStatus();
		if (s_req_status==null){s_req_status = "";}
		String s_requisitioner_code = requisitionHeader.getRequisitionerCode();
		if (s_requisitioner_code==null){s_requisitioner_code = "";}
		Date d_request_date = requisitionHeader.getRequisitionDate();
		if (d_request_date==null){d_request_date = new Date("");}
		String s_fiscal_year = requisitionHeader.getFiscalYear();
		if (s_fiscal_year==null){s_fiscal_year = "";}
		String s_priority_code = requisitionHeader.getPriorityCode();
		if (s_priority_code==null){s_priority_code = "";}
%>
						<tr>
							<td width=15% align=left class=browseRow nowrap><a href="javascript: createDisb(<%=i%>);void(0);"><%=headerEncoder.encodeForHTML(s_requisition_number)%></a><tsa:hidden id="icReqNumber_<%=i%>" name="icReqNumber_<%=i%>" value="<%=s_req_icheader%>"/></td>
							<td width=15% align=center><%=d_request_date%></td>
							<td width=15% align=center><%=s_req_status%></td>
							<td width=15% align=center nowrap><%=s_requisitioner_code%></td>
							<td width=15% align=center nowrap><%=s_priority_code%></td>
							<td width=15% align=center nowrap><%=s_fiscal_year%></td>
						</tr>
<% } %>
						</table>
					</td>
				</tr>
				</table>
			</td>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=100% align=center><a href="javascript: doSubmit('/inventory/open_item.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0></a></td>
</tr>
</table>
</td>
</tr>
</table>

<br>

</FORM>
<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function createDisb(row) {
		var num = document.getElementById("icReqNumber_" + row);
		frm.RequisitionHeader_icReqHeader.value = num.value;
		doSubmit('/inventory/dsb_summary.jsp','DisbCreate');
	}

	function highlightItem(row) {
		var myRow;

		if (row == 0) {
			myRow = document.all.itemRow.rows(row);
		}
		else {
			myRow = document.all.itemRows.rows(row - 1);
		}

		setRowClassName(myRow, "selectedRow");
	}

	function removeItemHighlight(row) {
		var myRow;

		if (row == 0) {
			myRow = document.all.itemRow.rows(row);
		}
		else {
			myRow = document.all.itemRows.rows(row - 1);
		}

		setRowClassName(myRow, "browseRow");
	}


// End Hide script -->
</SCRIPT>