<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>

<%

int i;
RequisitionLine reqLine = (RequisitionLine) request.getAttribute("requisitionLine");
String	s_req_status = (String) request.getAttribute("RequisitionHeader_status");
String	s_fiscal_year = (String) request.getAttribute("RequisitionHeader_fiscalYear");
String	s_req_subtype = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_rqSubType"));
String	s_item_location = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_itemLocation"));
String	s_req_type = reqLine.getReqType();
PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
String	s_account_separator = propertiesManager.getProperty("MISC", "AccountSeparator", "-");
String	s_price_decimals = propertiesManager.getProperty("MISC", "PriceDecimals", "2");
String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
String	s_minmax_restrict = propertiesManager.getProperty("REQ OPTIONS", "MinMaxRestrict", "N");
String 	s_duomRequired = propertiesManager.getProperty("INVENTORY", "DUALUOMREQUIRED", "N");
String  defaultByCommodity = propertiesManager.getProperty("ACCOUNTS", "DEFAULTBYCOMMODITY", "N");

List commentList = (List) request.getAttribute("docCommentList") ;
if (commentList == null) commentList = new ArrayList() ;

List attachmentList = (List) request.getAttribute("docAttachmentList") ;
if (attachmentList == null) attachmentList = new ArrayList() ;

String icMsrLine = (String)request.getAttribute("InspectionHeader_icMsrLine");
BigDecimal b_req_ic_header = reqLine.getIcReqHeader();
BigDecimal b_req_ic_line = reqLine.getIcReqLine();
BigDecimal bd_line_number = HiltonUtility.getBigDecimalFormatted(reqLine.getLineNumber(), 0);
String	s_line_count = (String) request.getAttribute("lineCount");
String	s_from_page = (String) request.getAttribute("frompage");
String newStandardCode = (String) request.getAttribute("newStandardCode");

if (newStandardCode == null)
	newStandardCode = "false";

String s_return_page = "/requests/req_item.jsp";
String s_return_method = "RequisitionLineRetrieve";

BigDecimal bd_item_qty = HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid);
BigDecimal bd_duomQuantity = HiltonUtility.getFormattedQuantity(reqLine.getDuomQuantity(), oid);
String s_item_asset = reqLine.getAsset();
String s_item_taxable = reqLine.getTaxable();
String s_udf1 = reqLine.getUdf1Code();
String s_shelfLife	= reqLine.getShelfLifeRqd();
BigDecimal b_item_unitprice = HiltonUtility.getFormattedPrice(reqLine.getUnitPrice(), oid);
BigDecimal	bd_um_factor = reqLine.getUmFactor();
String s_receipt_required = reqLine.getReceiptRequired();
String s_req_number = reqLine.getRequisitionNumber();
BigDecimal b_ic_po_line = reqLine.getIcPoLine();
String s_req_line_status = reqLine.getStatus();
RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
String s_udf_1_code = requisitionHeader.getUdf1Code();
String s_udf_14_code = requisitionHeader.getUdf14Code();
boolean fpeUser = user.isAFpe() ;
boolean engineerUser = user.isAEngineer() ;
boolean msrEngineer = user.isAnAdministeredBy();

InspectionHeader inspectionHeader = (InspectionHeader) request.getAttribute("inspectionHeader");
BigDecimal icInspNo = new BigDecimal("-1");
String std_code = "";
String stdDescription = "" ;
String cgdNo = "";
String cgdRev = "0";

if (inspectionHeader != null)
{
	icInspNo = inspectionHeader.getComp_id().getIcInspNo();
	if (inspectionHeader.getStandardCode() != null && !inspectionHeader.getStandardCode().equalsIgnoreCase("null"))
	{
		std_code = inspectionHeader.getStandardCode();
	}
	if (inspectionHeader.getCgdNo() != null)
		cgdNo = inspectionHeader.getCgdNo();
	if (inspectionHeader.getCgdRev() != null)
		cgdRev = inspectionHeader.getCgdRev().toString();
}

List inspectionLineList = (List) request.getAttribute("inspectionLineList");
if (inspectionLineList == null) inspectionLineList = new java.util.ArrayList() ;

List inspectionCodeList = (List) request.getAttribute("inspectionCodeList") ;
if (inspectionCodeList == null) inspectionCodeList = new ArrayList() ;

