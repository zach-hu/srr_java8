<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.ReceiptType" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String hideForwardToSelectionProperty =
		propertiesManager.getProperty("REC SELECTIONS","HIDEFORWARDTOSELECTION","N");
	String hideForwardToForAll = propertiesManager.getProperty("REC SELECTIONS", "HIDEFORWARDTOONALL", "N");
	List trackingList = (List)request.getAttribute("trackingList");

	ReceiptHeader receiptHeader = (ReceiptHeader) request.getAttribute("receiptHeader");
	BigDecimal bd_ic_rec_header = receiptHeader.getIcRecHeader();
	String	s_rec_number = receiptHeader.getReceiptNumber();
	String	s_rec_type = receiptHeader.getReceiptType();
	String	s_rec_status = receiptHeader.getReceiptStatus();
	String	s_rec_fiscal_year = receiptHeader.getFiscalYear();
	String  trackingNumber =	propertiesManager.getProperty("REC DEFAULTS","TRACKINGNUMBER","N");
	String  s_msr_number = "";
	if (HiltonUtility.isEmpty(s_rec_status))
		s_rec_status = DocumentStatus.RCV_INPROGRESS;

	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	if (poHeader != null) {
		s_msr_number = poHeader.getMsrNumber();
	}
	if (poHeader == null) {
		poHeader = new PoHeader();
	}
	String	s_po_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_poNumber"));
	if (HiltonUtility.isEmpty(s_po_number)) {
		s_po_number = poHeader.getPoNumber();
	}
	String	s_revision_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_revisionNumber"));
	String	s_release_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_releaseNumber"));
	if (HiltonUtility.isEmpty(s_revision_number)) {
		s_revision_number = poHeader.getRevisionNumber().toString();
	}
	if (HiltonUtility.isEmpty(s_release_number)) {
		s_release_number = poHeader.getReleaseNumber().toString();
	}
	BigDecimal	bd_revision_number = new BigDecimal(s_revision_number);
	BigDecimal	bd_release_number = new BigDecimal(s_release_number);

	String	defaultForwardTo = "";
	List poLineList = (List)request.getAttribute("poLineList");
	if (poLineList != null && poLineList.size() > 0) {
		for (int il=0; il < poLineList.size(); il++) {
			PoLine poLine = (PoLine) poLineList.get(il);
			if (poLine != null) {
				BigDecimal balanceQty = (poLine.getQuantity()).subtract(poLine.getQtyReceived());
				String	endUser = poLine.getRequisitionerCode();
				if (HiltonUtility.isEmpty(endUser))
					endUser = poHeader.getRequisitionerCode();
				if (HiltonUtility.isEmpty(endUser))
					endUser = poHeader.getBuyerCode();
				if (il == 0)
					defaultForwardTo = endUser;
				if (!endUser.equals(defaultForwardTo))
					defaultForwardTo = "";
			}
		}
	}

	String	receiptMethod = HiltonUtility.ckNull((String)request.getAttribute("receiptMethod"));
	String	s_current_process = "HEADER_GENERAL_INFO";
	String	s_current_page = "/receipts/rec_general_info.jsp";
	String	s_current_method = "ReceiptHeaderUpdate";
	String	s_current_process_method = "";

	BigDecimal	bd_zero = new BigDecimal(0);

	boolean allowEdit = false;
	if ((receiptMethod.equals("ReceiveByPackage") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0) ||
		(receiptMethod.equals("FinalizeReceipt") && s_rec_status.compareTo(DocumentStatus.RCV_PENDINGFINALIZATION) == 0) ||
		(receiptMethod.equals("ReceiveFromNothing") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0) ||
		(receiptMethod.equals("ReceiveByOrder") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0))
		allowEdit = true;
%>

