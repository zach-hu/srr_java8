<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>
<%
	Object objItemLocation = (Object) request.getAttribute("InvBinLocation_itemLocation");
	Object objItemNumber = (Object) request.getAttribute("InvBinLocation_itemNumber");
	String itemLocation = "";
	String itemNumber = "";
	if (objItemLocation instanceof String[]) {
		itemLocation = ((String[])objItemLocation)[0];
	} else {
		itemLocation = (String)objItemLocation;
	}
	if (objItemNumber instanceof String[]) {
		itemNumber = ((String[])objItemNumber)[0];
	} else {
		itemNumber = (String)objItemNumber;
	}
%>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	<% Encoder encoder = DefaultEncoder.getInstance(); %>
	window.parent.browseMove('toBin', '<%= encoder.encodeForJavaScript(itemNumber)%>',
			'<%= encoder.encodeForJavaScript(itemLocation)%>', '0');

// End Hide script -->
</SCRIPT>