<%@ page import="com.tsa.puridiom.userpreference.UserPreferenceManager" %>

<% String colorscheme = UserPreferenceManager.getInstance().getUserPreference(oid, uid, "COLOR", PropertiesManager.getInstance(oid).getProperty("MISC", "COLORSCHEMEDEFAULT", "orange"));%>
	<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=contextPath%>/system/styles/<%=colorscheme%>.css">
	