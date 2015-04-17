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
		<a href="javascript: doSubmit('/browse/browse_subcommodity_tree_admin.jsp', 'BrowseCleanupByBrowseId;SubCommodityRetrieveTree');">Return to Tree View</a>
	</td>
</tr>
<tr><td colspan=2><br></td></tr>
<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: addCommodity(); void(0);">Add</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/admin/systemtables/system_tables.jsp','DoNothing'); void(0);">Return</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	hideArea("resetOriginalOption");
	setNavCookie("/browse/browse.jsp", "BrowseRetrieveById", "Order History");
	setBrowseCookie("${esapi:encodeForJavaScript(browseObject.browseName)}");

	function returnMe(x) {
		var newInputField = "<input type='hidden' name='SubCommodity_commodity' value='" + x + "'>";

		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/subcommodity.jsp', 'SubCommodityRetrieveById');
	}

	function addCommodity() {
		doSubmit('admin/systemtables/subcommodity.jsp','DoNothing');
	}

// End Hide script -->
</SCRIPT>