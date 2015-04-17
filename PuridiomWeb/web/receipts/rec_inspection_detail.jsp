<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%@ page import="com.tsa.puridiom.commodity.CommodityManager" %>
<%@ page import="com.tsa.puridiom.catalog.tasks.CatalogItemManager" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<%
	int i;

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

	boolean extendedInventory = propertiesManager.getProperty("Modules", "Extended Inventory", "N").equalsIgnoreCase("Y");
	String s_duomRequired = propertiesManager.getProperty("INVENTORY", "DUALUOMREQUIRED", "N");
	String s_invitemxref = propertiesManager.getProperty("INVENTORY", "ITEMCROSSREF", "N");
	String s_date_format = propertiesManager.getProperty("MISC", "DateFormat", "MM-dd-yyyy");

	String s_ic_rec_header = (String) request.getAttribute("ReceiptHeader_icRecHeader");
	String s_rec_number = (String) request.getAttribute("ReceiptHeader_receiptNumber");
	String s_rec_type = (String) request.getAttribute("ReceiptHeader_receiptType");
	String s_rec_status = (String) request.getAttribute("ReceiptHeader_receiptStatus");
	String s_rec_fiscal_year = (String) request.getAttribute("ReceiptHeader_fiscalYear");

	if (HiltonUtility.isEmpty(s_rec_status)) {
		s_rec_status = DocumentStatus.RCV_INPROGRESS;
	}

	String s_ic_po_header = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_icPoHeader"));
	String s_ic_po_line = HiltonUtility.ckNull((String) request.getAttribute("PoLine_icPoLine"));
	String s_ic_req_header = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_icReqHeader"));
	String s_req_number = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_requisitionNumber"));
	String s_po_number = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_poNumber"));
	String s_revision_number = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_revisionNumber"));
	String s_release_number = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_releaseNumber"));
	if (HiltonUtility.isEmpty(s_revision_number)) {
		s_revision_number = "0";
	}
	if (HiltonUtility.isEmpty(s_release_number)) {
		s_release_number = "0";
	}
	BigDecimal bd_revision_number = new BigDecimal(s_revision_number);
	BigDecimal bd_release_number = new BigDecimal(s_release_number);
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	if (poHeader == null) {
		poHeader = new PoHeader();
	}

	String receiptMethod = HiltonUtility.ckNull((String) request
			.getAttribute("receiptMethod"));
	String s_current_process = "STEP1";
	String s_current_page = "/receipts/rec_inspection_detail.jsp";
	String s_current_method = "DoNothing";
	String s_current_process_method = "";
	String duomUmCode = "";
	BigDecimal bd_zero = new BigDecimal(0);

	boolean allowEdit = false;
	/*if ((receiptMethod.equals("FinalizeReceipt") && s_rec_status.compareTo(DocumentStatus.RCV_PENDINGFINALIZATION) == 0) ||
	 (receiptMethod.equals("ReceiveFromNothing") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0) ||
	 (receiptMethod.equals("ReceiveByTransfer") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0) ||
	 (receiptMethod.equals("Return") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0) ||
	 (receiptMethod.equals("ReceiveByOrder") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0))
	 allowEdit = true;*/

	ReceiptHeader receiptHeader = (ReceiptHeader) request
			.getAttribute("receiptHeader");
	if (receiptHeader == null) {
		receiptHeader = new ReceiptHeader();
	}

	ReceiptLine receiptLine = (ReceiptLine) request
			.getAttribute("receiptLine");
	if (receiptLine == null) {
		receiptLine = new ReceiptLine();
	}
	if (uid.equals(receiptLine.getInspectorAssigned())) {
		allowEdit = true;
	}

	String s_commodity = "";
	PoLine poLine = receiptLine.getPoLine();
	if (poLine == null) {
		poLine = new PoLine();
	} else {
		s_commodity = poLine.getCommodity();
	}

	RequisitionLine reqLine = receiptLine.getRequisitionLine();
	if (reqLine == null) {
		reqLine = new RequisitionLine();
	} else {
		s_commodity = reqLine.getCommodityCode();
	}
	Commodity commodity = CommodityManager.getInstance().getCommodity(
			oid, s_commodity);
	boolean isAsset = false;
	boolean isDualUm = false;
	boolean isSerNoRequired = false;

	if (commodity != null) {
		String atemp = commodity.getSerialNumbersRequired();
		String umtemp = commodity.getDuomRequired();
		if (atemp == null)
			atemp = "N";
		if (umtemp == null)
			umtemp = "N";
		isSerNoRequired = atemp.equalsIgnoreCase("Y");
		if (s_duomRequired.equalsIgnoreCase("Y"))
			isDualUm = umtemp.equalsIgnoreCase("Y");
	}

	boolean isInventoryItem = false;
	InvItem invItem = (InvItem) request.getAttribute("invItem");
	if (!(invItem == null)) {
		isInventoryItem = true;
		duomUmCode = receiptLine.getDuomUmCode();
		if (HiltonUtility.isEmpty(duomUmCode)) {
			duomUmCode = invItem.getDuomUmCode();
		}
		if (receiptMethod.equals("ReceiveByTransfer")) {
			receiptHeader.setShipToInv("Y");
		}
	} else {
		isInventoryItem = false;
	}

	String s_return_page = HiltonUtility.ckNull((String) request
			.getAttribute("returnPage"));
	if (HiltonUtility.isEmpty(s_return_page)) {
		s_return_page = "/receipts/rec_items_step_1.jsp";
	}
	String s_return_method = HiltonUtility.ckNull((String) request
			.getAttribute("returnMethod"));
	if (HiltonUtility.isEmpty(s_return_method)) {
		s_return_method = "ReceiptHeaderRetrieveById;ReceiptLineRetrieveByHeader";
	}

	String quantityDecimals = propertiesManager.getProperty("MISC",
			"QtyDecimals", "2");
	String priceDecimals = propertiesManager.getProperty("MISC",
			"PriceDecimals", "2");
	BigDecimal bd_line_number = HiltonUtility.getBigDecimalFormatted(
			receiptLine.getReceiptLine(), 0);
	if (bd_line_number.compareTo(new BigDecimal(0)) == 0)
		bd_line_number = new BigDecimal(1);
	String s_line_count = (String) request.getAttribute("lineCount");

	String shelfLifeRqd = poLine.getShelfLifeRqd();

	String s_corrosion_eval = receiptHeader.getCorrosionEval();

	String s_from_page = (String) request.getAttribute("frompage");
	String s_from_step = (String) request.getAttribute("fromstep");

	InspectionHeader inspectionHeader = (InspectionHeader) request
			.getAttribute("inspectionHeader");

	List inspectionLineList = (List) request
			.getAttribute("inspectionLineList");
	if (inspectionLineList == null)
		inspectionLineList = new ArrayList();

	BigDecimal icInspNo = new BigDecimal("-1");
	String suffixInspNumber = "";
	String sequencialNumber = "";

	String inspectType = "";
	String inspectTypeNumber = "";
	String udf2Code = ""; // ncr no

	String poNo = ""; // Where's it come from, this is a req
	String udf3Code = ""; //EDWS Box No

	String inspType = ""; // Not sure on this one (Why 2 inspection types)
	String purchaseSpecs = "";

	String inspectorId = "";
	String udf4Code = ""; // Dwg/Std

	String inspectorName = "";
	String remoteInspId = "";

	String engineerId = "";
	String remoteInspectorName = "";

	String engineerName = "";
	String area = "";

	String storageFacility = "";

	String location = "";

	String cgdNo = "";

	BigDecimal cgdRev = new BigDecimal(0);

	Date dateEntered = new Date();

	BigDecimal qtySignoff = new BigDecimal(0) ;
	BigDecimal qtyAccepted = new BigDecimal(0) ;
	BigDecimal qtyRejected = new BigDecimal(0) ;

	String icMsrLine = (String) request
			.getAttribute("InspectionHeader_icMsrLine");

	List commentList = (List) request.getAttribute("docCommentList");
	if (commentList == null) commentList = new ArrayList();

	List poHeaderCommentList = (List) request.getAttribute("poHeaderDocCommentList");
	if (poHeaderCommentList == null) poHeaderCommentList = new ArrayList();

	List poLineCommentList = (List) request.getAttribute("poLineDocCommentList");
	if (poLineCommentList == null) poLineCommentList = new ArrayList();

	List attachmentList = (List) request.getAttribute("docAttachmentList");
	if (attachmentList == null) attachmentList = new ArrayList();

	List mfrList = (List) request.getAttribute("mfrList");
	if (mfrList == null) mfrList = new ArrayList();

	if (inspectionHeader != null) {
		icInspNo = inspectionHeader.getComp_id().getIcInspNo();

		inspectType = inspectionHeader.getInspectType();
		udf2Code = HiltonUtility.ckNull(inspectionHeader.getUdf02()); // ncr no

		poNo = HiltonUtility.ckNull(inspectionHeader.getPoNumber());

		udf3Code = inspectionHeader.getUdf03(); //EDWS Box No

		inspType = inspectionHeader.getInspType(); // Not sure on this one (Why 2 inspection types)
		purchaseSpecs = inspectionHeader.getPurchaseSpecs();

		udf4Code = inspectionHeader.getUdf04(); // Dwg/Std

		inspectorId = inspectionHeader.getInspectorId();
		remoteInspId = inspectionHeader.getRemoteInspId();
		engineerId = inspectionHeader.getEngineerId();

		area = HiltonUtility.ckNull(inspectionHeader.getArea());
		storageFacility = HiltonUtility.ckNull(inspectionHeader
				.getStorage());
		location = HiltonUtility.ckNull(inspectionHeader.getLocation());

		dateEntered = inspectionHeader.getDateEntered();
		cgdNo = inspectionHeader.getCgdNo();
		cgdRev = HiltonUtility.ckNull(inspectionHeader.getCgdRev());


		qtySignoff = HiltonUtility.ckNull(inspectionHeader.getQtySignoff()) ;
		qtyAccepted =HiltonUtility.ckNull( inspectionHeader.getQtyAccepted()) ;
		qtyRejected = HiltonUtility.ckNull(inspectionHeader.getQtyRejected()) ;
	}

	UserProfile inspector = UserManager.getInstance().getUser(oid,
			inspectorId);
	UserProfile engineer = UserManager.getInstance().getUser(oid,
			engineerId);

	engineerName = engineer.getDisplayName();
	inspectorName = inspector.getDisplayName();

	boolean disableEdit = false;

	String numberStatic = "16";
	if (!HiltonUtility.isEmpty(inspectionHeader.getInspectionNumber())) {
		String[] split = inspectionHeader.getInspectionNumber().split(
				"-");
		if (split.length >= 3) {
			numberStatic = split[2];
		}
		if (inspectType.equals("GI") && split.length >= 4) {
			sequencialNumber = split[3];
		}
	}
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(dateEntered);
	int year = calendar.get(Calendar.YEAR);

	if (inspectType.equals("GI")) {
		suffixInspNumber = sequencialNumber;
	} else {
		suffixInspNumber = poNo;
	}

	if(inspectType.equals("RI")){
		inspectTypeNumber = "RIR";
	} else {
		inspectTypeNumber = inspectType;
	}

	String itemNumber = HiltonUtility.ckNull((String)request.getAttribute("InvItem_itemNumber"));

	String s_inspection_number = HiltonUtility.ckNull(inspectionHeader.getInspectionNumber());
