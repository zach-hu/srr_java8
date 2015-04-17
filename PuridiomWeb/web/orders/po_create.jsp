<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<%@ page import="com.tsa.puridiom.steporder.*" %>

<%
	ProcessSteps steps = null;
	String s_po_type = (String) request.getAttribute("PoHeader_poType");

	if (s_po_type.equals("BO"))		/*	Blanket Order	*/
	{
		steps = new ProcessSteps("posteps_bo", oid);
	}
	else if (s_po_type.equals("RO"))	/* Release Order */
	{
		steps = new ProcessSteps("posteps_ro", oid);
	}
	else if (s_po_type.equals("DO"))	/* Delivery Order */
	{
		steps = new ProcessSteps("posteps_do", oid);
	}
	else if (s_po_type.equals("DR"))	/* Delivery Release */
	{
		steps = new ProcessSteps("posteps_dr", oid);
	}
	else if (s_po_type.equals("SO"))	/* Service Order */
	{
		steps = new ProcessSteps("posteps_so", oid);
	}
	else if (s_po_type.equals("SB"))	/* Service Blanket */
	{
		steps = new ProcessSteps("posteps_sb", oid);
	}
	else if (s_po_type.equals("SR"))	/* Service Release */
	{
		steps = new ProcessSteps("posteps_sr", oid);
	}
	else if (s_po_type.equals("CT"))	/* Contract */
	{
		steps = new ProcessSteps("posteps_ct", oid);
	}
	else if (s_po_type.equals("DA"))	/* DOA Purchase Request */
	{
		steps = new ProcessSteps("posteps_da", oid);
	}
	else if (s_po_type.equals("GS"))	/* General Services Purchase Request */
	{
		steps = new ProcessSteps("posteps_gs", oid);
	}
	else		/* Purchase Order */
	{
		steps = new ProcessSteps("posteps_po", oid);
	}

%>

<tsa:hidden name="formtype" value="PO"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var s_link = "<%=steps.getLink(0)%>";
	var s_method = "<%=steps.getMethod(0)%>";
	var s_process = "<%=steps.getProcess(0)%>";

	setNavCookie("orders/po_create.jsp", "BrowseRetrieveById", "<%=OrderType.toString(s_po_type , oid)%>");

	if (s_process == "SHOPCART")
	{
		doSubmit('/orders/po_items.jsp', 'PoCreate');
	}
	else
	{
		doSubmit(s_link, 'PoCreate;' + s_method);
	}


// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>