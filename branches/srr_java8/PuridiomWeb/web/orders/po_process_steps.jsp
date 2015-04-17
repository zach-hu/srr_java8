<%@ page import="com.tsa.puridiom.steporder.*" %>

<%
	ProcessSteps steps = null;
	HashMap processMap = new HashMap();
	String	s_process_code = "";

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
	else		/* Purchase Order */
	{
		steps = new ProcessSteps("posteps_po", oid);
	}

	for (int ip = 0; ip < steps.getSize(); ip++)
	{
		s_process_code = steps.getProcess(ip);
		
		processMap.put(s_process_code, "TRUE");
	}
%>