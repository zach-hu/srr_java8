<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	String	icHeader = (String) request.getAttribute("DocAttachment_icHeader");
	String	s_ic_po_header = (String) request.getAttribute("PoHeader_icPoHeader");
	String	s_po_number = (String) request.getAttribute("PoHeader_poNumber");
	String	s_release_number = (String) request.getAttribute("PoHeader_releaseNumber");
	String	s_revision_number = (String) request.getAttribute("PoHeader_revisionNumber");
	String	s_po_status = (String) request.getAttribute("PoHeader_status");
	String formType = (String)request.getAttribute("formType");

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

	BigDecimal	bd_zero = new BigDecimal(0);
	BigDecimal	bd_release_number = new BigDecimal(s_release_number);
	BigDecimal	bd_revision_number = new BigDecimal(s_revision_number);

	if (s_po_number == null)
	{
		s_po_number = (String) request.getAttribute("formNumber");
	}
	if (s_ic_po_header == null)
	{
		s_ic_po_header = (String) request.getAttribute("DocComment_icHeader");
	}
	if (s_po_status == null)
	{
		s_po_status = (String) request.getAttribute("formStatus");
	}
%>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
<tsa:hidden name="PoHeader_status" value="<%=s_po_status%>"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=icHeader%>"/>
<tsa:hidden name="DocAttachment_docIc" value=""/>
<tsa:hidden name="DocAttachment_docSource" value="RQH"/>
<tsa:hidden name="DocAttachment_docPrint" value="N"/>
<tsa:hidden name="returnPage" value="/orders/po_attachments.jsp"/>
<tsa:hidden name="returnHandler" value="DocAttachmentRetrieveByHeader"/>
<tsa:hidden name="formType" value="<%=formType%>"/>
<tsa:hidden name="icXlsHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="userI" value="${userid}"/>


