<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<%
	//In this part we get some parameters like icHeader, assetNumber, assetTrackingNumber, assetItemNumber -->
	String	icHeader = (String) request.getAttribute("DocAttachment_icHeader");
	String	assetNumber = (String) request.getAttribute("Asset_tagNumber");
	String	assetTrackingNumber = (String) request.getAttribute("Asset_trackingNumber");
	String	assetItemNumber = (String) request.getAttribute("Asset_itemNumber");


	String	returnPage = "";
	boolean editMode = false;

	if (s_view.equals("WIZARD")) {
		returnPage = "/asset/asset_review.jsp";
	} else {
		returnPage = "/asset/asset_summary.jsp";
	}
%>
<tsa:hidden name="Asset_trackingNumber" value="<%=assetTrackingNumber%>"/>
<tsa:hidden name="Asset_itemNumber" value="<%=assetItemNumber%>"/>
<tsa:hidden name="Asset_tagNumber" value="<%=assetNumber%>"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=icHeader%>"/>
<tsa:hidden name="DocAttachment_docIc" value=""/>
<tsa:hidden name="DocAttachment_docSource" value="RQH"/>
<tsa:hidden name="DocAttachment_docPrint" value="N"/>
<tsa:hidden name="returnPage" value="/asset/asset_attachments.jsp"/>
<tsa:hidden name="returnHandler" value="DocAttachmentRetrieveByHeader"/>


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
		<!--In this part we show the ItemNumber and TrackingNumber attributes of asset-->
		<table border="0" cellspacing="0" cellpadding="1" width="100%">
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

<br><br><br>

<table border="0" cellpadding="0" cellspacing="0" width="680px" height="200px">
<tsa:tr>
	<tsa:td valign="top" align="center" width="100%">
		<table border="0" cellpadding="2" cellspacing="0">
		<tsa:tr>
			<tsa:td align="right"><tsa:label labelName="title" defaultString="Title" />:</tsa:td>
			<tsa:td><tsa:input type="text" name="DocAttachment_docTitle" size="60" maxLength="60"/></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="right"><tsa:label labelName="fileattach" defaultString="File to Attach" />:</tsa:td>
			<tsa:td><input type="file" name="file3" size="45"></tsa:td>
		</tsa:tr>
		<tsa:tr><tsa:td colspan="2"><br></tsa:td></tsa:tr>
		<tsa:tr>
			<tsa:td colspan="2" align="center"><tsa:input type="checkbox" name="ckboxPrint" value="Y"/>&nbsp;<b><tsa:label labelName="printasset" defaultString="Print with Asset" /></b></tsa:td>
<!--			<td align=center><b>Print with et</b></td>-->
		</tsa:tr>
		</table>

		<br><br>
		<!--In this part we have two buttons, the first is used to add an attachement file and the second to return to the last page-->
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tsa:tr>
			<tsa:td align="center" width="50%"><a href="javascript: doSubmit('/asset/asset_attachment_add.jsp', '--'); void(0);"><img src="<%=contextPath%>/images/button_add.gif" border="0" class="button"></a></tsa:td>
			<tsa:td align="center" width="50%"><a href="javascript: doSubmit('/asset/asset_attachments.jsp', 'DocAttachmentRetrieveByHeader'); void(0);"><img src="<%=contextPath%>/images/button_return.gif" border="0" class="button"></a></tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>
<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	validFileTypes = "<%=PropertiesManager.getInstance(oid).getProperty("DOCUMENTS", "ValidFileTypes", "")%>";

	frm.file3.contentEditable  = false;

	<!--This function follow to enter diferents parameteres to attach a document like the name, the document title or configure some features as printed-->
	function validateForm() {
		var handler = frm.handler.value;

		if (handler.indexOf("--") >= 0) {
			var doc = frm.file3.value;
			var title = frm.DocAttachment_docTitle.value;
			var alertMessage = "";

			if ( isEmpty(title) && isEmpty(doc) ) {
				alert("Please enter a Title and select a File to Attach.");
				return false;
			}
			else if (isEmpty(doc)) {
				alert('Please select a file to attach.');
				return false;
			}
			else if (!isAttachmentExtValid(doc)) {
				return false;
			}
			else if (isEmpty(title)) {
				alert('Please enter a Document Title.');
				return false;
			}
			else {
				if (frm.ckboxPrint.checked) {
					frm.DocAttachment_docPrint.value = "Y";
				} else {
					frm.DocAttachment_docPrint.value = "N";
				}
				frm.action =" <%=contextPath%>/HiltonDocumentUploadServlet";
				frm.action = frm.action + "?" + frm.organizationId.value;
				frm.enctype = "multipart/form-data";
				frm.encoding = "multipart/form-data";
			//	frm.submit();
			}
		}
		return true;
	}

// end hiding contents -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>