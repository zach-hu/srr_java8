<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.math.BigDecimal" %>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	quantityDecimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	ReceiptHeader receiptHeader = (ReceiptHeader) request.getAttribute("receiptHeader");
	if (receiptHeader == null) {
		receiptHeader = new ReceiptHeader();
	}
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	if (poHeader == null) {
		poHeader = new PoHeader();
	}
	List	poLineList = poHeader.getPoLineList();
	if (poLineList == null) {
		poLineList = new ArrayList();
	}
	Address vendor = (Address) poHeader.getVendorAddress();
	if (vendor == null) {
		vendor = new Address();
	}
	String	receiptNumber = receiptHeader.getReceiptNumber();
	if (HiltonUtility.isEmpty(receiptNumber)) {
		receiptNumber = "N/A";
	}
	String	receiptMethod = (String) request.getAttribute("receiptMethod");
	if (HiltonUtility.isEmpty(receiptMethod)) {
		receiptMethod = "ReceiveByOrder";
	}
	String	createAction = "FINALIZE";
	if (receiptMethod.equals("ReceiveByOrder")) {
		createAction = "SAVE";
	}
	BigDecimal	bd_zero = new BigDecimal(0);
%>

<tsa:hidden name="ReceiptHeader_icRecHeader" value="<%=receiptHeader.getIcRecHeader()%>"/>
<tsa:hidden name="ReceiptHeader_receiptNumber" value="<%=receiptHeader.getReceiptNumber()%>"/>
<tsa:hidden name="ReceiptHeader_receiptStatus" value="<%=receiptHeader.getReceiptStatus()%>"/>
<tsa:hidden name="ReceiptHeader_fiscalYear" value="<%=receiptHeader.getFiscalYear()%>"/>
<tsa:hidden name="ReceiptHeader_icPoHeader" value="<%=receiptHeader.getIcPoHeader()%>"/>
<tsa:hidden name="ReceiptHeader_receiptType" value="O"/>
<tsa:hidden name="receiptMethod" value="<%=receiptMethod%>"/>
<tsa:hidden name="createAction" value="<%=createAction%>"/>
<tsa:hidden name="allowBrowse" value="true"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Receipt Information</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td nowrap align=right><b>Order #:</b></td>
			<td width=100px><%=poHeader.getPoNumber()%></td>
