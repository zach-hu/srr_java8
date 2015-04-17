<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="java.math.*" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/browse.js"></SCRIPT>

<%
	int linecount = 0;
	List disbList = (List) request.getAttribute("disbLines");
	DisbHeader disbHeader = (DisbHeader)request.getAttribute("disbHeader");
	if (disbList != null)
	{
		linecount = disbList.size();
	}
	List invLocationList = (List)request.getAttribute("invLocationList");
%>

<input type='hidden' name='RequisitionHeader_icReqHeader' value='<%=request.getAttribute("RequisitionHeader_icReqHeader")%>'>
<input type='hidden' name='RequisitionHeader_requisitionType' value='<%=request.getAttribute("RequisitionHeader_requisitionType")%>'>
<input type='hidden' name='preview' value='FALSE'>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Disbursement Items Preview</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Disbursement #:</b></td>
			<td width=100px>N/A</td>
		</tr>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=100px>Preview</td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>


<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table id=itemTable border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
				<tr>
					<td width=10% class=browseHdr nowrap>&nbsp;Line #</td>
					<td width=10% class=browseHdr nowrap>&nbsp;Item/Part #</td>
					<td width=8% class=browseHdr nowrap>&nbsp;Qty on Hand</td>
					<td width=8% class=browseHdr nowrap>&nbsp;Aisle</td>
					<td width=8% class=browseHdr nowrap>&nbsp;Row</td>
					<td width=8% class=browseHdr nowrap>&nbsp;Tier</td>
					<td width=8% class=browseHdr nowrap>&nbsp;Bin</td>
					<td width=10% class=browseHdr nowrap>&nbsp;UI</td>
					<td width=10% class=browseHdr nowrap align=right>&nbsp;Quantity</td>
					<td width=10% class=browseHdr nowrap align=right>&nbsp;Price</td>
					<td width=10% class=browseHdr nowrap align=right>&nbsp;Line Total</td>
				</tr>
<%	for (int i = 0; i < linecount; i++)
		{
			DisbLine disbLine = (DisbLine) disbList.get(i);
			String s_qtyOnHand = "";
			if (invLocationList != null && invLocationList.size() > 0)
			{
				InvLocation invLocation = (InvLocation) invLocationList.get(i);
				if (invLocation != null)
					s_qtyOnHand = HiltonUtility.getFormattedQuantity(invLocation.getQtyOnHand(), oid).toString();
			}
			if ( ! ( disbLine.getQuantity().compareTo(new BigDecimal(0))== 0 ))
			{
%>
						<tr class=browseRow>
							<td width=10% class=browseRow nowrap align=right><%=i+1%></td>
							<td width=10% class=browseRow nowrap><%=HiltonUtility.ckNull(disbLine.getItemNumber())%></td>
							<td width=8% class=browseRow nowrap><%=s_qtyOnHand%></td>
							<td width=8% class=browseRow nowrap><%=HiltonUtility.ckNull(disbLine.getAisle())%></td>
							<td width=8% class=browseRow nowrap><%=HiltonUtility.ckNull(disbLine.getLocrow())%></td>
							<td width=8% class=browseRow nowrap><%=HiltonUtility.ckNull(disbLine.getTier())%></td>
							<td width=8% class=browseRow nowrap><%=HiltonUtility.ckNull(disbLine.getBin())%></td>
							<td width=10% class=browseRow nowrap><%=HiltonUtility.ckNull(disbLine.getUmCode())%></td>
							<td width=10% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(disbLine.getQuantity(), oid)%></td>
							<td width=10% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(disbLine.getUnitPrice(), oid)%></td>
							<td width=10% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(disbLine.getLineTotal(), oid)%></td>
						</tr>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow nowrap><%//=HiltonUtility.ckNull(disbLine.getDescription())%></td>
						</tr>
<%
			}
		}
%>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<!-- <td width=50% align=center><a href="javascript: doSubmit('/inventory/dsb_summary.jsp', 'DisbCreate;DisbursementRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_create.gif" border=0></a></td> -->
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/inventory/dsb_review.jsp', 'DisbCreate;DisbursementRetrieve'); void(0);">Create</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);">Cancel</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function hidebtn(a)
	{
		document.getElementById(a).style.visibility = "hidden";
	}

	function toggleDetailsDisplay(areaName, type) {
		var myAreas = document.getElementsByName(areaName);
		var myArea;
		var myImg = document.getElementById(areaName + "Img");
		var myText = document.getElementById(areaName + "Text");
		var hideArea = false;

		for (i = 0; i < myAreas.length; i++) {
			myArea = myAreas(i);


			if (myArea.style.display == "none") {
				myArea.style.display = "block";
			}
			else {
				hideArea = true;
				myArea.style.display = "none";
			}
		}

		if (type == undefined) {
			type = "Details";
		}

		if (hideArea) {
			myImg.src = "<%=contextPath%>/images/plus.gif";
			myImg.alt = "View" + type;
			myText.innerText = "View " + type;
		}
		else {
			myImg.src = "<%=contextPath%>/images/minus.gif";
			myImg.alt = "Hide " + type;
			myText.innerText = "Hide " + type;
		}
	}

	function viewItem(row) {
		var num = document.getElementById("icDsbLine_" + row);
		frm.DisbLine_icDsbLine.value = num.value;
		doSubmit('/inventory/dsb_line.jsp','DisbLineRetrieveById');
	}

	function viewLineAcc(row) {
		var num = document.getElementById("icDsbAcc_" + row);
		frm.Account_icLine.value = num.value;
		doSubmit('/inventory/dsb_accounts_ln.jsp','AccountRetrieveByLine');
	}


/*	function highlightItem(row) {
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
*/

// End Hide script -->
</SCRIPT>