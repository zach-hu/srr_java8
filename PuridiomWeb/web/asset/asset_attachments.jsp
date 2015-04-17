<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/attachments.js"></script>
<%
	//In this part we get information of asset and DocAttachment entities using the icHeader attribute -->
	String	icHeader = (String) request.getAttribute("DocAttachment_icHeader");
	String	assetNumber = (String) request.getAttribute("Asset_tagNumber");
	String	assetTrackingNumber = (String) request.getAttribute("Asset_trackingNumber");
	String	assetItemNumber = (String) request.getAttribute("Asset_itemNumber");
	boolean editMode = false;
	String	returnPage = "";


	if (s_view.equals("WIZARD")) {
		returnPage = "/asset/asset_general.jsp";
	}
%>
<tsa:hidden name="Asset_tagNumber" value="<%=assetNumber%>"/>
<tsa:hidden name="Asset_trackingNumber" value="<%=assetTrackingNumber%>"/>
<tsa:hidden name="Asset_itemNumber" value="<%=assetItemNumber%>"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=icHeader%>"/>
<tsa:hidden name="filename" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<%
	if (HiltonUtility.isEmpty(assetNumber)) {
		assetNumber = "N/A";
	}
%>

<table width="<%=formWidth%>" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tsa:tr>
			<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="attachments" defaultString="Attachments" /></div>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" width="3px"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<tsa:td valign="bottom" height="30px" width="*">
		<table border="0" cellspacing="0" cellpadding="1" width="100%">
		<!--In this part we show the ItemNumber and TrackingNumber attributes of asset-->
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="itemnumber" defaultString="ItemNumber #" />:</b></tsa:td>
			<tsa:td width="125px"><%=assetItemNumber%></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="trackingnumber" defaultString="TrackingNumber #" />:</b></tsa:td>
			<tsa:td width="125px"><%=assetTrackingNumber%></tsa:td>
		</tsa:tr>

		</table>
		<table cellpadding="0" cellspacing="0" border="0">
		<tsa:tr>
			<tsa:td width="100%" height="1px" cssClass="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td height="2px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>
<br>

<!--In this part we can choose some options like print or delete-->
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr>
	<tsa:td valign="top" align="center">
		<div id="attachmentList" style="visibility: visible; display: block '">
			<table border="0" cellpadding="1" cellspacing="0" width="500px">
			<tsa:tr>
				<tsa:td width="5%">&nbsp;</tsa:td>
				<tsa:td width="65%"><b><tsa:label labelName="documenttitle" defaultString="Document Title" /></b></tsa:td>
				<tsa:td width="5%">&nbsp;</tsa:td>
				<tsa:td width="10%" align="center"><b><tsa:label labelName="print" defaultString="Print" /></b></tsa:td>
<%		if (editMode) { %>
				<tsa:td width="15%" align="center"><b><tsa:label labelName="delete" defaultString="Delete" /></b></tsa:td>
<%		} else {%>
				<tsa:td width="15%" align="center">&nbsp;</tsa:td>
<%		}%>
			</tsa:tr>
			<tsa:tr>
				<tsa:td colspan="8" valign="top">
					<div id="noAttachmentsMsg" style="visibility: hidden; display: none;">
					<table border="0" cellpadding="1" cellspacing="0" width="500px">
					<tsa:tr><tsa:td width="100%" align="center"><br><tsa:label labelName="attachingmessage" defaultString="There are currently no documents attached." /><br></tsa:td></tsa:tr>
					</table>
					</div>

