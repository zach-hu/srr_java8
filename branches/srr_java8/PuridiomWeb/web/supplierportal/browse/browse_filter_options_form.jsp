<table id="filterOptions" border=0 cellpadding=2 cellspacing=0>
<tr>
	<td>&nbsp;</td>
	<td>Column Name</td>
	<td>Operator</td>
	<td>Filter Expression(*)</td>
	<td>Logical</td>
	<td>&nbsp;</td>
	<td>Sort Option</td>
</tr>
<%	int pt = 0 ;
		for (int ii = 0; ii < 5; ii++) {%>
<tr>
    <td>&nbsp;</td>
	<td>
	    <select name="colname" size=1>
<%
		    // load values from BrowseObject.browseColumns
			for (int ix=0; ix < browseColumns.length; ix++) {
				BrowseColumn column = browseColumns[ix];

		        if (column.isHidden()) {
		            if (pt == ix) pt++ ;
		            continue ;
				}
		        if (pt == ix) {
%>		<option value="<%=column.getColumnName()%>" selected><%=column.getLabel()%></option>
<%			} else { %>
			<option value="<%=column.getColumnName()%>"><%=column.getLabel()%></option>
<%			}
			}
			pt++ ;
%>
	    </select>&nbsp;
	</td>
	<td align=right>
		<tsa:hidden name="originalFilter" value="Y"/>
	    <select name="operator" size=1>
			<option value="=">=</option>
			<option value=">">></option>
			<option value="<"><</option>
			<option value=">=">>=</option>
			<option value="<="><=</option>
			<option value="<>"><></option>
	    </select>&nbsp;
	</td>
	<td>
        <input maxlength=30 name="filter_txt" size=30 type=text onChange="correctMe(<%=ii%>);">
    </td>
	<td>
<%		if (ii < 4) {%>
	    <select name="logicalOperator" size=1>
			<option value=""> </option>
			<option value="AND">AND</option>
			<option value="OR">OR</option>
	    </select>
<%		} else {%><!-- The last logical operator is not used --><tsa:hidden name="logicalOperator" value=""/>
<%		}%>
		&nbsp;
	</td>
	<td>&nbsp;</td>
	<td>
	    <select name="sort" size=1>
			<option value="N"> </option>
			<option value="ASC">Ascending</option>
			<option value="DESC">Descending</option>
	    </select>&nbsp;
	</td>
</tr>
<%}%>
<tr>
	<td colspan=3>&nbsp;</td>
	<td colspan=4 CLASS="small">(*)Enter Dates in YYYY-MM-DD format.</td>
</tr>
</table>
<table border=0 width=680px>
<tr><td id="hiddenFields"></td></tr>
<tr>
	<td align=center>
		<a href="javascript: setFilterOptions(); browse('<%=browseObject.getBrowseName()%>'); void(0);">
		<img class=button src="<%=contextPath%>/supplierportal/images/button_browse.gif" border=0></a>
	</td>
</tr>
</table>

<tsa:hidden name="userDateFormat" value="yyyy-MM-dd"/>

<script value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function correctMe ( ix ) {

		frmcolname = selectValue(frm.colname[ix]);
		frmFilter = frm.filter_txt[ix];

		if ( frmcolname.indexOf("rfh_rfq_number") >= 0) {
			if (frm.filter_txt[ix].value.indexOf("%") < 0) {
				nfilter( frmFilter );
				frmFilter = frm.filter_txt[ix].value;
				var r = "000000";

				if ( (frmFilter.length < 6) && (frmFilter.length > 0)) {
					frm.filter_txt[ix].value = r.substr(frmFilter.length) + frmFilter;
				}
			}
		}
		else if ( frmcolname.indexOf("rfh_rfq_status") >= 0 )
			nfilter( frmFilter );
		else if ( frmcolname.indexOf("rfh_fiscal_year") >= 0 )
			nfilter( frmFilter );
		else if ( frmcolname.indexOf("rfh_rfq_date") >= 0 )
			checkDate( frmFilter );
		else if ( frmcolname.indexOf("rfh_date_entered") >= 0 )
			checkDate( frmFilter );
		else if ( frmcolname.indexOf("rfh_due_date") >= 0 )
			checkDate( frmFilter );
		else
			frm.filter_txt[ix].value = frmFilter.value.toUpperCase();
	}

	function selectValue ( lstObject ) {
		var list = lstObject;
		var frmcolname = list.options[list.selectedIndex].value;
		return frmcolname;
	}

	function setFilterOptions() {
		var myTable = document.getElementById("filterOptions");
		var myHiddenFields = document.getElementById("hiddenFields");
		var filterFields = "";
		var previousLogicalOperator = "AND";

		for (var i=0; i < myTable.rows.length - 2; i++) {
			var filterColumn = frm.colname[i].value;
			var filterTxt = frm.filter_txt[i].value;
			var sort = frm.sort[i].value;

			if (!isEmpty(filterTxt) || sort != "N") {
				var operator = frm.operator[i].value;
				var logicalOperator = frm.logicalOperator[i].value;

				filterFields = filterFields + "<input type=hidden name=\"" + filterColumn + "\" value=\"" + filterTxt + "\">";
				filterFields = filterFields + "<input type=hidden name=\"" + filterColumn + "_operator\" value=\"" + operator + "\">";
				filterFields = filterFields + "<input type=hidden name=\"" + filterColumn + "_logic\" value=\"" + previousLogicalOperator + "\">";
				filterFields = filterFields + "<input type=hidden name=\"" + filterColumn + "_original\" value=\"Y\">";
				filterFields = filterFields + "<input type=hidden name=\"" + filterColumn + "_sort\" value=\"" + sort + "\">";

				previousLogicalOperator = logicalOperator;

			}
//			filterFields = filterFields + "<input type=hidden name=\"" + filterColumn + "_sort\" value=\"" + sort + "\">";
		}
		//myHiddenFields.innerHTML = filterFields;
	}

// End Hide script -->
</script>
