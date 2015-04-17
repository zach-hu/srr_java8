<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/comments.js"></script>
<%
	Encoder encoder = DefaultEncoder.getInstance();
	String	s_ivc_ic_header = (String) request.getAttribute("InvoiceHeader_icIvcHeader");
	String	s_ivc_number = (String) request.getAttribute("InvoiceHeader_invoiceNumber");
	String	s_ivc_status = (String) request.getAttribute("InvoiceHeader_status");

	if (s_ivc_number == null)
	{
		s_ivc_number = (String) request.getAttribute("formNumber");
	}
	if (s_ivc_ic_header == null)
	{
		s_ivc_ic_header = (String) request.getAttribute("DocComment_icHeader");
	}
	if (s_ivc_status == null)
	{
		s_ivc_status = (String) request.getAttribute("formStatus");
	}

	String	s_current_process = "HEADER_NOTES";
	String	s_current_page = "/invoice/iv_notes.jsp";
	String	s_current_method = "DocCommentUpdate";
	String	s_current_process_method = "";
%>

<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=s_ivc_ic_header%>"/>
<tsa:hidden name="InvoiceLine_icIvcHeader" value="<%=s_ivc_ic_header%>"/>
<tsa:hidden name="InvoiceHeader_invoiceNumber" value="<%=s_ivc_number%>"/>
<tsa:hidden name="InvoiceHeader_status" value="${invoiceHeader.status}"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_ivc_ic_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_ivc_ic_header%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_ivc_ic_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Default_referenceType" value="IVH"/>
<tsa:hidden name="formType" value="IVH"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="formNumber" value="<%=s_ivc_number%>"/>
<tsa:hidden name="formStatus" value="${invoiceHeader.status}"/>
<tsa:hidden name="currentPage" value="/invoice/iv_notes.jsp"/>
<tsa:hidden name="duplicateNumberFailurePage" value="/invoice/iv_notes.jsp"/>

<%
	if (HiltonUtility.isEmpty(s_ivc_number))
	{
		s_ivc_number = "N/A";
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>
				<table border=0 cellspacing=0 cellpadding=2>
				<tr><td class=hdr12 nowrap>Comments</td></tr>
				</table>
				</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Invoice #:</b></td>
			<td width=125px><%=encoder.encodeForHTML(s_ivc_number)%></td>
		</tr>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=125px>
				<% try {
					int i = Integer.parseInt(s_ivc_status);
					if (s_ivc_status.length() < 5) {%>
						<%=DocumentStatus.toString(s_ivc_status, oid)%>
					<% } %>
				<% } catch (Exception e) {} %>
			</td>
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
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top align=center>
		<div id="commentList" style="visibility: visible; display: block'">
			<table border=0 cellpadding=1 cellspacing=0 width=500px>
			<tr>
				<td width=4%>&nbsp;</td>
				<td width=20%><b>Comment ID</b></td>
				<td width=42%><b>Title</b></td>
				<td align=center width=7%><b>Print</b></td>
				<td align=center width=7%><b>Bold</b></td>
				<td align=center width=15%><b>Placement</b></td>
				<td width=5%>&nbsp;</td>
			</tr>
			<tr>
				<td colspan=8 vAlign=top>
					<div id="noCommentsMsg" style="visibility: hidden; display: none;">
					<table border=0 cellpadding=1 cellspacing=0 width=500px>
					<tr><td width=100% align=center>No comments to display.</td></tr>
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
			}
			DocCommentPK docCommentPK = (DocCommentPK) docComment.getComp_id();
			BigDecimal b_cmtOrder = docCommentPK.getCommentOrder();
