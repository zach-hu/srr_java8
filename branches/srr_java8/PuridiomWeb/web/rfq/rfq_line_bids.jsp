<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.util.List.*" %>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String s_price_decimals = propertiesManager.getProperty("MISC", "PriceDecimals", "2");
	String s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");

	RfqLine rfqLine = (RfqLine) request.getAttribute("rfqLine");
	String s_rfqStatus = (String) request.getAttribute("RfqHeader_status");
	int i;int b;
	BigDecimal bd_icRfqHeader = rfqLine.getIcRfqHeader();
	BigDecimal bd_icRfqLine = rfqLine.getIcRfqLine();
	BigDecimal bd_line_number = rfqLine.getRfqLine().setScale(0, BigDecimal.ROUND_HALF_UP);
	String s_rfqNumber = rfqLine.getRfqNumber();
	BigDecimal bd_quantity = rfqLine.getQuantity();
	BigDecimal bd_umFactor = rfqLine.getUmFactor();
	if (bd_umFactor==null || bd_umFactor.compareTo(new BigDecimal(0)) <= 0) {bd_umFactor = new BigDecimal(1);}
	String s_commodity = rfqLine.getCommodity();

	List lineBidList = (List) rfqLine.getRfqBidList();
	String s_line_count = (String) request.getAttribute("lineCount");
	String s_rfqAmendment = (String) request.getAttribute("RfqHeader_rfqAmendment");
	boolean editMode = false;

	if (role.getAccessRights("RFQ") > 1 && (s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) < 0 || s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENAMENDMENT) == 0)) {
		editMode = true;
	}
%>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=bd_icRfqHeader%>"/>
<tsa:hidden name="RfqHeader_status" value="<%=s_rfqStatus%>"/>
<tsa:hidden name="RfqVendor_icRfqHeader" value="<%=bd_icRfqHeader%>"/>
<tsa:hidden name="RfqLine_icRfqHeader" value="<%=bd_icRfqHeader%>"/>
<tsa:hidden name="RfqLine_icRfqLine" value="<%=bd_icRfqLine%>"/>
<tsa:hidden name="RfqBid_icRfqLine" value="<%=bd_icRfqLine%>"/>
<tsa:hidden name="RfqBid_icRfqHeader" value="<%=bd_icRfqHeader%>"/>
<tsa:hidden name="RfqNote_icLine" value="<%=bd_icRfqLine%>"/>
<tsa:hidden name="RfqNote_icHeader" value="<%=bd_icRfqHeader%>"/>
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_rfqNumber%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="<%=s_rfqAmendment%>"/>
<tsa:hidden name="lineCount" value="<%=s_line_count%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="currentVendorId" value=""/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Bids for Line Item <%=bd_line_number%> of <%=s_line_count%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<%@ include file="/rfq/rfq_status_title.jsp" %>
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
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center>
		<table border=0 cellspacing=0 cellpadding=2 width=75% class=browseRow>
			<tr>
				<td width=25% nowrap align=right valign=top><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-itemNumber", "Item/Part #")%>:&nbsp;</b></td>
				<td colspan=3 width=75%><%=rfqLine.getItemNumber()%></td>
			</tr>
			<tr>
				<td width=25% nowrap align=right valign=top><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-description", "Description")%>:&nbsp;</b></td>
				<td colspan=3 width=75%><%=rfqLine.getDescription()%></td>
			</tr>
			<tr>
				<td width=25% nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-commodity", "Commodity")%>:</b></td>
				<td width=25% nowrap><%=rfqLine.getCommodity()%></td>

			</tr>
			<tr>
				<td width=25% nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-quantityRequested", "Quantity Requested")%>:&nbsp;</b></td>
				<td width=25% nowrap>
					<%=HiltonUtility.getFormattedQuantity(rfqLine.getQuantity(), oid)%>&nbsp;(<%=rfqLine.getUmCode()%>)
					<tsa:hidden name="RfqLine_quantity" value="<%=bd_quantity%>"/>
					<tsa:hidden name="RfqLine_umFactor" value="<%=bd_umFactor%>"/>
				</td>
			</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center>
		<table border=1 cellspacing=0 cellpadding=0 width=75% class=browseHdr>
		<tr>
			<td class=browseHdr height=18px nowrap width=100%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
				<tr class=browseHdr>
					<td width=25% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-supplier", "Supplier")%></td>
					<td width=35% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-unitCost", "Unit Cost")%></td>
					<td width=30% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-extendedCost", "Extended Cost")%></td>
					<td width=10% class=browseHdr nowrap align=center>Notes</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<tsa:hidden name="RfqNote_vendorId" value=""/>