%>

<input type=hidden name="ReceiptHeader_icRecHeader" value="<%=s_ic_rec_header%>">
<input type=hidden name="ReceiptHeader_receiptNumber" value="<%=s_rec_number%>">
<input type=hidden name="ReceiptHeader_receiptType" value="<%=s_rec_type%>">
<input type=hidden name="ReceiptHeader_receiptStatus" value="<%=s_rec_status%>">
<input type=hidden name="ReceiptHeader_fiscalYear" value="<%=s_rec_fiscal_year%>">
<input type=hidden name="ReceiptLine_icRecHeader" value="<%=s_ic_rec_header%>">
<input type=hidden name="PoHeader_icPoHeader" value="<%=s_ic_po_header%>">
<input type=hidden name="PoHeader_poNumber" value="<%=s_po_number%>">
<input type=hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>">
<input type=hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>">
<input type=hidden name="PoLine_icPoHeader" value="<%=s_ic_po_header%>">
<input type=hidden name="PoLine_icPoLine" value="<%=s_ic_po_line%>">

<input type=hidden name="RequisitionHeader_icReqHeader" value="<%=s_ic_req_header%>">
<input type=hidden name="RequisitionLine_icReqHeader" value="<%=s_ic_req_header%>">
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_req_number%>"/>
<input type=hidden name="ReceiptLine_qtyStep1Received" value="<%=receiptLine.getQtyStep1Received()%>">
<input type=hidden name="ReceiptLine_inspLocation" value="<%=inspectionHeader.getLocation()%>">
<input type=hidden name="ReceiptLine_inspArea" value="<%=inspectionHeader.getArea()%>">
<input type=hidden name="ReceiptLine_inspStorage" value="<%=inspectionHeader.getStorage()%>">

<input type=hidden name="ReceiptLine_icRecLine" value="<%=receiptLine.getIcRecLine()%>">
<input type=hidden name="ReceiptHeader_shipToInv" value="<%=receiptHeader.getShipToInv()%>">
<input type=hidden name="InvBinLocation_tempIc" value="<%=receiptLine.getIcRecLine()%>">
<input type=hidden name="ReceiptHeader_icPoHeader" value="<%=s_ic_po_header%>">
<input type=hidden name="ReceiptLine_receiptLine" value="<%=bd_line_number%>">
<input type=hidden name="lineCount" value="<%=s_line_count%>">
<input type=hidden name="lineToRetrieve" value="">
<input type=hidden name="InvProperty_flag" value="I">
<input type=hidden name="returnPage" value="<%=s_return_page%>">
<input type=hidden name="returnMethod" value="<%=s_return_method%>">
<input type=hidden name="receiptMethod" value="<%=receiptMethod%>">
<input type=hidden name="InvItem_itemNumber" value="<%=itemNumber%>">
<input type=hidden name="Account_icHeader" value="<%=s_ic_rec_header%>">
<input type=hidden name="Account_icLine" value="0">
<input type=hidden name="Account_accountType" value="INH">

<tsa:hidden name="DocComment_icHeader" value="<%=icInspNo%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=icInspNo%>"/>
<tsa:hidden name="stdTable_tableType" value=""/>
<tsa:hidden name="formtype" value="INS"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="frompage" value="<%=s_from_page%>"/>
<tsa:hidden name="formstep" value="s_from_step"/>

