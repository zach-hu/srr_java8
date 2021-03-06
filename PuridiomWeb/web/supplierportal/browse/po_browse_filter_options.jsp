<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.text.*" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(user.getOrganizationId());
	String	dateFormat = propertiesManager.getProperty("MISC", "DateFormat", "yyyy-MM-dd");
	String	browseName = "poheader";
	String	s_last2	 = new String();
	String	s_last5	 = new String();
	String	s_last10 = new String();
	String	s_last15 = new String();
	String	s_last20 = new String();
	String	s_last30 = new String();

	SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd");
	Calendar rightNow = Calendar.getInstance();

	rightNow.add(rightNow.DAY_OF_MONTH, - 2);
	s_last2 = formatter.format(rightNow.getTime());

	rightNow = Calendar.getInstance();
	rightNow.add(rightNow.DAY_OF_MONTH, - 5);
	s_last5	= formatter.format(rightNow.getTime());

	rightNow = Calendar.getInstance();
	rightNow.add(rightNow.DAY_OF_MONTH, - 10);
	s_last10 = formatter.format(rightNow.getTime());

	rightNow = Calendar.getInstance();
	rightNow.add(rightNow.DAY_OF_MONTH, - 15);
	s_last15 = formatter.format(rightNow.getTime());

	rightNow = Calendar.getInstance();
	rightNow.add(rightNow.DAY_OF_MONTH, - 20);
	s_last20 = formatter.format(rightNow.getTime());

	rightNow = Calendar.getInstance();
	rightNow.add(rightNow.DAY_OF_MONTH, - 30);
	s_last30 = formatter.format(rightNow.getTime());
%>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/supplierportal/scripts/date_check.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/supplierportal/scripts/calendar.js"></SCRIPT>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Purchase Order Search Options</div>
			</td>
		</tr>
		</table>
	</td>
	<td width=30px valign=bottom><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" width="30" height="31" /></td>
	<td valign=bottom align=right>
		<table cellpadding="0" cellspacing="0" border=0>
			<tr>
				<td width=1000px height=1px class=lightShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1" /></td>
			</tr>
			<tr>
				<td height=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="2" /></td>
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
			<td align=right>Purpose</td>
			<td colspan=4><input maxlength=250 name="purpose" size=75 type=text onchange="upperCase(this);"></td>
		</tr>
		<tr>
			<td align=right>Order #</td>
			<td><input maxlength=30 name="poNumber" size=15 type=text></td>
			<td width=50px>&nbsp;</td>
			<td align=right>Order Date</td>
			<td>
			    <input type=text name="poDate" value="" onChange="checkDate( this );" size=15 maxLength=30>
				<a href="javascript: show_calendar('poDate', '<%=dateFormat%>');"><IMG src="<%=contextPath%>/supplierportal/images/calendar.gif" valign=bottom border=0></a>
			</td>
		</tr>
		<tr>
			<td align=right>Release</td>
			<td><input maxlength=30 name="release" size=15 type=text></td>
			<td width=50px>&nbsp;</td>
			<td align=right>Promised Date</td>
			<td>
			    <input type=text name="promisedDate" value="" onChange="checkDate( this );" size=15 maxLength=30>
				<a href="javascript: show_calendar('promisedDate', '<%=dateFormat%>');"><IMG src="<%=contextPath%>/supplierportal/images/calendar.gif" valign=bottom border=0></a>
			</td>
		</tr>
		<tr>
			<td align=right>Total</td>
			<td><input maxlength=30 name="total" size=15 type=text></td>
			<td width=50px>&nbsp;</td>
			<td align=right>Expiration Date</td>
			<td>
			    <input type=text name="expirationDate" value="" onChange="checkDate( this );" size=15 maxLength=30>
				<a href="javascript: show_calendar('expirationDate', '<%=dateFormat%>');"><IMG src="<%=contextPath%>/supplierportal/images/calendar.gif" valign=bottom border=0></a>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr>
	<td align=center>
		<br><br>
		<table border=0 cellpadding=2 cellspacing=0>
		<tr>
			<td align=right nowrap>Show Orders Added in the Last:</td>
			<td>
					<SELECT NAME="as_posted" TABINDEX="8">
						<OPTION value=""></OPTION>
						<OPTION value="<%=s_last2%>">2 days</OPTION>
						<OPTION value="<%=s_last5%>">5 days</OPTION>
						<OPTION value="<%=s_last10%>">10 days</OPTION>
						<OPTION value="<%=s_last15%>">15 days</OPTION>
						<OPTION value="<%=s_last20%>">20 days</OPTION>
						<OPTION value="<%=s_last30%>">30 days</OPTION>
					</SELECT>
			</td>
			<td width=50px>&nbsp;</td>
			<td align=right nowrap>Sort By:</td>
			<td>
					<SELECT NAME="as_sort" TABINDEX="9">
						<OPTION value=""></OPTION>
						<OPTION value="PoHeader_poDate" SELECTED>Order Date</OPTION>
						<OPTION value="PoHeader_poNumber">Order #</OPTION>
						<OPTION value="PoHeader_releaseNumber">Order Date</OPTION>
					</SELECT>
					<SELECT NAME="as_sort_opt" TABINDEX="10">
						<OPTION value=""></OPTION>
						<OPTION value="ASC" SELECTED>A</OPTION>
						<OPTION value="DESC">D</OPTION>
					</SELECT>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 width=680px>
