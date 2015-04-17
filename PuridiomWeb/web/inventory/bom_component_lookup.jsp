<%@ page language="java" errorPage="/system/error.jsp" %>

<%
String itemNo = request.getParameter("itemNo");
String itemDesc = "Component Item Description" ;
if(itemNo != null)
{
	
	response.setContentType("text/xml");
	response.setHeader("Cache-Control", "no-cache");
	// XML formatted response:
	response.getWriter().write("<data>");
	response.getWriter().write("<itemNumber>" + itemNo + "</itemNumber>") ;
	response.getWriter().write("<description>" + itemDesc + "</description>") ;
	response.getWriter().write("</data>");

} else {
	//nothing to show
	response.setStatus(HttpServletResponse.SC_NO_CONTENT);
}
%>
