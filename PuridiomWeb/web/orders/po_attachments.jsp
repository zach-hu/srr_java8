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
	BigDecimal	bd_zero = new BigDecimal(0);
	BigDecimal	bd_release_number = new BigDecimal(s_release_number);
	BigDecimal	bd_revision_number = new BigDecimal(s_revision_number);
	String	returnPage = "";
	boolean editMode = false;
	boolean b_addStandard = true;
	if (oid.equalsIgnoreCase("bsc04p") && s_po_type.equalsIgnoreCase("CT"))
	{
		b_addStandard = false;	//do not display add standard attachment link for bsc contracts
	}

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_po_admin = propertiesManager.getProperty("PO OPTIONS", "AdminChanges", "N");

	String	s_current_process = "HEADER_ATTACHMENTS";
	String	s_current_page = "/orders/po_attachments.jsp";
	String	s_current_method = "DocAttachmentUpdateByHeader";
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

	if (s_view.equals("WIZARD")) {
		returnPage = "/orders/po_review.jsp";
	} else {
		returnPage = "/orders/po_summary.jsp";
	}
%>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoSecurity_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
<tsa:hidden name="PoHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="PoHeader_status" value="<%=s_po_status%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="PoHeader_itemLocation" value="<%=s_item_location%>"/>
<tsa:hidden name="PoHeader_buyerCode" value="<%=s_buyer_code%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="POH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="StdTable_tableType" value=""/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="DocAttachment_icLine" value="0"/>
<tsa:hidden name="Default_referenceType" value="POH"/>
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
<%	if (poHeader != null) { %>
<tsa:hidden name="poIcReqHeader" value="<%= poHeader.getIcReqHeader().toString() %>"/>
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="attachments" defaultString="Attachments" /></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign="bottom" align="right" height="30px">
		<%@ include file="/orders/po_display_number.jsp" %>
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
			<br><b><tsa:label labelName="no-documents-attached" defaultString="There are currently no documents attached." /></b><br><br>
	<% 			} else {%>
			<div id="attachmentList" style="visibility: visible; display: block;">
				<table id="attachments" border=0 cellpadding=1 cellspacing=0 width=80%>
					<tr>
						<td width=5%>&nbsp;</td>
						<td width=50%><b><tsa:label labelName="document-title" defaultString="Document Title" /></b></td>
						<td width=5%>&nbsp;</td>
	<%		if (bAllowEdit) { %>
					<td width=11% align=center><b><tsa:label labelName="replace" defaultString="Replace" /></b></td>
	<%		}
				if (bDisplayPrintOption) {	%>
					<td width=10% align=center><b><tsa:label labelName="print" defaultString="Print" /></b></td>
	<%		}
				if (editMode && bAllowEdit) { %>
					<td width=10% align=center><b><tsa:label labelName="delete" defaultString="Delete" /></b></td>
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
						<td width=5% align=right id="doc_num_<%=i%>">&nbsp;<%=i+1%>.&nbsp;<tsa:hidden name="docIc" value="<%=docAttachment.getComp_id().getDocIc()%>"/><tsa:hidden name="icHeader" value="<%=docAttachment.getComp_id().getIcHeader()%>"/></td>
						<td width=65%><tsa:input type="text" name="docTitle" value="<%=docAttachment.getDocTitle()%>" maxLength="60" size="60" /></td>
						<td width=5% valign=middle align=center>
							<tsa:hidden name="docFilename" value = "<%=filename%>"/>
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
							<tsa:hidden name="docPrint" value="<%=docAttachment.getDocPrint()%>"/>
						</td>
<%		} else {	%>
						<tsa:hidden name="docPrint" value="Y"/>
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
			<table border=0 cellpadding=0 cellspacing=0 width=80%>
			<tr>
				<td nowrap align=center width=50%><a href="javascript: addAttachment(); void(0)"><font class=reset><b><tsa:label labelName="add-new" defaultString="Add new" /></b></font></a></td>
<%	if (b_addStandard) {	%>
				<td nowrap align=center width=50%><a href="javascript: addStandardAttachment(); void(0)"><span class="reset"><b><tsa:label labelName="add-standard-form" defaultString="Add standard form" /></b></span></a></td>
<%	}	%>
			</tr>
			<%	if (s_po_type.equalsIgnoreCase("CT") && oid.equalsIgnoreCase("bsc04p")) {	%>
			<tr>
				<td <%	if (b_addStandard){%> colspan="2"<%}%> align="center" >
					<table align="center">
					<tr>
						<td class="red">
						<br><br><tsa:label labelName="add-pertinent-documents-msg" defaultString="Add pertinent documents to the contract record as attachments." />
						</td>
					</tr>
					</table>
				</td>
			</tr>
<%	}	%>
			</table>
		</td>
	<%	if (s_view.equals("WIZARD")) { %>
	      <td rowspan=2 align="right" valign="top"><%@ include file="/orders/po_sidebar.jsp" %></td>
	<%	} %>
	</tr>
</table>

<br>
<br>

<div id="classicNavigation">
	<%	if (s_view.equalsIgnoreCase("CLASSIC") ) { %>
	<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
	<tr>
		<td width=50% align=center><div id="pxbutton"><a href="javascript: saveAttachments(); void(0);"><tsa:label labelName="save" defaultString="Save" /></a></div></td>
		<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('<%= headerEncoder.encodeForJavaScript(returnPage) %>', 'PoRetrieve'); void(0);"><tsa:label labelName="return" defaultString="Return" /></a></div></td>
	</tr>
	</table>
	<%	} %>
</div>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var editNewPage = "/orders/po_edit_attachment_new.jsp";

  var ponumber = "<%= headerEncoder.encodeForJavaScript(s_po_number) %>";
  var fiscalyear = "<%= headerEncoder.encodeForJavaScript(s_fiscal_year) %>";
  var currentpage = "<%= headerEncoder.encodeForJavaScript(s_current_page) %>";
  var currentmethod = "<%= headerEncoder.encodeForJavaScript(s_current_method) %>";
  var currentprocessmethod = "<%= headerEncoder.encodeForJavaScript(s_current_process_method) %>";

  var inEditMode = <%=editMode%>;
  var currentPage = "/orders/po_attachments.jsp";
  var returnPage = "<%= headerEncoder.encodeForJavaScript(returnPage) %>";
  var returnHandler = "PoRetrieve";
<%	if (!(list == null || list.size() == 0)) {%>
  var myTable = document.getElementById("attachments");
  var totalRows = myTable.rows.length - 1;
<%	}%>

	function validateForm() {
		if (frm.DocAttachment_delete.value == 'TRUE') { return true ; }
		var newInputFields = "";
		var handlers = "DocAttachmentUpdateByHeader;" + returnHandler;

		if (totalRows > 1) {
		    frm.hasDocs.value = "YES" ;
			for (var i=0; i < totalRows; i++) {
				var docIc = frm.docIc[i].value;
				var docTitle = frm.docTitle[i].value;
				var docPrint = frm.docPrint[i].value;
				var docPost = "N";

				if (frm.docPost != null && frm.docPost != undefined) {
					docPost = frm.docPost[i].value;
				}

				newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docIc' value='" + docIc + "'>";
				newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docTitle' value='" + docTitle + "'>";
				newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docPrint' value='" + docPrint + "'>";
				newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docPost' value='" + docPost + "'>";
			}
		} else if (totalRows == 1) {
			newInputFields = "<input type='hidden' name='DocAttachment_docIc' value='" + frm.docIc.value + "'>";
			newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docTitle' value='" + frm.docTitle.value + "'>";
			newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docPrint' value='" + frm.docPrint.value + "'>";
			if (frm.docPost != null && frm.docPost != undefined) {
				newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docPost' value='" + frm.docPost.value + "'>";
			} else {
				newInputFields = newInputFields + "<input type='hidden' name='DocAttachment_docPost' value='N'>";
			}
		} else {
		}
		setHiddenFields(newInputFields);
		return true ;
	}

	function addAttachment() {
		doSubmit('/orders/po_attachment_new.jsp', 'DoNothing');
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