<tsa:hidden name="InspectionHeader_icInspNo" value="<%=icInspNo%>"/>
<tsa:hidden name="InspectionHeader_icMsrLine" value="<%=icMsrLine%>"/>
<tsa:hidden name="originalQtySignoff" value="<%=qtySignoff%>"/>
<tsa:hidden name="originalQtyAccepted" value="<%=qtyAccepted%>"/>
<tsa:hidden name="originalQtyRejected" value="<%=qtyRejected%>"/>
<tsa:hidden name="InspectionMfr_icInspNo" value="<%=icInspNo%>"/>
<tsa:hidden name="InspectionMfr_icMsrLine" value="<%=icMsrLine%>"/>
<tsa:hidden name="InspectionMte_icInspNo" value="<%=icInspNo%>"/>

<tsa:hidden name="lineUpdated" value="false"/>
<tsa:hidden name="lineToRetrieve" value=""/>
<tsa:hidden name="writehistory" value="N"/>
<tsa:hidden name="originalQuantity" value=""/>
<tsa:hidden name="originalPrice" value=""/>
<tsa:hidden name="reqaction" value=""/>
<tsa:hidden name="duplicateItem" value="FALSE"/>
<tsa:hidden name="Commodity_commodity" value=""/>
<tsa:hidden name="accountFLD2" id="accountFLD2" value=""/>
<tsa:hidden name="updateUdf10" value="N"/>
<tsa:hidden name="commodityUpdateAccount" value=""/>
<tsa:hidden name="currentPage" value="item"/>
<tsa:hidden name="as_rows" value="<%=mfrList.size()%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "ins-detail", "Inspection Detail")%>  </div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td>
		<%
			if (!HiltonUtility.isEmpty(s_po_number)) {
		%>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Order #:</b>&nbsp;<%=s_po_number%>
		<%
			} else if (!HiltonUtility.isEmpty(s_req_number)) {
		%>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Transfer Request #:</b>&nbsp;<%=headerEncoder.encodeForHTML(s_req_number)%>
		<%
			}
		%>
			</td>
			<td align=right><b>Receipt #:</b></td>
		<%
			if (HiltonUtility.isEmpty(s_rec_number)) {
		%>
			<td width=100px>N/A</td>
		<%
			} else {
		%>
			<td width=100px><%=s_rec_number%></td>
		<%
			}
		%>
		</tr>
		<tr>
			<td>
		<%
			if (bd_revision_number.compareTo(bd_zero) > 0) {
		%>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Revision #:</b>&nbsp;<%=bd_revision_number%>
		<%
			}
		%>
		<%
			if (bd_release_number.compareTo(bd_zero) > 0) {
		%>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Release #:</b>&nbsp;<%=bd_release_number%>
		<%
			}
		%>
			</td>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "status", "Status")%>:</b></td>
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

