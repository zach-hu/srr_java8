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

String s_inspectCode = (String) request.getAttribute("inspectCode") ;
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
	boolean disableEdit = false;

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

<tsa:hidden name="as_rows" value="<%=inspectionLineList.size()%>"/>
<tsa:hidden name="as_maxrows" value="0"/>
<tsa:hidden name="crit_rows" value="<%=inspectionLineList.size()%>"/>
<tsa:hidden name="insp_rows" value="<%=inspectionCodeList.size()%>"/>
<tsa:hidden name="InspectionStd_standardCode" value="<%=inspectionHeader.getStandardCode()%>"></tsa:hidden>
<tsa:hidden name="InspectionStd_inspectType" value="<%=inspectionHeader.getInspectType()%>"></tsa:hidden>
<tsa:hidden name="inspectCode" value="<%=s_inspectCode%>"></tsa:hidden>

<tsa:hidden name="InspectionHeader_icInspNo" value="<%=s_icInspNo%>"/>
<tsa:hidden name="InspectionLine_icInspNo" value="<%=icInspNo%>"/>
<tsa:hidden name="InspectionHeader_icMsrLine" value="<%=s_icMsrLine%>"/>
<tsa:hidden name="InspectionHeader_status" value="<%=s_inspectionStatus%>"/>

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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Inspection Criteria Detail</div>
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
	<tsa:td width="30%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('/receipts/rec_inspection_crit.jsp','RecInspectionRetrieveDetail'); void(0);">Cancel</a></div></tsa:td>
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
		doSubmit('/receipts/rec_inspection_crit.jsp', 'InspectionUpdateCritDetail;RecInspectionRetrieveDetail');
	}

	function deleteThis()
	{
		frm.InspectionLine_inspectCode.value = "" ;
		doSubmit('/receipts/rec_inspection_crit.jsp', 'InspectionUpdateCritDetail;RecInspectionRetrieveDetail');
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