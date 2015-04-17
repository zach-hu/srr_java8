<%@ page import="com.tsa.puridiom.steporder.*" %>

<%
	ProcessSteps steps = null;
	HashMap processMap = new HashMap();
	String s_rfqType = (String)request.getAttribute("RfqHeader_rfqType");
	String	s_process_code = "";

	if (s_rfqType.equals("PR"))	/* Pricing */
	{
		steps = new ProcessSteps("rfqsteps_pr");
	}
	else if (s_rfqType.equals("PU"))	/* Purchasing */
	{
		steps = new ProcessSteps("rfqsteps_pu");
	}
	else if (s_rfqType.equals("RI"))	/* Request For Information */
	{
		steps = new ProcessSteps("rfqsteps_ri");
	}
	else if (s_rfqType.equals("RP"))	/* Request For Proposal */
	{
		steps = new ProcessSteps("rfqsteps_rp");
	}
	else if (s_rfqType.equals("IB"))	/* Invitation to Bid */
	{
		steps = new ProcessSteps("rfqsteps_ib");
	}
	else	/* Request for Quote */
	{
		steps = new ProcessSteps("rfqsteps_rq");
	}

	for (int ip = 0; ip < steps.getSize(); ip++)
	{
		s_process_code = steps.getProcess(ip);
		
		processMap.put(s_process_code, "TRUE");
	}
%>