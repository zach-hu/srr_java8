<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%
	String	icHeader = "0";
	String	returnPage = "/supplierportal/user/supplier_attachments.jsp";
	String	attachmentType = "Profile";
	String	docType = HiltonUtility.ckNull((String)request.getAttribute("VendorDocument_docType"));
	boolean editMode = true;
%>

<%@ include file="/supplierportal/documents/attachment_new.jsp" %>
<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	function addDocument() {
		doSubmit('/supplierportal/user/supplier_attachment_add.jsp', '--');
	}

// end hiding contents -->
</SCRIPT>

<%@ include file="/supplierportal/system/prevent_caching.jsp" %>