List inspectionStdList = (List) request.getAttribute("inspectionStdList") ;
if (inspectionStdList == null) inspectionStdList = new ArrayList() ;

HashMap inspMap = (HashMap) request.getAttribute("inspectionMap") ;
boolean disableEdit = true;
if ((s_req_status.compareTo(DocumentStatus.REQ_PLANNING_RECALLED ) == 0 || s_req_status.compareTo(DocumentStatus.REQ_PLANNING ) == 0) && ((HiltonUtility.ckNull(requisitionHeader.getOwner()).equals(uid)) || (HiltonUtility.ckNull(requisitionHeader.getRequisitionerCode()).equals(uid)))){
	disableEdit = false;
} else if((s_req_status.compareTo(DocumentStatus.REQ_PLANNING_REJECTED ) == 0 || s_req_status.compareTo(DocumentStatus.REQ_PLANNING_APPROVING ) == 0) && (fpeUser || msrEngineer)){
	disableEdit = false;
} 


boolean disableChange = true ;

if (inspectionStdList.size() > 0) {
	InspectionStd inspStd = (InspectionStd) inspectionStdList.get(0) ;
	stdDescription = inspStd.getDescription() ;
	if("true".equals(newStandardCode))
	{
		if (!HiltonUtility.isEmpty(inspStd.getCgdNo()))
		{
			cgdNo = inspStd.getCgdNo();
		}
		else
		{
			cgdNo = "";
		}
		if (inspStd.getCgdRev() != null)
		{
			cgdRev = inspStd.getCgdRev().toString();
		}
		else
		{
			cgdRev = "0";
		}
	}
}
	BigDecimal icInspLine = new BigDecimal("-1");
	String inspectCode = "";
	String seqNo = "";
	String critNo = "";
	String standardCode = "";
	String lockFlag = "";
	String udf1Code = "";
	String udf2Code = "";
	String udf3Code = "";
	String udf4Code = "";
	String udf5Code = "";

	String	s_inspection_action = HiltonUtility.ckNull((String) request.getAttribute("inspectionAction"));
	if(HiltonUtility.isEmpty(s_inspection_action)){
		s_inspection_action = "UPDATE";
	}

	String	s_old_standardCode = HiltonUtility.ckNull((String) request.getAttribute("old_InspectionStd_standardCode"));
	if(HiltonUtility.isEmpty(s_old_standardCode)) s_old_standardCode = inspectionHeader.getStandardCode();
%>
<tsa:hidden name="InspectionHeader_icInspNo" value="<%=icInspNo%>"/>
<tsa:hidden name="InspectionHeader_icMsrLine" value="<%=icMsrLine%>"/>
<tsa:hidden name="InspectionLine_icInspNo" value="<%=icInspNo%>"/>
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
<tsa:hidden name="crit_rows" value="<%=inspectionLineList.size()%>"/>
<tsa:hidden name="insp_rows" value="<%=inspectionCodeList.size()%>"/>
<tsa:hidden name="InspectionStd_standardCode" value="<%=inspectionHeader.getStandardCode()%>"></tsa:hidden>
<tsa:hidden name="old_InspectionStd_standardCode" value="<%=s_old_standardCode%>"></tsa:hidden>
<tsa:hidden name="InspectionStd_inspectType" value="<%=inspectionHeader.getInspectType()%>"></tsa:hidden>
<tsa:hidden name="inspectCode" value=""></tsa:hidden>

<tsa:hidden name="insp_maxrows" value="0"/>
<tsa:hidden name="crit_maxrows" value="0"/>
<tsa:hidden name="newStandardCode" value="<%=newStandardCode%>"/>

<tsa:hidden name="inspectionAction" value="<%=s_inspection_action %>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tsa:tr>
			<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="req-type" defaultString="Req Type"/>&nbsp;Inspection Criteria</div>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" width="3px"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<tsa:td valign="bottom" height="30px" width="*">
		<table cellpadding="1" cellspacing="0" border=0 width=100%>
		<tsa:tr>
			<tsa:td align="right"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "msrNumber", "MSR #")%>:</b></tsa:td>
			<tsa:td width="150px"><%=headerEncoder.encodeForHTML(s_req_number)%></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="right"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></tsa:td>
			<tsa:td width="150px"><%=DocumentStatus.toString(s_req_status, oid)%></tsa:td>
		</tsa:tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tsa:tr>
			<tsa:td height="2px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>

