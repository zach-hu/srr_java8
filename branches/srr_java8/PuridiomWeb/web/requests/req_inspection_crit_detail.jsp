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
String s_inspectCode = (String) request.getAttribute("inspectCode") ;

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
RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
String s_udf_1_code = requisitionHeader.getUdf1Code();
String s_udf_14_code = requisitionHeader.getUdf14Code();

InspectionHeader inspectionHeader = (InspectionHeader) request.getAttribute("inspectionHeader");
BigDecimal icInspNo = new BigDecimal("-1");
String std_code = "";
String stdDescription = "" ;

if (inspectionHeader != null)
{
	icInspNo = inspectionHeader.getComp_id().getIcInspNo();
}

List inspectionLineList = (List) request.getAttribute("inspectionLineList");
if (inspectionLineList == null) {
	inspectionLineList = new java.util.ArrayList() ;
	InspectionLine il = new InspectionLine() ;
	inspectionLineList.add(il) ;
}


List inspectionStdList = (List) request.getAttribute("inspectionStdList") ;
if (inspectionStdList == null) inspectionStdList = new ArrayList() ;

if (inspectionStdList.size() > 0) {
	InspectionStd inspStd = (InspectionStd) inspectionStdList.get(0) ;
	stdDescription = inspStd.getDescription() ;
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
<tsa:hidden name="allow" value="true"/>
<tsa:hidden name="frompage" value="<%=s_from_page%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
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
<tsa:hidden name="as_rows" value="<%=inspectionLineList.size()%>"/>
<tsa:hidden name="as_maxrows" value="0"/>
<tsa:hidden name="inspectCode" value="<%=s_inspectCode%>"></tsa:hidden>

<br>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tsa:tr>
			<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="req-type" defaultString="Req Type"/>&nbsp;Inspection Criteria Detail</div>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" width="3px"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<tsa:td valign="bottom" height="30px" width="*">
		<table cellpadding="1" cellspacing="0" border=0 width=100%>
		<tsa:tr>
			<tsa:td align="right"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "msrNumber", "MSR #")%>:</b></tsa:td>
			<tsa:td width="125px"><%=headerEncoder.encodeForHTML(s_req_number)%></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="right"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></tsa:td>
			<tsa:td width="125px"><%=DocumentStatus.toString(s_req_status, oid)%></tsa:td>
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

<%
	String inCode = "" ;
	String inCodeDesc = "" ;
	if (inspectionLineList != null && inspectionLineList.size() > 0) {
		InspectionLine inspLine = (InspectionLine) inspectionLineList.get(0) ;

		inCode = inspLine.getInspectCode() ;
		inCodeDesc = inspLine.getInspCodeDesc() ;
	}
	%>
		<table border="0" cellpadding="0" cellspacing="0" align="left">
		<tr>
			<tsa:td>
		<table id="outerInspectionCodes" border=0 cellpadding=2 cellspacing=2 align=center>
				<tsa:tr>
					<tsa:td>&nbsp;</tsa:td>
					<tsa:td></tsa:td>
					<tsa:td field="ins-inspCode"  noWrap="nowrap" align="center"><a href="javascript: browseLookup('InspectionLine_inspectCode','inspectionCode'); void(0);"><tsa:label labelName="ins-code" defaultString="Inspection Code"/></a>:</tsa:td>
					<tsa:td field="ins-codeDescription" noWrap="nowrap" align="left">&nbsp;&nbsp;&nbsp;<tsa:label labelName="ins-codeDescription" defaultString="Code Description"/>:</tsa:td>
					<tsa:td>&nbsp;</tsa:td>
				</tsa:tr>
				<tsa:tr>
					<tsa:td></tsa:td>
					<tsa:td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</tsa:td>
					<tsa:td align="center"><input type="text" name="InspectionLine_inspectCode" value="<%=inCode%>" size="20" maxlength="15" onchange="upperCase(this);">&nbsp;&nbsp;&nbsp;&nbsp;</tsa:td>
					<tsa:td align="center"><input type="text" name="InspectionCode_description" value="<%=inCodeDesc%>" size="75" maxlength="255"/></tsa:td>
					<tsa:td width="25px">&nbsp;</tsa:td>
					<tsa:td noWrap="nowrap" align="right"></tsa:td>
				</tsa:tr>
		</table>
	<table id="inspection" border="0" cellpadding="2" cellspacing="2" align="left" width="100%">
	<tsa:tr>
		<tsa:td width="15%">&nbsp;</tsa:td>
		<tsa:td id="insp-no" width="10%" align="left"><tsa:label labelName="insp-no" defaultString="Insp No."/></tsa:td>
		<tsa:td align="left" width="10%"><a href="javascript: browseCriteriaNo('InspectionLine_critNo', frm.InspectionLine_inspectCode.value); void(0);"><tsa:label labelName="ins-critNo" defaultString="Criteria No."/></a></tsa:td>
		<tsa:td align="left" width="55%"><tsa:label labelName ="ins-critDescription" defaultString="Criteria Description"/></tsa:td>
		<tsa:td width="10%">&nbsp;</tsa:td>

	</tsa:tr>
	</table>
	</tsa:td>
