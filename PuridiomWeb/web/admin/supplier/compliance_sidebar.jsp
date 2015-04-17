<%@ page import="com.tsa.puridiom.steporder.*" %>

<TABLE BORDER=0 CELLPADDING=0 CELLSPACING=0>
<TR><TD COLSPAN="3" HEIGHT="1px" CLASS="darkShadow"><IMG SRC="<%=contextPath%>/images/none.gif" HEIGHT="1px" WIDTH="100%"></TD></TR>
<TR CLASS="sidebar"><TD COLSPAN="3" HEIGHT="5px">&nbsp;</TD></TR>

<%
	HashMap processMap = new HashMap();
	String	s_process_code = "";
	String	s_process_link = "";
	String	s_process_label = "";
	String	s_process_method = "";

	String	s_previous_link = "";
	String	s_previous_label = "";
	String	s_previous_method = "";
	String	s_next_link = "";
	String	s_next_label = "";
	String	s_next_method = "";
	String	s_reqsteps = "";
	String    accountPref = "";

	boolean	b_current_process = false;

	int	i_step = 1;

	ProcessSteps steps = new ProcessSteps("vendorsteps", oid);

	for (int ip = 0; ip < steps.getSize(); ip++)
	{
		s_process_code = steps.getProcess(ip);
		s_process_link = steps.getLink(ip);
		s_process_label = steps.getLabel(ip);
		s_process_method = steps.getMethod(ip);

		processMap.put(s_process_code, "TRUE");

		if (s_current_process.equals(s_process_code))
		{
			b_current_process = true;
			s_current_process_method = s_process_method;

			if (ip > 0)
			{
				s_previous_link = steps.getLink(ip - 1);
				s_previous_label = steps.getLabel(ip - 1);
				s_previous_method = steps.getMethod(ip - 1);
			}
			if ( (ip + 1) < steps.getSize())
			{
				s_next_link = steps.getLink(ip + 1);
				s_next_label = steps.getLabel(ip + 1);
				s_next_method = steps.getMethod(ip + 1);
			}
		}
		else
		{
			b_current_process = false;
		}

		if (b_current_process)
		{
%>
<tsa:hidden name="accountPref" value="<%=accountPref%>"/>
<TR HEIGHT="25px">
	<TD WIDTH="5px" CLASS="processOn">&nbsp;</TD>
	<TD>
		<div id="pxsmallbutton"><%=i_step%></div>
	</TD>
	<TD CLASS="processOn" NOWRAP>
		&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, s_process_label, s_process_label, false)%>&nbsp;
	</TD>
</TR>
<%
		}
		else
		{
%>
<TR HEIGHT="25px">
	<TD CLASS="processOff" WIDTH="5px">&nbsp;</TD>
	<TD CLASS="processOff">
		<div id="pxsmallbutton"><A HREF="javascript: doSubmit('<%=s_process_link%>', '<%=s_current_method%>;<%=s_process_method%>');" class=processOff><%=i_step%></A></div>
	</TD>
	<TD CLASS="processOff" NOWRAP>
		&nbsp;<A HREF="javascript: doSubmit('<%=s_process_link%>', '<%=s_current_method%>;<%=s_process_method%>');" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, s_process_label, s_process_label, false)%></A>&nbsp;
	</TD>
</TR>
<%	}
		i_step++;
	}
%>
<tr class="processOff"><td colspan="3" height="5px">&nbsp;</td></tr>
<tr><td colspan="3" height="1px" class="darkShadow"><IMG SRC="<%=contextPath%>/images/none.gif" height="1px" width="100%"></TD></TR>
<tr class="processOff"><td colspan="3" height="5px">&nbsp;</td></tr>

<tr>
	<td colspan=3>
		<table border=0 width=100%>
		<tr>
			<td align=left>
<%	if (s_previous_link.length() > 0) { %>
				<table border=0>
				<tr>
					<td><div id="pxsmallbutton"><a href="javascript: doSubmit('<%=s_previous_link%>', '<%=s_current_method%>;<%=s_previous_method%>');"><<</a></div></td>
					<td class=processOn><a href="javascript: doSubmit('<%=s_previous_link%>', '<%=s_current_method%>;<%=s_previous_method%>');" class=processOn title="<%=s_previous_label%>">Back</a></td>
				</tr>
				</table>
<%	} %>
			</td>
			<td align=right>
<%	if (s_next_link.length() > 0) { %>
				<table border=0>
				<tr>
					<td class=processOn><a href="javascript: doSubmit('<%=s_next_link%>', '<%=s_current_method%>;<%=s_next_method%>');" class=processOn title="<%=s_next_label%>">Next</a></td>
					<td><div id="pxsmallbutton"><a href="javascript: doSubmit('<%=s_next_link%>', '<%=s_current_method%>;<%=s_next_method%>');">>></a></div></td>
				</tr>
				</table>
<%	} %>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr><td colspan="3" class="processOff"><br></td></tr>
<%	if (role.getAccessRights("VEND") > 1) { %>
<tr>
	<td colspan="3" class="processOff" align="center">
		<table border=0 cellpadding=1 cellspacing=0 width=100%>
		<tr>
			<%	if (role.getAccessRights("VEND") > 1) { %>
	<td width=25% align=center><div id="pxbutton"><a href="javascript: submitThis('/admin/supplier/supplier_compliances.jsp', '<%=updateHandler%>;VendorInsuranceDefaultRetrieveById;ContractsRetrieveByVendorId'); void(0);">Save</a></div></td>
<%	} %>
		</tr>
		</table>
	</td>
</tr>
<%	}	%>
</table>