<%
	if (HiltonUtility.isEmpty(s_po_number)) {
		s_po_number = "N/A";
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Upload XLS File</div>
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
			<td width=100px><%=s_po_number%></td>
<%	if (bd_release_number.compareTo(bd_zero) > 0)
		{
			i_colspan = i_colspan + 2; %>
			<td nowrap align=right><b>Release #:</b></td>
			<td width=100px><%=bd_release_number%></td>
<%	}
		if (bd_revision_number.compareTo(bd_zero) > 0)
		{
			i_colspan = i_colspan + 2; %>
			<td nowrap align=right><b>Revision #:</b></td>
			<td width=100px><%=bd_revision_number%></td>
<%	} %>
		</tr>
		<tr>
			<td colspan=<%=i_colspan%> nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=100px><%=DocumentStatus.toString(s_po_status, oid)%></td>
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
<table border=1 cellpadding=0 cellspacing=0 width=680px height=33px>
		<tr>
			<td align="left" class=hdr12>Import Line Items</td>
		</tr>
</table>
<table border=0 bordercolor=lightShadow cellpadding=0 cellspacing=0 width=680px>
<tr width=100%>
 <td width=40%>
	<table border=1 bordercolor=lightShadow cellpadding=0 cellspacing=0 height=444px width=100%>
	<tr>
		<td valign=top align=left width=100%>
			<table  valign=top border=0 cellpadding=2 cellspacing=0 width=100%>
				<tr>
					<td  valign=top align=left><b>Import .xls file <img src="<%=contextPath%>/images/question_blue.gif"/></b></td>
				</tr>
			</table>
			<br><br><br>
			<table border=0 cellpadding=2 cellspacing=0>
			<tr>
				<td><input type=file name=file3 size=44></td>
			</tr>
			<tr><td colspan=2><br></td></tr>
			</table>
			<table border=1 cellpadding=2 cellspacing=0 width=100%>
				<tr>
					<td class=hdr12 align=center>View a <a href ="javascript: showTemplateXls(); void(0);">sample .xls file</a>
	                                 to use as a reference for creating your file.</td>
				</tr>
			</table>
			<br><br>
			<table border=0 bordercolor=lightShadow cellpadding=2 cellspacing=0 width=100%>
				<tr>
					<td  valign=top align=left><b>Import Options:</b></td>
				</tr>
				<tr>
				 <td>
					<table border=0 cellpadding=2 cellspacing=0>
						<tr align=center>
							<td align=center><input type=radio name=checkInsert value="Before" checked></td>
							<td  align=center><b>Insert Before First Item</b></td>
						</tr>
						<tr align=center>
							<td align=center><input type=radio name=checkInsert value="Overwrite"></td>
							<td  align=center><b>Overwrite Existing Items</b></td>
						</tr>
						<tr align=center>
							<td align=center><input type=radio name=checkInsert value="After"></td>
							<td  align=center><b>Append After Last Item</b></td>
						</tr>
					</table>
				 </td>
				</tr>
			</table>
			<br><br>
			<table border=0 bordercolor=lightShadow cellpadding=0 cellspacing=0 width=100%>
			<tr>
				<td align=center width=50%><a href="javascript: doSubmit('/orders/po_xls_upload_items_add.jsp', '--'); void(0);"><img src="<%=contextPath%>/images/button_add.gif" border=0 class=button></a></td>
				<td align=center width=50%><a href="javascript: doSubmit('/orders/po_review.jsp', 'PoRetrieve'); void(0);"><img src="<%=contextPath%>/images/button_return.gif" border=0 class=button></a></td>
			</tr>
			</table>
	</td>
	<td width=20%>
	</td>
	<td width=40%>
	<table border=6 bordercolor=lightShadow cellpadding=0 cellspacing=0 height=444px width=340px>
		<tr>
			<td valign=top align=left width=100%>
			<table border=0 cellpadding=2 cellspacing=0 width=100%>
					<tr class=hdr12  width=100%>
						<td  align=center><b>How to Import an Item File</b></td>
					</tr>
			</table>
			<table border=0 bordercolor=lightShadow cellpadding=2 cellspacing=0>
						<tr>
						<td align=left>1.</td>
						<td  align=left><b>The Imported Item File must be in</b>
							<b><a href ="javascript: showTemplateXls(); void(0);">.xls format</a></b>
						</td>
						</tr>
						<tr>
						<td valign=top align=left>2.</td>
						<td align=left><b>The .xls file must contain the following headers:</b>
							<table border=0 cellpadding=2 cellspacing=0>
								<tr><td>* Item Number</td></tr>
								<tr><td>* Description</td></tr>
								<tr><td>* Quanty</td></tr>
								<tr><td>* Unit Of Measure (EA, DZ)</td></tr>
								<tr><td>* Unit Price</td></tr>
							</table>
							<b>Download Sample Item File :</b>
							<b><a href ="javascript: downloadXlsFile(); void(0);">sample .xls</a></b>
						</td>
						</tr>
						<tr>
						<td align=left>3.</td>
						<td  align=left><b>Import your .xls file by selecting "Browse"</b></td>
						</tr>
						<tr>
							<td align=left valign=top>4.</td>
							<td  align=left><b>Select Where you Want the Import </b>
											<b>to Insert the Items at:</b>
								<table border=0 cellpadding=2 cellspacing=0>
									<tr>
									<td>
									<b>* Selecting Overwrite existing items, will delete all items in your</b>
									<b>current Shopping Cart</b>
									</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td align=left>5.</td>
							<td  align=left><b>Click On "Add" to import your file.</b>
							</td>
						</tr>
					</table>
					</tr>
				</table>
			</td>
		</tr>
		</table>
		</td>
	</tr>
</table>
<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	validFileTypes = ".XLS";

	frm.file3.contentEditable  = false;

	function checkInsertFunction() {
		var insert = "";
		for(var i = 0; i < frm.checkInsert.length; i++)
		{
			if(frm.checkInsert[i].checked)
			{
				insert = frm.checkInsert[i].value;
			}
		}
		return insert;
    }

    function checkInsertSelectFunction() {
    alert(checkInsertFunction());
    }


	function validateForm() {
		var handler = frm.handler.value;

		if (handler.indexOf("--") >= 0) {
			var doc = frm.file3.value;
			//var title = frm.DocAttachment_docTitle.value;
			var alertMessage = "";

			if (isEmpty(doc)) {
				alert('Please select a file to attach.');
				return false;
			}
			else if (!isXlsExtValid(doc)) {
				return false;
			}
			else {
				frm.action ="<%=contextPath%>/HiltonDocumentXlsUploadServlet";
				frm.action = frm.action + "?" + frm.organizationId.value;
				frm.action = frm.action + ";" + frm.formType.value;
				frm.action = frm.action + ";" + frm.icXlsHeader.value;
				frm.action = frm.action + ";" + frm.userI.value;
				frm.action = frm.action + ";" + checkInsertFunction();
				frm.enctype = "multipart/form-data";
				frm.encoding = "multipart/form-data";
			}
		}
		return true;
	}


	function showTemplateXls(){

 		var imgFileName = "";
		var imageUrl = "<%=DictionaryManager.getInstance("host", oid).getProperty("item-image-url", "\\")%>";
        var imageFile = "<%=propertiesManager.getProperty("MISC", "UPLOADITEMSFILEIMAGE", "TemplateXls.png")%>";

		if (imageUrl.substring(imageUrl.length - 1) != "/") {
			imageUrl = imageUrl + "/";
		}

		imgFileName = imageFile;

		if (imgFileName.indexOf("http://") >= 0)
		{
			doSubmitToPopup(imgFileName, 'DoNothing', 'WIDTH=1000','HEIGHT=700');
		}
		else
		{
			doSubmitToPopup(imageUrl + imgFileName, 'DoNothing', 'WIDTH=1000','HEIGHT=700');
		}
	}

	function downloadXlsFile() {
	  var xlsFile = "<%=propertiesManager.getProperty("MISC", "UPLOADITEMSFILEXLSNAME", "Req_Rfq_PO_Upload.xls")%>";
	  var filename = xlsFile;
	  var newInputFields = "<input type='hidden' name='filename' value='" + filename + "'>" +
	  						"<input type='hidden' name='fromUploadItems' value='Y'>";
	  setHiddenFields(newInputFields);
	  doSubmit('/orders/po_xls_upload_items_new.jsp', 'DocAttachmentDownloadFile');
	}

// end hiding contents -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>