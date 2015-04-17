		<table border=0 width=100% cellpadding=2 cellspacing=0>
		<tr>
			<td colspan=2 width=5%>&nbsp;</td>
			<td width=85%>&nbsp;</td>
		</tr>
<%
	for ( int ii = 0; ii < i_qstrows; ii++ )
	{ 
		VendorQuestion vendorQuestion = (VendorQuestion) questionList.get(ii);
		String	responseText = vendorQuestion.getResponseText();
		if (HiltonUtility.isEmpty(responseText)) {
			responseText = "Pending Response...";
		}
%>
		<tr>
			<td>&nbsp;</td>
			<td valign=top align=right>&nbsp;<b><%=ii + 1%>.</b></td>
			<td valign=top><b><%=vendorQuestion.getQuestionText()%></b></td>
		</tr>
		<tr>
			<td colspan=2>&nbsp;</td>
			<td valign=top>
				<%=responseText%>
			</td>
		</tr>
		<tr><td colspan=3><br></td></tr>
<%	}

	if (i_qstrows == 0) {%>
		<tr><td align=center colspan=3>&nbsp;<b>No questions have been submitted for this solicitation.</b></td></tr>
<%	} %>
		</table>