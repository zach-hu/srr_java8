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

	ReceiptHeader  receiptHeader = (ReceiptHeader) request.getAttribute("receiptHeader");
	ReceiptLine  receiptLine = (ReceiptLine) request.getAttribute("receiptLine")	;
	if (receiptHeader ==null ) receiptHeader = new ReceiptHeader();
	if (receiptLine == null) receiptLine = new ReceiptLine() ;

	RequisitionLine reqLine = (RequisitionLine) request.getAttribute("requisitionLine");
	if (reqLine == null) reqLine = new RequisitionLine() ;

	String	s_req_status = (String) request.getAttribute("RequisitionHeader_status");
	String	s_fiscal_year = (String) request.getAttribute("RequisitionHeader_fiscalYear");
	String	s_req_subtype = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_rqSubType"));
	String	s_item_location = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_itemLocation"));

	String	s_inspection_action = HiltonUtility.ckNull((String) request.getAttribute("inspectionAction"));
	if(HiltonUtility.isEmpty(s_inspection_action)){
		s_inspection_action = "UPDATE";
	}

	String	s_req_type = reqLine.getReqType();
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_account_separator = propertiesManager.getProperty("MISC", "AccountSeparator", "-");
	String	s_price_decimals = propertiesManager.getProperty("MISC", "PriceDecimals", "2");
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String	s_minmax_restrict = propertiesManager.getProperty("REQ OPTIONS", "MinMaxRestrict", "N");
	String 	s_duomRequired = propertiesManager.getProperty("INVENTORY", "DUALUOMREQUIRED", "N");
	String  defaultByCommodity = propertiesManager.getProperty("ACCOUNTS", "DEFAULTBYCOMMODITY", "N");

    String icMsrLine = (String) 	request.getAttribute("InspectionHeader_icMsrLine") ;
    List commentList = (List) request.getAttribute("docCommentList") ;
    if (commentList == null) commentList = new ArrayList() ;

    List attachmentList = (List) request.getAttribute("docAttachmentList") ;
    if (attachmentList == null) attachmentList = new ArrayList() ;

    List mfgList = (List) request.getAttribute("mfgList") ;
    if (mfgList == null) mfgList = new ArrayList() ;

	BigDecimal b_req_ic_header = reqLine.getIcReqHeader();
	BigDecimal b_req_ic_line = reqLine.getIcReqLine();
	BigDecimal bd_line_number = HiltonUtility.getBigDecimalFormatted(reqLine.getLineNumber(), 0);
	String	s_line_count = (String) request.getAttribute("lineCount");
	String	s_from_page = (String) request.getAttribute("frompage");
	String	s_from_step = (String) request.getAttribute("fromstep");

	if (s_from_page == null)  s_from_page = "" ;
	String s_return_page = "/requests/req_item.jsp";
	String s_return_method = "RequisitionLineRetrieve";
	if (s_from_page.equals("inspection")) {
		s_return_page = "/requests/req_inspections.jsp" ;
		s_return_method = "RequisitionRetrieveInspections" ;
	}
	else if (s_from_page.equals("review")) {
		s_return_page = "/requests/req_review.jsp" ;
		s_return_method = "RequisitionRetrieve" ;
	}

	BigDecimal bd_item_qty = HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid);
	BigDecimal bd_duomQuantity = HiltonUtility.getFormattedQuantity(reqLine.getDuomQuantity(), oid);
	String s_item_asset = reqLine.getAsset();
	String s_item_taxable = reqLine.getTaxable();
	String s_udf1 = reqLine.getUdf1Code();
	String s_shelfLife	= reqLine.getShelfLifeRqd();
	BigDecimal b_item_unitprice = HiltonUtility.getFormattedPrice(reqLine.getUnitPrice(), oid);
	BigDecimal	bd_um_factor = reqLine.getUmFactor();
	String s_receipt_required = reqLine.getReceiptRequired();
	String s_req_line_status = reqLine.getStatus();
	String s_req_number = reqLine.getRequisitionNumber();
	BigDecimal b_ic_po_line = reqLine.getIcPoLine();
	boolean fpeUser = user.isAFpe() ;
	boolean engineerUser = user.isAEngineer() ;
	boolean msrEngineer = user.isAnAdministeredBy();

	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
	if (requisitionHeader == null) requisitionHeader = new RequisitionHeader() ;

	String s_udf_1_code = requisitionHeader.getUdf1Code();
	String s_udf_14_code = requisitionHeader.getUdf14Code();

	InspectionHeader inspectionHeader = (InspectionHeader) request.getAttribute("inspectionHeader") ;

	List inspectionLineList = (List) request.getAttribute("inspectionLineList");
	if(inspectionLineList == null)	inspectionLineList = new ArrayList();

	BigDecimal icInspNo  = new BigDecimal("-1") ;
	BigDecimal icRecNo	= new BigDecimal("0");   //  cannot be null
	String inspectType ="" ;
	String udf2Code = ""; // ncr no

	String poNo = "" ;  // Where's it come from, this is a req
	String udf3Code = ""; //EDWS Box No

	String inspType = ""; // Not sure on this one (Why 2 inspection types)
	String purchaseSpecs = "" ;

	String inspectorId = "" ;
	String udf4Code = "" ; // Dwg/Std

	String inspectorName = "" ;
	String remoteInspId = "" ;

	String engineerId = "" ;
	String remoteInspectorName = "" ;

	String engineerName = "" ;
	String area = "" ;

	String storageFacility = "" ;

	String location = "" ;

	String cgdNo = "";
	BigDecimal cgdRev = new BigDecimal(0);

	if (inspectionHeader != null) {
		 icInspNo = inspectionHeader.getComp_id().getIcInspNo() ;
		 icRecNo = inspectionHeader.getIcRecLine();

		 inspectType = inspectionHeader.getInspectType() ;
		 udf2Code = inspectionHeader.getUdf02() ;  // ncr no

		 poNo = "" ;  // Where's it come from, this is a req
		 udf3Code = inspectionHeader.getUdf03() ; 						//EDWS Box No

		 inspType = inspectionHeader.getInspType() ; 				// Not sure on this one (Why 2 inspection types)
		 purchaseSpecs = inspectionHeader.getPurchaseSpecs() ;

		 udf4Code = inspectionHeader.getUdf04() ; // Dwg/Std

		 inspectorId = inspectionHeader.getInspectorId() ;
		 remoteInspId = inspectionHeader.getRemoteInspId() ;
		 engineerId = inspectionHeader.getEngineerId() ;

			area = inspectionHeader.getArea() ;

		 storageFacility = inspectionHeader.getStorage() ;
		location = inspectionHeader.getLocation() ;
		cgdNo = inspectionHeader.getCgdNo();
		cgdRev = HiltonUtility.ckNull(inspectionHeader.getCgdRev());
	}

	 UserProfile inspector = UserManager.getInstance().getUser(oid, inspectorId);
	 UserProfile engineer = UserManager.getInstance().getUser(oid, engineerId);
	 UserProfile remoteInspector = UserManager.getInstance().getUser(oid, remoteInspId);

	 engineerName = engineer.getDisplayName() ;
	 remoteInspectorName = remoteInspector.getDisplayName() ;
	 inspectorName = inspector.getDisplayName() ;

	boolean commentsEnabled = false ;
	boolean attachmentsEnabled = false ;

	boolean disableEdit = true;
	
	switch(s_req_status){
		case DocumentStatus.REQ_PLANNING:
			if(HiltonUtility.ckNull(requisitionHeader.getOwner()).equals(uid))
				disableEdit = false;	
			break;
		case DocumentStatus.REQ_PLANNING_RECALLED:
			if(HiltonUtility.ckNull(requisitionHeader.getOwner()).equals(uid) || fpeUser || msrEngineer)
				disableEdit = false;
			break;
		case DocumentStatus.REQ_PLANNING_REJECTED:
			if(HiltonUtility.ckNull(requisitionHeader.getOwner()).equals(uid) || fpeUser || msrEngineer)	
				disableEdit = false;
			break;
		case DocumentStatus.REQ_PLANNING_APPROVING:
			if(fpeUser || msrEngineer)
				disableEdit = false;
			break;
		default:
			
	}
