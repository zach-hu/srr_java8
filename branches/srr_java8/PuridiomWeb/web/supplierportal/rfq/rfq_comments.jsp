<%
	int	i_comments = 0;
	boolean commentDisplayed = false;
	
	if (rfqCommentList != null) {
		i_comments = rfqCommentList.size();
	}

	int	i_first_row = 1;
	boolean	b_bold = false;
	String	s_after_comment = "";

	for (int ir = i_comments-1; ir >= 0 ; ir--) {
		DocComment comment = (DocComment) rfqCommentList.get(ir);
		if (!comment.getCommentWebpost().equals("Y")) {
			rfqCommentList.remove(ir);
			i_comments--;
		}
	}

    for (int ir = 0; ir < i_comments; ir++) {
		DocComment comment = (DocComment) rfqCommentList.get(ir);
		if (comment.getCommentBold().trim().equals("Y")) {
			b_bold = true;
		} else {
			b_bold = false;
		}

		String commentText = comment.getDocText().getStdText();
		
		if (comment.getCommentPlace().equals("B")) {
%>
				<tr height=28px>
					<td height=28px class=summary valign=top align=right><% if (!commentDisplayed) { %><b>Comments:</b><%}%></td>
					<td class=summary valign=top colspan=6><% if (b_bold) {%><b><%}%><%=commentText%><% if (b_bold) {%></b><%}%></td>
				</tr>
<%		commentDisplayed = true;
		} else {
			if (b_bold) {
				s_after_comment +=  "<B>" + commentText + "</B><BR><BR>";
			} else {
				s_after_comment += commentText + "<BR><BR>";
			}
		}
	}
%>
