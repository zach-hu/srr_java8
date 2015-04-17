<%
	String errorMessage = HiltonUtility.ckNull((String) request.getAttribute("errorMessage"));
%>
<div>
	<% if(!errorMessage.equalsIgnoreCase("")){ %>
	<table>
	<tr><td class=error align=center><%=errorMessage %></td></tr>
	</table>
	<%} %>
</div>


