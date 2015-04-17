<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.util.List.*" %>
<%@ page import="java.math.*" %>

<%@include file="/system/simple_treeview.jsp" %>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String invModule = propertiesManager.getProperty("Modules", "Standard Inventory", "N");
	String invItemNumber = HiltonUtility.ckNull((String) request.getAttribute("InvItem_itemNumber"));
	String invDescription =  HiltonUtility.ckNull((String) request.getAttribute("InvItem_description"));
%>

<tsa:hidden name="InvItem_itemNumber" value="<%=invItemNumber%>"/>
<tsa:hidden name="InvItem_description" value="<%=invDescription%>"/>
<tsa:hidden name="bomType" value=""/>
<tsa:hidden name="bomAction" value=""/>
<tsa:hidden name="BomMethod_icMethod" value=""/>
<tsa:hidden name="BomRouting_icRouting" value=""/>
<tsa:hidden name="BomTask_icRouting" value=""/>
<tsa:hidden name="BomComponent_icComponent" value=""/>
<tsa:hidden name="BomManufacturer_icComponent" value=""/>

<tsa:hidden name="itemAction" value="UPDATE"/>



<table border=0 width='100%' height='100%'>
<tr><td height='100%' valign=top width=200 nowrap  style='overflow:auto;'>
<CENTER CLASS="HDR14" >Bill of Materials</CENTER>
<CENTER CLASS="HDR14">View</CENTER><BR>
<%
	String component = "documents";
	String methodname = "retrieveTreeDocuments";
	String extra = "";
	String refreshright = request.getParameter("refreshright") ;

	if (refreshright == null) refreshright = "Y" ;

	TreeView tv = new TreeView();


	if ( retval >= 0)
	{
		String surl = application.getInitParameter("docUrl");
		String stext = "";


		if (surl == null)
		{
			surl = "/puridiom/xchange/upload/docs/";
		}
		else if (! surl.endsWith("/"))
		{
			surl += "/";
		}

		ArrayList  treeArray = (ArrayList) oObj.getObject("treeArray") ;

		tv.target="content";
		tv.setImagesUrl("/puridiom/xchange/treeview/images");

		int rows = treeArray.size() ;
		for (int row = 0; row < rows ; row++) {
			Hashtable tNode = (Hashtable) treeArray.get(row);


			String shic = (String) tNode.get("header_ic");
			String sfile = (String) tNode.get("filename") ;
			String	sfolderlong = (String) tNode.get("folderlong") ;
			String	sfoldername = (String) tNode.get("foldername") ;
			String	stitle = (String) tNode.get("title") ;
			String	slevel = (String) tNode.get("level");
			String sdocurl = null ;
			int level = Integer.parseInt(slevel) ;

			if (sfile.equals("!")) {
				// Folder
				sdocurl = "/puridiom/xchange/documents/documents.jsp?docic=" + shic + "&flong=" + sfolderlong + "&fname=" + sfoldername ;
				tv.createItem(level, sfoldername, sdocurl, stitle) ;
			} else {
				if (sfile.indexOf(".MSG") > 0) {
					sfile.replaceAll(".MSG",".msg") ;
				}
				sdocurl = surl + sfile;
				tv.createItem(level, stitle, sdocurl, stitle) ;
			}
		}

		out.print(tv.getTree());
	}

%>
	<br>

		<center>
			<A HREF="javascript: closeTree(); void(0);"><IMG TABINDEX=30 SRC="<%=contextPath%>/images/button_close.gif" BORDER="0"></A>
		</center>

	</td><td valign=top width="99%">
	<iframe name=content frameborder=0 width="100%" src="" height="100%"></iframe>
	</td></tr></table>
</body>

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
// parent.Content.location.href = chref ;

// end hiding contents -->
</SCRIPT>


</html>
