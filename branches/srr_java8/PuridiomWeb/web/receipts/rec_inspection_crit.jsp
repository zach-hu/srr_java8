<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>

<%

int i;
PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

ReceiptHeader  receiptHeader = (ReceiptHeader) request.getAttribute("receiptHeader") ;
if (receiptHeader ==null) receiptHeader = new ReceiptHeader() ;

ReceiptLine receiptLine = (ReceiptLine) request.getAttribute("receiptLine") ;
if (receiptLine ==null) receiptLine = new ReceiptLine() ;

BigDecimal	bd_zero = new BigDecimal(0);
BigDecimal bd_line_number = receiptLine.getReceiptLine() ;


String	s_ic_rec_header = (String)request.getAttribute("ReceiptHeader_icRecHeader");
String	s_rec_number = (String)request.getAttribute("ReceiptHeader_receiptNumber");
String	s_rec_type = (String)request.getAttribute("ReceiptHeader_receiptType");
String	s_rec_status = (String)request.getAttribute("ReceiptHeader_receiptStatus");
String	s_rec_fiscal_year = (String)request.getAttribute("ReceiptHeader_fiscalYear");
String s_inspectCode = (String) request.getAttribute("inspectCode") ;


if (HiltonUtility.isEmpty(s_rec_status)) {
	s_rec_status = DocumentStatus.RCV_INPROGRESS;
}

String	s_doc_ic_header = (String) request.getAttribute("DocAttachment_icHeader");
String	s_ic_po_header = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_icPoHeader"));
String	s_ic_req_header = HiltonUtility.ckNull((String)request.getAttribute("RequisitionHeader_icReqHeader"));
String	s_req_number = HiltonUtility.ckNull((String)request.getAttribute("RequisitionHeader_requisitionNumber"));
String	s_po_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_poNumber"));
String	s_revision_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_revisionNumber"));
String	s_release_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_releaseNumber"));

if (HiltonUtility.isEmpty(s_revision_number)) {
	s_revision_number = "0";
}
if (HiltonUtility.isEmpty(s_release_number)) {
	s_release_number = "0";
}
BigDecimal	bd_revision_number = new BigDecimal(s_revision_number);
BigDecimal	bd_release_number = new BigDecimal(s_release_number);
PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
if (poHeader == null) {
	poHeader = new PoHeader();
}

String	receiptMethod = HiltonUtility.ckNull((String)request.getAttribute("receiptMethod"));

	String	s_line_count = (String) request.getAttribute("lineCount");
	String	return_page = "";

	boolean editMode = false;

	String	s_icInspNo = (String) request.getAttribute("InspectionHeader_icInspNo");
	String	s_icMsrLine = (String) request.getAttribute("InspectionHeader_icMsrLine");
	String	s_inspectionStatus = (String) request.getAttribute("InspectionHeader_status");


	String	icHeaderEdit = "";
	if(request.getAttribute("icHeaderEdit")!=null){icHeaderEdit = (String)request.getAttribute("icHeaderEdit");}
	String	icHeaderHistoryEdit = "";
	if(request.getAttribute("icHeaderHistoryEdit")!=null){icHeaderHistoryEdit = (String)request.getAttribute("icHeaderHistoryEdit");}

	String editAttachmentsAccess = propertiesManager.getProperty("EDITATTACHMENTS","ENABLED","N");
	String	displayPrintOption = propertiesManager.getProperty("ATTACHMENTS", "DisplayPrintOption", "Y");

	boolean editAttachmentsAccessBool = false;

	if (editAttachmentsAccess.equalsIgnoreCase("Y")) {
		editAttachmentsAccessBool = true;
	}
	if (s_req_number == null) {
		s_rec_number = (String) request.getAttribute("formNumber");
	}
	if (s_ic_rec_header == null) {
		s_ic_rec_header = (String) request.getAttribute("DocAttachment_icHeader");
	}
	if (s_rec_status == null) {
		s_rec_status = (String) request.getAttribute("formStatus");
	}

//	if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0 || s_req_status.compareTo(DocumentStatus.RFQ_PRICED) == 0 || s_req_status.compareTo(DocumentStatus.TEMPLATE) == 0) {
//		editMode = true;
//	}

	return_page = "/requests/insspection_detail.jsp";

	String	s_current_process = "HEADER_NOTES";
	String	s_current_page = "/requests/ins_notes.jsp";
	String	s_current_method = "DocCommentUpdate";
	String	s_current_process_method = "";

	InspectionHeader inspectionHeader = (InspectionHeader) request.getAttribute("inspectionHeader");
	BigDecimal icInspNo = new BigDecimal("-1");
	String std_code = "";
	String stdDescription = "" ;

	if (inspectionHeader != null)
	{
		icInspNo = inspectionHeader.getComp_id().getIcInspNo();
		if (inspectionHeader.getStandardCode() != null)
		{
			std_code = inspectionHeader.getStandardCode();
		}
	}

	List inspectionLineList = (List) request.getAttribute("inspectionLineList");
	if (inspectionLineList == null) inspectionLineList = new java.util.ArrayList() ;

	List inspectionCodeList = (List) request.getAttribute("inspectionCodeList") ;
	if (inspectionCodeList == null) inspectionCodeList = new ArrayList() ;

	List inspectionStdList = (List) request.getAttribute("inspectionStdList") ;
	if (inspectionStdList == null) inspectionStdList = new ArrayList() ;

	HashMap inspMap = (HashMap) request.getAttribute("inspectionMap") ;
	boolean disableEdit = true;

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
<input type=hidden name="RequisitionHeader_icReqHeader" value="<%=s_ic_req_header%>">
<input type=hidden name="RequisitionLine_icReqHeader" value="<%=s_ic_req_header%>">
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_req_number%>"/>

