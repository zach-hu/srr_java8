<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.ReceiptType" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.commodity.CommodityManager" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	quantityDecimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String 	s_duomRequired = propertiesManager.getProperty("INVENTORY", "DUALUOMREQUIRED", "N");
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
	String forwardTo = receiptHeader.getForwardTo();

	String	receiptMethod = (String) request.getAttribute("receiptMethod");
	if (HiltonUtility.isEmpty(receiptMethod)) {
		receiptMethod = "Adjustment";
	}

	BigDecimal	bd_zero = new BigDecimal(0);
	String	receiptType = "";
	if (receiptMethod.equals("Adjustment")) {
		receiptType = ReceiptType.ADJUSTMENT;		//Adjustment
	} else if (receiptMethod.equals("Return")) {
		receiptType = ReceiptType.RETURN;
	} else {
		receiptType = ReceiptType.ORIGINAL;
	}
%>

<tsa:hidden name="ReceiptHeader_icRecHeader" value="<%=receiptHeader.getIcRecHeader()%>"/>
<tsa:hidden name="ReceiptHeader_receiptNumber" value="<%=receiptHeader.getReceiptNumber()%>"/>
<tsa:hidden name="ReceiptHeader_receiptStatus" value="<%=receiptHeader.getReceiptStatus()%>"/>
<tsa:hidden name="ReceiptHeader_fiscalYear" value="<%=receiptHeader.getFiscalYear()%>"/>
<tsa:hidden name="ReceiptHeader_icPoHeader" value="<%=receiptHeader.getIcPoHeader()%>"/>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=receiptHeader.getIcPoHeader() %>" />
<tsa:hidden name="ReceiptHeader_receiptType" value="<%=receiptType%>"/>
<tsa:hidden name="receiptMethod" value="<%=receiptMethod%>"/>
<tsa:hidden name="createAction" value="SAVE"/>
<tsa:hidden name="InvBinLocation_tempIc" value="0"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Receipt Adjustment</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
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
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td width=<%=formEntryWidth%> align=center>
		<table id=receiptInfoTable border=1 cellspacing=0 cellpadding=0 width=800px class=browseHdr>
		<tr><td colspan=2 class=browseHdr height=18px nowrap>&nbsp;<%=ReceiptType.toString(ReceiptType.ORIGINAL, oid)%> Receipt Information</td></tr>
		<tr>
			<td class=browseRow>
				<table border=0 cellspacing=0 cellpadding=2 width=100%>
				<tr>
					<td class=browseRow width=45%>
						<table border=0 cellspacing=0 cellpadding=1>
						<tr <%=HtmlWriter.isVisible(oid, "rec-supplier")%>>
							<td nowrap align=right width=91px><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-supplier", "Supplier")%>:&nbsp;</b></td>
							<td nowrap><%=receiptHeader.getVendorId()%></td>
						</tr>
