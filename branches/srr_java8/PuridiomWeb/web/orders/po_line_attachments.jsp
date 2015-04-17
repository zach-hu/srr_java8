<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/attachments.js"></script>
<%
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	String	icHeader = (String) request.getAttribute("DocAttachment_icHeader");
	String	s_ic_po_header = (String) request.getAttribute("PoHeader_icPoHeader");
	String	s_po_number = (String) request.getAttribute("PoHeader_poNumber");
	String	s_release_number = (String) request.getAttribute("PoHeader_releaseNumber");
	String	s_revision_number = (String) request.getAttribute("PoHeader_revisionNumber");
	String	s_fiscal_year = (String) request.getAttribute("PoHeader_fiscalYear");
	String	s_po_status = (String) request.getAttribute("PoHeader_status");
	String	s_po_type = (String) request.getAttribute("PoHeader_poType");
	String	s_item_location = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_itemLocation"));
	String	s_buyer_code = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_buyerCode"));
	String s_vendor_id = HiltonUtility.ckNull((String) request.getAttribute("VendorInsurance_vendorId"));

	String bd_ic_po_line = (String) request.getAttribute("PoLine_icPoLine");
	String bd_ic_po_line_key = (String) request.getAttribute("PoLine_icRelKey");
	String bd_ic_po_line_req = (String) request.getAttribute("PoLine_icReqLine");
	String bd_ic_po_line_rfq = (String) request.getAttribute("PoLine_icRfqLine");
	String bd_line_number = (String) request.getAttribute("PoLine_lineNumber");
	String s_po_line_status = (String) request.getAttribute("PoLine_status");

	BigDecimal	bd_zero = new BigDecimal(0);
	BigDecimal	bd_release_number = new BigDecimal(s_release_number);
	BigDecimal	bd_revision_number = new BigDecimal(s_revision_number);
	String	returnPage = "";

	String	s_line_count = (String) request.getAttribute("lineCount");

	boolean editMode = false;
	boolean b_addStandard = true;
	if (oid.equalsIgnoreCase("bsc04p") && s_po_type.equalsIgnoreCase("CT"))
	{
		b_addStandard = false;	//do not display add standard attachment link for bsc contracts
	}

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_po_admin = propertiesManager.getProperty("PO OPTIONS", "AdminChanges", "N");

	String	s_current_process = "SHOPCART";
	String	s_current_page = "/orders/po_line_attachments.jsp";
	String	s_current_method = "DocAttachmentUpdateByLine";
	String	s_current_process_method = "";

	boolean bAdminChanges = false;
	if (s_po_admin.equalsIgnoreCase("Y") && role.getAccessRights("PO") == 99)
	{
		bAdminChanges = true;
	}
	boolean bDisplayPrintOption = true;
	if (s_po_type.equalsIgnoreCase("CT") && oid.equalsIgnoreCase("bsc04p"))
	{
		bDisplayPrintOption = false;
	}

	if (s_po_number == null)
	{
		s_po_number = (String) request.getAttribute("formNumber");

		if (s_po_number == null)
		{
			s_po_number = "N/A";
		}
	}
	if (s_ic_po_header == null)
	{
		s_ic_po_header = (String) request.getAttribute("DocAttachment_icHeader");
	}
	if (s_po_status == null)
	{
		s_po_status = (String) request.getAttribute("formStatus");
	}

	if (s_po_status.compareTo(DocumentStatus.PO_REJECTED) <= 0) {
		editMode = true;
	}

	boolean bAllowEdit = true;
	if (oid.equalsIgnoreCase("akdoc") && !user.isAnAdminBuyer() && !user.getUserId().equals(s_buyer_code))
	{
		bAllowEdit = false;
	}

	if (s_po_status.compareTo(DocumentStatus.PO_REJECTED) > 0 || role.getAccessRights("PO") < 2) {
		bAllowEdit = false;
	}

	if (s_view.equals("WIZARD")) {
		returnPage = "/orders/po_review.jsp";
	} else {
		returnPage = "/orders/po_summary.jsp";
	}
%>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
<tsa:hidden name="PoHeader_status" value="<%=s_po_status%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="POH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>

<tsa:hidden name="PoLine_icPoLine" value="<%=bd_ic_po_line%>"/>
<tsa:hidden name="PoLine_icRelKey" value="<%=bd_ic_po_line_key%>"/>
<tsa:hidden name="PoLine_icReqLine" value="<%=bd_ic_po_line_req%>"/>
<tsa:hidden name="PoLine_icReqLineOld" value="<%=bd_ic_po_line_req%>"/>
<tsa:hidden name="PoLine_icRfqLine" value="<%=bd_ic_po_line_rfq%>"/>
<tsa:hidden name="PoLine_lineNumber" value="<%=bd_line_number%>"/>
<tsa:hidden name="PoLine_status" value="<%=s_po_line_status%>"/>

