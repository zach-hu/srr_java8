<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/comments.js"></script>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_display_options = propertiesManager.getProperty("COMMENTS", "DisplayOptions", "Y");
	String s_default_print = propertiesManager.getProperty("COMMENTS", "DefaultPrint", "N");
	String	s_req_ic_line = (String) request.getParameter("RequisitionLine_icReqLine");
	String	s_req_ic_header = (String) request.getAttribute("RequisitionHeader_icReqHeader");
	String	s_req_number = (String) request.getAttribute("RequisitionHeader_requisitionNumber");
	String	s_req_status = (String) request.getAttribute("RequisitionHeader_status");
	String	s_line_number = (String) request.getAttribute("RequisitionLine_lineNumber");
	String	s_line_count = (String) request.getAttribute("lineCount");
	String	s_from_page = HiltonUtility.ckNull((String) request.getAttribute("frompage"));
	String	s_fiscal_year = (String) request.getAttribute("RequisitionHeader_fiscalYear");
	String	s_item_location = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_itemLocation"));
	String	s_req_subtype = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_rqSubType"));

	String	s_icInspNo = (String) request.getAttribute("InspectionHeader_icInspNo");
	String	s_icMsrLine = (String) request.getAttribute("InspectionHeader_icMsrLine");
	String	s_inspectionStatus = (String) request.getAttribute("InspectionHeader_status");

	if (s_req_number == null)
	{
		s_req_number = (String) request.getAttribute("formNumber");
	}
	if (s_line_number == null)
	{
		s_line_number = (String) request.getAttribute("lineNumber");
	}
	if (s_req_ic_header == null)
	{
		s_req_ic_header = (String) request.getAttribute("DocComment_icHeader");
	}
	if (s_req_ic_line == null)
	{
		s_req_ic_line = (String) request.getAttribute("DocComment_icLine");
	}
	if (s_req_status == null)
	{
		s_req_status = (String) request.getAttribute("formStatus");
	}
	if (s_fiscal_year == null)	{		s_fiscal_year = (String) request.getAttribute("fiscalYear");		}
%>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="RequisitionLine_icReqLine" value="<%=s_req_ic_line%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_req_number%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=s_req_status%>"/>
<tsa:hidden name="RequisitionHeader_itemLocation" value="<%=s_item_location%>"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="<%=s_req_subtype%>"/>
<tsa:hidden name="RequisitionLine_lineNumber" value="<%=s_line_number%>"/>
<tsa:hidden name="lineCount" value="<%=s_line_count%>"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_icInspNo%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="Default_referenceType" value="INH"/>
<tsa:hidden name="formNumber" value="<%=s_req_number%>"/>
<tsa:hidden name="formStatus" value="<%=s_req_status%>"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="frompage" value="<%=s_from_page%>"/>
<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="InspectionHeader_icReqHeader" value="<%=s_icInspNo%>"/>
<tsa:hidden name="InspectionHeader_icMsrLine" value="<%=s_icMsrLine%>"/>
<tsa:hidden name="InspectionHeader_status" value="<%=s_inspectionStatus%>"/>

<%	if (HiltonUtility.isEmpty(s_req_number))	{		s_req_number = "N/A";	}	%>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tsa:tr>
			<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="lineItem" defaultString="Line Item"/> <%=s_line_number%> - <tsa:label labelName="comments" defaultString="Comments"/></div>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" width="3px"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<tsa:td valign="bottom" height="30px" width="*">
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="requisitionNumber" defaultString="Requisition #"/>:</b></tsa:td>
			<tsa:td width="125px"><%=headerEncoder.encodeForHTML(s_req_number)%></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="status" defaultString="Status"/>:</b></tsa:td>
			<tsa:td width="125px"><%=DocumentStatus.toString(s_req_status, oid)%></tsa:td>
		</tsa:tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0>
		<tsa:tr>
			<tsa:td width="100%" height="1px" cssClass="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td height="2px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tsa:tr>
	<tsa:td valign="top" align="center">
		<div id="commentList" style="visibility: visible; display: block'">
			<table border=0 cellpadding=1 cellspacing=0 width=500px>
			<tsa:tr>
				<tsa:td width="4%">&nbsp;</tsa:td>
				<tsa:td width="20%"><b><tsa:label labelName="commentId" defaultString="Comment ID"/></b></tsa:td>
				<tsa:td width="42%"><b><tsa:label labelName="title" defaultString="Title"/></b></tsa:td>
