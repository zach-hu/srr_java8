<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ page import="java.math.BigDecimal" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/filter.js"></script>

<%
	String	formField = (String) request.getAttribute("formField");
	String	fromPage = (String) request.getAttribute("fromPage");
	String	row = (String) request.getAttribute("index");
	if (formField == null)	{	formField = "";	}
	if (fromPage == null)	{	fromPage = "";	}
	if (row == null)		{	row = "0";		}
%>

<div id=pageForm style="display:none;visibility:hidden;">
<%@ include file="/browse/browse_form.jsp" %>

<tsa:hidden name="browsePage" value="/browse/browse_commodity_popup.jsp"/>
<tsa:hidden name="browseName" value=""/>
<tsa:hidden name="formField" value="<%=formField%>"/>
<tsa:hidden name="index" value="<%=row%>"/>
<tsa:hidden name="fromPage" value="<%=fromPage%>"/>

<table width=680px border=0>
<tr>
	<td align=center>
<%	if (browse.getBrowseName().equals("subcommodity")) {%>
		<a href="javascript: doSubmit('/browse/browse_subcommodity_tree.jsp', 'BrowseCleanupByBrowseId;SubCommodityRetrieveTree');">Return to Tree View</a>
<%	} else {%>
		<a href="javascript: doSubmit('/browse/browse_commodity_tree.jsp', 'BrowseCleanupByBrowseId;CommodityRetrieveTree');">Return to Tree View</a>
<%	}%>
	</td>
</tr>
<tr><td align=center><br></td></tr>
<tr>
	<td align=center>
		<a href="javascript: doSubmit('/system/close_window.jsp', 'BrowseCleanupByBrowseId');"><img class=button src="<%=contextPath%>/images/button_close.gif" border=0 alt="Close"></a>
	</td>
</tr>
</table>

</div>
<div id=pageLoading style="display:block;visibility:visible;">
<table border=0 cellspacing=0 cellpadding=0 width=100%>
<tr><td width=100% align=center valign=top><br><b>Loading Page... Please wait.</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border=1 width=200px height=15px></td></tr>
</table>
</div>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var rowSelect;
	var selectedData;
	var row = "<%=row%>";

	hideArea("resetOriginalOption");
	hideArea("resetFilterOptions");

	 if (filterSet) {
		displayArea("resetOption");
	} else {
		hideArea("resetOption");
	}

	function returnMe(x) {
		var fld = "<%=formField%>";
		selectedData = x;

		var Ocolumns = window.parent.frm.elements.item(fld);
		if (Ocolumns.length==undefined) {
			window.parent.frm.elements[fld].value = selectedData;
		} else {
			window.parent.frm.elements(fld)[row].value = x;
		}
		returnOthers(fld);

		doSubmit('/system/close_window.jsp', 'BrowseCleanupByBrowseId');
	}

	function returnOthers(formField) {
		if (window.parent.frm.elements("as_commodityName") != undefined) {
			var Ocolumns = window.parent.frm.elements.item("as_commodityName");
<%	if (browse.getBrowseName().equals("subcommodity")) {%>
			if (Ocolumns.length==undefined) {
				window.parent.frm.elements["as_commodityName"].value = frm.SubCommodity_description.value;
			} else {
				window.parent.frm.elements("as_commodityName")[row].value = frm.SubCommodity_description[rowSelect].value;
			}
//			window.parent.frm.elements("as_commodityName").value = frm.SubCommodity_description[rowSelect].value;
<%	} else {%>
			if (Ocolumns.length==undefined) {
				window.parent.frm.elements["as_commodityName"].value = frm.Commodity_description.value;
			} else {
				window.parent.frm.elements("as_commodityName")[row].value = frm.Commodity_description[rowSelect].value;
			}
//			window.parent.frm.elements("as_commodityName").value = frm.Commodity_description[rowSelect].value;
<%	}%>
		}

		if (window.parent.frm.elements("RequisitionLine_udf10Code") != undefined) {
			var Ocolumns = window.parent.frm.elements.item("RequisitionLine_udf10Code");
			<%	if (browse.getBrowseName().equals("subcommodity")) {%>
				window.parent.frm.elements["RequisitionLine_udf10Code"].value = frm.SubCommodity_commodity[rowSelect].value;
			<%	} else {%>
				if (Ocolumns.length==undefined) {
					window.parent.frm.elements["RequisitionLine_udf10Code"].value = frm.Commodity_commodityType[rowSelect].value;
	
				} else {
					window.parent.frm.elements["RequisitionLine_udf10Code"].value = frm.Commodity_commodityType[rowSelect].value;
	
				}
			<%	}%>
		}

		if (formField.indexOf("_commodityCode") > 0) {
			if (window.parent.frm.as_authbyName) {
				window.parent.frm.as_authbyName.value = frm.UserProfile_displayName[rowSelect].value;
			}
		}
	}

	function browse(x) {
		frm.browseName.value = x;

		doSubmit('/browse/browse_popup.jsp', 'BrowseRetrieve');
	}

	hideArea("pageLoading");
	displayArea("pageForm");

// End Hide script -->
</SCRIPT>