<tsa:hidden name="PoSecurity_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="PoHeader_itemLocation" value="<%=s_item_location%>"/>
<tsa:hidden name="PoHeader_buyerCode" value="<%=s_buyer_code%>"/>
<tsa:hidden name="StdTable_tableType" value=""/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="DocAttachment_icLine" value="<%=bd_ic_po_line%>"/>
<tsa:hidden name="Default_referenceType" value="POL"/>
<tsa:hidden name="hasDocs" value="NO"/>
<tsa:hidden name="DocAttachment_delete" value="FALSE"/>
<tsa:hidden name="Schedule_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="Schedule_documentType" value="POH"/>
<tsa:hidden name="filename" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="formNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="formStatus" value="<%=s_po_status%>"/>
<tsa:hidden name="fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="VendorInsurance_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="VendorInsurance_contNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="VendorInsurance_vendorId" value="<%=s_vendor_id%>"/>
<tsa:hidden name="formtype" value="PO"/>

<tsa:hidden name="DocAttachment_edit_docIc" value="FALSE"/>
<tsa:hidden name="DocAttachment_edit_docTitle" value="FALSE"/>

<input type=hidden name=lineCount value="<%=s_line_count%>">

<%	if (poHeader != null) { %>
<input type="hidden" name="poIcReqHeader" value="<%= poHeader.getIcReqHeader().toString() %>">
<%	} %>
<%
	if (HiltonUtility.isEmpty(s_po_number)) {
		s_po_number = "N/A";
	}
%>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Attachments</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
<%	int i_colspan = 1;%>
		<tr>
			<td nowrap align=right><b>Order #:</b></td>
			<td width=200px><%=s_po_number%></td>
<%	if (bd_release_number.compareTo(bd_zero) > 0)
		{
			i_colspan = i_colspan + 2; %>
			<td nowrap align=right><b>Release #:</b></td>
			<td width=200px><%=bd_release_number%></td>
<%	}
		if (bd_revision_number.compareTo(bd_zero) > 0)
		{
			i_colspan = i_colspan + 2; %>
			<td nowrap align=right><b>Revision #:</b></td>
			<td width=200px><%=bd_revision_number%></td>
<%	} %>
		</tr>
		<tr>
			<td colspan=<%=i_colspan%> nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=200px><%=DocumentStatus.toString(s_po_status, oid)%></td>
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
<%@ include file="/system/error_msg.jsp" %>
<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
	<tr>
		<td valign=top align=center>
		<%
		List list = (List) request.getAttribute("docAttachmentList");
		if (list == null || list.size() == 0) {
			%>
			<br><b>There are currently no documents attached.</b><br><br>
	<% 			} else {%>
			<div id="attachmentList" style="visibility: visible; display: block;">
				<table id="attachments" border=0 cellpadding=1 cellspacing=0 width=80%>
					<tr>
						<td width=5%>&nbsp;</td>
						<td width=50%><b>Document Title</b></td>
						<td width=5%>&nbsp;</td>
	<%		if (bAllowEdit) { %>
					<td width=11% align=center><b>Replace</b></td>
	<%		}
				if (bDisplayPrintOption) {	%>
					<td width=10% align=center><b>Print</b></td>
	<%		}
				if (editMode && bAllowEdit) { %>
					<td width=10% align=center><b>Delete</b></td>
	<%		} else {%>
					<td width=10% align=center>&nbsp;</td>
	<%		}%>
					</tr>
					<!--tr>
						<td colspan=8 vAlign=top>
							<div id="noAttachmentsMsg" style="visibility: hidden; display: none;">
							<table border=0 cellpadding=1 cellspacing=0 width=80%>
							<tr><td width=100% align=center><br>There are currently no documents attached.<br></td></tr>
							</table>
							</div>
							<table id="attachments" border=0 cellpadding=1 cellspacing=0 width=80%-->
		<%
//			List list = (List) request.getAttribute("docAttachmentList");

			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					DocAttachment docAttachment = (DocAttachment) list.get(i);
					DocAttachmentPK docAttachmentPK = (DocAttachmentPK) docAttachment.getComp_id();
					String	filename = docAttachment.getDocFilename();
					String	ext = filename.substring(filename.lastIndexOf(".") + 1);
		%>
					<tr>
						<td width=5% align=right id="doc_num_<%=i%>">&nbsp;<%=i+1%>.&nbsp;<tsa:hidden name="docIc" value="<%=docAttachment.getComp_id().getDocIc()%>"/></td>
						<td width=65%><input type=text name="docTitle" value = "<%=docAttachment.getDocTitle()%>" maxLength=60 size=60></td>
						<td width=5% valign=middle align=center>
							<input type=hidden name="docFilename" value = "<%=filename%>">
<%		if (ext.equalsIgnoreCase("DOC") || ext.equalsIgnoreCase("DOCX")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS") || ext.equalsIgnoreCase("XLSX")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("EML")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/email.gif" border=0 alt="Open Attached Email"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT") || ext.equalsIgnoreCase("PPTX")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%		} else {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%		}%>
						</td>
<%		if (bAllowEdit) { %>
						<td width=9% align=center><a href="javascript: editAttachment(<%=i%>); void(0);"><img src="<%=contextPath%>/images/cmd_edit.gif" alt="Click here to select another document to replace the existing attachment." border=0></a></td>
<%		}
			if (bDisplayPrintOption) { %>
						<td width=10% align=center>
							<input type="checkbox" name="cboxPrint" <% if (docAttachment.getDocPrint().indexOf("Y")>= 0) {%>CHECKED<%}%> value="Y" ONCLICK="setPrint(<%=i%>);">
							<input type=hidden name="docPrint" value="<%=docAttachment.getDocPrint()%>">
						</td>
<%		} else {	%>
						<input type=hidden name="docPrint" value="Y">
<%		}
			if (editMode && bAllowEdit) { %>
						<td width=15% id="doc_del_<%=i%>" align=center><a href="javascript: if (verifyAction('Are you sure you wish to delete Attachment line <%=(i+1)%>?')) { deleteMe(<%=i%>); } "><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></td>
<%		} else { %>
						<td width=15%>&nbsp;</td>
<%		} %>
					</tr>
		<%	}
			} %>
							<!--/table>
						</td>
					</tr-->
				</table>
	<%	if ( !oid.equalsIgnoreCase("akdoc") || user.isAnAdminBuyer() || user.getUserId().equals(s_buyer_code) ) {	%>
				<br>
	<%	}	%>
			</div>
	<%} %>
