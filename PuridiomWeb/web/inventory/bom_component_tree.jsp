<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Hashtable" %>
<%@ page import="com.tsa.puridiom.entity.BomComponent" %>


<%@include file="/system/simple_treeview.jsp" %>

<%
	String s_itemnumber = (String) request.getAttribute("InvItem_itemNumber");
	String s_description = (String) request.getAttribute("InvItem_description");
	List		tvList = (List) request.getAttribute("bomComponentTree") ;
%>

<tsa:hidden name="InvItem_itemNumber" value="<%=s_itemnumber%>"/>
<tsa:hidden name="InvItem_description" value="<%=s_description%>"/>

<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="currentPage" value="/inventory/bom_component_treeview.jsp"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Component Treeview</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Parent Item:</b></td>
			<td width=100px nowrap><%=s_itemnumber%></td>
		</tr>
		</table>
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
<table border=0 width='100%' height='100%'>
	<tr><td height='100%' valign=top nowrap  style='overflow:auto;'>
<%
	TreeView tv = new TreeView();

	int rows = tvList.size() ;

	if ( rows >= 0)
	{
		tv.target="content";
		tv.setImagesUrl(contextPath + "/images");

		for (int row = 0; row < rows ; row++) {
			Hashtable tNode = (Hashtable) tvList.get(row);
			BomComponent bc = (BomComponent) tNode.get("bomComponent") ;
			String s_citem = bc.getComponentItem() ;
			String s_desc = bc.getDescription() ;
			String s_uom = bc.getUnitOfMeasure() ;
			String s_qty = bc.getUsageQty().toString() ;
			String s_level = (String) tNode.get("level");

			int level = Integer.parseInt(s_level) ;
			String s_text = "<table><tr><td valign=top width=100px height=30px>" + s_citem + "</td><td valign=top width=235px height=30px>" + s_desc + "</td><td valign=top width=35px height=30px>" + s_uom + "</td><td align=right valign=top width=35px height=30px>" + s_qty + "</td></tr></table>" ;
			tv.createItem(level, s_text) ;
		}

		out.print(tv.getTree());
	}

%>
	<br>

		<center>
			<A HREF="javascript: closeTree(); void(0);"><IMG TABINDEX=30 SRC="/puridiom/xchange/images/button_close.gif" BORDER="0"></A>
		</center>

</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

function refreshTree()
{
	parent.location.reload() ;
}

function closeTree()
{
	window.top.hidePopWin() ;
}

<%
	String		ux = "" ;
	for (int ix = 0; ix < tv.folderList.size(); ix++) {
		java.util.Hashtable ht = (java.util.Hashtable) tv.folderList.get(ix) ;
		if (ix == 0) ux = (String) ht.get("href") ;
		%>

		toggle('<%=(String) ht.get("nValue")%>' , '<%=(String) ht.get("pValue")%>') ;
	<%
	}
//	if (refreshright.equals("Y")) { %>
		<%=ux%>
	<%// } %>
</SCRIPT>

