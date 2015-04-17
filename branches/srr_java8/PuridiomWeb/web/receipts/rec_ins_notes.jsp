<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/comments.js"></script>
<SCRIPT SRC="<%=contextPath%>/scripts/req-audit.js"></SCRIPT>
<%
PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

String	s_display_options = propertiesManager.getProperty("COMMENTS", "DisplayOptions", "Y");
String s_default_print = propertiesManager.getProperty("COMMENTS", "DefaultPrint", "N");

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
	String	s_current_page = "/receipts/rec_ins_notes.jsp";
	String	s_current_method = "DocCommentUpdate";
	String	s_current_process_method = "";
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
<tsa:hidden name="currentPage" value="/receipts/rec_ins_notes.jsp"/>

<tsa:hidden name="InspectionHeader_icInspNo" value="<%=s_icInspNo%>"/>
<tsa:hidden name="InspectionHeader_icMsrLine" value="<%=s_icMsrLine%>"/>
<tsa:hidden name="InspectionHeader_status" value="<%=s_inspectionStatus%>"/>

<%
	if (HiltonUtility.isEmpty(s_rec_number))
	{
		s_rec_number = "N/A";
	}
%>

<table width="<%=formWidth%>" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tsa:tr>
			<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12">
				<table border="0" cellspacing="0" cellpadding="2">
				<tsa:tr><tsa:td cssClass="hdr12" noWrap="nowrap"><tsa:label labelName="comments" defaultString="Comments"></tsa:label></tsa:td></tsa:tr>
				</table>
				</div>
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
<div style="display:none;">
<%@ include file="/receipts/rec_inspection_info.jsp" %>
</div>
<br>

<%
	List cmtList = (List) request.getAttribute("docCommentList");
	List docTextList = (List) request.getAttribute("docTextList");