%>
<tsa:hidden name="InspectionHeader_icInspNo" value="<%=icInspNo%>"/>
<tsa:hidden name="InspectionHeader_icMsrLine" value="<%=icMsrLine%>"/>
<tsa:hidden name="InspectionHeader_icRecLine" value="<%=icRecNo%>"/>
<tsa:hidden name="InspectionHeader_cgdRev" value="<%=cgdRev.toString()%>"/>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="RequisitionLine_icReqHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=reqLine.getRequisitionNumber()%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=s_req_status%>"/>
<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_req_type%>"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="<%=s_req_subtype%>"/>
<tsa:hidden name="RequisitionLine_icReqLine" value="<%=b_req_ic_line%>"/>
<tsa:hidden name="RequisitionLine_lineNumber" value="<%=bd_line_number%>"/>
<tsa:hidden name="RequisitionLine_itemSource" value="<%=reqLine.getItemSource()%>"/>
<tsa:hidden name="RequisitionHeader_requiredBy" value="<%=request.getAttribute(\"RequisitionHeader_requiredBy\")%>"/>
<tsa:hidden name="RequisitionHeader_itemLocation" value="<%=s_item_location%>"/>
<tsa:hidden name="RequisitionHeader_requisitionerCode" value="<%=reqLine.getRequisitionerCode()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="Account_icLine" value="<%=b_req_ic_line%>"/>
<tsa:hidden name="Account_accountType" value="RQL"/>
<tsa:hidden name="DocComment_icHeader" value="<%=icInspNo%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=icInspNo%>"/>
<tsa:hidden name="lineCount" value="<%=s_line_count%>"/>
<tsa:hidden name="stdTable_tableType" value=""/>
<tsa:hidden name="formtype" value="INS"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="frompage" value="<%=s_from_page%>"/>
<tsa:hidden name="formstep" value="s_from_step"/>

