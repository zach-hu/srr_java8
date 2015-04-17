<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.math.*" %>

<%
	String	s_ic_dsb_line = (String) request.getParameter("DisbLine_icDsbLine");
	String	s_ic_dsb_header = (String) request.getAttribute("DisbHeader_icDsbHeader");
	String	s_dsb_number = (String) request.getAttribute("DisbHeader_disbNumber");
	String	s_status = (String) request.getAttribute("DisbHeader_status");
	String	s_dsb_type = (String) request.getAttribute("DisbHeader_disbType");
	String	s_line_number = (String) request.getAttribute("DisbLine_lineNumber");
	String	s_line_count = (String) request.getAttribute("lineCount");
	String	s_from_page = HiltonUtility.ckNull((String) request.getAttribute("frompage"));

	if (s_dsb_number == null)
	{
		s_dsb_number = (String) request.getAttribute("formNumber");
	}
	if (s_line_number == null)
	{
		s_line_number = (String) request.getAttribute("lineNumber");
	}
	if (s_ic_dsb_header == null)
	{
		s_ic_dsb_header = (String) request.getAttribute("DocComment_icHeader");
	}
	if (s_ic_dsb_line == null)
	{
		s_ic_dsb_line = (String) request.getAttribute("DocComment_icLine");
	}
	if (s_status == null)
	{
		s_status = (String) request.getAttribute("formStatus");
	}
%>

<tsa:hidden name="DisbHeader_icDsbHeader" value="<%=s_ic_dsb_header%>"/>
<tsa:hidden name="DisbLine_icDsbLine" value="<%=s_ic_dsb_line%>"/>
<tsa:hidden name="DisbHeader_disbNumber" value="<%=s_dsb_number%>"/>
<tsa:hidden name="DisbHeader_disbType" value="<%=s_dsb_type%>"/>
<tsa:hidden name="DisbHeader_status" value="<%=s_status%>"/>
<tsa:hidden name="DisbLine_lineNumber" value="<%=s_line_number%>"/>
<tsa:hidden name="lineCount" value="<%=s_line_count%>"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_ic_dsb_header%>"/>
<tsa:hidden name="DocComment_icLine" value="<%=s_ic_dsb_line%>"/>
<tsa:hidden name="Default_referenceType" value="DBL"/>
<tsa:hidden name="formNumber" value="<%=s_dsb_number%>"/>
<tsa:hidden name="formStatus" value="<%=s_status%>"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="frompage" value="<%=s_from_page%>"/>

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
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Line Item <%=s_line_number%> - Comments</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Disbursement #:</b></td>
			<td width=100px><%=s_dsb_number%></td>
		</tr>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=100px><%=DocumentStatus.toString(s_status)%></td>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top>
		<TABLE BORDER="0" CELLPADDING="0" CELLSPACING="0" WIDTH="600px">
		<TR>
			<TD>
				<TABLE BORDER="0" CELLPADDING="1" CELLSPACING="0"  WIDTH="600px">
				<TR VALIGN="MIDDLE">
					<TD WIDTH=4%>&nbsp;</TD>
					<TD ALIGN="CENTER" WIDTH=15%><b>Comment ID</b></TD>
					<TD ALIGN="CENTER" WIDTH=28%><b>Title</b></TD>
					<TD ALIGN="CENTER" WIDTH=10% nowrap><b>Edit / Add</b></TD>
					<TD ALIGN="CENTER" WIDTH=7%><b>Print</b></TD>
					<TD ALIGN="CENTER" WIDTH=7%><b>Bold</b></TD>
					<TD ALIGN="LEFT" WIDTH=13%><b>Placement</b></TD>
					<TD WIDTH=1%>&nbsp;</TD>
				</TR>
				<tr valign=middle>
					<td colspan=8>
					<table ID=cmt_comments border=0 width=100%>
