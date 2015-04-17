<%
/***************************************************************/
/*																										*/
/*	Include File for Browse Page Navigation							*/
/*																										*/
/*	int ipageCount	-- Page Count												*/
/*																										*/
/*	The following javascript functions may be called:			*/
/*	isEmpty(x)																			*/
/*	nfilter(x)																						*/
/*	previousPage()																		*/
/*	nextPage()																				*/
/*	pageMe(x)																					*/
/*	moveMe(x)																				*/
/*							
*/
/***************************************************************/

	//limits the number of link pages to be displayed on the page for reasons of performace
	int printedPageCount = ipageCount;
	int currentPage = browse.getCurrentPage();
	if (currentPage < 100 && printedPageCount > 100) {
		printedPageCount = 100;
	} else if (printedPageCount > currentPage + 5) {
		printedPageCount = currentPage + 5;
	}
	
%>
		<div id="pageNavigation" name="pageNavigation" style="border:solid 0px; padding: 2px; height: 0px; align:center; display:none; visibility:hidden;">
		<table border=0 cellpadding=0 cellspacing=0 height=50px>
		<tr>
			<td align=center colspan=2 valign=middle width="100%" nowrap>
			<table border=0 cellpadding=0 cellspacing=0 width="100%">
			<tr>
				<td valign=bottom align=right nowrap><span id="previousPage" style="visibility: hidden; display: inline;" nowrap><a href="javascript: previousPage(); void(0);"><< Previous</a>&nbsp;</span></td>
				<td align=center nowrap>&nbsp;
					<div id="group1" style="display: inline;">
					<span id="pageLink1"><b>1</b></span>&nbsp;
<%	for (int ipg = 2; ipg <= printedPageCount; ipg++) {
		if ((ipg-1)%5 == 0) {%>
					</div>
					<div id="group<%=((ipg-1)/5) + 1%>" style="visibility:hidden; display:none;">
<%		}
		String modeWithAllRegisters = null;
		if(request.getAttribute("modeRetrieveAllRegisters")!=null){
			modeWithAllRegisters = (String)request.getAttribute("modeRetrieveAllRegisters");	
		}
		if(modeWithAllRegisters != null && modeWithAllRegisters.equals("Y")){%>
					<span id="pageLink<%=ipg%>" nowrap><a href="javascript: pageRetrieveAllRegistersMode(<%=ipg%>);"><%=ipg%></a></span>&nbsp;
<%		}else{%>
					<span id="pageLink<%=ipg%>" nowrap><a href="javascript: pageMe(<%=ipg%>);"><%=ipg%></a></span>&nbsp;
<%		}
	} %>
					</div>
				</td>
				<td valign=bottom nowrap><span id="nextPage" style="visibility: hidden; display: inline;" nowrap>&nbsp;<a href="javascript: nextPage(); void(0);">Next >></a></span></td>
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