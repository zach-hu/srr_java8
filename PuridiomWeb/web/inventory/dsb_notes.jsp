<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.util.List.*" %>
<%@ page import="java.math.*" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/comments.js"></script>

<%
	int i;
	String	s_ic_dsb_header = (String) request.getAttribute("DisbHeader_icDsbHeader");
	String	s_dsb_number = (String) request.getAttribute("DisbHeader_disbNumber");
	String	s_dsb_status = (String) request.getAttribute("DisbHeader_status");
	String	s_dsb_type = (String) request.getAttribute("DisbHeader_disbType");
	String	s_fiscal_year = (String) request.getAttribute("DisbHeader_fiscalYear");

	if (s_dsb_number == null)
	{
		s_dsb_number = (String) request.getAttribute("formNumber");
	}
	if (s_ic_dsb_header == null)
	{
		s_ic_dsb_header = (String) request.getAttribute("DocComment_icHeader");
	}
	if (s_dsb_status == null)
	{
		s_dsb_status = (String) request.getAttribute("formStatus");
	}
	if (s_fiscal_year == null)
	{
		s_fiscal_year = (String) request.getAttribute("fiscalYear");
	}

	String	s_current_process = "HEADER_NOTES";
	String	s_current_page = "/inventory/dsb_notes.jsp";
	String	s_current_method = "DocCommentUpdate";
	String	s_current_process_method = "";
%>

<tsa:hidden name="DisbHeader_icDsbHeader" value="<%=s_ic_dsb_header%>"/>
<tsa:hidden name="DisbHeader_disbNumber" value="<%=s_dsb_number%>"/>
<tsa:hidden name="DisbHeader_status" value="<%=s_dsb_status%>"/>
<tsa:hidden name="DisbHeader_disbType" value="<%=s_dsb_type%>"/>
<tsa:hidden name="DisbHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="DisbLine_icDsbHeader" value="<%=s_ic_dsb_header%>"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_ic_dsb_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="Account_icHeader" value="<%=s_ic_dsb_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="DBH"/>
<tsa:hidden name="Default_referenceType" value="DBH"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="formNumber" value="<%=s_dsb_number%>"/>
<tsa:hidden name="formStatus" value="<%=s_dsb_status%>"/>
<tsa:hidden name="fiscalYear" value="<%=s_fiscal_year%>"/>

<%
	if (HiltonUtility.isEmpty(s_dsb_number))
	{
		s_dsb_number = "N/A";
	}
%>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Comments</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Disbursement #:</b></td>
			<td width=125px><%=headerEncoder.encodeForHTML(s_dsb_number)%></td>
		</tr>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=125px><%=DocumentStatus.toString(s_dsb_status, oid)%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
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
					<tr><td width=100% align=center>No notes to display.</td></tr>
					</table>
					</div>
					<table id="cmt_comments" border=0 cellpadding=1 cellspacing=0 width=500px>
<%
	List cmtList = (List) request.getAttribute("docCommentList");
	List docTextList = (List) request.getAttribute("docTextList");

	if (cmtList != null)
	{
		for (i = 0; i < cmtList.size(); i++)
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
				<TR>
						<td align=right width=4% id="cmt_num_<%=i%>">&nbsp;<%=i+1%>.&nbsp;<tsa:hidden name="DocComment_commentOrder" value="<%=b_cmtOrder%>"/></td>
						<td width=20% id="cmt_id_<%=i%>"><%=docComment.getCommentId()%></td>
						<td width=42% id="cmt_edit"><a href="javascript: editCmt(<%=i%>); void(0);" title="Click here to View/Modify details for this comment."><div id="cmt_title"><%=docComment.getCommentTitle()%></div></a>
							<tsa:hidden name="DocComment_commentTitle" id="commentTitle_<%=i%>" value="<%=docComment.getCommentTitle()%>"/>
							<tsa:hidden name="DocComment_commentId" value="<%=docComment.getCommentId()%>"/>
							<tsa:hidden name="DocText_icText" value="<%=docComment.getIcText()%>"/>
							<tsa:hidden name="DocText_stdText" id="commentText_<%=i%>" value="<%=s_std_text%>"/>
							<tsa:hidden name="DocComment_commentPublic" id="commentPublic_<%=i%>" value="<%=docComment.getCommentPublic()%>"/>
					</TD>
					<td align=center width=7%><input type="checkbox" name="cboxPrint_<%=i%>" <% if (docComment.getCommentPrint().indexOf("Y")>= 0){ %>CHECKED<% } %> value="Y" ONCLICK="setPrint(<%=i%>);">
							<tsa:hidden id="commentPrint_<%=i%>" name="DocComment_commentPrint" value="<%=docComment.getCommentPrint()%>"/>
						</td>
					<td align=center width=7%><input type="checkbox" name="cboxBold_<%=i%>" <% if (docComment.getCommentBold().indexOf("Y")>= 0){ %>CHECKED<% } %> value="Y" ONCLICK="setBold(<%=i%>);">
							<tsa:hidden id="commentBold_<%=i%>" name="DocComment_commentBold" value="<%=docComment.getCommentBold()%>"/>
						</td>
						<td align=center width=15%>
							<select name="DocComment_commentPlace" id="commentPlace_<%=i%>">
								<option value="A" <% if (docComment.getCommentPlace().indexOf("A")>= 0){ %>selected<% } %>>After</option>
								<option value="B" <% if (docComment.getCommentPlace().indexOf("B")>= 0){ %>selected<% } %>>Before</option>
							</select>
						</td>
<%	if (s_dsb_status.compareTo(DocumentStatus.INV_INPROGRESS) == 0) { %>
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
<%	if (s_dsb_status.compareTo(DocumentStatus.INV_INPROGRESS) == 0) { %>
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
<%	if (s_dsb_status.compareTo(DocumentStatus.INV_INPROGRESS) == 0) { %>
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
	<td rowspan=2 align=right><%@ include file="/inventory/dsb_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<br>
<br>

<div id="classicNavigation">
<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if (s_dsb_status.compareTo(DocumentStatus.INV_INPROGRESS) == 0) { %>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/dsb_summary.jsp', 'DocCommentUpdate;DisbursementRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/dsb_summary.jsp', 'DisbursementRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
<%		} else {%>
	<td width=100% align=center><a href="javascript: doSubmit('/inventory/dsb_summary.jsp', 'DisbursementRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
<%		}%>
</tr>
</table>
<%	} %>
</div>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var displayOptions = "";
	var defaultPrint = "";

	var browser = browserCheck();
	var Rows = 0;
	var thisrow;
	myTable = document.getElementById("cmt_comments");
	var TotalRows = myTable.rows.length;
	var maxRows = myTable.rows.length;

	function thisLoad()
	{
		f_StartIt();
<%	if (s_dsb_status.compareTo(DocumentStatus.INV_INPROGRESS) > 0) { %>
			checkInputSettings();
<%	} %>
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>