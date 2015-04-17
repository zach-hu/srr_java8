<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ include file="/browse/browse_form.jsp" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/asset.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/budget.js"></SCRIPT>
<tsa:hidden name="browsePage" value="/browse/browse.jsp"/>
<tsa:hidden name="fromPage" value="/browse/browse.jsp"/>

<table border=0 width=680px>
<tr>
	<td align=center colspan=2>
		<a href="javascript: doSubmit('/browse/browse_commodity_tree_admin.jsp', 'BrowseCleanupByBrowseId;CommodityRetrieveTree');">Return to Tree View</a>
	</td>
</tr>
<tr><td colspan=2><br></td></tr>
<tr>
	<td width=50% align=center><a href="javascript: addCommodity(); void(0);"><img class=button src="<%=contextPath%>/images/button_add.gif" border=0 alt="Add"></a></td>
	<td align=center width=50%><a href="javascript: doSubmit('/admin/systemtables/system_tables.jsp','DoNothing');"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0></a></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	hideArea("resetOriginalOption");
	setNavCookie("/browse/browse.jsp", "BrowseRetrieveById", "<%=browseObject.getTitle()%>");
	setBrowseCookie("<%=browseObject.getBrowseName()%>");
	
	function returnMe(x) {
		var newInputField = "<input type='hidden' name='Commodity_commodity' value='" + x + "'>";

		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/commodity.jsp', 'CommodityRetrieveById');
	}
	
	function addCommodity() {
		doSubmit('admin/systemtables/commodity.jsp','DoNothing');	
	}

// End Hide script -->
</SCRIPT>