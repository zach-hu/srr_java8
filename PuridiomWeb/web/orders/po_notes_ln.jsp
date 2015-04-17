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
	String	s_line_count = (String) request.getAttribute("lineCount");
	String	s_ic_po_header = (String) request.getAttribute("PoHeader_icPoHeader");
	String	s_po_number = (String) request.getAttribute("PoHeader_poNumber");
	BigDecimal	bd_zero = new BigDecimal(0);
	String	s_release_number = (String) request.getAttribute("PoHeader_releaseNumber");
	String	s_revision_number = (String) request.getAttribute("PoHeader_revisionNumber");
	BigDecimal	bd_release_number = new BigDecimal(s_release_number);
	BigDecimal	bd_revision_number = new BigDecimal(s_revision_number);
	String	s_po_type = (String) request.getAttribute("PoHeader_poType");
	String	s_fiscal_year = (String) request.getAttribute("PoHeader_fiscalYear");
	String	s_po_status = (String) request.getAttribute("PoLine_status");
	String	s_item_location = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_itemLocation"));
	String	s_buyer_code = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_buyerCode"));
	String	s_ic_po_line = (String) request.getAttribute("PoLine_icPoLine");
	String	s_line_number = (String) request.getAttribute("PoLine_lineNumber");
	String	s_from_page = HiltonUtility.ckNull((String) request.getAttribute("frompage"));

	if (s_po_number == null)
	{
		s_po_number = (String) request.getAttribute("formNumber");

		if (s_po_number == null)
		{
			s_po_number = "N/A";
		}
	}
	if (s_line_number == null)
	{
		s_line_number = (String) request.getAttribute("lineNumber");
	}
	if (s_ic_po_header == null)
	{
		s_ic_po_header = (String) request.getAttribute("DocComment_icHeader");
	}
	if (s_ic_po_line == null)
	{
		s_ic_po_line = (String) request.getAttribute("DocComment_icLine");
	}
	if (s_po_status == null)
	{
		s_po_status = (String) request.getAttribute("formStatus");
	}
	if (s_fiscal_year == null)
	{
		s_fiscal_year = (String) request.getAttribute("fiscalYear");
	}

	boolean bAllowEdit = true;
	if ( role.getAccessRights("PO") < 2 || (!bAdminChanges && s_po_status.compareTo(DocumentStatus.PO_REJECTED) > 0)  || (s_po_status.compareTo(DocumentStatus.CANCELLED) >= 0))
	{
		bAllowEdit = false;
	}
	if ( oid.equalsIgnoreCase("akdoc") && !user.isAnAdminBuyer() && !user.getUserId().equals(s_buyer_code) )
	{
		bAllowEdit = false;
	}

	if (s_po_status.compareTo(DocumentStatus.PO_REJECTED) > 0 || role.getAccessRights("PO") < 2) {
		bAllowEdit = false;
	}
%>

<tsa:hidden name="PoHeader_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoLine_icPoLine" value="<%=s_ic_po_line%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="PoHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="PoHeader_status" value="<%=s_po_status%>"/>
<tsa:hidden name="PoHeader_itemLocation" value="<%=s_item_location%>"/>
<tsa:hidden name="PoHeader_buyerCode" value="<%=s_buyer_code%>"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="DocComment_icLine" value="<%=s_ic_po_line%>"/>
<tsa:hidden name="Default_referenceType" value="POL"/>
<tsa:hidden name="formNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="formStatus" value="<%=s_po_status%>"/>
<tsa:hidden name="lineCount" value="<%=s_line_count%>"/>
<tsa:hidden name="PoLine_lineNumber" value="<%=s_line_number%>"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="frompage" value="<%=s_from_page%>"/>
<tsa:hidden name="fiscalYear" value="<%=s_fiscal_year%>"/>

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
<%	int i_colspan = 1;%>
		<tr>
			<td nowrap align=right><b>Order #:</b></td>
			<td width=200px><%=s_po_number%></td>
<%	if (bd_release_number.compareTo(bd_zero) > 0)
		{
			i_colspan = i_colspan + 2; %>
			<td nowrap align=right><b>Release #:</b></td>
			<td width=200px><%=bd_release_number%></td>
<%	}
		if (bd_revision_number.compareTo(bd_zero) > 0)
		{
			i_colspan = i_colspan + 2; %>
			<td nowrap align=right><b>Revision #:</b></td>
			<td width=200px><%=bd_revision_number%></td>
<%	} %>
		</tr>
		<tr>
			<td colspan=<%=i_colspan%> nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=200px><%=DocumentStatus.toString(s_po_status, oid)%></td>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
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
				s_std_text = s_std_text.replace('"', '~');
	    		s_std_text = s_std_text.replaceAll("~", "&#34;");
			}
			DocCommentPK docCommentPK = (DocCommentPK) docComment.getComp_id();
			BigDecimal b_cmtOrder = docCommentPK.getCommentOrder();
			pageContext.setAttribute("i", i);
