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

	ReceiptHeader receiptHeader = (ReceiptHeader) request.getAttribute("receiptHeader");
	BigDecimal bd_ic_rec_header = receiptHeader.getIcRecHeader();
	String	s_rec_number = receiptHeader.getReceiptNumber();
	String	s_rec_type = receiptHeader.getReceiptType();
	String	s_rec_status = receiptHeader.getReceiptStatus();
	String	s_rec_fiscal_year = receiptHeader.getFiscalYear();
	if (HiltonUtility.isEmpty(s_rec_status))
		s_rec_status = DocumentStatus.RCV_INPROGRESS;

	RequisitionHeader reqHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
	if (reqHeader == null) {
		reqHeader = new RequisitionHeader();
	}

	String	s_req_number = HiltonUtility.ckNull((String)request.getAttribute("RequisitionHeader_requisitionNumber"));
	if (HiltonUtility.isEmpty(s_req_number)) {
		s_req_number = reqHeader.getRequisitionNumber();
	}

	String	defaultForwardTo = "";
	List reqLineList = (List)request.getAttribute("requisitionLineList");
	if (reqLineList != null && reqLineList.size() > 0) {
		for (int il=0; il < reqLineList.size(); il++) {
			RequisitionLine reqLine = (RequisitionLine) reqLineList.get(il);
			if (reqLine != null) {
				String	endUser = reqLine.getRequisitionerCode();
				if (HiltonUtility.isEmpty(endUser))
					endUser = reqHeader.getRequisitionerCode();
				if (il == 0)
					defaultForwardTo = endUser;
				if (!endUser.equals(defaultForwardTo))
					defaultForwardTo = "";
			}
		}
	}

	String	receiptMethod = HiltonUtility.ckNull((String)request.getAttribute("receiptMethod"));
	String	s_current_process = "HEADER_GENERAL_INFO";
	String	s_current_page = "/receipts/rec_general_info_t.jsp";
	String	s_current_method = "ReceiptHeaderUpdate";
	String	s_current_process_method = "";

	BigDecimal	bd_zero = new BigDecimal(0);

	boolean allowEdit = false;
	if ((receiptMethod.equals("ReceiveByPackage") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0) ||
		(receiptMethod.equals("FinalizeReceipt") && s_rec_status.compareTo(DocumentStatus.RCV_PENDINGFINALIZATION) == 0) ||
		(receiptMethod.equals("ReceiveFromNothing") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0) ||
		(receiptMethod.equals("ReceiveByTransfer") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0) ||
		(receiptMethod.equals("ReceiveByOrder") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0))
		allowEdit = true;
%>

<tsa:hidden name="ReceiptHeader_icRecHeader" value="<%=bd_ic_rec_header%>"/>
<tsa:hidden name="ReceiptHeader_receiptNumber" value="<%=s_rec_number%>"/>
<tsa:hidden name="ReceiptHeader_receiptType" value="<%=s_rec_type%>"/>
<tsa:hidden name="ReceiptHeader_receiptStatus" value="<%=s_rec_status%>"/>
<tsa:hidden name="ReceiptHeader_fiscalYear" value="<%=s_rec_fiscal_year%>"/>
<tsa:hidden name="ReceiptLine_icRecHeader" value="<%=bd_ic_rec_header%>"/>
<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=receiptHeader.getIcReqHeader()%>"/>
<tsa:hidden name="RequisitionLine_icReqHeader" value="<%=receiptHeader.getIcReqHeader()%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_req_number%>"/>

<tsa:hidden name="PoHeader_icPoHeader" value="<%=receiptHeader.getIcPoHeader()%>"/>
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
		<%	if (!HiltonUtility.isEmpty(s_req_number)) { %>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Transfer Request #:</b>&nbsp;<%=headerEncoder.encodeForHTML(s_req_number)%>
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
					<tr <%=HtmlWriter.isVisible(oid, "rec-receiptDate")%>>
						<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receiptDate", "Receipt Date")%>:&nbsp;</b></td>
						<td nowrap><tsa:hidden name="ReceiptHeader_receiptDate" value="<%=HiltonUtility.getFormattedDate(receiptHeader.getReceiptDate(), oid, userDateFormat)%>"/><%=HiltonUtility.getFormattedDate(receiptHeader.getReceiptDate(), oid, userDateFormat)%></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "rec-receivedBy")%>>
						<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receivedBy", "Received By")%>:&nbsp;</b></td>
						<td nowrap><tsa:hidden name="ReceiptHeader_receivedBy" value="<%=receiptHeader.getReceivedBy()%>"/><%=receiptHeader.getReceivedBy()%></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "rec-receivedBy")%>>
						<td nowrap align=right>&nbsp;</td>
						<td nowrap><%=UserManager.getInstance().getUser(oid, receiptHeader.getReceivedBy()).getDisplayName()%></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "rec-inventoryLocation")%>>
						<td nowrap align=right><a href="javascript: browseLookup('ReceiptHeader_itemLocation', 'item_location'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-inventoryLocation", "Inventory Location")%> for this receipt or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-inventoryLocation", "Inventory Location", true)%>:</a>&nbsp;</td>
						<td nowrap><input type=text name="ReceiptHeader_itemLocation" value="<%=receiptHeader.getItemLocation()%>" tabIndex=6 onchange="upperCase(this);"></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "rec-numberOfPackages")%>>
						<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-numberOfPackages", "# of Packages", true)%>:&nbsp;</td>
						<td nowrap><input type=text name="ReceiptHeader_pkgsReceived" value="<%=receiptHeader.getPkgsReceived().setScale(0, BigDecimal.ROUND_HALF_UP)%>" style="text-align:right" tabIndex=2 size=10 onchange="formatPkgQty()"></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "rec-packingSlip")%>>
						<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-packingSlip", "Packing Slip", true)%>:&nbsp;</td>
						<td nowrap><input type=text name="ReceiptHeader_packingSlip" value="<%=receiptHeader.getPackingSlip()%>" tabIndex=4 onchange="upperCase(this);"></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "rec-carrierCode")%>>
						<td nowrap align=right><a href="javascript: browseStd('ReceiptHeader_carrierCode', 'CARR'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-carrierCode", "Carrier Code")%> for this receipt or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-carrierCode", "Carrier Code", true)%>:</a>&nbsp;</td>
						<td nowrap><input type=text name="ReceiptHeader_carrierCode" value="<%=receiptHeader.getCarrierCode()%>" tabIndex=6 onchange="upperCase(this);"></td>
					</tr>
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
								<td nowrap align=right valign=top width=93px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receiptNotes", "Notes", true)%>:&nbsp;</td>
								<td><textarea name="ReceiptHeader_receiptNotes" cols=60 rows=3  onKeyDown="textCounter(this, 225);" onKeyUp="textCounter(this,225);" tabIndex=10>${esapi:encodeForHTML(receiptHeader.receiptNotes)}</textarea></td>
							</tr>
							</table>
						<td>
					</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
<% if (s_view.equals("WIZARD")) { %>
	<td rowspan=2 valign=top><%@ include file="/receipts/rec_sidebar.jsp" %></td>
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

	function formatPkgQty() {
		frm.ReceiptHeader_pkgsReceived.value = nformat(eval(nfilter(frm.ReceiptHeader_pkgsReceived)), 0);
	}

// End Hide script -->
</SCRIPT>