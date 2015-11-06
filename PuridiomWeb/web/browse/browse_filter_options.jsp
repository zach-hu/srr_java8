<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.browse.*" %>
<%@ page import="com.tsagate.foundation.utility.Dates"%>
<%
	BrowseObject browseObject = (BrowseObject) request.getAttribute("browseObject");
	BrowseColumn browseColumns[] = browseObject.getBrowseColumns();
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

	/*	added for System Table */
	String tableType = HiltonUtility.ckNull((String) request.getAttribute("tableType"));
	if (!HiltonUtility.isEmpty(tableType)  && browseObject.getBrowseName().indexOf("stdtable") >= 0)
	{
		String title = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, tableType, "");
		if (!HiltonUtility.isEmpty(title))
		{
			browseObject.setTitle(title + " Browse");
		}
	}
	String appMenuAction = HiltonUtility.ckNull((String) request.getAttribute("appMenuAction"));
	String sVendor_Id = HiltonUtility.ckNull((String) request.getAttribute("Vendor_vendorId"));
	String fromPage = HiltonUtility.ckNull((String) request.getAttribute("fromPage"));
	if(HiltonUtility.isEmpty(fromPage))
	{
		fromPage = HiltonUtility.ckNull((String) request.getAttribute("OriginalFromPage"));
	}

%>

<tsa:hidden name="Vendor_vendorId" value="<%=sVendor_Id%>"/>
<tsa:hidden name="appMenuAction" value="<%=appMenuAction%>"/>
<tsa:hidden name="OriginalFromPage" value="<%=fromPage%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=150px>
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
	<td valign=bottom width=3px><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right width="100%">
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
<br>
<br>

<%@ include file="/browse/browse_filter_options_form.jsp" %>
<%@ include file="/system/footer.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>