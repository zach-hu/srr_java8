<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.entity.VendorDocument" %>
<%@ page import="com.tsa.puridiom.entity.VendorDocumentPK" %>
<script language='Javascript1.2' src="<%=contextPath%>/supplierportal/scripts/attachments.js"></script>
<%
	String	icHeader = "0";
	String	returnPage = "";
	String	attachmentType = "Profile";
	String	currentPage = "/supplierportal/user/supplier_attachments.jsp";
	String	docType = HiltonUtility.ckNull((String)request.getAttribute("VendorDocument_docType"));
	boolean editMode = true;
%>
<%@ include file="/supplierportal/documents/attachment_list.jsp" %>
<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	function addNewDocument() {
		doSubmit('/supplierportal/user/supplier_attachment_new.jsp', 'DoNothing');
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/supplierportal/system/prevent_caching.jsp" %>