<%	if (s_po_status.compareTo(DocumentStatus.PO_REJECTED) <= 0 && role.getAccessRights("PO") > 1) { %>
			<table border=0 cellpadding=0 cellspacing=0 width=80%>
			<tr>
				<td nowrap align=center width=50%><a href="javascript: addAttachment(); void(0)"><font class=reset><b>Add new</b></font></a></td>
<%	if (b_addStandard) {	%>
				<td nowrap align=center width=50%><a href="javascript: addStandardAttachment(); void(0)"><span class="reset"><b>Add standard form</b></span></a></td>
<%	}	%>
			</tr>
			<%	if (s_po_type.equalsIgnoreCase("CT") && oid.equalsIgnoreCase("bsc04p")) {	%>
			<tr>
				<td <%	if (b_addStandard){%> colspan="2"<%}%> align="center" >
					<table align="center">
					<tr>
						<td class="red">
						<br><br>Add pertinent documents to the contract record as attachments.
						</td>
					</tr>
					</table>
				</td>
			</tr>
<%	}	%>
			</table>
<%	} %>
		</td>

	</tr>
</table>

<br>
<br>

<div id="classicNavigation">
<table border="0" cellpadding="0" cellspacing="0" width="<%=formEntryWidth%>">
	<tsa:tr>
<%	if (s_po_status.compareTo(DocumentStatus.PO_REJECTED) <= 0 && role.getAccessRights("PO") > 1) { %>
		<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: saveAttachments(); void(0);"><tsa:label labelName="po-save" defaultString="Save"></tsa:label></a></div></tsa:td>
		<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('/orders/po_item.jsp', 'PoLineRetrieve'); void(0);"><tsa:label labelName="po-return" defaultString="Return"></tsa:label></a></div></tsa:td>
<%	} else { %>
		<tsa:td width="100%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('/orders/po_item.jsp', 'PoLineRetrieve'); void(0);"><tsa:label labelName="po-return" defaultString="Return"></tsa:label></a></div></tsa:td>
<%	} %>
	</tsa:tr>
	</table>
</div>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var editNewPage = "/orders/po_item_edit_attachment_new.jsp";

  var ponumber = "<%= headerEncoder.encodeForJavaScript(s_po_number) %>";
  var fiscalyear = "<%= headerEncoder.encodeForJavaScript(s_fiscal_year) %>";
  var currentpage = "<%= headerEncoder.encodeForJavaScript(s_current_page) %>";
  var currentmethod = "<%= headerEncoder.encodeForJavaScript(s_current_method) %>";
  var currentprocessmethod = "<%= headerEncoder.encodeForJavaScript(s_current_process_method) %>";

  var inEditMode = <%=editMode%>;
  var currentPage = "/orders/po_line_attachments.jsp";
  var returnPage = "/orders/po_item.jsp";
  var returnHandler = "PoLineRetrieve";
<%	if (!(list == null || list.size() == 0)) {%>
  var myTable = document.getElementById("attachments");
  var totalRows = myTable.rows.length - 1;
<%	}%>

	function addAttachment() {
		doSubmit('/orders/po_item_attachment_new.jsp', 'DoNothing');
	}

	function thisLoad()
	{
		f_StartIt();
<%	if ( !bAllowEdit ) {	%>
			checkInputSettings();
<%	}	%>
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>