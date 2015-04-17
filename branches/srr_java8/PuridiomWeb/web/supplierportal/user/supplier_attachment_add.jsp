<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%
	String	icHeader = "0";
	String	vendorId = (String) request.getAttribute("VendorDocument_vendorId");
	String	docTitle = (String) request.getAttribute("VendorDocument_docTitle");
	String	docFilename = (String) request.getAttribute("VendorDocument_docFilename");
	String	docType = HiltonUtility.ckNull((String)request.getAttribute("VendorDocument_docType"));
	String	returnPage = (String) request.getAttribute("returnPage");
	String	returnHandler = (String) request.getAttribute("returnHandler");
	String	attachmentType = "Profile";
%>
<%@ include file="/supplierportal/documents/attachment_add.jsp" %>
<%@ include file="/supplierportal/system/footer.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>