%>
					<tr>
						<td align=right width=4% id="cmt_num_<%=i%>">&nbsp;<%=i+1%>.&nbsp;<tsa:hidden name="DocComment_commentOrder" value="<%=b_cmtOrder%>"/></td>
						<td width=20% id="cmt_id_<%=i%>"><%=docComment.getCommentId()%></td>
						<td width=42% id="cmt_edit"><a href="javascript: editCmt(<%=i%>); void(0);" title="Click here to View/Modify details for this comment."><div id="cmt_title" name="cmt_title"><%=docComment.getCommentTitle()%></div></a>
							<tsa:hidden name="DocComment_commentTitle" id="commentTitle_<%=i%>" value="<%=docComment.getCommentTitle()%>"/>
							<tsa:hidden name="DocComment_commentId" value="<%=docComment.getCommentId()%>"/>
							<tsa:hidden name="DocText_icText" value="<%=docComment.getIcText()%>"/>
							<tsa:hidden name="DocText_stdText" id="commentText_<%=i%>" value="<%=s_std_text%>"/>
							<tsa:hidden name="DocComment_commentPublic" id="commentPublic_<%=i%>" value="<%=docComment.getCommentPublic()%>"/>
						</td>
						<td align=center width=7%><input type="checkbox" id="cboxPrint_<%=i%>" name="cboxPrint_<%=i%>" <% if (docComment.getCommentPrint().indexOf("Y")>= 0){%>CHECKED<%}%> value="Y" ONCLICK="setPrint(<%=i%>);">
							<tsa:hidden id="commentPrint_<%=i%>" name="DocComment_commentPrint" value="<%=docComment.getCommentPrint()%>"/>
						</td>
						<td align=center width=7%><input type="checkbox" id="cboxBold_<%=i%>" name="cboxBold_<%=i%>" <% if (docComment.getCommentBold().indexOf("Y")>= 0){%>CHECKED<%}%> value="Y" ONCLICK="setBold(<%=i%>);">
							<tsa:hidden id="commentBold_<%=i%>" name="DocComment_commentBold" value="<%=docComment.getCommentBold()%>"/>
						</td>
						<td align=center width=15%>
							<select name="DocComment_commentPlace" id="commentPlace_<%=i%>">
								<option value="A" <% if (docComment.getCommentPlace().indexOf("A")>= 0){ %>selected<%}%>>After</option>
								<option value="B" <% if (docComment.getCommentPlace().indexOf("B")>= 0){ %>selected<%}%>>Before</option>
							</select>
						</td>
<%		if (s_ivc_status.compareTo(DocumentStatus.IVC_INPROGRESS) == 0 || s_ivc_status.compareTo(DocumentStatus.IVC_RECALLED) == 0) { %>
						<td align=center width=5% id="cmt_del_<%=i%>"><a href="javascript: if (verifyAction('Are you sure you wish to delete Comment line <%=(i+1)%>?')) { deleteMe(<%=i%>); } "><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></td>
<%		} else if ( s_ivc_status.compareTo(DocumentStatus.IVC_REJECTED) == 0 ) { %>
						<td align=center width=5% id="cmt_del_<%=i%>"><a href="javascript: if (verifyAction('Are you sure you wish to delete Comment line <%=(i+1)%>?')) { deleteMe(<%=i%>); } "><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></td>
<%		} else { %>
						<td width=5%>&nbsp;</td>
<%		} %>
					</tr>
<%	}
	} %>
					</table>
				</td>
			</tr>
			</table>
<%		if (s_ivc_status.compareTo(DocumentStatus.IVC_INPROGRESS) == 0 || s_ivc_status.compareTo(DocumentStatus.IVC_RECALLED) == 0) { %>
			<br>
			<table border=0 cellpadding=0 cellspacing=0 width=500px>
			<tr>
				<td nowrap align=center width=33%>
					<a href="javascript: addNew(); void(0)"><font class=reset><b>Add new</b></font></a>
				</td>
				<td nowrap align=center width=34%>
					<A href="javascript: addStandardComment(); void(0);"><font class=reset><b>Add standard comment</b></font></a>
				</td>
				<td nowrap align=center width=33%>
					<div id="deleteAllLink" style="visibility: visible; display: block;">
					<a href="javascript: deleteAll(); void(0)"><font class="reset"><b>Delete all</b></font></a>
					</div>
				</td>
			</tr>
			</table>
<%		} else if ( s_ivc_status.compareTo(DocumentStatus.IVC_REJECTED) == 0 ) { %>
			<br>
			<table border=0 cellpadding=0 cellspacing=0 width=500px>
			<tr>
				<td nowrap align=center width=33%>
					<a href="javascript: addNew(); void(0)"><font class=reset><b>Add new</b></font></a>
				</td>
				<td nowrap align=center width=34%>
					<A href="javascript: addStandardComment(); void(0);"><font class=reset><b>Add standard comment</b></font></a>
				</td>
				<td nowrap align=center width=33%>
					<div id="deleteAllLink" style="visibility: visible; display: block;">
					<a href="javascript: deleteAll(); void(0)"><font class="reset"><b>Delete all</b></font></a>
					</div>
				</td>
			</tr>
			</table>
<%	}%>
		</div>
		<div id="commentEdit" style="visibility: hidden; display: none">
			<table border=0 cellpadding=1 cellspacing=0 width=500px>
			<tr>
				<td valign=top>
					<table border=0 cellspacing=0 cellpadding=0 height=100%>
					<tr>
						<td>
							<table border=0 cellpadding=0 cellspacing=0 width=250px align=center>
							<tr>
								<td width=120px align=right nowrap height=18px><b>Comment Id:</b>&nbsp;</td>
								<td id=editCommentId>&nbsp;</td>
							</tr>
							<tr>
								<td width=120px align=right nowrap><b>Title:</b>&nbsp;</td>
								<td><input type="text" name="edit_commentTitle" value="" size=60 maxlength=60></td>
							</tr>
							<tr>
								<td width=120px valign=top align=right nowrap><b>Comment Text:</b>&nbsp;</td>
								<td><textarea name="edit_stdText" cols=59 rows=5></textarea></td>
							</tr>
							</table>
							<br>
							<table border=0 cellpadding=1 cellspacing=0 width=100%>
							<tr>
								<td width=140px align=right nowrap><input type=checkbox name="edit_cboxPrint" value="Y"></td>
								<td><b>Print</b></td>
								<td width=65px align=right nowrap><input type=checkbox name="edit_cboxBold" value="Y"></td>
								<td><b>Bold</b></td>
								<td width=120px align=right nowrap><b>Placement:</b>&nbsp;</td>
								<td>
									<select name="edit_commentPlace">
										<option value="A" selected>After</option>
										<option value="B">Before</option>
									</select>
								</td>
							</tr>
							</table>

							<br>

							<table border=0 cellpadding=1 cellspacing=0 width=100%>
							<tr>
