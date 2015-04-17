<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/comments.js"></script>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_po_admin = propertiesManager.getProperty("PO OPTIONS", "AdminChanges", "N");
	boolean bAdminChanges = false;
	if (s_po_admin.equalsIgnoreCase("Y") && role.getAccessRights("PO") == 99)
	{
		bAdminChanges = true;
	}
	InvItem invItem = (InvItem) request.getAttribute("invItem");
	String	s_item_number = (String) request.getAttribute("InvItem_itemNumber");
	String	s_po_number = (String) request.getAttribute("InvItem_poNumber");
	String	s_inv_type = (String) request.getAttribute("InvItem_itemType");
	String	inv_status = (String) request.getAttribute("InvItem_status");
	String	s_buyer_code = HiltonUtility.ckNull((String) request.getAttribute("invItem_buyerCode"));
	String itemAction = (String)request.getAttribute("itemAction");
	BigDecimal	bd_zero = new BigDecimal(0);

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
		s_item_number = (String) request.getAttribute("DocComment_icHeader");
	}
	if (inv_status == null)
	{
		inv_status = (String) request.getAttribute("formStatus");
	}

	boolean bAllowEdit = true;
	if ( role.getAccessRights("PO") < 2 || (!bAdminChanges && inv_status.compareTo(DocumentStatus.PO_REJECTED) > 0)  || (inv_status.compareTo(DocumentStatus.CANCELLED) >= 0))
	{
		bAllowEdit = false;
	}
	if (oid.equalsIgnoreCase("akdoc") && !user.isAnAdminBuyer() && !user.getUserId().equals(s_buyer_code))
	{
		bAllowEdit = false;
	}

	String	s_current_process = "HEADER_NOTES";
	String	s_current_page = "/inventory/inv_notes.jsp";
	String	s_current_method = "DocCommentUpdate";
	String	s_current_process_method = "";
%>

<tsa:hidden name="InvItem_itemNumber" value="<%=s_item_number%>"/>
<tsa:hidden name="InvItem_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="InvItem_status" value="<%=inv_status%>"/>
<tsa:hidden name="InvItem_itemType" value="<%=s_inv_type%>"/>
<tsa:hidden name="InvItem_buyerCode" value="<%=s_buyer_code%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_item_number%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="INV"/>
<tsa:hidden name="DocComment_icHeader" value="<%=invItem.getIcText()%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_item_number%>"/>
<tsa:hidden name="DocAttachment_icLine" value="0"/>

