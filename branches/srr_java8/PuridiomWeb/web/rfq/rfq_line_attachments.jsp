<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/attachments.js"></script>
<%
	String	icHeader = (String) request.getAttribute("DocAttachment_icHeader");
	String s_icRfqHeader = (String) request.getAttribute("RfqHeader_icRfqHeader");
	String s_rfqNumber = (String) request.getAttribute("RfqHeader_rfqNumber");
	String s_rfqAmendment = (String) request.getAttribute("RfqHeader_rfqAmendment");
	String s_rfqStatus = (String) request.getAttribute("RfqHeader_status");
	String	returnPage = "";
	String	s_current_process = "SHOPCART";
	String	s_current_page = "/rfq/rfq_line_attachments.jsp";
	String	s_current_method = "DocAttachmentUpdateByLine";
	String	s_current_process_method = "";

	String bd_ic_rfq_line = (String) request.getAttribute("RfqLine_icRfqLine");
	String bd_ic_rfq_line_rfq = (String) request.getAttribute("RfqLine_icRfqLine");
	String bd_item_number = (String) request.getAttribute("RfqLine_itemNumber");
	String s_rfq_line_status = (String) request.getAttribute("RfqLine_status");

	String	s_line_count = (String) request.getAttribute("lineCount");

	boolean solicitationActive = PropertiesManager.getInstance(oid).getProperty("RFQ OPTIONS", "SOLICITATIONS", "N").equalsIgnoreCase("Y");
	boolean editMode = false;

	if (s_rfqNumber == null) {
		s_rfqNumber = (String) request.getAttribute("formNumber");
	}
	if (s_icRfqHeader == null) {
		s_icRfqHeader = (String) request.getAttribute("DocAttachment_icHeader");
	}
	if (s_rfqStatus == null) {
		s_rfqStatus = (String) request.getAttribute("formStatus");
	}

	if (role.getAccessRights("RFQ") > 1 && (s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) < 0 || s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENAMENDMENT) == 0)) {
		editMode = true;
	}
	if (s_view.equals("WIZARD")) {
		returnPage = "/rfq/rfq_review.jsp";
	} else {
		returnPage = "/rfq/rfq_summary.jsp";
	}
%>
<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_rfqNumber%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="<%=s_rfqAmendment%>"/>
<tsa:hidden name="RfqHeader_status" value="<%=s_rfqStatus%>"/>
<tsa:hidden name="RfqHeader_rfqType" value="${esapi:encodeForHTMLAttribute(RfqHeader_rfqType)}"/>
<tsa:hidden name="RfqLine_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqQuestion_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="DocAttachment_icLine" value="<%=bd_ic_rfq_line%>"/>
<tsa:hidden name="Default_referenceType" value="RFL"/>
<tsa:hidden name="hasDocs" value="NO"/>
<tsa:hidden name="DocAttachment_delete" value="FALSE"/>
<tsa:hidden name="formNumber" value="<%=s_rfqNumber%>"/>
<tsa:hidden name="formStatus" value="<%=s_rfqStatus%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="filename" value=""/>

<tsa:hidden name="RfqLine_icRfqLine" value="<%=bd_ic_rfq_line%>"/>
<tsa:hidden name="RfqLine_icReqLineOld" value="<%=bd_ic_rfq_line_rfq%>"/>
<tsa:hidden name="RfqLine_itemNumber" value="<%=bd_item_number%>"/>
<tsa:hidden name="RfqLine_status" value="<%=s_rfq_line_status%>"/>

<tsa:hidden name="formtype" value="RFQ"/>
<tsa:hidden name="lineCount" value="<%=s_line_count%>"/>


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
		<%@ include file="/rfq/rfq_status_title.jsp" %>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
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

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top align=center>
		<div id="attachmentList" style="visibility: visible; display: block'">
			<table border=0 cellpadding=1 cellspacing=0 width=525px>
			<tr>
				<td width=5%>&nbsp;</td>
				<td width=60%><b>Document Title</b></td>
				<td width=5%>&nbsp;</td>
				<td width=10% align=center><b>Print</b></td>
<%		if (solicitationActive) {%>
				<td width=10% align=center><b>Post</b></td>
<%		}
			if (editMode) { %>
				<td width=10% align=center><b>Delete</b></td>
<%		} else {%>
				<td width=10% align=center><b>&nbsp;</b></td>
<%		}%>
			</tr>
			<tr>
				<td colspan=6 vAlign=top>
					<div id="noAttachmentsMsg" style="visibility: hidden; display: none;">
					<table border=0 cellpadding=1 cellspacing=0 width=525px>
					<tr><td width=100% align=center><br>There are currently no documents attached.<br></td></tr>
					</table>
					</div>
					<table id="attachments" border=0 cellpadding=1 cellspacing=0 width=525px>
