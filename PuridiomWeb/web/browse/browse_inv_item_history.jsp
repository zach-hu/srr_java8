<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<%
	String itemNumber = HiltonUtility.ckNull((String) request.getAttribute("InvItem_itemNumber"));
	String description = HiltonUtility.ckNull((String) request.getAttribute("InvItem_description"));
%>

<table border=0 width=680px>
<tr>
	<td width=150px align=right nowrap><b>Item Number:</b>&nbsp;</td>
	<td nowrap><%=headerEncoder.encodeForHTML(itemNumber)%></td>
</tr>
<tr>
	<td width=150px align=right><b>Description:</b>&nbsp;</td>
	<td><%=headerEncoder.encodeForHTML(description)%></td>
</tr>
</table>

<br>

<tsa:hidden name="browsePage" value="/browse/browse_inv_item_history.jsp"/>
<tsa:hidden name="InvItem_itemNumber" value="<%=itemNumber%>"/>
<tsa:hidden name="InvItem_description" value="<%=description%>"/>
<tsa:hidden name="itemAction" value="UPDATE"/>

<%@ include file="/browse/browse_form.jsp" %>

<table border=0 width=<%=formEntryWidth%>>
	<tr>
		<td align=center><div id="pxbutton"><a href="javascript: doSubmit('/inventory/inv_item.jsp', 'InvItemRetrieveById'); void(0);">Return</a></div></td>
	</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script
	setNavCookie("/browse/browse_inv_item_history.jsp", "BrowseRetrieveById", "<%=browseObject.getTitle()%>");
	hideArea("resetOriginalOption");

	function browse(x) {
		frm.browseName.value = x;

		doSubmit('browse/browse_inv_item_history.jsp', 'BrowseRetrieve');
	}

// End Hide script -->
</SCRIPT>


