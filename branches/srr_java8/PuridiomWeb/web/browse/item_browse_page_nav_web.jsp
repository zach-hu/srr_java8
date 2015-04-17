		<table cellspacing=0 cellpadding=0 width=100% border=0 valign=bottom>
		<tr>
			<td width=40%>Showing <span id="beginRecord"><%=1%></span> - <span id="endRecord"><%=webSitesItems.size()%></span> of <b><%=webSitesItems.size()%></b></td>
			<td width=60% align=right>
				<span id="previousPage" style="visibility: hidden; display: block;"></span>
				<span id="nextPage" style="visibility: hidden; display: block;"></span>
				<table border=0 cellpadding=2 cellspacing=1 align=right>
				<tr>
					<td nowrap><span id="previousGroup" style="visibility: hidden; display: block;"><a href="javascript: previousGroup(); void(0);"><< Previous 5</a>&nbsp;</span></td>
					<td nowrap align=center><div id="group1"><span id="pageLink1"><b>1</b></span>&nbsp;<% for (int ipg=2; ipg<=ipageCount; ipg++) { if (((ipg-1)%5) == 0) {%></div><div id="group<%=((ipg-1)/5) + 1%>" style="visibility:hidden; display:none;"><%}%><span id="pageLink<%=ipg%>"><a href="javascript: pageMe(<%=ipg%>);"><%=ipg%></a></span>&nbsp;<%}%></div></td>
					<td nowrap><span id="nextGroup" style="visibility: hidden; display: block;"><a href="javascript: nextGroup(); void(0);">Next 5 >></a></span></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>