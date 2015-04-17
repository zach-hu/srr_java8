<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>

<% //this page must be included AFTER header.jsp
Map propertyMap = (Map)request.getAttribute("selectedProperty");

if (propertyMap != null) {
	Set keys = propertyMap.keySet();

	//key is bin location icrc
	//value is serial number
	%><tsa:hidden name="disb_property_numberOfKeys" value="<%= keys.size() %>"/><%
	Iterator itr = keys.iterator();
	for (int i = 0; i < keys.size(); i++) {
		String key = itr.next().toString();

		List serialNumbers = (List) propertyMap.get(key);

		%><tsa:hidden name="disb_property_<%= i %>" value="<%= key %>"/>
		<tsa:hidden name="disb_property_<%= i %>_size" value="<%= serialNumbers.size() %>"/><%

		for (int j = 0; j < serialNumbers.size(); j++) {
			String serialNumber = serialNumbers.get(j).toString();
			%><tsa:hidden name="<%= \"disb_property_\" + i + \"_\" + j %>" value="<%= serialNumber %>"/><%
		}
	}
} else {
	%><tsa:hidden name="disb_property_numberOfKeys" value="0"/><%
}
%>