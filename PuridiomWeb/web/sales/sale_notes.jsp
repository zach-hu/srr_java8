<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/comments.js"></script>

<%
	String s_icSaleHeader = (String) request.getAttribute("SaleHeader_icSaleHeader");
	String s_saleNumber = (String) request.getAttribute("SaleHeader_saleNumber");
	String s_amendment = (String) request.getAttribute("SaleHeader_amendment");
	String s_status = (String) request.getAttribute("SaleHeader_status");
	String s_fiscalYear = (String) request.getAttribute("SaleHeader_fiscalYear");
	
	if (s_saleNumber == null) {
		s_saleNumber = (String) request.getAttribute("formNumber");
	}
	if (s_amendment == null) {
		s_amendment = "0000";
	}
	if (s_icSaleHeader == null) {
		s_icSaleHeader = (String) request.getAttribute("DocComment_icHeader");
	}
	if (s_status == null) {
		s_status = (String) request.getAttribute("formStatus");
	}	
	String	s_current_process = "HEADER_NOTES";
	String	s_current_page = "/sales/sale_notes.jsp";
	String	s_current_method = "DocCommentUpdate";
	String	s_current_process_method = "";
%>

<%@ include file="/sales/sale_hidden_fields.jsp" %>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Solicitation Notes</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<%@ include file="/sales/sale_status_title.jsp" %>
		<table cellpadding="0" cellspacing="0" border=0>
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
		<div id="commentList" style="visibility: visible; display: block'">
			<table border=0 cellpadding=1 cellspacing=0 width=500px>
			<tr>
				<td width=4%>&nbsp;</td>
				<td width=20%><b>Comment ID</b></td>
				<td width=38%><b>Title</b></td>
				<td align=center width=6%><b>Post</b></td>
				<td align=center width=7%><b>Print</b></td>
				<td align=center width=7%><b>Bold</b></td>
				<td align=center width=14%><b>Placement</b></td>
				<td width=4%>&nbsp;</td>
			</tr>
			<tr>
				<td colspan=8 vAlign=top>
					<div id="noCommentsMsg" style="visibility: hidden; display: none;">
					<table border=0 cellpadding=1 cellspacing=0 width=500px>
					<tr><td width=100% align=center>No notes to display.</td></tr>
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
						<td valign=top align=right width=4% id="cmt_num_<%=i%>">&nbsp;<%=i+1%>.<tsa:hidden name="DocComment_commentOrder" value="<%=b_cmtOrder%>"/></td>
						<td valign=top width=20% id="cmt_id_<%=i%>"><%=docComment.getCommentId()%></td>
						<td valign=top width=38% id="cmt_edit"><a href="javascript: editCmt(<%=i%>); void(0);" title="Click here to View/Modify details for this comment."><div id="cmt_title"><%=docComment.getCommentTitle()%></div></a>
							<tsa:hidden name="DocComment_commentTitle" id="commentTitle_<%=i%>" value="<%=docComment.getCommentTitle()%>"/>
							<tsa:hidden name="DocComment_commentId" maxlength=15 value="<%=docComment.getCommentId()%>"/>
							<tsa:hidden name="DocText_icText" value="<%=docComment.getIcText()%>"/>
							<tsa:hidden name="DocText_stdText" id="commentText_<%=i%>" value="<%=s_std_text%>"/>
							<tsa:hidden name="DocComment_commentPublic" id="commentPublic_<%=i%>" value="<%=docComment.getCommentPublic()%>"/>
						</td>
						<td valign=top align=center width=6%><input type="checkbox" name="cboxPost_<%=i%>" <% if (docComment.getCommentWebpost().indexOf("Y")>= 0){ %>CHECKED<%}%> value="Y" ONCLICK="setPost(<%=i%>);">
							<tsa:hidden id="commentPost_<%=i%>" name="DocComment_commentWebpost" value="<%=docComment.getCommentWebpost()%>"/>
						</td>
						<td valign=top align=center width=7%><input type="checkbox" name="cboxPrint_<%=i%>" <% if (docComment.getCommentPrint().indexOf("Y")>= 0){ %>CHECKED<%}%> value="Y" ONCLICK="setPrint(<%=i%>);">
							<tsa:hidden id="commentPrint_<%=i%>" name="DocComment_commentPrint" value="<%=docComment.getCommentPrint()%>"/>
						</td>
						<td valign=top align=center width=7%><input type="checkbox" name="cboxBold_<%=i%>" <% if (docComment.getCommentBold().indexOf("Y")>= 0){ %>CHECKED<%}%> value="Y" ONCLICK="setBold(<%=i%>);">
							<tsa:hidden id="commentBold_<%=i%>" name="DocComment_commentBold" value="<%=docComment.getCommentBold()%>"/>
						</td>
						<td valign=top align=center width=14%>
							<select name="DocComment_commentPlace" id="commentPlace_<%=i%>">
								<option value="A" <% if (docComment.getCommentPlace().indexOf("A")>= 0){ %>selected<%}%>>After</option>
								<option value="B" <% if (docComment.getCommentPlace().indexOf("B")>= 0){ %>selected<%}%>>Before</option>
							</select>
						</td>
