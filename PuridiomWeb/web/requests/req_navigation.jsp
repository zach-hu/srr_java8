<table border=0 width=100%>
<tr>
	<td align=center>
<%	if (s_previous_link.length() > 0) { %>
		<table border=0>
		<tr>
			<td><a href="javascript: doSubmit('<%=s_previous_link%>', '<%=s_current_method%>;<%=s_previous_method%>');"><img class=processOnImg src="<%=contextPath%>/images/arrows_left.gif" alt="<%=s_previous_label%>" border=0></a></td>
			<td class=processOn><a href="javascript: doSubmit('<%=s_previous_link%>', '<%=s_current_method%>;<%=s_previous_method%>');" class=processOn title="<%=s_previous_label%>">&nbsp;<tsa:label labelName="previous" defaultString="Previous" /></a></td>
		</tr>
		</table>
<%	} %>
	</td>
	<td align=center>
<%	if (s_next_link.length() > 0) { %>
		<table border=0>
		<tr>
			<td class=processOn><a href="javascript: doSubmit('<%=s_next_link%>', '<%=s_current_method%>;<%=s_next_method%>');" class=processOn title="<%=s_next_label%>"><tsa:label labelName="next" defaultString="Next" />&nbsp;</a></td>
			<td><a href="javascript: doSubmit('<%=s_next_link%>', '<%=s_current_method%>;<%=s_next_method%>');"><img class=processOnImg src="<%=contextPath%>/images/arrows_right.gif" alt="<%=s_next_label%>" border=0></a></td>
		</tr>
		</table>
<%	} %>
	</td>
</tr>
</table>