<%	if (poHeader.getReleaseNumber().compareTo(bd_zero) > 0)
		{%>
			<td nowrap align=right><b>Release #:</b></td>
			<td width=100px><%=poHeader.getReleaseNumber()%></td>
<%	}
		if (poHeader.getRevisionNumber().compareTo(bd_zero) > 0)
		{%>
			<td nowrap align=right><b>Revision #:</b></td>
			<td width=100px><%=poHeader.getRevisionNumber()%></td>
<%	} %>
		</tr>
		<tr>
			<td align=right><b>Receipt #:</b></td>
			<td width=100px><%=receiptNumber%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0>
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
				<table id=receiptInfoTable border=1 cellspacing=0 cellpadding=0 width=585px class=browseHdr>
				<tr>
					<td colspan=2 class=browseHdr height=18px nowrap>&nbsp;Receipt Information</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
						<tr>
							<td class=browseRow width=45%>
								<table border=0 cellspacing=0 cellpadding=1>
								<tr>
									<td nowrap align=right width=91px><b>Supplier:&nbsp;</b></td>
									<td nowrap><%=receiptHeader.getVendorId()%></td>
								</tr>
		<%		if (!HiltonUtility.isEmpty(vendor.getAddressLine1())) { %>
								<tr><td>&nbsp;</td><td nowrap><%=vendor.getAddressLine1()%></td></tr>
		<%		}
					if (!HiltonUtility.isEmpty(vendor.getAddressLine2())) { %>
								<tr><td>&nbsp;</td><td  height=12px nowrap><%=vendor.getAddressLine2()%></td></tr>
		<%		}
					if (!HiltonUtility.isEmpty(vendor.getAddressLine3())) { %>
								<tr><td>&nbsp;</td><td nowrap><%=vendor.getAddressLine3()%></td></tr>
		<%		}
					if (!HiltonUtility.isEmpty(vendor.getAddressLine4())) { %>
								<tr><td>&nbsp;</td><td nowrap><%=vendor.getAddressLine4()%></td></tr>
		<%		}
					if (!HiltonUtility.isEmpty(vendor.getCity() + vendor.getState() + vendor.getPostalCode())) { %>
								<tr><td>&nbsp;</td><td nowrap><%=vendor.getCity()%>&nbsp;<%=vendor.getState()%>&nbsp;<%=vendor.getPostalCode()%></td></tr>
		<%		}
					if (!HiltonUtility.isEmpty(vendor.getCountry())) {%>
								<tr><td>&nbsp;</td><td nowrap><%=vendor.getCountry()%></td></tr>
		<%		}
					if (!HiltonUtility.isEmpty(poHeader.getContactName())) { %>
								<tr><td>&nbsp;</td><td nowrap>Attn:&nbsp<%=HiltonUtility.ckNull(poHeader.getContactName())%></td></tr>
		<%		} %>
								<tr>
									<td nowrap align=right><b>Receipt Date:</b>&nbsp;</td>
									<td nowrap><%=HiltonUtility.getFormattedDate(receiptHeader.getReceiptDate(), oid, userDateFormat)%></td>
								</tr>
								</table>
							</td>
							<td class=browseRow width=55% align=center>
								<table border=0 cellspacing=0 cellpadding=1 class=browseRow>
								<tr>
									<td nowrap align=right><b># of Packages:</b>&nbsp;</td>
									<td nowrap><input type=text name="ReceiptHeader_pkgsReceived" value="<%=receiptHeader.getPkgsReceived().setScale(0, BigDecimal.ROUND_HALF_UP)%>" style="text-align:right" tabIndex=2 size=10></td>
								</tr>
								<tr>
									<td nowrap align=right><b>Packing Slip:&nbsp;</b></td>
									<td nowrap><input type=text name="ReceiptHeader_packingSlip" value="<%=receiptHeader.getPackingSlip()%>" tabIndex=4 onchange="upperCase(this);"></td>
								</tr>
								<tr>
									<td nowrap align=right><b><a href="javascript: browseStd('ReceiptHeader_carrierCode', 'CARR'); void(0);" title="Click here to choose the Carrier Code for this receipt or enter the value in the box on the right.">Carrier Code:</a>&nbsp;</b></td>
									<td nowrap><input type=text name="ReceiptHeader_carrierCode" value="<%=receiptHeader.getCarrierCode()%>" tabIndex=6 onchange="upperCase(this);"></td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan=2>
								<table border=0 cellspacing=0 cellpadding=0 class=browseRow>
								<tr>
									<td nowrap align=right valign=top width=93px><b>Receipt Notes:</b>&nbsp;</td>
									<td><textarea name="ReceiptHeader_receiptNotes" cols=90 rows=3  onKeyDown="textCounter(this, 225);" onKeyUp="textCounter(this,225);" tabIndex=8>${esapi:encodeForHTML(receiptHeader.receiptNotes)}</textarea></td>
								</tr>
								</table>
							<td>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<br>

		<table border=0 cellpadding=2 cellspacing=0 width=635px>
		<tr><td width=100% align=right><a href="javascript: receiveAll(); void(0);">Receive All Items</a></td></tr>
		</table>

		<table id=itemTable border=1 cellspacing=0 cellpadding=0 width=640px class=browseHdr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRowHdr>
				<tr>
					<td width=6% class=browseHdr nowrap align=center>Line #</td>
					<td width=30% class=browseHdr nowrap>Item/Part #</td>
					<td width=12% class=browseHdr nowrap>End User</td>
					<td width=10% class=browseHdr nowrap>Qty. Ordered</td>
					<td width=10% class=browseHdr nowrap align=center>Balance</td>
					<td width=12% class=browseHdr nowrap align=center>Qty. Received</td>
					<td width=8% class=browseHdr nowrap>&nbsp;</td>
					<td width=12% class=browseHdr nowrap>&nbsp;</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow>
