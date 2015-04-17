<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/comments.js"></script>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_display_options = propertiesManager.getProperty("COMMENTS", "DisplayOptions", "Y");
	Catalog catalog = (Catalog) request.getAttribute("catalog");
	CatalogItem catalogItem = (CatalogItem) request.getAttribute("catalogItem");
	String	s_catalog_id = (String) request.getAttribute("CatalogItem_id_catalogId");
	String	s_item_number = (String) request.getAttribute("CatalogItem_id_itemNumber");
	String s_default_print = propertiesManager.getProperty("COMMENTS", "DefaultPrint", "N");

	String	s_current_process = "ITEM_NOTES";
	String	s_current_page = "/admin/catalog/cat_item_notes.jsp";
	String	s_current_method = "DocCommentUpdate";
	String	s_current_process_method = "";
%>

<tsa:hidden name="CatalogItem_catalogId" value="<%= s_catalog_id %>"/>
<tsa:hidden name="CatalogItem_itemNumber" value="<%= s_item_number %>"/>
<tsa:hidden name="DocComment_icHeader" value="<%= catalog.getIcHeaderComment() %>"/>
<tsa:hidden name="DocComment_icLine" value="<%= catalogItem.getIcLineComment() %>"/>
<tsa:hidden name="Default_referenceType" value="CTI"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>

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
				<tr><td class=hdr12 nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "comments", "Comments")%></td></tr>
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
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "item", "Item")%> #:</b></td>
			<td width=125px><%= catalogItem.getComp_id().getItemNumber() %></td>
		</tr>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "catalog", "Catalog")%>:</b></td>
			<td width=125px><%= catalog.getCatalogId() %></td>
		</tr>
		</table>
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

<%
	List cmtList = (List) request.getAttribute("docCommentList");
	List docTextList = (List) request.getAttribute("docTextList");
%>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
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
		<div id="commentList" style="visibility: visible; display: block;">
			<table border=0 cellpadding=1 cellspacing=0 width=500px>
			<tr>
				<td class="browseHdrDk" width=8%>&nbsp;</td>
				<td class="browseHdrDk" width=16%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "commentID", "Comment ID")%></b></td>
				<td class="browseHdrDk" width=42%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "title", "Title")%></b></td>
<%	if (!s_display_options.equalsIgnoreCase("N")) {	%>
				<td class="browseHdrDk" align=center width=7%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "Print", "Print")%></b></td>
				<td class="browseHdrDk" align=center width=7%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "Bold", "Bold")%></b></td>
				<td class="browseHdrDk" align=center width=15%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "placement", "Placement")%></b></td>
