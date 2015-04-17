<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="java.math.*" %>
<%@ page import="java.util.Date.*" %>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=800px>
<tr>
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=800px>
		<tr>
			<td width=100% align=center valign=top>
				<table id=supplierTable border=1 cellspacing=0 cellpadding=0 width=800px class=browseHdr>
				<tr>
					<td class=browseHdr height=18px nowrap>&nbsp;Disbursements Browse</td>
				</tr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr>
							<td width=12% align=left><b>Created From</b></td>
							<td width=13% align=center><b>Disbursement #</b></td>
							<td width=10% align=center><b>Date</b></td>
							<td width=7% align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%></b></td>
							<td width=10% align=center><b>Comments</b></td>
							<td width=10% align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-fiscalYear", "Fiscal Year")%></b></td>
							<td width=10% align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%></b></td>
							<td width=10% align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitioner", "Requisitioner")%></b></td>
							<td width=13% align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitionNumber", "Requisition #")%></b></td>
							<td width=5% align=center><b>Delete</b></td>
						</tr>
						</table>
						<tsa:hidden name="DisbHeader_icDsbHeader" value=""/>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%
	List disbList = (List)request.getAttribute("disbHeader");
	for(int i=0;i<disbList.size();i++)
	{
	DisbHeader disbHeader = (DisbHeader)disbList.get(i);
//	if (s_total==null){s_total = new BigDecimal(0);}
	BigDecimal b_icReqHeader = disbHeader.getIcReqHeader();
	BigDecimal b_icDisbHeader = disbHeader.getIcDsbHeader();
	String s_reqNumber = disbHeader.getRequisitionNumber();
	if (s_reqNumber==null){s_reqNumber = "";}
	String s_status = disbHeader.getStatus();
	String s_disbNumber = disbHeader.getDisbNumber();
	if (s_disbNumber==null){s_disbNumber = "N/A";}
	Date d_disbDate = disbHeader.getDisbDate();
	String s_fiscalYear = disbHeader.getFiscalYear();
	if (s_fiscalYear==null){s_fiscalYear = "";}
	Date d_requiredBy = disbHeader.getDisbDate();
	String s_requiredBy_date = "";
	if (d_requiredBy != null)
	{
		s_requiredBy_date = HiltonUtility.getFormattedDate((d_requiredBy, oid, userDateFormat);
	}
	String s_requisitioner = disbHeader.getRequisitionerCode();
	if (s_requisitioner==null){s_requisitioner = "";}
	String s_itemLocation = disbHeader.getItemLocation();
	if (s_itemLocation==null){s_itemLocation = "";}
	String s_intComments = disbHeader.getInternalComments();
	if (s_intComments==null){s_intComments = "";}
	String s_owner = disbHeader.getOwner();
	if (s_owner==null){s_owner = "";}
	String s_disbType = disbHeader.getDisbType();
	String s_disbTypeString = "";
	if (s_disbType.equals("S")){
		s_disbTypeString = "Supply Req.";
	}
	else if (s_disbType.equals("T")){
		s_disbTypeString = "Transfer Req.";
	}
%>
						<tr>
							<td width=12% align=left><%=s_disbTypeString%></td>
							<td width=13% align=left class=browseRow nowrap><a href="javascript: viewItem(<%=i%>);void(0);"><%=s_disbNumber%></a><tsa:hidden id="dsbNumber_<%=i%>" value="<%=b_icDisbHeader%>"/></td>
							<td width=10% align=center><%=s_requiredBy_date%></td>
							<td width=7% align=center><%=s_status%></td>
							<td width=10% align=center nowrap><%=s_intComments%></td>
							<td width=10% align=center nowrap><%=s_fiscalYear%></td>
							<td width=10% align=center nowrap><%=s_owner%></td>
							<td width=10%% align=center nowrap><%=s_requisitioner%></td>
							<td width=13% align=center nowrap><%=headerEncoder.encodeForHTML(s_reqNumber)%></td>
							<td width=5% align=center><a href="javascript: deleteItem(<%=i%>);void(0);"><IMG SRC="<%=contextPath%>/images/delete.gif" BORDER="0" ALT="Delete Item"></a></td>
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
	<td width=100% align=center><a href="javascript: doSubmit('inventory/inv_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0></a></td>
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


	function viewItem(row) {
		var num = document.getElementById("dsbNumber_" + row);
		frm.DisbHeader_icDsbHeader.value = num.value;
		doSubmit('/inventory/dsb_summary.jsp','DisbursementRetrieveById');
	}

	function deleteItem(row) {
		var num = document.getElementById("dsbNumber_" + row);
		frm.DisbHeader_icDsbHeader.value = num.value;
		if (confirm("Are you sure you want to delete \"" + frm.DisbHeader_icDsbHeader.value  + "\"?")){
			doSubmit('inventory/dsb_summary.jsp','DisbHeaderDelete');
		}
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