<%
	if (lineBidList!=null)
	{
		for(i = 0; i < lineBidList.size(); i++)
		{
			RfqBid rfqBid = (RfqBid)lineBidList.get(i);
			RfqBidPK rfqBidPK = (RfqBidPK) rfqBid.getComp_id();

			String s_bidVendorId = rfqBidPK.getVendorId();
			String s_bidUmCode = rfqBid.getUmCode();
			if (s_bidUmCode==null){s_bidUmCode = "EA";}
			BigDecimal bd_bidUnitPrice = HiltonUtility.getFormattedPrice(rfqBid.getUnitPrice(), oid);
			//if (bd_bidUnitPrice==null){bd_bidUnitPrice = new BigDecimal(0.00);}
			//bd_bidUnitPrice = bd_bidUnitPrice.setScale(Integer.valueOf(s_price_decimals).intValue(),BigDecimal.ROUND_HALF_UP);
			String s_bidUnitPrice = bd_bidUnitPrice.toString();
			String s_bidCode = rfqBid.getBidCode();

			if (s_bidCode.equalsIgnoreCase("NC")) {
				s_bidUnitPrice = "No Charge";
			}
			else if (s_bidCode.equalsIgnoreCase("NSP")) {
				s_bidUnitPrice = "Not Separately Priced";
			}
			else if (s_bidCode.equalsIgnoreCase("SN")) {
				s_bidUnitPrice = "See Notes";
			}
			else if (s_bidCode.equalsIgnoreCase("NB")) {
				s_bidUnitPrice = "No Bid";
			}
			else if (s_bidCode.equalsIgnoreCase("NE")) {
				s_bidUnitPrice = "None Entered";
			}
			else if (s_bidCode.equalsIgnoreCase("00")) {
				//frm.unit_price[ir].value = frm.rfb_unit_price[ir].value;
			}
			else {
				//s_bidCode == "";
				//s_bidUnitPrice = "None Entered";
				//frm.rfb_unit_price[ir].value = "0.00";
			}

			BigDecimal bd_bidExtendedPrice = new BigDecimal(0);
			bd_bidExtendedPrice = ( (bd_quantity.multiply(bd_bidUnitPrice)).multiply(bd_umFactor) ).setScale(Integer.valueOf(s_dollar_decimals).intValue(),BigDecimal.ROUND_HALF_UP);
%>
				<tr class=browseRow>
					<td width=25% class=browseRow><a href="javascript: viewSupplier('<%=rfqBidPK.getVendorId()%>'); void(0);"><%=s_bidVendorId%></a><tsa:hidden name="RfqBid_vendorId" value="<%=s_bidVendorId%>"/></td>
					<td width=35% class=browseRow align=center>
						<table border=0 cellpadding=0 cellspacing=0>
						<tr>
							<td><input type="text" name="unitPrice" value="<%=s_bidUnitPrice%>" style="text-align:right" size="22" onChange="addup(<%=i%>);"></td>
<%	if (editMode) { %>
							<td><a href="javascript: void(0);" onClick="selectBidCodes(<%=i%>,event)"><img src="<%=contextPath%>/images/dropdown.gif" border=0></a></td>
<%	} %>
						</tr>
						</table>
						<tsa:hidden name="RfqBid_unitPrice" value="<%=bd_bidUnitPrice%>"/>
						<tsa:hidden name="RfqBid_bidCode" value="<%=s_bidCode%>"/>
					</td>
					<td width=30% class=browseRow align=center><input type="text" name="extPrice" value="<%=bd_bidExtendedPrice%>" style="text-align:right" size="15" disabled></td>
					<td width=10% class=browseRow align=center><a href="javascript: viewBidNotes('<%=s_bidVendorId%>'); void(0);"><img src="<%=contextPath%>/images/notes_line.gif" border=0></a></td>
				</tr>
<%		}
	}
	else {%>
				<tr><td colspan=4>&nbsp;</td></tr>
<% } %>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if (editMode) { %>
	<td width=50% align=center><a href="javascript: doSubmit('/rfq/rfq_item.jsp', 'RfqBidUpdate;RfqLineRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
<%	} %>
	<td width=50% align=center><a href="javascript: doSubmit('/rfq/rfq_item.jsp', 'RfqLineRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var currentRow = 0;
	var inputs;

	function thisLoad()
	{
		f_StartIt();
<%	if (!editMode) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
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

	function selectBidCodes (row, event) {
		currentRow = row;
		/*if (bw.ns4) {
			popUp('Menu3',event);
		} else {*/
			popUp('Menu1',event);
		//}
	}

	function setBidCode(x) {
		inputs = frm.elements.item("unitPrice");
		if (inputs.length==undefined)
		{
			frm.RfqBid_bidCode.value = x;
			if (x == "NB") {
				frm.unitPrice.value = "No Bid";
				frm.extPrice.value = "0.0000";
			}
			else if (x == "NC") {
				frm.unitPrice.value = "No Charge";
				frm.extPrice.value = "0.0000";
			}
			else if (x == "NSP") {
				frm.unitPrice.value = "Not Separately Priced";
				frm.extPrice.value = "0.0000";
			}
			else if (x == "SN") {
				frm.unitPrice.value = "See Notes";
				frm.extPrice.value = "0.0000";
			}
			else if (x == "NE") {
				frm.unitPrice.value = "None Entered";
				frm.extPrice.value = "0.0000";
			}
			else {
				frm.unitPrice.value = "0.0000";
				frm.extPrice.value = "0.0000";
				formatPrice(currentRow);
			}
			frm.RfqBid_unitPrice.value = "0.0000";
			//setItemSelected(currentRow);
		}
		else
		{
			frm.RfqBid_bidCode[currentRow].value = x;
			if (x == "NB") {
				frm.unitPrice[currentRow].value = "No Bid";
				frm.extPrice[currentRow].value = "0.0000";
			}
			else if (x == "NC") {
				frm.unitPrice[currentRow].value = "No Charge";
				frm.extPrice[currentRow].value = "0.0000";
			}
			else if (x == "NSP") {
				frm.unitPrice[currentRow].value = "Not Separately Priced";
				frm.extPrice[currentRow].value = "0.0000";
			}
			else if (x == "SN") {
				frm.unitPrice[currentRow].value = "See Notes";
				frm.extPrice[currentRow].value = "0.0000";
			}
			else if (x == "NE") {
				frm.unitPrice[currentRow].value = "None Entered";
				frm.extPrice[currentRow].value = "0.0000";
			}
			else {
				frm.unitPrice[currentRow].value = "0.0000";
				frm.extPrice[currentRow].value = "0.0000";
				formatPrice(currentRow);
			}
			frm.RfqBid_unitPrice[currentRow].value = "0.0000";
			//setItemSelected(currentRow);
		}
	}

	function formatPrice (x) {
		var dollar_dec = <%=Integer.valueOf(s_dollar_decimals).intValue()%>;
		var p = nformat(eval(nfilter(frm.unitPrice[x])),dollar_dec);

		frm.RfqBid_unitPrice[x].value = p;
		frm.unitPrice[x].value = p;

		resetBidCode(x);
	}

	function resetBidCode(x) {
		frm.RfqBid_bidCode[x].value = "00";
	}

	function addup (x)
	{
		inputs = frm.elements.item("unitPrice");
			if (inputs.length==undefined)
			{
				var dollar_dec = <%=Integer.valueOf(s_dollar_decimals).intValue()%>;
				var price_dec = <%=Integer.valueOf(s_price_decimals).intValue()%>;
				var qty_dec = <%=Integer.valueOf(s_quantity_decimals).intValue()%>;

				var p = nformat(eval(nfilter(frm.unitPrice)),price_dec);
				var q = nformat(eval(nfilter(frm.RfqLine_quantity)),qty_dec);
				var f = eval(nfilter(frm.RfqLine_umFactor));

				if (f == 0) { f = 1; }

				frm.RfqBid_unitPrice.value = p;
				frm.unitPrice.value = p;
				frm.extPrice.value = nformat( p * q * f, 2 );
			}
			else
			{
				var dollar_dec = <%=Integer.valueOf(s_dollar_decimals).intValue()%>;
				var price_dec = <%=Integer.valueOf(s_price_decimals).intValue()%>;
				var qty_dec = <%=Integer.valueOf(s_quantity_decimals).intValue()%>;

				var p = nformat(eval(nfilter(frm.unitPrice[x])),price_dec);
				var q = nformat(eval(nfilter(frm.RfqLine_quantity)),qty_dec);
				var f = eval(nfilter(frm.RfqLine_umFactor));

				if (f == 0) { f = 1; }

				frm.RfqBid_unitPrice[x].value = p;
				frm.unitPrice[x].value = p;
				frm.extPrice[x].value = nformat( p * q * f, 2 );
			}
	}

	function viewBidNotes(vendor){
		popupParameters = "RfqNote_icHeader=" + frm.RfqHeader_icRfqHeader.value + ";RfqNote_vendorId=" +  vendor + ";RfqNote_icLine=" +  frm.RfqNote_icLine.value;
		doSubmitToPopup('/rfq/rfq_supplier_notes.jsp', 'RfqNoteRetrieveById');
	}

	function viewSupplier(vendorId){
		frm.currentVendorId.value = vendorId;

		doSubmit('rfq/rfq_supplier_summary.jsp', 'RfqRetrieve');
	}

// End Hide script -->
</SCRIPT>