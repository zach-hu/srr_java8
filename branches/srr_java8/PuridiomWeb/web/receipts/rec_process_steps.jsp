<%@ page import="com.tsa.puridiom.steporder.*" %>

<%
	ProcessSteps steps = null;
	HashMap processMap = new HashMap();
	String	s_process_code = "";

	if (s_rec_type.equals("O"))		/* Original Receipt */
	{
		steps = new ProcessSteps("recsteps_s", oid);
	}
	else if (s_rec_type.equals("R"))	/* Return Receipt */
	{
		steps = new ProcessSteps("recsteps_r", oid);
	}
	else if (s_rec_type.equals("A"))	/* Adjustment Receipt */
	{
		steps = new ProcessSteps("recsteps_a", oid);
	}
	else if (s_rec_type.equals("P"))	/* Receipt Package */
	{
		steps = new ProcessSteps("recsteps_p", oid);
	}
	else					/*  Purchase Request  */
	{
		steps = new ProcessSteps("recsteps_o", oid);
	}

	for (int ip = 0; ip < steps.getSize(); ip++)
	{
		s_process_code = steps.getProcess(ip);

		processMap.put(s_process_code, "TRUE");
	}
%>