<%
		int ipl = 0;
		for (int il=0; il < poLineList.size(); il++) {
			PoLine poLine = (PoLine) poLineList.get(il);
			if (poLine != null && poLine.getStatus().compareTo(DocumentStatus.RCV_INVOICED) < 0) {
				BigDecimal balanceQty = (poLine.getQuantity()).subtract(poLine.getQtyReceived());
				String	endUser = poLine.getRequisitionerCode();

				if (HiltonUtility.isEmpty(endUser)) {
					endUser = poHeader.getRequisitionerCode();
				}
				if (HiltonUtility.isEmpty(endUser)) {
					endUser = poHeader.getBuyerCode();
				}

				if (receiptMethod.equals("FinalizeReceipt")) {
					String forwardTo = receiptHeader.getForwardTo();
					if (forwardTo.equals("END-USERS") && !endUser.equals(uid)) {
						continue;
					}
				} else {
					if (poLine.getReceiptRequired().equals("E")) {
						if (role.getAccessRights("RCVBYENDUSER") < 99 && !endUser.equals(uid)) {
							continue;
						} else if (role.getAccessRights("RCVBYENDUSER") < 3) {
							continue;
						}
					}
					else if (poLine.getReceiptRequired().equals("R")) {
						if (role.getAccessRights("RCVBYITEM") < 3) {
							continue;
						}
					} else {
						// Item must be Previously Received or No Receipt Required
						continue;
					}
				}
				if (balanceQty.compareTo(bd_zero) <= 0) {
					balanceQty = bd_zero;
				}
				List receiptLineList = poLine.getReceiptLineList();
%>
				<tr>
					<td width=6% class=browseRow nowrap align=right><%=poLine.getLineNumber().setScale(1, BigDecimal.ROUND_HALF_UP)%>&nbsp;</td>
					<td width=30% class=browseRow nowrap><%=poLine.getItemNumber()%></td>
					<td width=12% class=browseRow nowrap><%=UserManager.getInstance().getUser(oid,endUser).getDisplayName()%></td>
					<td width=10% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(poLine.getQuantity(), oid)%></td>
					<td width=10% class=browseRow nowrap align=right id="balanceQty"><%=HiltonUtility.getFormattedQuantity(balanceQty, oid)%></td>
					<td width=12% class=browseRow nowrap align=center>
						<input type=text name="ReceiptLine_qtyReceived" value="" style="text-align:right" size=10 onchange="checkBalance(<%=ipl%>);" tabIndex=<%=ipl + 10%>>
						<tsa:hidden name="balance" value="<%=HiltonUtility.getFormattedQuantity(balanceQty, oid)%>"/>
						<tsa:hidden name="ReceiptLine_icRecHeader" value="<%=receiptHeader.getIcRecHeader()%>"/>
						<tsa:hidden name="ReceiptLine_icRecLine" value=""/>
						<tsa:hidden name="ReceiptLine_icPoLine" value="<%=poLine.getIcPoLine()%>"/>
					</td>
					<td width=8% class=browseRow nowrap>
						<table border=0 cellpadding=0 cellspacing=0 width=100%>
						<tr>
							<td width=50% align=center>
								<div id="receiveAllLink">
							<%	if (balanceQty.compareTo(bd_zero) >= 1) {%><a href="javascript: receiveAllForItem(<%=ipl%>); void(0);"><img src="<%=contextPath%>/images/checkmark.gif" border=0 alt="Receive Remaining Balance" tabIndex=-1></a><%}%>
								</div>
							</td>
							<td width=50% align=center><div id="receiptNotes"><a href="javascript: viewReceiptNotes(<%=ipl%>); void(0);"><img src="<%=contextPath%>/images/cmd_edit.gif" border=0 alt="Item Receipt Notes" tabIndex=-1></a></div></td>
						</tr>
						</table>
					</td>
					<td width=12% class=browseRow align=center>
<%			if (receiptLineList != null && receiptLineList.size() > 0) {%>
						<div id="receiptHistory"><a href="javascript: viewReceiptHistory(<%=ipl%>); void(0);">View Receipt History</a></div>
<%			} else {%>
						<div id="receiptHistory">No Receipt History</div>
<%			}%>
					</td>
				</tr>
				<tr>
					<td class=browseRow nowrap>&nbsp;</td>
					<td colspan=5 class=browseRow valign=top><%=poLine.getDescription()%></td>
					<td colspan=2 class=browseRow nowrap>&nbsp;</td>
				</tr>
				<tr>
					<td class=browseRow colspan=8 align=center>
						<div id="itemReceiptNotes" style="visibility: hidden; display: none;">
						<table border=1 cellspacing=0 cellpadding=0 width=75% class=browseHdrDk>
						<tr>
							<td width=100% height=18px class=browseHdrDk>
								<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdrDk>
								<tr>
									<td width=95% class=browseHdrDk>&nbsp;<b>Item Receipt Notes</b></td>
									<td width=5% class=browseHdrDk align=right valign=bottom><a href="javascript: hideReceiptNotes(<%=ipl%>); void(0);"><img src="<%=contextPath%>/images/bar_close.gif" border=0 alt"Close Item Receipt Notes"></a></td>
								</tr>
								</table>
								<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
								<tr>
									<td align=center><textarea name="ReceiptLine_receiptNotes" cols=90 rows=4  onKeyDown="textCounter(this, 225);" onKeyUp="textCounter(this,225);"></textarea></td>
								</tr>
								</table>
							</td>
						</tr>
						</table>
						<br>
						</div>
						<div id="itemReceiptHistory" style="visibility: hidden; display: none;">
						<table border=1 cellspacing=0 cellpadding=0 width=98% class=browseHdrDk>
						<tr>
							<td width=100% height=18px class=browseHdrDk>
								<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdrDk>
								<tr>
									<td width=13% class=browseHdrDk>&nbsp;<b>Receipt #</b></td>
									<td width=12% class=browseHdrDk>&nbsp;<b>Type</b></td>
									<td width=13% class=browseHdrDk>&nbsp;<b>Trans. Date</b></td>
									<td width=15% class=browseHdrDk>&nbsp;<b>Entered By</b></td>
									<td width=16% class=browseHdrDk>&nbsp;<b>Pkg. Slip/RMA #</b></td>
									<td width=13% class=browseHdrDk>&nbsp;<b>Carrier Code</b></td>
									<td width=15% class=browseHdrDk align=right>&nbsp;<b>Qty. Received</b></td>
									<td width=3% class=browseHdrDk align=right valign=bottom><a href="javascript: hideReceiptHistory(<%=ipl%>); void(0);"><img src="<%=contextPath%>/images/bar_close.gif" border=0 alt='Close Receipt History'></a></td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%		if (receiptLineList != null) {
				for (int irl = 0; irl < receiptLineList.size(); irl++) {
					ReceiptLine receiptLine = (ReceiptLine) receiptLineList.get(irl);
					String	type = "Original";
					if (receiptLine.getReceiptType().equals("R")) {
						type = "Return";
					} else if (receiptLine.getReceiptType().equals("A")) {
						type = "Adjustment";
					}
%>
								<tr>
									<td width=13% class=browseRow><%=receiptLine.getReceiptNumber()%></td>
									<td width=12% class=browseRow><%=type%></td>
									<td width=13% class=browseRow><%=HiltonUtility.getFormattedDate(receiptLine.getReceiptDate(), oid, userDateFormat)%></td>
									<td width=15% class=browseRow><%=(UserManager.getInstance().getUser(oid, receiptLine.getReceivedBy())).getDisplayName()%></td>
									<td width=16% class=browseRow><%=receiptLine.getPackingSlip()%></td>
									<td width=13% class=browseRow><%=receiptLine.getCarrierCode()%></td>
									<td width=15% class=browseRow align=right><%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyReceived(), oid)%></td>
									<td width=5% class=browseRow>&nbsp;</td>
								</tr>
<%				if (!HiltonUtility.isEmpty(receiptLine.getReceiptNotes())) {%>
								<tr>
									<td colspan=8>
										<table border=0 cellpadding=2 cellspacing=0 width=87%>
										<tr>
											<td width=12% align=right valign=top>Notes:</td>
											<td valign=top><%=receiptLine.getReceiptNotes()%></td>
										</tr>
										</table>
									</td>
								</tr>
<%				}
				}
			}%>
								</table>
							</td>
						</tr>
						</table>
						</div>
					</td>
				</tr>
<%
				ipl++;
			}
		}
