<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.util.List.*" %>
<%@ page import="java.math.*" %>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=900px>
<tr>
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=900px>
		<tr>
			<td width=100% align=center valign=top>
				<table id=backorderTable border=1 cellspacing=0 cellpadding=0 width=900px class=browseHdr>
				<tr>
					<td class=browseHdr height=18px nowrap>&nbsp;Backorders</td>
				</tr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr>
							<td align=left><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitionNumber", "Requisition #")%></b></td>
							<td align=center><b>Requistion Date</b></td>
							<td align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%></b></td>
							<td align=center><b>Item Location</b></td>
							<td align=center><b>Item Number</b></td>
							<td align=center><b>Description</b></td>
							<td align=center><b>Unit of Measure</b></td>
							<td align=center><b>Qty Requested</b></td>
							<td align=center><b>Qty Backordered</b></td>
							<td align=center><b>Qty On Hand</b></td>
							<td align=center><b>Commodity</b></td>
							<td align=center><b>Requisitioned By</b></td>
							<td align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "department", "Department")%></b></td>
						</tr>
						</table>
						<tsa:hidden name="InvItem_itemNumber" value=""/>
						<tsa:hidden name="InvLocation_itemNumber" value=""/>
						<tsa:hidden name="RequisitionHeader_icReqHeader" value=""/>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%
	List reqList = (List)request.getAttribute("requisitionHeader");
	if (reqList != null)
	{
		for(int i=0;i<reqList.size();i++)
		{
			RequisitionHeader requisitionHeader = (RequisitionHeader) reqList.get(i);
			BigDecimal s_req_icheader = requisitionHeader.getIcReqHeader();
			String s_requisition_number = requisitionHeader.getRequisitionNumber();
			Date d_request_date = requisitionHeader.getRequisitionDate();
			String s_req_status = requisitionHeader.getStatus();
			String s_item_location = requisitionHeader.getItemLocation();
	/*		List lineList = (List) requisitionHeader.getRequisitionLineList();
			if(lineList != null){
				for(int o=0;o<lineList.size();o++)
				{
					RequisitionLine reqLine = (RequisitionLine) lineList.get(i);
					String s_commodity = reqLine.getCommodityCode();
					String s_description = reqLine.getDescription();
					String s_item_number = reqLine.getItemNumber();
	*/
%>
						<tr>
							<td width=8% align=left class=browseRow nowrap><a href="javascript: viewItem(<%=i%>);void(0);"><%=headerEncoder.encodeForHTML(s_requisition_number)%></a><tsa:hidden id="reqIcNumber_<%=i%>" value="<%=s_req_icheader%>"/></td>
							<td width=8% align=center nowrap><%=d_request_date%></td>
							<td width=5% align=center><%=s_req_status%></td>
							<td align=center nowrap><%=s_item_location%></td>
							<td align=center nowrap><%=headerEncoder.encodeForHTML(s_requisition_number)%></td>
							<td align=center nowrap>&nbsp</td>
							<td align=center nowrap>&nbsp</td>
							<td align=center nowrap>&nbsp</td>
							<td align=center nowrap>&nbsp</td>
							<td align=center nowrap>&nbsp</td>
							<td align=center nowrap>&nbsp</td>
							<td align=center nowrap>&nbsp</td>
							<td align=center nowrap>&nbsp</td>
						</tr>
<%		} //}
	} %>
						</table>
					</td>
				</tr>
				</table>
			</td>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=100% align=center><a href="javascript: doSubmit('inventory/open_backorders.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0></a></td>
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
		var num = document.getElementById("reqIcNumber_" + row);
		frm.RequisitionHeader_icReqHeader.value = num.value;
		alert(frm.RequisitionHeader_icReqHeader.value);
		doSubmit('/inventory/req_disb.jsp','RequisitionRetrieve');
	}

	function deleteItem(row) {
		var num = document.getElementById("invNumber_" + row);
		frm.InvItem_itemNumber.value = num.value;
		if (confirm("Are you sure you want to delete \"" + frm.InvItem_itemNumber.value  + "\"?")){
			doSubmit('inventory/inv_items.jsp','InvItemDelete');
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