<%		if (s_ivc_status.compareTo(DocumentStatus.IVC_INPROGRESS) == 0 || s_ivc_status.compareTo(DocumentStatus.IVC_RECALLED) == 0) { %>
								<td width=50% align=center><div id="pxbutton"><a href="javascript: populateCmt(); void(0);">Save</a></div></td>
								<td width=50% align=center><div id="pxbutton"><a href="javascript: cancelEdit(); void(0);">Return</a></div></td>
<%		} else if ( s_ivc_status.compareTo(DocumentStatus.IVC_REJECTED) == 0) { %>
								<td width=50% align=center><a href="javascript: populateCmt(); void(0);">Save</a></div></td>
								<td width=50% align=center><div id="pxbutton"><a href="javascript: cancelEdit(); void(0);">Return</a></div></td>
<%		} else {%>
								<td width=100% align=center><div id="pxbutton"><a href="javascript: cancelEdit(); void(0);">Return</a></div></td>
<%		}%>
							</tr>
							</table>
						</td>
					</tr>
					</table>
				</td>
			</tr>
			</table>
		</div>
	</td>
<%	if (s_view.equalsIgnoreCase("WIZARD")) { %>
	<td rowspan=2 align=right valign=top><%@ include file="/invoice/iv_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<br>
<br>

<div id="classicNavigation">
<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%		if (s_ivc_status.compareTo(DocumentStatus.IVC_INPROGRESS) == 0 || s_ivc_status.compareTo(DocumentStatus.IVC_RECALLED) == 0) { %>
	<td width=50% align=center><a href="javascript: doSubmit('/invoice/iv_summary.jsp', 'DocCommentUpdate;InvoiceRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/invoice/iv_summary.jsp', 'InvoiceRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
<%		} else if ( s_ivc_status.compareTo(DocumentStatus.IVC_REJECTED) == 0) { %>
	<td width=50% align=center><a href="javascript: doSubmit('/invoice/iv_summary.jsp', 'DocCommentUpdate;InvoiceRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/invoice/iv_summary.jsp', 'InvoiceRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
<%		} else {%>
	<td width=100% align=center><a href="javascript: doSubmit('/invoice/iv_summary.jsp', 'InvoiceRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
<%		}%>
</tr>
</table>
<%	} %>
</div>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var invoicenumber = "<%=encoder.encodeForJavaScript(s_ivc_number)%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var displayOptions = "";
	var defaultPrint = "";

	var browser = browserCheck();

	myTable = document.getElementById("cmt_comments");
	totalRows = myTable.rows.length

	function thisLoad()
	{
		f_StartIt();
		setDisplay();
<%		if (s_ivc_status.compareTo(DocumentStatus.IVC_RECALLED) > 0) { %>
	<%	if (!s_ivc_status.equals(DocumentStatus.IVC_REJECTED)) { %>
			checkInputSettings();
	<%	} %>
<%	} %>
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>