<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.util.List.*" %>
<%@ page import="java.math.*" %>
<%
	DisbHeader disbHeader = (DisbHeader) request.getAttribute("disbHeader");
	int i;
	BigDecimal b_icReqHeader = disbHeader.getIcReqHeader();
	BigDecimal b_icDisbHeader = disbHeader.getIcDsbHeader();
	BigDecimal b_icAccount = disbHeader.getIcAccount();
	String s_reqNumber = disbHeader.getRequisitionNumber();
	if (s_reqNumber==null){s_reqNumber = "";}
	String s_status = disbHeader.getStatus();
	String s_disbNumber = disbHeader.getDisbNumber();
	if (s_disbNumber==null){s_disbNumber = "";}
	Date d_disbDate = disbHeader.getDisbDate();
	String s_fiscalYear = disbHeader.getFiscalYear();
	if (s_fiscalYear==null){s_fiscalYear = "";}
	Date d_requiredBy = disbHeader.getDisbDate();
	String s_requisitioner = disbHeader.getRequisitionerCode();
	if (s_requisitioner==null){s_requisitioner = "";}
	String s_itemLocation = disbHeader.getItemLocation();
	if (s_itemLocation==null){s_itemLocation = "";}
	String s_intComments = disbHeader.getInternalComments();
	if (s_intComments==null){s_intComments = "";}
	BigDecimal b_subTotal = disbHeader.getSubtotal();
	if (b_subTotal==null){b_subTotal = new BigDecimal(0);}
%>

<form name="purchaseform" target="_parent" action="/procure" method="POST">
	<tsa:hidden name="DisbHeader_icDsbHeader" value="<%=b_icDisbHeader%>"/>
	<tsa:hidden name="DisbLine_icDsbHeader" value="<%=b_icDisbHeader%>"/>
	<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=b_icReqHeader%>"/>
	<tsa:hidden name="DisbHeader_icAccount" value="<%=b_icAccount%>"/>
	<tsa:hidden name="Account_icHeader" value="<%=b_icAccount%>"/>
	<tsa:hidden name="Account_icLine" value=""/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Disbursement Information</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
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
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
		<tr>
			<td width=100% align=center valign=top>
				<table id=supplierTable border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
				<tr>
					<td class=browseHdr height=18px nowrap>&nbsp;Header</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
						<tr>
							<td nowrap align=left width=30%><b>Requisition Number:</b>&nbsp;</td>
							<td nowrap><input type="text" name="DisbHeader_requisitionNumber" value="<%=s_reqNumber%>" disabled></td>
<%
	if (b_icAccount != null){
%>
							<td nowrap align=left><b><a href="javascript: doSubmit('/inventory/dsb_accounts.jsp', 'AccountRetrieveByHeader'); void(0);">View Disbursment Account Allocations&nbsp;</a></b></td>
<%  }
	else{%>
							<td nowrap>&nbsp;</td>
<%  } %>
							<td nowrap>&nbsp;</td>
						</tr>
						<tr>
							<td nowrap align=left width=30%><b>Disbursement Number:</b>&nbsp;</td>
							<td nowrap><input type="text" name="DisbHeader_disbNumber" value="<%=s_disbNumber%>" disabled>
								<tsa:hidden name="DisbLine_disbNumber" value="<%=s_disbNumber%>"/>
							</td>
							<td nowrap align=left><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "fiscalYear", "Fiscal Year")%>:&nbsp;</b></td>
							<td nowrap><input type="text" name="DisbHeader_fiscalYear" value="<%=s_fiscalYear%>" disabled></td>
						</tr>
						<tr>
							<td nowrap align=left><b>Disbursement Date:</b>&nbsp;</td>
							<td nowrap><input type="text" name="DisbHeader_disbDate" value="<%=d_disbDate%>" disabled></td>
							<td nowrap align=left width=30%><b>Requisition Status:</b>&nbsp;</td>
							<td nowrap><input type="text" name="DisbHeader_status" value="<%=s_status%>" disabled></td>
						</tr>
						</table>
						<div id="generalDetails" style="display:none;">
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr>
							<td nowrap align=left width=30%><b>Required By:&nbsp;</b></td>
							<td nowrap><input type="text" name="DisbHeader_disbDate" value="<%=d_requiredBy%>" disabled></td>
							<td nowrap align=left width=30%><b>Requisition Number:</b>&nbsp;</td>
							<td nowrap><input type="text" name="DisbHeader_requisitionNumber" value="<%=s_reqNumber%>" disabled></td>
						</tr>
						<tr>
							<td nowrap align=left width=30%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitioner", "Requisitioner")%>:</b>&nbsp;</td>
							<td nowrap><input type="text" name="DisbHeader_requisitionerCode" value="<%=s_requisitioner%>" disabled></td>
							<td nowrap align=left><b>Inventory Location:&nbsp;</b></td>
							<td nowrap><input type="text" name="DisbHeader_itemLocation" value="<%=s_itemLocation%>" disabled></td>
						</tr>
						<tr>
							<td nowrap align=left><b>Internal Comments:&nbsp;</b></td>
							<td nowrap><input type="text" name="DisbHeader_internalComments" value="<%=s_intComments%>"></td>
							<td nowrap align=left><b>&nbsp;</b></td>
							<td nowrap>&nbsp;</td>
						</tr>
						</table>
						</div>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
						<tr>
							<td align=right valign=middle>
								<a href="javascript: toggleDetailsDisplay('generalDetails', 'Details'); void(0);"><font color='#cf0000'><span id='generalDetailsText'>View All Details</span></font>&nbsp;<img id='generalDetailsImg' valign='baseline' src='<%=contextPath%>/images/plus.gif' border=0 alt="View Details"></a>&nbsp;
							</td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
			<tr>
				<td>
					<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
						<tr>
							<td class=browseHdr height=18px nowrap>&nbsp;Line Items</td>
							<tsa:hidden name="DisbLine_icDsbLine" value=""/>
						</tr>
					</table>
				</td>
		</tr>