<%@ include file="/receipts/rec_inspection_info.jsp" %>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formWidth%>>
<tr>
	<td align=center valign=top>
		<table border=0 cellpadding=0 cellspacing=0 height=100% width=100%>
		<tr>
			<td>
				<table border=0 cellpadding=1 cellspacing=0 width=100%>
				<tr>
					<td colspan="4"><br></td>
				</tr>
				<tr>
					<td colspan="2" valign=top>
						<!-- left side -->
						<table border="0" cellpadding="0" cellspacing="1" width=100%>
						<tr>
							<td width=175px <%=HtmlWriter.isVisible(oid, "ins-inspectType")%> nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "ins-inspectType", "Inspection",
									true)%>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "ins-inspection")%>><select name="InspectionHeader_inspectType" value="<%=inspectType%>" onchange="generateInspectionNumber();">
												<option value="RI"  <%if (inspectType.equals("RI") || HiltonUtility.isEmpty(inspectType)) {%> SELECTED <%}%>><tsa:label labelName="ins-receiptInspection" defaultString="Receipt Inspection (RICP)"></tsa:label></option>
												<option value="GI" <%if (inspectType.equals("GI")) {%> SELECTED <%}%>><tsa:label labelName="ins-generalInspection" defaultString="General Inspection"></tsa:label></option>
											</select>
							</td>
						</tr>
						<tr>
			              <td width=175px <%=HtmlWriter.isVisible(oid, "ins-inspectionNumber")%> nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-inspectionNumber", "RI/GI Number", true)%>:&nbsp;</td>
			              <td <%=HtmlWriter.isVisible(oid, "ins-inspectionNumber")%>>
			                <input type=text name="inspectionNumberPreffix" tabindex=1 size=8 maxlength=30 value="<%=year+"-"+inspectTypeNumber%>" onchange="upperCase(this);void(0);" disabled>-<input type=text name="inspectionNumber" tabindex=1 size=1 maxlength=2 value="<%=numberStatic%>" onchange="upperCase(this);void(0);">-<input type=text name="inspectionNumberSuffix" tabindex=1 size=12 maxlength=30 value="<%=suffixInspNumber%>" onchange="upperCase(this);void(0);" disabled>
			                <input type=hidden name="InspectionHeader_inspectionNumber" value="<%=s_inspection_number %>">
			              </td>
			            </tr>
						<tr>
							<td width=175px <%=HtmlWriter.isVisible(oid, "ins-cgdNo")%> nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "ins-cgdNo", "CGD No", true)%>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "ins-cgdNo")%>><input type=text name="InspectionHeader_cgdNo" tabindex=1 size=20 maxlength=20 value="<%=cgdNo%>" onchange="upperCase(this);void(0);"<%if(!allowEdit){%> disabled<%}%>></td>
						</tr>
						<tr>
							<td width=175px <%=HtmlWriter.isVisible(oid, "ins-cgdRev")%> nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "ins-cgdNo", "CGD Rev", true)%>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "ins-cgdRev")%>><input type=text name="InspectionHeader_cgdRev" tabindex=1 size=2 maxlength=2 value="<%=cgdRev%>" onchange="upperCase(this);void(0);"<%if(!allowEdit){%> disabled<%}%>></td>
						</tr>
						<tr>
							<td width=175px <%=HtmlWriter.isVisible(oid, "ins-poNo")%> nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "ins-poNo", "PO No", true)%>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "ins-poNo")%>><input type=text name="InspectionHeader_poNumber" tabindex=1 size=20 maxlength=20 value="<%=poNo%>" onchange="upperCase(this);void(0);" disabled></td>
						</tr>
						<tr>
							<td width=175px <%=HtmlWriter.isVisible(oid, "ins-inspType")%> nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "ins-inspType", "Insp Type", true)%>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "ins-inspType")%>><input type=text name="InspectionHeader_inspType" tabindex=1 size=10 maxlength=10 value="<%=inspType%>" onchange="upperCase(this);void(0);"></td>
						</tr>
						<tr>
							<td width=175px <%=HtmlWriter.isVisible(oid, "ins-inspectorId")%> nowrap align=right><a href="javascript: browseLookup('InspectionHeader_inspectorId', 'inspector'); void(0);" title="Click here to choose a <%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "ins-inspectorId", "inspector Id")%> for this order or enter the <%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "ins-inspectorId", "Inspector Id")%> in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "ins-inspectorId", "Inspector Id",
									true)%>:</a>&nbsp;
							<td <%=HtmlWriter.isVisible(oid, "ins-inspectorId")%>><input type=text name="InspectionHeader_inspectorId" tabindex=1 size=15 maxlength=15 value="<%=inspectorId%>" onchange="upperCase(this);  getNewInfo('inspector', this);"></td>
						</tr>
						<tr>
							<td width=175px <%=HtmlWriter.isVisible(oid, "ins-inspectorName")%> nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "ins-inspectorName",
									"Inspector Name", true)%>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "ins-inspectorName")%>><input type=text name="inspectorName" tabindex=1 size=30 maxlength=30 value="<%=inspectorName%>"  onfocus="this.blur();" disabled></td>
						</tr>
						<tr>
							<td width=175px <%=HtmlWriter.isVisible(oid, "ins-engineerId")%> nowrap align=right><a href="javascript: browseLookup('InspectionHeader_engineerId', 'qa-engineer'); void(0);" title="Click here to choose a <%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "ins-engineerId", "Engineer Id")%> for this order or enter the <%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "po-buyer", "Engineer Id")%> in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "ins-engineerId", "Engineer Id",
									true)%>:</a>&nbsp;
							<td <%=HtmlWriter.isVisible(oid, "ins-engineerId")%>><input type=text name="InspectionHeader_engineerId" tabindex=1 size=15 maxlength=15 value="<%=engineerId%>" onchange="upperCase(this);  getNewInfo('engineer', this);"></td>
						</tr>
						<tr>
							<td width=175px <%=HtmlWriter.isVisible(oid, "ins-engineerName")%> nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "ins-engineerName", "Engineer Name",
									true)%>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "ins-engineerName")%>><input type=text name="engineerName" tabindex=1 size=30 maxlength=30 value="<%=engineerName%>"  onfocus="this.blur();"  disabled></td>
						</tr>
						<tr>
							<tsa:td id="corrosionEvalLabelRow" field="rec-corrosionEval" width="8%" height="18px" align="right" noWrap="nowrap">
					        	<tsa:label labelName="rec-corrosionEval" defaultString="Corrosion Evaluation" checkRequired="true" noinstance="yes"/>:&nbsp;
					        </tsa:td>
					        <tsa:td id="corrosionEvalFieldRow" field="rec-corrosionEval">
					        	<input type="checkbox" name="c_checkbox" value="Y" <%if(s_corrosion_eval.indexOf("Y") >= 0){%>CHECKED<%}%> onclick="setCheckbox(frm.ReceiptHeader_corrosionEval, 0);" <%if(!allowEdit){%>disabled<%}%>>
								<tsa:hidden name="ReceiptHeader_corrosionEval" value="<%=s_corrosion_eval%>"/>
					        </tsa:td>
						</tr>
						<tr>
						<td width=100% colspan=2>
						<div id="giDetails" name="giDetails">
						<table border=0 cellpadding=0 cellspacing=0 width=100%>
							<tr>
								<td width=175px <%=HtmlWriter.isVisible(oid, "ins-qtySignoff")%> nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-qtySignoff", "Quantity for Sign-off", true)%>:&nbsp;</td>
								<td <%=HtmlWriter.isVisible(oid, "ins-qtySignoff")%>><input type=text name="InspectionHeader_qtySignoff" tabindex=1 size=10 maxlength=10 value="<%=qtySignoff%>" style="text-align:right"></td>
							</tr>
							<tr>
								<td width=175px <%=HtmlWriter.isVisible(oid, "ins-qtyAccepted")%> nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-qtyAccepted", "Quantity Accepted", true)%>:&nbsp;</td>
								<td <%=HtmlWriter.isVisible(oid, "ins-qtyAccepted")%>><input type=text name="InspectionHeader_qtyAccepted" tabindex=1 size=10 maxlength=10 value="<%=qtyAccepted%>" style="text-align:right"></td>
							</tr>
							<tr>
								<td width=175px <%=HtmlWriter.isVisible(oid, "ins-qtyRejected")%> nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-qtyRejected", "Quantity Rejected", true)%>:&nbsp;</td>
								<td <%=HtmlWriter.isVisible(oid, "ins-qtyRejected")%>><input type=text name="InspectionHeader_qtyRejected" tabindex=1 size=10 maxlength=10 value="<%=qtyRejected%>" style="text-align:right"></td>
							</tr>
						</table>
						</div>
						</td>
						</tr>

						</table>
						<!-- left side end -->
					</td>
					<td colspan="2" valign=top>
						<!-- right side -->
						<table border="0" cellpadding="0" cellspacing="1" width=100%>
						<tr>
							<td width=175px <%=HtmlWriter.isVisible(oid, "ins-udf02")%> nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "ins-udf02", "NCR No", true)%>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "ins-udf02")%>><input type=text name="InspectionHeader_udf02" tabindex=1 size=30 maxlength=30 value="<%=udf2Code%>" onchange="upperCase(this);void(0);"></td>
						</tr>
						<tr>
							<td width=175px <%=HtmlWriter.isVisible(oid, "ins-purchaseSpecs")%> nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "ins-purchaseSpecs",
									"Purchase Specs", true)%>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "ins-purchaseSpecs")%>><input type=text name="InspectionHeader_purchaseSpecs" tabindex=1 size=40 maxlength=40  value="<%=purchaseSpecs%>" onchange="upperCase(this);void(0);"></td>
						</tr>
						<tr>
							<td width=175px <%=HtmlWriter.isVisible(oid, "ins-udf04")%> nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "ins-udf04", "Dwg/Std", true)%>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "ins-udf04")%>><input type=text name="InspectionHeader_udf04" tabindex=1 size=40 maxlength=40 value="<%=udf4Code%>" onchange="upperCase(this);void(0);"></td>
						</tr>
						<tr>
							<td width=175px <%=HtmlWriter.isVisible(oid, "ins-remoteInspId")%> nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "ins-remoteInspId",
									"Remote Inspector", true)%>:&nbsp;
							<td <%=HtmlWriter.isVisible(oid, "ins-remoteInspId")%>><input type=text name="InspectionHeader_remoteInspId" tabindex=1 size=30 maxlength=30 value="<%=remoteInspId%>" onchange="upperCase(this); "></td>
						</tr>
						<tr>
							<td width=175px <%=HtmlWriter.isVisible(oid, "ins-area")%> nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "ins-area", "Area", true)%>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "ins-area")%>><input type=text name="InspectionHeader_area" tabindex=1 size=15 maxlength=15 value="<%=area%>" onchange="upperCase(this);void(0);"></td>
						</tr>
						<tr>
							<td width=175px style=\"visibility:hidden; display:none;\" nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "ins-storage", "Storage Facility",
									true)%>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "ins-storage")%>><input type=text name="InspectionHeader_storage" tabindex=1 size=15 maxlength=15 value="<%=storageFacility%>" onchange="upperCase(this);void(0);"></td>
						</tr>
						<tr>
							<td width=175px <%=HtmlWriter.isVisible(oid, "ins-loc")%> nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "ins-loc", "Location", true)%>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "ins-loc")%>><input type=text name="InspectionHeader_location" tabindex=1 size=15 maxlength=15 value="<%=location%>" onchange="upperCase(this);void(0);"></td>
						</tr>
						<%
							String shelfLifeDate = "";
							if (inspectionHeader != null)
								shelfLifeDate = HiltonUtility.getFormattedDate(inspectionHeader
										.getShelfLifeDate(), oid);
						%>
						<tr>
							<td width=175px <%=HtmlWriter.isVisible(oid, "ins-shelflifedate")%> nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "ins-shelftlifedate",
									"Shelf Life Date", true)%>:&nbsp;</td>
							<td  <%=HtmlWriter.isVisible(oid, "ins-shelflifedate")%>><input type="text" name="InspectionHeader_shelfLifeDate" value="<%=shelfLifeDate%>" size="10" maxLength="10" >
							<a href="javascript: show_calendar('InspectionHeader_shelfLifeDate', '<%=s_date_format%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign="bottom" border="0"></a>
							</td>
						</tr>
						<tr><br></br></tr>
						</table>
					</td>
				</tr>
						<!-- right side end -->
				</table>
		  </td>
		</tr>
		</table>
	</td>
	<td align=right width=220px valign=top>
		<table border=1 cellpadding=0 cellspacing=0>
		<tr>
		<td class=browseHdr><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inspectionOptions",
									"Inspection Options")%></td></tr>
		<tr>
			<td class=browseRow>
				<table border=0 cellpadding=0 cellspacing=0 width=225px>
				<tr height=15px><td nowrap align=center><a href="javascript: optionInspCriteria(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "optionInspCriteria",
									"Inspection Criteria")%></a></td></tr>
				<tr height=15px><td nowrap align=center><a href="javascript: optionInspDiscrep(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "optionInspDiscrep",
									"Inspection Discrepancies")%></a></td></tr>
				<tr height=15px><td nowrap align=center><a href="javascript: optionInspMte(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "rec-mteInspection",
									"M& TE Inspection")%></a></td></tr>
				<tr height=15px><td nowrap align=center><a href="javascript: optionInspComments(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "optionInspComments", "Comments")%></a></td></tr>
				<tr height=15px><td nowrap align=center><a href="javascript: optionInspAttachments(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "optionInspAttachments",
									"Attachments")%></a></td></tr>
				<tr height=15px><td nowrap align=center><a href="javascript: optionInspWorksheet(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "optionInspWorkSheet", "Inspection Worksheet")%></a></td></tr>
				<tr height=15px><td nowrap align=center><a href="javascript: optionInspPrint(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "optionInspPrint", "Inspection Report")%></a></td></tr>
				<tr height=15px><td nowrap align=center><a href="javascript: printLabel('ACC'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "rec-printLabelACC", "Print ACC Label")%></a></td></tr>
				<tr height=15px><td nowrap align=center><a href="javascript: printLabel('UNN'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "rec-printLabelUNN", "Print UNN Label")%></a></td></tr>
				<tr height=15px><td nowrap align=center><a href="javascript: optionInspHistory(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "optionInspHistory", "History")%></a></td></tr>
				</table>
		</td>
		</tr>
		</table>