%>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<div id="dummyFields" style="display:none;">
<tsa:hidden name="ReceiptLine_qtyReceived" value=""/>
<tsa:hidden name="balance" value=""/>
<tsa:hidden name="ReceiptLine_icRecHeader" value=""/>
<tsa:hidden name="ReceiptLine_icRecLine" value=""/>
<tsa:hidden name="ReceiptLine_icPoLine" value=""/>
<tsa:hidden name="ReceiptLine_notes" value=""/>
</div>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a href="javascript: finalizeReceipt(); void(0);"><img class=button src="<%=contextPath%>/images/button_finalize.gif" border=0 tabIndex=100></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('menu/main_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0 tabIndex=102></a></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

<%	if (receiptMethod.equals("FinalizeReceipt")) {%>
	frm.ReceiptHeader_pkgsReceived.disabled = true;
	frm.ReceiptHeader_packingSlip.disabled = true;
	frm.ReceiptHeader_carrierCode.disabled = true;
	frm.ReceiptHeader_receiptNotes.disabled = true;
<%	}%>

	function receiveAllForItem(row) {
		var balance = frm.balance[row].value;
		frm.ReceiptLine_qtyReceived[row].value = balance;

		checkBalance(row);
		if (frm.ReceiptLine_qtyReceived[row + 1] && frm.ReceiptLine_qtyReceived[row + 1].type != "hidden") {
			frm.ReceiptLine_qtyReceived[row + 1].focus();
		}
	}

	function checkBalance(row) {
		var qtyDecimals = <%=Integer.valueOf(quantityDecimals).intValue()%>;
		var receiptQty = nformat(eval(nfilter(frm.ReceiptLine_qtyReceived[row])), qtyDecimals);
		var originalBalance = nformat(eval(nfilter(frm.balance[row])), qtyDecimals);
		var newBalance = nformat(originalBalance - receiptQty, qtyDecimals);

		frm.ReceiptLine_qtyReceived[row].value = receiptQty;

		if (newBalance <= 0) {
			var d = document.all("balanceQty");
			if (d.length > 1) {
				d(row).innerHTML = nformat(0.0, qtyDecimals);
			} else {
				d.innerHTML = nformat(0.0, qtyDecimals);
			}

			var imgs = document.all("receiveAllLink");
			if (imgs.length > 1) {
				imgs(row).style.visibility = "hidden";
			} else {
				imgs.style.visibility = "hidden";
			}
		} else {
			var d = document.all("balanceQty");
			if (d.length > 1) {
				d(row).innerHTML = newBalance;
			} else {
				d.innerHTML = newBalance;
			}

			var imgs = document.all("receiveAllLink");
			if (imgs.length > 1) {
				imgs(row).style.visibility = "visible";
			} else {
				imgs.style.visibility = "visible";
			}
		}
	}

	function viewReceiptHistory(row) {
		var d = document.all("itemReceiptHistory");
		if (d.length > 1) {
			d(row).style.visibility = "visible";
			d(row).style.display = "block";
		} else {
			d.style.visibility = "visible";
			d.style.display = "block";
		}
		var t = document.all("receiptHistory");
		if (t.length > 1) {
			t(row).style.visibility = "hidden";
			t(row).style.display = "none";
		} else {
			t.style.visibility = "hidden";
			t.style.display = "none";
		}
	}

	function hideReceiptHistory(row) {
		var d = document.all("itemReceiptHistory");
		if (d.length > 1) {
			d(row).style.visibility = "hidden";
			d(row).style.display = "none";
		} else {
			d.style.visibility = "hidden";
			d.style.display = "none";
		}
		var t = document.all("receiptHistory");
		if (t.length > 1) {
			t(row).style.visibility = "visible";
			t(row).style.display = "block";
		} else {
			t.style.visibility = "visible";
			t.style.display = "block";
		}
	}

	function viewReceiptNotes(row) {
		var d = document.all("itemReceiptNotes");
		if (d.length > 1) {
			d(row).style.visibility = "visible";
			d(row).style.display = "block";
		} else {
			d.style.visibility = "visible";
			d.style.display = "block";
		}
		var t = document.all("receiptNotes");
		if (t.length > 1) {
			t(row).style.visibility = "hidden";
			t(row).style.display = "none";
		} else {
			t.style.visibility = "hidden";
			t.style.display = "none";
		}
	}

	function hideReceiptNotes(row) {
		var d = document.all("itemReceiptNotes");
		if (d.length > 1) {
			d(row).style.visibility = "hidden";
			d(row).style.display = "none";
		} else {
			d.style.visibility = "hidden";
			d.style.display = "none";
		}
		var t = document.all("receiptNotes");
		if (t.length > 1) {
			t(row).style.visibility = "visible";
			t(row).style.display = "block";
		} else {
			t.style.visibility = "visible";
			t.style.display = "block";
		}
	}

	function receiveAll() {
		var items = frm.elements.item("ReceiptLine_qtyReceived");
		var itemCount = 0;

		if (items.length != undefined){
			itemCount = items.length - 1;
		}
		for (var i=0; i < itemCount; i++) {
			receiveAllForItem(i);
		}
	}

	function finalizeReceipt() {
		var dummyFields = document.getElementById("dummyFields");
		if (dummyFields != null && dummyFields != undefined) {
			dummyFields.innerHTML = "";
		}
		doSubmit('receipts/rec_confirmation.jsp', 'ReceiptUpdate')
	}

// End Hide script -->
</SCRIPT>