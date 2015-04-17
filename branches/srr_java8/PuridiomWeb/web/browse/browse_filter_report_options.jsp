<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.browse.*" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	BrowseObject browseObject = (BrowseObject) request.getAttribute("browseObject");
	BrowseColumn browseColumns[] = browseObject.getBrowseColumns();
	String reportModule = (String)request.getAttribute("reportModule");
%>
<br>

<table width=680px cellpadding=0 cellspacing=0 border=0>
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
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right>
		<table cellpadding="0" cellspacing="0" border=0>
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
<table border=0 cellpadding=2 cellspacing=0 width=680px align="center">
<tr>
	<td align=center>
        <table >
        	<tr >
				<td valign="middle"><b>Format:&nbsp;&nbsp;</b></td>
        		<td valign="middle"><input type="radio"  name="format" value="PDF" checked >pdf&nbsp; </td>
        		<td valign="middle"><input type="radio" name="format" value="HTM">html&nbsp;</td>
        		<td valign="middle"><input type="radio" name="format" value="XLS">xls&nbsp;</td>
        	</tr>
		</table>
	</td>
</tr>
</table>

<%@ include file="/browse/browse_filter_options_form.jsp" %>
<%@ include file="/system/footer.jsp" %>
<script value=JavaScript>
<!-- Hide script

function returnToList(){
	setReports('<%=reportModule%>');
}

// End Hide script -->
</script>

<%@ include file="/system/prevent_caching.jsp" %>