<tsa:hidden name="ReceiptHeader_icRecHeader" value="<%=bd_ic_rec_header%>"/>
<tsa:hidden name="ReceiptHeader_receiptNumber" value="<%=s_rec_number%>"/>
<tsa:hidden name="ReceiptHeader_receiptType" value="<%=s_rec_type%>"/>
<tsa:hidden name="ReceiptHeader_receiptStatus" value="<%=s_rec_status%>"/>
<tsa:hidden name="ReceiptHeader_fiscalYear" value="<%=s_rec_fiscal_year%>"/>
<tsa:hidden name="ReceiptLine_icRecHeader" value="<%=bd_ic_rec_header%>"/>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=receiptHeader.getIcPoHeader()%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=receiptHeader.getIcPoHeader()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=bd_ic_rec_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="RCH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=bd_ic_rec_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=bd_ic_rec_header%>"/>
<tsa:hidden name="receiptMethod" value="<%=receiptMethod%>"/>
<tsa:hidden name="formType" value="REC"/>
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "package_information", "Package Information")%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td>
		<%	if (!HiltonUtility.isEmpty(s_po_number)) { %>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "order", "Order", false)%> #:</b>
			&nbsp;<%=headerEncoder.encodeForHTML(s_po_number)%>
		<%	} else { %>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "order", "Order", false)%> #:</b>
			&nbsp;<a href="javascript: getOrder(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "attachToOrder", "Attach to Order")%></a>
		<%	} %>
			</td>
			<td align=right><b>Receipt #:</b></td>
		<%	if (HiltonUtility.isEmpty(s_rec_number)) { %>
			<td width=100px>N/A</td>
		<%	} else { %>
			<td width=100px><%=s_rec_number%></td>
		<%	} %>
		</tr>
		<tr>
			<td>
		<%	if (bd_revision_number.compareTo(bd_zero) > 0) { %>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Revision #:</b>&nbsp;<%=bd_revision_number%>
		<%	} %>
		<%	if (bd_release_number.compareTo(bd_zero) > 0) { %>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Release #:</b>&nbsp;<%=bd_release_number%>
		<%	} %>
			</td>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=120px><%=DocumentStatus.toString(s_rec_status, oid)%></td>
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
<%@ include file="/receipts/rec_info.jsp" %>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top align=center>
		<table border=0 cellspacing=0 cellpadding=0 height=100%>
		<tr>
			<td>
				<table border=0 cellpadding=0 cellspacing=0 width=250px align=center>
					<tr <%=HtmlWriter.isVisible(oid, "rec-receiptDate")%> height=20px>
						<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receiptDate", "Receipt Date")%>:&nbsp;</b></td>
						<td nowrap><tsa:hidden name="ReceiptHeader_receiptDate" value="<%=HiltonUtility.getFormattedDate(receiptHeader.getReceiptDate(), oid, userDateFormat)%>"/><%=HiltonUtility.getFormattedDate(receiptHeader.getReceiptDate(), oid, userDateFormat)%></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "rec-receivedBy")%> height=20px>
						<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receivedBy", "Received By")%>:&nbsp;</b></td>
						<td nowrap><tsa:hidden name="ReceiptHeader_receivedBy" value="<%=receiptHeader.getReceivedBy()%>"/><%=receiptHeader.getReceivedBy()%></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "rec-receivedBy")%> height=20px>
						<td nowrap align=right>&nbsp;</td>
						<td nowrap><%=UserManager.getInstance().getUser(oid, receiptHeader.getReceivedBy()).getDisplayName()%></td>
					</tr>
					<tsa:tr field="rec-inventoryLocation">
						<td nowrap align=right><a href="javascript: browseLookup('ReceiptHeader_itemLocation', 'item_location'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-inventoryLocation", "Inventory Location")%> for this receipt or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-inventoryLocation", "Inventory Location", true)%>:</a>&nbsp;</td>
						<td nowrap><input type=text name="ReceiptHeader_itemLocation" value="<%=receiptHeader.getItemLocation()%>" tabIndex=6 onchange="upperCase(this);"></td>
					</tsa:tr>
					<tr <%=HtmlWriter.isVisible(oid, "rec-numberOfPackages")%>>
						<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-numberOfPackages", "# of Packages", true)%>:&nbsp;</b></td>
						<td nowrap><input type=text name="ReceiptHeader_pkgsReceived" value="<%=receiptHeader.getPkgsReceived().setScale(0, BigDecimal.ROUND_HALF_UP)%>" style="text-align:right" tabIndex=2 size=10 onchange="formatPkgQty()"></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "rec-packingSlip")%>>
						<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-packingSlip", "Packing Slip", true)%>:&nbsp;</td>
						<td nowrap><input type=text name="ReceiptHeader_packingSlip" value="<%=receiptHeader.getPackingSlip()%>" tabIndex=4 onchange="upperCase(this);"></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "rec-carrierCode")%>>
						<td nowrap align=right><b><a href="javascript: browseStd('ReceiptHeader_carrierCode', 'CARR'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-carrierCode", "Carrier Code")%> for this receipt or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-carrierCode", "Carrier Code", true)%>:</a>&nbsp;</b></td>
						<td nowrap><input type=text name="ReceiptHeader_carrierCode" value="<%=receiptHeader.getCarrierCode()%>" tabIndex=6 onchange="upperCase(this);"></td>
					</tr>
					<%-- 	if (!HiltonUtility.isEmpty(poHeader.getMsrNumber())) { --%>
					<tr <%=HtmlWriter.isVisible(oid, "po-msrNumber")%>>
						<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-msrNumber", "MSR #", true)%>:&nbsp;</td>
						<td nowrap><input type=text name="poMsrNumber" value="<%=s_msr_number%>" tabIndex=6 onchange="upperCase(this);"></td>
					</tr>
					<%-- } --%>
					<% if ((!"Y".equals(hideForwardToSelectionProperty) || "A".equals(s_rec_type)) && !hideForwardToForAll.equals("Y")){ %>
						<tr>
							<td nowrap align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-forwardTo", "Forward To")%>:&nbsp;</td>
							<td nowrap>
								<table border=0 cellpadding=1 cellspacing=0>
								<tr>
									<td align=right><input type=radio name=forwardToType value="E" onclick="setForwardTo();"></td>
									<td nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-endUser", "End User")%></td>
									<td align=right><input type=radio name=forwardToType value="S" onclick="setForwardTo();"></td>
									<td nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-specifyUser", "Specify User")%></td>
								</tr>
								</table>
							</td>
						</tr>
					<% } %>
					<tr>
						<td>&nbsp;</td>
						<td nowrap>
							<div id=forwardToSelection style="visibility:hidden; display:block;">
							<table border=0 cellpadding=1 cellspacing=0>
							<tr>
								<td><input type=text name="as_forwardTo" value="<%=receiptHeader.getForwardTo()%>" onchange="upperCase(this); setForwardTo(); getNewInfo('receiver', this);" tabIndex=8></td>
								<td><a href="javascript: setEndUsersChecked(false); browseLookup('ReceiptHeader_forwardTo', 'receiver');void(0);"><img src="<%=contextPath%>/images/browser.gif" border=0></a></td>
							</tr>
							<tr>
								<td colspan=2>
									<tsa:hidden name="ReceiptHeader_forwardTo" value="<%=receiptHeader.getForwardTo()%>"/>
									<input type=text name="as_forwardToName" value="<%=UserManager.getInstance().getUser(oid, receiptHeader.getForwardTo()).getDisplayName()%>" disabled size=25>
								</td>
							</tr>
							</table>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan=2>
							<table border=0 cellspacing=0 cellpadding=0 class=browseRow>
							<tr <%=HtmlWriter.isVisible(oid, "rec-receiptNotes")%>>
								<td nowrap align=right valign=top width=93px><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receiptNotes", "Notes", true)%>:&nbsp;</b></td>
								<td><textarea name="ReceiptHeader_receiptNotes" cols=60 rows=3  onKeyDown="textCounter(this, 225);" onKeyUp="textCounter(this,225);" tabIndex=10>${esapi:encodeForHTML(receiptHeader.receiptNotes)}</textarea></td>
							</tr>
							</table>
						<td>
					</tr>
				</table>
			</td>
			<% if(trackingNumber.equalsIgnoreCase("Y")) { %>
			<td>
				<table border=0 cellpadding=0 cellspacing=0 width=250px align=top>
					<tr>
						<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-proNumber", "PRO #'s")%>:&nbsp;</b></td>
						<td nowrap><INPUT TYPE="hidden" NAME="trackingNumber" size="30" MAXLENGTH="50" value="" ONCHANGE="upperCase(this);"></td>
						<td>
							<div class=browseHdrDk style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 200px; height: 80px; align:center; overflow-y:auto">
								<table id="proRecords" border=0 cellspacing=0 cellpadding=2>
								<% if (trackingList != null) {
									for (int j = 0; j < trackingList.size(); j++)
									{
										TrackingData trackingData = (TrackingData) trackingList.get(j) ;
									%>
									<tr><td><a href="javascript: viewTracking(); void(0);"><%=headerEncoder.encodeForHTML(trackingData.getTrackingNumber()) %></a></td></tr>
								<%	}
								}%>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td align=center colspan=3><a href="javascript: addNewTracking(); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-addProNumber", "Add PRO #")%> for this solicitation or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-addProNumber", "Add PRO #")%> in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-addProNumber", "Add PRO #", true)%></a></td>
					</tr>
				</table>
			</td>
			<% } %>
		</tr>
		</table>
	</td>
