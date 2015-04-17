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
	String	s_buyer_code = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_buyerCode"));
	BigDecimal	bd_zero = new BigDecimal(0);
	BigDecimal	bd_release_number = new BigDecimal(s_release_number);
	BigDecimal	bd_revision_number = new BigDecimal(s_revision_number);
	String	returnPage = "";
	boolean editMode = false;

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_po_admin = propertiesManager.getProperty("PO OPTIONS", "AdminChanges", "N");
	String	s_po_type = (String) request.getAttribute("PoHeader_poType");

	String	s_current_process = "HEADER_ATTACHMENTS";
	String	s_current_page = "/orders/po_contract_attachments.jsp";
	String	s_current_method = "DocAttachmentUpdateByHeader";
	String	s_current_process_method = "";

	  boolean bAdminChanges = false;
	  if (s_po_admin.equalsIgnoreCase("Y") && role.getAccessRights("PO") == 99)
	  {
	  	bAdminChanges = true;
	  }

	if (s_po_number == null)
	{
		s_po_number = (String) request.getAttribute("formNumber");
	}
	if (s_ic_po_header == null)
	{
		s_ic_po_header = (String) request.getAttribute("DocComment_icHeader");
	}
	if (s_po_status == null)
	{
		s_po_status = (String) request.getAttribute("formStatus");
	}

	if (s_po_status.compareTo(DocumentStatus.PO_REJECTED) <= 0) {
		editMode = true;
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
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
<tsa:hidden name="PoHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="PoHeader_status" value="<%=s_po_status%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="POH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="StdTable_tableType" value=""/>
<!--  <tsa:hidden name="DocAttachment_icHeader" value="<%=icHeader%>"/>
-->
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="hasDocs" value="NO"/>
<tsa:hidden name="DocAttachment_delete" value="FALSE"/>
<tsa:hidden name="Schedule_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="Schedule_documentType" value="POH"/>

<tsa:hidden name="filename" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="contract-contents" defaultString="Contract Contents" /></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
<%	int i_colspan = 1;%>
		<tr>
			<td nowrap align=right><b><tsa:label labelName="order-number" defaultString="Order #" />:</b></td>
			<td width=100px><%=s_po_number%></td>
<%	if (bd_release_number.compareTo(bd_zero) > 0)
		{
			i_colspan = i_colspan + 2; %>
			<td nowrap align=right><b><tsa:label labelName="release-number" defaultString="Release #" />:</b></td>
			<td width=100px><%=bd_release_number%></td>
<%	}
		if (bd_revision_number.compareTo(bd_zero) > 0)
		{
			i_colspan = i_colspan + 2; %>
			<td nowrap align=right><b><tsa:label labelName="revision-number" defaultString="Revision #" />:</b></td>
			<td width=100px><%=bd_revision_number%></td>
<%	} %>
		</tr>
		<tr>
			<td colspan=<%=i_colspan%> nowrap align=right><b><tsa:label labelName="status" defaultString="Status" />:</b></td>
			<td width=100px><%=DocumentStatus.toString(s_po_status, oid)%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width="100%">
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
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top align=center>
		<div id="attachmentList" style="visibility: visible; display: block'">
			<table border=0 cellpadding=1 cellspacing=0 width=500px>
			<tr>
				<td width=5%>&nbsp;</td>
				<td width=65%><b><tsa:label labelName="document-title" defaultString="Document Title" /></b></td>
				<td width=5%>&nbsp;</td>
				<td width=10% align=center><b><tsa:label labelName="print" defaultString="Print" /></b></td>
<%		if (editMode) { %>
				<td width=15% align=center><b><tsa:label labelName="delete" defaultString="Delete" /></b></td>
<%		} else {%>
				<td width=15% align=center>&nbsp;</td>
<%		}%>
			</tr>
			<tr>
				<td colspan=8 vAlign=top>
					<div id="noAttachmentsMsg" style="visibility: hidden; display: none;">
					<table border=0 cellpadding=1 cellspacing=0 width=500px>
					<tr><td width=100% align=center><br><tsa:label labelName="no-documents-attached-msg" defaultString="There are currently no documents attached." /><br></td></tr>
					</table>
					</div>
					<table id="attachments" border=0 cellpadding=1 cellspacing=0 width=500px>
<%
	List list = (List) request.getAttribute("docAttachmentList");

	if (list != null) {
		for (int i = 0; i < list.size(); i++) {
			DocAttachment docAttachment = (DocAttachment) list.get(i);
			DocAttachmentPK docAttachmentPK = (DocAttachmentPK) docAttachment.getComp_id();
			String	filename = docAttachment.getDocFilename();
			String	ext = filename.substring(filename.lastIndexOf(".") + 1);
%>
					<tr>
						<td width=5% align=right id="doc_num_<%=i%>">&nbsp;<%=i+1%>.&nbsp;<tsa:hidden name="docIc" value="<%=docAttachment.getComp_id().getDocIc()%>" /><tsa:hidden name="icHeader" value="<%=docAttachment.getComp_id().getIcHeader()%>"/></td>
						<td width=65%><tsa:input type="text" name="docTitle" value="<%=docAttachment.getDocTitle()%>" maxLength="60" size="60" /></td>
						<td width=5% valign=middle align=center>
							<tsa:hidden name="docFilename" value = "<%=filename%>"/>
<%		if (ext.equalsIgnoreCase("DOC")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%		} else {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%		}%>
						</td>
						<td width=10% align=center><input type="checkbox" name="cboxPrint" <% if (docAttachment.getDocPrint().indexOf("Y")>= 0) {%>CHECKED<%}%> value="Y" ONCLICK="setPrint(<%=i%>);">
							<tsa:hidden name="docPrint" value="<%=docAttachment.getDocPrint()%>"/>
						</td>
<%		if (editMode) { %>
						<td width=15% id="doc_del_<%=i%>" align=center><a href="javascript: if (verifyAction('Are you sure you wish to delete Attachment line <%=(i+1)%>?')) { deleteMe(<%=i%>); } "><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></td>
<%		} else { %>
						<td width=15%>&nbsp;</td>
<%		} %>
					</tr>
<%	}
	} %>
					</table>
				</td>
			</tr>
			</table>
			<br>
			<table border=0 cellpadding=0 cellspacing=0 width=500px>
			<tr><td nowrap align=center><a href="javascript: addAttachment(); void(0)"><font class=reset><b>Add new</b></font></a></td></tr>
			</table>
		</div>
	</td>
<%	if (s_view.equals("WIZARD")) { %>
      <td rowspan=2 align="right" valign="top"><%@ include file="/orders/po_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<!--
<tr>
	<td width=50% align=center><a href="javascript: saveAttachments(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('<%=returnPage%>', 'PoRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
</tr>
-->
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

  var ponumber = "<%= headerEncoder.encodeForJavaScript(s_po_number) %>";
  var fiscalyear = "<%= headerEncoder.encodeForJavaScript(s_fiscal_year) %>";
  var currentpage = "<%= headerEncoder.encodeForJavaScript(s_current_page) %>";
  var currentmethod = "<%= headerEncoder.encodeForJavaScript(s_current_method) %>";
  var currentprocessmethod = "<%=s_current_process_method%>";

	inEditMode = <%=editMode%>;
	currentPage = "/orders/po_contract_attachments.jsp";
	returnPage = "<%= headerEncoder.encodeForJavaScript(returnPage) %>";
	returnHandler = "PoRetrieve";
	myTable = document.getElementById("attachments");
	totalRows = myTable.rows.length;

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
		doSubmit('/orders/po_contract_attachment_new.jsp', 'DoNothing');
	}

	function deleteMe(row) {
		var docIc = "";
		if (totalRows > 1) {
			docIc = frm.docIc[row].value;
		} else {
			docIc = frm.docIc.value;
		}
		frm.DocAttachment_delete.value='TRUE';
		var newInputField = "<input type='hidden' name='DocAttachment_docIc' value='" + docIc + "'/>";
		setHiddenFields(newInputField);

		doSubmit(currentPage, 'PoHeaderRetrieveById;DocAttachmentDelete;DocAttachmentRetrieveByHeader');
	}
// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>