</td>
</tr>
</table>


<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
<td>
		<%
			if (!disableEdit) {
		%>
		<table border=0 cellpadding=2 cellspacing=2 align=center width=80%>
		<tr>
			<td align=center  width=100%><a href="javascript: addNew(); void(0);"><font class="reset"><b>
				<tsa:label labelName="ins-addNewDocument" defaultString="Add New Document" checkRequired="true" noinstance="yes"/>
			</b></font></a></td>
		</tr>
		</table>
		<%
			} else {
		%>
			<br>
		<%
			}
		%>
</td>
</tr>
<tr>
	<td>
		<table  width=80% border="0" cellpadding="2" cellspacing="2" align="center">
				<tr>
					<td width=10% <%=HtmlWriter.isVisible(oid, "ins-documentType")%> nowrap align=center><a href="javascript: setFirstEmpty(); browseStd('InspectionMfr_documentType', 'INSPDOCT', true, true);  void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "ins-documentType", "Document Type",
									true)%></a></td>
					<td width=40% <%=HtmlWriter.isVisible(oid, "ins-mfrName")%> nowrap align=center><%=DictionaryManager
									.getLabelsInstance(oid, language).getLabel(
											oid, "ins-mfrName", "Manufacturer",
											true)%></td>
					<td width=20% <%=HtmlWriter.isVisible(oid, "ins-mfrNo")%> nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "ins-mfrNo", "Mfg No.", true)%></td>
					<td width=20% <%=HtmlWriter.isVisible(oid, "ins-mfrHeatLot")%> nowrap align=center><%=DictionaryManager
									.getLabelsInstance(oid, language).getLabel(
											oid, "ins-mfrHeatLot",
											"Heat / Lot", true)%></td>
					<td width=5%>&nbsp;</td>
				</tr>
		</table>
		</td>
<tr>
	<td>

		<table id="manufacturers" width=80% border="0" cellpadding="2" cellspacing="2" align="center">
		<%
			String s_set_row = "";
			int i_rowcount = 0;
			for (i = 0; i < mfrList.size(); i++) {
				InspectionMfr inspectionMfr = (InspectionMfr) mfrList.get(i);
				s_set_row = "ONFOCUS='setCurrentRow(" + i_rowcount + ");'";

				String mfrDocumentType = HiltonUtility.ckNull(inspectionMfr
						.getDocumentType());
				String mfrName = HiltonUtility.ckNull(inspectionMfr
						.getMfrName());
				String mfrNo = HiltonUtility.ckNull(inspectionMfr.getMfrNo());
				String mfrHeatLot = HiltonUtility.ckNull(inspectionMfr
						.getMfrHeatLot());
		%>
						<tr>
							<td width=10% id="mfrDocumentType"><input type=text name="InspectionMfr_documentType" value="<%=mfrDocumentType%>" size=15 maxlength=15 onchange="upperCase(this);" <%=s_set_row%>></td>
							<td  width=40% id="mfrNameId"><input type="text" name="InspectionMfr_mfrName" value="<%=mfrName%>" size="35" maxLength="40" onchange="upperCase(this);" <%=s_set_row%>/></td>
							<td width=20% id="mfrNoId"><input type=text name="InspectionMfr_mfrNo" value="<%=mfrNo%>" size=20 maxlength=30 onchange="upperCase(this);" <%=s_set_row%>></td>
							<td width=20% id="mfrHeatLotId"><input type=text name="InspectionMfr_mfrHeatLot" value="<%=mfrHeatLot%>" size=30 maxlength=30 onchange="upperCase(this);" <%=s_set_row%>></td>
							<td width=5% id="mfr_del_<%=i%>"><a href="javascript: if (confirm('Are you sure you wish to delete this Manufacturer?')) { deleteMe(<%=i%>); } void(0);" tabindex="999"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></td>
						</tr>
		<%
			i_rowcount++;
			} //end for loop
		%>
		</table>

		<br>
		<tsa:hidden name="as_maxrows" value="<%=i_rowcount%>"/>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<tsa:td width="200px" noWrap="nowrap" align="right" valign="top">
		<tsa:label labelName="ins-internalComments" defaultString="Comments" checkRequired="true" noinstance="yes"/>:&nbsp;
	</tsa:td>
	<td align=left valign=top>
		<tsa:input type="textarea" name="InspectionHeader_internalComments" tabIndex="30" rows="6" cols="64" maxLength="255" onkeydown="textCounter(this, 255);" onkeyup="textCounter(this,255);" onchange="textCounter(this,255); upperCase(this);">${esapi:encodeForHTML(inspectionHeader.internalComments)}</tsa:input>
	</td>
</tr>
</table>

<br>

<table  border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=2% height=18px class=browseHdr>&nbsp;</td>
					<td width=10% height=18px class=browseHdr align=center><b><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "ins-criteria", "Criteria")%></b></td>
					<td width=10% height=18px class=browseHdr align=center>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "ins-inspCode", "Insp Code")%>.</b></td>
					<td width=40% height=18px class=browseHdr align=center>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "ins-codeDescription",
									"Code Description")%>.</b></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
<%
	if (inspectionLineList != null) {
		for (i = 0; i < inspectionLineList.size(); i++) {
			//			Account account = (Account) inspectionLineList.get(i);
			InspectionLine inspLine = (InspectionLine) inspectionLineList
					.get(i);
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=2% class=browseRow>&nbsp;</td>
					<td width=10% class=browseRow align=center><%=HiltonUtility.ckNull(inspLine.getCritNo())%></td>
					<td width=10% class=browseRow align=center valign=top><%=HiltonUtility.ckNull(inspLine.getInspectCode())%></td>
					<td width=40% class=browseRow align=left valign=top><%=HiltonUtility.ckNull(inspLine.getInspCodeDesc())%></td>
				</tr>
			</table>
<%
	}
	}
	if (inspectionLineList != null && inspectionLineList.size() > 0) {
%>
<%
	} else {
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td><b><%=DictionaryManager
										.getLabelsInstance(oid, language)
										.getLabel(oid, "noInspectionsItem",
												"There are no inspection records for this item")%>.</b></td></tr>
				</table>
<%
	}
