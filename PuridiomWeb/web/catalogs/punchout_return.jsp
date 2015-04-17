<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ page import="com.tsa.puridiom.punchoutcatalog.PunchOutRequestManager" %>
<%@ page import="com.tsa.puridiom.common.utility.HiltonUtility" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.util.*" %>
<%
	session.setAttribute("csrfVerified", Boolean.TRUE);
	try {
		session.setAttribute("nonce", SecureRandom.getInstance("SHA1PRNG").nextLong());
	} catch (Exception e) {
		e.printStackTrace();
	}
	String	requestId = request.getParameter("puridiomRequestId");
	Map requestParameters = PunchOutRequestManager.getInstance().getRequestParameters(requestId);
	String	handler = (String) requestParameters.get("punchOutReturnHandler");
	String	successPage = (String) requestParameters.get("punchOutReturnSuccessPage");
	Catalog	catalog = (Catalog)  requestParameters.get("catalog");

	String urlencoded = (String)request.getParameter("cxml-urlencoded");

	if(!HiltonUtility.isEmpty(urlencoded))
	{
		requestParameters.put("cxml-urlencoded", request.getParameter("cxml-urlencoded"));
	}
	else
	{
		requestParameters.put("cxml-urlencoded", request.getParameter("cXML-urlencoded"));
	}

	PunchOutRequestManager.getInstance().setRequestParameters(requestId, requestParameters);

	request.setAttribute("userId", (String) requestParameters.get("userId"));
	request.setAttribute("organizationId", (String) requestParameters.get("organizationId"));
	request.setAttribute("viewType", (String) requestParameters.get("viewType"));

	if (catalog == null) {
		catalog = new Catalog();
		catalog.setTitle("Punchout Catalog");
	}
%>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<tsa:hidden name="puridiomRequestId" value="<%=requestId%>"/>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=100% align=center valign=top><br><b>Processing <%=catalog.getTitle()%> Items... Please wait.</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border=1 width=200px height=15px></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	
	doSubmit('<%=successPage%>', '<%=handler%>');

	function checkHiddenMenu()
	{
		hideArea("navTable");
		displayArea("menubarSpacer");
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>