<%	if (!s_display_options.equalsIgnoreCase("N")) {	%>
				<tsa:td align="center" width="7%"><b><tsa:label labelName="print" defaultString="Print"/></b></tsa:td>
				<tsa:td align="center" width="7%"><b><tsa:label labelName="bold" defaultString="Bold"/></b></tsa:td>
				<tsa:td align="center" width="15%"><b><tsa:label labelName="placement" defaultString="Placement"/></b></tsa:td>
<%	}	%>
				<tsa:td width="5%">&nbsp;</tsa:td>
			</tsa:tr>
			<tsa:tr>
				<tsa:td colspan="8" valign="top">
					<div id="noCommentsMsg" style="visibility: hidden; display: none;">
					<table border=0 cellpadding=1 cellspacing=0 width=500px>
					<tsa:tr><tsa:td width="100%" align="center"><tsa:label labelName="noCommentsDisplay" defaultString="No comments to display"/>.</tsa:td></tsa:tr>
					</table>
					</div>
					<table id="cmt_comments" border=0 cellpadding=1 cellspacing=0 width=500px>
<%
	List cmtList = (List) request.getAttribute("docCommentList");
	List docTextList = (List) request.getAttribute("docTextList");

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
						<tsa:td valign="top" align="right" width="4%" id="cmt_num_<%=i%>">&nbsp;<%=i+1%>.&nbsp;<tsa:hidden name="DocComment_commentOrder" value="<%=b_cmtOrder%>"/></tsa:td>
						<tsa:td valign="top" width="20%" id="cmt_id_<%=i%>"><%=docComment.getCommentId()%></tsa:td>
						<tsa:td valign="top" width="42%" id="cmt_edit"><a href="javascript: editCmt(<%=i%>); void(0);"><div id="cmt_title"><%=docComment.getCommentTitle()%></div></a>
							<tsa:hidden name="DocComment_commentTitle" id="<%= \"commentTitle_\" + i %>" value="<%=docComment.getCommentTitle()%>"/>
							<tsa:hidden name="DocComment_commentId" value="<%=docComment.getCommentId()%>"/>
							<tsa:hidden name="DocText_icText" value="<%=docComment.getIcText()%>"/>
							<tsa:hidden name="DocText_stdText" id="<%= \"commentText_\" + i%>" value="<%=s_std_text%>"/>
							<tsa:hidden name="DocComment_commentPublic" id="<%= \"commentPublic_\" + i%>" value="<%=docComment.getCommentPublic()%>"/>
						</tsa:td>
						<tsa:td valign="top" align="center" width="7%">
							<div id="comment_print_<%=i%>">
								<% if (docComment.getCommentPrint().indexOf("Y")>= 0){ %>
									<tsa:input type="checkbox" name="cboxPrint_<%=i%>" checked="checked" value="Y" onclick="setPrint(<%=i%>);"/>
								<%}else { %>
									<tsa:input type="checkbox" name="cboxPrint_<%=i%>" value="Y" onclick="setPrint(<%=i%>);"/>
								<%} %>
								<tsa:hidden id="commentPrint_<%=i%>" name="DocComment_commentPrint" value="<%=docComment.getCommentPrint()%>"/>
							</div>
						</tsa:td>
						<tsa:td valign="top" align="center" width="7%">
							<div id="comment_bold_<%=i%>">
								<% if (docComment.getCommentBold().indexOf("Y")>= 0){ %>
									<tsa:input type="checkbox" name="cboxBold_<%=i%>" checked="checked" value="Y" onclick="setBold(<%=i%>);"/>
								<%}else { %>
									<tsa:input type="checkbox" name="cboxBold_<%=i%>" value="Y" onclick="setBold(<%=i%>);"/>
								<%} %>
								<tsa:hidden id="commentBold_<%=i%>" name="DocComment_commentBold" value="<%=docComment.getCommentBold()%>"/>
							</div>
						</tsa:td>
						<tsa:td valign="top" align="center" width="15%">
							<div id="comment_place_<%=i%>">
								<select name="DocComment_commentPlace" id="commentPlace_<%=i%>">
									<option value="A" <% if (docComment.getCommentPlace().indexOf("A")>= 0){ %>selected<%}%>><tsa:label labelName="after" defaultString="After"/></option>
									<option value="B" <% if (docComment.getCommentPlace().indexOf("B")>= 0){ %>selected<%}%>><tsa:label labelName="before" defaultString="Before"/></option>
								</select>
							</div>
						</tsa:td>