%>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr><td><br></td></tr>
</table>

<br>

<table <%=HtmlWriter.isVisible(oid, "ins-comments")%> border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=2% height=18px class=browseHdr><a href="javascript: optionInspComments(); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit"></td></a>
					<td width=64% height=18px class=browseHdr>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "comment", "Comment")%></b></td>
					<td width=8% height=18px class=browseHdr align=center>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "print", "Print")%></b></td>
					<td width=7% height=18px class=browseHdr align=center>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "bold", "Bold")%></b></td>
					<td width=16% height=18px class=browseHdr align=center>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "placement", "Placement")%></b></td>
					<%
						if (commentList != null && commentList.size() > 0) {
					%>
					<td width=3% height=18px class=browseHdr align=center><a href="javascript: toggleDetailsDisplay('commentDetails', 'Details'); void(0);"><img id='commentDetailsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Comments"></a></td>
					<%
						}
					%>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
<%
	String s_placeString = "";
	int ic = 0;
	if (poHeaderCommentList != null) {
		for (i = 0; i < poHeaderCommentList.size(); i++) {
			DocComment docComment = (DocComment) poHeaderCommentList
					.get(i);
			DocText docText = docComment.getDocText();
			String s_title = docComment.getCommentTitle();
			String s_print = docComment.getCommentPrint();
			String s_bold = docComment.getCommentBold();
			String s_place = docComment.getCommentPlace();
			String s_text = docText.getStdText();
			ic++;
			if (s_place.equals("A")) {
				s_place = "After";
			} else if (s_place.equals("B")) {
				s_place = "Before";
			}
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=58% class=browseRow><%=s_title%></td>
					<td width=8% class=browseRow align=center valign=top>POH</td>
					<td width=8% class=browseRow align=center valign=top><%=s_print%></td>
					<td width=7% class=browseRow align=center valign=top><%=s_bold%></td>
					<td width=16% class=browseRow align=center valign=top><%=s_place%></td>
					<td width=3% class=browseRow align=center valign=top>&nbsp;</td>
				</tr>
				</table>
				<div id="commentDetails" name="commentDetails" style="display:none;">
				<table>
				<tr>
					<td width=75px>&nbsp;</td>
					<td width=100%>
	<%
		if (s_bold.equals("Y")) {
	%>	<b><%
		}
	%><%=s_text%><%
		if (s_bold.equals("Y")) {
	%></b><%
		}
	%>
					</td>
				</tr>
				</table>
				</div>
	<%
		}
		}

		if (poLineCommentList != null) {
			for (i = 0; i < poLineCommentList.size(); i++) {
				DocComment docComment = (DocComment) poLineCommentList
						.get(i);
				DocText docText = docComment.getDocText();
				String s_title = docComment.getCommentTitle();
				String s_print = docComment.getCommentPrint();
				String s_bold = docComment.getCommentBold();
				String s_place = docComment.getCommentPlace();
				String s_text = docText.getStdText();
				ic++;
				if (s_place.equals("A")) {
					s_place = "After";
				} else if (s_place.equals("B")) {
					s_place = "Before";
				}
	%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=58% class=browseRow><%=s_title%></td>
					<td width=8% class=browseRow align=center valign=top>POL</td>
					<td width=8% class=browseRow align=center valign=top><%=s_print%></td>
					<td width=7% class=browseRow align=center valign=top><%=s_bold%></td>
					<td width=16% class=browseRow align=center valign=top><%=s_place%></td>
					<td width=3% class=browseRow align=center valign=top>&nbsp;</td>
				</tr>
				</table>
				<div id="commentDetails" name="commentDetails" style="display:none;">
				<table>
				<tr>
					<td width=75px>&nbsp;</td>
					<td width=100%>
	<%
		if (s_bold.equals("Y")) {
	%>	<b><%
		}
	%><%=s_text%><%
		if (s_bold.equals("Y")) {
	%></b><%
		}
	%>
					</td>
				</tr>
				</table>
				</div>
	<%
		}
		}

		if (commentList != null) {
			for (i = 0; i < commentList.size(); i++) {
				DocComment docComment = (DocComment) commentList.get(i);
				DocText docText = docComment.getDocText();
				String s_title = docComment.getCommentTitle();
				String s_print = docComment.getCommentPrint();
				String s_bold = docComment.getCommentBold();
				String s_place = docComment.getCommentPlace();
				String s_text = docText.getStdText();
				ic++;
				if (s_place.equals("A")) {
					s_place = "After";
				} else if (s_place.equals("B")) {
					s_place = "Before";
				}
	%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=66% class=browseRow><%=s_title%></td>
					<td width=8% class=browseRow align=center valign=top><%=s_print%></td>
					<td width=7% class=browseRow align=center valign=top><%=s_bold%></td>
					<td width=16% class=browseRow align=center valign=top><%=s_place%></td>
					<td width=3% class=browseRow align=center valign=top>&nbsp;</td>
				</tr>
				</table>
				<div id="commentDetails" name="commentDetails" style="display:none;">
				<table>
				<tr>
					<td width=75px>&nbsp;</td>
					<td width=100%>
<%
	if (s_bold.equals("Y")) {
%>	<b><%
		}
	%><%=s_text%><%
		if (s_bold.equals("Y")) {
	%></b><%
		}
	%>
					</td>
				</tr>
				</table>
				</div>
<%
	}
	}

	if (ic == 0) {
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "thereAreNoCommentsInspection", "There are no comments for this inspection")%>.</b></td></tr>
				</table>
<%
	}
%>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr><td><br></td></tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
						<tr>
							<td width=80% height=18px class=browseHdr>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "attachment", "Attachment")%></b></td>
							<td width=10% height=18px class=browseHdr align=center>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "print", "Print")%></b></td>
							<td width=10% height=18px class=browseHdr align=center>&nbsp;</td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
<%
	int ai = 0;
	if (attachmentList != null) {
		for (int ix = 0; ix < attachmentList.size(); ix++) {
			DocAttachment docAttachment = (DocAttachment) attachmentList
					.get(ix);
			if (docAttachment == null) {
				continue;
			}
			String filename = docAttachment.getDocFilename();
			String ext = filename
					.substring(filename.lastIndexOf(".") + 1);
			ai++;
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=80% class=browseRow>
						<table border=0 cellpadding=0 cellspacing=0 width=100% class=browseRow>
						<tr>
							<td width=25px align=center valign=middle>
<%
	if (ext.equalsIgnoreCase("DOC")) {
%>
							<a href="javascript: openDocument(<%=ix%>); void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%
	} else if (ext.equalsIgnoreCase("PDF")) {
%>
							<a href="javascript: openDocument(<%=ix%>); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%
	} else if (ext.equalsIgnoreCase("XLS")) {
%>
							<a href="javascript: openDocument(<%=ix%>); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%
	} else if (ext.equalsIgnoreCase("MPP")) {
%>
							<a href="javascript: openDocument(<%=ix%>); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%
	} else if (ext.equalsIgnoreCase("PPT")) {
%>
							<a href="javascript: openDocument(<%=ix%>); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%
	} else if (ext.equalsIgnoreCase("JPG")
					|| ext.equalsIgnoreCase("GIF")
					|| ext.equalsIgnoreCase("BMP")) {
%>
							<a href="javascript: openDocument(<%=ix%>); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%
	} else {
%>
							<a href="javascript: openDocument(<%=ix%>); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%
	}
%>
							</td>
							<td>
								<a href="javascript: openDocument(<%=ix%>); void(0);" title="Click here to open the <%=docAttachment.getDocTitle()%> attachment."><%=docAttachment.getDocTitle()%></a>
								<tsa:hidden name="docFilename" value="<%=filename%>"/>
							</td>
						</tr>
						</table>
					</td>
					<td width=10% class=browseRow align=center valign=top><%=docAttachment.getDocPrint()%></td>
					<td width=10% class=browseRow align=center valign=top></td>
				</tr>
				</table>

	<%
		}
		}
		if (ai == 0) {
	%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
                    <tr><td class=browseRow><%=DictionaryManager
										.getLabelsInstance(oid, language)
										.getLabel(oid,
												"thereAreNoAttachmentsReceipt",
												"There are no attachments associated with this requisition")%>.</td></tr>
				</table>
<%
	}
