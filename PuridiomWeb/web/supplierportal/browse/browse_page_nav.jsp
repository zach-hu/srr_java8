<%
/**************************************************************/
/*																							*/
/*	Include File for Browse Page Navigation						*/
/*																							*/
/*	int ipageCount	-- Page Count										*/
/*																							*/
/*	The following javascript functions may be called:			*/
/*	isEmpty(x)																*/
/*	nfilter(x)																			*/
/*	previousPage()																*/
/*	nextPage()																		*/
/*	pageMe(x)																		*/
/*	moveMe(x)																		*/
/*																							*/
/**************************************************************/
%>
		<div id="pageNavigation" style="border:solid 0px; background: #FFFFFF; padding: 2px; width: 680px; height: 0px; align:center; display:none; visibility:hidden;">
		<table border=0 cellpadding=0 cellspacing=0 width=680px height=50px>
		<tr>
			<td align=center colspan=2 valign=middle>
			<table border=0 cellpadding=0 cellspacing=0>
			<tr>
				<td colspan=3 align=center nowrap>
					<table border=0 cellpadding=2 cellspacing=1 align=center>
					<tr>
						<td align=right><b>Go To Page: </b><input type=text name="go_to_pg" size=5 value=""></td>
						<td><a href="javascript: if (!isEmpty(nfilter(frm.go_to_pg))) { pageMe(nfilter(frm.go_to_pg)); } void(0);"><img class=button src="<%=contextPath%>/supplierportal/images/button_go_sm.gif" border=0 valign=bottom></a></td>
					</tr>
					</table>
				</td>
			</tr>
			<tr><td colspan=3><br></td></tr>
			<tr>
				<td align=right><span id="previousPage" style="visibility: hidden; display: block;"><a href="javascript: previousPage(); void(0);"><< Previous</a></span></td>
				<td align=center>&nbsp;
					<span id="pageLink1"><b>1</b></span>&nbsp;<%	for (int ipg = 2; ipg <= ipageCount; ipg++) {%><span id="pageLink<%=ipg%>"><a href="javascript: pageMe(<%=ipg%>);"><%=ipg%></a></span>&nbsp;<%}%>
				</td>
				<td><span id="nextPage" style="visibility: hidden; display: block;">&nbsp;<a href="javascript: nextPage(); void(0);">Next >></a></span></td>
			</tr>
			<tr><td colspan=3><br></td></tr>
			<tr>
				<td colspan=3>
					<div id="groupNavigation" style="visibility:hidden; display:none;">
					<table border=0 cellspacing=0 cellpadding=0 width=100%>
					<tr>
						<td id="previousGroup"></td>
						<td id="nextGroup" align=right></td>
					</tr>
					<tr><td colspan=2><br></td></tr>
					</table>
					</div>
				</td>
			</tr>
			</table>
			</td>
		</tr>
		</table>
		</div>