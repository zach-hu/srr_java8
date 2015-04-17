<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.RequisitionType" %>

<%@ page import="com.tsa.puridiom.steporder.*" %>

<%
	ProcessSteps steps = null;
	String s_req_type = (String) request.getAttribute("RequisitionHeader_requisitionType");

	if (s_req_type.equals("S"))		/*	Supply Request	*/
	{
		steps = new ProcessSteps("reqsteps_s", oid);
	}
	else if (s_req_type.equals("Z"))	/* Imprint Request */
	{
		steps = new ProcessSteps("reqsteps_z", oid);
	}
	else if (s_req_type.equals("T"))	/* Transfer Request */
	{
		steps = new ProcessSteps("reqsteps_t", oid);
	}
	else if (s_req_type.equals("R"))	/* Replenish Request */
	{
		steps = new ProcessSteps("reqsteps_r", oid);
	}
	else if (s_req_type.equals("I"))	/* Inventory Return */
	{
		steps = new ProcessSteps("reqsteps_i", oid);
	}
	else if (s_req_type.equals("U"))	/* Purchase Return */
	{
		steps = new ProcessSteps("reqsteps_u", oid);
	}
	else if (s_req_type.equals("C"))	/* Change Request */
	{
		steps = new ProcessSteps("reqsteps_c", oid);
	}
	else if (s_req_type.equals("E"))	/* Release Request */
	{
		steps = new ProcessSteps("reqsteps_e", oid);
	}
	else if (s_req_type.equals("N"))	/* Pricing Request */
	{
		steps = new ProcessSteps("reqsteps_n", oid);
	}
	else if (s_req_type.equals("A"))	/* Capital Request */
	{
		steps = new ProcessSteps("reqsteps_a", oid);
	}
	else if (s_req_type.equals("K"))	/* Admin Check Request */
	{
		steps = new ProcessSteps("reqsteps_k", oid);
	}
	else if (s_req_type.equals("O"))	/* Project Request */
	{
		steps = new ProcessSteps("reqsteps_o", oid);
	}
	else		/* Purchase Request */
	{
		steps = new ProcessSteps("reqsteps_p", oid);
	}

%>

<tsa:hidden name="formtype" value="REQ"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_req_type%>"/>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	
	var s_link = "<%=steps.getLink(0)%>";
	var s_method = "<%=steps.getMethod(0)%>";
	var s_process = "<%=steps.getProcess(0)%>";

	if (s_process == "SHOPCART")
	{
		setNavCookie("/requests/req_items.jsp", "RequisitionCreate", "<%=headerEncoder.encodeForJavaScript(RequisitionType.toString(s_req_type, oid))%>");
		doSubmit('/requests/req_items.jsp', 'RequisitionCreate');
	}
	else
	{
		setNavCookie(s_link, "RequisitionCreate", "<%=headerEncoder.encodeForJavaScript(RequisitionType.toString(s_req_type, oid))%>");
		doSubmit(s_link, 'RequisitionCreate;' + s_method);
	}


// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>