<%	List docCommentList = (List) request.getAttribute("docCommentList");
		List docTextList = (List) request.getAttribute("docTextList");
		if (docCommentList != null)
		{
			for (int i = 0; i < docCommentList.size(); i++)
			{
				DocComment docComment = (DocComment) docCommentList.get(i);
				String	s_std_text = "";
				if (docTextList !=null)
				{
					DocText docText = (DocText) docTextList.get(i);
					s_std_text = docText.getStdText();
				}
				String	s_comment_id = docComment.getCommentId();
				String	s_title = docComment.getCommentTitle();
				String	s_print = docComment.getCommentPrint();
				String	s_bold = docComment.getCommentBold();
				String	s_place = docComment.getCommentPlace();
				String	s_public = docComment.getCommentPublic();

				BigDecimal	s_icText = docComment.getIcText();

				if (s_comment_id == null)	{	s_comment_id = "";	}
%>
					<tr>
					<TD WIDTH=4% ID=cmt_num_0 ALIGN="RIGHT">&nbsp;<%=i + 1%>.&nbsp;</TD>
					<TD WIDTH=15% ID=cmt_id ALIGN="CENTER"><input type=text name=DocComment_commentId size=15 maxlength=15 value="<%=s_comment_id%>" onfocus="this.blur();" disabled></TD>
					<TD WIDTH=28% ID=cmt_title ALIGN="CENTER">
						<input type=text name="DocComment_commentTitle" ID="commentTitle_<%=i%>" size=30 maxlength=60 value="<%=s_title%>" onfocus="this.blur();" disabled>
						<tsa:hidden name="DocText_icText" value="<%=s_icText%>"/>
						<tsa:hidden name="DocText_stdText" id="commentText_<%=i%>" value="<%=s_std_text%>"/>
						<tsa:hidden name="icText_icText" value="<%=s_icText%>"/>
						<tsa:hidden name="DocComment_commentPublic" id="commentPublic_<%=i%>" value="<%=s_public%>"/>
					</TD>
					<TD WIDTH=10% ID=cmt_edit ALIGN="CENTER">
						<a href="javascript: editCmt(<%=i%>); void(0);"><img name="icotext" ID="icotext_<%=i%>" src="<%=contextPath%>/images/ico_comment_on.gif" alt="Edit Comment" border=0></a>
					</TD>
					<TD WIDTH=7% ID=cmt_print ALIGN="CENTER">
						<input type=checkbox name="cboxPrint_<%=i%>" <% if (s_print.indexOf("Y") >= 0) { %> CHECKED <% } %> value="Y" ONCLICK="setPrint(<%=i%>);">
						<tsa:hidden id="commentPrint_<%=i%>" name="DocComment_commentPrint" value="<%=s_print%>"/>
					</TD>
					<TD WIDTH=7% ID=cmt_bold ALIGN="CENTER">
						<input type=checkbox name="cboxBold_<%=i%>" <% if (s_bold.indexOf("Y") >= 0) { %> CHECKED <% } %> value="Y" ONCLICK="setBold(<%=i%>);">
						<tsa:hidden name="DocComment_commentBold" id="commentBold_<%=i%>" value="<%=s_bold%>"/>
					</TD>
					<TD WIDTH=13% ID=cmt_place ALIGN="CENTER">
						<select name="DocComment_commentPlace">
							<option value="A" <% if (s_place.indexOf("A")>= 0){ %>SELECTED<%}%>>After</option>
							<option value="B" <% if (s_place.indexOf("B")>= 0){ %>SELECTED<%}%>>Before</option>
						</select>
					</TD>
<%	if (s_status.compareTo(DocumentStatus.INV_INPROGRESS) == 0) { %>
					<TD WIDTH=1% ID="cmt_del_<%=i%>" ALIGN="CENTER"><a href="javascript: deleteMe(<%=i%>); void(0);"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></TD>
<%			} else { %>
					<td width=1%>&nbsp;</td>
<%			} %>
					</tr>
<%		}
		} %>
					</table>
					</td>
				</tr>