<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td>
		<table id="upperInspectionCodes" border=0 cellpadding=0 cellspacing=0 align=center>
		<tsa:tr>
			<tsa:td align="left"><a href="javascript: browseStdInspections(); void(0)">Standard Inspection:</a></tsa:td>
			<tsa:td>&nbsp;&nbsp;&nbsp;&nbsp;Description:</tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td><input type="text" name="InspectionHeader_standardCode" value="<%=std_code%>" size="20" maxlength="15" onchange="upperCase(this);loadStdInspections(this);void(0);">&nbsp;&nbsp;&nbsp;&nbsp;</tsa:td>
			<tsa:td><input type="text" name="standardCodeDescription" value="<%=stdDescription%>" size="75" maxlength="255"/></tsa:td>
			<tsa:td width="25px">&nbsp;</tsa:td>
			<tsa:td noWrap="nowrap" align="right"></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="left">CGD No:</tsa:td>
			<tsa:td>&nbsp;&nbsp;&nbsp;&nbsp;CGD Rev:</tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td><input type="text" name="InspectionHeader_cgdNo" value="<%=cgdNo%>" size="20" maxlength="20">&nbsp;&nbsp;&nbsp;&nbsp;</tsa:td>
			<tsa:td><input type="text" name="InspectionHeader_cgdRev" value="<%=cgdRev%>" size="20" maxlength="5"/></tsa:td>
			<tsa:td width="25px">&nbsp;</tsa:td>
			<tsa:td noWrap="nowrap" align="right"></tsa:td>
		</tsa:tr>

		</table>

		<br>

		<hr width="580px" align="center" color="#9999CC">

