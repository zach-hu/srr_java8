<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>

<%@ page import="com.tsa.puridiom.entity.InvProperty" %>

<% //this page must be included AFTER header.jsp
List serialNumbers = (List) request.getAttribute("addedProperty");

if (serialNumbers != null) {
	%><tsa:hidden name="inv_property_size" value="<%= serialNumbers.size() %>"/><%

	for (int i = 0; i < serialNumbers.size(); i++) {
		InvProperty prop = (InvProperty)serialNumbers.get(i);

		%><tsa:hidden name="inv_property_<%= i %>" value="<%= prop.getSerialNumber() %>"/>
		<tsa:hidden name="inv_property_remarks_<%= i %>" value="<%= prop.getRemarks() %>"/><%
	}
} else {
	%><tsa:hidden name="inv_property_size" value="0"/><%
}
%>