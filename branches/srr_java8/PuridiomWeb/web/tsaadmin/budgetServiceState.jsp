<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@page import="java.util.Iterator"%>
<%@page import="com.tsa.service.budget.BudgetServiceJob"%>
<%@page import="com.tsa.service.budget.BudgetWrapper"%>
<%@page import="java.util.Map"%>

<%
String organizationId = (String)request.getAttribute("organizationId");
if(organizationId == null) organizationId = "DTN07P";
BudgetServiceJob  budgetService = BudgetServiceJob.getInstance();
//********************locking a budget ******

//********************END locking a budget ******



Map budgetCache = budgetService.getBudgetCache(organizationId);
if(budgetCache != null)
{
	Iterator it = budgetCache.entrySet().iterator();
	while (it.hasNext())
	{
	    Map.Entry pairs = (Map.Entry)it.next();
	    BudgetWrapper wrapper = (BudgetWrapper)pairs.getValue();
	    out.println(pairs.getKey() + "[Locked: ] = " + wrapper.isLocked()  + ", ");
	    BudgetCenter budgetCenter = (BudgetCenter)wrapper.getBudgetCenter();
	    out.println("[Pre: ] = " + budgetCenter.getPreEncumbered()  + ", ");
	    out.println("[Enc: ] = " + budgetCenter.getEncumbered() + ", ");
	    out.println("[Exp: ] = " + budgetCenter.getExpensed());
	    out.println("<br>");
	 }
}%>

<%@page import="com.tsa.puridiom.entity.BudgetCenter"%>
<br><br>

<%@ include file="/system/footer.jsp" %>

<script language='Javascript1.2' type="text/javascript">
<!--
	document.title = "Budget Service State";


//-->
</script>