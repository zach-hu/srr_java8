<%@ page import="com.tsa.puridiom.steporder.*" %>

<%
	ProcessSteps steps = null;
	HashMap processMap = new HashMap();
	String	s_process_code = "";

	if (s_req_type.equals("S"))		/* Supply Request */
	{
		steps = new ProcessSteps("reqsteps_s", oid);
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
	else if (s_req_type.equals("N"))	/* Pricing Requisition */
	{
		steps = new ProcessSteps("reqsteps_i", oid);
	}
	else					/*  Purchase Request  */
	{
		steps = new ProcessSteps("reqsteps_p", oid);
	}

	for (int ip = 0; ip < steps.getSize(); ip++)
	{
		s_process_code = steps.getProcess(ip);
		
		processMap.put(s_process_code, "TRUE");
	}
%>