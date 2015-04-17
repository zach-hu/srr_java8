<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	String	icHeader = (String) request.getAttribute("DocAttachment_icHeader");
	String	receiptNumber = (String) request.getAttribute("ReceiptHeader_receiptNumber");
	String	returnPage = (String) request.getAttribute("returnPage");
	String	returnHandler = (String) request.getAttribute("returnHandler");
	String	InvBinLocation_icRc = (String) request.getAttribute("InvBinLocation_icRc");

	String invItemBinNumber = (String) request.getAttribute("InvBinLocation_itemNumber");
	String invItemNumber = (String) request.getAttribute("InvLocation_itemNumber");
	String invItemLocation = (String) request.getAttribute("InvLocation_itemLocation");
	String s_bin_HdrIc = (String) request.getAttribute("InvBinLocation_hdrIc");
	String s_bin_IcRc = (String) request.getAttribute("InvBinLocation_icRc");
	String s_bin_IcRecLine = (String) request.getAttribute("InvBinLocation_icRecLine");
	String gpin = (String) request.getAttribute("gpin");

	String s_umCode = HiltonUtility.ckNull((String) request.getAttribute("InvItem_unitOfOrder"));
	String s_duomUmCode = HiltonUtility.ckNull((String) request.getAttribute("InvItem_duomUmCode"));
	String dualUmRequired = HiltonUtility.ckNull((String) request.getAttribute("dualUmRequired")) ;
	String serNoRequired = HiltonUtility.ckNull((String) request.getAttribute("serNoRequired")) ;

	if (HiltonUtility.isEmpty(returnHandler))
	{
		returnHandler = "DoNothing";
	}
	String	allowEdit = (String) request.getAttribute("allowEdit");
	boolean editMode = false;

	allowEdit = HiltonUtility.ckNull(allowEdit);

	if (allowEdit.equalsIgnoreCase("Y")) {
		editMode = true;
	}
%>
<tsa:hidden name="InvBinLocation_icRc" value="<%=s_bin_IcRc%>"/>
<tsa:hidden name="InvBinLocation_hdrIc" value="<%=s_bin_HdrIc%>"/>
<tsa:hidden name="InvBinLocation_icRecLine" value="<%=s_bin_IcRecLine%>"/>
<tsa:hidden name="InvBinLocation_itemLocation" value="${InvBinLocation_itemLocation}"/>
<tsa:hidden name="InvBinLocation_itemNumber" value="<%=invItemBinNumber%>"/>
<tsa:hidden name="InvLocation_itemNumber" value="<%=invItemNumber%>"/>
<tsa:hidden name="InvLocation_itemLocation" value="<%=invItemLocation%>"/>
<tsa:hidden name="InvProperty_icProperty" value=""/>

<tsa:hidden name="DocAttachment_icHeader" value="<%=icHeader%>"/>
<tsa:hidden name="DocAttachment_docIc" value=""/>
<tsa:hidden name="DocAttachment_docSource" value="RQH"/>
<tsa:hidden name="DocAttachment_docPrint" value="N"/>
<tsa:hidden name="allowEdit" value="<%=allowEdit%>"/>
<tsa:hidden name="returnPage" value="<%=returnPage%>"/>
<tsa:hidden name="returnHandler" value="<%=returnHandler%>"/>
<tsa:hidden name="gpin" value="<%=gpin%>"/>

<tsa:hidden name="InvItem_commodity" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"InvItem_commodity\"))%>"/>
<tsa:hidden name="InvItem_unitOfOrder" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"InvItem_unitOfOrder\"))%>"/>
<tsa:hidden name="InvItem_duomUmCode" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"InvItem_duomUmCode\"))%>"/>
<tsa:hidden name="dualUmRequired" value="<%=dualUmRequired%>"/>
<tsa:hidden name="serNoRequired" value="<%=serNoRequired%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Add Property Attachment</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<% if(! HiltonUtility.isEmpty(gpin)) { %>
			<td align=right><b>GPIN #:</b></td>
			<td width=100px><%=gpin%></td>
			<% } %>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br><br><br>

<table border=0 cellpadding=0 cellspacing=0 width=680px height=200px>
<tr>
	<td valign=top align=center width=100%>
		<table border=0 cellpadding=2 cellspacing=0>
		<tr>
			<td align=right>Title:</td>
			<td><input type=text name="DocAttachment_docTitle" size=60 maxLength=60></td>
		</tr>
		<tr>
			<td align=right>File to Attach:</td>
			<td><input type=file name=file3 size=45></td>
		</tr>
		<tr><td colspan=2><br></td></tr>
		</table>

		<br><br>

		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/inventory/inv_property_attachment_add.jsp', '--'); void(0);">Add</a></div></td>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/inventory/inv_property_attachments.jsp', 'DocAttachmentRetrieveByHeader'); void(0);">Return</a></div></td>
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

	validFileTypes = "<%=PropertiesManager.getInstance(oid).getProperty("DOCUMENTS", "ValidFileTypes", "")%>";

	frm.file3.contentEditable  = false;

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
				frm.action =" <%=contextPath%>/HiltonDocumentUploadServlet";
				frm.action = frm.action + "?" + frm.organizationId.value;
				frm.enctype = "multipart/form-data";
				frm.encoding = "multipart/form-data";
			}
		}
		return true;
	}

// end hiding contents -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>