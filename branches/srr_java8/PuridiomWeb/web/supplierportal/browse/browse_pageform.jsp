<%@ include file="/supplierportal/browse/browse_page_nav.jsp" %>
		<table border=0 cellpadding=0 cellspacing=0 width=680px> 
		<tr>
			<td>
			<div id="findPageOptions" style="visibility: hidden; display: none;">
				<table align=center border=0 cellpadding=2 cellspacing=1>
				<tr>
					<td align=right>Find Page that contains:<br> (within <%=labelsMap.get(sortedColumn)%>) </td>
					<td nowrap>
						<input type=text name="finder" maxlength="30" value="" size=30 onChange="upperCase(this);">&nbsp;
						<input type=button value="Find" name="request" onClick="findMe('<%=sortedColumn%>');">
					</td>
				</tr>
				</table>
			</div>
			<div id="filterOptions" style="visibility: hidden; display: none;">
				<table align=center border=0 cellpadding=2 cellspacing=1>
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
						<input type=text maxlength=30 name="filter" value="" size=30>&nbsp;
						<input type=button name="request" value="Filter" onClick="filterMe();">&nbsp;
						<span id="resetFilterOptions" style="visibility: visible; display: inline;">
							<input id="resetButton" type=button name="reset" value="Reset" onClick="resetMe();">&nbsp;
						</span>
					</td>
				</tr>
				</table>
			</div>
			<div id="resetOption" style="visibility: hidden; display: none;">
			<table align=center border=0 cellpadding=2 cellspacing=1>
				<tr>
					<td nowrap><input id="resetButton" type=button name="reset" value="Reset" onClick="resetMe();">&nbsp;</td>
				</tr>
			</table>
			</div>
			<div id="resetOriginalOption" style="visibility: hidden; display: none;">
			<table align=center border=0 cellpadding=2 cellspacing=1>
				<tr>
					<td nowrap><input id="resetOriginalButton" type=button name="reset" value="Reset Original" onClick="resetOriginal();">&nbsp;</td>
				</tr>
			</table>
			</div>
		</tr>
	</table>
	<table>
		<tr>
			<td id="catalogFields"></td>
		</tr>
	</table>