<tsa:hidden name="lineUpdated" value="false"/>
<tsa:hidden name="lineToRetrieve" value=""/>
<tsa:hidden name="catalogId" value="<%=reqLine.getCatalogId()%>"/>
<tsa:hidden name="writehistory" value="N"/>
<tsa:hidden name="originalQuantity" value=""/>
<tsa:hidden name="originalPrice" value=""/>
<tsa:hidden name="reqaction" value=""/>
<tsa:hidden name="duplicateItem" value="FALSE"/>
<tsa:hidden name="Commodity_commodity" value=""/>
<tsa:hidden name="accountFLD2" id="accountFLD2" value=""/>
<tsa:hidden name="updateUdf10" value="N"/>
<tsa:hidden name="commodityUpdateAccount" value=""/>
<tsa:hidden name="newAccount_icHeader" value="<%=b_req_ic_header%>"/>
<tsa:hidden name="newAccount_icLine" value="<%=b_req_ic_line%>"/>
<tsa:hidden name="originalCommodityCode" value="<%=reqLine.getCommodityCode()%>"/>
<tsa:hidden name="currentPage" value="item"/>
<tsa:hidden name="as_rows" value="<%=mfgList.size()%>"/>
<tsa:hidden name="as_maxrows" value="0"/>

<tsa:hidden name="inspectionAction" value="<%=s_inspection_action %>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="req-type" defaultString="Req Type"/>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-detail", "Inspection Detail")%>  </div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "msrNumber", "MSR #")%>:</b></td>
			<td width=150px><%=headerEncoder.encodeForHTML(s_req_number)%></td>
		</tr>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=150px><%=DocumentStatus.toString(s_req_status, oid)%></td>
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
<div style="margin-top: 10px;margin-left: 10px;">
<tsa:label labelName="highlight-message" defaultString="Fields highlighted in yellow are required." checkRequired="true"></tsa:label>
</div>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
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
							<td width=175px <%=HtmlWriter.isVisible(oid, "ins-inspectType")%> nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-inspectType", "Inspection", true)%>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "ins-inspection")%>><select name="InspectionHeader_inspectType" value="<%=inspectType%>" >
												<option value="RI"  <% if (inspectType.equals("RI") || HiltonUtility.isEmpty(inspectType)) {%> SELECTED <%}%>><tsa:label labelName="ins-receiptInspection" defaultString="Receipt Inspection (RICP)"></tsa:label></option>
												<option value="GI" <% if (inspectType.equals("GI") ) {%> SELECTED <%}%>><tsa:label labelName="ins-generalInspection" defaultString="General Inspection"></tsa:label></option>
											</select>
							</td>
						</tr>
						<tr>
							<tsa:td width="175px" field="ins-cgdNo" noWrap="nowrap" align="right"><tsa:label labelName="ins-cgdNo" defaultString="CGD No" checkRequired="true"/>:&nbsp;</tsa:td>
							<tsa:td field="ins-cgdNo"><tsa:input type="text" name="InspectionHeader_cgdNo" size="30" tabIndex="1" maxLength="20" value="<%=cgdNo%>" onchange="upperCase(this);void(0);"/></tsa:td>
						</tr>
						<tr>
							<td width=175px style="visibility:hidden; display:none;" nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-poNo", "PO No", true)%>:&nbsp;</td>
							<td style="visibility:hidden; display:none;"><input type=text name="PoNo" tabindex=1 size=20 maxlength=20 value="<%=poNo%>" onchange="upperCase(this);void(0);"></td>
						</tr>
						<tr>
							<td width=175px style="visibility:hidden; display:none;" nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-inspType", "Insp Type", true)%>:&nbsp;</td>
							<td style="visibility:hidden; display:none;"><input type=text name="InspectionHeader_inspType" tabindex=1 size=10 maxlength=10 value="<%=inspType%>" onchange="upperCase(this);void(0);"></td>
						</tr>
						<tr>
							<td width=175px style="visibility:hidden; display:none;" nowrap align=right><a href="javascript: browseLookup('InspectionHeader_inspectorId', 'inspector'); void(0);" title="Click here to choose a <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-inspectorId", "inspector Id")%> for this order or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-inspectorId", "Inspector Id")%> in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-inspectorId", "Inspector Id", true)%>:</a>&nbsp;
							<td style="visibility:hidden; display:none;"><input type=text name="InspectionHeader_inspectorId" tabindex=1 size=15 maxlength=15 value="<%=inspectorId%>" onchange="upperCase(this);  getNewInfo('inspector', this);"></td>
						</tr>
						<tr>
							<td width=175px style="visibility:hidden; display:none;" nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-inspectorName", "Inspector Name", true)%>:&nbsp;</td>
							<td style="visibility:hidden; display:none;"><input type=text name="inspectorName" tabindex=1 size=30 maxlength=30 value="<%=inspectorName%>"  onfocus="this.blur();" disabled></td>
						</tr>
						<tr>
							<td width=175px style="visibility:hidden; display:none;" nowrap align=right><a href="javascript: browseLookup('InspectionHeader_engineerId', 'engineer'); void(0);" title="Click here to choose a <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-engineerId", "Engineer Id")%> for this order or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-buyer", "Engineer Id")%> in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-engineerId", "Engineer Id", true)%>:</a>&nbsp;
							<td style="visibility:hidden; display:none;"><input type=text name="InspectionHeader_engineerId" tabindex=1 size=15 maxlength=15 value="<%=engineerId%>" onchange="upperCase(this);  getNewInfo('engineer', this);"></td>
						</tr>
						<tr>
							<td width=175px style="visibility:hidden; display:none;" nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-engineerName", "Engineer Name", true)%>:&nbsp;</td>
							<td style="visibility:hidden; display:none;"><input type=text name="engineerName" tabindex=1 size=30 maxlength=30 value="<%=engineerName%>"  onfocus="this.blur();"  disabled></td>
						</tr>
						</table>
						<!-- left side end -->
					</td>
					<td colspan="2" valign=top>
						<!-- right side -->
						<table border="0" cellpadding="0" cellspacing="1" width=100%>
						<tr>
							<td width=175px style="visibility:hidden; display:none;" nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-udf02", "NCR No", true)%>:&nbsp;</td>
							<td style="visibility:hidden; display:none;"><input type=text name="InspectionHeader_udf02" tabindex=1 size=15 maxlength=15 value="<%=udf2Code%>" onchange="upperCase(this);void(0);"></td>
						</tr>
						<tr>
							<td width=175px style="visibility:hidden; display:none;" nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-purchaseSpecs", "Purchase Specs", true)%>:&nbsp;</td>
							<td style="visibility:hidden; display:none;"><input type=text name="InspectionHeader_purchaseSpecs" tabindex=1 size=20 maxlength=20  value="<%=purchaseSpecs%>" onchange="upperCase(this);void(0);"></td>
						</tr>
						<tr>
							<td width=175px style="visibility:hidden; display:none;" nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-udf04", "Dwg/Std", true)%>:&nbsp;</td>
							<td style="visibility:hidden; display:none;"><input type=text name="InspectionHeader_udf04" tabindex=1 size=15 maxlength=15 value="<%=udf4Code%>" onchange="upperCase(this);void(0);"></td>
						</tr>
						<tr>
							<td width=175px style="visibility:hidden; display:none;" nowrap align=right><a href="javascript: browseLookup('InspectionHeader_remoteInspId', 'inspector'); void(0);" title="Click here to choose a <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-remoteInspId", "Remote Inspector Id")%> for this order or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-remoteInspId", "Remote Inspector Id")%> in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-remoteInspId", "Remote Inspector Id", true)%>:</a>&nbsp;
							<td style="visibility:hidden; display:none;"><input type=text name="InspectionHeader_remoteInspId" tabindex=1 size=15 maxlength=15 value="<%=remoteInspId%>" onchange="upperCase(this); getNewInfo('remoteinspector', this);"></td>
						</tr>
						<tr>
							<td width=175px style="visibility:hidden; display:none;" nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-inspName", "Remote Insp Name", true)%>:&nbsp;</td>
							<td style="visibility:hidden; display:none;"><input type=text name="remoteInspName" tabindex=1 size=30 maxlength=30 value="<%=remoteInspectorName%>"  onfocus="this.blur();" disabled></td>
						</tr>
						<tr>
							<td width=175px style="visibility:hidden; display:none;" nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-area", "Area", true)%>:&nbsp;</td>
							<td style="visibility:hidden; display:none;"><input type=text name="InspectionHeader_area" tabindex=1 size=15 maxlength=15 value="<%=area%>" onchange="upperCase(this);void(0);"></td>
						</tr>
						<tr>
							<td width=175px style="visibility:hidden; display:none;" nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-area", "Storage Facility", true)%>:&nbsp;</td>
							<td style="visibility:hidden; display:none;"><input type=text name="InspectionHeader_storage" tabindex=1 size=15 maxlength=15 value="<%=area%>" onchange="upperCase(this);void(0);"></td>
						</tr>
						<tr>
							<td width=175px style="visibility:hidden; display:none;" nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-loc", "Location", true)%>:&nbsp;</td>
							<td style="visibility:hidden; display:none;"><input type=text name="InspectionHeader_location" tabindex=1 size=15 maxlength=15 value="<%=location%>" onchange="upperCase(this);void(0);"></td>
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
		<td class=browseHdr><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inspectionOptions", "Inspection Options")%></td></tr>
		<tr>
			<td class=browseRow>
				<table border=0 cellpadding=0 cellspacing=0 width=225px>
				<tr height=15px><td nowrap align=center><a href="javascript: optionInspCriteria(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "optionInspCriteria", "Inspection Criteria")%></a></td></tr>
				<tr  height=15px><td nowrap align=center><a href="javascript: optionInspComments(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "optionInspComments", "Comments")%></a></td></tr>
				<tr style="visibility:hidden; display:none;" height=15px><td nowrap align=center><a href="javascript: optionInspAttachments(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "optionInspAttachments", "Attachments")%></a></td></tr>
				<tr style="visibility:hidden; display:none;" height=15px><td nowrap align=center><a href="javascript: optionInspWorksheet(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "optionInspWorkSheet", "Work Sheet")%></a></td></tr>
				<tr style="visibility:hidden; display:none;" height=15px><td nowrap align=center><a href="javascript: optionInspInspection(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "optionInspInspection", "Inspection")%></a></td></tr>
				<tr style="visibility:hidden; display:none;" height=15px><td nowrap align=center><a href="javascript: optionInspPrint(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "optionInspPrint", "Print/Email")%></a></td></tr>
				<tr height=15px><td nowrap align=center><a href="javascript: optionInspHistory(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "optionInspHistory", "History")%></a></td></tr>
				</table>
		</td>
		</tr>
		</table>