<%		if (!HiltonUtility.isEmpty(vendor.getAddressLine1())) { %>
						<tr <%=HtmlWriter.isVisible(oid, "rec-sup-addressLine1")%>><td>&nbsp;</td><td nowrap><%=vendor.getAddressLine1()%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(vendor.getAddressLine2())) { %>
						<tr <%=HtmlWriter.isVisible(oid, "rec-sup-addressLine2")%>><td>&nbsp;</td><td  height=12px nowrap><%=vendor.getAddressLine2()%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(vendor.getAddressLine3())) { %>
						<tr <%=HtmlWriter.isVisible(oid, "rec-sup-addressLine3")%>><td>&nbsp;</td><td nowrap><%=vendor.getAddressLine3()%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(vendor.getAddressLine4())) { %>
						<tr <%=HtmlWriter.isVisible(oid, "rec-sup-addressLine4")%>><td>&nbsp;</td><td nowrap><%=vendor.getAddressLine4()%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(vendor.getCity() + vendor.getState() + vendor.getPostalCode())) { %>
						<tr><td>&nbsp;</td><td nowrap><%=vendor.getCity()%>&nbsp;<%=vendor.getState()%>&nbsp;<%=vendor.getPostalCode()%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(vendor.getCountry())) {%>
						<tr <%=HtmlWriter.isVisible(oid, "rec-sup-country")%>><td>&nbsp;</td><td nowrap><%=vendor.getCountry()%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(poHeader.getContactName()))
			{ %>
						<tr <%=HtmlWriter.isVisible(oid, "rec-sup-attention")%>><td>&nbsp;</td><td nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-sup-attention", "Attn")%>:&nbsp<%=HiltonUtility.ckNull(poHeader.getContactName())%></td></tr>
<%		} %>
						<tr <%=HtmlWriter.isVisible(oid, "rec-receiptDate")%>>
							<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receiptDate", "Receipt Date")%>:</b>&nbsp;</td>
							<td nowrap><%=HiltonUtility.getFormattedDate(receiptHeader.getReceiptDate(), oid, userDateFormat)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "rec-receivedBy")%>>
							<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receivedBy", "Received By")%>:&nbsp;</b></td>
							<td nowrap><tsa:hidden name="ReceiptHeader_receivedBy" value="<%=receiptHeader.getReceivedBy()%>"/><%=receiptHeader.getReceivedBy()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "rec-receivedBy")%>>
							<td nowrap align=right>&nbsp;</td>
							<td nowrap><%=UserManager.getInstance().getUser(oid, receiptHeader.getReceivedBy()).getDisplayName()%></td>
						</tr>
						</table>
					</td>
					<td class=browseRow width=55% align=center>
						<table border=0 cellspacing=0 cellpadding=1 class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "rec-numberOfPackages")%>>
							<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-numberOfPackages", "# of Packages")%>:</b>&nbsp;</td>
							<td nowrap><%=receiptHeader.getPkgsReceived().setScale(0, BigDecimal.ROUND_HALF_UP)%><!--input type=text name="ReceiptHeader_pkgsReceived" value="<%=receiptHeader.getPkgsReceived().setScale(0, BigDecimal.ROUND_HALF_UP)%>" tabIndex=2 size=10--></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "rec-packingSlip")%>>
							<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-packingSlip", "Packing Slip")%>:&nbsp;</b></td>
							<td nowrap><%=receiptHeader.getPackingSlip()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "rec-carrierCode")%>>
							<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-carrierCode", "Carrier Code")%>:&nbsp;</b></td>
							<td nowrap><%=receiptHeader.getCarrierCode()%></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan=2>
						<table border=0 cellspacing=0 cellpadding=0 class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "rec-receiptNotes")%>>
							<td nowrap align=right valign=top width=93px><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receiptNotes", "Notes")%>:</b>&nbsp;</td>
							<td valign=top><%=receiptHeader.getReceiptNotes()%></td>
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

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td align=center width=<%=formEntryWidth%>>
		<table id=itemTable border=1 cellspacing=0 cellpadding=0 width=800px class=browseHdr>
