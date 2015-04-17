<%		List	itemCommentList = (List) rfqLine.getDocCommentList();
			String s_after_lncomment = "";
			int	i_lineComments = 0;
			
			commentDisplayed = false;
			
			if (itemCommentList != null) {
				i_lineComments = itemCommentList.size();
			}
			
			for (int ilc = i_lineComments-1; ilc >= 0 ; ilc--) {
				DocComment comment = (DocComment) itemCommentList.get(ilc);
				if (!comment.getCommentWebpost().trim().equals("Y")) {
					itemCommentList.remove(ilc);
					i_lineComments--;
				}
			}
		
		    for (int ilc = 0; ilc < i_lineComments; ilc++) {
				DocComment comment = (DocComment) itemCommentList.get(ilc);
				if (comment.getCommentBold().trim().equals("Y")) {
					b_bold = true;
				} else {
					b_bold = false;
				}
		
				String commentText = comment.getDocText().getStdText();
				
				if (comment.getCommentPlace().equals("B")) {%>
							<tr>
								<td class=itemSummary vAlign=top width=75px align=right><% if (!commentDisplayed) { %><b>Comments:</b><%}%></td>
								<td class=itemSummary vAlign=top><% if (b_bold) {%><b><%}%><%=commentText%><% if (b_bold) {%></b><%}%></td>
							</tr>
<%				commentDisplayed = true;
				} else {
					if (b_bold) {
						s_after_lncomment +=  "<B>" + commentText + "</B>";
					} else {
						s_after_lncomment += commentText;
					}
				}
			}
%>