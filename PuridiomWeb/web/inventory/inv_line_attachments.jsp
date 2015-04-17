<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/attachments.js"></script>
<%
	InvItem invItem = (InvItem) request.getAttribute("invItem");
	String	icHeader = (String) request.getAttribute("DocAttachment_icHeader");
	String	s_item_number = (String) request.getAttribute("InvItem_itemNumber");
	String	s_po_number = (String) request.getAttribute("InvItem_poNumber");
	String	inv_status = (String) request.getAttribute("InvItem_status");
	String	s_inv_type = (String) request.getAttribute("InvItem_itemType");
	String	s_buyer_code = HiltonUtility.ckNull((String) request.getAttribute("invItem_buyerCode"));
	String itemAction = (String)request.getAttribute("itemAction");
	BigDecimal	bd_zero = new BigDecimal(0);
	String	returnPage = "";
	boolean editMode = false;
	boolean b_addStandard = true;
	if (oid.equalsIgnoreCase("bsc04p") && s_inv_type.equalsIgnoreCase("CT"))
	{
		b_addStandard = false;	//do not display add standard attachment link for bsc contracts
	}

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	/*NuSs*/
	String	s_po_admin = propertiesManager.getProperty("INV OPTIONS", "AdminChanges", "N");

	String	s_current_process = "HEADER_ATTACHMENTS";
	String	s_current_page = "/inventory/inv_line_attachments.jsp";
	String	s_current_method = "DocAttachmentUpdateByHeader";
	String	s_current_process_method = "";

	  boolean bAdminChanges = false;
	  if (s_po_admin.equalsIgnoreCase("Y") && role.getAccessRights("INV") == 99)
	  {
	  	bAdminChanges = true;
	  }
	  boolean bDisplayPrintOption = true;
	  if (s_inv_type.equalsIgnoreCase("CT") && oid.equalsIgnoreCase("bsc04p"))
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
	if (s_item_number == null)
	{
		s_item_number = (String) request.getAttribute("DocAttachment_icHeader");
	}
	if (inv_status == null)
	{
		inv_status = (String) request.getAttribute("formStatus");
	}

	if (inv_status.compareTo(DocumentStatus.PO_REJECTED) <= 0) {
		editMode = true;
	}

	boolean bAllowEdit = true;
	if (oid.equalsIgnoreCase("akdoc") && !user.isAnAdminBuyer() && !user.getUserId().equals(s_buyer_code))
	{
		bAllowEdit = false;
	}

	if (role.getAccessRights("INV") <= 1)
	{
		bAllowEdit = false;
	}

	/*NuSs
	if (s_view.equals("WIZARD")) {
		returnPage = "/inventory/po_review.jsp";
	} else {
		returnPage = "/inventory/po_summary.jsp";
	}*/
%>
<tsa:hidden name="InvItem_itemNumber" value="<%=s_item_number%>"/>
<tsa:hidden name="InvItem_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="InvItem_status" value="<%=inv_status%>"/>
<tsa:hidden name="InvItem_itemType" value="<%=s_inv_type%>"/>
<tsa:hidden name="InvItem_buyerCode" value="<%=s_buyer_code%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_item_number%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="INV"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_item_number%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="StdTable_tableType" value=""/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=invItem.getIcText()%>"/>
<tsa:hidden name="DocAttachment_icLine" value="0"/>
<tsa:hidden name="Default_referenceType" value="INV"/>
<tsa:hidden name="hasDocs" value="NO"/>
<tsa:hidden name="DocAttachment_delete" value="FALSE"/>
<tsa:hidden name="Schedule_icHeader" value="<%=s_item_number%>"/>
<tsa:hidden name="Schedule_documentType" value="INV"/>
<tsa:hidden name="filename" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="formNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="formStatus" value="<%=inv_status%>"/>
<tsa:hidden name="formtype" value="INV"/>
<tsa:hidden name="itemAction" value="<%=itemAction%>"/>