</td>
</tr>
</table>


<table style="visibility:hidden; display:none;" border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
<td>
		<% if ( !  (disableEdit || s_return_method.startsWith("Req") == false)) { %>
		<table border=0 cellpadding=2 cellspacing=2 align=center width=80%>
		<tr>
			<td align=center  width=100%><a href="javascript: addNew(); void(0);"><font class="reset"><b>Add New Manufacturer</b></font></a></td>
		</tr>
		</table>
		<% } else { %>
			<br>
		<% } %>
</td>
</tr>
	<td>
		<table  width=80% border="0" cellpadding="2" cellspacing="2" align="center">
				<tr>
					<td width=40% <%=HtmlWriter.isVisible(oid, "ins-mfgName")%> nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-mfgName", "Manufacturer", true)%></td>
					<td width=25% <%=HtmlWriter.isVisible(oid, "ins-mfgNo")%> nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-mfgNo", "Mfg No.", true)%></td>
					<td width=25% <%=HtmlWriter.isVisible(oid, "ins-mfgHeatLot")%> nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-mfgHeatLot", "Heat / Lot", true)%></td>
					<td width=5%>&nbsp;</td></ts>
				</tr>
		</table>
		</td>
<tr>
	<td>

		<table id="manufacturers" width=80% border="0" cellpadding="2" cellspacing="2" align="center">
		<%
			String s_set_row = "";
			int i_rowcount = 0;
			for (i = 0; i < mfgList.size(); i++)
			{
				s_set_row = "ONFOCUS='setCurrentRow(" + i_rowcount + ");'";
				HashMap mfg = (HashMap) mfgList.get(i) ;

				String mfgName = HiltonUtility.ckNull((String) mfg.get("mfgName") );
				String mfgNo = HiltonUtility.ckNull((String) mfg.get("mfgNo") );
				String mfgHeatLot = HiltonUtility.ckNull( (String) mfg.get("mfgHeatLot")) ;

		%>
						<tr>
							<td  width=40% id="mfgNameId"><input type="text" name="mfgName" value="<%=mfgName%>" size="35" maxLength="40" onchange="upperCase(this);" <%=s_set_row%>/></td>
							<td width=25% id="mfgNoId"><input type=text name="mfgNo" value="<%=mfgNo%>" size=20 maxlength=30 onchange="upperCase(this);" <%=s_set_row%>></td>
							<td width=25% id="mfgHeatLotId"><input type=text name="mfgHeatLot" value="<%=mfgHeatLot%>" size=20 maxlength=30 onchange="upperCase(this);" <%=s_set_row%>></td>
							<td width=5% id="mfg_del_<%=i%>"><a href="javascript: if (confirm('Are you sure you wish to delete this Manufacturer?')) { deleteMe(<%=i%>); } void(0);" tabindex="999"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></td>
						</tr>
		<%		i_rowcount++;
				}	//end for loop
		%>
		</table>

		<br>
		<tsa:hidden name="as_maxrows" value="<%=i_rowcount%>"/>
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
					<td width=10% height=18px class=browseHdr align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-criteria", "Criteria")%></b></td>
					<td width=10% height=18px class=browseHdr align=center>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-inspCode", "Insp Code")%>.</b></td>
					<td width=40% height=18px class=browseHdr align=center>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ins-codeDescription", "Code Description")%>.</b></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>