%>
<table border="0" cellpadding="0" cellspacing="0" width="<%=formEntryWidth%>">
<tsa:tr>
	<tsa:td align="center" valign="top" width="80%">
		<table border="0" cellspacing="0" cellpadding="0" width="90%">
		<tsa:tr>
			<tsa:td align="center" valign="top">
				<div id="commentsMoveRow" style="visibility: hidden; display: none;">
					<table border="0" align="center" width="25px">
							<tsa:tr height="25px">
								<tsa:td align="center"><a href="javascript: //" onclick="javascript: move('U'); void(0);"><img src="<%=contextPath%>/images/up_arrows.gif" class="processOnImg" border="0" alt="Move Up"></a></tsa:td>
							</tsa:tr>
							<tsa:tr height="25px">
								<tsa:td align="center"><a href="javascript: //" onclick="javascript: move('D'); void(0);"><img src="<%=contextPath%>/images/down_arrows.gif" class="processOnImg" border="0" alt="Move Down"></a></tsa:td>
							</tsa:tr>
					</table>
				</div>
			</tsa:td>
			<tsa:td valign="top" align="center">
				<div id="commentList" style="visibility: visible; display: block; width: 100%">
					<table border="0" cellpadding="1" cellspacing="0" width="90%">
					<tsa:tr>
						<tsa:td cssClass="browseHdrDk" width="8%">&nbsp;</tsa:td>
						<tsa:td cssClass="browseHdrDk" width="16%"><b><tsa:label labelName="commentId" defaultString="Comment ID"></tsa:label></b></tsa:td>
						<tsa:td cssClass="browseHdrDk" width="42%"><b><tsa:label labelName="title" defaultString="Title"></tsa:label></b></tsa:td>
		<%	if (!s_display_options.equalsIgnoreCase("N")) {	%>
						<tsa:td cssClass="browseHdrDk" align="center" width="7%"><b><tsa:label labelName="print" defaultString="Print"></tsa:label></b></tsa:td>
						<tsa:td cssClass="browseHdrDk" align="center" width="7%"><b><tsa:label labelName="bold" defaultString="Bold"></tsa:label></b></tsa:td>
						<tsa:td cssClass="browseHdrDk" align="center" width="15%"><b><tsa:label labelName="placement" defaultString="Placement"></tsa:label></b></tsa:td>
		<%	}	%>
						<tsa:td cssClass="browseHdrDk" width="5%">&nbsp;</tsa:td>
					</tsa:tr>
					<tsa:tr>
						<tsa:td colspan="8" valign="top">
							<div id="noCommentsMsg" style="visibility: hidden; display: none;">
							<table border="0" cellpadding="1" cellspacing="0" width="100%">
							<tsa:tr><tsa:td width="100%" align="center"><tsa:label labelName="noCommentsDisplay" defaultString="No comments to display"></tsa:label>.</tsa:td></tsa:tr>
							</table>
							</div>
							<table id="cmt_comments" border="0" cellpadding="1" cellspacing="0" width="100%" class="browseRow">
		<%


			if (cmtList != null)
			{
				for (int i = 0; i < cmtList.size(); i++)
				{
					DocComment docComment = (DocComment) cmtList.get(i);
					String	s_std_text = "";
					if (docTextList != null && docTextList.size() > 0)
					{
						DocText docText = (DocText) docTextList.get(i);
						s_std_text = docText.getStdText();
						s_std_text = s_std_text.replace('"', '~');
		    			s_std_text = s_std_text.replaceAll("~", "&#34;");
					}
					DocCommentPK docCommentPK = (DocCommentPK) docComment.getComp_id();
					BigDecimal b_cmtOrder = docCommentPK.getCommentOrder();
		%>
							<tsa:tr>
								<tsa:td align="right" width="8%" id="<%= \"cmt_num_\" + i%>">&nbsp;<%=i+1%>.&nbsp;<a href="javascript: //" onclick="javascript: highlightRow(<%= i %>);"><img src="<%=contextPath%>/images/next.gif" border="0" alt="Select this line"></a>
									<tsa:hidden name="DocComment_commentOrder" value="<%=b_cmtOrder%>"/>
								</tsa:td>
								<tsa:td width="16%" id="<%= \"cmt_id_\" + i %>">
									<div id="comment_id_<%=i%>">
										<%=docComment.getCommentId()%>
									</div>
								</tsa:td>
								<tsa:td width="42%" id="cmt_edit">
									<div id="comment_edit_<%=i%>">
										<a href="javascript: editCmt(<%=i%>); void(0);" title="Click here to View/Modify details for this comment."><div id="cmt_title" name="cmt_title"><%=docComment.getCommentTitle()%></div></a>
										<tsa:hidden name="DocComment_commentTitle" id="<%= \"commentTitle_\" + i %>" value="<%=docComment.getCommentTitle()%>"/>
										<tsa:hidden name="DocComment_commentId" value="<%=docComment.getCommentId()%>"/>
										<tsa:hidden name="DocText_icText" value="<%=docComment.getIcText()%>"/>
										<tsa:hidden name="DocText_stdText" id="<%= \"commentText_\" + i %>" value="<%=s_std_text%>"/>
										<tsa:hidden name="DocComment_commentPublic" id="<%= \"commentPublic_\" + i%>" value="<%=docComment.getCommentPublic()%>"/>
									</div>
								</tsa:td>
								<tsa:td align="center" width="7%">
									<div id="comment_print_<%=i%>">
										<% if (docComment.getCommentPrint().indexOf("Y")>= 0){%>
											<tsa:input type="checkbox" id="<%= \"cboxPrint_\" + i %>" name="<%= \"cboxPrint_\" + i %>" checked="checked" value="Y" onclick="<%= \"setPrint(\" + i + \");\" %>" />
										<%}else {%>
											<tsa:input type="checkbox" id="<%= \"cboxPrint_\" + i %>" name="<%= \"cboxPrint_\" + i %>" value="Y" onclick="<%= \"setPrint(\" + i + \");\" %>" />
										<%} %>
										<tsa:hidden id="<%= \"commentPrint_\" + i %>" name="DocComment_commentPrint" value="<%=docComment.getCommentPrint()%>"/>
									</div>
								</tsa:td>
								<tsa:td align="center" width="7%">
									<div id="comment_bold_<%=i%>">
										<% if (docComment.getCommentBold().indexOf("Y")>= 0){%>
											<tsa:input type="checkbox" id="<%= \"cboxBold_\" + i %>" name="<%= \"cboxBold_\" + i%>" checked="checked" value="Y" onclick="<%= \"setBold(\" + i + \");\" %>" />
										<%}else {%>
											<tsa:input type="checkbox" id="<%= \"cboxBold_\" + i %>" name="<%= \"cboxBold_\" + i%>" value="Y" onclick="<%= \"setBold(\" + i + \");\" %>"/>
										<%} %>
										<tsa:hidden id="<%= \"commentBold_\" + i %>" name="DocComment_commentBold" value="<%=docComment.getCommentBold()%>"/>
									</div>
								</tsa:td>
								<tsa:td align="center" width="15%">
									<div id="comment_place_<%=i%>">
										<select name="DocComment_commentPlace" id="commentPlace_<%=i%>">
											<option value="A" <% if (docComment.getCommentPlace().indexOf("A")>= 0){ %>selected<%}%>><tsa:label labelName="after" defaultString="After"></tsa:label></option>
											<option value="B" <% if (docComment.getCommentPlace().indexOf("B")>= 0){ %>selected<%}%>><tsa:label labelName="before" defaultString="Before"></tsa:label></option>
										</select>
									</div>
								</tsa:td>
								<tsa:td align="center" width="5%" id="<%= \"cmt_del_\" + i %>"><a href="javascript: if (verifyAction('Are you sure you wish to delete Comment line <%=(i+1)%>?')) { deleteMe(<%=i%>); } "><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0"></a></tsa:td>
							</tsa:tr>
		<%	}
			} %>
							</table>
						</tsa:td>
					</tsa:tr>
					</table>
					<br>
					<table border="0" cellpadding="0" cellspacing="0" width="90%">
					<tsa:tr>
						<tsa:td noWrap="nowrap" align="center" width="33%">
							<a href="javascript: addNew(); void(0)"><font class="reset"><b><tsa:label labelName="addComment" defaultString="Add a comment"></tsa:label></b></font></a>
						</tsa:td>
						<tsa:td field="req-addStandardComment" noWrap="nowrap" align="center" width="34%">
							<A href="javascript: addStandardComment(); void(0);"><font class="reset"><b><tsa:label labelName="addStandardComment" defaultString="Add standard comment"></tsa:label></b></font></A>
						</tsa:td>
						<tsa:td noWrap="nowrap" align="center" width="33%">
							<div id="deleteAllLink" style="visibility: visible; display: block;">
							<a href="javascript: deleteAll(); void(0)"><font class="reset"><b><tsa:label labelName="deleteAll" defaultString="Delete all"></tsa:label></b></font></a>
							</div>
						</tsa:td>
					</tsa:tr>
					</table>
				</div>
				<div id="commentEdit" style="visibility: hidden; display: none">
					<table border="0" cellpadding="1" cellspacing="0" width="90%">
					<tsa:tr>
						<tsa:td valign="top">
							<table border="0" cellspacing="0" cellpadding="0" height="100%">
							<tsa:tr>
								<tsa:td>
									<table border="0" cellpadding="0" cellspacing="0" width="250px" align="center">
									<tsa:tr>
										<tsa:td width="120px" align="right" noWrap="nowrap" height="18px"><b><tsa:label labelName="req-comment-id" defaultString="Comment Id"></tsa:label>:</b>&nbsp;</tsa:td>
										<tsa:td id="editCommentId">&nbsp;</tsa:td>
									</tsa:tr>
									<tsa:tr>
										<tsa:td width="120px" align="right" noWrap="nowrap"><b><tsa:label labelName="req-title" defaultString="Title"></tsa:label>:</b>&nbsp;</tsa:td>
										<tsa:td><tsa:input type="text" name="edit_commentTitle" value="" size="60" maxLength="60" onkeypress="disableEnterKey()" /></tsa:td>
									</tsa:tr>
									<tsa:tr>
										<tsa:td width="120px" valign="top" align="right" noWrap="nowrap"><b><tsa:label labelName="req-comment-text" defaultString="Comment Text"></tsa:label>:</b>&nbsp;</tsa:td>
										<tsa:td><tsa:input type="textarea" name="edit_stdText" cols="59" rows="5"></tsa:input></tsa:td>
									</tsa:tr>
									</table>
									<br>

									<div id="commentOptions" style="visibility: visible; display: block">
									<table border="0" cellpadding="1" cellspacing="0" width="100%">
									<tsa:tr>
										<tsa:td width="140px" align="right" noWrap="nowrap"><tsa:input type="checkbox" name="edit_cboxPrint" value="Y" /></tsa:td>
										<tsa:td><b><tsa:label labelName="req-print" defaultString="Print"></tsa:label></b></tsa:td>
										<tsa:td width="65px" align="right" noWrap="nowrap"><tsa:input type="checkbox" name="edit_cboxBold" value="Y" /></tsa:td>
										<tsa:td><b><tsa:label labelName="req-bold" defaultString="Bold"></tsa:label></b></tsa:td>
										<tsa:td width="120px" align="right" noWrap="nowrap"><b><tsa:label labelName="req-placement" defaultString="Placement"></tsa:label>:</b>&nbsp;</tsa:td>
										<tsa:td>
											<select name="edit_commentPlace">
												<option value="A" selected><tsa:label labelName="req-after" defaultString="After"></tsa:label></option>
												<option value="B"><tsa:label labelName="req-before" defaultString="Before"></tsa:label></option>
											</select>
										</tsa:td>
									</tsa:tr>
									</table>
									</div>

									<br>

									<table border="0" cellpadding="1" cellspacing="0" width="100%">
									<tsa:tr>
										<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: populateCmt(); void(0);"><tsa:label labelName="ins-save" defaultString="Save"></tsa:label></a></div></tsa:td>
										<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: cancelEdit(); void(0);"><tsa:label labelName="ins-return" defaultString="Return"></tsa:label></a></div></tsa:td>
									</tsa:tr>
									</table>
								</tsa:td>
							</tsa:tr>
							</table>
						</tsa:td>
					</tsa:tr>
					</table>
				</div>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>
<br>

<div id="classicNavigation">
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript:  doSubmit('/receipts/rec_inspection_detail.jsp', 'DocCommentUpdate;RecInspectionRetrieveDetail'); void(0);"><tsa:label labelName="rec-save" defaultString="Save"></tsa:label></a></div></tsa:td>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('/receipts/rec_inspection_detail.jsp', 'RecInspectionRetrieveDetail'); void(0);"><tsa:label labelName="rec-return" defaultString="Return"></tsa:label></a></div></tsa:td>
</tsa:tr>
</table>
</div>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value="JavaScript">
<!-- Hide script

	frm = document.purchaseform;
	var contextPath = '<%= contextPath %>';
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var displayOptions = "<%=s_display_options%>";
	var defaultPrint = "<%=s_default_print%>";

	var browser = browserCheck();

	myTable = document.getElementById("cmt_comments");
	totalRows = myTable.rows.length

	function thisLoad()
	{
		f_StartIt();
		setDisplay();
//			checkInputSettings();

		hasCommentOrder = true;
		checkNumComments();
		checkDisplayOptions();
	}


	function disableEnterKey()
	{
	   if (window.event.keyCode == 13) window.event.keyCode = 0;
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>