<%
	List list = (List) request.getAttribute("docAttachmentList");
	if (list != null) {
		for (int i = 0; i < list.size(); i++) {
			DocAttachment docAttachment = (DocAttachment) list.get(i);
			if (docAttachment == null) {
				continue;
			}
			DocAttachmentPK docAttachmentPK = (DocAttachmentPK) docAttachment.getComp_id();
			String	filename = docAttachment.getDocFilename();
			String	ext = filename.substring(filename.lastIndexOf(".") + 1);
%>
					<tr>
						<td width=5% align=right id="doc_num_<%=i%>">&nbsp;<%=i+1%>.&nbsp;<tsa:hidden name="docIc" value="<%=docAttachment.getComp_id().getDocIc()%>"/></td>
						<td width=60%><tsa:input type="text" name="docTitle" value = "<%=docAttachment.getDocTitle()%>" maxLength="60" size="60"/></td>
						<td width=5% valign=middle align=center>
							<tsa:hidden name="docFilename" value = "<%=filename%>"/>
<%		if (ext.equalsIgnoreCase("DOC") || ext.equalsIgnoreCase("DOCX")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS") || ext.equalsIgnoreCase("XLSX")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
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
						<td width=10% align=center><input type="checkbox" name="cboxPrint" <% if (docAttachment.getDocPrint().indexOf("Y")>= 0){ %>CHECKED<%}%> value="Y" ONCLICK="setPrint(<%=i%>);">
							<tsa:hidden name="docPrint" value="<%=docAttachment.getDocPrint()%>"/>
						</td>
<%		if (solicitationActive) {%>
						<td width=10% align=center><input type="checkbox" name="cboxPost" <% if (docAttachment.getDocPost().indexOf("Y")>= 0){ %>CHECKED<%}%> value="Y" ONCLICK="setPost(<%=i%>);">
							<tsa:hidden name="docPost" value="<%=docAttachment.getDocPost()%>"/>

<%		}
			if (editMode) { %>
						<td width=10% id="doc_del_<%=i%>" align=center><a href="javascript: if (verifyAction('Are you sure you wish to delete Attachment line <%=(i+1)%>?')) { deleteMe(<%=i%>); } "><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></td>
<%		} else { %>
						<td width=10%>&nbsp;</td>
<%		} %>
					</tr>
<%	}
	}%>
					</table>
				</td>
			</tr>
			</table>
<%	if (editMode) {%>
			<br>
			<table border=0 cellpadding=0 cellspacing=0 width=500px>
			<tr>
				<td nowrap align=center width=50%><a href="javascript: addAttachment(); void(0)"><span class="reset"><b>Add new</b></span></a></td>
				<td nowrap align=center width=50%><a href="javascript: addStandardAttachment(); void(0)"><span class="reset"><b>Add standard form</b></span></a></td>
			</tr>
			</table>
<%	}%>
		</div>
	</td>
</tr>
</table>

<br>
<br>

<div id="classicNavigation">
<table border="0" cellpadding="0" cellspacing="0" width="<%=formEntryWidth%>">
	<tsa:tr>
<%	if (editMode) { %>
		<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: saveAttachments(); void(0);"><tsa:label labelName="rfq-save" defaultString="Save"></tsa:label></a></div></tsa:td>
		<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_item.jsp', 'RfqLineRetrieve'); void(0);"><tsa:label labelName="rfq-return" defaultString="Return"></tsa:label></a></div></tsa:td>
<%	} else {%>
		<tsa:td width="100%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_item.jsp', 'RfqLineRetrieve'); void(0);"><tsa:label labelName="rfq-return" defaultString="Return"></tsa:label></a></div></tsa:td>
<%	}%>
	</tsa:tr>
	</table>
</div>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var inEditMode = <%=editMode%>;
	var currentPage = "<%=s_current_page%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var currentPage = "/rfq/rfq_line_attachments.jsp";
	var returnPage = "/rfq/rfq_item.jsp";
	var returnHandler = "RfqLineRetrieve";
	var rfqnumber = "<%=s_rfqNumber%>";
	var myTable = document.getElementById("attachments");
	var totalRows = myTable.rows.length;


	function addAttachment() {
		doSubmit('/rfq/rfq_item_attachment_new.jsp', 'DoNothing');
	}

	function thisLoad() {
		f_StartIt();
		setDisplay();
<%	if (!editMode) { %>
		checkInputSettings();
		allowEdit = false;
<%	} %>
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>