<%		if (s_status.compareTo(DocumentStatus.SALE_BIDS_PENDING) < 0 || s_status.compareTo(DocumentStatus.SALE_OPENAMENDMENT) == 0) { %>
						<td valign=top align=center width=4% id="cmt_del_<%=i%>"><a href="javascript: if (verifyAction('Are you sure you wish to delete Comment line <%=(i+1)%>?')) { deleteMe(<%=i%>); } "><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></td>
<%		} else { %>
						<td valign=top width=4%>&nbsp;</td>
<%		} %>
					</tr>
<%	}
	} %>
					</table>
				</td>
			</tr>
			</table>
<%		if (s_status.compareTo(DocumentStatus.SALE_BIDS_PENDING) < 0 || s_status.compareTo(DocumentStatus.SALE_OPENAMENDMENT) == 0) { %>
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
								<td><textarea name="edit_stdText" cols=59 rows=10></textarea></td>
							</tr>
							</table>
							<br>
							<table border=0 cellpadding=1 cellspacing=0 width=100%>
							<tr>
								<td width=75px align=right nowrap><input type=checkbox name="edit_cboxPost" value="Y"></td>
								<td><b>Post</b></td>
								<td width=50px align=right nowrap><input type=checkbox name="edit_cboxPrint" value="Y"></td>
								<td><b>Print</b></td>
								<td width=50px align=right nowrap><input type=checkbox name="edit_cboxBold" value="Y"></td>
								<td><b>Bold</b></td>
								<td width=100px align=right nowrap><b>Placement:</b>&nbsp;</td>
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
<%		if (s_status.compareTo(DocumentStatus.SALE_BIDS_PENDING) < 0 || s_status.compareTo(DocumentStatus.SALE_OPENAMENDMENT) == 0) { %>
								<td width=50% align=center><a href="javascript: populateCmt(); void(0);"><img src="<%=contextPath%>/images/button_save.gif" border=0 class=button></a></td>
								<td width=50% align=center><a href="javascript: cancelEdit(); void(0);"><img src="<%=contextPath%>/images/button_return.gif" border=0 class=button></a></td>
<%		} else {%>
								<td width=100% align=center><a href="javascript: cancelEdit(); void(0);"><img src="<%=contextPath%>/images/button_return.gif" border=0 class=button></a></td>
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
	<td rowspan=2 align=right valign=top><%@ include file="/sales/sale_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<br>

<div id="classicNavigation">
<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if (s_status.compareTo(DocumentStatus.SALE_BIDS_PENDING) < 0 || s_status.compareTo(DocumentStatus.SALE_OPENAMENDMENT) == 0) { %>
	<td width=50% align=center><a href="javascript: doSubmit('/sales/sale_summary.jsp', 'SaleHeaderCommentUpdate;SaleRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/sales/sale_summary.jsp', 'SaleRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
<%	} else {%>
	<td width=100% align=center><a href="javascript: doSubmit('/sales/sale_summary.jsp', 'SaleRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
<%	}%>
</tr>
</table>
<%	} %>
</div>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	
	var saleNumber = "<%=s_saleNumber%>";
	var fiscalyear = "<%=s_fiscalYear%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var browser = browserCheck();
	
	myTable = document.getElementById("cmt_comments");
	totalRows = myTable.rows.length;
	
	function thisLoad()
	{
		f_StartIt();
		setDisplay();
<%	if (s_status.compareTo(DocumentStatus.SALE_BIDS_PENDING) >= 0 && s_status.compareTo(DocumentStatus.SALE_OPENAMENDMENT) != 0) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

	function addNew() {
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
		myRow = myTable.insertRow(count);
		count++;
		
		myCell = myRow.insertCell();
		myCell.id = "cmt_num_" + (count-1);
		myCell.align = "right";
		myCell.width = "4%";
		myCell.innerHTML = "&nbsp" + count + ".<input type=hidden name=\"DocComment_commentOrder\" value=\"" + (count) + "\">";
		
		myCell = myRow.insertCell();
		myCell.id = "cmt_id_" + (count-1);
		myCell.width = "20%";
		myCell.innerHTML = "&nbsp;";

		myCell = myRow.insertCell();
		myCell.id = "cmt_edit";
		myCell.width = "38%";
		myCell.innerHTML = "<a href=\"javascript: editCmt(" + (count - 1) + "); void(0);\"><div id=\"cmt_title\"></div>" +
			"<input type=hidden name=\"DocComment_commentTitle\" id=\"commentTitle_" + (count-1) + "\" maxlength=\"60\" value=\"\">" +
			"<input type=hidden name=\"DocComment_commentId\" maxlength=15 value=\"\">" +
			"<input type=hidden name=\"DocText_icText\" value=\"\">" +
			"<input type=hidden name=\"DocText_stdText\" id=\"commentText_" + (count-1) + "\" value=\"\">" + 
			"<input type=hidden name=\"DocComment_commentPublic\" id=\"commentPublic_" + (count - 1) + "\" value=\"\">";

		myCell = myRow.insertCell();
		myCell.id = "cmt_post";
		myCell.align = "center";
		myCell.width = "6%";
		myCell.innerHTML = "<input type=\"checkbox\" name=\"cboxPost_" + (count-1) + "\" value=\"Y\" ONCLICK=\"setPost(" + (count-1) + ");\">" +
			"<input type=hidden name=\"DocComment_commentWebpost\" id=\"commentPost_" + (count-1) + "\" value=\"N\">";

		myCell = myRow.insertCell();
		myCell.id = "cmt_print";
		myCell.align = "center";
		myCell.width = "7%";
		myCell.innerHTML = "<input type=\"checkbox\" name=\"cboxPrint_" + (count-1) + "\" value=\"Y\" ONCLICK=\"setPrint(" + (count-1) + ");\">" +
			"<input type=hidden name=\"DocComment_commentPrint\" id=\"commentPrint_" + (count-1) + "\" value=\"N\">";
		
		myCell = myRow.insertCell();
		myCell.id = "cmt_bold";
		myCell.align = "center";
		myCell.width = "7%";
		myCell.innerHTML = "<input type=\"checkbox\" name=\"cboxBold_" + (count-1) + "\" value=\"Y\" ONCLICK=\"setBold(" + (count-1) + ");\">" +
			"<input type=hidden name=\"DocComment_commentBold\" id=\"commentBold_" + (count-1) + "\" value=\"N\">";
				
		myCell = myRow.insertCell();
		myCell.id = "cmt_place";
		myCell.align = "center";
		myCell.width = "14%";
		myCell.innerHTML = "<select name=\"DocComment_commentPlace\" id=\"commentPlace_" + (count-1) + "\"><option value=\"A\" selected>After</option><option value=\"B\">Before</option></select>";

		myCell = myRow.insertCell();
		myCell.id = "cmt_del_" + count;
		myCell.align = "center";
		myCell.width = "4%";
		myCell.innerHTML = "<a href=\"javascript: if (confirm('Are you sure you wish to delete Comment line " + count  + "?')) { deleteMe(" + (count-1) + "); } void(0);\"><img src=\"<%=contextPath%>/images/delete.gif\" alt=\"Delete\" border=\"0\"></a>";

		totalRows++
		
		editRow = count -1;
		editCmt(count-1);
	}

	function editCmt(row) {
		var commentList = document.getElementById("commentList");
		var classicNav = document.getElementById("classicNavigation");
		var commentEdit = document.getElementById("commentEdit");

		commentList.style.visibility = "hidden";
		commentList.style.display = "none";
		classicNav.style.visibility = "hidden";
		classicNav.style.display = "none";
		commentEdit.style.visibility = "visible";
		commentEdit.style.display = "block";
				
		var cmtId =  document.getElementById("cmt_id_" + row).innerText;
		var cmtTitle =  document.getElementById("commentTitle_" + row).value;
		var cmtText = document.getElementById("commentText_" + row).value;
		var cmtPublic = document.getElementById("commentPublic_" + row).value;
		
		var cmtRowsTotal = myTable.rows.length;
	
		document.getElementById("editCommentId").innerText = cmtId;
		frm.edit_commentTitle.value = cmtTitle;
		frm.edit_stdText.value = cmtText;
		frm.edit_cboxPost.checked = document.getElementById("cboxPost_" + row).checked;
		frm.edit_cboxPrint.checked = document.getElementById("cboxPrint_" + row).checked;
		frm.edit_cboxBold.checked = document.getElementById("cboxBold_" + row).checked;
		frm.edit_commentPlace.value = document.getElementById("commentPlace_" + row).value;
		
		editRow = row;
		inEditMode = true;
	}

	function populateCmt() {
		var cmtTitle =  frm.edit_commentTitle.value;
		var cmtText = frm.edit_stdText.value;
		
		if (isEmpty(cmtTitle)) {
			alert("You must enter a title for this comment.");
			return false;
		}
		
		populateTitle(cmtTitle, editRow);
		document.getElementById("commentText_" + editRow).value = cmtText;
		document.getElementById("cboxPost_" + editRow).checked = frm.edit_cboxPost.checked;
		document.getElementById("cboxPost_" + editRow).fireEvent("onclick");
		document.getElementById("cboxPrint_" + editRow).checked = frm.edit_cboxPrint.checked;
		document.getElementById("cboxPrint_" + editRow).fireEvent("onclick");
		document.getElementById("cboxBold_" + editRow).checked = frm.edit_cboxBold.checked;
		document.getElementById("cboxBold_" + editRow).fireEvent("onclick");
		document.getElementById("commentPlace_" + editRow).value = frm.edit_commentPlace.value;

		var commentList = document.getElementById("commentList");
		var commentEdit = document.getElementById("commentEdit");
		var classicNav = document.getElementById("classicNavigation");
		
		commentEdit.style.visibility = "hidden";
		commentEdit.style.display = "none";
		commentList.style.visibility = "visible";
		commentList.style.display = "block";
		classicNav.style.visibility = "visible";
		classicNav.style.display = "block";
		
		inEditMode = false;
	}
	
	function setPost(row){
		var cbox = document.getElementById("cboxPost_" + row);
		var cmtPost = document.getElementById("commentPost_" + row);
		if (cbox.checked==true)
		{
			cmtPost.value = "Y";
		}
		else
		{
			cmtPost.value = "N";
		}
	}

	function deleteMe(row) {
		var currentRows = myTable.rows.length;
			
		if (currentRows == 0) {
			return;
		}
		else if (currentRows > 1) {
			for (var i = row; i < (currentRows - 1); i++) {
				var cmtOrder = frm.DocComment_commentOrder[i + 1].value;
				var cmtId = frm.DocComment_commentId[i + 1].value;
				var cmtTitle = frm.DocComment_commentTitle[i + 1].value;
				var cmtPost = frm.DocComment_commentWebpost[i + 1].value;
				var cmtPrint = frm.DocComment_commentPrint[i + 1].value;
				var cmtBold = frm.DocComment_commentBold[i + 1].value;
				var cmtPlace = frm.DocComment_commentPlace[i + 1].value;
				var cmtPublic = frm.DocComment_commentPublic[i + 1].value;
				var icText = frm.DocText_icText[i + 1].value;
				var stdText = frm.DocText_stdText[i + 1].value;
				
				frm.DocComment_commentOrder[i].value = cmtOrder;
				frm.DocComment_commentId[i].value = cmtId;
				frm.DocComment_commentTitle[i].value = cmtTitle;
				frm.DocComment_commentWebpost[i].value = cmtPost;
				frm.DocComment_commentPrint[i].value = cmtPrint;
				frm.DocComment_commentBold[i].value = cmtBold;
				frm.DocComment_commentPlace[i].value = cmtPlace;
				frm.DocComment_commentPublic[i].value = cmtPublic;
				frm.DocText_icText[i].value = icText;
				frm.DocText_stdText[i].value = stdText;

				if (cmtPost == "Y") {
					document.getElementById("cboxPost_" + i).checked = true;
				} else {
					document.getElementById("cboxPost_" + i).checked = false;
				}
				if (cmtPrint == "Y") {
					document.getElementById("cboxPrint_" + i).checked = true;
				} else {
					document.getElementById("cboxPrint_" + i).checked = false;
				}
				if (cmtBold == "Y") {
					document.getElementById("cboxBold_" + i).checked = true;
				} else {
					document.getElementById("cboxBold_" + i).checked = false;
				}

				populateTitle(cmtTitle, i);
				
				document.getElementById("cmt_id_" + i).innerText = cmtId;
			}
		}
		
		myTable.deleteRow(currentRows - 1);

		if (currentRows <= 1)
		{
			frm.deleteall.value = "TRUE";
			
			var noComments = document.getElementById("noCommentsMsg");
			noComments.style.visibility = "visible";
			noComments.style.display = "block";
			
			var deleteAllLink = document.getElementById("deleteAllLink");
			deleteAllLink.style.visibility = "hidden";
			deleteAllLink.style.display = "none";
		}
	}


// End Hide script -->
</SCRIPT>