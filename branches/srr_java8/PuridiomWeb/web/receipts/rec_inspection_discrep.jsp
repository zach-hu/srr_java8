<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>

<%

PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

String	s_date_format = propertiesManager.getProperty("MISC", "DateFormat", "MM-dd-yyyy");
String	s_ic_rec_header = (String)request.getAttribute("ReceiptHeader_icRecHeader");
String	s_rec_number = (String)request.getAttribute("ReceiptHeader_receiptNumber");
String	s_rec_type = (String)request.getAttribute("ReceiptHeader_receiptType");
String	s_rec_status = (String)request.getAttribute("ReceiptHeader_receiptStatus");
String	s_rec_fiscal_year = (String)request.getAttribute("ReceiptHeader_fiscalYear");


if (HiltonUtility.isEmpty(s_rec_status)) {
	s_rec_status = DocumentStatus.RCV_INPROGRESS;
}

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

String	receiptMethod = HiltonUtility.ckNull((String)request.getAttribute("receiptMethod"));
String	s_current_process = "STEP1";
String	s_current_page = "/receipts/rec_items_to_step_1.jsp";
String	s_current_method = "DoNothing";
String	s_current_process_method = "";
String	duomUmCode = "" ;

BigDecimal	bd_zero = new BigDecimal(0);

boolean allowEdit = false;

PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
if (poHeader == null) 	poHeader = new PoHeader();

InspectionHeader inspectionHeader = (InspectionHeader) request.getAttribute("inspectionHeader") ;
if (inspectionHeader == null) inspectionHeader = new InspectionHeader() ;

ReceiptHeader	receiptHeader = (ReceiptHeader)request.getAttribute("receiptHeader");
if (receiptHeader == null) receiptHeader = new ReceiptHeader();

ReceiptLine	receiptLine = (ReceiptLine)request.getAttribute("receiptLine");
if (receiptLine == null) receiptLine = new ReceiptLine();

PoLine	poLine = (PoLine) request.getAttribute("poLine") ;
if (poLine == null) 	poLine = new PoLine();

RequisitionLine reqLine = receiptLine.getRequisitionLine() ;
if (reqLine == null) 	reqLine = new RequisitionLine() ;

if(uid.equals(receiptLine.getInspectorAssigned())){
	allowEdit = true;
}

String	s_return_page = HiltonUtility.ckNull((String)request.getAttribute("returnPage"));
if (HiltonUtility.isEmpty(s_return_page)) {
	s_return_page = "/receipts/rec_inspection_discrep.jsp";
}
String	s_return_method = HiltonUtility.ckNull((String)request.getAttribute("returnMethod"));
if (HiltonUtility.isEmpty(s_return_method)) {
	s_return_method = "'RecInspectionRetrieveDetail";
}
BigDecimal	bd_line_number = HiltonUtility.getBigDecimalFormatted(receiptLine.getReceiptLine(), 0);
if (bd_line_number.compareTo(new BigDecimal(0)) == 0)
	bd_line_number = new BigDecimal(1);

String	s_line_count = (String) request.getAttribute("lineCount");

BigDecimal receiptQty = receiptLine.getQtyStep0Accepted() ;
BigDecimal qtyReceived = receiptLine.getQtyStep0Accepted() ;
BigDecimal qtyRejected = receiptLine.getQtyStep1Rejected() ;
BigDecimal qtyAccepted = receiptLine.getQtyStep1Accepted() ;
// BigDecimal balanceQty = qtyReceived.subtract(qtyAccepted) ;

String	uom = poLine.getUmCode();
if (HiltonUtility.isEmpty(uom)) {
	uom = "EA";
}

String s_inspNo =(String)  request.getAttribute("InspectionDiscrep_icInspNo") ;
if (s_inspNo == null) s_inspNo = "0" ;

List inspectionDiscrepList = (List) request.getAttribute("inspectionDiscrepList") ;
if (inspectionDiscrepList == null) inspectionDiscrepList = new ArrayList() ;

List receiptDiscrepStatusList = (List) request.getAttribute("receiptDiscrepStatusList") ;
if (receiptDiscrepStatusList == null) receiptDiscrepStatusList = new ArrayList() ;

int i = 0;

String inspector = receiptLine.getInspectorAssigned() ;
String engineer = receiptLine.getEngineerAssigned() ;

boolean  disableEdit = (inspector.compareTo(uid) !=  0) ;

%>