<tr><td id="hiddenFields"></td></tr>
<tr>
	<td align=center width=50%>
		<a href="javascript: search(); void(0);">
		<img class=button src="<%=contextPath%>/supplierportal/images/button_browse.gif" border=0></a>
	</td>
	<td align=center width=50%>
		<a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/supplierportal/images/button_cancel.gif" tabindex=77 border=0></a>
	</td>
</tr>
</table>

<script value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var browseName = "<%=browseName%>";

	function search() {
		setFilterOptions();
		browse(browseName);
	}

	function setFilterOptions() {
		setOriginalFilter("PoHeader_vendorId", "=", frm.vendorId.value);
		setOriginalFilter("PoHeader_status", ">=", "<%=DocumentStatus.PO_AWARDED%>");

		if (!isEmpty(frm.purpose.value)) {
			setOriginalFilter("PoHeader_internalComments", "LIKE", "%" + frm.purpose.value + "%");
		}
		setOriginalFilter("PoHeader_poNumber", "=", frm.poNumber.value);
		setOriginalFilter("PoHeader_releaseNumber", "=", frm.release.value);
		setOriginalFilter("PoHeader_poDate", "=", frm.poDate.value);
		setOriginalFilter("PoHeader_promisedDate", "=", frm.promisedDate.value);
		setOriginalFilter("PoHeader_expirationDate", "=", frm.expirationDate.value);
		setOriginalFilter("PoHeader_poDate", ">=", frm.as_posted.options[frm.as_posted.selectedIndex].value);

		var sortCol	 = frm.as_sort.options[frm.as_sort.selectedIndex].value;
		var sortOrd	 = frm.as_sort_opt.options[frm.as_sort_opt.selectedIndex].value;
		setOriginalSort(sortCol, sortOrd);
	}

	function setFilterOptionsOld() {
		var myTable = document.getElementById("filterOptions");
		var myHiddenFields = document.getElementById("hiddenFields");
		var filterFields = document.all("as_filter_txt");
		var previousLogicalOperator = "AND";

		for (var i=0; i < filterFields.length; i++) {
			var filterColumn = frm.as_colname[i].value;
			var filterTxt = frm.as_filter_txt[i].value;
//alert("filterColumn = " + filterColumn);
//alert("filterTxt = " + filterTxt);
			if (!isEmpty(filterTxt)) {
				if (filterTxt.indexOf("%") >= 0) {
					setOriginalFilter(filterColumn, "LIKE", filterTxt);
				} else {
					setOriginalFilter(filterColumn, "=", filterTxt);
				}
			}
		}

		var posted	= frm.as_posted.options[frm.as_posted.selectedIndex].value;
		if (!isEmpty(posted)) {
			setOriginalFilter("RfqHeader_appDate", ">=", posted);
		}

		var sortCol	 = frm.as_sort.options[frm.as_sort.selectedIndex].value;
		var sortOrd	 = frm.as_sort_opt.options[frm.as_sort_opt.selectedIndex].value;
		if (!isEmpty(sortCol)) {
			setOriginalSort(sortCol, sortOrd);
		}
	}

// End Hide script -->
</script>

<%@ include file="/supplierportal/system/footer.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>