<tsa:hidden name="Default_referenceType" value="INV"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="formNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="formStatus" value="<%=inv_status%>"/>
<tsa:hidden name="Schedule_icHeader" value="<%=s_item_number%>"/>
<tsa:hidden name="Schedule_documentType" value="INV"/>
<tsa:hidden name="formtype" value="INV"/>
<tsa:hidden name="itemAction" value="<%=itemAction%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="comments" defaultString="Comments" /></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
</tr>
</table>

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
	<td valign=top align=center width=100%>
		<div id="commentList" style="visibility: visible; display: block'">
			<table border=0 cellpadding=1 cellspacing=0 width=80%>
			<tr>
				<td class="browseHdrDk" width=8%>&nbsp;</td>
				<td class="browseHdrDk" width=16%><b><tsa:label labelName="po-commentid" defaultString="Comment ID" /></b></td>
				<td class="browseHdrDk" width=42%><b><tsa:label labelName="po-title" defaultString="Title" /></b></td>
				<td class="browseHdrDk" align=center width=7%><b><tsa:label labelName="po-print" defaultString="Print" /></b></td>
				<td class="browseHdrDk" align=center width=7%><b><tsa:label labelName="po-bold" defaultString="Bold" /></b></td>
				<td class="browseHdrDk" align=center width=15%><b><tsa:label labelName="po-placement" defaultString="Placement" /></b></td>
				<td class="browseHdrDk" width=5%>&nbsp;</td>
			</tr>
			<tr>
				<td colspan=8 vAlign=top>
					<div id="noCommentsMsg" style="visibility: hidden; display: none;">
					<table border=0 cellpadding=1 cellspacing=0 width=100%>
					<tr><td width=100% align=center><tsa:label labelName="nocomments" defaultString="No comments to display" />.</td></tr>
					</table>
					</div>
					<table id="cmt_comments" border=0 cellpadding=1 cellspacing=0 width=100%>
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
					<tr>
						<td valign=top align=right width=8% id="cmt_num_<%=i%>">&nbsp;<%=i+1%>.<a href="javascript: //" onclick="javascript: highlightRow(<%= i %>);"><img src="<%=contextPath%>/images/next.gif" border="0" alt="Select this line"></a><tsa:hidden name="DocComment_commentOrder" value="<%=b_cmtOrder%>"/></td>
						<td valign=top width=16% id="cmt_id_<%=i%>">
							<div id="comment_id_<%=i%>">
								<%=docComment.getCommentId()%>
							</div>
						</td>
						<td valign=top width=42% id="cmt_edit">
							<div id="comment_edit_<%=i%>">
								<a href="javascript: editCmt(<%=i%>); hideSaveThem(); void(0);" title="Click here to View/Modify details for this comment."><div id="cmt_title" name="cmt_title"><%=docComment.getCommentTitle()%></div></a>
								<tsa:hidden name="DocComment_commentTitle" id="<%= \"commentTitle_\" + i%>" value="<%=docComment.getCommentTitle()%>"/>
								<tsa:hidden name="DocComment_commentId" value="<%=docComment.getCommentId()%>"/>
								<tsa:hidden name="DocText_icText" value="<%=docComment.getIcText()%>"/>
								<tsa:hidden name="DocText_stdText" id="<%= \"commentText_\" + i%>" value="<%=s_std_text%>"/>
								<tsa:hidden name="DocComment_commentPublic" id="<%= \"commentPublic_\" + i%>" value="<%=docComment.getCommentPublic()%>"/>
							</div>
						</td>
						<td valign=top align=center width=7%>
							<div id="comment_print_<%=i%>">
								<input type="checkbox" name="cboxPrint_<%=i%>" <% if (docComment.getCommentPrint().indexOf("Y")>= 0){ %>CHECKED<%}%> value="Y" ONCLICK="setPrint(<%=i%>);">
								<tsa:hidden id="<%= \"commentPrint_\" + i%>" name="DocComment_commentPrint" value="<%=docComment.getCommentPrint()%>"/>
							</div>
						</td>
						<td valign=top align=center width=7%>
							<div id="comment_bold_<%=i%>">
								<input type="checkbox" name="cboxBold_<%=i%>" <% if (docComment.getCommentBold().indexOf("Y")>= 0){ %>CHECKED<%}%> value="Y" ONCLICK="setBold(<%=i%>);">
								<tsa:hidden id="<%= \"commentBold_\" + i%>" name="DocComment_commentBold" value="<%=docComment.getCommentBold()%>"/>
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
<%		if ( bAllowEdit )  { %>
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
<%	if (bAllowEdit) {	%>
			<br>
			<table border=0 cellpadding=0 cellspacing=0 width=100%>
			<tr>
				<td nowrap align=center width=33%>
					<a href="javascript: addNew();hideSaveThem(); void(0)"><font class=reset><b><tsa:label labelName="addnewcomment" defaultString="Add new" /></b></font></a>
				</td>
				<td nowrap align=center width=34%>
					<a href="javascript: addStandardComment(); void(0);"><font class=reset><b><tsa:label labelName="addstdcomment" defaultString="Add standard comment" /></b></font></a>
				</td>
				<td nowrap align=center width=33%>
					<div id="deleteAllLink" style="visibility: visible; display: block;">
					<a href="javascript: deleteAll(); void(0)"><font class="reset"><b><tsa:label labelName="delall" defaultString="Delete all" /></b></font></a>
					</div>
				</td>
			</tr>
			</table>
			<br>
<%	}	%>
		</div>

		<%	if (s_inv_type.equalsIgnoreCase("CT") && oid.equalsIgnoreCase("bsc04p")) {	%>
		<table align="center">
			<tr>
				<td class="red" align="center">
					<br><br><tsa:label labelName="commentMessage" defaultString="Comments should always be considered \"internal\" comments and can relate to either the documentation or the supplier." />
				</td>
			</tr>
		</table>
		<%	}	%>

		<div id="commentEdit" style="visibility: hidden; display: none">
			<table border=0 cellpadding=1 cellspacing=0 width=80%>
			<tr>
				<td valign=top>
					<table border=0 cellspacing=0 cellpadding=0 height=100%>
					<tr>
						<td>
							<table border=0 cellpadding=0 cellspacing=0 width=100% align=center>
							<tr>
								<td width=120px align=right nowrap height=18px><b><tsa:label labelName="commentId" defaultString="Comment Id" />:</b>&nbsp;</td>
								<td id=editCommentId>&nbsp;</td>
							</tr>
							<tr>
								<td width=120px align=right nowrap><b><tsa:label labelName="title" defaultString="Title" />:</b>&nbsp;</td>
								<td><tsa:input type="text" name="edit_commentTitle" value="" size="60" maxLength="60" onkeypress="disableEnterKey()" /></td>
							</tr>
							<tr>
								<td width=120px valign=top align=right nowrap><b><tsa:label labelName="commentText" defaultString="Comment Text" />:</b>&nbsp;</td>
								<td><tsa:input type="textarea" name="edit_stdText" cols="59" rows="5"></tsa:input></td>
							</tr>
							</table>
							<br>
							<table border=0 cellpadding=1 cellspacing=0 width=100%>
							<tr>
								<td width=140px align=right nowrap><tsa:input type="checkbox" name="edit_cboxPrint" value="Y" /></td>
								<td><b><tsa:label labelName="print" defaultString="Print" /></b></td>
								<td width=65px align=right nowrap><tsa:input type="checkbox" name="edit_cboxBold" value="Y" /></td>
								<td><b>Bold</b></td>
								<td width=120px align=right nowrap><b><tsa:label labelName="placement" defaultString="Placement" />:</b>&nbsp;</td>
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
<%	if ( bAllowEdit )  { %>
								<td width=50% align=center><div id="pxbutton"><a href="javascript: populateCmt();showSaveThem(); void(0);"><tsa:label labelName="save" defaultString="Save" /></a></div></td>
								<td width=50% align=center><div id="pxbutton"><a href="javascript: cancelEdit();showSaveThem(); void(0);"><tsa:label labelName="return" defaultString="Return" /></a></div></td>
<%	} else {%>
								<td width=100% align=center><div id="pxbutton"><a href="javascript: cancelEdit();showSaveThem(); void(0);"><tsa:label labelName="return" defaultString="Return" /></a></div></td>
<%	}%>
							</tr>
							</table>

						</td>
					</tr>
					</table>
				</td>
			</tr>
				<br>

			</table>
		</div>
		<table border=0 cellpadding=1 cellspacing=0 width=100%>
			<tr id="SaveThem" name="SaveThem">
				<td align=center><div id="pxbutton"><a href="javascript: doSubmit('/inventory/inv_item.jsp', 'DocCommentUpdate;InvItemRetrieveById'); void(0);"><tsa:label labelName="Save" defaultString="Save" /></a></div></td>
				<td align=center><div id="pxbutton"><a href="javascript: doSubmit('/inventory/inv_item.jsp', 'InvItemRetrieveById'); void(0);"><tsa:label labelName="return" defaultString="Return" /></a></div></td>
			</tr>
		</table>
	</td>
</tr>

<div id="classicNavigation">
<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if ( bAllowEdit ){ %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/inventory/inv_item.jsp', 'DocCommentUpdate;InvItemRetrieveById'); void(0);"><tsa:label labelName="save" defaultString="Save" /></a>/div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/inventory/inv_item.jsp', 'InvItemRetrieveById'); void(0);"><tsa:label labelName="return" defaultString="Return" /></a></div></td>
<%	} else {%>
	<td width=100% align=center><div id="pxbutton"><a href="javascript: doSubmit('/inventory/inv_item.jsp', 'InvItemRetrieveById'); void(0);"><tsa:label labelName="return" defaultString="Return" /></a></div></td>
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
	var ponumber = "<%=s_po_number%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var displayOptions = "";
	var defaultPrint = "";

	var browser = browserCheck();

	myTable = document.getElementById("cmt_comments");
	totalRows = myTable.rows.length;

	function hideSaveThem(){
		hideArea("SaveThem");
	}


	function showSaveThem(){
		displayArea("SaveThem");
	}

	function thisLoad()
	{
		f_StartIt();
		setDisplay();
<%	if ( !bAllowEdit ) { %>
			checkInputSettings();
<%	} %>
		hasCommentOrder = true;
		checkNumComments();
	}
	function disableEnterKey()
	{
	   if (window.event.keyCode == 13) window.event.keyCode = 0;
	}

	function addNew() {
		frm.deleteall.value = false;

		var selectedRowHTML;
		var noComments = document.getElementById("noCommentsMsg");
		noComments.style.visibility = "hidden";
		noComments.style.display = "none";

		var deleteAllLink = document.getElementById("deleteAllLink");
		deleteAllLink.style.visibility = "visible";
		deleteAllLink.style.display = "block";

		var classicNav = document.getElementById("classicNavigation");
		classicNav.style.visibility = "visible";
		classicNav.style.display = "block";

		count = myTable.rows.length;

		myRow = createRow(myTable);
		count++;

		selectedRowHTML = (hasCommentOrder) ? "<a href=\"javascript: //\" onclick=\"javascript: highlightRow(" + (count - 1) + ");\"><img src=\"" + contextPath + "/images/next.gif\" border=\"0\" alt=\"Select this line\"></a>" : "";

		myCell = createCell(myRow);
		myCell.id = "cmt_num_" + (count-1);
		myCell.align = "right";
		myCell.width = ((hasCommentOrder) ? "8" : "4") + "%";
		myCell.innerHTML = "&nbsp;" + count + ".&nbsp;" + selectedRowHTML +
			"<input type=hidden name=\"DocComment_commentOrder\" value=\"" + (count) + "\">";

		myCell = createCell(myRow);
		myCell.id = "cmt_id_" + (count-1);
		myCell.width = ((hasCommentOrder) ? "16" : "20") + "%";
		myCell.innerHTML = "<div id=\"comment_id_" + (count - 1) + "\">&nbsp;</div>";

		myCell = createCell(myRow);
		myCell.id = "cmt_edit";
		myCell.width = "42%";
		myCell.innerHTML = "<div id=\"comment_edit_" + (count - 1) + "\">" +
			"<a href=\"javascript: editCmt(" + (count - 1) + "); hideSaveThem(); void(0);\"><div id=\"cmt_title\" name=\"cmt_title\"></div></a>" +
			"<input type=hidden name=\"DocComment_commentTitle\" id=\"commentTitle_" + (count-1) + "\" maxlength=\"60\" value=\"\">" +
			"<input type=hidden name=\"DocComment_commentId\" maxlength=15 value=\"\">" +
			"<input type=hidden name=\"DocText_icText\" value=\"\">" +
			"<input type=hidden name=\"DocText_stdText\" id=\"commentText_" + (count-1) + "\" value=\"\">" +
			"<input type=hidden name=\"DocComment_commentPublic\" id=\"commentPublic_" + (count - 1) + "\" value=\"\">" +
			"</div>";

		myCell = createCell(myRow);
		myCell.id = "cmt_print";
		myCell.align = "center";
		myCell.width = "7%";
		myCell.innerHTML = "<div id=\"comment_print_" + (count - 1) + "\">" +
			"<input type=\"checkbox\" id=\"cboxPrint_" + (count-1) + "\" name=\"cboxPrint_" + (count-1) + "\" value=\"Y\" ONCLICK=\"setPrint(" + (count-1) + ");\">" +
			"<input type=hidden name=\"DocComment_commentPrint\" id=\"commentPrint_" + (count-1) + "\" value=\"N\">" +
			"</div>";

		myCell = createCell(myRow);
		myCell.id = "cmt_bold";
		myCell.align = "center";
		myCell.width = "7%";
		myCell.innerHTML = "<div id=\"comment_bold_" + (count - 1) + "\">" +
			"<input type=\"checkbox\" id=\"cboxBold_" + (count-1) + "\" name=\"cboxBold_" + (count-1) + "\" value=\"Y\" ONCLICK=\"setBold(" + (count-1) + ");\">" +
			"<input type=hidden name=\"DocComment_commentBold\" id=\"commentBold_" + (count-1) + "\" value=\"N\">" +
			"</div>";

		myCell = createCell(myRow);
		myCell.id = "cmt_place";
		myCell.align = "center";
		myCell.width = "15%";
		myCell.innerHTML = "<div id=\"comment_place_" + (count - 1) + "\">" +
			"<select name=\"DocComment_commentPlace\" id=\"commentPlace_" + (count-1) + "\"><option value=\"A\" selected>After</option><option value=\"B\">Before</option></select>" +
			"</div>";

		myCell = createCell(myRow);
		myCell.id = "cmt_del_" + count;
		myCell.align = "center";
		myCell.width = "5%";
		myCell.innerHTML = "<a href=\"javascript: if (confirm('Are you sure you wish to delete Comment line " + count  + "?')) { deleteMe(" + (count-1) + "); } void(0);\"><img src=\"" + contextPath + "/images/delete.gif\" alt=\"Delete\" border=\"0\"></a>";

		totalRows++

		editRow = count -1;
		editCmt(count-1);

		if (frm.edit_commentTitle) {
			frm.edit_commentTitle.focus();
		}

		if (hasCommentOrder) {
			hideCommentsMoveRow();
		}
	}

// End Hide script -->
</SCRIPT>