<%
		int	ipl = 0;
		for (int il=0; il < poLineList.size(); il++) {
			PoLine poLine = (PoLine) poLineList.get(il);
			String	s_commodity = poLine.getCommodity() ;
			Commodity commodity = CommodityManager.getInstance().getCommodity(oid, s_commodity) ;
			boolean isAsset = false ;
			boolean isDualUm = false ;

			if (commodity != null) {
				String atemp = commodity.getAsset() ;
				String umtemp = commodity.getDuomRequired() ;
				if (atemp == null) atemp = "N" ;
				if (umtemp == null) umtemp = "N" ;
				isAsset = atemp.equalsIgnoreCase("Y") ;
				if (s_duomRequired.equalsIgnoreCase("Y")) isDualUm = umtemp.equalsIgnoreCase("Y") ;
			}

			BigDecimal iclinekey = poLine.getIcLineKey();

			List receiptLineList = poLine.getReceiptLineList();
			if (poLine.getStatus().compareTo(DocumentStatus.RCV_INVOICED) < 0 && receiptLineList != null && receiptLineList.size() > 0) {
				BigDecimal balanceQty = (poLine.getQuantity()).subtract(poLine.getQtyReceived());
				String	endUser = poLine.getRequisitionerCode();

				if (HiltonUtility.isEmpty(endUser)) {
					endUser = poHeader.getRequisitionerCode();
				}
				if (HiltonUtility.isEmpty(endUser)) {
					endUser = poHeader.getBuyerCode();
				}

				if (balanceQty.compareTo(bd_zero) <= 0) {
					balanceQty = bd_zero;
				}

				if (!HiltonUtility.isEmpty(forwardTo)) {
					if (forwardTo.equals("END-USERS") && !endUser.equals(uid)) {
						continue;
					}
				} else {
					if (poLine.getReceiptRequired().equals("E")) {
						if (role.getAccessRights("RCVBYENDUSER") < 3 && !endUser.equals(uid)) {
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
%>
			<tr>
				<td>
					<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
					<tr>
						<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=12% class=browseHdr nowrap vAlign=middle height=16px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-lineNumber", "Line #")%>:&nbsp;<font style="font-Weight:normal"><%=poLine.getLineNumber().setScale(1, BigDecimal.ROUND_HALF_UP)%></font>&nbsp;</td>
						<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width=25% class=browseHdr nowrap vAlign=middle><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-itemNumber", "Item/Part #")%>:&nbsp;<font style="font-Weight:normal"><%=poLine.getItemNumber()%></font></td>
						<td <%=HtmlWriter.isVisible(oid, "rec-asset")%> width=5% class=browseHdr nowrap vAlign=middle><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-asset", "Asset")%>:<font style="font-Weight:normal"></td>
						<td <%=HtmlWriter.isVisible(oid, "rec-asset")%> width=58% class=browseHdr nowrap vAlign=middle><input type="checkbox" name="c_asset" <% if (poLine.getAsset().equals("Y") ) {%> checked="checked" <% } %> disabled="disabled"/><tsa:hidden name="PoLine_asset" value="<%=poLine.getAsset()%>"/>&nbsp;</td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "rec-description")%>><td colspan=4 class=browseHdr vAlign=top><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-description", "Description")%>:</b>&nbsp;<font style="font-Weight:normal"><%=poLine.getDescription()%></font></td></tr>
					<tr <%=HtmlWriter.isVisible(oid, "asset-description")%>>
						<td colspan=4 class=browseHdr vAlign=top>
						&nbsp;
						<font style="font-Weight:normal">
						<%	if (assetsActive && poLine.getAsset().equals("Y") &&  Integer.valueOf(poLine.getStatus()).intValue() >= 3030 ) { %>
						<a href="javascript: viewAssetRelated('<%=iclinekey%>'); "><img src="<%=contextPath%>/images/asset3.gif" border=0></a>
						<%	}%>
						</font></td>
					</tr>
				<tr>
					<td colspan=4 class=browseHdr>
						<table border=0 cellspacing=0 cellpadding=0 class=browseHdr width=100%>
						<tr>
							<td width=33%>
								<table border=0 cellspacing=0 cellpadding=1 class=browseHdr>
								<tr>
									<td <%=HtmlWriter.isVisible(oid, "rec-po-LN01")%> class=browseHdr  nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN01", "Line UDF 1", false)%>:</b></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-po-LN01")%> class=browseHdr ><%=poLine.getUdf1Code()%></td>
								</tr>
								<tr>
									<td <%=HtmlWriter.isVisible(oid, "rec-po-LN04")%> class=browseHdr  nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN04", "Line UDF 4", false)%>:</b></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-po-LN04")%> class=browseHdr ><%=poLine.getUdf4Code()%></td>
								</tr>
								</table>
							</td>
							<td width=33%>
								<table border=0 cellspacing=0 cellpadding=1 class=browseHdr>
								<tr>
									<td <%=HtmlWriter.isVisible(oid, "rec-po-LN02")%> class=browseHdr  nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN02", "Line UDF 2", false)%>:</td>
									<td <%=HtmlWriter.isVisible(oid, "rec-po-LN02")%> class=browseHdr ><%=poLine.getUdf2Code()%></td>
								</tr>
							<%if (!oid.equalsIgnoreCase("vse06p")) { %>
								<tr>
									<td <%=HtmlWriter.isVisible(oid, "rec-po-LN05")%> class=browseHdr  nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN05", "Line UDF 5", false)%>:</b></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-po-LN05")%> class=browseHdr ><%=poLine.getUdf5Code()%></td>
								</tr>
							<% } %>
								</table>
							</td>
							<td width=33%>
								<table border=0 cellspacing=0 cellpadding=1 class=browseHdr>
								<tr>
									<td <%=HtmlWriter.isVisible(oid, "rec-po-LN03")%> class=browseHdr  nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN03", "Line UDF 3", false)%>:</td>
									<td <%=HtmlWriter.isVisible(oid, "rec-po-LN03")%> class=browseHdr ><%=poLine.getUdf3Code()%></td>
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
			<tr>
				<td class=browseRow align=center>
					<div id="items" style="visibility: visible; display: block;">
					<table border=0 cellspacing=0 cellpadding=0 width=750px id="itemsTable">
					<tr>
						<td <%=HtmlWriter.isVisible(oid, "rec-receiptType")%> width=10%><u><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receiptType", "Type")%></u></td>
						<td <%=HtmlWriter.isVisible(oid, "rec-receiptDate")%> width=20%><u><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receiptDate", "Receipt Date")%></u></td>
						<td <%=HtmlWriter.isVisible(oid, "rec-umCode")%> width=10%><u><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-umCode", "U/M")%></u></td>
						<td <%=HtmlWriter.isVisible(oid, "rec-quantityReceived")%> width=15% align=right><u><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityReceived", "Qty Received")%></u></td>
						<td <%=HtmlWriter.isVisible(oid, "rec-quantityRejected")%> width=15% align=right><u><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityRejected", "Qty Rejected")%></u></td>
						<td <%=HtmlWriter.isVisible(oid, "rec-quantityAccepted")%> width=15% align=right><u><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityAccepted", "Qty Accepted")%></u></td>
						<td width=2%>&nbsp;</td>
						<td <%=HtmlWriter.isVisible(oid, "rec-receiptNotes")%> width=21%><u><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receiptNotes", "Notes")%></u></td>
					</tr>
<%
				BigDecimal totalQtyReceived = new BigDecimal(0);
				BigDecimal totalQtyRejected = new BigDecimal(0);
				BigDecimal totalQtyAccepted = new BigDecimal(0);
				String	umCode = poLine.getUmCode() ;
				String  duomUmCode = "" ;
				for (int irl = 0; irl < receiptLineList.size(); irl++) {
					ReceiptLine receiptLine = (ReceiptLine) receiptLineList.get(irl);
					BigDecimal qtyReceived = receiptLine.getQtyReceived();
					BigDecimal qtyRejected = receiptLine.getQtyRejected();
					BigDecimal qtyAccepted = receiptLine.getQtyAccepted();
					BigDecimal duomQtyReceived = receiptLine.getDuomQtyReceived() ;
					if (duomQtyReceived == null) duomQtyReceived = new BigDecimal(0);
					duomUmCode = receiptLine.getDuomUmCode() ;

					if (receiptLine.getReceiptNumber().equals(receiptHeader.getReceiptNumber())) {
						totalQtyReceived = totalQtyReceived.add(qtyReceived);
						totalQtyRejected = totalQtyRejected.add(qtyRejected);
						totalQtyAccepted = totalQtyAccepted.add(qtyAccepted);
%>
					<tr>
						<td <%=HtmlWriter.isVisible(oid, "rec-receiptType")%>class=browseRow vAlign=top><%=ReceiptType.toString(receiptLine.getReceiptType(), oid)%></td>
						<td <%=HtmlWriter.isVisible(oid, "rec-receiptDate")%> class=browseRow vAlign=top><%=HiltonUtility.getFormattedDate(receiptLine.getReceiptDate(), oid, userDateFormat)%></td>
						<td <%=HtmlWriter.isVisible(oid, "rec-umCode")%>class=browseRow vAlign=top><%=umCode%></td>
						<td <%=HtmlWriter.isVisible(oid, "rec-quantityReceived")%> class=browseRow vAlign=top align=right><%=HiltonUtility.getFormattedQuantity(qtyReceived, oid)%></td>
						<td <%=HtmlWriter.isVisible(oid, "rec-quantityRejected")%> class=browseRow vAlign=top align=right><%=HiltonUtility.getFormattedQuantity(qtyRejected, oid)%></td>
						<td <%=HtmlWriter.isVisible(oid, "rec-quantityAccepted")%> class=browseRow vAlign=top align=right><%=HiltonUtility.getFormattedQuantity(qtyAccepted, oid)%></td>
						<td class=browseRow vAlign=top>&nbsp;</td>
						<td <%=HtmlWriter.isVisible(oid, "rec-receiptNotes")%> class=browseRow vAlign=top><%=receiptLine.getReceiptNotes()%></td>
					</tr>
<% if (isDualUm) { %>
					<tr>
						<td colspan=2 vAlign=top align=right>Secondary UOM:&nbsp;&nbsp;</td>
						<td <%=HtmlWriter.isVisible(oid, "rec-duomUmCode")%>class=browseRow vAlign=top><%=duomUmCode%></td>
						<td <%=HtmlWriter.isVisible(oid, "rec-duomQuantityReceived")%> class=browseRow vAlign=top align=right><%=HiltonUtility.getFormattedQuantity(duomQtyReceived, oid)%></td>
					</tr>
<% } %>
<%				}
				}%>
					<tr>
						<td colspan=2 align=center></td>
						<td <%=HtmlWriter.isVisible(oid, "rec-umCode")%>class=browseRow vAlign=top><%=umCode%></td>
						<td align=right>
							<select name="receivedAdjFactor" tabIndex=<%=(ipl*2)%> onchange="checkReceivedBalance(<%=ipl%>); setAccepted(<%=ipl%>); void(0);" style="font-size:9px">
								<option value="1.0">+</option>
								<option value="-1.0">-</option>
							</select>
							<input type=text name="receivedAdjQty" value="" style="text-align:right" size=8 onchange="checkReceivedBalance(<%=ipl%>); setAccepted(<%=ipl%>); void(0);" tabIndex=<%=(ipl*2) + 1%>>
						</td>
						<td align=right>
							<select name="rejectedAdjFactor" tabIndex=<%=(ipl*2)%> onchange="checkRejectedBalance(<%=ipl%>); setAccepted(<%=ipl%>); void(0);" style="font-size:9px">
								<option value="1.0">+</option>
								<option value="-1.0">-</option>
							</select>
							<input type=text name="rejectedAdjQty" value="" style="text-align:right" size=8 onchange="checkRejectedBalance(<%=ipl%>); setAccepted(<%=ipl%>); void(0);" tabIndex=<%=(ipl*2) + 1%>>
						</td>
						<td align=right>
							<select name="adjFactor" tabIndex=<%=(ipl*2)%> style="font-size:9px" readonly>
								<option value="1.0">+</option>
								<option value="-1.0">-</option>
							</select>
							<input type=text name="adjQty" value="" style="text-align:right" size=8 tabIndex=<%=(ipl*2) + 1%> readonly>
							<tsa:hidden name="ReceiptLine_icRecHeader" value="<%=receiptHeader.getIcRecHeader()%>"/>
							<tsa:hidden name="ReceiptLine_icRecLine" value=""/>
							<tsa:hidden name="ReceiptLine_icPoLine" value="<%=poLine.getIcPoLine()%>"/>
							<tsa:hidden name="ReceiptLine_icReqLine" value="<%=poLine.getIcReqLine() %>"/>
							<tsa:hidden name="ReceiptLine_icLineHistory" value="<%=poLine.getIcLineHistory() %>"/>

							<tsa:hidden name="ReceiptLine_qtyReceived" value=""/>
							<tsa:hidden name="ReceiptLine_qtyRejected" value=""/>
							<tsa:hidden name="ReceiptLine_qtyAccepted" value=""/>
							<tsa:hidden name="rcl_qtyReceived" value=""/>
							<tsa:hidden name="qtyReceived" value="<%=HiltonUtility.getFormattedQuantity(totalQtyReceived, oid)%>"/>
							<tsa:hidden name="qtyRejected" value="<%=HiltonUtility.getFormattedQuantity(totalQtyRejected, oid)%>"/>
							<tsa:hidden name="qtyAccepted" value="<%=HiltonUtility.getFormattedQuantity(totalQtyAccepted, oid)%>"/>
							<tsa:hidden name="ReceiptLine_itemLocation" value="<%=poHeader.getItemLocation() %>"/>
						</td>
						<td rowspan=3></td>
						<td rowspan=3><textarea name="ReceiptLine_receiptNotes" cols=40 rows=4  onKeyDown="textCounter(this, 255);" onKeyUp="textCounter(this,255);"></textarea></td>
					</tr>
					<tr>
<% if (isDualUm) { %>
						<td colspan=2 align=right>Secondary UOM:&nbsp;&nbsp;&nbsp;</td>
						<td <%=HtmlWriter.isVisible(oid, "rec-umCode")%>class=browseRow vAlign=top><%=duomUmCode%></td>
						<td align=right>
							<select name="receivedDuomAdjFactor" tabIndex=<%=(ipl*2)%> onchange="setAccepted(<%=ipl%>); void(0);" style="font-size:9px">
								<option value="1.0">+</option>
								<option value="-1.0">-</option>
							</select>
							<input type=text name="receivedDuomAdjQty" value="" style="text-align:right" size=8 onchange="setAccepted(<%=ipl%>); void(0);" tabIndex=<%=(ipl*2) + 1%>>
						</td>
						<td rowspan=3></td>
<% } else { %>
					<td>
						<tsa:hidden name="receivedDuomAdjQty" value="0"/>
					</td>

<% } %>
					</tr>
					<tr>
						<td colspan=3>&nbsp;</td>
						<td colspan=3 height=10px><hr size=0 valign=top></td>
					</tr>
					<tr>
						<td colspan=3 align=right valign=top height=10px nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-adjustedQuantity", "Adjusted Qty")%>:</b></td>
						<td id="updatedQtyReceived" valign=top align=right height=10px nowrap><%=HiltonUtility.getFormattedQuantity(totalQtyReceived, oid)%></td>
						<td id="updatedQtyRejected" valign=top align=right height=10px nowrap><%=HiltonUtility.getFormattedQuantity(totalQtyRejected, oid)%></td>
						<td id="updatedQtyAccepted" valign=top align=right height=10px nowrap><%=HiltonUtility.getFormattedQuantity(totalQtyAccepted, oid)%></td>
					</tr>
					</table>
					</div>
<%			if (receiptLineList != null && receiptLineList.size() > 0) {%>
					<div id="receiptHistory">
					<table border=0 cellspacing=0 cellpadding=0 width=100%>
					<tr><td align=right><a href="javascript: viewReceiptHistory(<%=ipl%>); void(0);">View Receipt History</a></td></tr>
					</table>
					</div>
<%			} else {%>
					<div id="receiptHistory">
					<table border=0 cellspacing=0 cellpadding=0 width=100%>
					<tr><td align=right>No Receipt History</td></tr>
					</table>
					</div>
<%			}%>
<%@ include file="/receipts/rec_item_history.jsp"%>
					<br>
				</td>
			</tr>
<%			ipl++;
			}
		}%>
		</table>
	</td>
