<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<%
	String	s_req_ic_header = (String) request.getAttribute("DocAttachment_icHeader");
	String	s_req_number = (String) request.getAttribute("RequisitionHeader_requisitionNumber");
	String	s_req_status = (String) request.getAttribute("RequisitionHeader_status");
	String	s_fiscal_year = (String) request.getAttribute("RequisitionHeader_fiscalYear");
	String	s_req_type = (String) request.getAttribute("RequisitionHeader_requisitionType");
	String formType = (String)request.getAttribute("formType");
    String buyerId = (String)request.getAttribute("buyerId");
	String	return_page = "";

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	boolean editMode = false;

	if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0 || s_req_status.compareTo(DocumentStatus.RFQ_PRICED) == 0) {
		editMode = true;
	}
	if (s_view.equals("WIZARD")) {
		return_page = "/requests/req_review.jsp";
	} else {
		return_page = "/requests/req_summary.jsp";
	}
%>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_req_number%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=s_req_status%>"/>
<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_req_type%>"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="DocAttachment_docIc" value=""/>
<tsa:hidden name="DocAttachment_docSource" value="RQH"/>
<tsa:hidden name="DocAttachment_docPrint" value="N"/>
<tsa:hidden name="return_page" value="/requests/req_attachments.jsp"/>
<tsa:hidden name="return_handler" value="DocAttachmentRetrieveByHeader"/>
<tsa:hidden name="formType" value="<%=formType%>"/>
<tsa:hidden name="icXlsHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="buyerId" value="<%=buyerId%>"/>
<tsa:hidden name="userI" value="${userId}"/>




