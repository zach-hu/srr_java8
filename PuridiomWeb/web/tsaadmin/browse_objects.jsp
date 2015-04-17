<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.browse.BrowseManager" %>
<%@ page import="com.tsa.puridiom.UserLogged" %>
<%
    out.println("<br><b>EXISTING BROWSES OBJECTS</b><br><br>");

	Set	availableBrowseIds = BrowseManager.getInstance().getAvailableBrowseIds();
	Map organizations = BrowseManager.getInstance().getOrganizationBrowses();
	Set	organizationIds = organizations.keySet();
	Iterator iterator = organizationIds.iterator();
    
    while (iterator.hasNext()) {
        String	organizationId = (String) iterator.next();
        
        out.println("<hr><b>" + organizationId + "<hr>");
        
        Map sessions = (Map) organizations.get(organizationId);
        
        Set	sessionIds = sessions.keySet();
		Iterator sessionIterator = sessionIds.iterator();
		
		while (sessionIterator.hasNext()) {
			String	sessionId = (String) sessionIterator.next();
			String	userId = UserLogged.getInstance(organizationId).getSessionUserId(sessionId);
			out.println("<br><b>BROWSES FOR: " + userId + " - " + sessionId + "</b>");
			
	        List browseIdList = (List) sessions.get(sessionId);
	        if (browseIdList != null) {
	        	for (int i=0; i < browseIdList.size(); i++) {
		        	String	browseId = (String) browseIdList.get(i);
		        	if (availableBrowseIds.contains(browseId)) {
		        		out.println("<br>" + browseId);
		        	}
	        	}
	        }
       		out.println("<br>");
		}
		out.println("<br><br>");
	}
%>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr><td align=center><a href="javascript: doSubmit('/tsaadmin/tsaadmin_menu.jsp'); void(0);"><img src="<%=contextPath%>/images/button_return.gif" border=0 class=button alt="Return to Tsa Admin Menu"></a></td></tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
  
	document.title = "Browse Objects";
  
// -->
// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>