<tsa:tr>
			<tsa:td>
	<table id="inspectionCodes" border="0" cellpadding="2" cellspacing="2" align="left" width="100%">
<%
	String s_set_row = "";
	int i_rowcount = 0;
	int sequenceNo = 0;

	if (inspectionLineList != null){

	for (i = 0; i < inspectionLineList.size(); i++)
	{
		s_set_row = "ONFOCUS='setCurrentRow(" + i_rowcount + ");'";
		InspectionLine inspectionLine = (InspectionLine) inspectionLineList.get(i);
		sequenceNo++ ;

%>
				<tr>
					<tsa:td width="15%">&nbsp; <input type="hidden" name="inspectionCode<%=i%>" value="<%=inspectionLine.getInspectCode() %>"></tsa:td>
					<tsa:td id="insp_num_<%=i%>" valign="top" align="left" width="10%"><%=sequenceNo%>
					<input type="hidden" name="InspectionLine_seqNo" value="<%=sequenceNo%>"></tsa:td>

					<tsa:td  id="critNum" valign="top" width="10%"><input type="text" name="InspectionLine_critNo" value="<%=inspectionLine.getCritNo() %>" size="4" maxLength="2"  <%=s_set_row%>></tsa:td>
					<tsa:td id="critDesc" width="55%"><TEXTAREA NAME="InspectionLine_critDescription" COLS=70 ROWS=5 <%=s_set_row%> ><%=inspectionLine.getCritDescription()%></TEXTAREA></tsa:td>
					<tsa:td id="insp_del_<%=i%>" width="10%"><a href="javascript: if (confirm('Are you sure you wish to delete this Criteria?')) { deleteMe(<%=i%>); } void(0);" tabindex="999"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></tsa:td>
				</tr>
<%		i_rowcount++;
		}	//end for loop
	}
%>
				</table>

				<br>
				<br>
				<br>
				<tsa:hidden name="as_maxrows" value="<%=i_rowcount%>"/>
			</tsa:td>
		</tsa:tr>
		</table>

		<br>
		<tsa:tr>
			<tsa:td align="center"><a href="javascript: addNew(); void(0);"><font class="reset"><b>Add New Criteria</b></font></a></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td><hr width="580px" align="center" color="#9999CC"></hr></tsa:td>
		</tsa:tr>
		<br>

</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tsa:tr>
	<tsa:td width="30%" align="center"><div id="pxbutton"><a href="javascript: submitThis()">Save</a></div></tsa:td>
	<tsa:td width="30%" align="center"><div id="pxbutton"><a href="javascript: deleteThis()">Delete</a></div></tsa:td>
	<tsa:td width="30%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('/requests/req_inspection_crit.jsp','InspectionRetrieveDetail'); void(0);">Cancel</a></div></tsa:td>