<!--In this part we have a list that contain the attachment files in diferents ways and formats like doc, pdf, xls, gif, and bmp-->
<table id="attachments" border="0" cellpadding="1" cellspacing="0" width="500px">
<%
	List list = (List) request.getAttribute("docAttachmentList");

	if (list != null) {
		for (int i = 0; i < list.size(); i++) {
			DocAttachment docAttachment = (DocAttachment) list.get(i);
			DocAttachmentPK docAttachmentPK = (DocAttachmentPK) docAttachment.getComp_id();
			String	filename = docAttachment.getDocFilename();
			String	ext = filename.substring(filename.lastIndexOf(".") + 1);
%>
					<tsa:tr>
						<tsa:td width="5%" align="right" id="doc_num_<%=i%>">&nbsp;<%=i+1%>.&nbsp;<tsa:hidden name="docIc" value="<%=docAttachment.getComp_id().getDocIc()%>"/><tsa:hidden name="icHeader" value="<%=docAttachment.getComp_id().getIcHeader()%>"/></tsa:td>
						<tsa:td width="65%"><tsa:input type="text" name="docTitle" value = "<%=docAttachment.getDocTitle()%>" maxLength="60" size="60"/></tsa:td>
						<tsa:td width="5%" valign="middle" align="center">
							<tsa:hidden name="docFilename" value = "<%=filename%>"/>
<%			if (ext.equalsIgnoreCase("DOC")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border="0" alt="Open Attached MS Word Document"></a>
<%			} else if (ext.equalsIgnoreCase("PDF")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border="0" alt="Open Attached Adobe PDF Document"></a>
<%			} else if (ext.equalsIgnoreCase("XLS")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border="0" alt="Open Attached MS Excel Document"></a>
<%			} else if (ext.equalsIgnoreCase("MPP")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border="0" alt="Open Attached MS Project Document"></a>
<%			} else if (ext.equalsIgnoreCase("PPT")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border="0" alt="Open Attached MS Powerpoint Document"></a>
<%			} else if (ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border="0" alt="Open Attached Image"></a>
<%			} else {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border="0" alt="Open Attached Document"></a>
<%			}%>
						</tsa:td>
						<tsa:td width="10%" align="center">
						<% if (docAttachment.getDocPrint().indexOf("Y")>= 0) {%>
						<tsa:input type="checkbox" name="cboxPrint" checked="checked" value="Y" onclick="setPrint(<%=i%>);" />
						<%} else {%>
						<tsa:input type="checkbox" name="cboxPrint" value="Y" onclick="setPrint(<%=i%>);" />
						<%} %>
							<tsa:hidden name="docPrint" value="<%=docAttachment.getDocPrint()%>"/>
						</tsa:td>
<%			if (editMode) { %>
						<tsa:td width="15%" id="doc_del_<%=i%>" align="center"><a href="javascript: if (verifyAction('Are you sure you wish to delete Attachment line <%=(i+1)%>?')) { deleteMe(<%=i%>); } "><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0"></a></tsa:td>
<%			} else { %>
						<tsa:td width="15%">&nbsp;</tsa:td>
<%			} %>
					</tsa:tr>
<%		}
	} %>
					</table>
				</tsa:td>
			</tsa:tr>
			</table>
			<br>
			<table border="0" cellpadding="0" cellspacing="0" width="500px">
			<tsa:tr><tsa:td noWrap="nowrap" align="center"><a href="javascript: doSubmit('/asset/asset_attachment_new.jsp', 'DoNothing'); void(0)"><font class="reset"><b><tsa:label labelName="addnew" defaultString="Add new" /></b></font></a></tsa:td></tsa:tr>
			</table>
		</div>
	</tsa:td>
</tsa:tr>
</table>

<br>
<br>
<!--In this part we have two buttons, the first is used to save an attachement file and the second to return to the last page-->
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr>
	<tsa:td width="50%" align="center"><a href="javascript: saveAttachments(); void(0);"><img class="button" src="<%=contextPath%>/images/button_save.gif" border="0"></a></tsa:td>
	<tsa:td width="50%" align="center"><a href="javascript: doSubmit('/asset/asset_general.jsp', 'AssetRetrieveById'); void(0);"><img class="button" src="<%=contextPath%>/images/button_return.gif" border="0"></a></tsa:td>
</tsa:tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value="JavaScript">
<!-- Hide script


	frm = document.purchaseform;

	inEditMode = <%=editMode%>;
	currentPage = "/asset/asset_attachments.jsp";
	returnPage = "<%=headerEncoder.encodeForJavaScript(returnPage)%>";
	returnHandler = "AssetRetrieveById";
	myTable = document.getElementById("attachments");
	totalRows = myTable.rows.length


// End Hide script -->
</SCRIPT>
