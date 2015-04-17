<%@ page import="com.tsa.puridiom.steporder.*" %>

<%
	ProcessSteps steps = new ProcessSteps("salesteps", oid);
	HashMap processMap = new HashMap();
	String	s_process_code = "";

	for (int ip = 0; ip < steps.getSize(); ip++)
	{
		s_process_code = steps.getProcess(ip);
		
		processMap.put(s_process_code, "TRUE");
	}
%>