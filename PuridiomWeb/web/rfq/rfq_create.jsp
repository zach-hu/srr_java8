<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<%@ page import="com.tsa.puridiom.steporder.*" %>

<%
	ProcessSteps steps = null;
	String s_rfq_type = (String) request.getAttribute("RfqHeader_rfqType");

	if (s_rfq_type.equals("PU"))		/*	Purchasing	*/
	{
		steps = new ProcessSteps("rfqsteps_pu", oid);
	}
	else if (s_rfq_type.equals("PR"))	/* Pricing */
	{
		steps = new ProcessSteps("rfqsteps_pr", oid);
	}
	else if (s_rfq_type.equals("RI"))	/* Request for Information */
	{
		steps = new ProcessSteps("rfqsteps_ri", oid);
	}
	else if (s_rfq_type.equals("RP"))	/* Request for Proposal */
	{
		steps = new ProcessSteps("rfqsteps_rp", oid);
	}
	else if (s_rfq_type.equals("IB"))	/* Invitation to Bid */
	{
		steps = new ProcessSteps("rfqsteps_ib", oid);
	}
	else if (s_rfq_type.equals("QC"))	/* Quote for Construction */
	{
		steps = new ProcessSteps("rfqsteps_qc", oid);
	}
	else if (s_rfq_type.equals("IQ"))	/* Invitation for Quote */
	{
		steps = new ProcessSteps("rfqsteps_iq", oid);
	}
	else		/* Request for Quotation */
	{
		steps = new ProcessSteps("rfqsteps_rq", oid);
	}

%>

<tsa:hidden name="formtype" value="RFQ"/>
<tsa:hidden name="RfqHeader_rfqType" value="${RfqHeader_rfqType}"/>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var s_link = "<%=steps.getLink(0)%>";
	var s_method = "<%=steps.getMethod(0)%>";
	var s_process = "<%=steps.getProcess(0)%>";

	if (s_process == "SHOPCART")
	{
		doSubmit('/rfq/rfq_items.jsp', 'RfqCreate');
	}
	else
	{
		doSubmit(s_link, 'RfqCreate;' + s_method);
	}


// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>