</tsa:tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script
	frm=document.purchaseform;
	var maxRows = frm.as_maxrows.value;
	var myTable = document.getElementById("inspectionCodes");
	var TotalRows = myTable.rows.length;
	var maxRows = frm.as_maxrows.value;
	var accRows = frm.as_rows.value;


	if (TotalRows <= 0)
	{
		TotalRows = 0;
		addNew();
	}
	setCurrentRow(0);



	function browse(x)
	{
		frm.browseName.value = x;

		doSubmit('/browse/browse_sys_tables.jsp', 'BrowseRetrieve');
	}

	function browseCriteriaNo(frmField,inspectionCode){
		popupParameters = "colname=InspectionCrit_id_inspectCode;operator==;filter_txt=" + inspectionCode + ";logicalOperator=AND;originalFilter=N;sort=N;"
		browseLookup(frmField, 'inspectioncrit-no');
	}

	function addNew()
	{
		//frm.deleteall.value = "FALSE";
		myTable = document.getElementById("inspectionCodes");
		TotalRows = TotalRows+1;
		count = myTable.rows.length;

		myRow = myTable.insertRow(count);
		maxRows++;
		count++ ;

		myCell = myRow.insertCell();
		id = "spacer1";
		myCell.id = id;
		myCell.valign = "top";
		myCell.align = "left";
		myCell.width = "15%" ;
		myCell.innerHTML = "&nbsp;";

		myCell = myRow.insertCell();
		id = "insp_num_" + (count - 1);
		myCell.id = id;
		myCell.valign = "top";
		myCell.align = "left";
		myCell.width = "10%" ;
		myCell.innerHTML = (count ) + "<INPUT TYPE=\"HIDDEN\" NAME=\"InspectionLine_seqNo\" value=" + (count) + ">";

		myCell = myRow.insertCell();
		id = "critNum";
		myCell.id = id;
		myCell.valign = "top";
		myCell.align = "left";
		myCell.width = "10%" ;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"InspectionLine_critNo\" SIZE=\"4\" MAXLENGTH=\"2\" value=\"\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";

		myCell = myRow.insertCell();
		id = "critDesc";
		myCell.id = id;
		myCell.valign = "top";
		myCell.align = "left";
		myCell.width = "55%" ;
		myCell.innerHTML = "<TEXTAREA NAME='InspectionLine_critDescription' COLS=70 ROWS=5 ONFOCUS='setCurrentRow(" + (count - 1) + ");' ></TEXTAREA>";

		myCell = myRow.insertCell();
		id = "insp_del_" + (count);
		myCell.valign = "top";
		myCell.id = id;
		myCell.width = "10%" ;
		myCell.innerHTML = "<A href=\"javascript: if (confirm('Are you sure you wish to delete this Criteria?')) { deleteMe(" + count + "); } void(0)\" TABINDEX=\"999\"><IMG SRC=\"<%=contextPath%>/images/delete.gif\" ALT=\"Delete\" BORDER=\"0\"></A>";

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

		myTable = document.getElementById("inspectionCodes");
		var currentRows = myTable.rows.length - 1;

		if (currentRows < 0)
		{
			return;
		} 	else 	{
			for (var i = row; i < (currentRows ); i++)
			{
				var fld1 = frm.InspectionLine_critNo[i + 1].value;
				var fld2 = frm.InspectionLine_critDescription[i + 1].value;

				frm.InspectionLine_critNo[i].value = fld1;
				frm.InspectionLine_critDescription[i].value = fld2;
			}
		}

		myTable = document.getElementById("inspectionCodes");
		myTable.deleteRow(currentRows );
	}

	function viewInpsectionCodes()
	{
	}

	function submitThis()
	{
		doSubmit('/requests/req_inspection_crit.jsp', 'InspectionUpdateCritDetail;InspectionRetrieveDetail');
	}

	function deleteThis()
	{
		frm.InspectionLine_inspectCode.value = "" ;
		doSubmit('/requests/req_inspection_crit.jsp', 'InspectionUpdateCritDetail;InspectionRetrieveDetail');
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