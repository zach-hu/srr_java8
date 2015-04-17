<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="java.math.BigDecimal" %>
<%@ include file="/browse/browse_form.jsp" %>
<%
	String	s_acct_type = (String) request.getAttribute("formType");
	String	s_alloc_method = (String) request.getAttribute("allocMethod");
	String	s_from_page = (String) request.getAttribute("frompage");

	s_acct_type = HiltonUtility.ckNull(s_acct_type);
	s_alloc_method = HiltonUtility.ckNull(s_alloc_method);
	s_from_page = HiltonUtility.ckNull(s_from_page);
%>

<tsa:hidden name="browsePage" value="/browse/browse_std_account.jsp"/>
<tsa:hidden name="browseName" value=""/>
<tsa:hidden name="formType" value="<%=s_acct_type%>"/>
<tsa:hidden name="frompage" value="<%=s_from_page%>"/>
<tsa:hidden name="allocMethod" value="<%=s_alloc_method%>"/>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formWidth%>>
<tr>
	<td width=100% align=center><div id="pxbutton"><a href="javascript: returnAbort(); void(0);">Return</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	hideArea("resetOriginalOption");

	function addStdAccount(ic)
	{
		var pageName = "/base/get_acct_info.jsp";

		var myCell = document.getElementById("hiddenFields");
		myCell.innerHTML = "<INPUT TYPE=\"HIDDEN\" NAME=\"StdAccount_icHeader\" value=\"" + ic + "\">";

		doSubmit(pageName, 'BrowseCleanupByBrowseId;AccountRetrieveStandardById');
	}

	function returnAbort()
	{
		var myTable = window.parent.document.getElementById("accounts");
		var rowCount= myTable.rows.length - 1;

		if (rowCount == 0)
		{
			window.parent.addNew();
		}

		doSubmit('/system/close_window.jsp', 'BrowseCleanupByBrowseId');
		//window.top.hidePopWin();
	}

// End Hide script -->
</SCRIPT>