<input type=hidden name="ReceiptHeader_icRecHeader" value="<%=s_ic_rec_header%>">
<input type=hidden name="ReceiptHeader_icHeaderHistory" value="<%=receiptHeader.getIcHeaderHistory()%>">
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

<input type=hidden name="Account_icHeader" value="<%=s_ic_rec_header%>">
<input type=hidden name="Account_icLine" value="0">
<input type=hidden name="Account_accountType" value="RCH">
<input type=hidden name="DocComment_icHeader" value="<%=s_ic_rec_header%>">
<input type=hidden name="DocComment_icLine" value="0">
<input type=hidden name="DocAttachment_icHeader" value="<%=s_ic_rec_header%>">
<input type=hidden name="receiptMethod" value="<%=receiptMethod%>">
<input type=hidden name="formType" value="REC">
<input type=hidden name="allowBrowse" value="true">

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

<input type="hidden" name="InspectionHeader_icInspNo" value="<%=s_inspNo%>">
<input type="hidden" name="InspectionHeader_icMsrLine" value="<%=receiptLine.getIcLineHistory()%>">
<input type="hidden" name="InspectionHeader_area" value="<%=inspectionHeader.getArea() %>">
<input type="hidden" name="InspectionHeader_location" value="<%=inspectionHeader.getLocation() %>">
<input type="hidden" name="InspectionHeader_storage" value="<%=inspectionHeader.getStorage() %>">

<input type="hidden" name="InspectionLine_icMsrLine" value="<%=receiptLine.getIcLineHistory()%>">
<input type="hidden" name="InspectionDiscrep_icRecHeader" value="<%=receiptLine.getIcRecHeader()%>">
<input type="hidden" name="InspectionDiscrep_icRecLine" value="<%=receiptLine.getIcRecLine()%>">

<input type="hidden" name="InspectionDiscrep_icMsrLine" value="<%=receiptLine.getIcRecLine()%>">
<input type="hidden" name="InspectionDiscrep_icInspNo" value="<%=s_inspNo%>">
<input type="hidden" name="InspectionDispos_icInspDiscrep" value="0">

<input type="hidden" name="inspectCode" value= "">
<input type="hidden" name="inspRequirements" value= "">
<input type="hidden" name="inspDescription" value= "">
<input type="hidden" name="inspStartDate" value= "">
<input type="hidden" name="inspStatus" value= "">

<input type="hidden" name="discrepQty" value= "0">

<tsa:hidden name="stdTable_tableType" value=""/>
<tsa:hidden name="formtype" value="INS"/>
<tsa:hidden name="allow" value="true"/>
<tsa:hidden name="lineUpdated" value="false"/>
<tsa:hidden name="lineToRetrieve" value=""/>
<tsa:hidden name="catalogId" value="<%=reqLine.getCatalogId()%>"/>
<tsa:hidden name="writehistory" value="N"/>
<tsa:hidden name="originalQuantity" value=""/>
<tsa:hidden name="originalPrice" value=""/>
<tsa:hidden name="duplicateItem" value="FALSE"/>
<tsa:hidden name="currentPage" value="item"/>
<tsa:hidden name="as_rows" value="<%=inspectionDiscrepList.size()%>"/>

<br>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Inspection Discrepancies</div>
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
</tr>
</table>

<div style="display:none;">
<%@ include file="/receipts/rec_inspection_info.jsp" %>
</div>
<br>

<table border=0 cellspacing=0 cellpadding=0 width=850px>
<tr>
	<td>

	<%
			BigDecimal qtyToInspect = HiltonUtility.ckNull(receiptLine.getQtyStep0Accepted());
			BigDecimal inspected = HiltonUtility.ckNull(receiptLine.getQtyStep1Accepted());
			BigDecimal balanceQty = qtyToInspect.subtract(inspected);
	%>
		<table border="0" cellpadding="0" cellspacing="0" align="center" width=880px>
		<tr>
			<tsa:td>
		<table id="lineInfo" border=0 cellpadding=2 cellspacing=2 align=center>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=6% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-lineNumber", "Line #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width=20% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-itemNumber", "Item/Part #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-uom")%> width=3% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-uom", "UOM")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityReceived")%> width=15% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityReceived", "Received")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityInspected")%> width=15% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityInspected", "Inspected")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-inqa")%> width=15% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-inqa", "In QA")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityRejected")%> width=15% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityRejected", "Qty Rejected")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityAccepted")%> width=15% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityAccepted", "Qty Accepted")%></td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=6% class=browseRow nowrap align=right><%=poLine.getLineNumber().setScale(1, BigDecimal.ROUND_HALF_UP)%>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width=20% class=browseRow nowrap><%=poLine.getItemNumber()%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-uom")%> width=3% class=browseRow nowrap align=right><%=poLine.getUmCode()%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityReceived", s_rec_type)%> width=15% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(qtyToInspect, oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityInspected")%> width=15% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(inspected, oid)%>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityInQA")%> width=15% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(balanceQty, oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityRejected")%> width=15% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyStep1Rejected(), oid)%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityAccepted")%> width=15% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(receiptLine.getQtyStep1Accepted(), oid)%></td>
				</tr>
				<tr>
					<td class=browseRow nowrap>&nbsp;</td>
					<td class=browseRow colspan="3" valign=top><%=poLine.getDescription()%></td>
				</tr>
		</table>
	</tsa:td>