<%
	List disbList = (List) request.getAttribute("disbLines");
	if(disbList != null){
	for(i=0;i<disbList.size();i++)
	{
		DisbLine disbLine = (DisbLine) disbList.get(i);
		BigDecimal b_ic_dsbLine = disbLine.getIcDsbLine();
		BigDecimal b_ic_accLine = disbLine.getIcDsbAccount();
		String s_item_number = disbLine.getItemNumber();
		if (s_item_number==null){s_item_number = "";}
		String s_aisle = disbLine.getAisle();
		if (s_aisle==null){s_aisle = "";}
		String s_row = disbLine.getLocrow();
		if (s_row==null){s_row = "";}
		String s_tier = disbLine.getTier();
		if (s_tier==null){s_tier = "";}
		String s_bin = disbLine.getBin();
		if (s_bin==null){s_bin = "";}
		String s_umCode = disbLine.getUmCode();
		if (s_umCode==null){s_umCode = "";}
		BigDecimal b_quantity = disbLine.getQuantity();
		if (b_quantity==null){b_quantity = new BigDecimal(0);}
		BigDecimal b_unitPrice = disbLine.getUnitPrice();
		if (b_unitPrice==null){b_unitPrice = new BigDecimal(0);}
		BigDecimal b_lineTotal = disbLine.getLineTotal();
		if (b_lineTotal==null){b_lineTotal = new BigDecimal(0);}
		String s_vendorId = disbLine.getVendorId();
		if (s_vendorId==null){s_vendorId = "";}
		String s_lot = disbLine.getLot();
		if (s_lot==null){s_lot = "";}
		String s_mfgNo = disbLine.getManufactNo();
		if (s_mfgNo==null){s_mfgNo = "";}
		String s_location = disbLine.getItemLocation();
		if (s_location==null){s_location = "";}

		if (i==1){
%>
		<div id="itemDetails" style="display:none;">
<% 		} %>
		<tr>
			<td>
				<table id=itemTable border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
				<tr>
					<td>
						<table>
							<tr>
								<td width=7% class=browseHdr nowrap>&nbsp;Line #</td>
								<td width=15% class=browseHdr nowrap>&nbsp;Item/Part #</td>
								<td width=10% class=browseHdr nowrap align="center">&nbsp;Aisle</td>
								<td width=10% class=browseHdr nowrap align="center">&nbsp;Row</td>
								<td width=10% class=browseHdr nowrap align="center">&nbsp;Tier</td>
								<td width=10% class=browseHdr nowrap align="center">&nbsp;Bin</td>
								<td width=10% class=browseHdr nowrap align="center">&nbsp;UI</td>
								<td width=10% class=browseHdr nowrap align="center">&nbsp;Quantity</td>
								<td width=10% class=browseHdr nowrap align="center">&nbsp;Price</td>
							</tr>
						</table>
					</td>
				</tr>
				</table>
				<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseRow>
					<tr>
						<td colspan=4>
							<table id=itemRow border=1 cellspacing=0 cellpadding=1 width=665px class=browseRow>
								<td width=9% class=browseRow nowrap><%=i+1%><tsa:hidden name="DisbLine_lineNumber" value="<%=(i+1)%>"/></td>
								<td width=16% class=browseRow nowrap><a href="javascript: viewItem(<%=i%>); void(0);"><%=s_item_number%></a><tsa:hidden id="icDsbLine_<%=i%>" name="icDsbLine_<%=i%>" value="<%=b_ic_dsbLine%>"/><tsa:hidden name="DisbLine_itemNumber" value="<%=s_item_number%>"/></td>
								<td width=11% class=browseRow nowrap align="center"><input type="text" size=5 name="DisbLine_aisle" value="<%=s_aisle%>" disabled></td>
								<td width=11% class=browseRow nowrap align="center"><input type="text" size=5 name="DisbLine_locrow" value="<%=s_row%>" disabled></td>
								<td width=11% class=browseRow nowrap align="center"><input type="text" size=5 name="DisbLine_tier" value="<%=s_tier%>" disabled></td>
								<td width=11% class=browseRow nowrap align="center"><input type="text" size=5 name="DisbLine_bin" value="<%=s_bin%>" disabled></td>
								<td width=11% class=browseRow nowrap align="center"><input type="text" size=5 name="DisbLine_umCode" value="<%=s_umCode%>" disabled></td>
								<td width=10% class=browseRow nowrap align="center"><input type="text" size=5 name="DisbLine_quantity" value="<%=HiltonUtility.getFormattedQuantity(b_quantity, oid)%>" disabled><tsa:hidden name="old_DisbLine_qty" value="<%=b_quantity%>"/></td>
								<td width=10% class=browseRow nowrap align="center"><input type="text" size=5 name="DisbLine_unitPrice" value="<%=HiltonUtility.getFormattedPrice(b_unitPrice, oid)%>" disabled><!--<tsa:hidden name="DisbLine_itemLocation" value="<%=s_location%>"/>--></td>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan=2 class=browseRow nowrap>Description</td>
						<td class=browseRow nowrap><b>Line Total:</b>&nbsp;</td>
						<td class=browseRow nowrap><input type="text" name="DisbLine_lineTotal" value="<%=b_lineTotal%>" disabled></td>
					</tr>
					<tr>
<%
	if (b_ic_accLine != null){
%>
						<td class=browseRow nowrap>&nbsp;</td>
						<td class=browseRow nowrap>
						<tsa:hidden id="icDsbAcc_<%=i%>" name="icDsbAcc_<%=i%>" value="<%=b_ic_accLine%>"/>
						<b><a href="javascript: doSubmit('/inventory/dsb_accounts.jsp', 'AccountRetrieveByLine'); void(0);">View Line Account Allocations&nbsp;</a></b></td>
						</td>
						<td class=browseRow nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "vendorId", "Supplier Code")%>:</b>&nbsp;</td>
						<td class=browseRow nowrap><input type="text" name="DisbLine_vendorId" value="<%=s_vendorId%>" disabled></td>
<%  }
	else{ %>
						<td colspan=2 class=browseRow nowrap>&nbsp;</td>
						<td class=browseRow nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "vendorId", "Supplier Code")%>:</b>&nbsp;</td>
						<td class=browseRow nowrap><input type="text" name="DisbLine_vendorId" value="<%=s_vendorId%>" disabled></td>
<%  } %>
					</tr>
					<tr>
						<td colspan=2 class=browseRow nowrap>&nbsp;</td>
						<td class=browseRow nowrap><b>Lot:</b>&nbsp;</td>
						<td class=browseRow nowrap><input type="text" name="DisbLine_lot" value="<%=s_lot%>" disabled></td>
					</tr>
					<tr>
						<td colspan=2 class=browseRow nowrap>&nbsp;</td>
						<td class=browseRow nowrap><b>Mfg No:</b>&nbsp;</td>
						<td class=browseRow nowrap><input type="text" name="DisbLine_manufactNo" value="<%=s_mfgNo%>" disabled></td>
					</tr>
				</table>
<% }} %>
				</div>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=30% valign=middle align=right>
						<a href="javascript: toggleDetailsDisplay('itemDetails', 'Items'); void(0);"><font color='#cf0000'><span id='itemDetailsText'>View All Items</span></font>&nbsp;<img id='itemDetailsImg' valign='baseline' src='<%=contextPath%>/images/plus.gif' border=0 alt="View Details"></a>&nbsp;
					</td>
				</tr>
				</table>

				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr>
							<td width=85% class=browseRow nowrap align=right><b>Disbursement Total:</b>&nbsp;</td>
							<td width=10% class=browseRow nowrap align=right><input type="text" name="DisbHeader_subtotal" value="<%=b_subTotal%>" disabled></td>
							<td width=5% class=browseRow nowrap align=right>&nbsp;</td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/dsb_summary.jsp', 'DisbForward','DisbursementRetrieveById'); void(0);"><img class=button src="<%=contextPath%>/images/button_forward.gif" border=0></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/dsb_select_type.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0></a></td>
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
			myText.innerText = "View All " + type;
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