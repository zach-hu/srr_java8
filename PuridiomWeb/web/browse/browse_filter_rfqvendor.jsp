<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.browse.*" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="java.math.*" %>

<%
	BrowseObject browseObject = (BrowseObject) request.getAttribute("browseObject");
	BrowseColumn browseColumns[] = browseObject.getBrowseColumns();
	String s_icRfqHeader = (String) request.getAttribute("RfqHeader_icRfqHeader");
	String s_rfqNumber = (String) request.getAttribute("RfqHeader_rfqNumber");
	String s_rfqAmendment = (String) request.getAttribute("RfqHeader_rfqAmendment");
	String s_rfqStatus = (String) request.getAttribute("RfqHeader_status");
	String s_fiscalYear = (String) request.getAttribute("RfqHeader_fiscalYear");
%>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_rfqNumber%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="<%=s_rfqAmendment%>"/>
<tsa:hidden name="RfqHeader_status" value="<%=s_rfqStatus%>"/>
<tsa:hidden name="RfqHeader_rfqType" value="${RfqHeader_rfqType}"/>
<tsa:hidden name="RfqHeader_fiscalYear" value="<%=s_fiscalYear%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=browseObject.getTitle()%> Options</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
	<td valign=bottom align=middle width=*>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td nowrap align=right><b>Solicitation #:</b></td>
			<td widh=125px><%=s_rfqNumber%></td>
<%	int i_colspan = 1;
		if (s_rfqAmendment.compareTo("0000") > 0)
		{
			i_colspan = i_colspan + 2;
%>
			<td nowrap align=right><b>Amendment #:</b></td>
			<td width=125px><%=s_rfqAmendment%></td>
<%	} %>
		</tr>
		<tr>
			<td align=right colspan=<%=i_colspan%>><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=125px><%=DocumentStatus.toString(s_rfqStatus)%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
	<td align=center>
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
				        if (column.isHidden() && !column.getAllowFilter().equals("Y"))
				        {
				            if (pt == ix) pt++ ;
				            continue ;
						}
						if (column.getLabel().length() <= 0)
						{
							if (pt == ix) pt++;
							continue;
						}
				        if (pt == ix) {
		%>		<option value="<%=column.getColumnName()%>" selected><%=column.getLabel(oid, language)%></option>
		<%			} else { %>
					<option value="<%=column.getColumnName()%>"><%=column.getLabel(oid, language)%></option>
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
		</table>

		<br>

		<table border=0 width=500px>
		<tr>
			<td width=50% align=center>
				<div id="pxbutton"><a href="javascript: setFilterOptions(); setSupplierBrowse(); doSubmit('/browse/browse_rfq_vendor.jsp', 'BrowseRetrieve'); void(0);">Browse</a></div>
			</td>
			<td width=50% align=center>
				<div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_suppliers.jsp', 'RfqRetrieve'); void(0);">Return</a></div>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<script value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var filterFields = "";

	function correctMe ( ix ) {

		frmcolname = selectValue(frm.colname[ix]);
		frmFilter = frm.filter_txt[ix];

		if ( frmcolname.indexOf("rqh_requisition_number") >= 0 || frmcolname.indexOf("poh_po_number") >= 0 || frmcolname.indexOf("rfh_rfq_number") >= 0) {
			if (frm.filter_txt[ix].value.indexOf("%") < 0) {
				nfilter( frmFilter );
				frmFilter = frm.filter_txt[ix].value;
				var r = "000000";

				if ( (frmFilter.length < 6) && (frmFilter.length > 0)) {
					frm.filter_txt[ix].value = r.substr(frmFilter.length) + frmFilter;
				}
			}
		}
		else if ( frmcolname.indexOf("rqh_requisition_status") >= 0 )
			nfilter( frmFilter );
		else if ( frmcolname.indexOf("rqh_fiscal_year") >= 0 )
			nfilter( frmFilter );
		else if ( frmcolname.indexOf("rqh_total") >= 0 )
			nfilter( frmFilter );
		else if ( frmcolname.indexOf("rqh_estimated_cost") >= 0 )
			nfilter( frmFilter );
		else if ( frmcolname.indexOf("rqh_requisition_date") >= 0 )
			checkDate( frmFilter );
		else if ( frmcolname.indexOf("rqh_date_entered") >= 0 )
			checkDate( frmFilter );
		else if ( frmcolname.indexOf("rqd_required_date") >= 0 )
			checkDate( frmFilter );
		else if ( frmcolname.indexOf("rfh_rfq_status") >= 0 )
			nfilter( frmFilter );
		else if ( frmcolname.indexOf("rfh_fiscal_year") >= 0 )
			nfilter( frmFilter );
		else if ( frmcolname.indexOf("rfh_total") >= 0 )
			nfilter( frmFilter );
		else if ( frmcolname.indexOf("rfh_estimated_cost") >= 0 )
			nfilter( frmFilter );
		else if ( frmcolname.indexOf("rfh_rfq_date") >= 0 )
			checkDate( frmFilter );
		else if ( frmcolname.indexOf("rfh_date_entered") >= 0 )
			checkDate( frmFilter );
		else if ( frmcolname.indexOf("rfh_due_date") >= 0 )
			checkDate( frmFilter );
		else if ( frmcolname.indexOf("poh_status") >= 0 )
			nfilter( frmFilter );
		else if ( frmcolname.indexOf("poh_fiscal_year") >= 0 )
			nfilter( frmFilter );
		else if ( frmcolname.indexOf("poh_total") >= 0 )
			nfilter( frmFilter );
		else if ( frmcolname.indexOf("poh_po_date") >= 0 )
			checkDate( frmFilter );
		else if ( frmcolname.indexOf("poh_date_entered") >= 0 )
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
		frm.browseName.value = "rfqvendor";
		var myTable = document.getElementById("filterOptions");
		var previousLogicalOperator = "AND";

		for (var i=0; i < myTable.rows.length - 2; i++) {
			var filterColumn = frm.colname[i].value;
			var filterTxt = frm.filter_txt[i].value;
			var sort = frm.sort[i].value;

			if (!isEmpty(filterTxt) || sort != "N") {
				var operator = frm.operator[i].value;
				var logicalOperator = frm.logicalOperator[i].value;

				if (filterTxt.indexOf("%") >= 0) {
					frm.operator[i][frm.operator[i].selectedIndex].value = "LIKE";
				}

				filterFields = filterFields + "<input type=hidden name=\"" + filterColumn + "\" value=\"" + filterTxt + "\">";
				filterFields = filterFields + "<input type=hidden name=\"" + filterColumn + "_operator\" value=\"" + operator + "\">";
				filterFields = filterFields + "<input type=hidden name=\"" + filterColumn + "_logic\" value=\"" + previousLogicalOperator + "\">";
				filterFields = filterFields + "<input type=hidden name=\"" + filterColumn + "_original\" value=\"Y\">";
				filterFields = filterFields + "<input type=hidden name=\"" + filterColumn + "_sort\" value=\"" + sort + "\">";

				previousLogicalOperator = logicalOperator;

			}
		}
	}

	function setSupplierBrowse()
	{
		if (filterFields.indexOf("_commodity") > 0)
		{
			frm.browseName.value = "rfqvendor_commodity";
		}
		if (filterFields.indexOf("Commodity_description") > 0)
		{
			frm.browseName.value = "rfqvendor_commodity";
		}
	}

// End Hide script -->
</script>

<%@ include file="/system/prevent_caching.jsp" %>