</tr>
		<tsa:tr>
			<tsa:td><hr width="580px" align="center" color="#9999CC"></hr></tsa:td>
		</tsa:tr>
		<tr>
		<td>
				<table id="inspectionDiscrepHdr" border="0" cellpadding="2" cellspacing="2" align="left" width="100%" style='table-layout:fixed';>
				<tsa:tr>
					<tsa:td field="ins-inspectCode"  align="center" width="6%"><a href="javascript: viewInspectCodes(); void(0);"><tsa:label labelName="ins-inspectCode" defaultString="Insp  Code"/></a></tsa:td>
					<tsa:td field="ins-discrepRequirements"  align="left" width="25%"><tsa:label labelName="ins-discrepRequirements" defaultString="PO Requirements"/></tsa:td>
					<tsa:td field="ins-discrepDescription"  align="left" width="25%"><tsa:label labelName="ins-discrepDescription" defaultString="Discrepant Description"/></tsa:td>
					<tsa:td field="ins-discrepQuantity"  align="left" width="8%"><tsa:label labelName="ins-discrepQuantity" defaultString="Quantity"/></tsa:td>
					<tsa:td field="ins-discrepStatus"  align="left" width="11%"><tsa:label labelName="ins-discrepStatus" defaultString="Status"/></tsa:td>
					<tsa:td field="ins-discrepStartDate"  align="left" width="11%"><tsa:label labelName="ins-discrepStartDate" defaultString="Status Date"/></tsa:td>
					<tsa:td width="3%">&nbsp;</tsa:td>
				</tsa:tr>
				</table>
		</td>
		</tr>
		<tr>
			<tsa:td>
				<table id="inspectionDiscrepRows" border="0" cellpadding="2" cellspacing="2" align="left" width="100%" style='table-layout:fixed';>