<%
	if (inspectionLineList != null)
	{
		for (i = 0;i < inspectionLineList.size(); i++)
		{
//			Account account = (Account) inspectionLineList.get(i);
			InspectionLine inspLine = (InspectionLine) inspectionLineList.get(i) ;
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=2% class=browseRow>&nbsp;</td>
					<td width=10% class=browseRow align=center><%=HiltonUtility.ckNull(inspLine.getCritNo())%></td>
					<td width=10% class=browseRow align=center valign=top><%=HiltonUtility.ckNull(inspLine.getInspectCode())%></td>
					<td width=40% class=browseRow align=left valign=top><%=HiltonUtility.ckNull(inspLine.getInspCodeDesc())%></td>
				</tr>
			</table>
<%	}
	}
	if (inspectionLineList != null && inspectionLineList.size() > 0) { %>
<%} else {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "noInspectionsItem", "There are no inspection records for this item")%>.</b></td></tr>
				</table>
<%}%>
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
					<td width=2% height=18px class=browseHdr></td></a>
					<td width=64% height=18px class=browseHdr>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "comment", "Comment")%></b></td>
					<td width=8% height=18px class=browseHdr align=center>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "print", "Print")%></b></td>
					<td width=7% height=18px class=browseHdr align=center>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bold", "Bold")%></b></td>
					<td width=16% height=18px class=browseHdr align=center>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "placement", "Placement")%></b></td>
					<%	if (commentList != null && commentList.size() > 0)
					{%>
					<td width=3% height=18px class=browseHdr align=center><a href="javascript: toggleDetailsDisplay('commentDetails', 'Details'); void(0);"><img id='commentDetailsImg' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Comments"></a></td>
					<%	}%>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
