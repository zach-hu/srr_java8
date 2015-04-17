<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.browse.*" %>
<%@ page import="com.tsa.puridiom.vendor.VendorManager" %>
<%@ page import="java.math.*" %>
<%
	BrowseObject browseObject = (BrowseObject) request.getAttribute("browseObject");
	BrowseColumn browseColumns[] = browseObject.getBrowseColumns();
	String s_vendorId = (String) request.getAttribute("VendorAffiliate_vendorId");
%>

<tsa:hidden name="Vendor_vendorId" value="${VendorAffiliate_vendorId}"/>
<tsa:hidden name="VendorAffiliate_vendorId" value="${VendorAffiliate_vendorId}"/>

<table width=<%=formEntryWidth%> cellpadding=0 cellspacing=0 border=0>
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
	<td valign=bottom align=right width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td nowrap align=right><b><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-vendorId", "Supplier Id")%>:</b></td>
			<td nowrap width=150px>${esapi:encodeForHTML(VendorAffiliate_vendorId)}</td>
		</tr>
		<tr>
			<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-vendorName", "Supplier Name")%>:</b></td>
			<td nowrap><%=VendorManager.getInstance().getVendorName(oid, s_vendorId)%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
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
			<td width=50% align=center><div id="pxbutton"><a href="javascript: setFilterOptions(); setSupplierBrowse(); doSubmit('/browse/browse_affiliates.jsp', 'BrowseRetrieve'); void(0);">Browse</a></td>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/admin/supplier/supplier_affiliations.jsp', 'VendorAffiliateRetrieveByVendorId;VendorRetrieveById'); void(0);">Return</a></td>
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
	}

	function selectValue ( lstObject ) {
		var list = lstObject;
		var frmcolname = list.options[list.selectedIndex].value;
		return frmcolname;
	}

	function setFilterOptions() {
		frm.browseName.value = "vendor-affiliates";
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
		frm.browseName.value = "vendor-affiliates";
	}

// End Hide script -->
</script>

<%@ include file="/system/prevent_caching.jsp" %>