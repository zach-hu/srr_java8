<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.StdDocumentType" %>
<%@ page import="com.tsa.puridiom.entity.StdDocument" %>
<%@ page import="com.tsa.puridiom.organization.OrganizationManager" %>
<%
	String companyName = OrganizationManager.getInstance().getOrganizationName(oid);
	if (HiltonUtility.isEmpty(companyName)) {
		companyName = "This organization";
	}
%>
<tsa:hidden name="filename" value=""/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Standard Terms & Conditions</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right height=30px>
		<table cellpadding="0" cellspacing="0" border=0>
		<tr>
			<td width=1000px height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
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
<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td width=10px>&nbsp;</td>
	<td>
		<%=companyName%>'s standard Procurement Documents are available 
		to download for your review. These documents cannot be altered. Any exceptions taken to 
		the terms and conditions presented here must be approved in writing by the procurement 
		representative. Only those terms and conditions provided via printed copy by the procurement 
		representative in the Purchase Order/Subcontract shall be considered the contract documents.
	</td>
</tr>
</table>
<br>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top align=center>
		<div id="documentList" style="visibility: visible; display: block;'">
			<table border=0 cellpadding=1 cellspacing=0 width=500px>
			<tr>
				<td width=5%>&nbsp;</td>
				<td width=50%><b>Document Title</b></td>
				<td width=30%><b>Type</b></td>
				<td width=15% align=center><b>Date Posted</b></td>
			</tr>
			<tr>
				<td colspan=8 vAlign=top>
					<div id="noDocumentsMsg" style="visibility: hidden; display: none;">
					<table border=0 cellpadding=2 cellspacing=0 width=100%>
					<tr><td width=100% align=center><br>There are currently no Standard Documents listed.<br></td></tr>
					</table>
					</div>
					<table id="documents" border=0 cellpadding=1 cellspacing=0 width=100%>
<%
	List list = (List) request.getAttribute("stdDocumentList");
	
	if (list != null) {
		for (int i = 0; i < list.size(); i++) {
			StdDocument stdDocument = (StdDocument) list.get(i);
			String	filename = stdDocument.getFileName();
			String	ext = filename.substring(filename.lastIndexOf(".") + 1);
			String	docType = stdDocument.getDocType();
%>
					<tr>
						<td width=5% valign=middle align=center>
							<tsa:hidden name="docFilename" value = "<%=filename%>"/>
<%		if (ext.equalsIgnoreCase("DOC")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%		} else {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%		}%>
						</td>
						<td width=50%><%=stdDocument.getTitle()%></td>
						<td width=30%><%=StdDocumentType.toString(docType, oid)%></td>
						<td width=15% align=center><%=stdDocument.getDatePosted()%></td>
<%	}
	} %>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a href="javascript: if (confirmAcceptance()) {doSubmit('menu/main_menu.jsp', 'AcceptTerms');} void(0);"><img src="<%=contextPath%>/images/button_accept.gif" class=button border=0 tabIndex=110 alt="Click here to accept terms and conditions."></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('menu/main_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_continue.gif" border=0></a></td>
</tr>	
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	
	var myTable = document.getElementById("documents");
	var totalRows = myTable.rows.length

	function thisLoad() {
		if (totalRows <= 0)
		{
			var noDocuments = document.getElementById("noDocumentsMsg");
			noDocuments.style.visibility = "visible";
			noDocuments.style.display = "block";
		}
	}
	
	function openDocument(row) {
		var filename = "";
		
		if (totalRows > 1) {
			filename = frm.docFilename[row].value;
		} else {
			filename = frm.docFilename.value;
		}

		popupUrl = "";
		popupHandler = "StdDocumentDownloadFile";
		popupUserId = frm.userId.value;
		popupOrganizationId = frm.organizationId.value;
		popupParameters = "filename=" + filename;
			
		if (theFocus == null) { theFocus = 'document_window'; }
		
		var winspecs = "WIDTH=680,HEIGHT=680,resizable=1,scrollbars=1,menubar=0,location=0,top=0,left=0";		
		document_window = window.open("<%=contextPath%>/system/popup.html", "document_window", winspecs);
	
		if (theFocus == 'main') {
			self.focus();
		}
		else {
			document_window.focus();
		}
	
		if (document_window.opener == null) document_window.opener = window;
		self.name = "main";
	}

	
	function confirmAcceptance() {
		if (confirm("Click OK to confirm your acceptance of all terms and conditions listed here and within all documents listed.")) {
			return true;
		} else {
			return false;
		}
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>