<%
	if (HiltonUtility.isEmpty(s_req_number)) {
		s_req_number = "N/A";
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="uploadXLS" defaultString="Upload XLS File" /></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b><tsa:label labelName="requisitionNumber" defaultString="Requisition #" />:</b></td>
			<td width=100px><%=headerEncoder.encodeForHTML(s_req_number)%></td>
		</tr>
		<tr>
			<td align=right><b><tsa:label labelName="status" defaultString="Status" />:</b></td>
			<td width=100px><%=DocumentStatus.toString(s_req_status, oid)%></td>
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
			<td align="left" class=hdr12><tsa:label labelName="importLineItems" defaultString="Import Line Items" /></td>
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
					<td  valign=top align=left><b><tsa:label labelName="importXLS" defaultString="Import .xls file" /> <img src="<%=contextPath%>/images/question_blue.gif"/></b></td>
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
					<td class=hdr12 align=center><tsa:label labelName="view_a" defaultString="View a" /> <a href ="javascript: showTemplateXls(); void(0);"><tsa:label labelName="sampleXLSFile" defaultString="sample .xls file" /></a>
	                                 <tsa:label labelName="useAsReference" defaultString="to use as a reference for creating your file" />.</td>
				</tr>
			</table>
			<br><br>
			<table border=0 bordercolor=lightShadow cellpadding=2 cellspacing=0 width=100%>
				<tr>
					<td  valign=top align=left><b><tsa:label labelName="importOptions" defaultString="Import Options" />:</b></td>
				</tr>
				<tr>
				 <td>
					<table border=0 cellpadding=2 cellspacing=0>
						<tr align=center>
							<td align=center><tsa:input type="radio" name="checkInsert" value="Before" checked="true" /></td>
							<td  align=center><b><tsa:label labelName="insertBeforeFirstItem" defaultString="Insert Before First Item" /></b></td>
						</tr>
						<tr align=center>
							<td align=center><tsa:input type="radio" name="checkInsert" value="Overwrite" /></td>
							<td  align=center><b><tsa:label labelName="overwriteExistingItems" defaultString="Overwrite Existing Items" /></b></td>
						</tr>
						<tr align=center>
							<td align=center><tsa:input type="radio" name="checkInsert" value="After" /></td>
							<td  align=center><b><tsa:label labelName="appendAfterLastItem" defaultString="Append After Last Item" /></b></td>
						</tr>
					</table>
				 </td>
				</tr>
			</table>
			<br><br>
			<table border=0 bordercolor=lightShadow cellpadding=0 cellspacing=0 width=100%>
			<tr>
				<td align=center width=50%><a href="javascript: doSubmit('/requests/req_xls_upload_items_add.jsp', '--'); void(0);"><img src="<%=contextPath%>/images/button_add.gif" border=0 class=button></a></td>
				<td align=center width=50%><a href="javascript: doSubmit('/requests/req_review.jsp', 'RequisitionRetrieve'); void(0);"><img src="<%=contextPath%>/images/button_return.gif" border=0 class=button></a></td>
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
						<td  align=center><b><tsa:label labelName="howImportItemFile" defaultString="How to Import an Item File" /></b></td>
					</tr>
			</table>
			<table border=0 bordercolor=lightShadow cellpadding=2 cellspacing=0>
						<tr>
						<td align=left>1.</td>
						<td  align=left><b><tsa:label labelName="importedFileItemMust" defaultString="The Imported Item File must be in" /></b>
							<b><a href ="javascript: showTemplateXls(); void(0);"><tsa:label labelName="xlsFormat" defaultString=".xls format" /></a></b>
						</td>
						</tr>
						<tr>
						<td valign=top align=left>2.</td>
						<td align=left><b><tsa:label labelName="xlsFileMustContain" defaultString="The .xls file must contain the following headers" />:</b>
							<table border=0 cellpadding=2 cellspacing=0>
								<tr><td>* <tsa:label labelName="itemNumber" defaultString="Item Number" /></td></tr>
								<tr><td>* <tsa:label labelName="description" defaultString="Description" /></td></tr>
								<tr><td>* <tsa:label labelName="quanty" defaultString="Quanty" /></td></tr>
								<tr><td>* <tsa:label labelName="unitMeasure" defaultString="Unit Of Measure (EA, DZ)" /></td></tr>
								<tr><td>* <tsa:label labelName="unitPrice" defaultString="Unit Price" /></td></tr>
							</table>
							<b><tsa:label labelName="downloadSampleFile" defaultString="Download Sample Item File" /> :</b>
							<b><a href ="javascript: downloadXlsFile(); void(0);"><tsa:label labelName="sampleXLS" defaultString="sample .xls" /></a></b>
						</td>
						</tr>
						<tr>
						<td align=left>3.</td>
						<td  align=left><b><tsa:label labelName="importXLSFileBrowse" defaultString="Import your .xls file by selecting Browse" /></b></td>
						</tr>
						<tr>
							<td align=left valign=top>4.</td>
							<td  align=left><b><tsa:label labelName="selectWhereWantImport" defaultString="Select Where you Want the Import" /> </b>
											<b><tsa:label labelName="toInsertItems" defaultString="to Insert the Items at" />:</b>
								<table border=0 cellpadding=2 cellspacing=0>
									<tr>
									<td>
									<b>* <tsa:label labelName="selectingOverwriteItems" defaultString="Selecting Overwrite existing items, will delete all items in your" /></b>

									<b><tsa:label labelName="currentShoppingCart" defaultString="current Shopping Cart" /></b>
									</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td align=left>5.</td>
							<td  align=left><b><tsa:label labelName="clicAddToImportFile" defaultString="Click On Add to import your file" />.</b>
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
<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	validFileTypes = ".XLS";
	var reqnumber = "<%=headerEncoder.encodeForJavaScript(s_req_number)%>";
	var fiscalyear = "<%=s_fiscal_year%>";

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
			//	frm.submit();
			}
		}
		return true;
	}

	 function showTemplateXls(){
 		var imgFileName = "";
		directoryName = "";
		var imageUrl = "<%=DictionaryManager.getInstance("host", oid).getProperty("item-image-url")%>";
        var imageFile = "<%=propertiesManager.getProperty("MISC", "UPLOADITEMSFILEIMAGE", "TemplateXls.png")%>";

		if (imageUrl.substring(imageUrl.length - 1) != "/") {
			imageUrl = imageUrl + "/";
		}

		imageUrl = imageUrl + directoryName + "/";
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
	  doSubmit('/requests/req_xls_upload_items_new.jsp', 'DocAttachmentDownloadFile');
	}

// end hiding contents -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>