<%
	if (inspectionCodeList != null) {
	%>

		<table border="0" cellpadding="0" cellspacing="0" align="left">
		<tr>
			<td>
				<table id="inspectHdr" border=0 cellpadding=2 cellspacing=2 align=left width=80%>
						<tsa:tr>
							<tsa:td   width="5%">&nbsp;</tsa:td>
							<tsa:td field="ins-inspCode"  width="20%" noWrap="nowrap" align="left"><tsa:label labelName="ins-code" defaultString="Inspection Code"/></a></tsa:td>
							<tsa:td field="ins-codeDescription" width="55%" noWrap="left" align="left">&nbsp;&nbsp;&nbsp;<tsa:label labelName="ins-codeDescription" defaultString="Code Description"/></tsa:td>
							<tsa:td width="20%">&nbsp;</tsa:td>
						</tsa:tr>
				</table>
				</td>
				</tr>
				<tr>
				<td>
				<table id="inspectCodes" border=0 cellpadding=2 cellspacing=2 align=center width=80%>
		<%	int inspCount = 0;
				for (i = 0; i < inspectionCodeList.size() ; i++ ) {
					InspectionLine inspectionLine = (InspectionLine) inspMap.get("code" + (i + 1)) ;
					if (inspectionLine == null) continue ;
					String s_set_row = "";
					s_set_row = "ONFOCUS='setInspectRow(" + inspCount + ");'";
		%>
						<tsa:tr>
							<tsa:td width="5%">&nbsp;</tsa:td>
							<tsa:td align="left" width="20%"><input type="text" name="InspectionLine_inspectCode" value="<%=inspectionLine.getInspectCode()%>" size="20" maxlength="15" onchange="upperCase(this);" readonly>&nbsp;</tsa:td>
							<tsa:td align="left" width="55%"><input type="text" name="InspectionCode_description" value="<%=inspectionLine.getInspCodeDesc()%>" size="70" maxlength="255" readonly/></tsa:td>

<%
		String  stdCode = inspectionLine.getStandardCode() ;
		if (! disableEdit && HiltonUtility.isEmpty(stdCode)) { %>
							<tsa:td width="20%"><a href="javascript: changeCriteria('<%=inspectionLine.getInspectCode()%>'); void(0);"><font class="reset">Change Criteria</font></a></tsa:td>
<% } else { %>
							<tsa:td width="20%">&nbsp;</tsa:td>
<% } %>
						</tsa:tr>
						<tsa:tr>
							<td colspan="4">
								<div  id="details<%=i%>" style="visibility:visible;display:block;" class=summary>
								<table id="inspectionCrit" border="0" cellpadding="2" cellspacing="2" align="right" width="100%">
								<tr>
								<td>
									<table id="criteriaHdr" border="0" cellpadding="2" cellspacing="2" align="left" width="100%">
									<tsa:tr>
										<tsa:td width="15%">&nbsp;</tsa:td>
										<tsa:td id="insp-no" width="10%" align="left"><tsa:label labelName="insp-no" defaultString="Insp No."/></tsa:td>
										<tsa:td align="left" width="10%"><tsa:label labelName="ins-critNo" defaultString="Criteria No."/></tsa:td>
										<tsa:td align="left" width="65%"><tsa:label labelName ="ins-critDescription" defaultString="Criteria Description"/></tsa:td>
									</tsa:tr>
									</table>
								</td>
								</tr>
								<tr>
								<td>
									<table id="criteriaCodes<%=i%>" border="0" cellpadding="2" cellspacing="2" align="left" width="100%">
								<%
										int sequenceNo = 0;
										int critCount = 0;
										for (int ix = 1; ix < 100; ix++) {
											InspectionLine critLine = (InspectionLine) inspMap.get("crit" + Integer.toString(i + 1) + Integer.toString(ix));
											if (critLine == null) break ;
											s_set_row = "ONFOCUS='setCriteriaRow(" + critCount + ");'";
											sequenceNo++ ;
								%>
												<tr>
													<tsa:td width="15%">&nbsp; <input type="hidden" name="inspectionCode<%=i%><%=ix%>" value="<%=inspectionLine.getInspectCode() %>"></tsa:td>
													<tsa:td id="insp_num_<%=i%><%=ix%>" valign="top" align="left" width="10%"><%=sequenceNo%><input type="hidden" name="InspectionLine_seqNo" value="<%=sequenceNo%>"></tsa:td>

													<tsa:td  id="critNum<%=i%><%=ix%>" valign="top" width="10%"><input type="text" name="InspectionLine_critNo<%=i%><%=ix%>" value="<%=critLine.getCritNo() %>" size="4" maxLength="2" readonly></tsa:td>
													<tsa:td id="critDesc<%=i%><%=ix%>" width="65%"><TEXTAREA NAME="critDesc<%=i%><%=ix%>" COLS=70 ROWS=5 readonly="readonly"><%=critLine.getCritDescription()%></TEXTAREA></tsa:td>
												</tr>
									<% critCount++ ;
									}	%>
									</table>
									<tsa:hidden name="crit_maxrows" value="<%=critCount%>"/>
								</td>
								</tr>
								</table>
								</div>
							</td>
						</tsa:tr>
					<% inspCount++ ;
					} %>
				</table>

			</td>
		</tr>
		<tr>
		<% if (! disableEdit) { %>
			<tsa:td align="center"><a href="javascript: changeCriteria('(new)'); void(0);"><font class="reset"><b>Add New Inspection Code</b></font></a></tsa:td>
			<% } %>
		</tr>
		<tsa:tr>
			<tsa:td><hr width="580px" align="center" color="#9999CC"></hr></tsa:td>
		</tsa:tr>
		</table>
<%
	}
%>

		<br>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tsa:tr>
<% if (! disableEdit) { %>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: submitThis()">Save</a></div></tsa:td>
<% } %>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: submitCancel()">Cancel</a></div></tsa:td>
</tsa:tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script
	frm=document.purchaseform;
//	var critMaxRows = frm.crit_maxrows.value;
//	var critTable = document.getElementById("criteriaCodes");
//	var critTotalRows = critTable.rows.length;
//	var critRows = frm.crit_rows.value;

