<%@ page import="com.tsa.puridiom.steporder.*" %>

<table border=0 cellpadding=0 cellspacing=0>
<tr><td colspan="3" height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" height="1px" width="100%"></td></tr>
<tr class="sidebar"><td colspan="3" height="5px">&nbsp;</td></tr>

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

	boolean	b_current_process = false;

	int	i_step = 1;

	ProcessSteps steps = new ProcessSteps("salesteps", oid);

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
		else if (s_current_process.indexOf("LINE") == 0 && (s_process_code.equals("ITEMS") || s_process_code.equals("ITEM")))
		{
			b_current_process = false;

			if (ip > 0)
			{
				s_previous_link = steps.getLink(ip - 1);
				s_previous_label = steps.getLabel(ip - 1);
				s_previous_method = steps.getMethod (ip -1);
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
<TR HEIGHT="25px">
	<TD WIDTH="5px" CLASS="processOn">&nbsp;</TD>
	<TD CLASS="processOn">
		<img class=processOnImg src="<%=contextPath%>/images/step<%=i_step%>on.gif" BORDER="0">
	</TD>
	<TD CLASS="processOn" NOWRAP>
		&nbsp;<%=s_process_label%>&nbsp;
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
		<img class=processOffImg src="<%=contextPath%>/images/step<%=i_step%>off.gif" BORDER="0">
	</TD>
	<TD CLASS="processOff" NOWRAP>
		&nbsp;<A HREF="javascript: doSubmit('<%=s_process_link%>', '<%=s_current_method%>;<%=s_process_method%>');" class=processOff><%=s_process_label%></A>&nbsp;
	</TD>
</TR>
<%			if (s_process_code.equals("ITEMS") || s_process_code.equals("ITEM"))
			{
%>
				<%//@ include file="/xchange/requests/req_item_sidebar.jsp" %>
<%			}

		}

		i_step++;
	}
%>
<tr class="processOff"><td colspan="3" height="5px">&nbsp;</td></tr>
<tr><td colspan="3" height="1px" class="darkShadow"><IMG SRC="<%=contextPath%>/images/none.gif" HEIGHT="1px" WIDTH="100%"></TD></TR>
<tr class="processOff"><td colspan="3" height="5px">&nbsp;</td></tr>

<tr>
	<td colspan=3>
		<table border=0 width=100%>
		<tr>
			<td align=left>
<%	if (s_previous_link.length() > 0) { %>
				<table border=0>
				<tr>
					<td><a href="javascript: doSubmit('<%=s_previous_link%>', '<%=s_current_method%>;<%=s_previous_method%>');"><img class=processOnImg src="<%=contextPath%>/images/arrows_left.gif" alt="<%=s_previous_label%>" border=0></a></td>
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
					<td><a href="javascript: doSubmit('<%=s_next_link%>', '<%=s_current_method%>;<%=s_next_method%>');"><img class=processOnImg src="<%=contextPath%>/images/arrows_right.gif" alt="<%=s_next_label%>" border=0></a></td>
				</tr>
				</table>
<%	} %>
			</td>
		</tr>
		</table>
	</td>
</tr>

<tr><td colspan="3" class="processOff"><br></td></tr>
<%	if (s_status.compareTo(DocumentStatus.SALE_BIDS_PENDING) < 0 || s_status.compareTo(DocumentStatus.SALE_OPENAMENDMENT) == 0)
		{ %>
<tr>
	<td colspan="3" class="processOff" align="center">
<%			if ( s_current_page.equals("/sales/sale_review.jsp") && !s_saleNumber.equals("N/A") )
				{ %>
		<a tabindex=50 href="javascript: salePost(); void(0);"><img class=button src="<%=contextPath%>/images/button_forward.gif" border=0 alt="Post Sale"></a>
<%			}
				else
				{ %>
		<a tabindex=50 href="javascript: saleSave(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a>
<%			} %>
	</td>
</tr>
<%		} %>
</table>