%>
					<tr>
						<td valign=top align=right width=4% id="cmt_num_<%=i%>">&nbsp;<%=i+1%>.<tsa:hidden name="DocComment_commentOrder" value="<%=b_cmtOrder%>"/></td>
						<td valign=top width=20% id="cmt_id_<%=i%>"><%=docComment.getCommentId()%></td>
						<td valign=top width=42% id="cmt_edit"><a href="javascript: editCmt(<%=i%>); void(0);"><div id="cmt_title"><%=docComment.getCommentTitle()%></div></a>
							<tsa:hidden name="DocComment_commentTitle" id="commentTitle_${i}" value="<%=docComment.getCommentTitle()%>"/>
							<tsa:hidden name="DocComment_commentId" value="<%=docComment.getCommentId()%>"/>
							<tsa:hidden name="DocText_icText" value="<%=docComment.getIcText()%>"/>
							<tsa:hidden name="DocText_stdText" id="commentText_${i}" value="<%=s_std_text%>"/>
							<tsa:hidden name="DocComment_commentPublic" id="commentPublic_${i}" value="<%=docComment.getCommentPublic()%>"/>
						</td>
						<td valign=top align=center width=7%>							
							<% if (docComment.getCommentPrint().indexOf("Y")>= 0){ %>
								<tsa:input type="checkbox" name="cboxPrint_${i}" checked="checked" value="Y" onclick="setPrint(${i});"/>
							<%}else { %>
								<tsa:input type="checkbox" name="cboxPrint_${i}" value="Y" onclick="setPrint(${i});"/>
							<%} %>
							<tsa:hidden id="commentPrint_${i}" name="DocComment_commentPrint" value="<%=docComment.getCommentPrint()%>"/>
						</td>
						<td valign=top align=center width=7%>
							<% if (docComment.getCommentBold().indexOf("Y")>= 0){ %>
								<tsa:input type="checkbox" name="cboxBold_${i}" checked="checked" value="Y" onclick="setBold(${i});"/>
							<%}else { %>
								<tsa:input type="checkbox" name="cboxBold_${i}" value="Y" onclick="setBold(${i});"/>
							<%} %>
							<tsa:hidden id="commentBold_${i}" name="DocComment_commentBold" value="<%=docComment.getCommentBold()%>"/>
						</td>
						<td valign=top align=center width=15%>
							<select name="DocComment_commentPlace" id="commentPlace_<%=i%>">
								<option value="A" <% if (docComment.getCommentPlace().indexOf("A")>= 0){ %>selected<%}%>>After</option>
								<option value="B" <% if (docComment.getCommentPlace().indexOf("B")>= 0){ %>selected<%}%>>Before</option>
							</select>
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
<%	if ( bAllowEdit ) {	%>
			<br>
			<table border=0 cellpadding=0 cellspacing=0 width=500px>
			<tsa:tr>
				<tsa:td noWrap="nowrap" align="center" width="33%">
					<a href="javascript: addNew(); void(0)"><font class=reset><b><tsa:label labelName="addComment" defaultString="Add new"/></b></font></a>
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
<%	if ( bAllowEdit )  { %>
								<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: populateCmt(); void(0);"><tsa:label labelName="rfq-save" defaultString="Save"></tsa:label></a></div></tsa:td>
								<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: cancelEdit(); void(0);"><tsa:label labelName="rfq-return" defaultString="Return"></tsa:label></a></div></tsa:td>
<%	} else {%>								
								<tsa:td width="100%" align="center"><div id="pxbutton"><a href="javascript: cancelEdit(); void(0);"><tsa:label labelName="rfq-return" defaultString="Return"></tsa:label></a></div></tsa:td>
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
</tr>
</table>

<br>

<div id="classicNavigation">
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if ( bAllowEdit )  { %>	
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('/orders/po_item.jsp', 'DocCommentUpdate;PoLineRetrieve'); void(0);"><tsa:label labelName="rfq-save" defaultString="Save"></tsa:label></a></div></tsa:td>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('/orders/po_item.jsp', 'PoLineRetrieve'); void(0);"><tsa:label labelName="rfq-return" defaultString="Return"></tsa:label></a></div></tsa:td>	
<%	} else {%>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('/orders/po_item.jsp', 'PoLineRetrieve'); void(0);"><tsa:label labelName="rfq-return" defaultString="Return"></tsa:label></a></div></tsa:td>	
<%	}%>
</tr>
</table>
</div>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var browser = browserCheck();
	var currentmethod = "DocCommentUpdate";
	var currentprocessmethod = "DocCommentUpdate/DocCommentRetrieveByLine";
	var displayOptions = "";
	var defaultPrint = "";

	myTable = document.getElementById("cmt_comments");
	totalRows = myTable.rows.length;

	function thisLoad()
	{
		f_StartIt();
		setDisplay();
<%	if ( !bAllowEdit )  { %>
			checkInputSettings();
<%	} %>
	}

// End Hide script -->
</SCRIPT>