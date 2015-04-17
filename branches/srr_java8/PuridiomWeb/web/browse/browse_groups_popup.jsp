<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ page import="java.math.BigDecimal" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/filter.js"></script>

<tsa:hidden name="browseName" value=""/>
<tsa:hidden name="browsePage" value="/browse/browse_groups_popup.jsp"/>

<%@ include file="/browse/browse_form.jsp" %>

<%	
	String	formField = (String) request.getAttribute("formField");
	String	fromPage = (String) request.getAttribute("fromPage");
	String	row = (String) request.getAttribute("index");
	if (fromPage == null)	{	fromPage = "";	}
	if (row == null)			{	row = "0";			}
%>

<table width=100% border=0>
<tr>
	<td align=center><br><div id="pxbutton"><a href="javascript: window.top.hidePopWin();">Close</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script
	
	frm = document.purchaseform;

	var rowSelect;
	var selectedData;
	var row = "<%=row%>";

	hideArea("resetOriginalOption");
	 if (filterSet) {
		displayArea("resetFilterOptions");
	} else {
		hideArea("resetFilterOptions");
	}
	
	function selectGroup(gid, desc) {
		window.parent.addRole(gid, desc);
		window.top.hidePopWin();
	}

	function browse(x) {
		frm.browseName.value = x;

		doSubmit('/browse/browse_groups_popup.jsp', 'BrowseRetrieve');
	}
	
// End Hide script -->
</SCRIPT>