//	if (critTotalRows <= 0)
//	{
//		critTotalRows = 0;
//		addNewCriteriaRow(1);
//	}
//	setCriteriaRow(0);

	var inspMaxRows = frm.insp_maxrows.value;
	var inspTable = document.getElementById("inspectCodes");
	var inspTotalRows = inspTable.rows.length;
	var inspRows = frm.insp_rows.value;

	if (inspTotalRows <= 0)
	{
		inspTotalRows = 0;
//		addNewInspectRow();
	}
	// setInspectRow(0);


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
		if (disableEdit) { %>
			checkInputSettings();
//			allowInputEdit(frm.InspectionHeader_inspectType, true);
		<% }
		%>
	}

	function browse(x)
	{
		frm.browseName.value = x;

		doSubmit('/browse/browse_sys_tables.jsp', 'BrowseRetrieve');
	}

	function changeCriteria(code) {

		frm.inspectCode.value = code ;
		doSubmit('/requests/req_inspection_crit_detail.jsp','InspectionRetrieveCritDetail');
	}

	function addNewInspectRow()
	{
		//frm.deleteall.value = "FALSE";
		myTable = document.getElementById("inspectCodes");
		inspTotalRows = inspTotalRows+1;
		count = myTable.rows.length;
		myRow = myTable.insertRow(count);
//		maxRows++;

		myCell = myRow.insertCell();
		id = "inspcell1" ;
		myCell.id = id;
		myCell.width="5%";
		myCell.innerHTML = "&nbsp;" ;

		myCell = myRow.insertCell();
		id = "inspCode";
		myCell.id = id;
		myCell.width="20%";
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"InspectionCode_inspectCode\" SIZE=\"20\" MAXLENGTH=\"15\" value=\"\"  ONFOCUS='setInspectRow(" + (count - 1) + ");'>";

		myCell = myRow.insertCell();
		id = "inspDesc";
		myCell.id = id;
		myCell.width="55%";
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"InspectionCode_description\" SIZE=\"70\" MAXLENGTH=\"255\" value=\"\"  ONFOCUS='setCriteriaRow(" + (count - 1) + ");'>";

		myCell = myRow.insertCell();
		id = "inspcell4" + (count + 1);
		myCell.id = id;
		myCell.width="20%" ;
		myCell.innerHTML = "&nbsp;" ;

		setInspectRow(count);
	}

	function setInspectRow(row) {
		inspectRow = row ;
	}

	function deleteInspectRow( row)
	{
//		maxRows = maxRows - 1;	//needs to be set so that when validateForm is called it has the appropriate row count

		myTable = document.getElementById(tableId);
		currentRows = myTable.rows.length - 1;

		if (currentRows == 0)
		{
			return;
		}
		else if (currentRows > 1)
		{
			for (var i = row; i < (currentRows - 1); i++)
			{
				var fld1 = frm.InspectionLine_critNo[i + 1].value;
				var fld2 = frm.critDescription[i + 1].value;


				frm.InspectionLine_critNo[i].value = fld1;
				frm.critDescription[i].value = fld2;
			}
		}

		myTable = document.getElementById(tableId);
		myTable.deleteRow(currentRows);
/*
		if (currentRows <= 1)
		{
			frm.deleteall.value = "TRUE";
		}
*/
		//to fix totals percent
	}

	function browseStdInspections(frmField){
		var inspectType = frm.InspectionStd_inspectType.value ;

		popupParameters = "colname=InspectionStd_inspectType;operator==;filter_txt=" + inspectType + ";logicalOperator=AND;originalFilter=N;sort=N;"
		browseLookup('InspectionHeader_standardCode', 'inspectionstd-msr');
	}

	function loadStdInspections(frmField)
	{
		frm.newStandardCode.value = "true";
		doSubmit('/requests/req_inspection_crit.jsp','InspectionStdLoad;InspectionRetrieveDetail');
	}

	function viewInpsectionCodes()
	{
	}

	function submitThis()
	{
		doSubmit('/requests/inspection_detail.jsp', 'InspectionUpdateDetail;InspectionRetrieveDetail');
	}

	function submitCancel()
	{
		var stdCode = frm.InspectionStd_standardCode.value;
		var oldStdCode = frm.old_InspectionStd_standardCode.value;
		if(stdCode != oldStdCode){
			frm.InspectionHeader_standardCode.value = oldStdCode;
			doSubmit('/requests/inspection_detail.jsp','InspectionStdLoad;InspectionRetrieveDetail');
		} else {
			doSubmit('/requests/inspection_detail.jsp','InspectionRetrieveDetail');
		}
	}

	function validateForm()
	{

		return true;
	}

	function setDeleteAll()
	{
		frm.deleteall.value = "TRUE";
	}

// End Hide script -->
</SCRIPT>