<%	if (s_status.compareTo(DocumentStatus.INV_INPROGRESS) == 0) { %>
				<tr valign=top>
					<td>&nbsp;</td>
					<td nowrap><a href="javascript: addNew(); void(0)"><font class="reset"><b>Add new</B></font></a></td>
					<TD nowrap colspan=4  ALIGN="CENTER"><A href="javascript: browseComments(); void(0);"><FONT CLASS="reset"><B>Add standard comment</B></FONT></A></TD>
					<td align=right nowrap><a href="javascript: deleteAll(); void(0)"><font class="reset"><b>Delete all</B></font></a>&nbsp;</td>
				</tr>
<%	} %>
				</TABLE>
			</TD>
		</TR>

		</TABLE>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/dsb_item.jsp', 'DocCommentUpdate;DisbLineRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/dsb_item.jsp', 'DisbLineRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var displayOptions = "";
	var defaultPrint = "";
	var browser = browserCheck();
	var Rows = 0;

	myTable = document.getElementById("cmt_comments");
	var TotalRows = myTable.rows.length;

	if (TotalRows <= 0){
		addNew();
	}

	function deleteMe(row) {
		if (confirm("Are you sure you wish to delete \"Comment\" line " + (row+1) + "?"))
		{
			newDel = 0;

			myTable = document.getElementById("cmt_comments");
			count = myTable.rows.length;
			myTable.deleteRow(row);

			for(i = 0;i <= count; i++)
			{
				del = "cmt_del_" + i;
				myCell = document.getElementById(del);

				if(myCell != null)
				{
					myCell.innerHTML = "<a href=\"javascript: deleteMe(" + newDel + "); void(0)\"><img name=\"deletethis\" src=\"<%=contextPath%>/images/delete.gif\" alt=\"Delete\" border=0></a>";
					newDel = newDel + 1;
				}
			}

			if (count == 1)
			{
				frm.deleteall.value = "TRUE";
			}
		}
	}

	function deleteAll(){
		if (confirm("Are you sure you wish to delete all Comments?"))
		{
			frm.deleteall.value = "TRUE";
			myTable = document.getElementById("cmt_comments");
			count = myTable.rows.length;

			for(i = 0; i < count; i++)
			{
				myTable.deleteRow(0);
			}
		}
	}

	function addNew(){
		myTable = document.getElementById("cmt_comments");

		count = myTable.rows.length;
		Rows++;
		myRow = myTable.insertRow(count);
		count++;

		myCell = myRow.insertCell();
		id = "cmt_num_" + count;
		myCell.id = id;
		myCell.align = "right";
		myCell.width = "4%";
		myCell.innerHTML = "&nbsp" + count + ".<INPUT TYPE=\"HIDDEN\" NAME=\"DocComment_commentOrder\" value=\"" + (count) + "\">";

		myCell = myRow.insertCell();
		id = "cmt_id";
		myCell.id = id;
		myCell.align = "center";
		myCell.width="15%";
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"DocComment_commentId\" SIZE=\"15\" MAXLENGTH=\"15\" value=\"\" ONFOCUS=\"this.blur();\" disabled><INPUT TYPE=\"HIDDEN\" NAME=\"DocComment_commentPublic\" ID=\"commentPublic_" + (count-1) + "\" value=\"\">";

		myCell = myRow.insertCell();
		id = "cmt_title";
		myCell.id = id;
		myCell.align = "center";
		myCell.width = "28%";
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"DocComment_commentTitle\" ID=\"commentTitle_" + (count-1) + "\" SIZE=\"30\" MAXLENGTH=\"60\" value=\"\" ONFOCUS=\"this.blur();\" disabled><INPUT TYPE=\"HIDDEN\" NAME=\"DocText_icText\" value=\"\"><INPUT TYPE=\"HIDDEN\" NAME=\"icText_icText\" value=\"\"><INPUT TYPE=\"HIDDEN\" NAME=\"DocText_stdText\" ID=\"commentText_" + (count-1) + "\" value=\"\">";

		myCell = myRow.insertCell();
		id = "cmt_edit";
		myCell.id = id;
		myCell.align = "center";
		myCell.width = "10%";
		myCell.innerHTML = "<A HREF=\"javascript: editCmt(" + (count-1) + "); void(0);\"><IMG NAME=\"icotext\" ID=\"icotext_" + (count - 1) + "\" SRC=\"<%=contextPath%>/images/ico_comment_off.gif\" ALT=\"Edit Comment\" BORDER=\"0\"></A>";

		myCell = myRow.insertCell();
		id = "cmt_print";
		myCell.id = id;
		myCell.align = "center";
		myCell.width = "7%";
		myCell.innerHTML = "<INPUT TYPE=\"CHECKBOX\" NAME=\"cboxPrint_" + (count-1) + "\" value=\"Y\" ONCLICK=\"setPrint(" + (count-1) + ");\"><INPUT TYPE=\"HIDDEN\" NAME=\"DocComment_commentPrint\" ID=\"commentPrint_" + (count-1) + "\" value=\"N\">";

		myCell = myRow.insertCell();
		id = "cmt_bold";
		myCell.id = id;
		myCell.align = "center";
		myCell.width = "7%";
		myCell.innerHTML = "<INPUT TYPE=\"CHECKBOX\" NAME=\"cboxBold_" + (count-1) + "\" value=\"Y\" ONCLICK=\"setBold(" + (count-1) + ");\"><INPUT TYPE=\"HIDDEN\" NAME=\"DocComment_commentBold\" ID=\"commentBold_" + (count-1) + "\" value=\"N\">";

		myCell = myRow.insertCell();
		id = "cmt_place";
		myCell.id = id;
		myCell.align = "center";
		myCell.width = "13%";
		myCell.innerHTML = "<select name=\"DocComment_commentPlace\"><option value=\"A\" SELECTED>After</option><option value=\"B\">Before</option></select>";

		myCell = myRow.insertCell();
		id = "cmt_del_" + count;
		myCell.id = id;
		myCell.align = "center";
		myCell.width = "1%";
		myCell.innerHTML = "<A href=\"javascript: deleteMe(" + (count-1) + "); void(0)\"><IMG SRC=\"<%=contextPath%>/images/delete.gif\" ALT=\"Delete\" BORDER=\"0\"></A>";

		newDel = 0;
		newNum = 1;
		TotalRows++
		for(i=0;i<=TotalRows;i++){
			//fix number in deleteMe()
			del = "cmt_del_" + i;
			myCell = document.getElementById(del);
			if(myCell != null){
				myCell.innerHTML = "<a href=\"javascript: deleteMe(" + newDel + "); void(0)\"><img name=\"deletethis\" src=\"<%=contextPath%>/images/delete.gif\" alt=\"Delete\" border=0></a>";
				newDel=newDel+1;
			}
			//fix line number
			num = "cmt_num_" + i;
			myCell = document.getElementById(num);
			if(myCell != null){
				myCell.innerHTML = "&nbsp" + newNum + "." + "<INPUT TYPE=\"HIDDEN\" NAME=\"DocComment_commentOrder\" value=\"" + newNum + "\">"
				newNum=newNum+1;
			}
		}

		if (TotalRows >=2 ){
			editCmt(count-1);
		}
	}

	function editCmt(row){
		var frmTitle = document.getElementById("commentTitle_" + row);
		var frmText = document.getElementById("commentText_" + row);
		var frmPublic = document.getElementById("commentPublic_" + row);
		var cmtTitle = frmTitle.value;
		var cmtText = frmText.value;
		var cmtPublic = frmPublic.value;
		var cmtRowsTotal = myTable.rows.length;
		popupParameters = "cmtTitle=" + cmtTitle + ";cmtText=" + cmtText + ";cmtPublic=" + cmtPublic + ";cmtRow=" + row + ";cmtTotal=" + cmtRowsTotal;

<%	if (s_status.compareTo(DocumentStatus.INV_INPROGRESS) > 0) { %>
			popupParameters = popupParameters + ";allowEdit=false";
<%	} %>

		doSubmitToPopup('/base/notes_edit.jsp', 'DoNothing', 'WIDTH=500px', 'HEIGHT=300px');
	}

	function setPrint(row){
		var cbox = document.getElementById("cboxPrint_" + row);
		var cmtPrint = document.getElementById("commentPrint_" + row);
		if (cbox.checked==true)
		{
			cmtPrint.value = "Y";
		}
		else
		{
			cmtPrint.value = "N";
		}
	}

	function setBold(row){
		var cbox = document.getElementById("cboxBold_" + row);
		var cmtBold = document.getElementById("commentBold_" + row);
		if (cbox.checked==true)
		{
			cmtBold.value = "Y";
		}
		else
		{
			cmtBold.value = "N";
		}
	}

	function thisLoad()
	{
		f_StartIt();
<%	if (s_status.compareTo(DocumentStatus.INV_INPROGRESS) > 0) { %>
			checkInputSettings();
<%	} %>
	}

// End Hide script -->
</SCRIPT>