%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<%
		if (!disableEdit) {
	%>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: submitThis(); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: submitCancel(); void(0);">Return</a></div></td>
	<%
		} else {
	%>
	<td width=100% align=center><div id="pxbutton"><a href="javascript: submitCancel(); void(0);">Return</a></div></td>
	<%
		}
	%>
</tr>
<tr><td><br></td></tr>
</table>


<%@ include file="/system/footer.jsp" %>


<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var info_window = null;
	var populated = false;
	var uomArray = new Array();
	var oid = "<%=oid%>";
	var myTable = document.getElementById("manufacturers");
	var TotalRows = myTable.rows.length - 1;
	var maxRows = frm.as_maxrows.value;
	var accRows = frm.as_rows.value;

	//setGiDetails("<%=inspectType%>") ;

	if (TotalRows < 0)
	{
		TotalRows = 0;
		addNew();
	}
	setCurrentRow(0);

	function thisLoad()
	{
		f_StartIt();
		<%if (disableEdit) {%>
			checkInputSettings();
			allowInputEdit(frm.InspectionHeader_inspectType, true);
		<%}%>
	}

	function optionInspHistory()
	{
		popupParameters = "ReceiptLine_icPoLine=<%=receiptLine.getIcPoLine()%>;HistoryLog_icLineHistory=<%=receiptLine.getIcLineHistory()%>;formtype=RECReceiptLine_icRecLine=<%=receiptLine.getIcRecLine()%>;receiptNumber=<%=receiptLine.getReceiptNumber()%>";
		doSubmitToPopup('/receipts/rec_history.jsp', 'HistoryLogRetrieveByHistoryLine', 'width=675px', 'height=350px');
	}


	function retrieveInpections()
	{
		doSubmit('/requests/req_inspections_ln.jsp','ReceiptLineUpdate;ReceiptLineRetrieveInspectionst');
	}

	function generateInspectionNumber()
	 {
	   var inspectType = frm.InspectionHeader_inspectType.value;
	   var year = "<%=year%>";
	   if(inspectType == "RI"){
		   frm.inspectionNumberPreffix.value = year + "-RIR";
	   } else {
		   frm.inspectionNumberPreffix.value = year + "-" + inspectType;
	   }
	   if(inspectType == "GI"){
		   <%if(!sequencialNumber.equals("")){%>
		   		frm.inspectionNumberSuffix.value = '<%=sequencialNumber%>';
		   <%} else {%>
			   	frm.InspectionHeader_inspectionNumber.value = "";
			   	doSubmit('/receipts/rec_inspection_detail.jsp','InspectionHeaderUpdate;InspectionGetFormNumber;RecInspectionRetrieveDetail');
		   <%}%>
	   } else {
	   		frm.inspectionNumberSuffix.value = '<%=poNo%>';
	   }
	 }

	function submitThis()
	{
		frm.formtype.value = "REC" ;
		frm.frompage.value = "shopcart" ;
		frm.InspectionHeader_inspectionNumber.value = frm.inspectionNumberPreffix.value + "-" + frm.inspectionNumber.value + "-" + frm.inspectionNumberSuffix.value;
		frm.ReceiptLine_inspLocation.value = frm.InspectionHeader_location.value
		frm.ReceiptLine_inspArea.value = frm.InspectionHeader_area.value
		frm.ReceiptLine_inspStorage.value = frm.InspectionHeader_storage.value
		doSubmit('/receipts/rec_item_step_1.jsp','InspectionUpdateReceiptDetail;ReceiptHeaderUpdate;ReceiptLineUpdateById;ReceiptHeaderDataRetrieve;PoHeaderRetrieveById;ReceiptLineRetrieve') ;
	}

	function submitCancel()
	{
		frm.formtype.value = "REC" ;
		frm.frompage.value = "shopcart" ;
		doSubmit('/receipts/rec_item_step_1.jsp','ReceiptHeaderDataRetrieve;PoHeaderRetrieveById;ReceiptLineRetrieve') ;
	}

	function optionInspCriteria()
	{
		doSubmit('/receipts/rec_inspection_crit.jsp','InspectionUpdateReceiptDetail;RecInspectionRetrieveCriteria;ReceiptHeaderUpdate');
	}

	function optionInspComments()
	{
		doSubmit('/receipts/rec_ins_notes.jsp','InspectionUpdateReceiptDetail;DocCommentRetrieveByHeader;ReceiptHeaderUpdate');
	}

	function optionInspAttachments()
	{
		doSubmit('/receipts/rec_ins_attachments.jsp','InspectionUpdateReceiptDetail;DocAttachmentRetrieveByHeader;ReceiptHeaderUpdate');
	}

	function optionInspMte() {
		var inspected = frm.ReceiptLine_qtyStep1Received.value;
		if(inspected == null || inspected == "0.00"){
			alert("You must inspect a quantity greater than zero!");
		} else {
			doSubmit('receipts/rec_inspection_mte.jsp','InspectionUpdateReceiptDetail;InspectionMteRetrieve;ReceiptHeaderUpdate');
		}
	}

	function optionInspDiscrep() {
		var inspected = frm.ReceiptLine_qtyStep1Received.value;
		if(inspected == null || inspected == "0.00"){
			alert("You must inspect a quantity greater than zero!");
		} else {
			doSubmit('receipts/rec_inspection_discrep.jsp','InspectionUpdateReceiptDetail;InspectionDiscrepRetrieve;ReceiptHeaderUpdate');
		}
	}

	function optionInspWorksheet()
	{
		popupParameters = "ReceiptHeader_icRecHeader=<%=receiptHeader.getIcRecHeader()%>";
		popupParameters = popupParameters + ";ReceiptLine_icRecLine=<%=receiptLine.getIcRecLine()%>";
		popupParameters = popupParameters + ";PoHeader_icPoHeader=<%=receiptHeader.getIcPoHeader()%>";
		popupParameters = popupParameters + ";PoLine_icPoLine=<%=receiptLine.getIcPoLine()%>";
		popupParameters = popupParameters + ";InspectionHeader_icInspNo=<%=icInspNo%>";
		popupParameters = popupParameters + ";InspectionHeader_icMsrLine=<%=icMsrLine%>";
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";
		popupParameters = popupParameters + ";viewNow=Y";
	
		doSubmitToPopup('/system/popupDocAttachment.jsp', 'PrintRecInspWorksheetPdf', 'width=775px', 'height=850px');
	}


	function optionInspPrint()
	{
		popupParameters = "ReceiptHeader_icRecHeader=<%=receiptHeader.getIcRecHeader()%>";
		popupParameters = popupParameters + ";ReceiptLine_icRecLine=<%=receiptLine.getIcRecLine()%>";
		popupParameters = popupParameters + ";PoHeader_icPoHeader=<%=receiptHeader.getIcPoHeader()%>";
		popupParameters = popupParameters + ";PoLine_icPoLine=<%=receiptLine.getIcPoLine()%>";
		popupParameters = popupParameters + ";InspectionHeader_icInspNo=<%=icInspNo%>";
		popupParameters = popupParameters + ";InspectionHeader_icMsrLine=<%=icMsrLine%>";
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";
		popupParameters = popupParameters + ";viewNow=Y";
	
		doSubmitToPopup('/system/popupDocAttachment.jsp', 'PrintRecInspEmail', 'width=775px', 'height=850px');
	}

	function addNew()
	{
		myTable = document.getElementById("manufacturers");
		TotalRows = TotalRows+1;
		count = myTable.rows.length;
		myRow = myTable.insertRow(count);
		maxRows++;

		myCell = myRow.insertCell();
		id = "mfrDocumentType";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"InspectionMfr_documentType\" SIZE=\"15\" MAXLENGTH=\"15\" value=\"\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "ins-documentType")%>
		<%=HtmlWriter.cellDisplay(oid, "ins-documentType")%>

		myCell = myRow.insertCell();
		id = "mfrName";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"InspectionMfr_mfrName\" SIZE=\"35\" MAXLENGTH=\"40\" value=\"\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "ins-mfrName")%>
		<%=HtmlWriter.cellDisplay(oid, "ins-mfrName")%>

		myCell = myRow.insertCell();
		id = "mfrNo";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"InspectionMfr_mfrNo\" SIZE=\"20\" MAXLENGTH=\"30\" value=\"\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "ins-mfrNo")%>
		<%=HtmlWriter.cellDisplay(oid, "ins-mfrNo")%>

		myCell = myRow.insertCell();
		id = "mfrHeatLot";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"InspectionMfr_mfrHeatLot\" SIZE=\"20\" MAXLENGTH=\"30\" value=\"\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "ins-mfrHeatLot")%>
		<%=HtmlWriter.cellDisplay(oid, "ins-mfrHeatLot")%>

		myCell = myRow.insertCell();
		id = "mfr_del_" + (count);
		myCell.id = id;
		myCell.innerHTML = "<A href=\"javascript: if (confirm('Are you sure you wish to delete this Manufactuer?')) { deleteMe(" + (count) + "); } void(0)\" TABINDEX=\"999\"><IMG SRC=\"<%=contextPath%>/images/delete.gif\" ALT=\"Delete\" BORDER=\"0\"></A>";

		setCurrentRow(count - 1);
		count++;
	}

	function setCurrentRow(row)
	{
		currentRow = row;
	}

	function deleteMe(row)
	{
		maxRows = maxRows - 1;	//needs to be set so that when validateForm is called it has the appropriate row count
		accRows = accRows = 1;

		myTable = document.getElementById("manufacturers");
		var currentRows = myTable.rows.length - 1;

		if (currentRows < 0)
		{
			return;
		}
		else if (currentRows > 1)
		{
			for (var i = row; i < (currentRows); i++)
			{
				frm.InspectionMfr_documentType[i].value = frm.InspectionMfr_documentType[i + 1].value ;
				frm.InspectionMfr_mfrName[i].value = frm.InspectionMfr_mfrName[i + 1].value;
				frm.InspectionMfr_mfrNo[i].value = frm.InspectionMfr_mfrNo[i + 1].value;
				frm.InspectionMfr_mfrHeatLot[i].value = frm.InspectionMfr_mfrHeatLot[i + 1].value;
			}
		}

		myTable = document.getElementById("manufacturers");
		myTable.deleteRow(currentRows);
	}


	function openDocument(row)
	{
		var filename = "";
		if (document.all("docFilename").length > 1)
		{
			filename = frm.docFilename[row].value;
		}
		else
		{
			filename = frm.docFilename.value;
		}
		openAttachment(filename);
	}

		function deleteAll()
	{
		if (confirm("Are you sure you wish to delete all Manufacturers?"))
		{
			myTable = document.getElementById("manufactuers");
			count = myTable.rows.length - 1;

			maxRows = 0;

			for(i = 0; i < count; i++)
			{
				myTable.deleteRow(1);
			}

			addNew();

		}
	}

	function setGiDetails(code) {
		var myArea = document.getElementById("giDetails");
		if (code == "GI") {
			myArea.style.display = "block";
			myArea.style.visibility = "visible" ;
		} else {
			myArea.style.display = "none";
			myArea.style.visibility = "hidden" ;
		}
	}

	function toggleDetailsDisplay(areaName, type) {
		var myAreas = document.getElementsByName(areaName);
		var myArea;
		var myImg = document.getElementById(areaName + "Img");
		var myStyle;
		var hideArea = false;

		for (i = 0; i < myAreas.length; i++) {
			myArea = myAreas[i];
			myStyle = myArea.style.display ;
			if (myArea.style.display == "none") {
				myArea.style.display = "block";
				if (type == "bavDetailOptions") {
					myArea.style.visibility = "visible" ;
				}
			}
			else {
				hideArea = true;
				myArea.style.display = "none";
				if (type == "bavDetailOptions") {
					myArea.style.visibility = "hidden" ;
				}
			}
		}

		if (type == undefined) {
			type = "Details";
		}

		if (type != "addItemOptions" && type != "bavDetailOptions")
		{
			/*	don't want to do this for the add item options	*/
			if (hideArea)
			{
				myImg.src = "<%=contextPath%>/images/arrows_down.gif";
				myImg.alt = "View" + type;
			}
			else
			{
				myImg.src = "<%=contextPath%>/images/arrows_up.gif";
				myImg.alt = "Hide " + type;
			}
		}
	}

	function setFirstEmpty()
	{
		var myTableEmpty = document.getElementById("manufacturers");
		var countEmpty = myTableEmpty.rows.length;

		for (var j = 0; j < (countEmpty); j++)
		{
			if (frm.InspectionMfr_documentType[j].value == "")
			{
				setCurrentRow(j);
				break;
			}
		}
	}

	function printLabel(status)
	{
		popupParameters = "ReceiptHeader_icRecHeader=<%=receiptLine.getIcRecHeader()%>";
		popupParameters = popupParameters + ";ReceiptLine_icRecLine=<%=receiptLine.getIcRecLine()%>";
		popupParameters = popupParameters + ";PoHeader_icPoHeader=<%=receiptHeader.getIcPoHeader()%>";
		popupParameters = popupParameters + ";PoLine_icPoLine=<%=receiptLine.getIcPoLine()%>";
		popupParameters = popupParameters + ";RequisitionHeader_icReqHeader=<%=s_ic_req_header%>";
		popupParameters = popupParameters + ";RequisitionLine_icLineHistory=<%=receiptLine.getIcLineHistory()%>";
		popupParameters = popupParameters + ";InspectionHeader_icMsrLine=" + frm.InspectionHeader_icMsrLine.value;
		popupParameters = popupParameters + ";InspectionHeader_icInspNo=" + frm.InspectionHeader_icInspNo.value;
		popupParameters = popupParameters + ";formType='REC'";
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";
		popupParameters = popupParameters + ";viewNow=Y";
		popupParameters = popupParameters + ";status=" + status;

		doSubmitToPopup('/system/popupDocAttachment.jsp', 'PrintRecLineLabelPdf', 'width=775px', 'height=850px');
	}

// End Hide script -->
</SCRIPT>