<%		if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0 || s_req_status.compareTo(DocumentStatus.RFQ_PRICED) == 0 || s_req_status.compareTo(DocumentStatus.TEMPLATE) == 0) { %>
						<tsa:td valign="top" align="center" width="5%" id="cmt_del_<%=i%>"><a href="javascript: if (verifyAction('Are you sure you wish to delete Comment line <%=(i+1)%>?')) { deleteMe(<%=i%>); } "><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></tsa:td>
<%		} else { %>
						<tsa:td width="5%">&nbsp;</tsa:td>
<%		} %>
					</tsa:tr>
<%	}
	} %>
					</table>
				</tsa:td>
			</tsa:tr>
			</table>
<%		if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0 || s_req_status.compareTo(DocumentStatus.RFQ_PRICED) == 0 || s_req_status.compareTo(DocumentStatus.TEMPLATE) == 0) { %>
			<br>
			<table border=0 cellpadding=0 cellspacing=0 width=500px>
			<tsa:tr>
				<tsa:td noWrap="nowrap" align="center" width="33%">
					<a href="javascript: addNew(); void(0)"><font class=reset><b><tsa:label labelName="addComment" defaultString="Add a comment"/></b></font></a>
				</tsa:td>
				<tsa:td field="req-addStandardComment" noWrap="nowrap" align="center" width="34%">
					<A href="javascript: addStandardComment(); void(0);"><font class=reset><b><tsa:label labelName="addStandardComment" defaultString="Add standard comment"/></b></font></A>
				</tsa:td>
				<tsa:td noWrap="nowrap" align="center" width="33%">
					<div id="deleteAllLink" style="visibility: visible; display: block;">
					<a href="javascript: deleteAll(); void(0)"><font class="reset"><b><tsa:label labelName="deleteAll" defaultString="Delete all"/></b></font></a>
					</div>
				</tsa:td>
			</tsa:tr>
			</table>
<%	}%>
		</div>
		<div id="commentEdit" style="visibility: hidden; display: none">
			<table border=0 cellpadding=1 cellspacing=0 width=500px>
			<tsa:tr>
				<tsa:td valign="top">
					<table border=0 cellspacing=0 cellpadding=0 height=100%>
					<tsa:tr>
						<tsa:td>
							<table border=0 cellpadding=0 cellspacing=0 width=250px align=center>
							<tsa:tr>
								<tsa:td width="120px" align="right" noWrap="nowrap" height="18px"><b><tsa:label labelName="req-comment-id" defaultString="Comment Id"/>:</b>&nbsp;</tsa:td>
								<tsa:td id="editCommentId">&nbsp;</tsa:td>
							</tsa:tr>
							<tsa:tr>
								<tsa:td width="120px" align="right" noWrap="nowrap"><b><tsa:label labelName="req-title" defaultString="Title"/>:</b>&nbsp;</tsa:td>
								<tsa:td><tsa:input type="text" name="edit_commentTitle" value="" size="60" maxLength="60"/></tsa:td>
							</tsa:tr>
							<tsa:tr>
								<tsa:td width="120px" valign="top" align="right" noWrap="nowrap"><b><tsa:label labelName="req-comment-text" defaultString="Comment Text"/>:</b>&nbsp;</tsa:td>
								<tsa:td><tsa:input type="textarea" name="edit_stdText" cols="60" rows="5"></tsa:input></tsa:td>
							</tsa:tr>
							</table>

							<div id="commentOptions" style="visibility: visible; display: block">
							<table border=0 cellpadding=1 cellspacing=0 width=100%>
							<tsa:tr>
								<tsa:td width="140px" align="right" noWrap="nowrap"><tsa:input type="checkbox" name="edit_cboxPrint" value="Y"/></tsa:td>
								<tsa:td><b><tsa:label labelName="req-print" defaultString="Print"/></b></tsa:td>
								<tsa:td width="65px" align="right" noWrap="nowrap"><tsa:input type="checkbox" name="edit_cboxBold" value="Y"/></tsa:td>
								<tsa:td><b><tsa:label labelName="req-bold" defaultString="Bold"/></b></tsa:td>
								<tsa:td width="120px" align="right" noWrap="nowrap"><b><tsa:label labelName="req-placement" defaultString="Placement"/>:</b>&nbsp;</tsa:td>
								<tsa:td>
									<select name="edit_commentPlace">
										<option value="A" selected><tsa:label labelName="req-after" defaultString="After"/></option>
										<option value="B"><tsa:label labelName="req-before" defaultString="Before"/></option>
									</select>
								</tsa:td>
							</tsa:tr>
							</table>
							</div>

							<br>

							<table border=0 cellpadding=1 cellspacing=0 width=100%>
							<tsa:tr>
