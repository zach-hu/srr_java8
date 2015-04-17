<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/comments.js"></script>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

	ReceiptHeader receiptHeader = (ReceiptHeader) request.getAttribute("receiptHeader");

	String	s_ic_rec_header = (String)request.getAttribute("ReceiptHeader_icRecHeader");
	String	s_rec_number = (String)request.getAttribute("ReceiptHeader_receiptNumber");
	String	s_rec_type = (String)request.getAttribute("ReceiptHeader_receiptType");
	String	s_rec_status = (String)request.getAttribute("ReceiptHeader_receiptStatus");
	String	s_rec_fiscal_year = (String)request.getAttribute("ReceiptHeader_fiscalYear");
	if (HiltonUtility.isEmpty(s_rec_status)) {
		s_rec_status = DocumentStatus.RCV_INPROGRESS;
	}

	String	s_ic_po_header = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_icPoHeader"));
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
	String	s_current_process = "HEADER_NOTES";
	String	s_current_page = "/receipts/rec_notes.jsp";
	String	s_current_method = "DocCommentUpdate";
	String	s_current_process_method = "";

	BigDecimal	bd_zero = new BigDecimal(0);

	boolean allowEdit = false;
	if ((receiptMethod.equals("FinalizeReceipt") && s_rec_status.compareTo(DocumentStatus.RCV_PENDINGFINALIZATION) == 0) ||
		(receiptMethod.equals("ReceiveFromNothing") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0) ||
		(receiptMethod.equals("ReceiveByOrder") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0))
		allowEdit = true;
%>

<tsa:hidden name="ReceiptHeader_icRecHeader" value="<%=s_ic_rec_header%>"/>
<tsa:hidden name="ReceiptHeader_receiptNumber" value="<%=s_rec_number%>"/>
<tsa:hidden name="ReceiptHeader_receiptType" value="<%=s_rec_type%>"/>
<tsa:hidden name="ReceiptHeader_receiptStatus" value="<%=s_rec_status%>"/>
<tsa:hidden name="ReceiptHeader_fiscalYear" value="<%=s_rec_fiscal_year%>"/>
<tsa:hidden name="ReceiptLine_icRecHeader" value="<%=s_ic_rec_header%>"/>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_ic_rec_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="RCH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_ic_rec_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_ic_rec_header%>"/>
<tsa:hidden name="receiptMethod" value="<%=receiptMethod%>"/>
<tsa:hidden name="formType" value="REC"/>
<tsa:hidden name="allowBrowse" value="true"/>

<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="Default_referenceType" value="RCH"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "comments", "Comments")%></div>
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
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Order #:</b>&nbsp;<%=headerEncoder.encodeForHTML(s_po_number)%>
		<%	} %>
			</td>
			<td align=right><b>Receipt #:</b></td>
		<%	if (HiltonUtility.isEmpty(s_rec_number)) { %>
			<td width=100px>N/A</td>
		<%	} else { %>
			<td width=100px><%=headerEncoder.encodeForHTML(s_rec_number)%></td>
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
<%@ include file="/receipts/rec_info.jsp" %>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top align=center>
		<div id="commentsMoveRow" style="visibility: hidden; display: none">
			<table border="0" align="center" width="25px">
					<tr height="25px">
						<td align="center"><a href="javascript: //" onclick="javascript: move('U'); void(0);"><img src="<%=contextPath%>/images/up_arrows.gif" class="processOnImg" border="0" alt="Move Up"></a></td>
					</tr>
					<tr height="25px">
						<td align="center"><a href="javascript: //" onclick="javascript: move('D'); void(0);"><img src="<%=contextPath%>/images/down_arrows.gif" class="processOnImg" border="0" alt="Move Down"></a></td>
					</tr>
			</table>
		</div>
	</td>
	<td valign=top align=center>
		<div id="commentList" style="visibility: visible; display: block'">
			<table border=0 cellpadding=1 cellspacing=0 width=500px>
			<tr>
				<td class="browseHdrDk" width=8%>&nbsp;</td>
				<td class="browseHdrDk" width=16%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-commentid", "Comment ID")%></b></td>
				<td class="browseHdrDk" width=42%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-title", "Title")%></b></td>
				<td class="browseHdrDk" align=center width=7%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-print", "Print")%></b></td>
				<td class="browseHdrDk" align=center width=7%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-bold", "Bold")%></b></td>
				<td class="browseHdrDk" align=center width=15%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-placement", "Placement")%></b></td>
				<td class="browseHdrDk" width=5%>&nbsp;</td>
			</tr>
			<tr>
				<td colspan=8 vAlign=top>
					<div id="noCommentsMsg" style="visibility: hidden; display: none;">
					<table border=0 cellpadding=1 cellspacing=0 width=500px>
					<tr><td width=100% align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "nocomments", "No comments to display")%>.</td></tr>
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
			pageContext.setAttribute("i", i);