<%
	String s_placeString = "";
	if (commentList != null)
	{
		for (i = 0;i < commentList.size(); i++)
		{
			DocComment docComment = (DocComment) commentList.get(i);
			DocText docText = docComment.getDocText();
			String s_title = docComment.getCommentTitle();
			String s_print = docComment.getCommentPrint();
			String s_bold = docComment.getCommentBold();
			String s_place = docComment.getCommentPlace();
			String s_text = docText.getStdText();

			if (s_place.equals("A"))
			{
				s_place = "After";
			}
			else if (s_place.equals("B"))
			{
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
<%				if (s_bold.equals("Y")) 	{ %>	<b><% } %><%=s_text%><% if (s_bold.equals("Y")) { %></b><% } %>
					</td>
				</tr>
				</table>
				</div>
<% 	}
	}
	if (commentList == null || commentList.size() == 0) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "thereAreNoCommentsInspection", "There are no comments for this inspection")%>.</b></td></tr>
				</table>
<%}%>
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
							<td width=80% height=18px class=browseHdr>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "attachment", "Attachment")%></b></td>
							<td width=10% height=18px class=browseHdr align=center>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "print", "Print")%></b></td>
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
				for(int ix = 0; ix < attachmentList.size(); ix++) {
					DocAttachment docAttachment = (DocAttachment) attachmentList.get(ix);
					if (docAttachment == null) {
						continue;
					}
					String	filename = docAttachment.getDocFilename();
					String	ext = filename.substring(filename.lastIndexOf(".") + 1);
					ai++;
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=80% class=browseRow>
						<table border=0 cellpadding=0 cellspacing=0 width=100% class=browseRow>
						<tr>
							<td width=25px align=center valign=middle>
<%		if (ext.equalsIgnoreCase("DOC")) {%>
							<a href="javascript: openDocument(<%=ix%>); void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
							<a href="javascript: openDocument(<%=ix%>); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS")) {%>
							<a href="javascript: openDocument(<%=ix%>); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
							<a href="javascript: openDocument(<%=ix%>); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT")) {%>
							<a href="javascript: openDocument(<%=ix%>); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
							<a href="javascript: openDocument(<%=ix%>); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%		} else {%>
							<a href="javascript: openDocument(<%=ix%>); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%		}%>
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

	<% 		}
			}
			if (ai == 0) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
                    <tr><td class=browseRow><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "thereAreNoAttachmentsRequisition", "There are no attachments associated with this requisition")%>.</td></tr>
				</table>