<input type=hidden name="ReceiptLine_icRecLine" value="<%=request.getAttribute("ReceiptLine_icRecLine")%>">
<input type=hidden name="ReceiptHeader_shipToInv" value="<%=request.getAttribute("ReceiptHeader_shipToInv")%>">
<input type=hidden name="InvBinLocation_tempIc" value="<%=request.getAttribute("InvBinLocation_tempIc")%>">
<input type=hidden name="ReceiptHeader_icPoHeader" value="<%=request.getAttribute("ReceiptHeader_icPoHeader")%>">
<input type=hidden name="ReceiptLine_receiptLine" value="<%=request.getAttribute("ReceiptLine_receiptLine") %>">
<tsa:hidden name="DocComment_icHeader" value="<%=s_icInspNo%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_icInspNo%>"/>
<input type=hidden name="lineCount" value="<%=s_line_count%>">
<input type=hidden name="lineToRetrieve" value="">
<input type=hidden name="InvProperty_flag" value="I">

<input type=hidden name="Account_icHeader" value="<%=s_ic_rec_header%>">
<input type=hidden name="Account_icLine" value="0">
<input type=hidden name="Account_accountType" value="INH">

<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="Default_referenceType" value="INH"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_doc_ic_header%>"/>
<tsa:hidden name="hasDocs" value="NO"/>
<tsa:hidden name="DocAttachment_delete" value="FALSE"/>
<tsa:hidden name="DocAttachment_edit_docIc" value="FALSE"/>
<tsa:hidden name="DocAttachment_edit_docTitle" value="FALSE"/>
<tsa:hidden name="filename" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="formNumber" value="<%=s_rec_number%>"/>
<tsa:hidden name="formStatus" value="<%=s_rec_status%>"/>
<tsa:hidden name="icHeaderEdit" value="<%=icHeaderEdit%>"/>
<tsa:hidden name="icHeaderHistoryEdit" value="<%=icHeaderHistoryEdit%>"/>
<tsa:hidden name="formtype" value="INS"/>
<tsa:hidden name="frompage" value="<%=(String) request.getAttribute(\"frompage\")%>"/>

<tsa:hidden name="crit_rows" value="<%=inspectionLineList.size()%>"/>
<tsa:hidden name="insp_rows" value="<%=inspectionCodeList.size()%>"/>
<tsa:hidden name="InspectionStd_standardCode" value="<%=inspectionHeader.getStandardCode()%>"></tsa:hidden>
<tsa:hidden name="InspectionStd_inspectType" value="<%=inspectionHeader.getInspectType()%>"></tsa:hidden>
<tsa:hidden name="inspectCode" value="<%=s_inspectCode%>"></tsa:hidden>

<tsa:hidden name="InspectionHeader_icInspNo" value="<%=s_icInspNo%>"/>
<tsa:hidden name="InspectionLine_icInspNo" value="<%=icInspNo%>"/>
<tsa:hidden name="InspectionHeader_icMsrLine" value="<%=s_icMsrLine%>"/>
<tsa:hidden name="InspectionHeader_status" value="<%=s_inspectionStatus%>"/>

<%
	if (HiltonUtility.isEmpty(s_rec_number))
	{
		s_rec_number = "N/A";
	}
%>


<tsa:hidden name="insp_maxrows" value="0"/>
<tsa:hidden name="crit_maxrows" value="0"/>
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Inspection Criteria</div>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" width="3px"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td>
		<%	if (!HiltonUtility.isEmpty(s_po_number)) { %>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Order #:</b>&nbsp;<%=s_po_number%>
		<%	} else if (!HiltonUtility.isEmpty(s_req_number)) { %>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Transfer Request #:</b>&nbsp;<%=headerEncoder.encodeForHTML(s_req_number)%>
		<%  } %>
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
</tsa:tr>
</table>

<div style="display:none;">
<%@ include file="/receipts/rec_inspection_info.jsp" %>
</div>
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

<% if (! disableEdit) { %>
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
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('/receipts/rec_inspection_detail.jsp','RecInspectionRetrieveDetail'); void(0);">Cancel</a></div></tsa:td>
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
		doSubmit('/receipts/rec_inspection_crit_detail.jsp','RecInspectionRetrieveCritDetail');
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
		browseLookup('InspectionHeader_standardCode', 'inspectionstd');
	}


	function loadStdInspections(frmField)
	{

		doSubmit('/receipts/rec_inspection_crit.jsp','InspectionStdLoad;RecInspectionRetrieveDetail');
	}


	function viewInpsectionCodes()
	{
	}

	function submitThis()
	{
		doSubmit('/receipts/rec_inspection_detail.jsp', 'RecInspectionRetrieveDetail');
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