<%
	String s_set_row = "";
	int i_rowcount = 0;
	int sequenceNo = 0;

	if (inspectionDiscrepList != null){

	for (i = 0; i < inspectionDiscrepList.size(); i++)
	{
		s_set_row = "ONFOCUS='setCurrentRow(" + i_rowcount + ");'";
		InspectionDiscrep inspectionDiscrep = (InspectionDiscrep) inspectionDiscrepList.get(i);
		sequenceNo++ ;
		BigDecimal icInspDiscrep = inspectionDiscrep.getIcInspDiscrep() ;
		if (icInspDiscrep == null) icInspDiscrep =   new BigDecimal(0) ;

%>
				<tr>
					<tsa:td  id="inspectCode" valign="top" width="9%"><input type="text" name="InspectionDiscrep_inspectCode" value="<%=inspectionDiscrep.getInspectCode() %>" size="5" maxLength="15"  <%=s_set_row%>></tsa:td>
					<tsa:td  id="inspRequirements" valign="top" width="25%"><input type="text" name="InspectionDiscrep_inspRequirements" value="<%=inspectionDiscrep.getInspRequirements() %>" size="25" maxLength="255"  <%=s_set_row%>></tsa:td>
					<tsa:td  id="inspDescription" valign="top" width="25%"><input type="text" name="InspectionDiscrep_inspDescription" value="<%=inspectionDiscrep.getInspDescription() %>" size="25" maxLength="255"  <%=s_set_row%>></tsa:td>
					<tsa:td  id="inspQuantity" valign="top" width="15%"><input type="text" name="InspectionDiscrep_inspQuantity" value="<%=HiltonUtility.getFormattedQuantity(inspectionDiscrep.getInspQuantity(), oid)%>" style="text-align:right"  size="10" maxLength="15"  <%=s_set_row%>></tsa:td>

					<%String discrepStat = inspectionDiscrep.getStatus();
					if (discrepStat == null) discrepStat = "" ;%>
					<tsa:td  id="status" valign="top" width="11%" align="left">
							<SELECT NAME="InspectionDiscrep_status" MAXLENGTH="10" <%=s_set_row%>>
							<OPTION value="<%=discrepStat %>" <%if (discrepStat.equalsIgnoreCase("")) {%>SELECTED<%}%>></VALUE>
<%
							for (int ip = 0; ip < receiptDiscrepStatusList.size(); ip++) {
								StdTable tbl = (StdTable) receiptDiscrepStatusList.get(ip) ;
								String stat = tbl.getTableKey() ;
								String statName = tbl.getDescription() ;
%>
							<OPTION value="<%=stat%>" <%if (discrepStat.equalsIgnoreCase(stat)) {%>SELECTED<%}%>><%=statName%></OPTION>
<%						}	%>
						</SELECT>
					 </tsa:td>

					<tsa:td  id="inspStartDate" valign="top" width="11%"><input type="text" name="InspectionDiscrep_inspStartDate" value="<%=HiltonUtility.getFormattedDate(inspectionDiscrep.getInspStartDate(),oid) %>" size="10" maxLength="10"  <%=s_set_row%>>
					<a href="javascript: getDateField(<%=i %>);"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left."  border="0"></a></tsa:td>
					<tsa:td id="disppo_<%=i%>" valign="top" align="left" width="3%">
					<a href="javascript: disposInspection(<%=i%>,<%=icInspDiscrep %>);"><img src="<%=contextPath%>/images/add.gif" alt="Click here to enter disposition for this discrepancy." valign="bottom" border="0"></a>
					<input type="hidden" name="InspectionDiscrep_icInspDiscrep" value="<%=icInspDiscrep%>">
					<input type="hidden" name="InspectionDiscrep_keySequence" value="<%=sequenceNo%>"></tsa:td>

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
		</tr>
		</table>

		<br>
		<tsa:tr>
			<% if (! disableEdit ) {   %>
				<tsa:td align="center"><a href="javascript: addNew(); void(0);"><font class="reset"><b>Add New Discrepancy</b></font></a></tsa:td>
			<% } %>
		</tsa:tr>
		<tsa:tr>
			<tsa:td><hr width="580px" align="center" color="#9999CC"></hr></tsa:td>
		</tsa:tr>
		<br>

</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=980px>
<tsa:tr>
	<% if (! disableEdit) { %>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: submitThis()">Save</a></div></tsa:td>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: cancelThis(); void(0);">Return</a></div></tsa:td>
	<% } else { %>
	<tsa:td width="100%" align="center"><div id="pxbutton"><a href="javascript: cancelThis(); void(0);">Return</a></div></tsa:td>
	<% } %>
</tsa:tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script
	frm=document.purchaseform;
	var maxRows = frm.as_maxrows.value;
	var myTable = document.getElementById("inspectionDiscrepRows");
	var TotalRows = myTable.rows.length;
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
		if ( disableEdit) { %>
		checkInputSettings();
		<% }
		%>
	}


	function browse(x)
	{
		frm.browseName.value = x;

		doSubmit('/browse/browse_sys_tables.jsp', 'BrowseRetrieve');
	}

	function addNew()
	{
		//frm.deleteall.value = "FALSE";
		myTable = document.getElementById("inspectionDiscrepRows");
		TotalRows = TotalRows+1;
		count = myTable.rows.length;

		myRow = myTable.insertRow(count);
		maxRows++;
		count++ ;


		myCell = myRow.insertCell();
		id = "inspectCode";
		myCell.id = id;
		myCell.valign = "top";
		myCell.align = "left";
		myCell.width = "5%" ;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"InspectionDiscrep_inspectCode\" SIZE=\"5\" MAXLENGTH=\"15\" value=\"\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";

		myCell = myRow.insertCell();
		id = "inspRequirements";
		myCell.id = id;
		myCell.valign = "top";
		myCell.align = "left";
		myCell.width = "25%" ;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"InspectionDiscrep_inspRequirements\" SIZE=\"40\" MAXLENGTH=\"255\" value=\"\"  ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";

		myCell = myRow.insertCell();
		id = "inspDescription";
		myCell.id = id;
		myCell.valign = "top";
		myCell.align = "left";
		myCell.width = "25%" ;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"InspectionDiscrep_inspDescription\" SIZE=\"40\" MAXLENGTH=\"255\" value=\"\"  ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";

		myCell = myRow.insertCell();
		id = "inspQuantity";
		myCell.id = id;
		myCell.valign = "top";
		myCell.align = "left";
		myCell.width = "8%" ;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"InspectionDiscrep_inspQuantity\" STYLE=\"text-align:right\" SIZE=\"10\" MAXLENGTH=\"15\" value=\"0\"  ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";

		htmlText = "<SELECT NAME=\"InspectionDiscrep_status\" MAXLENGTH=\"10\"  ONFOCUS='setCurrentRow(\" + (count - 1) + \");'>" ;
		htmlText = htmlText + "<OPTION value=\"DRAFT\"></OPTION>" ;
<%
				for (int ip = 0; ip < receiptDiscrepStatusList.size(); ip++) {
					StdTable tbl = (StdTable) receiptDiscrepStatusList.get(ip) ;
					String stat = tbl.getTableKey() ;
					String statName = tbl.getDescription() ;
%>
						htmlText = htmlText + "<OPTION value=\"<%=stat%>\" <%if (stat.equalsIgnoreCase("DRAFT")) {%>SELECTED<%}%>><%=statName%></OPTION>";
<%						}	%>
		htmlText = htmlText  + "	</SELECT>" ;

		myCell = myRow.insertCell();
		id = "inspStatus";
		myCell.id = id;
		myCell.valign = "top";
		myCell.align = "left";
		myCell.width = "11%" ;
		myCell.innerHTML = htmlText ;
		//"<INPUT TYPE=\"TEXT\" NAME=\"InspectionDiscrep_inspStatus\" SIZE=\"10\" MAXLENGTH=\"10\" value=\"\"  ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";

		myCell = myRow.insertCell();
		id = "inspStartDate";
		myCell.id = id;
		myCell.valign = "top";
		myCell.align = "left";
		myCell.width = "11%" ;
		htmlText = "<INPUT TYPE=\"TEXT\" NAME=\"InspectionDiscrep_inspStartDate\" SIZE=\"10\" MAXLENGTH=\"10\" value=\"<%=HiltonUtility.getFormattedDate(d_today,oid)%>\"  ONFOCUS='setCurrentRow(" + (count - 1) + ");'>" ;
		htmlText = htmlText + "<a href=\"javascript: getDateField(count - 1);\"><img src=\"<%=contextPath%>/images/calendar.gif\" alt=\"Click here to choose a date or enter a date in the box on the left.\" border=\"0\"></a>";
		myCell.innerHTML = htmlText;

		myCell = myRow.insertCell();
		id = "dispo_" + (count);
		myCell.valign = "top";
		myCell.id = id;
		myCell.width = "3%" ;
		myCell.innerHTML = "<input type=\"hidden\" name=\"InspectionDiscrep_icInspDiscrep\" value=\"0\">	";
//		myCell.innerHTML =   "&nbsp;" ;

//		myCell = myRow.insertCell();
//		id = "del_" + (count);
//		myCell.valign = "top";
//		myCell.id = id;
//		myCell.width = "3%" ;
//		myCell.innerHTML = "<input type=\"hidden\" name=\"InspectionDiscrep_icInspDiscrep\" value=\"0\">	<A href=\"javascript: if (confirm('Are you sure you wish to delete this Discrepancy?')) { deleteMe(" + count + "); } void(0)\" TABINDEX=\"999\"><IMG SRC=\"<%=contextPath%>/images/delete.gif\" ALT=\"Delete\" BORDER=\"0\"></A>";

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

		myTable = document.getElementById("inspectionDiscrepRows");
		var currentRows = myTable.rows.length - 1;

		if (currentRows < 0)
		{
			return;
		}
		else if (currentRows > 1)
		{
			for (var i = row; i < (currentRows - 1); i++)
			{
				var fld1 = frm.InspectionDiscrep_inspectCode[i + 1].value;
				var fld2 = frm.InspectionDiscrep_inspRequirements[i + 1].value;
				var fld3 = frm.InspectionDiscrep_inspDescription[i + 1].value;
				var fld4 = frm.InspectionDiscrep_inspQuantity[i + 1].value;
				var fld5 = frm.InspectionDiscrep_status[i + 1].value;
				var fld6 = frm.InspectionDiscrep_inspStartDate[i + 1].value;


				frm.InspectionDiscrep_inspectCode[i].value = fld1;
				frm.InspectionDiscrep_inspRequirements[i].value = fld2;
				frm.InspectionDiscrep_inspDescription[i].value = fld3;
				frm.InspectionDiscrep_inspQuantity[i].value = fld4;
				frm.InspectionDiscrep_status[i].value = fld5;
				frm.InspectionDiscrep_inspStartDate[i].value = fld6;

			}
		}

		myTable = document.getElementById("inspectionDiscrepRows");
		myTable.deleteRow(currentRows);
/*
		if (currentRows <= 1)
		{
			frm.deleteall.value = "TRUE";
		}
*/
		//to fix totals percent


	}

	function discrepValidate() {

		recqty = <%=receiptLine.getQtyStep0Accepted()%> ;
		qty = 0
		ok =  true ;
		var currentRows = myTable.rows.length ;


		if (currentRows > 0) {
			if (currentRows > 1) {
				for (var i = 0; i < currentRows; i++)
				{
					if (eval( frm.InspectionDiscrep_inspQuantity[i].value) > recqty)   {
						ok = false ;
						break ;
					}
				}
			} else {
				if ( eval( frm.InspectionDiscrep_inspQuantity.value) > recqty) {
					ok = false ;
				}
			}
			if (! ok) {
				alert("Quantity inspected is greater than the quantity in QA!")
			}
		}
		return ok ;
	}

	function disposInspection(row, ic)
	{
		frm.InspectionDispos_icInspDiscrep.value = ic ;
		var currentRows = myTable.rows.length ;
		if (currentRows > 1) {
			frm.discrepQty.value =  frm.InspectionDiscrep_inspQuantity[row].value;
			frm.inspectCode.value =  frm.InspectionDiscrep_inspectCode[row].value;
			frm.inspRequirements.value =  frm.InspectionDiscrep_inspRequirements[row].value;
			frm.inspDescription.value =  frm.InspectionDiscrep_inspDescription[row].value;
			frm.inspStartDate.value =  frm.InspectionDiscrep_inspStartDate[row].value;
			frm.inspStatus.value =  frm.InspectionDiscrep_status[row].value;
		} else {
			frm.discrepQty.value = frm.InspectionDiscrep_inspQuantity.value;
			frm.inspectCode.value =  frm.InspectionDiscrep_inspectCode.value;
			frm.inspRequirements.value =  frm.InspectionDiscrep_inspRequirements.value;
			frm.inspDescription.value =  frm.InspectionDiscrep_inspDescription.value;
			frm.inspStartDate.value =  frm.InspectionDiscrep_inspStartDate.value;
			frm.inspStatus.value =  frm.InspectionDiscrep_status.value;
		}
		if (discrepValidate()) {
			doSubmit('receipts/rec_inspection_dispos.jsp','InspectionDiscrepSave;InspectionDisposRetrieve');
		}
	}

	function viewInspectCodes()
	{
		popupParameters = "InspectionHeader_icMsrLine=" + frm.InspectionHeader_icMsrLine.value + ";";
		browseLookup("InspectionDiscrep_inspectCode", "discrep-inspectioncode");
	}

	function submitThis()
	{
		if (discrepValidate()) {
			doSubmit('/receipts/rec_inspection_discrep.jsp','InspectionDiscrepSave;InspectionDiscrepRetrieve') ;
		}
		// doSubmit('/receipts/rec_item_step_1.jsp','InspectionDiscrepSave;InvItemRetrieveById;ReceiptHeaderRetrieveById;ReceiptLineRetrieve') ;
	}

	function cancelThis() {
		doSubmit('/receipts/rec_inspection_detail.jsp','RecInspectionRetrieveDetail') ;
	}


	function validateForm()
	{

		return true;
	}

	function setDeleteAll()
	{
		frm.deleteall.value = "TRUE";
	}

	function getDateField(row)
	{
		if (row == 0 && maxRows == 1)
		{
			show_calendar('InspectionDiscrep_inspStartDate', '<%=userDateFormat%>');
		}
		else
		{
			show_calendar('InspectionDiscrep_inspStartDate[' + row + ']', '<%=userDateFormat%>');
		}
	}

// End Hide script -->
</SCRIPT>