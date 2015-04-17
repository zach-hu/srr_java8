<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="java.io.*" %>
<%@ page import="com.tsagate.properties.*" %>
<%ReadProperties rp = new ReadProperties();
Map propertiesList = rp.getPropertiesListByOid();
Set oidSet = propertiesList.keySet();
Iterator iterator = oidSet.iterator();%>
<table border="0">
	<%while (iterator.hasNext())
	{
		String oidDir = (String) iterator.next();
		if(!oid.equalsIgnoreCase("cvs"))
		{%>
			<tr>
				<th colspan="2"><%=oidDir.toUpperCase()%></th>
			</tr>
			<%File oidFiles[] = (File[])propertiesList.get(oidDir);
			for(int i =0; i < oidFiles.length; i++)
			{
				if(!oidFiles[i].getName().equalsIgnoreCase("cvs"))
				{%>
					<tr>
						<td></td>
						<td>	<a href="javascript: readThem('<%=oidDir%>', '<%=oidFiles[i].getName()%>'); void(0);"><%=oidFiles[i].getName() %></a></td>
					</tr>
				<%}
			}%>
		<%}
	}%>
</table>
<tsa:hidden name="propsFileName" value=""/>
<tsa:hidden name="oid" value="<%=oid %>"/>
<%@ include file="/system/footer.jsp" %>

<script language='Javascript1.2' type="text/javascript">
<!--
	var frm = document.purchaseform;

	function readThem(currentoid, file)
	{
		frm.oid.value = currentoid;
		frm.propsFileName.value = file;

		//set cursor to hourglass while the system is processing
	    document.body.style.cursor = "wait";
	    frm.target = "_self"

	    doSubmit('/tsaadmin/edit_properties.jsp', 'DoNothing');
	}
//-->
</script>