</tr>
</table>

<div id="dummyFields" style="display:none;">
<tsa:hidden name="ReceiptLine_icRecHeader" value=""/>
<tsa:hidden name="ReceiptLine_icRecLine" value=""/>
<tsa:hidden name="ReceiptLine_icPoLine" value=""/>
<tsa:hidden name="ReceiptLine_notes" value=""/>
<tsa:hidden name="ReceiptLine_qtyReceived" value=""/>
<tsa:hidden name="ReceiptLine_qtyRejected" value=""/>
<tsa:hidden name="ReceiptLine_qtyAccepted" value=""/>
<tsa:hidden name="rcl_qtyReceived" value=""/>
<tsa:hidden name="qtyReceived" value=""/>
<tsa:hidden name="qtyRejected" value=""/>
<tsa:hidden name="qtyAccepted" value=""/>
<tsa:hidden name="receivedAdjFactor" value = ""/>
<tsa:hidden name="receivedAdjQty" value = ""/>
<tsa:hidden name="rejectedAdjFactor" value = ""/>
<tsa:hidden name="rejectedAdjQty" value = ""/>
<tsa:hidden name="adjFactor" value = ""/>
<tsa:hidden name="adjQty" value = ""/>
<tsa:hidden name="qtyPrevRejected" value=""/>
</div>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('receipts/rec_confirmation.jsp', 'ReceiptAdjustment'); void(0);">Finalize</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('menu/main_menu.jsp', 'DoNothing'); void(0);">Cancel</a></div></td>
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

	var qtyDecimals = <%=Integer.valueOf(quantityDecimals).intValue()%>;

	hideLineWithoutReceipt();

	function checkBalance(row) {
		var adjQty = nformat(eval(nfilter(frm.adjQty[row])), qtyDecimals);
		var adjFactor = frm.adjFactor[row][frm.adjFactor[row].selectedIndex].value;

		frm.adjQty[row].value = adjQty;

		adjQty = eval(adjQty * adjFactor);
		var receiptQty = eval(nfilter(frm.qtyReceived[row]));
		var newBalance = nformat(eval(receiptQty + adjQty), qtyDecimals);
		if (newBalance < 0) {
			alert("You cannot make a negative adjustment greater than the receipt quantity.");

			var correctionQty = eval(0 - newBalance);
			var receivedAdjQty = eval(nfilter(frm.receivedAdjQty[row]));
			var receivedAdjFactor = frm.receivedAdjFactor[row][frm.receivedAdjFactor[row].selectedIndex].value;

			receivedAdjQty = eval(receivedAdjQty * receivedAdjFactor);
			receivedAdjQty = eval(receivedAdjQty + correctionQty);

			if (receivedAdjQty > 0) {
				frm.receivedAdjFactor[row].value = "1.0";
			} else {
				frm.receivedAdjFactor[row].value = "-1.0";
				receivedAdjQty = eval(receivedAdjQty * -1.0);
			}
			frm.receivedAdjQty[row].value = receivedAdjQty;

			frm.adjQty[row].value = nformat(receiptQty, qtyDecimals);
			frm.adjQty[row].select();
			newBalance = "0.00";
		} else {
			frm.ReceiptLine_qtyReceived[row].value = adjQty;
		}
		var d = document.all("updatedQty");
		if (d.length > 1) {
			d(row).innerHTML = nformat(newBalance, qtyDecimals);
		} else {
			d.innerHTML = nformat(newBalance, qtyDecimals);
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

	function hideLineWithoutReceipt() {
		var myDivs = document.all("items");
		var myItemTable = document.all("itemTable");

		if (myDivs != null && myDivs != undefined) {
			if (myDivs.length > 1) {
				for (var i=myDivs.length - 1; i >= 0 ; i--){
					var myTable = document.all("itemsTable")(i);
					var rows = myTable.rows.length;
					if (rows <= 4) {
						var row = myItemTable.rows((i*2) + 1);
						row.style.display = "none";

						row = myItemTable.rows(i*2);
						row.style.display = "none";
					}
				}
			} else {
				var myTable = document.all("itemsTable");
				var rows = myTable.rows.length;
				if (rows <= 4) {
					myDivs.style.visiblity = "hidden";
					myDivs.style.display = "none";
				}
			}
		}
	}

	function viewAssetRelated(iclinekey) {
		var newInputField = "<input type='hidden' name='Asset_icLineKey' value='" + iclinekey + "'>";
			newInputField = newInputField + "<input type='hidden' name='allowBrowse' value='true'>";
			newInputField = newInputField + "<input type='hidden' name='action'		value='rec-adjustment'>";
			newInputField = newInputField + "<input type='hidden' name='process'	value='AssetRetrieveByIcLineKey'>";
			newInputField = newInputField + "<input type='hidden' name='urlret'		value='/receipts/rec_adjustment'>";
		setHiddenFields(newInputField);
		doSubmit("/asset/asset_rec_browse.jsp", "AssetRetrieveByIcLineKey");
	}

	function getReceivedAdjQty(row) {
		var receivedAdjQty = nformat(eval(nfilter(frm.receivedAdjQty[row])), qtyDecimals);
		var receivedAdjFactor = frm.receivedAdjFactor[row][frm.receivedAdjFactor[row].selectedIndex].value;

		frm.receivedAdjQty[row].value = receivedAdjQty;

		receivedAdjQty = eval(receivedAdjQty * receivedAdjFactor);

		return receivedAdjQty;
	}

	function getRejectedAdjQty(row) {
		var rejectedAdjQty = nformat(eval(nfilter(frm.rejectedAdjQty[row])), qtyDecimals);
		var rejectedAdjFactor = frm.rejectedAdjFactor[row][frm.rejectedAdjFactor[row].selectedIndex].value;

		frm.rejectedAdjQty[row].value = rejectedAdjQty;

		rejectedAdjQty = eval(rejectedAdjQty * rejectedAdjFactor);

		return rejectedAdjQty;
	}

	function getAcceptedAdjQty(row) {
		var acceptedAdjQty = nformat(eval(nfilter(frm.adjQty[row])), qtyDecimals);
		var acceptedAdjFactor = frm.adjFactor[row][frm.adjFactor[row].selectedIndex].value;

		acceptedAdjQty = eval(acceptedAdjQty * acceptedAdjFactor);

		return acceptedAdjQty;
	}

	function checkReceivedBalance(row) {
		var receivedAdjQty = getReceivedAdjQty(row);
		var origReceivedQty = eval(nfilter(frm.qtyReceived[row]));
		var newBalance = nformat(eval(origReceivedQty + receivedAdjQty), qtyDecimals);

		if (newBalance < 0) {
			alert("You cannot make a negative adjustment greater than the original received quantity.");
			frm.receivedAdjQty[row].value =nformat(origReceivedQty, qtyDecimals);
			frm.receivedAdjQty[row].select();
			newBalance = "0.00";
		} else {
			frm.ReceiptLine_qtyReceived[row].value = receivedAdjQty;
		}

		var d = document.all("updatedQtyReceived");
		if (d.length > 1) {
			d(row).innerHTML = nformat(newBalance, qtyDecimals);
		} else {
			d.innerHTML = nformat(newBalance, qtyDecimals);
		}
	}


	function checkRejectedBalance(row) {
		var rejectedAdjQty = getRejectedAdjQty(row);
		var origRejectedQty = eval(nfilter(frm.qtyRejected[row]));
		var newBalance = nformat(eval(origRejectedQty + rejectedAdjQty), qtyDecimals);
		if (newBalance < 0) {
			alert("You cannot make a negative adjustment greater than the original rejected quantity.");
			frm.rejectedAdjQty[row].value =nformat(rejectedAdjQty, qtyDecimals);
			frm.rejectedAdjQty[row].select();
			newBalance = "0.00";
		} else {
			var newPrevRejectedReturnQty = frm.qtyPrevRejected[row].value + rejectedAdjQty;

			if (newPrevRejectedReturnQty < 0) {
				alert("You cannot make a negative rejected quantity adjustment greater than " + (newPrevRejectedReturnQty*-1.0) + "  (At least some of the items previously rejected have already been returned.)");

			} else {

			}
			frm.ReceiptLine_qtyRejected[row].value = rejectedAdjQty;
		}

		var d = document.all("updatedQtyRejected");
		if (d.length > 1) {
			d(row).innerHTML = nformat(newBalance, qtyDecimals);
		} else {
			d.innerHTML = nformat(newBalance, qtyDecimals);
		}
	}

	function setAccepted(row) {
		var receivedAdjQty = eval(getReceivedAdjQty(row));
		var rejectedAdjQty = eval(getRejectedAdjQty(row));
		var acceptedAdjQty = eval(receivedAdjQty - rejectedAdjQty);
		var origAcceptedQty = eval(nfilter(frm.qtyAccepted[row]));
		var newBalance = nformat(eval(origAcceptedQty + acceptedAdjQty), qtyDecimals);

		frm.ReceiptLine_qtyAccepted[row].value = acceptedAdjQty;

		if (acceptedAdjQty < 0) {
			frm.adjFactor[row].value = "-1.0";
			acceptedAdjQty = eval(acceptedAdjQty * -1.0);
		}

		frm.adjQty[row].value = nformat(acceptedAdjQty, qtyDecimals);
		//frm.adjQty[row].fireEvent("onchange");

		var d = document.all("updatedQtyAccepted");
		if (d.length > 1) {
			d(row).innerHTML = nformat(newBalance, qtyDecimals);
		} else {
			d.innerHTML = nformat(newBalance, qtyDecimals);
		}
	}


	function validateForm() {
		var handlers = frm.handler.value;
		if (handlers.indexOf("ReceiptAdjustment") >=0) {
			var myItems = document.all("ReceiptLine_qtyReceived");

			if (myItems != null && myItems != undefined) {
				for (var i=0; i < myItems.length - 1; i++) {
					var acceptedAdjQty = getAcceptedAdjQty(i);
					var totalQtyAccepted = eval(frm.qtyAccepted[i].value);
					var newBalance = eval(totalQtyAccepted + acceptedAdjQty);

					if (newBalance < 0) {
						alert("You cannot make an adjustment resulting in a negative accepted quantity.");
						frm.receivedAdjQty[i].focus();
						frm.receivedAdjQty[i].select();
						return false;
					}
				}
			}
		}
		return true;
	}

// End Hide script -->
</SCRIPT>