<tsa:hidden name="DocAttachment_edit_docIc" value="FALSE"/>
<tsa:hidden name="DocAttachment_edit_docTitle" value="FALSE"/>
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
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
<%	int i_colspan = 1;%>
		<tr>
			<td nowrap align=right><b><tsa:label labelName="order-number" defaultString="Order #" />:</b></td>
			<td width=100px><%=s_item_number%></td>
		</tr>
		<tr>
			<td colspan=<%=i_colspan%> nowrap align=right><b><tsa:label labelName="status" defaultString="Status" />:</b></td>
			<td width=100px><%=DocumentStatus.toString(inv_status, oid)%></td>
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


<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
	<tr>
		<td valign=top align=center>
		<%
		List list = (List) request.getAttribute("docAttachmentList");
		if (list == null || list.size() == 0) {
			%>
			<b><tsa:label labelName="no-documents-attached" defaultString="There are currently no documents attached." /></b><br><br>
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
		<%

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
<%		if (ext.equalsIgnoreCase("DOC")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("EML")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/email.gif" border=0 alt="Open Attached Email"></a>
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
				</table>
	<%	if ( !oid.equalsIgnoreCase("akdoc") || user.isAnAdminBuyer() || user.getUserId().equals(s_buyer_code) ) {	%>
				<br>
	<%	}	%>
			</div>
	<%} %>
			<table border=0 cellpadding=0 cellspacing=0 width=80%>
			<tr>
				<td id="addAttachment" nowrap align=center width=50%><a href="javascript: addAttachment(); void(0)"><font class=reset><b><tsa:label labelName="add-new" defaultString="Add new" /></b></font></a></td>
<%	if (b_addStandard) {	%>
				<td id="addStdAttachment" nowrap align=center width=50%><a href="javascript: addStandardAttachment(); void(0)"><span class="reset"><b><tsa:label labelName="add-standard-form" defaultString="Add standard form" /></b></span></a></td>
<%	}	%>
			</tr>
			<%	if (s_inv_type.equalsIgnoreCase("CT") && oid.equalsIgnoreCase("bsc04p")) {	%>
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
	</tr>
</table>

<br>
<br>

<div id="classicNavigation">
<table border="0" cellpadding="0" cellspacing="0" width="<%=formEntryWidth%>">
	<tsa:tr>
<%	if (role.getAccessRights("INV") > 1) { %>
		<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: saveAttachments(); void(0);"><tsa:label labelName="inv-save" defaultString="Save"></tsa:label></a></div></tsa:td>
		<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('/inventory/inv_item.jsp', 'InvItemRetrieveById'); void(0);"><tsa:label labelName="inv-return" defaultString="Return"></tsa:label></a></div></tsa:td>
<%	} else {%>
		<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('/inventory/inv_item.jsp', 'InvItemRetrieveById'); void(0);"><tsa:label labelName="inv-return" defaultString="Return"></tsa:label></a></div></tsa:td>
<%	} %>
	</tsa:tr>
	</table>
</div>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
//NuSs
	var editNewPage = "/inventory/po_edit_attachment_new.jsp";

  var ponumber = "<%=s_po_number%>";
  var currentpage = "<%=s_current_page%>";
  var currentmethod = "<%=s_current_method%>";
  var currentprocessmethod = "<%=s_current_process_method%>";

  var inEditMode = <%=editMode%>;
  var currentPage = "/inventory/inv_line_attachments.jsp";
  var returnPage = "/inventory/inv_item.jsp";
  var returnHandler = "InvItemRetrieveById";
<%	if (!(list == null || list.size() == 0)) {%>
  var myTable = document.getElementById("attachments");
  var totalRows = myTable.rows.length - 1;
<%	}%>

	function addAttachment() {
		doSubmit('/inventory/inv_attachment_new.jsp', 'DoNothing');
	}

	function thisLoad()
	{
		f_StartIt();
<%	if ( !bAllowEdit ) {	%>
			checkInputSettings();
			$('#addAttachment, #addStdAttachment').hide();
<%	}	%>
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>