<%		}%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<% if (! disableEdit) { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: submitThis(); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: submitCancel(); void(0);">Return</a></div></td>
	<% }  else { %>
	<td width=100% align=center><div id="pxbutton"><a href="javascript: submitCancel(); void(0);">Return</a></div></td>
	<%  }  %>
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


	if (TotalRows <= 0)
	{
		TotalRows = 0;
		addNew();
	}
	setCurrentRow(0);

	function thisLoad()
	{
		f_StartIt();
		<%

		if (s_req_type.equals("M")) {
			if ((s_req_line_status.compareTo(DocumentStatus.REQ_PLANNING_RECALLED ) == 0 || s_req_line_status.compareTo(DocumentStatus.REQ_PLANNING ) == 0) && ((HiltonUtility.ckNull(requisitionHeader.getOwner()).equals(uid)) || (HiltonUtility.ckNull(requisitionHeader.getRequisitionerCode()).equals(uid)))){
				disableEdit = false;
			} else if((s_req_line_status.compareTo(DocumentStatus.REQ_PLANNING_REJECTED ) == 0 || s_req_line_status.compareTo(DocumentStatus.REQ_PLANNING_APPROVING ) == 0) && (fpeUser || msrEngineer)){
				disableEdit = false;
			} 
    } else {
    	disableEdit = (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) >= 0 && s_req_status.compareTo(DocumentStatus.RFQ_PRICED) != 0 && s_req_status.compareTo(DocumentStatus.TEMPLATE) != 0) ;
    }
		if (disableEdit ) { %>
		checkInputSettings();
		<%}
		if (! disableEdit) { %>
			allowInputEdit(frm.InspectionHeader_inspectType, true);
			allowInputEdit(frm.InspectionHeader_cgdNo, true);
		<% }
		%>
	}

	function optionInspHistory()
	{
		popupParameters = "HistoryLog_icLineHistory=<%=reqLine.getIcLineHistory()%>;formtype=REQ;RequisitionLine_icReqLine=<%=reqLine.getIcReqLine()%>;requisitionNumber=<%=headerEncoder.encodeForJavaScript(reqLine.getRequisitionNumber())%>;requisitionType=<%=reqLine.getReqType()%>";
		doSubmitToPopup('/requests/req_history.jsp', 'HistoryLogRetrieveByHistoryLine', 'width=675px', 'height=350px');
	}


	function retrieveInpections()
	{
		doSubmit('/requests/req_inspections_ln.jsp','RequisitionLineUpdate;RequisitionLineRetrieveInspectionst');
	}

	function submitThis()
	{
		frm.formtype.value = "REQ" ;
		frm.frompage.value = "shopcart" ;
		doSubmit('<%=s_return_page%>', 'ReqInspectionDetailUpdate;<%=s_return_method%>');
	}

	function submitCancel()
	{
		var action = "<%=headerEncoder.encodeForJavaScript(s_inspection_action)%>";
		frm.formtype.value = "REQ" ;
		frm.frompage.value = "shopcart" ;

		if(action == "CREATE"){
			doSubmit('<%=s_return_page%>', 'InspectionDetailDelete;<%=s_return_method%>');
		} else {
			doSubmit('<%=s_return_page%>', '<%=s_return_method%>');
		}
	}

	function optionInspCriteria()
	{
		doSubmit('/requests/req_inspection_crit.jsp','ReqInspectionDetailUpdate;InspectionRetrieveDetail');
	}

	function optionInspComments()
	{
		doSubmit('/requests/ins_notes.jsp','ReqInspectionDetailUpdate;DocCommentRetrieveByHeader');
	}

	function optionInspAttachments()
	{
		doSubmit('/requests/ins_attachments.jsp','ReqInspectionDetailUpdate;DocAttachmentRetrieveByHeader');
	}

	function optionInspWorksheet()
	{
	popupParameters = "ReceiptHeader_icRecHeader=<%=receiptHeader.getIcRecHeader()%>";
	popupParameters = popupParameters + ";ReceiptLine_icRecLine=<%=receiptLine.getIcRecLine()%>";
	popupParameters = popupParameters + ";PoHeader_icPoHeader=<%=receiptHeader.getIcPoHeader()%>";
	popupParameters = popupParameters + ";PoLine_icPoLine=<%=receiptLine.getIcPoLine()%>";
	popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
	popupParameters = popupParameters + ";oid=<%=oid%>";
	popupParameters = popupParameters + ";viewNow=Y";

	doSubmitToLookup('', 'PrintRecInspWorksheetPdf', 'width=775px', 'height=850px');
	}


	function optionInspPrint()
	{
	popupParameters = "ReceiptHeader_icRecHeader=<%=receiptHeader.getIcRecHeader()%>";
	popupParameters = popupParameters + ";ReceiptLine_icRecLine=<%=receiptLine.getIcRecLine()%>";
	popupParameters = popupParameters + ";PoHeader_icPoHeader=<%=receiptHeader.getIcPoHeader()%>";
	popupParameters = popupParameters + ";PoLine_icPoLine=<%=receiptLine.getIcPoLine()%>";
	popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
	popupParameters = popupParameters + ";oid=<%=oid%>";
	popupParameters = popupParameters + ";viewNow=Y";

	doSubmitToLookup('', 'PrintRecInspEmail', 'width=775px', 'height=850px');
	}


	function addNew()
	{
		myTable = document.getElementById("manufacturers");
		TotalRows = TotalRows+1;
		count = myTable.rows.length;
		myRow = myTable.insertRow(count);
		maxRows++;

		myCell = myRow.insertCell();
		id = "mfgName";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"mfgName\" SIZE=\"35\" MAXLENGTH=\"40\" value=\"\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "ins-mfgName")%>
		<%=HtmlWriter.cellDisplay(oid, "ins-mfgName")%>

		myCell = myRow.insertCell();
		id = "mfgNo";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"mfgNo\" SIZE=\"20\" MAXLENGTH=\"30\" value=\"\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "ins-mfgNo")%>
		<%=HtmlWriter.cellDisplay(oid, "ins-mfgNo")%>

		myCell = myRow.insertCell();
		id = "mfgHeatLot";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"mfgHeatLot\" SIZE=\"20\" MAXLENGTH=\"30\" value=\"\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "ins-mfgHeatLot")%>
		<%=HtmlWriter.cellDisplay(oid, "ins-mfgHeatLot")%>

		myCell = myRow.insertCell();
		id = "mfg_del_" + (count - 1);
		myCell.id = id;
		myCell.innerHTML = "<A href=\"javascript: if (confirm('Are you sure you wish to delete this Manufactuer?')) { deleteMe(" + (count - 1) + "); } void(0)\" TABINDEX=\"999\"><IMG SRC=\"<%=contextPath%>/images/delete.gif\" ALT=\"Delete\" BORDER=\"0\"></A>";

		setCurrentRow(count - 1);
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

		if (currentRows == 0)
		{
			return;
		}
		else if (currentRows > 1)
		{
			for (var i = row; i < (currentRows - 1); i++)
			{
				frm.mfgName[i].value = frm.mfgName[i + 1].value;
				frm.mfgNo[i].value = frm.mfgNo[i + 1].value;
				frm.mfgHeatLot[i].value = frm.mfgHeatLot[i + 1].value;

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



// End Hide script -->
</SCRIPT>