%>
					<tr>
						<td valign=top align=right width=8% id="cmt_num_<%=i%>">&nbsp;<%=i+1%>.<a href="javascript: //" onclick="javascript: highlightRow(<%= i %>);"><img src="<%=contextPath%>/images/next.gif" border="0" alt="Select this line"></a><tsa:hidden name="DocComment_commentOrder" value="<%=b_cmtOrder%>"/></td>
						<td valign=top width=16% id="cmt_id_<%=i%>">
							<div id="comment_id_<%=i%>">
								<%=docComment.getCommentId()%>
							</div>
						</td>
						<td valign=top width=42% id="cmt_edit">
							<div id="comment_edit_<%=i%>">
								<a href="javascript: editCmt(<%=i%>); void(0);" title="Click here to View/Modify details for this comment."><div id="cmt_title" name="cmt_title"><%=docComment.getCommentTitle()%></div></a>
								<tsa:hidden name="DocComment_commentTitle" id="commentTitle_${i}" value="<%=docComment.getCommentTitle()%>"/>
								<tsa:hidden name="DocComment_commentId" value="<%=docComment.getCommentId()%>"/>
								<tsa:hidden name="DocText_icText" value="<%=docComment.getIcText()%>"/>
								<tsa:hidden name="DocText_stdText" id="commentText_${i}" value="<%=s_std_text%>"/>
								<tsa:hidden name="DocComment_commentPublic" id="commentPublic_${i}" value="<%=docComment.getCommentPublic()%>"/>
							</div>
						</td>
						<td valign=top align=center width=7%>
							<div id="comment_print_<%=i%>">
								<input type="checkbox" name="cboxPrint_<%=i%>" <% if (docComment.getCommentPrint().indexOf("Y")>= 0){ %>CHECKED<%}%> value="Y" ONCLICK="setPrint(<%=i%>);">
								<tsa:hidden id="commentPrint_${i}" name="DocComment_commentPrint" value="<%=docComment.getCommentPrint()%>"/>
							</div>
						</td>
						<td valign=top align=center width=7%>
							<div id="comment_bold_<%=i%>">
								<input type="checkbox" name="cboxBold_<%=i%>" <% if (docComment.getCommentBold().indexOf("Y")>= 0){ %>CHECKED<%}%> value="Y" ONCLICK="setBold(<%=i%>);">
								<tsa:hidden id="commentBold_${i}" name="DocComment_commentBold" value="<%=docComment.getCommentBold()%>"/>
							</div>
						</td>
						<td valign=top align=center width=15%>
							<div id="comment_place_<%=i%>">
								<select name="DocComment_commentPlace" id="commentPlace_<%=i%>">
									<option value="A" <% if (docComment.getCommentPlace().indexOf("A")>= 0){ %>selected<%}%>>After</option>
									<option value="B" <% if (docComment.getCommentPlace().indexOf("B")>= 0){ %>selected<%}%>>Before</option>
								</select>
							</div>
						</td>
<%		if ( allowEdit )  { %>
						<td valign=top align=center width=5% id="cmt_del_<%=i%>"><a href="javascript: if (verifyAction('Are you sure you wish to delete Comment line <%=(i+1)%>?')) { deleteMe(<%=i%>); } "><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></td>
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
<%	if (allowEdit) {	%>
			<br>
			<table border=0 cellpadding=0 cellspacing=0 width=500px>
			<tr>
				<td nowrap align=center width=33%>
					<a href="javascript: addNew(); void(0)"><font class=reset><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addnewcomment", "Add new", s_rec_type)%></b></font></a>
				</td>
				<td nowrap align=center width=34%>
					<a href="javascript: addStandardComment(); void(0);"><font class=reset><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addstdcomment", "Add standard comment",s_rec_type)%></b></font></a>
				</td>
				<td nowrap align=center width=33%>
					<div id="deleteAllLink" style="visibility: visible; display: block;">
					<a href="javascript: deleteAll(); void(0)"><font class="reset"><b>Delete all</b></font></a>
					</div>
				</td>
			</tr>
			</table>
			<br>
<%	}	%>
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
								<td><input type="text" name="edit_commentTitle" value="" size=60 maxlength=60 onkeypress="disableEnterKey()" ></td>
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
<%	if ( allowEdit )  { %>
								<td width=50% align=center><div id="pxbutton"><a href="javascript: populateCmt(); void(0);">Save</a></div></td>
								<td width=50% align=center><div id="pxbutton"><a href="javascript: cancelEdit(); void(0);">Return</a></div></td>
<%	} else {%>
								<td width=100% align=center><div id="pxbutton"><a href="javascript: cancelEdit(); void(0);">Return</a></div></td>
<%	}%>
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
	<td rowspan=2 align=right valign=top><%@ include file="/receipts/rec_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<br>

<div id="classicNavigation">
<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if ( allowEdit ){ %>
	<td width=50% align=center><a href="javascript: doSubmit('/orders/po_summary.jsp', 'DocCommentUpdate;PoRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/orders/po_summary.jsp', 'PoRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
<%	} else {%>
	<td width=100% align=center><a href="javascript: doSubmit('/orders/po_summary.jsp', 'PoRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
<%	}%>
</tr>
</table>
<%	} %>
</div>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var contextPath = '<%= contextPath %>';
	var recNumber = "<%=headerEncoder.encodeForJavaScript(s_rec_number)%>";
	var fiscalyear = "<%=headerEncoder.encodeForJavaScript(s_rec_fiscal_year)%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var displayOptions = "";
	var defaultPrint = "";

	var browser = browserCheck();

	myTable = document.getElementById("cmt_comments");
	totalRows = myTable.rows.length;

	function thisLoad()
	{
		f_StartIt();
		setDisplay();
<%	if ( !allowEdit ) { %>
		checkInputSettings();
<%	} %>
		hasCommentOrder = true;
		checkNumComments();
	}
	function disableEnterKey()
	{
	   if (window.event.keyCode == 13) window.event.keyCode = 0;
	}

// End Hide script -->
</SCRIPT>