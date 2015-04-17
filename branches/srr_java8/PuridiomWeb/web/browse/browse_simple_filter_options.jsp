<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.browse.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	BrowseObject browseObject = (BrowseObject) request.getAttribute("browseObject");
	BrowseColumn browseColumns[] = browseObject.getBrowseColumns();

	/*	added for System Table */
	String tableType = HiltonUtility.ckNull((String) request.getAttribute("tableType"));
	if (!HiltonUtility.isEmpty(tableType))
	{
		String title = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, tableType, "");
		if (!HiltonUtility.isEmpty(title))
		{
			browseObject.setTitle(title + " Browse");
		}
	}
%>

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
	<td valign=bottom width="3px"><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right width="*">
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

<tsa:hidden name="filterType" value="SIMPLE"/>

<%@ include file="/browse/browse_simple_filter_form.jsp" %>
<%@ include file="/system/footer.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>