<%		if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0 || s_req_status.compareTo(DocumentStatus.RFQ_PRICED) == 0 || s_req_status.compareTo(DocumentStatus.TEMPLATE) == 0) { %>
								<tsa:td width="50%" align="center"><a href="javascript: populateCmt(); void(0);"><img src="<%=contextPath%>/images/button_save.gif" border=0 class=button></a></tsa:td>
								<tsa:td width="50%" align="center"><a href="javascript: cancelEdit(); void(0);"><img src="<%=contextPath%>/images/button_return.gif" border=0 class=button></a></tsa:td>
<%		} else {%>
								<tsa:td width="100%" align="center"><a href="javascript: cancelEdit(); void(0);"><img src="<%=contextPath%>/images/button_return.gif" border=0 class=button></a></tsa:td>
<%		}%>
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

<br>

<div id="classicNavigation">
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tsa:tr>
	<tsa:td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/requests/inspection_detail.jsp', 'DocCommentUpdate;InspectionRetrieveDetail'); void(0);">Save</a></div></tsa:td>
	<tsa:td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/requests/inspection_detail.jsp', 'InspectionRetrieveDetail')'); void(0);">Return</a></div></tsa:td>
</tsa:tr>
</table>
</div>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var browser = browserCheck();
	var currentmethod = "DocCommentUpdate";
	var currentprocessmethod = "DocCommentUpdate/DocCommentRetrieveByLine";
	var displayOptions = "<%=s_display_options%>";
	var defaultPrint = "<%=s_default_print%>";

	myTable = document.getElementById("cmt_comments");
	totalRows = myTable.rows.length

	function thisLoad()
	{
		f_StartIt();
		setDisplay();
<%		if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) >= 0 && s_req_status.compareTo(DocumentStatus.RFQ_PRICED) != 0 && s_req_status.compareTo(DocumentStatus.TEMPLATE) != 0) { %>
			checkInputSettings();
<%	} %>
		checkDisplayOptions();
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>