<%	}	%>
				<td class="browseHdrDk" width=5%>&nbsp;</td>
			</tr>
			<tr>
				<td colspan=8 vAlign=top>
					<div id="noCommentsMsg" style="visibility: hidden; display: none;">
					<table border=0 cellpadding=1 cellspacing=0 width=500px>
					<tr><td width=100% align=center>No comments to display.</td></tr>
					</table>
					</div>
					<table id="cmt_comments" border=0 cellpadding=1 cellspacing=0 width=500px class="browseRow">
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
					<tr>
						<td align="right" width=8% id="cmt_num_<%=i%>">&nbsp;<%=i+1%>.&nbsp;<a href="javascript: //" onclick="javascript: highlightRow(<%= i %>);"><img src="<%=contextPath%>/images/next.gif" border="0" alt="Select this line"></a>
							<tsa:hidden name="DocComment_commentOrder" value="<%=b_cmtOrder%>"/>
						</td>
						<td width=16% id="cmt_id_<%=i%>">
							<div id="comment_id_<%=i%>">
								<%=docComment.getCommentId()%>
							</div>
						</td>
						<td width=42% id="cmt_edit">
							<div id="comment_edit_<%=i%>">
								<a href="javascript: editCmt(<%=i%>); void(0);" title="Click here to View/Modify details for this comment."><div id="cmt_title" name="cmt_title"><%=docComment.getCommentTitle()%></div></a>
								<tsa:hidden name="DocComment_commentTitle" id="commentTitle_<%=i%>" value="<%=docComment.getCommentTitle()%>"/>
								<tsa:hidden name="DocComment_commentId" value="<%=docComment.getCommentId()%>"/>
								<tsa:hidden name="DocText_icText" value="<%=docComment.getIcText()%>"/>
								<tsa:hidden name="DocText_stdText" id="commentText_<%=i%>" value="<%=s_std_text%>"/>
								<tsa:hidden name="DocComment_commentPublic" id="commentPublic_<%=i%>" value="<%=docComment.getCommentPublic()%>"/>
							</div>
						</td>
						<td align=center width=7%>
							<div id="comment_print_<%=i%>">
								<input type="checkbox" id="cboxPrint_<%=i%>" name="cboxPrint_<%=i%>" <% if (docComment.getCommentPrint().indexOf("Y")>= 0){%>CHECKED<%}%> value="Y" ONCLICK="setPrint(<%=i%>);">
								<tsa:hidden id="commentPrint_<%=i%>" name="DocComment_commentPrint" value="<%=docComment.getCommentPrint()%>"/>
							</div>
						</td>
						<td align=center width=7%>
							<div id="comment_bold_<%=i%>">
								<input type="checkbox" id="cboxBold_<%=i%>" name="cboxBold_<%=i%>" <% if (docComment.getCommentBold().indexOf("Y")>= 0){%>CHECKED<%}%> value="Y" ONCLICK="setBold(<%=i%>);">
								<tsa:hidden id="commentBold_<%=i%>" name="DocComment_commentBold" value="<%=docComment.getCommentBold()%>"/>
							</div>
						</td>
						<td align=center width=15%>
							<div id="comment_place_<%=i%>">
								<select name="DocComment_commentPlace" id="commentPlace_<%=i%>">
									<option value="A" <% if (docComment.getCommentPlace().indexOf("A")>= 0){ %>selected<%}%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "after", "After")%></option>
									<option value="B" <% if (docComment.getCommentPlace().indexOf("B")>= 0){ %>selected<%}%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "before", "Before")%></option>
								</select>
							</div>
						</td>
						<td align=center width=5% id="cmt_del_<%=i%>"><a href="javascript: if (verifyAction('Are you sure you wish to delete Comment line <%=(i+1)%>?')) { deleteMe(<%=i%>); } "><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></td>
					</tr>
<%	}
	} %>
					</table>
				</td>
			</tr>
			</table>
			<br>
			<table border=0 cellpadding=0 cellspacing=0 width=500px>
			<tr>
				<td nowrap align=center width=50%>
					<a href="javascript: addNew(); void(0)"><font class=reset><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addComment", "Add a comment")%></b></font></a>
				</td>
				<!-- td nowrap align=center width=34%>
					<A href="javascript: addStandardComment(); void(0);"><font class=reset><b>Add standard comment</b></font></a>
				</td-->
				<td nowrap align=center width=50%>
					<div id="deleteAllLink" style="visibility: visible; display: block;">
					<a href="javascript: deleteAll(); void(0)"><font class="reset"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "deleteAll", "Delete all")%></b></font></a>
					</div>
				</td>
			</tr>
			</table>
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

							<div id="commentOptions" style="visibility: visible; display: block">
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
							</div>

							<br>

							<table border=0 cellpadding=1 cellspacing=0 width=100%>
							<tr>
								<td width=50% align=center><a href="javascript: populateCmt(); void(0);"><img src="<%=contextPath%>/images/button_save.gif" border=0 class=button></a></td>
								<td width=50% align=center><a href="javascript: cancelEdit(); void(0);"><img src="<%=contextPath%>/images/button_return.gif" border=0 class=button></a></td>
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
</tr>
</table>

<br>
<br>

<div id="classicNavigation">
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a href="javascript: doSubmit('/admin/catalog/catalog_item.jsp', 'DocCommentUpdate;CatalogItemRetrieveById'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/admin/catalog/catalog_item.jsp', 'CatalogItemRetrieveById'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
</tr>
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

		hasCommentOrder = true;
		checkNumComments();
		checkDisplayOptions();
	}
// End Hide script -->
</SCRIPT>