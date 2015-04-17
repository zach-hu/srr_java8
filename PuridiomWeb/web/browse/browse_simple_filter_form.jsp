<%@ page import="com.tsa.puridiom.browse.BrowseColumn" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>

<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
	<td align=center>
		<table id="filterOptions" border=0 cellpadding=2 cellspacing=0>
<%
		int filterRows = 10;
		int ix = 0;
		boolean addDateInstructions = false;

		if (browseColumns.length < filterRows) {
			filterRows = browseColumns.length;
		}

		for (int ii = 0; ix < filterRows; ii++) {
			int columnCount = 0;
		    // load values from BrowseObject.browseColumns
	    	BrowseColumn column = browseColumns[ii];

	        if (column.isHidden()) {
	            continue ;
			}
			if (column.getType().equals("DATE")) {
				addDateInstructions = true;
			}
%>
		<tr>
			<!-- <td align=right><%=column.getLabel()%></td> -->
			<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, column.getLabel(), column.getLabel())%></td>
			<td>
				<tsa:hidden name="colname" value="<%=column.getColumnName()%>"/>
				<tsa:hidden name="originalFilter" value="Y"/>
			    <tsa:hidden name="operator" value="="/>
			    <tsa:hidden name="logicalOperator" value=""/>
			    <tsa:hidden name="sort" value="N"/>
		        <input maxlength=30 name="filter_txt" size=30 type=text onChange="correctMe(<%=ix%>);">
		    </td>
		    <td><% if (column.getType().equals("DATE")) {%><a href="javascript: show_calendar('filter_txt[<%=ix%>]', 'yyyy-MM-dd');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a><%}%></td>
		</tr>
<%		ix++;
		}%>
		<tr>
			<td colspan=2 align=center>
				<br>
				<table border=0 cellpadding=0 cellspacing=0>
				<% if (addDateInstructions) {%>
				<tr>
					<td align=center CLASS="small">(*)<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "enter-dates-in", "Enter Dates in")%> <%=PropertiesManager.getInstance(oid).getProperty("MISC", "DateFormat", "MM-dd-yyyy").toUpperCase()%>.
				</tr>
				<tr><td><br></td></tr>
				<% }%>
				<tr>
					<td>
						<table border=0 cellpadding=1 cellspacing=0>
						<tr>
							<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "display", "Display")%></td>
							<td>
								<select name="pageSize">
									<option value="1" <% if (browseObject.getPageSize() == 1) {%>selected<%}%>>1</option>
									<option value="5" <% if (browseObject.getPageSize() == 5) {%>selected<%}%>>5</option>
									<option value="10" <% if (browseObject.getPageSize() == 10) {%>selected<%}%>>10</option>
									<option value="15" <% if (browseObject.getPageSize() == 15) {%>selected<%}%>>15</option>
									<option value="20" <% if (browseObject.getPageSize() == 20) {%>selected<%}%>>20</option>
									<option value="25" <% if (browseObject.getPageSize() == 25) {%>selected<%}%>>25</option>
									<option value="50" <% if (browseObject.getPageSize() == 50) {%>selected<%}%>>50</option>
								</select>
							</td>
							<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "results-per-page", "Results Per Page")%></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>

		<br><br>

		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td align="center">
				<a href="javascript: setFilterOptions(); browse('${esapi:encodeForJavaScript(browseObject.browseName)}');  void(0);">
				<img class=button src="<%=contextPath%>/images/button_browse.gif" border=0 alt="Browse Items">
				</a>
			</td>
			<td align="center">
				<a href="javascript: doSubmit('menu/main_menu.jsp','DoNothing'); void(0);">
				<img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0>
				</a>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<script value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	setNavCookie("/browse/browse_simple_filter_options.jsp", "BrowseGetOptions", "<%=browseObject.getTitle()%> Options", true);
	document.cookie = "browseName=" + "<%=HiltonUtility.ckNull((String) request.getAttribute("browseName"))%>";

	function checkHiddenMenu() {
			hideArea("navTable");
			displayArea("menuBarSpacer");
	}


	function correctMe ( ix ) {
		var frmFilter = frm.filter_txt[ix].value;
		frmFilter = trim(frm.filter_txt[ix]).toUpperCase();
		frm.filter_txt[ix].value = frmFilter;
	}

	function selectValue ( lstObject ) {
		var list = lstObject;
		var frmcolname = list.options[list.selectedIndex].value;
		return frmcolname;
	}

	function setFilterOptions() {
		var myTable = document.getElementById("filterOptions");
		var filterFields = "";
		var previousLogicalOperator = "AND";

		<% if (role.getAccessRights("RCVHISTORY") < 99){
			if (browseObject.getBrowseName().indexOf("receipt")>=0) { %>
				setOriginalFilter("PoHeader_departmentCode", "=", "<%=user.getDepartment()%>");
			<% }
		}%>

		for (var i=0; i < myTable.rows.length - 1; i++) {
			var filterColumn = frm.colname[i].value;
			var filterTxt = frm.filter_txt[i].value;
			var sort = frm.sort[i].value;

			if (!isEmpty(filterTxt) && filterTxt.indexOf("%") >= 0) {
				frm.operator[i].value = "LIKE";
			}
		}
		return filterFields;
	}

// End Hide script -->
</script>