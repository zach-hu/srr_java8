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

PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
if (poHeader == null) 	poHeader = new PoHeader();

InspectionHeader inspectionHeader = (InspectionHeader) request.getAttribute("inspectionHeader") ;
if (inspectionHeader == null) inspectionHeader = new InspectionHeader() ;

ReceiptHeader	receiptHeader = (ReceiptHeader)request.getAttribute("receiptHeader");
if (receiptHeader == null) 	receiptHeader = new ReceiptHeader();

ReceiptLine	receiptLine = (ReceiptLine)request.getAttribute("receiptLine");
if (receiptLine == null) 	receiptLine = new ReceiptLine();

PoLine	poLine = (PoLine) request.getAttribute("poLine") ;
if (poLine == null) 	poLine = new PoLine();

RequisitionLine reqLine = receiptLine.getRequisitionLine() ;
if (reqLine == null) 	reqLine = new RequisitionLine() ;

String engineerId = receiptLine.getEngineerAssigned() ;
if (engineerId == null) engineerId = "" ;
boolean disableEdit = false ;
if(! uid.equals(engineerId)){
	disableEdit = true;
}

String	s_return_page = HiltonUtility.ckNull((String)request.getAttribute("returnPage"));
if (HiltonUtility.isEmpty(s_return_page)) {
	s_return_page = "/receipts/rec_items_step_1.jsp";
}
String	s_return_method = HiltonUtility.ckNull((String)request.getAttribute("returnMethod"));
if (HiltonUtility.isEmpty(s_return_method)) {
	s_return_method = "ReceiptHeaderRetrieveById;ReceiptLineRetrieveByHeader";
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

String s_inspNo =(String)  request.getAttribute("InspectionDispos_icInspNo") ;
if (s_inspNo == null) s_inspNo = "0" ;

List inspectionDisposList = (List) request.getAttribute("inspectionDisposList") ;
if (inspectionDisposList == null) inspectionDisposList = new ArrayList() ;

String inspectCode =  (String)request.getAttribute("inspectCode") ;
String inspRequirements =  (String)request.getAttribute("inspRequirements") ;
String discrepQty =  (String)request.getAttribute("discrepQty") ;

String inspDescription =  (String)request.getAttribute("inspDescription") ;
String inspStatus = (String)request.getAttribute("inspStatus") ;
String inspStartDate = (String)request.getAttribute("inspStartDate") ;

String inspStatusText = "" ;
//for (int ip = 0; ip < receiptDiscrepStatusList.size(); ip++) {
//	StdTable tbl = (StdTable) receiptDiscrepStatusList.get(ip) ;
//	String stat = tbl.getTableKey() ;
//	if (stat.compareTo(inspStatus) != 0) continue ;
//	inspStatusText = tbl.getDescription() ;
// }

int i = 0;

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
<input type="hidden" name="InspectionLine_icMsrLine" value="<%=receiptLine.getIcLineHistory()%>">
<input type="hidden" name="InspectionHeader_area" value="<%=inspectionHeader.getArea() %>">
<input type="hidden" name="InspectionHeader_location" value="<%=inspectionHeader.getLocation() %>">
<input type="hidden" name="InspectionHeader_storage" value="<%=inspectionHeader.getStorage() %>">

<input type="hidden" name="InspectionDispos_icRecHeader" value="<%=receiptLine.getIcRecHeader()%>">
<input type="hidden" name="InspectionDispos_icRecLine" value="<%=receiptLine.getIcRecLine()%>">

<input type="hidden" name="InspectionDispos_icMsrLine" value="<%=receiptLine.getIcRecLine()%>">
<input type="hidden" name="InspectionDispos_icInspNo" value="<%=s_inspNo%>">
<input type="hidden" name="InspectionDispos_icInspDiscrep" value="<%=(String) request.getAttribute("InspectionDispos_icInspDiscrep")%>">

<input type="hidden" name="discrepQty" value="<%=discrepQty%>">
<input type="hidden" name="inspectCode" value="<%=inspectCode%>">
<input type="hidden" name="inspRequirements" value="<%=inspRequirements%>">
<input type="hidden" name="inspDescription" value="<%=inspDescription%>">
<input type="hidden" name="inspStatus" value="<%=inspStatus%>">
<input type="hidden" name="inspStartDate" value="<%=inspStartDate%>">

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
<tsa:hidden name="as_rows" value="<%=inspectionDisposList.size()%>"/>

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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Inspection Dispositions</div>
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

<table border=0 cellspacing=0 cellpadding=0 width=880px>
<tr>
	<td>
	<%
			BigDecimal qtyToInspect = HiltonUtility.ckNull(receiptLine.getQtyStep0Accepted());
			BigDecimal inspected = HiltonUtility.ckNull(receiptLine.getQtyStep1Accepted());
			BigDecimal balanceQty = qtyToInspect.subtract(inspected);
	%>

		<table border="0" cellpadding="0" cellspacing="0" align="center">
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
			<tsa:td>
		<table id="inspectionDiscrepHdr" border="0" cellpadding="2" cellspacing="2" align="center" width="100%" style='table-layout:fixed';>
				<tsa:tr>
					<tsa:td field="ins-inspectCode"  cssClass="browseHdr" align="center" width="5%">Insp  Code</tsa:td>
					<tsa:td field="ins-discrepRequirements"  cssClass="browseHdr" align="left" width="25%"><tsa:label labelName="ins-discrepRequirements" defaultString="PO Requirements"/></tsa:td>
					<tsa:td field="ins-discrepDescription"  cssClass="browseHdr" align="left" width="25%"><tsa:label labelName="ins-discrepDescription" defaultString="Discrepant Description"/></tsa:td>
					<tsa:td field="ins-discrepQuantity"  cssClass="browseHdr" align="center" width="8%"><tsa:label labelName="ins-discrepQuantity" defaultString="Quantity"/></tsa:td>
					<tsa:td field="ins-discrepStatus"  cssClass="browseHdr" align="left" width="11%"><tsa:label labelName="ins-discrepStatus" defaultString="Status"/></tsa:td>
					<tsa:td field="ins-discrepStartDate"  cssClass="browseHdr" align="center" width="11%"><tsa:label labelName="ins-discrepStartDate" defaultString="Status Date"/></tsa:td>
				</tsa:tr>
		</table>
			</tsa:td>
		</tsa:tr>
		<tsa:tr>
		<tsa:td>
				<table id="inspectionDiscrepRow" border="0" cellpadding="2" cellspacing="2" align="center" width="100%" style='table-layout:fixed';>
				<tr>
					<tsa:td  id="inspectCode" valign="top" width="5%" align="center"><%=inspectCode %></tsa:td>
					<tsa:td  id="inspRequirements" valign="top" width="25%"><%=inspRequirements %></tsa:td>
					<tsa:td  id="inspDescription" valign="top" width="25%"><%=inspDescription %></tsa:td>
					<tsa:td  id="inspQuantity" valign="top" width="8%" align="center"><%=HiltonUtility.getFormattedQuantity(discrepQty, oid)%></tsa:td>
					<tsa:td  id="inspStatusText" valign="top" width="11%"><%=inspStatusText%></tsa:td>

					<tsa:td  id="inspStartDate" valign="top" width="11%" align="center"><%=inspStartDate %></tsa:td>

				</tr>
				</table>

		</tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td><hr width="680px" align="center" color="#9999CC"></hr></tsa:td>
		</tsa:tr>

		<tr>
		<td>
	<table id="inspectionDisposHdr" border="0" cellpadding="2" cellspacing="2" align="left" width="100%" style='table-layout:fixed';>
				<tsa:tr>
					<tsa:td width="5%">&nbsp;</tsa:td>
					<tsa:td field="ins-dispType"  width="10%" noWrap="nowrap" align="center"><a href="javascript: browseStd('InspectionDispos_dispType','DISP',true); void(0);"><tsa:label labelName="ins-dispType" defaultString="Disp Type"/></a></tsa:td>
					<tsa:td field="ins-disposition"  width="25%" noWrap="nowrap" align="left"><tsa:label labelName="ins-disposition" defaultString="D/C Disposition"/></a></tsa:td>
					<tsa:td field="ins-description"  width="25%" noWrap="nowrap" align="left"><tsa:label labelName="ins-descriptionn" defaultString="D/C Disposition Description"/></a></tsa:td>
					<tsa:td field="ins-dispQuantity" width="10%" noWrap="nowrap" align="left">&nbsp;<tsa:label labelName="ins-dispQuantity" defaultString="Quantity"/></tsa:td>
					<tsa:td field="ins-dispClosed" width="15%" noWrap="nowrap" align="left">&nbsp;<tsa:label labelName="ins-dispClosed" defaultString="Disp Closed"/></tsa:td>
				</tsa:tr>
	</table>
		</td>
		</tr>
		<tr>
			<tsa:td>
	<table id="inspectionDisposRows" border="0" cellpadding="2" cellspacing="2" align="left" width="100%" style='table-layout:fixed';>

<%
	String s_set_row = "";
	int i_rowcount = 0;
	int sequenceNo = 0;

	if (inspectionDisposList != null){

	for (i = 0; i < inspectionDisposList.size(); i++)
	{
		s_set_row = "ONFOCUS='setCurrentRow(" + i_rowcount + ");'";
		InspectionDispos inspectionDispos = (InspectionDispos) inspectionDisposList.get(i);
		sequenceNo++ ;

%>
				<tr>
					<tsa:td id="seqNo_<%=i%>" valign="top" align="left" width="5%">&nbsp;	</tsa:td>

					<tsa:td  id="dispType" valign="top" width="10%"><input type="text" name="InspectionDispos_dispType" value="<%=inspectionDispos.getDispType() %>" size="10" maxLength="15"  <%=s_set_row%>></tsa:td>
					<tsa:td  id="disposition" valign="top" width="25%"><input type="text" name="InspectionDispos_disposition" value="<%=inspectionDispos.getDisposition() %>" size="40" maxLength="255"  <%=s_set_row%>></tsa:td>
					<tsa:td  id="description" valign="top" width="25%">
						<textarea name="InspectionDispos_description" cols=40 rows=4 onKeyDown="textCounter(this, 2000);" onKeyUp="textCounter(this,2000);"><%=inspectionDispos.getDescription() %></textarea>
					</tsa:td>
					<tsa:td  id="dispQuantity" valign="top" width="10%"><input type="text" name="InspectionDispos_dispQuantity" value="<%=HiltonUtility.getFormattedQuantity(inspectionDispos.getDispQuantity(),oid)%>" style="text-align:right" size="10" maxLength="15"  <%=s_set_row%>></tsa:td>
					<tsa:td  id="dispClosed" valign="top" width="15%"><input type="text" name="InspectionDispos_dispClosed" value="<%=HiltonUtility.getFormattedDate(inspectionDispos.getDispClosed(),oid) %>" size="10" maxLength="10"  <%=s_set_row%>>
					<a href="javascript: getDateField(<%=i %>);"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign="bottom" border="0"></a>
					</tsa:td>
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
		<% if (! disableEdit)  {  %>
		<tsa:tr>
			<tsa:td align="center"><a href="javascript: addNew(); void(0);"><font class="reset"><b>Add New Disposition</b></font></a></tsa:td>
		</tsa:tr>
		<% } %>
		<tsa:tr>
			<tsa:td><hr width="580px" align="center" color="#9999CC"></hr></tsa:td>
		</tsa:tr>
		<br>

</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=880px>
<tsa:tr>
	<% if (! disableEdit)  {  %>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: submitThis()">Save</a></div></tsa:td>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: cancelThis(); void(0);">Return</a></div></tsa:td>
	<% } else {  %>
	<tsa:td width="100%" align="center"><div id="pxbutton"><a href="javascript: cancelThis(); void(0);">Return</a></div></tsa:td>
	<% }  %>
</tsa:tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script
	frm=document.purchaseform;
	var maxRows = frm.as_maxrows.value;
	var myTable = document.getElementById("inspectionDisposRows");
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

	function browseCriteriaNo(frmField,inspectionCode){
		popupParameters = "colname=InspectionCrit_id_inspectCode;operator==;filter_txt=" + inspectionCode + ";logicalOperator=AND;originalFilter=N;sort=N;"
		browseLookup(frmField, 'inspectioncrit-no');
	}

	function addNew()
	{
		//frm.deleteall.value = "FALSE";
		myTable = document.getElementById("inspectionDisposRows");
		TotalRows = TotalRows+1;
		count = myTable.rows.length;

		myRow = myTable.insertRow(count);
		maxRows++;
		count++ ;

		myCell = myRow.insertCell();
		id = "seqNo_" + (count - 1);
		myCell.id = id;
		myCell.valign = "top";
		myCell.align = "left";
		myCell.width = "5%" ;
		myCell.innerHTML = "" ;

		myCell = myRow.insertCell();
		id = "dispType";
		myCell.id = id;
		myCell.valign = "top";
		myCell.align = "left";
		myCell.width = "10%" ;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"InspectionDispos_dispType\" SIZE=\"15\" MAXLENGTH=\"15\" value=\"\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";

		myCell = myRow.insertCell();
		id = "disposition";
		myCell.id = id;
		myCell.valign = "top";
		myCell.align = "left";
		myCell.width = "25%" ;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"InspectionDispos_disposition\" SIZE=\"40\" MAXLENGTH=\"255\" value=\"\"  ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";

		myCell = myRow.insertCell();
		id = "description";
		myCell.id = id;
		myCell.valign = "top";
		myCell.align = "left";
		myCell.width = "25%" ;
		myCell.innerHTML = "<textarea name=\"InspectionDispos_description\" cols=40 rows=4 onKeyDown=\"textCounter(this, 2000);\" onKeyUp=\"textCounter(this,2000);\"></textarea>";

		myCell = myRow.insertCell();
		id = "dispQuantity";
		myCell.id = id;
		myCell.valign = "top";
		myCell.align = "left";
		myCell.width = "10%" ;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"InspectionDispos_dispQuantity\" STYLE=\"text-align:right\" SIZE=\"10\" MAXLENGTH=\"15\" value=\"0\"  ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";

		myCell = myRow.insertCell();
		id = "dispClosed";
		myCell.id = id;
		myCell.valign = "top";
		myCell.align = "left";
		myCell.width = "15%" ;
		htmlText = "<INPUT TYPE=\"TEXT\" NAME=\"InspectionDispos_dispClosed\" SIZE=\"10\" MAXLENGTH=\"10\" value=\"<%=HiltonUtility.getFormattedDate(d_today,oid)%>\"  ONFOCUS='setCurrentRow(" + (count - 1) + ");'>" ;
		htmlText = htmlText + "<a href=\"javascript: getDateField( count- 1);\"><img src=\"<%=contextPath%>/images/calendar.gif\" alt=\"Click here to choose a date or enter a date in the box on the left.\" valign=\"bottom\" border=\"0\"></a>";
		myCell.innerHTML = htmlText;


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

		myTable = document.getElementById("inspectionDisposRows");
		var currentRows = myTable.rows.length - 1;
		if (currentRows < 0)
		{
			return;
		}
		else if (currentRows > 1)
		{
			for (var i = row; i < (currentRows - 1); i++)
			{
				var fld1 = frm.InspectionDispos_disposition[i + 1].value;
				var fld2 = frm.InspectionDispos_description[i + 1].value;
				var fld3 = frm.InspectionDispos_dispType[i + 1].value;
				var fld4 = frm.InspectionDispos_dispQuantity[i + 1].value;
				var fld5 = frm.InspectionDispos_dispClosed[i + 1].value;


				frm.InspectionDispos_disposition[i].value = fld1;
				InspectionDispos_description[i].value = fld2
				frm.InspectionDispos_dispType[i].value = fld3;
				frm.InspectionDispos_dispQuantity[i].value = fld4;
				frm.InspectionDispos_dispClosed[i].value = fld5;

			}
		}

		myTable = document.getElementById("inspectionDisposRows");
		myTable.deleteRow(currentRows);
/*
		if (currentRows <= 1)
		{
			frm.deleteall.value = "TRUE";
		}
*/
		//to fix totals percent


	}

	function disposValidate() {

		insqty = eval(frm.discrepQty.value ) ;
		qty = 0
		ok =  true ;
		var currentRows = myTable.rows.length ;


		if (currentRows > 0) {
			if (currentRows > 1) {
				for (var i = 0; i < currentRows; i++)
				{
					if (eval( frm.InspectionDispos_dispQuantity[i].value) > insqty)   {
						ok = false ;
						break ;
					}
				}
			} else {
				if (  eval( frm.InspectionDispos_dispQuantity.value) > insqty) {
					ok = false
				}
			}
			if (! ok) {
				alert("Disposition quantity is greater than discrepancy quantity " + insqty + "!")
			}
		}
		return ok ;
	}

	function viewInpsectionCodes()
	{
	}

	function submitThis()
	{
		if (disposValidate()) {
			doSubmit('receipts/rec_inspection_dispos.jsp','InspectionDisposSave;InspectionDisposRetrieve');
		}
//		doSubmit('receipts/rec_inspection_discrep.jsp','InspectionDisposSave;InspectionDiscrepRetrieve');
	}

	function cancelThis() {
		doSubmit('receipts/rec_inspection_discrep.jsp','InspectionDiscrepRetrieve');
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
			show_calendar('InspectionDispos_dispClosed', '<%=userDateFormat%>');
		}
		else
		{
			show_calendar('InspectionDispos_dispClosed[' + row + ']', '<%=userDateFormat%>');
		}
	}

// End Hide script -->
</SCRIPT>