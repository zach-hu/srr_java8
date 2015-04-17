
		<table border=0 cellpadding=0 cellspacing=0>
		<tr>
			<td>
			<div id="findPageOptions" style="visibility: hidden; display=none;">
				<!--table align=center border=0 cellpadding=1 cellspacing=1>
				<tr>
					<td align=right>Find Page that contains:<br> (within <%=labelsMap.get(sortedColumn)%>) </td>
					<td nowrap>
						<input type=text name="finder" maxlength="30" value="" size=30 onChange="upperCase(this);">&nbsp;
						<input type=button value="Find" name="request" onClick="findMe('<%=headerEncoder.encodeForJavaScript(sortedColumn)%>');">
					</td>
				</tr>
				</table-->
			</div>
			<div id="filterOptions" style="visibility: visible; display=inline;">
				<table align=center border=0 cellpadding=1 cellspacing=1>
				<tr>
					<td nowrap>Filter:
						<select name="dbcolumn" size="1"><%=sbFilterOptions%></select>
					</td>
					<td nowrap>
						<select name="filtor" SIZE="1"><option value="=">=
							<option value="&gt;">&gt;<option value="&lt;">&lt;<option value="&gt;=">&gt;=<option value="&lt;=">&lt;=<option value="<>">&lt;&gt;
						</select>
					</td>
					<td nowrap>
						<input type=text maxlength=30 name="filter" value="" size=25>
						<%if(browseObject.getBrowseName().equalsIgnoreCase("receiptheader") || browseObject.getBrowseName().equalsIgnoreCase("receipt-package")){%>
						<input type=button name="request" value="Filter" onClick="filterMeWithField();">
						<% } else {%>
						<input type=button name="request" value="Filter" onClick="filterMe();">
						<% }%>
						<span id="resetFilterOptions" style="visibility: visible; display=inline;">
							<%if(browseObject.getBrowseName().equalsIgnoreCase("commodity")){%>
								<td><input id="resetButton" type=button name="reset" value="Reset" onClick="resetMe();"></td>
							<%} else {%>
								<td><input id="resetButton" type=button name="reset" value="Reset" onClick="resetMe();"></td>
							<%}%>
						</span>
					</td>
				</tr>
				</table>
			</div>
			<%if(browseObject.getBrowseName().equalsIgnoreCase("auto-close")){%>
				<table align=center border=0 cellpadding=1 cellspacing=1>
				<tr>
					<td align=center>
						<a href="javascript: Accept(); void(0);">
						<img class=button src="<%=contextPath%>/images/button_ok.gif" border=0></a>
					</td>
					<td>&nbsp</td><td>&nbsp</td><td>&nbsp</td>
					<td align=center>
						<a href="javascript: doSubmit('browse/browse_auto_close.jsp', 'DoNothing'); void(0);">
						<img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0 alt="Cancel"></a>
					</td>
				</tr>
			<%} else {%>
				<div id="resetFilterOption" style="visibility: hidden; display=none;">
				<table align=center border=0 cellpadding=1 cellspacing=1>
					<tr>
						<td nowrap><input id="resetButton" type=button name="reset" value="Reset" onClick="resetMe();"></td>
					</tr>
				</table>
				</div>
				<div id="resetOriginalOption" style="visibility: visible; display=block;">
				<table align=center border=0 cellpadding=1 cellspacing=1>
					<tr>
						<td nowrap><input id="resetOriginalButton" type=button name="reset" value="Reset Original" onClick="resetOriginal();">&nbsp;</td>
					</tr>
				</table>
				</div>
			<%} %>
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<td id="catalogFields"></td>
		</tr>
	</table>