<% if (s_view.equals("WIZARD")) { %>
	<td rowspan=2 valign=top align=right><%@ include file="/receipts/rec_sidebar.jsp" %></td>
<% } %>
</tr>
</table>
<br>
<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var recNumber = "<%=s_rec_number%>";
	var fiscalyear = "<%=s_rec_fiscal_year%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

	var defaultForwardTo = "<%=defaultForwardTo%>";
	var defaultForwardToName = "<%=UserManager.getInstance().getUser(oid, defaultForwardTo).getDisplayName()%>";

	function thisLoad()
	{
		f_StartIt();
	<%	if (!allowEdit) { %>
		checkInputSettings();
	<%	} %>
	}

<%if (!hideForwardToForAll.equals("Y")){
	if (!"Y".equals(hideForwardToSelectionProperty) || "A".equals(s_rec_type)) { %>
		if (!isEmpty(frm.as_forwardToName.value) && frm.as_forwardToName.value != "END-USERS") {
		frm.forwardToType[1].checked = true;
		frm.forwardToType[1].fireEvent("onclick");
	} else {
		if (isEmpty(defaultForwardTo) || defaultForwardTo == "END-USERS") {
			frm.forwardToType[0].checked = true;
			frm.forwardToType[0].fireEvent("onclick");
		} else {
			frm.forwardToType[1].checked = true;
			frm.forwardToType[1].fireEvent("onclick");
			frm.ReceiptHeader_forwardTo.value = defaultForwardTo;
			frm.as_forwardTo.value = defaultForwardTo;
			frm.as_forwardToName.value = defaultForwardToName;
		}
	}
<% }
}%>
<% if (!hideForwardToForAll.equals("Y")) {%>
	function setForwardToEndUsers() {
		if (frm.forwardToType.value == "E") {
			frm.as_forwardTo.value = "";
			frm.as_forwardToName.value = "";
			frm.ReceiptHeader_forwardTo.value = "END-USERS";
			hideArea("forwardToSelection");
		} else {
			displayArea("forwardToSelection");
			frm.ReceiptHeader_forwardTo.value = "";
			frm.as_forwardTo.focus();
		}
	}

	function setForwardTo() {
		if (frm.forwardToType[1].checked) {
			upperCase(frm.ReceiptHeader_forwardTo);
			frm.ReceiptHeader_forwardTo.value = frm.as_forwardTo.value;
			displayArea("forwardToSelection");
		} else {
			frm.as_forwardTo.value = "";
			frm.as_forwardToName.value = "";
			frm.ReceiptHeader_forwardTo.value = "END-USERS";
			hideAreaWithBlock("forwardToSelection");
		}
	}

	function setEndUsersChecked(setChecked) {
		if (setChecked) {
			frm.forwardToType[0].checked = true;
			frm.forwardToType[0].fireEvent("onclick");
		} else {
			frm.forwardToType[1].checked = true;
			frm.forwardToType[1].fireEvent("onclick");
		}
	}
<%} %>
	function formatPkgQty() {
		frm.ReceiptHeader_pkgsReceived.value = nformat(eval(nfilter(frm.ReceiptHeader_pkgsReceived)), 0);
	}

	function addNewTracking()
	{	
		//popupParameters += ";trackingNumber=" + frm.trackingNumber.value;
		popupParameters += ";receiptMethod=" + frm.receiptMethod.value;
		var newInputField = "<input type='hidden' name='viewTrackingNumber' value='Y'>";		
	    setHiddenFields(newInputField);
		doSubmit('/receipts/rec_tracking_info.jsp','ReceiptHeaderUpdate;RetrieveTrackingNumber');
	}

	function viewTracking()
	{
		//popupParameters += ";trackingNumber=" + frm.trackingNumber.value;
		var newInputField = "<input type='hidden' name='viewTrackingNumber' value='N'>";		
	    setHiddenFields(newInputField);
		popupParameters += ";receiptMethod=" + frm.receiptMethod.value;
		doSubmit('/receipts/rec_tracking_info.jsp','ReceiptHeaderUpdate;RetrieveTrackingNumber');
	}

	function getOrder()
	{
		popupParameters = "browseName=rec-get-order";
		doSubmitToPopup('browse/browse_rec_po.jsp', 'BrowseRetrieve', 'WIDTH=700', 'HEIGHT=500');
	}

// End Hide script -->
</SCRIPT>