<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.utility.BlackBox" %>
<%
	String decrypted = request.getParameter("decryptedPassword");
	String password = BlackBox.getEncrypt(decrypted);
	
	out.println("Password for " + decrypted + " = " + password);
%>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr><td align=center><a href="javascript: doSubmit('/tsaadmin/tsaadmin_menu.jsp'); void(0);"><img src="<%=contextPath%>/images/button_return.gif" border=0 class=button alt="Return to Tsa Admin Menu"></a></td></tr>
</table>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
  
	document.title = "Encrypted Password";
  
// -->
// End Hide script -->
</SCRIPT>